package example.sparkcmminus

import event_lang.dsl.{AbstractChannelImp, HDL, TState}
import example.sparkcmminus.SessionCMMinusTypes.M._
import example.sparkcmminus.SessionCMMinusTypes.MESSAGES.StartDriver.{DriverDesc, DriverFailed, End, PrepSpawn}
import example.sparkcmminus.SessionCMMinusTypes.MESSAGES.StartEx.{ExDesc, ExFailed, ExFinished}
import example.sparkcmminus.SessionCMMinusTypes.MESSAGES._

import scala.util.Random

case class MState(wUnit : Int = 2) extends TState {
  def exDescription(implicit id: Long): ExDesc = ExDesc(LaunchExecutor())

  def driverDescription(implicit id: Long): DriverDesc = DriverDesc(LaunchDriver())

  def appId(implicit id: Long): String = ""

  def exId(implicit id: Long): Int = 0

  def workLeft(implicit id: Long) : Boolean = {
    wUnit > 0
  }
  def updateState(implicit id : Long) : MState = this.copy(wUnit = wUnit -1)

  def spawnStartEx(id : Long, childId : Option[Long]) :MState = this
}


class EP_Master(sState: MState = MState()) extends EPType_M[MState] {

  implicit def chToId(c: AbstractChannelImp): Long = {
    c.session.sesId
  }


//type λ[ST <: TState] = HDL[ST]
  import example.sparkcmminus.SessionCMMinusTypes.M._

  override def onStartUp: MState = sState

  override val receive : Seq[HDL[MState]] = ELoop(
    /*
Start Driver
 */
    λ_state(StartDriver_m_M.SndDriverDesc) {
      case (s, c) =>
        implicit val sID: Long = c
        (s, c ! s.driverDescription)
    },
    λ(StartDriver_m_M.RcvAck) {
      case c => c ? {
        case (m,c2) => c2
      }
    },
    λ_state(StartDriver_m_M.SndPrepSpawn){
      case (s,c) if s.workLeft(c.session.sesId) =>
        println(s"[Master] Issue PrepSpawn in $s")
        (s,c ! PrepSpawn())
    },
    λ_state(StartDriver_m_M.SndEnd){
      case (s,c) =>
        (s,c ! End())
    },
    λ_static_state(StartDriver_m_M.SpawnStartEx) {
      case (s,c) =>
        println(s"[Master] Spawn StartEx")
        implicit val sID: Long = c
        (s.updateState,c)
    },
    λ_state(StartDriver_m_M.Failed_wD_W,StartDriver_m_M.SndDriverFailed) {
      case (s,c) =>
        implicit val sID: Long = c
        (s,c.failed_wD_W() ! DriverFailed(s.appId))
    },
    λ_static_state(StartDriver_m_M.SpawnStartEx){
      case (s,c) =>
        (s.spawnStartEx(c.session.sesId,c.session.newSubId),c)
    },
      /*
      Start Executor
       */
      λ_state (StartEx_m_M.SndExDesc) {
      case (s, c) =>
        implicit val sID: Long = c
        (s, c ! s.exDescription)
    },



    λ(StartEx_m_M.RcvExDone, StartEx_m_M.SndExFinished) {
      case c => c ? {
        case (m, c2) => {
          c2 ! ExFinished(m.appId, m.exId)
        }
      }
    },



    λ_state(StartEx_m_M.Failed_wEx_W, StartEx_m_M.SndExFailed) {
      case (s, c) =>
        implicit val sID: Long = c
        (s, c.failed_wEx_W() ! ExFailed(s.appId, s.exId))
    },
    λ_static(StartEx_m_M.SpawnStartEx) {
      case s => s
    }

  )

}
