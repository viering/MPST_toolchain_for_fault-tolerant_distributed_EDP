package example.TestProtocols.pingpong.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object PingPong {
object RS {
val B : RoleSet = RoleSet("B")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object Main {
case class Ping(m:String) extends MSG {
   override def l : String = "Ping"
}

case class Pong(m:String) extends MSG {
   override def l : String = "Pong"
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
  override protected def __children: List[EPMain_b_B] = List(RecT,End_b_B_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPMain_b_B with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPMain_b_B] = List(RcvPing)
  override type implT = __RecTImp
  override type implNextT = __RcvPingImp
override def toString() : String = {"EPMain_b_B.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait RcvPing extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvPing extends RcvPing {
  override protected def __children: List[EPMain_b_B] = List(SndPong)
  override type implT = __RcvPingImp
  override type implNextT = __SndPongImp
override def toString() : String = {"EPMain_b_B.RcvPing"}
  override type msgT = MESSAGES.Main.Ping
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "Ping"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPingImp(c,session)}

protected case class __RcvPingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPing
}
  def rcvFrma_A : (MESSAGES.Main.Ping,__SndPongImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.Ping],__SndPongImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.Ping,__SndPongImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.Ping],__SndPongImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.Ping = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.Ping]}
def ? : MESSAGES.Main.Ping = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.Ping]}
def channelCon : __SndPongImp = {__SndPongImp(c,session)}

}


  trait SndPong extends EPMain_b_B with event_lang.dsl.ChannelTypeSnd
  object SndPong extends SndPong {
  override protected def __children: List[EPMain_b_B] = List(T)
  override type implT = __SndPongImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_b_B.SndPong"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "Pong" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPongImp(c,session)}

protected case class __SndPongImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPong
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.Main.Pong) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__TImp(c,session)}
def !(m : MESSAGES.Main.Pong) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__TImp(c,session)}
def snd(m : MESSAGES.Main.Pong) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__TImp(c,session)}

}


protected  trait T extends EPMain_b_B with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPMain_b_B] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_b_B.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
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
  override protected def __children: List[EPMain_a_A] = List(RecT,Failed_b_B)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPMain_a_A with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPMain_a_A] = List(SndPing)
  override type implT = __RecTImp
  override type implNextT = __SndPingImp
override def toString() : String = {"EPMain_a_A.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait SndPing extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndPing extends SndPing {
  override protected def __children: List[EPMain_a_A] = List(RcvPong)
  override type implT = __SndPingImp
  override type implNextT = __RcvPongImp
override def toString() : String = {"EPMain_a_A.SndPing"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "Ping" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPingImp(c,session)}

protected case class __SndPingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPing
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.Ping) : __RcvPongImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvPongImp(c,session)}
def !(m : MESSAGES.Main.Ping) : __RcvPongImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvPongImp(c,session)}
def snd(m : MESSAGES.Main.Ping) : __RcvPongImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvPongImp(c,session)}

}


  trait RcvPong extends EPMain_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvPong extends RcvPong {
  override protected def __children: List[EPMain_a_A] = List(T)
  override type implT = __RcvPongImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_a_A.RcvPong"}
  override type msgT = MESSAGES.Main.Pong
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "Pong"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPongImp(c,session)}

protected case class __RcvPongImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPong
}
  def rcvFrmb_B : (MESSAGES.Main.Pong,__TImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.Pong],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.Pong,__TImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.Pong],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.Pong = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.Pong]}
def ? : MESSAGES.Main.Pong = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.Pong]}
def channelCon : __TImp = {__TImp(c,session)}

}


protected  trait T extends EPMain_a_A with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPMain_a_A] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_a_A.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
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
