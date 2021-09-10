package example.sparkCluster
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object SparkCM {
object RS {
val W : RoleSet = RoleSet("W")
val M : RoleSet = RoleSet("M")
val ZK : RoleSet = RoleSet("ZK")
}

object MESSAGES {
object Main {
case class BMsg() extends MSG {
   override def l : String = "BMsg"
}

case class NewDriver(appID:Int,numEx:Int) extends MSG {
   override def l : String = "NewDriver"
}

case class FailMtoM() extends MSG {
   override def l : String = "FailMtoM"
}

case class L3() extends MSG {
   override def l : String = "L3"
}

case class PrepSpawn() extends MSG {
   override def l : String = "PrepSpawn"
}

case class DriverDone(appID:Int) extends MSG {
   override def l : String = "DriverDone"
}

case class FailMtoW() extends MSG {
   override def l : String = "FailMtoW"
}

case class End() extends MSG {
   override def l : String = "End"
}

}

object GSel {
case class AckNStatus(appID:Int) extends MSG {
   override def l : String = "AckNStatus"
}

case class LaunchDriver(appID:Int,driver:example.sparkCluster.AbstractDriver) extends MSG {
   override def l : String = "LaunchDriver"
}

case class FailGSelMtoW(appId:Int) extends MSG {
   override def l : String = "FailGSelMtoW"
}

case class End() extends MSG {
   override def l : String = "End"
}

case class StartExCase(appID:Int) extends MSG {
   override def l : String = "StartExCase"
}

}

object GStartEx {
case class StartEx(appId:Int,exId:Int,task:example.sparkCluster.Task) extends MSG {
   override def l : String = "StartEx"
}

case class ExFailed(appId:Int,exId:Int) extends MSG {
   override def l : String = "ExFailed"
}

case class ExRunning(appId:Int,exId:Int) extends MSG {
   override def l : String = "ExRunning"
}

case class ExFinishStatus(appId:Int,exId:Int) extends MSG {
   override def l : String = "ExFinishStatus"
}

case class ExStarted(appId:Int,exId:Int) extends MSG {
   override def l : String = "ExStarted"
}

case class ExDone(appId:Int,exId:Int) extends MSG {
   override def l : String = "ExDone"
}

}

}

object PROTOCOLS {
object GStartEx {
val wEx_W = Role("wEx",RoleSet("W"))
val m_M = Role("m",RoleSet("M"))
val w_W = Role("w",RoleSet("W"))
}

object GSel {
val w_W = Role("w",RoleSet("W"))
val W = RoleSet("W")
val m_M = Role("m",RoleSet("M"))
}

object Main {
val m_M = Role("m",RoleSet("M"))
val M = RoleSet("M")
val W = RoleSet("W")
val zk_ZK = Role("zk",RoleSet("ZK"))
}

}

object W {
val subs : Seq[dsl.ChannelTypeSubS] = List(GSel_w_W.EPGSel_w_W,GStartEx_wEx_W.EPGStartEx_wEx_W,Main_W.EPMain_W,GStartEx_w_W.EPGStartEx_w_W,GSel_W.EPGSel_W)
trait __EPType_W extends AbstractChannelType {

}

trait EPType_W[T<: TState] extends AbstractEndPoint[__EPType_W,T] {
override val roleSet: RoleSet = RoleSet("W")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(GSel_w_W.EPGSel_w_W,GStartEx_wEx_W.EPGStartEx_wEx_W,Main_W.EPMain_W,GStartEx_w_W.EPGStartEx_w_W,GSel_W.EPGSel_W)

}

object GStartEx_wEx_W{
trait EPGStartEx_wEx_W extends __EPType_W

object EPGStartEx_wEx_W extends EPGStartEx_wEx_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPGStartEx_wEx_W] = List(Hdl)
  override type implT = __EPGStartEx_wEx_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPGStartEx_wEx_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("w",RoleSet("W"))) 
  override def argsP: Role = Role("wEx",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("wEx",RoleSet("W")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "GStartEx"
}

protected case class __EPGStartEx_wEx_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPGStartEx_wEx_W
}
  
}


protected  trait Hdl extends EPGStartEx_wEx_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPGStartEx_wEx_W] = List(RcvStartEx,End_wEx_W_GStartExFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvStartExImp
override def toString() : String = {"EPGStartEx_wEx_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvStartEx extends EPGStartEx_wEx_W with event_lang.dsl.ChannelTypeRcv
  object RcvStartEx extends RcvStartEx {
  override protected def __children: List[EPGStartEx_wEx_W] = List(SndExStarted)
  override type implT = __RcvStartExImp
  override type implNextT = __SndExStartedImp
override def toString() : String = {"EPGStartEx_wEx_W.RcvStartEx"}
  override type msgT = MESSAGES.GStartEx.StartEx
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "StartEx"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvStartExImp(c,session)}

protected case class __RcvStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvStartEx
}
  def rcvFrmm_M : (MESSAGES.GStartEx.StartEx,__SndExStartedImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.StartEx],__SndExStartedImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GStartEx.StartEx,__SndExStartedImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.StartEx],__SndExStartedImp(c,session))) 
}
def rcvMSG : MESSAGES.GStartEx.StartEx = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.StartEx]}
def ? : MESSAGES.GStartEx.StartEx = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.StartEx]}
def channelCon : __SndExStartedImp = {__SndExStartedImp(c,session)}

}


  trait SndExStarted extends EPGStartEx_wEx_W with event_lang.dsl.ChannelTypeSnd
  object SndExStarted extends SndExStarted {
  override protected def __children: List[EPGStartEx_wEx_W] = List(SndExDone)
  override type implT = __SndExStartedImp
  override type implNextT = __SndExDoneImp
override def toString() : String = {"EPGStartEx_wEx_W.SndExStarted"}
    override def to : RRole = Role("m",RoleSet("M")) 
   override def l : String = "ExStarted" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndExStartedImp(c,session)}

protected case class __SndExStartedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndExStarted
}
  private var notUsed = true
def sndTom_M(m : MESSAGES.GStartEx.ExStarted) : __SndExDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__SndExDoneImp(c,session)}
def !(m : MESSAGES.GStartEx.ExStarted) : __SndExDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__SndExDoneImp(c,session)}
def snd(m : MESSAGES.GStartEx.ExStarted) : __SndExDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__SndExDoneImp(c,session)}

}


  trait SndExDone extends EPGStartEx_wEx_W with event_lang.dsl.ChannelTypeSnd
  object SndExDone extends SndExDone {
  override protected def __children: List[EPGStartEx_wEx_W] = List(End_wEx_W_GStartEx)
  override type implT = __SndExDoneImp
  override type implNextT = __End_wEx_W_GStartExImp
override def toString() : String = {"EPGStartEx_wEx_W.SndExDone"}
    override def to : RRole = Role("m",RoleSet("M")) 
   override def l : String = "ExDone" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndExDoneImp(c,session)}

protected case class __SndExDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndExDone
}
  private var notUsed = true
def sndTom_M(m : MESSAGES.GStartEx.ExDone) : __End_wEx_W_GStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__End_wEx_W_GStartExImp(c,session)}
def !(m : MESSAGES.GStartEx.ExDone) : __End_wEx_W_GStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__End_wEx_W_GStartExImp(c,session)}
def snd(m : MESSAGES.GStartEx.ExDone) : __End_wEx_W_GStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__End_wEx_W_GStartExImp(c,session)}

}


protected  trait End_wEx_W_GStartEx extends EPGStartEx_wEx_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wEx_W_GStartEx extends End_wEx_W_GStartEx {
  override protected def __children: List[EPGStartEx_wEx_W] = List()
  override type implT = __End_wEx_W_GStartExImp
  override type implNextT = Nothing
override def toString() : String = {"EPGStartEx_wEx_W.End_wEx_W_GStartEx"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wEx_W_GStartExImp(c,session)}

protected case class __End_wEx_W_GStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wEx_W_GStartEx
}
  
}



protected  trait End_wEx_W_GStartExFHandling extends EPGStartEx_wEx_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wEx_W_GStartExFHandling extends End_wEx_W_GStartExFHandling {
  override protected def __children: List[EPGStartEx_wEx_W] = List()
  override type implT = __End_wEx_W_GStartExFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPGStartEx_wEx_W.End_wEx_W_GStartExFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wEx_W_GStartExFHandlingImp(c,session)}

protected case class __End_wEx_W_GStartExFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wEx_W_GStartExFHandling
}
  
}


}

object GStartEx_w_W{
trait EPGStartEx_w_W extends __EPType_W

object EPGStartEx_w_W extends EPGStartEx_w_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPGStartEx_w_W] = List(Hdl)
  override type implT = __EPGStartEx_w_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPGStartEx_w_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("w",RoleSet("W"))) 
  override def argsP: Role = Role("wEx",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("w",RoleSet("W")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "GStartEx"
}

protected case class __EPGStartEx_w_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPGStartEx_w_W
}
  
}


