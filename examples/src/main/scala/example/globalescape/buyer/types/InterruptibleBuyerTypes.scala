package example.globalescape.buyer.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object InterruptibleBuyer {
object RS {
val Interrupt : RoleSet = RoleSet("Interrupt")
val Bank : RoleSet = RoleSet("Bank")
val Client : RoleSet = RoleSet("Client")
val Seller : RoleSet = RoleSet("Seller")
}

object MESSAGES {
object NegotiationCB {
case class OK() extends MSG {
   override def l : String = "OK"
}

case class NEM() extends MSG {
   override def l : String = "NEM"
}

case class AboardNeg() extends MSG {
   override def l : String = "AboardNeg"
}

}

object SelS {
case class BFailedToI() extends MSG {
   override def l : String = "BFailedToI"
}

case class BFailedToC() extends MSG {
   override def l : String = "BFailedToC"
}

}

object Aboard {
case class AboardToB() extends MSG {
   override def l : String = "AboardToB"
}

case class D() extends MSG {
   override def l : String = "D"
}

case class AboardToC() extends MSG {
   override def l : String = "AboardToC"
}

}

object Main {
case class MFailureToI() extends MSG {
   override def l : String = "MFailureToI"
}

case class MFailureToB() extends MSG {
   override def l : String = "MFailureToB"
}

}

object ThreeBuyers {
case class D() extends MSG {
   override def l : String = "D"
}

case class BuyerInterToInter() extends MSG {
   override def l : String = "BuyerInterToInter"
}

case class Co() extends MSG {
   override def l : String = "Co"
}

case class O() extends MSG {
   override def l : String = "O"
}

case class BuyerInterToB() extends MSG {
   override def l : String = "BuyerInterToB"
}

case class M() extends MSG {
   override def l : String = "M"
}

case class BuyerInterToC() extends MSG {
   override def l : String = "BuyerInterToC"
}

case class A() extends MSG {
   override def l : String = "A"
}

}

}

object PROTOCOLS {
object ThreeBuyers {
val i_Interrupt = Role("i",RoleSet("Interrupt"))
val Interrupt = RoleSet("Interrupt")
val s_Seller = Role("s",RoleSet("Seller"))
val c_Client = Role("c",RoleSet("Client"))
val b_Bank = Role("b",RoleSet("Bank"))
}

object SelS {
val b_Bank = Role("b",RoleSet("Bank"))
val Interrupt = RoleSet("Interrupt")
val s_Seller = Role("s",RoleSet("Seller"))
val c_Client = Role("c",RoleSet("Client"))
}

object Aboard {
val i_Interrupt = Role("i",RoleSet("Interrupt"))
val s_Seller = Role("s",RoleSet("Seller"))
val c_Client = Role("c",RoleSet("Client"))
val b_Bank = Role("b",RoleSet("Bank"))
}

object NegotiationCB {
val i_Interrupt = Role("i",RoleSet("Interrupt"))
val c_Client = Role("c",RoleSet("Client"))
val b_Bank = Role("b",RoleSet("Bank"))
}

object Main {
val c_Client = Role("c",RoleSet("Client"))
val Bank = RoleSet("Bank")
val Interrupt = RoleSet("Interrupt")
val s_Seller = Role("s",RoleSet("Seller"))
}

}

object Interrupt {
val subs : Seq[dsl.ChannelTypeSubS] = List(ThreeBuyers_Interrupt.EPThreeBuyers_Interrupt,SelS_Interrupt.EPSelS_Interrupt,ThreeBuyers_i_Interrupt.EPThreeBuyers_i_Interrupt,NegotiationCB_i_Interrupt.EPNegotiationCB_i_Interrupt,Aboard_i_Interrupt.EPAboard_i_Interrupt,Main_Interrupt.EPMain_Interrupt)
trait __EPType_Interrupt extends AbstractChannelType {

}

trait EPType_Interrupt[T<: TState] extends AbstractEndPoint[__EPType_Interrupt,T] {
override val roleSet: RoleSet = RoleSet("Interrupt")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(ThreeBuyers_Interrupt.EPThreeBuyers_Interrupt,SelS_Interrupt.EPSelS_Interrupt,ThreeBuyers_i_Interrupt.EPThreeBuyers_i_Interrupt,NegotiationCB_i_Interrupt.EPNegotiationCB_i_Interrupt,Aboard_i_Interrupt.EPAboard_i_Interrupt,Main_Interrupt.EPMain_Interrupt)

}

object Aboard_i_Interrupt{
trait EPAboard_i_Interrupt extends __EPType_Interrupt

object EPAboard_i_Interrupt extends EPAboard_i_Interrupt with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPAboard_i_Interrupt] = List(Hdl)
  override type implT = __EPAboard_i_InterruptImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPAboard_i_InterruptImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("i",RoleSet("Interrupt")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Aboard"
}

protected case class __EPAboard_i_InterruptImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPAboard_i_Interrupt
}
  
}


protected  trait Hdl extends EPAboard_i_Interrupt with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPAboard_i_Interrupt] = List(End_i_Interrupt_Aboard,End_i_Interrupt_AboardFHandling)
  override type implT = __HdlImp
  override type implNextT = __End_i_Interrupt_AboardImp
override def toString() : String = {"EPAboard_i_Interrupt.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_i_Interrupt_Aboard extends EPAboard_i_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_i_Interrupt_Aboard extends End_i_Interrupt_Aboard {
  override protected def __children: List[EPAboard_i_Interrupt] = List()
  override type implT = __End_i_Interrupt_AboardImp
  override type implNextT = Nothing
override def toString() : String = {"EPAboard_i_Interrupt.End_i_Interrupt_Aboard"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_Interrupt_AboardImp(c,session)}

protected case class __End_i_Interrupt_AboardImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_Interrupt_Aboard
}
  
}



protected  trait End_i_Interrupt_AboardFHandling extends EPAboard_i_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_i_Interrupt_AboardFHandling extends End_i_Interrupt_AboardFHandling {
  override protected def __children: List[EPAboard_i_Interrupt] = List()
  override type implT = __End_i_Interrupt_AboardFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPAboard_i_Interrupt.End_i_Interrupt_AboardFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_Interrupt_AboardFHandlingImp(c,session)}

protected case class __End_i_Interrupt_AboardFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_Interrupt_AboardFHandling
}
  
}


}

object SelS_Interrupt{
trait EPSelS_Interrupt extends __EPType_Interrupt

object EPSelS_Interrupt extends EPSelS_Interrupt with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelS_Interrupt] = List(Hdl)
  override type implT = __EPSelS_InterruptImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelS_InterruptImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client"))) 
  override def argsP: Role = Role("b",RoleSet("Bank")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def prjTo : RRole = RoleSet("Interrupt") 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "SelS"
}

protected case class __EPSelS_InterruptImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelS_Interrupt
}
  
}


protected  trait Hdl extends EPSelS_Interrupt with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelS_Interrupt] = List(SpawnThreeBuyers,RcvBFailedToI)
  override type implT = __HdlImp
  override type implNextT = __SpawnThreeBuyersImp
override def toString() : String = {"EPSelS_Interrupt.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnThreeBuyers extends EPSelS_Interrupt with event_lang.dsl.ChannelTypeSpawn
  object SpawnThreeBuyers extends SpawnThreeBuyers {
  override protected def __children: List[EPSelS_Interrupt] = List(End_Interrupt_SelS)
  override type implT = __SpawnThreeBuyersImp
  override type implNextT = __End_Interrupt_SelSImp
override def toString() : String = {"EPSelS_Interrupt.SpawnThreeBuyers"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def name: String = "ThreeBuyers" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnThreeBuyersImp(c,session)}

protected case class __SpawnThreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnThreeBuyers
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_Interrupt_SelS extends EPSelS_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_Interrupt_SelS extends End_Interrupt_SelS {
  override protected def __children: List[EPSelS_Interrupt] = List()
  override type implT = __End_Interrupt_SelSImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_Interrupt.End_Interrupt_SelS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Interrupt_SelSImp(c,session)}

protected case class __End_Interrupt_SelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Interrupt_SelS
}
  
}



  trait RcvBFailedToI extends EPSelS_Interrupt with event_lang.dsl.ChannelTypeRcv
  object RcvBFailedToI extends RcvBFailedToI {
  override protected def __children: List[EPSelS_Interrupt] = List(End_Interrupt_SelSFHandling)
  override type implT = __RcvBFailedToIImp
  override type implNextT = __End_Interrupt_SelSFHandlingImp
override def toString() : String = {"EPSelS_Interrupt.RcvBFailedToI"}
  override type msgT = MESSAGES.SelS.BFailedToI
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "BFailedToI"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBFailedToIImp(c,session)}

protected case class __RcvBFailedToIImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBFailedToI
}
  def rcvFrms_Seller : (MESSAGES.SelS.BFailedToI,__End_Interrupt_SelSFHandlingImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.SelS.BFailedToI],__End_Interrupt_SelSFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SelS.BFailedToI,__End_Interrupt_SelSFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.SelS.BFailedToI],__End_Interrupt_SelSFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.SelS.BFailedToI = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.SelS.BFailedToI]}
def ? : MESSAGES.SelS.BFailedToI = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.SelS.BFailedToI]}
def channelCon : __End_Interrupt_SelSFHandlingImp = {__End_Interrupt_SelSFHandlingImp(c,session)}

}


protected  trait End_Interrupt_SelSFHandling extends EPSelS_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_Interrupt_SelSFHandling extends End_Interrupt_SelSFHandling {
  override protected def __children: List[EPSelS_Interrupt] = List()
  override type implT = __End_Interrupt_SelSFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_Interrupt.End_Interrupt_SelSFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Interrupt_SelSFHandlingImp(c,session)}

protected case class __End_Interrupt_SelSFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Interrupt_SelSFHandling
}
  
}


}

object NegotiationCB_i_Interrupt{
trait EPNegotiationCB_i_Interrupt extends __EPType_Interrupt

object EPNegotiationCB_i_Interrupt extends EPNegotiationCB_i_Interrupt with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPNegotiationCB_i_Interrupt] = List(Hdl)
  override type implT = __EPNegotiationCB_i_InterruptImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPNegotiationCB_i_InterruptImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("i",RoleSet("Interrupt")) 
  override def rootRole: Role = Role("c",RoleSet("Client")) 
  override def name : String = "NegotiationCB"
}

protected case class __EPNegotiationCB_i_InterruptImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPNegotiationCB_i_Interrupt
}
  
}


protected  trait Hdl extends EPNegotiationCB_i_Interrupt with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPNegotiationCB_i_Interrupt] = List(End_i_Interrupt_NegotiationCB,End_i_Interrupt_NegotiationCBFHandling)
  override type implT = __HdlImp
  override type implNextT = __End_i_Interrupt_NegotiationCBImp
override def toString() : String = {"EPNegotiationCB_i_Interrupt.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_i_Interrupt_NegotiationCB extends EPNegotiationCB_i_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_i_Interrupt_NegotiationCB extends End_i_Interrupt_NegotiationCB {
  override protected def __children: List[EPNegotiationCB_i_Interrupt] = List()
  override type implT = __End_i_Interrupt_NegotiationCBImp
  override type implNextT = Nothing
override def toString() : String = {"EPNegotiationCB_i_Interrupt.End_i_Interrupt_NegotiationCB"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_Interrupt_NegotiationCBImp(c,session)}

protected case class __End_i_Interrupt_NegotiationCBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_Interrupt_NegotiationCB
}
  
}



protected  trait End_i_Interrupt_NegotiationCBFHandling extends EPNegotiationCB_i_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_i_Interrupt_NegotiationCBFHandling extends End_i_Interrupt_NegotiationCBFHandling {
  override protected def __children: List[EPNegotiationCB_i_Interrupt] = List()
  override type implT = __End_i_Interrupt_NegotiationCBFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPNegotiationCB_i_Interrupt.End_i_Interrupt_NegotiationCBFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_Interrupt_NegotiationCBFHandlingImp(c,session)}

protected case class __End_i_Interrupt_NegotiationCBFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_Interrupt_NegotiationCBFHandling
}
  
}


}

