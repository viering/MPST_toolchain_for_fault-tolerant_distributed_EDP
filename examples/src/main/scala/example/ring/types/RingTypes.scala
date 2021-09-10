package example.ring.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object Ring {
object RS {
val W : RoleSet = RoleSet("W")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object Chain {
case class MLast() extends MSG {
   override def l : String = "MLast"
}

case class FChain() extends MSG {
   override def l : String = "FChain"
}

case class Spawn() extends MSG {
   override def l : String = "Spawn"
}

case class MI() extends MSG {
   override def l : String = "MI"
}

case class End() extends MSG {
   override def l : String = "End"
}

}

object Pick {
case class M1() extends MSG {
   override def l : String = "M1"
}

case class FPick() extends MSG {
   override def l : String = "FPick"
}

}

object Main {
case class FMain() extends MSG {
   override def l : String = "FMain"
}

}

}

object PROTOCOLS {
object Pick {
val w1_W = Role("w1",RoleSet("W"))
val W = RoleSet("W")
val a_A = Role("a",RoleSet("A"))
val w0_W = Role("w0",RoleSet("W"))
}

object Main {
val w0_W = Role("w0",RoleSet("W"))
val W = RoleSet("W")
val a_A = Role("a",RoleSet("A"))
}

object Chain {
val wII_W = Role("wII",RoleSet("W"))
val W = RoleSet("W")
val a_A = Role("a",RoleSet("A"))
val w0_W = Role("w0",RoleSet("W"))
val wI_W = Role("wI",RoleSet("W"))
}

}

object W {
val subs : Seq[dsl.ChannelTypeSubS] = List(Pick_w1_W.EPPick_w1_W,Pick_w0_W.EPPick_w0_W,Chain_wI_W.EPChain_wI_W,Main_W.EPMain_W,Main_w0_W.EPMain_w0_W,Chain_wII_W.EPChain_wII_W,Chain_W.EPChain_W,Pick_W.EPPick_W,Chain_w0_W.EPChain_w0_W)
trait __EPType_W extends AbstractChannelType {

}

trait EPType_W[T<: TState] extends AbstractEndPoint[__EPType_W,T] {
override val roleSet: RoleSet = RoleSet("W")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Pick_w1_W.EPPick_w1_W,Pick_w0_W.EPPick_w0_W,Chain_wI_W.EPChain_wI_W,Main_W.EPMain_W,Main_w0_W.EPMain_w0_W,Chain_wII_W.EPChain_wII_W,Chain_W.EPChain_W,Pick_W.EPPick_W,Chain_w0_W.EPChain_w0_W)

}

object Pick_W{
trait EPPick_W extends __EPType_W

object EPPick_W extends EPPick_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPick_W] = List(Hdl)
  override type implT = __EPPick_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPick_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W"))) 
  override def argsP: Role = Role("w1",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = RoleSet("W") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Pick"
}

protected case class __EPPick_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPick_W
}
  
}


protected  trait Hdl extends EPPick_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPick_W] = List(SpawnChain,RcvFPick)
  override type implT = __HdlImp
  override type implNextT = __SpawnChainImp
override def toString() : String = {"EPPick_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnChain extends EPPick_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnChain extends SpawnChain {
  override protected def __children: List[EPPick_W] = List(End_W_Pick)
  override type implT = __SpawnChainImp
  override type implNextT = __End_W_PickImp
override def toString() : String = {"EPPick_W.SpawnChain"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("w1",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Chain" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnChainImp(c,session)}

protected case class __SpawnChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnChain
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_W_Pick extends EPPick_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_Pick extends End_W_Pick {
  override protected def __children: List[EPPick_W] = List()
  override type implT = __End_W_PickImp
  override type implNextT = Nothing
override def toString() : String = {"EPPick_W.End_W_Pick"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_PickImp(c,session)}

protected case class __End_W_PickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_Pick
}
  
}



  trait RcvFPick extends EPPick_W with event_lang.dsl.ChannelTypeRcv
  object RcvFPick extends RcvFPick {
  override protected def __children: List[EPPick_W] = List(End_W_PickFHandling)
  override type implT = __RcvFPickImp
  override type implNextT = __End_W_PickFHandlingImp
override def toString() : String = {"EPPick_W.RcvFPick"}
  override type msgT = MESSAGES.Pick.FPick
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FPick"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFPickImp(c,session)}

protected case class __RcvFPickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFPick
}
  def rcvFrma_A : (MESSAGES.Pick.FPick,__End_W_PickFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pick.FPick],__End_W_PickFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pick.FPick,__End_W_PickFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pick.FPick],__End_W_PickFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Pick.FPick = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pick.FPick]}
def ? : MESSAGES.Pick.FPick = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pick.FPick]}
def channelCon : __End_W_PickFHandlingImp = {__End_W_PickFHandlingImp(c,session)}

}


protected  trait End_W_PickFHandling extends EPPick_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_PickFHandling extends End_W_PickFHandling {
  override protected def __children: List[EPPick_W] = List()
  override type implT = __End_W_PickFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPick_W.End_W_PickFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_PickFHandlingImp(c,session)}

protected case class __End_W_PickFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_PickFHandling
}
  
}


}

object Main_w0_W{
trait EPMain_w0_W extends __EPType_W

object EPMain_w0_W extends EPMain_w0_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_w0_W] = List(Hdl)
  override type implT = __EPMain_w0_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_w0_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("w0",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("w0",RoleSet("W")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Main"
}

protected case class __EPMain_w0_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_w0_W
}
  
}


protected  trait Hdl extends EPMain_w0_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_w0_W] = List(SpawnPick,End_w0_W_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnPickImp
override def toString() : String = {"EPMain_w0_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnPick extends EPMain_w0_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnPick extends SpawnPick {
  override protected def __children: List[EPMain_w0_W] = List(End_w0_W_Main)
  override type implT = __SpawnPickImp
  override type implNextT = __End_w0_W_MainImp
override def toString() : String = {"EPMain_w0_W.SpawnPick"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Pick" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPickImp(c,session)}

protected case class __SpawnPickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPick
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_w0_W_Main extends EPMain_w0_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w0_W_Main extends End_w0_W_Main {
  override protected def __children: List[EPMain_w0_W] = List()
  override type implT = __End_w0_W_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_w0_W.End_w0_W_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w0_W_MainImp(c,session)}

protected case class __End_w0_W_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w0_W_Main
}
  
}



protected  trait End_w0_W_MainFHandling extends EPMain_w0_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w0_W_MainFHandling extends End_w0_W_MainFHandling {
  override protected def __children: List[EPMain_w0_W] = List()
  override type implT = __End_w0_W_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_w0_W.End_w0_W_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w0_W_MainFHandlingImp(c,session)}

protected case class __End_w0_W_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w0_W_MainFHandling
}
  
}


}