protected  trait Hdl extends EPGStartEx_w_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPGStartEx_w_W] = List(RcvExRunning,RcvExFailed)
  override type implT = __HdlImp
  override type implNextT = __RcvExRunningImp
override def toString() : String = {"EPGStartEx_w_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvExRunning extends EPGStartEx_w_W with event_lang.dsl.ChannelTypeRcv
  object RcvExRunning extends RcvExRunning {
  override protected def __children: List[EPGStartEx_w_W] = List(RcvExFinishStatus)
  override type implT = __RcvExRunningImp
  override type implNextT = __RcvExFinishStatusImp
override def toString() : String = {"EPGStartEx_w_W.RcvExRunning"}
  override type msgT = MESSAGES.GStartEx.ExRunning
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "ExRunning"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExRunningImp(c,session)}

protected case class __RcvExRunningImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExRunning
}
  def rcvFrmm_M : (MESSAGES.GStartEx.ExRunning,__RcvExFinishStatusImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExRunning],__RcvExFinishStatusImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GStartEx.ExRunning,__RcvExFinishStatusImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExRunning],__RcvExFinishStatusImp(c,session))) 
}
def rcvMSG : MESSAGES.GStartEx.ExRunning = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExRunning]}
def ? : MESSAGES.GStartEx.ExRunning = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExRunning]}
def channelCon : __RcvExFinishStatusImp = {__RcvExFinishStatusImp(c,session)}

}


  trait RcvExFinishStatus extends EPGStartEx_w_W with event_lang.dsl.ChannelTypeRcv
  object RcvExFinishStatus extends RcvExFinishStatus {
  override protected def __children: List[EPGStartEx_w_W] = List(End_w_W_GStartEx)
  override type implT = __RcvExFinishStatusImp
  override type implNextT = __End_w_W_GStartExImp
override def toString() : String = {"EPGStartEx_w_W.RcvExFinishStatus"}
  override type msgT = MESSAGES.GStartEx.ExFinishStatus
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "ExFinishStatus"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExFinishStatusImp(c,session)}

protected case class __RcvExFinishStatusImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExFinishStatus
}
  def rcvFrmm_M : (MESSAGES.GStartEx.ExFinishStatus,__End_w_W_GStartExImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExFinishStatus],__End_w_W_GStartExImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GStartEx.ExFinishStatus,__End_w_W_GStartExImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExFinishStatus],__End_w_W_GStartExImp(c,session))) 
}
def rcvMSG : MESSAGES.GStartEx.ExFinishStatus = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExFinishStatus]}
def ? : MESSAGES.GStartEx.ExFinishStatus = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExFinishStatus]}
def channelCon : __End_w_W_GStartExImp = {__End_w_W_GStartExImp(c,session)}

}


protected  trait End_w_W_GStartEx extends EPGStartEx_w_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w_W_GStartEx extends End_w_W_GStartEx {
  override protected def __children: List[EPGStartEx_w_W] = List()
  override type implT = __End_w_W_GStartExImp
  override type implNextT = Nothing
override def toString() : String = {"EPGStartEx_w_W.End_w_W_GStartEx"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w_W_GStartExImp(c,session)}

protected case class __End_w_W_GStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w_W_GStartEx
}
  
}



  trait RcvExFailed extends EPGStartEx_w_W with event_lang.dsl.ChannelTypeRcv
  object RcvExFailed extends RcvExFailed {
  override protected def __children: List[EPGStartEx_w_W] = List(End_w_W_GStartExFHandling)
  override type implT = __RcvExFailedImp
  override type implNextT = __End_w_W_GStartExFHandlingImp
override def toString() : String = {"EPGStartEx_w_W.RcvExFailed"}
  override type msgT = MESSAGES.GStartEx.ExFailed
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "ExFailed"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExFailedImp(c,session)}

protected case class __RcvExFailedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExFailed
}
  def rcvFrmm_M : (MESSAGES.GStartEx.ExFailed,__End_w_W_GStartExFHandlingImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExFailed],__End_w_W_GStartExFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GStartEx.ExFailed,__End_w_W_GStartExFHandlingImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExFailed],__End_w_W_GStartExFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.GStartEx.ExFailed = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExFailed]}
def ? : MESSAGES.GStartEx.ExFailed = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GStartEx.ExFailed]}
def channelCon : __End_w_W_GStartExFHandlingImp = {__End_w_W_GStartExFHandlingImp(c,session)}

}


protected  trait End_w_W_GStartExFHandling extends EPGStartEx_w_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w_W_GStartExFHandling extends End_w_W_GStartExFHandling {
  override protected def __children: List[EPGStartEx_w_W] = List()
  override type implT = __End_w_W_GStartExFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPGStartEx_w_W.End_w_W_GStartExFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w_W_GStartExFHandlingImp(c,session)}

protected case class __End_w_W_GStartExFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w_W_GStartExFHandling
}
  
}


}

object Main_W{
trait EPMain_W extends __EPType_W

object EPMain_W extends EPMain_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_W] = List(Hdl)
  override type implT = __EPMain_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("zk",RoleSet("ZK"))) 
  override def argsP: Role = Role("m",RoleSet("M")) 
  override def argsRs: List[RoleSet] = List(RoleSet("M"), RoleSet("W")) 
  override def prjTo : RRole = RoleSet("W") 
  override def rootRole: Role = Role("zk",RoleSet("ZK")) 
  override def name : String = "Main"
}

protected case class __EPMain_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_W
}
  
}


protected  trait Hdl extends EPMain_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_W] = List(RecT,RcvFailMtoW)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPMain_W with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPMain_W] = List(Merge_PrepSpawn_BMsg_L3)
  override type implT = __RecTImp
  override type implNextT = __Merge_PrepSpawn_BMsg_L3Imp
override def toString() : String = {"EPMain_W.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait Merge_PrepSpawn_BMsg_L3 extends EPMain_W with event_lang.dsl.ChannelTypeMerge
protected  object Merge_PrepSpawn_BMsg_L3 extends Merge_PrepSpawn_BMsg_L3 {
  override protected def __children: List[EPMain_W] = List(RcvPrepSpawn,RcvBMsg,RcvL3)
  override type implT = __Merge_PrepSpawn_BMsg_L3Imp
  override type implNextT = __RcvPrepSpawnImp
override def toString() : String = {"EPMain_W.Merge_PrepSpawn_BMsg_L3"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Merge_PrepSpawn_BMsg_L3Imp(c,session)}

protected case class __Merge_PrepSpawn_BMsg_L3Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Merge_PrepSpawn_BMsg_L3
}
  
}


  trait RcvPrepSpawn extends EPMain_W with event_lang.dsl.ChannelTypeRcv
  object RcvPrepSpawn extends RcvPrepSpawn {
  override protected def __children: List[EPMain_W] = List(SpawnGSel)
  override type implT = __RcvPrepSpawnImp
  override type implNextT = __SpawnGSelImp
override def toString() : String = {"EPMain_W.RcvPrepSpawn"}
  override type msgT = MESSAGES.Main.PrepSpawn
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "PrepSpawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPrepSpawnImp(c,session)}

protected case class __RcvPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPrepSpawn
}
  def rcvFrmm_M : (MESSAGES.Main.PrepSpawn,__SpawnGSelImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.PrepSpawn],__SpawnGSelImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.PrepSpawn,__SpawnGSelImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.PrepSpawn],__SpawnGSelImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.PrepSpawn = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.PrepSpawn]}
def ? : MESSAGES.Main.PrepSpawn = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.PrepSpawn]}
def channelCon : __SpawnGSelImp = {__SpawnGSelImp(c,session)}

}


  trait SpawnGSel extends EPMain_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnGSel extends SpawnGSel {
  override protected def __children: List[EPMain_W] = List(T)
  override type implT = __SpawnGSelImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_W.SpawnGSel"}
    override def y: List[Role] = List(Role("m",RoleSet("M"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "GSel" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnGSelImp(c,session)}

protected case class __SpawnGSelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnGSel
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPMain_W with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPMain_W] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_W.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvBMsg extends EPMain_W with event_lang.dsl.ChannelTypeRcv
  object RcvBMsg extends RcvBMsg {
  override protected def __children: List[EPMain_W] = List(T)
  override type implT = __RcvBMsgImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_W.RcvBMsg"}
  override type msgT = MESSAGES.Main.BMsg
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "BMsg"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBMsgImp(c,session)}

protected case class __RcvBMsgImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBMsg
}
  def rcvFrmm_M : (MESSAGES.Main.BMsg,__TImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.BMsg],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.BMsg,__TImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.BMsg],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.BMsg = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.BMsg]}
def ? : MESSAGES.Main.BMsg = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.BMsg]}
def channelCon : __TImp = {__TImp(c,session)}

}

