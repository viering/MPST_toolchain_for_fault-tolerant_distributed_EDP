package example.faulty.twoBuyer.coverage
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object TwoBuyer {
object RS {
val Buyer : RoleSet = RoleSet("Buyer")
val Seller : RoleSet = RoleSet("Seller")
}

object MESSAGES {
object TwoBuyer {
case class ArrivalDate(d:String) extends MSG {
   override def l : String = "ArrivalDate"
}

case class Price2(i:Int) extends MSG {
   override def l : String = "Price2"
}

case class Quit() extends MSG {
   override def l : String = "Quit"
}

case class CancelTrans() extends MSG {
   override def l : String = "CancelTrans"
}

case class Ok() extends MSG {
   override def l : String = "Ok"
}

case class Addr(s:String) extends MSG {
   override def l : String = "Addr"
}

case class IPay(i:Int) extends MSG {
   override def l : String = "IPay"
}

case class Request(s:String) extends MSG {
   override def l : String = "Request"
}

case class Price1(i:Int) extends MSG {
   override def l : String = "Price1"
}

}

object Main {
case class CancelMain() extends MSG {
   override def l : String = "CancelMain"
}

}

}

object PROTOCOLS {
object Main {
val b1_Buyer = Role("b1",RoleSet("Buyer"))
val Buyer = RoleSet("Buyer")
val s_Seller = Role("s",RoleSet("Seller"))
}

object TwoBuyer {
val b2_Buyer = Role("b2",RoleSet("Buyer"))
val s_Seller = Role("s",RoleSet("Seller"))
val b1_Buyer = Role("b1",RoleSet("Buyer"))
}

}

object Buyer {
val subs : Seq[dsl.ChannelTypeSubS] = List(TwoBuyer_b2_Buyer.EPTwoBuyer_b2_Buyer,TwoBuyer_b1_Buyer.EPTwoBuyer_b1_Buyer,Main_b1_Buyer.EPMain_b1_Buyer,Main_Buyer.EPMain_Buyer)
trait __EPType_Buyer extends AbstractChannelType {

}

trait EPType_Buyer[T<: TState] extends AbstractEndPoint[__EPType_Buyer,T] {
override val roleSet: RoleSet = RoleSet("Buyer")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(TwoBuyer_b2_Buyer.EPTwoBuyer_b2_Buyer,TwoBuyer_b1_Buyer.EPTwoBuyer_b1_Buyer,Main_b1_Buyer.EPMain_b1_Buyer,Main_Buyer.EPMain_Buyer)

}

object TwoBuyer_b2_Buyer{
trait EPTwoBuyer_b2_Buyer extends __EPType_Buyer

object EPTwoBuyer_b2_Buyer extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List(Hdl)
  override type implT = __EPTwoBuyer_b2_BuyerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPTwoBuyer_b2_BuyerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("b1",RoleSet("Buyer"))) 
  override def argsP: Role = Role("b2",RoleSet("Buyer")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("b2",RoleSet("Buyer")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "TwoBuyer"
}

protected case class __EPTwoBuyer_b2_BuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPTwoBuyer_b2_Buyer
}
  
}


protected  trait Hdl extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List(RcvPrice2,End_b2_Buyer_TwoBuyerFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvPrice2Imp
override def toString() : String = {"EPTwoBuyer_b2_Buyer.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvPrice2 extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeRcv
  object RcvPrice2 extends RcvPrice2 {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List(RcvIPay)
  override type implT = __RcvPrice2Imp
  override type implNextT = __RcvIPayImp
override def toString() : String = {"EPTwoBuyer_b2_Buyer.RcvPrice2"}
  override type msgT = MESSAGES.TwoBuyer.Price2
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "Price2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPrice2Imp(c,session)}

protected case class __RcvPrice2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPrice2
}
  def rcvFrms_Seller : (MESSAGES.TwoBuyer.Price2,__RcvIPayImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.Price2],__RcvIPayImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoBuyer.Price2,__RcvIPayImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.Price2],__RcvIPayImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoBuyer.Price2 = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.Price2]}
def ? : MESSAGES.TwoBuyer.Price2 = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.Price2]}
def channelCon : __RcvIPayImp = {__RcvIPayImp(c,session)}

}


  trait RcvIPay extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeRcv
  object RcvIPay extends RcvIPay {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List(SelOkQuit)
  override type implT = __RcvIPayImp
  override type implNextT = __SelOkQuitImp
override def toString() : String = {"EPTwoBuyer_b2_Buyer.RcvIPay"}
  override type msgT = MESSAGES.TwoBuyer.IPay
   override def frm : Role = Role("b1",RoleSet("Buyer")) 
   override def l : String = "IPay"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvIPayImp(c,session)}

