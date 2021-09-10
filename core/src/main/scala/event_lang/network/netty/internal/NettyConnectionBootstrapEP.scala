package event_lang.network.netty.internal

import java.net.InetSocketAddress
import java.util.concurrent.atomic.{AtomicBoolean, AtomicInteger}

import event_lang._
import event_lang.network.netty.EPAddr
import event_lang.network.netty.internal.SetupMessages._
import event_lang.semantic.CommonTypes.PartID
import event_lang.intern.logging.Logger
import event_lang.types._
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.group.DefaultChannelGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel._
import io.netty.util.concurrent.GlobalEventExecutor


trait CreateID{
  def createID(r : RoleSet) : PartID
}
class CreateIncIDs extends CreateID{
  val aInt = new AtomicInteger(0)
  override def createID(r: RoleSet): PartID = aInt.incrementAndGet()
}

class NettyConnectionBootstrapEP(val myAddr: EPAddr,
                                 canSpawnMain: (Iterable[(Int, RoleSet)], Iterable[(Int, String)]) => Option[SignalSpawnMain],
                                createID : CreateID = new CreateIncIDs()) extends Logger {

  val epChannelReg =
    new DefaultChannelGroup(GlobalEventExecutor.INSTANCE)


  val notSpawned = new AtomicBoolean(true)

  val conReg = collection.concurrent.TrieMap[EPAddr, (Int, RoleSet)]()
  val pIDtoCId = collection.concurrent.TrieMap[Int, String]()
  var numAccsNeeded = 0
  val numAccs = new AtomicInteger(0)
  var mainChan: Channel = null


  def start(): Unit = {
    val group = new NioEventLoopGroup()
    val bootStrap = new ServerBootstrap()
    bootStrap
      .group(group)
      .channel(classOf[NioServerSocketChannel])
      .localAddress(
        new InetSocketAddress(myAddr.addr, myAddr.port))
      .childHandler(new ChannelInitializer[SocketChannel] {
        override def initChannel(ch: SocketChannel): Unit = ch.pipeline().addLast(new ObjDeserializeDecoder).
          addLast(new ObjSerializeEncoder).
          addLast(new MeshHandler())
      })
      .childOption(ChannelOption.SO_KEEPALIVE, Boolean.box(true))

    mainChan = bootStrap.bind().sync().channel()
    mainChan.closeFuture().sync()
  }




  class MeshHandler extends SimpleChannelInboundHandler[NetworkSetupMSG] {

    override def channelActive(ctx: ChannelHandlerContext): Unit = {
      epChannelReg.add(ctx.channel())
    }

    override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
      Console.err.println(s"we discarded ${cause.getStackTrace.mkString(",")}")
      cause.printStackTrace()
      System.exit(-1)
    }

    override def channelRead0(ctx: ChannelHandlerContext,
                              msg: NetworkSetupMSG): Unit = msg match {
      case msg@Connect(adr, r, cID) =>
        trace(s"[BootstrapEP] Connection Setup: $msg")
        val add = ctx.channel().remoteAddress().asInstanceOf[InetSocketAddress].getAddress.getAddress
        val id = createID.createID(r)
        val conRegSize = conReg.size
        assert(!conReg.contains(adr))
        val check = conReg.put(adr, (id, r))
        assert(conRegSize < conReg.size)
        if(cID.nonEmpty)
          pIDtoCId.put(id,cID.get)
        assert(check.isEmpty)

        val m = BCastUpdate(conReg.toMap)
        trace(s"[BootstrapEP] Connection Setup: $msg --update msg: $m")
        epChannelReg.flushAndWrite(m)
        epChannelReg.flush()


        val msp = canSpawnMain(conReg.values,pIDtoCId)
        if (msp.nonEmpty && notSpawned.compareAndSet(true, false)) {
          debug(s"spawn main with ${msp.get} : ${epChannelReg.size()}")
          val fu = epChannelReg.flushAndWrite(msp.get)
          numAccsNeeded = conReg.size
        }
      case AccRootSessionSpawn(_) =>
        val inc = numAccs.incrementAndGet()
        trace(s"Received $msg -- ($inc accs sofar) -- ${ctx.channel().metadata()}")

        if (inc >= numAccsNeeded) {
          info(s"[$myAddr] Connected to all participants")
          epChannelReg.flushAndWrite(SignalBootstrapCompletion())
          epChannelReg.close()
          mainChan.close()
        }

    }
  }
}
