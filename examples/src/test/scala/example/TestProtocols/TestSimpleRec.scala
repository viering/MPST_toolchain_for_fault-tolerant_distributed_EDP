package example.TestProtocols

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import example.TestProtocols.simple_rec.{EP_A, EP_B}
import example.util.{NettyEPAddr, EPRunner, VoidOutStream}
import examples.TestProtocols.simple_rec.generated.Simple_Rec.B.Main_b_B.RcvM
import org.scalatest.concurrent.{ThreadSignaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

class TestSimpleRec extends AnyFunSuite with TimeLimitedTests {

  val timeLimit = Span(50, Seconds)
  override val defaultTestSignaler = ThreadSignaler

  test("500 Runs then stop by failure") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1
        var network: LHostSessionChannelEPs = null
        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
        var g1HandlerWasExecuted = false


        var curV = 0
        val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
          case (RcvM, _, c) =>
            curV += 1
            if (curV > 500) network.ep_ses_channels(0).signalFail(1)
          case _ =>
        }

        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_A(), List(0)), (() => new EP_B(customCode = customCode), List(1)))
        network = ne._1
        eps = ne._2
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
        assert(curV >= 500)
      }
    }
  }

//  test("500 Runs then stop by failure (Netty)") {
//    Console.withOut(new VoidOutStream) {
//      Console.withErr(new VoidOutStream) {
//        val mainId = 0
//        val pickId = 1
//        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
//        var g1HandlerWasExecuted = false
//
//        var ne: (Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) = null
//        var curV = 0
//        val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
//          case (RcvM, _, c) =>
//            curV += 1
//            if (curV > 500) {
//              eps(c.session.myId).network.signalFail(1)
//            }
//          case _ =>
//        }
//
//        ne = EPRunner.bootstrapNetty(mainId, pickId, 0, 0,
//          (() => new EP_A(), List(0)), (() => new EP_B(customCode = customCode), List(1)))(NettyEPAddr.getBootstrapAddr)
//        eps = ne._1
//        ne._2.foreach(_.start())
//        ne._2.foreach(_.join)
//        assert(curV >= 500)
//      }
//    }
//  }
}