object Chain_wII_W{
trait EPChain_wII_W extends __EPType_W

object EPChain_wII_W extends EPChain_wII_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPChain_wII_W] = List(Hdl)
  override type implT = __EPChain_wII_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPChain_wII_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wI",RoleSet("W"))) 
  override def argsP: Role = Role("wII",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("wII",RoleSet("W")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Chain"
}

protected case class __EPChain_wII_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPChain_wII_W
}
  
}


protected  trait Hdl extends EPChain_wII_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPChain_wII_W] = List(RcvMI,End_wII_W_ChainFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvMIImp
override def toString() : String = {"EPChain_wII_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvMI extends EPChain_wII_W with event_lang.dsl.ChannelTypeRcv
  object RcvMI extends RcvMI {
  override protected def __children: List[EPChain_wII_W] = List(SelSpawnEnd)
  override type implT = __RcvMIImp
  override type implNextT = __SelSpawnEndImp
override def toString() : String = {"EPChain_wII_W.RcvMI"}
  override type msgT = MESSAGES.Chain.MI
   override def frm : Role = Role("wI",RoleSet("W")) 
   override def l : String = "MI"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMIImp(c,session)}

protected case class __RcvMIImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMI
}
  def rcvFrmwI_W : (MESSAGES.Chain.MI,__SelSpawnEndImp) = {(c.rcv(Role("wI",RoleSet("W"))).asInstanceOf[MESSAGES.Chain.MI],__SelSpawnEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.MI,__SelSpawnEndImp),T]) : T = {
  f((c.rcv(Role("wI",RoleSet("W"))).asInstanceOf[MESSAGES.Chain.MI],__SelSpawnEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.MI = {c.rcv(Role("wI",RoleSet("W"))).asInstanceOf[MESSAGES.Chain.MI]}
def ? : MESSAGES.Chain.MI = {c.rcv(Role("wI",RoleSet("W"))).asInstanceOf[MESSAGES.Chain.MI]}
def channelCon : __SelSpawnEndImp = {__SelSpawnEndImp(c,session)}

}


protected  trait SelSpawnEnd extends EPChain_wII_W with event_lang.dsl.ChannelTypeBrn
protected  object SelSpawnEnd extends SelSpawnEnd {
  override protected def __children: List[EPChain_wII_W] = List(RcvSpawn,RcvEnd)
  override type implT = __SelSpawnEndImp
  override type implNextT = __RcvSpawnImp
override def toString() : String = {"EPChain_wII_W.SelSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelSpawnEndImp(c,session)}

protected case class __SelSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvSpawn extends EPChain_wII_W with event_lang.dsl.ChannelTypeRcv
  object RcvSpawn extends RcvSpawn {
  override protected def __children: List[EPChain_wII_W] = List(SpawnChain)
  override type implT = __RcvSpawnImp
  override type implNextT = __SpawnChainImp
override def toString() : String = {"EPChain_wII_W.RcvSpawn"}
  override type msgT = MESSAGES.Chain.Spawn
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "Spawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvSpawnImp(c,session)}

protected case class __RcvSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvSpawn
}
  def rcvFrma_A : (MESSAGES.Chain.Spawn,__SpawnChainImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn],__SpawnChainImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.Spawn,__SpawnChainImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn],__SpawnChainImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.Spawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn]}
def ? : MESSAGES.Chain.Spawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn]}
def channelCon : __SpawnChainImp = {__SpawnChainImp(c,session)}

}


  trait SpawnChain extends EPChain_wII_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnChain extends SpawnChain {
  override protected def __children: List[EPChain_wII_W] = List(End_wII_W_ChainSpawn)
  override type implT = __SpawnChainImp
  override type implNextT = __End_wII_W_ChainSpawnImp
override def toString() : String = {"EPChain_wII_W.SpawnChain"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wII",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Chain" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnChainImp(c,session)}

protected case class __SpawnChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnChain
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_wII_W_ChainSpawn extends EPChain_wII_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wII_W_ChainSpawn extends End_wII_W_ChainSpawn {
  override protected def __children: List[EPChain_wII_W] = List()
  override type implT = __End_wII_W_ChainSpawnImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_wII_W.End_wII_W_ChainSpawn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wII_W_ChainSpawnImp(c,session)}

protected case class __End_wII_W_ChainSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wII_W_ChainSpawn
}
  
}



  trait RcvEnd extends EPChain_wII_W with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPChain_wII_W] = List(SndMLast)
  override type implT = __RcvEndImp
  override type implNextT = __SndMLastImp
override def toString() : String = {"EPChain_wII_W.RcvEnd"}
  override type msgT = MESSAGES.Chain.End
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrma_A : (MESSAGES.Chain.End,__SndMLastImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End],__SndMLastImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.End,__SndMLastImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End],__SndMLastImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End]}
def ? : MESSAGES.Chain.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End]}
def channelCon : __SndMLastImp = {__SndMLastImp(c,session)}

}


  trait SndMLast extends EPChain_wII_W with event_lang.dsl.ChannelTypeSnd
  object SndMLast extends SndMLast {
  override protected def __children: List[EPChain_wII_W] = List(End_wII_W_ChainEnd)
  override type implT = __SndMLastImp
  override type implNextT = __End_wII_W_ChainEndImp
override def toString() : String = {"EPChain_wII_W.SndMLast"}
    override def to : RRole = Role("w0",RoleSet("W")) 
   override def l : String = "MLast" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMLastImp(c,session)}

protected case class __SndMLastImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndMLast
}
  private var notUsed = true
def sndTow0_W(m : MESSAGES.Chain.MLast) : __End_wII_W_ChainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w0",RoleSet("W")),m)
__End_wII_W_ChainEndImp(c,session)}
def !(m : MESSAGES.Chain.MLast) : __End_wII_W_ChainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w0",RoleSet("W")),m)
__End_wII_W_ChainEndImp(c,session)}
def snd(m : MESSAGES.Chain.MLast) : __End_wII_W_ChainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w0",RoleSet("W")),m)
__End_wII_W_ChainEndImp(c,session)}

}


protected  trait End_wII_W_ChainEnd extends EPChain_wII_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wII_W_ChainEnd extends End_wII_W_ChainEnd {
  override protected def __children: List[EPChain_wII_W] = List()
  override type implT = __End_wII_W_ChainEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_wII_W.End_wII_W_ChainEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wII_W_ChainEndImp(c,session)}

protected case class __End_wII_W_ChainEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wII_W_ChainEnd
}
  
}