//there was an occurens of t already

  trait RcvL3 extends EPMain_W with event_lang.dsl.ChannelTypeRcv
  object RcvL3 extends RcvL3 {
  override protected def __children: List[EPMain_W] = List(End_W_MainL3)
  override type implT = __RcvL3Imp
  override type implNextT = __End_W_MainL3Imp
override def toString() : String = {"EPMain_W.RcvL3"}
  override type msgT = MESSAGES.Main.L3
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "L3"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvL3Imp(c,session)}

protected case class __RcvL3Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvL3
}
  def rcvFrmm_M : (MESSAGES.Main.L3,__End_W_MainL3Imp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.L3],__End_W_MainL3Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.L3,__End_W_MainL3Imp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.L3],__End_W_MainL3Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.L3 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.L3]}
def ? : MESSAGES.Main.L3 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.L3]}
def channelCon : __End_W_MainL3Imp = {__End_W_MainL3Imp(c,session)}

}


protected  trait End_W_MainL3 extends EPMain_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_MainL3 extends End_W_MainL3 {
  override protected def __children: List[EPMain_W] = List()
  override type implT = __End_W_MainL3Imp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_W.End_W_MainL3"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_MainL3Imp(c,session)}

protected case class __End_W_MainL3Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_MainL3
}
  
}



  trait RcvFailMtoW extends EPMain_W with event_lang.dsl.ChannelTypeRcv
  object RcvFailMtoW extends RcvFailMtoW {
  override protected def __children: List[EPMain_W] = List(End_W_MainFHandling)
  override type implT = __RcvFailMtoWImp
  override type implNextT = __End_W_MainFHandlingImp
override def toString() : String = {"EPMain_W.RcvFailMtoW"}
  override type msgT = MESSAGES.Main.FailMtoW
   override def frm : Role = Role("zk",RoleSet("ZK")) 
   override def l : String = "FailMtoW"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFailMtoWImp(c,session)}

protected case class __RcvFailMtoWImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFailMtoW
}
  def rcvFrmzk_ZK : (MESSAGES.Main.FailMtoW,__End_W_MainFHandlingImp) = {(c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.FailMtoW],__End_W_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FailMtoW,__End_W_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.FailMtoW],__End_W_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FailMtoW = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.FailMtoW]}
def ? : MESSAGES.Main.FailMtoW = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.FailMtoW]}
def channelCon : __End_W_MainFHandlingImp = {__End_W_MainFHandlingImp(c,session)}

}


protected  trait End_W_MainFHandling extends EPMain_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_MainFHandling extends End_W_MainFHandling {
  override protected def __children: List[EPMain_W] = List()
  override type implT = __End_W_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_W.End_W_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_MainFHandlingImp(c,session)}

protected case class __End_W_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_MainFHandling
}
  
}


}

object GSel_W{
trait EPGSel_W extends __EPType_W

object EPGSel_W extends EPGSel_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPGSel_W] = List(Hdl)
  override type implT = __EPGSel_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPGSel_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("w",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = RoleSet("W") 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "GSel"
}

protected case class __EPGSel_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPGSel_W
}
  
}


protected  trait Hdl extends EPGSel_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPGSel_W] = List(RecT,RcvFailGSelMtoW)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPGSel_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPGSel_W with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPGSel_W] = List(SelStartExCaseEnd)
  override type implT = __RecTImp
  override type implNextT = __SelStartExCaseEndImp
override def toString() : String = {"EPGSel_W.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelStartExCaseEnd extends EPGSel_W with event_lang.dsl.ChannelTypeBrn
protected  object SelStartExCaseEnd extends SelStartExCaseEnd {
  override protected def __children: List[EPGSel_W] = List(RcvStartExCase,RcvEnd)
  override type implT = __SelStartExCaseEndImp
  override type implNextT = __RcvStartExCaseImp
override def toString() : String = {"EPGSel_W.SelStartExCaseEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelStartExCaseEndImp(c,session)}

protected case class __SelStartExCaseEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelStartExCaseEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvStartExCase extends EPGSel_W with event_lang.dsl.ChannelTypeRcv
  object RcvStartExCase extends RcvStartExCase {
  override protected def __children: List[EPGSel_W] = List(SpawnGStartEx)
  override type implT = __RcvStartExCaseImp
  override type implNextT = __SpawnGStartExImp
override def toString() : String = {"EPGSel_W.RcvStartExCase"}
  override type msgT = MESSAGES.GSel.StartExCase
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "StartExCase"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvStartExCaseImp(c,session)}

protected case class __RcvStartExCaseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvStartExCase
}
  def rcvFrmm_M : (MESSAGES.GSel.StartExCase,__SpawnGStartExImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.StartExCase],__SpawnGStartExImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GSel.StartExCase,__SpawnGStartExImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.StartExCase],__SpawnGStartExImp(c,session))) 
}
def rcvMSG : MESSAGES.GSel.StartExCase = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.StartExCase]}
def ? : MESSAGES.GSel.StartExCase = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.StartExCase]}
def channelCon : __SpawnGStartExImp = {__SpawnGStartExImp(c,session)}

}


  trait SpawnGStartEx extends EPGSel_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnGStartEx extends SpawnGStartEx {
  override protected def __children: List[EPGSel_W] = List(T)
  override type implT = __SpawnGStartExImp
  override type implNextT = __TImp
override def toString() : String = {"EPGSel_W.SpawnGStartEx"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("w",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "GStartEx" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnGStartExImp(c,session)}

protected case class __SpawnGStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnGStartEx
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPGSel_W with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPGSel_W] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPGSel_W.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvEnd extends EPGSel_W with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPGSel_W] = List(End_W_GSelEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_W_GSelEndImp
override def toString() : String = {"EPGSel_W.RcvEnd"}
  override type msgT = MESSAGES.GSel.End
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrmm_M : (MESSAGES.GSel.End,__End_W_GSelEndImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.End],__End_W_GSelEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GSel.End,__End_W_GSelEndImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.End],__End_W_GSelEndImp(c,session))) 
}
def rcvMSG : MESSAGES.GSel.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.End]}
def ? : MESSAGES.GSel.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.End]}
def channelCon : __End_W_GSelEndImp = {__End_W_GSelEndImp(c,session)}

}


protected  trait End_W_GSelEnd extends EPGSel_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_GSelEnd extends End_W_GSelEnd {
  override protected def __children: List[EPGSel_W] = List()
  override type implT = __End_W_GSelEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPGSel_W.End_W_GSelEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_GSelEndImp(c,session)}

protected case class __End_W_GSelEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_GSelEnd
}
  
}



  trait RcvFailGSelMtoW extends EPGSel_W with event_lang.dsl.ChannelTypeRcv
  object RcvFailGSelMtoW extends RcvFailGSelMtoW {
  override protected def __children: List[EPGSel_W] = List(End_W_GSelFHandling)
  override type implT = __RcvFailGSelMtoWImp
  override type implNextT = __End_W_GSelFHandlingImp
override def toString() : String = {"EPGSel_W.RcvFailGSelMtoW"}
  override type msgT = MESSAGES.GSel.FailGSelMtoW
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "FailGSelMtoW"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFailGSelMtoWImp(c,session)}

protected case class __RcvFailGSelMtoWImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFailGSelMtoW
}
  def rcvFrmm_M : (MESSAGES.GSel.FailGSelMtoW,__End_W_GSelFHandlingImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.FailGSelMtoW],__End_W_GSelFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GSel.FailGSelMtoW,__End_W_GSelFHandlingImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.FailGSelMtoW],__End_W_GSelFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.GSel.FailGSelMtoW = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.FailGSelMtoW]}
def ? : MESSAGES.GSel.FailGSelMtoW = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.FailGSelMtoW]}
def channelCon : __End_W_GSelFHandlingImp = {__End_W_GSelFHandlingImp(c,session)}

}


protected  trait End_W_GSelFHandling extends EPGSel_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_GSelFHandling extends End_W_GSelFHandling {
  override protected def __children: List[EPGSel_W] = List()
  override type implT = __End_W_GSelFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPGSel_W.End_W_GSelFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_GSelFHandlingImp(c,session)}

protected case class __End_W_GSelFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_GSelFHandling
}
  
}


}

object GSel_w_W{
trait EPGSel_w_W extends __EPType_W

object EPGSel_w_W extends EPGSel_w_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPGSel_w_W] = List(Hdl)
  override type implT = __EPGSel_w_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPGSel_w_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("w",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("w",RoleSet("W")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "GSel"
}

protected case class __EPGSel_w_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPGSel_w_W
}
  
}


