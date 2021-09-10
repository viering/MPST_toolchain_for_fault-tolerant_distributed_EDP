package example.TestProtocols.pingpong_spawn_branch
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object PingPongSpawnBranch {
object RS {
val B : RoleSet = RoleSet("B")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object PingPong {
case class Start() extends MSG {
   override def l : String = "Start"
}

case class FailD() extends MSG {
   override def l : String = "FailD"
}

case class Pong(m:String) extends MSG {
   override def l : String = "Pong"
}

case class EndBrn() extends MSG {
   override def l : String = "EndBrn"
}

case class Ping(m:String) extends MSG {
   override def l : String = "Ping"
}

case class End() extends MSG {
   override def l : String = "End"
}

case class PingBrn() extends MSG {
   override def l : String = "PingBrn"
}

}

object Main {
case class LMain() extends MSG {
   override def l : String = "LMain"
}

case class FailB() extends MSG {
   override def l : String = "FailB"
}

}

}

object PROTOCOLS {
object PingPong {
val bb_B = Role("bb",RoleSet("B"))
val a_A = Role("a",RoleSet("A"))
val b_B = Role("b",RoleSet("B"))
}

object Main {
val b_B = Role("b",RoleSet("B"))
val B = RoleSet("B")
val a_A = Role("a",RoleSet("A"))
}

}

object B {
val subs : Seq[dsl.ChannelTypeSubS] = List(PingPong_bb_B.EPPingPong_bb_B,PingPong_b_B.EPPingPong_b_B,Main_b_B.EPMain_b_B,Main_B.EPMain_B)
trait __EPType_B extends AbstractChannelType {

}

trait EPType_B[T<: TState] extends AbstractEndPoint[__EPType_B,T] {
override val roleSet: RoleSet = RoleSet("B")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(PingPong_bb_B.EPPingPong_bb_B,PingPong_b_B.EPPingPong_b_B,Main_b_B.EPMain_b_B,Main_B.EPMain_B)

}

object PingPong_bb_B{
trait EPPingPong_bb_B extends __EPType_B

object EPPingPong_bb_B extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPingPong_bb_B] = List(Hdl)
  override type implT = __EPPingPong_bb_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPingPong_bb_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("bb",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "PingPong"
}

protected case class __EPPingPong_bb_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPingPong_bb_B
}
  
}


protected  trait Hdl extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPingPong_bb_B] = List(SndStart,End_bb_B_PingPongFHandling)
  override type implT = __HdlImp
  override type implNextT = __SndStartImp
override def toString() : String = {"EPPingPong_bb_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndStart extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeSnd
  object SndStart extends SndStart {
  override protected def __children: List[EPPingPong_bb_B] = List(RecT)
  override type implT = __SndStartImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPingPong_bb_B.SndStart"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "Start" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndStartImp(c,session)}

protected case class __SndStartImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndStart
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.PingPong.Start) : __RecTImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__RecTImp(c,session)}
def !(m : MESSAGES.PingPong.Start) : __RecTImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__RecTImp(c,session)}
def snd(m : MESSAGES.PingPong.Start) : __RecTImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__RecTImp(c,session)}

}


protected  trait RecT extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPPingPong_bb_B] = List(Merge_PingBrn_EndBrn)
  override type implT = __RecTImp
  override type implNextT = __Merge_PingBrn_EndBrnImp
override def toString() : String = {"EPPingPong_bb_B.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait Merge_PingBrn_EndBrn extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeMerge
protected  object Merge_PingBrn_EndBrn extends Merge_PingBrn_EndBrn {
  override protected def __children: List[EPPingPong_bb_B] = List(RcvPingBrn,RcvEndBrn)
  override type implT = __Merge_PingBrn_EndBrnImp
  override type implNextT = __RcvPingBrnImp
override def toString() : String = {"EPPingPong_bb_B.Merge_PingBrn_EndBrn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Merge_PingBrn_EndBrnImp(c,session)}

protected case class __Merge_PingBrn_EndBrnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Merge_PingBrn_EndBrn
}
  
}


  trait RcvPingBrn extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeRcv
  object RcvPingBrn extends RcvPingBrn {
  override protected def __children: List[EPPingPong_bb_B] = List(T)
  override type implT = __RcvPingBrnImp
  override type implNextT = __TImp
override def toString() : String = {"EPPingPong_bb_B.RcvPingBrn"}
  override type msgT = MESSAGES.PingPong.PingBrn
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "PingBrn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPingBrnImp(c,session)}

protected case class __RcvPingBrnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPingBrn
}
  def rcvFrma_A : (MESSAGES.PingPong.PingBrn,__TImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.PingBrn],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.PingPong.PingBrn,__TImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.PingBrn],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.PingPong.PingBrn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.PingBrn]}