protected  trait End_wII_W_ChainFHandling extends EPChain_wII_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wII_W_ChainFHandling extends End_wII_W_ChainFHandling {
  override protected def __children: List[EPChain_wII_W] = List()
  override type implT = __End_wII_W_ChainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_wII_W.End_wII_W_ChainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wII_W_ChainFHandlingImp(c,session)}

protected case class __End_wII_W_ChainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wII_W_ChainFHandling
}
  
}


}

object Pick_w0_W{
trait EPPick_w0_W extends __EPType_W

object EPPick_w0_W extends EPPick_w0_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPick_w0_W] = List(Hdl)
  override type implT = __EPPick_w0_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPick_w0_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W"))) 
  override def argsP: Role = Role("w1",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("w0",RoleSet("W")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Pick"
}

protected case class __EPPick_w0_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPick_w0_W
}
  
}


protected  trait Hdl extends EPPick_w0_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPick_w0_W] = List(SndM1,RcvFPick)
  override type implT = __HdlImp
  override type implNextT = __SndM1Imp
override def toString() : String = {"EPPick_w0_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndM1 extends EPPick_w0_W with event_lang.dsl.ChannelTypeSnd
  object SndM1 extends SndM1 {
  override protected def __children: List[EPPick_w0_W] = List(SpawnChain)
  override type implT = __SndM1Imp
  override type implNextT = __SpawnChainImp
override def toString() : String = {"EPPick_w0_W.SndM1"}
    override def to : RRole = Role("w1",RoleSet("W")) 
   override def l : String = "M1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndM1Imp(c,session)}

protected case class __SndM1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndM1
}
  private var notUsed = true
def sndTow1_W(m : MESSAGES.Pick.M1) : __SpawnChainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w1",RoleSet("W")),m)
__SpawnChainImp(c,session)}
def !(m : MESSAGES.Pick.M1) : __SpawnChainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w1",RoleSet("W")),m)
__SpawnChainImp(c,session)}
def snd(m : MESSAGES.Pick.M1) : __SpawnChainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w1",RoleSet("W")),m)
__SpawnChainImp(c,session)}

}


  trait SpawnChain extends EPPick_w0_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnChain extends SpawnChain {
  override protected def __children: List[EPPick_w0_W] = List(End_w0_W_Pick)
  override type implT = __SpawnChainImp
  override type implNextT = __End_w0_W_PickImp
override def toString() : String = {"EPPick_w0_W.SpawnChain"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("w1",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Chain" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnChainImp(c,session)}

protected case class __SpawnChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnChain
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_w0_W_Pick extends EPPick_w0_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w0_W_Pick extends End_w0_W_Pick {
  override protected def __children: List[EPPick_w0_W] = List()
  override type implT = __End_w0_W_PickImp
  override type implNextT = Nothing
override def toString() : String = {"EPPick_w0_W.End_w0_W_Pick"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w0_W_PickImp(c,session)}

protected case class __End_w0_W_PickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w0_W_Pick
}
  
}



  trait RcvFPick extends EPPick_w0_W with event_lang.dsl.ChannelTypeRcv
  object RcvFPick extends RcvFPick {
  override protected def __children: List[EPPick_w0_W] = List(End_w0_W_PickFHandling)
  override type implT = __RcvFPickImp
  override type implNextT = __End_w0_W_PickFHandlingImp
override def toString() : String = {"EPPick_w0_W.RcvFPick"}
  override type msgT = MESSAGES.Pick.FPick
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FPick"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFPickImp(c,session)}

protected case class __RcvFPickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFPick
}
  def rcvFrma_A : (MESSAGES.Pick.FPick,__End_w0_W_PickFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pick.FPick],__End_w0_W_PickFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pick.FPick,__End_w0_W_PickFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pick.FPick],__End_w0_W_PickFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Pick.FPick = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pick.FPick]}
def ? : MESSAGES.Pick.FPick = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pick.FPick]}
def channelCon : __End_w0_W_PickFHandlingImp = {__End_w0_W_PickFHandlingImp(c,session)}

}


protected  trait End_w0_W_PickFHandling extends EPPick_w0_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w0_W_PickFHandling extends End_w0_W_PickFHandling {
  override protected def __children: List[EPPick_w0_W] = List()
  override type implT = __End_w0_W_PickFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPick_w0_W.End_w0_W_PickFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w0_W_PickFHandlingImp(c,session)}

protected case class __End_w0_W_PickFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w0_W_PickFHandling
}
  
}


}

object Chain_W{
trait EPChain_W extends __EPType_W

object EPChain_W extends EPChain_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPChain_W] = List(Hdl)
  override type implT = __EPChain_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPChain_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wI",RoleSet("W"))) 
  override def argsP: Role = Role("wII",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = RoleSet("W") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Chain"
}

protected case class __EPChain_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPChain_W
}
  
}


protected  trait Hdl extends EPChain_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPChain_W] = List(SelSpawnEnd,RcvFChain)
  override type implT = __HdlImp
  override type implNextT = __SelSpawnEndImp
override def toString() : String = {"EPChain_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait SelSpawnEnd extends EPChain_W with event_lang.dsl.ChannelTypeBrn
protected  object SelSpawnEnd extends SelSpawnEnd {
  override protected def __children: List[EPChain_W] = List(RcvSpawn,RcvEnd)
  override type implT = __SelSpawnEndImp
  override type implNextT = __RcvSpawnImp
override def toString() : String = {"EPChain_W.SelSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelSpawnEndImp(c,session)}

protected case class __SelSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvSpawn extends EPChain_W with event_lang.dsl.ChannelTypeRcv
  object RcvSpawn extends RcvSpawn {
  override protected def __children: List[EPChain_W] = List(SpawnChain)
  override type implT = __RcvSpawnImp
  override type implNextT = __SpawnChainImp
override def toString() : String = {"EPChain_W.RcvSpawn"}
  override type msgT = MESSAGES.Chain.Spawn
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "Spawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvSpawnImp(c,session)}

protected case class __RcvSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvSpawn
}
  def rcvFrma_A : (MESSAGES.Chain.Spawn,__SpawnChainImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn],__SpawnChainImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.Spawn,__SpawnChainImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn],__SpawnChainImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.Spawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn]}
