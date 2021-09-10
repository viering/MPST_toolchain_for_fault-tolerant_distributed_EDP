package example.TestProtocols.simple_rec

import event_lang.dsl._
import event_lang.types.RoleSet
import examples.TestProtocols.simple_rec.generated.Simple_Rec.A._
import examples.TestProtocols.simple_rec.generated.Simple_Rec.B.{EPType_B, Main_b_B, __EPType_B}
import examples.TestProtocols.simple_rec.generated.Simple_Rec.MESSAGES.Main.M
import examples.TestProtocols.simple_rec.generated.Simple_Rec._

case class StateA(c: Int = 0) extends TState
case class StateB()extends TState

class EP_A(val block: (AbstractChannelType, StateA, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateA, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_A[StateA] with AbstractEndPointTesting[__EPType_A,StateA] {
  override val roleSet: RoleSet = RS.A

  override def onStartUp: StateA = StateA()

  override val subs: Seq[ChannelTypeSubS] = A.subs


  override val receive: Seq[HDL[StateA]] = ELoop(
    λ_state(Main_a_A.SndM) {
      case (s, c) => {
        (s.copy(c = s.c + 1), c ! M(s.c))
      }
    },
    λ(Main_a_A.Failed_b_B) {
      case c => {
        c.failed_b_B()
      }
    }
  )
}

class EP_B(val block: (AbstractChannelType, StateB, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateB, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_B[StateB] with AbstractEndPointTesting[__EPType_B,StateB]{
  override val roleSet: RoleSet = RS.B

  override def onStartUp: StateB = StateB()


  override val receive: Seq[HDL[StateB]] = ELoop(
    λ(Main_b_B.RcvM) {
      case c => c ? {
        case (m, c) =>
          println(m)
          c
      }
    }
  )
  override val subs: Seq[ChannelTypeSubS] = B.subs
}
