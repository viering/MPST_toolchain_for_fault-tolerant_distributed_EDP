package event_lang.network.netty.internal

import java.net.InetSocketAddress
import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicBoolean

import event_lang._
import event_lang.network.EndPointMessages.EPtoEPMSG
import event_lang.network.SpawnMain
import event_lang.network.netty.EPAddr
import event_lang.network.netty.internal.SetupMessages._
import event_lang.semantic.CommonTypes.PartID
import event_lang.intern.logging.Logger
import event_lang.types._
import io.netty.bootstrap.{Bootstrap, ServerBootstrap}
import io.netty.channel._
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.{NioServerSocketChannel, NioSocketChannel}


class NettyConnectionManger(val role: RoleSet, val msgQueue: NettyLayerRcvAPi[EPtoEPMSG],val myLAdd : String, val myCID : Option[String]) extends Logger{
  self =>

  val gBootstrapConnection = new NioEventLoopGroup()
  val gEPtoEPGroup = new NioEventLoopGroup()

  var idToAdd: Map[Int, EPAddr] = Map[Int, EPAddr]()
  var idToRS = Map[Int, RoleSet]()
  var idToChannel = collection.concurrent.TrieMap[Int, Channel]()
  var id: Int = -1
  var myServerAddr: EPAddr = null
  //var idToRRole: Map[Int, RRole] = null


  var spawnMain: SpawnMain = null

  val started = new AtomicBoolean(false)

  class EPNetworkAPI extends NettyLayerSndApi[EPtoEPMSG] {
    private lazy val __myID = id

    def inputQueue: NettyLayerRcvAPi[EPtoEPMSG] = self.msgQueue

    override def ready: Boolean = started.get() && spawnMain.idToRoleSet.size == idToChannel.size

    override def mainSessionInfo: SpawnMain = self.spawnMain

    override def snd(toId: Int, msg: EPtoEPMSG) = {
      idToChannel(toId).writeAndFlush(msg)
    }

    override def myID: Int = {
      assert(__myID != -1 && __myID == id)
      __myID
    }

    override def broadcast(msg: EPtoEPMSG): Unit = {
      idToChannel.values.foreach(_.writeAndFlush(msg))
    }

    override def shutdown(): Unit = {
      for ((k, c) <- idToChannel)
        c.close()
    }
  }

  private def bootstrap(g: EventLoopGroup, facHdl: () => ChannelHandler): Bootstrap = {
    val b = new Bootstrap()
    b.group(g)
      .channel(classOf[NioSocketChannel])
      .handler(new ChannelInitializer[SocketChannel]() {
        override def initChannel(ch: SocketChannel): Unit = {
          val p = ch.pipeline()
          p.addLast(new ChannelInitializer[SocketChannel] {
            override def initChannel(ch: SocketChannel): Unit = {
              val p = ch.pipeline()
              p.addLast(new ObjDeserializeDecoder).
                addLast(new ObjSerializeEncoder).
                addLast(facHdl())
            }
          })
        }
      })
    b
  }

  private def connect(ePAddr: EPAddr, id: PartID): Unit = {
    //assert(self.id != -1)
    val cEP = bootstrap(gEPtoEPGroup, () => {
      new EPtoEPHandler()
    }).connect(ePAddr.addr, ePAddr.port)

    cEP.addListener((future: ChannelFuture) => {
      debug(s"${self.id} has connected to $id @ $ePAddr")
      val c = cEP.channel()
      val res = self.idToChannel.put(id, c)
      assert(res.isEmpty)
    })
  }


  def start(to: EPAddr): EPNetworkAPI = {
    val bootStrap = new ServerBootstrap()
    bootStrap
      .localAddress(myLAdd,0)
      .group(gBootstrapConnection)
      .channel(classOf[NioServerSocketChannel])
      .childHandler(new ChannelInitializer[SocketChannel] {
        override def initChannel(ch: SocketChannel): Unit = {
          val p = ch.pipeline()
          p.addLast(new ObjDeserializeDecoder).
            addLast(new ObjSerializeEncoder).
            addLast(new EPtoEPHandler())
        }
      })
      .childOption(ChannelOption.SO_KEEPALIVE, Boolean.box(true))

    val fServerChannel = bootStrap.bind(myLAdd,0).sync().channel() //.closeFuture()//.sync()

    //fServerChannel.await()
    val addr = fServerChannel.localAddress().asInstanceOf[InetSocketAddress]
    debug(s"the address ${fServerChannel.localAddress().asInstanceOf[InetSocketAddress].getHostString} : ${addr.toString}")
    this.myServerAddr = EPAddr(addr.getHostString, addr.getPort)
    val group = new NioEventLoopGroup()

    bootstrap(group, () => {
      new EPtoBootstrapHandler(fServerChannel)
    }).connect(to.addr, to.port) //.sync().channel().closeFuture().sync()
    val sndlayer = new EPNetworkAPI()
    msgQueue.init(sndlayer)
    sndlayer
  }


  class EPtoBootstrapHandler(val c: Channel) extends SimpleChannelInboundHandler[NetworkSetupMSG] {

    import io.netty.channel.ChannelHandlerContext

    override def channelActive(ctx: ChannelHandlerContext): Unit = {
      assert(myServerAddr != null)
      val msg = Connect(myServerAddr, role, myCID)
      debug(s"${myServerAddr.addr} -- $msg")
      ctx.writeAndFlush(msg)
    }

    override def channelReadComplete(ctx: ChannelHandlerContext): Unit = {
      ctx.flush
    }

    override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
      cause.printStackTrace()
      ctx.close
    }


    override def channelRead0(ctx: ChannelHandlerContext,
                              msg: NetworkSetupMSG): Unit = {
      msg match {
        case BCastUpdate(eps) =>
          if (self.id == -1 && eps.contains(self.myServerAddr)) {
           // assert(eps.contains(self.myServerAddr),s"$eps must contain my addr: ${self.myServerAddr}")
            self.id = eps(self.myServerAddr)._1
          }
          eps.foreach({ case (k, v) =>
            if (!self.idToAdd.contains(v._1)) {
              self.idToAdd = self.idToAdd + (v._1 -> k)
              self.idToRS += (v._1 -> v._2)
              connect(k, v._1)
            }
                      })
        case m@SignalSpawnMain(mainID, idToRole, idToRRoleSet,iToc) =>
          debug(s"[${self.myServerAddr}] received: $m -- will send Acc if/once i established a connection with all my peers")
          assert(self.id != -1)
          self.spawnMain = SpawnMain(0, mainID, idToRole, idToRRoleSet,iToc)
          gBootstrapConnection.submit(new Callable[Boolean] {
            override def call(): Boolean = {
              while(self.idToChannel.size != idToRRoleSet.size){
              }
              self.started.set(true)
              debug(s"[${self.myServerAddr}] Send out acc")
              ctx.channel().writeAndFlush(AccRootSessionSpawn(self.id))
              true
            }
          })
        case m@SignalBootstrapCompletion() =>
          ctx.channel().close()
        //ctx.close()
      }
    }
  }

  class EPtoEPHandler extends SimpleChannelInboundHandler[EPtoEPMSG] {

    import io.netty.channel.ChannelHandlerContext

    override def channelRead0(ctx: ChannelHandlerContext,
                              msg: EPtoEPMSG): Unit = {
      trace(s"Rcv: $msg")
      msgQueue.rcvMSG(msg)
    }
  }

}






