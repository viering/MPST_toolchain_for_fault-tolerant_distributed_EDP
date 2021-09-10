package example.TestProtocols.pingpong_spawn

import java.util.Calendar
import event_lang.{EndPoint, _}
import EndPoint.SpawnMainSelection
import dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPoint, AbstractEndPointTesting, ChannelTypeSubS, TState}
import event_lang.types.{Role, RoleSet}
import example.TestProtocols.pingpong_spawn.PingPongSpawn.A.{EPType_A, __EPType_A}
import example.TestProtocols.pingpong_spawn.PingPongSpawn.B.{EPType_B, __EPType_B}
import example.TestProtocols.pingpong_spawn.PingPongSpawn.MESSAGES.Main.{FailB, LMain}
import example.TestProtocols.pingpong_spawn.PingPongSpawn.MESSAGES.PingPong._
import example.TestProtocols.pingpong_spawn.PingPongSpawn._
import example.util.NettyEPAddr
import semantic.{Participant, _}

import scala.util.Random

/*
{
  "{" +
    "PingPong (a : A, b : B, _bb : B) = {"
      "bb -> a : Start(). mu t. a -> b : Ping(m:String). b -> a : Pong(m :String). t" +
     with
      bb@a. a -> b : FailD().0
    };
    Main (a : A, _b : B,B) = {
        a -> b : LMain(). spawn PingPong(a,b,_B).0
      with
        b@a. a -> B : FailB().0
    }
 */


case class AState() extends TState

