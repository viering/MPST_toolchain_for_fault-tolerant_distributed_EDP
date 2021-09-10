package event_lang.dsl

import event_lang._
import event_lang.semantic.CommonTypes.PartID
import types.MSG

trait TState


//TODO revise the way generics are used in the dsl.

trait HDL[ST <: TState] {

  type MSGT <: MSG


  val cStart: AbstractChannelType
  val inCStart: AbstractChannelType
  val endC: AbstractChannelType

  def definedAt(local_type: AbstractChannelType): Boolean

  def canExecute(cStart: AbstractChannelType, msg: Option[MSG], state: ST,
                 frozenChannelImp: AbstractChannelImp): Boolean

  def execute(m: Option[MSG], state: ST, channel: AbstractChannelImp): (ST, AbstractChannelImp)
}



case class Handler[
  S <: AbstractChannelType,
  SIN <: AbstractChannelType,
  E <: AbstractChannelType,
  SS <: AbstractChannelImp,
  EE <: AbstractChannelImp,
  MT <: MSG,
  ST <: TState](
                 cStart: S, inCStart: SIN, endC: E, __hdl: PartialFunction[(MT, ST, SS), (ST, EE)
]) extends HDL[ST] {

  override type MSGT = MT

  override def definedAt(local_type: AbstractChannelType): Boolean = {
    cStart == local_type
  }

  override def canExecute(cStart: AbstractChannelType, msg: Option[MSG],
                          state: ST, frozenChannelImp: AbstractChannelImp): Boolean = {

    if (cStart != this.cStart)
      return false

    cStart match {
      case cs: ChannelTypeRcv if msg.nonEmpty && cs.l == msg.get.l =>
        __hdl.isDefinedAt((msg.orNull.asInstanceOf[MT], state, frozenChannelImp.asInstanceOf[SS]))
      case cs: ChannelTypeExecWithOutMSG if msg.isEmpty =>
        __hdl.isDefinedAt((msg.orNull.asInstanceOf[MT], state, frozenChannelImp.asInstanceOf[SS]))
      case _: ChannelTypeSpawn if msg.isEmpty =>
        __hdl.isDefinedAt((msg.orNull.asInstanceOf[MT], state, frozenChannelImp.asInstanceOf[SS]))
      case _ => false
    }
  }


  override def execute(m: Option[MSG], state: ST,
                       channel: AbstractChannelImp): (ST, AbstractChannelImp) = {
    __hdl((m.orNull.asInstanceOf[MT], state, channel.asInstanceOf[SS]))
  }
}


case class PickHandler[
  ST <: TState](
                 cStart: AbstractChannelType, __hdl: PartialFunction[(ST, Session, Seq[PartID]), (ST, Option[PartID])
]) {


  def definedAt(local_type: AbstractChannelType): Boolean = {
    cStart == local_type
  }

  def canExecute(cStart: AbstractChannelType, state: ST, partIDs: Seq[PartID],session: Session): Boolean = {

    if (cStart != this.cStart)
      return false
    __hdl.isDefinedAt(state,session,partIDs)
  }


  def execute(cStart: AbstractChannelType, state: ST, pIDs: Seq[PartID], session: Session): (ST,Option[PartID])  = {
    __hdl(state,session,pIDs)
  }
}

//TODO make FinishSpawnHandler and PickHandler uniform and merge togetherd?
case class FinishSpawnHandler[
  ST <: TState](
                 cStart: ChannelTypeSubS, __hdl: PartialFunction[(ST, Session,Session), ST]
) {

  assert(cStart.isInstanceOf[AbstractChannelType])

  def definedAt(local_type: AbstractChannelType): Boolean = {
    cStart.asInstanceOf[AbstractChannelType] == local_type
  }

  def canExecute(cStart: String, state: ST, parentSession: Session, childSession : Session): Boolean = {

    if (cStart != this.cStart.name)
      return false
    __hdl.isDefinedAt(state,parentSession,childSession)
  }


  def execute(cStart: AbstractChannelType, state: ST, parentSession: Session, childSession : Session): ST  = {
    __hdl(state,parentSession,childSession)
  }
}

