package example.TestProtocols.restartOnFailure.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object RestartOnFailure {
object RS {
val B : RoleSet = RoleSet("B")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object Main {
case class FEnd() extends MSG {
   override def l : String = "FEnd"
}

}

object RestartP {
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

object RestartP {
val bb_B = Role("bb",RoleSet("B"))
val B = RoleSet("B")
val a_A = Role("a",RoleSet("A"))
val b_B = Role("b",RoleSet("B"))
}

}

object B {
val subs : Seq[dsl.ChannelTypeSubS] = List(RestartP_bb_B.EPRestartP_bb_B,Main_B.EPMain_B,RestartP_b_B.EPRestartP_b_B,RestartP_B.EPRestartP_B,Main_b_B.EPMain_b_B)
trait __EPType_B extends AbstractChannelType {

}

trait EPType_B[T<: TState] extends AbstractEndPoint[__EPType_B,T] {
override val roleSet: RoleSet = RoleSet("B")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(RestartP_bb_B.EPRestartP_bb_B,Main_B.EPMain_B,RestartP_b_B.EPRestartP_b_B,RestartP_B.EPRestartP_B,Main_b_B.EPMain_b_B)

}

object RestartP_b_B{
trait EPRestartP_b_B extends __EPType_B

object EPRestartP_b_B extends EPRestartP_b_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPRestartP_b_B] = List(Hdl)
  override type implT = __EPRestartP_b_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPRestartP_b_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
  override def prjTo : RRole = Role("b",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "RestartP"
}

protected case class __EPRestartP_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPRestartP_b_B
}
  
}


protected  trait Hdl extends EPRestartP_b_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPRestartP_b_B] = List(RcvM,SelF1F2)
  override type implT = __HdlImp
  override type implNextT = __RcvMImp
override def toString() : String = {"EPRestartP_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvM extends EPRestartP_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvM extends RcvM {
  override protected def __children: List[EPRestartP_b_B] = List(SndM1)
  override type implT = __RcvMImp
  override type implNextT = __SndM1Imp
override def toString() : String = {"EPRestartP_b_B.RcvM"}
  override type msgT = MESSAGES.RestartP.M
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "M"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMImp(c,session)}

protected case class __RcvMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvM
}
  def rcvFrma_A : (MESSAGES.RestartP.M,__SndM1Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.M],__SndM1Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.RestartP.M,__SndM1Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.M],__SndM1Imp(c,session))) 
}
def rcvMSG : MESSAGES.RestartP.M = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.M]}
def ? : MESSAGES.RestartP.M = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.M]}
def channelCon : __SndM1Imp = {__SndM1Imp(c,session)}

}


  trait SndM1 extends EPRestartP_b_B with event_lang.dsl.ChannelTypeSnd
  object SndM1 extends SndM1 {
  override protected def __children: List[EPRestartP_b_B] = List(End_b_B_RestartP)
  override type implT = __SndM1Imp
  override type implNextT = __End_b_B_RestartPImp
override def toString() : String = {"EPRestartP_b_B.SndM1"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "M1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndM1Imp(c,session)}

protected case class __SndM1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndM1
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.RestartP.M1) : __End_b_B_RestartPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_RestartPImp(c,session)}
def !(m : MESSAGES.RestartP.M1) : __End_b_B_RestartPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_RestartPImp(c,session)}
def snd(m : MESSAGES.RestartP.M1) : __End_b_B_RestartPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_RestartPImp(c,session)}

}


protected  trait End_b_B_RestartP extends EPRestartP_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_RestartP extends End_b_B_RestartP {
  override protected def __children: List[EPRestartP_b_B] = List()
  override type implT = __End_b_B_RestartPImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_b_B.End_b_B_RestartP"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_RestartPImp(c,session)}

protected case class __End_b_B_RestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_RestartP
}
  
}



