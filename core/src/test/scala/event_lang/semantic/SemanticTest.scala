package event_lang.semantic

import event_lang._
import dsl.{AbstractEndPoint, ChannelTypeSubS, HDL, TState}
import event_lang.semantic.testtypes.TEST_SPAWN.A.EPType_A
import event_lang.semantic.testtypes.TEST_SPAWN.B.EPType_B
import network.{SessionChannelEP, SpawnMain}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}
import semantic.testtypes.TEST_SPAWN.MESSAGES.GOne.{FailGOne, MSGAtoB, MSGOne, MSGTwo}
import semantic.testtypes.TEST_SPAWN.MESSAGES.Main.{FailMain, MSGMCast}
import types.RoleSet
import testtypes.TEST_SPAWN._

import scala.collection.mutable



object HdlKeys {
  val a_main_spawn = "a_main_spawn"
  val a_main_mcast = "a_main_mcast"
  val a_main_failure_b = "a_main_failure_b"
  val a_gone_msgAtoB = "a_gone_msgAtoB"
  val a_gone_msgOne = "a_gone_msgOne"
  val a_gone_msgTwo = "a_gone_msgTwo"
  val a_gone_failure = "a_gone_failure"
  val b_main_spawn = "b_main_spawn"
  val b_main_mcast = "b_main_mcast"
  val B_main_mcast = "B_main_mcast"
  val B_main_spawn = "B_main_spawn"
  val B_main_failure_b = "B_main_failure_b"
  val b_gone_msgAtoB = "b_gone_msgAtoB"
  val b_gone_msgTwo = "b_gone_msgTwo"
  val b_gone_failGOne = "b_gone_failGOne"
  val bb_gone_msgOne = "bb_gone_msgOne"

}
class VoidOutStream extends java.io.OutputStream{
  override def write(i: Int): Unit = {

  }
}
class SemanticTest extends AnyFunSuite with TimeLimitedTests {

  val timeLimit = Span(30, Seconds)
  override val defaultTestSignaler = new Signaler {
    override def apply(testThread: Thread): Unit = testThread.stop()
  }

  val a_Id = 0
  val b_Id = 1
  val bb_Id = 2

  case class AState() extends TState

  class EP_A(val hdlCtr: HdlController, val hdlExecKeys: mutable.Set[String], val nwork: SessionChannelEP) extends  EPType_A[AState] {
    override def onStartUp: AState = AState()

    import A.{Main_a_A => main, GOne_a_A => gOne}

    override val receive: Seq[HDL[AState]] = ELoop(

      /**
        * "Main (a : A, _b : B,B) = {" +
        * "   a -> B: MSGMCast(). spawn GOne(a,b,_B).0" +
        * " with " +
        * "   b@a. a -> B : FailMain().0" +
        * "};
        */
      register(main.SndMSGMCast) {
        case (s, c) =>
          if (hdlCtr.IssueFailureMCast)
            nwork.signalFail(b_Id)
          hdlExecKeys.add(HdlKeys.a_main_mcast)
          (s, c.sndToB(MSGMCast()))
      },
      registerStatic(main.SpawnGOne) {
        case s =>
          hdlExecKeys.add(HdlKeys.a_main_spawn)
          s
      },
      register(main.Failed_b_B, main.SndFailMain) {
        case (s, c) =>
          hdlExecKeys.add(HdlKeys.a_main_failure_b)
          (s, c.failed_b_B().sndToB(FailMain()))
      },

      /**
        * "GOne (a : A, b : B, _bb : B) = {" +
        * "   a -> b: MSGAtoB(). bb -> a : MSGOne(). b -> a : MSGTwo().0" +
        * " with " +
        * "   bb@a. a -> b : FailGOne().0" +
        * "};"
        */
      register(gOne.SndMSGAtoB) {
        case (s, c) if hdlCtr.MSGAtoB =>
          hdlExecKeys.add(HdlKeys.a_gone_msgAtoB)
          if (hdlCtr.IssueFailure_b_MSGAtoB)
            nwork.signalFail(b_Id)
          (s, c.sndTob_B(MSGAtoB()))
      },

      λ(gOne.RcvMSGOne) {
        case c if hdlCtr.MSGOne => c ? {
          case (m,c2)=>
          if (hdlCtr.IssueFailure_bb_RcvMSGOne) {
            Thread.sleep(500)
            nwork.signalFail(bb_Id)
          }
          hdlExecKeys.add(HdlKeys.a_gone_msgOne)
          c2
      }},
      λ(gOne.RcvMSGTwo) {
        case c if hdlCtr.RcvMSGTwo => c ? {
          case (m,c2)=>
          hdlExecKeys.add(HdlKeys.a_gone_msgTwo)
         c2
      }},
      register(gOne.Failed_bb_B, gOne.SndFailGOne) {
        case (s, c) =>
          hdlExecKeys.add(HdlKeys.a_gone_failure)
          (s, c.failed_bb_B().sndTob_B(FailGOne()))
      }
      )
  }


