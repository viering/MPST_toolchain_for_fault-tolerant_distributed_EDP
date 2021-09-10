package example.TestProtocols

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import example.TestProtocols.value_branching.ValueBranching.S.Main_s_S.{RcvNo, RcvOK}
import example.TestProtocols.value_branching.{EP_Buyer, EP_Seller}
import example.util.{NettyEPAddr, EPRunner, VoidOutStream}
import org.scalatest.concurrent.{ThreadSignaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

class TestVBranching extends AnyFunSuite with TimeLimitedTests{

  val timeLimit = Span(120, Seconds)
  override val defaultTestSignaler = ThreadSignaler

  test("Price ok") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    var okEx = false

    val customCode: (AbstractChannelType,TState,AbstractChannelImp ) => Unit = {
      case (RcvOK,_,_) =>
        okEx=true
      case (RcvNo,_,_)=>
        assert(false)
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0,0,
      (() => new EP_Seller(bPrice=20,customCode = customCode), List(0)), (() => new EP_Buyer(maxPrice = 50), List(1)))
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(okEx)
  }}}

  test("Price not ok") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
      val mainId = 0
      val pickId = 1
      var network: LHostSessionChannelEPs = null
      var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
      var okEx = false
      val customCode: (AbstractChannelType,TState,AbstractChannelImp ) => Unit = {
        case (RcvOK,_,_) =>
          assert(false)
        case (RcvNo,_,_)=>
          okEx=true
        case _ =>
      }

      val ne = EPRunner.bootstrap(mainId, pickId, 0,0,
        (() => new EP_Seller(bPrice=200,customCode = customCode), List(0)), (() => new EP_Buyer(maxPrice = 50), List(1)))
      network = ne._1
      eps = ne._2
      ne._3.foreach(_.start())
      ne._3.foreach(_.join)
      assert(okEx)
    }
    }}

//  test("Price ok (Netty Layer)") {
//    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
//      val mainId = 0
//      val pickId = 1
//      var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
//      var okEx = false
//
//      val customCode: (AbstractChannelType,TState,AbstractChannelImp ) => Unit = {
//        case (RcvOK,_,_) =>
//          okEx=true
//        case (RcvNo,_,_)=>
//          assert(false)
//        case _ =>
//      }
//
//      val ne = EPRunner.bootstrapNetty(mainId, pickId, 0,0,
//        (() => new EP_Seller(bPrice=20,customCode = customCode), List(0)), (() => new EP_Buyer(maxPrice = 50), List(1)))(NettyEPAddr.getBootstrapAddr)
//      eps = ne._1
//      ne._2.foreach(_.start())
//      ne._2.foreach(_.join)
//      assert(okEx)
//    }}
//}


//  test("Price not ok (Netty Layer)") {
//    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
//      val mainId = 0
//      val pickId = 1
//      var network: LHostSessionChannelEPs = null
//      var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
//      var okEx = false
//      val customCode: (AbstractChannelType,TState,AbstractChannelImp ) => Unit = {
//        case (RcvOK,_,_) =>
//          assert(false)
//        case (RcvNo,_,_)=>
//          okEx=true
//        case _ =>
//      }
//
//      val ne = EPRunner.bootstrapNetty(mainId, pickId, 0,0,
//        (() => new EP_Seller(bPrice=200,customCode = customCode), List(0)), (() => new EP_Buyer(maxPrice = 50), List(1)))(NettyEPAddr.getBootstrapAddr)
//      eps = ne._1
//      ne._2.foreach(_.start())
//      ne._2.foreach(_.join)
//      assert(okEx)
//    }
//    }}

  }
