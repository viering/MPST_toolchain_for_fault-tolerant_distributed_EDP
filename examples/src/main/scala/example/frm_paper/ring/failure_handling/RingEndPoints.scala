package example.frm_paper.ring.failure_handling

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, HDL, TState}
import example.frm_paper.ring.failure_handling.generated.RingABC._
import example.frm_paper.ring.failure_handling.generated.RingABC.A.{EPType_A, __EPType_A}
import example.frm_paper.ring.failure_handling.generated.RingABC.B.{EPType_B, __EPType_B}
import example.frm_paper.ring.failure_handling.generated.RingABC.C.{EPType_C, __EPType_C}
import example.frm_paper.ring.failure_handling.generated.RingABC.MESSAGES.Main._
import example.frm_paper.ring.failure_handling.generated.RingABC.MESSAGES.g1._
import example.frm_paper.ring.failure_handling.generated.RingABC.MESSAGES.g2._


case class StateA() extends TState

class EP_A(val block: (AbstractChannelType, StateA, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateA, AbstractChannelImp) => Unit = (d, x, s) => {})
  extends EPType_A[StateA] with AbstractEndPointTesting[__EPType_A, StateA] {

  import A._

  override def onStartUp: StateA = StateA()

  override val receive: Seq[HDL[StateA]] = ELoop(
    λ_static(Main_a_A.Spawng1) {
      case c =>
        c
    },
    λ(Main_a_A.SndL1) {
      case c => c ! L1()
    },
    λ(Main_a_A.Failed_b_B, Main_a_A.SndL6) {
      case c => c.failed_b_B() ! L6()
    },

    λ(g1_a_A.RcvL3) {
      case c => c ? {
        case (m, c) =>
          println(s"#### Received L3()")
          c
      }
    },
    λ(g1_a_A.Failed_c_C, g1_a_A.SndL7) {
      case c =>
        val cc = c.failed_c_C()
        cc ! L7()
    },
    λ(g1_a_A.RcvL8) {
      case c => c ? {
        case (m, c) =>
          println(s"### Rcv L8")
          c
      }
    },
    λ(g2_a_A.SndL4) {
      case c => c ! L4()
    },
    λ(g2_a_A.RcvL5) {
      case c => c ? {
        case (m, c) =>
          println(s"#### Received L5()")
          c
      }
    },
    λ(g2_a_A.Failed_c_C) {
      case c => c.failed_c_C()
    }
  )
}


case class StateB() extends TState

class EP_B(val block: (AbstractChannelType, StateB, AbstractChannelImp) => Boolean = (d, s, x) => false,
           val customCode: (AbstractChannelType, StateB, AbstractChannelImp) => Unit = (s,d, x) => {})
  extends EPType_B[StateB] with AbstractEndPointTesting[__EPType_B, StateB] {

  import B._

  override def onStartUp: StateB = StateB()

  override val receive: Seq[HDL[StateB]] = ELoop(
    λ(Main_b_B.RcvL1) {
      case c =>
        c ? {
          case (m, c) =>
            println(s"#### Received L1()")
            c
        }
    },
    λ(g1_b_B.SndL2) {
      case c => c ! L2()
    },

    λ(g1_b_B.RcvL7, g1_b_B.SndL8) {
      case c => c ? {  case (_, c) => c ! L8()   }}
  )
}


case class StateC() extends TState

class EP_C(val block: (AbstractChannelType, StateC, AbstractChannelImp) => Boolean = (s, S, X) => false,
           val customCode: (AbstractChannelType, StateC, AbstractChannelImp) => Unit = (d, s, x) => {})
  extends EPType_C[StateC] with AbstractEndPointTesting[__EPType_C, StateC] {

  import C._

  override def onStartUp: StateC = StateC()

  override val receive: Seq[HDL[StateC]] = ELoop(
    λ(Main_C.RcvL6) {
      case c => c ? {
        case (m, c) => c
      }
    },
    λ(g1_c_C.RcvL2, g1_c_C.SndL3) {
      case c => c ? {
        case (m, c) => c ! L3()
      }
    },
    λ(g2_c_C.RcvL4, g2_c_C.SndL5) {
      case c => c ? {
        case (m, c) => c ! L5()
      }
    }
  )
}