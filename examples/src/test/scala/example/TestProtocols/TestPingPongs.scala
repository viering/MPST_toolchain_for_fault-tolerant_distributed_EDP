package example.TestProtocols

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import example.TestProtocols.pingpong_spawn_branch.PingPongSpawnBranch.B.PingPong_b_B.{RcvEnd, RcvPing}
import example.TestProtocols.pingpong_spawn_branch.PingPongSpawnBranch.B.PingPong_bb_B.{RcvEndBrn, RcvPingBrn}
import example.util.{EPRunner, VoidOutStream}
import org.scalatest.concurrent.{ThreadSignaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

class TestPingPongs extends AnyFunSuite with TimeLimitedTests{

  val timeLimit = Span(30, Seconds)
  override val defaultTestSignaler = ThreadSignaler

  test("Ping Pong: One round and termination via Failure") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    import example.TestProtocols.pingpong.{EP_A, EP_B}
    import example.TestProtocols.pingpong.types.PingPong.A.Main_a_A.RcvPong

    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null

    var aRound = false


    var curV = 0
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvPong, _, _) =>
        network.ep_ses_channels(0).signalFail(1)
        aRound = true
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new EP_A(customCode = customCode), List(0)), (() => new EP_B(), List(1)))
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(aRound)
  }}}

  test("Ping Pong Spawn: One round and termination via Failure") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    import example.TestProtocols.pingpong_spawn.{EP_A, EP_B}
    import example.TestProtocols.pingpong_spawn.PingPongSpawn.A.PingPong_a_A.RcvPong
    import example.TestProtocols.pingpong_spawn.PingPongSpawn.B.Main_B

    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null

    var aRound = false

    var curV = 0
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvPong, _, _) =>
        network.ep_ses_channels(0).signalFail(1)
      case (Main_B.RcvFailB ,_,_) =>
        aRound = true
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new EP_A(customCode = customCode), List(0)), (() => new EP_B(customCode=customCode), List(1,2)))
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(aRound)
  }}}

  test("Ping Pong Spawn Branch: Normal execution") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    import example.TestProtocols.pingpong_spawn_branch.{EP_A, EP_B}
    import example.TestProtocols.pingpong_spawn_branch._

    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null

    var aRound = false

    var vPingCnt = 1
    var vPingBrnCnt = 1

    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvPing, _, _) =>
        vPingCnt+=1
      case (RcvPingBrn ,_,_) =>
        vPingBrnCnt += 1
      case (RcvEnd,_,_)=>
        assert(vPingCnt==12)
      case (RcvEndBrn,_,_)=>
        assert(vPingBrnCnt==12)
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new EP_A(customCode = customCode), List(0)), (() => new EP_B(customCode=customCode), List(1,2)))
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(vPingCnt==12)
    assert(vPingBrnCnt==12)
  }}}
}