protected case class __RcvIPayImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvIPay
}
  def rcvFrmb1_Buyer : (MESSAGES.TwoBuyer.IPay,__SelOkQuitImp) = {(c.rcv(Role("b1",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.IPay],__SelOkQuitImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoBuyer.IPay,__SelOkQuitImp),T]) : T = {
  f((c.rcv(Role("b1",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.IPay],__SelOkQuitImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoBuyer.IPay = {c.rcv(Role("b1",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.IPay]}
def ? : MESSAGES.TwoBuyer.IPay = {c.rcv(Role("b1",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.IPay]}
def channelCon : __SelOkQuitImp = {__SelOkQuitImp(c,session)}

}


protected  trait SelOkQuit extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeSel
protected  object SelOkQuit extends SelOkQuit {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List(SndOk,SndQuit)
  override type implT = __SelOkQuitImp
  override type implNextT = __SndOkImp
override def toString() : String = {"EPTwoBuyer_b2_Buyer.SelOkQuit"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOkQuitImp(c,session)}

protected case class __SelOkQuitImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOkQuit
}
  private var notUsed = true
def !(m : MESSAGES.TwoBuyer.Ok) : __SndAddrImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
 __SndAddrImp(c,session)}
def sndTos_Seller(m : MESSAGES.TwoBuyer.Ok) : __SndAddrImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
 __SndAddrImp(c,session)}

def !(m : MESSAGES.TwoBuyer.Quit) : __End_b2_Buyer_TwoBuyerQuitImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
 __End_b2_Buyer_TwoBuyerQuitImp(c,session)}
def sndTos_Seller(m : MESSAGES.TwoBuyer.Quit) : __End_b2_Buyer_TwoBuyerQuitImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
 __End_b2_Buyer_TwoBuyerQuitImp(c,session)}

}


  trait SndOk extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeSnd
  object SndOk extends SndOk {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List(SndAddr)
  override type implT = __SndOkImp
  override type implNextT = __SndAddrImp
override def toString() : String = {"EPTwoBuyer_b2_Buyer.SndOk"}
    override def to : RRole = Role("s",RoleSet("Seller")) 
   override def l : String = "Ok" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndOkImp(c,session)}

protected case class __SndOkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndOk
}
  private var notUsed = true
def sndTos_Seller(m : MESSAGES.TwoBuyer.Ok) : __SndAddrImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__SndAddrImp(c,session)}
def !(m : MESSAGES.TwoBuyer.Ok) : __SndAddrImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__SndAddrImp(c,session)}
def snd(m : MESSAGES.TwoBuyer.Ok) : __SndAddrImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__SndAddrImp(c,session)}

}


  trait SndAddr extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeSnd
  object SndAddr extends SndAddr {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List(RcvArrivalDate)
  override type implT = __SndAddrImp
  override type implNextT = __RcvArrivalDateImp
override def toString() : String = {"EPTwoBuyer_b2_Buyer.SndAddr"}
    override def to : RRole = Role("s",RoleSet("Seller")) 
   override def l : String = "Addr" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAddrImp(c,session)}

protected case class __SndAddrImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAddr
}
  private var notUsed = true
def sndTos_Seller(m : MESSAGES.TwoBuyer.Addr) : __RcvArrivalDateImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvArrivalDateImp(c,session)}
def !(m : MESSAGES.TwoBuyer.Addr) : __RcvArrivalDateImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvArrivalDateImp(c,session)}
def snd(m : MESSAGES.TwoBuyer.Addr) : __RcvArrivalDateImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvArrivalDateImp(c,session)}

}


  trait RcvArrivalDate extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeRcv
  object RcvArrivalDate extends RcvArrivalDate {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List(End_b2_Buyer_TwoBuyerOk)
  override type implT = __RcvArrivalDateImp
  override type implNextT = __End_b2_Buyer_TwoBuyerOkImp
override def toString() : String = {"EPTwoBuyer_b2_Buyer.RcvArrivalDate"}
  override type msgT = MESSAGES.TwoBuyer.ArrivalDate
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "ArrivalDate"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvArrivalDateImp(c,session)}

protected case class __RcvArrivalDateImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvArrivalDate
}
  def rcvFrms_Seller : (MESSAGES.TwoBuyer.ArrivalDate,__End_b2_Buyer_TwoBuyerOkImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.ArrivalDate],__End_b2_Buyer_TwoBuyerOkImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoBuyer.ArrivalDate,__End_b2_Buyer_TwoBuyerOkImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.ArrivalDate],__End_b2_Buyer_TwoBuyerOkImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoBuyer.ArrivalDate = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.ArrivalDate]}
def ? : MESSAGES.TwoBuyer.ArrivalDate = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.ArrivalDate]}
def channelCon : __End_b2_Buyer_TwoBuyerOkImp = {__End_b2_Buyer_TwoBuyerOkImp(c,session)}

}


protected  trait End_b2_Buyer_TwoBuyerOk extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeEnd
protected  object End_b2_Buyer_TwoBuyerOk extends End_b2_Buyer_TwoBuyerOk {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List()
  override type implT = __End_b2_Buyer_TwoBuyerOkImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoBuyer_b2_Buyer.End_b2_Buyer_TwoBuyerOk"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b2_Buyer_TwoBuyerOkImp(c,session)}

protected case class __End_b2_Buyer_TwoBuyerOkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b2_Buyer_TwoBuyerOk
}
  
}



  trait SndQuit extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeSnd
  object SndQuit extends SndQuit {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List(End_b2_Buyer_TwoBuyerQuit)
  override type implT = __SndQuitImp
  override type implNextT = __End_b2_Buyer_TwoBuyerQuitImp
override def toString() : String = {"EPTwoBuyer_b2_Buyer.SndQuit"}
    override def to : RRole = Role("s",RoleSet("Seller")) 
   override def l : String = "Quit" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndQuitImp(c,session)}

protected case class __SndQuitImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndQuit
}
  private var notUsed = true
