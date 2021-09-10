package example.TestProtocols.branchFActivation.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object BranchInFActivation {
object RS {
val B : RoleSet = RoleSet("B")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object Main {
case class M() extends MSG {
   override def l : String = "M"
}

case class M1() extends MSG {
   override def l : String = "M1"
}

case class F1() extends MSG {
   override def l : String = "F1"
}

case class F2() extends MSG {
   override def l : String = "F2"
}

}

}

object PROTOCOLS {
object Main {
val b_B = Role("b",RoleSet("B"))
val B = RoleSet("B")
val a_A = Role("a",RoleSet("A"))
}

}

object B {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_b_B.EPMain_b_B,Main_B.EPMain_B)
trait __EPType_B extends AbstractChannelType {

}

trait EPType_B[T<: TState] extends AbstractEndPoint[__EPType_B,T] {
override val roleSet: RoleSet = RoleSet("B")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_b_B.EPMain_b_B,Main_B.EPMain_B)

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
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
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
  override protected def __children: List[EPMain_b_B] = List(SndM1)
  override type implT = __RcvMImp
  override type implNextT = __SndM1Imp
override def toString() : String = {"EPMain_b_B.RcvM"}
  override type msgT = MESSAGES.Main.M
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "M"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMImp(c,session)}

protected case class __RcvMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvM
}
  def rcvFrma_A : (MESSAGES.Main.M,__SndM1Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.M],__SndM1Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.M,__SndM1Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.M],__SndM1Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.M = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.M]}
def ? : MESSAGES.Main.M = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.M]}
def channelCon : __SndM1Imp = {__SndM1Imp(c,session)}

}


  trait SndM1 extends EPMain_b_B with event_lang.dsl.ChannelTypeSnd
  object SndM1 extends SndM1 {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_Main)
  override type implT = __SndM1Imp
  override type implNextT = __End_b_B_MainImp
override def toString() : String = {"EPMain_b_B.SndM1"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "M1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndM1Imp(c,session)}

protected case class __SndM1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndM1
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.Main.M1) : __End_b_B_MainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_MainImp(c,session)}
def !(m : MESSAGES.Main.M1) : __End_b_B_MainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_MainImp(c,session)}
def snd(m : MESSAGES.Main.M1) : __End_b_B_MainImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_MainImp(c,session)}

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

object Main_B{
trait EPMain_B extends __EPType_B

object EPMain_B extends EPMain_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_B] = List(Hdl)
  override type implT = __EPMain_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("b",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
  override def prjTo : RRole = RoleSet("B") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Main"
}

protected case class __EPMain_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_B
}
  
}


protected  trait Hdl extends EPMain_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_B] = List(End_B_Main,SelF1F2)
  override type implT = __HdlImp
  override type implNextT = __End_B_MainImp
override def toString() : String = {"EPMain_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_B_Main extends EPMain_B with event_lang.dsl.ChannelTypeEnd
protected  object End_B_Main extends End_B_Main {
  override protected def __children: List[EPMain_B] = List()
  override type implT = __End_B_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_B.End_B_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_B_MainImp(c,session)}

protected case class __End_B_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_B_Main
}
  
}



protected  trait SelF1F2 extends EPMain_B with event_lang.dsl.ChannelTypeBrn
protected  object SelF1F2 extends SelF1F2 {
  override protected def __children: List[EPMain_B] = List(RcvF1,RcvF2)
  override type implT = __SelF1F2Imp
  override type implNextT = __RcvF1Imp
override def toString() : String = {"EPMain_B.SelF1F2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelF1F2Imp(c,session)}

protected case class __SelF1F2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelF1F2
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvF1 extends EPMain_B with event_lang.dsl.ChannelTypeRcv
  object RcvF1 extends RcvF1 {
  override protected def __children: List[EPMain_B] = List(End_B_MainF1_FHandling)
  override type implT = __RcvF1Imp
  override type implNextT = __End_B_MainF1_FHandlingImp
override def toString() : String = {"EPMain_B.RcvF1"}
  override type msgT = MESSAGES.Main.F1
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "F1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF1Imp(c,session)}

protected case class __RcvF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF1
}
  def rcvFrma_A : (MESSAGES.Main.F1,__End_B_MainF1_FHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.F1],__End_B_MainF1_FHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.F1,__End_B_MainF1_FHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.F1],__End_B_MainF1_FHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.F1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.F1]}
