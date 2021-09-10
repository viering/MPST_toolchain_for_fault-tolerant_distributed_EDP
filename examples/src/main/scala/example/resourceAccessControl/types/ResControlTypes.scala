package example.resourceAccessControl.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object ResControl {
object RS {
val InTwo : RoleSet = RoleSet("InTwo")
val InOne : RoleSet = RoleSet("InOne")
val U : RoleSet = RoleSet("U")
val C : RoleSet = RoleSet("C")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object InterruptOne {
case class F2() extends MSG {
   override def l : String = "F2"
}

case class F() extends MSG {
   override def l : String = "F"
}

case class F1() extends MSG {
   override def l : String = "F1"
}

}

object ResAccCon {
case class F2() extends MSG {
   override def l : String = "F2"
}

case class F1() extends MSG {
   override def l : String = "F1"
}

case class Req(dur:Int) extends MSG {
   override def l : String = "Req"
}

case class F() extends MSG {
   override def l : String = "F"
}

}

object HdlRes {
case class F2() extends MSG {
   override def l : String = "F2"
}

case class F1() extends MSG {
   override def l : String = "F1"
}

case class F() extends MSG {
   override def l : String = "F"
}

case class Start() extends MSG {
   override def l : String = "Start"
}

}

object InterruptTwo {
case class Resume() extends MSG {
   override def l : String = "Resume"
}

case class Data() extends MSG {
   override def l : String = "Data"
}

case class F2() extends MSG {
   override def l : String = "F2"
}

case class InterPaus() extends MSG {
   override def l : String = "InterPaus"
}

case class F3() extends MSG {
   override def l : String = "F3"
}

}

}

object PROTOCOLS {
object InterruptOne {
val iOne_InOne = Role("iOne",RoleSet("InOne"))
val InTwo = RoleSet("InTwo")
val c_C = Role("c",RoleSet("C"))
val u_U = Role("u",RoleSet("U"))
val a_A = Role("a",RoleSet("A"))
}

object HdlRes {
val a_A = Role("a",RoleSet("A"))
val InOne = RoleSet("InOne")
val InTwo = RoleSet("InTwo")
val u_U = Role("u",RoleSet("U"))
val c_C = Role("c",RoleSet("C"))
}

object ResAccCon {
val u_U = Role("u",RoleSet("U"))
val A = RoleSet("A")
val InOne = RoleSet("InOne")
val InTwo = RoleSet("InTwo")
val c_C = Role("c",RoleSet("C"))
}

object InterruptTwo {
val iTwo_InTwo = Role("iTwo",RoleSet("InTwo"))
val InTwo = RoleSet("InTwo")
val c_C = Role("c",RoleSet("C"))
val u_U = Role("u",RoleSet("U"))
val a_A = Role("a",RoleSet("A"))
}

}

object InTwo {
val subs : Seq[dsl.ChannelTypeSubS] = List(HdlRes_InTwo.EPHdlRes_InTwo,ResAccCon_InTwo.EPResAccCon_InTwo,InterruptTwo_iTwo_InTwo.EPInterruptTwo_iTwo_InTwo,InterruptTwo_InTwo.EPInterruptTwo_InTwo,InterruptOne_InTwo.EPInterruptOne_InTwo)
trait __EPType_InTwo extends AbstractChannelType {

}

trait EPType_InTwo[T<: TState] extends AbstractEndPoint[__EPType_InTwo,T] {
override val roleSet: RoleSet = RoleSet("InTwo")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(HdlRes_InTwo.EPHdlRes_InTwo,ResAccCon_InTwo.EPResAccCon_InTwo,InterruptTwo_iTwo_InTwo.EPInterruptTwo_iTwo_InTwo,InterruptTwo_InTwo.EPInterruptTwo_InTwo,InterruptOne_InTwo.EPInterruptOne_InTwo)

}

object InterruptTwo_iTwo_InTwo{
trait EPInterruptTwo_iTwo_InTwo extends __EPType_InTwo

object EPInterruptTwo_iTwo_InTwo extends EPInterruptTwo_iTwo_InTwo with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptTwo_iTwo_InTwo] = List(Hdl)
  override type implT = __EPInterruptTwo_iTwo_InTwoImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptTwo_iTwo_InTwoImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iTwo",RoleSet("InTwo")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = Role("iTwo",RoleSet("InTwo")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptTwo"
}

protected case class __EPInterruptTwo_iTwo_InTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptTwo_iTwo_InTwo
}
  
}


protected  trait Hdl extends EPInterruptTwo_iTwo_InTwo with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptTwo_iTwo_InTwo] = List(End_iTwo_InTwo_InterruptTwo,End_iTwo_InTwo_InterruptTwoFHandling)
  override type implT = __HdlImp
  override type implNextT = __End_iTwo_InTwo_InterruptTwoImp
override def toString() : String = {"EPInterruptTwo_iTwo_InTwo.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_iTwo_InTwo_InterruptTwo extends EPInterruptTwo_iTwo_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_iTwo_InTwo_InterruptTwo extends End_iTwo_InTwo_InterruptTwo {
  override protected def __children: List[EPInterruptTwo_iTwo_InTwo] = List()
  override type implT = __End_iTwo_InTwo_InterruptTwoImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptTwo_iTwo_InTwo.End_iTwo_InTwo_InterruptTwo"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_iTwo_InTwo_InterruptTwoImp(c,session)}

protected case class __End_iTwo_InTwo_InterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_iTwo_InTwo_InterruptTwo
}
  
}



protected  trait End_iTwo_InTwo_InterruptTwoFHandling extends EPInterruptTwo_iTwo_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_iTwo_InTwo_InterruptTwoFHandling extends End_iTwo_InTwo_InterruptTwoFHandling {
  override protected def __children: List[EPInterruptTwo_iTwo_InTwo] = List()
  override type implT = __End_iTwo_InTwo_InterruptTwoFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptTwo_iTwo_InTwo.End_iTwo_InTwo_InterruptTwoFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_iTwo_InTwo_InterruptTwoFHandlingImp(c,session)}

protected case class __End_iTwo_InTwo_InterruptTwoFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_iTwo_InTwo_InterruptTwoFHandling
}
  
}


}

object InterruptTwo_InTwo{
trait EPInterruptTwo_InTwo extends __EPType_InTwo

object EPInterruptTwo_InTwo extends EPInterruptTwo_InTwo with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptTwo_InTwo] = List(Hdl)
  override type implT = __EPInterruptTwo_InTwoImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptTwo_InTwoImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iTwo",RoleSet("InTwo")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = RoleSet("InTwo") 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptTwo"
}

protected case class __EPInterruptTwo_InTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptTwo_InTwo
}
  
}


protected  trait Hdl extends EPInterruptTwo_InTwo with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptTwo_InTwo] = List(End_InTwo_InterruptTwo,RcvF2)
  override type implT = __HdlImp
  override type implNextT = __End_InTwo_InterruptTwoImp
override def toString() : String = {"EPInterruptTwo_InTwo.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_InTwo_InterruptTwo extends EPInterruptTwo_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_InTwo_InterruptTwo extends End_InTwo_InterruptTwo {
  override protected def __children: List[EPInterruptTwo_InTwo] = List()
  override type implT = __End_InTwo_InterruptTwoImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptTwo_InTwo.End_InTwo_InterruptTwo"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InTwo_InterruptTwoImp(c,session)}

protected case class __End_InTwo_InterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InTwo_InterruptTwo
}
  
}



  trait RcvF2 extends EPInterruptTwo_InTwo with event_lang.dsl.ChannelTypeRcv
  object RcvF2 extends RcvF2 {
  override protected def __children: List[EPInterruptTwo_InTwo] = List(SpawnInterruptTwo)
  override type implT = __RcvF2Imp
  override type implNextT = __SpawnInterruptTwoImp
override def toString() : String = {"EPInterruptTwo_InTwo.RcvF2"}
  override type msgT = MESSAGES.InterruptTwo.F2
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF2Imp(c,session)}

protected case class __RcvF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF2
}
  def rcvFrmc_C : (MESSAGES.InterruptTwo.F2,__SpawnInterruptTwoImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.F2],__SpawnInterruptTwoImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.InterruptTwo.F2,__SpawnInterruptTwoImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.F2],__SpawnInterruptTwoImp(c,session))) 
}
def rcvMSG : MESSAGES.InterruptTwo.F2 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.F2]}
def ? : MESSAGES.InterruptTwo.F2 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.F2]}
def channelCon : __SpawnInterruptTwoImp = {__SpawnInterruptTwoImp(c,session)}

}


  trait SpawnInterruptTwo extends EPInterruptTwo_InTwo with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptTwo extends SpawnInterruptTwo {
  override protected def __children: List[EPInterruptTwo_InTwo] = List(End_InTwo_InterruptTwoFHandling)
  override type implT = __SpawnInterruptTwoImp
  override type implNextT = __End_InTwo_InterruptTwoFHandlingImp
override def toString() : String = {"EPInterruptTwo_InTwo.SpawnInterruptTwo"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InTwo") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptTwo" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptTwoImp(c,session)}

protected case class __SpawnInterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptTwo
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_InTwo_InterruptTwoFHandling extends EPInterruptTwo_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_InTwo_InterruptTwoFHandling extends End_InTwo_InterruptTwoFHandling {
  override protected def __children: List[EPInterruptTwo_InTwo] = List()
  override type implT = __End_InTwo_InterruptTwoFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptTwo_InTwo.End_InTwo_InterruptTwoFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InTwo_InterruptTwoFHandlingImp(c,session)}

protected case class __End_InTwo_InterruptTwoFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InTwo_InterruptTwoFHandling
}
  
}


}

object ResAccCon_InTwo{
trait EPResAccCon_InTwo extends __EPType_InTwo

object EPResAccCon_InTwo extends EPResAccCon_InTwo with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPResAccCon_InTwo] = List(Hdl)
  override type implT = __EPResAccCon_InTwoImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPResAccCon_InTwoImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("u",RoleSet("U")) 
  override def argsRs: List[RoleSet] = List(RoleSet("A"), RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = RoleSet("InTwo") 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "ResAccCon"
}

protected case class __EPResAccCon_InTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPResAccCon_InTwo
}
  
}


protected  trait Hdl extends EPResAccCon_InTwo with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPResAccCon_InTwo] = List(SpawnHdlRes,RcvF2)
  override type implT = __HdlImp
  override type implNextT = __SpawnHdlResImp