def sndTos_Seller(m : MESSAGES.TwoBuyer.Quit) : __End_b2_Buyer_TwoBuyerQuitImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__End_b2_Buyer_TwoBuyerQuitImp(c,session)}
def !(m : MESSAGES.TwoBuyer.Quit) : __End_b2_Buyer_TwoBuyerQuitImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__End_b2_Buyer_TwoBuyerQuitImp(c,session)}
def snd(m : MESSAGES.TwoBuyer.Quit) : __End_b2_Buyer_TwoBuyerQuitImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__End_b2_Buyer_TwoBuyerQuitImp(c,session)}

}


protected  trait End_b2_Buyer_TwoBuyerQuit extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeEnd
protected  object End_b2_Buyer_TwoBuyerQuit extends End_b2_Buyer_TwoBuyerQuit {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List()
  override type implT = __End_b2_Buyer_TwoBuyerQuitImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoBuyer_b2_Buyer.End_b2_Buyer_TwoBuyerQuit"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b2_Buyer_TwoBuyerQuitImp(c,session)}

protected case class __End_b2_Buyer_TwoBuyerQuitImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b2_Buyer_TwoBuyerQuit
}
  
}



protected  trait End_b2_Buyer_TwoBuyerFHandling extends EPTwoBuyer_b2_Buyer with event_lang.dsl.ChannelTypeEnd
protected  object End_b2_Buyer_TwoBuyerFHandling extends End_b2_Buyer_TwoBuyerFHandling {
  override protected def __children: List[EPTwoBuyer_b2_Buyer] = List()
  override type implT = __End_b2_Buyer_TwoBuyerFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoBuyer_b2_Buyer.End_b2_Buyer_TwoBuyerFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b2_Buyer_TwoBuyerFHandlingImp(c,session)}

protected case class __End_b2_Buyer_TwoBuyerFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b2_Buyer_TwoBuyerFHandling
}
  
}


}

object TwoBuyer_b1_Buyer{
trait EPTwoBuyer_b1_Buyer extends __EPType_Buyer

object EPTwoBuyer_b1_Buyer extends EPTwoBuyer_b1_Buyer with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPTwoBuyer_b1_Buyer] = List(Hdl)
  override type implT = __EPTwoBuyer_b1_BuyerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPTwoBuyer_b1_BuyerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("b1",RoleSet("Buyer"))) 
  override def argsP: Role = Role("b2",RoleSet("Buyer")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("b1",RoleSet("Buyer")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "TwoBuyer"
}

protected case class __EPTwoBuyer_b1_BuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPTwoBuyer_b1_Buyer
}
  
}


protected  trait Hdl extends EPTwoBuyer_b1_Buyer with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPTwoBuyer_b1_Buyer] = List(SndRequest,RcvCancelTrans)
  override type implT = __HdlImp
  override type implNextT = __SndRequestImp
override def toString() : String = {"EPTwoBuyer_b1_Buyer.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndRequest extends EPTwoBuyer_b1_Buyer with event_lang.dsl.ChannelTypeSnd
  object SndRequest extends SndRequest {
  override protected def __children: List[EPTwoBuyer_b1_Buyer] = List(RcvPrice1)
  override type implT = __SndRequestImp
  override type implNextT = __RcvPrice1Imp
override def toString() : String = {"EPTwoBuyer_b1_Buyer.SndRequest"}
    override def to : RRole = Role("s",RoleSet("Seller")) 
   override def l : String = "Request" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndRequestImp(c,session)}

protected case class __SndRequestImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndRequest
}
  private var notUsed = true
def sndTos_Seller(m : MESSAGES.TwoBuyer.Request) : __RcvPrice1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvPrice1Imp(c,session)}
def !(m : MESSAGES.TwoBuyer.Request) : __RcvPrice1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvPrice1Imp(c,session)}
def snd(m : MESSAGES.TwoBuyer.Request) : __RcvPrice1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("Seller")),m)
__RcvPrice1Imp(c,session)}

}


  trait RcvPrice1 extends EPTwoBuyer_b1_Buyer with event_lang.dsl.ChannelTypeRcv
  object RcvPrice1 extends RcvPrice1 {
  override protected def __children: List[EPTwoBuyer_b1_Buyer] = List(SndIPay)
  override type implT = __RcvPrice1Imp
  override type implNextT = __SndIPayImp
override def toString() : String = {"EPTwoBuyer_b1_Buyer.RcvPrice1"}
  override type msgT = MESSAGES.TwoBuyer.Price1
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "Price1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPrice1Imp(c,session)}

protected case class __RcvPrice1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPrice1
}
  def rcvFrms_Seller : (MESSAGES.TwoBuyer.Price1,__SndIPayImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.Price1],__SndIPayImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoBuyer.Price1,__SndIPayImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.Price1],__SndIPayImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoBuyer.Price1 = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.Price1]}
def ? : MESSAGES.TwoBuyer.Price1 = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.Price1]}
def channelCon : __SndIPayImp = {__SndIPayImp(c,session)}

}


  trait SndIPay extends EPTwoBuyer_b1_Buyer with event_lang.dsl.ChannelTypeSnd
  object SndIPay extends SndIPay {
  override protected def __children: List[EPTwoBuyer_b1_Buyer] = List(End_b1_Buyer_TwoBuyer)
  override type implT = __SndIPayImp
  override type implNextT = __End_b1_Buyer_TwoBuyerImp
override def toString() : String = {"EPTwoBuyer_b1_Buyer.SndIPay"}
    override def to : RRole = Role("b2",RoleSet("Buyer")) 
   override def l : String = "IPay" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndIPayImp(c,session)}