def ? : MESSAGES.PingPong.PingBrn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.PingBrn]}
def channelCon : __TImp = {__TImp(c,session)}

}


protected  trait T extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPPingPong_bb_B] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPingPong_bb_B.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvEndBrn extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeRcv
  object RcvEndBrn extends RcvEndBrn {
  override protected def __children: List[EPPingPong_bb_B] = List(End_bb_B_PingPongEndBrn)
  override type implT = __RcvEndBrnImp
  override type implNextT = __End_bb_B_PingPongEndBrnImp
override def toString() : String = {"EPPingPong_bb_B.RcvEndBrn"}
  override type msgT = MESSAGES.PingPong.EndBrn
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "EndBrn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndBrnImp(c,session)}

protected case class __RcvEndBrnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEndBrn
}
  def rcvFrma_A : (MESSAGES.PingPong.EndBrn,__End_bb_B_PingPongEndBrnImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.EndBrn],__End_bb_B_PingPongEndBrnImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.PingPong.EndBrn,__End_bb_B_PingPongEndBrnImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.EndBrn],__End_bb_B_PingPongEndBrnImp(c,session))) 
}
def rcvMSG : MESSAGES.PingPong.EndBrn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.EndBrn]}
def ? : MESSAGES.PingPong.EndBrn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.EndBrn]}
def channelCon : __End_bb_B_PingPongEndBrnImp = {__End_bb_B_PingPongEndBrnImp(c,session)}

}


protected  trait End_bb_B_PingPongEndBrn extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeEnd
protected  object End_bb_B_PingPongEndBrn extends End_bb_B_PingPongEndBrn {
  override protected def __children: List[EPPingPong_bb_B] = List()
  override type implT = __End_bb_B_PingPongEndBrnImp
  override type implNextT = Nothing
override def toString() : String = {"EPPingPong_bb_B.End_bb_B_PingPongEndBrn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_bb_B_PingPongEndBrnImp(c,session)}

protected case class __End_bb_B_PingPongEndBrnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_bb_B_PingPongEndBrn
}
  
}



protected  trait End_bb_B_PingPongFHandling extends EPPingPong_bb_B with event_lang.dsl.ChannelTypeEnd
protected  object End_bb_B_PingPongFHandling extends End_bb_B_PingPongFHandling {
  override protected def __children: List[EPPingPong_bb_B] = List()
  override type implT = __End_bb_B_PingPongFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPingPong_bb_B.End_bb_B_PingPongFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_bb_B_PingPongFHandlingImp(c,session)}

protected case class __End_bb_B_PingPongFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_bb_B_PingPongFHandling
}
  
}


}

object PingPong_b_B{
trait EPPingPong_b_B extends __EPType_B

object EPPingPong_b_B extends EPPingPong_b_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPingPong_b_B] = List(Hdl)
  override type implT = __EPPingPong_b_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPingPong_b_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("b",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "PingPong"
}

protected case class __EPPingPong_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPingPong_b_B
}
  
}


protected  trait Hdl extends EPPingPong_b_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPingPong_b_B] = List(RecT,RcvFailD)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPingPong_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPPingPong_b_B with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPPingPong_b_B] = List(SelPingEnd)
  override type implT = __RecTImp
  override type implNextT = __SelPingEndImp
