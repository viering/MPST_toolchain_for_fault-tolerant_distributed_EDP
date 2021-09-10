package event_lang.network.netty.internal

import event_lang.network.netty.EPAddr
import event_lang.semantic.CommonTypes.PartID
import event_lang.types._

object SetupMessages{
  trait NetworkSetupMSG
  case class NettyHello(msg: String) extends NetworkSetupMSG
  case class Connect(adr: EPAddr, r: RoleSet, myID : Option[String]) extends NetworkSetupMSG
  case class BCastUpdate(eps: Map[EPAddr, (Int, RoleSet)]) extends NetworkSetupMSG
  case class SignalSpawnMain(mainID: Int, idToRole : Map[Int,Role], idToRRoleSet: Map[Int, RoleSet], idToCID : Map[Int, String]) extends NetworkSetupMSG
  case class AccRootSessionSpawn(frm : PartID) extends NetworkSetupMSG
  case class SignalBootstrapCompletion() extends NetworkSetupMSG
}