  case class BState() extends TState
/*
        "GOne (a : A, b : B, _bb : B) = {" +
        "   a -> b: MSGAtoB(). bb -> a : MSGOne(). b -> a : MSGTwo().0" +
        " with " +
        "   bb@a. a -> b : FailGOne().0" +
        "};" +
        "Main (a : A, _b : B,B) = {" +
        "   a -> B: MSGMCast(). spawn GOne(a,b,_B).0" +
        " with " +
        "   b@a. a -> B : FailMain().0" +
        "};}"
 */
  class EP_B(val hdlCtr: HdlController, val hdlExecKeys: mutable.Set[String], val
  nwork: SessionChannelEP) extends EPType_B[BState] {
    override def onStartUp: BState = BState()

    import B.{Main_B => main_B, Main_b_B => main_b, GOne_b_B => gOne_b, GOne_bb_B => gOne_bb}

    override val receive: Seq[HDL[BState]] = ELoop(
      /**
        * "Main (a : A, _b : B,B) = {" +
      DE
  * "   a -> B: MSGMCast(). spawn GOne(a,b,_B).0" +
        * " with " +
        * "   b@a. a -> B : FailMain().0" +
        * "};}"
        */
      registerStatic(main_b.SpawnGOne) {
        case s if hdlCtr.spawnGone =>
          hdlExecKeys.add(HdlKeys.b_main_spawn)
          s
      },
      λ(main_b.RcvMSGMCast) {
        case c if hdlCtr.RcvMSGMCast=> c ? {
          case (m, c2) =>
            hdlExecKeys.add(HdlKeys.b_main_mcast)
            c2
        }},
      λ(main_B.RcvMSGMCast) {
        case c if hdlCtr.RcvMSGMCast => c ? {
          case (m, c2) =>
            hdlExecKeys.add(HdlKeys.B_main_mcast)
            c2
        }},
      registerStatic(main_B.SpawnGOne) {
        case s if hdlCtr.spawnGone =>
          hdlExecKeys.add(HdlKeys.B_main_spawn)
          s
      },
      λ(main_B.RcvFailMain) {
        case c if hdlCtr.fail_b=> c ? {
          case (m,c2)=>
          hdlExecKeys.add(HdlKeys.B_main_failure_b)
         c2
      }},
//      λ(main_b.RcvFailMain) {
//        case c => c ? {
//          case (m,c2)=>
//          assert(false)
//          c2
//      }},

      /**
        * "GOne (a : A, b : B, _bb : B) = {" +
        * "   a -> b: MSGAtoB(). bb -> a : MSGOne(). b -> a : MSGTwo().0" +
        * " with " +
        * "   bb@a. a -> b : FailGOne().0" +
        * "};" +
        */
      λ(gOne_b.RcvMSGAtoB, gOne_b.SndMSGTwo) {
        case c if hdlCtr.RcvMSGAtoB => c ? {
          case (m,c2)=>
          if (hdlCtr.IssueFailure_bb_RcvMSGAtoB)
            nwork.signalFail(bb_Id)
          hdlExecKeys.add(HdlKeys.b_gone_msgAtoB)
          hdlExecKeys.add(HdlKeys.b_gone_msgTwo)
            c2 ! MSGTwo()
      }},
      register(gOne_bb.SndMSGOne) {
        case (s, c) if hdlCtr.MSGOne =>
          hdlExecKeys.add(HdlKeys.bb_gone_msgOne)
          (s, c.sndToa_A(MSGOne()))
      },
      λ(gOne_b.RcvFailGOne) {
        case c if hdlCtr.fail_bb=> c ? {
          case (m,c2)=>
          if(hdlCtr.IssueFailure_b_RcvFailGOne)
            nwork.signalFail(b_Id)
          hdlExecKeys.add(HdlKeys.b_gone_failGOne)
       c2
      }}

      )
  }