object ThreeBuyers_i_Interrupt{
trait EPThreeBuyers_i_Interrupt extends __EPType_Interrupt

object EPThreeBuyers_i_Interrupt extends EPThreeBuyers_i_Interrupt with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPThreeBuyers_i_Interrupt] = List(Hdl)
  override type implT = __EPThreeBuyers_i_InterruptImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPThreeBuyers_i_InterruptImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def prjTo : RRole = Role("i",RoleSet("Interrupt")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "ThreeBuyers"
}

protected case class __EPThreeBuyers_i_InterruptImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPThreeBuyers_i_Interrupt
}
  
}


protected  trait Hdl extends EPThreeBuyers_i_Interrupt with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPThreeBuyers_i_Interrupt] = List(SpawnNegotiationCB,End_i_Interrupt_ThreeBuyersFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnNegotiationCBImp
override def toString() : String = {"EPThreeBuyers_i_Interrupt.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnNegotiationCB extends EPThreeBuyers_i_Interrupt with event_lang.dsl.ChannelTypeSpawn
  object SpawnNegotiationCB extends SpawnNegotiationCB {
  override protected def __children: List[EPThreeBuyers_i_Interrupt] = List(End_i_Interrupt_ThreeBuyers)
  override type implT = __SpawnNegotiationCBImp
  override type implNextT = __End_i_Interrupt_ThreeBuyersImp
override def toString() : String = {"EPThreeBuyers_i_Interrupt.SpawnNegotiationCB"}
    override def y: List[Role] = List(Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "NegotiationCB" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnNegotiationCBImp(c,session)}

protected case class __SpawnNegotiationCBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnNegotiationCB
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_i_Interrupt_ThreeBuyers extends EPThreeBuyers_i_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_i_Interrupt_ThreeBuyers extends End_i_Interrupt_ThreeBuyers {
  override protected def __children: List[EPThreeBuyers_i_Interrupt] = List()
  override type implT = __End_i_Interrupt_ThreeBuyersImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_i_Interrupt.End_i_Interrupt_ThreeBuyers"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_Interrupt_ThreeBuyersImp(c,session)}

protected case class __End_i_Interrupt_ThreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_Interrupt_ThreeBuyers
}
  
}



protected  trait End_i_Interrupt_ThreeBuyersFHandling extends EPThreeBuyers_i_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_i_Interrupt_ThreeBuyersFHandling extends End_i_Interrupt_ThreeBuyersFHandling {
  override protected def __children: List[EPThreeBuyers_i_Interrupt] = List()
  override type implT = __End_i_Interrupt_ThreeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_i_Interrupt.End_i_Interrupt_ThreeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_Interrupt_ThreeBuyersFHandlingImp(c,session)}

protected case class __End_i_Interrupt_ThreeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_Interrupt_ThreeBuyersFHandling
}
  
}


}

object ThreeBuyers_Interrupt{
trait EPThreeBuyers_Interrupt extends __EPType_Interrupt

object EPThreeBuyers_Interrupt extends EPThreeBuyers_Interrupt with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPThreeBuyers_Interrupt] = List(Hdl)
  override type implT = __EPThreeBuyers_InterruptImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPThreeBuyers_InterruptImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def prjTo : RRole = RoleSet("Interrupt") 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "ThreeBuyers"
}

protected case class __EPThreeBuyers_InterruptImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPThreeBuyers_Interrupt
}
  
}


protected  trait Hdl extends EPThreeBuyers_Interrupt with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPThreeBuyers_Interrupt] = List(SpawnNegotiationCB,RcvBuyerInterToInter)
  override type implT = __HdlImp
  override type implNextT = __SpawnNegotiationCBImp
override def toString() : String = {"EPThreeBuyers_Interrupt.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnNegotiationCB extends EPThreeBuyers_Interrupt with event_lang.dsl.ChannelTypeSpawn
  object SpawnNegotiationCB extends SpawnNegotiationCB {
  override protected def __children: List[EPThreeBuyers_Interrupt] = List(End_Interrupt_ThreeBuyers)
  override type implT = __SpawnNegotiationCBImp
  override type implNextT = __End_Interrupt_ThreeBuyersImp
override def toString() : String = {"EPThreeBuyers_Interrupt.SpawnNegotiationCB"}
    override def y: List[Role] = List(Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "NegotiationCB" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnNegotiationCBImp(c,session)}

protected case class __SpawnNegotiationCBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnNegotiationCB
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_Interrupt_ThreeBuyers extends EPThreeBuyers_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_Interrupt_ThreeBuyers extends End_Interrupt_ThreeBuyers {
  override protected def __children: List[EPThreeBuyers_Interrupt] = List()
  override type implT = __End_Interrupt_ThreeBuyersImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_Interrupt.End_Interrupt_ThreeBuyers"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Interrupt_ThreeBuyersImp(c,session)}

protected case class __End_Interrupt_ThreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Interrupt_ThreeBuyers
}
  
}



  trait RcvBuyerInterToInter extends EPThreeBuyers_Interrupt with event_lang.dsl.ChannelTypeRcv
  object RcvBuyerInterToInter extends RcvBuyerInterToInter {
  override protected def __children: List[EPThreeBuyers_Interrupt] = List(SpawnAboard)
  override type implT = __RcvBuyerInterToInterImp
  override type implNextT = __SpawnAboardImp
override def toString() : String = {"EPThreeBuyers_Interrupt.RcvBuyerInterToInter"}
  override type msgT = MESSAGES.ThreeBuyers.BuyerInterToInter
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "BuyerInterToInter"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBuyerInterToInterImp(c,session)}

protected case class __RcvBuyerInterToInterImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBuyerInterToInter
}
  def rcvFrms_Seller : (MESSAGES.ThreeBuyers.BuyerInterToInter,__SpawnAboardImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToInter],__SpawnAboardImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ThreeBuyers.BuyerInterToInter,__SpawnAboardImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToInter],__SpawnAboardImp(c,session))) 
}
def rcvMSG : MESSAGES.ThreeBuyers.BuyerInterToInter = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToInter]}
def ? : MESSAGES.ThreeBuyers.BuyerInterToInter = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToInter]}
def channelCon : __SpawnAboardImp = {__SpawnAboardImp(c,session)}

}


  trait SpawnAboard extends EPThreeBuyers_Interrupt with event_lang.dsl.ChannelTypeSpawn
  object SpawnAboard extends SpawnAboard {
  override protected def __children: List[EPThreeBuyers_Interrupt] = List(End_Interrupt_ThreeBuyersFHandling)
  override type implT = __SpawnAboardImp
  override type implNextT = __End_Interrupt_ThreeBuyersFHandlingImp
override def toString() : String = {"EPThreeBuyers_Interrupt.SpawnAboard"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "Aboard" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnAboardImp(c,session)}

protected case class __SpawnAboardImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnAboard
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_Interrupt_ThreeBuyersFHandling extends EPThreeBuyers_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_Interrupt_ThreeBuyersFHandling extends End_Interrupt_ThreeBuyersFHandling {
  override protected def __children: List[EPThreeBuyers_Interrupt] = List()
  override type implT = __End_Interrupt_ThreeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_Interrupt.End_Interrupt_ThreeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Interrupt_ThreeBuyersFHandlingImp(c,session)}

protected case class __End_Interrupt_ThreeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Interrupt_ThreeBuyersFHandling
}
  
}


}

object Main_Interrupt{
trait EPMain_Interrupt extends __EPType_Interrupt

object EPMain_Interrupt extends EPMain_Interrupt with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_Interrupt] = List(Hdl)
  override type implT = __EPMain_InterruptImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_InterruptImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller"))) 
  override def argsP: Role = Role("c",RoleSet("Client")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Bank"), RoleSet("Interrupt")) 
  override def prjTo : RRole = RoleSet("Interrupt") 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Main"
}

protected case class __EPMain_InterruptImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_Interrupt
}
  
}


protected  trait Hdl extends EPMain_Interrupt with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_Interrupt] = List(SpawnSelS,RcvMFailureToI)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelSImp
override def toString() : String = {"EPMain_Interrupt.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelS extends EPMain_Interrupt with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelS extends SpawnSelS {
  override protected def __children: List[EPMain_Interrupt] = List(End_Interrupt_Main)
  override type implT = __SpawnSelSImp
  override type implNextT = __End_Interrupt_MainImp
override def toString() : String = {"EPMain_Interrupt.SpawnSelS"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client"))) 
  override def pickR: RoleSet = RoleSet("Bank") 
  override def rs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def name: String = "SelS" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelSImp(c,session)}

protected case class __SpawnSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelS
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_Interrupt_Main extends EPMain_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_Interrupt_Main extends End_Interrupt_Main {
  override protected def __children: List[EPMain_Interrupt] = List()
  override type implT = __End_Interrupt_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Interrupt.End_Interrupt_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Interrupt_MainImp(c,session)}

protected case class __End_Interrupt_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Interrupt_Main
}
  
}



  trait RcvMFailureToI extends EPMain_Interrupt with event_lang.dsl.ChannelTypeRcv
  object RcvMFailureToI extends RcvMFailureToI {
  override protected def __children: List[EPMain_Interrupt] = List(End_Interrupt_MainFHandling)
  override type implT = __RcvMFailureToIImp
  override type implNextT = __End_Interrupt_MainFHandlingImp
override def toString() : String = {"EPMain_Interrupt.RcvMFailureToI"}
  override type msgT = MESSAGES.Main.MFailureToI
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "MFailureToI"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMFailureToIImp(c,session)}

protected case class __RcvMFailureToIImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMFailureToI
}
  def rcvFrms_Seller : (MESSAGES.Main.MFailureToI,__End_Interrupt_MainFHandlingImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.MFailureToI],__End_Interrupt_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.MFailureToI,__End_Interrupt_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.MFailureToI],__End_Interrupt_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.MFailureToI = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.MFailureToI]}
def ? : MESSAGES.Main.MFailureToI = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.MFailureToI]}
def channelCon : __End_Interrupt_MainFHandlingImp = {__End_Interrupt_MainFHandlingImp(c,session)}

}


protected  trait End_Interrupt_MainFHandling extends EPMain_Interrupt with event_lang.dsl.ChannelTypeEnd
protected  object End_Interrupt_MainFHandling extends End_Interrupt_MainFHandling {
  override protected def __children: List[EPMain_Interrupt] = List()
  override type implT = __End_Interrupt_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Interrupt.End_Interrupt_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Interrupt_MainFHandlingImp(c,session)}

protected case class __End_Interrupt_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Interrupt_MainFHandling
}
  
}


}

}

object Bank {
val subs : Seq[dsl.ChannelTypeSubS] = List(ThreeBuyers_b_Bank.EPThreeBuyers_b_Bank,Main_Bank.EPMain_Bank,NegotiationCB_b_Bank.EPNegotiationCB_b_Bank,SelS_b_Bank.EPSelS_b_Bank,Aboard_b_Bank.EPAboard_b_Bank)
trait __EPType_Bank extends AbstractChannelType {

}

trait EPType_Bank[T<: TState] extends AbstractEndPoint[__EPType_Bank,T] {
override val roleSet: RoleSet = RoleSet("Bank")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(ThreeBuyers_b_Bank.EPThreeBuyers_b_Bank,Main_Bank.EPMain_Bank,NegotiationCB_b_Bank.EPNegotiationCB_b_Bank,SelS_b_Bank.EPSelS_b_Bank,Aboard_b_Bank.EPAboard_b_Bank)

}

object SelS_b_Bank{
trait EPSelS_b_Bank extends __EPType_Bank

object EPSelS_b_Bank extends EPSelS_b_Bank with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelS_b_Bank] = List(Hdl)
  override type implT = __EPSelS_b_BankImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelS_b_BankImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client"))) 
  override def argsP: Role = Role("b",RoleSet("Bank")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def prjTo : RRole = Role("b",RoleSet("Bank")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "SelS"
}

protected case class __EPSelS_b_BankImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelS_b_Bank
}
  
}


