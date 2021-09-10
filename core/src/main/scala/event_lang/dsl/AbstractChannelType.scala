package event_lang.dsl

import event_lang._
import event_lang.network.SpawnMain
import types._



trait AbstractChannelType {
  type implT <: AbstractChannelImp
  type implNextT <: AbstractChannelImp
  type msgT <: MSG

  protected [event_lang] def children: List[AbstractChannelType] = __children
  protected def __children: List[AbstractChannelType]

  protected def __create(c: AbstractChannel, s : Session): implT
  protected [event_lang] def create(c: AbstractChannel, s : Session): implT = __create(c,s)
}

/**
  * [?a:l1!b:l2] (x,y_a, y:b) = x[y_a]?.x[y_b]!
  *
  *
  * .... (x : ?a:l1.!b:l2) = x.rcv( (m :l1)  => ());x.snd(m : l2)
  */




trait AbstractChannel {
  def snd(to: RRole, m: MSG)

  def rcv(frm: Role): MSG

}

case class FrozenChannel(msg: Option[MSG]) extends AbstractChannel {
  override def snd(to: RRole, m: MSG): Unit = {}

  override def rcv(frm: Role): MSG = {
    msg.orNull
  }
}


trait Session {
  def sesId : Long
  def myId : Int
  def subSessions : Set[Long]
  def newSubId : Option[Long]
  def setupInfo : SpawnMain
  def failedIDs : Set[Int]


  //not sure we should expose those - it helps a lot in testing

  def hasNoSubsession() : Boolean = !subSessions.nonEmpty
  def roleToPId : Map[Role,Int]

}
trait AbstractChannelImp {
  def from: AbstractChannelType
  def session : Session
}


trait ChannelTypeExecWithOutMSG


trait ChannelTypeSnd extends ChannelTypeExecWithOutMSG{
  def to : RRole
  def l : String
}

trait ChannelTypeSel extends ChannelTypeExecWithOutMSG
trait ChannelTypeMSnd extends ChannelTypeExecWithOutMSG
trait ChannelTypeMerge
trait ChannelTypeRcv {
  def frm : Role
  def l : String
}
trait ChannelTypeBrn
trait ChannelTypeRec extends ChannelTypeExecWithOutMSG
trait ChannelTypeT extends ChannelTypeExecWithOutMSG
trait ChannelTypeHdl
trait ChannelTypeSpawn {
  def y: List[Role]
  def pickR: RoleSet
  def rs: List[RoleSet]
  def name : String
  def subC(r : RRole) : ChannelTypeSubS
}
trait ChannelTypeDUMMY
trait ChannelTypeSubS {
  def name : String
  def prjTo : RRole
  def body: AbstractChannelType
  def argsC: List[Role]
  def argsP: Role
  def argsRs: List[RoleSet]

  def rootRole : Role
}
trait ChannelTypeFDtct extends ChannelTypeExecWithOutMSG {
  def suspect : Role
}
trait ChannelTypeEnd
