
package example.TestProtocols.value_branching

import event_lang._
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object ValueBranching {
object RS {
val B : RoleSet = RoleSet("B")
val S : RoleSet = RoleSet("S")
}

object MESSAGES {
object Main {
case class Book(price:Int) extends MSG {
   override def l : String = "Book"
}

case class OK() extends MSG {
   override def l : String = "OK"
}

case class No() extends MSG {
   override def l : String = "No"
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
val s_S = Role("s",RoleSet("S"))
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
  override def argsC: List[Role] = List(Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("b",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
  override def prjTo : RRole = Role("b",RoleSet("B")) 
  override def rootRole: Role = Role("s",RoleSet("S")) 
  override def name : String = "Main"
}

protected case class __EPMain_b_BImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_b_B
}
  
}


protected  trait Hdl extends EPMain_b_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_b_B] = List(RcvBook,RcvFailMain)
  override type implT = __HdlImp
  override type implNextT = __RcvBookImp
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvBook extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvBook extends RcvBook {
  override protected def __children: List[EPMain_b_B] = List(SelOKNo)
  override type implT = __RcvBookImp
  override type implNextT = __SelOKNoImp
  override type msgT = MESSAGES.Main.Book
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "Book"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvBookImp(c,session)}

protected case class __RcvBookImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvBook
}
  def rcvFrms_S : (MESSAGES.Main.Book,__SelOKNoImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.Book],__SelOKNoImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.Book,__SelOKNoImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.Book],__SelOKNoImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.Book = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.Book]}
def ? : MESSAGES.Main.Book = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.Book]}
def channelCon : __SelOKNoImp = {__SelOKNoImp(c,session)}

}


protected  trait SelOKNo extends EPMain_b_B with event_lang.dsl.ChannelTypeSel
protected  object SelOKNo extends SelOKNo {
  override protected def __children: List[EPMain_b_B] = List(SndOK,SndNo)
  override type implT = __SelOKNoImp
  override type implNextT = __SndOKImp
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOKNoImp(c,session)}

protected case class __SelOKNoImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOKNo
}
  def !(m : MESSAGES.Main.OK) : __End_b_B_MainOKImp = {c.snd(Role("s",RoleSet("S")),m)
 __End_b_B_MainOKImp(c,session)}
def sndTos_S(m : MESSAGES.Main.OK) : __End_b_B_MainOKImp = {c.snd(Role("s",RoleSet("S")),m)
 __End_b_B_MainOKImp(c,session)}

def !(m : MESSAGES.Main.No) : __End_b_B_MainNoImp = {c.snd(Role("s",RoleSet("S")),m)
 __End_b_B_MainNoImp(c,session)}
def sndTos_S(m : MESSAGES.Main.No) : __End_b_B_MainNoImp = {c.snd(Role("s",RoleSet("S")),m)
 __End_b_B_MainNoImp(c,session)}

}


  trait SndOK extends EPMain_b_B with event_lang.dsl.ChannelTypeSnd
  object SndOK extends SndOK {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_MainOK)
  override type implT = __SndOKImp
  override type implNextT = __End_b_B_MainOKImp
    override def to : RRole = Role("s",RoleSet("S")) 
   override def l : String = "OK" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndOKImp(c,session)}

protected case class __SndOKImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndOK
}
  def sndTos_S(m : MESSAGES.Main.OK) : __End_b_B_MainOKImp = {c.snd(Role("s",RoleSet("S")),m)
__End_b_B_MainOKImp(c,session)}
def !(m : MESSAGES.Main.OK) : __End_b_B_MainOKImp = {c.snd(Role("s",RoleSet("S")),m)
__End_b_B_MainOKImp(c,session)}
def snd(m : MESSAGES.Main.OK) : __End_b_B_MainOKImp = {c.snd(Role("s",RoleSet("S")),m)
__End_b_B_MainOKImp(c,session)}

}


protected  trait End_b_B_MainOK extends EPMain_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_MainOK extends End_b_B_MainOK {
  override protected def __children: List[EPMain_b_B] = List()
  override type implT = __End_b_B_MainOKImp
  override type implNextT = Nothing
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_MainOKImp(c,session)}

