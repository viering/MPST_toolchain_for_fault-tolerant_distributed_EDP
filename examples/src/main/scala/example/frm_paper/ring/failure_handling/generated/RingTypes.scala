package example.frm_paper.ring.failure_handling.generated
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object RingABC {
object RS {
val C : RoleSet = RoleSet("C")
val B : RoleSet = RoleSet("B")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object Main {
case class L6() extends MSG {
   override def l : String = "L6"
}

case class L1() extends MSG {
   override def l : String = "L1"
}

}

object g1 {
case class L2() extends MSG {
   override def l : String = "L2"
}

case class L3() extends MSG {
   override def l : String = "L3"
}

case class L7() extends MSG {
   override def l : String = "L7"
}

case class L8() extends MSG {
   override def l : String = "L8"
}

}

object g2 {
case class L4() extends MSG {
   override def l : String = "L4"
}

case class L5() extends MSG {
   override def l : String = "L5"
}

}

}

object PROTOCOLS {
object g2 {
val c_C = Role("c",RoleSet("C"))
val a_A = Role("a",RoleSet("A"))
}

object Main {
val b_B = Role("b",RoleSet("B"))
val C = RoleSet("C")
val a_A = Role("a",RoleSet("A"))
}

object g1 {
val c_C = Role("c",RoleSet("C"))
val a_A = Role("a",RoleSet("A"))
val b_B = Role("b",RoleSet("B"))
}

}

object C {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_C.EPMain_C,g1_c_C.EPg1_c_C,g2_c_C.EPg2_c_C)
trait __EPType_C extends AbstractChannelType {

}

trait EPType_C[T<: TState] extends AbstractEndPoint[__EPType_C,T] {
override val roleSet: RoleSet = RoleSet("C")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_C.EPMain_C,g1_c_C.EPg1_c_C,g2_c_C.EPg2_c_C)

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
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
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
  override protected def __children: List[EPMain_C] = List(Spawng1,RcvL6)
  override type implT = __HdlImp
  override type implNextT = __Spawng1Imp
override def toString() : String = {"EPMain_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait Spawng1 extends EPMain_C with event_lang.dsl.ChannelTypeSpawn
  object Spawng1 extends Spawng1 {
  override protected def __children: List[EPMain_C] = List(End_C_Main)
  override type implT = __Spawng1Imp
  override type implNextT = __End_C_MainImp
override def toString() : String = {"EPMain_C.Spawng1"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "g1" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Spawng1Imp(c,session)}

protected case class __Spawng1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Spawng1
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



  trait RcvL6 extends EPMain_C with event_lang.dsl.ChannelTypeRcv
  object RcvL6 extends RcvL6 {
  override protected def __children: List[EPMain_C] = List(Spawng2)
  override type implT = __RcvL6Imp
  override type implNextT = __Spawng2Imp
override def toString() : String = {"EPMain_C.RcvL6"}
  override type msgT = MESSAGES.Main.L6
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "L6"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvL6Imp(c,session)}

protected case class __RcvL6Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvL6
}
  def rcvFrma_A : (MESSAGES.Main.L6,__Spawng2Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.L6],__Spawng2Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.L6,__Spawng2Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.L6],__Spawng2Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.L6 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.L6]}
def ? : MESSAGES.Main.L6 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.L6]}
def channelCon : __Spawng2Imp = {__Spawng2Imp(c,session)}

}


  trait Spawng2 extends EPMain_C with event_lang.dsl.ChannelTypeSpawn
  object Spawng2 extends Spawng2 {
  override protected def __children: List[EPMain_C] = List(End_C_MainFHandling)
  override type implT = __Spawng2Imp
  override type implNextT = __End_C_MainFHandlingImp
override def toString() : String = {"EPMain_C.Spawng2"}
    override def y: List[Role] = List(Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "g2" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Spawng2Imp(c,session)}

protected case class __Spawng2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Spawng2
}
  // SPAWN is handled internally -- i.e. no use code here
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

