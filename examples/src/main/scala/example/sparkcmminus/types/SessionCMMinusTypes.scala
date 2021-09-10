package example.sparkcmminus
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object SessionCMMinusTypes {
object RS {
val W : RoleSet = RoleSet("W")
val M : RoleSet = RoleSet("M")
}

object MESSAGES {
object StartDriver {
case class Ack(appID:String) extends MSG {
   override def l : String = "Ack"
}

case class DriverDesc(driver:example.sparkcmminus.LaunchDriver) extends MSG {
   override def l : String = "DriverDesc"
}

case class PrepSpawn() extends MSG {
   override def l : String = "PrepSpawn"
}

case class DriverFailed(appId:String) extends MSG {
   override def l : String = "DriverFailed"
}

case class End() extends MSG {
   override def l : String = "End"
}

}

object StartEx {
case class ExDesc(launchEx:example.sparkcmminus.LaunchExecutor) extends MSG {
   override def l : String = "ExDesc"
}

case class ExDone(appId:String,exId:Int) extends MSG {
   override def l : String = "ExDone"
}

case class ExFinished(appId:String,exId:Int) extends MSG {
   override def l : String = "ExFinished"
}

case class ExFailed(appId:String,exId:Int) extends MSG {
   override def l : String = "ExFailed"
}

}

}

object PROTOCOLS {
object StartEx {
val wEx_W = Role("wEx",RoleSet("W"))
val W = RoleSet("W")
val m_M = Role("m",RoleSet("M"))
val wD_W = Role("wD",RoleSet("W"))
}

object StartDriver {
val wD_W = Role("wD",RoleSet("W"))
val W = RoleSet("W")
val m_M = Role("m",RoleSet("M"))
}

}

object W {
val subs : Seq[dsl.ChannelTypeSubS] = List(StartDriver_wD_W.EPStartDriver_wD_W,StartDriver_W.EPStartDriver_W,StartEx_W.EPStartEx_W,StartEx_wD_W.EPStartEx_wD_W,StartEx_wEx_W.EPStartEx_wEx_W)
trait __EPType_W extends AbstractChannelType {

}

trait EPType_W[T<: TState] extends AbstractEndPoint[__EPType_W,T] {
override val roleSet: RoleSet = RoleSet("W")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(StartDriver_wD_W.EPStartDriver_wD_W,StartDriver_W.EPStartDriver_W,StartEx_W.EPStartEx_W,StartEx_wD_W.EPStartEx_wD_W,StartEx_wEx_W.EPStartEx_wEx_W)

}

object StartDriver_W{
trait EPStartDriver_W extends __EPType_W

object EPStartDriver_W extends EPStartDriver_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStartDriver_W] = List(Hdl)
  override type implT = __EPStartDriver_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStartDriver_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("wD",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = RoleSet("W") 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "StartDriver"
}

protected case class __EPStartDriver_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStartDriver_W
}
  
}


protected  trait Hdl extends EPStartDriver_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStartDriver_W] = List(RecT,RcvDriverFailed)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStartDriver_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPStartDriver_W with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPStartDriver_W] = List(SelPrepSpawnEnd)
  override type implT = __RecTImp
  override type implNextT = __SelPrepSpawnEndImp
override def toString() : String = {"EPStartDriver_W.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelPrepSpawnEnd extends EPStartDriver_W with event_lang.dsl.ChannelTypeBrn
protected  object SelPrepSpawnEnd extends SelPrepSpawnEnd {
  override protected def __children: List[EPStartDriver_W] = List(RcvPrepSpawn,RcvEnd)
  override type implT = __SelPrepSpawnEndImp
  override type implNextT = __RcvPrepSpawnImp
override def toString() : String = {"EPStartDriver_W.SelPrepSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelPrepSpawnEndImp(c,session)}

protected case class __SelPrepSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelPrepSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvPrepSpawn extends EPStartDriver_W with event_lang.dsl.ChannelTypeRcv
  object RcvPrepSpawn extends RcvPrepSpawn {
  override protected def __children: List[EPStartDriver_W] = List(SpawnStartEx)
  override type implT = __RcvPrepSpawnImp
  override type implNextT = __SpawnStartExImp
override def toString() : String = {"EPStartDriver_W.RcvPrepSpawn"}
  override type msgT = MESSAGES.StartDriver.PrepSpawn
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "PrepSpawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPrepSpawnImp(c,session)}

protected case class __RcvPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPrepSpawn
}
  def rcvFrmm_M : (MESSAGES.StartDriver.PrepSpawn,__SpawnStartExImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.PrepSpawn],__SpawnStartExImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartDriver.PrepSpawn,__SpawnStartExImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.PrepSpawn],__SpawnStartExImp(c,session))) 
}
def rcvMSG : MESSAGES.StartDriver.PrepSpawn = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.PrepSpawn]}
def ? : MESSAGES.StartDriver.PrepSpawn = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.PrepSpawn]}
def channelCon : __SpawnStartExImp = {__SpawnStartExImp(c,session)}

}


  trait SpawnStartEx extends EPStartDriver_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnStartEx extends SpawnStartEx {
  override protected def __children: List[EPStartDriver_W] = List(T)
  override type implT = __SpawnStartExImp
  override type implNextT = __TImp
override def toString() : String = {"EPStartDriver_W.SpawnStartEx"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "StartEx" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStartExImp(c,session)}

protected case class __SpawnStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStartEx
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPStartDriver_W with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPStartDriver_W] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStartDriver_W.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvEnd extends EPStartDriver_W with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPStartDriver_W] = List(End_W_StartDriverEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_W_StartDriverEndImp
override def toString() : String = {"EPStartDriver_W.RcvEnd"}
  override type msgT = MESSAGES.StartDriver.End
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrmm_M : (MESSAGES.StartDriver.End,__End_W_StartDriverEndImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.End],__End_W_StartDriverEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartDriver.End,__End_W_StartDriverEndImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.End],__End_W_StartDriverEndImp(c,session))) 
}
def rcvMSG : MESSAGES.StartDriver.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.End]}
def ? : MESSAGES.StartDriver.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.End]}
def channelCon : __End_W_StartDriverEndImp = {__End_W_StartDriverEndImp(c,session)}

}


