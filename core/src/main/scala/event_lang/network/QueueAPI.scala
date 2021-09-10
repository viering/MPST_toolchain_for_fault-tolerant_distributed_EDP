package event_lang.network

import event_lang.network.EndPointMessages.{ControlMSG, SessionMSG}
import event_lang.semantic.CommonTypes.{PartID, SessionID}
import event_lang.types.MSG

trait QueueAPI {
  type SesId = SessionID
  type ParId = PartID

  def peek(sesId: SesId, frm: ParId): Option[SessionMSG]

  def exist(sesId: SesId, frm: ParId, filter: MSG => Boolean): Boolean

  def poll(sesId: SesId, frm: ParId): Option[SessionMSG]

  def peekControlMsg(sesId: SesId): Option[ControlMSG]

  def pollControlMsg(sesId: SesId): Option[ControlMSG]

}