protected case class __SndIPayImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndIPay
}
  private var notUsed = true
def sndTob2_Buyer(m : MESSAGES.TwoBuyer.IPay) : __End_b1_Buyer_TwoBuyerImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b2",RoleSet("Buyer")),m)
__End_b1_Buyer_TwoBuyerImp(c,session)}
def !(m : MESSAGES.TwoBuyer.IPay) : __End_b1_Buyer_TwoBuyerImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b2",RoleSet("Buyer")),m)
__End_b1_Buyer_TwoBuyerImp(c,session)}
def snd(m : MESSAGES.TwoBuyer.IPay) : __End_b1_Buyer_TwoBuyerImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b2",RoleSet("Buyer")),m)
__End_b1_Buyer_TwoBuyerImp(c,session)}

}


protected  trait End_b1_Buyer_TwoBuyer extends EPTwoBuyer_b1_Buyer with event_lang.dsl.ChannelTypeEnd
protected  object End_b1_Buyer_TwoBuyer extends End_b1_Buyer_TwoBuyer {
  override protected def __children: List[EPTwoBuyer_b1_Buyer] = List()
  override type implT = __End_b1_Buyer_TwoBuyerImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoBuyer_b1_Buyer.End_b1_Buyer_TwoBuyer"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b1_Buyer_TwoBuyerImp(c,session)}

protected case class __End_b1_Buyer_TwoBuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b1_Buyer_TwoBuyer
}
  
}



  trait RcvCancelTrans extends EPTwoBuyer_b1_Buyer with event_lang.dsl.ChannelTypeRcv
  object RcvCancelTrans extends RcvCancelTrans {
  override protected def __children: List[EPTwoBuyer_b1_Buyer] = List(End_b1_Buyer_TwoBuyerFHandling)
  override type implT = __RcvCancelTransImp
  override type implNextT = __End_b1_Buyer_TwoBuyerFHandlingImp
override def toString() : String = {"EPTwoBuyer_b1_Buyer.RcvCancelTrans"}
  override type msgT = MESSAGES.TwoBuyer.CancelTrans
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "CancelTrans"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvCancelTransImp(c,session)}

protected case class __RcvCancelTransImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvCancelTrans
}
  def rcvFrms_Seller : (MESSAGES.TwoBuyer.CancelTrans,__End_b1_Buyer_TwoBuyerFHandlingImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.CancelTrans],__End_b1_Buyer_TwoBuyerFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoBuyer.CancelTrans,__End_b1_Buyer_TwoBuyerFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.CancelTrans],__End_b1_Buyer_TwoBuyerFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoBuyer.CancelTrans = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.CancelTrans]}
def ? : MESSAGES.TwoBuyer.CancelTrans = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.TwoBuyer.CancelTrans]}
def channelCon : __End_b1_Buyer_TwoBuyerFHandlingImp = {__End_b1_Buyer_TwoBuyerFHandlingImp(c,session)}

}


protected  trait End_b1_Buyer_TwoBuyerFHandling extends EPTwoBuyer_b1_Buyer with event_lang.dsl.ChannelTypeEnd
protected  object End_b1_Buyer_TwoBuyerFHandling extends End_b1_Buyer_TwoBuyerFHandling {
  override protected def __children: List[EPTwoBuyer_b1_Buyer] = List()
  override type implT = __End_b1_Buyer_TwoBuyerFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoBuyer_b1_Buyer.End_b1_Buyer_TwoBuyerFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b1_Buyer_TwoBuyerFHandlingImp(c,session)}

protected case class __End_b1_Buyer_TwoBuyerFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b1_Buyer_TwoBuyerFHandling
}
  
}


}

object Main_b1_Buyer{
trait EPMain_b1_Buyer extends __EPType_Buyer

object EPMain_b1_Buyer extends EPMain_b1_Buyer with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_b1_Buyer] = List(Hdl)
  override type implT = __EPMain_b1_BuyerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_b1_BuyerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller"))) 
  override def argsP: Role = Role("b1",RoleSet("Buyer")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Buyer")) 
  override def prjTo : RRole = Role("b1",RoleSet("Buyer")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Main"
}

protected case class __EPMain_b1_BuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_b1_Buyer
}
  
}


protected  trait Hdl extends EPMain_b1_Buyer with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_b1_Buyer] = List(SpawnTwoBuyer,End_b1_Buyer_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnTwoBuyerImp
override def toString() : String = {"EPMain_b1_Buyer.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnTwoBuyer extends EPMain_b1_Buyer with event_lang.dsl.ChannelTypeSpawn
  object SpawnTwoBuyer extends SpawnTwoBuyer {
  override protected def __children: List[EPMain_b1_Buyer] = List(End_b1_Buyer_Main)
  override type implT = __SpawnTwoBuyerImp
  override type implNextT = __End_b1_Buyer_MainImp
override def toString() : String = {"EPMain_b1_Buyer.SpawnTwoBuyer"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("b1",RoleSet("Buyer"))) 
  override def pickR: RoleSet = RoleSet("Buyer") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "TwoBuyer" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnTwoBuyerImp(c,session)}

protected case class __SpawnTwoBuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnTwoBuyer
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_b1_Buyer_Main extends EPMain_b1_Buyer with event_lang.dsl.ChannelTypeEnd
protected  object End_b1_Buyer_Main extends End_b1_Buyer_Main {
  override protected def __children: List[EPMain_b1_Buyer] = List()
  override type implT = __End_b1_Buyer_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_b1_Buyer.End_b1_Buyer_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b1_Buyer_MainImp(c,session)}

protected case class __End_b1_Buyer_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b1_Buyer_Main
}
  
}