object g1_c_C{
trait EPg1_c_C extends __EPType_C

object EPg1_c_C extends EPg1_c_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPg1_c_C] = List(Hdl)
  override type implT = __EPg1_c_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPg1_c_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("c",RoleSet("C")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "g1"
}

protected case class __EPg1_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPg1_c_C
}
  
}


protected  trait Hdl extends EPg1_c_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPg1_c_C] = List(RcvL2,End_c_C_g1FHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvL2Imp
override def toString() : String = {"EPg1_c_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvL2 extends EPg1_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvL2 extends RcvL2 {
  override protected def __children: List[EPg1_c_C] = List(SndL3)
  override type implT = __RcvL2Imp
  override type implNextT = __SndL3Imp
override def toString() : String = {"EPg1_c_C.RcvL2"}
  override type msgT = MESSAGES.g1.L2
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "L2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvL2Imp(c,session)}

protected case class __RcvL2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvL2
}
  def rcvFrmb_B : (MESSAGES.g1.L2,__SndL3Imp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.g1.L2],__SndL3Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.g1.L2,__SndL3Imp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.g1.L2],__SndL3Imp(c,session))) 
}
def rcvMSG : MESSAGES.g1.L2 = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.g1.L2]}
def ? : MESSAGES.g1.L2 = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.g1.L2]}
def channelCon : __SndL3Imp = {__SndL3Imp(c,session)}

}


  trait SndL3 extends EPg1_c_C with event_lang.dsl.ChannelTypeSnd
  object SndL3 extends SndL3 {
  override protected def __children: List[EPg1_c_C] = List(End_c_C_g1)
  override type implT = __SndL3Imp
  override type implNextT = __End_c_C_g1Imp
override def toString() : String = {"EPg1_c_C.SndL3"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "L3" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndL3Imp(c,session)}

protected case class __SndL3Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndL3
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.g1.L3) : __End_c_C_g1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_c_C_g1Imp(c,session)}
def !(m : MESSAGES.g1.L3) : __End_c_C_g1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_c_C_g1Imp(c,session)}
def snd(m : MESSAGES.g1.L3) : __End_c_C_g1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_c_C_g1Imp(c,session)}

}


protected  trait End_c_C_g1 extends EPg1_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_g1 extends End_c_C_g1 {
  override protected def __children: List[EPg1_c_C] = List()
  override type implT = __End_c_C_g1Imp
  override type implNextT = Nothing
override def toString() : String = {"EPg1_c_C.End_c_C_g1"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_g1Imp(c,session)}

protected case class __End_c_C_g1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_g1
}
  
}



protected  trait End_c_C_g1FHandling extends EPg1_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_g1FHandling extends End_c_C_g1FHandling {
  override protected def __children: List[EPg1_c_C] = List()
  override type implT = __End_c_C_g1FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPg1_c_C.End_c_C_g1FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_g1FHandlingImp(c,session)}

protected case class __End_c_C_g1FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_g1FHandling
}
  
}


}

object g2_c_C{
trait EPg2_c_C extends __EPType_C

object EPg2_c_C extends EPg2_c_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPg2_c_C] = List(Hdl)
  override type implT = __EPg2_c_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPg2_c_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("c",RoleSet("C")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "g2"
}

protected case class __EPg2_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPg2_c_C
}
  
}


