package event_lang.semantic.testtypes
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object TEST_SPAWN {
object RS {
val B : RoleSet = RoleSet("B")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object GOne {
case class MSGOne() extends MSG {
   override def l : String = "MSGOne"
}

case class MSGAtoB() extends MSG {
   override def l : String = "MSGAtoB"
}

case class MSGTwo() extends MSG {
   override def l : String = "MSGTwo"
}

case class FailGOne() extends MSG {
   override def l : String = "FailGOne"
}

}

object Main {
case class MSGMCast() extends MSG {
   override def l : String = "MSGMCast"
}

case class FailMain() extends MSG {
   override def l : String = "FailMain"
}

}

}

object PROTOCOLS {
object Main {
val b_B = Role("b",RoleSet("B"))
val B = RoleSet("B")
val a_A = Role("a",RoleSet("A"))
}

object GOne {
val bb_B = Role("bb",RoleSet("B"))
val a_A = Role("a",RoleSet("A"))
val b_B = Role("b",RoleSet("B"))
}

}

object B {
val subs : Seq[dsl.ChannelTypeSubS] = List(GOne_bb_B.EPGOne_bb_B,GOne_b_B.EPGOne_b_B,Main_b_B.EPMain_b_B,Main_B.EPMain_B)
trait __EPType_B extends AbstractChannelType {

}

trait EPType_B[T<: TState] extends AbstractEndPoint[__EPType_B,T] {
override val roleSet: RoleSet = RoleSet("B")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(GOne_bb_B.EPGOne_bb_B,GOne_b_B.EPGOne_b_B,Main_b_B.EPMain_b_B,Main_B.EPMain_B)

}

object GOne_bb_B{
trait EPGOne_bb_B extends __EPType_B

object EPGOne_bb_B extends EPGOne_bb_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPGOne_bb_B] = List(Hdl)
  override type implT = __EPGOne_bb_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPGOne_bb_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("bb",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "GOne"
}

protected case class __EPGOne_bb_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPGOne_bb_B
}
  
}


protected  trait Hdl extends EPGOne_bb_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPGOne_bb_B] = List(SndMSGOne,End_bb_B_GOneFHandling)
  override type implT = __HdlImp
  override type implNextT = __SndMSGOneImp
override def toString() : String = {"EPGOne_bb_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndMSGOne extends EPGOne_bb_B with event_lang.dsl.ChannelTypeSnd
  object SndMSGOne extends SndMSGOne {
  override protected def __children: List[EPGOne_bb_B] = List(End_bb_B_GOne)
  override type implT = __SndMSGOneImp
  override type implNextT = __End_bb_B_GOneImp
override def toString() : String = {"EPGOne_bb_B.SndMSGOne"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "MSGOne" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMSGOneImp(c,session)}

protected case class __SndMSGOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndMSGOne
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.GOne.MSGOne) : __End_bb_B_GOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_bb_B_GOneImp(c,session)}
def !(m : MESSAGES.GOne.MSGOne) : __End_bb_B_GOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_bb_B_GOneImp(c,session)}
def snd(m : MESSAGES.GOne.MSGOne) : __End_bb_B_GOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_bb_B_GOneImp(c,session)}

}


protected  trait End_bb_B_GOne extends EPGOne_bb_B with event_lang.dsl.ChannelTypeEnd
protected  object End_bb_B_GOne extends End_bb_B_GOne {
  override protected def __children: List[EPGOne_bb_B] = List()
  override type implT = __End_bb_B_GOneImp
  override type implNextT = Nothing
override def toString() : String = {"EPGOne_bb_B.End_bb_B_GOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_bb_B_GOneImp(c,session)}

protected case class __End_bb_B_GOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_bb_B_GOne
}
  
}



protected  trait End_bb_B_GOneFHandling extends EPGOne_bb_B with event_lang.dsl.ChannelTypeEnd
protected  object End_bb_B_GOneFHandling extends End_bb_B_GOneFHandling {
  override protected def __children: List[EPGOne_bb_B] = List()
  override type implT = __End_bb_B_GOneFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPGOne_bb_B.End_bb_B_GOneFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_bb_B_GOneFHandlingImp(c,session)}

protected case class __End_bb_B_GOneFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_bb_B_GOneFHandling
}
  
}


}

object GOne_b_B{
trait EPGOne_b_B extends __EPType_B

object EPGOne_b_B extends EPGOne_b_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPGOne_b_B] = List(Hdl)
  override type implT = __EPGOne_b_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPGOne_b_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("b",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "GOne"
}

