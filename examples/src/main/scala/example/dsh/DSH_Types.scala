package example.dsh
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object DSH {
object RS {
val C : RoleSet = RoleSet("C")
val P : RoleSet = RoleSet("P")
val R : RoleSet = RoleSet("R")
}

object MESSAGES {
object SH {
case class BothOut() extends MSG {
   override def l : String = "BothOut"
}

case class ClosePC() extends MSG {
   override def l : String = "ClosePC"
}

case class BothInR() extends MSG {
   override def l : String = "BothInR"
}

case class IsAbove(x:Int,y:Int) extends MSG {
   override def l : String = "IsAbove"
}

case class ResSec(b:Boolean) extends MSG {
   override def l : String = "ResSec"
}

case class FM() extends MSG {
   override def l : String = "FM"
}

case class Intrsct(x1:Int,y1:Int,x2:Int,y2:Int) extends MSG {
   override def l : String = "Intrsct"
}

case class SecOut(x:Int,y:Int) extends MSG {
   override def l : String = "SecOut"
}

case class BothOutTwo() extends MSG {
   override def l : String = "BothOutTwo"
}

case class ClosePR() extends MSG {
   override def l : String = "ClosePR"
}

case class BothInC(x2:Int,y2:Int) extends MSG {
   override def l : String = "BothInC"
}

case class Res(b:Boolean) extends MSG {
   override def l : String = "Res"
}

case class SecIn(x1:Int,y1:Int,x2:Int,y2:Int) extends MSG {
   override def l : String = "SecIn"
}

case class IsAboveSec(x:Int,y:Int) extends MSG {
   override def l : String = "IsAboveSec"
}

case class ResIntrsct(x1:Int,y1:Int) extends MSG {
   override def l : String = "ResIntrsct"
}

}

object Main {
case class F() extends MSG {
   override def l : String = "F"
}

}

}

object PROTOCOLS {
object SH {
val c_C = Role("c",RoleSet("C"))
val p_P = Role("p",RoleSet("P"))
val r_R = Role("r",RoleSet("R"))
}

object Main {
val r_R = Role("r",RoleSet("R"))
val C = RoleSet("C")
val p_P = Role("p",RoleSet("P"))
}

}

object C {
val subs : Seq[dsl.ChannelTypeSubS] = List(SH_c_C.EPSH_c_C,Main_C.EPMain_C)
trait __EPType_C extends AbstractChannelType {

}

trait EPType_C[T<: TState] extends AbstractEndPoint[__EPType_C,T] {
override val roleSet: RoleSet = RoleSet("C")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(SH_c_C.EPSH_c_C,Main_C.EPMain_C)

}

object SH_c_C{
trait EPSH_c_C extends __EPType_C

object EPSH_c_C extends EPSH_c_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSH_c_C] = List(Hdl)
  override type implT = __EPSH_c_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSH_c_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("p",RoleSet("P")), Role("r",RoleSet("R"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("c",RoleSet("C")) 
  override def rootRole: Role = Role("p",RoleSet("P")) 
  override def name : String = "SH"
}

protected case class __EPSH_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSH_c_C
}
  
}


protected  trait Hdl extends EPSH_c_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSH_c_C] = List(RecT,End_c_C_SHFHandling)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPSH_c_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPSH_c_C with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPSH_c_C] = List(Merge_BothInC_BothOutTwo_SecOut_ClosePC)
  override type implT = __RecTImp
  override type implNextT = __Merge_BothInC_BothOutTwo_SecOut_ClosePCImp
override def toString() : String = {"EPSH_c_C.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait Merge_BothInC_BothOutTwo_SecOut_ClosePC extends EPSH_c_C with event_lang.dsl.ChannelTypeMerge
protected  object Merge_BothInC_BothOutTwo_SecOut_ClosePC extends Merge_BothInC_BothOutTwo_SecOut_ClosePC {
  override protected def __children: List[EPSH_c_C] = List(RcvBothInC,RcvBothOutTwo,SelSecOutSecIn,RcvClosePC)
  override type implT = __Merge_BothInC_BothOutTwo_SecOut_ClosePCImp
  override type implNextT = __RcvBothInCImp
override def toString() : String = {"EPSH_c_C.Merge_BothInC_BothOutTwo_SecOut_ClosePC"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Merge_BothInC_BothOutTwo_SecOut_ClosePCImp(c,session)}

protected case class __Merge_BothInC_BothOutTwo_SecOut_ClosePCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Merge_BothInC_BothOutTwo_SecOut_ClosePC
}
  
}


  trait RcvBothInC extends EPSH_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvBothInC extends RcvBothInC {
  override protected def __children: List[EPSH_c_C] = List(T)
  override type implT = __RcvBothInCImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_c_C.RcvBothInC"}
  override type msgT = MESSAGES.SH.BothInC
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "BothInC"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBothInCImp(c,session)}

protected case class __RcvBothInCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBothInC
}
  def rcvFrmp_P : (MESSAGES.SH.BothInC,__TImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothInC],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.BothInC,__TImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothInC],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.BothInC = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothInC]}
def ? : MESSAGES.SH.BothInC = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothInC]}
def channelCon : __TImp = {__TImp(c,session)}

}


protected  trait T extends EPSH_c_C with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPSH_c_C] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPSH_c_C.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvBothOutTwo extends EPSH_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvBothOutTwo extends RcvBothOutTwo {
  override protected def __children: List[EPSH_c_C] = List(T)
  override type implT = __RcvBothOutTwoImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_c_C.RcvBothOutTwo"}
  override type msgT = MESSAGES.SH.BothOutTwo
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "BothOutTwo"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBothOutTwoImp(c,session)}

protected case class __RcvBothOutTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBothOutTwo
}
  def rcvFrmp_P : (MESSAGES.SH.BothOutTwo,__TImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothOutTwo],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.BothOutTwo,__TImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothOutTwo],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.BothOutTwo = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothOutTwo]}
def ? : MESSAGES.SH.BothOutTwo = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothOutTwo]}
def channelCon : __TImp = {__TImp(c,session)}

}

//there was an occurens of t already

