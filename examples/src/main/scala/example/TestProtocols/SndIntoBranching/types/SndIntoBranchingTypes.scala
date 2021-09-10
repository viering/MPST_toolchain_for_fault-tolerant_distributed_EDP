package examples.TestProtocols.SndIntoBranching.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object SndIntoBranching {
object RS {
val B : RoleSet = RoleSet("B")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object Main {
case class B2() extends MSG {
   override def l : String = "B2"
}

case class M() extends MSG {
   override def l : String = "M"
}

case class B1() extends MSG {
   override def l : String = "B1"
}

case class B21() extends MSG {
   override def l : String = "B21"
}

case class B11() extends MSG {
   override def l : String = "B11"
}

case class B12() extends MSG {
   override def l : String = "B12"
}

}

}

object PROTOCOLS {
object Main {
val b_B = Role("b",RoleSet("B"))
val a_A = Role("a",RoleSet("A"))
}

}

object B {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_b_B.EPMain_b_B)
trait __EPType_B extends AbstractChannelType {

}

trait EPType_B[T<: TState] extends AbstractEndPoint[__EPType_B,T] {
override val roleSet: RoleSet = RoleSet("B")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_b_B.EPMain_b_B)

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
  override def argsRs: List[RoleSet] = List() 
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
  override protected def __children: List[EPMain_b_B] = List(RcvM,End_b_B_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvMImp
override def toString() : String = {"EPMain_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvM extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvM extends RcvM {
  override protected def __children: List[EPMain_b_B] = List(SelB1B2)
  override type implT = __RcvMImp
  override type implNextT = __SelB1B2Imp
override def toString() : String = {"EPMain_b_B.RcvM"}
  override type msgT = MESSAGES.Main.M
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "M"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMImp(c,session)}

protected case class __RcvMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvM
}
  def rcvFrma_A : (MESSAGES.Main.M,__SelB1B2Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.M],__SelB1B2Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.M,__SelB1B2Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.M],__SelB1B2Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.M = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.M]}
def ? : MESSAGES.Main.M = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.M]}
def channelCon : __SelB1B2Imp = {__SelB1B2Imp(c,session)}

}


protected  trait SelB1B2 extends EPMain_b_B with event_lang.dsl.ChannelTypeBrn
protected  object SelB1B2 extends SelB1B2 {
  override protected def __children: List[EPMain_b_B] = List(RcvB1,RcvB2)
  override type implT = __SelB1B2Imp
  override type implNextT = __RcvB1Imp
override def toString() : String = {"EPMain_b_B.SelB1B2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelB1B2Imp(c,session)}

protected case class __SelB1B2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelB1B2
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvB1 extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvB1 extends RcvB1 {
  override protected def __children: List[EPMain_b_B] = List(SelB11B12)
  override type implT = __RcvB1Imp
  override type implNextT = __SelB11B12Imp
override def toString() : String = {"EPMain_b_B.RcvB1"}
  override type msgT = MESSAGES.Main.B1
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "B1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvB1Imp(c,session)}

protected case class __RcvB1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvB1
}
  def rcvFrma_A : (MESSAGES.Main.B1,__SelB11B12Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B1],__SelB11B12Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.B1,__SelB11B12Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B1],__SelB11B12Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.B1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B1]}
def ? : MESSAGES.Main.B1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B1]}
def channelCon : __SelB11B12Imp = {__SelB11B12Imp(c,session)}

}


protected  trait SelB11B12 extends EPMain_b_B with event_lang.dsl.ChannelTypeBrn
protected  object SelB11B12 extends SelB11B12 {
  override protected def __children: List[EPMain_b_B] = List(RcvB11,RcvB12)
  override type implT = __SelB11B12Imp
  override type implNextT = __RcvB11Imp
override def toString() : String = {"EPMain_b_B.SelB11B12"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelB11B12Imp(c,session)}

protected case class __SelB11B12Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelB11B12
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvB11 extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvB11 extends RcvB11 {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_MainB11_B1)
  override type implT = __RcvB11Imp
  override type implNextT = __End_b_B_MainB11_B1Imp
override def toString() : String = {"EPMain_b_B.RcvB11"}
  override type msgT = MESSAGES.Main.B11
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "B11"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvB11Imp(c,session)}