override def toString() : String = {"EPPingPong_b_B.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelPingEnd extends EPPingPong_b_B with event_lang.dsl.ChannelTypeBrn
protected  object SelPingEnd extends SelPingEnd {
  override protected def __children: List[EPPingPong_b_B] = List(RcvPing,RcvEnd)
  override type implT = __SelPingEndImp
  override type implNextT = __RcvPingImp
override def toString() : String = {"EPPingPong_b_B.SelPingEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelPingEndImp(c,session)}

protected case class __SelPingEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelPingEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvPing extends EPPingPong_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvPing extends RcvPing {
  override protected def __children: List[EPPingPong_b_B] = List(SndPong)
  override type implT = __RcvPingImp
  override type implNextT = __SndPongImp
override def toString() : String = {"EPPingPong_b_B.RcvPing"}
  override type msgT = MESSAGES.PingPong.Ping
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "Ping"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPingImp(c,session)}

protected case class __RcvPingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPing
}
  def rcvFrma_A : (MESSAGES.PingPong.Ping,__SndPongImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.Ping],__SndPongImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.PingPong.Ping,__SndPongImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.Ping],__SndPongImp(c,session))) 
}
def rcvMSG : MESSAGES.PingPong.Ping = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.Ping]}
def ? : MESSAGES.PingPong.Ping = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.Ping]}
def channelCon : __SndPongImp = {__SndPongImp(c,session)}

}


  trait SndPong extends EPPingPong_b_B with event_lang.dsl.ChannelTypeSnd
  object SndPong extends SndPong {
  override protected def __children: List[EPPingPong_b_B] = List(T)
  override type implT = __SndPongImp
  override type implNextT = __TImp
override def toString() : String = {"EPPingPong_b_B.SndPong"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "Pong" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPongImp(c,session)}

protected case class __SndPongImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPong
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.PingPong.Pong) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__TImp(c,session)}
def !(m : MESSAGES.PingPong.Pong) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__TImp(c,session)}
def snd(m : MESSAGES.PingPong.Pong) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__TImp(c,session)}

}


protected  trait T extends EPPingPong_b_B with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPPingPong_b_B] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPingPong_b_B.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvEnd extends EPPingPong_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPPingPong_b_B] = List(End_b_B_PingPongEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_b_B_PingPongEndImp
override def toString() : String = {"EPPingPong_b_B.RcvEnd"}
  override type msgT = MESSAGES.PingPong.End
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrma_A : (MESSAGES.PingPong.End,__End_b_B_PingPongEndImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.End],__End_b_B_PingPongEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.PingPong.End,__End_b_B_PingPongEndImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.End],__End_b_B_PingPongEndImp(c,session))) 
}
def rcvMSG : MESSAGES.PingPong.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.End]}
def ? : MESSAGES.PingPong.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.End]}
def channelCon : __End_b_B_PingPongEndImp = {__End_b_B_PingPongEndImp(c,session)}

}


protected  trait End_b_B_PingPongEnd extends EPPingPong_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_PingPongEnd extends End_b_B_PingPongEnd {
  override protected def __children: List[EPPingPong_b_B] = List()
  override type implT = __End_b_B_PingPongEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPPingPong_b_B.End_b_B_PingPongEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_PingPongEndImp(c,session)}

protected case class __End_b_B_PingPongEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_PingPongEnd
}
  
}



  trait RcvFailD extends EPPingPong_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvFailD extends RcvFailD {
  override protected def __children: List[EPPingPong_b_B] = List(End_b_B_PingPongFHandling)
  override type implT = __RcvFailDImp
  override type implNextT = __End_b_B_PingPongFHandlingImp
override def toString() : String = {"EPPingPong_b_B.RcvFailD"}
  override type msgT = MESSAGES.PingPong.FailD
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FailD"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFailDImp(c,session)}

protected case class __RcvFailDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFailD
}
  def rcvFrma_A : (MESSAGES.PingPong.FailD,__End_b_B_PingPongFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.FailD],__End_b_B_PingPongFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.PingPong.FailD,__End_b_B_PingPongFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.FailD],__End_b_B_PingPongFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.PingPong.FailD = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.FailD]}