protected case class __End_b_B_MainOKImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_MainOK
}
  
}



  trait SndNo extends EPMain_b_B with event_lang.dsl.ChannelTypeSnd
  object SndNo extends SndNo {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_MainNo)
  override type implT = __SndNoImp
  override type implNextT = __End_b_B_MainNoImp
    override def to : RRole = Role("s",RoleSet("S")) 
   override def l : String = "No" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndNoImp(c,session)}

protected case class __SndNoImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndNo
}
  def sndTos_S(m : MESSAGES.Main.No) : __End_b_B_MainNoImp = {c.snd(Role("s",RoleSet("S")),m)
__End_b_B_MainNoImp(c,session)}
def !(m : MESSAGES.Main.No) : __End_b_B_MainNoImp = {c.snd(Role("s",RoleSet("S")),m)
__End_b_B_MainNoImp(c,session)}
def snd(m : MESSAGES.Main.No) : __End_b_B_MainNoImp = {c.snd(Role("s",RoleSet("S")),m)
__End_b_B_MainNoImp(c,session)}

}


protected  trait End_b_B_MainNo extends EPMain_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_MainNo extends End_b_B_MainNo {
  override protected def __children: List[EPMain_b_B] = List()
  override type implT = __End_b_B_MainNoImp
  override type implNextT = Nothing
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_MainNoImp(c,session)}

protected case class __End_b_B_MainNoImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_b_B_MainNo
}
  
}



  trait RcvFailMain extends EPMain_b_B with event_lang.dsl.ChannelTypeRcv
  object RcvFailMain extends RcvFailMain {
  override protected def __children: List[EPMain_b_B] = List(End_b_B_MainFHandling)
  override type implT = __RcvFailMainImp
  override type implNextT = __End_b_B_MainFHandlingImp
  override type msgT = MESSAGES.Main.FailMain
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "FailMain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFailMainImp(c,session)}

protected case class __RcvFailMainImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFailMain
}
  def rcvFrms_S : (MESSAGES.Main.FailMain,__End_b_B_MainFHandlingImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.FailMain],__End_b_B_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FailMain,__End_b_B_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.FailMain],__End_b_B_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FailMain = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.FailMain]}
def ? : MESSAGES.Main.FailMain = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.FailMain]}
def channelCon : __End_b_B_MainFHandlingImp = {__End_b_B_MainFHandlingImp(c,session)}

}


protected  trait End_b_B_MainFHandling extends EPMain_b_B with event_lang.dsl.ChannelTypeEnd
protected  object End_b_B_MainFHandling extends End_b_B_MainFHandling {
  override protected def __children: List[EPMain_b_B] = List()
  override type implT = __End_b_B_MainFHandlingImp
  override type implNextT = Nothing
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_b_B_MainFHandlingImp(c,session)}

protected case class __End_b_B_MainFHandlingImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
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
  override def argsC: List[Role] = List(Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("b",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
  override def prjTo : RRole = RoleSet("B") 
  override def rootRole: Role = Role("s",RoleSet("S")) 
  override def name : String = "Main"
}

protected case class __EPMain_BImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_B
}
  
}


protected  trait Hdl extends EPMain_B with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_B] = List(End_B_Main,RcvFailMain)
  override type implT = __HdlImp
  override type implNextT = __End_B_MainImp
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_B_Main extends EPMain_B with event_lang.dsl.ChannelTypeEnd
protected  object End_B_Main extends End_B_Main {
  override protected def __children: List[EPMain_B] = List()
  override type implT = __End_B_MainImp
  override type implNextT = Nothing
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_B_MainImp(c,session)}

protected case class __End_B_MainImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_B_Main
}
  
}



  trait RcvFailMain extends EPMain_B with event_lang.dsl.ChannelTypeRcv
  object RcvFailMain extends RcvFailMain {
  override protected def __children: List[EPMain_B] = List(End_B_MainFHandling)
  override type implT = __RcvFailMainImp
  override type implNextT = __End_B_MainFHandlingImp
  override type msgT = MESSAGES.Main.FailMain
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "FailMain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFailMainImp(c,session)}

protected case class __RcvFailMainImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFailMain
}
  def rcvFrms_S : (MESSAGES.Main.FailMain,__End_B_MainFHandlingImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.FailMain],__End_B_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FailMain,__End_B_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.FailMain],__End_B_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FailMain = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.FailMain]}
