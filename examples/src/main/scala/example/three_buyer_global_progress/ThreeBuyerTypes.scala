package example.three_buyer_global_progress
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object ThreeBuyer {
object RS {
val C : RoleSet = RoleSet("C")
val S : RoleSet = RoleSet("S")
val B : RoleSet = RoleSet("B")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object threeBuyers {
case class Book(title:String) extends MSG {
   override def l : String = "Book"
}

case class QuitS() extends MSG {
   override def l : String = "QuitS"
}

case class MyShare(p:Int) extends MSG {
   override def l : String = "MyShare"
}

case class F3Bb() extends MSG {
   override def l : String = "F3Bb"
}

case class Addr(s:String) extends MSG {
   override def l : String = "Addr"
}

case class QuitA() extends MSG {
   override def l : String = "QuitA"
}

case class OkS() extends MSG {
   override def l : String = "OkS"
}

case class F3Bs() extends MSG {
   override def l : String = "F3Bs"
}

case class PriceB(p:Int) extends MSG {
   override def l : String = "PriceB"
}

case class OkA() extends MSG {
   override def l : String = "OkA"
}

case class PriceA(p:Int) extends MSG {
   override def l : String = "PriceA"
}

case class ShipD(d:String) extends MSG {
   override def l : String = "ShipD"
}

case class F3BC() extends MSG {
   override def l : String = "F3BC"
}

}

object negotiationBC {
case class YourShare(i:Int) extends MSG {
   override def l : String = "YourShare"
}

case class End() extends MSG {
   override def l : String = "End"
}

case class Ok() extends MSG {
   override def l : String = "Ok"
}

case class No() extends MSG {
   override def l : String = "No"
}

}

object SelS {
case class FSelC() extends MSG {
   override def l : String = "FSelC"
}

case class FSelS() extends MSG {
   override def l : String = "FSelS"
}

case class FSelb() extends MSG {
   override def l : String = "FSelb"
}

}

object Main {
case class FMainF1() extends MSG {
   override def l : String = "FMainF1"
}

case class FMainF2() extends MSG {
   override def l : String = "FMainF2"
}

}

}

object PROTOCOLS {
object negotiationBC {
val c_C = Role("c",RoleSet("C"))
val b_B = Role("b",RoleSet("B"))
}

object SelS {
val s_S = Role("s",RoleSet("S"))
val S = RoleSet("S")
val C = RoleSet("C")
val a_A = Role("a",RoleSet("A"))
val b_B = Role("b",RoleSet("B"))
}

object Main {
val b_B = Role("b",RoleSet("B"))
val C = RoleSet("C")
val S = RoleSet("S")
val a_A = Role("a",RoleSet("A"))
}

object threeBuyers {
val ss_S = Role("ss",RoleSet("S"))
val C = RoleSet("C")
val a_A = Role("a",RoleSet("A"))
val b_B = Role("b",RoleSet("B"))
val s_S = Role("s",RoleSet("S"))
}

}

object C {
val subs : Seq[dsl.ChannelTypeSubS] = List(threeBuyers_C.EPthreeBuyers_C,negotiationBC_c_C.EPnegotiationBC_c_C,SelS_C.EPSelS_C,Main_C.EPMain_C)
trait __EPType_C extends AbstractChannelType {

}

trait EPType_C[T<: TState] extends AbstractEndPoint[__EPType_C,T] {
override val roleSet: RoleSet = RoleSet("C")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(threeBuyers_C.EPthreeBuyers_C,negotiationBC_c_C.EPnegotiationBC_c_C,SelS_C.EPSelS_C,Main_C.EPMain_C)

}

object threeBuyers_C{
trait EPthreeBuyers_C extends __EPType_C

object EPthreeBuyers_C extends EPthreeBuyers_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPthreeBuyers_C] = List(Hdl)
  override type implT = __EPthreeBuyers_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPthreeBuyers_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("ss",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
  override def prjTo : RRole = RoleSet("C") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "threeBuyers"
}

protected case class __EPthreeBuyers_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPthreeBuyers_C
}
  
}


protected  trait Hdl extends EPthreeBuyers_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPthreeBuyers_C] = List(SpawnnegotiationBC,RcvF3BC)
  override type implT = __HdlImp
  override type implNextT = __SpawnnegotiationBCImp
override def toString() : String = {"EPthreeBuyers_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnnegotiationBC extends EPthreeBuyers_C with event_lang.dsl.ChannelTypeSpawn
  object SpawnnegotiationBC extends SpawnnegotiationBC {
  override protected def __children: List[EPthreeBuyers_C] = List(End_C_threeBuyers)
  override type implT = __SpawnnegotiationBCImp
  override type implNextT = __End_C_threeBuyersImp
override def toString() : String = {"EPthreeBuyers_C.SpawnnegotiationBC"}
    override def y: List[Role] = List(Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "negotiationBC" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnnegotiationBCImp(c,session)}

protected case class __SpawnnegotiationBCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnnegotiationBC
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_C_threeBuyers extends EPthreeBuyers_C with event_lang.dsl.ChannelTypeEnd
protected  object End_C_threeBuyers extends End_C_threeBuyers {
  override protected def __children: List[EPthreeBuyers_C] = List()
  override type implT = __End_C_threeBuyersImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_C.End_C_threeBuyers"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_C_threeBuyersImp(c,session)}

protected case class __End_C_threeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_C_threeBuyers
}
  
}



  trait RcvF3BC extends EPthreeBuyers_C with event_lang.dsl.ChannelTypeRcv
  object RcvF3BC extends RcvF3BC {
  override protected def __children: List[EPthreeBuyers_C] = List(End_C_threeBuyersFHandling)
  override type implT = __RcvF3BCImp
  override type implNextT = __End_C_threeBuyersFHandlingImp
override def toString() : String = {"EPthreeBuyers_C.RcvF3BC"}
  override type msgT = MESSAGES.threeBuyers.F3BC
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "F3BC"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF3BCImp(c,session)}

protected case class __RcvF3BCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF3BC
}
  def rcvFrma_A : (MESSAGES.threeBuyers.F3BC,__End_C_threeBuyersFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3BC],__End_C_threeBuyersFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.F3BC,__End_C_threeBuyersFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3BC],__End_C_threeBuyersFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.F3BC = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3BC]}
def ? : MESSAGES.threeBuyers.F3BC = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3BC]}
def channelCon : __End_C_threeBuyersFHandlingImp = {__End_C_threeBuyersFHandlingImp(c,session)}

}


protected  trait End_C_threeBuyersFHandling extends EPthreeBuyers_C with event_lang.dsl.ChannelTypeEnd
protected  object End_C_threeBuyersFHandling extends End_C_threeBuyersFHandling {
  override protected def __children: List[EPthreeBuyers_C] = List()
  override type implT = __End_C_threeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_C.End_C_threeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_C_threeBuyersFHandlingImp(c,session)}

protected case class __End_C_threeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_C_threeBuyersFHandling
}
  
}


}

object negotiationBC_c_C{
trait EPnegotiationBC_c_C extends __EPType_C

object EPnegotiationBC_c_C extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPnegotiationBC_c_C] = List(Hdl)
  override type implT = __EPnegotiationBC_c_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPnegotiationBC_c_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("c",RoleSet("C")) 
  override def rootRole: Role = Role("b",RoleSet("B")) 
  override def name : String = "negotiationBC"
}

protected case class __EPnegotiationBC_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPnegotiationBC_c_C
}
  
}


protected  trait Hdl extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPnegotiationBC_c_C] = List(SelYourShareEnd,End_c_C_negotiationBCFHandling)
  override type implT = __HdlImp
  override type implNextT = __SelYourShareEndImp
override def toString() : String = {"EPnegotiationBC_c_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait SelYourShareEnd extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeBrn
protected  object SelYourShareEnd extends SelYourShareEnd {
  override protected def __children: List[EPnegotiationBC_c_C] = List(RcvYourShare,RcvEnd)
  override type implT = __SelYourShareEndImp
  override type implNextT = __RcvYourShareImp
override def toString() : String = {"EPnegotiationBC_c_C.SelYourShareEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelYourShareEndImp(c,session)}

protected case class __SelYourShareEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelYourShareEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvYourShare extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvYourShare extends RcvYourShare {
  override protected def __children: List[EPnegotiationBC_c_C] = List(SelOkNo)
  override type implT = __RcvYourShareImp
  override type implNextT = __SelOkNoImp
override def toString() : String = {"EPnegotiationBC_c_C.RcvYourShare"}
  override type msgT = MESSAGES.negotiationBC.YourShare
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "YourShare"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvYourShareImp(c,session)}

protected case class __RcvYourShareImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvYourShare
}
  def rcvFrmb_B : (MESSAGES.negotiationBC.YourShare,__SelOkNoImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.negotiationBC.YourShare],__SelOkNoImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.negotiationBC.YourShare,__SelOkNoImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.negotiationBC.YourShare],__SelOkNoImp(c,session))) 
}
def rcvMSG : MESSAGES.negotiationBC.YourShare = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.negotiationBC.YourShare]}
def ? : MESSAGES.negotiationBC.YourShare = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.negotiationBC.YourShare]}
def channelCon : __SelOkNoImp = {__SelOkNoImp(c,session)}

}


protected  trait SelOkNo extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeSel
protected  object SelOkNo extends SelOkNo {
  override protected def __children: List[EPnegotiationBC_c_C] = List(SndOk,SndNo)
  override type implT = __SelOkNoImp
  override type implNextT = __SndOkImp
override def toString() : String = {"EPnegotiationBC_c_C.SelOkNo"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOkNoImp(c,session)}

protected case class __SelOkNoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOkNo
}
  private var notUsed = true
def !(m : MESSAGES.negotiationBC.Ok) : __End_c_C_negotiationBCOk_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __End_c_C_negotiationBCOk_YourShareImp(c,session)}
def sndTob_B(m : MESSAGES.negotiationBC.Ok) : __End_c_C_negotiationBCOk_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __End_c_C_negotiationBCOk_YourShareImp(c,session)}

def !(m : MESSAGES.negotiationBC.No) : __End_c_C_negotiationBCNo_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __End_c_C_negotiationBCNo_YourShareImp(c,session)}
def sndTob_B(m : MESSAGES.negotiationBC.No) : __End_c_C_negotiationBCNo_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __End_c_C_negotiationBCNo_YourShareImp(c,session)}

}


  trait SndOk extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeSnd
  object SndOk extends SndOk {
  override protected def __children: List[EPnegotiationBC_c_C] = List(End_c_C_negotiationBCOk_YourShare)
  override type implT = __SndOkImp
  override type implNextT = __End_c_C_negotiationBCOk_YourShareImp
override def toString() : String = {"EPnegotiationBC_c_C.SndOk"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "Ok" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndOkImp(c,session)}

protected case class __SndOkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndOk
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.negotiationBC.Ok) : __End_c_C_negotiationBCOk_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_c_C_negotiationBCOk_YourShareImp(c,session)}
def !(m : MESSAGES.negotiationBC.Ok) : __End_c_C_negotiationBCOk_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_c_C_negotiationBCOk_YourShareImp(c,session)}
def snd(m : MESSAGES.negotiationBC.Ok) : __End_c_C_negotiationBCOk_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_c_C_negotiationBCOk_YourShareImp(c,session)}

}