protected  trait SelSecOutSecIn extends EPSH_c_C with event_lang.dsl.ChannelTypeBrn
protected  object SelSecOutSecIn extends SelSecOutSecIn {
  override protected def __children: List[EPSH_c_C] = List(RcvSecOut,RcvSecIn)
  override type implT = __SelSecOutSecInImp
  override type implNextT = __RcvSecOutImp
override def toString() : String = {"EPSH_c_C.SelSecOutSecIn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelSecOutSecInImp(c,session)}

protected case class __SelSecOutSecInImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelSecOutSecIn
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvSecOut extends EPSH_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvSecOut extends RcvSecOut {
  override protected def __children: List[EPSH_c_C] = List(T)
  override type implT = __RcvSecOutImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_c_C.RcvSecOut"}
  override type msgT = MESSAGES.SH.SecOut
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "SecOut"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvSecOutImp(c,session)}

protected case class __RcvSecOutImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvSecOut
}
  def rcvFrmp_P : (MESSAGES.SH.SecOut,__TImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.SecOut],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.SecOut,__TImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.SecOut],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.SecOut = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.SecOut]}
def ? : MESSAGES.SH.SecOut = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.SecOut]}
def channelCon : __TImp = {__TImp(c,session)}

}

//there was an occurens of t already

  trait RcvSecIn extends EPSH_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvSecIn extends RcvSecIn {
  override protected def __children: List[EPSH_c_C] = List(T)
  override type implT = __RcvSecInImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_c_C.RcvSecIn"}
  override type msgT = MESSAGES.SH.SecIn
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "SecIn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvSecInImp(c,session)}

protected case class __RcvSecInImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvSecIn
}
  def rcvFrmp_P : (MESSAGES.SH.SecIn,__TImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.SecIn],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.SecIn,__TImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.SecIn],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.SecIn = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.SecIn]}
def ? : MESSAGES.SH.SecIn = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.SecIn]}
def channelCon : __TImp = {__TImp(c,session)}

}

//there was an occurens of t already

  trait RcvClosePC extends EPSH_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvClosePC extends RcvClosePC {
  override protected def __children: List[EPSH_c_C] = List(End_c_C_SHClosePC)
  override type implT = __RcvClosePCImp
  override type implNextT = __End_c_C_SHClosePCImp
override def toString() : String = {"EPSH_c_C.RcvClosePC"}
  override type msgT = MESSAGES.SH.ClosePC
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "ClosePC"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvClosePCImp(c,session)}

protected case class __RcvClosePCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvClosePC
}
  def rcvFrmp_P : (MESSAGES.SH.ClosePC,__End_c_C_SHClosePCImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.ClosePC],__End_c_C_SHClosePCImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.ClosePC,__End_c_C_SHClosePCImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.ClosePC],__End_c_C_SHClosePCImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.ClosePC = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.ClosePC]}
def ? : MESSAGES.SH.ClosePC = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.ClosePC]}
def channelCon : __End_c_C_SHClosePCImp = {__End_c_C_SHClosePCImp(c,session)}

}


protected  trait End_c_C_SHClosePC extends EPSH_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_SHClosePC extends End_c_C_SHClosePC {
  override protected def __children: List[EPSH_c_C] = List()
  override type implT = __End_c_C_SHClosePCImp
  override type implNextT = Nothing
override def toString() : String = {"EPSH_c_C.End_c_C_SHClosePC"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_SHClosePCImp(c,session)}

protected case class __End_c_C_SHClosePCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_SHClosePC
}
  
}



protected  trait End_c_C_SHFHandling extends EPSH_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_SHFHandling extends End_c_C_SHFHandling {
  override protected def __children: List[EPSH_c_C] = List()
  override type implT = __End_c_C_SHFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSH_c_C.End_c_C_SHFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_SHFHandlingImp(c,session)}

protected case class __End_c_C_SHFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_SHFHandling
}
  
}


}

object Main_C{
trait EPMain_C extends __EPType_C

object EPMain_C extends EPMain_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_C] = List(Hdl)
  override type implT = __EPMain_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("p",RoleSet("P"))) 
  override def argsP: Role = Role("r",RoleSet("R")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
  override def prjTo : RRole = RoleSet("C") 
  override def rootRole: Role = Role("p",RoleSet("P")) 
  override def name : String = "Main"
}

protected case class __EPMain_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_C
}
  
}


protected  trait Hdl extends EPMain_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_C] = List(SpawnSH,RcvF)
  override type implT = __HdlImp
  override type implNextT = __SpawnSHImp
override def toString() : String = {"EPMain_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSH extends EPMain_C with event_lang.dsl.ChannelTypeSpawn
  object SpawnSH extends SpawnSH {
  override protected def __children: List[EPMain_C] = List(End_C_Main)
  override type implT = __SpawnSHImp
  override type implNextT = __End_C_MainImp
override def toString() : String = {"EPMain_C.SpawnSH"}
    override def y: List[Role] = List(Role("p",RoleSet("P")), Role("r",RoleSet("R"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "SH" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSHImp(c,session)}

protected case class __SpawnSHImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSH
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_C_Main extends EPMain_C with event_lang.dsl.ChannelTypeEnd
protected  object End_C_Main extends End_C_Main {
  override protected def __children: List[EPMain_C] = List()
  override type implT = __End_C_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_C.End_C_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_C_MainImp(c,session)}

protected case class __End_C_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_C_Main
}
  
}



  trait RcvF extends EPMain_C with event_lang.dsl.ChannelTypeRcv
  object RcvF extends RcvF {
  override protected def __children: List[EPMain_C] = List(End_C_MainFHandling)
  override type implT = __RcvFImp
  override type implNextT = __End_C_MainFHandlingImp
override def toString() : String = {"EPMain_C.RcvF"}
  override type msgT = MESSAGES.Main.F
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "F"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFImp(c,session)}

protected case class __RcvFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF
}
  def rcvFrmp_P : (MESSAGES.Main.F,__End_C_MainFHandlingImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.Main.F],__End_C_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.F,__End_C_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.Main.F],__End_C_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.F = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.Main.F]}
def ? : MESSAGES.Main.F = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.Main.F]}
def channelCon : __End_C_MainFHandlingImp = {__End_C_MainFHandlingImp(c,session)}

}


protected  trait End_C_MainFHandling extends EPMain_C with event_lang.dsl.ChannelTypeEnd
protected  object End_C_MainFHandling extends End_C_MainFHandling {
  override protected def __children: List[EPMain_C] = List()
  override type implT = __End_C_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_C.End_C_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_C_MainFHandlingImp(c,session)}

protected case class __End_C_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_C_MainFHandling
}
  
}


}

}

