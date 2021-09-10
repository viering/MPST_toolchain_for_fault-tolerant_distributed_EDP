package example.TestProtocols.pingpong_spawn_branch

import java.util.Calendar

import event_lang._
import dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPoint, AbstractEndPointTesting, ChannelTypeSubS}
import event_lang.types.{Role, RoleSet}
import example.TestProtocols.pingpong_spawn_branch.PingPongSpawnBranch.A.__EPType_A
import example.TestProtocols.pingpong_spawn_branch.PingPongSpawnBranch._
import example.TestProtocols.pingpong_spawn_branch.PingPongSpawnBranch.B.{EPType_B, __EPType_B}
import example.TestProtocols.pingpong_spawn_branch.PingPongSpawnBranch.MESSAGES.Main.{FailB, LMain}
import example.TestProtocols.pingpong_spawn_branch.PingPongSpawnBranch.MESSAGES.PingPong._
import network.SpawnMain
import event_lang.network.LHChannel.LHostSessionChannelEPs
import semantic._

import scala.util.Random

/*
{
    "PingPong (a : A, b : B, _bb : B) = {" +
      "bb -> a : Start(). mu t. " +
        "a -> b : {" +
          "Ping(m:String) : a -> bb : PingBrn(). b -> a : Pong(m :String). t," +
          "End() : a -> bb : EndBrn(). 0" +
        "}" +
      " with " +
        "bb@a. a -> b : FailD().0" +
    "};" +
    "Main (a : A, _b : B,B) = {" +
      "a -> b : LMain(). spawn PingPong(a,b,_B).0" +
    " with " +
      "b@a. a -> B : FailB().0" +
    "};}"
*/


class EP_A(val maxPing : Int = 10,val block: (AbstractChannelType, AState, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, AState, AbstractChannelImp) => Unit = (d, x, s) => {}) extends A.EPType_A[AState] with AbstractEndPointTesting[__EPType_A,AState] {
  override val roleSet: RoleSet = RoleSet("A")
  override val subs: Seq[ChannelTypeSubS] = A.subs

  override def onStartUp: AState = AState()



  import PingPongSpawnBranch.A.{Main_a_A => main, PingPong_a_A => pp}

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
        println(s"[a_ping_pong]rcv $m")
        c2
    }},
    register(pp.SndPing, pp.SndPingBrn) {
      case (s, c) if s.pingCnt <= maxPing => {
        println(s"[a_ping_pong] Snd Ping to b")
        (s.copy(pingCnt = s.pingCnt + 1), c.sndTob_B(Ping(s"Ping_${s.pingCnt}")).sndTobb_B(
          PingBrn()))
      }
    },
    register(pp.SndEnd, pp.SndEndBrn) {
      case (s, c) => {
        println(s"[a_ping_pong] Snd End Brn")
        (s.copy(pingCnt = s.pingCnt + 1), c.sndTob_B(End()).sndTobb_B(EndBrn()))
      }
    }

    ,
    λ(pp.RcvPong) {
      case c => c ? {
        case (m,c2)=>
        Thread.sleep(100)
        println(s"[a_ping_pong] rcv: $m")
       c2}
    },
    register(pp.Failed_bb_B, pp.SndFailD) {
      case (s, c) =>
        println(s"[a_ping_pong] ### FHandling: d@A")
        (s, c.failed_bb_B().sndTob_B(FailD()))
    }
    )
}

class EP_B(val block: (AbstractChannelType, AState, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, AState, AbstractChannelImp) => Unit = (d, x, s) => {}) extends B.EPType_B[AState] with AbstractEndPointTesting[__EPType_B ,AState ]{
  override def onStartUp: AState = AState()

  override val subs: Seq[ChannelTypeSubS] = B.subs

  import PingPongSpawnBranch.B.{
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
      c2}
    },

    registerStatic(main.SpawnPingPong)({
      case s =>
        println("[b_main] Spawned PingPong")
        s
    }),


    λ(mainB.RcvFailB) {
      case c => c ? {
        case (m,c2)=>
        println("[B_main] RcvFailB")
        c2}
    },

    /*
      Ping Pong (b)
    */
    λ(pp.RcvPing, pp.SndPong) {
      case c => c ? {
        case (m,c2)=>
        Thread.sleep(100)
        println(s"[b_ping_pong] rcv: $m")
       c2 ! (Pong(s"Pong_${m.m}"))}
    },
    λ(pp.RcvFailD) {
      case c => c ? {
        case (m,c2)=>
        println(s"[b_ping_pong] I received $m")
        c2}
    }
    ,
    λ(pp.RcvEnd){
      case c => c ? {
        case (m,c2)=>
        println(s"[b_ping_pong] I received End($m)")
       c2}
    },

    register(bb_pp.SndStart) {
      case (s, c) =>
        println("[bb_pp] snd Start")
        (s, c.sndToa_A(Start()))
    },

    λ(bb_pp.RcvEndBrn) {
      case c => c ? {
        case (m,c2)=>
        println(s"[bb_pp] Rcv :$m")
        c2}
    },
    λ(bb_pp.RcvPingBrn) {
      case c => c ? {
        case (m,c2)=>
        println(s"[bb_pp] Rcv :$m")
       c2}
    }
    )

  override val roleSet: RoleSet = RoleSet("B")
}

object PingPongSpawnBranchMain {
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


    val mainSes = SpawnMain(0, 0, Map((0 -> roleA), (1 -> roleB)),
                            Map(0 -> rsA, 1 -> rsB, 2 -> rsB))
    val dummyNetwork = new LHostSessionChannelEPs(3, mainSes)

    /**
      * End Points
      */


    val failB = Random.nextBoolean()
    //var epB: OperationalSemantic[AbstractEndPoint[__EPType_B,AState], AState] = null

    //A
    new Thread(() => {
      //println("Start A")
      val ep = EndPoint.createLocalOnlyEP(
        new EP_A(),
        dummyNetwork,
        0
        )
      ep.start()
    }).start()
    //B
    new Thread(() => {
      val ep = EndPoint.createLocalOnlyEP(
        new EP_B(),
        dummyNetwork,
        1
        )
      ep.start()
      println(s"B done")
    }).start()


    //B2
    new Thread(() => {
      val ep = EndPoint.createLocalOnlyEP(
        new EP_B(),
        dummyNetwork,
        2
        )
      ep.start()
      println(s"B done")
    }).start()


    Thread.sleep(1000)

    //dummyNetwork.getLayer(1).signalFail(2)
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