protected  trait End_c_C_negotiationBCOk_YourShare extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_negotiationBCOk_YourShare extends End_c_C_negotiationBCOk_YourShare {
  override protected def __children: List[EPnegotiationBC_c_C] = List()
  override type implT = __End_c_C_negotiationBCOk_YourShareImp
  override type implNextT = Nothing
override def toString() : String = {"EPnegotiationBC_c_C.End_c_C_negotiationBCOk_YourShare"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_negotiationBCOk_YourShareImp(c,session)}

protected case class __End_c_C_negotiationBCOk_YourShareImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_negotiationBCOk_YourShare
}
  
}



  trait SndNo extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeSnd
  object SndNo extends SndNo {
  override protected def __children: List[EPnegotiationBC_c_C] = List(End_c_C_negotiationBCNo_YourShare)
  override type implT = __SndNoImp
  override type implNextT = __End_c_C_negotiationBCNo_YourShareImp
override def toString() : String = {"EPnegotiationBC_c_C.SndNo"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "No" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndNoImp(c,session)}

protected case class __SndNoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndNo
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.negotiationBC.No) : __End_c_C_negotiationBCNo_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_c_C_negotiationBCNo_YourShareImp(c,session)}
def !(m : MESSAGES.negotiationBC.No) : __End_c_C_negotiationBCNo_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_c_C_negotiationBCNo_YourShareImp(c,session)}
def snd(m : MESSAGES.negotiationBC.No) : __End_c_C_negotiationBCNo_YourShareImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_c_C_negotiationBCNo_YourShareImp(c,session)}

}


protected  trait End_c_C_negotiationBCNo_YourShare extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_negotiationBCNo_YourShare extends End_c_C_negotiationBCNo_YourShare {
  override protected def __children: List[EPnegotiationBC_c_C] = List()
  override type implT = __End_c_C_negotiationBCNo_YourShareImp
  override type implNextT = Nothing
override def toString() : String = {"EPnegotiationBC_c_C.End_c_C_negotiationBCNo_YourShare"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_negotiationBCNo_YourShareImp(c,session)}

protected case class __End_c_C_negotiationBCNo_YourShareImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_negotiationBCNo_YourShare
}
  
}



  trait RcvEnd extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPnegotiationBC_c_C] = List(End_c_C_negotiationBCEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_c_C_negotiationBCEndImp
override def toString() : String = {"EPnegotiationBC_c_C.RcvEnd"}
  override type msgT = MESSAGES.negotiationBC.End
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrmb_B : (MESSAGES.negotiationBC.End,__End_c_C_negotiationBCEndImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.negotiationBC.End],__End_c_C_negotiationBCEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.negotiationBC.End,__End_c_C_negotiationBCEndImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.negotiationBC.End],__End_c_C_negotiationBCEndImp(c,session))) 
}
def rcvMSG : MESSAGES.negotiationBC.End = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.negotiationBC.End]}
def ? : MESSAGES.negotiationBC.End = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.negotiationBC.End]}
def channelCon : __End_c_C_negotiationBCEndImp = {__End_c_C_negotiationBCEndImp(c,session)}

}


protected  trait End_c_C_negotiationBCEnd extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_negotiationBCEnd extends End_c_C_negotiationBCEnd {
  override protected def __children: List[EPnegotiationBC_c_C] = List()
  override type implT = __End_c_C_negotiationBCEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPnegotiationBC_c_C.End_c_C_negotiationBCEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_negotiationBCEndImp(c,session)}

protected case class __End_c_C_negotiationBCEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_negotiationBCEnd
}
  
}



protected  trait End_c_C_negotiationBCFHandling extends EPnegotiationBC_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_negotiationBCFHandling extends End_c_C_negotiationBCFHandling {
  override protected def __children: List[EPnegotiationBC_c_C] = List()
  override type implT = __End_c_C_negotiationBCFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPnegotiationBC_c_C.End_c_C_negotiationBCFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_negotiationBCFHandlingImp(c,session)}

protected case class __End_c_C_negotiationBCFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_negotiationBCFHandling
}
  
}


}

object SelS_C{
trait EPSelS_C extends __EPType_C

object EPSelS_C extends EPSelS_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelS_C] = List(Hdl)
  override type implT = __EPSelS_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelS_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("s",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("S"), RoleSet("C")) 
  override def prjTo : RRole = RoleSet("C") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "SelS"
}

protected case class __EPSelS_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelS_C
}
  
}


protected  trait Hdl extends EPSelS_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelS_C] = List(SpawnthreeBuyers,RcvFSelC)
  override type implT = __HdlImp
  override type implNextT = __SpawnthreeBuyersImp
override def toString() : String = {"EPSelS_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnthreeBuyers extends EPSelS_C with event_lang.dsl.ChannelTypeSpawn
  object SpawnthreeBuyers extends SpawnthreeBuyers {
  override protected def __children: List[EPSelS_C] = List(End_C_SelS)
  override type implT = __SpawnthreeBuyersImp
  override type implNextT = __End_C_SelSImp
override def toString() : String = {"EPSelS_C.SpawnthreeBuyers"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def pickR: RoleSet = RoleSet("S") 
  override def rs: List[RoleSet] = List(RoleSet("C")) 
  override def name: String = "threeBuyers" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnthreeBuyersImp(c,session)}

protected case class __SpawnthreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnthreeBuyers
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_C_SelS extends EPSelS_C with event_lang.dsl.ChannelTypeEnd
protected  object End_C_SelS extends End_C_SelS {
  override protected def __children: List[EPSelS_C] = List()
  override type implT = __End_C_SelSImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_C.End_C_SelS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_C_SelSImp(c,session)}

protected case class __End_C_SelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_C_SelS
}
  
}



  trait RcvFSelC extends EPSelS_C with event_lang.dsl.ChannelTypeRcv
  object RcvFSelC extends RcvFSelC {
  override protected def __children: List[EPSelS_C] = List(End_C_SelSFHandling)
  override type implT = __RcvFSelCImp
  override type implNextT = __End_C_SelSFHandlingImp
override def toString() : String = {"EPSelS_C.RcvFSelC"}
  override type msgT = MESSAGES.SelS.FSelC
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FSelC"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFSelCImp(c,session)}

protected case class __RcvFSelCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFSelC
}
  def rcvFrma_A : (MESSAGES.SelS.FSelC,__End_C_SelSFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelC],__End_C_SelSFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SelS.FSelC,__End_C_SelSFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelC],__End_C_SelSFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.SelS.FSelC = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelC]}
def ? : MESSAGES.SelS.FSelC = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelC]}
def channelCon : __End_C_SelSFHandlingImp = {__End_C_SelSFHandlingImp(c,session)}

}


protected  trait End_C_SelSFHandling extends EPSelS_C with event_lang.dsl.ChannelTypeEnd
protected  object End_C_SelSFHandling extends End_C_SelSFHandling {
  override protected def __children: List[EPSelS_C] = List()
  override type implT = __End_C_SelSFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_C.End_C_SelSFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_C_SelSFHandlingImp(c,session)}

protected case class __End_C_SelSFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_C_SelSFHandling
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
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("b",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C"), RoleSet("S")) 
  override def prjTo : RRole = RoleSet("C") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Main"
}

protected case class __EPMain_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_C
}
  
}


protected  trait Hdl extends EPMain_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_C] = List(SpawnSelS,RcvFMainF1)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelSImp
override def toString() : String = {"EPMain_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelS extends EPMain_C with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelS extends SpawnSelS {
  override protected def __children: List[EPMain_C] = List(End_C_Main)
  override type implT = __SpawnSelSImp
  override type implNextT = __End_C_MainImp
override def toString() : String = {"EPMain_C.SpawnSelS"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("S") 
  override def rs: List[RoleSet] = List(RoleSet("S"), RoleSet("C")) 
  override def name: String = "SelS" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelSImp(c,session)}

protected case class __SpawnSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelS
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



  trait RcvFMainF1 extends EPMain_C with event_lang.dsl.ChannelTypeRcv
  object RcvFMainF1 extends RcvFMainF1 {
  override protected def __children: List[EPMain_C] = List(End_C_MainFHandling)
  override type implT = __RcvFMainF1Imp
  override type implNextT = __End_C_MainFHandlingImp
override def toString() : String = {"EPMain_C.RcvFMainF1"}
  override type msgT = MESSAGES.Main.FMainF1
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FMainF1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFMainF1Imp(c,session)}

protected case class __RcvFMainF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFMainF1
}
  def rcvFrma_A : (MESSAGES.Main.FMainF1,__End_C_MainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMainF1],__End_C_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FMainF1,__End_C_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMainF1],__End_C_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FMainF1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMainF1]}
def ? : MESSAGES.Main.FMainF1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMainF1]}
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

object S {
val subs : Seq[dsl.ChannelTypeSubS] = List(SelS_S.EPSelS_S,Main_S.EPMain_S,SelS_s_S.EPSelS_s_S,threeBuyers_s_S.EPthreeBuyers_s_S,threeBuyers_ss_S.EPthreeBuyers_ss_S)
trait __EPType_S extends AbstractChannelType {

}

trait EPType_S[T<: TState] extends AbstractEndPoint[__EPType_S,T] {
override val roleSet: RoleSet = RoleSet("S")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(SelS_S.EPSelS_S,Main_S.EPMain_S,SelS_s_S.EPSelS_s_S,threeBuyers_s_S.EPthreeBuyers_s_S,threeBuyers_ss_S.EPthreeBuyers_ss_S)

}

object SelS_S{
trait EPSelS_S extends __EPType_S

object EPSelS_S extends EPSelS_S with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelS_S] = List(Hdl)
  override type implT = __EPSelS_SImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelS_SImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("s",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("S"), RoleSet("C")) 
  override def prjTo : RRole = RoleSet("S") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "SelS"
}

protected case class __EPSelS_SImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelS_S
}
  
}


protected  trait Hdl extends EPSelS_S with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelS_S] = List(SpawnthreeBuyers,RcvFSelS)
  override type implT = __HdlImp
  override type implNextT = __SpawnthreeBuyersImp