protected  trait End_W_StartDriverEnd extends EPStartDriver_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_StartDriverEnd extends End_W_StartDriverEnd {
  override protected def __children: List[EPStartDriver_W] = List()
  override type implT = __End_W_StartDriverEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartDriver_W.End_W_StartDriverEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_StartDriverEndImp(c,session)}

protected case class __End_W_StartDriverEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_StartDriverEnd
}
  
}



  trait RcvDriverFailed extends EPStartDriver_W with event_lang.dsl.ChannelTypeRcv
  object RcvDriverFailed extends RcvDriverFailed {
  override protected def __children: List[EPStartDriver_W] = List(SpawnStartDriver)
  override type implT = __RcvDriverFailedImp
  override type implNextT = __SpawnStartDriverImp
override def toString() : String = {"EPStartDriver_W.RcvDriverFailed"}
  override type msgT = MESSAGES.StartDriver.DriverFailed
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "DriverFailed"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDriverFailedImp(c,session)}

protected case class __RcvDriverFailedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDriverFailed
}
  def rcvFrmm_M : (MESSAGES.StartDriver.DriverFailed,__SpawnStartDriverImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.DriverFailed],__SpawnStartDriverImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartDriver.DriverFailed,__SpawnStartDriverImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.DriverFailed],__SpawnStartDriverImp(c,session))) 
}
def rcvMSG : MESSAGES.StartDriver.DriverFailed = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.DriverFailed]}
def ? : MESSAGES.StartDriver.DriverFailed = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.DriverFailed]}
def channelCon : __SpawnStartDriverImp = {__SpawnStartDriverImp(c,session)}

}


  trait SpawnStartDriver extends EPStartDriver_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnStartDriver extends SpawnStartDriver {
  override protected def __children: List[EPStartDriver_W] = List(End_W_StartDriverFHandling)
  override type implT = __SpawnStartDriverImp
  override type implNextT = __End_W_StartDriverFHandlingImp
override def toString() : String = {"EPStartDriver_W.SpawnStartDriver"}
    override def y: List[Role] = List(Role("m",RoleSet("M"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "StartDriver" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStartDriverImp(c,session)}

protected case class __SpawnStartDriverImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStartDriver
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_W_StartDriverFHandling extends EPStartDriver_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_StartDriverFHandling extends End_W_StartDriverFHandling {
  override protected def __children: List[EPStartDriver_W] = List()
  override type implT = __End_W_StartDriverFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartDriver_W.End_W_StartDriverFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_StartDriverFHandlingImp(c,session)}

protected case class __End_W_StartDriverFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_StartDriverFHandling
}
  
}


}

object StartEx_wEx_W{
trait EPStartEx_wEx_W extends __EPType_W

object EPStartEx_wEx_W extends EPStartEx_wEx_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStartEx_wEx_W] = List(Hdl)
  override type implT = __EPStartEx_wEx_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStartEx_wEx_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def argsP: Role = Role("wEx",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("wEx",RoleSet("W")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "StartEx"
}

protected case class __EPStartEx_wEx_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStartEx_wEx_W
}
  
}


protected  trait Hdl extends EPStartEx_wEx_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStartEx_wEx_W] = List(RcvExDesc,End_wEx_W_StartExFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvExDescImp
override def toString() : String = {"EPStartEx_wEx_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvExDesc extends EPStartEx_wEx_W with event_lang.dsl.ChannelTypeRcv
  object RcvExDesc extends RcvExDesc {
  override protected def __children: List[EPStartEx_wEx_W] = List(SndExDone)
  override type implT = __RcvExDescImp
  override type implNextT = __SndExDoneImp
override def toString() : String = {"EPStartEx_wEx_W.RcvExDesc"}
  override type msgT = MESSAGES.StartEx.ExDesc
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "ExDesc"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExDescImp(c,session)}

protected case class __RcvExDescImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExDesc
}
  def rcvFrmm_M : (MESSAGES.StartEx.ExDesc,__SndExDoneImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExDesc],__SndExDoneImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartEx.ExDesc,__SndExDoneImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExDesc],__SndExDoneImp(c,session))) 
}
def rcvMSG : MESSAGES.StartEx.ExDesc = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExDesc]}
def ? : MESSAGES.StartEx.ExDesc = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExDesc]}
def channelCon : __SndExDoneImp = {__SndExDoneImp(c,session)}

}


  trait SndExDone extends EPStartEx_wEx_W with event_lang.dsl.ChannelTypeSnd
  object SndExDone extends SndExDone {
  override protected def __children: List[EPStartEx_wEx_W] = List(End_wEx_W_StartEx)
  override type implT = __SndExDoneImp
  override type implNextT = __End_wEx_W_StartExImp
override def toString() : String = {"EPStartEx_wEx_W.SndExDone"}
    override def to : RRole = Role("m",RoleSet("M")) 
   override def l : String = "ExDone" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndExDoneImp(c,session)}