protected  trait Hdl extends EPGSel_w_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPGSel_w_W] = List(RcvLaunchDriver,End_w_W_GSelFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvLaunchDriverImp
override def toString() : String = {"EPGSel_w_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvLaunchDriver extends EPGSel_w_W with event_lang.dsl.ChannelTypeRcv
  object RcvLaunchDriver extends RcvLaunchDriver {
  override protected def __children: List[EPGSel_w_W] = List(SndAckNStatus)
  override type implT = __RcvLaunchDriverImp
  override type implNextT = __SndAckNStatusImp
override def toString() : String = {"EPGSel_w_W.RcvLaunchDriver"}
  override type msgT = MESSAGES.GSel.LaunchDriver
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "LaunchDriver"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvLaunchDriverImp(c,session)}

protected case class __RcvLaunchDriverImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvLaunchDriver
}
  def rcvFrmm_M : (MESSAGES.GSel.LaunchDriver,__SndAckNStatusImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.LaunchDriver],__SndAckNStatusImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GSel.LaunchDriver,__SndAckNStatusImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.LaunchDriver],__SndAckNStatusImp(c,session))) 
}
def rcvMSG : MESSAGES.GSel.LaunchDriver = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.LaunchDriver]}
def ? : MESSAGES.GSel.LaunchDriver = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.LaunchDriver]}
def channelCon : __SndAckNStatusImp = {__SndAckNStatusImp(c,session)}

}


  trait SndAckNStatus extends EPGSel_w_W with event_lang.dsl.ChannelTypeSnd
  object SndAckNStatus extends SndAckNStatus {
  override protected def __children: List[EPGSel_w_W] = List(RecT)
  override type implT = __SndAckNStatusImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPGSel_w_W.SndAckNStatus"}
    override def to : RRole = Role("m",RoleSet("M")) 
   override def l : String = "AckNStatus" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAckNStatusImp(c,session)}

protected case class __SndAckNStatusImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAckNStatus
}
  private var notUsed = true
def sndTom_M(m : MESSAGES.GSel.AckNStatus) : __RecTImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__RecTImp(c,session)}
def !(m : MESSAGES.GSel.AckNStatus) : __RecTImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__RecTImp(c,session)}
def snd(m : MESSAGES.GSel.AckNStatus) : __RecTImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__RecTImp(c,session)}

}


protected  trait RecT extends EPGSel_w_W with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPGSel_w_W] = List(SelStartExCaseEnd)
  override type implT = __RecTImp
  override type implNextT = __SelStartExCaseEndImp
override def toString() : String = {"EPGSel_w_W.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelStartExCaseEnd extends EPGSel_w_W with event_lang.dsl.ChannelTypeBrn
protected  object SelStartExCaseEnd extends SelStartExCaseEnd {
  override protected def __children: List[EPGSel_w_W] = List(RcvStartExCase,RcvEnd)
  override type implT = __SelStartExCaseEndImp
  override type implNextT = __RcvStartExCaseImp
override def toString() : String = {"EPGSel_w_W.SelStartExCaseEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelStartExCaseEndImp(c,session)}

protected case class __SelStartExCaseEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelStartExCaseEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvStartExCase extends EPGSel_w_W with event_lang.dsl.ChannelTypeRcv
  object RcvStartExCase extends RcvStartExCase {
  override protected def __children: List[EPGSel_w_W] = List(SpawnGStartEx)
  override type implT = __RcvStartExCaseImp
  override type implNextT = __SpawnGStartExImp
override def toString() : String = {"EPGSel_w_W.RcvStartExCase"}
  override type msgT = MESSAGES.GSel.StartExCase
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "StartExCase"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvStartExCaseImp(c,session)}

protected case class __RcvStartExCaseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvStartExCase
}
  def rcvFrmm_M : (MESSAGES.GSel.StartExCase,__SpawnGStartExImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.StartExCase],__SpawnGStartExImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GSel.StartExCase,__SpawnGStartExImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.StartExCase],__SpawnGStartExImp(c,session))) 
}
def rcvMSG : MESSAGES.GSel.StartExCase = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.StartExCase]}
def ? : MESSAGES.GSel.StartExCase = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.StartExCase]}
def channelCon : __SpawnGStartExImp = {__SpawnGStartExImp(c,session)}

}


  trait SpawnGStartEx extends EPGSel_w_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnGStartEx extends SpawnGStartEx {
  override protected def __children: List[EPGSel_w_W] = List(T)
  override type implT = __SpawnGStartExImp
  override type implNextT = __TImp
override def toString() : String = {"EPGSel_w_W.SpawnGStartEx"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("w",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "GStartEx" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnGStartExImp(c,session)}

protected case class __SpawnGStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnGStartEx
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPGSel_w_W with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPGSel_w_W] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPGSel_w_W.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvEnd extends EPGSel_w_W with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPGSel_w_W] = List(End_w_W_GSelEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_w_W_GSelEndImp
override def toString() : String = {"EPGSel_w_W.RcvEnd"}
  override type msgT = MESSAGES.GSel.End
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrmm_M : (MESSAGES.GSel.End,__End_w_W_GSelEndImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.End],__End_w_W_GSelEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GSel.End,__End_w_W_GSelEndImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.End],__End_w_W_GSelEndImp(c,session))) 
}
def rcvMSG : MESSAGES.GSel.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.End]}
def ? : MESSAGES.GSel.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.GSel.End]}
def channelCon : __End_w_W_GSelEndImp = {__End_w_W_GSelEndImp(c,session)}

}


protected  trait End_w_W_GSelEnd extends EPGSel_w_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w_W_GSelEnd extends End_w_W_GSelEnd {
  override protected def __children: List[EPGSel_w_W] = List()
  override type implT = __End_w_W_GSelEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPGSel_w_W.End_w_W_GSelEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w_W_GSelEndImp(c,session)}

protected case class __End_w_W_GSelEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w_W_GSelEnd
}
  
}



protected  trait End_w_W_GSelFHandling extends EPGSel_w_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w_W_GSelFHandling extends End_w_W_GSelFHandling {
  override protected def __children: List[EPGSel_w_W] = List()
  override type implT = __End_w_W_GSelFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPGSel_w_W.End_w_W_GSelFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w_W_GSelFHandlingImp(c,session)}

protected case class __End_w_W_GSelFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w_W_GSelFHandling
}
  
}


}

}

object M {
val subs : Seq[dsl.ChannelTypeSubS] = List(GStartEx_m_M.EPGStartEx_m_M,GSel_m_M.EPGSel_m_M,Main_m_M.EPMain_m_M,Main_M.EPMain_M)
trait __EPType_M extends AbstractChannelType {

}

trait EPType_M[T<: TState] extends AbstractEndPoint[__EPType_M,T] {
override val roleSet: RoleSet = RoleSet("M")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(GStartEx_m_M.EPGStartEx_m_M,GSel_m_M.EPGSel_m_M,Main_m_M.EPMain_m_M,Main_M.EPMain_M)

}

object GStartEx_m_M{
trait EPGStartEx_m_M extends __EPType_M

object EPGStartEx_m_M extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPGStartEx_m_M] = List(Hdl)
  override type implT = __EPGStartEx_m_MImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPGStartEx_m_MImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("w",RoleSet("W"))) 
  override def argsP: Role = Role("wEx",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("m",RoleSet("M")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "GStartEx"
}

protected case class __EPGStartEx_m_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPGStartEx_m_M
}
  
}


protected  trait Hdl extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPGStartEx_m_M] = List(SndStartEx,Failed_wEx_W)
  override type implT = __HdlImp
  override type implNextT = __SndStartExImp
override def toString() : String = {"EPGStartEx_m_M.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndStartEx extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeSnd
  object SndStartEx extends SndStartEx {
  override protected def __children: List[EPGStartEx_m_M] = List(RcvExStarted)
  override type implT = __SndStartExImp
  override type implNextT = __RcvExStartedImp
override def toString() : String = {"EPGStartEx_m_M.SndStartEx"}
    override def to : RRole = Role("wEx",RoleSet("W")) 
   override def l : String = "StartEx" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndStartExImp(c,session)}

protected case class __SndStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndStartEx
}
  private var notUsed = true
def sndTowEx_W(m : MESSAGES.GStartEx.StartEx) : __RcvExStartedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wEx",RoleSet("W")),m)
__RcvExStartedImp(c,session)}
def !(m : MESSAGES.GStartEx.StartEx) : __RcvExStartedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wEx",RoleSet("W")),m)
__RcvExStartedImp(c,session)}
def snd(m : MESSAGES.GStartEx.StartEx) : __RcvExStartedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wEx",RoleSet("W")),m)
__RcvExStartedImp(c,session)}

}


  trait RcvExStarted extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeRcv
  object RcvExStarted extends RcvExStarted {
  override protected def __children: List[EPGStartEx_m_M] = List(SndExRunning)
  override type implT = __RcvExStartedImp
  override type implNextT = __SndExRunningImp
override def toString() : String = {"EPGStartEx_m_M.RcvExStarted"}
  override type msgT = MESSAGES.GStartEx.ExStarted
   override def frm : Role = Role("wEx",RoleSet("W")) 
   override def l : String = "ExStarted"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExStartedImp(c,session)}

