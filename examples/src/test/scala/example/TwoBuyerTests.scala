package example

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import event_lang.types.{Role, RoleSet}
import example.two_buyer.TwoBuyer.Buyer.Main_Buyer.RcvCancelMain
import example.two_buyer.TwoBuyer.Buyer.TwoBuyer_b1_Buyer.{RcvCancelTrans, RcvPrice1}
import example.two_buyer.TwoBuyer.Buyer.TwoBuyer_b2_Buyer.RcvArrivalDate
import example.two_buyer.TwoBuyer.Seller.TwoBuyer_s_Seller.{RcvQuit, RcvRequest}
import example.two_buyer.{Buyer, Seller}
import example.util.{EPRunner, VoidOutStream}
import org.scalatest.concurrent.{Signaler, ThreadSignaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

class TwoBuyerTests extends AnyFunSuite with TimeLimitedTests{

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
    var g1HandlerWasExecuted = false


    var curV = 0
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvArrivalDate, _, c) =>
        g1HandlerWasExecuted = true
      case (RcvQuit, _, _) => assert(false)
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,(() => new Seller(customCode = customCode), List(0)),
      (() => new Buyer(customCode = customCode), 1 to 2) )
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(g1HandlerWasExecuted)
  }}}

  test("Normal execution: Book to expensive") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    var g1HandlerWasExecuted = false


    var curV = 0
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvArrivalDate, _, c) =>
        assert(false)
      case (RcvQuit, _, _) =>
        g1HandlerWasExecuted = true
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new Seller(BOOK_ONE_Price = 200, customCode = customCode), List(0)),(() => new Buyer(customCode = customCode), 1 to 2) )
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(g1HandlerWasExecuted)
  }}}

  test("Failure execution: b1 fails in TwoBuyer") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    var g1HandlerWasExecuted = false
    var ne: (LHostSessionChannelEPs, Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) =null

    var curV = 0
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvArrivalDate, _, c) =>
        assert(false)
      case (RcvQuit, _, _) =>
        assert(false)
      case (RcvRequest,_,c)=>
        val idB1 = c.session.roleToPId(role_b1)
        ne._3(idB1).stop()
        network.ep_ses_channels(0).signalFail(idB1)
      case (RcvCancelMain,_,c) => g1HandlerWasExecuted=true
      case _ =>
    }
    val blockOn: (AbstractChannelType, TState, AbstractChannelImp) => Boolean = {
      case (RcvPrice1, _, c) =>
        false
      case _ => false
    }

    ne=EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new Seller(BOOK_ONE_Price = 200, customCode = customCode), List(0)),(() => new Buyer(customCode = customCode,block = blockOn), 1 to 2))
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(g1HandlerWasExecuted)
  }}}

  test("Failure execution: b2 fails in TwoBuyer") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    var g1HandlerWasExecuted = false
    var ne: (LHostSessionChannelEPs, Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) =null

    var curV = 0
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvArrivalDate, _, c) =>
        assert(false)
      case (RcvQuit, _, _) =>
        assert(false)
      case (RcvRequest,_,c)=>
        val idB1 = c.session.roleToPId(role_b2)
        ne._3(idB1).stop()
        network.ep_ses_channels(0).signalFail(idB1)
      case (RcvCancelTrans,_,c) => g1HandlerWasExecuted=true
      case _ =>
    }
    val blockOn: (AbstractChannelType, TState, AbstractChannelImp) => Boolean = {
      case (RcvPrice1, _, c) =>
        false
      case _ => false
    }

    ne=EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new Seller(BOOK_ONE_Price = 200, customCode = customCode), List(0)),(() => new Buyer(customCode = customCode,block = blockOn), 1 to 2))
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(g1HandlerWasExecuted)
  }}}
}