override def toString() : String = {"EPSelS_S.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnthreeBuyers extends EPSelS_S with event_lang.dsl.ChannelTypeSpawn
  object SpawnthreeBuyers extends SpawnthreeBuyers {
  override protected def __children: List[EPSelS_S] = List(End_S_SelS)
  override type implT = __SpawnthreeBuyersImp
  override type implNextT = __End_S_SelSImp
override def toString() : String = {"EPSelS_S.SpawnthreeBuyers"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def pickR: RoleSet = RoleSet("S") 
  override def rs: List[RoleSet] = List(RoleSet("C")) 
  override def name: String = "threeBuyers" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnthreeBuyersImp(c,session)}

protected case class __SpawnthreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnthreeBuyers
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_S_SelS extends EPSelS_S with event_lang.dsl.ChannelTypeEnd
protected  object End_S_SelS extends End_S_SelS {
  override protected def __children: List[EPSelS_S] = List()
  override type implT = __End_S_SelSImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_S.End_S_SelS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_S_SelSImp(c,session)}

protected case class __End_S_SelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_S_SelS
}
  
}



  trait RcvFSelS extends EPSelS_S with event_lang.dsl.ChannelTypeRcv
  object RcvFSelS extends RcvFSelS {
  override protected def __children: List[EPSelS_S] = List(End_S_SelSFHandling)
  override type implT = __RcvFSelSImp
  override type implNextT = __End_S_SelSFHandlingImp
override def toString() : String = {"EPSelS_S.RcvFSelS"}
  override type msgT = MESSAGES.SelS.FSelS
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FSelS"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFSelSImp(c,session)}

protected case class __RcvFSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFSelS
}
  def rcvFrma_A : (MESSAGES.SelS.FSelS,__End_S_SelSFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelS],__End_S_SelSFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SelS.FSelS,__End_S_SelSFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelS],__End_S_SelSFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.SelS.FSelS = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelS]}
def ? : MESSAGES.SelS.FSelS = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelS]}
def channelCon : __End_S_SelSFHandlingImp = {__End_S_SelSFHandlingImp(c,session)}

}


protected  trait End_S_SelSFHandling extends EPSelS_S with event_lang.dsl.ChannelTypeEnd
protected  object End_S_SelSFHandling extends End_S_SelSFHandling {
  override protected def __children: List[EPSelS_S] = List()
  override type implT = __End_S_SelSFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_S.End_S_SelSFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_S_SelSFHandlingImp(c,session)}

protected case class __End_S_SelSFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_S_SelSFHandling
}
  
}


}

object Main_S{
trait EPMain_S extends __EPType_S

object EPMain_S extends EPMain_S with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_S] = List(Hdl)
  override type implT = __EPMain_SImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_SImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("b",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C"), RoleSet("S")) 
  override def prjTo : RRole = RoleSet("S") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Main"
}

protected case class __EPMain_SImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_S
}
  
}


protected  trait Hdl extends EPMain_S with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_S] = List(SpawnSelS,RcvFMainF2)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelSImp
override def toString() : String = {"EPMain_S.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelS extends EPMain_S with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelS extends SpawnSelS {
  override protected def __children: List[EPMain_S] = List(End_S_Main)
  override type implT = __SpawnSelSImp
  override type implNextT = __End_S_MainImp
override def toString() : String = {"EPMain_S.SpawnSelS"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("S") 
  override def rs: List[RoleSet] = List(RoleSet("S"), RoleSet("C")) 
  override def name: String = "SelS" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelSImp(c,session)}

protected case class __SpawnSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelS
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_S_Main extends EPMain_S with event_lang.dsl.ChannelTypeEnd
protected  object End_S_Main extends End_S_Main {
  override protected def __children: List[EPMain_S] = List()
  override type implT = __End_S_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_S.End_S_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_S_MainImp(c,session)}

protected case class __End_S_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_S_Main
}
  
}



  trait RcvFMainF2 extends EPMain_S with event_lang.dsl.ChannelTypeRcv
  object RcvFMainF2 extends RcvFMainF2 {
  override protected def __children: List[EPMain_S] = List(End_S_MainFHandling)
  override type implT = __RcvFMainF2Imp
  override type implNextT = __End_S_MainFHandlingImp
override def toString() : String = {"EPMain_S.RcvFMainF2"}
  override type msgT = MESSAGES.Main.FMainF2
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FMainF2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFMainF2Imp(c,session)}

protected case class __RcvFMainF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFMainF2
}
  def rcvFrma_A : (MESSAGES.Main.FMainF2,__End_S_MainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMainF2],__End_S_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FMainF2,__End_S_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMainF2],__End_S_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FMainF2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMainF2]}
def ? : MESSAGES.Main.FMainF2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMainF2]}
def channelCon : __End_S_MainFHandlingImp = {__End_S_MainFHandlingImp(c,session)}

}


protected  trait End_S_MainFHandling extends EPMain_S with event_lang.dsl.ChannelTypeEnd
protected  object End_S_MainFHandling extends End_S_MainFHandling {
  override protected def __children: List[EPMain_S] = List()
  override type implT = __End_S_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_S.End_S_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_S_MainFHandlingImp(c,session)}

protected case class __End_S_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_S_MainFHandling
}
  
}


}

object SelS_s_S{
trait EPSelS_s_S extends __EPType_S

object EPSelS_s_S extends EPSelS_s_S with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelS_s_S] = List(Hdl)
  override type implT = __EPSelS_s_SImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelS_s_SImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("s",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("S"), RoleSet("C")) 
  override def prjTo : RRole = Role("s",RoleSet("S")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "SelS"
}

protected case class __EPSelS_s_SImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelS_s_S
}
  
}


protected  trait Hdl extends EPSelS_s_S with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelS_s_S] = List(SpawnthreeBuyers,End_s_S_SelSFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnthreeBuyersImp
override def toString() : String = {"EPSelS_s_S.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnthreeBuyers extends EPSelS_s_S with event_lang.dsl.ChannelTypeSpawn
  object SpawnthreeBuyers extends SpawnthreeBuyers {
  override protected def __children: List[EPSelS_s_S] = List(End_s_S_SelS)
  override type implT = __SpawnthreeBuyersImp
  override type implNextT = __End_s_S_SelSImp
override def toString() : String = {"EPSelS_s_S.SpawnthreeBuyers"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def pickR: RoleSet = RoleSet("S") 
  override def rs: List[RoleSet] = List(RoleSet("C")) 
  override def name: String = "threeBuyers" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnthreeBuyersImp(c,session)}

protected case class __SpawnthreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnthreeBuyers
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_s_S_SelS extends EPSelS_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_SelS extends End_s_S_SelS {
  override protected def __children: List[EPSelS_s_S] = List()
  override type implT = __End_s_S_SelSImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_s_S.End_s_S_SelS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_SelSImp(c,session)}

protected case class __End_s_S_SelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_SelS
}
  
}



protected  trait End_s_S_SelSFHandling extends EPSelS_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_SelSFHandling extends End_s_S_SelSFHandling {
  override protected def __children: List[EPSelS_s_S] = List()
  override type implT = __End_s_S_SelSFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_s_S.End_s_S_SelSFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_SelSFHandlingImp(c,session)}

protected case class __End_s_S_SelSFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_SelSFHandling
}
  
}


}

object threeBuyers_ss_S{
trait EPthreeBuyers_ss_S extends __EPType_S

object EPthreeBuyers_ss_S extends EPthreeBuyers_ss_S with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPthreeBuyers_ss_S] = List(Hdl)
  override type implT = __EPthreeBuyers_ss_SImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPthreeBuyers_ss_SImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("ss",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
  override def prjTo : RRole = Role("ss",RoleSet("S")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "threeBuyers"
}

protected case class __EPthreeBuyers_ss_SImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPthreeBuyers_ss_S
}
  
}


protected  trait Hdl extends EPthreeBuyers_ss_S with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPthreeBuyers_ss_S] = List(End_ss_S_threeBuyers,End_ss_S_threeBuyersFHandling)
  override type implT = __HdlImp
  override type implNextT = __End_ss_S_threeBuyersImp
override def toString() : String = {"EPthreeBuyers_ss_S.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_ss_S_threeBuyers extends EPthreeBuyers_ss_S with event_lang.dsl.ChannelTypeEnd
protected  object End_ss_S_threeBuyers extends End_ss_S_threeBuyers {
  override protected def __children: List[EPthreeBuyers_ss_S] = List()
  override type implT = __End_ss_S_threeBuyersImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_ss_S.End_ss_S_threeBuyers"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_ss_S_threeBuyersImp(c,session)}

protected case class __End_ss_S_threeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_ss_S_threeBuyers
}
  
}



protected  trait End_ss_S_threeBuyersFHandling extends EPthreeBuyers_ss_S with event_lang.dsl.ChannelTypeEnd
protected  object End_ss_S_threeBuyersFHandling extends End_ss_S_threeBuyersFHandling {
  override protected def __children: List[EPthreeBuyers_ss_S] = List()
  override type implT = __End_ss_S_threeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_ss_S.End_ss_S_threeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_ss_S_threeBuyersFHandlingImp(c,session)}

protected case class __End_ss_S_threeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_ss_S_threeBuyersFHandling
}
  
}


}

object threeBuyers_s_S{
trait EPthreeBuyers_s_S extends __EPType_S

object EPthreeBuyers_s_S extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPthreeBuyers_s_S] = List(Hdl)
  override type implT = __EPthreeBuyers_s_SImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPthreeBuyers_s_SImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("ss",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
  override def prjTo : RRole = Role("s",RoleSet("S")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "threeBuyers"
}

protected case class __EPthreeBuyers_s_SImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPthreeBuyers_s_S
}
  
}


protected  trait Hdl extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPthreeBuyers_s_S] = List(RcvBook,RcvF3Bs)
  override type implT = __HdlImp
  override type implNextT = __RcvBookImp
override def toString() : String = {"EPthreeBuyers_s_S.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvBook extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeRcv
  object RcvBook extends RcvBook {
  override protected def __children: List[EPthreeBuyers_s_S] = List(SndPriceA)
  override type implT = __RcvBookImp
  override type implNextT = __SndPriceAImp
override def toString() : String = {"EPthreeBuyers_s_S.RcvBook"}
  override type msgT = MESSAGES.threeBuyers.Book
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "Book"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBookImp(c,session)}

protected case class __RcvBookImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBook
}
  def rcvFrma_A : (MESSAGES.threeBuyers.Book,__SndPriceAImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.Book],__SndPriceAImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.Book,__SndPriceAImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.Book],__SndPriceAImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.Book = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.Book]}