protected  trait End_b1_Buyer_MainFHandling extends EPMain_b1_Buyer with event_lang.dsl.ChannelTypeEnd
protected  object End_b1_Buyer_MainFHandling extends End_b1_Buyer_MainFHandling {
  override protected def __children: List[EPMain_b1_Buyer] = List()
  override type implT = __End_b1_Buyer_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_b1_Buyer.End_b1_Buyer_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b1_Buyer_MainFHandlingImp(c,session)}

protected case class __End_b1_Buyer_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b1_Buyer_MainFHandling
}
  
}


}

object Main_Buyer{
trait EPMain_Buyer extends __EPType_Buyer

object EPMain_Buyer extends EPMain_Buyer with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_Buyer] = List(Hdl)
  override type implT = __EPMain_BuyerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_BuyerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller"))) 
  override def argsP: Role = Role("b1",RoleSet("Buyer")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Buyer")) 
  override def prjTo : RRole = RoleSet("Buyer") 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "Main"
}

protected case class __EPMain_BuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_Buyer
}
  
}


protected  trait Hdl extends EPMain_Buyer with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_Buyer] = List(SpawnTwoBuyer,RcvCancelMain)
  override type implT = __HdlImp
  override type implNextT = __SpawnTwoBuyerImp
override def toString() : String = {"EPMain_Buyer.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnTwoBuyer extends EPMain_Buyer with event_lang.dsl.ChannelTypeSpawn
  object SpawnTwoBuyer extends SpawnTwoBuyer {
  override protected def __children: List[EPMain_Buyer] = List(End_Buyer_Main)
  override type implT = __SpawnTwoBuyerImp
  override type implNextT = __End_Buyer_MainImp
override def toString() : String = {"EPMain_Buyer.SpawnTwoBuyer"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("b1",RoleSet("Buyer"))) 
  override def pickR: RoleSet = RoleSet("Buyer") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "TwoBuyer" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnTwoBuyerImp(c,session)}

protected case class __SpawnTwoBuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnTwoBuyer
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_Buyer_Main extends EPMain_Buyer with event_lang.dsl.ChannelTypeEnd
protected  object End_Buyer_Main extends End_Buyer_Main {
  override protected def __children: List[EPMain_Buyer] = List()
  override type implT = __End_Buyer_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Buyer.End_Buyer_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Buyer_MainImp(c,session)}

protected case class __End_Buyer_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Buyer_Main
}
  
}



  trait RcvCancelMain extends EPMain_Buyer with event_lang.dsl.ChannelTypeRcv
  object RcvCancelMain extends RcvCancelMain {
  override protected def __children: List[EPMain_Buyer] = List(End_Buyer_MainFHandling)
  override type implT = __RcvCancelMainImp
  override type implNextT = __End_Buyer_MainFHandlingImp
override def toString() : String = {"EPMain_Buyer.RcvCancelMain"}
  override type msgT = MESSAGES.Main.CancelMain
   override def frm : Role = Role("s",RoleSet("Seller")) 
   override def l : String = "CancelMain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvCancelMainImp(c,session)}

protected case class __RcvCancelMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvCancelMain
}
  def rcvFrms_Seller : (MESSAGES.Main.CancelMain,__End_Buyer_MainFHandlingImp) = {(c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.CancelMain],__End_Buyer_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.CancelMain,__End_Buyer_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.CancelMain],__End_Buyer_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.CancelMain = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.CancelMain]}
def ? : MESSAGES.Main.CancelMain = {c.rcv(Role("s",RoleSet("Seller"))).asInstanceOf[MESSAGES.Main.CancelMain]}
def channelCon : __End_Buyer_MainFHandlingImp = {__End_Buyer_MainFHandlingImp(c,session)}

}


protected  trait End_Buyer_MainFHandling extends EPMain_Buyer with event_lang.dsl.ChannelTypeEnd
protected  object End_Buyer_MainFHandling extends End_Buyer_MainFHandling {
  override protected def __children: List[EPMain_Buyer] = List()
  override type implT = __End_Buyer_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Buyer.End_Buyer_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Buyer_MainFHandlingImp(c,session)}

protected case class __End_Buyer_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Buyer_MainFHandling
}
  
}


}

}