object P {
val subs : Seq[dsl.ChannelTypeSubS] = List(SH_p_P.EPSH_p_P,Main_p_P.EPMain_p_P)
trait __EPType_P extends AbstractChannelType {

}

trait EPType_P[T<: TState] extends AbstractEndPoint[__EPType_P,T] {
override val roleSet: RoleSet = RoleSet("P")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(SH_p_P.EPSH_p_P,Main_p_P.EPMain_p_P)

}

object SH_p_P{
trait EPSH_p_P extends __EPType_P

object EPSH_p_P extends EPSH_p_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSH_p_P] = List(Hdl)
  override type implT = __EPSH_p_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSH_p_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("p",RoleSet("P")), Role("r",RoleSet("R"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("p",RoleSet("P")) 
  override def rootRole: Role = Role("p",RoleSet("P")) 
  override def name : String = "SH"
}

protected case class __EPSH_p_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSH_p_P
}
  
}


protected  trait Hdl extends EPSH_p_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSH_p_P] = List(RecT,Failed_c_C)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPSH_p_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPSH_p_P with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPSH_p_P] = List(SelIsAboveClosePR)
  override type implT = __RecTImp
  override type implNextT = __SelIsAboveClosePRImp
override def toString() : String = {"EPSH_p_P.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelIsAboveClosePR extends EPSH_p_P with event_lang.dsl.ChannelTypeSel
protected  object SelIsAboveClosePR extends SelIsAboveClosePR {
  override protected def __children: List[EPSH_p_P] = List(SndIsAbove,SndClosePR)
  override type implT = __SelIsAboveClosePRImp
  override type implNextT = __SndIsAboveImp
override def toString() : String = {"EPSH_p_P.SelIsAboveClosePR"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelIsAboveClosePRImp(c,session)}

protected case class __SelIsAboveClosePRImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelIsAboveClosePR
}
  private var notUsed = true
def !(m : MESSAGES.SH.IsAbove) : __RcvResImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __RcvResImp(c,session)}
def sndTor_R(m : MESSAGES.SH.IsAbove) : __RcvResImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __RcvResImp(c,session)}

def !(m : MESSAGES.SH.ClosePR) : __SndClosePCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __SndClosePCImp(c,session)}
def sndTor_R(m : MESSAGES.SH.ClosePR) : __SndClosePCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __SndClosePCImp(c,session)}

}


  trait SndIsAbove extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndIsAbove extends SndIsAbove {
  override protected def __children: List[EPSH_p_P] = List(RcvRes)
  override type implT = __SndIsAboveImp
  override type implNextT = __RcvResImp
override def toString() : String = {"EPSH_p_P.SndIsAbove"}
    override def to : RRole = Role("r",RoleSet("R")) 
   override def l : String = "IsAbove" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndIsAboveImp(c,session)}

protected case class __SndIsAboveImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndIsAbove
}
  private var notUsed = true
def sndTor_R(m : MESSAGES.SH.IsAbove) : __RcvResImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__RcvResImp(c,session)}
def !(m : MESSAGES.SH.IsAbove) : __RcvResImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__RcvResImp(c,session)}
def snd(m : MESSAGES.SH.IsAbove) : __RcvResImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__RcvResImp(c,session)}

}


  trait RcvRes extends EPSH_p_P with event_lang.dsl.ChannelTypeRcv
  object RcvRes extends RcvRes {
  override protected def __children: List[EPSH_p_P] = List(SndIsAboveSec)
  override type implT = __RcvResImp
  override type implNextT = __SndIsAboveSecImp
override def toString() : String = {"EPSH_p_P.RcvRes"}
  override type msgT = MESSAGES.SH.Res
   override def frm : Role = Role("r",RoleSet("R")) 
   override def l : String = "Res"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvResImp(c,session)}

protected case class __RcvResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvRes
}
  def rcvFrmr_R : (MESSAGES.SH.Res,__SndIsAboveSecImp) = {(c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.Res],__SndIsAboveSecImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.Res,__SndIsAboveSecImp),T]) : T = {
  f((c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.Res],__SndIsAboveSecImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.Res = {c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.Res]}
def ? : MESSAGES.SH.Res = {c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.Res]}
def channelCon : __SndIsAboveSecImp = {__SndIsAboveSecImp(c,session)}

}


  trait SndIsAboveSec extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndIsAboveSec extends SndIsAboveSec {
  override protected def __children: List[EPSH_p_P] = List(RcvResSec)
  override type implT = __SndIsAboveSecImp
  override type implNextT = __RcvResSecImp
override def toString() : String = {"EPSH_p_P.SndIsAboveSec"}
    override def to : RRole = Role("r",RoleSet("R")) 
   override def l : String = "IsAboveSec" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndIsAboveSecImp(c,session)}

protected case class __SndIsAboveSecImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndIsAboveSec
}
  private var notUsed = true
def sndTor_R(m : MESSAGES.SH.IsAboveSec) : __RcvResSecImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__RcvResSecImp(c,session)}
def !(m : MESSAGES.SH.IsAboveSec) : __RcvResSecImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__RcvResSecImp(c,session)}
def snd(m : MESSAGES.SH.IsAboveSec) : __RcvResSecImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__RcvResSecImp(c,session)}

}


  trait RcvResSec extends EPSH_p_P with event_lang.dsl.ChannelTypeRcv
  object RcvResSec extends RcvResSec {
  override protected def __children: List[EPSH_p_P] = List(SelBothInRBothOutIntrsct)
  override type implT = __RcvResSecImp
  override type implNextT = __SelBothInRBothOutIntrsctImp
override def toString() : String = {"EPSH_p_P.RcvResSec"}
  override type msgT = MESSAGES.SH.ResSec
   override def frm : Role = Role("r",RoleSet("R")) 
   override def l : String = "ResSec"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvResSecImp(c,session)}

protected case class __RcvResSecImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvResSec
}
  def rcvFrmr_R : (MESSAGES.SH.ResSec,__SelBothInRBothOutIntrsctImp) = {(c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.ResSec],__SelBothInRBothOutIntrsctImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.ResSec,__SelBothInRBothOutIntrsctImp),T]) : T = {
  f((c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.ResSec],__SelBothInRBothOutIntrsctImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.ResSec = {c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.ResSec]}
def ? : MESSAGES.SH.ResSec = {c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.ResSec]}
def channelCon : __SelBothInRBothOutIntrsctImp = {__SelBothInRBothOutIntrsctImp(c,session)}

}


