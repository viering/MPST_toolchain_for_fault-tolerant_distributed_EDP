package example.TestProtocols.restartOnFailure

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, HDL, TState}
import example.TestProtocols.restartOnFailure.types.RestartOnFailure.A._
import example.TestProtocols.restartOnFailure.types.RestartOnFailure.B._
import example.TestProtocols.restartOnFailure.types.RestartOnFailure.MESSAGES.Main.FEnd
import example.TestProtocols.restartOnFailure.types.RestartOnFailure.MESSAGES.RestartP._
case class AState(restartCnt : Int = 0) extends TState

class EP_A(val numRestart : Int = 2, val block: (AbstractChannelType, AState, AbstractChannelImp) => Boolean = (d, x, s) => false,
                val customCode: (AbstractChannelType, AState, AbstractChannelImp) => Unit = (d, x, s) => {})
  extends EPType_A[AState] with AbstractEndPointTesting[__EPType_A,AState] {


  override def onStartUp: AState = AState()


  override val receive: Seq[HDL[AState]] = ELoop(
    λ(Main_a_A.Failed_b_B,Main_a_A.SndFEnd) {
      case c => c.failed_b_B() ! FEnd()
    },
    λ(RestartP_a_A.SndM) {
      case c => c ! M()
    },
    λ(RestartP_a_A.RcvM1) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(RestartP_a_A.Failed_bb_B) {
      case c => c.failed_bb_B()
    },
    λ_state(RestartP_a_A.SndF1) {
      case (s,c) if s.restartCnt < numRestart => (s.copy(restartCnt = s.restartCnt + 1),c ! F1())
    },
    λ_state(RestartP_a_A.SndF2) {
      case (s,c) if s.restartCnt >= numRestart => (s,c ! F2())
    },
  )
}
case class BState() extends TState
class EP_B(val block: (AbstractChannelType, BState, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, BState, AbstractChannelImp) => Unit = (d, x, s) => {})
  extends EPType_B[BState] with AbstractEndPointTesting[__EPType_B,BState] {


  override def onStartUp: BState = BState()


  override val receive: Seq[HDL[BState]] = ELoop(
    λ(Main_B.RcvFEnd) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(RestartP_b_B.RcvM) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(RestartP_b_B.SndM1) {
      case c =>
        c ! M1()
    },
    λ(RestartP_B.RcvF1) {
      case c =>
        println(s"B:Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(RestartP_B.RcvF2) {
      case c =>
        println(s"B:Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(RestartP_b_B.RcvF1) {
      case c =>
        println(s"b_B: Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(RestartP_b_B.RcvF2) {
      case c =>
        println(s"b_B: Rcv: ${c.rcvMSG}")
        c.channelCon
    },
  )
}