protected case class __RcvExStartedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExStarted
}
  def rcvFrmwEx_W : (MESSAGES.GStartEx.ExStarted,__SndExRunningImp) = {(c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.GStartEx.ExStarted],__SndExRunningImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GStartEx.ExStarted,__SndExRunningImp),T]) : T = {
  f((c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.GStartEx.ExStarted],__SndExRunningImp(c,session))) 
}
def rcvMSG : MESSAGES.GStartEx.ExStarted = {c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.GStartEx.ExStarted]}
def ? : MESSAGES.GStartEx.ExStarted = {c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.GStartEx.ExStarted]}
def channelCon : __SndExRunningImp = {__SndExRunningImp(c,session)}

}


  trait SndExRunning extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeSnd
  object SndExRunning extends SndExRunning {
  override protected def __children: List[EPGStartEx_m_M] = List(RcvExDone)
  override type implT = __SndExRunningImp
  override type implNextT = __RcvExDoneImp
override def toString() : String = {"EPGStartEx_m_M.SndExRunning"}
    override def to : RRole = Role("w",RoleSet("W")) 
   override def l : String = "ExRunning" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndExRunningImp(c,session)}

protected case class __SndExRunningImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndExRunning
}
  private var notUsed = true
def sndTow_W(m : MESSAGES.GStartEx.ExRunning) : __RcvExDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__RcvExDoneImp(c,session)}
def !(m : MESSAGES.GStartEx.ExRunning) : __RcvExDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__RcvExDoneImp(c,session)}
def snd(m : MESSAGES.GStartEx.ExRunning) : __RcvExDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__RcvExDoneImp(c,session)}

}


  trait RcvExDone extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeRcv
  object RcvExDone extends RcvExDone {
  override protected def __children: List[EPGStartEx_m_M] = List(SndExFinishStatus)
  override type implT = __RcvExDoneImp
  override type implNextT = __SndExFinishStatusImp
override def toString() : String = {"EPGStartEx_m_M.RcvExDone"}
  override type msgT = MESSAGES.GStartEx.ExDone
   override def frm : Role = Role("wEx",RoleSet("W")) 
   override def l : String = "ExDone"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExDoneImp(c,session)}

protected case class __RcvExDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExDone
}
  def rcvFrmwEx_W : (MESSAGES.GStartEx.ExDone,__SndExFinishStatusImp) = {(c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.GStartEx.ExDone],__SndExFinishStatusImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GStartEx.ExDone,__SndExFinishStatusImp),T]) : T = {
  f((c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.GStartEx.ExDone],__SndExFinishStatusImp(c,session))) 
}
def rcvMSG : MESSAGES.GStartEx.ExDone = {c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.GStartEx.ExDone]}
def ? : MESSAGES.GStartEx.ExDone = {c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.GStartEx.ExDone]}
def channelCon : __SndExFinishStatusImp = {__SndExFinishStatusImp(c,session)}

}


  trait SndExFinishStatus extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeSnd
  object SndExFinishStatus extends SndExFinishStatus {
  override protected def __children: List[EPGStartEx_m_M] = List(End_m_M_GStartEx)
  override type implT = __SndExFinishStatusImp
  override type implNextT = __End_m_M_GStartExImp
override def toString() : String = {"EPGStartEx_m_M.SndExFinishStatus"}
    override def to : RRole = Role("w",RoleSet("W")) 
   override def l : String = "ExFinishStatus" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndExFinishStatusImp(c,session)}

protected case class __SndExFinishStatusImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndExFinishStatus
}
  private var notUsed = true
def sndTow_W(m : MESSAGES.GStartEx.ExFinishStatus) : __End_m_M_GStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__End_m_M_GStartExImp(c,session)}
def !(m : MESSAGES.GStartEx.ExFinishStatus) : __End_m_M_GStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__End_m_M_GStartExImp(c,session)}
def snd(m : MESSAGES.GStartEx.ExFinishStatus) : __End_m_M_GStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__End_m_M_GStartExImp(c,session)}

}


protected  trait End_m_M_GStartEx extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_GStartEx extends End_m_M_GStartEx {
  override protected def __children: List[EPGStartEx_m_M] = List()
  override type implT = __End_m_M_GStartExImp
  override type implNextT = Nothing
override def toString() : String = {"EPGStartEx_m_M.End_m_M_GStartEx"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_GStartExImp(c,session)}

protected case class __End_m_M_GStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_GStartEx
}
  
}



  trait Failed_wEx_W extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeFDtct
  object Failed_wEx_W extends Failed_wEx_W {
  override protected def __children: List[EPGStartEx_m_M] = List(SndExFailed)
  override type implT = __Failed_wEx_WImp
  override type implNextT = __SndExFailedImp
override def toString() : String = {"EPGStartEx_m_M.Failed_wEx_W"}
  override def suspect : Role = Role("wEx",RoleSet("W")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_wEx_WImp(c,session)}

protected case class __Failed_wEx_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_wEx_W
}
  def failed_wEx_W(): __SndExFailedImp = {__SndExFailedImp(c,session)}

}


  trait SndExFailed extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeSnd
  object SndExFailed extends SndExFailed {
  override protected def __children: List[EPGStartEx_m_M] = List(End_m_M_GStartExFHandling)
  override type implT = __SndExFailedImp
  override type implNextT = __End_m_M_GStartExFHandlingImp
override def toString() : String = {"EPGStartEx_m_M.SndExFailed"}
    override def to : RRole = Role("w",RoleSet("W")) 
   override def l : String = "ExFailed" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndExFailedImp(c,session)}

protected case class __SndExFailedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndExFailed
}
  private var notUsed = true
def sndTow_W(m : MESSAGES.GStartEx.ExFailed) : __End_m_M_GStartExFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__End_m_M_GStartExFHandlingImp(c,session)}
def !(m : MESSAGES.GStartEx.ExFailed) : __End_m_M_GStartExFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__End_m_M_GStartExFHandlingImp(c,session)}
def snd(m : MESSAGES.GStartEx.ExFailed) : __End_m_M_GStartExFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__End_m_M_GStartExFHandlingImp(c,session)}

}


protected  trait End_m_M_GStartExFHandling extends EPGStartEx_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_GStartExFHandling extends End_m_M_GStartExFHandling {
  override protected def __children: List[EPGStartEx_m_M] = List()
  override type implT = __End_m_M_GStartExFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPGStartEx_m_M.End_m_M_GStartExFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_GStartExFHandlingImp(c,session)}

protected case class __End_m_M_GStartExFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_GStartExFHandling
}
  
}


}

object GSel_m_M{
trait EPGSel_m_M extends __EPType_M

object EPGSel_m_M extends EPGSel_m_M with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPGSel_m_M] = List(Hdl)
  override type implT = __EPGSel_m_MImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPGSel_m_MImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("w",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("m",RoleSet("M")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "GSel"
}

protected case class __EPGSel_m_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPGSel_m_M
}
  
}


protected  trait Hdl extends EPGSel_m_M with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPGSel_m_M] = List(SndLaunchDriver,Failed_w_W)
  override type implT = __HdlImp
  override type implNextT = __SndLaunchDriverImp
override def toString() : String = {"EPGSel_m_M.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndLaunchDriver extends EPGSel_m_M with event_lang.dsl.ChannelTypeSnd
  object SndLaunchDriver extends SndLaunchDriver {
  override protected def __children: List[EPGSel_m_M] = List(RcvAckNStatus)
  override type implT = __SndLaunchDriverImp
  override type implNextT = __RcvAckNStatusImp
override def toString() : String = {"EPGSel_m_M.SndLaunchDriver"}
    override def to : RRole = Role("w",RoleSet("W")) 
   override def l : String = "LaunchDriver" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndLaunchDriverImp(c,session)}

protected case class __SndLaunchDriverImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndLaunchDriver
}
  private var notUsed = true
def sndTow_W(m : MESSAGES.GSel.LaunchDriver) : __RcvAckNStatusImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__RcvAckNStatusImp(c,session)}
def !(m : MESSAGES.GSel.LaunchDriver) : __RcvAckNStatusImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__RcvAckNStatusImp(c,session)}
def snd(m : MESSAGES.GSel.LaunchDriver) : __RcvAckNStatusImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__RcvAckNStatusImp(c,session)}

}


  trait RcvAckNStatus extends EPGSel_m_M with event_lang.dsl.ChannelTypeRcv
  object RcvAckNStatus extends RcvAckNStatus {
  override protected def __children: List[EPGSel_m_M] = List(RecT)
  override type implT = __RcvAckNStatusImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPGSel_m_M.RcvAckNStatus"}
  override type msgT = MESSAGES.GSel.AckNStatus
   override def frm : Role = Role("w",RoleSet("W")) 
   override def l : String = "AckNStatus"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAckNStatusImp(c,session)}