protected  trait SelBothInRBothOutIntrsct extends EPSH_p_P with event_lang.dsl.ChannelTypeSel
protected  object SelBothInRBothOutIntrsct extends SelBothInRBothOutIntrsct {
  override protected def __children: List[EPSH_p_P] = List(SndBothInR,SndBothOut,SndIntrsct)
  override type implT = __SelBothInRBothOutIntrsctImp
  override type implNextT = __SndBothInRImp
override def toString() : String = {"EPSH_p_P.SelBothInRBothOutIntrsct"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelBothInRBothOutIntrsctImp(c,session)}

protected case class __SelBothInRBothOutIntrsctImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelBothInRBothOutIntrsct
}
  private var notUsed = true
def !(m : MESSAGES.SH.BothInR) : __SndBothInCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __SndBothInCImp(c,session)}
def sndTor_R(m : MESSAGES.SH.BothInR) : __SndBothInCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __SndBothInCImp(c,session)}

def !(m : MESSAGES.SH.BothOut) : __SndBothOutTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __SndBothOutTwoImp(c,session)}
def sndTor_R(m : MESSAGES.SH.BothOut) : __SndBothOutTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __SndBothOutTwoImp(c,session)}

def !(m : MESSAGES.SH.Intrsct) : __RcvResIntrsctImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __RcvResIntrsctImp(c,session)}
def sndTor_R(m : MESSAGES.SH.Intrsct) : __RcvResIntrsctImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
 __RcvResIntrsctImp(c,session)}

}


  trait SndBothInR extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndBothInR extends SndBothInR {
  override protected def __children: List[EPSH_p_P] = List(SndBothInC)
  override type implT = __SndBothInRImp
  override type implNextT = __SndBothInCImp
override def toString() : String = {"EPSH_p_P.SndBothInR"}
    override def to : RRole = Role("r",RoleSet("R")) 
   override def l : String = "BothInR" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBothInRImp(c,session)}

protected case class __SndBothInRImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBothInR
}
  private var notUsed = true
def sndTor_R(m : MESSAGES.SH.BothInR) : __SndBothInCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__SndBothInCImp(c,session)}
def !(m : MESSAGES.SH.BothInR) : __SndBothInCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__SndBothInCImp(c,session)}
def snd(m : MESSAGES.SH.BothInR) : __SndBothInCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__SndBothInCImp(c,session)}

}


  trait SndBothInC extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndBothInC extends SndBothInC {
  override protected def __children: List[EPSH_p_P] = List(T)
  override type implT = __SndBothInCImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_p_P.SndBothInC"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "BothInC" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBothInCImp(c,session)}

protected case class __SndBothInCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBothInC
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.SH.BothInC) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}
def !(m : MESSAGES.SH.BothInC) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}
def snd(m : MESSAGES.SH.BothInC) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}

}


protected  trait T extends EPSH_p_P with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPSH_p_P] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPSH_p_P.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait SndBothOut extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndBothOut extends SndBothOut {
  override protected def __children: List[EPSH_p_P] = List(SndBothOutTwo)
  override type implT = __SndBothOutImp
  override type implNextT = __SndBothOutTwoImp
override def toString() : String = {"EPSH_p_P.SndBothOut"}
    override def to : RRole = Role("r",RoleSet("R")) 
   override def l : String = "BothOut" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBothOutImp(c,session)}

protected case class __SndBothOutImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBothOut
}
  private var notUsed = true
def sndTor_R(m : MESSAGES.SH.BothOut) : __SndBothOutTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__SndBothOutTwoImp(c,session)}
def !(m : MESSAGES.SH.BothOut) : __SndBothOutTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__SndBothOutTwoImp(c,session)}
def snd(m : MESSAGES.SH.BothOut) : __SndBothOutTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__SndBothOutTwoImp(c,session)}

}


  trait SndBothOutTwo extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndBothOutTwo extends SndBothOutTwo {
  override protected def __children: List[EPSH_p_P] = List(T)
  override type implT = __SndBothOutTwoImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_p_P.SndBothOutTwo"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "BothOutTwo" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBothOutTwoImp(c,session)}

protected case class __SndBothOutTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBothOutTwo
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.SH.BothOutTwo) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}
def !(m : MESSAGES.SH.BothOutTwo) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}
def snd(m : MESSAGES.SH.BothOutTwo) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}

}

//there was an occurens of t already

  trait SndIntrsct extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndIntrsct extends SndIntrsct {
  override protected def __children: List[EPSH_p_P] = List(RcvResIntrsct)
  override type implT = __SndIntrsctImp
  override type implNextT = __RcvResIntrsctImp
override def toString() : String = {"EPSH_p_P.SndIntrsct"}
    override def to : RRole = Role("r",RoleSet("R")) 
   override def l : String = "Intrsct" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndIntrsctImp(c,session)}

protected case class __SndIntrsctImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndIntrsct
}
  private var notUsed = true
def sndTor_R(m : MESSAGES.SH.Intrsct) : __RcvResIntrsctImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__RcvResIntrsctImp(c,session)}
def !(m : MESSAGES.SH.Intrsct) : __RcvResIntrsctImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__RcvResIntrsctImp(c,session)}
def snd(m : MESSAGES.SH.Intrsct) : __RcvResIntrsctImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__RcvResIntrsctImp(c,session)}

}


  trait RcvResIntrsct extends EPSH_p_P with event_lang.dsl.ChannelTypeRcv
  object RcvResIntrsct extends RcvResIntrsct {
  override protected def __children: List[EPSH_p_P] = List(SelSecOutSecIn)
  override type implT = __RcvResIntrsctImp
  override type implNextT = __SelSecOutSecInImp
override def toString() : String = {"EPSH_p_P.RcvResIntrsct"}
  override type msgT = MESSAGES.SH.ResIntrsct
   override def frm : Role = Role("r",RoleSet("R")) 
   override def l : String = "ResIntrsct"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvResIntrsctImp(c,session)}

protected case class __RcvResIntrsctImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvResIntrsct
}
  def rcvFrmr_R : (MESSAGES.SH.ResIntrsct,__SelSecOutSecInImp) = {(c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.ResIntrsct],__SelSecOutSecInImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.ResIntrsct,__SelSecOutSecInImp),T]) : T = {
  f((c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.ResIntrsct],__SelSecOutSecInImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.ResIntrsct = {c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.ResIntrsct]}
def ? : MESSAGES.SH.ResIntrsct = {c.rcv(Role("r",RoleSet("R"))).asInstanceOf[MESSAGES.SH.ResIntrsct]}
def channelCon : __SelSecOutSecInImp = {__SelSecOutSecInImp(c,session)}

}