override def toString() : String = {"EPResAccCon_InTwo.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnHdlRes extends EPResAccCon_InTwo with event_lang.dsl.ChannelTypeSpawn
  object SpawnHdlRes extends SpawnHdlRes {
  override protected def __children: List[EPResAccCon_InTwo] = List(End_InTwo_ResAccCon)
  override type implT = __SpawnHdlResImp
  override type implNextT = __End_InTwo_ResAccConImp
override def toString() : String = {"EPResAccCon_InTwo.SpawnHdlRes"}
    override def y: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def pickR: RoleSet = RoleSet("A") 
  override def rs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def name: String = "HdlRes" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnHdlResImp(c,session)}

protected case class __SpawnHdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnHdlRes
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_InTwo_ResAccCon extends EPResAccCon_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_InTwo_ResAccCon extends End_InTwo_ResAccCon {
  override protected def __children: List[EPResAccCon_InTwo] = List()
  override type implT = __End_InTwo_ResAccConImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_InTwo.End_InTwo_ResAccCon"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InTwo_ResAccConImp(c,session)}

protected case class __End_InTwo_ResAccConImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InTwo_ResAccCon
}
  
}



  trait RcvF2 extends EPResAccCon_InTwo with event_lang.dsl.ChannelTypeRcv
  object RcvF2 extends RcvF2 {
  override protected def __children: List[EPResAccCon_InTwo] = List(End_InTwo_ResAccConFHandling)
  override type implT = __RcvF2Imp
  override type implNextT = __End_InTwo_ResAccConFHandlingImp
override def toString() : String = {"EPResAccCon_InTwo.RcvF2"}
  override type msgT = MESSAGES.ResAccCon.F2
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF2Imp(c,session)}

protected case class __RcvF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF2
}
  def rcvFrmc_C : (MESSAGES.ResAccCon.F2,__End_InTwo_ResAccConFHandlingImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F2],__End_InTwo_ResAccConFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ResAccCon.F2,__End_InTwo_ResAccConFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F2],__End_InTwo_ResAccConFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.ResAccCon.F2 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F2]}
def ? : MESSAGES.ResAccCon.F2 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F2]}
def channelCon : __End_InTwo_ResAccConFHandlingImp = {__End_InTwo_ResAccConFHandlingImp(c,session)}

}


protected  trait End_InTwo_ResAccConFHandling extends EPResAccCon_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_InTwo_ResAccConFHandling extends End_InTwo_ResAccConFHandling {
  override protected def __children: List[EPResAccCon_InTwo] = List()
  override type implT = __End_InTwo_ResAccConFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_InTwo.End_InTwo_ResAccConFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InTwo_ResAccConFHandlingImp(c,session)}

protected case class __End_InTwo_ResAccConFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InTwo_ResAccConFHandling
}
  
}


}

object HdlRes_InTwo{
trait EPHdlRes_InTwo extends __EPType_InTwo

object EPHdlRes_InTwo extends EPHdlRes_InTwo with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPHdlRes_InTwo] = List(Hdl)
  override type implT = __EPHdlRes_InTwoImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPHdlRes_InTwoImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("a",RoleSet("A")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = RoleSet("InTwo") 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "HdlRes"
}

protected case class __EPHdlRes_InTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPHdlRes_InTwo
}
  
}


protected  trait Hdl extends EPHdlRes_InTwo with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPHdlRes_InTwo] = List(SpawnInterruptOne,RcvF2)
  override type implT = __HdlImp
  override type implNextT = __SpawnInterruptOneImp
override def toString() : String = {"EPHdlRes_InTwo.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnInterruptOne extends EPHdlRes_InTwo with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptOne extends SpawnInterruptOne {
  override protected def __children: List[EPHdlRes_InTwo] = List(End_InTwo_HdlRes)
  override type implT = __SpawnInterruptOneImp
  override type implNextT = __End_InTwo_HdlResImp
override def toString() : String = {"EPHdlRes_InTwo.SpawnInterruptOne"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InOne") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptOne" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptOneImp(c,session)}

protected case class __SpawnInterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptOne
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_InTwo_HdlRes extends EPHdlRes_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_InTwo_HdlRes extends End_InTwo_HdlRes {
  override protected def __children: List[EPHdlRes_InTwo] = List()
  override type implT = __End_InTwo_HdlResImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_InTwo.End_InTwo_HdlRes"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InTwo_HdlResImp(c,session)}

protected case class __End_InTwo_HdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InTwo_HdlRes
}
  
}



  trait RcvF2 extends EPHdlRes_InTwo with event_lang.dsl.ChannelTypeRcv
  object RcvF2 extends RcvF2 {
  override protected def __children: List[EPHdlRes_InTwo] = List(End_InTwo_HdlResFHandling)
  override type implT = __RcvF2Imp
  override type implNextT = __End_InTwo_HdlResFHandlingImp
override def toString() : String = {"EPHdlRes_InTwo.RcvF2"}
  override type msgT = MESSAGES.HdlRes.F2
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF2Imp(c,session)}

protected case class __RcvF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF2
}
  def rcvFrmc_C : (MESSAGES.HdlRes.F2,__End_InTwo_HdlResFHandlingImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F2],__End_InTwo_HdlResFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.HdlRes.F2,__End_InTwo_HdlResFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F2],__End_InTwo_HdlResFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.HdlRes.F2 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F2]}
def ? : MESSAGES.HdlRes.F2 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F2]}
def channelCon : __End_InTwo_HdlResFHandlingImp = {__End_InTwo_HdlResFHandlingImp(c,session)}

}


protected  trait End_InTwo_HdlResFHandling extends EPHdlRes_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_InTwo_HdlResFHandling extends End_InTwo_HdlResFHandling {
  override protected def __children: List[EPHdlRes_InTwo] = List()
  override type implT = __End_InTwo_HdlResFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_InTwo.End_InTwo_HdlResFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InTwo_HdlResFHandlingImp(c,session)}

protected case class __End_InTwo_HdlResFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InTwo_HdlResFHandling
}
  
}


}

object InterruptOne_InTwo{
trait EPInterruptOne_InTwo extends __EPType_InTwo

object EPInterruptOne_InTwo extends EPInterruptOne_InTwo with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptOne_InTwo] = List(Hdl)
  override type implT = __EPInterruptOne_InTwoImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptOne_InTwoImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iOne",RoleSet("InOne")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = RoleSet("InTwo") 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptOne"
}

protected case class __EPInterruptOne_InTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptOne_InTwo
}
  
}


protected  trait Hdl extends EPInterruptOne_InTwo with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptOne_InTwo] = List(SpawnInterruptTwo,RcvF2)
  override type implT = __HdlImp
  override type implNextT = __SpawnInterruptTwoImp
override def toString() : String = {"EPInterruptOne_InTwo.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnInterruptTwo extends EPInterruptOne_InTwo with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptTwo extends SpawnInterruptTwo {
  override protected def __children: List[EPInterruptOne_InTwo] = List(End_InTwo_InterruptOne)
  override type implT = __SpawnInterruptTwoImp
  override type implNextT = __End_InTwo_InterruptOneImp
override def toString() : String = {"EPInterruptOne_InTwo.SpawnInterruptTwo"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InTwo") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptTwo" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptTwoImp(c,session)}

protected case class __SpawnInterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptTwo
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_InTwo_InterruptOne extends EPInterruptOne_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_InTwo_InterruptOne extends End_InTwo_InterruptOne {
  override protected def __children: List[EPInterruptOne_InTwo] = List()
  override type implT = __End_InTwo_InterruptOneImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_InTwo.End_InTwo_InterruptOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InTwo_InterruptOneImp(c,session)}

protected case class __End_InTwo_InterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InTwo_InterruptOne
}
  
}



  trait RcvF2 extends EPInterruptOne_InTwo with event_lang.dsl.ChannelTypeRcv
  object RcvF2 extends RcvF2 {
  override protected def __children: List[EPInterruptOne_InTwo] = List(End_InTwo_InterruptOneFHandling)
  override type implT = __RcvF2Imp
  override type implNextT = __End_InTwo_InterruptOneFHandlingImp
override def toString() : String = {"EPInterruptOne_InTwo.RcvF2"}
  override type msgT = MESSAGES.InterruptOne.F2
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF2Imp(c,session)}

protected case class __RcvF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF2
}
  def rcvFrmc_C : (MESSAGES.InterruptOne.F2,__End_InTwo_InterruptOneFHandlingImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F2],__End_InTwo_InterruptOneFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.InterruptOne.F2,__End_InTwo_InterruptOneFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F2],__End_InTwo_InterruptOneFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.InterruptOne.F2 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F2]}
def ? : MESSAGES.InterruptOne.F2 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F2]}
def channelCon : __End_InTwo_InterruptOneFHandlingImp = {__End_InTwo_InterruptOneFHandlingImp(c,session)}

}


protected  trait End_InTwo_InterruptOneFHandling extends EPInterruptOne_InTwo with event_lang.dsl.ChannelTypeEnd
protected  object End_InTwo_InterruptOneFHandling extends End_InTwo_InterruptOneFHandling {
  override protected def __children: List[EPInterruptOne_InTwo] = List()
  override type implT = __End_InTwo_InterruptOneFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_InTwo.End_InTwo_InterruptOneFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InTwo_InterruptOneFHandlingImp(c,session)}

protected case class __End_InTwo_InterruptOneFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InTwo_InterruptOneFHandling
}
  
}


}

}

object InOne {
val subs : Seq[dsl.ChannelTypeSubS] = List(ResAccCon_InOne.EPResAccCon_InOne,HdlRes_InOne.EPHdlRes_InOne,InterruptOne_iOne_InOne.EPInterruptOne_iOne_InOne)
trait __EPType_InOne extends AbstractChannelType {

}

trait EPType_InOne[T<: TState] extends AbstractEndPoint[__EPType_InOne,T] {
override val roleSet: RoleSet = RoleSet("InOne")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(ResAccCon_InOne.EPResAccCon_InOne,HdlRes_InOne.EPHdlRes_InOne,InterruptOne_iOne_InOne.EPInterruptOne_iOne_InOne)

}

object ResAccCon_InOne{
trait EPResAccCon_InOne extends __EPType_InOne

object EPResAccCon_InOne extends EPResAccCon_InOne with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPResAccCon_InOne] = List(Hdl)
  override type implT = __EPResAccCon_InOneImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPResAccCon_InOneImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("u",RoleSet("U")) 
  override def argsRs: List[RoleSet] = List(RoleSet("A"), RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = RoleSet("InOne") 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "ResAccCon"
}

protected case class __EPResAccCon_InOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPResAccCon_InOne
}
  
}


protected  trait Hdl extends EPResAccCon_InOne with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPResAccCon_InOne] = List(SpawnHdlRes,RcvF1)
  override type implT = __HdlImp
  override type implNextT = __SpawnHdlResImp