object Seller {
val subs : Seq[dsl.ChannelTypeSubS] = List(TwoBuyer_s_Seller.EPTwoBuyer_s_Seller,Main_s_Seller.EPMain_s_Seller)
trait __EPType_Seller extends AbstractChannelType {

}

trait EPType_Seller[T<: TState] extends AbstractEndPoint[__EPType_Seller,T] {
override val roleSet: RoleSet = RoleSet("Seller")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(TwoBuyer_s_Seller.EPTwoBuyer_s_Seller,Main_s_Seller.EPMain_s_Seller)

}

object TwoBuyer_s_Seller{
trait EPTwoBuyer_s_Seller extends __EPType_Seller

object EPTwoBuyer_s_Seller extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(Hdl)
  override type implT = __EPTwoBuyer_s_SellerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPTwoBuyer_s_SellerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller")), Role("b1",RoleSet("Buyer"))) 
  override def argsP: Role = Role("b2",RoleSet("Buyer")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("s",RoleSet("Seller")) 
  override def rootRole: Role = Role("s",RoleSet("Seller")) 
  override def name : String = "TwoBuyer"
}

protected case class __EPTwoBuyer_s_SellerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPTwoBuyer_s_Seller
}
  
}


protected  trait Hdl extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(RcvRequest,Failed_b2_Buyer)
  override type implT = __HdlImp
  override type implNextT = __RcvRequestImp
override def toString() : String = {"EPTwoBuyer_s_Seller.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvRequest extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeRcv
  object RcvRequest extends RcvRequest {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(SndPrice1)
  override type implT = __RcvRequestImp
  override type implNextT = __SndPrice1Imp
override def toString() : String = {"EPTwoBuyer_s_Seller.RcvRequest"}
  override type msgT = MESSAGES.TwoBuyer.Request
   override def frm : Role = Role("b1",RoleSet("Buyer")) 
   override def l : String = "Request"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvRequestImp(c,session)}

protected case class __RcvRequestImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvRequest
}
  def rcvFrmb1_Buyer : (MESSAGES.TwoBuyer.Request,__SndPrice1Imp) = {(c.rcv(Role("b1",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Request],__SndPrice1Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoBuyer.Request,__SndPrice1Imp),T]) : T = {
  f((c.rcv(Role("b1",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Request],__SndPrice1Imp(c,session))) 
}
def rcvMSG : MESSAGES.TwoBuyer.Request = {c.rcv(Role("b1",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Request]}
def ? : MESSAGES.TwoBuyer.Request = {c.rcv(Role("b1",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Request]}
def channelCon : __SndPrice1Imp = {__SndPrice1Imp(c,session)}

}


  trait SndPrice1 extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndPrice1 extends SndPrice1 {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(SndPrice2)
  override type implT = __SndPrice1Imp
  override type implNextT = __SndPrice2Imp
override def toString() : String = {"EPTwoBuyer_s_Seller.SndPrice1"}
    override def to : RRole = Role("b1",RoleSet("Buyer")) 
   override def l : String = "Price1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPrice1Imp(c,session)}

protected case class __SndPrice1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPrice1
}
  private var notUsed = true
def sndTob1_Buyer(m : MESSAGES.TwoBuyer.Price1) : __SndPrice2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b1",RoleSet("Buyer")),m)
__SndPrice2Imp(c,session)}
def !(m : MESSAGES.TwoBuyer.Price1) : __SndPrice2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b1",RoleSet("Buyer")),m)
__SndPrice2Imp(c,session)}
def snd(m : MESSAGES.TwoBuyer.Price1) : __SndPrice2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b1",RoleSet("Buyer")),m)
__SndPrice2Imp(c,session)}

}


  trait SndPrice2 extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndPrice2 extends SndPrice2 {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(SelOkQuit)
  override type implT = __SndPrice2Imp
  override type implNextT = __SelOkQuitImp
override def toString() : String = {"EPTwoBuyer_s_Seller.SndPrice2"}
    override def to : RRole = Role("b2",RoleSet("Buyer")) 
   override def l : String = "Price2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPrice2Imp(c,session)}

protected case class __SndPrice2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPrice2
}
  private var notUsed = true
def sndTob2_Buyer(m : MESSAGES.TwoBuyer.Price2) : __SelOkQuitImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b2",RoleSet("Buyer")),m)
__SelOkQuitImp(c,session)}
def !(m : MESSAGES.TwoBuyer.Price2) : __SelOkQuitImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b2",RoleSet("Buyer")),m)
__SelOkQuitImp(c,session)}
def snd(m : MESSAGES.TwoBuyer.Price2) : __SelOkQuitImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b2",RoleSet("Buyer")),m)
__SelOkQuitImp(c,session)}

}


protected  trait SelOkQuit extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeBrn
protected  object SelOkQuit extends SelOkQuit {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(RcvOk,RcvQuit)
  override type implT = __SelOkQuitImp
  override type implNextT = __RcvOkImp
override def toString() : String = {"EPTwoBuyer_s_Seller.SelOkQuit"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOkQuitImp(c,session)}

protected case class __SelOkQuitImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOkQuit
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvOk extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeRcv
  object RcvOk extends RcvOk {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(RcvAddr)
  override type implT = __RcvOkImp
  override type implNextT = __RcvAddrImp
override def toString() : String = {"EPTwoBuyer_s_Seller.RcvOk"}
  override type msgT = MESSAGES.TwoBuyer.Ok
   override def frm : Role = Role("b2",RoleSet("Buyer")) 
   override def l : String = "Ok"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvOkImp(c,session)}

protected case class __RcvOkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvOk
}
  def rcvFrmb2_Buyer : (MESSAGES.TwoBuyer.Ok,__RcvAddrImp) = {(c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Ok],__RcvAddrImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoBuyer.Ok,__RcvAddrImp),T]) : T = {
  f((c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Ok],__RcvAddrImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoBuyer.Ok = {c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Ok]}
def ? : MESSAGES.TwoBuyer.Ok = {c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Ok]}
def channelCon : __RcvAddrImp = {__RcvAddrImp(c,session)}

}


  trait RcvAddr extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeRcv
  object RcvAddr extends RcvAddr {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(SndArrivalDate)
  override type implT = __RcvAddrImp
  override type implNextT = __SndArrivalDateImp
override def toString() : String = {"EPTwoBuyer_s_Seller.RcvAddr"}
  override type msgT = MESSAGES.TwoBuyer.Addr
   override def frm : Role = Role("b2",RoleSet("Buyer")) 
   override def l : String = "Addr"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAddrImp(c,session)}