class EP_A(val block: (AbstractChannelType, AState, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, AState, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_A[AState] with AbstractEndPointTesting[__EPType_A,AState ]{
  override val roleSet: RoleSet = RoleSet("A")
  override val subs: Seq[ChannelTypeSubS] = A.subs

  override def onStartUp: AState = AState()

  import example.TestProtocols.pingpong_spawn.PingPongSpawn.A.{Main_a_A => main, PingPong_a_A => pp}


  override val receive = ELoop(
    /*
    Main
     */

    register(main.SndLMain, main.SndLMain) { case (s, c) =>
      println(s"[a_main] Snd LMain() to b")
      (s, c.sndTob_B(LMain()))
    },


    //reg on entry / leave a local type position
    registerStatic(main.SpawnPingPong) {
      case s =>
        println("[a_main] Spawned PingPong")
        s
    },
    register(main.Failed_b_B, main.SndFailB) {
      case (s, c) => {
        println(s"[a_main] ### FHandling: b@A")
        (s, c.failed_b_B().sndToB(FailB()))
      }
    },
    /*
    Ping Pong
     */
    λ(pp.RcvStart) {
      case c => c ? {
        case (m,c2)=>
        println("[a_ping_pong]rcv Start")
        c2
    }},
    register(pp.SndPing) {
      case (s, c) => {
        println(s"[a_ping_pong] Snd Ping to b")
        (s, c.sndTob_B(Ping(s"Ping_${Calendar.getInstance().getTime()}")))
      }
    }    ,
    λ(pp.RcvPong) {
      case c => c ? {
        case (m,c2)=>
        Thread.sleep(100)
        println(s"[a_ping_pong] rcv: $m")
        c2
    }},
    register(pp.Failed_bb_B, pp.SndFailD) {
      case (s, c) =>
        println(s"[a_ping_pong] ### FHandling: d@A")
        (s, c.failed_bb_B().sndTob_B(FailD()))
    }
    )
}

class EP_B(val block: (AbstractChannelType, AState, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, AState, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_B[AState] with AbstractEndPointTesting[__EPType_B,AState]{
  override def onStartUp: AState = AState()

  override val subs: Seq[ChannelTypeSubS] = B.subs

  import example.TestProtocols.pingpong_spawn.PingPongSpawn.B.{
    Main_b_B => main, Main_B => mainB, PingPong_b_B => pp, PingPong_bb_B =>
    bb_pp
  }


  override val receive = ELoop(
    /*
      Main
    */
    λ(main.RcvLMain) {
      case c => c ? {
        case (m,c2)=>
      println(s"[b_main] rcv $m from a")
      c2
    }},

    registerStatic(main.SpawnPingPong)({
      case s =>
        println("[b_main] Spawned PingPong")
        s
    }),

    λ(mainB.RcvFailB) {
      case c => c ? {
        case (m, c2) =>
          println("[B_main] RcvFailB")
          c2
      }},

    /*
      Ping Pong (b)
    */
    register(pp.SndPong) {
      case (s, c) => {
        println(s"[b_ping_pong] snd Pong to a")
        (s, c.sndToa_A(Pong(s"Pong_${Calendar.getInstance().getTime()}")))
      }
    },
    λ(pp.RcvPing) {
      case c => c ? {
        case (m,c2)=>
          Thread.sleep(100)
          println(s"[b_ping_pong] rcv: $m")
         c2
      }
    },
    λ(pp.RcvFailD) {
      case c =>
       c ? {case (m,c2) =>
         println(s"[b_ping_pong] I received $m")
         c2
       }
    }
    ,

    register(bb_pp.SndStart) {
      case (s, c) =>
        println("[bb_pp] snd Start")
        (s, c.sndToa_A(Start()))
    }
    )

  override val roleSet: RoleSet = RoleSet("B")
}

object PingPong_Spawn_ImplImpl {
  def main(args: Array[String]): Unit = {


    //    var pId = 1
    //
    //    def createParticipants(r: Role, num: Int): Set[Participant] = {
    //      (1 to num).map(_ => Participant({
    //                                        pId += 1
    //                                        pId
    //                                      }, r)).toSet
    //    }
    //
    //    def createConfig(s: Session, p: Participant, sessionType: AbstractChannelType with
    //    ChannelTypeSubS): Config = {
    //      val hdl = sessionType.children.head
    //      val dBlock = hdl.children.head
    //      val fBlock = hdl.children.tail.head
    //      Config(s, p, dBlock, fBlock)
    //    }

    val rsA = RoleSet("A")
    val rsB = RoleSet("B")


    /**
      * bootstrap ep
      */
    new Thread(() => {
      EndPoint.createNettyConnectionBootstrapManger(NettyEPAddr.DEFAULT_BOOTSTRAP_ADDR, rsToIDs => {
        if (rsToIDs.getOrElse(rsA, Set()).size >= 1 &&
          rsToIDs.getOrElse(rsB, Set()).size >= 2) {
          Some(SpawnMainSelection(
            Participant(rsToIDs(rsA).head, Role("a", RoleSet("A"))),
            Participant(rsToIDs(rsB).head, Role("b", RoleSet("B")))))
        } else {
          None
        }
      })
    }).start()

    /**
      * End Points
      */


    val failB = Random.nextBoolean()
    //var epB: OperationalSemantic[Any,Any] = null

    //A
    new Thread(() => {
      //println("Start A")
      EndPoint.createEPusingNetty(new EP_A(), NettyEPAddr.DEFAULT_BOOTSTRAP_ADDR, "127.0.0.1").start()
      println(s"A done")
    }).start()
    //B
    new Thread(() => {
      //println("Start A")
      val epB = EndPoint.createEPusingNetty(new EP_B(), NettyEPAddr.DEFAULT_BOOTSTRAP_ADDR, "127.0.0.1")
      epB.start()
      println(s"B done")
    }).start()


    //B2
    new Thread(() => {
      //println("Start A")
      val epB = EndPoint.createEPusingNetty(new EP_B(), NettyEPAddr.DEFAULT_BOOTSTRAP_ADDR, "127.0.0.1")
      epB.start()
      println(s"B done")
    }).start()


    Thread.sleep(4000)


    //    for (c <- epB.__debug_cfgs) {
    //      if (c.s.id > 1000) {
    //        //val id = c.s.roleToId(Role("d", RoleSet("DUMMY")))
    //        val id = c.s.roleToId(Role("b", RoleSet("B")))
    //        println(s"signal fail $id")
    //        epB.network.signalFail(id)
    //      }
    //
    //    }


    Thread.sleep(5000)
    println("")
  }
}