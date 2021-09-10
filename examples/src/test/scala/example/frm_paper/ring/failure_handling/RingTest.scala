package example.frm_paper.ring.failure_handling

import java.util.concurrent.atomic.AtomicInteger
import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import event_lang.types.{Role, RoleSet}
import example.frm_paper.ring.failure_handling.generated.RingABC.A.g1_a_A.{Failed_c_C, RcvL3, RcvL8}
import example.frm_paper.ring.failure_handling.generated.RingABC.A.g2_a_A
import example.frm_paper.ring.failure_handling.generated.RingABC.A.g2_a_A.RcvL5
import example.frm_paper.ring.failure_handling.generated.RingABC.B.g1_b_B.{RcvL7, SndL2}
import example.frm_paper.ring.failure_handling.generated.RingABC.C.Main_C.RcvL6
import example.frm_paper.ring.failure_handling.generated.RingABC.C.g1_c_C.RcvL2
import example.frm_paper.ring.failure_handling.generated.RingABC.C.g2_c_C.RcvL4
import example.util.{NettyEPAddr, EPRunner, VoidOutStream}
import org.scalatest.funsuite.AnyFunSuite
import example.TestUtil._
import example.util
import org.scalatest.SequentialNestedSuiteExecution
import org.scalatest.concurrent.{Signaler, ThreadSignaler, TimeLimitedTests}
import org.scalatest.time.{Millis, Seconds, Span}

class RingABCTest extends AnyFunSuite with SequentialNestedSuiteExecution with TimeLimitedTests{

  val timeLimit = Span(50, Seconds)
  override val defaultTestSignaler = new Signaler {
    override def apply(testThread: Thread): Unit = testThread.stop()
  }


  test("Normal Execution") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    var g1HandlerWasExecuted = false

    val customCode: (AbstractChannelType,TState,AbstractChannelImp ) => Unit = {
      case (RcvL3,_,_) =>
        g1HandlerWasExecuted = true
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0,0,
      (() => new EP_A(customCode = customCode), List(0)), (() => new EP_B(), List(1)),
      (() => new EP_C(), 2 to 3))
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(g1HandlerWasExecuted)
  }}}


  test("Example execution frm paper: b fails after the spawn of g1 but before the end of g1") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    var g1HandlerWasExecuted = false
    val block: (AbstractChannelType,TState,AbstractChannelImp ) => Boolean = {
      case (RcvL3,_,_) => true
      case _ => false
    }
    val customCode: (AbstractChannelType,TState,AbstractChannelImp )  => Unit = {
      case (RcvL2,_,_)=>
        network.ep_ses_channels(0).signalFail(1)

      case (RcvL5,_,_) =>
        g1HandlerWasExecuted = true
      case (RcvL7,_,_)=> assert(false)
      case (Failed_c_C,_,_)=> assert(false)
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0,0,
      (() => new EP_A(customCode=customCode), List(0)), (() => new EP_B(customCode = customCode), List(1)),
      (() => new EP_C(block = block,customCode=customCode), 2 to 3))

    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(g1HandlerWasExecuted)
  }}}


//  test("Example execution frm paper: b fails after the spawn of g1 but before the end of g1 (Netty)") {
//    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
//      val mainId = 0
//      val pickId = 1
//      var ne: (Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) =  null
//      var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
//      var g1HandlerWasExecuted = false
//      val block: (AbstractChannelType,TState,AbstractChannelImp ) => Boolean = {
//        case (RcvL3,_,_) => true
//        case _ => false
//      }
//      val customCode: (AbstractChannelType,TState,AbstractChannelImp )  => Unit = {
//        case (RcvL2,_,c)=>
//          assert(c.session.myId != 1 && c.session.myId != 0)
//          eps(c.session.myId).network.signalFail(1)
//          ne._2(1).stop()
//        case (RcvL5,_,_) =>
//          g1HandlerWasExecuted = true
//        case (RcvL7,_,_)=> assert(false)
//        case (Failed_c_C,_,_)=> assert(false)
//        case _ =>
//      }
//
//      ne = EPRunner.bootstrapNetty(mainId, pickId, 0,0,
//        (() => new EP_A(customCode=customCode), List(0)), (() => new EP_B(customCode = customCode), List(1)),
//        (() => new EP_C(block = block,customCode=customCode), 2 to 3))(NettyEPAddr.getBootstrapAddr)
//
//      eps = ne._1
//      ne._2.foreach(_.start())
//      ne._2.foreach(_.join)
//      assert(g1HandlerWasExecuted)
//    }}
//}


  test("Termination Test: Failure of all Cs - after spawn of g1 ") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    var g1HandlerWasExecuted = false
    val block: (AbstractChannelType,TState,AbstractChannelImp ) => Boolean = {
      case (RcvL2,_,_) => true
      case _ => false
    }
    val customCode: (AbstractChannelType,TState,AbstractChannelImp )  => Unit = {
      case (SndL2,_,_)=>
        val epA = eps.getEP(mainId)
        val idC = epA.aSubCfgOf(mainId).s.roleToId(role_c)
        network.ep_ses_channels(0).signalFail(idC)
      case (RcvL7,_,_) =>
        network.ep_ses_channels(0).signalFail(2)
        network.ep_ses_channels(0).signalFail(3)
        g1HandlerWasExecuted = true
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0,0,
      (() => new EP_A(customCode=customCode), List(0)), (() => new EP_B(customCode = customCode), List(1)),
      (() => new EP_C(block = block), 2 to 3))

    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(g1HandlerWasExecuted)
  }}}