  def ep_threads(bodies: (() => Unit)*): Array[Thread] = {
    val bs = bodies.toSeq
    val th = new Array[Thread](bs.size)
    for (i <- th.indices)
      th(i) = new Thread(new Runnable {
        override def run(): Unit = try{
          bs(i)()
        }catch {
          case e : Throwable => assert(false, e.printStackTrace())
        }
      })

    th
  }

  def createEndPoints(controller: HdlController*): Array[mutable.Set[String]] = {
    val aA = PROTOCOLS.Main.a_A
    val bB = PROTOCOLS.Main.b_B
    val A = RS.A
    val B = RS.B

    val cntrllr = controller.toSeq

    val mainSes = SpawnMain(0, 0, Map((0 -> aA), (1 -> bB)), Map(0 -> A, 1 -> B, 2 -> B), Map())
    val dummyNetwork = new LHostSessionChannelEPs(3, mainSes)

    //fixme make thread save ...
    val keys = new Array[mutable.Set[String]](controller.size)
    for (i <- keys.indices)
      keys(i) = mutable.Set[String]()

    assert(controller.size == 3)

    val th = ep_threads(() => {
      //println("Start A")
      val ep = EndPoint.createLocalOnlyEP(
        new EP_A(cntrllr(0), keys(0), dummyNetwork.getLayer(0)),
        dummyNetwork,
        0
        )
      ep.start()
    }, () => {
      val ep = EndPoint.createLocalOnlyEP(
        new EP_B(cntrllr(1), keys(1), dummyNetwork.getLayer(1)),
        dummyNetwork,
        1
        )
      ep.start()
      println(s"B done")
    }, () => {
      val ep = EndPoint.createLocalOnlyEP(
        new EP_B(cntrllr(2), keys(2), dummyNetwork.getLayer(2)),
        dummyNetwork,
        2
        )
      ep.start()
      println(s"B done")
    })

    for (t <- th)
      t.start()
    for (t <- th)
      t.join()

    keys
  }


  implicit class SetContainsAll(set: mutable.Set[String]) {
    def containsAll(keys: String*): Boolean = {
      keys.forall(set.contains)
    }
  }

