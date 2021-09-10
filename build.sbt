import sbt.Tests.{Group, SubProcess}

val scalaFullBuildVersion = "2.12.12"
val scalaBuildVersion = "2.12"
name := "lang_fhandling"
version := "0.1"

ThisBuild / organization := "org.me"
ThisBuild / version := "0.1-SNAPSHOT"

publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)
name := "My Project"
scalaVersion in ThisBuild := scalaFullBuildVersion
//scalacOptions += "-target:jvm-1.11"

logLevel := Level.Error

lazy val global = project
  .in(file("."))
  .settings(retrieveManaged := true)
  .settings(
    settings,
    logLevel := sbt.util.Level.Error,
    retrieveManaged := true,
    parallelExecution in Test := false,
    parallelExecution in ThisBuild := false
  )
  .aggregate(
    core,
    examples,
    util
  )


lazy val core = project
  .settings(retrieveManaged := true)
  .settings(
    name := "core",
    settings,
    libraryDependencies ++= commonDependencies ++ Seq(dependencies.antlr,
      dependencies.netty,
      dependencies.chill,
      dependencies.chillBijection,
      dependencies.kryo,
      //                                                      dependencies.objenesis,
      dependencies.config,
      dependencies.scalaTest)
  )


lazy val genTypesTwoBuyers = taskKey[Unit]("Generate Types for TwoBuyer")


lazy val util = project
  .settings(retrieveManaged := true)
  .settings(
    name := "util",
    //    fullRunTask(genTypesTwoBuyers, Test, "event_lang.LocalTypeGenerator",
    //      "TwoBuyer",
    //      "example.two_buyer",
    //      "examples/src/main/scala/example/two_buyer/TwoBuyerTypes.scala",
    //      "resources/global_types/TwoBuyer.g"
    //    ),
    fullRunInputTask(InputKey("generateTypes"), Test, "event_lang.LocalTypeGenerator"),
    fullRunInputTask(InputKey("generateExampleTypes"), Test, "event_lang.HardcodedLocalTypeGen"
    ),
    settings,
    libraryDependencies ++= commonDependencies
  ).dependsOn(
  core
)

lazy val runRingABC = taskKey[Unit]("Execute Ring ABC")
lazy val runStreaming = taskKey[Unit]("Execute Streaming")
lazy val runTwoBuyer = taskKey[Unit]("Execute Two Buyer")
lazy val runThreeBuyer = taskKey[Unit]("Execute Three Buyer")
lazy val runSessionCM = taskKey[Unit]("Execute the Session CM")
lazy val runDSH = taskKey[Unit]("Execute the Sutherland-Hodgman application")
lazy val runNPip = taskKey[Unit]("Execute the N-stage Pipe")
lazy val runNPipe = taskKey[Unit]("Execute the N-stage Pipe")
lazy val runNRing = taskKey[Unit]("Execute the N-state Ring")
lazy val runTwoFactor = taskKey[Unit]("Execute the Two Factor")
lazy val runResControl = taskKey[Unit]("Execute the Resource Control")
lazy val runWebCrawler = taskKey[Unit]("Execute the WebCrawler")
lazy val runInterruptibleThreeBuyer = taskKey[Unit]("Execute the Interruptible 3-Buyers")
lazy val runFailureAwareStreaming = taskKey[Unit]("Execute the Failure-Aware Streaming")

lazy val runTwoBuyerCovError = taskKey[Unit]("Execute Two Buyer -- with Coverage Error")
lazy val runTwoBuyerLinError = taskKey[Unit]("Execute Two Buyer -- with Linearity Error")

lazy val runTestVBranching = taskKey[Unit]("Test VBraching")
lazy val runTestStreaming = taskKey[Unit]("Test Streaming")
lazy val runTestTwoBuyer = taskKey[Unit]("Test Two Buyer")
lazy val runTestThreeBuyer = taskKey[Unit]("Test Three Buyer")
lazy val runTestSessionCM = taskKey[Unit]("Test The Session CM")
lazy val runTestAllExample = taskKey[Unit]("Test that runs all examples from the paper")

def groupByFirst(tests: Seq[TestDefinition]) =
  tests groupBy (_.name(0)) map {
    case (letter, tests) =>
      val options = ForkOptions().withRunJVMOptions(Vector("-Dfirst.letter"+letter))
      new Group(letter.toString, tests, SubProcess(options))
  } toSeq