//  test("Termination Test: Failure of all Cs - after spawn of g1 (Netty)") {
//    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
//      val mainId = 0
//      val pickId = 1
//      var ne: (Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) =  null
//      var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
//      var g1HandlerWasExecuted = false
//      val block: (AbstractChannelType,TState,AbstractChannelImp ) => Boolean = {
//        case (RcvL2,_,_) => true
//        case _ => false
//      }
//      val customCode: (AbstractChannelType,TState,AbstractChannelImp )  => Unit = {
//        case (SndL2,_,c)=>
////          val epA = eps.getEP(mainId)
//          val idC = c.session.roleToPId(role_c) //epA.aSubCfgOf(mainId).s.roleToId(role_c)
//          assert(c.session.myId != idC)
//          eps(c.session.myId).network.signalFail(idC)
//        case (RcvL7,_,c) =>
//          assert(c.session.myId != 2 && c.session.myId != 3 )
//          eps(c.session.myId).network.signalFail(2)
//          eps(c.session.myId).network.signalFail(3)
//          g1HandlerWasExecuted = true
//        case _ =>
//      }
//
//       ne = EPRunner.bootstrapNetty(mainId, pickId, 0,0,
//        (() => new EP_A(customCode=customCode), List(0)), (() => new EP_B(customCode = customCode), List(1)),
//        (() => new EP_C(block = block), 2 to 3))(util.NettyEPAddr.getBootstrapAddr)
//
//      eps = ne._1
//      ne._2.foreach(_.start())
//      ne._2.foreach(_.join)
//      assert(g1HandlerWasExecuted)
//    }}
//  }


  test("Handing in g1 and main") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    var handlerWasExecuted = false
    val block: (AbstractChannelType,TState,AbstractChannelImp )  => Boolean = {
      case (RcvL2,_,_) => true
      case _ => false
    }
    val customCodeB: (AbstractChannelType,TState,AbstractChannelImp )  => Unit = {
      case ( SndL2,_,c) =>
        val idC = c.session.roleToPId(role_c)
        network.ep_ses_channels(0).signalFail(idC)
      case ( RcvL7,_,_) =>
        network.ep_ses_channels(0).signalFail(1)
      case _ =>
    }
    val customCodeC: (AbstractChannelType,TState,AbstractChannelImp ) => Unit = {
      case (RcvL4,_,_) =>
        handlerWasExecuted = true
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0,0,
      (() => new EP_A(), List(0)), (() => new EP_B(customCode= customCodeB), List(1)),
      (() => new EP_C(block, customCodeC), 2 to 3))

    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(handlerWasExecuted)
  }}}

  test("Trigger all failure handlings") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream) {
      val mainId = 0
      val pickId = 1
      var network: LHostSessionChannelEPs = null
      var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
      val hdlExe = new AtomicInteger(0)
      val block: (AbstractChannelType, TState, AbstractChannelImp) => Boolean = {
        case (RcvL2, _, _) => true
        case (RcvL5, _, _) => true
        case (RcvL8, _, _) => true
        case _ => false
      }
      val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
        case (SndL2, _, c) =>
          val idC = c.session.roleToPId(role_c)
          network.ep_ses_channels(0).signalFail(idC)

        case (RcvL7, _, _) =>
          network.ep_ses_channels(0).signalFail(1) //fail of b
          hdlExe.incrementAndGet()
        case (RcvL4, _, _) =>
          network.ep_ses_channels(0).signalFail(3) //fail of c
          network.ep_ses_channels(0).signalFail(2) //
        case (RcvL6, _, _) =>
          hdlExe.incrementAndGet()
        case (g2_a_A.Failed_c_C, _, _) =>
          hdlExe.incrementAndGet()
        case _ =>
      }

      val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
        (() => new EP_A(block, customCode), List(0)), (() => new EP_B(block, customCode), List(1)),
        (() => new EP_C(block, customCode), 2 to 3))

      network = ne._1
      eps = ne._2
      ne._3.foreach(_.start())
      ne._3.foreach(_.join)
      assert(hdlExe.get() == 3)
    }} }

}