override def toString() : String = {"EPResAccCon_InOne.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnHdlRes extends EPResAccCon_InOne with event_lang.dsl.ChannelTypeSpawn
  object SpawnHdlRes extends SpawnHdlRes {
  override protected def __children: List[EPResAccCon_InOne] = List(End_InOne_ResAccCon)
  override type implT = __SpawnHdlResImp
  override type implNextT = __End_InOne_ResAccConImp
override def toString() : String = {"EPResAccCon_InOne.SpawnHdlRes"}
    override def y: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def pickR: RoleSet = RoleSet("A") 
  override def rs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def name: String = "HdlRes" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnHdlResImp(c,session)}

protected case class __SpawnHdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnHdlRes
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_InOne_ResAccCon extends EPResAccCon_InOne with event_lang.dsl.ChannelTypeEnd
protected  object End_InOne_ResAccCon extends End_InOne_ResAccCon {
  override protected def __children: List[EPResAccCon_InOne] = List()
  override type implT = __End_InOne_ResAccConImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_InOne.End_InOne_ResAccCon"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InOne_ResAccConImp(c,session)}

protected case class __End_InOne_ResAccConImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InOne_ResAccCon
}
  
}



  trait RcvF1 extends EPResAccCon_InOne with event_lang.dsl.ChannelTypeRcv
  object RcvF1 extends RcvF1 {
  override protected def __children: List[EPResAccCon_InOne] = List(End_InOne_ResAccConFHandling)
  override type implT = __RcvF1Imp
  override type implNextT = __End_InOne_ResAccConFHandlingImp
override def toString() : String = {"EPResAccCon_InOne.RcvF1"}
  override type msgT = MESSAGES.ResAccCon.F1
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF1Imp(c,session)}

protected case class __RcvF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF1
}
  def rcvFrmc_C : (MESSAGES.ResAccCon.F1,__End_InOne_ResAccConFHandlingImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F1],__End_InOne_ResAccConFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ResAccCon.F1,__End_InOne_ResAccConFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F1],__End_InOne_ResAccConFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.ResAccCon.F1 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F1]}
def ? : MESSAGES.ResAccCon.F1 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F1]}
def channelCon : __End_InOne_ResAccConFHandlingImp = {__End_InOne_ResAccConFHandlingImp(c,session)}

}


protected  trait End_InOne_ResAccConFHandling extends EPResAccCon_InOne with event_lang.dsl.ChannelTypeEnd
protected  object End_InOne_ResAccConFHandling extends End_InOne_ResAccConFHandling {
  override protected def __children: List[EPResAccCon_InOne] = List()
  override type implT = __End_InOne_ResAccConFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_InOne.End_InOne_ResAccConFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InOne_ResAccConFHandlingImp(c,session)}

protected case class __End_InOne_ResAccConFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InOne_ResAccConFHandling
}
  
}


}

object HdlRes_InOne{
trait EPHdlRes_InOne extends __EPType_InOne

object EPHdlRes_InOne extends EPHdlRes_InOne with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPHdlRes_InOne] = List(Hdl)
  override type implT = __EPHdlRes_InOneImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPHdlRes_InOneImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("a",RoleSet("A")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = RoleSet("InOne") 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "HdlRes"
}

protected case class __EPHdlRes_InOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPHdlRes_InOne
}
  
}


protected  trait Hdl extends EPHdlRes_InOne with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPHdlRes_InOne] = List(SpawnInterruptOne,RcvF1)
  override type implT = __HdlImp
  override type implNextT = __SpawnInterruptOneImp
override def toString() : String = {"EPHdlRes_InOne.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnInterruptOne extends EPHdlRes_InOne with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptOne extends SpawnInterruptOne {
  override protected def __children: List[EPHdlRes_InOne] = List(End_InOne_HdlRes)
  override type implT = __SpawnInterruptOneImp
  override type implNextT = __End_InOne_HdlResImp
override def toString() : String = {"EPHdlRes_InOne.SpawnInterruptOne"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InOne") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptOne" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptOneImp(c,session)}

protected case class __SpawnInterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptOne
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_InOne_HdlRes extends EPHdlRes_InOne with event_lang.dsl.ChannelTypeEnd
protected  object End_InOne_HdlRes extends End_InOne_HdlRes {
  override protected def __children: List[EPHdlRes_InOne] = List()
  override type implT = __End_InOne_HdlResImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_InOne.End_InOne_HdlRes"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InOne_HdlResImp(c,session)}

protected case class __End_InOne_HdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InOne_HdlRes
}
  
}



  trait RcvF1 extends EPHdlRes_InOne with event_lang.dsl.ChannelTypeRcv
  object RcvF1 extends RcvF1 {
  override protected def __children: List[EPHdlRes_InOne] = List(End_InOne_HdlResFHandling)
  override type implT = __RcvF1Imp
  override type implNextT = __End_InOne_HdlResFHandlingImp
override def toString() : String = {"EPHdlRes_InOne.RcvF1"}
  override type msgT = MESSAGES.HdlRes.F1
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF1Imp(c,session)}

protected case class __RcvF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF1
}
  def rcvFrmc_C : (MESSAGES.HdlRes.F1,__End_InOne_HdlResFHandlingImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F1],__End_InOne_HdlResFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.HdlRes.F1,__End_InOne_HdlResFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F1],__End_InOne_HdlResFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.HdlRes.F1 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F1]}
def ? : MESSAGES.HdlRes.F1 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F1]}
def channelCon : __End_InOne_HdlResFHandlingImp = {__End_InOne_HdlResFHandlingImp(c,session)}

}


protected  trait End_InOne_HdlResFHandling extends EPHdlRes_InOne with event_lang.dsl.ChannelTypeEnd
protected  object End_InOne_HdlResFHandling extends End_InOne_HdlResFHandling {
  override protected def __children: List[EPHdlRes_InOne] = List()
  override type implT = __End_InOne_HdlResFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_InOne.End_InOne_HdlResFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_InOne_HdlResFHandlingImp(c,session)}

protected case class __End_InOne_HdlResFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_InOne_HdlResFHandling
}
  
}


}

object InterruptOne_iOne_InOne{
trait EPInterruptOne_iOne_InOne extends __EPType_InOne

object EPInterruptOne_iOne_InOne extends EPInterruptOne_iOne_InOne with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptOne_iOne_InOne] = List(Hdl)
  override type implT = __EPInterruptOne_iOne_InOneImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptOne_iOne_InOneImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iOne",RoleSet("InOne")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = Role("iOne",RoleSet("InOne")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptOne"
}

protected case class __EPInterruptOne_iOne_InOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptOne_iOne_InOne
}
  
}


protected  trait Hdl extends EPInterruptOne_iOne_InOne with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptOne_iOne_InOne] = List(End_iOne_InOne_InterruptOne,End_iOne_InOne_InterruptOneFHandling)
  override type implT = __HdlImp
  override type implNextT = __End_iOne_InOne_InterruptOneImp
override def toString() : String = {"EPInterruptOne_iOne_InOne.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_iOne_InOne_InterruptOne extends EPInterruptOne_iOne_InOne with event_lang.dsl.ChannelTypeEnd
protected  object End_iOne_InOne_InterruptOne extends End_iOne_InOne_InterruptOne {
  override protected def __children: List[EPInterruptOne_iOne_InOne] = List()
  override type implT = __End_iOne_InOne_InterruptOneImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_iOne_InOne.End_iOne_InOne_InterruptOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_iOne_InOne_InterruptOneImp(c,session)}

protected case class __End_iOne_InOne_InterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_iOne_InOne_InterruptOne
}
  
}



protected  trait End_iOne_InOne_InterruptOneFHandling extends EPInterruptOne_iOne_InOne with event_lang.dsl.ChannelTypeEnd
protected  object End_iOne_InOne_InterruptOneFHandling extends End_iOne_InOne_InterruptOneFHandling {
  override protected def __children: List[EPInterruptOne_iOne_InOne] = List()
  override type implT = __End_iOne_InOne_InterruptOneFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_iOne_InOne.End_iOne_InOne_InterruptOneFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_iOne_InOne_InterruptOneFHandlingImp(c,session)}

protected case class __End_iOne_InOne_InterruptOneFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_iOne_InOne_InterruptOneFHandling
}
  
}


}

}

object U {
val subs : Seq[dsl.ChannelTypeSubS] = List(ResAccCon_u_U.EPResAccCon_u_U,HdlRes_u_U.EPHdlRes_u_U,InterruptOne_u_U.EPInterruptOne_u_U,InterruptTwo_u_U.EPInterruptTwo_u_U)
trait __EPType_U extends AbstractChannelType {

}

trait EPType_U[T<: TState] extends AbstractEndPoint[__EPType_U,T] {
override val roleSet: RoleSet = RoleSet("U")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(ResAccCon_u_U.EPResAccCon_u_U,HdlRes_u_U.EPHdlRes_u_U,InterruptOne_u_U.EPInterruptOne_u_U,InterruptTwo_u_U.EPInterruptTwo_u_U)

}

object ResAccCon_u_U{
trait EPResAccCon_u_U extends __EPType_U

object EPResAccCon_u_U extends EPResAccCon_u_U with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPResAccCon_u_U] = List(Hdl)
  override type implT = __EPResAccCon_u_UImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPResAccCon_u_UImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("u",RoleSet("U")) 
  override def argsRs: List[RoleSet] = List(RoleSet("A"), RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = Role("u",RoleSet("U")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "ResAccCon"
}

protected case class __EPResAccCon_u_UImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPResAccCon_u_U
}
  
}


protected  trait Hdl extends EPResAccCon_u_U with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPResAccCon_u_U] = List(SndReq,End_u_U_ResAccConFHandling)
  override type implT = __HdlImp
  override type implNextT = __SndReqImp
override def toString() : String = {"EPResAccCon_u_U.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndReq extends EPResAccCon_u_U with event_lang.dsl.ChannelTypeSnd
  object SndReq extends SndReq {
  override protected def __children: List[EPResAccCon_u_U] = List(SpawnHdlRes)
  override type implT = __SndReqImp
  override type implNextT = __SpawnHdlResImp
override def toString() : String = {"EPResAccCon_u_U.SndReq"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "Req" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndReqImp(c,session)}

protected case class __SndReqImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndReq
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.ResAccCon.Req) : __SpawnHdlResImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__SpawnHdlResImp(c,session)}
def !(m : MESSAGES.ResAccCon.Req) : __SpawnHdlResImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__SpawnHdlResImp(c,session)}
def snd(m : MESSAGES.ResAccCon.Req) : __SpawnHdlResImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__SpawnHdlResImp(c,session)}

}


  trait SpawnHdlRes extends EPResAccCon_u_U with event_lang.dsl.ChannelTypeSpawn
  object SpawnHdlRes extends SpawnHdlRes {
  override protected def __children: List[EPResAccCon_u_U] = List(End_u_U_ResAccCon)
  override type implT = __SpawnHdlResImp
  override type implNextT = __End_u_U_ResAccConImp
override def toString() : String = {"EPResAccCon_u_U.SpawnHdlRes"}
    override def y: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def pickR: RoleSet = RoleSet("A") 
  override def rs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def name: String = "HdlRes" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnHdlResImp(c,session)}