protected case class __RcvAckNStatusImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAckNStatus
}
  def rcvFrmw_W : (MESSAGES.GSel.AckNStatus,__RecTImp) = {(c.rcv(Role("w",RoleSet("W"))).asInstanceOf[MESSAGES.GSel.AckNStatus],__RecTImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GSel.AckNStatus,__RecTImp),T]) : T = {
  f((c.rcv(Role("w",RoleSet("W"))).asInstanceOf[MESSAGES.GSel.AckNStatus],__RecTImp(c,session))) 
}
def rcvMSG : MESSAGES.GSel.AckNStatus = {c.rcv(Role("w",RoleSet("W"))).asInstanceOf[MESSAGES.GSel.AckNStatus]}
def ? : MESSAGES.GSel.AckNStatus = {c.rcv(Role("w",RoleSet("W"))).asInstanceOf[MESSAGES.GSel.AckNStatus]}
def channelCon : __RecTImp = {__RecTImp(c,session)}

}


protected  trait RecT extends EPGSel_m_M with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPGSel_m_M] = List(SelStartExCaseEnd)
  override type implT = __RecTImp
  override type implNextT = __SelStartExCaseEndImp
override def toString() : String = {"EPGSel_m_M.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelStartExCaseEnd extends EPGSel_m_M with event_lang.dsl.ChannelTypeSel
protected  object SelStartExCaseEnd extends SelStartExCaseEnd {
  override protected def __children: List[EPGSel_m_M] = List(SndStartExCase,SndEnd)
  override type implT = __SelStartExCaseEndImp
  override type implNextT = __SndStartExCaseImp
override def toString() : String = {"EPGSel_m_M.SelStartExCaseEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelStartExCaseEndImp(c,session)}

protected case class __SelStartExCaseEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelStartExCaseEnd
}
  private var notUsed = true
def !(m : MESSAGES.GSel.StartExCase) : __SpawnGStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __SpawnGStartExImp(c,session)}
def sndToW(m : MESSAGES.GSel.StartExCase) : __SpawnGStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __SpawnGStartExImp(c,session)}

def !(m : MESSAGES.GSel.End) : __End_m_M_GSelEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __End_m_M_GSelEndImp(c,session)}
def sndToW(m : MESSAGES.GSel.End) : __End_m_M_GSelEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __End_m_M_GSelEndImp(c,session)}

}


  trait SndStartExCase extends EPGSel_m_M with event_lang.dsl.ChannelTypeSnd
  object SndStartExCase extends SndStartExCase {
  override protected def __children: List[EPGSel_m_M] = List(SpawnGStartEx)
  override type implT = __SndStartExCaseImp
  override type implNextT = __SpawnGStartExImp
override def toString() : String = {"EPGSel_m_M.SndStartExCase"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "StartExCase" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndStartExCaseImp(c,session)}

protected case class __SndStartExCaseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndStartExCase
}
  private var notUsed = true
def sndToW(m : MESSAGES.GSel.StartExCase) : __SpawnGStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnGStartExImp(c,session)}
def !(m : MESSAGES.GSel.StartExCase) : __SpawnGStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnGStartExImp(c,session)}
def snd(m : MESSAGES.GSel.StartExCase) : __SpawnGStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnGStartExImp(c,session)}

}


  trait SpawnGStartEx extends EPGSel_m_M with event_lang.dsl.ChannelTypeSpawn
  object SpawnGStartEx extends SpawnGStartEx {
  override protected def __children: List[EPGSel_m_M] = List(T)
  override type implT = __SpawnGStartExImp
  override type implNextT = __TImp
override def toString() : String = {"EPGSel_m_M.SpawnGStartEx"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("w",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "GStartEx" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnGStartExImp(c,session)}

protected case class __SpawnGStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnGStartEx
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPGSel_m_M with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPGSel_m_M] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPGSel_m_M.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait SndEnd extends EPGSel_m_M with event_lang.dsl.ChannelTypeSnd
  object SndEnd extends SndEnd {
  override protected def __children: List[EPGSel_m_M] = List(End_m_M_GSelEnd)
  override type implT = __SndEndImp
  override type implNextT = __End_m_M_GSelEndImp
override def toString() : String = {"EPGSel_m_M.SndEnd"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "End" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndImp(c,session)}

protected case class __SndEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEnd
}
  private var notUsed = true
def sndToW(m : MESSAGES.GSel.End) : __End_m_M_GSelEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_GSelEndImp(c,session)}
def !(m : MESSAGES.GSel.End) : __End_m_M_GSelEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_GSelEndImp(c,session)}
def snd(m : MESSAGES.GSel.End) : __End_m_M_GSelEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_GSelEndImp(c,session)}

}


protected  trait End_m_M_GSelEnd extends EPGSel_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_GSelEnd extends End_m_M_GSelEnd {
  override protected def __children: List[EPGSel_m_M] = List()
  override type implT = __End_m_M_GSelEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPGSel_m_M.End_m_M_GSelEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_GSelEndImp(c,session)}

protected case class __End_m_M_GSelEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_GSelEnd
}
  
}



  trait Failed_w_W extends EPGSel_m_M with event_lang.dsl.ChannelTypeFDtct
  object Failed_w_W extends Failed_w_W {
  override protected def __children: List[EPGSel_m_M] = List(SndFailGSelMtoW)
  override type implT = __Failed_w_WImp
  override type implNextT = __SndFailGSelMtoWImp
override def toString() : String = {"EPGSel_m_M.Failed_w_W"}
  override def suspect : Role = Role("w",RoleSet("W")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_w_WImp(c,session)}

protected case class __Failed_w_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_w_W
}
  def failed_w_W(): __SndFailGSelMtoWImp = {__SndFailGSelMtoWImp(c,session)}

}


  trait SndFailGSelMtoW extends EPGSel_m_M with event_lang.dsl.ChannelTypeSnd
  object SndFailGSelMtoW extends SndFailGSelMtoW {
  override protected def __children: List[EPGSel_m_M] = List(End_m_M_GSelFHandling)
  override type implT = __SndFailGSelMtoWImp
  override type implNextT = __End_m_M_GSelFHandlingImp
override def toString() : String = {"EPGSel_m_M.SndFailGSelMtoW"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "FailGSelMtoW" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFailGSelMtoWImp(c,session)}

protected case class __SndFailGSelMtoWImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFailGSelMtoW
}
  private var notUsed = true
def sndToW(m : MESSAGES.GSel.FailGSelMtoW) : __End_m_M_GSelFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_GSelFHandlingImp(c,session)}
def !(m : MESSAGES.GSel.FailGSelMtoW) : __End_m_M_GSelFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_GSelFHandlingImp(c,session)}
def snd(m : MESSAGES.GSel.FailGSelMtoW) : __End_m_M_GSelFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_GSelFHandlingImp(c,session)}

}


protected  trait End_m_M_GSelFHandling extends EPGSel_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_GSelFHandling extends End_m_M_GSelFHandling {
  override protected def __children: List[EPGSel_m_M] = List()
  override type implT = __End_m_M_GSelFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPGSel_m_M.End_m_M_GSelFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_GSelFHandlingImp(c,session)}

protected case class __End_m_M_GSelFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_GSelFHandling
}
  
}


}

object Main_m_M{
trait EPMain_m_M extends __EPType_M

object EPMain_m_M extends EPMain_m_M with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_m_M] = List(Hdl)
  override type implT = __EPMain_m_MImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_m_MImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("zk",RoleSet("ZK"))) 
  override def argsP: Role = Role("m",RoleSet("M")) 
  override def argsRs: List[RoleSet] = List(RoleSet("M"), RoleSet("W")) 
  override def prjTo : RRole = Role("m",RoleSet("M")) 
  override def rootRole: Role = Role("zk",RoleSet("ZK")) 
  override def name : String = "Main"
}

protected case class __EPMain_m_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_m_M
}
  
}


protected  trait Hdl extends EPMain_m_M with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_m_M] = List(RecT,End_m_M_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_m_M.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPMain_m_M with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPMain_m_M] = List(SelNewDriverDriverDoneEnd)
  override type implT = __RecTImp
  override type implNextT = __SelNewDriverDriverDoneEndImp
override def toString() : String = {"EPMain_m_M.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelNewDriverDriverDoneEnd extends EPMain_m_M with event_lang.dsl.ChannelTypeSel
protected  object SelNewDriverDriverDoneEnd extends SelNewDriverDriverDoneEnd {
  override protected def __children: List[EPMain_m_M] = List(SndNewDriver,SndDriverDone,SndEnd)
  override type implT = __SelNewDriverDriverDoneEndImp
  override type implNextT = __SndNewDriverImp
override def toString() : String = {"EPMain_m_M.SelNewDriverDriverDoneEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelNewDriverDriverDoneEndImp(c,session)}

protected case class __SelNewDriverDriverDoneEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelNewDriverDriverDoneEnd
}
  private var notUsed = true
def !(m : MESSAGES.Main.NewDriver) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
 __SndPrepSpawnImp(c,session)}
def sndTozk_ZK(m : MESSAGES.Main.NewDriver) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
 __SndPrepSpawnImp(c,session)}