protected  trait Hdl extends EPSelS_b_Bank with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelS_b_Bank] = List(SpawnThreeBuyers,End_b_Bank_SelSFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnThreeBuyersImp
override def toString() : String = {"EPSelS_b_Bank.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnThreeBuyers extends EPSelS_b_Bank with event_lang.dsl.ChannelTypeSpawn
  object SpawnThreeBuyers extends SpawnThreeBuyers {
  override protected def __children: List[EPSelS_b_Bank] = List(End_b_Bank_SelS)
  override type implT = __SpawnThreeBuyersImp
  override type implNextT = __End_b_Bank_SelSImp
override def toString() : String = {"EPSelS_b_Bank.SpawnThreeBuyers"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def name: String = "ThreeBuyers" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnThreeBuyersImp(c,session)}

protected case class __SpawnThreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnThreeBuyers
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_b_Bank_SelS extends EPSelS_b_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_b_Bank_SelS extends End_b_Bank_SelS {
  override protected def __children: List[EPSelS_b_Bank] = List()
  override type implT = __End_b_Bank_SelSImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_b_Bank.End_b_Bank_SelS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_Bank_SelSImp(c,session)}

protected case class __End_b_Bank_SelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_Bank_SelS
}
  
}



protected  trait End_b_Bank_SelSFHandling extends EPSelS_b_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_b_Bank_SelSFHandling extends End_b_Bank_SelSFHandling {
  override protected def __children: List[EPSelS_b_Bank] = List()
  override type implT = __End_b_Bank_SelSFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_b_Bank.End_b_Bank_SelSFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_Bank_SelSFHandlingImp(c,session)}

protected case class __End_b_Bank_SelSFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_Bank_SelSFHandling
}
  
}


}

object Main_Bank{
trait EPMain_Bank extends __EPType_Bank

object EPMain_Bank extends EPMain_Bank with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_Bank] = List(Hdl)
  override type implT = __EPMain_BankImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_BankImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller"))) 
  override def argsP: Role = Role("c",RoleSet("Client")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Bank"), RoleSet("Interrupt")) 
  override def prjTo : RRole = RoleSet("Bank") 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Main"
}

protected case class __EPMain_BankImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_Bank
}
  
}


protected  trait Hdl extends EPMain_Bank with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_Bank] = List(SpawnSelS,RcvMFailureToB)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelSImp
override def toString() : String = {"EPMain_Bank.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelS extends EPMain_Bank with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelS extends SpawnSelS {
  override protected def __children: List[EPMain_Bank] = List(End_Bank_Main)
  override type implT = __SpawnSelSImp
  override type implNextT = __End_Bank_MainImp
override def toString() : String = {"EPMain_Bank.SpawnSelS"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client"))) 
  override def pickR: RoleSet = RoleSet("Bank") 
  override def rs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def name: String = "SelS" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelSImp(c,session)}

protected case class __SpawnSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelS
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_Bank_Main extends EPMain_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_Bank_Main extends End_Bank_Main {
  override protected def __children: List[EPMain_Bank] = List()
  override type implT = __End_Bank_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Bank.End_Bank_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Bank_MainImp(c,session)}

protected case class __End_Bank_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Bank_Main
}
  
}



  trait RcvMFailureToB extends EPMain_Bank with event_lang.dsl.ChannelTypeRcv
  object RcvMFailureToB extends RcvMFailureToB {
  override protected def __children: List[EPMain_Bank] = List(End_Bank_MainFHandling)
  override type implT = __RcvMFailureToBImp
  override type implNextT = __End_Bank_MainFHandlingImp
override def toString() : String = {"EPMain_Bank.RcvMFailureToB"}
  override type msgT = MESSAGES.Main.MFailureToB
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "MFailureToB"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMFailureToBImp(c,session)}

protected case class __RcvMFailureToBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMFailureToB
}
  def rcvFrms_Seller : (MESSAGES.Main.MFailureToB,__End_Bank_MainFHandlingImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.MFailureToB],__End_Bank_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.MFailureToB,__End_Bank_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.MFailureToB],__End_Bank_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.MFailureToB = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.MFailureToB]}
def ? : MESSAGES.Main.MFailureToB = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.MFailureToB]}
def channelCon : __End_Bank_MainFHandlingImp = {__End_Bank_MainFHandlingImp(c,session)}

}


protected  trait End_Bank_MainFHandling extends EPMain_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_Bank_MainFHandling extends End_Bank_MainFHandling {
  override protected def __children: List[EPMain_Bank] = List()
  override type implT = __End_Bank_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Bank.End_Bank_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Bank_MainFHandlingImp(c,session)}

protected case class __End_Bank_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Bank_MainFHandling
}
  
}


}

object NegotiationCB_b_Bank{
trait EPNegotiationCB_b_Bank extends __EPType_Bank

object EPNegotiationCB_b_Bank extends EPNegotiationCB_b_Bank with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPNegotiationCB_b_Bank] = List(Hdl)
  override type implT = __EPNegotiationCB_b_BankImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPNegotiationCB_b_BankImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("b",RoleSet("Bank")) 
  override def rootRole: Role = Role("c",RoleSet("Client")) 
  override def name : String = "NegotiationCB"
}

protected case class __EPNegotiationCB_b_BankImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPNegotiationCB_b_Bank
}
  
}


protected  trait Hdl extends EPNegotiationCB_b_Bank with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPNegotiationCB_b_Bank] = List(SelOKNEM,RcvAboardNeg)
  override type implT = __HdlImp
  override type implNextT = __SelOKNEMImp
override def toString() : String = {"EPNegotiationCB_b_Bank.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait SelOKNEM extends EPNegotiationCB_b_Bank with event_lang.dsl.ChannelTypeSel
protected  object SelOKNEM extends SelOKNEM {
  override protected def __children: List[EPNegotiationCB_b_Bank] = List(SndOK,SndNEM)
  override type implT = __SelOKNEMImp
  override type implNextT = __SndOKImp
override def toString() : String = {"EPNegotiationCB_b_Bank.SelOKNEM"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOKNEMImp(c,session)}

protected case class __SelOKNEMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOKNEM
}
  private var notUsed = true
def !(m : MESSAGES.NegotiationCB.OK) : __End_b_Bank_NegotiationCBOKImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
 __End_b_Bank_NegotiationCBOKImp(c,session)}
def sndToc_Client(m : MESSAGES.NegotiationCB.OK) : __End_b_Bank_NegotiationCBOKImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
 __End_b_Bank_NegotiationCBOKImp(c,session)}

def !(m : MESSAGES.NegotiationCB.NEM) : __End_b_Bank_NegotiationCBNEMImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
 __End_b_Bank_NegotiationCBNEMImp(c,session)}
def sndToc_Client(m : MESSAGES.NegotiationCB.NEM) : __End_b_Bank_NegotiationCBNEMImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
 __End_b_Bank_NegotiationCBNEMImp(c,session)}

}


  trait SndOK extends EPNegotiationCB_b_Bank with event_lang.dsl.ChannelTypeSnd
  object SndOK extends SndOK {
  override protected def __children: List[EPNegotiationCB_b_Bank] = List(End_b_Bank_NegotiationCBOK)
  override type implT = __SndOKImp
  override type implNextT = __End_b_Bank_NegotiationCBOKImp
override def toString() : String = {"EPNegotiationCB_b_Bank.SndOK"}
    override def to : RRole = Role("c",RoleSet("Client")) 
   override def l : String = "OK" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndOKImp(c,session)}

protected case class __SndOKImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndOK
}
  private var notUsed = true
def sndToc_Client(m : MESSAGES.NegotiationCB.OK) : __End_b_Bank_NegotiationCBOKImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_b_Bank_NegotiationCBOKImp(c,session)}
def !(m : MESSAGES.NegotiationCB.OK) : __End_b_Bank_NegotiationCBOKImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_b_Bank_NegotiationCBOKImp(c,session)}
def snd(m : MESSAGES.NegotiationCB.OK) : __End_b_Bank_NegotiationCBOKImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_b_Bank_NegotiationCBOKImp(c,session)}

}


protected  trait End_b_Bank_NegotiationCBOK extends EPNegotiationCB_b_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_b_Bank_NegotiationCBOK extends End_b_Bank_NegotiationCBOK {
  override protected def __children: List[EPNegotiationCB_b_Bank] = List()
  override type implT = __End_b_Bank_NegotiationCBOKImp
  override type implNextT = Nothing
override def toString() : String = {"EPNegotiationCB_b_Bank.End_b_Bank_NegotiationCBOK"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_Bank_NegotiationCBOKImp(c,session)}

protected case class __End_b_Bank_NegotiationCBOKImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_Bank_NegotiationCBOK
}
  
}



  trait SndNEM extends EPNegotiationCB_b_Bank with event_lang.dsl.ChannelTypeSnd
  object SndNEM extends SndNEM {
  override protected def __children: List[EPNegotiationCB_b_Bank] = List(End_b_Bank_NegotiationCBNEM)
  override type implT = __SndNEMImp
  override type implNextT = __End_b_Bank_NegotiationCBNEMImp
override def toString() : String = {"EPNegotiationCB_b_Bank.SndNEM"}
    override def to : RRole = Role("c",RoleSet("Client")) 
   override def l : String = "NEM" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndNEMImp(c,session)}

protected case class __SndNEMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndNEM
}
  private var notUsed = true
def sndToc_Client(m : MESSAGES.NegotiationCB.NEM) : __End_b_Bank_NegotiationCBNEMImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_b_Bank_NegotiationCBNEMImp(c,session)}
def !(m : MESSAGES.NegotiationCB.NEM) : __End_b_Bank_NegotiationCBNEMImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_b_Bank_NegotiationCBNEMImp(c,session)}
def snd(m : MESSAGES.NegotiationCB.NEM) : __End_b_Bank_NegotiationCBNEMImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_b_Bank_NegotiationCBNEMImp(c,session)}

}


protected  trait End_b_Bank_NegotiationCBNEM extends EPNegotiationCB_b_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_b_Bank_NegotiationCBNEM extends End_b_Bank_NegotiationCBNEM {
  override protected def __children: List[EPNegotiationCB_b_Bank] = List()
  override type implT = __End_b_Bank_NegotiationCBNEMImp
  override type implNextT = Nothing
override def toString() : String = {"EPNegotiationCB_b_Bank.End_b_Bank_NegotiationCBNEM"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_Bank_NegotiationCBNEMImp(c,session)}

protected case class __End_b_Bank_NegotiationCBNEMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_Bank_NegotiationCBNEM
}
  
}



  trait RcvAboardNeg extends EPNegotiationCB_b_Bank with event_lang.dsl.ChannelTypeRcv
  object RcvAboardNeg extends RcvAboardNeg {
  override protected def __children: List[EPNegotiationCB_b_Bank] = List(End_b_Bank_NegotiationCBFHandling)
  override type implT = __RcvAboardNegImp
  override type implNextT = __End_b_Bank_NegotiationCBFHandlingImp
override def toString() : String = {"EPNegotiationCB_b_Bank.RcvAboardNeg"}
  override type msgT = MESSAGES.NegotiationCB.AboardNeg
   override def frm : Role = Role("c",RoleSet("Client")) 
   override def l : String = "AboardNeg"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAboardNegImp(c,session)}

protected case class __RcvAboardNegImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAboardNeg
}
  def rcvFrmc_Client : (MESSAGES.NegotiationCB.AboardNeg,__End_b_Bank_NegotiationCBFHandlingImp) = {(c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.NegotiationCB.AboardNeg],__End_b_Bank_NegotiationCBFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.NegotiationCB.AboardNeg,__End_b_Bank_NegotiationCBFHandlingImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.NegotiationCB.AboardNeg],__End_b_Bank_NegotiationCBFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.NegotiationCB.AboardNeg = {c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.NegotiationCB.AboardNeg]}
def ? : MESSAGES.NegotiationCB.AboardNeg = {c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.NegotiationCB.AboardNeg]}
def channelCon : __End_b_Bank_NegotiationCBFHandlingImp = {__End_b_Bank_NegotiationCBFHandlingImp(c,session)}

}