protected case class __SpawnHdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnHdlRes
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_u_U_ResAccCon extends EPResAccCon_u_U with event_lang.dsl.ChannelTypeEnd
protected  object End_u_U_ResAccCon extends End_u_U_ResAccCon {
  override protected def __children: List[EPResAccCon_u_U] = List()
  override type implT = __End_u_U_ResAccConImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_u_U.End_u_U_ResAccCon"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_u_U_ResAccConImp(c,session)}

protected case class __End_u_U_ResAccConImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_u_U_ResAccCon
}
  
}



protected  trait End_u_U_ResAccConFHandling extends EPResAccCon_u_U with event_lang.dsl.ChannelTypeEnd
protected  object End_u_U_ResAccConFHandling extends End_u_U_ResAccConFHandling {
  override protected def __children: List[EPResAccCon_u_U] = List()
  override type implT = __End_u_U_ResAccConFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_u_U.End_u_U_ResAccConFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_u_U_ResAccConFHandlingImp(c,session)}

protected case class __End_u_U_ResAccConFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_u_U_ResAccConFHandling
}
  
}


}

object HdlRes_u_U{
trait EPHdlRes_u_U extends __EPType_U

object EPHdlRes_u_U extends EPHdlRes_u_U with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPHdlRes_u_U] = List(Hdl)
  override type implT = __EPHdlRes_u_UImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPHdlRes_u_UImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("a",RoleSet("A")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = Role("u",RoleSet("U")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "HdlRes"
}

protected case class __EPHdlRes_u_UImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPHdlRes_u_U
}
  
}


protected  trait Hdl extends EPHdlRes_u_U with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPHdlRes_u_U] = List(SpawnInterruptOne,RcvF)
  override type implT = __HdlImp
  override type implNextT = __SpawnInterruptOneImp
override def toString() : String = {"EPHdlRes_u_U.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnInterruptOne extends EPHdlRes_u_U with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptOne extends SpawnInterruptOne {
  override protected def __children: List[EPHdlRes_u_U] = List(End_u_U_HdlRes)
  override type implT = __SpawnInterruptOneImp
  override type implNextT = __End_u_U_HdlResImp
override def toString() : String = {"EPHdlRes_u_U.SpawnInterruptOne"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InOne") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptOne" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptOneImp(c,session)}

protected case class __SpawnInterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptOne
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_u_U_HdlRes extends EPHdlRes_u_U with event_lang.dsl.ChannelTypeEnd
protected  object End_u_U_HdlRes extends End_u_U_HdlRes {
  override protected def __children: List[EPHdlRes_u_U] = List()
  override type implT = __End_u_U_HdlResImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_u_U.End_u_U_HdlRes"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_u_U_HdlResImp(c,session)}

protected case class __End_u_U_HdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_u_U_HdlRes
}
  
}



  trait RcvF extends EPHdlRes_u_U with event_lang.dsl.ChannelTypeRcv
  object RcvF extends RcvF {
  override protected def __children: List[EPHdlRes_u_U] = List(End_u_U_HdlResFHandling)
  override type implT = __RcvFImp
  override type implNextT = __End_u_U_HdlResFHandlingImp
override def toString() : String = {"EPHdlRes_u_U.RcvF"}
  override type msgT = MESSAGES.HdlRes.F
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFImp(c,session)}

protected case class __RcvFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF
}
  def rcvFrmc_C : (MESSAGES.HdlRes.F,__End_u_U_HdlResFHandlingImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F],__End_u_U_HdlResFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.HdlRes.F,__End_u_U_HdlResFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F],__End_u_U_HdlResFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.HdlRes.F = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F]}
def ? : MESSAGES.HdlRes.F = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.F]}
def channelCon : __End_u_U_HdlResFHandlingImp = {__End_u_U_HdlResFHandlingImp(c,session)}

}


protected  trait End_u_U_HdlResFHandling extends EPHdlRes_u_U with event_lang.dsl.ChannelTypeEnd
protected  object End_u_U_HdlResFHandling extends End_u_U_HdlResFHandling {
  override protected def __children: List[EPHdlRes_u_U] = List()
  override type implT = __End_u_U_HdlResFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_u_U.End_u_U_HdlResFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_u_U_HdlResFHandlingImp(c,session)}

protected case class __End_u_U_HdlResFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_u_U_HdlResFHandling
}
  
}


}

object InterruptOne_u_U{
trait EPInterruptOne_u_U extends __EPType_U

object EPInterruptOne_u_U extends EPInterruptOne_u_U with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptOne_u_U] = List(Hdl)
  override type implT = __EPInterruptOne_u_UImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptOne_u_UImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iOne",RoleSet("InOne")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = Role("u",RoleSet("U")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptOne"
}

protected case class __EPInterruptOne_u_UImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptOne_u_U
}
  
}


protected  trait Hdl extends EPInterruptOne_u_U with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptOne_u_U] = List(SpawnInterruptTwo,RcvF)
  override type implT = __HdlImp
  override type implNextT = __SpawnInterruptTwoImp
override def toString() : String = {"EPInterruptOne_u_U.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnInterruptTwo extends EPInterruptOne_u_U with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptTwo extends SpawnInterruptTwo {
  override protected def __children: List[EPInterruptOne_u_U] = List(End_u_U_InterruptOne)
  override type implT = __SpawnInterruptTwoImp
  override type implNextT = __End_u_U_InterruptOneImp
override def toString() : String = {"EPInterruptOne_u_U.SpawnInterruptTwo"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InTwo") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptTwo" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptTwoImp(c,session)}

protected case class __SpawnInterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptTwo
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_u_U_InterruptOne extends EPInterruptOne_u_U with event_lang.dsl.ChannelTypeEnd
protected  object End_u_U_InterruptOne extends End_u_U_InterruptOne {
  override protected def __children: List[EPInterruptOne_u_U] = List()
  override type implT = __End_u_U_InterruptOneImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_u_U.End_u_U_InterruptOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_u_U_InterruptOneImp(c,session)}

protected case class __End_u_U_InterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_u_U_InterruptOne
}
  
}



  trait RcvF extends EPInterruptOne_u_U with event_lang.dsl.ChannelTypeRcv
  object RcvF extends RcvF {
  override protected def __children: List[EPInterruptOne_u_U] = List(End_u_U_InterruptOneFHandling)
  override type implT = __RcvFImp
  override type implNextT = __End_u_U_InterruptOneFHandlingImp
override def toString() : String = {"EPInterruptOne_u_U.RcvF"}
  override type msgT = MESSAGES.InterruptOne.F
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFImp(c,session)}

protected case class __RcvFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF
}
  def rcvFrmc_C : (MESSAGES.InterruptOne.F,__End_u_U_InterruptOneFHandlingImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F],__End_u_U_InterruptOneFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.InterruptOne.F,__End_u_U_InterruptOneFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F],__End_u_U_InterruptOneFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.InterruptOne.F = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F]}
def ? : MESSAGES.InterruptOne.F = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F]}
def channelCon : __End_u_U_InterruptOneFHandlingImp = {__End_u_U_InterruptOneFHandlingImp(c,session)}

}


protected  trait End_u_U_InterruptOneFHandling extends EPInterruptOne_u_U with event_lang.dsl.ChannelTypeEnd
protected  object End_u_U_InterruptOneFHandling extends End_u_U_InterruptOneFHandling {
  override protected def __children: List[EPInterruptOne_u_U] = List()
  override type implT = __End_u_U_InterruptOneFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_u_U.End_u_U_InterruptOneFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_u_U_InterruptOneFHandlingImp(c,session)}

protected case class __End_u_U_InterruptOneFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_u_U_InterruptOneFHandling
}
  
}


}

object InterruptTwo_u_U{
trait EPInterruptTwo_u_U extends __EPType_U

object EPInterruptTwo_u_U extends EPInterruptTwo_u_U with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptTwo_u_U] = List(Hdl)
  override type implT = __EPInterruptTwo_u_UImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptTwo_u_UImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iTwo",RoleSet("InTwo")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = Role("u",RoleSet("U")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptTwo"
}

protected case class __EPInterruptTwo_u_UImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptTwo_u_U
}
  
}


protected  trait Hdl extends EPInterruptTwo_u_U with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptTwo_u_U] = List(RecT,RcvInterPaus)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPInterruptTwo_u_U.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPInterruptTwo_u_U with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPInterruptTwo_u_U] = List(RcvData)
  override type implT = __RecTImp
  override type implNextT = __RcvDataImp
override def toString() : String = {"EPInterruptTwo_u_U.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait RcvData extends EPInterruptTwo_u_U with event_lang.dsl.ChannelTypeRcv
  object RcvData extends RcvData {
  override protected def __children: List[EPInterruptTwo_u_U] = List(T)
  override type implT = __RcvDataImp
  override type implNextT = __TImp
override def toString() : String = {"EPInterruptTwo_u_U.RcvData"}
  override type msgT = MESSAGES.InterruptTwo.Data
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "Data"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDataImp(c,session)}

protected case class __RcvDataImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvData
}
  def rcvFrma_A : (MESSAGES.InterruptTwo.Data,__TImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.InterruptTwo.Data],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.InterruptTwo.Data,__TImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.InterruptTwo.Data],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.InterruptTwo.Data = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.InterruptTwo.Data]}
def ? : MESSAGES.InterruptTwo.Data = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.InterruptTwo.Data]}
def channelCon : __TImp = {__TImp(c,session)}

}


protected  trait T extends EPInterruptTwo_u_U with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPInterruptTwo_u_U] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPInterruptTwo_u_U.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvInterPaus extends EPInterruptTwo_u_U with event_lang.dsl.ChannelTypeRcv
  object RcvInterPaus extends RcvInterPaus {
  override protected def __children: List[EPInterruptTwo_u_U] = List(SndResume)
  override type implT = __RcvInterPausImp
  override type implNextT = __SndResumeImp
override def toString() : String = {"EPInterruptTwo_u_U.RcvInterPaus"}
  override type msgT = MESSAGES.InterruptTwo.InterPaus
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "InterPaus"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvInterPausImp(c,session)}