protected  trait SelF1F2 extends EPRestartP_b_B with event_lang.dsl.ChannelTypeBrn
protected  object SelF1F2 extends SelF1F2 {
  override protected def __children: List[EPRestartP_b_B] = List(RcvF1,RcvF2)
  override type implT = __SelF1F2Imp
  override type implNextT = __RcvF1Imp
override def toString() : String = {"EPRestartP_b_B.SelF1F2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelF1F2Imp(c,session)}

protected case class __SelF1F2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelF1F2
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvF1 extends EPRestartP_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvF1 extends RcvF1 {
  override protected def __children: List[EPRestartP_b_B] = List(SpawnRestartP)
  override type implT = __RcvF1Imp
  override type implNextT = __SpawnRestartPImp
override def toString() : String = {"EPRestartP_b_B.RcvF1"}
  override type msgT = MESSAGES.RestartP.F1
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "F1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF1Imp(c,session)}

protected case class __RcvF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF1
}
  def rcvFrma_A : (MESSAGES.RestartP.F1,__SpawnRestartPImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F1],__SpawnRestartPImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.RestartP.F1,__SpawnRestartPImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F1],__SpawnRestartPImp(c,session))) 
}
def rcvMSG : MESSAGES.RestartP.F1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F1]}
def ? : MESSAGES.RestartP.F1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F1]}
def channelCon : __SpawnRestartPImp = {__SpawnRestartPImp(c,session)}

}


  trait SpawnRestartP extends EPRestartP_b_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnRestartP extends SpawnRestartP {
  override protected def __children: List[EPRestartP_b_B] = List(End_b_B_RestartPF1_FHandling)
  override type implT = __SpawnRestartPImp
  override type implNextT = __End_b_B_RestartPF1_FHandlingImp
override def toString() : String = {"EPRestartP_b_B.SpawnRestartP"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List(RoleSet("B")) 
  override def name: String = "RestartP" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnRestartPImp(c,session)}

protected case class __SpawnRestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnRestartP
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_b_B_RestartPF1_FHandling extends EPRestartP_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_RestartPF1_FHandling extends End_b_B_RestartPF1_FHandling {
  override protected def __children: List[EPRestartP_b_B] = List()
  override type implT = __End_b_B_RestartPF1_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_b_B.End_b_B_RestartPF1_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_RestartPF1_FHandlingImp(c,session)}

protected case class __End_b_B_RestartPF1_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_RestartPF1_FHandling
}
  
}



  trait RcvF2 extends EPRestartP_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvF2 extends RcvF2 {
  override protected def __children: List[EPRestartP_b_B] = List(End_b_B_RestartPF2_FHandling)
  override type implT = __RcvF2Imp
  override type implNextT = __End_b_B_RestartPF2_FHandlingImp
override def toString() : String = {"EPRestartP_b_B.RcvF2"}
  override type msgT = MESSAGES.RestartP.F2
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "F2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF2Imp(c,session)}

protected case class __RcvF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF2
}
  def rcvFrma_A : (MESSAGES.RestartP.F2,__End_b_B_RestartPF2_FHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F2],__End_b_B_RestartPF2_FHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.RestartP.F2,__End_b_B_RestartPF2_FHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F2],__End_b_B_RestartPF2_FHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.RestartP.F2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F2]}
def ? : MESSAGES.RestartP.F2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F2]}
def channelCon : __End_b_B_RestartPF2_FHandlingImp = {__End_b_B_RestartPF2_FHandlingImp(c,session)}

}


protected  trait End_b_B_RestartPF2_FHandling extends EPRestartP_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_RestartPF2_FHandling extends End_b_B_RestartPF2_FHandling {
  override protected def __children: List[EPRestartP_b_B] = List()
  override type implT = __End_b_B_RestartPF2_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_b_B.End_b_B_RestartPF2_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_RestartPF2_FHandlingImp(c,session)}