protected case class __SndExDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndExDone
}
  private var notUsed = true
def sndTom_M(m : MESSAGES.StartEx.ExDone) : __End_wEx_W_StartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__End_wEx_W_StartExImp(c,session)}
def !(m : MESSAGES.StartEx.ExDone) : __End_wEx_W_StartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__End_wEx_W_StartExImp(c,session)}
def snd(m : MESSAGES.StartEx.ExDone) : __End_wEx_W_StartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__End_wEx_W_StartExImp(c,session)}

}


protected  trait End_wEx_W_StartEx extends EPStartEx_wEx_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wEx_W_StartEx extends End_wEx_W_StartEx {
  override protected def __children: List[EPStartEx_wEx_W] = List()
  override type implT = __End_wEx_W_StartExImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartEx_wEx_W.End_wEx_W_StartEx"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wEx_W_StartExImp(c,session)}

protected case class __End_wEx_W_StartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wEx_W_StartEx
}
  
}



protected  trait End_wEx_W_StartExFHandling extends EPStartEx_wEx_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wEx_W_StartExFHandling extends End_wEx_W_StartExFHandling {
  override protected def __children: List[EPStartEx_wEx_W] = List()
  override type implT = __End_wEx_W_StartExFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartEx_wEx_W.End_wEx_W_StartExFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wEx_W_StartExFHandlingImp(c,session)}

protected case class __End_wEx_W_StartExFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wEx_W_StartExFHandling
}
  
}


}

object StartDriver_wD_W{
trait EPStartDriver_wD_W extends __EPType_W

object EPStartDriver_wD_W extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStartDriver_wD_W] = List(Hdl)
  override type implT = __EPStartDriver_wD_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStartDriver_wD_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("wD",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("wD",RoleSet("W")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "StartDriver"
}

protected case class __EPStartDriver_wD_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStartDriver_wD_W
}
  
}


protected  trait Hdl extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStartDriver_wD_W] = List(RcvDriverDesc,End_wD_W_StartDriverFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvDriverDescImp
override def toString() : String = {"EPStartDriver_wD_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvDriverDesc extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeRcv
  object RcvDriverDesc extends RcvDriverDesc {
  override protected def __children: List[EPStartDriver_wD_W] = List(SndAck)
  override type implT = __RcvDriverDescImp
  override type implNextT = __SndAckImp
override def toString() : String = {"EPStartDriver_wD_W.RcvDriverDesc"}
  override type msgT = MESSAGES.StartDriver.DriverDesc
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "DriverDesc"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDriverDescImp(c,session)}

protected case class __RcvDriverDescImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDriverDesc
}
  def rcvFrmm_M : (MESSAGES.StartDriver.DriverDesc,__SndAckImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.DriverDesc],__SndAckImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartDriver.DriverDesc,__SndAckImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.DriverDesc],__SndAckImp(c,session))) 
}
def rcvMSG : MESSAGES.StartDriver.DriverDesc = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.DriverDesc]}
def ? : MESSAGES.StartDriver.DriverDesc = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.DriverDesc]}
def channelCon : __SndAckImp = {__SndAckImp(c,session)}

}


  trait SndAck extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeSnd
  object SndAck extends SndAck {
  override protected def __children: List[EPStartDriver_wD_W] = List(RecT)
  override type implT = __SndAckImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStartDriver_wD_W.SndAck"}
    override def to : RRole = Role("m",RoleSet("M")) 
   override def l : String = "Ack" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAckImp(c,session)}

protected case class __SndAckImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAck
}
  private var notUsed = true
def sndTom_M(m : MESSAGES.StartDriver.Ack) : __RecTImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__RecTImp(c,session)}
def !(m : MESSAGES.StartDriver.Ack) : __RecTImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__RecTImp(c,session)}
def snd(m : MESSAGES.StartDriver.Ack) : __RecTImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__RecTImp(c,session)}

}


protected  trait RecT extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPStartDriver_wD_W] = List(SelPrepSpawnEnd)
  override type implT = __RecTImp
  override type implNextT = __SelPrepSpawnEndImp