protected case class __EPGOne_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPGOne_b_B
}
  
}


protected  trait Hdl extends EPGOne_b_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPGOne_b_B] = List(RcvMSGAtoB,RcvFailGOne)
  override type implT = __HdlImp
  override type implNextT = __RcvMSGAtoBImp
override def toString() : String = {"EPGOne_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvMSGAtoB extends EPGOne_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvMSGAtoB extends RcvMSGAtoB {
  override protected def __children: List[EPGOne_b_B] = List(SndMSGTwo)
  override type implT = __RcvMSGAtoBImp
  override type implNextT = __SndMSGTwoImp
override def toString() : String = {"EPGOne_b_B.RcvMSGAtoB"}
  override type msgT = MESSAGES.GOne.MSGAtoB
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "MSGAtoB"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMSGAtoBImp(c,session)}

protected case class __RcvMSGAtoBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMSGAtoB
}
  def rcvFrma_A : (MESSAGES.GOne.MSGAtoB,__SndMSGTwoImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.GOne.MSGAtoB],__SndMSGTwoImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GOne.MSGAtoB,__SndMSGTwoImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.GOne.MSGAtoB],__SndMSGTwoImp(c,session))) 
}
def rcvMSG : MESSAGES.GOne.MSGAtoB = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.GOne.MSGAtoB]}
def ? : MESSAGES.GOne.MSGAtoB = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.GOne.MSGAtoB]}
def channelCon : __SndMSGTwoImp = {__SndMSGTwoImp(c,session)}

}


  trait SndMSGTwo extends EPGOne_b_B with event_lang.dsl.ChannelTypeSnd
  object SndMSGTwo extends SndMSGTwo {
  override protected def __children: List[EPGOne_b_B] = List(End_b_B_GOne)
  override type implT = __SndMSGTwoImp
  override type implNextT = __End_b_B_GOneImp
override def toString() : String = {"EPGOne_b_B.SndMSGTwo"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "MSGTwo" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMSGTwoImp(c,session)}

protected case class __SndMSGTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndMSGTwo
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.GOne.MSGTwo) : __End_b_B_GOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_GOneImp(c,session)}
def !(m : MESSAGES.GOne.MSGTwo) : __End_b_B_GOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_GOneImp(c,session)}
def snd(m : MESSAGES.GOne.MSGTwo) : __End_b_B_GOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_GOneImp(c,session)}

}


protected  trait End_b_B_GOne extends EPGOne_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_GOne extends End_b_B_GOne {
  override protected def __children: List[EPGOne_b_B] = List()
  override type implT = __End_b_B_GOneImp
  override type implNextT = Nothing
override def toString() : String = {"EPGOne_b_B.End_b_B_GOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_GOneImp(c,session)}

protected case class __End_b_B_GOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_GOne
}
  
}



  trait RcvFailGOne extends EPGOne_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvFailGOne extends RcvFailGOne {
  override protected def __children: List[EPGOne_b_B] = List(End_b_B_GOneFHandling)
  override type implT = __RcvFailGOneImp
  override type implNextT = __End_b_B_GOneFHandlingImp
override def toString() : String = {"EPGOne_b_B.RcvFailGOne"}
  override type msgT = MESSAGES.GOne.FailGOne
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FailGOne"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFailGOneImp(c,session)}

protected case class __RcvFailGOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFailGOne
}
  def rcvFrma_A : (MESSAGES.GOne.FailGOne,__End_b_B_GOneFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.GOne.FailGOne],__End_b_B_GOneFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GOne.FailGOne,__End_b_B_GOneFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.GOne.FailGOne],__End_b_B_GOneFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.GOne.FailGOne = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.GOne.FailGOne]}
def ? : MESSAGES.GOne.FailGOne = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.GOne.FailGOne]}
def channelCon : __End_b_B_GOneFHandlingImp = {__End_b_B_GOneFHandlingImp(c,session)}

}


protected  trait End_b_B_GOneFHandling extends EPGOne_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_GOneFHandling extends End_b_B_GOneFHandling {
  override protected def __children: List[EPGOne_b_B] = List()
  override type implT = __End_b_B_GOneFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPGOne_b_B.End_b_B_GOneFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_GOneFHandlingImp(c,session)}