protected case class __End_b_B_RestartPF2_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_RestartPF2_FHandling
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
  override protected def __children: List[EPMain_b_B] = List(SpawnRestartP,End_b_B_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnRestartPImp
override def toString() : String = {"EPMain_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnRestartP extends EPMain_b_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnRestartP extends SpawnRestartP {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_Main)
  override type implT = __SpawnRestartPImp
  override type implNextT = __End_b_B_MainImp
override def toString() : String = {"EPMain_b_B.SpawnRestartP"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List(RoleSet("B")) 
  override def name: String = "RestartP" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnRestartPImp(c,session)}

protected case class __SpawnRestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnRestartP
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

object RestartP_B{
trait EPRestartP_B extends __EPType_B

object EPRestartP_B extends EPRestartP_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPRestartP_B] = List(Hdl)
  override type implT = __EPRestartP_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPRestartP_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
  override def prjTo : RRole = RoleSet("B") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "RestartP"
}

protected case class __EPRestartP_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPRestartP_B
}
  
}


protected  trait Hdl extends EPRestartP_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPRestartP_B] = List(End_B_RestartP,SelF1F2)
  override type implT = __HdlImp
  override type implNextT = __End_B_RestartPImp
override def toString() : String = {"EPRestartP_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_B_RestartP extends EPRestartP_B with event_lang.dsl.ChannelTypeEnd
protected  object End_B_RestartP extends End_B_RestartP {
  override protected def __children: List[EPRestartP_B] = List()
  override type implT = __End_B_RestartPImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_B.End_B_RestartP"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_B_RestartPImp(c,session)}

protected case class __End_B_RestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_B_RestartP
}
  
}



protected  trait SelF1F2 extends EPRestartP_B with event_lang.dsl.ChannelTypeBrn
protected  object SelF1F2 extends SelF1F2 {
  override protected def __children: List[EPRestartP_B] = List(RcvF1,RcvF2)
  override type implT = __SelF1F2Imp
  override type implNextT = __RcvF1Imp
override def toString() : String = {"EPRestartP_B.SelF1F2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelF1F2Imp(c,session)}

protected case class __SelF1F2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelF1F2
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvF1 extends EPRestartP_B with event_lang.dsl.ChannelTypeRcv
  object RcvF1 extends RcvF1 {
  override protected def __children: List[EPRestartP_B] = List(SpawnRestartP)
  override type implT = __RcvF1Imp
  override type implNextT = __SpawnRestartPImp
override def toString() : String = {"EPRestartP_B.RcvF1"}
  override type msgT = MESSAGES.RestartP.F1
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "F1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF1Imp(c,session)}

protected case class __RcvF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF1
}
  def rcvFrma_A : (MESSAGES.RestartP.F1,__SpawnRestartPImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F1],__SpawnRestartPImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.RestartP.F1,__SpawnRestartPImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F1],__SpawnRestartPImp(c,session))) 
}
def rcvMSG : MESSAGES.RestartP.F1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F1]}
def ? : MESSAGES.RestartP.F1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F1]}
def channelCon : __SpawnRestartPImp = {__SpawnRestartPImp(c,session)}

}


  trait SpawnRestartP extends EPRestartP_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnRestartP extends SpawnRestartP {
  override protected def __children: List[EPRestartP_B] = List(End_B_RestartPF1_FHandling)
  override type implT = __SpawnRestartPImp
  override type implNextT = __End_B_RestartPF1_FHandlingImp
override def toString() : String = {"EPRestartP_B.SpawnRestartP"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List(RoleSet("B")) 
  override def name: String = "RestartP" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnRestartPImp(c,session)}

protected case class __SpawnRestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnRestartP
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_B_RestartPF1_FHandling extends EPRestartP_B with event_lang.dsl.ChannelTypeEnd
protected  object End_B_RestartPF1_FHandling extends End_B_RestartPF1_FHandling {
  override protected def __children: List[EPRestartP_B] = List()
  override type implT = __End_B_RestartPF1_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_B.End_B_RestartPF1_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_B_RestartPF1_FHandlingImp(c,session)}

protected case class __End_B_RestartPF1_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_B_RestartPF1_FHandling
}
  
}



  trait RcvF2 extends EPRestartP_B with event_lang.dsl.ChannelTypeRcv
  object RcvF2 extends RcvF2 {
  override protected def __children: List[EPRestartP_B] = List(End_B_RestartPF2_FHandling)
  override type implT = __RcvF2Imp
  override type implNextT = __End_B_RestartPF2_FHandlingImp
override def toString() : String = {"EPRestartP_B.RcvF2"}
  override type msgT = MESSAGES.RestartP.F2
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "F2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF2Imp(c,session)}

protected case class __RcvF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF2
}
  def rcvFrma_A : (MESSAGES.RestartP.F2,__End_B_RestartPF2_FHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F2],__End_B_RestartPF2_FHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.RestartP.F2,__End_B_RestartPF2_FHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F2],__End_B_RestartPF2_FHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.RestartP.F2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F2]}
