package example.TestProtocols.branchFActivation

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, HDL, TState}
import example.TestProtocols.branchFActivation.types.BranchInFActivation.A._
import example.TestProtocols.branchFActivation.types.BranchInFActivation.B._
import example.TestProtocols.branchFActivation.types.BranchInFActivation.MESSAGES.Main._
case class AState() extends TState

class EP_A(val block: (AbstractChannelType, AState, AbstractChannelImp) => Boolean = (d, x, s) => false,
                val customCode: (AbstractChannelType, AState, AbstractChannelImp) => Unit = (d, x, s) => {})
  extends EPType_A[AState] with AbstractEndPointTesting[__EPType_A,AState] {


  override def onStartUp: AState = AState()


  override val receive: Seq[HDL[AState]] = ELoop(
    λ(Main_a_A.SndM) {
      case c => c ! M()
    },
    λ(Main_a_A.RcvM1) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(Main_a_A.Failed_b_B) {
      case c => c.failed_b_B()
    },
    λ(Main_a_A.SndF1) {
      case c => c ! F1()
    },
    λ(Main_a_A.SndF2) {
      case c => c ! F2()
    },
  )
}
case class BState() extends TState
class EP_B(val block: (AbstractChannelType, BState, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, BState, AbstractChannelImp) => Unit = (d, x, s) => {})
  extends EPType_B[BState] with AbstractEndPointTesting[__EPType_B,BState] {


  override def onStartUp: BState = BState()


  override val receive: Seq[HDL[BState]] = ELoop(
    λ(Main_b_B.RcvM) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(Main_b_B.SndM1) {
      case c =>
        c ! M1()
    },
    λ(Main_B.RcvF1) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(Main_B.RcvF2) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    },
  )
}