def ? : MESSAGES.Main.F1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.F1]}
def channelCon : __End_B_MainF1_FHandlingImp = {__End_B_MainF1_FHandlingImp(c,session)}

}


protected  trait End_B_MainF1_FHandling extends EPMain_B with event_lang.dsl.ChannelTypeEnd
protected  object End_B_MainF1_FHandling extends End_B_MainF1_FHandling {
  override protected def __children: List[EPMain_B] = List()
  override type implT = __End_B_MainF1_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_B.End_B_MainF1_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_B_MainF1_FHandlingImp(c,session)}

protected case class __End_B_MainF1_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_B_MainF1_FHandling
}
  
}



  trait RcvF2 extends EPMain_B with event_lang.dsl.ChannelTypeRcv
  object RcvF2 extends RcvF2 {
  override protected def __children: List[EPMain_B] = List(End_B_MainF2_FHandling)
  override type implT = __RcvF2Imp
  override type implNextT = __End_B_MainF2_FHandlingImp
override def toString() : String = {"EPMain_B.RcvF2"}
  override type msgT = MESSAGES.Main.F2
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "F2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF2Imp(c,session)}

protected case class __RcvF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF2
}
  def rcvFrma_A : (MESSAGES.Main.F2,__End_B_MainF2_FHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.F2],__End_B_MainF2_FHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.F2,__End_B_MainF2_FHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.F2],__End_B_MainF2_FHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.F2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.F2]}
def ? : MESSAGES.Main.F2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.F2]}
def channelCon : __End_B_MainF2_FHandlingImp = {__End_B_MainF2_FHandlingImp(c,session)}

}


protected  trait End_B_MainF2_FHandling extends EPMain_B with event_lang.dsl.ChannelTypeEnd
protected  object End_B_MainF2_FHandling extends End_B_MainF2_FHandling {
  override protected def __children: List[EPMain_B] = List()
  override type implT = __End_B_MainF2_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_B.End_B_MainF2_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_B_MainF2_FHandlingImp(c,session)}

protected case class __End_B_MainF2_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_B_MainF2_FHandling
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
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
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
  override protected def __children: List[EPMain_a_A] = List(RcvM1)
  override type implT = __SndMImp
  override type implNextT = __RcvM1Imp
override def toString() : String = {"EPMain_a_A.SndM"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "M" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMImp(c,session)}

protected case class __SndMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndM
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.M) : __RcvM1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvM1Imp(c,session)}
def !(m : MESSAGES.Main.M) : __RcvM1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvM1Imp(c,session)}
def snd(m : MESSAGES.Main.M) : __RcvM1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvM1Imp(c,session)}

}


  trait RcvM1 extends EPMain_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvM1 extends RcvM1 {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_Main)
  override type implT = __RcvM1Imp
  override type implNextT = __End_a_A_MainImp
override def toString() : String = {"EPMain_a_A.RcvM1"}
  override type msgT = MESSAGES.Main.M1
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "M1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvM1Imp(c,session)}

protected case class __RcvM1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvM1
}
  def rcvFrmb_B : (MESSAGES.Main.M1,__End_a_A_MainImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.M1],__End_a_A_MainImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.M1,__End_a_A_MainImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.M1],__End_a_A_MainImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.M1 = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.M1]}
def ? : MESSAGES.Main.M1 = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.M1]}
def channelCon : __End_a_A_MainImp = {__End_a_A_MainImp(c,session)}

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
  override protected def __children: List[EPMain_a_A] = List(SelF1F2)
  override type implT = __Failed_b_BImp
  override type implNextT = __SelF1F2Imp