def ? : MESSAGES.RestartP.F2 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.RestartP.F2]}
def channelCon : __End_B_RestartPF2_FHandlingImp = {__End_B_RestartPF2_FHandlingImp(c,session)}

}


protected  trait End_B_RestartPF2_FHandling extends EPRestartP_B with event_lang.dsl.ChannelTypeEnd
protected  object End_B_RestartPF2_FHandling extends End_B_RestartPF2_FHandling {
  override protected def __children: List[EPRestartP_B] = List()
  override type implT = __End_B_RestartPF2_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_B.End_B_RestartPF2_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_B_RestartPF2_FHandlingImp(c,session)}

protected case class __End_B_RestartPF2_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_B_RestartPF2_FHandling
}
  
}


}

object RestartP_bb_B{
trait EPRestartP_bb_B extends __EPType_B

object EPRestartP_bb_B extends EPRestartP_bb_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPRestartP_bb_B] = List(Hdl)
  override type implT = __EPRestartP_bb_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPRestartP_bb_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
  override def prjTo : RRole = Role("bb",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "RestartP"
}

protected case class __EPRestartP_bb_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPRestartP_bb_B
}
  
}


protected  trait Hdl extends EPRestartP_bb_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPRestartP_bb_B] = List(End_bb_B_RestartP,End_bb_B_RestartPFHandling)
  override type implT = __HdlImp
  override type implNextT = __End_bb_B_RestartPImp
override def toString() : String = {"EPRestartP_bb_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_bb_B_RestartP extends EPRestartP_bb_B with event_lang.dsl.ChannelTypeEnd
protected  object End_bb_B_RestartP extends End_bb_B_RestartP {
  override protected def __children: List[EPRestartP_bb_B] = List()
  override type implT = __End_bb_B_RestartPImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_bb_B.End_bb_B_RestartP"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_bb_B_RestartPImp(c,session)}

protected case class __End_bb_B_RestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_bb_B_RestartP
}
  
}



protected  trait End_bb_B_RestartPFHandling extends EPRestartP_bb_B with event_lang.dsl.ChannelTypeEnd
protected  object End_bb_B_RestartPFHandling extends End_bb_B_RestartPFHandling {
  override protected def __children: List[EPRestartP_bb_B] = List()
  override type implT = __End_bb_B_RestartPFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_bb_B.End_bb_B_RestartPFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_bb_B_RestartPFHandlingImp(c,session)}

protected case class __End_bb_B_RestartPFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_bb_B_RestartPFHandling
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
  override protected def __children: List[EPMain_B] = List(SpawnRestartP,RcvFEnd)
  override type implT = __HdlImp
  override type implNextT = __SpawnRestartPImp