def ? : MESSAGES.threeBuyers.Book = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.Book]}
def channelCon : __SndPriceAImp = {__SndPriceAImp(c,session)}

}


  trait SndPriceA extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeSnd
  object SndPriceA extends SndPriceA {
  override protected def __children: List[EPthreeBuyers_s_S] = List(SndPriceB)
  override type implT = __SndPriceAImp
  override type implNextT = __SndPriceBImp
override def toString() : String = {"EPthreeBuyers_s_S.SndPriceA"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "PriceA" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPriceAImp(c,session)}

protected case class __SndPriceAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPriceA
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.threeBuyers.PriceA) : __SndPriceBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SndPriceBImp(c,session)}
def !(m : MESSAGES.threeBuyers.PriceA) : __SndPriceBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SndPriceBImp(c,session)}
def snd(m : MESSAGES.threeBuyers.PriceA) : __SndPriceBImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SndPriceBImp(c,session)}

}


  trait SndPriceB extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeSnd
  object SndPriceB extends SndPriceB {
  override protected def __children: List[EPthreeBuyers_s_S] = List(SelOkSQuitS)
  override type implT = __SndPriceBImp
  override type implNextT = __SelOkSQuitSImp
override def toString() : String = {"EPthreeBuyers_s_S.SndPriceB"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "PriceB" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPriceBImp(c,session)}

protected case class __SndPriceBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPriceB
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.threeBuyers.PriceB) : __SelOkSQuitSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SelOkSQuitSImp(c,session)}
def !(m : MESSAGES.threeBuyers.PriceB) : __SelOkSQuitSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SelOkSQuitSImp(c,session)}
def snd(m : MESSAGES.threeBuyers.PriceB) : __SelOkSQuitSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SelOkSQuitSImp(c,session)}

}


protected  trait SelOkSQuitS extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeBrn
protected  object SelOkSQuitS extends SelOkSQuitS {
  override protected def __children: List[EPthreeBuyers_s_S] = List(RcvOkS,RcvQuitS)
  override type implT = __SelOkSQuitSImp
  override type implNextT = __RcvOkSImp
override def toString() : String = {"EPthreeBuyers_s_S.SelOkSQuitS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOkSQuitSImp(c,session)}

protected case class __SelOkSQuitSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOkSQuitS
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvOkS extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeRcv
  object RcvOkS extends RcvOkS {
  override protected def __children: List[EPthreeBuyers_s_S] = List(RcvAddr)
  override type implT = __RcvOkSImp
  override type implNextT = __RcvAddrImp
override def toString() : String = {"EPthreeBuyers_s_S.RcvOkS"}
  override type msgT = MESSAGES.threeBuyers.OkS
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "OkS"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvOkSImp(c,session)}

protected case class __RcvOkSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvOkS
}
  def rcvFrmb_B : (MESSAGES.threeBuyers.OkS,__RcvAddrImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.OkS],__RcvAddrImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.OkS,__RcvAddrImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.OkS],__RcvAddrImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.OkS = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.OkS]}
def ? : MESSAGES.threeBuyers.OkS = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.OkS]}
def channelCon : __RcvAddrImp = {__RcvAddrImp(c,session)}

}


  trait RcvAddr extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeRcv
  object RcvAddr extends RcvAddr {
  override protected def __children: List[EPthreeBuyers_s_S] = List(SndShipD)
  override type implT = __RcvAddrImp
  override type implNextT = __SndShipDImp
override def toString() : String = {"EPthreeBuyers_s_S.RcvAddr"}
  override type msgT = MESSAGES.threeBuyers.Addr
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "Addr"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAddrImp(c,session)}

protected case class __RcvAddrImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAddr
}
  def rcvFrmb_B : (MESSAGES.threeBuyers.Addr,__SndShipDImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.Addr],__SndShipDImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.Addr,__SndShipDImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.Addr],__SndShipDImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.Addr = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.Addr]}
def ? : MESSAGES.threeBuyers.Addr = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.Addr]}
def channelCon : __SndShipDImp = {__SndShipDImp(c,session)}

}


  trait SndShipD extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeSnd
  object SndShipD extends SndShipD {
  override protected def __children: List[EPthreeBuyers_s_S] = List(End_s_S_threeBuyersOkS)
  override type implT = __SndShipDImp
  override type implNextT = __End_s_S_threeBuyersOkSImp
override def toString() : String = {"EPthreeBuyers_s_S.SndShipD"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "ShipD" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndShipDImp(c,session)}

protected case class __SndShipDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndShipD
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.threeBuyers.ShipD) : __End_s_S_threeBuyersOkSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_s_S_threeBuyersOkSImp(c,session)}
def !(m : MESSAGES.threeBuyers.ShipD) : __End_s_S_threeBuyersOkSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_s_S_threeBuyersOkSImp(c,session)}
def snd(m : MESSAGES.threeBuyers.ShipD) : __End_s_S_threeBuyersOkSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_s_S_threeBuyersOkSImp(c,session)}

}


protected  trait End_s_S_threeBuyersOkS extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_threeBuyersOkS extends End_s_S_threeBuyersOkS {
  override protected def __children: List[EPthreeBuyers_s_S] = List()
  override type implT = __End_s_S_threeBuyersOkSImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_s_S.End_s_S_threeBuyersOkS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_threeBuyersOkSImp(c,session)}

protected case class __End_s_S_threeBuyersOkSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_threeBuyersOkS
}
  
}



  trait RcvQuitS extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeRcv
  object RcvQuitS extends RcvQuitS {
  override protected def __children: List[EPthreeBuyers_s_S] = List(End_s_S_threeBuyersQuitS)
  override type implT = __RcvQuitSImp
  override type implNextT = __End_s_S_threeBuyersQuitSImp
override def toString() : String = {"EPthreeBuyers_s_S.RcvQuitS"}
  override type msgT = MESSAGES.threeBuyers.QuitS
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "QuitS"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvQuitSImp(c,session)}

protected case class __RcvQuitSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvQuitS
}
  def rcvFrmb_B : (MESSAGES.threeBuyers.QuitS,__End_s_S_threeBuyersQuitSImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.QuitS],__End_s_S_threeBuyersQuitSImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.QuitS,__End_s_S_threeBuyersQuitSImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.QuitS],__End_s_S_threeBuyersQuitSImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.QuitS = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.QuitS]}
def ? : MESSAGES.threeBuyers.QuitS = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.QuitS]}
def channelCon : __End_s_S_threeBuyersQuitSImp = {__End_s_S_threeBuyersQuitSImp(c,session)}

}


protected  trait End_s_S_threeBuyersQuitS extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_threeBuyersQuitS extends End_s_S_threeBuyersQuitS {
  override protected def __children: List[EPthreeBuyers_s_S] = List()
  override type implT = __End_s_S_threeBuyersQuitSImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_s_S.End_s_S_threeBuyersQuitS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_threeBuyersQuitSImp(c,session)}

protected case class __End_s_S_threeBuyersQuitSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_threeBuyersQuitS
}
  
}



  trait RcvF3Bs extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeRcv
  object RcvF3Bs extends RcvF3Bs {
  override protected def __children: List[EPthreeBuyers_s_S] = List(End_s_S_threeBuyersFHandling)
  override type implT = __RcvF3BsImp
  override type implNextT = __End_s_S_threeBuyersFHandlingImp
override def toString() : String = {"EPthreeBuyers_s_S.RcvF3Bs"}
  override type msgT = MESSAGES.threeBuyers.F3Bs
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "F3Bs"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF3BsImp(c,session)}

protected case class __RcvF3BsImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF3Bs
}
  def rcvFrma_A : (MESSAGES.threeBuyers.F3Bs,__End_s_S_threeBuyersFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3Bs],__End_s_S_threeBuyersFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.F3Bs,__End_s_S_threeBuyersFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3Bs],__End_s_S_threeBuyersFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.F3Bs = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3Bs]}
def ? : MESSAGES.threeBuyers.F3Bs = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3Bs]}
def channelCon : __End_s_S_threeBuyersFHandlingImp = {__End_s_S_threeBuyersFHandlingImp(c,session)}

}


protected  trait End_s_S_threeBuyersFHandling extends EPthreeBuyers_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_threeBuyersFHandling extends End_s_S_threeBuyersFHandling {
  override protected def __children: List[EPthreeBuyers_s_S] = List()
  override type implT = __End_s_S_threeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_s_S.End_s_S_threeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_threeBuyersFHandlingImp(c,session)}

protected case class __End_s_S_threeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_threeBuyersFHandling
}
  
}


}

}

object B {
val subs : Seq[dsl.ChannelTypeSubS] = List(threeBuyers_b_B.EPthreeBuyers_b_B,negotiationBC_b_B.EPnegotiationBC_b_B,SelS_b_B.EPSelS_b_B,Main_b_B.EPMain_b_B)
trait __EPType_B extends AbstractChannelType {

}

trait EPType_B[T<: TState] extends AbstractEndPoint[__EPType_B,T] {
override val roleSet: RoleSet = RoleSet("B")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(threeBuyers_b_B.EPthreeBuyers_b_B,negotiationBC_b_B.EPnegotiationBC_b_B,SelS_b_B.EPSelS_b_B,Main_b_B.EPMain_b_B)

}

object threeBuyers_b_B{
trait EPthreeBuyers_b_B extends __EPType_B

object EPthreeBuyers_b_B extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPthreeBuyers_b_B] = List(Hdl)
  override type implT = __EPthreeBuyers_b_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPthreeBuyers_b_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("ss",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
  override def prjTo : RRole = Role("b",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "threeBuyers"
}

protected case class __EPthreeBuyers_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPthreeBuyers_b_B
}
  
}


protected  trait Hdl extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPthreeBuyers_b_B] = List(RcvPriceB,RcvF3Bb)
  override type implT = __HdlImp
  override type implNextT = __RcvPriceBImp
override def toString() : String = {"EPthreeBuyers_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvPriceB extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvPriceB extends RcvPriceB {
  override protected def __children: List[EPthreeBuyers_b_B] = List(RcvMyShare)
  override type implT = __RcvPriceBImp
  override type implNextT = __RcvMyShareImp
override def toString() : String = {"EPthreeBuyers_b_B.RcvPriceB"}
  override type msgT = MESSAGES.threeBuyers.PriceB
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "PriceB"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPriceBImp(c,session)}

protected case class __RcvPriceBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPriceB
}
  def rcvFrms_S : (MESSAGES.threeBuyers.PriceB,__RcvMyShareImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.PriceB],__RcvMyShareImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.PriceB,__RcvMyShareImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.PriceB],__RcvMyShareImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.PriceB = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.PriceB]}
def ? : MESSAGES.threeBuyers.PriceB = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.PriceB]}
def channelCon : __RcvMyShareImp = {__RcvMyShareImp(c,session)}

}


  trait RcvMyShare extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvMyShare extends RcvMyShare {
  override protected def __children: List[EPthreeBuyers_b_B] = List(SpawnnegotiationBC)
  override type implT = __RcvMyShareImp
  override type implNextT = __SpawnnegotiationBCImp
override def toString() : String = {"EPthreeBuyers_b_B.RcvMyShare"}
  override type msgT = MESSAGES.threeBuyers.MyShare
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "MyShare"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMyShareImp(c,session)}