def ? : MESSAGES.Main.FailMain = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.Main.FailMain]}
def channelCon : __End_B_MainFHandlingImp = {__End_B_MainFHandlingImp(c,session)}

}


protected  trait End_B_MainFHandling extends EPMain_B with event_lang.dsl.ChannelTypeEnd
protected  object End_B_MainFHandling extends End_B_MainFHandling {
  override protected def __children: List[EPMain_B] = List()
  override type implT = __End_B_MainFHandlingImp
  override type implNextT = Nothing
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_B_MainFHandlingImp(c,session)}

protected case class __End_B_MainFHandlingImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_B_MainFHandling
}
  
}


}

}

object S {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_s_S.EPMain_s_S)
trait __EPType_S extends AbstractChannelType {

}

trait EPType_S[T<: TState] extends AbstractEndPoint[__EPType_S,T] {
override val roleSet: RoleSet = RoleSet("S")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_s_S.EPMain_s_S)

}

object Main_s_S{
trait EPMain_s_S extends __EPType_S

object EPMain_s_S extends EPMain_s_S with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_s_S] = List(Hdl)
  override type implT = __EPMain_s_SImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_s_SImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("b",RoleSet("B")) 
  override def argsRs: List[RoleSet] = List(RoleSet("B")) 
  override def prjTo : RRole = Role("s",RoleSet("S")) 
  override def rootRole: Role = Role("s",RoleSet("S")) 
  override def name : String = "Main"
}

protected case class __EPMain_s_SImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_s_S
}
  
}


protected  trait Hdl extends EPMain_s_S with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_s_S] = List(SndBook,Failed_b_B)
  override type implT = __HdlImp
  override type implNextT = __SndBookImp
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndBook extends EPMain_s_S with event_lang.dsl.ChannelTypeSnd
  object SndBook extends SndBook {
  override protected def __children: List[EPMain_s_S] = List(SelOKNo)
  override type implT = __SndBookImp
  override type implNextT = __SelOKNoImp
    override def to : RRole = Role("b",RoleSet("B")) 
   override def l : String = "Book" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndBookImp(c,session)}

protected case class __SndBookImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndBook
}
  def sndTob_B(m : MESSAGES.Main.Book) : __SelOKNoImp = {c.snd(Role("b",RoleSet("B")),m)
__SelOKNoImp(c,session)}
def !(m : MESSAGES.Main.Book) : __SelOKNoImp = {c.snd(Role("b",RoleSet("B")),m)
__SelOKNoImp(c,session)}
def snd(m : MESSAGES.Main.Book) : __SelOKNoImp = {c.snd(Role("b",RoleSet("B")),m)
__SelOKNoImp(c,session)}

}


protected  trait SelOKNo extends EPMain_s_S with event_lang.dsl.ChannelTypeBrn
protected  object SelOKNo extends SelOKNo {
  override protected def __children: List[EPMain_s_S] = List(RcvOK,RcvNo)
  override type implT = __SelOKNoImp
  override type implNextT = __RcvOKImp
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelOKNoImp(c,session)}

protected case class __SelOKNoImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelOKNo
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvOK extends EPMain_s_S with event_lang.dsl.ChannelTypeRcv
  object RcvOK extends RcvOK {
  override protected def __children: List[EPMain_s_S] = List(End_s_S_MainOK)
  override type implT = __RcvOKImp
  override type implNextT = __End_s_S_MainOKImp
  override type msgT = MESSAGES.Main.OK
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "OK"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvOKImp(c,session)}

protected case class __RcvOKImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvOK
}
  def rcvFrmb_B : (MESSAGES.Main.OK,__End_s_S_MainOKImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.OK],__End_s_S_MainOKImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.OK,__End_s_S_MainOKImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.OK],__End_s_S_MainOKImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.OK = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.OK]}