def ? : MESSAGES.Chain.Spawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn]}
def channelCon : __SpawnChainImp = {__SpawnChainImp(c,session)}

}


  trait SpawnChain extends EPChain_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnChain extends SpawnChain {
  override protected def __children: List[EPChain_W] = List(End_W_ChainSpawn)
  override type implT = __SpawnChainImp
  override type implNextT = __End_W_ChainSpawnImp
override def toString() : String = {"EPChain_W.SpawnChain"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wII",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Chain" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnChainImp(c,session)}

protected case class __SpawnChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnChain
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_W_ChainSpawn extends EPChain_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_ChainSpawn extends End_W_ChainSpawn {
  override protected def __children: List[EPChain_W] = List()
  override type implT = __End_W_ChainSpawnImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_W.End_W_ChainSpawn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_ChainSpawnImp(c,session)}

protected case class __End_W_ChainSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_ChainSpawn
}
  
}



  trait RcvEnd extends EPChain_W with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPChain_W] = List(End_W_ChainEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_W_ChainEndImp
override def toString() : String = {"EPChain_W.RcvEnd"}
  override type msgT = MESSAGES.Chain.End
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrma_A : (MESSAGES.Chain.End,__End_W_ChainEndImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End],__End_W_ChainEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.End,__End_W_ChainEndImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End],__End_W_ChainEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End]}
def ? : MESSAGES.Chain.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End]}
def channelCon : __End_W_ChainEndImp = {__End_W_ChainEndImp(c,session)}

}


protected  trait End_W_ChainEnd extends EPChain_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_ChainEnd extends End_W_ChainEnd {
  override protected def __children: List[EPChain_W] = List()
  override type implT = __End_W_ChainEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_W.End_W_ChainEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_ChainEndImp(c,session)}

protected case class __End_W_ChainEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_ChainEnd
}
  
}



  trait RcvFChain extends EPChain_W with event_lang.dsl.ChannelTypeRcv
  object RcvFChain extends RcvFChain {
  override protected def __children: List[EPChain_W] = List(End_W_ChainFHandling)
  override type implT = __RcvFChainImp
  override type implNextT = __End_W_ChainFHandlingImp
override def toString() : String = {"EPChain_W.RcvFChain"}
  override type msgT = MESSAGES.Chain.FChain
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FChain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFChainImp(c,session)}

protected case class __RcvFChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFChain
}
  def rcvFrma_A : (MESSAGES.Chain.FChain,__End_W_ChainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain],__End_W_ChainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.FChain,__End_W_ChainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain],__End_W_ChainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.FChain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain]}
def ? : MESSAGES.Chain.FChain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain]}
def channelCon : __End_W_ChainFHandlingImp = {__End_W_ChainFHandlingImp(c,session)}

}


protected  trait End_W_ChainFHandling extends EPChain_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_ChainFHandling extends End_W_ChainFHandling {
  override protected def __children: List[EPChain_W] = List()
  override type implT = __End_W_ChainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_W.End_W_ChainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_ChainFHandlingImp(c,session)}

protected case class __End_W_ChainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_ChainFHandling
}
  
}


}

object Chain_wI_W{
trait EPChain_wI_W extends __EPType_W

object EPChain_wI_W extends EPChain_wI_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPChain_wI_W] = List(Hdl)
  override type implT = __EPChain_wI_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPChain_wI_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wI",RoleSet("W"))) 
  override def argsP: Role = Role("wII",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("wI",RoleSet("W")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Chain"
}

protected case class __EPChain_wI_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPChain_wI_W
}
  
}


protected  trait Hdl extends EPChain_wI_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPChain_wI_W] = List(SndMI,RcvFChain)
  override type implT = __HdlImp
  override type implNextT = __SndMIImp
override def toString() : String = {"EPChain_wI_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndMI extends EPChain_wI_W with event_lang.dsl.ChannelTypeSnd
  object SndMI extends SndMI {
  override protected def __children: List[EPChain_wI_W] = List(SelSpawnEnd)
  override type implT = __SndMIImp
  override type implNextT = __SelSpawnEndImp
override def toString() : String = {"EPChain_wI_W.SndMI"}
    override def to : RRole = Role("wII",RoleSet("W")) 
   override def l : String = "MI" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMIImp(c,session)}

protected case class __SndMIImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndMI
}
  private var notUsed = true
def sndTowII_W(m : MESSAGES.Chain.MI) : __SelSpawnEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wII",RoleSet("W")),m)
__SelSpawnEndImp(c,session)}
def !(m : MESSAGES.Chain.MI) : __SelSpawnEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wII",RoleSet("W")),m)
__SelSpawnEndImp(c,session)}
def snd(m : MESSAGES.Chain.MI) : __SelSpawnEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("wII",RoleSet("W")),m)
__SelSpawnEndImp(c,session)}

}


protected  trait SelSpawnEnd extends EPChain_wI_W with event_lang.dsl.ChannelTypeBrn
protected  object SelSpawnEnd extends SelSpawnEnd {
  override protected def __children: List[EPChain_wI_W] = List(RcvSpawn,RcvEnd)
  override type implT = __SelSpawnEndImp
  override type implNextT = __RcvSpawnImp
override def toString() : String = {"EPChain_wI_W.SelSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelSpawnEndImp(c,session)}

protected case class __SelSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvSpawn extends EPChain_wI_W with event_lang.dsl.ChannelTypeRcv
  object RcvSpawn extends RcvSpawn {
  override protected def __children: List[EPChain_wI_W] = List(SpawnChain)
  override type implT = __RcvSpawnImp
  override type implNextT = __SpawnChainImp
override def toString() : String = {"EPChain_wI_W.RcvSpawn"}
  override type msgT = MESSAGES.Chain.Spawn
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "Spawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvSpawnImp(c,session)}

protected case class __RcvSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvSpawn
}
  def rcvFrma_A : (MESSAGES.Chain.Spawn,__SpawnChainImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn],__SpawnChainImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.Spawn,__SpawnChainImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn],__SpawnChainImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.Spawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn]}
def ? : MESSAGES.Chain.Spawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn]}
def channelCon : __SpawnChainImp = {__SpawnChainImp(c,session)}

}


  trait SpawnChain extends EPChain_wI_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnChain extends SpawnChain {
  override protected def __children: List[EPChain_wI_W] = List(End_wI_W_ChainSpawn)
  override type implT = __SpawnChainImp
  override type implNextT = __End_wI_W_ChainSpawnImp
override def toString() : String = {"EPChain_wI_W.SpawnChain"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wII",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Chain" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnChainImp(c,session)}

protected case class __SpawnChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnChain
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_wI_W_ChainSpawn extends EPChain_wI_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wI_W_ChainSpawn extends End_wI_W_ChainSpawn {
  override protected def __children: List[EPChain_wI_W] = List()
  override type implT = __End_wI_W_ChainSpawnImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_wI_W.End_wI_W_ChainSpawn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wI_W_ChainSpawnImp(c,session)}

protected case class __End_wI_W_ChainSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wI_W_ChainSpawn
}
  
}



  trait RcvEnd extends EPChain_wI_W with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPChain_wI_W] = List(End_wI_W_ChainEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_wI_W_ChainEndImp
override def toString() : String = {"EPChain_wI_W.RcvEnd"}
  override type msgT = MESSAGES.Chain.End
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrma_A : (MESSAGES.Chain.End,__End_wI_W_ChainEndImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End],__End_wI_W_ChainEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.End,__End_wI_W_ChainEndImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End],__End_wI_W_ChainEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End]}
def ? : MESSAGES.Chain.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End]}
def channelCon : __End_wI_W_ChainEndImp = {__End_wI_W_ChainEndImp(c,session)}

}