protected  trait SelSecOutSecIn extends EPSH_p_P with event_lang.dsl.ChannelTypeSel
protected  object SelSecOutSecIn extends SelSecOutSecIn {
  override protected def __children: List[EPSH_p_P] = List(SndSecOut,SndSecIn)
  override type implT = __SelSecOutSecInImp
  override type implNextT = __SndSecOutImp
override def toString() : String = {"EPSH_p_P.SelSecOutSecIn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelSecOutSecInImp(c,session)}

protected case class __SelSecOutSecInImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelSecOutSecIn
}
  private var notUsed = true
def !(m : MESSAGES.SH.SecOut) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __TImp(c,session)}
def sndToc_C(m : MESSAGES.SH.SecOut) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __TImp(c,session)}

def !(m : MESSAGES.SH.SecIn) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __TImp(c,session)}
def sndToc_C(m : MESSAGES.SH.SecIn) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __TImp(c,session)}

}


  trait SndSecOut extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndSecOut extends SndSecOut {
  override protected def __children: List[EPSH_p_P] = List(T)
  override type implT = __SndSecOutImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_p_P.SndSecOut"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "SecOut" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndSecOutImp(c,session)}

protected case class __SndSecOutImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndSecOut
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.SH.SecOut) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}
def !(m : MESSAGES.SH.SecOut) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}
def snd(m : MESSAGES.SH.SecOut) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}

}

//there was an occurens of t already

  trait SndSecIn extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndSecIn extends SndSecIn {
  override protected def __children: List[EPSH_p_P] = List(T)
  override type implT = __SndSecInImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_p_P.SndSecIn"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "SecIn" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndSecInImp(c,session)}

protected case class __SndSecInImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndSecIn
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.SH.SecIn) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}
def !(m : MESSAGES.SH.SecIn) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}
def snd(m : MESSAGES.SH.SecIn) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__TImp(c,session)}

}

//there was an occurens of t already

  trait SndClosePR extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndClosePR extends SndClosePR {
  override protected def __children: List[EPSH_p_P] = List(SndClosePC)
  override type implT = __SndClosePRImp
  override type implNextT = __SndClosePCImp
override def toString() : String = {"EPSH_p_P.SndClosePR"}
    override def to : RRole = Role("r",RoleSet("R")) 
   override def l : String = "ClosePR" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndClosePRImp(c,session)}

protected case class __SndClosePRImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndClosePR
}
  private var notUsed = true
def sndTor_R(m : MESSAGES.SH.ClosePR) : __SndClosePCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__SndClosePCImp(c,session)}
def !(m : MESSAGES.SH.ClosePR) : __SndClosePCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__SndClosePCImp(c,session)}
def snd(m : MESSAGES.SH.ClosePR) : __SndClosePCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__SndClosePCImp(c,session)}

}


  trait SndClosePC extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndClosePC extends SndClosePC {
  override protected def __children: List[EPSH_p_P] = List(End_p_P_SHClosePR)
  override type implT = __SndClosePCImp
  override type implNextT = __End_p_P_SHClosePRImp
override def toString() : String = {"EPSH_p_P.SndClosePC"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "ClosePC" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndClosePCImp(c,session)}

protected case class __SndClosePCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndClosePC
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.SH.ClosePC) : __End_p_P_SHClosePRImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_p_P_SHClosePRImp(c,session)}
def !(m : MESSAGES.SH.ClosePC) : __End_p_P_SHClosePRImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_p_P_SHClosePRImp(c,session)}
def snd(m : MESSAGES.SH.ClosePC) : __End_p_P_SHClosePRImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_p_P_SHClosePRImp(c,session)}

}


protected  trait End_p_P_SHClosePR extends EPSH_p_P with event_lang.dsl.ChannelTypeEnd
protected  object End_p_P_SHClosePR extends End_p_P_SHClosePR {
  override protected def __children: List[EPSH_p_P] = List()
  override type implT = __End_p_P_SHClosePRImp
  override type implNextT = Nothing
override def toString() : String = {"EPSH_p_P.End_p_P_SHClosePR"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_p_P_SHClosePRImp(c,session)}

protected case class __End_p_P_SHClosePRImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_p_P_SHClosePR
}
  
}



  trait Failed_c_C extends EPSH_p_P with event_lang.dsl.ChannelTypeFDtct
  object Failed_c_C extends Failed_c_C {
  override protected def __children: List[EPSH_p_P] = List(SndFM)
  override type implT = __Failed_c_CImp
  override type implNextT = __SndFMImp
override def toString() : String = {"EPSH_p_P.Failed_c_C"}
  override def suspect : Role = Role("c",RoleSet("C")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_c_CImp(c,session)}

protected case class __Failed_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_c_C
}
  def failed_c_C(): __SndFMImp = {__SndFMImp(c,session)}

}


  trait SndFM extends EPSH_p_P with event_lang.dsl.ChannelTypeSnd
  object SndFM extends SndFM {
  override protected def __children: List[EPSH_p_P] = List(End_p_P_SHFHandling)
  override type implT = __SndFMImp
  override type implNextT = __End_p_P_SHFHandlingImp
override def toString() : String = {"EPSH_p_P.SndFM"}
    override def to : RRole = Role("r",RoleSet("R")) 
   override def l : String = "FM" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFMImp(c,session)}

protected case class __SndFMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFM
}
  private var notUsed = true
def sndTor_R(m : MESSAGES.SH.FM) : __End_p_P_SHFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__End_p_P_SHFHandlingImp(c,session)}
def !(m : MESSAGES.SH.FM) : __End_p_P_SHFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__End_p_P_SHFHandlingImp(c,session)}
def snd(m : MESSAGES.SH.FM) : __End_p_P_SHFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("r",RoleSet("R")),m)
__End_p_P_SHFHandlingImp(c,session)}

}


protected  trait End_p_P_SHFHandling extends EPSH_p_P with event_lang.dsl.ChannelTypeEnd
protected  object End_p_P_SHFHandling extends End_p_P_SHFHandling {
  override protected def __children: List[EPSH_p_P] = List()
  override type implT = __End_p_P_SHFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSH_p_P.End_p_P_SHFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_p_P_SHFHandlingImp(c,session)}