protected case class __RcvInterPausImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvInterPaus
}
  def rcvFrmc_C : (MESSAGES.InterruptTwo.InterPaus,__SndResumeImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.InterPaus],__SndResumeImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.InterruptTwo.InterPaus,__SndResumeImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.InterPaus],__SndResumeImp(c,session))) 
}
def rcvMSG : MESSAGES.InterruptTwo.InterPaus = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.InterPaus]}
def ? : MESSAGES.InterruptTwo.InterPaus = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.InterPaus]}
def channelCon : __SndResumeImp = {__SndResumeImp(c,session)}

}


  trait SndResume extends EPInterruptTwo_u_U with event_lang.dsl.ChannelTypeSnd
  object SndResume extends SndResume {
  override protected def __children: List[EPInterruptTwo_u_U] = List(SpawnInterruptTwo)
  override type implT = __SndResumeImp
  override type implNextT = __SpawnInterruptTwoImp
override def toString() : String = {"EPInterruptTwo_u_U.SndResume"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "Resume" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndResumeImp(c,session)}

protected case class __SndResumeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndResume
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.InterruptTwo.Resume) : __SpawnInterruptTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SpawnInterruptTwoImp(c,session)}
def !(m : MESSAGES.InterruptTwo.Resume) : __SpawnInterruptTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SpawnInterruptTwoImp(c,session)}
def snd(m : MESSAGES.InterruptTwo.Resume) : __SpawnInterruptTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SpawnInterruptTwoImp(c,session)}

}


  trait SpawnInterruptTwo extends EPInterruptTwo_u_U with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptTwo extends SpawnInterruptTwo {
  override protected def __children: List[EPInterruptTwo_u_U] = List(End_u_U_InterruptTwoFHandling)
  override type implT = __SpawnInterruptTwoImp
  override type implNextT = __End_u_U_InterruptTwoFHandlingImp
override def toString() : String = {"EPInterruptTwo_u_U.SpawnInterruptTwo"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InTwo") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptTwo" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptTwoImp(c,session)}

protected case class __SpawnInterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptTwo
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_u_U_InterruptTwoFHandling extends EPInterruptTwo_u_U with event_lang.dsl.ChannelTypeEnd
protected  object End_u_U_InterruptTwoFHandling extends End_u_U_InterruptTwoFHandling {
  override protected def __children: List[EPInterruptTwo_u_U] = List()
  override type implT = __End_u_U_InterruptTwoFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptTwo_u_U.End_u_U_InterruptTwoFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_u_U_InterruptTwoFHandlingImp(c,session)}

protected case class __End_u_U_InterruptTwoFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_u_U_InterruptTwoFHandling
}
  
}


}

}

object C {
val subs : Seq[dsl.ChannelTypeSubS] = List(ResAccCon_c_C.EPResAccCon_c_C,HdlRes_c_C.EPHdlRes_c_C,InterruptOne_c_C.EPInterruptOne_c_C,InterruptTwo_c_C.EPInterruptTwo_c_C)
trait __EPType_C extends AbstractChannelType {

}

trait EPType_C[T<: TState] extends AbstractEndPoint[__EPType_C,T] {
override val roleSet: RoleSet = RoleSet("C")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(ResAccCon_c_C.EPResAccCon_c_C,HdlRes_c_C.EPHdlRes_c_C,InterruptOne_c_C.EPInterruptOne_c_C,InterruptTwo_c_C.EPInterruptTwo_c_C)

}

object ResAccCon_c_C{
trait EPResAccCon_c_C extends __EPType_C

object EPResAccCon_c_C extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPResAccCon_c_C] = List(Hdl)
  override type implT = __EPResAccCon_c_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPResAccCon_c_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("u",RoleSet("U")) 
  override def argsRs: List[RoleSet] = List(RoleSet("A"), RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = Role("c",RoleSet("C")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "ResAccCon"
}

protected case class __EPResAccCon_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPResAccCon_c_C
}
  
}


protected  trait Hdl extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPResAccCon_c_C] = List(RcvReq,Failed_u_U)
  override type implT = __HdlImp
  override type implNextT = __RcvReqImp
override def toString() : String = {"EPResAccCon_c_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvReq extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvReq extends RcvReq {
  override protected def __children: List[EPResAccCon_c_C] = List(SpawnHdlRes)
  override type implT = __RcvReqImp
  override type implNextT = __SpawnHdlResImp
override def toString() : String = {"EPResAccCon_c_C.RcvReq"}
  override type msgT = MESSAGES.ResAccCon.Req
   override def frm : Role = Role("u",RoleSet("U")) 
   override def l : String = "Req"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvReqImp(c,session)}

protected case class __RcvReqImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvReq
}
  def rcvFrmu_U : (MESSAGES.ResAccCon.Req,__SpawnHdlResImp) = {(c.rcv(Role("u",RoleSet("U"))).asInstanceOf[MESSAGES.ResAccCon.Req],__SpawnHdlResImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ResAccCon.Req,__SpawnHdlResImp),T]) : T = {
  f((c.rcv(Role("u",RoleSet("U"))).asInstanceOf[MESSAGES.ResAccCon.Req],__SpawnHdlResImp(c,session))) 
}
def rcvMSG : MESSAGES.ResAccCon.Req = {c.rcv(Role("u",RoleSet("U"))).asInstanceOf[MESSAGES.ResAccCon.Req]}
def ? : MESSAGES.ResAccCon.Req = {c.rcv(Role("u",RoleSet("U"))).asInstanceOf[MESSAGES.ResAccCon.Req]}
def channelCon : __SpawnHdlResImp = {__SpawnHdlResImp(c,session)}

}


  trait SpawnHdlRes extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeSpawn
  object SpawnHdlRes extends SpawnHdlRes {
  override protected def __children: List[EPResAccCon_c_C] = List(End_c_C_ResAccCon)
  override type implT = __SpawnHdlResImp
  override type implNextT = __End_c_C_ResAccConImp
override def toString() : String = {"EPResAccCon_c_C.SpawnHdlRes"}
    override def y: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def pickR: RoleSet = RoleSet("A") 
  override def rs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def name: String = "HdlRes" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnHdlResImp(c,session)}

protected case class __SpawnHdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnHdlRes
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_c_C_ResAccCon extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_ResAccCon extends End_c_C_ResAccCon {
  override protected def __children: List[EPResAccCon_c_C] = List()
  override type implT = __End_c_C_ResAccConImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_c_C.End_c_C_ResAccCon"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_ResAccConImp(c,session)}

protected case class __End_c_C_ResAccConImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_ResAccCon
}
  
}



  trait Failed_u_U extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeFDtct
  object Failed_u_U extends Failed_u_U {
  override protected def __children: List[EPResAccCon_c_C] = List(SndF)
  override type implT = __Failed_u_UImp
  override type implNextT = __SndFImp
override def toString() : String = {"EPResAccCon_c_C.Failed_u_U"}
  override def suspect : Role = Role("u",RoleSet("U")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_u_UImp(c,session)}

protected case class __Failed_u_UImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_u_U
}
  def failed_u_U(): __SndFImp = {__SndFImp(c,session)}

}


  trait SndF extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF extends SndF {
  override protected def __children: List[EPResAccCon_c_C] = List(SndF1)
  override type implT = __SndFImp
  override type implNextT = __SndF1Imp
override def toString() : String = {"EPResAccCon_c_C.SndF"}
    override def to : RRole = RoleSet("A") 
   override def l : String = "F" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFImp(c,session)}

protected case class __SndFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF
}
  private var notUsed = true
def sndToA(m : MESSAGES.ResAccCon.F) : __SndF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("A"),m)
__SndF1Imp(c,session)}
def !(m : MESSAGES.ResAccCon.F) : __SndF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("A"),m)
__SndF1Imp(c,session)}
def snd(m : MESSAGES.ResAccCon.F) : __SndF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("A"),m)
__SndF1Imp(c,session)}

}


  trait SndF1 extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF1 extends SndF1 {
  override protected def __children: List[EPResAccCon_c_C] = List(SndF2)
  override type implT = __SndF1Imp
  override type implNextT = __SndF2Imp
override def toString() : String = {"EPResAccCon_c_C.SndF1"}
    override def to : RRole = RoleSet("InOne") 
   override def l : String = "F1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF1Imp(c,session)}

protected case class __SndF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF1
}
  private var notUsed = true
def sndToInOne(m : MESSAGES.ResAccCon.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InOne"),m)
__SndF2Imp(c,session)}
def !(m : MESSAGES.ResAccCon.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InOne"),m)
__SndF2Imp(c,session)}
def snd(m : MESSAGES.ResAccCon.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InOne"),m)
__SndF2Imp(c,session)}

}


  trait SndF2 extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF2 extends SndF2 {
  override protected def __children: List[EPResAccCon_c_C] = List(End_c_C_ResAccConFHandling)
  override type implT = __SndF2Imp
  override type implNextT = __End_c_C_ResAccConFHandlingImp
override def toString() : String = {"EPResAccCon_c_C.SndF2"}
    override def to : RRole = RoleSet("InTwo") 
   override def l : String = "F2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF2Imp(c,session)}

protected case class __SndF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF2
}
  private var notUsed = true
def sndToInTwo(m : MESSAGES.ResAccCon.F2) : __End_c_C_ResAccConFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__End_c_C_ResAccConFHandlingImp(c,session)}
def !(m : MESSAGES.ResAccCon.F2) : __End_c_C_ResAccConFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__End_c_C_ResAccConFHandlingImp(c,session)}
def snd(m : MESSAGES.ResAccCon.F2) : __End_c_C_ResAccConFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__End_c_C_ResAccConFHandlingImp(c,session)}

}


protected  trait End_c_C_ResAccConFHandling extends EPResAccCon_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_ResAccConFHandling extends End_c_C_ResAccConFHandling {
  override protected def __children: List[EPResAccCon_c_C] = List()
  override type implT = __End_c_C_ResAccConFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_c_C.End_c_C_ResAccConFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_ResAccConFHandlingImp(c,session)}

protected case class __End_c_C_ResAccConFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_ResAccConFHandling
}
  
}


}

object HdlRes_c_C{
trait EPHdlRes_c_C extends __EPType_C

object EPHdlRes_c_C extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPHdlRes_c_C] = List(Hdl)
  override type implT = __EPHdlRes_c_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPHdlRes_c_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("a",RoleSet("A")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = Role("c",RoleSet("C")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "HdlRes"
}

protected case class __EPHdlRes_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPHdlRes_c_C
}
  
}


