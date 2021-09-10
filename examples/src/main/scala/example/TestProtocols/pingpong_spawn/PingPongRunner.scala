package example.TestProtocols.pingpong_spawn

import event_lang._
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.network.SpawnMain
import event_lang.types.{Role, RoleSet}

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

//
//case class AState() extends TState
//
//class EP_A extends AbstractEndPoint[A.EPType_A, AState] {
//  override val roleSet: RoleSet = RoleSet("A")
//  override val subs: Seq[ChannelTypeSubS] = A.subs
//
//  override def onStartUp: AState = AState()
//
//  import PingPongSpawn.A.{Main_a_A => main, PingPong_a_A => pp}
//
//
//  override val receive = ELoop(
//    /*
//    Main
//     */
//
//    register(main.SndLMain, main.SndLMain) { case (s, c) =>
//      println(s"[a_main] Snd LMain() to b")
//      (s, c.sndTob_B(LMain()))
//    },
//
//
//    //reg on entry / leave a local type position
//    registerStatic(main.SpawnPingPong) {
//      case s =>
//        println("[a_main] Spawned PingPong")
//        s
//    },
//    register(main.Failed_b_B, main.SndFailB) {
//      case (s, c) => {
//        println(s"[a_main] ### FHandling: b@A")
//        (s, c.failed_b_B().sndToB(FailB()))
//      }
//    },
//    /*
//    Ping Pong
//     */
//    regRcv(pp.RcvStart) {
//      case (Start(), s, c) =>
//        println("[a_ping_pong]rcv Start")
//        (s, c)
//    },
//    register(pp.SndPing) {
//      case (s, c) => {
//        println(s"[a_ping_pong] Snd Ping to b")
//        (s, c.sndTob_B(Ping(s"Ping_${Calendar.getInstance().getTime()}")))
//      }
//    }
//    ,
//    regRcv(pp.RcvPong) {
//      case (m: Pong, s, c) =>
//        Thread.sleep(100)
//        println(s"[a_ping_pong] rcv: $m")
//        (s, c)
//    },
//    register(pp.Failed_bb_B, pp.SndFailD) {
//      case (s, c) =>
//        println(s"[a_ping_pong] ### FHandling: d@A")
//        (s, c.failed_bb_B().sndTob_B(FailD()))
//    }
//    )
//}
//
//class EP_B extends AbstractEndPoint[B.EPType_B, AState] {
//  override def onStartUp: AState = AState()
//
//  override val subs: Seq[ChannelTypeSubS] = B.subs
//
//  import PingPongSpawn.B.{
//    Main_b_B => main, Main_B => mainB, PingPong_b_B => pp, PingPong_bb_B =>
//    bb_pp
//  }
//
//
//  override val receive = ELoop(
//    /*
//      Main
//    */
//    regRcv(main.RcvLMain) { case (m, s, c) =>
//      println(s"[b_main] rcv $m from a")
//      (s, c)
//    },
//
//    registerStatic(main.SpawnPingPong)({
//      case s =>
//        println("[b_main] Spawned PingPong")
//        s
//    }),
//
//    regRcv(main.RcvFailB) {
//      case (m, s, c) =>
//        println("[b_main] RcvFailB")
//        (s, c)
//    },
//    regRcv(mainB.RcvFailB) {
//      case (m, s, c) =>
//        println("[B_main] RcvFailB")
//        (s, c)
//    },
//
//    /*
//      Ping Pong (b)
//    */
//    register(pp.SndPong) {
//      case (s, c) => {
//        println(s"[b_ping_pong] snd Pong to a")
//        (s, c.sndToa_A(Pong(s"Pong_${Calendar.getInstance().getTime()}")))
//      }
//    }
//
//
//    ,
//    regRcv(pp.RcvPing) {
//      case (m: Ping, s, c) =>
//        Thread.sleep(100)
//        println(s"[b_ping_pong] rcv: $m")
//        (s, c)
//    },
//    regRcv(pp.RcvFailD) {
//      case (m: FailD, s, c) =>
//        println(s"[b_ping_pong] I received $m")
//        (s, c)
//    }
//    ,
//
//    register(bb_pp.SndStart) {
//      case (s, c) =>
//        println("[bb_pp] snd Start")
//        (s, c.sndToa_A(Start()))
//    }
//    )
//
//  override val roleSet: RoleSet = RoleSet("B")
//}

object PingPongRunner {
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

    val roleA = Role("a", RoleSet("A"))
    val roleB = Role("b", RoleSet("B"))
    val rsA = RoleSet("A")
    val rsB = RoleSet("B")


    val mainSes = SpawnMain(0, 0, Map((0 -> roleA), (1 -> roleB)), Map(0 -> rsA, 1 -> rsB,2 -> rsB))
    val dummyNetwork = new LHostSessionChannelEPs(3, mainSes)

    /**
      * End Points
      */


    val failB = Random.nextBoolean()
    //var epB: OperationalSemantic[EPType_B, AState] = null

    //A
    new Thread(() => {
      //println("Start A")
      val ep =EndPoint.createLocalOnlyEP(
        new EP_A(),
        dummyNetwork,
        0
        )
      ep.start()
    }).start()
    //B
    new Thread(() => {
      val ep =EndPoint.createLocalOnlyEP(
        new EP_B(),
        dummyNetwork,
        1
        )
      ep.start()
      println(s"B done")
    }).start()


    //B2
    new Thread(() => {
      val ep =EndPoint.createLocalOnlyEP(
        new EP_B(),
        dummyNetwork,
        2
        )
      ep.start()
      println(s"B done")
    }).start()


    Thread.sleep(1000)

    dummyNetwork.getLayer(1).signalFail(2)
    //dummyNetwork.getLayer(0).signalFail(1)


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