def ? : MESSAGES.PingPong.FailD = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.PingPong.FailD]}
def channelCon : __End_b_B_PingPongFHandlingImp = {__End_b_B_PingPongFHandlingImp(c,session)}

}


protected  trait End_b_B_PingPongFHandling extends EPPingPong_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_PingPongFHandling extends End_b_B_PingPongFHandling {
  override protected def __children: List[EPPingPong_b_B] = List()
  override type implT = __End_b_B_PingPongFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPingPong_b_B.End_b_B_PingPongFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_PingPongFHandlingImp(c,session)}

protected case class __End_b_B_PingPongFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_PingPongFHandling
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
  override protected def __children: List[EPMain_b_B] = List(RcvLMain,End_b_B_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvLMainImp
override def toString() : String = {"EPMain_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvLMain extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvLMain extends RcvLMain {
  override protected def __children: List[EPMain_b_B] = List(SpawnPingPong)
  override type implT = __RcvLMainImp
  override type implNextT = __SpawnPingPongImp
override def toString() : String = {"EPMain_b_B.RcvLMain"}
  override type msgT = MESSAGES.Main.LMain
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "LMain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvLMainImp(c,session)}

protected case class __RcvLMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvLMain
}
  def rcvFrma_A : (MESSAGES.Main.LMain,__SpawnPingPongImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.LMain],__SpawnPingPongImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.LMain,__SpawnPingPongImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.LMain],__SpawnPingPongImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.LMain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.LMain]}
def ? : MESSAGES.Main.LMain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.LMain]}
def channelCon : __SpawnPingPongImp = {__SpawnPingPongImp(c,session)}

}


  trait SpawnPingPong extends EPMain_b_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnPingPong extends SpawnPingPong {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_Main)
  override type implT = __SpawnPingPongImp
  override type implNextT = __End_b_B_MainImp
override def toString() : String = {"EPMain_b_B.SpawnPingPong"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "PingPong" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPingPongImp(c,session)}

protected case class __SpawnPingPongImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPingPong
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
  override protected def __children: List[EPMain_B] = List(SpawnPingPong,RcvFailB)
  override type implT = __HdlImp
  override type implNextT = __SpawnPingPongImp
override def toString() : String = {"EPMain_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnPingPong extends EPMain_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnPingPong extends SpawnPingPong {
  override protected def __children: List[EPMain_B] = List(End_B_Main)
  override type implT = __SpawnPingPongImp
  override type implNextT = __End_B_MainImp
override def toString() : String = {"EPMain_B.SpawnPingPong"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "PingPong" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPingPongImp(c,session)}

protected case class __SpawnPingPongImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPingPong
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



  trait RcvFailB extends EPMain_B with event_lang.dsl.ChannelTypeRcv
  object RcvFailB extends RcvFailB {
  override protected def __children: List[EPMain_B] = List(End_B_MainFHandling)
  override type implT = __RcvFailBImp
  override type implNextT = __End_B_MainFHandlingImp
override def toString() : String = {"EPMain_B.RcvFailB"}
  override type msgT = MESSAGES.Main.FailB
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FailB"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFailBImp(c,session)}

protected case class __RcvFailBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFailB
}
  def rcvFrma_A : (MESSAGES.Main.FailB,__End_B_MainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FailB],__End_B_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FailB,__End_B_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FailB],__End_B_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FailB = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FailB]}
def ? : MESSAGES.Main.FailB = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FailB]}
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
val subs : Seq[dsl.ChannelTypeSubS] = List(PingPong_a_A.EPPingPong_a_A,Main_a_A.EPMain_a_A)
trait __EPType_A extends AbstractChannelType {

}

trait EPType_A[T<: TState] extends AbstractEndPoint[__EPType_A,T] {
override val roleSet: RoleSet = RoleSet("A")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(PingPong_a_A.EPPingPong_a_A,Main_a_A.EPMain_a_A)

}

object PingPong_a_A{
trait EPPingPong_a_A extends __EPType_A

object EPPingPong_a_A extends EPPingPong_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPingPong_a_A] = List(Hdl)
  override type implT = __EPPingPong_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPingPong_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "PingPong"
}

