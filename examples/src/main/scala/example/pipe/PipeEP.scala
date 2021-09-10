package example.pipe

import java.util.concurrent.atomic.AtomicInteger
import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, TState}
import example.pipe.types.Pipe.A.{EPType_A, __EPType_A}
import example.pipe.types.Pipe.MESSAGES.Main.FMain
import example.pipe.types.Pipe.MESSAGES.Pipe.{End, FPipe, PM, PrepSpawn}
import example.pipe.types.Pipe.P.{EPType_P, Main_P}

import scala.util.Random


//case class CState() extends TState
//
//class EP_C(sState: CState = CState()) extends EPType_C[CState] {
//   import example.LetItRecover.webcrawler.types.webCrawler.P._
//
//  override def onStartUp: CState = sState
//
//  override val receive = ELoop(
//    λ(ResAccCon_c_C.RcvReq) {
//      case c => c.channelCon
//    },
//  )
//}
case class AState(numPips: Int = 20) extends TState

class EP_A(sState: AState = AState(), val block: (AbstractChannelType, AState, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, AState, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_A[AState] with AbstractEndPointTesting[__EPType_A, AState]
  {

    import example.pipe.types.Pipe.A._

    override def onStartUp: AState = sState

    override val receive = ELoop(
      λ(Main_a_A.Failed_nP_P, Main_a_A.SndFMain) {
        case c => c.failed_nP_P() ! FMain()
      },

      λ(Main_a_A.Failed_nP_P, Main_a_A.SndFMain) {
        case c => c.failed_nP_P() ! FMain()
      },

      λ_state(Pipe_a_A.SndPrepSpawn) {
        case (s, c) if s.numPips > 0 =>
          (s.copy(numPips = s.numPips - 1), c ! PrepSpawn())
      },
      λ_state(Pipe_a_A.SndEnd) {
        case (s, c) if s.numPips <= 0 => (s, c ! End())
      },
      λ(Pipe_a_A.Failed_p_P, Pipe_a_A.SndFPipe) {
        case c => c.failed_p_P() ! FPipe()
      }

    )

  }

  case class PState() extends TState

  class EP_P(sState: PState = PState()) extends EPType_P[PState] {

    import example.pipe.types.Pipe.P._

    override def onStartUp: PState = sState

    override val receive = ELoop(
      λ(Main_P.RcvFMain) {
        case c => c.channelCon
      },

      λ(Main_P.RcvFMain) {
        case c => c.channelCon
      },

      λ(Pipe_P.RcvPrepSpawn) {
        case c => c.channelCon
      },

      λ(Pipe_P.RcvFPipe) {
        case c => c.channelCon
      },

      λ_state(Pipe_nP_P.SndPM) {
        case (s, c) =>
          (s, c ! PM(c.session.myId))
      },
      λ(Pipe_nP_P.RcvPrepSpawn) {
        case c => c.channelCon
      },

      λ(Pipe_nP_P.RcvFPipe) {
        case c => c.channelCon
      },

      λ(Pipe_p_P.RcvPrepSpawn) {
        case c => c.channelCon
      },
      λ(Pipe_p_P.RcvPM) {
        case c =>
          println(s"${c.rcvMSG.id} --> ${c.session.myId}")
          c.channelCon
      },
      λ(Pipe_P.RcvEnd) {
        case c => c.channelCon
      },
      λ(Pipe_nP_P.RcvEnd) {
        case c => c.channelCon
      },
      λ(Pipe_p_P.RcvEnd) {
        case c => c.channelCon
      },
    )
  }