protected  trait End_wI_W_ChainEnd extends EPChain_wI_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wI_W_ChainEnd extends End_wI_W_ChainEnd {
  override protected def __children: List[EPChain_wI_W] = List()
  override type implT = __End_wI_W_ChainEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_wI_W.End_wI_W_ChainEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wI_W_ChainEndImp(c,session)}

protected case class __End_wI_W_ChainEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wI_W_ChainEnd
}
  
}



  trait RcvFChain extends EPChain_wI_W with event_lang.dsl.ChannelTypeRcv
  object RcvFChain extends RcvFChain {
  override protected def __children: List[EPChain_wI_W] = List(End_wI_W_ChainFHandling)
  override type implT = __RcvFChainImp
  override type implNextT = __End_wI_W_ChainFHandlingImp
override def toString() : String = {"EPChain_wI_W.RcvFChain"}
  override type msgT = MESSAGES.Chain.FChain
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FChain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFChainImp(c,session)}

protected case class __RcvFChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFChain
}
  def rcvFrma_A : (MESSAGES.Chain.FChain,__End_wI_W_ChainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain],__End_wI_W_ChainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.FChain,__End_wI_W_ChainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain],__End_wI_W_ChainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.FChain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain]}
def ? : MESSAGES.Chain.FChain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain]}
def channelCon : __End_wI_W_ChainFHandlingImp = {__End_wI_W_ChainFHandlingImp(c,session)}

}


protected  trait End_wI_W_ChainFHandling extends EPChain_wI_W with event_lang.dsl.ChannelTypeEnd
protected  object End_wI_W_ChainFHandling extends End_wI_W_ChainFHandling {
  override protected def __children: List[EPChain_wI_W] = List()
  override type implT = __End_wI_W_ChainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_wI_W.End_wI_W_ChainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_wI_W_ChainFHandlingImp(c,session)}

protected case class __End_wI_W_ChainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_wI_W_ChainFHandling
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
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("w0",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = RoleSet("W") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Main"
}

protected case class __EPMain_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_W
}
  
}


protected  trait Hdl extends EPMain_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_W] = List(SpawnPick,RcvFMain)
  override type implT = __HdlImp
  override type implNextT = __SpawnPickImp
override def toString() : String = {"EPMain_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnPick extends EPMain_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnPick extends SpawnPick {
  override protected def __children: List[EPMain_W] = List(End_W_Main)
  override type implT = __SpawnPickImp
  override type implNextT = __End_W_MainImp
override def toString() : String = {"EPMain_W.SpawnPick"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Pick" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPickImp(c,session)}

protected case class __SpawnPickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPick
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_W_Main extends EPMain_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_Main extends End_W_Main {
  override protected def __children: List[EPMain_W] = List()
  override type implT = __End_W_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_W.End_W_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_MainImp(c,session)}

protected case class __End_W_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_Main
}
  
}



  trait RcvFMain extends EPMain_W with event_lang.dsl.ChannelTypeRcv
  object RcvFMain extends RcvFMain {
  override protected def __children: List[EPMain_W] = List(End_W_MainFHandling)
  override type implT = __RcvFMainImp
  override type implNextT = __End_W_MainFHandlingImp
override def toString() : String = {"EPMain_W.RcvFMain"}
  override type msgT = MESSAGES.Main.FMain
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FMain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFMainImp(c,session)}

protected case class __RcvFMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFMain
}
  def rcvFrma_A : (MESSAGES.Main.FMain,__End_W_MainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMain],__End_W_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FMain,__End_W_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMain],__End_W_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FMain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMain]}
def ? : MESSAGES.Main.FMain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMain]}
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

object Chain_w0_W{
trait EPChain_w0_W extends __EPType_W

object EPChain_w0_W extends EPChain_w0_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPChain_w0_W] = List(Hdl)
  override type implT = __EPChain_w0_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPChain_w0_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wI",RoleSet("W"))) 
  override def argsP: Role = Role("wII",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("w0",RoleSet("W")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Chain"
}

protected case class __EPChain_w0_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPChain_w0_W
}
  
}


protected  trait Hdl extends EPChain_w0_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPChain_w0_W] = List(SelSpawnEnd,RcvFChain)
  override type implT = __HdlImp
  override type implNextT = __SelSpawnEndImp
override def toString() : String = {"EPChain_w0_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait SelSpawnEnd extends EPChain_w0_W with event_lang.dsl.ChannelTypeBrn
protected  object SelSpawnEnd extends SelSpawnEnd {
  override protected def __children: List[EPChain_w0_W] = List(RcvSpawn,RcvEnd)
  override type implT = __SelSpawnEndImp
  override type implNextT = __RcvSpawnImp
override def toString() : String = {"EPChain_w0_W.SelSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelSpawnEndImp(c,session)}

protected case class __SelSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvSpawn extends EPChain_w0_W with event_lang.dsl.ChannelTypeRcv
  object RcvSpawn extends RcvSpawn {
  override protected def __children: List[EPChain_w0_W] = List(SpawnChain)
  override type implT = __RcvSpawnImp
  override type implNextT = __SpawnChainImp
override def toString() : String = {"EPChain_w0_W.RcvSpawn"}
  override type msgT = MESSAGES.Chain.Spawn
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "Spawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvSpawnImp(c,session)}

protected case class __RcvSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvSpawn
}
  def rcvFrma_A : (MESSAGES.Chain.Spawn,__SpawnChainImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn],__SpawnChainImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.Spawn,__SpawnChainImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn],__SpawnChainImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.Spawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn]}
def ? : MESSAGES.Chain.Spawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.Spawn]}
def channelCon : __SpawnChainImp = {__SpawnChainImp(c,session)}

}


  trait SpawnChain extends EPChain_w0_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnChain extends SpawnChain {
  override protected def __children: List[EPChain_w0_W] = List(End_w0_W_ChainSpawn)
  override type implT = __SpawnChainImp
  override type implNextT = __End_w0_W_ChainSpawnImp
override def toString() : String = {"EPChain_w0_W.SpawnChain"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wII",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Chain" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnChainImp(c,session)}

protected case class __SpawnChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnChain
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_w0_W_ChainSpawn extends EPChain_w0_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w0_W_ChainSpawn extends End_w0_W_ChainSpawn {
  override protected def __children: List[EPChain_w0_W] = List()
  override type implT = __End_w0_W_ChainSpawnImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_w0_W.End_w0_W_ChainSpawn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w0_W_ChainSpawnImp(c,session)}

protected case class __End_w0_W_ChainSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w0_W_ChainSpawn
}
  
}



  trait RcvEnd extends EPChain_w0_W with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPChain_w0_W] = List(RcvMLast)
  override type implT = __RcvEndImp
  override type implNextT = __RcvMLastImp