protected case class __EPPingPong_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPingPong_a_A
}
  
}


protected  trait Hdl extends EPPingPong_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPingPong_a_A] = List(RcvStart,Failed_bb_B)
  override type implT = __HdlImp
  override type implNextT = __RcvStartImp
override def toString() : String = {"EPPingPong_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvStart extends EPPingPong_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvStart extends RcvStart {
  override protected def __children: List[EPPingPong_a_A] = List(RecT)
  override type implT = __RcvStartImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPingPong_a_A.RcvStart"}
  override type msgT = MESSAGES.PingPong.Start
   override def frm : Role = Role("bb",RoleSet("B")) 
   override def l : String = "Start"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvStartImp(c,session)}

protected case class __RcvStartImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvStart
}
  def rcvFrmbb_B : (MESSAGES.PingPong.Start,__RecTImp) = {(c.rcv(Role("bb",RoleSet("B"))).asInstanceOf[MESSAGES.PingPong.Start],__RecTImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.PingPong.Start,__RecTImp),T]) : T = {
  f((c.rcv(Role("bb",RoleSet("B"))).asInstanceOf[MESSAGES.PingPong.Start],__RecTImp(c,session))) 
}
def rcvMSG : MESSAGES.PingPong.Start = {c.rcv(Role("bb",RoleSet("B"))).asInstanceOf[MESSAGES.PingPong.Start]}
def ? : MESSAGES.PingPong.Start = {c.rcv(Role("bb",RoleSet("B"))).asInstanceOf[MESSAGES.PingPong.Start]}
def channelCon : __RecTImp = {__RecTImp(c,session)}

}


protected  trait RecT extends EPPingPong_a_A with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPPingPong_a_A] = List(SelPingEnd)
  override type implT = __RecTImp
  override type implNextT = __SelPingEndImp
override def toString() : String = {"EPPingPong_a_A.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelPingEnd extends EPPingPong_a_A with event_lang.dsl.ChannelTypeSel
protected  object SelPingEnd extends SelPingEnd {
  override protected def __children: List[EPPingPong_a_A] = List(SndPing,SndEnd)
  override type implT = __SelPingEndImp
  override type implNextT = __SndPingImp
override def toString() : String = {"EPPingPong_a_A.SelPingEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelPingEndImp(c,session)}

protected case class __SelPingEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelPingEnd
}
  private var notUsed = true
def !(m : MESSAGES.PingPong.Ping) : __SndPingBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __SndPingBrnImp(c,session)}
def sndTob_B(m : MESSAGES.PingPong.Ping) : __SndPingBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __SndPingBrnImp(c,session)}

def !(m : MESSAGES.PingPong.End) : __SndEndBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __SndEndBrnImp(c,session)}
def sndTob_B(m : MESSAGES.PingPong.End) : __SndEndBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
 __SndEndBrnImp(c,session)}

}


  trait SndPing extends EPPingPong_a_A with event_lang.dsl.ChannelTypeSnd
  object SndPing extends SndPing {
  override protected def __children: List[EPPingPong_a_A] = List(SndPingBrn)
  override type implT = __SndPingImp
  override type implNextT = __SndPingBrnImp
override def toString() : String = {"EPPingPong_a_A.SndPing"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "Ping" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPingImp(c,session)}

protected case class __SndPingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPing
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.PingPong.Ping) : __SndPingBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndPingBrnImp(c,session)}
def !(m : MESSAGES.PingPong.Ping) : __SndPingBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndPingBrnImp(c,session)}
def snd(m : MESSAGES.PingPong.Ping) : __SndPingBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndPingBrnImp(c,session)}

}


  trait SndPingBrn extends EPPingPong_a_A with event_lang.dsl.ChannelTypeSnd
  object SndPingBrn extends SndPingBrn {
  override protected def __children: List[EPPingPong_a_A] = List(RcvPong)
  override type implT = __SndPingBrnImp
  override type implNextT = __RcvPongImp
override def toString() : String = {"EPPingPong_a_A.SndPingBrn"}
    override def to : RRole = Role("bb",RoleSet("B")) 
   override def l : String = "PingBrn" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPingBrnImp(c,session)}

