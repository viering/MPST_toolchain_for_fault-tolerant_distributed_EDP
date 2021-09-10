package example.ring

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, TState}
import event_lang.types.{Role, RoleSet}
import example.ring.types.Ring.A.{EPType_A, __EPType_A}
import example.ring.types.Ring.MESSAGES.Chain.{End, FChain, MI, MLast, Spawn}
import example.ring.types.Ring.MESSAGES.Main._
import example.ring.types.Ring.MESSAGES.Pick._
import example.ring.types.Ring.W.EPType_W


case class AState(numChains: Int = 8) extends TState

class EP_A(sState: AState = AState(), val block: (AbstractChannelType, AState, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, AState, AbstractChannelImp) => Unit = (a, b, c) => {}) extends EPType_A[AState] with AbstractEndPointTesting[__EPType_A, AState] {

  import example.ring.types.Ring.A._

  override def onStartUp: AState = sState

  override val receive = ELoop(
    λ(Main_a_A.Failed_w0_W, Main_a_A.SndFMain) {
      case c => c.failed_w0_W() ! FMain()
    },

    λ(Pick_a_A.Failed_w1_W, Pick_a_A.SndFPick) {
      case c => c.failed_w1_W() ! FPick()
    },

    λ_state(Chain_a_A.SndSpawn) {
      case (s, c) if s.numChains > 0 =>
        (s.copy(numChains = s.numChains - 1), c ! Spawn())
    },
    λ_state(Chain_a_A.SndEnd) {
      case (s, c) if s.numChains <= 0 => (s, c ! End())
    },
    λ(Chain_a_A.Failed_wII_W, Chain_a_A.SndFChain) {
      case c => c.failed_wII_W() ! FChain()
    }
  )
}

case class WState() extends TState

class EP_W(sState: WState = WState()) extends EPType_W[WState] {

  import example.ring.types.Ring.W._

  override def onStartUp: WState = sState

  override val receive = ELoop(
    λ(Main_W.RcvFMain) {
      case c => c.channelCon
    },

    λ(Pick_w0_W.SndM1) {
      case c => c ! M1()
    },
    λ(Pick_w0_W.RcvFPick) {
      case c => c.channelCon
    },
    λ(Pick_W.RcvFPick) {
      case c => c.channelCon
    },
    λ(Pick_w1_W.RcvM1) {
      case c =>
        println(s"${c.session.roleToPId(Role("w0", RoleSet("W")))} w0 -- M() -> ${c.session.myId}: w")
        c.channelCon
    },

    λ(Chain_W.RcvEnd) {
      case c => c.channelCon
    },
    λ(Chain_W.RcvSpawn) {
      case c => c.channelCon
    },
    λ(Chain_W.RcvFChain) {
      case c => c.channelCon
    },

    λ(Chain_w0_W.RcvMLast) {
      case c =>
        println(s"${c.session.roleToPId(Role("wII", RoleSet("W")))} wII -- M() -> ${c.session.myId}: w0")
        c.channelCon
    },
    λ(Chain_w0_W.RcvEnd) {
      case c => c.channelCon
    },
    λ(Chain_w0_W.RcvFChain) {
      case c => c.channelCon
    },
    λ(Chain_w0_W.RcvSpawn) {
      case c => c.channelCon
    },

    λ(Chain_wI_W.SndMI) {
      case c => c ! MI()
    },
    λ(Chain_wI_W.RcvSpawn) {
      case c => c.channelCon
    },
    λ(Chain_wI_W.RcvEnd) {
      case c => c.channelCon
    },
    λ(Chain_wI_W.RcvFChain) {
      case c => c.channelCon
    },
    λ(Chain_wII_W.RcvMI) {
      case c =>
        println(s"${c.session.roleToPId(Role("wI", RoleSet("W")))} wI -- M() -> ${c.session.myId}: wII")
        c.channelCon
    },
    λ(Chain_wII_W.RcvEnd, Chain_wII_W.SndMLast) {
      case c => c.channelCon ! MLast()
    },
    λ(Chain_wII_W.RcvSpawn) {
      case c => c.channelCon
    },
  )
}