protected case class __End_b_B_GOneFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_GOneFHandling
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
  override protected def __children: List[EPMain_b_B] = List(RcvMSGMCast,End_b_B_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvMSGMCastImp
override def toString() : String = {"EPMain_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvMSGMCast extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvMSGMCast extends RcvMSGMCast {
  override protected def __children: List[EPMain_b_B] = List(SpawnGOne)
  override type implT = __RcvMSGMCastImp
  override type implNextT = __SpawnGOneImp
override def toString() : String = {"EPMain_b_B.RcvMSGMCast"}
  override type msgT = MESSAGES.Main.MSGMCast
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "MSGMCast"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMSGMCastImp(c,session)}

protected case class __RcvMSGMCastImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMSGMCast
}
  def rcvFrma_A : (MESSAGES.Main.MSGMCast,__SpawnGOneImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.MSGMCast],__SpawnGOneImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.MSGMCast,__SpawnGOneImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.MSGMCast],__SpawnGOneImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.MSGMCast = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.MSGMCast]}
def ? : MESSAGES.Main.MSGMCast = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.MSGMCast]}
def channelCon : __SpawnGOneImp = {__SpawnGOneImp(c,session)}

}


  trait SpawnGOne extends EPMain_b_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnGOne extends SpawnGOne {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_Main)
  override type implT = __SpawnGOneImp
  override type implNextT = __End_b_B_MainImp
override def toString() : String = {"EPMain_b_B.SpawnGOne"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "GOne" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnGOneImp(c,session)}

protected case class __SpawnGOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnGOne
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
  override protected def __children: List[EPMain_B] = List(RcvMSGMCast,RcvFailMain)
  override type implT = __HdlImp
  override type implNextT = __RcvMSGMCastImp
override def toString() : String = {"EPMain_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvMSGMCast extends EPMain_B with event_lang.dsl.ChannelTypeRcv
  object RcvMSGMCast extends RcvMSGMCast {
  override protected def __children: List[EPMain_B] = List(SpawnGOne)
  override type implT = __RcvMSGMCastImp
  override type implNextT = __SpawnGOneImp
override def toString() : String = {"EPMain_B.RcvMSGMCast"}
  override type msgT = MESSAGES.Main.MSGMCast
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "MSGMCast"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMSGMCastImp(c,session)}

protected case class __RcvMSGMCastImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMSGMCast
}
  def rcvFrma_A : (MESSAGES.Main.MSGMCast,__SpawnGOneImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.MSGMCast],__SpawnGOneImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.MSGMCast,__SpawnGOneImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.MSGMCast],__SpawnGOneImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.MSGMCast = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.MSGMCast]}
def ? : MESSAGES.Main.MSGMCast = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.MSGMCast]}
def channelCon : __SpawnGOneImp = {__SpawnGOneImp(c,session)}

}


  trait SpawnGOne extends EPMain_B with event_lang.dsl.ChannelTypeSpawn
  object SpawnGOne extends SpawnGOne {
  override protected def __children: List[EPMain_B] = List(End_B_Main)
  override type implT = __SpawnGOneImp
  override type implNextT = __End_B_MainImp
override def toString() : String = {"EPMain_B.SpawnGOne"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "GOne" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnGOneImp(c,session)}

protected case class __SpawnGOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnGOne
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



  trait RcvFailMain extends EPMain_B with event_lang.dsl.ChannelTypeRcv
  object RcvFailMain extends RcvFailMain {
  override protected def __children: List[EPMain_B] = List(End_B_MainFHandling)
  override type implT = __RcvFailMainImp
  override type implNextT = __End_B_MainFHandlingImp
override def toString() : String = {"EPMain_B.RcvFailMain"}
  override type msgT = MESSAGES.Main.FailMain
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FailMain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFailMainImp(c,session)}

protected case class __RcvFailMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFailMain
}
  def rcvFrma_A : (MESSAGES.Main.FailMain,__End_B_MainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FailMain],__End_B_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FailMain,__End_B_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FailMain],__End_B_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FailMain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FailMain]}
def ? : MESSAGES.Main.FailMain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FailMain]}
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
val subs : Seq[dsl.ChannelTypeSubS] = List(GOne_a_A.EPGOne_a_A,Main_a_A.EPMain_a_A)
trait __EPType_A extends AbstractChannelType {

}

trait EPType_A[T<: TState] extends AbstractEndPoint[__EPType_A,T] {
override val roleSet: RoleSet = RoleSet("A")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(GOne_a_A.EPGOne_a_A,Main_a_A.EPMain_a_A)

}

object GOne_a_A{
trait EPGOne_a_A extends __EPType_A

object EPGOne_a_A extends EPGOne_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPGOne_a_A] = List(Hdl)
  override type implT = __EPGOne_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPGOne_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("bb",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "GOne"
}