  test("Run TEST Spawn") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val results = createEndPoints(HdlController(), HdlController(), HdlController())
    assert(results(a_Id).size == 5)
    assert(results(b_Id).size == 4)
    assert(results(bb_Id).size == 3)
    import HdlKeys._
    assert(results(a_Id).containsAll(a_main_spawn, a_main_mcast, a_gone_msgOne, a_gone_msgTwo,
                                     a_gone_msgAtoB))
    assert(results(b_Id).containsAll(b_main_spawn, b_main_mcast, b_gone_msgTwo, b_gone_msgAtoB))
    assert(results(bb_Id).containsAll(B_main_spawn, B_main_mcast, bb_gone_msgOne))
  }}}

  test("Failure handling (main) no subsession") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val hdlc = HdlController(RcvMSGMCast = false, IssueFailureMCast = true)
    val results = createEndPoints(hdlc, hdlc, hdlc)
    assert(results(a_Id).size == 2)
    assert(results(b_Id).size == 0)
    assert(results(bb_Id).size == 1)
    import HdlKeys._
    assert(results(a_Id).containsAll(a_main_failure_b, a_main_mcast))
    //assert(results(b_Id).containsAll(b_main_spawn, b_main_mcast, b_gone_msgTwo, b_gone_msgAtoB))
    assert(results(bb_Id).containsAll(B_main_failure_b))
  }}}

  test("Failure handling (main) with subsession") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val hdlc = HdlController(RcvMSGAtoB = false, MSGTwo = false, IssueFailure_b_MSGAtoB = true)
    val results = createEndPoints(hdlc, hdlc, hdlc)
    assert(results(a_Id).size == 4)
    assert(results(b_Id).size <= 2)
    assert(results(bb_Id).size == 3 || results(bb_Id).size == 4)
    import HdlKeys._
    assert(results(a_Id).containsAll(a_gone_msgAtoB, a_main_failure_b, a_main_mcast, a_main_spawn))
    assert(results(b_Id).containsAll(b_main_spawn, b_main_mcast))
    assert(results(bb_Id).containsAll(B_main_failure_b, B_main_spawn, B_main_mcast))
    if (results(bb_Id).size == 4) results(bb_Id).contains(bb_gone_msgOne)
  }}}

  test("Failure handling (gOne) no cln necessary") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val hdlc = HdlController(MSGOne = false, MSGTwo = false, IssueFailure_bb_RcvMSGAtoB = true)
    val results = createEndPoints(hdlc, hdlc, hdlc)
    assert(results(a_Id).size == 4)
    assert(results(b_Id).size == 5)
    assert(results(bb_Id).size == 2)
    import HdlKeys._
    assert(results(a_Id).containsAll(a_gone_msgAtoB, a_gone_failure, a_main_mcast, a_main_spawn),
           s"${results(a_Id).mkString("; ")}")
    assert(results(b_Id).containsAll(b_main_spawn, b_main_mcast, b_gone_msgTwo, b_gone_msgAtoB,
                                     b_gone_failGOne))
    assert(results(bb_Id).containsAll(B_main_spawn, B_main_mcast))
  }}}

  test("Failure handling (gOne) cln necessary") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val hdlc = HdlController(RcvMSGAtoB = false, RcvMSGTwo = false,
                             IssueFailure_bb_RcvMSGOne = true)
    val results = createEndPoints(hdlc, hdlc, hdlc)
    assert(results(a_Id).size == 5)
    assert(results(b_Id).size == 3)
    assert(results(bb_Id).size == 3)
    import HdlKeys._
    assert(results(a_Id).containsAll(a_gone_msgAtoB, a_gone_failure, a_gone_msgOne, a_main_mcast,
                                     a_main_spawn),
           s"${results(a_Id).mkString("; ")}")
    assert(results(b_Id).containsAll(b_main_spawn, b_main_mcast,
                                     b_gone_failGOne))
    assert(results(bb_Id).containsAll(B_main_spawn, B_main_mcast, bb_gone_msgOne))
  }}}

  test("Failure handling (gOne) then failure main") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val hdlc = HdlController(RcvMSGAtoB = false, RcvMSGTwo = false,
                             IssueFailure_bb_RcvMSGOne = true, IssueFailure_b_RcvFailGOne = true)
    val results = createEndPoints(hdlc, hdlc, hdlc)
    assert(results(a_Id).size == 6)
    assert(results(b_Id).size == 3)
    assert(results(bb_Id).size == 3)
    import HdlKeys._
    assert(results(a_Id).containsAll(a_gone_msgAtoB, a_gone_failure,a_main_failure_b, a_gone_msgOne, a_main_mcast,
                                     a_main_spawn),
           s"${results(a_Id).mkString("; ")}")
    assert(results(b_Id).containsAll(b_main_spawn, b_main_mcast,
                                     b_gone_failGOne))
    assert(results(bb_Id).containsAll(B_main_spawn, B_main_mcast, bb_gone_msgOne))
  }}}

}