def !(m : MESSAGES.Main.DriverDone) : __SndBMsgImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
 __SndBMsgImp(c,session)}
def sndTozk_ZK(m : MESSAGES.Main.DriverDone) : __SndBMsgImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
 __SndBMsgImp(c,session)}

def !(m : MESSAGES.Main.End) : __SndL3Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
 __SndL3Imp(c,session)}
def sndTozk_ZK(m : MESSAGES.Main.End) : __SndL3Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
 __SndL3Imp(c,session)}

}


  trait SndNewDriver extends EPMain_m_M with event_lang.dsl.ChannelTypeSnd
  object SndNewDriver extends SndNewDriver {
  override protected def __children: List[EPMain_m_M] = List(SndPrepSpawn)
  override type implT = __SndNewDriverImp
  override type implNextT = __SndPrepSpawnImp
override def toString() : String = {"EPMain_m_M.SndNewDriver"}
    override def to : RRole = Role("zk",RoleSet("ZK")) 
   override def l : String = "NewDriver" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndNewDriverImp(c,session)}

protected case class __SndNewDriverImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndNewDriver
}
  private var notUsed = true
def sndTozk_ZK(m : MESSAGES.Main.NewDriver) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
__SndPrepSpawnImp(c,session)}
def !(m : MESSAGES.Main.NewDriver) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
__SndPrepSpawnImp(c,session)}
def snd(m : MESSAGES.Main.NewDriver) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
__SndPrepSpawnImp(c,session)}

}


  trait SndPrepSpawn extends EPMain_m_M with event_lang.dsl.ChannelTypeSnd
  object SndPrepSpawn extends SndPrepSpawn {
  override protected def __children: List[EPMain_m_M] = List(SpawnGSel)
  override type implT = __SndPrepSpawnImp
  override type implNextT = __SpawnGSelImp
override def toString() : String = {"EPMain_m_M.SndPrepSpawn"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "PrepSpawn" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPrepSpawnImp(c,session)}

protected case class __SndPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPrepSpawn
}
  private var notUsed = true
def sndToW(m : MESSAGES.Main.PrepSpawn) : __SpawnGSelImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnGSelImp(c,session)}
def !(m : MESSAGES.Main.PrepSpawn) : __SpawnGSelImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnGSelImp(c,session)}
def snd(m : MESSAGES.Main.PrepSpawn) : __SpawnGSelImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnGSelImp(c,session)}

}


  trait SpawnGSel extends EPMain_m_M with event_lang.dsl.ChannelTypeSpawn
  object SpawnGSel extends SpawnGSel {
  override protected def __children: List[EPMain_m_M] = List(T)
  override type implT = __SpawnGSelImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_m_M.SpawnGSel"}
    override def y: List[Role] = List(Role("m",RoleSet("M"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "GSel" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnGSelImp(c,session)}

protected case class __SpawnGSelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnGSel
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPMain_m_M with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPMain_m_M] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_m_M.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait SndDriverDone extends EPMain_m_M with event_lang.dsl.ChannelTypeSnd
  object SndDriverDone extends SndDriverDone {
  override protected def __children: List[EPMain_m_M] = List(SndBMsg)
  override type implT = __SndDriverDoneImp
  override type implNextT = __SndBMsgImp
override def toString() : String = {"EPMain_m_M.SndDriverDone"}
    override def to : RRole = Role("zk",RoleSet("ZK")) 
   override def l : String = "DriverDone" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDriverDoneImp(c,session)}

protected case class __SndDriverDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDriverDone
}
  private var notUsed = true
def sndTozk_ZK(m : MESSAGES.Main.DriverDone) : __SndBMsgImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
__SndBMsgImp(c,session)}
def !(m : MESSAGES.Main.DriverDone) : __SndBMsgImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
__SndBMsgImp(c,session)}
def snd(m : MESSAGES.Main.DriverDone) : __SndBMsgImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
__SndBMsgImp(c,session)}

}


  trait SndBMsg extends EPMain_m_M with event_lang.dsl.ChannelTypeSnd
  object SndBMsg extends SndBMsg {
  override protected def __children: List[EPMain_m_M] = List(T)
  override type implT = __SndBMsgImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_m_M.SndBMsg"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "BMsg" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBMsgImp(c,session)}

protected case class __SndBMsgImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBMsg
}
  private var notUsed = true
def sndToW(m : MESSAGES.Main.BMsg) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__TImp(c,session)}
def !(m : MESSAGES.Main.BMsg) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__TImp(c,session)}
def snd(m : MESSAGES.Main.BMsg) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__TImp(c,session)}

}

//there was an occurens of t already

  trait SndEnd extends EPMain_m_M with event_lang.dsl.ChannelTypeSnd
  object SndEnd extends SndEnd {
  override protected def __children: List[EPMain_m_M] = List(SndL3)
  override type implT = __SndEndImp
  override type implNextT = __SndL3Imp
override def toString() : String = {"EPMain_m_M.SndEnd"}
    override def to : RRole = Role("zk",RoleSet("ZK")) 
   override def l : String = "End" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndImp(c,session)}

protected case class __SndEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEnd
}
  private var notUsed = true
def sndTozk_ZK(m : MESSAGES.Main.End) : __SndL3Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
__SndL3Imp(c,session)}
def !(m : MESSAGES.Main.End) : __SndL3Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
__SndL3Imp(c,session)}
def snd(m : MESSAGES.Main.End) : __SndL3Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("zk",RoleSet("ZK")),m)
__SndL3Imp(c,session)}

}


  trait SndL3 extends EPMain_m_M with event_lang.dsl.ChannelTypeSnd
  object SndL3 extends SndL3 {
  override protected def __children: List[EPMain_m_M] = List(End_m_M_MainEnd)
  override type implT = __SndL3Imp
  override type implNextT = __End_m_M_MainEndImp
override def toString() : String = {"EPMain_m_M.SndL3"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "L3" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndL3Imp(c,session)}

protected case class __SndL3Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndL3
}
  private var notUsed = true
def sndToW(m : MESSAGES.Main.L3) : __End_m_M_MainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_MainEndImp(c,session)}
def !(m : MESSAGES.Main.L3) : __End_m_M_MainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_MainEndImp(c,session)}
def snd(m : MESSAGES.Main.L3) : __End_m_M_MainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_MainEndImp(c,session)}

}


protected  trait End_m_M_MainEnd extends EPMain_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_MainEnd extends End_m_M_MainEnd {
  override protected def __children: List[EPMain_m_M] = List()
  override type implT = __End_m_M_MainEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_m_M.End_m_M_MainEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_MainEndImp(c,session)}

protected case class __End_m_M_MainEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_MainEnd
}
  
}



protected  trait End_m_M_MainFHandling extends EPMain_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_MainFHandling extends End_m_M_MainFHandling {
  override protected def __children: List[EPMain_m_M] = List()
  override type implT = __End_m_M_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_m_M.End_m_M_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_MainFHandlingImp(c,session)}

protected case class __End_m_M_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_MainFHandling
}
  
}


}

object Main_M{
trait EPMain_M extends __EPType_M

object EPMain_M extends EPMain_M with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_M] = List(Hdl)
  override type implT = __EPMain_MImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_MImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("zk",RoleSet("ZK"))) 
  override def argsP: Role = Role("m",RoleSet("M")) 
  override def argsRs: List[RoleSet] = List(RoleSet("M"), RoleSet("W")) 
  override def prjTo : RRole = RoleSet("M") 
  override def rootRole: Role = Role("zk",RoleSet("ZK")) 
  override def name : String = "Main"
}

protected case class __EPMain_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_M
}
  
}


protected  trait Hdl extends EPMain_M with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_M] = List(End_M_Main,RcvFailMtoM)
  override type implT = __HdlImp
  override type implNextT = __End_M_MainImp
override def toString() : String = {"EPMain_M.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_M_Main extends EPMain_M with event_lang.dsl.ChannelTypeEnd
protected  object End_M_Main extends End_M_Main {
  override protected def __children: List[EPMain_M] = List()
  override type implT = __End_M_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_M.End_M_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_M_MainImp(c,session)}

protected case class __End_M_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_M_Main
}
  
}



  trait RcvFailMtoM extends EPMain_M with event_lang.dsl.ChannelTypeRcv
  object RcvFailMtoM extends RcvFailMtoM {
  override protected def __children: List[EPMain_M] = List(End_M_MainFHandling)
  override type implT = __RcvFailMtoMImp
  override type implNextT = __End_M_MainFHandlingImp
override def toString() : String = {"EPMain_M.RcvFailMtoM"}
  override type msgT = MESSAGES.Main.FailMtoM
   override def frm : Role = Role("zk",RoleSet("ZK")) 
   override def l : String = "FailMtoM"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFailMtoMImp(c,session)}