override def toString() : String = {"EPChain_w0_W.RcvEnd"}
  override type msgT = MESSAGES.Chain.End
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrma_A : (MESSAGES.Chain.End,__RcvMLastImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End],__RcvMLastImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.End,__RcvMLastImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End],__RcvMLastImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End]}
def ? : MESSAGES.Chain.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.End]}
def channelCon : __RcvMLastImp = {__RcvMLastImp(c,session)}

}


  trait RcvMLast extends EPChain_w0_W with event_lang.dsl.ChannelTypeRcv
  object RcvMLast extends RcvMLast {
  override protected def __children: List[EPChain_w0_W] = List(End_w0_W_ChainEnd)
  override type implT = __RcvMLastImp
  override type implNextT = __End_w0_W_ChainEndImp
override def toString() : String = {"EPChain_w0_W.RcvMLast"}
  override type msgT = MESSAGES.Chain.MLast
   override def frm : Role = Role("wII",RoleSet("W")) 
   override def l : String = "MLast"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMLastImp(c,session)}

protected case class __RcvMLastImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMLast
}
  def rcvFrmwII_W : (MESSAGES.Chain.MLast,__End_w0_W_ChainEndImp) = {(c.rcv(Role("wII",RoleSet("W"))).asInstanceOf[MESSAGES.Chain.MLast],__End_w0_W_ChainEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.MLast,__End_w0_W_ChainEndImp),T]) : T = {
  f((c.rcv(Role("wII",RoleSet("W"))).asInstanceOf[MESSAGES.Chain.MLast],__End_w0_W_ChainEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.MLast = {c.rcv(Role("wII",RoleSet("W"))).asInstanceOf[MESSAGES.Chain.MLast]}
def ? : MESSAGES.Chain.MLast = {c.rcv(Role("wII",RoleSet("W"))).asInstanceOf[MESSAGES.Chain.MLast]}
def channelCon : __End_w0_W_ChainEndImp = {__End_w0_W_ChainEndImp(c,session)}

}


protected  trait End_w0_W_ChainEnd extends EPChain_w0_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w0_W_ChainEnd extends End_w0_W_ChainEnd {
  override protected def __children: List[EPChain_w0_W] = List()
  override type implT = __End_w0_W_ChainEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_w0_W.End_w0_W_ChainEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w0_W_ChainEndImp(c,session)}

protected case class __End_w0_W_ChainEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w0_W_ChainEnd
}
  
}



  trait RcvFChain extends EPChain_w0_W with event_lang.dsl.ChannelTypeRcv
  object RcvFChain extends RcvFChain {
  override protected def __children: List[EPChain_w0_W] = List(End_w0_W_ChainFHandling)
  override type implT = __RcvFChainImp
  override type implNextT = __End_w0_W_ChainFHandlingImp
override def toString() : String = {"EPChain_w0_W.RcvFChain"}
  override type msgT = MESSAGES.Chain.FChain
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FChain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFChainImp(c,session)}

protected case class __RcvFChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFChain
}
  def rcvFrma_A : (MESSAGES.Chain.FChain,__End_w0_W_ChainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain],__End_w0_W_ChainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Chain.FChain,__End_w0_W_ChainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain],__End_w0_W_ChainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Chain.FChain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain]}
def ? : MESSAGES.Chain.FChain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Chain.FChain]}
def channelCon : __End_w0_W_ChainFHandlingImp = {__End_w0_W_ChainFHandlingImp(c,session)}

}


protected  trait End_w0_W_ChainFHandling extends EPChain_w0_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w0_W_ChainFHandling extends End_w0_W_ChainFHandling {
  override protected def __children: List[EPChain_w0_W] = List()
  override type implT = __End_w0_W_ChainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_w0_W.End_w0_W_ChainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w0_W_ChainFHandlingImp(c,session)}

protected case class __End_w0_W_ChainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w0_W_ChainFHandling
}
  
}


}

object Pick_w1_W{
trait EPPick_w1_W extends __EPType_W

object EPPick_w1_W extends EPPick_w1_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPick_w1_W] = List(Hdl)
  override type implT = __EPPick_w1_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPick_w1_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W"))) 
  override def argsP: Role = Role("w1",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("w1",RoleSet("W")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Pick"
}

protected case class __EPPick_w1_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPick_w1_W
}
  
}


protected  trait Hdl extends EPPick_w1_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPick_w1_W] = List(RcvM1,End_w1_W_PickFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvM1Imp
override def toString() : String = {"EPPick_w1_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvM1 extends EPPick_w1_W with event_lang.dsl.ChannelTypeRcv
  object RcvM1 extends RcvM1 {
  override protected def __children: List[EPPick_w1_W] = List(SpawnChain)
  override type implT = __RcvM1Imp
  override type implNextT = __SpawnChainImp
override def toString() : String = {"EPPick_w1_W.RcvM1"}
  override type msgT = MESSAGES.Pick.M1
   override def frm : Role = Role("w0",RoleSet("W")) 
   override def l : String = "M1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvM1Imp(c,session)}

protected case class __RcvM1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvM1
}
  def rcvFrmw0_W : (MESSAGES.Pick.M1,__SpawnChainImp) = {(c.rcv(Role("w0",RoleSet("W"))).asInstanceOf[MESSAGES.Pick.M1],__SpawnChainImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pick.M1,__SpawnChainImp),T]) : T = {
  f((c.rcv(Role("w0",RoleSet("W"))).asInstanceOf[MESSAGES.Pick.M1],__SpawnChainImp(c,session))) 
}
def rcvMSG : MESSAGES.Pick.M1 = {c.rcv(Role("w0",RoleSet("W"))).asInstanceOf[MESSAGES.Pick.M1]}
def ? : MESSAGES.Pick.M1 = {c.rcv(Role("w0",RoleSet("W"))).asInstanceOf[MESSAGES.Pick.M1]}
def channelCon : __SpawnChainImp = {__SpawnChainImp(c,session)}

}


  trait SpawnChain extends EPPick_w1_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnChain extends SpawnChain {
  override protected def __children: List[EPPick_w1_W] = List(End_w1_W_Pick)
  override type implT = __SpawnChainImp
  override type implNextT = __End_w1_W_PickImp
override def toString() : String = {"EPPick_w1_W.SpawnChain"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("w1",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Chain" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnChainImp(c,session)}

protected case class __SpawnChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnChain
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_w1_W_Pick extends EPPick_w1_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w1_W_Pick extends End_w1_W_Pick {
  override protected def __children: List[EPPick_w1_W] = List()
  override type implT = __End_w1_W_PickImp
  override type implNextT = Nothing
override def toString() : String = {"EPPick_w1_W.End_w1_W_Pick"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w1_W_PickImp(c,session)}

protected case class __End_w1_W_PickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w1_W_Pick
}
  
}



protected  trait End_w1_W_PickFHandling extends EPPick_w1_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w1_W_PickFHandling extends End_w1_W_PickFHandling {
  override protected def __children: List[EPPick_w1_W] = List()
  override type implT = __End_w1_W_PickFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPick_w1_W.End_w1_W_PickFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w1_W_PickFHandlingImp(c,session)}

protected case class __End_w1_W_PickFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w1_W_PickFHandling
}
  
}


}

}