override def toString() : String = {"EPMain_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnRestartP extends EPMain_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnRestartP extends SpawnRestartP {
  override protected def __children: List[EPMain_B] = List(End_B_Main)
  override type implT = __SpawnRestartPImp
  override type implNextT = __End_B_MainImp
override def toString() : String = {"EPMain_B.SpawnRestartP"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List(RoleSet("B")) 
  override def name: String = "RestartP" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnRestartPImp(c,session)}

protected case class __SpawnRestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnRestartP
}
  // SPAWN is handled internally -- i.e. no use code here
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



  trait RcvFEnd extends EPMain_B with event_lang.dsl.ChannelTypeRcv
  object RcvFEnd extends RcvFEnd {
  override protected def __children: List[EPMain_B] = List(End_B_MainFHandling)
  override type implT = __RcvFEndImp
  override type implNextT = __End_B_MainFHandlingImp
override def toString() : String = {"EPMain_B.RcvFEnd"}
  override type msgT = MESSAGES.Main.FEnd
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FEnd"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFEndImp(c,session)}

protected case class __RcvFEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFEnd
}
  def rcvFrma_A : (MESSAGES.Main.FEnd,__End_B_MainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FEnd],__End_B_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FEnd,__End_B_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FEnd],__End_B_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FEnd = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FEnd]}
def ? : MESSAGES.Main.FEnd = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FEnd]}
def channelCon : __End_B_MainFHandlingImp = {__End_B_MainFHandlingImp(c,session)}

}


protected  trait End_B_MainFHandling extends EPMain_B with event_lang.dsl.ChannelTypeEnd
protected  object End_B_MainFHandling extends End_B_MainFHandling {
  override protected def __children: List[EPMain_B] = List()
  override type implT = __End_B_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_B.End_B_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_B_MainFHandlingImp(c,session)}

protected case class __End_B_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_B_MainFHandling
}
  
}


}

}

object A {
val subs : Seq[dsl.ChannelTypeSubS] = List(RestartP_a_A.EPRestartP_a_A,Main_a_A.EPMain_a_A)
trait __EPType_A extends AbstractChannelType {

}

trait EPType_A[T<: TState] extends AbstractEndPoint[__EPType_A,T] {
override val roleSet: RoleSet = RoleSet("A")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(RestartP_a_A.EPRestartP_a_A,Main_a_A.EPMain_a_A)

}

object RestartP_a_A{
trait EPRestartP_a_A extends __EPType_A

object EPRestartP_a_A extends EPRestartP_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPRestartP_a_A] = List(Hdl)
  override type implT = __EPRestartP_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPRestartP_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "RestartP"
}

protected case class __EPRestartP_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPRestartP_a_A
}
  
}


protected  trait Hdl extends EPRestartP_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPRestartP_a_A] = List(SndM,Failed_bb_B)
  override type implT = __HdlImp
  override type implNextT = __SndMImp
override def toString() : String = {"EPRestartP_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndM extends EPRestartP_a_A with event_lang.dsl.ChannelTypeSnd
  object SndM extends SndM {
  override protected def __children: List[EPRestartP_a_A] = List(RcvM1)
  override type implT = __SndMImp
  override type implNextT = __RcvM1Imp
override def toString() : String = {"EPRestartP_a_A.SndM"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "M" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMImp(c,session)}

protected case class __SndMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndM
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.RestartP.M) : __RcvM1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvM1Imp(c,session)}
def !(m : MESSAGES.RestartP.M) : __RcvM1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvM1Imp(c,session)}
def snd(m : MESSAGES.RestartP.M) : __RcvM1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvM1Imp(c,session)}

}


  trait RcvM1 extends EPRestartP_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvM1 extends RcvM1 {
  override protected def __children: List[EPRestartP_a_A] = List(End_a_A_RestartP)
  override type implT = __RcvM1Imp
  override type implNextT = __End_a_A_RestartPImp
override def toString() : String = {"EPRestartP_a_A.RcvM1"}
  override type msgT = MESSAGES.RestartP.M1
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "M1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvM1Imp(c,session)}

protected case class __RcvM1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvM1
}
  def rcvFrmb_B : (MESSAGES.RestartP.M1,__End_a_A_RestartPImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.RestartP.M1],__End_a_A_RestartPImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.RestartP.M1,__End_a_A_RestartPImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.RestartP.M1],__End_a_A_RestartPImp(c,session))) 
}
def rcvMSG : MESSAGES.RestartP.M1 = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.RestartP.M1]}
def ? : MESSAGES.RestartP.M1 = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.RestartP.M1]}
def channelCon : __End_a_A_RestartPImp = {__End_a_A_RestartPImp(c,session)}

}