protected  trait Hdl extends EPg2_c_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPg2_c_C] = List(RcvL4,End_c_C_g2FHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvL4Imp
override def toString() : String = {"EPg2_c_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvL4 extends EPg2_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvL4 extends RcvL4 {
  override protected def __children: List[EPg2_c_C] = List(SndL5)
  override type implT = __RcvL4Imp
  override type implNextT = __SndL5Imp
override def toString() : String = {"EPg2_c_C.RcvL4"}
  override type msgT = MESSAGES.g2.L4
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "L4"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvL4Imp(c,session)}

protected case class __RcvL4Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvL4
}
  def rcvFrma_A : (MESSAGES.g2.L4,__SndL5Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.g2.L4],__SndL5Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.g2.L4,__SndL5Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.g2.L4],__SndL5Imp(c,session))) 
}
def rcvMSG : MESSAGES.g2.L4 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.g2.L4]}
def ? : MESSAGES.g2.L4 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.g2.L4]}
def channelCon : __SndL5Imp = {__SndL5Imp(c,session)}

}


  trait SndL5 extends EPg2_c_C with event_lang.dsl.ChannelTypeSnd
  object SndL5 extends SndL5 {
  override protected def __children: List[EPg2_c_C] = List(End_c_C_g2)
  override type implT = __SndL5Imp
  override type implNextT = __End_c_C_g2Imp
override def toString() : String = {"EPg2_c_C.SndL5"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "L5" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndL5Imp(c,session)}

protected case class __SndL5Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndL5
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.g2.L5) : __End_c_C_g2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_c_C_g2Imp(c,session)}
def !(m : MESSAGES.g2.L5) : __End_c_C_g2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_c_C_g2Imp(c,session)}
def snd(m : MESSAGES.g2.L5) : __End_c_C_g2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_c_C_g2Imp(c,session)}

}


protected  trait End_c_C_g2 extends EPg2_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_g2 extends End_c_C_g2 {
  override protected def __children: List[EPg2_c_C] = List()
  override type implT = __End_c_C_g2Imp
  override type implNextT = Nothing
override def toString() : String = {"EPg2_c_C.End_c_C_g2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_g2Imp(c,session)}

protected case class __End_c_C_g2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_g2
}
  
}



protected  trait End_c_C_g2FHandling extends EPg2_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_g2FHandling extends End_c_C_g2FHandling {
  override protected def __children: List[EPg2_c_C] = List()
  override type implT = __End_c_C_g2FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPg2_c_C.End_c_C_g2FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_g2FHandlingImp(c,session)}

protected case class __End_c_C_g2FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_g2FHandling
}
  
}


}

}

object B {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_b_B.EPMain_b_B,g1_b_B.EPg1_b_B)
trait __EPType_B extends AbstractChannelType {

}

trait EPType_B[T<: TState] extends AbstractEndPoint[__EPType_B,T] {
override val roleSet: RoleSet = RoleSet("B")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_b_B.EPMain_b_B,g1_b_B.EPg1_b_B)

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
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
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
  override protected def __children: List[EPMain_b_B] = List(RcvL1,End_b_B_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvL1Imp
override def toString() : String = {"EPMain_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvL1 extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvL1 extends RcvL1 {
  override protected def __children: List[EPMain_b_B] = List(Spawng1)
  override type implT = __RcvL1Imp
  override type implNextT = __Spawng1Imp
override def toString() : String = {"EPMain_b_B.RcvL1"}
  override type msgT = MESSAGES.Main.L1
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "L1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvL1Imp(c,session)}

protected case class __RcvL1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvL1
}
  def rcvFrma_A : (MESSAGES.Main.L1,__Spawng1Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.L1],__Spawng1Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.L1,__Spawng1Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.L1],__Spawng1Imp(c,session))) 
}
def rcvMSG : MESSAGES.Main.L1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.L1]}
def ? : MESSAGES.Main.L1 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.L1]}
def channelCon : __Spawng1Imp = {__Spawng1Imp(c,session)}

}


  trait Spawng1 extends EPMain_b_B with event_lang.dsl.ChannelTypeSpawn
  object Spawng1 extends Spawng1 {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_Main)
  override type implT = __Spawng1Imp
  override type implNextT = __End_b_B_MainImp
override def toString() : String = {"EPMain_b_B.Spawng1"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "g1" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Spawng1Imp(c,session)}

protected case class __Spawng1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Spawng1
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

object g1_b_B{
trait EPg1_b_B extends __EPType_B

object EPg1_b_B extends EPg1_b_B with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPg1_b_B] = List(Hdl)
  override type implT = __EPg1_b_BImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPg1_b_BImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("b",RoleSet("B")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "g1"
}