protected  trait Hdl extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPHdlRes_c_C] = List(SndStart,Failed_a_A)
  override type implT = __HdlImp
  override type implNextT = __SndStartImp
override def toString() : String = {"EPHdlRes_c_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndStart extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeSnd
  object SndStart extends SndStart {
  override protected def __children: List[EPHdlRes_c_C] = List(SpawnInterruptOne)
  override type implT = __SndStartImp
  override type implNextT = __SpawnInterruptOneImp
override def toString() : String = {"EPHdlRes_c_C.SndStart"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "Start" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndStartImp(c,session)}

protected case class __SndStartImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndStart
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.HdlRes.Start) : __SpawnInterruptOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SpawnInterruptOneImp(c,session)}
def !(m : MESSAGES.HdlRes.Start) : __SpawnInterruptOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SpawnInterruptOneImp(c,session)}
def snd(m : MESSAGES.HdlRes.Start) : __SpawnInterruptOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SpawnInterruptOneImp(c,session)}

}


  trait SpawnInterruptOne extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptOne extends SpawnInterruptOne {
  override protected def __children: List[EPHdlRes_c_C] = List(End_c_C_HdlRes)
  override type implT = __SpawnInterruptOneImp
  override type implNextT = __End_c_C_HdlResImp
override def toString() : String = {"EPHdlRes_c_C.SpawnInterruptOne"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InOne") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptOne" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptOneImp(c,session)}

protected case class __SpawnInterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptOne
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_c_C_HdlRes extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_HdlRes extends End_c_C_HdlRes {
  override protected def __children: List[EPHdlRes_c_C] = List()
  override type implT = __End_c_C_HdlResImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_c_C.End_c_C_HdlRes"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_HdlResImp(c,session)}

protected case class __End_c_C_HdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_HdlRes
}
  
}



  trait Failed_a_A extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeFDtct
  object Failed_a_A extends Failed_a_A {
  override protected def __children: List[EPHdlRes_c_C] = List(SndF)
  override type implT = __Failed_a_AImp
  override type implNextT = __SndFImp
override def toString() : String = {"EPHdlRes_c_C.Failed_a_A"}
  override def suspect : Role = Role("a",RoleSet("A")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_a_AImp(c,session)}

protected case class __Failed_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_a_A
}
  def failed_a_A(): __SndFImp = {__SndFImp(c,session)}

}


  trait SndF extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF extends SndF {
  override protected def __children: List[EPHdlRes_c_C] = List(SndF1)
  override type implT = __SndFImp
  override type implNextT = __SndF1Imp
override def toString() : String = {"EPHdlRes_c_C.SndF"}
    override def to : RRole = Role("u",RoleSet("U")) 
   override def l : String = "F" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFImp(c,session)}

protected case class __SndFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF
}
  private var notUsed = true
def sndTou_U(m : MESSAGES.HdlRes.F) : __SndF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__SndF1Imp(c,session)}
def !(m : MESSAGES.HdlRes.F) : __SndF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__SndF1Imp(c,session)}
def snd(m : MESSAGES.HdlRes.F) : __SndF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__SndF1Imp(c,session)}

}


  trait SndF1 extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF1 extends SndF1 {
  override protected def __children: List[EPHdlRes_c_C] = List(SndF2)
  override type implT = __SndF1Imp
  override type implNextT = __SndF2Imp
override def toString() : String = {"EPHdlRes_c_C.SndF1"}
    override def to : RRole = RoleSet("InOne") 
   override def l : String = "F1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF1Imp(c,session)}

protected case class __SndF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF1
}
  private var notUsed = true
def sndToInOne(m : MESSAGES.HdlRes.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InOne"),m)
__SndF2Imp(c,session)}
def !(m : MESSAGES.HdlRes.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InOne"),m)
__SndF2Imp(c,session)}
def snd(m : MESSAGES.HdlRes.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InOne"),m)
__SndF2Imp(c,session)}

}


  trait SndF2 extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF2 extends SndF2 {
  override protected def __children: List[EPHdlRes_c_C] = List(End_c_C_HdlResFHandling)
  override type implT = __SndF2Imp
  override type implNextT = __End_c_C_HdlResFHandlingImp
override def toString() : String = {"EPHdlRes_c_C.SndF2"}
    override def to : RRole = RoleSet("InTwo") 
   override def l : String = "F2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF2Imp(c,session)}

protected case class __SndF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF2
}
  private var notUsed = true
def sndToInTwo(m : MESSAGES.HdlRes.F2) : __End_c_C_HdlResFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__End_c_C_HdlResFHandlingImp(c,session)}
def !(m : MESSAGES.HdlRes.F2) : __End_c_C_HdlResFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__End_c_C_HdlResFHandlingImp(c,session)}
def snd(m : MESSAGES.HdlRes.F2) : __End_c_C_HdlResFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__End_c_C_HdlResFHandlingImp(c,session)}

}


protected  trait End_c_C_HdlResFHandling extends EPHdlRes_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_HdlResFHandling extends End_c_C_HdlResFHandling {
  override protected def __children: List[EPHdlRes_c_C] = List()
  override type implT = __End_c_C_HdlResFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_c_C.End_c_C_HdlResFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_HdlResFHandlingImp(c,session)}

protected case class __End_c_C_HdlResFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_HdlResFHandling
}
  
}


}

object InterruptOne_c_C{
trait EPInterruptOne_c_C extends __EPType_C

object EPInterruptOne_c_C extends EPInterruptOne_c_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptOne_c_C] = List(Hdl)
  override type implT = __EPInterruptOne_c_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptOne_c_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iOne",RoleSet("InOne")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = Role("c",RoleSet("C")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptOne"
}

protected case class __EPInterruptOne_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptOne_c_C
}
  
}


protected  trait Hdl extends EPInterruptOne_c_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptOne_c_C] = List(SpawnInterruptTwo,Failed_iOne_InOne)
  override type implT = __HdlImp
  override type implNextT = __SpawnInterruptTwoImp
override def toString() : String = {"EPInterruptOne_c_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnInterruptTwo extends EPInterruptOne_c_C with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptTwo extends SpawnInterruptTwo {
  override protected def __children: List[EPInterruptOne_c_C] = List(End_c_C_InterruptOne)
  override type implT = __SpawnInterruptTwoImp
  override type implNextT = __End_c_C_InterruptOneImp
override def toString() : String = {"EPInterruptOne_c_C.SpawnInterruptTwo"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InTwo") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptTwo" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptTwoImp(c,session)}

protected case class __SpawnInterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptTwo
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_c_C_InterruptOne extends EPInterruptOne_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_InterruptOne extends End_c_C_InterruptOne {
  override protected def __children: List[EPInterruptOne_c_C] = List()
  override type implT = __End_c_C_InterruptOneImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_c_C.End_c_C_InterruptOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_InterruptOneImp(c,session)}

protected case class __End_c_C_InterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_InterruptOne
}
  
}



  trait Failed_iOne_InOne extends EPInterruptOne_c_C with event_lang.dsl.ChannelTypeFDtct
  object Failed_iOne_InOne extends Failed_iOne_InOne {
  override protected def __children: List[EPInterruptOne_c_C] = List(SndF)
  override type implT = __Failed_iOne_InOneImp
  override type implNextT = __SndFImp
override def toString() : String = {"EPInterruptOne_c_C.Failed_iOne_InOne"}
  override def suspect : Role = Role("iOne",RoleSet("InOne")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_iOne_InOneImp(c,session)}

protected case class __Failed_iOne_InOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_iOne_InOne
}
  def failed_iOne_InOne(): __SndFImp = {__SndFImp(c,session)}

}


  trait SndF extends EPInterruptOne_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF extends SndF {
  override protected def __children: List[EPInterruptOne_c_C] = List(SndF1)
  override type implT = __SndFImp
  override type implNextT = __SndF1Imp
override def toString() : String = {"EPInterruptOne_c_C.SndF"}
    override def to : RRole = Role("u",RoleSet("U")) 
   override def l : String = "F" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFImp(c,session)}

protected case class __SndFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF
}
  private var notUsed = true
def sndTou_U(m : MESSAGES.InterruptOne.F) : __SndF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__SndF1Imp(c,session)}
def !(m : MESSAGES.InterruptOne.F) : __SndF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__SndF1Imp(c,session)}
def snd(m : MESSAGES.InterruptOne.F) : __SndF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__SndF1Imp(c,session)}

}


  trait SndF1 extends EPInterruptOne_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF1 extends SndF1 {
  override protected def __children: List[EPInterruptOne_c_C] = List(SndF2)
  override type implT = __SndF1Imp
  override type implNextT = __SndF2Imp
override def toString() : String = {"EPInterruptOne_c_C.SndF1"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "F1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF1Imp(c,session)}

protected case class __SndF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF1
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.InterruptOne.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SndF2Imp(c,session)}
def !(m : MESSAGES.InterruptOne.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SndF2Imp(c,session)}
def snd(m : MESSAGES.InterruptOne.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SndF2Imp(c,session)}

}


  trait SndF2 extends EPInterruptOne_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF2 extends SndF2 {
  override protected def __children: List[EPInterruptOne_c_C] = List(End_c_C_InterruptOneFHandling)
  override type implT = __SndF2Imp
  override type implNextT = __End_c_C_InterruptOneFHandlingImp
override def toString() : String = {"EPInterruptOne_c_C.SndF2"}
    override def to : RRole = RoleSet("InTwo") 
   override def l : String = "F2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF2Imp(c,session)}

protected case class __SndF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF2
}
  private var notUsed = true
def sndToInTwo(m : MESSAGES.InterruptOne.F2) : __End_c_C_InterruptOneFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__End_c_C_InterruptOneFHandlingImp(c,session)}
def !(m : MESSAGES.InterruptOne.F2) : __End_c_C_InterruptOneFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__End_c_C_InterruptOneFHandlingImp(c,session)}
def snd(m : MESSAGES.InterruptOne.F2) : __End_c_C_InterruptOneFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__End_c_C_InterruptOneFHandlingImp(c,session)}

}


protected  trait End_c_C_InterruptOneFHandling extends EPInterruptOne_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_InterruptOneFHandling extends End_c_C_InterruptOneFHandling {
  override protected def __children: List[EPInterruptOne_c_C] = List()
  override type implT = __End_c_C_InterruptOneFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_c_C.End_c_C_InterruptOneFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_InterruptOneFHandlingImp(c,session)}

protected case class __End_c_C_InterruptOneFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_InterruptOneFHandling
}
  
}


}

object InterruptTwo_c_C{
trait EPInterruptTwo_c_C extends __EPType_C

object EPInterruptTwo_c_C extends EPInterruptTwo_c_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptTwo_c_C] = List(Hdl)
  override type implT = __EPInterruptTwo_c_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptTwo_c_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iTwo",RoleSet("InTwo")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = Role("c",RoleSet("C")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptTwo"
}