protected case class __EPGOne_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPGOne_a_A
}
  
}


protected  trait Hdl extends EPGOne_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPGOne_a_A] = List(SndMSGAtoB,Failed_bb_B)
  override type implT = __HdlImp
  override type implNextT = __SndMSGAtoBImp
override def toString() : String = {"EPGOne_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndMSGAtoB extends EPGOne_a_A with event_lang.dsl.ChannelTypeSnd
  object SndMSGAtoB extends SndMSGAtoB {
  override protected def __children: List[EPGOne_a_A] = List(RcvMSGOne)
  override type implT = __SndMSGAtoBImp
  override type implNextT = __RcvMSGOneImp
override def toString() : String = {"EPGOne_a_A.SndMSGAtoB"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "MSGAtoB" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMSGAtoBImp(c,session)}

protected case class __SndMSGAtoBImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndMSGAtoB
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.GOne.MSGAtoB) : __RcvMSGOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvMSGOneImp(c,session)}
def !(m : MESSAGES.GOne.MSGAtoB) : __RcvMSGOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvMSGOneImp(c,session)}
def snd(m : MESSAGES.GOne.MSGAtoB) : __RcvMSGOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvMSGOneImp(c,session)}

}


  trait RcvMSGOne extends EPGOne_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvMSGOne extends RcvMSGOne {
  override protected def __children: List[EPGOne_a_A] = List(RcvMSGTwo)
  override type implT = __RcvMSGOneImp
  override type implNextT = __RcvMSGTwoImp
override def toString() : String = {"EPGOne_a_A.RcvMSGOne"}
  override type msgT = MESSAGES.GOne.MSGOne
   override def frm : Role = Role("bb",RoleSet("B")) 
   override def l : String = "MSGOne"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMSGOneImp(c,session)}

protected case class __RcvMSGOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMSGOne
}
  def rcvFrmbb_B : (MESSAGES.GOne.MSGOne,__RcvMSGTwoImp) = {(c.rcv(Role("bb",RoleSet("B"))).asInstanceOf[MESSAGES.GOne.MSGOne],__RcvMSGTwoImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GOne.MSGOne,__RcvMSGTwoImp),T]) : T = {
  f((c.rcv(Role("bb",RoleSet("B"))).asInstanceOf[MESSAGES.GOne.MSGOne],__RcvMSGTwoImp(c,session))) 
}
def rcvMSG : MESSAGES.GOne.MSGOne = {c.rcv(Role("bb",RoleSet("B"))).asInstanceOf[MESSAGES.GOne.MSGOne]}
def ? : MESSAGES.GOne.MSGOne = {c.rcv(Role("bb",RoleSet("B"))).asInstanceOf[MESSAGES.GOne.MSGOne]}
def channelCon : __RcvMSGTwoImp = {__RcvMSGTwoImp(c,session)}

}


  trait RcvMSGTwo extends EPGOne_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvMSGTwo extends RcvMSGTwo {
  override protected def __children: List[EPGOne_a_A] = List(End_a_A_GOne)
  override type implT = __RcvMSGTwoImp
  override type implNextT = __End_a_A_GOneImp
override def toString() : String = {"EPGOne_a_A.RcvMSGTwo"}
  override type msgT = MESSAGES.GOne.MSGTwo
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "MSGTwo"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvMSGTwoImp(c,session)}

protected case class __RcvMSGTwoImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvMSGTwo
}
  def rcvFrmb_B : (MESSAGES.GOne.MSGTwo,__End_a_A_GOneImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.GOne.MSGTwo],__End_a_A_GOneImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.GOne.MSGTwo,__End_a_A_GOneImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.GOne.MSGTwo],__End_a_A_GOneImp(c,session))) 
}
def rcvMSG : MESSAGES.GOne.MSGTwo = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.GOne.MSGTwo]}
def ? : MESSAGES.GOne.MSGTwo = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.GOne.MSGTwo]}
def channelCon : __End_a_A_GOneImp = {__End_a_A_GOneImp(c,session)}

}