protected  trait End_b_Bank_NegotiationCBFHandling extends EPNegotiationCB_b_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_b_Bank_NegotiationCBFHandling extends End_b_Bank_NegotiationCBFHandling {
  override protected def __children: List[EPNegotiationCB_b_Bank] = List()
  override type implT = __End_b_Bank_NegotiationCBFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPNegotiationCB_b_Bank.End_b_Bank_NegotiationCBFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_Bank_NegotiationCBFHandlingImp(c,session)}

protected case class __End_b_Bank_NegotiationCBFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_Bank_NegotiationCBFHandling
}
  
}


}

object ThreeBuyers_b_Bank{
trait EPThreeBuyers_b_Bank extends __EPType_Bank

object EPThreeBuyers_b_Bank extends EPThreeBuyers_b_Bank with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPThreeBuyers_b_Bank] = List(Hdl)
  override type implT = __EPThreeBuyers_b_BankImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPThreeBuyers_b_BankImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def prjTo : RRole = Role("b",RoleSet("Bank")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "ThreeBuyers"
}

protected case class __EPThreeBuyers_b_BankImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPThreeBuyers_b_Bank
}
  
}


protected  trait Hdl extends EPThreeBuyers_b_Bank with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPThreeBuyers_b_Bank] = List(RcvCo,RcvBuyerInterToB)
  override type implT = __HdlImp
  override type implNextT = __RcvCoImp
override def toString() : String = {"EPThreeBuyers_b_Bank.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvCo extends EPThreeBuyers_b_Bank with event_lang.dsl.ChannelTypeRcv
  object RcvCo extends RcvCo {
  override protected def __children: List[EPThreeBuyers_b_Bank] = List(SpawnNegotiationCB)
  override type implT = __RcvCoImp
  override type implNextT = __SpawnNegotiationCBImp
override def toString() : String = {"EPThreeBuyers_b_Bank.RcvCo"}
  override type msgT = MESSAGES.ThreeBuyers.Co
   override def frm : Role = Role("c",RoleSet("Client")) 
   override def l : String = "Co"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvCoImp(c,session)}

protected case class __RcvCoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvCo
}
  def rcvFrmc_Client : (MESSAGES.ThreeBuyers.Co,__SpawnNegotiationCBImp) = {(c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.Co],__SpawnNegotiationCBImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ThreeBuyers.Co,__SpawnNegotiationCBImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.Co],__SpawnNegotiationCBImp(c,session))) 
}
def rcvMSG : MESSAGES.ThreeBuyers.Co = {c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.Co]}
def ? : MESSAGES.ThreeBuyers.Co = {c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.Co]}
def channelCon : __SpawnNegotiationCBImp = {__SpawnNegotiationCBImp(c,session)}

}


  trait SpawnNegotiationCB extends EPThreeBuyers_b_Bank with event_lang.dsl.ChannelTypeSpawn
  object SpawnNegotiationCB extends SpawnNegotiationCB {
  override protected def __children: List[EPThreeBuyers_b_Bank] = List(End_b_Bank_ThreeBuyers)
  override type implT = __SpawnNegotiationCBImp
  override type implNextT = __End_b_Bank_ThreeBuyersImp
override def toString() : String = {"EPThreeBuyers_b_Bank.SpawnNegotiationCB"}
    override def y: List[Role] = List(Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "NegotiationCB" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnNegotiationCBImp(c,session)}

protected case class __SpawnNegotiationCBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnNegotiationCB
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_b_Bank_ThreeBuyers extends EPThreeBuyers_b_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_b_Bank_ThreeBuyers extends End_b_Bank_ThreeBuyers {
  override protected def __children: List[EPThreeBuyers_b_Bank] = List()
  override type implT = __End_b_Bank_ThreeBuyersImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_b_Bank.End_b_Bank_ThreeBuyers"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_Bank_ThreeBuyersImp(c,session)}

protected case class __End_b_Bank_ThreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_Bank_ThreeBuyers
}
  
}



  trait RcvBuyerInterToB extends EPThreeBuyers_b_Bank with event_lang.dsl.ChannelTypeRcv
  object RcvBuyerInterToB extends RcvBuyerInterToB {
  override protected def __children: List[EPThreeBuyers_b_Bank] = List(SpawnAboard)
  override type implT = __RcvBuyerInterToBImp
  override type implNextT = __SpawnAboardImp
override def toString() : String = {"EPThreeBuyers_b_Bank.RcvBuyerInterToB"}
  override type msgT = MESSAGES.ThreeBuyers.BuyerInterToB
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "BuyerInterToB"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBuyerInterToBImp(c,session)}

protected case class __RcvBuyerInterToBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBuyerInterToB
}
  def rcvFrms_Seller : (MESSAGES.ThreeBuyers.BuyerInterToB,__SpawnAboardImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToB],__SpawnAboardImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ThreeBuyers.BuyerInterToB,__SpawnAboardImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToB],__SpawnAboardImp(c,session))) 
}
def rcvMSG : MESSAGES.ThreeBuyers.BuyerInterToB = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToB]}
def ? : MESSAGES.ThreeBuyers.BuyerInterToB = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToB]}
def channelCon : __SpawnAboardImp = {__SpawnAboardImp(c,session)}

}


  trait SpawnAboard extends EPThreeBuyers_b_Bank with event_lang.dsl.ChannelTypeSpawn
  object SpawnAboard extends SpawnAboard {
  override protected def __children: List[EPThreeBuyers_b_Bank] = List(End_b_Bank_ThreeBuyersFHandling)
  override type implT = __SpawnAboardImp
  override type implNextT = __End_b_Bank_ThreeBuyersFHandlingImp
override def toString() : String = {"EPThreeBuyers_b_Bank.SpawnAboard"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "Aboard" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnAboardImp(c,session)}

protected case class __SpawnAboardImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnAboard
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_b_Bank_ThreeBuyersFHandling extends EPThreeBuyers_b_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_b_Bank_ThreeBuyersFHandling extends End_b_Bank_ThreeBuyersFHandling {
  override protected def __children: List[EPThreeBuyers_b_Bank] = List()
  override type implT = __End_b_Bank_ThreeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_b_Bank.End_b_Bank_ThreeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_Bank_ThreeBuyersFHandlingImp(c,session)}

protected case class __End_b_Bank_ThreeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_Bank_ThreeBuyersFHandling
}
  
}


}

object Aboard_b_Bank{
trait EPAboard_b_Bank extends __EPType_Bank

object EPAboard_b_Bank extends EPAboard_b_Bank with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPAboard_b_Bank] = List(Hdl)
  override type implT = __EPAboard_b_BankImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPAboard_b_BankImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("b",RoleSet("Bank")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Aboard"
}

protected case class __EPAboard_b_BankImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPAboard_b_Bank
}
  
}


protected  trait Hdl extends EPAboard_b_Bank with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPAboard_b_Bank] = List(End_b_Bank_Aboard,RcvAboardToB)
  override type implT = __HdlImp
  override type implNextT = __End_b_Bank_AboardImp
override def toString() : String = {"EPAboard_b_Bank.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_b_Bank_Aboard extends EPAboard_b_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_b_Bank_Aboard extends End_b_Bank_Aboard {
  override protected def __children: List[EPAboard_b_Bank] = List()
  override type implT = __End_b_Bank_AboardImp
  override type implNextT = Nothing
override def toString() : String = {"EPAboard_b_Bank.End_b_Bank_Aboard"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_Bank_AboardImp(c,session)}

protected case class __End_b_Bank_AboardImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_Bank_Aboard
}
  
}



  trait RcvAboardToB extends EPAboard_b_Bank with event_lang.dsl.ChannelTypeRcv
  object RcvAboardToB extends RcvAboardToB {
  override protected def __children: List[EPAboard_b_Bank] = List(End_b_Bank_AboardFHandling)
  override type implT = __RcvAboardToBImp
  override type implNextT = __End_b_Bank_AboardFHandlingImp
override def toString() : String = {"EPAboard_b_Bank.RcvAboardToB"}
  override type msgT = MESSAGES.Aboard.AboardToB
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "AboardToB"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAboardToBImp(c,session)}

protected case class __RcvAboardToBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAboardToB
}
  def rcvFrms_Seller : (MESSAGES.Aboard.AboardToB,__End_b_Bank_AboardFHandlingImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.AboardToB],__End_b_Bank_AboardFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Aboard.AboardToB,__End_b_Bank_AboardFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.AboardToB],__End_b_Bank_AboardFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Aboard.AboardToB = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.AboardToB]}
def ? : MESSAGES.Aboard.AboardToB = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.AboardToB]}
def channelCon : __End_b_Bank_AboardFHandlingImp = {__End_b_Bank_AboardFHandlingImp(c,session)}

}


protected  trait End_b_Bank_AboardFHandling extends EPAboard_b_Bank with event_lang.dsl.ChannelTypeEnd
protected  object End_b_Bank_AboardFHandling extends End_b_Bank_AboardFHandling {
  override protected def __children: List[EPAboard_b_Bank] = List()
  override type implT = __End_b_Bank_AboardFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPAboard_b_Bank.End_b_Bank_AboardFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_Bank_AboardFHandlingImp(c,session)}

protected case class __End_b_Bank_AboardFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_Bank_AboardFHandling
}
  
}


}

}

object Client {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_c_Client.EPMain_c_Client,SelS_c_Client.EPSelS_c_Client,ThreeBuyers_c_Client.EPThreeBuyers_c_Client,Aboard_c_Client.EPAboard_c_Client,NegotiationCB_c_Client.EPNegotiationCB_c_Client)
trait __EPType_Client extends AbstractChannelType {

}

trait EPType_Client[T<: TState] extends AbstractEndPoint[__EPType_Client,T] {
override val roleSet: RoleSet = RoleSet("Client")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_c_Client.EPMain_c_Client,SelS_c_Client.EPSelS_c_Client,ThreeBuyers_c_Client.EPThreeBuyers_c_Client,Aboard_c_Client.EPAboard_c_Client,NegotiationCB_c_Client.EPNegotiationCB_c_Client)

}

object NegotiationCB_c_Client{
trait EPNegotiationCB_c_Client extends __EPType_Client

object EPNegotiationCB_c_Client extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPNegotiationCB_c_Client] = List(Hdl)
  override type implT = __EPNegotiationCB_c_ClientImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPNegotiationCB_c_ClientImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("c",RoleSet("Client")) 
  override def rootRole: Role = Role("c",RoleSet("Client")) 
  override def name : String = "NegotiationCB"
}

protected case class __EPNegotiationCB_c_ClientImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPNegotiationCB_c_Client
}
  
}


protected  trait Hdl extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPNegotiationCB_c_Client] = List(SelOKNEM,Failed_i_Interrupt)
  override type implT = __HdlImp
  override type implNextT = __SelOKNEMImp
override def toString() : String = {"EPNegotiationCB_c_Client.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait SelOKNEM extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeBrn
protected  object SelOKNEM extends SelOKNEM {
  override protected def __children: List[EPNegotiationCB_c_Client] = List(RcvOK,RcvNEM)
  override type implT = __SelOKNEMImp
  override type implNextT = __RcvOKImp
override def toString() : String = {"EPNegotiationCB_c_Client.SelOKNEM"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOKNEMImp(c,session)}

protected case class __SelOKNEMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOKNEM
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvOK extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeRcv
  object RcvOK extends RcvOK {
  override protected def __children: List[EPNegotiationCB_c_Client] = List(End_c_Client_NegotiationCBOK)
  override type implT = __RcvOKImp
  override type implNextT = __End_c_Client_NegotiationCBOKImp
override def toString() : String = {"EPNegotiationCB_c_Client.RcvOK"}
  override type msgT = MESSAGES.NegotiationCB.OK
   override def frm : Role = Role("b",RoleSet("Bank")) 
   override def l : String = "OK"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvOKImp(c,session)}

protected case class __RcvOKImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvOK
}
  def rcvFrmb_Bank : (MESSAGES.NegotiationCB.OK,__End_c_Client_NegotiationCBOKImp) = {(c.rcv(Role("b",RoleSet("Bank"))).asInstanceOf[MESSAGES.NegotiationCB.OK],__End_c_Client_NegotiationCBOKImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.NegotiationCB.OK,__End_c_Client_NegotiationCBOKImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("Bank"))).asInstanceOf[MESSAGES.NegotiationCB.OK],__End_c_Client_NegotiationCBOKImp(c,session))) 
}
def rcvMSG : MESSAGES.NegotiationCB.OK = {c.rcv(Role("b",RoleSet("Bank"))).asInstanceOf[MESSAGES.NegotiationCB.OK]}
def ? : MESSAGES.NegotiationCB.OK = {c.rcv(Role("b",RoleSet("Bank"))).asInstanceOf[MESSAGES.NegotiationCB.OK]}
def channelCon : __End_c_Client_NegotiationCBOKImp = {__End_c_Client_NegotiationCBOKImp(c,session)}

}


