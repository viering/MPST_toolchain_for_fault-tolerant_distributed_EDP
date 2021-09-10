package example.TestProtocols.pingpong

import event_lang._
import event_lang.dsl._
import event_lang.types.RoleSet
import example.TestProtocols.pingpong.types.PingPong.A.{EPType_A, __EPType_A}
import example.TestProtocols.pingpong.types.PingPong.B.EPType_B
import example.TestProtocols.pingpong.types.PingPong.MESSAGES.Main.{Ping, Pong}
import example.TestProtocols.pingpong.types.PingPong._


case class StateB(buf: String = "") extends TState

case class StateA(buf: String = "") extends TState

class EP_A(val block: (AbstractChannelType, StateA, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateA, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_A[StateA] with AbstractEndPointTesting[__EPType_A, StateA] {
  override val roleSet: RoleSet = RoleSet("A")

  override def onStartUp: StateA = StateA()

  override val subs: Seq[ChannelTypeSubS] = A.subs

  import example.TestProtocols.pingpong.types.PingPong.A.{Main_a_A => main}

  //TODO remove
  def remove_late(o: Object): Unit = {
    println(o)
  }

  def remove_later_as_well() = {
    val f = (o: Object) => {
      println(o)
    }
    f
  }

  // (EPType_A, StateA) => (EPType_A, StateA)
  //
  override val receive = ELoop(
    // (args) => {Body}
    λ(main.SndPing, main.SndPing) {
      case c => {
        c ! Ping(s"Ping")
      }
    },
    λ_state(main.RcvPong, main.RcvPong) {
      case (s, c) => {
        c ? {
          case (m, c2) =>
            Thread.sleep(100)
//            for (1 <- 0 to 10) {
//              print(s"${c.rcvMSG}")
//              print(c.rcvFrmb_B._2)
//              print(c ? {case (m,c) => m})
//            }
            println(s"rcv: $m s:")
            (s.copy(buf = s.buf + m), c2)
        }
      }
    }
    ,
    λ(main.Failed_b_B) {
      case c =>
        c.failed_b_B()
    }
  )
}

class EP_B extends EPType_B[StateB] {
  override val roleSet: RoleSet = RoleSet("B")

  override def onStartUp: StateB = StateB()

  import example.TestProtocols.pingpong.types.PingPong.B.{Main_b_B => main}

  override val receive = ELoop(
    λ_state(main.SndPong, main.SndPong) {
      case (s, c) => {
        val ns = if (s.buf.length > 100) s.copy(buf = "") else s
        (ns, c.sndToa_A(Pong(s"${ns.buf} Pong")))
      }
      //case _ => null
    },
    λ_state(main.RcvPing) {
      case (s, c) => {
        c ? {
          case (m, c2) =>
            Thread.sleep(100)
            println(s"rcv: $m s:")
            (s.copy(buf = s.buf + m), c2)
        }
      }
    }
  )
  override val subs: Seq[ChannelTypeSubS] = B.subs
}