protected case class __End_p_P_SHFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_p_P_SHFHandling
}
  
}


}

object Main_p_P{
trait EPMain_p_P extends __EPType_P

object EPMain_p_P extends EPMain_p_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_p_P] = List(Hdl)
  override type implT = __EPMain_p_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_p_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("p",RoleSet("P"))) 
  override def argsP: Role = Role("r",RoleSet("R")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
  override def prjTo : RRole = Role("p",RoleSet("P")) 
  override def rootRole: Role = Role("p",RoleSet("P")) 
  override def name : String = "Main"
}

protected case class __EPMain_p_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_p_P
}
  
}


protected  trait Hdl extends EPMain_p_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_p_P] = List(SpawnSH,Failed_r_R)
  override type implT = __HdlImp
  override type implNextT = __SpawnSHImp
override def toString() : String = {"EPMain_p_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSH extends EPMain_p_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnSH extends SpawnSH {
  override protected def __children: List[EPMain_p_P] = List(End_p_P_Main)
  override type implT = __SpawnSHImp
  override type implNextT = __End_p_P_MainImp
override def toString() : String = {"EPMain_p_P.SpawnSH"}
    override def y: List[Role] = List(Role("p",RoleSet("P")), Role("r",RoleSet("R"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "SH" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSHImp(c,session)}

protected case class __SpawnSHImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSH
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_p_P_Main extends EPMain_p_P with event_lang.dsl.ChannelTypeEnd
protected  object End_p_P_Main extends End_p_P_Main {
  override protected def __children: List[EPMain_p_P] = List()
  override type implT = __End_p_P_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_p_P.End_p_P_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_p_P_MainImp(c,session)}

protected case class __End_p_P_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_p_P_Main
}
  
}



  trait Failed_r_R extends EPMain_p_P with event_lang.dsl.ChannelTypeFDtct
  object Failed_r_R extends Failed_r_R {
  override protected def __children: List[EPMain_p_P] = List(SndF)
  override type implT = __Failed_r_RImp
  override type implNextT = __SndFImp
override def toString() : String = {"EPMain_p_P.Failed_r_R"}
  override def suspect : Role = Role("r",RoleSet("R")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_r_RImp(c,session)}

protected case class __Failed_r_RImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_r_R
}
  def failed_r_R(): __SndFImp = {__SndFImp(c,session)}

}


  trait SndF extends EPMain_p_P with event_lang.dsl.ChannelTypeSnd
  object SndF extends SndF {
  override protected def __children: List[EPMain_p_P] = List(End_p_P_MainFHandling)
  override type implT = __SndFImp
  override type implNextT = __End_p_P_MainFHandlingImp
override def toString() : String = {"EPMain_p_P.SndF"}
    override def to : RRole = RoleSet("C") 
   override def l : String = "F" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFImp(c,session)}

protected case class __SndFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF
}
  private var notUsed = true
def sndToC(m : MESSAGES.Main.F) : __End_p_P_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__End_p_P_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.F) : __End_p_P_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__End_p_P_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.F) : __End_p_P_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__End_p_P_MainFHandlingImp(c,session)}

}


protected  trait End_p_P_MainFHandling extends EPMain_p_P with event_lang.dsl.ChannelTypeEnd
protected  object End_p_P_MainFHandling extends End_p_P_MainFHandling {
  override protected def __children: List[EPMain_p_P] = List()
  override type implT = __End_p_P_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_p_P.End_p_P_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_p_P_MainFHandlingImp(c,session)}

protected case class __End_p_P_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_p_P_MainFHandling
}
  
}


}

}

object R {
val subs : Seq[dsl.ChannelTypeSubS] = List(SH_r_R.EPSH_r_R,Main_r_R.EPMain_r_R)
trait __EPType_R extends AbstractChannelType {

}

trait EPType_R[T<: TState] extends AbstractEndPoint[__EPType_R,T] {
override val roleSet: RoleSet = RoleSet("R")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(SH_r_R.EPSH_r_R,Main_r_R.EPMain_r_R)

}

object SH_r_R{
trait EPSH_r_R extends __EPType_R

object EPSH_r_R extends EPSH_r_R with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSH_r_R] = List(Hdl)
  override type implT = __EPSH_r_RImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSH_r_RImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("p",RoleSet("P")), Role("r",RoleSet("R"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("r",RoleSet("R")) 
  override def rootRole: Role = Role("p",RoleSet("P")) 
  override def name : String = "SH"
}

protected case class __EPSH_r_RImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSH_r_R
}
  
}


protected  trait Hdl extends EPSH_r_R with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSH_r_R] = List(RecT,RcvFM)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPSH_r_R.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPSH_r_R with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPSH_r_R] = List(SelIsAboveClosePR)
  override type implT = __RecTImp
  override type implNextT = __SelIsAboveClosePRImp
override def toString() : String = {"EPSH_r_R.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelIsAboveClosePR extends EPSH_r_R with event_lang.dsl.ChannelTypeBrn
protected  object SelIsAboveClosePR extends SelIsAboveClosePR {
  override protected def __children: List[EPSH_r_R] = List(RcvIsAbove,RcvClosePR)
  override type implT = __SelIsAboveClosePRImp
  override type implNextT = __RcvIsAboveImp
override def toString() : String = {"EPSH_r_R.SelIsAboveClosePR"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelIsAboveClosePRImp(c,session)}

protected case class __SelIsAboveClosePRImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelIsAboveClosePR
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvIsAbove extends EPSH_r_R with event_lang.dsl.ChannelTypeRcv
  object RcvIsAbove extends RcvIsAbove {
  override protected def __children: List[EPSH_r_R] = List(SndRes)
  override type implT = __RcvIsAboveImp
  override type implNextT = __SndResImp
override def toString() : String = {"EPSH_r_R.RcvIsAbove"}
  override type msgT = MESSAGES.SH.IsAbove
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "IsAbove"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvIsAboveImp(c,session)}

protected case class __RcvIsAboveImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvIsAbove
}
  def rcvFrmp_P : (MESSAGES.SH.IsAbove,__SndResImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.IsAbove],__SndResImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.IsAbove,__SndResImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.IsAbove],__SndResImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.IsAbove = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.IsAbove]}