protected case class __EPInterruptTwo_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptTwo_c_C
}
  
}


protected  trait Hdl extends EPInterruptTwo_c_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptTwo_c_C] = List(End_c_C_InterruptTwo,Failed_iTwo_InTwo)
  override type implT = __HdlImp
  override type implNextT = __End_c_C_InterruptTwoImp
override def toString() : String = {"EPInterruptTwo_c_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_c_C_InterruptTwo extends EPInterruptTwo_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_InterruptTwo extends End_c_C_InterruptTwo {
  override protected def __children: List[EPInterruptTwo_c_C] = List()
  override type implT = __End_c_C_InterruptTwoImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptTwo_c_C.End_c_C_InterruptTwo"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_InterruptTwoImp(c,session)}

protected case class __End_c_C_InterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_InterruptTwo
}
  
}



  trait Failed_iTwo_InTwo extends EPInterruptTwo_c_C with event_lang.dsl.ChannelTypeFDtct
  object Failed_iTwo_InTwo extends Failed_iTwo_InTwo {
  override protected def __children: List[EPInterruptTwo_c_C] = List(SndInterPaus)
  override type implT = __Failed_iTwo_InTwoImp
  override type implNextT = __SndInterPausImp
override def toString() : String = {"EPInterruptTwo_c_C.Failed_iTwo_InTwo"}
  override def suspect : Role = Role("iTwo",RoleSet("InTwo")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_iTwo_InTwoImp(c,session)}

protected case class __Failed_iTwo_InTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_iTwo_InTwo
}
  def failed_iTwo_InTwo(): __SndInterPausImp = {__SndInterPausImp(c,session)}

}


  trait SndInterPaus extends EPInterruptTwo_c_C with event_lang.dsl.ChannelTypeSnd
  object SndInterPaus extends SndInterPaus {
  override protected def __children: List[EPInterruptTwo_c_C] = List(SndF2)
  override type implT = __SndInterPausImp
  override type implNextT = __SndF2Imp
override def toString() : String = {"EPInterruptTwo_c_C.SndInterPaus"}
    override def to : RRole = Role("u",RoleSet("U")) 
   override def l : String = "InterPaus" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndInterPausImp(c,session)}

protected case class __SndInterPausImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndInterPaus
}
  private var notUsed = true
def sndTou_U(m : MESSAGES.InterruptTwo.InterPaus) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__SndF2Imp(c,session)}
def !(m : MESSAGES.InterruptTwo.InterPaus) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__SndF2Imp(c,session)}
def snd(m : MESSAGES.InterruptTwo.InterPaus) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__SndF2Imp(c,session)}

}


  trait SndF2 extends EPInterruptTwo_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF2 extends SndF2 {
  override protected def __children: List[EPInterruptTwo_c_C] = List(SndF3)
  override type implT = __SndF2Imp
  override type implNextT = __SndF3Imp
override def toString() : String = {"EPInterruptTwo_c_C.SndF2"}
    override def to : RRole = RoleSet("InTwo") 
   override def l : String = "F2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF2Imp(c,session)}

protected case class __SndF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF2
}
  private var notUsed = true
def sndToInTwo(m : MESSAGES.InterruptTwo.F2) : __SndF3Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__SndF3Imp(c,session)}
def !(m : MESSAGES.InterruptTwo.F2) : __SndF3Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__SndF3Imp(c,session)}
def snd(m : MESSAGES.InterruptTwo.F2) : __SndF3Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("InTwo"),m)
__SndF3Imp(c,session)}

}


  trait SndF3 extends EPInterruptTwo_c_C with event_lang.dsl.ChannelTypeSnd
  object SndF3 extends SndF3 {
  override protected def __children: List[EPInterruptTwo_c_C] = List(SpawnInterruptTwo)
  override type implT = __SndF3Imp
  override type implNextT = __SpawnInterruptTwoImp
override def toString() : String = {"EPInterruptTwo_c_C.SndF3"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "F3" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF3Imp(c,session)}

protected case class __SndF3Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF3
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.InterruptTwo.F3) : __SpawnInterruptTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SpawnInterruptTwoImp(c,session)}
def !(m : MESSAGES.InterruptTwo.F3) : __SpawnInterruptTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SpawnInterruptTwoImp(c,session)}
def snd(m : MESSAGES.InterruptTwo.F3) : __SpawnInterruptTwoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SpawnInterruptTwoImp(c,session)}

}


  trait SpawnInterruptTwo extends EPInterruptTwo_c_C with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptTwo extends SpawnInterruptTwo {
  override protected def __children: List[EPInterruptTwo_c_C] = List(End_c_C_InterruptTwoFHandling)
  override type implT = __SpawnInterruptTwoImp
  override type implNextT = __End_c_C_InterruptTwoFHandlingImp
override def toString() : String = {"EPInterruptTwo_c_C.SpawnInterruptTwo"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InTwo") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptTwo" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptTwoImp(c,session)}

protected case class __SpawnInterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptTwo
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_c_C_InterruptTwoFHandling extends EPInterruptTwo_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_InterruptTwoFHandling extends End_c_C_InterruptTwoFHandling {
  override protected def __children: List[EPInterruptTwo_c_C] = List()
  override type implT = __End_c_C_InterruptTwoFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptTwo_c_C.End_c_C_InterruptTwoFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_InterruptTwoFHandlingImp(c,session)}

protected case class __End_c_C_InterruptTwoFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_InterruptTwoFHandling
}
  
}


}

}

object A {
val subs : Seq[dsl.ChannelTypeSubS] = List(ResAccCon_A.EPResAccCon_A,HdlRes_a_A.EPHdlRes_a_A,InterruptOne_a_A.EPInterruptOne_a_A,InterruptTwo_a_A.EPInterruptTwo_a_A)
trait __EPType_A extends AbstractChannelType {

}

trait EPType_A[T<: TState] extends AbstractEndPoint[__EPType_A,T] {
override val roleSet: RoleSet = RoleSet("A")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(ResAccCon_A.EPResAccCon_A,HdlRes_a_A.EPHdlRes_a_A,InterruptOne_a_A.EPInterruptOne_a_A,InterruptTwo_a_A.EPInterruptTwo_a_A)

}

object ResAccCon_A{
trait EPResAccCon_A extends __EPType_A

object EPResAccCon_A extends EPResAccCon_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPResAccCon_A] = List(Hdl)
  override type implT = __EPResAccCon_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPResAccCon_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("u",RoleSet("U")) 
  override def argsRs: List[RoleSet] = List(RoleSet("A"), RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = RoleSet("A") 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "ResAccCon"
}

protected case class __EPResAccCon_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPResAccCon_A
}
  
}


protected  trait Hdl extends EPResAccCon_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPResAccCon_A] = List(SpawnHdlRes,RcvF)
  override type implT = __HdlImp
  override type implNextT = __SpawnHdlResImp
override def toString() : String = {"EPResAccCon_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnHdlRes extends EPResAccCon_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnHdlRes extends SpawnHdlRes {
  override protected def __children: List[EPResAccCon_A] = List(End_A_ResAccCon)
  override type implT = __SpawnHdlResImp
  override type implNextT = __End_A_ResAccConImp
override def toString() : String = {"EPResAccCon_A.SpawnHdlRes"}
    override def y: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def pickR: RoleSet = RoleSet("A") 
  override def rs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def name: String = "HdlRes" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnHdlResImp(c,session)}

protected case class __SpawnHdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnHdlRes
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_A_ResAccCon extends EPResAccCon_A with event_lang.dsl.ChannelTypeEnd
protected  object End_A_ResAccCon extends End_A_ResAccCon {
  override protected def __children: List[EPResAccCon_A] = List()
  override type implT = __End_A_ResAccConImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_A.End_A_ResAccCon"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_A_ResAccConImp(c,session)}

protected case class __End_A_ResAccConImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_A_ResAccCon
}
  
}



  trait RcvF extends EPResAccCon_A with event_lang.dsl.ChannelTypeRcv
  object RcvF extends RcvF {
  override protected def __children: List[EPResAccCon_A] = List(End_A_ResAccConFHandling)
  override type implT = __RcvFImp
  override type implNextT = __End_A_ResAccConFHandlingImp
override def toString() : String = {"EPResAccCon_A.RcvF"}
  override type msgT = MESSAGES.ResAccCon.F
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFImp(c,session)}

protected case class __RcvFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF
}
  def rcvFrmc_C : (MESSAGES.ResAccCon.F,__End_A_ResAccConFHandlingImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F],__End_A_ResAccConFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ResAccCon.F,__End_A_ResAccConFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F],__End_A_ResAccConFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.ResAccCon.F = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F]}
def ? : MESSAGES.ResAccCon.F = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.ResAccCon.F]}
def channelCon : __End_A_ResAccConFHandlingImp = {__End_A_ResAccConFHandlingImp(c,session)}

}


protected  trait End_A_ResAccConFHandling extends EPResAccCon_A with event_lang.dsl.ChannelTypeEnd
protected  object End_A_ResAccConFHandling extends End_A_ResAccConFHandling {
  override protected def __children: List[EPResAccCon_A] = List()
  override type implT = __End_A_ResAccConFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPResAccCon_A.End_A_ResAccConFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_A_ResAccConFHandlingImp(c,session)}

protected case class __End_A_ResAccConFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_A_ResAccConFHandling
}
  
}


}

object HdlRes_a_A{
trait EPHdlRes_a_A extends __EPType_A

object EPHdlRes_a_A extends EPHdlRes_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPHdlRes_a_A] = List(Hdl)
  override type implT = __EPHdlRes_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPHdlRes_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("u",RoleSet("U")), Role("c",RoleSet("C"))) 
  override def argsP: Role = Role("a",RoleSet("A")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InOne"), RoleSet("InTwo")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "HdlRes"
}

protected case class __EPHdlRes_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPHdlRes_a_A
}
  
}