protected case class __RcvMyShareImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMyShare
}
  def rcvFrma_A : (MESSAGES.threeBuyers.MyShare,__SpawnnegotiationBCImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.MyShare],__SpawnnegotiationBCImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.MyShare,__SpawnnegotiationBCImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.MyShare],__SpawnnegotiationBCImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.MyShare = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.MyShare]}
def ? : MESSAGES.threeBuyers.MyShare = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.MyShare]}
def channelCon : __SpawnnegotiationBCImp = {__SpawnnegotiationBCImp(c,session)}

}


  trait SpawnnegotiationBC extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnnegotiationBC extends SpawnnegotiationBC {
  override protected def __children: List[EPthreeBuyers_b_B] = List(SelOkSQuitS)
  override type implT = __SpawnnegotiationBCImp
  override type implNextT = __SelOkSQuitSImp
override def toString() : String = {"EPthreeBuyers_b_B.SpawnnegotiationBC"}
    override def y: List[Role] = List(Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "negotiationBC" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnnegotiationBCImp(c,session)}

protected case class __SpawnnegotiationBCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnnegotiationBC
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait SelOkSQuitS extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeSel
protected  object SelOkSQuitS extends SelOkSQuitS {
  override protected def __children: List[EPthreeBuyers_b_B] = List(SndOkS,SndQuitS)
  override type implT = __SelOkSQuitSImp
  override type implNextT = __SndOkSImp
override def toString() : String = {"EPthreeBuyers_b_B.SelOkSQuitS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOkSQuitSImp(c,session)}

protected case class __SelOkSQuitSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOkSQuitS
}
  private var notUsed = true
def !(m : MESSAGES.threeBuyers.OkS) : __SndOkAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
 __SndOkAImp(c,session)}
def sndTos_S(m : MESSAGES.threeBuyers.OkS) : __SndOkAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
 __SndOkAImp(c,session)}

def !(m : MESSAGES.threeBuyers.QuitS) : __SndQuitAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
 __SndQuitAImp(c,session)}
def sndTos_S(m : MESSAGES.threeBuyers.QuitS) : __SndQuitAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
 __SndQuitAImp(c,session)}

}


  trait SndOkS extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeSnd
  object SndOkS extends SndOkS {
  override protected def __children: List[EPthreeBuyers_b_B] = List(SndOkA)
  override type implT = __SndOkSImp
  override type implNextT = __SndOkAImp
override def toString() : String = {"EPthreeBuyers_b_B.SndOkS"}
    override def to : RRole = Role("s",RoleSet("S")) 
   override def l : String = "OkS" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndOkSImp(c,session)}

protected case class __SndOkSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndOkS
}
  private var notUsed = true
def sndTos_S(m : MESSAGES.threeBuyers.OkS) : __SndOkAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SndOkAImp(c,session)}
def !(m : MESSAGES.threeBuyers.OkS) : __SndOkAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SndOkAImp(c,session)}
def snd(m : MESSAGES.threeBuyers.OkS) : __SndOkAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SndOkAImp(c,session)}

}


  trait SndOkA extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeSnd
  object SndOkA extends SndOkA {
  override protected def __children: List[EPthreeBuyers_b_B] = List(SndAddr)
  override type implT = __SndOkAImp
  override type implNextT = __SndAddrImp
override def toString() : String = {"EPthreeBuyers_b_B.SndOkA"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "OkA" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndOkAImp(c,session)}

protected case class __SndOkAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndOkA
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.threeBuyers.OkA) : __SndAddrImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SndAddrImp(c,session)}
def !(m : MESSAGES.threeBuyers.OkA) : __SndAddrImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SndAddrImp(c,session)}
def snd(m : MESSAGES.threeBuyers.OkA) : __SndAddrImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__SndAddrImp(c,session)}

}


  trait SndAddr extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeSnd
  object SndAddr extends SndAddr {
  override protected def __children: List[EPthreeBuyers_b_B] = List(RcvShipD)
  override type implT = __SndAddrImp
  override type implNextT = __RcvShipDImp
override def toString() : String = {"EPthreeBuyers_b_B.SndAddr"}
    override def to : RRole = Role("s",RoleSet("S")) 
   override def l : String = "Addr" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAddrImp(c,session)}

protected case class __SndAddrImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAddr
}
  private var notUsed = true
def sndTos_S(m : MESSAGES.threeBuyers.Addr) : __RcvShipDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__RcvShipDImp(c,session)}
def !(m : MESSAGES.threeBuyers.Addr) : __RcvShipDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__RcvShipDImp(c,session)}
def snd(m : MESSAGES.threeBuyers.Addr) : __RcvShipDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__RcvShipDImp(c,session)}

}


  trait RcvShipD extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvShipD extends RcvShipD {
  override protected def __children: List[EPthreeBuyers_b_B] = List(End_b_B_threeBuyersOkS)
  override type implT = __RcvShipDImp
  override type implNextT = __End_b_B_threeBuyersOkSImp
override def toString() : String = {"EPthreeBuyers_b_B.RcvShipD"}
  override type msgT = MESSAGES.threeBuyers.ShipD
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "ShipD"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvShipDImp(c,session)}

protected case class __RcvShipDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvShipD
}
  def rcvFrms_S : (MESSAGES.threeBuyers.ShipD,__End_b_B_threeBuyersOkSImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.ShipD],__End_b_B_threeBuyersOkSImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.ShipD,__End_b_B_threeBuyersOkSImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.ShipD],__End_b_B_threeBuyersOkSImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.ShipD = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.ShipD]}
def ? : MESSAGES.threeBuyers.ShipD = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.ShipD]}
def channelCon : __End_b_B_threeBuyersOkSImp = {__End_b_B_threeBuyersOkSImp(c,session)}

}


protected  trait End_b_B_threeBuyersOkS extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_threeBuyersOkS extends End_b_B_threeBuyersOkS {
  override protected def __children: List[EPthreeBuyers_b_B] = List()
  override type implT = __End_b_B_threeBuyersOkSImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_b_B.End_b_B_threeBuyersOkS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_threeBuyersOkSImp(c,session)}

protected case class __End_b_B_threeBuyersOkSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_threeBuyersOkS
}
  
}



  trait SndQuitS extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeSnd
  object SndQuitS extends SndQuitS {
  override protected def __children: List[EPthreeBuyers_b_B] = List(SndQuitA)
  override type implT = __SndQuitSImp
  override type implNextT = __SndQuitAImp
override def toString() : String = {"EPthreeBuyers_b_B.SndQuitS"}
    override def to : RRole = Role("s",RoleSet("S")) 
   override def l : String = "QuitS" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndQuitSImp(c,session)}

protected case class __SndQuitSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndQuitS
}
  private var notUsed = true
def sndTos_S(m : MESSAGES.threeBuyers.QuitS) : __SndQuitAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SndQuitAImp(c,session)}
def !(m : MESSAGES.threeBuyers.QuitS) : __SndQuitAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SndQuitAImp(c,session)}
def snd(m : MESSAGES.threeBuyers.QuitS) : __SndQuitAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SndQuitAImp(c,session)}

}


  trait SndQuitA extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeSnd
  object SndQuitA extends SndQuitA {
  override protected def __children: List[EPthreeBuyers_b_B] = List(End_b_B_threeBuyersQuitS)
  override type implT = __SndQuitAImp
  override type implNextT = __End_b_B_threeBuyersQuitSImp
override def toString() : String = {"EPthreeBuyers_b_B.SndQuitA"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "QuitA" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndQuitAImp(c,session)}

protected case class __SndQuitAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndQuitA
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.threeBuyers.QuitA) : __End_b_B_threeBuyersQuitSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_threeBuyersQuitSImp(c,session)}
def !(m : MESSAGES.threeBuyers.QuitA) : __End_b_B_threeBuyersQuitSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_threeBuyersQuitSImp(c,session)}
def snd(m : MESSAGES.threeBuyers.QuitA) : __End_b_B_threeBuyersQuitSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_threeBuyersQuitSImp(c,session)}

}


protected  trait End_b_B_threeBuyersQuitS extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_threeBuyersQuitS extends End_b_B_threeBuyersQuitS {
  override protected def __children: List[EPthreeBuyers_b_B] = List()
  override type implT = __End_b_B_threeBuyersQuitSImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_b_B.End_b_B_threeBuyersQuitS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_threeBuyersQuitSImp(c,session)}

protected case class __End_b_B_threeBuyersQuitSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_threeBuyersQuitS
}
  
}



  trait RcvF3Bb extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvF3Bb extends RcvF3Bb {
  override protected def __children: List[EPthreeBuyers_b_B] = List(End_b_B_threeBuyersFHandling)
  override type implT = __RcvF3BbImp
  override type implNextT = __End_b_B_threeBuyersFHandlingImp
override def toString() : String = {"EPthreeBuyers_b_B.RcvF3Bb"}
  override type msgT = MESSAGES.threeBuyers.F3Bb
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "F3Bb"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF3BbImp(c,session)}

protected case class __RcvF3BbImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF3Bb
}
  def rcvFrma_A : (MESSAGES.threeBuyers.F3Bb,__End_b_B_threeBuyersFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3Bb],__End_b_B_threeBuyersFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.F3Bb,__End_b_B_threeBuyersFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3Bb],__End_b_B_threeBuyersFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.F3Bb = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3Bb]}
def ? : MESSAGES.threeBuyers.F3Bb = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.threeBuyers.F3Bb]}
def channelCon : __End_b_B_threeBuyersFHandlingImp = {__End_b_B_threeBuyersFHandlingImp(c,session)}

}


protected  trait End_b_B_threeBuyersFHandling extends EPthreeBuyers_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_threeBuyersFHandling extends End_b_B_threeBuyersFHandling {
  override protected def __children: List[EPthreeBuyers_b_B] = List()
  override type implT = __End_b_B_threeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_b_B.End_b_B_threeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_threeBuyersFHandlingImp(c,session)}

protected case class __End_b_B_threeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_threeBuyersFHandling
}
  
}


}

object negotiationBC_b_B{
trait EPnegotiationBC_b_B extends __EPType_B

object EPnegotiationBC_b_B extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPnegotiationBC_b_B] = List(Hdl)
  override type implT = __EPnegotiationBC_b_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPnegotiationBC_b_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("b",RoleSet("B")) 
  override def rootRole: Role = Role("b",RoleSet("B")) 
  override def name : String = "negotiationBC"
}

protected case class __EPnegotiationBC_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPnegotiationBC_b_B
}
  
}


protected  trait Hdl extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPnegotiationBC_b_B] = List(SelYourShareEnd,Failed_c_C)
  override type implT = __HdlImp
  override type implNextT = __SelYourShareEndImp