def ? : MESSAGES.SH.IsAbove = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.IsAbove]}
def channelCon : __SndResImp = {__SndResImp(c,session)}

}


  trait SndRes extends EPSH_r_R with event_lang.dsl.ChannelTypeSnd
  object SndRes extends SndRes {
  override protected def __children: List[EPSH_r_R] = List(RcvIsAboveSec)
  override type implT = __SndResImp
  override type implNextT = __RcvIsAboveSecImp
override def toString() : String = {"EPSH_r_R.SndRes"}
    override def to : RRole = Role("p",RoleSet("P")) 
   override def l : String = "Res" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndResImp(c,session)}

protected case class __SndResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndRes
}
  private var notUsed = true
def sndTop_P(m : MESSAGES.SH.Res) : __RcvIsAboveSecImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__RcvIsAboveSecImp(c,session)}
def !(m : MESSAGES.SH.Res) : __RcvIsAboveSecImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__RcvIsAboveSecImp(c,session)}
def snd(m : MESSAGES.SH.Res) : __RcvIsAboveSecImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__RcvIsAboveSecImp(c,session)}

}


  trait RcvIsAboveSec extends EPSH_r_R with event_lang.dsl.ChannelTypeRcv
  object RcvIsAboveSec extends RcvIsAboveSec {
  override protected def __children: List[EPSH_r_R] = List(SndResSec)
  override type implT = __RcvIsAboveSecImp
  override type implNextT = __SndResSecImp
override def toString() : String = {"EPSH_r_R.RcvIsAboveSec"}
  override type msgT = MESSAGES.SH.IsAboveSec
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "IsAboveSec"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvIsAboveSecImp(c,session)}

protected case class __RcvIsAboveSecImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvIsAboveSec
}
  def rcvFrmp_P : (MESSAGES.SH.IsAboveSec,__SndResSecImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.IsAboveSec],__SndResSecImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.IsAboveSec,__SndResSecImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.IsAboveSec],__SndResSecImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.IsAboveSec = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.IsAboveSec]}
def ? : MESSAGES.SH.IsAboveSec = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.IsAboveSec]}
def channelCon : __SndResSecImp = {__SndResSecImp(c,session)}

}


  trait SndResSec extends EPSH_r_R with event_lang.dsl.ChannelTypeSnd
  object SndResSec extends SndResSec {
  override protected def __children: List[EPSH_r_R] = List(SelBothInRBothOutIntrsct)
  override type implT = __SndResSecImp
  override type implNextT = __SelBothInRBothOutIntrsctImp
override def toString() : String = {"EPSH_r_R.SndResSec"}
    override def to : RRole = Role("p",RoleSet("P")) 
   override def l : String = "ResSec" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndResSecImp(c,session)}

protected case class __SndResSecImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndResSec
}
  private var notUsed = true
def sndTop_P(m : MESSAGES.SH.ResSec) : __SelBothInRBothOutIntrsctImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__SelBothInRBothOutIntrsctImp(c,session)}
def !(m : MESSAGES.SH.ResSec) : __SelBothInRBothOutIntrsctImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__SelBothInRBothOutIntrsctImp(c,session)}
def snd(m : MESSAGES.SH.ResSec) : __SelBothInRBothOutIntrsctImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__SelBothInRBothOutIntrsctImp(c,session)}

}


protected  trait SelBothInRBothOutIntrsct extends EPSH_r_R with event_lang.dsl.ChannelTypeBrn
protected  object SelBothInRBothOutIntrsct extends SelBothInRBothOutIntrsct {
  override protected def __children: List[EPSH_r_R] = List(RcvBothInR,RcvBothOut,RcvIntrsct)
  override type implT = __SelBothInRBothOutIntrsctImp
  override type implNextT = __RcvBothInRImp
override def toString() : String = {"EPSH_r_R.SelBothInRBothOutIntrsct"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelBothInRBothOutIntrsctImp(c,session)}

protected case class __SelBothInRBothOutIntrsctImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelBothInRBothOutIntrsct
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvBothInR extends EPSH_r_R with event_lang.dsl.ChannelTypeRcv
  object RcvBothInR extends RcvBothInR {
  override protected def __children: List[EPSH_r_R] = List(T)
  override type implT = __RcvBothInRImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_r_R.RcvBothInR"}
  override type msgT = MESSAGES.SH.BothInR
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "BothInR"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBothInRImp(c,session)}

protected case class __RcvBothInRImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBothInR
}
  def rcvFrmp_P : (MESSAGES.SH.BothInR,__TImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothInR],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.BothInR,__TImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothInR],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.BothInR = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothInR]}
def ? : MESSAGES.SH.BothInR = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothInR]}
def channelCon : __TImp = {__TImp(c,session)}

}


protected  trait T extends EPSH_r_R with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPSH_r_R] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPSH_r_R.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvBothOut extends EPSH_r_R with event_lang.dsl.ChannelTypeRcv
  object RcvBothOut extends RcvBothOut {
  override protected def __children: List[EPSH_r_R] = List(T)
  override type implT = __RcvBothOutImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_r_R.RcvBothOut"}
  override type msgT = MESSAGES.SH.BothOut
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "BothOut"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBothOutImp(c,session)}

protected case class __RcvBothOutImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBothOut
}
  def rcvFrmp_P : (MESSAGES.SH.BothOut,__TImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothOut],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.BothOut,__TImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothOut],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.BothOut = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothOut]}
def ? : MESSAGES.SH.BothOut = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.BothOut]}
def channelCon : __TImp = {__TImp(c,session)}

}

//there was an occurens of t already

  trait RcvIntrsct extends EPSH_r_R with event_lang.dsl.ChannelTypeRcv
  object RcvIntrsct extends RcvIntrsct {
  override protected def __children: List[EPSH_r_R] = List(SndResIntrsct)
  override type implT = __RcvIntrsctImp
  override type implNextT = __SndResIntrsctImp
override def toString() : String = {"EPSH_r_R.RcvIntrsct"}
  override type msgT = MESSAGES.SH.Intrsct
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "Intrsct"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvIntrsctImp(c,session)}

protected case class __RcvIntrsctImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvIntrsct
}
  def rcvFrmp_P : (MESSAGES.SH.Intrsct,__SndResIntrsctImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.Intrsct],__SndResIntrsctImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.Intrsct,__SndResIntrsctImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.Intrsct],__SndResIntrsctImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.Intrsct = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.Intrsct]}
