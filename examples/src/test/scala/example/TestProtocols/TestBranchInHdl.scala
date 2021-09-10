package example.TestProtocols

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.network.SessionChannelEP
import event_lang.semantic.OperationalSemantic
import example.TestProtocols.branchFActivation.{EP_A, EP_B}
import example.TestProtocols.branchFActivation.types.BranchInFActivation.A.Main_a_A._
import example.TestProtocols.branchFActivation.types.BranchInFActivation.B.Main_B.{RcvF1, RcvF2}
import example.TestProtocols.branchFActivation.types.BranchInFActivation.PROTOCOLS.Main.b_B
import example.util.{EPRunner, VoidOutStream}
import org.scalatest.concurrent.{ThreadSignaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

class TestBranchInHdl extends AnyFunSuite with TimeLimitedTests {

  val timeLimit = Span(30, Seconds)
  override val defaultTestSignaler = ThreadSignaler

  test("Branching for FHandling terminates") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var sndFMsg = false
        var network: SessionChannelEP = null
        val eps = EPRunner.bootstrap(0, 1, 0, 0,
          (() => new EP_A(
            customCode = {
              case (SndM, s, cimpl) =>
                val idB = cimpl.session.roleToPId(b_B)
                println(s"Kill $idB")
                network.signalFail(idB)
              case (SndF1, _, _) => sndFMsg = true
              case (SndF2, _, _) => sndFMsg = true
              case _ =>
            },
            block = {
              case (RcvM1, s, cimpl) => true
              case _ => false
            }
          ), List(0)), (() => new EP_B(), List(1, 2)))

        network = eps._2(0).network
        eps._3.foreach(_.start())

        eps._3.foreach(_.join())
        assert(sndFMsg)
      }
    }
  }

  test("Non failure termination") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var network: SessionChannelEP = null
        val eps = EPRunner.bootstrap(0, 1, 0, 0,
          (() => new EP_A(
            customCode = {
              case _ =>
            },
            block = {
              case (RcvM1, s, cimpl) => false
              case _ => false
            }
          ), List(0)), (() => new EP_B(), List(1, 2)))

        network = eps._2(0).network
        eps._3.foreach(_.start())

        eps._3.foreach(_.join())
      }
    }
  }
}
