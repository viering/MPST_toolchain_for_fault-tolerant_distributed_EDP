package example.frm_paper.ring.fault_tolerant

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, HDL, TState}
import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.A.{EPType_A, __EPType_A}
import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.B.{EPType_B, __EPType_B}
import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.C.{EPType_C, __EPType_C}
import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.MESSAGES.Main._
import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.MESSAGES.g1._


case class StateA() extends TState

class EP_A(val block: (AbstractChannelType, StateA, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateA, AbstractChannelImp) => Unit = (d, x, s) => {})
  extends EPType_A[StateA] with AbstractEndPointTesting[__EPType_A, StateA] {

  override def onStartUp: StateA = StateA()

  import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.A._

  override val receive: Seq[HDL[StateA]] = ELoop(
    λ(Main_a_A.SndL1) {
      case c => c ! L1("Hello")
    },
    λ(Main_a_A.Failed_b_B, Main_a_A.SndLFMain1, Main_a_A.SndLFMain2) {
      case c => c.failed_b_B() ! LFMain1() ! LFMain2()
    },
    λ(g1_a_A.RcvL3) {
      case c => c ? {
        case (m, c) =>
          println(s"## Rcv $m")
          c
      }
    },
    λ(g1_a_A.Failed_c_C, g1_a_A.SndLFg2, g1_a_A.SndLFg3) {
      case c => c.failed_c_C() ! LFg2() ! LFg3()
    }
  )
}

case class StateB() extends TState

class EP_B(val block: (AbstractChannelType, StateB, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateB, AbstractChannelImp) => Unit = (a,b,c) => {})
  extends EPType_B[StateB] with AbstractEndPointTesting[__EPType_B, StateB] {

  override def onStartUp: StateB = StateB()

  import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.B._

  override val receive: Seq[HDL[StateB]] = ELoop(
    λ(Main_b_B.RcvL1) {
      case c => c ? {
        case (m, c) => c
      }
    },
    λ(Main_B.RcvLFMain1) {
      case c => c ? {
        case (m, c) => c
      }
    },
    λ(g1_b_B.SndL2) {
      case c => c ! L2("Hello")
    },
    λ(g1_b_B.RcvLFg2) {
      case c => c ? {
        case (m, c) => c
      }
    }
  )
}

case class StateC() extends TState

class EP_C(val block: (AbstractChannelType, StateC, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateC, AbstractChannelImp) => Unit = (a,b,c) => {})
  extends EPType_C[StateC] with AbstractEndPointTesting[__EPType_C, StateC] {

  import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.C._

  override def onStartUp: StateC = StateC()
  import g1_c_C._
  import g1_c_C.{RcvL2 => bRcvL2}
  import g1_c_C.{SndL3 => aSndL3}

  override val receive: Seq[HDL[StateC]] = ELoop(
    λ(Main_C.RcvLFMain2) {
      case c => c ? {
        case (m, c) => c
      }
    },
//    λ(g1_c_C.RcvL2, g1_c_C.SndL3) {
//      case c => c ? {
//        case (m, c) => c ! L3()
//      }
//    },
    λ(bRcvL2, aSndL3) { case c =>
      c ? { case (L2(m), c) =>  c ! L3(m) }},
    λ(g1_C.RcvLFg3) {
      case c => c ? {
        case (m, c) =>

          c
      }
    }
  )
}