override def toString() : String = {"EPStartDriver_wD_W.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelPrepSpawnEnd extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeBrn
protected  object SelPrepSpawnEnd extends SelPrepSpawnEnd {
  override protected def __children: List[EPStartDriver_wD_W] = List(RcvPrepSpawn,RcvEnd)
  override type implT = __SelPrepSpawnEndImp
  override type implNextT = __RcvPrepSpawnImp
override def toString() : String = {"EPStartDriver_wD_W.SelPrepSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelPrepSpawnEndImp(c,session)}

protected case class __SelPrepSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelPrepSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvPrepSpawn extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeRcv
  object RcvPrepSpawn extends RcvPrepSpawn {
  override protected def __children: List[EPStartDriver_wD_W] = List(SpawnStartEx)
  override type implT = __RcvPrepSpawnImp
  override type implNextT = __SpawnStartExImp
override def toString() : String = {"EPStartDriver_wD_W.RcvPrepSpawn"}
  override type msgT = MESSAGES.StartDriver.PrepSpawn
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "PrepSpawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPrepSpawnImp(c,session)}

protected case class __RcvPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPrepSpawn
}
  def rcvFrmm_M : (MESSAGES.StartDriver.PrepSpawn,__SpawnStartExImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.PrepSpawn],__SpawnStartExImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartDriver.PrepSpawn,__SpawnStartExImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.PrepSpawn],__SpawnStartExImp(c,session))) 
}
def rcvMSG : MESSAGES.StartDriver.PrepSpawn = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.PrepSpawn]}
def ? : MESSAGES.StartDriver.PrepSpawn = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.PrepSpawn]}
def channelCon : __SpawnStartExImp = {__SpawnStartExImp(c,session)}

}


  trait SpawnStartEx extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnStartEx extends SpawnStartEx {
  override protected def __children: List[EPStartDriver_wD_W] = List(T)
  override type implT = __SpawnStartExImp
  override type implNextT = __TImp
override def toString() : String = {"EPStartDriver_wD_W.SpawnStartEx"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "StartEx" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStartExImp(c,session)}

protected case class __SpawnStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStartEx
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPStartDriver_wD_W] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStartDriver_wD_W.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvEnd extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPStartDriver_wD_W] = List(End_wD_W_StartDriverEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_wD_W_StartDriverEndImp
override def toString() : String = {"EPStartDriver_wD_W.RcvEnd"}
  override type msgT = MESSAGES.StartDriver.End
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrmm_M : (MESSAGES.StartDriver.End,__End_wD_W_StartDriverEndImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.End],__End_wD_W_StartDriverEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartDriver.End,__End_wD_W_StartDriverEndImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.End],__End_wD_W_StartDriverEndImp(c,session))) 
}
def rcvMSG : MESSAGES.StartDriver.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.End]}
def ? : MESSAGES.StartDriver.End = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartDriver.End]}
def channelCon : __End_wD_W_StartDriverEndImp = {__End_wD_W_StartDriverEndImp(c,session)}

}


protected  trait End_wD_W_StartDriverEnd extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wD_W_StartDriverEnd extends End_wD_W_StartDriverEnd {
  override protected def __children: List[EPStartDriver_wD_W] = List()
  override type implT = __End_wD_W_StartDriverEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartDriver_wD_W.End_wD_W_StartDriverEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wD_W_StartDriverEndImp(c,session)}

protected case class __End_wD_W_StartDriverEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wD_W_StartDriverEnd
}
  
}



protected  trait End_wD_W_StartDriverFHandling extends EPStartDriver_wD_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wD_W_StartDriverFHandling extends End_wD_W_StartDriverFHandling {
  override protected def __children: List[EPStartDriver_wD_W] = List()
  override type implT = __End_wD_W_StartDriverFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartDriver_wD_W.End_wD_W_StartDriverFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wD_W_StartDriverFHandlingImp(c,session)}

protected case class __End_wD_W_StartDriverFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wD_W_StartDriverFHandling
}
  
}


}

object StartEx_W{
trait EPStartEx_W extends __EPType_W

object EPStartEx_W extends EPStartEx_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStartEx_W] = List(Hdl)
  override type implT = __EPStartEx_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStartEx_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def argsP: Role = Role("wEx",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = RoleSet("W") 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "StartEx"
}

protected case class __EPStartEx_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStartEx_W
}
  
}


protected  trait Hdl extends EPStartEx_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStartEx_W] = List(End_W_StartEx,RcvExFailed)
  override type implT = __HdlImp
  override type implNextT = __End_W_StartExImp
override def toString() : String = {"EPStartEx_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_W_StartEx extends EPStartEx_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_StartEx extends End_W_StartEx {
  override protected def __children: List[EPStartEx_W] = List()
  override type implT = __End_W_StartExImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartEx_W.End_W_StartEx"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_StartExImp(c,session)}

protected case class __End_W_StartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_StartEx
}
  
}



  trait RcvExFailed extends EPStartEx_W with event_lang.dsl.ChannelTypeRcv
  object RcvExFailed extends RcvExFailed {
  override protected def __children: List[EPStartEx_W] = List(SpawnStartEx)
  override type implT = __RcvExFailedImp
  override type implNextT = __SpawnStartExImp
override def toString() : String = {"EPStartEx_W.RcvExFailed"}
  override type msgT = MESSAGES.StartEx.ExFailed
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "ExFailed"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExFailedImp(c,session)}

protected case class __RcvExFailedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExFailed
}
  def rcvFrmm_M : (MESSAGES.StartEx.ExFailed,__SpawnStartExImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFailed],__SpawnStartExImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartEx.ExFailed,__SpawnStartExImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFailed],__SpawnStartExImp(c,session))) 
}
def rcvMSG : MESSAGES.StartEx.ExFailed = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFailed]}
def ? : MESSAGES.StartEx.ExFailed = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFailed]}
def channelCon : __SpawnStartExImp = {__SpawnStartExImp(c,session)}

}


  trait SpawnStartEx extends EPStartEx_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnStartEx extends SpawnStartEx {
  override protected def __children: List[EPStartEx_W] = List(End_W_StartExFHandling)
  override type implT = __SpawnStartExImp
  override type implNextT = __End_W_StartExFHandlingImp
override def toString() : String = {"EPStartEx_W.SpawnStartEx"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "StartEx" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStartExImp(c,session)}

protected case class __SpawnStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStartEx
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_W_StartExFHandling extends EPStartEx_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_StartExFHandling extends End_W_StartExFHandling {
  override protected def __children: List[EPStartEx_W] = List()
  override type implT = __End_W_StartExFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartEx_W.End_W_StartExFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_StartExFHandlingImp(c,session)}

protected case class __End_W_StartExFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_StartExFHandling
}
  
}


}