protected case class __EPg1_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPg1_b_B
}
  
}


protected  trait Hdl extends EPg1_b_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPg1_b_B] = List(SndL2,RcvL7)
  override type implT = __HdlImp
  override type implNextT = __SndL2Imp
override def toString() : String = {"EPg1_b_B.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndL2 extends EPg1_b_B with event_lang.dsl.ChannelTypeSnd
  object SndL2 extends SndL2 {
  override protected def __children: List[EPg1_b_B] = List(End_b_B_g1)
  override type implT = __SndL2Imp
  override type implNextT = __End_b_B_g1Imp
override def toString() : String = {"EPg1_b_B.SndL2"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "L2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndL2Imp(c,session)}

protected case class __SndL2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndL2
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.g1.L2) : __End_b_B_g1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_b_B_g1Imp(c,session)}
def !(m : MESSAGES.g1.L2) : __End_b_B_g1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_b_B_g1Imp(c,session)}
def snd(m : MESSAGES.g1.L2) : __End_b_B_g1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_b_B_g1Imp(c,session)}

}


protected  trait End_b_B_g1 extends EPg1_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_g1 extends End_b_B_g1 {
  override protected def __children: List[EPg1_b_B] = List()
  override type implT = __End_b_B_g1Imp
  override type implNextT = Nothing
override def toString() : String = {"EPg1_b_B.End_b_B_g1"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_g1Imp(c,session)}

protected case class __End_b_B_g1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_g1
}
  
}



  trait RcvL7 extends EPg1_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvL7 extends RcvL7 {
  override protected def __children: List[EPg1_b_B] = List(SndL8)
  override type implT = __RcvL7Imp
  override type implNextT = __SndL8Imp
override def toString() : String = {"EPg1_b_B.RcvL7"}
  override type msgT = MESSAGES.g1.L7
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "L7"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvL7Imp(c,session)}

protected case class __RcvL7Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvL7
}
  def rcvFrma_A : (MESSAGES.g1.L7,__SndL8Imp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.g1.L7],__SndL8Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.g1.L7,__SndL8Imp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.g1.L7],__SndL8Imp(c,session))) 
}
def rcvMSG : MESSAGES.g1.L7 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.g1.L7]}
def ? : MESSAGES.g1.L7 = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.g1.L7]}
def channelCon : __SndL8Imp = {__SndL8Imp(c,session)}

}


  trait SndL8 extends EPg1_b_B with event_lang.dsl.ChannelTypeSnd
  object SndL8 extends SndL8 {
  override protected def __children: List[EPg1_b_B] = List(End_b_B_g1FHandling)
  override type implT = __SndL8Imp
  override type implNextT = __End_b_B_g1FHandlingImp
override def toString() : String = {"EPg1_b_B.SndL8"}
    override def to : RRole = Role("a",RoleSet("A")) 
   override def l : String = "L8" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndL8Imp(c,session)}

protected case class __SndL8Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndL8
}
  private var notUsed = true
def sndToa_A(m : MESSAGES.g1.L8) : __End_b_B_g1FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_g1FHandlingImp(c,session)}
def !(m : MESSAGES.g1.L8) : __End_b_B_g1FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_g1FHandlingImp(c,session)}
def snd(m : MESSAGES.g1.L8) : __End_b_B_g1FHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("a",RoleSet("A")),m)
__End_b_B_g1FHandlingImp(c,session)}

}


protected  trait End_b_B_g1FHandling extends EPg1_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_g1FHandling extends End_b_B_g1FHandling {
  override protected def __children: List[EPg1_b_B] = List()
  override type implT = __End_b_B_g1FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPg1_b_B.End_b_B_g1FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_g1FHandlingImp(c,session)}

protected case class __End_b_B_g1FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_g1FHandling
}
  
}


}

}

object A {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_a_A.EPMain_a_A,g1_a_A.EPg1_a_A,g2_a_A.EPg2_a_A)
trait __EPType_A extends AbstractChannelType {

}