protected  trait End_a_A_RestartP extends EPRestartP_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_RestartP extends End_a_A_RestartP {
  override protected def __children: List[EPRestartP_a_A] = List()
  override type implT = __End_a_A_RestartPImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_a_A.End_a_A_RestartP"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_RestartPImp(c,session)}

protected case class __End_a_A_RestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_RestartP
}
  
}



  trait Failed_bb_B extends EPRestartP_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_bb_B extends Failed_bb_B {
  override protected def __children: List[EPRestartP_a_A] = List(SelF1F2)
  override type implT = __Failed_bb_BImp
  override type implNextT = __SelF1F2Imp
override def toString() : String = {"EPRestartP_a_A.Failed_bb_B"}
  override def suspect : Role = Role("bb",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_bb_BImp(c,session)}

protected case class __Failed_bb_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_bb_B
}
  def failed_bb_B(): __SelF1F2Imp = {__SelF1F2Imp(c,session)}

}


protected  trait SelF1F2 extends EPRestartP_a_A with event_lang.dsl.ChannelTypeSel
protected  object SelF1F2 extends SelF1F2 {
  override protected def __children: List[EPRestartP_a_A] = List(SndF1,SndF2)
  override type implT = __SelF1F2Imp
  override type implNextT = __SndF1Imp
override def toString() : String = {"EPRestartP_a_A.SelF1F2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelF1F2Imp(c,session)}

protected case class __SelF1F2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelF1F2
}
  private var notUsed = true
def !(m : MESSAGES.RestartP.F1) : __SpawnRestartPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
 __SpawnRestartPImp(c,session)}
def sndToB(m : MESSAGES.RestartP.F1) : __SpawnRestartPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
 __SpawnRestartPImp(c,session)}

def !(m : MESSAGES.RestartP.F2) : __End_a_A_RestartPF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
 __End_a_A_RestartPF2_FHandlingImp(c,session)}
def sndToB(m : MESSAGES.RestartP.F2) : __End_a_A_RestartPF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
 __End_a_A_RestartPF2_FHandlingImp(c,session)}

}


  trait SndF1 extends EPRestartP_a_A with event_lang.dsl.ChannelTypeSnd
  object SndF1 extends SndF1 {
  override protected def __children: List[EPRestartP_a_A] = List(SpawnRestartP)
  override type implT = __SndF1Imp
  override type implNextT = __SpawnRestartPImp
override def toString() : String = {"EPRestartP_a_A.SndF1"}
    override def to : RRole = RoleSet("B") 
   override def l : String = "F1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF1Imp(c,session)}

protected case class __SndF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF1
}
  private var notUsed = true