protected case class __RcvB11Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvB11
}
  def rcvFrma_A : (MESSAGES.Main.B11,__End_b_B_MainB11_B1Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B11],__End_b_B_MainB11_B1Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.B11,__End_b_B_MainB11_B1Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B11],__End_b_B_MainB11_B1Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.B11 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B11]}
def ? : MESSAGES.Main.B11 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B11]}
def channelCon : __End_b_B_MainB11_B1Imp = {__End_b_B_MainB11_B1Imp(c,session)}

}


protected  trait End_b_B_MainB11_B1 extends EPMain_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_MainB11_B1 extends End_b_B_MainB11_B1 {
  override protected def __children: List[EPMain_b_B] = List()
  override type implT = __End_b_B_MainB11_B1Imp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_b_B.End_b_B_MainB11_B1"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_MainB11_B1Imp(c,session)}

protected case class __End_b_B_MainB11_B1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_MainB11_B1
}
  
}



  trait RcvB12 extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvB12 extends RcvB12 {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_MainB12_B1)
  override type implT = __RcvB12Imp
  override type implNextT = __End_b_B_MainB12_B1Imp
override def toString() : String = {"EPMain_b_B.RcvB12"}
  override type msgT = MESSAGES.Main.B12
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "B12"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvB12Imp(c,session)}

protected case class __RcvB12Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvB12
}
  def rcvFrma_A : (MESSAGES.Main.B12,__End_b_B_MainB12_B1Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B12],__End_b_B_MainB12_B1Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.B12,__End_b_B_MainB12_B1Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B12],__End_b_B_MainB12_B1Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.B12 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B12]}
def ? : MESSAGES.Main.B12 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B12]}
def channelCon : __End_b_B_MainB12_B1Imp = {__End_b_B_MainB12_B1Imp(c,session)}

}


protected  trait End_b_B_MainB12_B1 extends EPMain_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_MainB12_B1 extends End_b_B_MainB12_B1 {
  override protected def __children: List[EPMain_b_B] = List()
  override type implT = __End_b_B_MainB12_B1Imp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_b_B.End_b_B_MainB12_B1"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_MainB12_B1Imp(c,session)}

protected case class __End_b_B_MainB12_B1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_MainB12_B1
}
  
}



  trait RcvB2 extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvB2 extends RcvB2 {
  override protected def __children: List[EPMain_b_B] = List(RcvB21)
  override type implT = __RcvB2Imp
  override type implNextT = __RcvB21Imp
override def toString() : String = {"EPMain_b_B.RcvB2"}
  override type msgT = MESSAGES.Main.B2
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "B2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvB2Imp(c,session)}

protected case class __RcvB2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvB2
}
  def rcvFrma_A : (MESSAGES.Main.B2,__RcvB21Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B2],__RcvB21Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.B2,__RcvB21Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B2],__RcvB21Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.B2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B2]}
def ? : MESSAGES.Main.B2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B2]}
def channelCon : __RcvB21Imp = {__RcvB21Imp(c,session)}

}


  trait RcvB21 extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvB21 extends RcvB21 {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_MainB2)
  override type implT = __RcvB21Imp
  override type implNextT = __End_b_B_MainB2Imp
override def toString() : String = {"EPMain_b_B.RcvB21"}
  override type msgT = MESSAGES.Main.B21
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "B21"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvB21Imp(c,session)}

protected case class __RcvB21Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvB21
}
  def rcvFrma_A : (MESSAGES.Main.B21,__End_b_B_MainB2Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B21],__End_b_B_MainB2Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.B21,__End_b_B_MainB2Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B21],__End_b_B_MainB2Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.B21 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B21]}
def ? : MESSAGES.Main.B21 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.B21]}
def channelCon : __End_b_B_MainB2Imp = {__End_b_B_MainB2Imp(c,session)}

}


protected  trait End_b_B_MainB2 extends EPMain_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_MainB2 extends End_b_B_MainB2 {
  override protected def __children: List[EPMain_b_B] = List()
  override type implT = __End_b_B_MainB2Imp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_b_B.End_b_B_MainB2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_MainB2Imp(c,session)}