object StartEx_wD_W{
trait EPStartEx_wD_W extends __EPType_W

object EPStartEx_wD_W extends EPStartEx_wD_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStartEx_wD_W] = List(Hdl)
  override type implT = __EPStartEx_wD_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStartEx_wD_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def argsP: Role = Role("wEx",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("wD",RoleSet("W")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "StartEx"
}

protected case class __EPStartEx_wD_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStartEx_wD_W
}
  
}


protected  trait Hdl extends EPStartEx_wD_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStartEx_wD_W] = List(RcvExFinished,RcvExFailed)
  override type implT = __HdlImp
  override type implNextT = __RcvExFinishedImp
override def toString() : String = {"EPStartEx_wD_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvExFinished extends EPStartEx_wD_W with event_lang.dsl.ChannelTypeRcv
  object RcvExFinished extends RcvExFinished {
  override protected def __children: List[EPStartEx_wD_W] = List(End_wD_W_StartEx)
  override type implT = __RcvExFinishedImp
  override type implNextT = __End_wD_W_StartExImp
override def toString() : String = {"EPStartEx_wD_W.RcvExFinished"}
  override type msgT = MESSAGES.StartEx.ExFinished
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "ExFinished"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExFinishedImp(c,session)}

protected case class __RcvExFinishedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExFinished
}
  def rcvFrmm_M : (MESSAGES.StartEx.ExFinished,__End_wD_W_StartExImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFinished],__End_wD_W_StartExImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartEx.ExFinished,__End_wD_W_StartExImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFinished],__End_wD_W_StartExImp(c,session))) 
}
def rcvMSG : MESSAGES.StartEx.ExFinished = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFinished]}
def ? : MESSAGES.StartEx.ExFinished = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFinished]}
def channelCon : __End_wD_W_StartExImp = {__End_wD_W_StartExImp(c,session)}

}


protected  trait End_wD_W_StartEx extends EPStartEx_wD_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wD_W_StartEx extends End_wD_W_StartEx {
  override protected def __children: List[EPStartEx_wD_W] = List()
  override type implT = __End_wD_W_StartExImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartEx_wD_W.End_wD_W_StartEx"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wD_W_StartExImp(c,session)}

protected case class __End_wD_W_StartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wD_W_StartEx
}
  
}



  trait RcvExFailed extends EPStartEx_wD_W with event_lang.dsl.ChannelTypeRcv
  object RcvExFailed extends RcvExFailed {
  override protected def __children: List[EPStartEx_wD_W] = List(SpawnStartEx)
  override type implT = __RcvExFailedImp
  override type implNextT = __SpawnStartExImp
override def toString() : String = {"EPStartEx_wD_W.RcvExFailed"}
  override type msgT = MESSAGES.StartEx.ExFailed
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "ExFailed"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExFailedImp(c,session)}

protected case class __RcvExFailedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExFailed
}
  def rcvFrmm_M : (MESSAGES.StartEx.ExFailed,__SpawnStartExImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFailed],__SpawnStartExImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartEx.ExFailed,__SpawnStartExImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFailed],__SpawnStartExImp(c,session))) 
}
def rcvMSG : MESSAGES.StartEx.ExFailed = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFailed]}
def ? : MESSAGES.StartEx.ExFailed = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.StartEx.ExFailed]}
def channelCon : __SpawnStartExImp = {__SpawnStartExImp(c,session)}

}


  trait SpawnStartEx extends EPStartEx_wD_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnStartEx extends SpawnStartEx {
  override protected def __children: List[EPStartEx_wD_W] = List(End_wD_W_StartExFHandling)
  override type implT = __SpawnStartExImp
  override type implNextT = __End_wD_W_StartExFHandlingImp
override def toString() : String = {"EPStartEx_wD_W.SpawnStartEx"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "StartEx" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStartExImp(c,session)}

protected case class __SpawnStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStartEx
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_wD_W_StartExFHandling extends EPStartEx_wD_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wD_W_StartExFHandling extends End_wD_W_StartExFHandling {
  override protected def __children: List[EPStartEx_wD_W] = List()
  override type implT = __End_wD_W_StartExFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartEx_wD_W.End_wD_W_StartExFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wD_W_StartExFHandlingImp(c,session)}

protected case class __End_wD_W_StartExFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wD_W_StartExFHandling
}
  
}


}

}

object M {
val subs : Seq[dsl.ChannelTypeSubS] = List(StartEx_m_M.EPStartEx_m_M,StartDriver_m_M.EPStartDriver_m_M)
trait __EPType_M extends AbstractChannelType {

}

trait EPType_M[T<: TState] extends AbstractEndPoint[__EPType_M,T] {
override val roleSet: RoleSet = RoleSet("M")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(StartEx_m_M.EPStartEx_m_M,StartDriver_m_M.EPStartDriver_m_M)

}

object StartEx_m_M{
trait EPStartEx_m_M extends __EPType_M

object EPStartEx_m_M extends EPStartEx_m_M with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStartEx_m_M] = List(Hdl)
  override type implT = __EPStartEx_m_MImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStartEx_m_MImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def argsP: Role = Role("wEx",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("m",RoleSet("M")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "StartEx"
}

protected case class __EPStartEx_m_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStartEx_m_M
}
  
}