override def toString() : String = {"EPnegotiationBC_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait SelYourShareEnd extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeSel
protected  object SelYourShareEnd extends SelYourShareEnd {
  override protected def __children: List[EPnegotiationBC_b_B] = List(SndYourShare,SndEnd)
  override type implT = __SelYourShareEndImp
  override type implNextT = __SndYourShareImp
override def toString() : String = {"EPnegotiationBC_b_B.SelYourShareEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelYourShareEndImp(c,session)}

protected case class __SelYourShareEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelYourShareEnd
}
  private var notUsed = true
def !(m : MESSAGES.negotiationBC.YourShare) : __SelOkNoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __SelOkNoImp(c,session)}
def sndToc_C(m : MESSAGES.negotiationBC.YourShare) : __SelOkNoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __SelOkNoImp(c,session)}

def !(m : MESSAGES.negotiationBC.End) : __End_b_B_negotiationBCEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_b_B_negotiationBCEndImp(c,session)}
def sndToc_C(m : MESSAGES.negotiationBC.End) : __End_b_B_negotiationBCEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_b_B_negotiationBCEndImp(c,session)}

}


  trait SndYourShare extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeSnd
  object SndYourShare extends SndYourShare {
  override protected def __children: List[EPnegotiationBC_b_B] = List(SelOkNo)
  override type implT = __SndYourShareImp
  override type implNextT = __SelOkNoImp
override def toString() : String = {"EPnegotiationBC_b_B.SndYourShare"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "YourShare" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndYourShareImp(c,session)}

protected case class __SndYourShareImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndYourShare
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.negotiationBC.YourShare) : __SelOkNoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__SelOkNoImp(c,session)}
def !(m : MESSAGES.negotiationBC.YourShare) : __SelOkNoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__SelOkNoImp(c,session)}
def snd(m : MESSAGES.negotiationBC.YourShare) : __SelOkNoImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__SelOkNoImp(c,session)}

}


protected  trait SelOkNo extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeBrn
protected  object SelOkNo extends SelOkNo {
  override protected def __children: List[EPnegotiationBC_b_B] = List(RcvOk,RcvNo)
  override type implT = __SelOkNoImp
  override type implNextT = __RcvOkImp
override def toString() : String = {"EPnegotiationBC_b_B.SelOkNo"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOkNoImp(c,session)}

protected case class __SelOkNoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOkNo
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvOk extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvOk extends RcvOk {
  override protected def __children: List[EPnegotiationBC_b_B] = List(End_b_B_negotiationBCOk_YourShare)
  override type implT = __RcvOkImp
  override type implNextT = __End_b_B_negotiationBCOk_YourShareImp
override def toString() : String = {"EPnegotiationBC_b_B.RcvOk"}
  override type msgT = MESSAGES.negotiationBC.Ok
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "Ok"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvOkImp(c,session)}

protected case class __RcvOkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvOk
}
  def rcvFrmc_C : (MESSAGES.negotiationBC.Ok,__End_b_B_negotiationBCOk_YourShareImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.negotiationBC.Ok],__End_b_B_negotiationBCOk_YourShareImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.negotiationBC.Ok,__End_b_B_negotiationBCOk_YourShareImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.negotiationBC.Ok],__End_b_B_negotiationBCOk_YourShareImp(c,session))) 
}
def rcvMSG : MESSAGES.negotiationBC.Ok = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.negotiationBC.Ok]}
def ? : MESSAGES.negotiationBC.Ok = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.negotiationBC.Ok]}
def channelCon : __End_b_B_negotiationBCOk_YourShareImp = {__End_b_B_negotiationBCOk_YourShareImp(c,session)}

}


protected  trait End_b_B_negotiationBCOk_YourShare extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_negotiationBCOk_YourShare extends End_b_B_negotiationBCOk_YourShare {
  override protected def __children: List[EPnegotiationBC_b_B] = List()
  override type implT = __End_b_B_negotiationBCOk_YourShareImp
  override type implNextT = Nothing
override def toString() : String = {"EPnegotiationBC_b_B.End_b_B_negotiationBCOk_YourShare"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_negotiationBCOk_YourShareImp(c,session)}

protected case class __End_b_B_negotiationBCOk_YourShareImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_negotiationBCOk_YourShare
}
  
}



  trait RcvNo extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvNo extends RcvNo {
  override protected def __children: List[EPnegotiationBC_b_B] = List(End_b_B_negotiationBCNo_YourShare)
  override type implT = __RcvNoImp
  override type implNextT = __End_b_B_negotiationBCNo_YourShareImp
override def toString() : String = {"EPnegotiationBC_b_B.RcvNo"}
  override type msgT = MESSAGES.negotiationBC.No
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "No"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvNoImp(c,session)}

protected case class __RcvNoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvNo
}
  def rcvFrmc_C : (MESSAGES.negotiationBC.No,__End_b_B_negotiationBCNo_YourShareImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.negotiationBC.No],__End_b_B_negotiationBCNo_YourShareImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.negotiationBC.No,__End_b_B_negotiationBCNo_YourShareImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.negotiationBC.No],__End_b_B_negotiationBCNo_YourShareImp(c,session))) 
}
def rcvMSG : MESSAGES.negotiationBC.No = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.negotiationBC.No]}
def ? : MESSAGES.negotiationBC.No = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.negotiationBC.No]}
def channelCon : __End_b_B_negotiationBCNo_YourShareImp = {__End_b_B_negotiationBCNo_YourShareImp(c,session)}

}


protected  trait End_b_B_negotiationBCNo_YourShare extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_negotiationBCNo_YourShare extends End_b_B_negotiationBCNo_YourShare {
  override protected def __children: List[EPnegotiationBC_b_B] = List()
  override type implT = __End_b_B_negotiationBCNo_YourShareImp
  override type implNextT = Nothing
override def toString() : String = {"EPnegotiationBC_b_B.End_b_B_negotiationBCNo_YourShare"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_negotiationBCNo_YourShareImp(c,session)}

protected case class __End_b_B_negotiationBCNo_YourShareImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_negotiationBCNo_YourShare
}
  
}



  trait SndEnd extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeSnd
  object SndEnd extends SndEnd {
  override protected def __children: List[EPnegotiationBC_b_B] = List(End_b_B_negotiationBCEnd)
  override type implT = __SndEndImp
  override type implNextT = __End_b_B_negotiationBCEndImp
override def toString() : String = {"EPnegotiationBC_b_B.SndEnd"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "End" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndImp(c,session)}

protected case class __SndEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEnd
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.negotiationBC.End) : __End_b_B_negotiationBCEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_b_B_negotiationBCEndImp(c,session)}
def !(m : MESSAGES.negotiationBC.End) : __End_b_B_negotiationBCEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_b_B_negotiationBCEndImp(c,session)}
def snd(m : MESSAGES.negotiationBC.End) : __End_b_B_negotiationBCEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_b_B_negotiationBCEndImp(c,session)}

}


protected  trait End_b_B_negotiationBCEnd extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_negotiationBCEnd extends End_b_B_negotiationBCEnd {
  override protected def __children: List[EPnegotiationBC_b_B] = List()
  override type implT = __End_b_B_negotiationBCEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPnegotiationBC_b_B.End_b_B_negotiationBCEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_negotiationBCEndImp(c,session)}

protected case class __End_b_B_negotiationBCEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_negotiationBCEnd
}
  
}



  trait Failed_c_C extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeFDtct
  object Failed_c_C extends Failed_c_C {
  override protected def __children: List[EPnegotiationBC_b_B] = List(End_b_B_negotiationBCFHandling)
  override type implT = __Failed_c_CImp
  override type implNextT = __End_b_B_negotiationBCFHandlingImp
override def toString() : String = {"EPnegotiationBC_b_B.Failed_c_C"}
  override def suspect : Role = Role("c",RoleSet("C")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_c_CImp(c,session)}

protected case class __Failed_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_c_C
}
  def failed_c_C(): __End_b_B_negotiationBCFHandlingImp = {__End_b_B_negotiationBCFHandlingImp(c,session)}

}


protected  trait End_b_B_negotiationBCFHandling extends EPnegotiationBC_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_negotiationBCFHandling extends End_b_B_negotiationBCFHandling {
  override protected def __children: List[EPnegotiationBC_b_B] = List()
  override type implT = __End_b_B_negotiationBCFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPnegotiationBC_b_B.End_b_B_negotiationBCFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_negotiationBCFHandlingImp(c,session)}

protected case class __End_b_B_negotiationBCFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_negotiationBCFHandling
}
  
}


}

object SelS_b_B{
trait EPSelS_b_B extends __EPType_B

object EPSelS_b_B extends EPSelS_b_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelS_b_B] = List(Hdl)
  override type implT = __EPSelS_b_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelS_b_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("s",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("S"), RoleSet("C")) 
  override def prjTo : RRole = Role("b",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "SelS"
}

protected case class __EPSelS_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelS_b_B
}
  
}


protected  trait Hdl extends EPSelS_b_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelS_b_B] = List(SpawnthreeBuyers,RcvFSelb)
  override type implT = __HdlImp
  override type implNextT = __SpawnthreeBuyersImp
override def toString() : String = {"EPSelS_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnthreeBuyers extends EPSelS_b_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnthreeBuyers extends SpawnthreeBuyers {
  override protected def __children: List[EPSelS_b_B] = List(End_b_B_SelS)
  override type implT = __SpawnthreeBuyersImp
  override type implNextT = __End_b_B_SelSImp
override def toString() : String = {"EPSelS_b_B.SpawnthreeBuyers"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def pickR: RoleSet = RoleSet("S") 
  override def rs: List[RoleSet] = List(RoleSet("C")) 
  override def name: String = "threeBuyers" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnthreeBuyersImp(c,session)}

protected case class __SpawnthreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnthreeBuyers
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_b_B_SelS extends EPSelS_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_SelS extends End_b_B_SelS {
  override protected def __children: List[EPSelS_b_B] = List()
  override type implT = __End_b_B_SelSImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_b_B.End_b_B_SelS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_SelSImp(c,session)}

protected case class __End_b_B_SelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_SelS
}
  
}



  trait RcvFSelb extends EPSelS_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvFSelb extends RcvFSelb {
  override protected def __children: List[EPSelS_b_B] = List(End_b_B_SelSFHandling)
  override type implT = __RcvFSelbImp
  override type implNextT = __End_b_B_SelSFHandlingImp
override def toString() : String = {"EPSelS_b_B.RcvFSelb"}
  override type msgT = MESSAGES.SelS.FSelb
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FSelb"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFSelbImp(c,session)}

protected case class __RcvFSelbImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFSelb
}
  def rcvFrma_A : (MESSAGES.SelS.FSelb,__End_b_B_SelSFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelb],__End_b_B_SelSFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SelS.FSelb,__End_b_B_SelSFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelb],__End_b_B_SelSFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.SelS.FSelb = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelb]}
def ? : MESSAGES.SelS.FSelb = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.SelS.FSelb]}
def channelCon : __End_b_B_SelSFHandlingImp = {__End_b_B_SelSFHandlingImp(c,session)}

}