protected  trait End_c_Client_NegotiationCBOK extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_NegotiationCBOK extends End_c_Client_NegotiationCBOK {
  override protected def __children: List[EPNegotiationCB_c_Client] = List()
  override type implT = __End_c_Client_NegotiationCBOKImp
  override type implNextT = Nothing
override def toString() : String = {"EPNegotiationCB_c_Client.End_c_Client_NegotiationCBOK"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_NegotiationCBOKImp(c,session)}

protected case class __End_c_Client_NegotiationCBOKImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_NegotiationCBOK
}
  
}



  trait RcvNEM extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeRcv
  object RcvNEM extends RcvNEM {
  override protected def __children: List[EPNegotiationCB_c_Client] = List(End_c_Client_NegotiationCBNEM)
  override type implT = __RcvNEMImp
  override type implNextT = __End_c_Client_NegotiationCBNEMImp
override def toString() : String = {"EPNegotiationCB_c_Client.RcvNEM"}
  override type msgT = MESSAGES.NegotiationCB.NEM
   override def frm : Role = Role("b",RoleSet("Bank")) 
   override def l : String = "NEM"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvNEMImp(c,session)}

protected case class __RcvNEMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvNEM
}
  def rcvFrmb_Bank : (MESSAGES.NegotiationCB.NEM,__End_c_Client_NegotiationCBNEMImp) = {(c.rcv(Role("b",RoleSet("Bank"))).asInstanceOf[MESSAGES.NegotiationCB.NEM],__End_c_Client_NegotiationCBNEMImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.NegotiationCB.NEM,__End_c_Client_NegotiationCBNEMImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("Bank"))).asInstanceOf[MESSAGES.NegotiationCB.NEM],__End_c_Client_NegotiationCBNEMImp(c,session))) 
}
def rcvMSG : MESSAGES.NegotiationCB.NEM = {c.rcv(Role("b",RoleSet("Bank"))).asInstanceOf[MESSAGES.NegotiationCB.NEM]}
def ? : MESSAGES.NegotiationCB.NEM = {c.rcv(Role("b",RoleSet("Bank"))).asInstanceOf[MESSAGES.NegotiationCB.NEM]}
def channelCon : __End_c_Client_NegotiationCBNEMImp = {__End_c_Client_NegotiationCBNEMImp(c,session)}

}


protected  trait End_c_Client_NegotiationCBNEM extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_NegotiationCBNEM extends End_c_Client_NegotiationCBNEM {
  override protected def __children: List[EPNegotiationCB_c_Client] = List()
  override type implT = __End_c_Client_NegotiationCBNEMImp
  override type implNextT = Nothing
override def toString() : String = {"EPNegotiationCB_c_Client.End_c_Client_NegotiationCBNEM"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_NegotiationCBNEMImp(c,session)}

protected case class __End_c_Client_NegotiationCBNEMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_NegotiationCBNEM
}
  
}



  trait Failed_i_Interrupt extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeFDtct
  object Failed_i_Interrupt extends Failed_i_Interrupt {
  override protected def __children: List[EPNegotiationCB_c_Client] = List(SndAboardNeg)
  override type implT = __Failed_i_InterruptImp
  override type implNextT = __SndAboardNegImp
override def toString() : String = {"EPNegotiationCB_c_Client.Failed_i_Interrupt"}
  override def suspect : Role = Role("i",RoleSet("Interrupt")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_i_InterruptImp(c,session)}

protected case class __Failed_i_InterruptImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_i_Interrupt
}
  def failed_i_Interrupt(): __SndAboardNegImp = {__SndAboardNegImp(c,session)}

}


  trait SndAboardNeg extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeSnd
  object SndAboardNeg extends SndAboardNeg {
  override protected def __children: List[EPNegotiationCB_c_Client] = List(End_c_Client_NegotiationCBFHandling)
  override type implT = __SndAboardNegImp
  override type implNextT = __End_c_Client_NegotiationCBFHandlingImp
override def toString() : String = {"EPNegotiationCB_c_Client.SndAboardNeg"}
    override def to : RRole = Role("b",RoleSet("Bank")) 
   override def l : String = "AboardNeg" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAboardNegImp(c,session)}

protected case class __SndAboardNegImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAboardNeg
}
  private var notUsed = true
def sndTob_Bank(m : MESSAGES.NegotiationCB.AboardNeg) : __End_c_Client_NegotiationCBFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__End_c_Client_NegotiationCBFHandlingImp(c,session)}
def !(m : MESSAGES.NegotiationCB.AboardNeg) : __End_c_Client_NegotiationCBFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__End_c_Client_NegotiationCBFHandlingImp(c,session)}
def snd(m : MESSAGES.NegotiationCB.AboardNeg) : __End_c_Client_NegotiationCBFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__End_c_Client_NegotiationCBFHandlingImp(c,session)}

}


protected  trait End_c_Client_NegotiationCBFHandling extends EPNegotiationCB_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_NegotiationCBFHandling extends End_c_Client_NegotiationCBFHandling {
  override protected def __children: List[EPNegotiationCB_c_Client] = List()
  override type implT = __End_c_Client_NegotiationCBFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPNegotiationCB_c_Client.End_c_Client_NegotiationCBFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_NegotiationCBFHandlingImp(c,session)}

protected case class __End_c_Client_NegotiationCBFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_NegotiationCBFHandling
}
  
}


}

object Aboard_c_Client{
trait EPAboard_c_Client extends __EPType_Client

object EPAboard_c_Client extends EPAboard_c_Client with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPAboard_c_Client] = List(Hdl)
  override type implT = __EPAboard_c_ClientImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPAboard_c_ClientImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("c",RoleSet("Client")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Aboard"
}

protected case class __EPAboard_c_ClientImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPAboard_c_Client
}
  
}


protected  trait Hdl extends EPAboard_c_Client with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPAboard_c_Client] = List(RcvD,RcvAboardToC)
  override type implT = __HdlImp
  override type implNextT = __RcvDImp
override def toString() : String = {"EPAboard_c_Client.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvD extends EPAboard_c_Client with event_lang.dsl.ChannelTypeRcv
  object RcvD extends RcvD {
  override protected def __children: List[EPAboard_c_Client] = List(End_c_Client_Aboard)
  override type implT = __RcvDImp
  override type implNextT = __End_c_Client_AboardImp
override def toString() : String = {"EPAboard_c_Client.RcvD"}
  override type msgT = MESSAGES.Aboard.D
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "D"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDImp(c,session)}

protected case class __RcvDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvD
}
  def rcvFrms_Seller : (MESSAGES.Aboard.D,__End_c_Client_AboardImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.D],__End_c_Client_AboardImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Aboard.D,__End_c_Client_AboardImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.D],__End_c_Client_AboardImp(c,session))) 
}
def rcvMSG : MESSAGES.Aboard.D = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.D]}
def ? : MESSAGES.Aboard.D = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.D]}
def channelCon : __End_c_Client_AboardImp = {__End_c_Client_AboardImp(c,session)}

}


protected  trait End_c_Client_Aboard extends EPAboard_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_Aboard extends End_c_Client_Aboard {
  override protected def __children: List[EPAboard_c_Client] = List()
  override type implT = __End_c_Client_AboardImp
  override type implNextT = Nothing
override def toString() : String = {"EPAboard_c_Client.End_c_Client_Aboard"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_AboardImp(c,session)}

protected case class __End_c_Client_AboardImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_Aboard
}
  
}



  trait RcvAboardToC extends EPAboard_c_Client with event_lang.dsl.ChannelTypeRcv
  object RcvAboardToC extends RcvAboardToC {
  override protected def __children: List[EPAboard_c_Client] = List(End_c_Client_AboardFHandling)
  override type implT = __RcvAboardToCImp
  override type implNextT = __End_c_Client_AboardFHandlingImp
override def toString() : String = {"EPAboard_c_Client.RcvAboardToC"}
  override type msgT = MESSAGES.Aboard.AboardToC
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "AboardToC"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAboardToCImp(c,session)}

protected case class __RcvAboardToCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAboardToC
}
  def rcvFrms_Seller : (MESSAGES.Aboard.AboardToC,__End_c_Client_AboardFHandlingImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.AboardToC],__End_c_Client_AboardFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Aboard.AboardToC,__End_c_Client_AboardFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.AboardToC],__End_c_Client_AboardFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Aboard.AboardToC = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.AboardToC]}
def ? : MESSAGES.Aboard.AboardToC = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Aboard.AboardToC]}
def channelCon : __End_c_Client_AboardFHandlingImp = {__End_c_Client_AboardFHandlingImp(c,session)}

}


protected  trait End_c_Client_AboardFHandling extends EPAboard_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_AboardFHandling extends End_c_Client_AboardFHandling {
  override protected def __children: List[EPAboard_c_Client] = List()
  override type implT = __End_c_Client_AboardFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPAboard_c_Client.End_c_Client_AboardFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_AboardFHandlingImp(c,session)}

protected case class __End_c_Client_AboardFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_AboardFHandling
}
  
}


}

object Main_c_Client{
trait EPMain_c_Client extends __EPType_Client

object EPMain_c_Client extends EPMain_c_Client with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_c_Client] = List(Hdl)
  override type implT = __EPMain_c_ClientImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_c_ClientImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller"))) 
  override def argsP: Role = Role("c",RoleSet("Client")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Bank"), RoleSet("Interrupt")) 
  override def prjTo : RRole = Role("c",RoleSet("Client")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Main"
}

protected case class __EPMain_c_ClientImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_c_Client
}
  
}


protected  trait Hdl extends EPMain_c_Client with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_c_Client] = List(SpawnSelS,End_c_Client_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelSImp
override def toString() : String = {"EPMain_c_Client.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelS extends EPMain_c_Client with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelS extends SpawnSelS {
  override protected def __children: List[EPMain_c_Client] = List(End_c_Client_Main)
  override type implT = __SpawnSelSImp
  override type implNextT = __End_c_Client_MainImp
override def toString() : String = {"EPMain_c_Client.SpawnSelS"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client"))) 
  override def pickR: RoleSet = RoleSet("Bank") 
  override def rs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def name: String = "SelS" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelSImp(c,session)}

protected case class __SpawnSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelS
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_c_Client_Main extends EPMain_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_Main extends End_c_Client_Main {
  override protected def __children: List[EPMain_c_Client] = List()
  override type implT = __End_c_Client_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_c_Client.End_c_Client_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_MainImp(c,session)}

protected case class __End_c_Client_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_Main
}
  
}



protected  trait End_c_Client_MainFHandling extends EPMain_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_MainFHandling extends End_c_Client_MainFHandling {
  override protected def __children: List[EPMain_c_Client] = List()
  override type implT = __End_c_Client_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_c_Client.End_c_Client_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_MainFHandlingImp(c,session)}

protected case class __End_c_Client_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_MainFHandling
}
  
}


}

object ThreeBuyers_c_Client{
trait EPThreeBuyers_c_Client extends __EPType_Client

object EPThreeBuyers_c_Client extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(Hdl)
  override type implT = __EPThreeBuyers_c_ClientImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPThreeBuyers_c_ClientImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def prjTo : RRole = Role("c",RoleSet("Client")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "ThreeBuyers"
}

protected case class __EPThreeBuyers_c_ClientImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPThreeBuyers_c_Client
}
  
}


protected  trait Hdl extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(SndO,RcvBuyerInterToC)
  override type implT = __HdlImp
  override type implNextT = __SndOImp