protected case class __SndPingBrnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPingBrn
}
  private var notUsed = true
def sndTobb_B(m : MESSAGES.PingPong.PingBrn) : __RcvPongImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("bb",RoleSet("B")),m)
__RcvPongImp(c,session)}
def !(m : MESSAGES.PingPong.PingBrn) : __RcvPongImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("bb",RoleSet("B")),m)
__RcvPongImp(c,session)}
def snd(m : MESSAGES.PingPong.PingBrn) : __RcvPongImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("bb",RoleSet("B")),m)
__RcvPongImp(c,session)}

}


  trait RcvPong extends EPPingPong_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvPong extends RcvPong {
  override protected def __children: List[EPPingPong_a_A] = List(T)
  override type implT = __RcvPongImp
  override type implNextT = __TImp
override def toString() : String = {"EPPingPong_a_A.RcvPong"}
  override type msgT = MESSAGES.PingPong.Pong
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "Pong"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPongImp(c,session)}

protected case class __RcvPongImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPong
}
  def rcvFrmb_B : (MESSAGES.PingPong.Pong,__TImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.PingPong.Pong],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.PingPong.Pong,__TImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.PingPong.Pong],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.PingPong.Pong = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.PingPong.Pong]}
def ? : MESSAGES.PingPong.Pong = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.PingPong.Pong]}
def channelCon : __TImp = {__TImp(c,session)}

}


protected  trait T extends EPPingPong_a_A with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPPingPong_a_A] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPingPong_a_A.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait SndEnd extends EPPingPong_a_A with event_lang.dsl.ChannelTypeSnd
  object SndEnd extends SndEnd {
  override protected def __children: List[EPPingPong_a_A] = List(SndEndBrn)
  override type implT = __SndEndImp
  override type implNextT = __SndEndBrnImp
override def toString() : String = {"EPPingPong_a_A.SndEnd"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "End" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndImp(c,session)}

protected case class __SndEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEnd
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.PingPong.End) : __SndEndBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndEndBrnImp(c,session)}
def !(m : MESSAGES.PingPong.End) : __SndEndBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndEndBrnImp(c,session)}
def snd(m : MESSAGES.PingPong.End) : __SndEndBrnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SndEndBrnImp(c,session)}

}


  trait SndEndBrn extends EPPingPong_a_A with event_lang.dsl.ChannelTypeSnd
  object SndEndBrn extends SndEndBrn {
  override protected def __children: List[EPPingPong_a_A] = List(End_a_A_PingPongEnd)
  override type implT = __SndEndBrnImp
  override type implNextT = __End_a_A_PingPongEndImp
override def toString() : String = {"EPPingPong_a_A.SndEndBrn"}
    override def to : RRole = Role("bb",RoleSet("B")) 
   override def l : String = "EndBrn" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndBrnImp(c,session)}

protected case class __SndEndBrnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEndBrn
}
  private var notUsed = true
def sndTobb_B(m : MESSAGES.PingPong.EndBrn) : __End_a_A_PingPongEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("bb",RoleSet("B")),m)
__End_a_A_PingPongEndImp(c,session)}
def !(m : MESSAGES.PingPong.EndBrn) : __End_a_A_PingPongEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("bb",RoleSet("B")),m)
__End_a_A_PingPongEndImp(c,session)}
def snd(m : MESSAGES.PingPong.EndBrn) : __End_a_A_PingPongEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("bb",RoleSet("B")),m)
__End_a_A_PingPongEndImp(c,session)}

}


protected  trait End_a_A_PingPongEnd extends EPPingPong_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_PingPongEnd extends End_a_A_PingPongEnd {
  override protected def __children: List[EPPingPong_a_A] = List()
  override type implT = __End_a_A_PingPongEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPPingPong_a_A.End_a_A_PingPongEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_PingPongEndImp(c,session)}