trait EPType_A[T<: TState] extends AbstractEndPoint[__EPType_A,T] {
override val roleSet: RoleSet = RoleSet("A")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_a_A.EPMain_a_A,g1_a_A.EPg1_a_A,g2_a_A.EPg2_a_A)

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
  override def argsRs: List[RoleSet] = List(RoleSet("C")) 
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
  override protected def __children: List[EPMain_a_A] = List(SndL1,Failed_b_B)
  override type implT = __HdlImp
  override type implNextT = __SndL1Imp
override def toString() : String = {"EPMain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndL1 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndL1 extends SndL1 {
  override protected def __children: List[EPMain_a_A] = List(Spawng1)
  override type implT = __SndL1Imp
  override type implNextT = __Spawng1Imp
override def toString() : String = {"EPMain_a_A.SndL1"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "L1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndL1Imp(c,session)}

protected case class __SndL1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndL1
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.Main.L1) : __Spawng1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__Spawng1Imp(c,session)}
def !(m : MESSAGES.Main.L1) : __Spawng1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__Spawng1Imp(c,session)}
def snd(m : MESSAGES.Main.L1) : __Spawng1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__Spawng1Imp(c,session)}

}


  trait Spawng1 extends EPMain_a_A with event_lang.dsl.ChannelTypeSpawn
  object Spawng1 extends Spawng1 {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_Main)
  override type implT = __Spawng1Imp
  override type implNextT = __End_a_A_MainImp
override def toString() : String = {"EPMain_a_A.Spawng1"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "g1" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Spawng1Imp(c,session)}

protected case class __Spawng1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Spawng1
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
  override protected def __children: List[EPMain_a_A] = List(SndL6)
  override type implT = __Failed_b_BImp
  override type implNextT = __SndL6Imp
override def toString() : String = {"EPMain_a_A.Failed_b_B"}
  override def suspect : Role = Role("b",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b_BImp(c,session)}

protected case class __Failed_b_BImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b_B
}
  def failed_b_B(): __SndL6Imp = {__SndL6Imp(c,session)}

}


  trait SndL6 extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndL6 extends SndL6 {
  override protected def __children: List[EPMain_a_A] = List(Spawng2)
  override type implT = __SndL6Imp
  override type implNextT = __Spawng2Imp
override def toString() : String = {"EPMain_a_A.SndL6"}
    override def to : RRole = RoleSet("C") 
   override def l : String = "L6" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndL6Imp(c,session)}

protected case class __SndL6Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndL6
}
  private var notUsed = true
def sndToC(m : MESSAGES.Main.L6) : __Spawng2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__Spawng2Imp(c,session)}
def !(m : MESSAGES.Main.L6) : __Spawng2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__Spawng2Imp(c,session)}
def snd(m : MESSAGES.Main.L6) : __Spawng2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("C"),m)
__Spawng2Imp(c,session)}

}


  trait Spawng2 extends EPMain_a_A with event_lang.dsl.ChannelTypeSpawn
  object Spawng2 extends Spawng2 {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainFHandling)
  override type implT = __Spawng2Imp
  override type implNextT = __End_a_A_MainFHandlingImp
override def toString() : String = {"EPMain_a_A.Spawng2"}
    override def y: List[Role] = List(Role("a",RoleSet("A"))) 
  override def pickR: RoleSet = RoleSet("C") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "g2" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Spawng2Imp(c,session)}

protected case class __Spawng2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Spawng2
}
  // SPAWN is handled internally -- i.e. no use code here
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

object g1_a_A{
trait EPg1_a_A extends __EPType_A

object EPg1_a_A extends EPg1_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPg1_a_A] = List(Hdl)
  override type implT = __EPg1_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPg1_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("b",RoleSet("B"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "g1"
}

protected case class __EPg1_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPg1_a_A
}
  
}


protected  trait Hdl extends EPg1_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPg1_a_A] = List(RcvL3,Failed_c_C)
  override type implT = __HdlImp
  override type implNextT = __RcvL3Imp