override def toString() : String = {"EPThreeBuyers_c_Client.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndO extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeSnd
  object SndO extends SndO {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(RcvA)
  override type implT = __SndOImp
  override type implNextT = __RcvAImp
override def toString() : String = {"EPThreeBuyers_c_Client.SndO"}
    override def to : RRole = Role("s",RoleSet("Seller")) 
   override def l : String = "O" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndOImp(c,session)}

protected case class __SndOImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndO
}
  private var notUsed = true
def sndTos_Seller(m : MESSAGES.ThreeBuyers.O) : __RcvAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvAImp(c,session)}
def !(m : MESSAGES.ThreeBuyers.O) : __RcvAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvAImp(c,session)}
def snd(m : MESSAGES.ThreeBuyers.O) : __RcvAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvAImp(c,session)}

}


  trait RcvA extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeRcv
  object RcvA extends RcvA {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(SndCo)
  override type implT = __RcvAImp
  override type implNextT = __SndCoImp
override def toString() : String = {"EPThreeBuyers_c_Client.RcvA"}
  override type msgT = MESSAGES.ThreeBuyers.A
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "A"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAImp(c,session)}

protected case class __RcvAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvA
}
  def rcvFrms_Seller : (MESSAGES.ThreeBuyers.A,__SndCoImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.A],__SndCoImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ThreeBuyers.A,__SndCoImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.A],__SndCoImp(c,session))) 
}
def rcvMSG : MESSAGES.ThreeBuyers.A = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.A]}
def ? : MESSAGES.ThreeBuyers.A = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.A]}
def channelCon : __SndCoImp = {__SndCoImp(c,session)}

}


  trait SndCo extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeSnd
  object SndCo extends SndCo {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(SpawnNegotiationCB)
  override type implT = __SndCoImp
  override type implNextT = __SpawnNegotiationCBImp
override def toString() : String = {"EPThreeBuyers_c_Client.SndCo"}
    override def to : RRole = Role("b",RoleSet("Bank")) 
   override def l : String = "Co" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndCoImp(c,session)}

protected case class __SndCoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndCo
}
  private var notUsed = true
def sndTob_Bank(m : MESSAGES.ThreeBuyers.Co) : __SpawnNegotiationCBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__SpawnNegotiationCBImp(c,session)}
def !(m : MESSAGES.ThreeBuyers.Co) : __SpawnNegotiationCBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__SpawnNegotiationCBImp(c,session)}
def snd(m : MESSAGES.ThreeBuyers.Co) : __SpawnNegotiationCBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__SpawnNegotiationCBImp(c,session)}

}


  trait SpawnNegotiationCB extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeSpawn
  object SpawnNegotiationCB extends SpawnNegotiationCB {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(SndM)
  override type implT = __SpawnNegotiationCBImp
  override type implNextT = __SndMImp
override def toString() : String = {"EPThreeBuyers_c_Client.SpawnNegotiationCB"}
    override def y: List[Role] = List(Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "NegotiationCB" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnNegotiationCBImp(c,session)}

protected case class __SpawnNegotiationCBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnNegotiationCB
}
  // SPAWN is handled internally -- i.e. no use code here
}


  trait SndM extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeSnd
  object SndM extends SndM {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(RcvD)
  override type implT = __SndMImp
  override type implNextT = __RcvDImp
override def toString() : String = {"EPThreeBuyers_c_Client.SndM"}
    override def to : RRole = Role("s",RoleSet("Seller")) 
   override def l : String = "M" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMImp(c,session)}

protected case class __SndMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndM
}
  private var notUsed = true
def sndTos_Seller(m : MESSAGES.ThreeBuyers.M) : __RcvDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvDImp(c,session)}
def !(m : MESSAGES.ThreeBuyers.M) : __RcvDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvDImp(c,session)}
def snd(m : MESSAGES.ThreeBuyers.M) : __RcvDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvDImp(c,session)}

}


  trait RcvD extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeRcv
  object RcvD extends RcvD {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(End_c_Client_ThreeBuyers)
  override type implT = __RcvDImp
  override type implNextT = __End_c_Client_ThreeBuyersImp
override def toString() : String = {"EPThreeBuyers_c_Client.RcvD"}
  override type msgT = MESSAGES.ThreeBuyers.D
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "D"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDImp(c,session)}

protected case class __RcvDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvD
}
  def rcvFrms_Seller : (MESSAGES.ThreeBuyers.D,__End_c_Client_ThreeBuyersImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.D],__End_c_Client_ThreeBuyersImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ThreeBuyers.D,__End_c_Client_ThreeBuyersImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.D],__End_c_Client_ThreeBuyersImp(c,session))) 
}
def rcvMSG : MESSAGES.ThreeBuyers.D = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.D]}
def ? : MESSAGES.ThreeBuyers.D = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.D]}
def channelCon : __End_c_Client_ThreeBuyersImp = {__End_c_Client_ThreeBuyersImp(c,session)}

}


protected  trait End_c_Client_ThreeBuyers extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_ThreeBuyers extends End_c_Client_ThreeBuyers {
  override protected def __children: List[EPThreeBuyers_c_Client] = List()
  override type implT = __End_c_Client_ThreeBuyersImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_c_Client.End_c_Client_ThreeBuyers"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_ThreeBuyersImp(c,session)}

protected case class __End_c_Client_ThreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_ThreeBuyers
}
  
}



  trait RcvBuyerInterToC extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeRcv
  object RcvBuyerInterToC extends RcvBuyerInterToC {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(SpawnAboard)
  override type implT = __RcvBuyerInterToCImp
  override type implNextT = __SpawnAboardImp
override def toString() : String = {"EPThreeBuyers_c_Client.RcvBuyerInterToC"}
  override type msgT = MESSAGES.ThreeBuyers.BuyerInterToC
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "BuyerInterToC"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBuyerInterToCImp(c,session)}

protected case class __RcvBuyerInterToCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBuyerInterToC
}
  def rcvFrms_Seller : (MESSAGES.ThreeBuyers.BuyerInterToC,__SpawnAboardImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToC],__SpawnAboardImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ThreeBuyers.BuyerInterToC,__SpawnAboardImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToC],__SpawnAboardImp(c,session))) 
}
def rcvMSG : MESSAGES.ThreeBuyers.BuyerInterToC = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToC]}
def ? : MESSAGES.ThreeBuyers.BuyerInterToC = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.ThreeBuyers.BuyerInterToC]}
def channelCon : __SpawnAboardImp = {__SpawnAboardImp(c,session)}

}


  trait SpawnAboard extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeSpawn
  object SpawnAboard extends SpawnAboard {
  override protected def __children: List[EPThreeBuyers_c_Client] = List(End_c_Client_ThreeBuyersFHandling)
  override type implT = __SpawnAboardImp
  override type implNextT = __End_c_Client_ThreeBuyersFHandlingImp
override def toString() : String = {"EPThreeBuyers_c_Client.SpawnAboard"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "Aboard" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnAboardImp(c,session)}

protected case class __SpawnAboardImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnAboard
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_c_Client_ThreeBuyersFHandling extends EPThreeBuyers_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_ThreeBuyersFHandling extends End_c_Client_ThreeBuyersFHandling {
  override protected def __children: List[EPThreeBuyers_c_Client] = List()
  override type implT = __End_c_Client_ThreeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_c_Client.End_c_Client_ThreeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_ThreeBuyersFHandlingImp(c,session)}

protected case class __End_c_Client_ThreeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_ThreeBuyersFHandling
}
  
}


}

object SelS_c_Client{
trait EPSelS_c_Client extends __EPType_Client

object EPSelS_c_Client extends EPSelS_c_Client with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelS_c_Client] = List(Hdl)
  override type implT = __EPSelS_c_ClientImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelS_c_ClientImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client"))) 
  override def argsP: Role = Role("b",RoleSet("Bank")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def prjTo : RRole = Role("c",RoleSet("Client")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "SelS"
}

protected case class __EPSelS_c_ClientImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelS_c_Client
}
  
}


protected  trait Hdl extends EPSelS_c_Client with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelS_c_Client] = List(SpawnThreeBuyers,RcvBFailedToC)
  override type implT = __HdlImp
  override type implNextT = __SpawnThreeBuyersImp
override def toString() : String = {"EPSelS_c_Client.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnThreeBuyers extends EPSelS_c_Client with event_lang.dsl.ChannelTypeSpawn
  object SpawnThreeBuyers extends SpawnThreeBuyers {
  override protected def __children: List[EPSelS_c_Client] = List(End_c_Client_SelS)
  override type implT = __SpawnThreeBuyersImp
  override type implNextT = __End_c_Client_SelSImp
override def toString() : String = {"EPSelS_c_Client.SpawnThreeBuyers"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def name: String = "ThreeBuyers" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnThreeBuyersImp(c,session)}

protected case class __SpawnThreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnThreeBuyers
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_c_Client_SelS extends EPSelS_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_SelS extends End_c_Client_SelS {
  override protected def __children: List[EPSelS_c_Client] = List()
  override type implT = __End_c_Client_SelSImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_c_Client.End_c_Client_SelS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_SelSImp(c,session)}

protected case class __End_c_Client_SelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_SelS
}
  
}



  trait RcvBFailedToC extends EPSelS_c_Client with event_lang.dsl.ChannelTypeRcv
  object RcvBFailedToC extends RcvBFailedToC {
  override protected def __children: List[EPSelS_c_Client] = List(End_c_Client_SelSFHandling)
  override type implT = __RcvBFailedToCImp
  override type implNextT = __End_c_Client_SelSFHandlingImp
override def toString() : String = {"EPSelS_c_Client.RcvBFailedToC"}
  override type msgT = MESSAGES.SelS.BFailedToC
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "BFailedToC"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBFailedToCImp(c,session)}

protected case class __RcvBFailedToCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBFailedToC
}
  def rcvFrms_Seller : (MESSAGES.SelS.BFailedToC,__End_c_Client_SelSFHandlingImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.SelS.BFailedToC],__End_c_Client_SelSFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SelS.BFailedToC,__End_c_Client_SelSFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.SelS.BFailedToC],__End_c_Client_SelSFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.SelS.BFailedToC = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.SelS.BFailedToC]}
def ? : MESSAGES.SelS.BFailedToC = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.SelS.BFailedToC]}
def channelCon : __End_c_Client_SelSFHandlingImp = {__End_c_Client_SelSFHandlingImp(c,session)}

}


protected  trait End_c_Client_SelSFHandling extends EPSelS_c_Client with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Client_SelSFHandling extends End_c_Client_SelSFHandling {
  override protected def __children: List[EPSelS_c_Client] = List()
  override type implT = __End_c_Client_SelSFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_c_Client.End_c_Client_SelSFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Client_SelSFHandlingImp(c,session)}

protected case class __End_c_Client_SelSFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Client_SelSFHandling
}
  
}


}

}

object Seller {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_s_Seller.EPMain_s_Seller,ThreeBuyers_s_Seller.EPThreeBuyers_s_Seller,SelS_s_Seller.EPSelS_s_Seller,Aboard_s_Seller.EPAboard_s_Seller)
trait __EPType_Seller extends AbstractChannelType {

}

trait EPType_Seller[T<: TState] extends AbstractEndPoint[__EPType_Seller,T] {
override val roleSet: RoleSet = RoleSet("Seller")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_s_Seller.EPMain_s_Seller,ThreeBuyers_s_Seller.EPThreeBuyers_s_Seller,SelS_s_Seller.EPSelS_s_Seller,Aboard_s_Seller.EPAboard_s_Seller)

}

object Main_s_Seller{
trait EPMain_s_Seller extends __EPType_Seller

object EPMain_s_Seller extends EPMain_s_Seller with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_s_Seller] = List(Hdl)
  override type implT = __EPMain_s_SellerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_s_SellerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller"))) 
  override def argsP: Role = Role("c",RoleSet("Client")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Bank"), RoleSet("Interrupt")) 
  override def prjTo : RRole = Role("s",RoleSet("Seller")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Main"
}

protected case class __EPMain_s_SellerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_s_Seller
}
  
}


