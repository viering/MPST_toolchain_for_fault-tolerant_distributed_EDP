package event_lang.projection

import event_lang.codegen.{ChannelGenerator, GlobalTypes}
import event_lang.intern.parser.ProtocolParser
import event_lang.types.Projection
import org.scalatest.funsuite.AnyFunSuite

class SimpleProjectionTests extends AnyFunSuite {
  def canProjectAndCodeGen(gtype: String, pName: String): Unit = {
    try {
      val g = ProtocolParser.parseString(gtype)
      val d = Projection(g, pName)
      val cb = ChannelGenerator.apply(d)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        assert(false, s"Could not project and code gen $pName of $gtype")
    }
  }

  def notProejctable(gtype: String, pName: String = ""): Unit = {
    val g = ProtocolParser.parseString(gtype)
    var failed = false
    try {
      val d = Projection(g, pName)

    } catch {
      case e: AssertionError =>
        println(s"As expected Assertion was triggered. The failed assertion is\n${e.getMessage}") //getStackTrace.mkString("\n")}"
        failed = true;
    }
    assert(failed, s"This protocol must fail during projection ($gtype)")
  }

  test("Faulty one") {
    notProejctable(GlobalTypes.TestProtocols.NotProjectable.faultyOne)
  }

  test("Faulty two") {
    notProejctable(GlobalTypes.TestProtocols.NotProjectable.faultyTwo)
  }

  test("Faulty three") {
    notProejctable(GlobalTypes.TestProtocols.NotProjectable.faultyThree)
  }

  test("Not mergable one") {
    notProejctable(GlobalTypes.TestProtocols.NotProjectable.faultyNotMergeableOne)
  }

  test("Not mergable two") {
    notProejctable(GlobalTypes.TestProtocols.NotProjectable.faultyNotMergeableTwo)
  }

  test("We can project and codegen protocols") {
    canProjectAndCodeGen(GlobalTypes.TestProtocols.PING_PONG, "PingPong")
    canProjectAndCodeGen(GlobalTypes.TestProtocols.BranchInFActivation, "BranchInFActivation")
    canProjectAndCodeGen(GlobalTypes.TestProtocols.RestartInFhandling, "RestartOnFailure")
    canProjectAndCodeGen(GlobalTypes.TestProtocols.SIMPLE_SPAWN, "TEST_SPAWN")
    canProjectAndCodeGen(GlobalTypes.JACM_16.STREAMING, "Streaming")
    canProjectAndCodeGen(GlobalTypes.JACM_16.TWO_BUYER, "TwoBuyer")
    canProjectAndCodeGen(GlobalTypes.GlobalProgress.THREE_BUYER, "ThreeBuyer")
    canProjectAndCodeGen(GlobalTypes.OurPaper.RING_ONE, "RingABC")
    canProjectAndCodeGen(GlobalTypes.OurPaper.RING_FAULT_TOLERANT, "RingFaultTolerant")
    canProjectAndCodeGen(GlobalTypes.TestProtocols.SIMPLE_REC, "Simple_Rec")
    canProjectAndCodeGen(GlobalTypes.ParameterisedSession.RING, "Ring")
    canProjectAndCodeGen(GlobalTypes.ParameterisedSession.PIPE, "Pipe")
    canProjectAndCodeGen(GlobalTypes.TestProtocols.SndIntoBranch, "SndIntoBranching")
    canProjectAndCodeGen(GlobalTypes.OurPaper.VALUE_BRANCHING, "ValueBranching")
    canProjectAndCodeGen(GlobalTypes.CC.DSH, "DSH")
    canProjectAndCodeGen(GlobalTypes.OurPaper.SESSION_CM_PAPER, "SessionCMMinusTypes")
    canProjectAndCodeGen(GlobalTypes.ExceptionalAsynST_POPL.twoFactor, "TwoFactor")
    canProjectAndCodeGen(GlobalTypes.GlobalEscape.InterruptableThreeBuyer, "InterruptibleBuyer")
    canProjectAndCodeGen(GlobalTypes.PracticalInterruptible.resAccessControl, "ResControl")
    canProjectAndCodeGen(GlobalTypes.JACM_16.STREAMING, "Streaming")
    canProjectAndCodeGen(GlobalTypes.TestProtocols.PING_PONG_Spawn_Branch, "PingPongSpawnBranch")
    canProjectAndCodeGen(GlobalTypes.LetItRecoverCC17.webCrawler, "webCrawler")
    canProjectAndCodeGen(GlobalTypes.OurPaper.SESSION_CM_FULL, "SessionCMMinusTypes")
    canProjectAndCodeGen(GlobalTypes.ESOP_Crash_Handling.GENERALIZED_STREAMING, "ESOPStreamingGeneralised")

  }

}