override def toString() : String = {"EPMain_a_A.Failed_b_B"}
  override def suspect : Role = Role("b",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b_BImp(c,session)}

protected case class __Failed_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b_B
}
  def failed_b_B(): __SelF1F2Imp = {__SelF1F2Imp(c,session)}

}


protected  trait SelF1F2 extends EPMain_a_A with event_lang.dsl.ChannelTypeSel
protected  object SelF1F2 extends SelF1F2 {
  override protected def __children: List[EPMain_a_A] = List(SndF1,SndF2)
  override type implT = __SelF1F2Imp
  override type implNextT = __SndF1Imp
override def toString() : String = {"EPMain_a_A.SelF1F2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelF1F2Imp(c,session)}

protected case class __SelF1F2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelF1F2
}
  private var notUsed = true
def !(m : MESSAGES.Main.F1) : __End_a_A_MainF1_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
 __End_a_A_MainF1_FHandlingImp(c,session)}
def sndToB(m : MESSAGES.Main.F1) : __End_a_A_MainF1_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
 __End_a_A_MainF1_FHandlingImp(c,session)}

def !(m : MESSAGES.Main.F2) : __End_a_A_MainF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
 __End_a_A_MainF2_FHandlingImp(c,session)}
def sndToB(m : MESSAGES.Main.F2) : __End_a_A_MainF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
 __End_a_A_MainF2_FHandlingImp(c,session)}

}


  trait SndF1 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndF1 extends SndF1 {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainF1_FHandling)
  override type implT = __SndF1Imp
  override type implNextT = __End_a_A_MainF1_FHandlingImp
override def toString() : String = {"EPMain_a_A.SndF1"}
    override def to : RRole = RoleSet("B") 
   override def l : String = "F1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF1Imp(c,session)}

protected case class __SndF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF1
}
  private var notUsed = true
def sndToB(m : MESSAGES.Main.F1) : __End_a_A_MainF1_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainF1_FHandlingImp(c,session)}
def !(m : MESSAGES.Main.F1) : __End_a_A_MainF1_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainF1_FHandlingImp(c,session)}
def snd(m : MESSAGES.Main.F1) : __End_a_A_MainF1_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainF1_FHandlingImp(c,session)}

}


protected  trait End_a_A_MainF1_FHandling extends EPMain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_MainF1_FHandling extends End_a_A_MainF1_FHandling {
  override protected def __children: List[EPMain_a_A] = List()
  override type implT = __End_a_A_MainF1_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_a_A.End_a_A_MainF1_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_MainF1_FHandlingImp(c,session)}

protected case class __End_a_A_MainF1_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_MainF1_FHandling
}
  
}



  trait SndF2 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndF2 extends SndF2 {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainF2_FHandling)
  override type implT = __SndF2Imp
  override type implNextT = __End_a_A_MainF2_FHandlingImp
override def toString() : String = {"EPMain_a_A.SndF2"}
    override def to : RRole = RoleSet("B") 
   override def l : String = "F2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF2Imp(c,session)}

protected case class __SndF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF2
}
  private var notUsed = true
def sndToB(m : MESSAGES.Main.F2) : __End_a_A_MainF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainF2_FHandlingImp(c,session)}
def !(m : MESSAGES.Main.F2) : __End_a_A_MainF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainF2_FHandlingImp(c,session)}
def snd(m : MESSAGES.Main.F2) : __End_a_A_MainF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainF2_FHandlingImp(c,session)}

}


protected  trait End_a_A_MainF2_FHandling extends EPMain_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_MainF2_FHandling extends End_a_A_MainF2_FHandling {
  override protected def __children: List[EPMain_a_A] = List()
  override type implT = __End_a_A_MainF2_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_a_A.End_a_A_MainF2_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_MainF2_FHandlingImp(c,session)}

protected case class __End_a_A_MainF2_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_MainF2_FHandling
}
  
}


}

}

}
