package example.sparkcmminus

import event_lang.dsl.{AbstractChannelImp, HDL, TState}
import example.sparkcmminus.SessionCMMinusTypes.W._
import example.sparkcmminus.SessionCMMinusTypes.MESSAGES.StartDriver.{Ack, DriverDesc, DriverFailed, End, PrepSpawn}
import example.sparkcmminus.SessionCMMinusTypes.MESSAGES.StartEx.{ExDesc, ExDone, ExFailed, ExFinished}
import example.sparkcmminus.SessionCMMinusTypes.MESSAGES._

import scala.util.Random

case class WState() extends TState {

  def appId(implicit id: Long): String = ""

  def exId(implicit id: Long): Int = 0

  def setDriverDesc(ld: LaunchDriver)(implicit id: Long): WState = this

  def startDriver(implicit id: Long): WState = this


  def spawnStartEx(id: Long, childId: Option[Long]): WState = this
}


class EP_Worker(sState: WState = WState()) extends EPType_W[WState] {

  implicit def chToId(c: AbstractChannelImp): Long = {
    c.session.sesId
  }


  import example.sparkcmminus.SessionCMMinusTypes.M._

  override def onStartUp: WState = sState

  override val receive = ELoop(
    /*
Start Driver
 */
    λ_state(StartDriver_wD_W.RcvDriverDesc, StartDriver_wD_W.SndAck) {
      case (s, c) =>
        c ? {
          case (m, c2) =>
            implicit val sID: Long = c
            (s.setDriverDesc(m.driver).startDriver, c2 ! Ack(m.driver.appId))
        }
    },
    λ(StartDriver_W.RcvPrepSpawn) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    λ(StartDriver_wD_W.RcvPrepSpawn) {
      case c => c ? {
        case (m, c2) =>
          println(s"[wD] Received $m")
          c2
      }
    },
    λ(StartDriver_W.RcvEnd) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    λ(StartDriver_wD_W.RcvEnd) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    λ_static_state(StartDriver_wD_W.SpawnStartEx) {
      case (s, c) =>
        (s, c)
    },
    λ_static_state(StartDriver_W.SpawnStartEx) {
      case (s, c) =>
        (s.spawnStartEx(c.session.sesId, c.session.newSubId), c)
    },
    λ(StartDriver_W.RcvDriverFailed) {
      case c
      => c ? {
        case (m, c2) =>
          println(s"[W] Rcv $m")
          c2
      }
    },
    λ_static_state(StartDriver_W.SpawnStartEx) {
      case (s, c) => (s, c)
    },
    /*
    Start Executor
     */
    λ_state(StartEx_wD_W.RcvExFinished) {
      case (s, c) =>
        c ? {
          case (m, c2) =>
            implicit val sID: Long = c
            println(s"[wD] Received $m in $s")
            (s, c2)
        }
    },
    λ_state(StartEx_wEx_W.RcvExDesc, StartEx_wEx_W.SndExDone) {
      case (s, c) => c ? {
        case (m, c2) => {
          println(s"[wEx] Received $m in $s")
          //TODO here we would normally need to perform work and only send out the message after doing the work
          (s, c2 ! ExDone(m.launchEx.appId, m.launchEx.exId))
        }
      }
    },
    λ_state(StartEx_W.RcvExFailed) {
      case (s, c) => c ? {
        case (m, c2) =>
          implicit val sID: Long = c
          println(s"[W] Received $m")
          (s, c2)
      }
    },
    λ_state(StartEx_wD_W.RcvExFailed) {
      case (s, c) => c ? {
        case (m, c2) =>
          implicit val sID: Long = c
          println(s"[wD] Received $m")
          (s, c2)
      }
    }

  )

}