protected  trait Hdl extends EPMain_s_Seller with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_s_Seller] = List(SpawnSelS,Failed_c_Client)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelSImp
override def toString() : String = {"EPMain_s_Seller.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelS extends EPMain_s_Seller with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelS extends SpawnSelS {
  override protected def __children: List[EPMain_s_Seller] = List(End_s_Seller_Main)
  override type implT = __SpawnSelSImp
  override type implNextT = __End_s_Seller_MainImp
override def toString() : String = {"EPMain_s_Seller.SpawnSelS"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client"))) 
  override def pickR: RoleSet = RoleSet("Bank") 
  override def rs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def name: String = "SelS" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelSImp(c,session)}

protected case class __SpawnSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelS
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_s_Seller_Main extends EPMain_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_Main extends End_s_Seller_Main {
  override protected def __children: List[EPMain_s_Seller] = List()
  override type implT = __End_s_Seller_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_s_Seller.End_s_Seller_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_MainImp(c,session)}

protected case class __End_s_Seller_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_Main
}
  
}



  trait Failed_c_Client extends EPMain_s_Seller with event_lang.dsl.ChannelTypeFDtct
  object Failed_c_Client extends Failed_c_Client {
  override protected def __children: List[EPMain_s_Seller] = List(SndMFailureToB)
  override type implT = __Failed_c_ClientImp
  override type implNextT = __SndMFailureToBImp
override def toString() : String = {"EPMain_s_Seller.Failed_c_Client"}
  override def suspect : Role = Role("c",RoleSet("Client")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_c_ClientImp(c,session)}

protected case class __Failed_c_ClientImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_c_Client
}
  def failed_c_Client(): __SndMFailureToBImp = {__SndMFailureToBImp(c,session)}

}


  trait SndMFailureToB extends EPMain_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndMFailureToB extends SndMFailureToB {
  override protected def __children: List[EPMain_s_Seller] = List(SndMFailureToI)
  override type implT = __SndMFailureToBImp
  override type implNextT = __SndMFailureToIImp
override def toString() : String = {"EPMain_s_Seller.SndMFailureToB"}
    override def to : RRole = RoleSet("Bank") 
   override def l : String = "MFailureToB" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMFailureToBImp(c,session)}

protected case class __SndMFailureToBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndMFailureToB
}
  private var notUsed = true
def sndToBank(m : MESSAGES.Main.MFailureToB) : __SndMFailureToIImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Bank"),m)
__SndMFailureToIImp(c,session)}
def !(m : MESSAGES.Main.MFailureToB) : __SndMFailureToIImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Bank"),m)
__SndMFailureToIImp(c,session)}
def snd(m : MESSAGES.Main.MFailureToB) : __SndMFailureToIImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Bank"),m)
__SndMFailureToIImp(c,session)}

}


  trait SndMFailureToI extends EPMain_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndMFailureToI extends SndMFailureToI {
  override protected def __children: List[EPMain_s_Seller] = List(End_s_Seller_MainFHandling)
  override type implT = __SndMFailureToIImp
  override type implNextT = __End_s_Seller_MainFHandlingImp
override def toString() : String = {"EPMain_s_Seller.SndMFailureToI"}
    override def to : RRole = RoleSet("Interrupt") 
   override def l : String = "MFailureToI" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMFailureToIImp(c,session)}

protected case class __SndMFailureToIImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndMFailureToI
}
  private var notUsed = true
def sndToInterrupt(m : MESSAGES.Main.MFailureToI) : __End_s_Seller_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Interrupt"),m)
__End_s_Seller_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.MFailureToI) : __End_s_Seller_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Interrupt"),m)
__End_s_Seller_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.MFailureToI) : __End_s_Seller_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Interrupt"),m)
__End_s_Seller_MainFHandlingImp(c,session)}

}


protected  trait End_s_Seller_MainFHandling extends EPMain_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_MainFHandling extends End_s_Seller_MainFHandling {
  override protected def __children: List[EPMain_s_Seller] = List()
  override type implT = __End_s_Seller_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_s_Seller.End_s_Seller_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_MainFHandlingImp(c,session)}

protected case class __End_s_Seller_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_MainFHandling
}
  
}


}

object ThreeBuyers_s_Seller{
trait EPThreeBuyers_s_Seller extends __EPType_Seller

object EPThreeBuyers_s_Seller extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(Hdl)
  override type implT = __EPThreeBuyers_s_SellerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPThreeBuyers_s_SellerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def prjTo : RRole = Role("s",RoleSet("Seller")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "ThreeBuyers"
}

protected case class __EPThreeBuyers_s_SellerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPThreeBuyers_s_Seller
}
  
}


protected  trait Hdl extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(RcvO,Failed_i_Interrupt)
  override type implT = __HdlImp
  override type implNextT = __RcvOImp
override def toString() : String = {"EPThreeBuyers_s_Seller.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvO extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeRcv
  object RcvO extends RcvO {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(SndA)
  override type implT = __RcvOImp
  override type implNextT = __SndAImp
override def toString() : String = {"EPThreeBuyers_s_Seller.RcvO"}
  override type msgT = MESSAGES.ThreeBuyers.O
   override def frm : Role = Role("c",RoleSet("Client")) 
   override def l : String = "O"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvOImp(c,session)}

protected case class __RcvOImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvO
}
  def rcvFrmc_Client : (MESSAGES.ThreeBuyers.O,__SndAImp) = {(c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.O],__SndAImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ThreeBuyers.O,__SndAImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.O],__SndAImp(c,session))) 
}
def rcvMSG : MESSAGES.ThreeBuyers.O = {c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.O]}
def ? : MESSAGES.ThreeBuyers.O = {c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.O]}
def channelCon : __SndAImp = {__SndAImp(c,session)}

}


  trait SndA extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndA extends SndA {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(RcvM)
  override type implT = __SndAImp
  override type implNextT = __RcvMImp
override def toString() : String = {"EPThreeBuyers_s_Seller.SndA"}
    override def to : RRole = Role("c",RoleSet("Client")) 
   override def l : String = "A" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAImp(c,session)}

protected case class __SndAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndA
}
  private var notUsed = true
def sndToc_Client(m : MESSAGES.ThreeBuyers.A) : __RcvMImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__RcvMImp(c,session)}
def !(m : MESSAGES.ThreeBuyers.A) : __RcvMImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__RcvMImp(c,session)}
def snd(m : MESSAGES.ThreeBuyers.A) : __RcvMImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__RcvMImp(c,session)}

}


  trait RcvM extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeRcv
  object RcvM extends RcvM {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(SndD)
  override type implT = __RcvMImp
  override type implNextT = __SndDImp
override def toString() : String = {"EPThreeBuyers_s_Seller.RcvM"}
  override type msgT = MESSAGES.ThreeBuyers.M
   override def frm : Role = Role("c",RoleSet("Client")) 
   override def l : String = "M"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMImp(c,session)}

protected case class __RcvMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvM
}
  def rcvFrmc_Client : (MESSAGES.ThreeBuyers.M,__SndDImp) = {(c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.M],__SndDImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.ThreeBuyers.M,__SndDImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.M],__SndDImp(c,session))) 
}
def rcvMSG : MESSAGES.ThreeBuyers.M = {c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.M]}
def ? : MESSAGES.ThreeBuyers.M = {c.rcv(Role("c",RoleSet("Client"))).asInstanceOf[MESSAGES.ThreeBuyers.M]}
def channelCon : __SndDImp = {__SndDImp(c,session)}

}


  trait SndD extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndD extends SndD {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(End_s_Seller_ThreeBuyers)
  override type implT = __SndDImp
  override type implNextT = __End_s_Seller_ThreeBuyersImp
override def toString() : String = {"EPThreeBuyers_s_Seller.SndD"}
    override def to : RRole = Role("c",RoleSet("Client")) 
   override def l : String = "D" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDImp(c,session)}

protected case class __SndDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndD
}
  private var notUsed = true
def sndToc_Client(m : MESSAGES.ThreeBuyers.D) : __End_s_Seller_ThreeBuyersImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_s_Seller_ThreeBuyersImp(c,session)}
def !(m : MESSAGES.ThreeBuyers.D) : __End_s_Seller_ThreeBuyersImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_s_Seller_ThreeBuyersImp(c,session)}
def snd(m : MESSAGES.ThreeBuyers.D) : __End_s_Seller_ThreeBuyersImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_s_Seller_ThreeBuyersImp(c,session)}

}


protected  trait End_s_Seller_ThreeBuyers extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_ThreeBuyers extends End_s_Seller_ThreeBuyers {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List()
  override type implT = __End_s_Seller_ThreeBuyersImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_s_Seller.End_s_Seller_ThreeBuyers"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_ThreeBuyersImp(c,session)}

protected case class __End_s_Seller_ThreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_ThreeBuyers
}
  
}



  trait Failed_i_Interrupt extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeFDtct
  object Failed_i_Interrupt extends Failed_i_Interrupt {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(SndBuyerInterToC)
  override type implT = __Failed_i_InterruptImp
  override type implNextT = __SndBuyerInterToCImp
override def toString() : String = {"EPThreeBuyers_s_Seller.Failed_i_Interrupt"}
  override def suspect : Role = Role("i",RoleSet("Interrupt")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_i_InterruptImp(c,session)}

protected case class __Failed_i_InterruptImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_i_Interrupt
}
  def failed_i_Interrupt(): __SndBuyerInterToCImp = {__SndBuyerInterToCImp(c,session)}

}


  trait SndBuyerInterToC extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndBuyerInterToC extends SndBuyerInterToC {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(SndBuyerInterToB)
  override type implT = __SndBuyerInterToCImp
  override type implNextT = __SndBuyerInterToBImp
override def toString() : String = {"EPThreeBuyers_s_Seller.SndBuyerInterToC"}
    override def to : RRole = Role("c",RoleSet("Client")) 
   override def l : String = "BuyerInterToC" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBuyerInterToCImp(c,session)}

protected case class __SndBuyerInterToCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBuyerInterToC
}
  private var notUsed = true
def sndToc_Client(m : MESSAGES.ThreeBuyers.BuyerInterToC) : __SndBuyerInterToBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__SndBuyerInterToBImp(c,session)}
def !(m : MESSAGES.ThreeBuyers.BuyerInterToC) : __SndBuyerInterToBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__SndBuyerInterToBImp(c,session)}
def snd(m : MESSAGES.ThreeBuyers.BuyerInterToC) : __SndBuyerInterToBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__SndBuyerInterToBImp(c,session)}

}


  trait SndBuyerInterToB extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndBuyerInterToB extends SndBuyerInterToB {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(SndBuyerInterToInter)
  override type implT = __SndBuyerInterToBImp
  override type implNextT = __SndBuyerInterToInterImp
override def toString() : String = {"EPThreeBuyers_s_Seller.SndBuyerInterToB"}
    override def to : RRole = Role("b",RoleSet("Bank")) 
   override def l : String = "BuyerInterToB" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBuyerInterToBImp(c,session)}

protected case class __SndBuyerInterToBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBuyerInterToB
}
  private var notUsed = true
def sndTob_Bank(m : MESSAGES.ThreeBuyers.BuyerInterToB) : __SndBuyerInterToInterImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__SndBuyerInterToInterImp(c,session)}
def !(m : MESSAGES.ThreeBuyers.BuyerInterToB) : __SndBuyerInterToInterImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__SndBuyerInterToInterImp(c,session)}
def snd(m : MESSAGES.ThreeBuyers.BuyerInterToB) : __SndBuyerInterToInterImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__SndBuyerInterToInterImp(c,session)}

}


  trait SndBuyerInterToInter extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndBuyerInterToInter extends SndBuyerInterToInter {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(SpawnAboard)
  override type implT = __SndBuyerInterToInterImp
  override type implNextT = __SpawnAboardImp
override def toString() : String = {"EPThreeBuyers_s_Seller.SndBuyerInterToInter"}
    override def to : RRole = RoleSet("Interrupt") 
   override def l : String = "BuyerInterToInter" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBuyerInterToInterImp(c,session)}

protected case class __SndBuyerInterToInterImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBuyerInterToInter
}
  private var notUsed = true