protected case class __RcvAddrImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAddr
}
  def rcvFrmb2_Buyer : (MESSAGES.TwoBuyer.Addr,__SndArrivalDateImp) = {(c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Addr],__SndArrivalDateImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoBuyer.Addr,__SndArrivalDateImp),T]) : T = {
  f((c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Addr],__SndArrivalDateImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoBuyer.Addr = {c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Addr]}
def ? : MESSAGES.TwoBuyer.Addr = {c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Addr]}
def channelCon : __SndArrivalDateImp = {__SndArrivalDateImp(c,session)}

}


  trait SndArrivalDate extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndArrivalDate extends SndArrivalDate {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(End_s_Seller_TwoBuyerOk)
  override type implT = __SndArrivalDateImp
  override type implNextT = __End_s_Seller_TwoBuyerOkImp
override def toString() : String = {"EPTwoBuyer_s_Seller.SndArrivalDate"}
    override def to : RRole = Role("b2",RoleSet("Buyer")) 
   override def l : String = "ArrivalDate" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndArrivalDateImp(c,session)}

protected case class __SndArrivalDateImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndArrivalDate
}
  private var notUsed = true
def sndTob2_Buyer(m : MESSAGES.TwoBuyer.ArrivalDate) : __End_s_Seller_TwoBuyerOkImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b2",RoleSet("Buyer")),m)
__End_s_Seller_TwoBuyerOkImp(c,session)}
def !(m : MESSAGES.TwoBuyer.ArrivalDate) : __End_s_Seller_TwoBuyerOkImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b2",RoleSet("Buyer")),m)
__End_s_Seller_TwoBuyerOkImp(c,session)}
def snd(m : MESSAGES.TwoBuyer.ArrivalDate) : __End_s_Seller_TwoBuyerOkImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b2",RoleSet("Buyer")),m)
__End_s_Seller_TwoBuyerOkImp(c,session)}

}


protected  trait End_s_Seller_TwoBuyerOk extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_TwoBuyerOk extends End_s_Seller_TwoBuyerOk {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List()
  override type implT = __End_s_Seller_TwoBuyerOkImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoBuyer_s_Seller.End_s_Seller_TwoBuyerOk"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_TwoBuyerOkImp(c,session)}

protected case class __End_s_Seller_TwoBuyerOkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_TwoBuyerOk
}
  
}



  trait RcvQuit extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeRcv
  object RcvQuit extends RcvQuit {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(End_s_Seller_TwoBuyerQuit)
  override type implT = __RcvQuitImp
  override type implNextT = __End_s_Seller_TwoBuyerQuitImp
override def toString() : String = {"EPTwoBuyer_s_Seller.RcvQuit"}
  override type msgT = MESSAGES.TwoBuyer.Quit
   override def frm : Role = Role("b2",RoleSet("Buyer")) 
   override def l : String = "Quit"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvQuitImp(c,session)}

protected case class __RcvQuitImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvQuit
}
  def rcvFrmb2_Buyer : (MESSAGES.TwoBuyer.Quit,__End_s_Seller_TwoBuyerQuitImp) = {(c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Quit],__End_s_Seller_TwoBuyerQuitImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoBuyer.Quit,__End_s_Seller_TwoBuyerQuitImp),T]) : T = {
  f((c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Quit],__End_s_Seller_TwoBuyerQuitImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoBuyer.Quit = {c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Quit]}
def ? : MESSAGES.TwoBuyer.Quit = {c.rcv(Role("b2",RoleSet("Buyer"))).asInstanceOf[MESSAGES.TwoBuyer.Quit]}
def channelCon : __End_s_Seller_TwoBuyerQuitImp = {__End_s_Seller_TwoBuyerQuitImp(c,session)}

}


protected  trait End_s_Seller_TwoBuyerQuit extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_TwoBuyerQuit extends End_s_Seller_TwoBuyerQuit {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List()
  override type implT = __End_s_Seller_TwoBuyerQuitImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoBuyer_s_Seller.End_s_Seller_TwoBuyerQuit"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_TwoBuyerQuitImp(c,session)}

protected case class __End_s_Seller_TwoBuyerQuitImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_TwoBuyerQuit
}
  
}



  trait Failed_b2_Buyer extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeFDtct
  object Failed_b2_Buyer extends Failed_b2_Buyer {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(SndCancelTrans)
  override type implT = __Failed_b2_BuyerImp
  override type implNextT = __SndCancelTransImp
override def toString() : String = {"EPTwoBuyer_s_Seller.Failed_b2_Buyer"}
  override def suspect : Role = Role("b2",RoleSet("Buyer")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b2_BuyerImp(c,session)}

protected case class __Failed_b2_BuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b2_Buyer
}
  def failed_b2_Buyer(): __SndCancelTransImp = {
__SndCancelTransImp(c,session)}

}


  trait SndCancelTrans extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndCancelTrans extends SndCancelTrans {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List(End_s_Seller_TwoBuyerFHandling)
  override type implT = __SndCancelTransImp
  override type implNextT = __End_s_Seller_TwoBuyerFHandlingImp
override def toString() : String = {"EPTwoBuyer_s_Seller.SndCancelTrans"}
    override def to : RRole = Role("b1",RoleSet("Buyer")) 
   override def l : String = "CancelTrans" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndCancelTransImp(c,session)}

