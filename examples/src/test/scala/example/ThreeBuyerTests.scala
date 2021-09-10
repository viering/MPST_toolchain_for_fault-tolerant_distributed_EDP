package example

import java.util.concurrent.atomic.AtomicInteger

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import event_lang.types.{Role, RoleSet}
import example.three_buyer_global_progress.{EP_A, EP_B, EP_C, EP_S}
import example.three_buyer_global_progress.ThreeBuyer.A.threeBuyers_a_A.{RcvOkA, RcvQuitA}
import example.three_buyer_global_progress.ThreeBuyer.B.negotiationBC_b_B.{RcvNo, RcvOk, SndYourShare}
import example.three_buyer_global_progress.ThreeBuyer.B.threeBuyers_b_B.RcvShipD
import example.three_buyer_global_progress.ThreeBuyer.C.negotiationBC_c_C.RcvYourShare
import example.three_buyer_global_progress.ThreeBuyer.S.threeBuyers_s_S.RcvQuitS
import example.util.{EPRunner, VoidOutStream}
import org.scalatest.concurrent.{Signaler, ThreadSignaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

class ThreeBuyerTests extends AnyFunSuite with TimeLimitedTests {

  val timeLimit = Span(30, Seconds)
  override val defaultTestSignaler = new Signaler {
    override def apply(testThread: Thread): Unit = testThread.stop()
  }



  val role_b1: Role = Role("b1", RoleSet("Buyer"))
  val role_b2: Role = Role("b2", RoleSet("Buyer"))


  test("Normal execution: Buy book") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    var hdlExCheck = new AtomicInteger(0)


    var curV = 0
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvOkA, _, c) =>
        hdlExCheck.incrementAndGet()
      case (RcvShipD, _, _) =>
        hdlExCheck.incrementAndGet()
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new EP_A(customCode = customCode), List(0)), (() => new EP_B(customCode = customCode), List(1)),
      (() => new EP_C(customCode = customCode), 2 to 3), (() => new EP_S(customCode = customCode), 4 to 5))

    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(hdlExCheck.get() == 2)
  }}}

  test("Normal execution: Book to expensive") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    val hdlExCheck = new AtomicInteger(0)


    var curV = 0
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvNo, _, c) =>
        hdlExCheck.incrementAndGet()
      case (RcvQuitA, _, _) =>
        hdlExCheck.incrementAndGet()
      case (RcvQuitS, _, _) =>
        hdlExCheck.incrementAndGet()
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new EP_A(customCode = customCode), List(0)), (() => new EP_B(customCode = customCode), List(1)),
      (() => new EP_C(customCode = customCode), 2 to 3), (() => new EP_S(PRICE = 59999, customCode = customCode), 4 to 5))

    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(hdlExCheck.get() == 3)
  }}}
  test("Failure execution: c fails") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    val hdlExCheck = new AtomicInteger(0)
    var ne: (LHostSessionChannelEPs, Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) = null


    val blockOn: (AbstractChannelType, TState, AbstractChannelImp) => Boolean = {
      case (RcvYourShare, _, _) => true
      case _ => false
    }
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (SndYourShare, _, c) =>
        val iPID = c.session.roleToPId(TestUtil.role_c)
        ne._3(iPID).stop()
        network.ep_ses_channels(c.session.roleToPId(TestUtil.role_b)).signalFail(iPID)
      case (RcvOk,_,_)=> assert(false)
      case (RcvQuitA, _, _) =>
        hdlExCheck.incrementAndGet()
      case (RcvQuitS, _, _) =>
        hdlExCheck.incrementAndGet()
      case _ =>
    }

    ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new EP_A(customCode = customCode), List(0)), (() => new EP_B(customCode = customCode), List(1)),
      (() => new EP_C(customCode = customCode), 2 to 3), (() => new EP_S(PRICE = 59999, customCode = customCode), 4 to 5))

    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(hdlExCheck.get() == 2)
  }}}

}