protected case class __RcvFailMtoMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFailMtoM
}
  def rcvFrmzk_ZK : (MESSAGES.Main.FailMtoM,__End_M_MainFHandlingImp) = {(c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.FailMtoM],__End_M_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FailMtoM,__End_M_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.FailMtoM],__End_M_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FailMtoM = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.FailMtoM]}
def ? : MESSAGES.Main.FailMtoM = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.FailMtoM]}
def channelCon : __End_M_MainFHandlingImp = {__End_M_MainFHandlingImp(c,session)}

}


protected  trait End_M_MainFHandling extends EPMain_M with event_lang.dsl.ChannelTypeEnd
protected  object End_M_MainFHandling extends End_M_MainFHandling {
  override protected def __children: List[EPMain_M] = List()
  override type implT = __End_M_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_M.End_M_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_M_MainFHandlingImp(c,session)}

protected case class __End_M_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_M_MainFHandling
}
  
}


}

}

object ZK {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_zk_ZK.EPMain_zk_ZK)
trait __EPType_ZK extends AbstractChannelType {

}

trait EPType_ZK[T<: TState] extends AbstractEndPoint[__EPType_ZK,T] {
override val roleSet: RoleSet = RoleSet("ZK")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_zk_ZK.EPMain_zk_ZK)

}

object Main_zk_ZK{
trait EPMain_zk_ZK extends __EPType_ZK

object EPMain_zk_ZK extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_zk_ZK] = List(Hdl)
  override type implT = __EPMain_zk_ZKImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_zk_ZKImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("zk",RoleSet("ZK"))) 
  override def argsP: Role = Role("m",RoleSet("M")) 
  override def argsRs: List[RoleSet] = List(RoleSet("M"), RoleSet("W")) 
  override def prjTo : RRole = Role("zk",RoleSet("ZK")) 
  override def rootRole: Role = Role("zk",RoleSet("ZK")) 
  override def name : String = "Main"
}

protected case class __EPMain_zk_ZKImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_zk_ZK
}
  
}


protected  trait Hdl extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_zk_ZK] = List(RecT,Failed_m_M)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_zk_ZK.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPMain_zk_ZK] = List(SelNewDriverDriverDoneEnd)
  override type implT = __RecTImp
  override type implNextT = __SelNewDriverDriverDoneEndImp
override def toString() : String = {"EPMain_zk_ZK.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelNewDriverDriverDoneEnd extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeBrn
protected  object SelNewDriverDriverDoneEnd extends SelNewDriverDriverDoneEnd {
  override protected def __children: List[EPMain_zk_ZK] = List(RcvNewDriver,RcvDriverDone,RcvEnd)
  override type implT = __SelNewDriverDriverDoneEndImp
  override type implNextT = __RcvNewDriverImp
override def toString() : String = {"EPMain_zk_ZK.SelNewDriverDriverDoneEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelNewDriverDriverDoneEndImp(c,session)}

protected case class __SelNewDriverDriverDoneEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelNewDriverDriverDoneEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvNewDriver extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeRcv
  object RcvNewDriver extends RcvNewDriver {
  override protected def __children: List[EPMain_zk_ZK] = List(T)
  override type implT = __RcvNewDriverImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_zk_ZK.RcvNewDriver"}
  override type msgT = MESSAGES.Main.NewDriver
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "NewDriver"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvNewDriverImp(c,session)}

protected case class __RcvNewDriverImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvNewDriver
}
  def rcvFrmm_M : (MESSAGES.Main.NewDriver,__TImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.NewDriver],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.NewDriver,__TImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.NewDriver],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.NewDriver = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.NewDriver]}
def ? : MESSAGES.Main.NewDriver = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.NewDriver]}
def channelCon : __TImp = {__TImp(c,session)}

}


protected  trait T extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPMain_zk_ZK] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_zk_ZK.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvDriverDone extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeRcv
  object RcvDriverDone extends RcvDriverDone {
  override protected def __children: List[EPMain_zk_ZK] = List(T)
  override type implT = __RcvDriverDoneImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_zk_ZK.RcvDriverDone"}
  override type msgT = MESSAGES.Main.DriverDone
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "DriverDone"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDriverDoneImp(c,session)}

protected case class __RcvDriverDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDriverDone
}
  def rcvFrmm_M : (MESSAGES.Main.DriverDone,__TImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.DriverDone],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.DriverDone,__TImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.DriverDone],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.DriverDone = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.DriverDone]}
def ? : MESSAGES.Main.DriverDone = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.DriverDone]}
def channelCon : __TImp = {__TImp(c,session)}

}

//there was an occurens of t already

  trait RcvEnd extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPMain_zk_ZK] = List(End_zk_ZK_MainEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_zk_ZK_MainEndImp
override def toString() : String = {"EPMain_zk_ZK.RcvEnd"}
  override type msgT = MESSAGES.Main.End
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrmm_M : (MESSAGES.Main.End,__End_zk_ZK_MainEndImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.End],__End_zk_ZK_MainEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.End,__End_zk_ZK_MainEndImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.End],__End_zk_ZK_MainEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.End]}
def ? : MESSAGES.Main.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Main.End]}
def channelCon : __End_zk_ZK_MainEndImp = {__End_zk_ZK_MainEndImp(c,session)}

}


protected  trait End_zk_ZK_MainEnd extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeEnd
protected  object End_zk_ZK_MainEnd extends End_zk_ZK_MainEnd {
  override protected def __children: List[EPMain_zk_ZK] = List()
  override type implT = __End_zk_ZK_MainEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_zk_ZK.End_zk_ZK_MainEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_zk_ZK_MainEndImp(c,session)}

protected case class __End_zk_ZK_MainEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_zk_ZK_MainEnd
}
  
}



  trait Failed_m_M extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeFDtct
  object Failed_m_M extends Failed_m_M {
  override protected def __children: List[EPMain_zk_ZK] = List(SndFailMtoM)
  override type implT = __Failed_m_MImp
  override type implNextT = __SndFailMtoMImp
override def toString() : String = {"EPMain_zk_ZK.Failed_m_M"}
  override def suspect : Role = Role("m",RoleSet("M")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_m_MImp(c,session)}

protected case class __Failed_m_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_m_M
}
  def failed_m_M(): __SndFailMtoMImp = {__SndFailMtoMImp(c,session)}

}


  trait SndFailMtoM extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSnd
  object SndFailMtoM extends SndFailMtoM {
  override protected def __children: List[EPMain_zk_ZK] = List(SndFailMtoW)
  override type implT = __SndFailMtoMImp
  override type implNextT = __SndFailMtoWImp
override def toString() : String = {"EPMain_zk_ZK.SndFailMtoM"}
    override def to : RRole = RoleSet("M") 
   override def l : String = "FailMtoM" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFailMtoMImp(c,session)}

protected case class __SndFailMtoMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFailMtoM
}
  private var notUsed = true
def sndToM(m : MESSAGES.Main.FailMtoM) : __SndFailMtoWImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("M"),m)
__SndFailMtoWImp(c,session)}
def !(m : MESSAGES.Main.FailMtoM) : __SndFailMtoWImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("M"),m)
__SndFailMtoWImp(c,session)}
def snd(m : MESSAGES.Main.FailMtoM) : __SndFailMtoWImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("M"),m)
__SndFailMtoWImp(c,session)}

}


  trait SndFailMtoW extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSnd
  object SndFailMtoW extends SndFailMtoW {
  override protected def __children: List[EPMain_zk_ZK] = List(End_zk_ZK_MainFHandling)
  override type implT = __SndFailMtoWImp
  override type implNextT = __End_zk_ZK_MainFHandlingImp
override def toString() : String = {"EPMain_zk_ZK.SndFailMtoW"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "FailMtoW" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFailMtoWImp(c,session)}

protected case class __SndFailMtoWImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFailMtoW
}
  private var notUsed = true
def sndToW(m : MESSAGES.Main.FailMtoW) : __End_zk_ZK_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_zk_ZK_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.FailMtoW) : __End_zk_ZK_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_zk_ZK_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.FailMtoW) : __End_zk_ZK_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_zk_ZK_MainFHandlingImp(c,session)}

}


protected  trait End_zk_ZK_MainFHandling extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeEnd
protected  object End_zk_ZK_MainFHandling extends End_zk_ZK_MainFHandling {
  override protected def __children: List[EPMain_zk_ZK] = List()
  override type implT = __End_zk_ZK_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_zk_ZK.End_zk_ZK_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_zk_ZK_MainFHandlingImp(c,session)}

protected case class __End_zk_ZK_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_zk_ZK_MainFHandling
}
  
}


}

}

}