object A {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_a_A.EPMain_a_A,Pick_a_A.EPPick_a_A,Chain_a_A.EPChain_a_A)
trait __EPType_A extends AbstractChannelType {

}

trait EPType_A[T<: TState] extends AbstractEndPoint[__EPType_A,T] {
override val roleSet: RoleSet = RoleSet("A")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_a_A.EPMain_a_A,Pick_a_A.EPPick_a_A,Chain_a_A.EPChain_a_A)

}

object Main_a_A{
trait EPMain_a_A extends __EPType_A

object EPMain_a_A extends EPMain_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_a_A] = List(Hdl)
  override type implT = __EPMain_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("w0",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Main"
}

protected case class __EPMain_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_a_A
}
  
}


protected  trait Hdl extends EPMain_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_a_A] = List(SpawnPick,Failed_w0_W)
  override type implT = __HdlImp
  override type implNextT = __SpawnPickImp
override def toString() : String = {"EPMain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnPick extends EPMain_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnPick extends SpawnPick {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_Main)
  override type implT = __SpawnPickImp
  override type implNextT = __End_a_A_MainImp
override def toString() : String = {"EPMain_a_A.SpawnPick"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Pick" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPickImp(c,session)}

protected case class __SpawnPickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPick
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_a_A_Main extends EPMain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_Main extends End_a_A_Main {
  override protected def __children: List[EPMain_a_A] = List()
  override type implT = __End_a_A_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_a_A.End_a_A_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_MainImp(c,session)}

protected case class __End_a_A_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_Main
}
  
}



  trait Failed_w0_W extends EPMain_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_w0_W extends Failed_w0_W {
  override protected def __children: List[EPMain_a_A] = List(SndFMain)
  override type implT = __Failed_w0_WImp
  override type implNextT = __SndFMainImp
override def toString() : String = {"EPMain_a_A.Failed_w0_W"}
  override def suspect : Role = Role("w0",RoleSet("W")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_w0_WImp(c,session)}

protected case class __Failed_w0_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_w0_W
}
  def failed_w0_W(): __SndFMainImp = {__SndFMainImp(c,session)}

}


  trait SndFMain extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFMain extends SndFMain {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainFHandling)
  override type implT = __SndFMainImp
  override type implNextT = __End_a_A_MainFHandlingImp
override def toString() : String = {"EPMain_a_A.SndFMain"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "FMain" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFMainImp(c,session)}

protected case class __SndFMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFMain
}
  private var notUsed = true
def sndToW(m : MESSAGES.Main.FMain) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.FMain) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.FMain) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_MainFHandlingImp(c,session)}

}


protected  trait End_a_A_MainFHandling extends EPMain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_MainFHandling extends End_a_A_MainFHandling {
  override protected def __children: List[EPMain_a_A] = List()
  override type implT = __End_a_A_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_a_A.End_a_A_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_MainFHandlingImp(c,session)}

protected case class __End_a_A_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_MainFHandling
}
  
}


}

object Pick_a_A{
trait EPPick_a_A extends __EPType_A

object EPPick_a_A extends EPPick_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPick_a_A] = List(Hdl)
  override type implT = __EPPick_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPick_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W"))) 
  override def argsP: Role = Role("w1",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Pick"
}

protected case class __EPPick_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPick_a_A
}
  
}


protected  trait Hdl extends EPPick_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPick_a_A] = List(SpawnChain,Failed_w1_W)
  override type implT = __HdlImp
  override type implNextT = __SpawnChainImp
override def toString() : String = {"EPPick_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnChain extends EPPick_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnChain extends SpawnChain {
  override protected def __children: List[EPPick_a_A] = List(End_a_A_Pick)
  override type implT = __SpawnChainImp
  override type implNextT = __End_a_A_PickImp
override def toString() : String = {"EPPick_a_A.SpawnChain"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("w1",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Chain" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnChainImp(c,session)}

protected case class __SpawnChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnChain
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_a_A_Pick extends EPPick_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_Pick extends End_a_A_Pick {
  override protected def __children: List[EPPick_a_A] = List()
  override type implT = __End_a_A_PickImp
  override type implNextT = Nothing
override def toString() : String = {"EPPick_a_A.End_a_A_Pick"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_PickImp(c,session)}

protected case class __End_a_A_PickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_Pick
}
  
}



  trait Failed_w1_W extends EPPick_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_w1_W extends Failed_w1_W {
  override protected def __children: List[EPPick_a_A] = List(SndFPick)
  override type implT = __Failed_w1_WImp
  override type implNextT = __SndFPickImp
override def toString() : String = {"EPPick_a_A.Failed_w1_W"}
  override def suspect : Role = Role("w1",RoleSet("W")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_w1_WImp(c,session)}

protected case class __Failed_w1_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_w1_W
}
  def failed_w1_W(): __SndFPickImp = {__SndFPickImp(c,session)}

}


  trait SndFPick extends EPPick_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFPick extends SndFPick {
  override protected def __children: List[EPPick_a_A] = List(End_a_A_PickFHandling)
  override type implT = __SndFPickImp
  override type implNextT = __End_a_A_PickFHandlingImp
override def toString() : String = {"EPPick_a_A.SndFPick"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "FPick" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFPickImp(c,session)}

protected case class __SndFPickImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFPick
}
  private var notUsed = true
def sndToW(m : MESSAGES.Pick.FPick) : __End_a_A_PickFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_PickFHandlingImp(c,session)}
def !(m : MESSAGES.Pick.FPick) : __End_a_A_PickFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_PickFHandlingImp(c,session)}
def snd(m : MESSAGES.Pick.FPick) : __End_a_A_PickFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_PickFHandlingImp(c,session)}

}