protected  trait Hdl extends EPStartEx_m_M with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStartEx_m_M] = List(SndExDesc,Failed_wEx_W)
  override type implT = __HdlImp
  override type implNextT = __SndExDescImp
override def toString() : String = {"EPStartEx_m_M.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndExDesc extends EPStartEx_m_M with event_lang.dsl.ChannelTypeSnd
  object SndExDesc extends SndExDesc {
  override protected def __children: List[EPStartEx_m_M] = List(RcvExDone)
  override type implT = __SndExDescImp
  override type implNextT = __RcvExDoneImp
override def toString() : String = {"EPStartEx_m_M.SndExDesc"}
    override def to : RRole = Role("wEx",RoleSet("W")) 
   override def l : String = "ExDesc" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndExDescImp(c,session)}

protected case class __SndExDescImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndExDesc
}
  private var notUsed = true
def sndTowEx_W(m : MESSAGES.StartEx.ExDesc) : __RcvExDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wEx",RoleSet("W")),m)
__RcvExDoneImp(c,session)}
def !(m : MESSAGES.StartEx.ExDesc) : __RcvExDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wEx",RoleSet("W")),m)
__RcvExDoneImp(c,session)}
def snd(m : MESSAGES.StartEx.ExDesc) : __RcvExDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wEx",RoleSet("W")),m)
__RcvExDoneImp(c,session)}

}


  trait RcvExDone extends EPStartEx_m_M with event_lang.dsl.ChannelTypeRcv
  object RcvExDone extends RcvExDone {
  override protected def __children: List[EPStartEx_m_M] = List(SndExFinished)
  override type implT = __RcvExDoneImp
  override type implNextT = __SndExFinishedImp
override def toString() : String = {"EPStartEx_m_M.RcvExDone"}
  override type msgT = MESSAGES.StartEx.ExDone
   override def frm : Role = Role("wEx",RoleSet("W")) 
   override def l : String = "ExDone"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvExDoneImp(c,session)}

protected case class __RcvExDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvExDone
}
  def rcvFrmwEx_W : (MESSAGES.StartEx.ExDone,__SndExFinishedImp) = {(c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.StartEx.ExDone],__SndExFinishedImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartEx.ExDone,__SndExFinishedImp),T]) : T = {
  f((c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.StartEx.ExDone],__SndExFinishedImp(c,session))) 
}
def rcvMSG : MESSAGES.StartEx.ExDone = {c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.StartEx.ExDone]}
def ? : MESSAGES.StartEx.ExDone = {c.rcv(Role("wEx",RoleSet("W"))).asInstanceOf[MESSAGES.StartEx.ExDone]}
def channelCon : __SndExFinishedImp = {__SndExFinishedImp(c,session)}

}


  trait SndExFinished extends EPStartEx_m_M with event_lang.dsl.ChannelTypeSnd
  object SndExFinished extends SndExFinished {
  override protected def __children: List[EPStartEx_m_M] = List(End_m_M_StartEx)
  override type implT = __SndExFinishedImp
  override type implNextT = __End_m_M_StartExImp
override def toString() : String = {"EPStartEx_m_M.SndExFinished"}
    override def to : RRole = Role("wD",RoleSet("W")) 
   override def l : String = "ExFinished" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndExFinishedImp(c,session)}

protected case class __SndExFinishedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndExFinished
}
  private var notUsed = true
def sndTowD_W(m : MESSAGES.StartEx.ExFinished) : __End_m_M_StartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wD",RoleSet("W")),m)
__End_m_M_StartExImp(c,session)}
def !(m : MESSAGES.StartEx.ExFinished) : __End_m_M_StartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wD",RoleSet("W")),m)
__End_m_M_StartExImp(c,session)}
def snd(m : MESSAGES.StartEx.ExFinished) : __End_m_M_StartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wD",RoleSet("W")),m)
__End_m_M_StartExImp(c,session)}

}


protected  trait End_m_M_StartEx extends EPStartEx_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_StartEx extends End_m_M_StartEx {
  override protected def __children: List[EPStartEx_m_M] = List()
  override type implT = __End_m_M_StartExImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartEx_m_M.End_m_M_StartEx"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_StartExImp(c,session)}

protected case class __End_m_M_StartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_StartEx
}
  
}



  trait Failed_wEx_W extends EPStartEx_m_M with event_lang.dsl.ChannelTypeFDtct
  object Failed_wEx_W extends Failed_wEx_W {
  override protected def __children: List[EPStartEx_m_M] = List(SndExFailed)
  override type implT = __Failed_wEx_WImp
  override type implNextT = __SndExFailedImp
override def toString() : String = {"EPStartEx_m_M.Failed_wEx_W"}
  override def suspect : Role = Role("wEx",RoleSet("W")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_wEx_WImp(c,session)}

protected case class __Failed_wEx_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_wEx_W
}
  def failed_wEx_W(): __SndExFailedImp = {__SndExFailedImp(c,session)}

}


  trait SndExFailed extends EPStartEx_m_M with event_lang.dsl.ChannelTypeSnd
  object SndExFailed extends SndExFailed {
  override protected def __children: List[EPStartEx_m_M] = List(SpawnStartEx)
  override type implT = __SndExFailedImp
  override type implNextT = __SpawnStartExImp
override def toString() : String = {"EPStartEx_m_M.SndExFailed"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "ExFailed" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndExFailedImp(c,session)}

protected case class __SndExFailedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndExFailed
}
  private var notUsed = true