protected case class __SndCancelTransImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndCancelTrans
}
  private var notUsed = true
def sndTob1_Buyer(m : MESSAGES.TwoBuyer.CancelTrans) : __End_s_Seller_TwoBuyerFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b1",RoleSet("Buyer")),m)
__End_s_Seller_TwoBuyerFHandlingImp(c,session)}
def !(m : MESSAGES.TwoBuyer.CancelTrans) : __End_s_Seller_TwoBuyerFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b1",RoleSet("Buyer")),m)
__End_s_Seller_TwoBuyerFHandlingImp(c,session)}
def snd(m : MESSAGES.TwoBuyer.CancelTrans) : __End_s_Seller_TwoBuyerFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b1",RoleSet("Buyer")),m)
__End_s_Seller_TwoBuyerFHandlingImp(c,session)}

}


protected  trait End_s_Seller_TwoBuyerFHandling extends EPTwoBuyer_s_Seller with event_lang.dsl.ChannelTypeEnd
protected  object End_s_Seller_TwoBuyerFHandling extends End_s_Seller_TwoBuyerFHandling {
  override protected def __children: List[EPTwoBuyer_s_Seller] = List()
  override type implT = __End_s_Seller_TwoBuyerFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoBuyer_s_Seller.End_s_Seller_TwoBuyerFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_Seller_TwoBuyerFHandlingImp(c,session)}

protected case class __End_s_Seller_TwoBuyerFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_Seller_TwoBuyerFHandling
}
  
}


}

object Main_s_Seller{
trait EPMain_s_Seller extends __EPType_Seller

object EPMain_s_Seller extends EPMain_s_Seller with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_s_Seller] = List(Hdl)
  override type implT = __EPMain_s_SellerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_s_SellerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("Seller"))) 
  override def argsP: Role = Role("b1",RoleSet("Buyer")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Buyer")) 
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
  override protected def __children: List[EPMain_s_Seller] = List(SpawnTwoBuyer,Failed_b1_Buyer)
  override type implT = __HdlImp
  override type implNextT = __SpawnTwoBuyerImp
override def toString() : String = {"EPMain_s_Seller.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnTwoBuyer extends EPMain_s_Seller with event_lang.dsl.ChannelTypeSpawn
  object SpawnTwoBuyer extends SpawnTwoBuyer {
  override protected def __children: List[EPMain_s_Seller] = List(End_s_Seller_Main)
  override type implT = __SpawnTwoBuyerImp
  override type implNextT = __End_s_Seller_MainImp
override def toString() : String = {"EPMain_s_Seller.SpawnTwoBuyer"}
    override def y: List[Role] = List(Role("s",RoleSet("Seller")), Role("b1",RoleSet("Buyer"))) 
  override def pickR: RoleSet = RoleSet("Buyer") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "TwoBuyer" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnTwoBuyerImp(c,session)}

protected case class __SpawnTwoBuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnTwoBuyer
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



  trait Failed_b1_Buyer extends EPMain_s_Seller with event_lang.dsl.ChannelTypeFDtct
  object Failed_b1_Buyer extends Failed_b1_Buyer {
  override protected def __children: List[EPMain_s_Seller] = List(SndCancelMain)
  override type implT = __Failed_b1_BuyerImp
  override type implNextT = __SndCancelMainImp
override def toString() : String = {"EPMain_s_Seller.Failed_b1_Buyer"}
  override def suspect : Role = Role("b1",RoleSet("Buyer")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b1_BuyerImp(c,session)}

protected case class __Failed_b1_BuyerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b1_Buyer
}
  def failed_b1_Buyer(): __SndCancelMainImp = {
__SndCancelMainImp(c,session)}

}


  trait SndCancelMain extends EPMain_s_Seller with event_lang.dsl.ChannelTypeSnd
  object SndCancelMain extends SndCancelMain {
  override protected def __children: List[EPMain_s_Seller] = List(End_s_Seller_MainFHandling)
  override type implT = __SndCancelMainImp
  override type implNextT = __End_s_Seller_MainFHandlingImp
override def toString() : String = {"EPMain_s_Seller.SndCancelMain"}
    override def to : RRole = RoleSet("Buyer") 
   override def l : String = "CancelMain" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndCancelMainImp(c,session)}

protected case class __SndCancelMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndCancelMain
}
  private var notUsed = true
def sndToBuyer(m : MESSAGES.Main.CancelMain) : __End_s_Seller_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Buyer"),m)
__End_s_Seller_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.CancelMain) : __End_s_Seller_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Buyer"),m)
__End_s_Seller_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.CancelMain) : __End_s_Seller_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Buyer"),m)
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

}

}