protected  trait Hdl extends EPHdlRes_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPHdlRes_a_A] = List(RcvStart,End_a_A_HdlResFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvStartImp
override def toString() : String = {"EPHdlRes_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvStart extends EPHdlRes_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvStart extends RcvStart {
  override protected def __children: List[EPHdlRes_a_A] = List(SpawnInterruptOne)
  override type implT = __RcvStartImp
  override type implNextT = __SpawnInterruptOneImp
override def toString() : String = {"EPHdlRes_a_A.RcvStart"}
  override type msgT = MESSAGES.HdlRes.Start
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "Start"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvStartImp(c,session)}

protected case class __RcvStartImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvStart
}
  def rcvFrmc_C : (MESSAGES.HdlRes.Start,__SpawnInterruptOneImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.Start],__SpawnInterruptOneImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.HdlRes.Start,__SpawnInterruptOneImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.Start],__SpawnInterruptOneImp(c,session))) 
}
def rcvMSG : MESSAGES.HdlRes.Start = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.Start]}
def ? : MESSAGES.HdlRes.Start = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.HdlRes.Start]}
def channelCon : __SpawnInterruptOneImp = {__SpawnInterruptOneImp(c,session)}

}


  trait SpawnInterruptOne extends EPHdlRes_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptOne extends SpawnInterruptOne {
  override protected def __children: List[EPHdlRes_a_A] = List(End_a_A_HdlRes)
  override type implT = __SpawnInterruptOneImp
  override type implNextT = __End_a_A_HdlResImp
override def toString() : String = {"EPHdlRes_a_A.SpawnInterruptOne"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InOne") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptOne" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptOneImp(c,session)}

protected case class __SpawnInterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptOne
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_a_A_HdlRes extends EPHdlRes_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_HdlRes extends End_a_A_HdlRes {
  override protected def __children: List[EPHdlRes_a_A] = List()
  override type implT = __End_a_A_HdlResImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_a_A.End_a_A_HdlRes"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_HdlResImp(c,session)}

protected case class __End_a_A_HdlResImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_HdlRes
}
  
}



protected  trait End_a_A_HdlResFHandling extends EPHdlRes_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_HdlResFHandling extends End_a_A_HdlResFHandling {
  override protected def __children: List[EPHdlRes_a_A] = List()
  override type implT = __End_a_A_HdlResFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPHdlRes_a_A.End_a_A_HdlResFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_HdlResFHandlingImp(c,session)}

protected case class __End_a_A_HdlResFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_HdlResFHandling
}
  
}


}

object InterruptOne_a_A{
trait EPInterruptOne_a_A extends __EPType_A

object EPInterruptOne_a_A extends EPInterruptOne_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptOne_a_A] = List(Hdl)
  override type implT = __EPInterruptOne_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptOne_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iOne",RoleSet("InOne")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptOne"
}

protected case class __EPInterruptOne_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptOne_a_A
}
  
}


protected  trait Hdl extends EPInterruptOne_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptOne_a_A] = List(SpawnInterruptTwo,RcvF1)
  override type implT = __HdlImp
  override type implNextT = __SpawnInterruptTwoImp
override def toString() : String = {"EPInterruptOne_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnInterruptTwo extends EPInterruptOne_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptTwo extends SpawnInterruptTwo {
  override protected def __children: List[EPInterruptOne_a_A] = List(End_a_A_InterruptOne)
  override type implT = __SpawnInterruptTwoImp
  override type implNextT = __End_a_A_InterruptOneImp
override def toString() : String = {"EPInterruptOne_a_A.SpawnInterruptTwo"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InTwo") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptTwo" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptTwoImp(c,session)}

protected case class __SpawnInterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptTwo
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_a_A_InterruptOne extends EPInterruptOne_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_InterruptOne extends End_a_A_InterruptOne {
  override protected def __children: List[EPInterruptOne_a_A] = List()
  override type implT = __End_a_A_InterruptOneImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_a_A.End_a_A_InterruptOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_InterruptOneImp(c,session)}

protected case class __End_a_A_InterruptOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_InterruptOne
}
  
}



  trait RcvF1 extends EPInterruptOne_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvF1 extends RcvF1 {
  override protected def __children: List[EPInterruptOne_a_A] = List(End_a_A_InterruptOneFHandling)
  override type implT = __RcvF1Imp
  override type implNextT = __End_a_A_InterruptOneFHandlingImp
override def toString() : String = {"EPInterruptOne_a_A.RcvF1"}
  override type msgT = MESSAGES.InterruptOne.F1
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF1Imp(c,session)}

protected case class __RcvF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF1
}
  def rcvFrmc_C : (MESSAGES.InterruptOne.F1,__End_a_A_InterruptOneFHandlingImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F1],__End_a_A_InterruptOneFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.InterruptOne.F1,__End_a_A_InterruptOneFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F1],__End_a_A_InterruptOneFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.InterruptOne.F1 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F1]}
def ? : MESSAGES.InterruptOne.F1 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptOne.F1]}
def channelCon : __End_a_A_InterruptOneFHandlingImp = {__End_a_A_InterruptOneFHandlingImp(c,session)}

}


protected  trait End_a_A_InterruptOneFHandling extends EPInterruptOne_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_InterruptOneFHandling extends End_a_A_InterruptOneFHandling {
  override protected def __children: List[EPInterruptOne_a_A] = List()
  override type implT = __End_a_A_InterruptOneFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptOne_a_A.End_a_A_InterruptOneFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_InterruptOneFHandlingImp(c,session)}

protected case class __End_a_A_InterruptOneFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_InterruptOneFHandling
}
  
}


}

object InterruptTwo_a_A{
trait EPInterruptTwo_a_A extends __EPType_A

object EPInterruptTwo_a_A extends EPInterruptTwo_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPInterruptTwo_a_A] = List(Hdl)
  override type implT = __EPInterruptTwo_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPInterruptTwo_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("iTwo",RoleSet("InTwo")) 
  override def argsRs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("c",RoleSet("C")) 
  override def name : String = "InterruptTwo"
}

protected case class __EPInterruptTwo_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPInterruptTwo_a_A
}
  
}


protected  trait Hdl extends EPInterruptTwo_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPInterruptTwo_a_A] = List(RecT,RcvF3)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPInterruptTwo_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPInterruptTwo_a_A with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPInterruptTwo_a_A] = List(SndData)
  override type implT = __RecTImp
  override type implNextT = __SndDataImp
override def toString() : String = {"EPInterruptTwo_a_A.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait SndData extends EPInterruptTwo_a_A with event_lang.dsl.ChannelTypeSnd
  object SndData extends SndData {
  override protected def __children: List[EPInterruptTwo_a_A] = List(T)
  override type implT = __SndDataImp
  override type implNextT = __TImp
override def toString() : String = {"EPInterruptTwo_a_A.SndData"}
    override def to : RRole = Role("u",RoleSet("U")) 
   override def l : String = "Data" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDataImp(c,session)}

protected case class __SndDataImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndData
}
  private var notUsed = true
def sndTou_U(m : MESSAGES.InterruptTwo.Data) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__TImp(c,session)}
def !(m : MESSAGES.InterruptTwo.Data) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__TImp(c,session)}
def snd(m : MESSAGES.InterruptTwo.Data) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("u",RoleSet("U")),m)
__TImp(c,session)}

}


protected  trait T extends EPInterruptTwo_a_A with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPInterruptTwo_a_A] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPInterruptTwo_a_A.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvF3 extends EPInterruptTwo_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvF3 extends RcvF3 {
  override protected def __children: List[EPInterruptTwo_a_A] = List(RcvResume)
  override type implT = __RcvF3Imp
  override type implNextT = __RcvResumeImp
override def toString() : String = {"EPInterruptTwo_a_A.RcvF3"}
  override type msgT = MESSAGES.InterruptTwo.F3
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "F3"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF3Imp(c,session)}

protected case class __RcvF3Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF3
}
  def rcvFrmc_C : (MESSAGES.InterruptTwo.F3,__RcvResumeImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.F3],__RcvResumeImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.InterruptTwo.F3,__RcvResumeImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.F3],__RcvResumeImp(c,session))) 
}
def rcvMSG : MESSAGES.InterruptTwo.F3 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.F3]}
def ? : MESSAGES.InterruptTwo.F3 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.InterruptTwo.F3]}
def channelCon : __RcvResumeImp = {__RcvResumeImp(c,session)}

}


  trait RcvResume extends EPInterruptTwo_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvResume extends RcvResume {
  override protected def __children: List[EPInterruptTwo_a_A] = List(SpawnInterruptTwo)
  override type implT = __RcvResumeImp
  override type implNextT = __SpawnInterruptTwoImp
override def toString() : String = {"EPInterruptTwo_a_A.RcvResume"}
  override type msgT = MESSAGES.InterruptTwo.Resume
   override def frm : Role = Role("u",RoleSet("U")) 
   override def l : String = "Resume"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvResumeImp(c,session)}

protected case class __RcvResumeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvResume
}
  def rcvFrmu_U : (MESSAGES.InterruptTwo.Resume,__SpawnInterruptTwoImp) = {(c.rcv(Role("u",RoleSet("U"))).asInstanceOf[MESSAGES.InterruptTwo.Resume],__SpawnInterruptTwoImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.InterruptTwo.Resume,__SpawnInterruptTwoImp),T]) : T = {
  f((c.rcv(Role("u",RoleSet("U"))).asInstanceOf[MESSAGES.InterruptTwo.Resume],__SpawnInterruptTwoImp(c,session))) 
}
def rcvMSG : MESSAGES.InterruptTwo.Resume = {c.rcv(Role("u",RoleSet("U"))).asInstanceOf[MESSAGES.InterruptTwo.Resume]}
def ? : MESSAGES.InterruptTwo.Resume = {c.rcv(Role("u",RoleSet("U"))).asInstanceOf[MESSAGES.InterruptTwo.Resume]}
def channelCon : __SpawnInterruptTwoImp = {__SpawnInterruptTwoImp(c,session)}

}


  trait SpawnInterruptTwo extends EPInterruptTwo_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnInterruptTwo extends SpawnInterruptTwo {
  override protected def __children: List[EPInterruptTwo_a_A] = List(End_a_A_InterruptTwoFHandling)
  override type implT = __SpawnInterruptTwoImp
  override type implNextT = __End_a_A_InterruptTwoFHandlingImp
override def toString() : String = {"EPInterruptTwo_a_A.SpawnInterruptTwo"}
    override def y: List[Role] = List(Role("c",RoleSet("C")), Role("u",RoleSet("U")), Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("InTwo") 
  override def rs: List[RoleSet] = List(RoleSet("InTwo")) 
  override def name: String = "InterruptTwo" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnInterruptTwoImp(c,session)}

protected case class __SpawnInterruptTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnInterruptTwo
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_a_A_InterruptTwoFHandling extends EPInterruptTwo_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_InterruptTwoFHandling extends End_a_A_InterruptTwoFHandling {
  override protected def __children: List[EPInterruptTwo_a_A] = List()
  override type implT = __End_a_A_InterruptTwoFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPInterruptTwo_a_A.End_a_A_InterruptTwoFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_InterruptTwoFHandlingImp(c,session)}

protected case class __End_a_A_InterruptTwoFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_InterruptTwoFHandling
}
  
}


}

}

}