lazy val examples = project
  .settings(retrieveManaged := true)
  .settings(
    name := "examples",
    fullRunTask(runRingABC, Test, "example.frm_paper.ring.failure_handling.RingRunner", "arg1", "arg2"),
    fullRunTask(runStreaming, Test, "example.streaming_popl_08.StreamingRunnerNoNetwork", "arg1", "arg2"),
    fullRunTask(runTwoBuyer, Test, "example.two_buyer.TwoBuyerLocalRunner", "arg1", "arg2"),
    fullRunTask(runThreeBuyer, Test, "example.three_buyer_global_progress.ThreeBuyerRunner", "arg1", "arg2"),
    fullRunTask(runSessionCM, Test, "example.sparkCluster.ClusterRunner", "arg1", "arg2"),
    //
    fullRunTask(runDSH, Test, "example.dsh.DSH_Runner", ""),
    fullRunTask(runNPip, Test, "example.pipe.PipeRunner", ""),
    fullRunTask(runNPipe, Test, "example.pipe.PipeRunner", ""),
    fullRunTask(runNRing, Test, "example.ring.RingRunner", ""),
    fullRunTask(runTwoFactor, Test, "example.ExceptionalAsynST_POPL.twoFactor.TwoFactorRunner", ""),
    fullRunTask(runResControl, Test, "example.resourceAccessControl.ResControllRunner", ""),
    fullRunTask(runWebCrawler, Test, "example.LetItRecover.webcrawler.WebCrawlerRunner", ""),
    fullRunTask(runInterruptibleThreeBuyer, Test, "example.globalescape.buyer.InterBuyerRunner", ""),
    fullRunTask(runFailureAwareStreaming, Test, "example.esop_crash_handling.streaming.ClusterRunner", ""),
    //
    fullRunTask(runTwoBuyerCovError, Test, "example.faulty.twoBuyer.coverage.TwoBuyerRunnerCoverageError", ""),
    fullRunTask(runTwoBuyerLinError, Test, "example.faulty.twoBuyer.linearity.TwoBuyerRunnerLinError", ""),
    runTestVBranching := {
      (testOnly in Test).toTask(s" example.TestProtocols.TestVBranching").value
    },
    runTestStreaming := {
      (testOnly in Test).toTask(s" example.StreamingTests").value
    },
    runTestTwoBuyer := {
      (testOnly in Test).toTask(s" example.TwoBuyerTests").value
    },
    runTestThreeBuyer := {
      (testOnly in Test).toTask(s" example.ThreeBuyerTests").value
    },
    runTestSessionCM := {
      (testOnly in Test).toTask(s" example.session_cm.TestSessionCM").value
    },
    runTestAllExample := {
      (testOnly in Test).toTask(s" example.PaperExampleTests").value
    },
    settings,
//    connectInput in run := true,
//    Test / fork := true,
//    Test / testGrouping := groupByFirst( (Test / definedTests).value ),
//      parallelExecution in Test := false,
    parallelExecution in ThisBuild := false,
    libraryDependencies ++= commonDependencies ++ Seq(dependencies.scalaTest, dependencies.netty, dependencies.config, dependencies.javaassist, dependencies.quicklens)
  )
  .dependsOn(
    core
  )


resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

lazy val commonDependencies = Seq(
  dependencies.log4j,
)

lazy val settings = commonSettings


lazy val compilerOptions = Seq(
  "utf8",
  "-language:postfixOps",
  "-explaintypes"
)

lazy val commonSettings = Seq(
  publishArtifact in(Compile, packageDoc) := false,
  publishConfiguration := publishConfiguration.value.withOverwrite(true),
  publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true),
  scalacOptions ++= compilerOptions,
  retrieveManaged := true,
  parallelExecution in Test := false)

lazy val dependencies =
  new {
//
//    val scalaReflect = "org.scala-lang" %% "scala-reflect"
//    val scalaCompiler = "org.scala-lang" % "scala-compiler" % scalaFullBuildVersion withSources() withJavadoc()

    val antlr = "org.antlr" % "antlr4" % "4.5.1"
    //    val slf4j            = "org.slf4j" % "slf4j-simple" % "1.7.12"  withSources() withJavadoc()
    //    val log4jslf4j       = "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.13.3"  withSources() withJavadoc()
    val log4j = "org.apache.logging.log4j" % "log4j-core" % "2.13.3" withSources() withJavadoc()

    val netty = "io.netty" % "netty-all" % "4.1.52.Final"

    val chill = "com.twitter" %% "chill" % "0.9.5"
    val chillBijection = "com.twitter" %% "chill-bijection" % "0.9.5"
    val kryo = "com.esotericsoftware" % "kryo-shaded" % "4.0.2"
    val objenesis = "org.objenesis" % "objenesis" % "3.0.1"

    val quicklens = "com.softwaremill.quicklens" %% "quicklens" % "1.6.1"
    //    val spark = "org.apache.spark" %% "spark-core"% "3.0.1"

    val javaassist = "org.javassist" % "javassist" % "3.25.0-GA"
    val scalaTest = "org.scalatest" %% "scalatest" % "3.1.0" % "test" withSources() withJavadoc()
    val config = "com.typesafe" % "config" % "1.3.4" withSources() withJavadoc()
  }