protected case class __End_b_B_MainB2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_MainB2
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
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_a_A.EPMain_a_A)
trait __EPType_A extends AbstractChannelType {

}

trait EPType_A[T<: TState] extends AbstractEndPoint[__EPType_A,T] {
override val roleSet: RoleSet = RoleSet("A")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_a_A.EPMain_a_A)

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
  override def argsRs: List[RoleSet] = List() 
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
  override protected def __children: List[EPMain_a_A] = List(SndM,Failed_b_B)
  override type implT = __HdlImp
  override type implNextT = __SndMImp
override def toString() : String = {"EPMain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndM extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndM extends SndM {
  override protected def __children: List[EPMain_a_A] = List(SelB1B2)
  override type implT = __SndMImp
  override type implNextT = __SelB1B2Imp
override def toString() : String = {"EPMain_a_A.SndM"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "M" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMImp(c,session)}

protected case class __SndMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndM
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.M) : __SelB1B2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SelB1B2Imp(c,session)}
def !(m : MESSAGES.Main.M) : __SelB1B2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SelB1B2Imp(c,session)}
def snd(m : MESSAGES.Main.M) : __SelB1B2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SelB1B2Imp(c,session)}

}


protected  trait SelB1B2 extends EPMain_a_A with event_lang.dsl.ChannelTypeSel
protected  object SelB1B2 extends SelB1B2 {
  override protected def __children: List[EPMain_a_A] = List(SndB1,SndB2)
  override type implT = __SelB1B2Imp
  override type implNextT = __SndB1Imp
override def toString() : String = {"EPMain_a_A.SelB1B2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelB1B2Imp(c,session)}

protected case class __SelB1B2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelB1B2
}
  private var notUsed = true
def !(m : MESSAGES.Main.B1) : __SelB11B12Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __SelB11B12Imp(c,session)}
def sndTob_B(m : MESSAGES.Main.B1) : __SelB11B12Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __SelB11B12Imp(c,session)}

def !(m : MESSAGES.Main.B2) : __SndB21Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __SndB21Imp(c,session)}
def sndTob_B(m : MESSAGES.Main.B2) : __SndB21Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __SndB21Imp(c,session)}

}


  trait SndB1 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndB1 extends SndB1 {
  override protected def __children: List[EPMain_a_A] = List(SelB11B12)
  override type implT = __SndB1Imp
  override type implNextT = __SelB11B12Imp
override def toString() : String = {"EPMain_a_A.SndB1"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "B1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndB1Imp(c,session)}

protected case class __SndB1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndB1
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.B1) : __SelB11B12Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SelB11B12Imp(c,session)}
def !(m : MESSAGES.Main.B1) : __SelB11B12Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SelB11B12Imp(c,session)}
def snd(m : MESSAGES.Main.B1) : __SelB11B12Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SelB11B12Imp(c,session)}

}


protected  trait SelB11B12 extends EPMain_a_A with event_lang.dsl.ChannelTypeSel
protected  object SelB11B12 extends SelB11B12 {
  override protected def __children: List[EPMain_a_A] = List(SndB11,SndB12)
  override type implT = __SelB11B12Imp
  override type implNextT = __SndB11Imp
override def toString() : String = {"EPMain_a_A.SelB11B12"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelB11B12Imp(c,session)}

protected case class __SelB11B12Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelB11B12
}
  private var notUsed = true
def !(m : MESSAGES.Main.B11) : __End_a_A_MainB11_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __End_a_A_MainB11_B1Imp(c,session)}
def sndTob_B(m : MESSAGES.Main.B11) : __End_a_A_MainB11_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __End_a_A_MainB11_B1Imp(c,session)}

def !(m : MESSAGES.Main.B12) : __End_a_A_MainB12_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __End_a_A_MainB12_B1Imp(c,session)}
def sndTob_B(m : MESSAGES.Main.B12) : __End_a_A_MainB12_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __End_a_A_MainB12_B1Imp(c,session)}

}


  trait SndB11 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndB11 extends SndB11 {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainB11_B1)
  override type implT = __SndB11Imp
  override type implNextT = __End_a_A_MainB11_B1Imp