def ? : MESSAGES.Main.OK = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.OK]}
def channelCon : __End_s_S_MainOKImp = {__End_s_S_MainOKImp(c,session)}

}


protected  trait End_s_S_MainOK extends EPMain_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_MainOK extends End_s_S_MainOK {
  override protected def __children: List[EPMain_s_S] = List()
  override type implT = __End_s_S_MainOKImp
  override type implNextT = Nothing
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_MainOKImp(c,session)}

protected case class __End_s_S_MainOKImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_MainOK
}
  
}



  trait RcvNo extends EPMain_s_S with event_lang.dsl.ChannelTypeRcv
  object RcvNo extends RcvNo {
  override protected def __children: List[EPMain_s_S] = List(End_s_S_MainNo)
  override type implT = __RcvNoImp
  override type implNextT = __End_s_S_MainNoImp
  override type msgT = MESSAGES.Main.No
   override def frm : Role = Role("b",RoleSet("B")) 
   override def l : String = "No"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvNoImp(c,session)}

protected case class __RcvNoImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvNo
}
  def rcvFrmb_B : (MESSAGES.Main.No,__End_s_S_MainNoImp) = {(c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.No],__End_s_S_MainNoImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.No,__End_s_S_MainNoImp),T]) : T = {
  f((c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.No],__End_s_S_MainNoImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.No = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.No]}
def ? : MESSAGES.Main.No = {c.rcv(Role("b",RoleSet("B"))).asInstanceOf[MESSAGES.Main.No]}
def channelCon : __End_s_S_MainNoImp = {__End_s_S_MainNoImp(c,session)}

}


protected  trait End_s_S_MainNo extends EPMain_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_MainNo extends End_s_S_MainNo {
  override protected def __children: List[EPMain_s_S] = List()
  override type implT = __End_s_S_MainNoImp
  override type implNextT = Nothing
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_MainNoImp(c,session)}

protected case class __End_s_S_MainNoImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_MainNo
}
  
}



  trait Failed_b_B extends EPMain_s_S with event_lang.dsl.ChannelTypeFDtct
  object Failed_b_B extends Failed_b_B {
  override protected def __children: List[EPMain_s_S] = List(SndFailMain)
  override type implT = __Failed_b_BImp
  override type implNextT = __SndFailMainImp
  override def suspect : Role = Role("b",RoleSet("B")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_b_BImp(c,session)}

protected case class __Failed_b_BImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_b_B
}
  def failed_b_B(): __SndFailMainImp = {//FIXME: not doing anything for now
__SndFailMainImp(c,session)}

}


  trait SndFailMain extends EPMain_s_S with event_lang.dsl.ChannelTypeSnd
  object SndFailMain extends SndFailMain {
  override protected def __children: List[EPMain_s_S] = List(End_s_S_MainFHandling)
  override type implT = __SndFailMainImp
  override type implNextT = __End_s_S_MainFHandlingImp
    override def to : RRole = RoleSet("B") 
   override def l : String = "FailMain" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFailMainImp(c,session)}

protected case class __SndFailMainImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFailMain
}
  def sndToB(m : MESSAGES.Main.FailMain) : __End_s_S_MainFHandlingImp = {c.snd(RoleSet("B"),m)
__End_s_S_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.FailMain) : __End_s_S_MainFHandlingImp = {c.snd(RoleSet("B"),m)
__End_s_S_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.FailMain) : __End_s_S_MainFHandlingImp = {c.snd(RoleSet("B"),m)
__End_s_S_MainFHandlingImp(c,session)}

}


protected  trait End_s_S_MainFHandling extends EPMain_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_MainFHandling extends End_s_S_MainFHandling {
  override protected def __children: List[EPMain_s_S] = List()
  override type implT = __End_s_S_MainFHandlingImp
  override type implNextT = Nothing
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_MainFHandlingImp(c,session)}

protected case class __End_s_S_MainFHandlingImp(c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_MainFHandling
}
  
}


}

}

}