protected  trait End_b_B_SelSFHandling extends EPSelS_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_SelSFHandling extends End_b_B_SelSFHandling {
  override protected def __children: List[EPSelS_b_B] = List()
  override type implT = __End_b_B_SelSFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_b_B.End_b_B_SelSFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_SelSFHandlingImp(c,session)}

protected case class __End_b_B_SelSFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_SelSFHandling
}
  
}


}

object Main_b_B{
trait EPMain_b_B extends __EPType_B

object EPMain_b_B extends EPMain_b_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_b_B] = List(Hdl)
  override type implT = __EPMain_b_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_b_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("b",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C"), RoleSet("S")) 
  override def prjTo : RRole = Role("b",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Main"
}

protected case class __EPMain_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_b_B
}
  
}


protected  trait Hdl extends EPMain_b_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_b_B] = List(SpawnSelS,End_b_B_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelSImp
override def toString() : String = {"EPMain_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelS extends EPMain_b_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelS extends SpawnSelS {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_Main)
  override type implT = __SpawnSelSImp
  override type implNextT = __End_b_B_MainImp
override def toString() : String = {"EPMain_b_B.SpawnSelS"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("S") 
  override def rs: List[RoleSet] = List(RoleSet("S"), RoleSet("C")) 
  override def name: String = "SelS" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelSImp(c,session)}

protected case class __SpawnSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelS
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_b_B_Main extends EPMain_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_Main extends End_b_B_Main {
  override protected def __children: List[EPMain_b_B] = List()
  override type implT = __End_b_B_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_b_B.End_b_B_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_MainImp(c,session)}

protected case class __End_b_B_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_Main
}
  
}



protected  trait End_b_B_MainFHandling extends EPMain_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_MainFHandling extends End_b_B_MainFHandling {
  override protected def __children: List[EPMain_b_B] = List()
  override type implT = __End_b_B_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_b_B.End_b_B_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_MainFHandlingImp(c,session)}

protected case class __End_b_B_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_MainFHandling
}
  
}


}

}

object A {
val subs : Seq[dsl.ChannelTypeSubS] = List(threeBuyers_a_A.EPthreeBuyers_a_A,SelS_a_A.EPSelS_a_A,Main_a_A.EPMain_a_A)
trait __EPType_A extends AbstractChannelType {

}

trait EPType_A[T<: TState] extends AbstractEndPoint[__EPType_A,T] {
override val roleSet: RoleSet = RoleSet("A")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(threeBuyers_a_A.EPthreeBuyers_a_A,SelS_a_A.EPSelS_a_A,Main_a_A.EPMain_a_A)

}

object threeBuyers_a_A{
trait EPthreeBuyers_a_A extends __EPType_A

object EPthreeBuyers_a_A extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPthreeBuyers_a_A] = List(Hdl)
  override type implT = __EPthreeBuyers_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPthreeBuyers_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("ss",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "threeBuyers"
}

protected case class __EPthreeBuyers_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPthreeBuyers_a_A
}
  
}


protected  trait Hdl extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPthreeBuyers_a_A] = List(SndBook,Failed_ss_S)
  override type implT = __HdlImp
  override type implNextT = __SndBookImp
override def toString() : String = {"EPthreeBuyers_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndBook extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeSnd
  object SndBook extends SndBook {
  override protected def __children: List[EPthreeBuyers_a_A] = List(RcvPriceA)
  override type implT = __SndBookImp
  override type implNextT = __RcvPriceAImp
override def toString() : String = {"EPthreeBuyers_a_A.SndBook"}
    override def to : RRole = Role("s",RoleSet("S")) 
   override def l : String = "Book" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBookImp(c,session)}

protected case class __SndBookImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBook
}
  private var notUsed = true
def sndTos_S(m : MESSAGES.threeBuyers.Book) : __RcvPriceAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__RcvPriceAImp(c,session)}
def !(m : MESSAGES.threeBuyers.Book) : __RcvPriceAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__RcvPriceAImp(c,session)}
def snd(m : MESSAGES.threeBuyers.Book) : __RcvPriceAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__RcvPriceAImp(c,session)}

}


  trait RcvPriceA extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvPriceA extends RcvPriceA {
  override protected def __children: List[EPthreeBuyers_a_A] = List(SndMyShare)
  override type implT = __RcvPriceAImp
  override type implNextT = __SndMyShareImp
override def toString() : String = {"EPthreeBuyers_a_A.RcvPriceA"}
  override type msgT = MESSAGES.threeBuyers.PriceA
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "PriceA"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPriceAImp(c,session)}

protected case class __RcvPriceAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPriceA
}
  def rcvFrms_S : (MESSAGES.threeBuyers.PriceA,__SndMyShareImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.PriceA],__SndMyShareImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.PriceA,__SndMyShareImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.PriceA],__SndMyShareImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.PriceA = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.PriceA]}
def ? : MESSAGES.threeBuyers.PriceA = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.threeBuyers.PriceA]}
def channelCon : __SndMyShareImp = {__SndMyShareImp(c,session)}

}


  trait SndMyShare extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeSnd
  object SndMyShare extends SndMyShare {
  override protected def __children: List[EPthreeBuyers_a_A] = List(Merge_OkA_QuitA)
  override type implT = __SndMyShareImp
  override type implNextT = __Merge_OkA_QuitAImp
override def toString() : String = {"EPthreeBuyers_a_A.SndMyShare"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "MyShare" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMyShareImp(c,session)}

protected case class __SndMyShareImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndMyShare
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.threeBuyers.MyShare) : __Merge_OkA_QuitAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__Merge_OkA_QuitAImp(c,session)}
def !(m : MESSAGES.threeBuyers.MyShare) : __Merge_OkA_QuitAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__Merge_OkA_QuitAImp(c,session)}
def snd(m : MESSAGES.threeBuyers.MyShare) : __Merge_OkA_QuitAImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__Merge_OkA_QuitAImp(c,session)}

}


protected  trait Merge_OkA_QuitA extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeMerge
protected  object Merge_OkA_QuitA extends Merge_OkA_QuitA {
  override protected def __children: List[EPthreeBuyers_a_A] = List(RcvOkA,RcvQuitA)
  override type implT = __Merge_OkA_QuitAImp
  override type implNextT = __RcvOkAImp
override def toString() : String = {"EPthreeBuyers_a_A.Merge_OkA_QuitA"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Merge_OkA_QuitAImp(c,session)}

protected case class __Merge_OkA_QuitAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Merge_OkA_QuitA
}
  
}


  trait RcvOkA extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvOkA extends RcvOkA {
  override protected def __children: List[EPthreeBuyers_a_A] = List(End_a_A_threeBuyersOkA)
  override type implT = __RcvOkAImp
  override type implNextT = __End_a_A_threeBuyersOkAImp
override def toString() : String = {"EPthreeBuyers_a_A.RcvOkA"}
  override type msgT = MESSAGES.threeBuyers.OkA
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "OkA"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvOkAImp(c,session)}

protected case class __RcvOkAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvOkA
}
  def rcvFrmb_B : (MESSAGES.threeBuyers.OkA,__End_a_A_threeBuyersOkAImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.OkA],__End_a_A_threeBuyersOkAImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.OkA,__End_a_A_threeBuyersOkAImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.OkA],__End_a_A_threeBuyersOkAImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.OkA = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.OkA]}
def ? : MESSAGES.threeBuyers.OkA = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.OkA]}
def channelCon : __End_a_A_threeBuyersOkAImp = {__End_a_A_threeBuyersOkAImp(c,session)}

}


protected  trait End_a_A_threeBuyersOkA extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_threeBuyersOkA extends End_a_A_threeBuyersOkA {
  override protected def __children: List[EPthreeBuyers_a_A] = List()
  override type implT = __End_a_A_threeBuyersOkAImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_a_A.End_a_A_threeBuyersOkA"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_threeBuyersOkAImp(c,session)}

protected case class __End_a_A_threeBuyersOkAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_threeBuyersOkA
}
  
}



  trait RcvQuitA extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvQuitA extends RcvQuitA {
  override protected def __children: List[EPthreeBuyers_a_A] = List(End_a_A_threeBuyersQuitA)
  override type implT = __RcvQuitAImp
  override type implNextT = __End_a_A_threeBuyersQuitAImp
override def toString() : String = {"EPthreeBuyers_a_A.RcvQuitA"}
  override type msgT = MESSAGES.threeBuyers.QuitA
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "QuitA"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvQuitAImp(c,session)}

protected case class __RcvQuitAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvQuitA
}
  def rcvFrmb_B : (MESSAGES.threeBuyers.QuitA,__End_a_A_threeBuyersQuitAImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.QuitA],__End_a_A_threeBuyersQuitAImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.threeBuyers.QuitA,__End_a_A_threeBuyersQuitAImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.QuitA],__End_a_A_threeBuyersQuitAImp(c,session))) 
}
def rcvMSG : MESSAGES.threeBuyers.QuitA = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.QuitA]}
def ? : MESSAGES.threeBuyers.QuitA = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.threeBuyers.QuitA]}
def channelCon : __End_a_A_threeBuyersQuitAImp = {__End_a_A_threeBuyersQuitAImp(c,session)}

}


protected  trait End_a_A_threeBuyersQuitA extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_threeBuyersQuitA extends End_a_A_threeBuyersQuitA {
  override protected def __children: List[EPthreeBuyers_a_A] = List()
  override type implT = __End_a_A_threeBuyersQuitAImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_a_A.End_a_A_threeBuyersQuitA"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_threeBuyersQuitAImp(c,session)}

protected case class __End_a_A_threeBuyersQuitAImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_threeBuyersQuitA
}
  
}



  trait Failed_ss_S extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_ss_S extends Failed_ss_S {
  override protected def __children: List[EPthreeBuyers_a_A] = List(SndF3Bb)
  override type implT = __Failed_ss_SImp
  override type implNextT = __SndF3BbImp
override def toString() : String = {"EPthreeBuyers_a_A.Failed_ss_S"}
  override def suspect : Role = Role("ss",RoleSet("S")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_ss_SImp(c,session)}

protected case class __Failed_ss_SImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_ss_S
}
  def failed_ss_S(): __SndF3BbImp = {__SndF3BbImp(c,session)}

}


  trait SndF3Bb extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeSnd
  object SndF3Bb extends SndF3Bb {
  override protected def __children: List[EPthreeBuyers_a_A] = List(SndF3Bs)
  override type implT = __SndF3BbImp
  override type implNextT = __SndF3BsImp
override def toString() : String = {"EPthreeBuyers_a_A.SndF3Bb"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "F3Bb" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF3BbImp(c,session)}

protected case class __SndF3BbImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF3Bb
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.threeBuyers.F3Bb) : __SndF3BsImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndF3BsImp(c,session)}
def !(m : MESSAGES.threeBuyers.F3Bb) : __SndF3BsImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndF3BsImp(c,session)}
def snd(m : MESSAGES.threeBuyers.F3Bb) : __SndF3BsImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndF3BsImp(c,session)}

}


  trait SndF3Bs extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeSnd
  object SndF3Bs extends SndF3Bs {
  override protected def __children: List[EPthreeBuyers_a_A] = List(SndF3BC)
  override type implT = __SndF3BsImp
  override type implNextT = __SndF3BCImp
override def toString() : String = {"EPthreeBuyers_a_A.SndF3Bs"}
    override def to : RRole = Role("s",RoleSet("S")) 
   override def l : String = "F3Bs" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF3BsImp(c,session)}