def sndToW(m : MESSAGES.StartEx.ExFailed) : __SpawnStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnStartExImp(c,session)}
def !(m : MESSAGES.StartEx.ExFailed) : __SpawnStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnStartExImp(c,session)}
def snd(m : MESSAGES.StartEx.ExFailed) : __SpawnStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnStartExImp(c,session)}

}


  trait SpawnStartEx extends EPStartEx_m_M with event_lang.dsl.ChannelTypeSpawn
  object SpawnStartEx extends SpawnStartEx {
  override protected def __children: List[EPStartEx_m_M] = List(End_m_M_StartExFHandling)
  override type implT = __SpawnStartExImp
  override type implNextT = __End_m_M_StartExFHandlingImp
override def toString() : String = {"EPStartEx_m_M.SpawnStartEx"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "StartEx" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStartExImp(c,session)}

protected case class __SpawnStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStartEx
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_m_M_StartExFHandling extends EPStartEx_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_StartExFHandling extends End_m_M_StartExFHandling {
  override protected def __children: List[EPStartEx_m_M] = List()
  override type implT = __End_m_M_StartExFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartEx_m_M.End_m_M_StartExFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_StartExFHandlingImp(c,session)}

protected case class __End_m_M_StartExFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_StartExFHandling
}
  
}


}

object StartDriver_m_M{
trait EPStartDriver_m_M extends __EPType_M

object EPStartDriver_m_M extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStartDriver_m_M] = List(Hdl)
  override type implT = __EPStartDriver_m_MImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStartDriver_m_MImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("wD",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("m",RoleSet("M")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "StartDriver"
}

protected case class __EPStartDriver_m_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStartDriver_m_M
}
  
}


protected  trait Hdl extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStartDriver_m_M] = List(SndDriverDesc,Failed_wD_W)
  override type implT = __HdlImp
  override type implNextT = __SndDriverDescImp
override def toString() : String = {"EPStartDriver_m_M.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndDriverDesc extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeSnd
  object SndDriverDesc extends SndDriverDesc {
  override protected def __children: List[EPStartDriver_m_M] = List(RcvAck)
  override type implT = __SndDriverDescImp
  override type implNextT = __RcvAckImp
override def toString() : String = {"EPStartDriver_m_M.SndDriverDesc"}
    override def to : RRole = Role("wD",RoleSet("W")) 
   override def l : String = "DriverDesc" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDriverDescImp(c,session)}

protected case class __SndDriverDescImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDriverDesc
}
  private var notUsed = true
def sndTowD_W(m : MESSAGES.StartDriver.DriverDesc) : __RcvAckImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wD",RoleSet("W")),m)
__RcvAckImp(c,session)}
def !(m : MESSAGES.StartDriver.DriverDesc) : __RcvAckImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wD",RoleSet("W")),m)
__RcvAckImp(c,session)}
def snd(m : MESSAGES.StartDriver.DriverDesc) : __RcvAckImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wD",RoleSet("W")),m)
__RcvAckImp(c,session)}

}


  trait RcvAck extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeRcv
  object RcvAck extends RcvAck {
  override protected def __children: List[EPStartDriver_m_M] = List(RecT)
  override type implT = __RcvAckImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStartDriver_m_M.RcvAck"}
  override type msgT = MESSAGES.StartDriver.Ack
   override def frm : Role = Role("wD",RoleSet("W")) 
   override def l : String = "Ack"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAckImp(c,session)}

protected case class __RcvAckImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAck
}
  def rcvFrmwD_W : (MESSAGES.StartDriver.Ack,__RecTImp) = {(c.rcv(Role("wD",RoleSet("W"))).asInstanceOf[MESSAGES.StartDriver.Ack],__RecTImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.StartDriver.Ack,__RecTImp),T]) : T = {
  f((c.rcv(Role("wD",RoleSet("W"))).asInstanceOf[MESSAGES.StartDriver.Ack],__RecTImp(c,session))) 
}
def rcvMSG : MESSAGES.StartDriver.Ack = {c.rcv(Role("wD",RoleSet("W"))).asInstanceOf[MESSAGES.StartDriver.Ack]}
def ? : MESSAGES.StartDriver.Ack = {c.rcv(Role("wD",RoleSet("W"))).asInstanceOf[MESSAGES.StartDriver.Ack]}
def channelCon : __RecTImp = {__RecTImp(c,session)}

}


protected  trait RecT extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPStartDriver_m_M] = List(SelPrepSpawnEnd)
  override type implT = __RecTImp
  override type implNextT = __SelPrepSpawnEndImp
override def toString() : String = {"EPStartDriver_m_M.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelPrepSpawnEnd extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeSel
protected  object SelPrepSpawnEnd extends SelPrepSpawnEnd {
  override protected def __children: List[EPStartDriver_m_M] = List(SndPrepSpawn,SndEnd)
  override type implT = __SelPrepSpawnEndImp
  override type implNextT = __SndPrepSpawnImp
override def toString() : String = {"EPStartDriver_m_M.SelPrepSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelPrepSpawnEndImp(c,session)}

protected case class __SelPrepSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelPrepSpawnEnd
}
  private var notUsed = true
def !(m : MESSAGES.StartDriver.PrepSpawn) : __SpawnStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __SpawnStartExImp(c,session)}
def sndToW(m : MESSAGES.StartDriver.PrepSpawn) : __SpawnStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __SpawnStartExImp(c,session)}