override def toString() : String = {"EPg1_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvL3 extends EPg1_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvL3 extends RcvL3 {
  override protected def __children: List[EPg1_a_A] = List(End_a_A_g1)
  override type implT = __RcvL3Imp
  override type implNextT = __End_a_A_g1Imp
override def toString() : String = {"EPg1_a_A.RcvL3"}
  override type msgT = MESSAGES.g1.L3
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "L3"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvL3Imp(c,session)}

protected case class __RcvL3Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvL3
}
  def rcvFrmc_C : (MESSAGES.g1.L3,__End_a_A_g1Imp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.g1.L3],__End_a_A_g1Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.g1.L3,__End_a_A_g1Imp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.g1.L3],__End_a_A_g1Imp(c,session))) 
}
def rcvMSG : MESSAGES.g1.L3 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.g1.L3]}
def ? : MESSAGES.g1.L3 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.g1.L3]}
def channelCon : __End_a_A_g1Imp = {__End_a_A_g1Imp(c,session)}

}


protected  trait End_a_A_g1 extends EPg1_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_g1 extends End_a_A_g1 {
  override protected def __children: List[EPg1_a_A] = List()
  override type implT = __End_a_A_g1Imp
  override type implNextT = Nothing
override def toString() : String = {"EPg1_a_A.End_a_A_g1"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_g1Imp(c,session)}

protected case class __End_a_A_g1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_g1
}
  
}



  trait Failed_c_C extends EPg1_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_c_C extends Failed_c_C {
  override protected def __children: List[EPg1_a_A] = List(SndL7)
  override type implT = __Failed_c_CImp
  override type implNextT = __SndL7Imp
override def toString() : String = {"EPg1_a_A.Failed_c_C"}
  override def suspect : Role = Role("c",RoleSet("C")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_c_CImp(c,session)}

protected case class __Failed_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_c_C
}
  def failed_c_C(): __SndL7Imp = {__SndL7Imp(c,session)}

}


  trait SndL7 extends EPg1_a_A with event_lang.dsl.ChannelTypeSnd
  object SndL7 extends SndL7 {
  override protected def __children: List[EPg1_a_A] = List(RcvL8)
  override type implT = __SndL7Imp
  override type implNextT = __RcvL8Imp
override def toString() : String = {"EPg1_a_A.SndL7"}
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "L7" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndL7Imp(c,session)}

protected case class __SndL7Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndL7
}
  private var notUsed = true
def sndTob_B(m : MESSAGES.g1.L7) : __RcvL8Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvL8Imp(c,session)}
def !(m : MESSAGES.g1.L7) : __RcvL8Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvL8Imp(c,session)}
def snd(m : MESSAGES.g1.L7) : __RcvL8Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("b",RoleSet("B")),m)
__RcvL8Imp(c,session)}

}


  trait RcvL8 extends EPg1_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvL8 extends RcvL8 {
  override protected def __children: List[EPg1_a_A] = List(End_a_A_g1FHandling)
  override type implT = __RcvL8Imp
  override type implNextT = __End_a_A_g1FHandlingImp
override def toString() : String = {"EPg1_a_A.RcvL8"}
  override type msgT = MESSAGES.g1.L8
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "L8"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvL8Imp(c,session)}

protected case class __RcvL8Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvL8
}
  def rcvFrmb_B : (MESSAGES.g1.L8,__End_a_A_g1FHandlingImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.g1.L8],__End_a_A_g1FHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.g1.L8,__End_a_A_g1FHandlingImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.g1.L8],__End_a_A_g1FHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.g1.L8 = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.g1.L8]}
def ? : MESSAGES.g1.L8 = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.g1.L8]}
def channelCon : __End_a_A_g1FHandlingImp = {__End_a_A_g1FHandlingImp(c,session)}

}