def sndToInterrupt(m : MESSAGES.ThreeBuyers.BuyerInterToInter) : __SpawnAboardImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Interrupt"),m)
__SpawnAboardImp(c,session)}
def !(m : MESSAGES.ThreeBuyers.BuyerInterToInter) : __SpawnAboardImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Interrupt"),m)
__SpawnAboardImp(c,session)}
def snd(m : MESSAGES.ThreeBuyers.BuyerInterToInter) : __SpawnAboardImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Interrupt"),m)
__SpawnAboardImp(c,session)}

}


  trait SpawnAboard extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeSpawn
  object SpawnAboard extends SpawnAboard {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List(End_s_Seller_ThreeBuyersFHandling)
  override type implT = __SpawnAboardImp
  override type implNextT = __End_s_Seller_ThreeBuyersFHandlingImp
override def toString() : String = {"EPThreeBuyers_s_Seller.SpawnAboard"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "Aboard" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnAboardImp(c,session)}

protected case class __SpawnAboardImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnAboard
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_s_Seller_ThreeBuyersFHandling extends EPThreeBuyers_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_ThreeBuyersFHandling extends End_s_Seller_ThreeBuyersFHandling {
  override protected def __children: List[EPThreeBuyers_s_Seller] = List()
  override type implT = __End_s_Seller_ThreeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPThreeBuyers_s_Seller.End_s_Seller_ThreeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_ThreeBuyersFHandlingImp(c,session)}

protected case class __End_s_Seller_ThreeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_ThreeBuyersFHandling
}
  
}


}

object SelS_s_Seller{
trait EPSelS_s_Seller extends __EPType_Seller

object EPSelS_s_Seller extends EPSelS_s_Seller with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelS_s_Seller] = List(Hdl)
  override type implT = __EPSelS_s_SellerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelS_s_SellerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client"))) 
  override def argsP: Role = Role("b",RoleSet("Bank")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def prjTo : RRole = Role("s",RoleSet("Seller")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "SelS"
}

protected case class __EPSelS_s_SellerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelS_s_Seller
}
  
}


protected  trait Hdl extends EPSelS_s_Seller with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelS_s_Seller] = List(SpawnThreeBuyers,Failed_b_Bank)
  override type implT = __HdlImp
  override type implNextT = __SpawnThreeBuyersImp
override def toString() : String = {"EPSelS_s_Seller.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnThreeBuyers extends EPSelS_s_Seller with event_lang.dsl.ChannelTypeSpawn
  object SpawnThreeBuyers extends SpawnThreeBuyers {
  override protected def __children: List[EPSelS_s_Seller] = List(End_s_Seller_SelS)
  override type implT = __SpawnThreeBuyersImp
  override type implNextT = __End_s_Seller_SelSImp
override def toString() : String = {"EPSelS_s_Seller.SpawnThreeBuyers"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def pickR: RoleSet = RoleSet("Interrupt") 
  override def rs: List[RoleSet] = List(RoleSet("Interrupt")) 
  override def name: String = "ThreeBuyers" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnThreeBuyersImp(c,session)}

protected case class __SpawnThreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnThreeBuyers
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_s_Seller_SelS extends EPSelS_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_SelS extends End_s_Seller_SelS {
  override protected def __children: List[EPSelS_s_Seller] = List()
  override type implT = __End_s_Seller_SelSImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_s_Seller.End_s_Seller_SelS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_SelSImp(c,session)}

protected case class __End_s_Seller_SelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_SelS
}
  
}



  trait Failed_b_Bank extends EPSelS_s_Seller with event_lang.dsl.ChannelTypeFDtct
  object Failed_b_Bank extends Failed_b_Bank {
  override protected def __children: List[EPSelS_s_Seller] = List(SndBFailedToC)
  override type implT = __Failed_b_BankImp
  override type implNextT = __SndBFailedToCImp
override def toString() : String = {"EPSelS_s_Seller.Failed_b_Bank"}
  override def suspect : Role = Role("b",RoleSet("Bank")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b_BankImp(c,session)}

protected case class __Failed_b_BankImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b_Bank
}
  def failed_b_Bank(): __SndBFailedToCImp = {__SndBFailedToCImp(c,session)}

}


  trait SndBFailedToC extends EPSelS_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndBFailedToC extends SndBFailedToC {
  override protected def __children: List[EPSelS_s_Seller] = List(SndBFailedToI)
  override type implT = __SndBFailedToCImp
  override type implNextT = __SndBFailedToIImp
override def toString() : String = {"EPSelS_s_Seller.SndBFailedToC"}
    override def to : RRole = Role("c",RoleSet("Client")) 
   override def l : String = "BFailedToC" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBFailedToCImp(c,session)}

protected case class __SndBFailedToCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBFailedToC
}
  private var notUsed = true
def sndToc_Client(m : MESSAGES.SelS.BFailedToC) : __SndBFailedToIImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__SndBFailedToIImp(c,session)}
def !(m : MESSAGES.SelS.BFailedToC) : __SndBFailedToIImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__SndBFailedToIImp(c,session)}
def snd(m : MESSAGES.SelS.BFailedToC) : __SndBFailedToIImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__SndBFailedToIImp(c,session)}

}


  trait SndBFailedToI extends EPSelS_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndBFailedToI extends SndBFailedToI {
  override protected def __children: List[EPSelS_s_Seller] = List(End_s_Seller_SelSFHandling)
  override type implT = __SndBFailedToIImp
  override type implNextT = __End_s_Seller_SelSFHandlingImp
override def toString() : String = {"EPSelS_s_Seller.SndBFailedToI"}
    override def to : RRole = RoleSet("Interrupt") 
   override def l : String = "BFailedToI" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBFailedToIImp(c,session)}

protected case class __SndBFailedToIImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBFailedToI
}
  private var notUsed = true
def sndToInterrupt(m : MESSAGES.SelS.BFailedToI) : __End_s_Seller_SelSFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Interrupt"),m)
__End_s_Seller_SelSFHandlingImp(c,session)}
def !(m : MESSAGES.SelS.BFailedToI) : __End_s_Seller_SelSFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Interrupt"),m)
__End_s_Seller_SelSFHandlingImp(c,session)}
def snd(m : MESSAGES.SelS.BFailedToI) : __End_s_Seller_SelSFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Interrupt"),m)
__End_s_Seller_SelSFHandlingImp(c,session)}

}


protected  trait End_s_Seller_SelSFHandling extends EPSelS_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_SelSFHandling extends End_s_Seller_SelSFHandling {
  override protected def __children: List[EPSelS_s_Seller] = List()
  override type implT = __End_s_Seller_SelSFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_s_Seller.End_s_Seller_SelSFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_SelSFHandlingImp(c,session)}

protected case class __End_s_Seller_SelSFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_SelSFHandling
}
  
}


}

object Aboard_s_Seller{
trait EPAboard_s_Seller extends __EPType_Seller

object EPAboard_s_Seller extends EPAboard_s_Seller with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPAboard_s_Seller] = List(Hdl)
  override type implT = __EPAboard_s_SellerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPAboard_s_SellerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("c",RoleSet("Client")), Role("b",RoleSet("Bank"))) 
  override def argsP: Role = Role("i",RoleSet("Interrupt")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("s",RoleSet("Seller")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Aboard"
}

protected case class __EPAboard_s_SellerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPAboard_s_Seller
}
  
}


protected  trait Hdl extends EPAboard_s_Seller with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPAboard_s_Seller] = List(SndD,Failed_i_Interrupt)
  override type implT = __HdlImp
  override type implNextT = __SndDImp
override def toString() : String = {"EPAboard_s_Seller.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndD extends EPAboard_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndD extends SndD {
  override protected def __children: List[EPAboard_s_Seller] = List(End_s_Seller_Aboard)
  override type implT = __SndDImp
  override type implNextT = __End_s_Seller_AboardImp
override def toString() : String = {"EPAboard_s_Seller.SndD"}
    override def to : RRole = Role("c",RoleSet("Client")) 
   override def l : String = "D" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDImp(c,session)}

protected case class __SndDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndD
}
  private var notUsed = true
def sndToc_Client(m : MESSAGES.Aboard.D) : __End_s_Seller_AboardImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_s_Seller_AboardImp(c,session)}
def !(m : MESSAGES.Aboard.D) : __End_s_Seller_AboardImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_s_Seller_AboardImp(c,session)}
def snd(m : MESSAGES.Aboard.D) : __End_s_Seller_AboardImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__End_s_Seller_AboardImp(c,session)}

}


protected  trait End_s_Seller_Aboard extends EPAboard_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_Aboard extends End_s_Seller_Aboard {
  override protected def __children: List[EPAboard_s_Seller] = List()
  override type implT = __End_s_Seller_AboardImp
  override type implNextT = Nothing
override def toString() : String = {"EPAboard_s_Seller.End_s_Seller_Aboard"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_AboardImp(c,session)}

protected case class __End_s_Seller_AboardImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_Aboard
}
  
}



  trait Failed_i_Interrupt extends EPAboard_s_Seller with event_lang.dsl.ChannelTypeFDtct
  object Failed_i_Interrupt extends Failed_i_Interrupt {
  override protected def __children: List[EPAboard_s_Seller] = List(SndAboardToC)
  override type implT = __Failed_i_InterruptImp
  override type implNextT = __SndAboardToCImp
override def toString() : String = {"EPAboard_s_Seller.Failed_i_Interrupt"}
  override def suspect : Role = Role("i",RoleSet("Interrupt")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_i_InterruptImp(c,session)}

protected case class __Failed_i_InterruptImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_i_Interrupt
}
  def failed_i_Interrupt(): __SndAboardToCImp = {__SndAboardToCImp(c,session)}

}


  trait SndAboardToC extends EPAboard_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndAboardToC extends SndAboardToC {
  override protected def __children: List[EPAboard_s_Seller] = List(SndAboardToB)
  override type implT = __SndAboardToCImp
  override type implNextT = __SndAboardToBImp
override def toString() : String = {"EPAboard_s_Seller.SndAboardToC"}
    override def to : RRole = Role("c",RoleSet("Client")) 
   override def l : String = "AboardToC" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAboardToCImp(c,session)}

protected case class __SndAboardToCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAboardToC
}
  private var notUsed = true
def sndToc_Client(m : MESSAGES.Aboard.AboardToC) : __SndAboardToBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__SndAboardToBImp(c,session)}
def !(m : MESSAGES.Aboard.AboardToC) : __SndAboardToBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__SndAboardToBImp(c,session)}
def snd(m : MESSAGES.Aboard.AboardToC) : __SndAboardToBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Client")),m)
__SndAboardToBImp(c,session)}

}


  trait SndAboardToB extends EPAboard_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndAboardToB extends SndAboardToB {
  override protected def __children: List[EPAboard_s_Seller] = List(End_s_Seller_AboardFHandling)
  override type implT = __SndAboardToBImp
  override type implNextT = __End_s_Seller_AboardFHandlingImp
override def toString() : String = {"EPAboard_s_Seller.SndAboardToB"}
    override def to : RRole = Role("b",RoleSet("Bank")) 
   override def l : String = "AboardToB" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAboardToBImp(c,session)}

protected case class __SndAboardToBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAboardToB
}
  private var notUsed = true
def sndTob_Bank(m : MESSAGES.Aboard.AboardToB) : __End_s_Seller_AboardFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__End_s_Seller_AboardFHandlingImp(c,session)}
def !(m : MESSAGES.Aboard.AboardToB) : __End_s_Seller_AboardFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__End_s_Seller_AboardFHandlingImp(c,session)}
def snd(m : MESSAGES.Aboard.AboardToB) : __End_s_Seller_AboardFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("Bank")),m)
__End_s_Seller_AboardFHandlingImp(c,session)}

}


protected  trait End_s_Seller_AboardFHandling extends EPAboard_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_AboardFHandling extends End_s_Seller_AboardFHandling {
  override protected def __children: List[EPAboard_s_Seller] = List()
  override type implT = __End_s_Seller_AboardFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPAboard_s_Seller.End_s_Seller_AboardFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_AboardFHandlingImp(c,session)}

protected case class __End_s_Seller_AboardFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_AboardFHandling
}
  
}


}

}

}