def !(m : MESSAGES.StartDriver.End) : __End_m_M_StartDriverEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __End_m_M_StartDriverEndImp(c,session)}
def sndToW(m : MESSAGES.StartDriver.End) : __End_m_M_StartDriverEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __End_m_M_StartDriverEndImp(c,session)}

}


  trait SndPrepSpawn extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeSnd
  object SndPrepSpawn extends SndPrepSpawn {
  override protected def __children: List[EPStartDriver_m_M] = List(SpawnStartEx)
  override type implT = __SndPrepSpawnImp
  override type implNextT = __SpawnStartExImp
override def toString() : String = {"EPStartDriver_m_M.SndPrepSpawn"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "PrepSpawn" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPrepSpawnImp(c,session)}

protected case class __SndPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPrepSpawn
}
  private var notUsed = true
def sndToW(m : MESSAGES.StartDriver.PrepSpawn) : __SpawnStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnStartExImp(c,session)}
def !(m : MESSAGES.StartDriver.PrepSpawn) : __SpawnStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnStartExImp(c,session)}
def snd(m : MESSAGES.StartDriver.PrepSpawn) : __SpawnStartExImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnStartExImp(c,session)}

}


  trait SpawnStartEx extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeSpawn
  object SpawnStartEx extends SpawnStartEx {
  override protected def __children: List[EPStartDriver_m_M] = List(T)
  override type implT = __SpawnStartExImp
  override type implNextT = __TImp
override def toString() : String = {"EPStartDriver_m_M.SpawnStartEx"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("wD",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "StartEx" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStartExImp(c,session)}

protected case class __SpawnStartExImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStartEx
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPStartDriver_m_M] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStartDriver_m_M.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait SndEnd extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeSnd
  object SndEnd extends SndEnd {
  override protected def __children: List[EPStartDriver_m_M] = List(End_m_M_StartDriverEnd)
  override type implT = __SndEndImp
  override type implNextT = __End_m_M_StartDriverEndImp
override def toString() : String = {"EPStartDriver_m_M.SndEnd"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "End" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndImp(c,session)}

protected case class __SndEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEnd
}
  private var notUsed = true
def sndToW(m : MESSAGES.StartDriver.End) : __End_m_M_StartDriverEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_StartDriverEndImp(c,session)}
def !(m : MESSAGES.StartDriver.End) : __End_m_M_StartDriverEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_StartDriverEndImp(c,session)}
def snd(m : MESSAGES.StartDriver.End) : __End_m_M_StartDriverEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_m_M_StartDriverEndImp(c,session)}

}


protected  trait End_m_M_StartDriverEnd extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_StartDriverEnd extends End_m_M_StartDriverEnd {
  override protected def __children: List[EPStartDriver_m_M] = List()
  override type implT = __End_m_M_StartDriverEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartDriver_m_M.End_m_M_StartDriverEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_StartDriverEndImp(c,session)}

protected case class __End_m_M_StartDriverEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_StartDriverEnd
}
  
}



  trait Failed_wD_W extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeFDtct
  object Failed_wD_W extends Failed_wD_W {
  override protected def __children: List[EPStartDriver_m_M] = List(SndDriverFailed)
  override type implT = __Failed_wD_WImp
  override type implNextT = __SndDriverFailedImp
override def toString() : String = {"EPStartDriver_m_M.Failed_wD_W"}
  override def suspect : Role = Role("wD",RoleSet("W")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_wD_WImp(c,session)}

protected case class __Failed_wD_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_wD_W
}
  def failed_wD_W(): __SndDriverFailedImp = {__SndDriverFailedImp(c,session)}

}


  trait SndDriverFailed extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeSnd
  object SndDriverFailed extends SndDriverFailed {
  override protected def __children: List[EPStartDriver_m_M] = List(SpawnStartDriver)
  override type implT = __SndDriverFailedImp
  override type implNextT = __SpawnStartDriverImp
override def toString() : String = {"EPStartDriver_m_M.SndDriverFailed"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "DriverFailed" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDriverFailedImp(c,session)}

protected case class __SndDriverFailedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDriverFailed
}
  private var notUsed = true
def sndToW(m : MESSAGES.StartDriver.DriverFailed) : __SpawnStartDriverImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnStartDriverImp(c,session)}
def !(m : MESSAGES.StartDriver.DriverFailed) : __SpawnStartDriverImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnStartDriverImp(c,session)}
def snd(m : MESSAGES.StartDriver.DriverFailed) : __SpawnStartDriverImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnStartDriverImp(c,session)}

}


  trait SpawnStartDriver extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeSpawn
  object SpawnStartDriver extends SpawnStartDriver {
  override protected def __children: List[EPStartDriver_m_M] = List(End_m_M_StartDriverFHandling)
  override type implT = __SpawnStartDriverImp
  override type implNextT = __End_m_M_StartDriverFHandlingImp
override def toString() : String = {"EPStartDriver_m_M.SpawnStartDriver"}
    override def y: List[Role] = List(Role("m",RoleSet("M"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "StartDriver" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStartDriverImp(c,session)}

protected case class __SpawnStartDriverImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStartDriver
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_m_M_StartDriverFHandling extends EPStartDriver_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_StartDriverFHandling extends End_m_M_StartDriverFHandling {
  override protected def __children: List[EPStartDriver_m_M] = List()
  override type implT = __End_m_M_StartDriverFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStartDriver_m_M.End_m_M_StartDriverFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_StartDriverFHandlingImp(c,session)}

protected case class __End_m_M_StartDriverFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_StartDriverFHandling
}
  
}


}

}

}