protected  trait End_a_A_g1FHandling extends EPg1_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_g1FHandling extends End_a_A_g1FHandling {
  override protected def __children: List[EPg1_a_A] = List()
  override type implT = __End_a_A_g1FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPg1_a_A.End_a_A_g1FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_g1FHandlingImp(c,session)}

protected case class __End_a_A_g1FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_g1FHandling
}
  
}


}

object g2_a_A{
trait EPg2_a_A extends __EPType_A

object EPg2_a_A extends EPg2_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPg2_a_A] = List(Hdl)
  override type implT = __EPg2_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPg2_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "g2"
}

protected case class __EPg2_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPg2_a_A
}
  
}


protected  trait Hdl extends EPg2_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPg2_a_A] = List(SndL4,Failed_c_C)
  override type implT = __HdlImp
  override type implNextT = __SndL4Imp
override def toString() : String = {"EPg2_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndL4 extends EPg2_a_A with event_lang.dsl.ChannelTypeSnd
  object SndL4 extends SndL4 {
  override protected def __children: List[EPg2_a_A] = List(RcvL5)
  override type implT = __SndL4Imp
  override type implNextT = __RcvL5Imp
override def toString() : String = {"EPg2_a_A.SndL4"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "L4" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndL4Imp(c,session)}

protected case class __SndL4Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndL4
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.g2.L4) : __RcvL5Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__RcvL5Imp(c,session)}
def !(m : MESSAGES.g2.L4) : __RcvL5Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__RcvL5Imp(c,session)}
def snd(m : MESSAGES.g2.L4) : __RcvL5Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__RcvL5Imp(c,session)}

}


  trait RcvL5 extends EPg2_a_A with event_lang.dsl.ChannelTypeRcv
  object RcvL5 extends RcvL5 {
  override protected def __children: List[EPg2_a_A] = List(End_a_A_g2)
  override type implT = __RcvL5Imp
  override type implNextT = __End_a_A_g2Imp
override def toString() : String = {"EPg2_a_A.RcvL5"}
  override type msgT = MESSAGES.g2.L5
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "L5"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvL5Imp(c,session)}

protected case class __RcvL5Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvL5
}
  def rcvFrmc_C : (MESSAGES.g2.L5,__End_a_A_g2Imp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.g2.L5],__End_a_A_g2Imp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.g2.L5,__End_a_A_g2Imp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.g2.L5],__End_a_A_g2Imp(c,session))) 
}
def rcvMSG : MESSAGES.g2.L5 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.g2.L5]}
def ? : MESSAGES.g2.L5 = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.g2.L5]}
def channelCon : __End_a_A_g2Imp = {__End_a_A_g2Imp(c,session)}

}


protected  trait End_a_A_g2 extends EPg2_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_g2 extends End_a_A_g2 {
  override protected def __children: List[EPg2_a_A] = List()
  override type implT = __End_a_A_g2Imp
  override type implNextT = Nothing
override def toString() : String = {"EPg2_a_A.End_a_A_g2"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_g2Imp(c,session)}

protected case class __End_a_A_g2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_g2
}
  
}



  trait Failed_c_C extends EPg2_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_c_C extends Failed_c_C {
  override protected def __children: List[EPg2_a_A] = List(End_a_A_g2FHandling)
  override type implT = __Failed_c_CImp
  override type implNextT = __End_a_A_g2FHandlingImp
override def toString() : String = {"EPg2_a_A.Failed_c_C"}
  override def suspect : Role = Role("c",RoleSet("C")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_c_CImp(c,session)}

protected case class __Failed_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_c_C
}
  def failed_c_C(): __End_a_A_g2FHandlingImp = {__End_a_A_g2FHandlingImp(c,session)}

}


protected  trait End_a_A_g2FHandling extends EPg2_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_g2FHandling extends End_a_A_g2FHandling {
  override protected def __children: List[EPg2_a_A] = List()
  override type implT = __End_a_A_g2FHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPg2_a_A.End_a_A_g2FHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_g2FHandlingImp(c,session)}

protected case class __End_a_A_g2FHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_g2FHandling
}
  
}


}

}

}
