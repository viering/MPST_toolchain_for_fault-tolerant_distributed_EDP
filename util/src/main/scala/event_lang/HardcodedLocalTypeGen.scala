package event_lang

import java.io.PrintWriter

import event_lang.codegen._
import event_lang.intern.parser._
import event_lang.types.Projection

object HardcodedLocalTypeGen {
  def codeGen(gtype: String, pName: String, file: String, pack: String): Unit = {
    println(s"\n\n## Generated type api.\nFor the protocol $pName to file: $file,") // \n(global type: $gtype)
    val g = ProtocolParser.parseString(gtype)
    val d = Projection(g, pName)
    val cb = ChannelGenerator.apply(d)

    //println(cb.channelCode())
    val pw = new PrintWriter(file)
    pw.write(pack + "\n")
    pw.write(cb.channelCode())
    pw.close()
  }

  def main(args: Array[String]): Unit = {
    //   println(GlobalTypes.TestProtocols.SIMPLE_REC.slice(40,60))


    codeGen(GlobalTypes.CC.DSH, "DSH",
      "examples/src/main/scala/example/dsh/DSH_Types.scala",
      "package example.dsh")

    codeGen(GlobalTypes.ESOP_Crash_Handling.GENERALIZED_STREAMING, "Streaming",
      "examples/src/main/scala/example/esop_crash_handling/streaming/types/StreamingTypes.scala",
      "package example.esop_crash_handling.streaming.types")


    codeGen(GlobalTypes.ExceptionalAsynST_POPL.twoFactor, "TwoFactor",
      "examples/src/main/scala/example/ExceptionalAsynST_POPL/twoFactor/types/TwoFactorTypes.scala",
      "package example.ExceptionalAsynST_POPL.twoFactor.types")


    codeGen(GlobalTypes.OurPaper.RING_ONE, "RingABC",
      "examples/src/main/scala/example/frm_paper/ring/failure_handling/generated/RingTypes.scala",
      "package example.frm_paper.ring.failure_handling.generated")

    codeGen(GlobalTypes.OurPaper.RING_FAULT_TOLERANT, "RingFaultTolerant",
      "examples/src/main/scala/example/frm_paper/ring/fault_tolerant/generated/RingFTTypes.scala",
      "package example.frm_paper.ring.fault_tolerant.generated")

    codeGen(GlobalTypes.GlobalEscape.InterruptableThreeBuyer, "InterruptibleBuyer",
      "examples/src/main/scala/example/globalescape/buyer/types//InterruptibleBuyerTypes.scala",
      "package example.globalescape.buyer.types")

    codeGen(GlobalTypes.LetItRecoverCC17.webCrawler, "webCrawler",
      "examples/src/main/scala/example/LetItRecover/webcrawler/types/webCrawlerTypes.scala",
      "package example.LetItRecover.webcrawler.types")

    codeGen(GlobalTypes.ParameterisedSession.PIPE, "Pipe",
      "examples/src/main/scala/example/pipe/types/PipeTypes.scala",
      "package example.pipe.types")

    codeGen(GlobalTypes.PracticalInterruptible.resAccessControl, "ResControl",
      "examples/src/main/scala/example/resourceAccessControl/types/ResControlTypes.scala",
      "package example.resourceAccessControl.types")

    codeGen(GlobalTypes.ParameterisedSession.RING, "Ring",
      "examples/src/main/scala/example/ring/types/RingTypes.scala",
      "package example.ring.types")

    codeGen(GlobalTypes.OurPaper.SESSION_CM_SIMPLE_ALTERNATIVE_DESIGN, "SparkCM",
      "examples/src/main/scala/example/sparkCluster/ClusterManagerTypes.scala",
      "package example.sparkCluster")

    codeGen(GlobalTypes.OurPaper.SESSION_CM_PAPER, "SessionCMMinusTypes",
      "examples/src/main/scala/example/sparkcmminus/types/SessionCMMinusTypes.scala",
      "package example.sparkcmminus")

    codeGen(GlobalTypes.JACM_16.STREAMING, "Streaming",
      "examples/src/main/scala/example/streaming_popl_08/StreamingEndPointTypes.scala",
      "package example.streaming_popl_08")


    codeGen(GlobalTypes.TestProtocols.BranchInFActivation,
      "BranchInFActivation",
      "examples/src/main/scala/example/TestProtocols/branchFActivation/types/BranchInFActivationTypes.scala",
      "package example.TestProtocols.branchFActivation.types")

    codeGen(GlobalTypes.TestProtocols.PING_PONG,
      "PingPong",
      "examples/src/main/scala/example/TestProtocols/pingpong/types/PingPong.scala",
      "package example.TestProtocols.pingpong.types")

    codeGen(GlobalTypes.TestProtocols.PING_PONG_Spawn, "PingPongSpawn",
      "examples/src/main/scala/example/TestProtocols/pingpong_spawn/PingPongSpawn_Types.scala",
      "package example.TestProtocols.pingpong_spawn")
    //
    codeGen(GlobalTypes.TestProtocols.PING_PONG_Spawn_Branch, "PingPongSpawnBranch",
      "examples/src/main/scala/example/TestProtocols/pingpong_spawn_branch/PingPongSpawnBranch_Types.scala",
      "package example.TestProtocols.pingpong_spawn_branch")

    codeGen(GlobalTypes.TestProtocols.RestartInFhandling,
      "RestartOnFailure",
      "examples/src/main/scala/example/TestProtocols/restartOnFailure/types/RestartOnFailureTypes.scala",
      "package example.TestProtocols.restartOnFailure.types")


    codeGen(GlobalTypes.TestProtocols.SndIntoBranch, "SndIntoBranching",
      "examples/src/main/scala/example/TestProtocols/SndIntoBranching/types/SndIntoBranchingTypes.scala",
      "package examples.TestProtocols.SndIntoBranching.types")

    codeGen(GlobalTypes.TestProtocols.SIMPLE_REC, "Simple_Rec",
      "examples/src/main/scala/example/TestProtocols/simple_rec/generated/SRecTypes.scala",
      "package examples.TestProtocols.simple_rec.generated")

    codeGen(GlobalTypes.GlobalProgress.THREE_BUYER, "ThreeBuyer",
      "examples/src/main/scala/example/three_buyer_global_progress/ThreeBuyerTypes.scala",
      "package example.three_buyer_global_progress")

    codeGen(GlobalTypes.JACM_16.TWO_BUYER, "TwoBuyer",
      "examples/src/main/scala/example/two_buyer/TwoBuyerTypes.scala",
      "package example.two_buyer")
    //########


    codeGen(GlobalTypes.TestProtocols.SIMPLE_SPAWN, "TEST_SPAWN",
      "core/src/test/scala/event_lang/semantic/testtypes/TEST_SPAWN.scala",
      "package event_lang.semantic.testtypes")


  }
}