protected case class __End_a_A_PingPongEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_PingPongEnd
}
  
}



  trait Failed_bb_B extends EPPingPong_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_bb_B extends Failed_bb_B {
  override protected def __children: List[EPPingPong_a_A] = List(SndFailD)
  override type implT = __Failed_bb_BImp
  override type implNextT = __SndFailDImp
override def toString() : String = {"EPPingPong_a_A.Failed_bb_B"}
  override def suspect : Role = Role("bb",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_bb_BImp(c,session)}

protected case class __Failed_bb_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_bb_B
}
  def failed_bb_B(): __SndFailDImp = {__SndFailDImp(c,session)}

}


  trait SndFailD extends EPPingPong_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFailD extends SndFailD {
  override protected def __children: List[EPPingPong_a_A] = List(End_a_A_PingPongFHandling)
  override type implT = __SndFailDImp
  override type implNextT = __End_a_A_PingPongFHandlingImp
override def toString() : String = {"EPPingPong_a_A.SndFailD"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "FailD" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFailDImp(c,session)}

protected case class __SndFailDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFailD
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.PingPong.FailD) : __End_a_A_PingPongFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_PingPongFHandlingImp(c,session)}
def !(m : MESSAGES.PingPong.FailD) : __End_a_A_PingPongFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_PingPongFHandlingImp(c,session)}
def snd(m : MESSAGES.PingPong.FailD) : __End_a_A_PingPongFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_PingPongFHandlingImp(c,session)}

}


protected  trait End_a_A_PingPongFHandling extends EPPingPong_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_PingPongFHandling extends End_a_A_PingPongFHandling {
  override protected def __children: List[EPPingPong_a_A] = List()
  override type implT = __End_a_A_PingPongFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPingPong_a_A.End_a_A_PingPongFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_PingPongFHandlingImp(c,session)}

protected case class __End_a_A_PingPongFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_PingPongFHandling
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
  override protected def __children: List[EPMain_a_A] = List(SndLMain,Failed_b_B)
  override type implT = __HdlImp
  override type implNextT = __SndLMainImp
override def toString() : String = {"EPMain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndLMain extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndLMain extends SndLMain {
  override protected def __children: List[EPMain_a_A] = List(SpawnPingPong)
  override type implT = __SndLMainImp
  override type implNextT = __SpawnPingPongImp
override def toString() : String = {"EPMain_a_A.SndLMain"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "LMain" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndLMainImp(c,session)}

protected case class __SndLMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndLMain
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.LMain) : __SpawnPingPongImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SpawnPingPongImp(c,session)}
def !(m : MESSAGES.Main.LMain) : __SpawnPingPongImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SpawnPingPongImp(c,session)}
def snd(m : MESSAGES.Main.LMain) : __SpawnPingPongImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__SpawnPingPongImp(c,session)}

}


  trait SpawnPingPong extends EPMain_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnPingPong extends SpawnPingPong {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_Main)
  override type implT = __SpawnPingPongImp
  override type implNextT = __End_a_A_MainImp
override def toString() : String = {"EPMain_a_A.SpawnPingPong"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "PingPong" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPingPongImp(c,session)}

protected case class __SpawnPingPongImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPingPong
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
  override protected def __children: List[EPMain_a_A] = List(SndFailB)
  override type implT = __Failed_b_BImp
  override type implNextT = __SndFailBImp
override def toString() : String = {"EPMain_a_A.Failed_b_B"}
  override def suspect : Role = Role("b",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b_BImp(c,session)}

protected case class __Failed_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b_B
}
  def failed_b_B(): __SndFailBImp = {__SndFailBImp(c,session)}

}


  trait SndFailB extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFailB extends SndFailB {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainFHandling)
  override type implT = __SndFailBImp
  override type implNextT = __End_a_A_MainFHandlingImp
override def toString() : String = {"EPMain_a_A.SndFailB"}
    override def to : RRole = RoleSet("B") 
   override def l : String = "FailB" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFailBImp(c,session)}

protected case class __SndFailBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFailB
}
  private var notUsed = true
def sndToB(m : MESSAGES.Main.FailB) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.FailB) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.FailB) : __End_a_A_MainFHandlingImp = {
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