override def toString() : String = {"EPMain_a_A.SndB11"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "B11" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndB11Imp(c,session)}

protected case class __SndB11Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndB11
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.B11) : __End_a_A_MainB11_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_MainB11_B1Imp(c,session)}
def !(m : MESSAGES.Main.B11) : __End_a_A_MainB11_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_MainB11_B1Imp(c,session)}
def snd(m : MESSAGES.Main.B11) : __End_a_A_MainB11_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_MainB11_B1Imp(c,session)}

}


protected  trait End_a_A_MainB11_B1 extends EPMain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_MainB11_B1 extends End_a_A_MainB11_B1 {
  override protected def __children: List[EPMain_a_A] = List()
  override type implT = __End_a_A_MainB11_B1Imp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_a_A.End_a_A_MainB11_B1"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_MainB11_B1Imp(c,session)}

protected case class __End_a_A_MainB11_B1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_MainB11_B1
}
  
}



  trait SndB12 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndB12 extends SndB12 {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainB12_B1)
  override type implT = __SndB12Imp
  override type implNextT = __End_a_A_MainB12_B1Imp
override def toString() : String = {"EPMain_a_A.SndB12"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "B12" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndB12Imp(c,session)}

protected case class __SndB12Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndB12
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.B12) : __End_a_A_MainB12_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_MainB12_B1Imp(c,session)}
def !(m : MESSAGES.Main.B12) : __End_a_A_MainB12_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_MainB12_B1Imp(c,session)}
def snd(m : MESSAGES.Main.B12) : __End_a_A_MainB12_B1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_MainB12_B1Imp(c,session)}

}


protected  trait End_a_A_MainB12_B1 extends EPMain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_MainB12_B1 extends End_a_A_MainB12_B1 {
  override protected def __children: List[EPMain_a_A] = List()
  override type implT = __End_a_A_MainB12_B1Imp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_a_A.End_a_A_MainB12_B1"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_MainB12_B1Imp(c,session)}

protected case class __End_a_A_MainB12_B1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_MainB12_B1
}
  
}



  trait SndB2 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndB2 extends SndB2 {
  override protected def __children: List[EPMain_a_A] = List(SndB21)
  override type implT = __SndB2Imp
  override type implNextT = __SndB21Imp
override def toString() : String = {"EPMain_a_A.SndB2"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "B2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndB2Imp(c,session)}

protected case class __SndB2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndB2
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.B2) : __SndB21Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndB21Imp(c,session)}
def !(m : MESSAGES.Main.B2) : __SndB21Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndB21Imp(c,session)}
def snd(m : MESSAGES.Main.B2) : __SndB21Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndB21Imp(c,session)}

}


  trait SndB21 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndB21 extends SndB21 {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainB2)
  override type implT = __SndB21Imp
  override type implNextT = __End_a_A_MainB2Imp
override def toString() : String = {"EPMain_a_A.SndB21"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "B21" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndB21Imp(c,session)}

protected case class __SndB21Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndB21
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.B21) : __End_a_A_MainB2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_MainB2Imp(c,session)}
def !(m : MESSAGES.Main.B21) : __End_a_A_MainB2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_MainB2Imp(c,session)}
def snd(m : MESSAGES.Main.B21) : __End_a_A_MainB2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_MainB2Imp(c,session)}

}


protected  trait End_a_A_MainB2 extends EPMain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_MainB2 extends End_a_A_MainB2 {
  override protected def __children: List[EPMain_a_A] = List()
  override type implT = __End_a_A_MainB2Imp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_a_A.End_a_A_MainB2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_MainB2Imp(c,session)}

protected case class __End_a_A_MainB2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_MainB2
}
  
}



  trait Failed_b_B extends EPMain_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_b_B extends Failed_b_B {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainFHandling)
  override type implT = __Failed_b_BImp
  override type implNextT = __End_a_A_MainFHandlingImp
override def toString() : String = {"EPMain_a_A.Failed_b_B"}
  override def suspect : Role = Role("b",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b_BImp(c,session)}

protected case class __Failed_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b_B
}
  def failed_b_B(): __End_a_A_MainFHandlingImp = {__End_a_A_MainFHandlingImp(c,session)}

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
