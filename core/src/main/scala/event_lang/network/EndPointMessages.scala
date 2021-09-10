package event_lang.network


import event_lang.types._

object EndPointMessages {

  trait EPtoEPMSG

  trait SessionMSG extends EPtoEPMSG {
    def sesID: Long

    def frm: Int
  }
  trait ControlMSG extends EPtoEPMSG

  trait GlobalMSG extends EPtoEPMSG

  case class FailureSuspicionToMain(idFailedP: Int) extends GlobalMSG with ControlMSG
  case class FailureNotification(idFailedP: Int) extends GlobalMSG with ControlMSG
  case class SignalSpawnIntend(sesID: Long, name: String, frm: Int) extends SessionMSG with ControlMSG
  case class SpawnSession(sesID: Long, frm: Int, newSesId: Long, pickedPID: Int, name: String) extends SessionMSG
  case class DataMSG(frm: Int, to: Int, sesID: Long, payload: MSG) extends SessionMSG
  case class SignalTerminationIntend(sesID: Long, frm: Int, defaultActivity : Boolean) extends SessionMSG with ControlMSG
  case class SignalTermination(sesID: Long, frm: Int, defaultActivity : Boolean) extends SessionMSG

}