protected  trait End_a_A_PickFHandling extends EPPick_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_PickFHandling extends End_a_A_PickFHandling {
  override protected def __children: List[EPPick_a_A] = List()
  override type implT = __End_a_A_PickFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPick_a_A.End_a_A_PickFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_PickFHandlingImp(c,session)}

protected case class __End_a_A_PickFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_PickFHandling
}
  
}


}

object Chain_a_A{
trait EPChain_a_A extends __EPType_A

object EPChain_a_A extends EPChain_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPChain_a_A] = List(Hdl)
  override type implT = __EPChain_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPChain_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wI",RoleSet("W"))) 
  override def argsP: Role = Role("wII",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Chain"
}

protected case class __EPChain_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPChain_a_A
}
  
}


protected  trait Hdl extends EPChain_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPChain_a_A] = List(SelSpawnEnd,Failed_wII_W)
  override type implT = __HdlImp
  override type implNextT = __SelSpawnEndImp
override def toString() : String = {"EPChain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait SelSpawnEnd extends EPChain_a_A with event_lang.dsl.ChannelTypeSel
protected  object SelSpawnEnd extends SelSpawnEnd {
  override protected def __children: List[EPChain_a_A] = List(SndSpawn,SndEnd)
  override type implT = __SelSpawnEndImp
  override type implNextT = __SndSpawnImp
override def toString() : String = {"EPChain_a_A.SelSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelSpawnEndImp(c,session)}

protected case class __SelSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelSpawnEnd
}
  private var notUsed = true
def !(m : MESSAGES.Chain.Spawn) : __SpawnChainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __SpawnChainImp(c,session)}
def sndToW(m : MESSAGES.Chain.Spawn) : __SpawnChainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __SpawnChainImp(c,session)}

def !(m : MESSAGES.Chain.End) : __End_a_A_ChainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __End_a_A_ChainEndImp(c,session)}
def sndToW(m : MESSAGES.Chain.End) : __End_a_A_ChainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
 __End_a_A_ChainEndImp(c,session)}

}


  trait SndSpawn extends EPChain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndSpawn extends SndSpawn {
  override protected def __children: List[EPChain_a_A] = List(SpawnChain)
  override type implT = __SndSpawnImp
  override type implNextT = __SpawnChainImp
override def toString() : String = {"EPChain_a_A.SndSpawn"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "Spawn" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndSpawnImp(c,session)}

protected case class __SndSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndSpawn
}
  private var notUsed = true
def sndToW(m : MESSAGES.Chain.Spawn) : __SpawnChainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnChainImp(c,session)}
def !(m : MESSAGES.Chain.Spawn) : __SpawnChainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnChainImp(c,session)}
def snd(m : MESSAGES.Chain.Spawn) : __SpawnChainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnChainImp(c,session)}

}


  trait SpawnChain extends EPChain_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnChain extends SpawnChain {
  override protected def __children: List[EPChain_a_A] = List(End_a_A_ChainSpawn)
  override type implT = __SpawnChainImp
  override type implNextT = __End_a_A_ChainSpawnImp
override def toString() : String = {"EPChain_a_A.SpawnChain"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("w0",RoleSet("W")), Role("wII",RoleSet("W"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Chain" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnChainImp(c,session)}

protected case class __SpawnChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnChain
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_a_A_ChainSpawn extends EPChain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_ChainSpawn extends End_a_A_ChainSpawn {
  override protected def __children: List[EPChain_a_A] = List()
  override type implT = __End_a_A_ChainSpawnImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_a_A.End_a_A_ChainSpawn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_ChainSpawnImp(c,session)}

protected case class __End_a_A_ChainSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_ChainSpawn
}
  
}



  trait SndEnd extends EPChain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndEnd extends SndEnd {
  override protected def __children: List[EPChain_a_A] = List(End_a_A_ChainEnd)
  override type implT = __SndEndImp
  override type implNextT = __End_a_A_ChainEndImp
override def toString() : String = {"EPChain_a_A.SndEnd"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "End" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndImp(c,session)}

protected case class __SndEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEnd
}
  private var notUsed = true
def sndToW(m : MESSAGES.Chain.End) : __End_a_A_ChainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_ChainEndImp(c,session)}
def !(m : MESSAGES.Chain.End) : __End_a_A_ChainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_ChainEndImp(c,session)}
def snd(m : MESSAGES.Chain.End) : __End_a_A_ChainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_ChainEndImp(c,session)}

}


protected  trait End_a_A_ChainEnd extends EPChain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_ChainEnd extends End_a_A_ChainEnd {
  override protected def __children: List[EPChain_a_A] = List()
  override type implT = __End_a_A_ChainEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_a_A.End_a_A_ChainEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_ChainEndImp(c,session)}

protected case class __End_a_A_ChainEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_ChainEnd
}
  
}



  trait Failed_wII_W extends EPChain_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_wII_W extends Failed_wII_W {
  override protected def __children: List[EPChain_a_A] = List(SndFChain)
  override type implT = __Failed_wII_WImp
  override type implNextT = __SndFChainImp
override def toString() : String = {"EPChain_a_A.Failed_wII_W"}
  override def suspect : Role = Role("wII",RoleSet("W")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_wII_WImp(c,session)}

protected case class __Failed_wII_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_wII_W
}
  def failed_wII_W(): __SndFChainImp = {__SndFChainImp(c,session)}

}


  trait SndFChain extends EPChain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFChain extends SndFChain {
  override protected def __children: List[EPChain_a_A] = List(End_a_A_ChainFHandling)
  override type implT = __SndFChainImp
  override type implNextT = __End_a_A_ChainFHandlingImp
override def toString() : String = {"EPChain_a_A.SndFChain"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "FChain" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFChainImp(c,session)}

protected case class __SndFChainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFChain
}
  private var notUsed = true
def sndToW(m : MESSAGES.Chain.FChain) : __End_a_A_ChainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_ChainFHandlingImp(c,session)}
def !(m : MESSAGES.Chain.FChain) : __End_a_A_ChainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_ChainFHandlingImp(c,session)}
def snd(m : MESSAGES.Chain.FChain) : __End_a_A_ChainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_a_A_ChainFHandlingImp(c,session)}

}


protected  trait End_a_A_ChainFHandling extends EPChain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_ChainFHandling extends End_a_A_ChainFHandling {
  override protected def __children: List[EPChain_a_A] = List()
  override type implT = __End_a_A_ChainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPChain_a_A.End_a_A_ChainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_ChainFHandlingImp(c,session)}

protected case class __End_a_A_ChainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_ChainFHandling
}
  
}


}

}

}