protected  trait End_a_A_GOne extends EPGOne_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_GOne extends End_a_A_GOne {
  override protected def __children: List[EPGOne_a_A] = List()
  override type implT = __End_a_A_GOneImp
  override type implNextT = Nothing
override def toString() : String = {"EPGOne_a_A.End_a_A_GOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_GOneImp(c,session)}

protected case class __End_a_A_GOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_GOne
}
  
}



  trait Failed_bb_B extends EPGOne_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_bb_B extends Failed_bb_B {
  override protected def __children: List[EPGOne_a_A] = List(SndFailGOne)
  override type implT = __Failed_bb_BImp
  override type implNextT = __SndFailGOneImp
override def toString() : String = {"EPGOne_a_A.Failed_bb_B"}
  override def suspect : Role = Role("bb",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_bb_BImp(c,session)}

protected case class __Failed_bb_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_bb_B
}
  def failed_bb_B(): __SndFailGOneImp = {__SndFailGOneImp(c,session)}

}


  trait SndFailGOne extends EPGOne_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFailGOne extends SndFailGOne {
  override protected def __children: List[EPGOne_a_A] = List(End_a_A_GOneFHandling)
  override type implT = __SndFailGOneImp
  override type implNextT = __End_a_A_GOneFHandlingImp
override def toString() : String = {"EPGOne_a_A.SndFailGOne"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "FailGOne" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFailGOneImp(c,session)}

protected case class __SndFailGOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFailGOne
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.GOne.FailGOne) : __End_a_A_GOneFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_GOneFHandlingImp(c,session)}
def !(m : MESSAGES.GOne.FailGOne) : __End_a_A_GOneFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_GOneFHandlingImp(c,session)}
def snd(m : MESSAGES.GOne.FailGOne) : __End_a_A_GOneFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__End_a_A_GOneFHandlingImp(c,session)}

}


protected  trait End_a_A_GOneFHandling extends EPGOne_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_GOneFHandling extends End_a_A_GOneFHandling {
  override protected def __children: List[EPGOne_a_A] = List()
  override type implT = __End_a_A_GOneFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPGOne_a_A.End_a_A_GOneFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_GOneFHandlingImp(c,session)}

protected case class __End_a_A_GOneFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_GOneFHandling
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
  override protected def __children: List[EPMain_a_A] = List(SndMSGMCast,Failed_b_B)
  override type implT = __HdlImp
  override type implNextT = __SndMSGMCastImp
override def toString() : String = {"EPMain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndMSGMCast extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndMSGMCast extends SndMSGMCast {
  override protected def __children: List[EPMain_a_A] = List(SpawnGOne)
  override type implT = __SndMSGMCastImp
  override type implNextT = __SpawnGOneImp
override def toString() : String = {"EPMain_a_A.SndMSGMCast"}
    override def to : RRole = RoleSet("B") 
   override def l : String = "MSGMCast" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndMSGMCastImp(c,session)}

protected case class __SndMSGMCastImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndMSGMCast
}
  private var notUsed = true
def sndToB(m : MESSAGES.Main.MSGMCast) : __SpawnGOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__SpawnGOneImp(c,session)}
def !(m : MESSAGES.Main.MSGMCast) : __SpawnGOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__SpawnGOneImp(c,session)}
def snd(m : MESSAGES.Main.MSGMCast) : __SpawnGOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__SpawnGOneImp(c,session)}

}


  trait SpawnGOne extends EPMain_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnGOne extends SpawnGOne {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_Main)
  override type implT = __SpawnGOneImp
  override type implNextT = __End_a_A_MainImp
override def toString() : String = {"EPMain_a_A.SpawnGOne"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("B") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "GOne" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnGOneImp(c,session)}

protected case class __SpawnGOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnGOne
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
  override protected def __children: List[EPMain_a_A] = List(SndFailMain)
  override type implT = __Failed_b_BImp
  override type implNextT = __SndFailMainImp
override def toString() : String = {"EPMain_a_A.Failed_b_B"}
  override def suspect : Role = Role("b",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b_BImp(c,session)}

protected case class __Failed_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b_B
}
  def failed_b_B(): __SndFailMainImp = {__SndFailMainImp(c,session)}

}


  trait SndFailMain extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFailMain extends SndFailMain {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainFHandling)
  override type implT = __SndFailMainImp
  override type implNextT = __End_a_A_MainFHandlingImp
override def toString() : String = {"EPMain_a_A.SndFailMain"}
    override def to : RRole = RoleSet("B") 
   override def l : String = "FailMain" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFailMainImp(c,session)}

protected case class __SndFailMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFailMain
}
  private var notUsed = true
def sndToB(m : MESSAGES.Main.FailMain) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.FailMain) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("B"),m)
__End_a_A_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.FailMain) : __End_a_A_MainFHandlingImp = {
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