protected case class __SndF3BsImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF3Bs
}
  private var notUsed = true
def sndTos_S(m : MESSAGES.threeBuyers.F3Bs) : __SndF3BCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SndF3BCImp(c,session)}
def !(m : MESSAGES.threeBuyers.F3Bs) : __SndF3BCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SndF3BCImp(c,session)}
def snd(m : MESSAGES.threeBuyers.F3Bs) : __SndF3BCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SndF3BCImp(c,session)}

}


  trait SndF3BC extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeSnd
  object SndF3BC extends SndF3BC {
  override protected def __children: List[EPthreeBuyers_a_A] = List(End_a_A_threeBuyersFHandling)
  override type implT = __SndF3BCImp
  override type implNextT = __End_a_A_threeBuyersFHandlingImp
override def toString() : String = {"EPthreeBuyers_a_A.SndF3BC"}
    override def to : RRole = RoleSet("C") 
   override def l : String = "F3BC" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF3BCImp(c,session)}

protected case class __SndF3BCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF3BC
}
  private var notUsed = true
def sndToC(m : MESSAGES.threeBuyers.F3BC) : __End_a_A_threeBuyersFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__End_a_A_threeBuyersFHandlingImp(c,session)}
def !(m : MESSAGES.threeBuyers.F3BC) : __End_a_A_threeBuyersFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__End_a_A_threeBuyersFHandlingImp(c,session)}
def snd(m : MESSAGES.threeBuyers.F3BC) : __End_a_A_threeBuyersFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__End_a_A_threeBuyersFHandlingImp(c,session)}

}


protected  trait End_a_A_threeBuyersFHandling extends EPthreeBuyers_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_threeBuyersFHandling extends End_a_A_threeBuyersFHandling {
  override protected def __children: List[EPthreeBuyers_a_A] = List()
  override type implT = __End_a_A_threeBuyersFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPthreeBuyers_a_A.End_a_A_threeBuyersFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_threeBuyersFHandlingImp(c,session)}

protected case class __End_a_A_threeBuyersFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_threeBuyersFHandling
}
  
}


}

object SelS_a_A{
trait EPSelS_a_A extends __EPType_A

object EPSelS_a_A extends EPSelS_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelS_a_A] = List(Hdl)
  override type implT = __EPSelS_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelS_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("s",RoleSet("S")) 
  override def argsRs: List[RoleSet] = List(RoleSet("S"), RoleSet("C")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "SelS"
}

protected case class __EPSelS_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelS_a_A
}
  
}


protected  trait Hdl extends EPSelS_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelS_a_A] = List(SpawnthreeBuyers,Failed_s_S)
  override type implT = __HdlImp
  override type implNextT = __SpawnthreeBuyersImp
override def toString() : String = {"EPSelS_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnthreeBuyers extends EPSelS_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnthreeBuyers extends SpawnthreeBuyers {
  override protected def __children: List[EPSelS_a_A] = List(End_a_A_SelS)
  override type implT = __SpawnthreeBuyersImp
  override type implNextT = __End_a_A_SelSImp
override def toString() : String = {"EPSelS_a_A.SpawnthreeBuyers"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B")), Role("s",RoleSet("S"))) 
  override def pickR: RoleSet = RoleSet("S") 
  override def rs: List[RoleSet] = List(RoleSet("C")) 
  override def name: String = "threeBuyers" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnthreeBuyersImp(c,session)}

protected case class __SpawnthreeBuyersImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnthreeBuyers
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_a_A_SelS extends EPSelS_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_SelS extends End_a_A_SelS {
  override protected def __children: List[EPSelS_a_A] = List()
  override type implT = __End_a_A_SelSImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_a_A.End_a_A_SelS"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_SelSImp(c,session)}

protected case class __End_a_A_SelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_SelS
}
  
}



  trait Failed_s_S extends EPSelS_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_s_S extends Failed_s_S {
  override protected def __children: List[EPSelS_a_A] = List(SndFSelb)
  override type implT = __Failed_s_SImp
  override type implNextT = __SndFSelbImp
override def toString() : String = {"EPSelS_a_A.Failed_s_S"}
  override def suspect : Role = Role("s",RoleSet("S")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_s_SImp(c,session)}

protected case class __Failed_s_SImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_s_S
}
  def failed_s_S(): __SndFSelbImp = {__SndFSelbImp(c,session)}

}


  trait SndFSelb extends EPSelS_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFSelb extends SndFSelb {
  override protected def __children: List[EPSelS_a_A] = List(SndFSelS)
  override type implT = __SndFSelbImp
  override type implNextT = __SndFSelSImp
override def toString() : String = {"EPSelS_a_A.SndFSelb"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "FSelb" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFSelbImp(c,session)}

protected case class __SndFSelbImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFSelb
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.SelS.FSelb) : __SndFSelSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndFSelSImp(c,session)}
def !(m : MESSAGES.SelS.FSelb) : __SndFSelSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndFSelSImp(c,session)}
def snd(m : MESSAGES.SelS.FSelb) : __SndFSelSImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndFSelSImp(c,session)}

}


  trait SndFSelS extends EPSelS_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFSelS extends SndFSelS {
  override protected def __children: List[EPSelS_a_A] = List(SndFSelC)
  override type implT = __SndFSelSImp
  override type implNextT = __SndFSelCImp
override def toString() : String = {"EPSelS_a_A.SndFSelS"}
    override def to : RRole = RoleSet("S") 
   override def l : String = "FSelS" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFSelSImp(c,session)}

protected case class __SndFSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFSelS
}
  private var notUsed = true
def sndToS(m : MESSAGES.SelS.FSelS) : __SndFSelCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("S"),m)
__SndFSelCImp(c,session)}
def !(m : MESSAGES.SelS.FSelS) : __SndFSelCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("S"),m)
__SndFSelCImp(c,session)}
def snd(m : MESSAGES.SelS.FSelS) : __SndFSelCImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("S"),m)
__SndFSelCImp(c,session)}

}


  trait SndFSelC extends EPSelS_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFSelC extends SndFSelC {
  override protected def __children: List[EPSelS_a_A] = List(End_a_A_SelSFHandling)
  override type implT = __SndFSelCImp
  override type implNextT = __End_a_A_SelSFHandlingImp
override def toString() : String = {"EPSelS_a_A.SndFSelC"}
    override def to : RRole = RoleSet("C") 
   override def l : String = "FSelC" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFSelCImp(c,session)}

protected case class __SndFSelCImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFSelC
}
  private var notUsed = true
def sndToC(m : MESSAGES.SelS.FSelC) : __End_a_A_SelSFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__End_a_A_SelSFHandlingImp(c,session)}
def !(m : MESSAGES.SelS.FSelC) : __End_a_A_SelSFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__End_a_A_SelSFHandlingImp(c,session)}
def snd(m : MESSAGES.SelS.FSelC) : __End_a_A_SelSFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__End_a_A_SelSFHandlingImp(c,session)}

}


protected  trait End_a_A_SelSFHandling extends EPSelS_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_SelSFHandling extends End_a_A_SelSFHandling {
  override protected def __children: List[EPSelS_a_A] = List()
  override type implT = __End_a_A_SelSFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelS_a_A.End_a_A_SelSFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_SelSFHandlingImp(c,session)}

protected case class __End_a_A_SelSFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_SelSFHandling
}
  
}


}

object Main_a_A{
trait EPMain_a_A extends __EPType_A

object EPMain_a_A extends EPMain_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_a_A] = List(Hdl)
  override type implT = __EPMain_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("b",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("C"), RoleSet("S")) 
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
  override protected def __children: List[EPMain_a_A] = List(SpawnSelS,Failed_b_B)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelSImp
override def toString() : String = {"EPMain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelS extends EPMain_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelS extends SpawnSelS {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_Main)
  override type implT = __SpawnSelSImp
  override type implNextT = __End_a_A_MainImp
override def toString() : String = {"EPMain_a_A.SpawnSelS"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("S") 
  override def rs: List[RoleSet] = List(RoleSet("S"), RoleSet("C")) 
  override def name: String = "SelS" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelSImp(c,session)}

protected case class __SpawnSelSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelS
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



  trait Failed_b_B extends EPMain_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_b_B extends Failed_b_B {
  override protected def __children: List[EPMain_a_A] = List(SndFMainF1)
  override type implT = __Failed_b_BImp
  override type implNextT = __SndFMainF1Imp
override def toString() : String = {"EPMain_a_A.Failed_b_B"}
  override def suspect : Role = Role("b",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b_BImp(c,session)}

protected case class __Failed_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b_B
}
  def failed_b_B(): __SndFMainF1Imp = {__SndFMainF1Imp(c,session)}

}


  trait SndFMainF1 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFMainF1 extends SndFMainF1 {
  override protected def __children: List[EPMain_a_A] = List(SndFMainF2)
  override type implT = __SndFMainF1Imp
  override type implNextT = __SndFMainF2Imp
override def toString() : String = {"EPMain_a_A.SndFMainF1"}
    override def to : RRole = RoleSet("C") 
   override def l : String = "FMainF1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFMainF1Imp(c,session)}

protected case class __SndFMainF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFMainF1
}
  private var notUsed = true
def sndToC(m : MESSAGES.Main.FMainF1) : __SndFMainF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__SndFMainF2Imp(c,session)}
def !(m : MESSAGES.Main.FMainF1) : __SndFMainF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__SndFMainF2Imp(c,session)}
def snd(m : MESSAGES.Main.FMainF1) : __SndFMainF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__SndFMainF2Imp(c,session)}

}


  trait SndFMainF2 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFMainF2 extends SndFMainF2 {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainFHandling)
  override type implT = __SndFMainF2Imp
  override type implNextT = __End_a_A_MainFHandlingImp
override def toString() : String = {"EPMain_a_A.SndFMainF2"}
    override def to : RRole = RoleSet("S") 
   override def l : String = "FMainF2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFMainF2Imp(c,session)}

protected case class __SndFMainF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFMainF2
}
  private var notUsed = true
def sndToS(m : MESSAGES.Main.FMainF2) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("S"),m)
__End_a_A_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.FMainF2) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("S"),m)
__End_a_A_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.FMainF2) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("S"),m)
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

}

}