def sndToB(m : MESSAGES.RestartP.F1) : __SpawnRestartPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__SpawnRestartPImp(c,session)}
def !(m : MESSAGES.RestartP.F1) : __SpawnRestartPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__SpawnRestartPImp(c,session)}
def snd(m : MESSAGES.RestartP.F1) : __SpawnRestartPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__SpawnRestartPImp(c,session)}

}


  trait SpawnRestartP extends EPRestartP_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnRestartP extends SpawnRestartP {
  override protected def __children: List[EPRestartP_a_A] = List(End_a_A_RestartPF1_FHandling)
  override type implT = __SpawnRestartPImp
  override type implNextT = __End_a_A_RestartPF1_FHandlingImp
override def toString() : String = {"EPRestartP_a_A.SpawnRestartP"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List(RoleSet("B")) 
  override def name: String = "RestartP" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnRestartPImp(c,session)}

protected case class __SpawnRestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnRestartP
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_a_A_RestartPF1_FHandling extends EPRestartP_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_RestartPF1_FHandling extends End_a_A_RestartPF1_FHandling {
  override protected def __children: List[EPRestartP_a_A] = List()
  override type implT = __End_a_A_RestartPF1_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_a_A.End_a_A_RestartPF1_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_RestartPF1_FHandlingImp(c,session)}

protected case class __End_a_A_RestartPF1_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_RestartPF1_FHandling
}
  
}



  trait SndF2 extends EPRestartP_a_A with event_lang.dsl.ChannelTypeSnd
  object SndF2 extends SndF2 {
  override protected def __children: List[EPRestartP_a_A] = List(End_a_A_RestartPF2_FHandling)
  override type implT = __SndF2Imp
  override type implNextT = __End_a_A_RestartPF2_FHandlingImp
override def toString() : String = {"EPRestartP_a_A.SndF2"}
    override def to : RRole = RoleSet("B") 
   override def l : String = "F2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF2Imp(c,session)}

protected case class __SndF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF2
}
  private var notUsed = true
def sndToB(m : MESSAGES.RestartP.F2) : __End_a_A_RestartPF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_RestartPF2_FHandlingImp(c,session)}
def !(m : MESSAGES.RestartP.F2) : __End_a_A_RestartPF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_RestartPF2_FHandlingImp(c,session)}
def snd(m : MESSAGES.RestartP.F2) : __End_a_A_RestartPF2_FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_RestartPF2_FHandlingImp(c,session)}

}


protected  trait End_a_A_RestartPF2_FHandling extends EPRestartP_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_RestartPF2_FHandling extends End_a_A_RestartPF2_FHandling {
  override protected def __children: List[EPRestartP_a_A] = List()
  override type implT = __End_a_A_RestartPF2_FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPRestartP_a_A.End_a_A_RestartPF2_FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_RestartPF2_FHandlingImp(c,session)}

protected case class __End_a_A_RestartPF2_FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_RestartPF2_FHandling
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
  override protected def __children: List[EPMain_a_A] = List(SpawnRestartP,Failed_b_B)
  override type implT = __HdlImp
  override type implNextT = __SpawnRestartPImp
override def toString() : String = {"EPMain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnRestartP extends EPMain_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnRestartP extends SpawnRestartP {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_Main)
  override type implT = __SpawnRestartPImp
  override type implNextT = __End_a_A_MainImp
override def toString() : String = {"EPMain_a_A.SpawnRestartP"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List(RoleSet("B")) 
  override def name: String = "RestartP" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnRestartPImp(c,session)}

protected case class __SpawnRestartPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnRestartP
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
  override protected def __children: List[EPMain_a_A] = List(SndFEnd)
  override type implT = __Failed_b_BImp
  override type implNextT = __SndFEndImp
override def toString() : String = {"EPMain_a_A.Failed_b_B"}
  override def suspect : Role = Role("b",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b_BImp(c,session)}

protected case class __Failed_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b_B
}
  def failed_b_B(): __SndFEndImp = {__SndFEndImp(c,session)}

}


  trait SndFEnd extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFEnd extends SndFEnd {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainFHandling)
  override type implT = __SndFEndImp
  override type implNextT = __End_a_A_MainFHandlingImp
override def toString() : String = {"EPMain_a_A.SndFEnd"}
    override def to : RRole = RoleSet("B") 
   override def l : String = "FEnd" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFEndImp(c,session)}

protected case class __SndFEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFEnd
}
  private var notUsed = true
def sndToB(m : MESSAGES.Main.FEnd) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.FEnd) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.FEnd) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
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