def ? : MESSAGES.SH.Intrsct = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.Intrsct]}
def channelCon : __SndResIntrsctImp = {__SndResIntrsctImp(c,session)}

}


  trait SndResIntrsct extends EPSH_r_R with event_lang.dsl.ChannelTypeSnd
  object SndResIntrsct extends SndResIntrsct {
  override protected def __children: List[EPSH_r_R] = List(T)
  override type implT = __SndResIntrsctImp
  override type implNextT = __TImp
override def toString() : String = {"EPSH_r_R.SndResIntrsct"}
    override def to : RRole = Role("p",RoleSet("P")) 
   override def l : String = "ResIntrsct" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndResIntrsctImp(c,session)}

protected case class __SndResIntrsctImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndResIntrsct
}
  private var notUsed = true
def sndTop_P(m : MESSAGES.SH.ResIntrsct) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__TImp(c,session)}
def !(m : MESSAGES.SH.ResIntrsct) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__TImp(c,session)}
def snd(m : MESSAGES.SH.ResIntrsct) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__TImp(c,session)}

}

//there was an occurens of t already

  trait RcvClosePR extends EPSH_r_R with event_lang.dsl.ChannelTypeRcv
  object RcvClosePR extends RcvClosePR {
  override protected def __children: List[EPSH_r_R] = List(End_r_R_SHClosePR)
  override type implT = __RcvClosePRImp
  override type implNextT = __End_r_R_SHClosePRImp
override def toString() : String = {"EPSH_r_R.RcvClosePR"}
  override type msgT = MESSAGES.SH.ClosePR
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "ClosePR"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvClosePRImp(c,session)}

protected case class __RcvClosePRImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvClosePR
}
  def rcvFrmp_P : (MESSAGES.SH.ClosePR,__End_r_R_SHClosePRImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.ClosePR],__End_r_R_SHClosePRImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.ClosePR,__End_r_R_SHClosePRImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.ClosePR],__End_r_R_SHClosePRImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.ClosePR = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.ClosePR]}
def ? : MESSAGES.SH.ClosePR = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.ClosePR]}
def channelCon : __End_r_R_SHClosePRImp = {__End_r_R_SHClosePRImp(c,session)}

}


protected  trait End_r_R_SHClosePR extends EPSH_r_R with event_lang.dsl.ChannelTypeEnd
protected  object End_r_R_SHClosePR extends End_r_R_SHClosePR {
  override protected def __children: List[EPSH_r_R] = List()
  override type implT = __End_r_R_SHClosePRImp
  override type implNextT = Nothing
override def toString() : String = {"EPSH_r_R.End_r_R_SHClosePR"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_r_R_SHClosePRImp(c,session)}

protected case class __End_r_R_SHClosePRImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_r_R_SHClosePR
}
  
}



  trait RcvFM extends EPSH_r_R with event_lang.dsl.ChannelTypeRcv
  object RcvFM extends RcvFM {
  override protected def __children: List[EPSH_r_R] = List(End_r_R_SHFHandling)
  override type implT = __RcvFMImp
  override type implNextT = __End_r_R_SHFHandlingImp
override def toString() : String = {"EPSH_r_R.RcvFM"}
  override type msgT = MESSAGES.SH.FM
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "FM"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFMImp(c,session)}

protected case class __RcvFMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFM
}
  def rcvFrmp_P : (MESSAGES.SH.FM,__End_r_R_SHFHandlingImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.FM],__End_r_R_SHFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SH.FM,__End_r_R_SHFHandlingImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.FM],__End_r_R_SHFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.SH.FM = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.FM]}
def ? : MESSAGES.SH.FM = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.SH.FM]}
def channelCon : __End_r_R_SHFHandlingImp = {__End_r_R_SHFHandlingImp(c,session)}

}


protected  trait End_r_R_SHFHandling extends EPSH_r_R with event_lang.dsl.ChannelTypeEnd
protected  object End_r_R_SHFHandling extends End_r_R_SHFHandling {
  override protected def __children: List[EPSH_r_R] = List()
  override type implT = __End_r_R_SHFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSH_r_R.End_r_R_SHFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_r_R_SHFHandlingImp(c,session)}

protected case class __End_r_R_SHFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_r_R_SHFHandling
}
  
}


}

object Main_r_R{
trait EPMain_r_R extends __EPType_R

object EPMain_r_R extends EPMain_r_R with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_r_R] = List(Hdl)
  override type implT = __EPMain_r_RImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_r_RImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("p",RoleSet("P"))) 
  override def argsP: Role = Role("r",RoleSet("R")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
  override def prjTo : RRole = Role("r",RoleSet("R")) 
  override def rootRole: Role = Role("p",RoleSet("P")) 
  override def name : String = "Main"
}

protected case class __EPMain_r_RImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_r_R
}
  
}


protected  trait Hdl extends EPMain_r_R with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_r_R] = List(SpawnSH,End_r_R_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnSHImp
override def toString() : String = {"EPMain_r_R.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSH extends EPMain_r_R with event_lang.dsl.ChannelTypeSpawn
  object SpawnSH extends SpawnSH {
  override protected def __children: List[EPMain_r_R] = List(End_r_R_Main)
  override type implT = __SpawnSHImp
  override type implNextT = __End_r_R_MainImp
override def toString() : String = {"EPMain_r_R.SpawnSH"}
    override def y: List[Role] = List(Role("p",RoleSet("P")), Role("r",RoleSet("R"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "SH" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSHImp(c,session)}

protected case class __SpawnSHImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSH
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_r_R_Main extends EPMain_r_R with event_lang.dsl.ChannelTypeEnd
protected  object End_r_R_Main extends End_r_R_Main {
  override protected def __children: List[EPMain_r_R] = List()
  override type implT = __End_r_R_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_r_R.End_r_R_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_r_R_MainImp(c,session)}

protected case class __End_r_R_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_r_R_Main
}
  
}



protected  trait End_r_R_MainFHandling extends EPMain_r_R with event_lang.dsl.ChannelTypeEnd
protected  object End_r_R_MainFHandling extends End_r_R_MainFHandling {
  override protected def __children: List[EPMain_r_R] = List()
  override type implT = __End_r_R_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_r_R.End_r_R_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_r_R_MainFHandlingImp(c,session)}

protected case class __End_r_R_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_r_R_MainFHandling
}
  
}


}

}

}
