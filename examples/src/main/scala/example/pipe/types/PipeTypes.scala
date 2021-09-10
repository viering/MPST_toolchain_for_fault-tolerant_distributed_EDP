package example.pipe.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object Pipe {
object RS {
val P : RoleSet = RoleSet("P")
val A : RoleSet = RoleSet("A")
}

object MESSAGES {
object Pipe {
case class PM(id:Int) extends MSG {
   override def l : String = "PM"
}

case class PrepSpawn() extends MSG {
   override def l : String = "PrepSpawn"
}

case class End() extends MSG {
   override def l : String = "End"
}

case class FPipe() extends MSG {
   override def l : String = "FPipe"
}

}

object Main {
case class FMain() extends MSG {
   override def l : String = "FMain"
}

}

}

object PROTOCOLS {
object Main {
val nP_P = Role("nP",RoleSet("P"))
val P = RoleSet("P")
val a_A = Role("a",RoleSet("A"))
}

object Pipe {
val p_P = Role("p",RoleSet("P"))
val P = RoleSet("P")
val a_A = Role("a",RoleSet("A"))
val nP_P = Role("nP",RoleSet("P"))
}

}

object P {
val subs : Seq[dsl.ChannelTypeSubS] = List(Pipe_nP_P.EPPipe_nP_P,Pipe_P.EPPipe_P,Pipe_p_P.EPPipe_p_P,Main_P.EPMain_P,Main_nP_P.EPMain_nP_P)
trait __EPType_P extends AbstractChannelType {

}

trait EPType_P[T<: TState] extends AbstractEndPoint[__EPType_P,T] {
override val roleSet: RoleSet = RoleSet("P")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Pipe_nP_P.EPPipe_nP_P,Pipe_P.EPPipe_P,Pipe_p_P.EPPipe_p_P,Main_P.EPMain_P,Main_nP_P.EPMain_nP_P)

}

object Pipe_P{
trait EPPipe_P extends __EPType_P

object EPPipe_P extends EPPipe_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPipe_P] = List(Hdl)
  override type implT = __EPPipe_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPipe_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("nP",RoleSet("P"))) 
  override def argsP: Role = Role("p",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = RoleSet("P") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Pipe"
}

protected case class __EPPipe_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPipe_P
}
  
}


protected  trait Hdl extends EPPipe_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPipe_P] = List(SelPrepSpawnEnd,RcvFPipe)
  override type implT = __HdlImp
  override type implNextT = __SelPrepSpawnEndImp
override def toString() : String = {"EPPipe_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait SelPrepSpawnEnd extends EPPipe_P with event_lang.dsl.ChannelTypeBrn
protected  object SelPrepSpawnEnd extends SelPrepSpawnEnd {
  override protected def __children: List[EPPipe_P] = List(RcvPrepSpawn,RcvEnd)
  override type implT = __SelPrepSpawnEndImp
  override type implNextT = __RcvPrepSpawnImp
override def toString() : String = {"EPPipe_P.SelPrepSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelPrepSpawnEndImp(c,session)}

protected case class __SelPrepSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelPrepSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvPrepSpawn extends EPPipe_P with event_lang.dsl.ChannelTypeRcv
  object RcvPrepSpawn extends RcvPrepSpawn {
  override protected def __children: List[EPPipe_P] = List(SpawnPipe)
  override type implT = __RcvPrepSpawnImp
  override type implNextT = __SpawnPipeImp
override def toString() : String = {"EPPipe_P.RcvPrepSpawn"}
  override type msgT = MESSAGES.Pipe.PrepSpawn
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "PrepSpawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPrepSpawnImp(c,session)}

protected case class __RcvPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPrepSpawn
}
  def rcvFrma_A : (MESSAGES.Pipe.PrepSpawn,__SpawnPipeImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn],__SpawnPipeImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pipe.PrepSpawn,__SpawnPipeImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn],__SpawnPipeImp(c,session))) 
}
def rcvMSG : MESSAGES.Pipe.PrepSpawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn]}
def ? : MESSAGES.Pipe.PrepSpawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn]}
def channelCon : __SpawnPipeImp = {__SpawnPipeImp(c,session)}

}


  trait SpawnPipe extends EPPipe_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnPipe extends SpawnPipe {
  override protected def __children: List[EPPipe_P] = List(End_P_PipePrepSpawn)
  override type implT = __SpawnPipeImp
  override type implNextT = __End_P_PipePrepSpawnImp
override def toString() : String = {"EPPipe_P.SpawnPipe"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("p",RoleSet("P"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Pipe" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPipeImp(c,session)}

protected case class __SpawnPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPipe
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_P_PipePrepSpawn extends EPPipe_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_PipePrepSpawn extends End_P_PipePrepSpawn {
  override protected def __children: List[EPPipe_P] = List()
  override type implT = __End_P_PipePrepSpawnImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_P.End_P_PipePrepSpawn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_PipePrepSpawnImp(c,session)}

protected case class __End_P_PipePrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_PipePrepSpawn
}
  
}



  trait RcvEnd extends EPPipe_P with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPPipe_P] = List(End_P_PipeEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_P_PipeEndImp
override def toString() : String = {"EPPipe_P.RcvEnd"}
  override type msgT = MESSAGES.Pipe.End
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrma_A : (MESSAGES.Pipe.End,__End_P_PipeEndImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End],__End_P_PipeEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pipe.End,__End_P_PipeEndImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End],__End_P_PipeEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Pipe.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End]}
def ? : MESSAGES.Pipe.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End]}
def channelCon : __End_P_PipeEndImp = {__End_P_PipeEndImp(c,session)}

}


protected  trait End_P_PipeEnd extends EPPipe_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_PipeEnd extends End_P_PipeEnd {
  override protected def __children: List[EPPipe_P] = List()
  override type implT = __End_P_PipeEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_P.End_P_PipeEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_PipeEndImp(c,session)}

protected case class __End_P_PipeEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_PipeEnd
}
  
}



  trait RcvFPipe extends EPPipe_P with event_lang.dsl.ChannelTypeRcv
  object RcvFPipe extends RcvFPipe {
  override protected def __children: List[EPPipe_P] = List(End_P_PipeFHandling)
  override type implT = __RcvFPipeImp
  override type implNextT = __End_P_PipeFHandlingImp
override def toString() : String = {"EPPipe_P.RcvFPipe"}
  override type msgT = MESSAGES.Pipe.FPipe
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FPipe"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFPipeImp(c,session)}

protected case class __RcvFPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFPipe
}
  def rcvFrma_A : (MESSAGES.Pipe.FPipe,__End_P_PipeFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.FPipe],__End_P_PipeFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pipe.FPipe,__End_P_PipeFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.FPipe],__End_P_PipeFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Pipe.FPipe = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.FPipe]}
def ? : MESSAGES.Pipe.FPipe = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.FPipe]}
def channelCon : __End_P_PipeFHandlingImp = {__End_P_PipeFHandlingImp(c,session)}

}


protected  trait End_P_PipeFHandling extends EPPipe_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_PipeFHandling extends End_P_PipeFHandling {
  override protected def __children: List[EPPipe_P] = List()
  override type implT = __End_P_PipeFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_P.End_P_PipeFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_PipeFHandlingImp(c,session)}

protected case class __End_P_PipeFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_PipeFHandling
}
  
}


}

object Main_P{
trait EPMain_P extends __EPType_P

object EPMain_P extends EPMain_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_P] = List(Hdl)
  override type implT = __EPMain_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("nP",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = RoleSet("P") 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Main"
}

protected case class __EPMain_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_P
}
  
}


protected  trait Hdl extends EPMain_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_P] = List(SpawnPipe,RcvFMain)
  override type implT = __HdlImp
  override type implNextT = __SpawnPipeImp
override def toString() : String = {"EPMain_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnPipe extends EPMain_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnPipe extends SpawnPipe {
  override protected def __children: List[EPMain_P] = List(End_P_Main)
  override type implT = __SpawnPipeImp
  override type implNextT = __End_P_MainImp
override def toString() : String = {"EPMain_P.SpawnPipe"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("nP",RoleSet("P"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Pipe" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPipeImp(c,session)}

protected case class __SpawnPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPipe
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_P_Main extends EPMain_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_Main extends End_P_Main {
  override protected def __children: List[EPMain_P] = List()
  override type implT = __End_P_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_P.End_P_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_MainImp(c,session)}

protected case class __End_P_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_Main
}
  
}



  trait RcvFMain extends EPMain_P with event_lang.dsl.ChannelTypeRcv
  object RcvFMain extends RcvFMain {
  override protected def __children: List[EPMain_P] = List(End_P_MainFHandling)
  override type implT = __RcvFMainImp
  override type implNextT = __End_P_MainFHandlingImp
override def toString() : String = {"EPMain_P.RcvFMain"}
  override type msgT = MESSAGES.Main.FMain
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FMain"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFMainImp(c,session)}

protected case class __RcvFMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFMain
}
  def rcvFrma_A : (MESSAGES.Main.FMain,__End_P_MainFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMain],__End_P_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FMain,__End_P_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMain],__End_P_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FMain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMain]}
def ? : MESSAGES.Main.FMain = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Main.FMain]}
def channelCon : __End_P_MainFHandlingImp = {__End_P_MainFHandlingImp(c,session)}

}


protected  trait End_P_MainFHandling extends EPMain_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_MainFHandling extends End_P_MainFHandling {
  override protected def __children: List[EPMain_P] = List()
  override type implT = __End_P_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_P.End_P_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_MainFHandlingImp(c,session)}

protected case class __End_P_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_MainFHandling
}
  
}


}

object Main_nP_P{
trait EPMain_nP_P extends __EPType_P

object EPMain_nP_P extends EPMain_nP_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_nP_P] = List(Hdl)
  override type implT = __EPMain_nP_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_nP_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("nP",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = Role("nP",RoleSet("P")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Main"
}

protected case class __EPMain_nP_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_nP_P
}
  
}


protected  trait Hdl extends EPMain_nP_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_nP_P] = List(SpawnPipe,End_nP_P_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnPipeImp
override def toString() : String = {"EPMain_nP_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnPipe extends EPMain_nP_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnPipe extends SpawnPipe {
  override protected def __children: List[EPMain_nP_P] = List(End_nP_P_Main)
  override type implT = __SpawnPipeImp
  override type implNextT = __End_nP_P_MainImp
override def toString() : String = {"EPMain_nP_P.SpawnPipe"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("nP",RoleSet("P"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Pipe" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPipeImp(c,session)}

protected case class __SpawnPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPipe
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_nP_P_Main extends EPMain_nP_P with event_lang.dsl.ChannelTypeEnd
protected  object End_nP_P_Main extends End_nP_P_Main {
  override protected def __children: List[EPMain_nP_P] = List()
  override type implT = __End_nP_P_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_nP_P.End_nP_P_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_nP_P_MainImp(c,session)}

protected case class __End_nP_P_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_nP_P_Main
}
  
}



protected  trait End_nP_P_MainFHandling extends EPMain_nP_P with event_lang.dsl.ChannelTypeEnd
protected  object End_nP_P_MainFHandling extends End_nP_P_MainFHandling {
  override protected def __children: List[EPMain_nP_P] = List()
  override type implT = __End_nP_P_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_nP_P.End_nP_P_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_nP_P_MainFHandlingImp(c,session)}

protected case class __End_nP_P_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_nP_P_MainFHandling
}
  
}


}

object Pipe_p_P{
trait EPPipe_p_P extends __EPType_P

object EPPipe_p_P extends EPPipe_p_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPipe_p_P] = List(Hdl)
  override type implT = __EPPipe_p_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPipe_p_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("nP",RoleSet("P"))) 
  override def argsP: Role = Role("p",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = Role("p",RoleSet("P")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Pipe"
}

protected case class __EPPipe_p_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPipe_p_P
}
  
}


protected  trait Hdl extends EPPipe_p_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPipe_p_P] = List(RcvPM,End_p_P_PipeFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvPMImp
override def toString() : String = {"EPPipe_p_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvPM extends EPPipe_p_P with event_lang.dsl.ChannelTypeRcv
  object RcvPM extends RcvPM {
  override protected def __children: List[EPPipe_p_P] = List(SelPrepSpawnEnd)
  override type implT = __RcvPMImp
  override type implNextT = __SelPrepSpawnEndImp
override def toString() : String = {"EPPipe_p_P.RcvPM"}
  override type msgT = MESSAGES.Pipe.PM
   override def frm : Role = Role("nP",RoleSet("P")) 
   override def l : String = "PM"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPMImp(c,session)}

protected case class __RcvPMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPM
}
  def rcvFrmnP_P : (MESSAGES.Pipe.PM,__SelPrepSpawnEndImp) = {(c.rcv(Role("nP",RoleSet("P"))).asInstanceOf[MESSAGES.Pipe.PM],__SelPrepSpawnEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pipe.PM,__SelPrepSpawnEndImp),T]) : T = {
  f((c.rcv(Role("nP",RoleSet("P"))).asInstanceOf[MESSAGES.Pipe.PM],__SelPrepSpawnEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Pipe.PM = {c.rcv(Role("nP",RoleSet("P"))).asInstanceOf[MESSAGES.Pipe.PM]}
def ? : MESSAGES.Pipe.PM = {c.rcv(Role("nP",RoleSet("P"))).asInstanceOf[MESSAGES.Pipe.PM]}
def channelCon : __SelPrepSpawnEndImp = {__SelPrepSpawnEndImp(c,session)}

}


protected  trait SelPrepSpawnEnd extends EPPipe_p_P with event_lang.dsl.ChannelTypeBrn
protected  object SelPrepSpawnEnd extends SelPrepSpawnEnd {
  override protected def __children: List[EPPipe_p_P] = List(RcvPrepSpawn,RcvEnd)
  override type implT = __SelPrepSpawnEndImp
  override type implNextT = __RcvPrepSpawnImp
override def toString() : String = {"EPPipe_p_P.SelPrepSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelPrepSpawnEndImp(c,session)}

protected case class __SelPrepSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelPrepSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvPrepSpawn extends EPPipe_p_P with event_lang.dsl.ChannelTypeRcv
  object RcvPrepSpawn extends RcvPrepSpawn {
  override protected def __children: List[EPPipe_p_P] = List(SpawnPipe)
  override type implT = __RcvPrepSpawnImp
  override type implNextT = __SpawnPipeImp
override def toString() : String = {"EPPipe_p_P.RcvPrepSpawn"}
  override type msgT = MESSAGES.Pipe.PrepSpawn
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "PrepSpawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPrepSpawnImp(c,session)}

protected case class __RcvPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPrepSpawn
}
  def rcvFrma_A : (MESSAGES.Pipe.PrepSpawn,__SpawnPipeImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn],__SpawnPipeImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pipe.PrepSpawn,__SpawnPipeImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn],__SpawnPipeImp(c,session))) 
}
def rcvMSG : MESSAGES.Pipe.PrepSpawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn]}
def ? : MESSAGES.Pipe.PrepSpawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn]}
def channelCon : __SpawnPipeImp = {__SpawnPipeImp(c,session)}

}


  trait SpawnPipe extends EPPipe_p_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnPipe extends SpawnPipe {
  override protected def __children: List[EPPipe_p_P] = List(End_p_P_PipePrepSpawn)
  override type implT = __SpawnPipeImp
  override type implNextT = __End_p_P_PipePrepSpawnImp
override def toString() : String = {"EPPipe_p_P.SpawnPipe"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("p",RoleSet("P"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Pipe" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPipeImp(c,session)}

protected case class __SpawnPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPipe
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_p_P_PipePrepSpawn extends EPPipe_p_P with event_lang.dsl.ChannelTypeEnd
protected  object End_p_P_PipePrepSpawn extends End_p_P_PipePrepSpawn {
  override protected def __children: List[EPPipe_p_P] = List()
  override type implT = __End_p_P_PipePrepSpawnImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_p_P.End_p_P_PipePrepSpawn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_p_P_PipePrepSpawnImp(c,session)}

protected case class __End_p_P_PipePrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_p_P_PipePrepSpawn
}
  
}



  trait RcvEnd extends EPPipe_p_P with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPPipe_p_P] = List(End_p_P_PipeEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_p_P_PipeEndImp
override def toString() : String = {"EPPipe_p_P.RcvEnd"}
  override type msgT = MESSAGES.Pipe.End
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrma_A : (MESSAGES.Pipe.End,__End_p_P_PipeEndImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End],__End_p_P_PipeEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pipe.End,__End_p_P_PipeEndImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End],__End_p_P_PipeEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Pipe.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End]}
def ? : MESSAGES.Pipe.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End]}
def channelCon : __End_p_P_PipeEndImp = {__End_p_P_PipeEndImp(c,session)}

}


protected  trait End_p_P_PipeEnd extends EPPipe_p_P with event_lang.dsl.ChannelTypeEnd
protected  object End_p_P_PipeEnd extends End_p_P_PipeEnd {
  override protected def __children: List[EPPipe_p_P] = List()
  override type implT = __End_p_P_PipeEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_p_P.End_p_P_PipeEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_p_P_PipeEndImp(c,session)}

protected case class __End_p_P_PipeEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_p_P_PipeEnd
}
  
}



protected  trait End_p_P_PipeFHandling extends EPPipe_p_P with event_lang.dsl.ChannelTypeEnd
protected  object End_p_P_PipeFHandling extends End_p_P_PipeFHandling {
  override protected def __children: List[EPPipe_p_P] = List()
  override type implT = __End_p_P_PipeFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_p_P.End_p_P_PipeFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_p_P_PipeFHandlingImp(c,session)}

protected case class __End_p_P_PipeFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_p_P_PipeFHandling
}
  
}


}

object Pipe_nP_P{
trait EPPipe_nP_P extends __EPType_P

object EPPipe_nP_P extends EPPipe_nP_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPipe_nP_P] = List(Hdl)
  override type implT = __EPPipe_nP_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPipe_nP_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("nP",RoleSet("P"))) 
  override def argsP: Role = Role("p",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = Role("nP",RoleSet("P")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Pipe"
}

protected case class __EPPipe_nP_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPipe_nP_P
}
  
}


protected  trait Hdl extends EPPipe_nP_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPipe_nP_P] = List(SndPM,RcvFPipe)
  override type implT = __HdlImp
  override type implNextT = __SndPMImp
override def toString() : String = {"EPPipe_nP_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndPM extends EPPipe_nP_P with event_lang.dsl.ChannelTypeSnd
  object SndPM extends SndPM {
  override protected def __children: List[EPPipe_nP_P] = List(SelPrepSpawnEnd)
  override type implT = __SndPMImp
  override type implNextT = __SelPrepSpawnEndImp
override def toString() : String = {"EPPipe_nP_P.SndPM"}
    override def to : RRole = Role("p",RoleSet("P")) 
   override def l : String = "PM" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPMImp(c,session)}

protected case class __SndPMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPM
}
  private var notUsed = true
def sndTop_P(m : MESSAGES.Pipe.PM) : __SelPrepSpawnEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__SelPrepSpawnEndImp(c,session)}
def !(m : MESSAGES.Pipe.PM) : __SelPrepSpawnEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__SelPrepSpawnEndImp(c,session)}
def snd(m : MESSAGES.Pipe.PM) : __SelPrepSpawnEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__SelPrepSpawnEndImp(c,session)}

}


protected  trait SelPrepSpawnEnd extends EPPipe_nP_P with event_lang.dsl.ChannelTypeBrn
protected  object SelPrepSpawnEnd extends SelPrepSpawnEnd {
  override protected def __children: List[EPPipe_nP_P] = List(RcvPrepSpawn,RcvEnd)
  override type implT = __SelPrepSpawnEndImp
  override type implNextT = __RcvPrepSpawnImp
override def toString() : String = {"EPPipe_nP_P.SelPrepSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelPrepSpawnEndImp(c,session)}

protected case class __SelPrepSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelPrepSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvPrepSpawn extends EPPipe_nP_P with event_lang.dsl.ChannelTypeRcv
  object RcvPrepSpawn extends RcvPrepSpawn {
  override protected def __children: List[EPPipe_nP_P] = List(SpawnPipe)
  override type implT = __RcvPrepSpawnImp
  override type implNextT = __SpawnPipeImp
override def toString() : String = {"EPPipe_nP_P.RcvPrepSpawn"}
  override type msgT = MESSAGES.Pipe.PrepSpawn
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "PrepSpawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPrepSpawnImp(c,session)}

protected case class __RcvPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPrepSpawn
}
  def rcvFrma_A : (MESSAGES.Pipe.PrepSpawn,__SpawnPipeImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn],__SpawnPipeImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pipe.PrepSpawn,__SpawnPipeImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn],__SpawnPipeImp(c,session))) 
}
def rcvMSG : MESSAGES.Pipe.PrepSpawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn]}
def ? : MESSAGES.Pipe.PrepSpawn = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.PrepSpawn]}
def channelCon : __SpawnPipeImp = {__SpawnPipeImp(c,session)}

}


  trait SpawnPipe extends EPPipe_nP_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnPipe extends SpawnPipe {
  override protected def __children: List[EPPipe_nP_P] = List(End_nP_P_PipePrepSpawn)
  override type implT = __SpawnPipeImp
  override type implNextT = __End_nP_P_PipePrepSpawnImp
override def toString() : String = {"EPPipe_nP_P.SpawnPipe"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("p",RoleSet("P"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Pipe" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPipeImp(c,session)}

protected case class __SpawnPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPipe
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_nP_P_PipePrepSpawn extends EPPipe_nP_P with event_lang.dsl.ChannelTypeEnd
protected  object End_nP_P_PipePrepSpawn extends End_nP_P_PipePrepSpawn {
  override protected def __children: List[EPPipe_nP_P] = List()
  override type implT = __End_nP_P_PipePrepSpawnImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_nP_P.End_nP_P_PipePrepSpawn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_nP_P_PipePrepSpawnImp(c,session)}

protected case class __End_nP_P_PipePrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_nP_P_PipePrepSpawn
}
  
}



  trait RcvEnd extends EPPipe_nP_P with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPPipe_nP_P] = List(End_nP_P_PipeEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_nP_P_PipeEndImp
override def toString() : String = {"EPPipe_nP_P.RcvEnd"}
  override type msgT = MESSAGES.Pipe.End
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrma_A : (MESSAGES.Pipe.End,__End_nP_P_PipeEndImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End],__End_nP_P_PipeEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pipe.End,__End_nP_P_PipeEndImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End],__End_nP_P_PipeEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Pipe.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End]}
def ? : MESSAGES.Pipe.End = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.End]}
def channelCon : __End_nP_P_PipeEndImp = {__End_nP_P_PipeEndImp(c,session)}

}


protected  trait End_nP_P_PipeEnd extends EPPipe_nP_P with event_lang.dsl.ChannelTypeEnd
protected  object End_nP_P_PipeEnd extends End_nP_P_PipeEnd {
  override protected def __children: List[EPPipe_nP_P] = List()
  override type implT = __End_nP_P_PipeEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_nP_P.End_nP_P_PipeEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_nP_P_PipeEndImp(c,session)}

protected case class __End_nP_P_PipeEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_nP_P_PipeEnd
}
  
}



  trait RcvFPipe extends EPPipe_nP_P with event_lang.dsl.ChannelTypeRcv
  object RcvFPipe extends RcvFPipe {
  override protected def __children: List[EPPipe_nP_P] = List(End_nP_P_PipeFHandling)
  override type implT = __RcvFPipeImp
  override type implNextT = __End_nP_P_PipeFHandlingImp
override def toString() : String = {"EPPipe_nP_P.RcvFPipe"}
  override type msgT = MESSAGES.Pipe.FPipe
   override def frm : Role = Role("a",RoleSet("A")) 
   override def l : String = "FPipe"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFPipeImp(c,session)}

protected case class __RcvFPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFPipe
}
  def rcvFrma_A : (MESSAGES.Pipe.FPipe,__End_nP_P_PipeFHandlingImp) = {(c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.FPipe],__End_nP_P_PipeFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Pipe.FPipe,__End_nP_P_PipeFHandlingImp),T]) : T = {
  f((c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.FPipe],__End_nP_P_PipeFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Pipe.FPipe = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.FPipe]}
def ? : MESSAGES.Pipe.FPipe = {c.rcv(Role("a",RoleSet("A"))).asInstanceOf[MESSAGES.Pipe.FPipe]}
def channelCon : __End_nP_P_PipeFHandlingImp = {__End_nP_P_PipeFHandlingImp(c,session)}

}


protected  trait End_nP_P_PipeFHandling extends EPPipe_nP_P with event_lang.dsl.ChannelTypeEnd
protected  object End_nP_P_PipeFHandling extends End_nP_P_PipeFHandling {
  override protected def __children: List[EPPipe_nP_P] = List()
  override type implT = __End_nP_P_PipeFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_nP_P.End_nP_P_PipeFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_nP_P_PipeFHandlingImp(c,session)}

protected case class __End_nP_P_PipeFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_nP_P_PipeFHandling
}
  
}


}

}

object A {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_a_A.EPMain_a_A,Pipe_a_A.EPPipe_a_A)
trait __EPType_A extends AbstractChannelType {

}

trait EPType_A[T<: TState] extends AbstractEndPoint[__EPType_A,T] {
override val roleSet: RoleSet = RoleSet("A")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_a_A.EPMain_a_A,Pipe_a_A.EPPipe_a_A)

}

object Main_a_A{
trait EPMain_a_A extends __EPType_A

object EPMain_a_A extends EPMain_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_a_A] = List(Hdl)
  override type implT = __EPMain_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A"))) 
  override def argsP: Role = Role("nP",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
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
  override protected def __children: List[EPMain_a_A] = List(SpawnPipe,Failed_nP_P)
  override type implT = __HdlImp
  override type implNextT = __SpawnPipeImp
override def toString() : String = {"EPMain_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnPipe extends EPMain_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnPipe extends SpawnPipe {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_Main)
  override type implT = __SpawnPipeImp
  override type implNextT = __End_a_A_MainImp
override def toString() : String = {"EPMain_a_A.SpawnPipe"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("nP",RoleSet("P"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Pipe" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPipeImp(c,session)}

protected case class __SpawnPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPipe
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



  trait Failed_nP_P extends EPMain_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_nP_P extends Failed_nP_P {
  override protected def __children: List[EPMain_a_A] = List(SndFMain)
  override type implT = __Failed_nP_PImp
  override type implNextT = __SndFMainImp
override def toString() : String = {"EPMain_a_A.Failed_nP_P"}
  override def suspect : Role = Role("nP",RoleSet("P")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_nP_PImp(c,session)}

protected case class __Failed_nP_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_nP_P
}
  def failed_nP_P(): __SndFMainImp = {__SndFMainImp(c,session)}

}


  trait SndFMain extends EPMain_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFMain extends SndFMain {
  override protected def __children: List[EPMain_a_A] = List(End_a_A_MainFHandling)
  override type implT = __SndFMainImp
  override type implNextT = __End_a_A_MainFHandlingImp
override def toString() : String = {"EPMain_a_A.SndFMain"}
    override def to : RRole = RoleSet("P") 
   override def l : String = "FMain" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFMainImp(c,session)}

protected case class __SndFMainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFMain
}
  private var notUsed = true
def sndToP(m : MESSAGES.Main.FMain) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_a_A_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.FMain) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_a_A_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.FMain) : __End_a_A_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
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

object Pipe_a_A{
trait EPPipe_a_A extends __EPType_A

object EPPipe_a_A extends EPPipe_a_A with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPipe_a_A] = List(Hdl)
  override type implT = __EPPipe_a_AImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPipe_a_AImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("a",RoleSet("A")), Role("nP",RoleSet("P"))) 
  override def argsP: Role = Role("p",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = Role("a",RoleSet("A")) 
  override def rootRole: Role = Role("a",RoleSet("A")) 
  override def name : String = "Pipe"
}

protected case class __EPPipe_a_AImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPipe_a_A
}
  
}


protected  trait Hdl extends EPPipe_a_A with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPipe_a_A] = List(SelPrepSpawnEnd,Failed_p_P)
  override type implT = __HdlImp
  override type implNextT = __SelPrepSpawnEndImp
override def toString() : String = {"EPPipe_a_A.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait SelPrepSpawnEnd extends EPPipe_a_A with event_lang.dsl.ChannelTypeSel
protected  object SelPrepSpawnEnd extends SelPrepSpawnEnd {
  override protected def __children: List[EPPipe_a_A] = List(SndPrepSpawn,SndEnd)
  override type implT = __SelPrepSpawnEndImp
  override type implNextT = __SndPrepSpawnImp
override def toString() : String = {"EPPipe_a_A.SelPrepSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelPrepSpawnEndImp(c,session)}

protected case class __SelPrepSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelPrepSpawnEnd
}
  private var notUsed = true
def !(m : MESSAGES.Pipe.PrepSpawn) : __SpawnPipeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
 __SpawnPipeImp(c,session)}
def sndToP(m : MESSAGES.Pipe.PrepSpawn) : __SpawnPipeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
 __SpawnPipeImp(c,session)}

def !(m : MESSAGES.Pipe.End) : __End_a_A_PipeEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
 __End_a_A_PipeEndImp(c,session)}
def sndToP(m : MESSAGES.Pipe.End) : __End_a_A_PipeEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
 __End_a_A_PipeEndImp(c,session)}

}


  trait SndPrepSpawn extends EPPipe_a_A with event_lang.dsl.ChannelTypeSnd
  object SndPrepSpawn extends SndPrepSpawn {
  override protected def __children: List[EPPipe_a_A] = List(SpawnPipe)
  override type implT = __SndPrepSpawnImp
  override type implNextT = __SpawnPipeImp
override def toString() : String = {"EPPipe_a_A.SndPrepSpawn"}
    override def to : RRole = RoleSet("P") 
   override def l : String = "PrepSpawn" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPrepSpawnImp(c,session)}

protected case class __SndPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPrepSpawn
}
  private var notUsed = true
def sndToP(m : MESSAGES.Pipe.PrepSpawn) : __SpawnPipeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnPipeImp(c,session)}
def !(m : MESSAGES.Pipe.PrepSpawn) : __SpawnPipeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnPipeImp(c,session)}
def snd(m : MESSAGES.Pipe.PrepSpawn) : __SpawnPipeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnPipeImp(c,session)}

}


  trait SpawnPipe extends EPPipe_a_A with event_lang.dsl.ChannelTypeSpawn
  object SpawnPipe extends SpawnPipe {
  override protected def __children: List[EPPipe_a_A] = List(End_a_A_PipePrepSpawn)
  override type implT = __SpawnPipeImp
  override type implNextT = __End_a_A_PipePrepSpawnImp
override def toString() : String = {"EPPipe_a_A.SpawnPipe"}
    override def y: List[Role] = List(Role("a",RoleSet("A")), Role("p",RoleSet("P"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Pipe" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPipeImp(c,session)}

protected case class __SpawnPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPipe
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_a_A_PipePrepSpawn extends EPPipe_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_PipePrepSpawn extends End_a_A_PipePrepSpawn {
  override protected def __children: List[EPPipe_a_A] = List()
  override type implT = __End_a_A_PipePrepSpawnImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_a_A.End_a_A_PipePrepSpawn"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_PipePrepSpawnImp(c,session)}

protected case class __End_a_A_PipePrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_PipePrepSpawn
}
  
}



  trait SndEnd extends EPPipe_a_A with event_lang.dsl.ChannelTypeSnd
  object SndEnd extends SndEnd {
  override protected def __children: List[EPPipe_a_A] = List(End_a_A_PipeEnd)
  override type implT = __SndEndImp
  override type implNextT = __End_a_A_PipeEndImp
override def toString() : String = {"EPPipe_a_A.SndEnd"}
    override def to : RRole = RoleSet("P") 
   override def l : String = "End" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndImp(c,session)}

protected case class __SndEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEnd
}
  private var notUsed = true
def sndToP(m : MESSAGES.Pipe.End) : __End_a_A_PipeEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_a_A_PipeEndImp(c,session)}
def !(m : MESSAGES.Pipe.End) : __End_a_A_PipeEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_a_A_PipeEndImp(c,session)}
def snd(m : MESSAGES.Pipe.End) : __End_a_A_PipeEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_a_A_PipeEndImp(c,session)}

}


protected  trait End_a_A_PipeEnd extends EPPipe_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_PipeEnd extends End_a_A_PipeEnd {
  override protected def __children: List[EPPipe_a_A] = List()
  override type implT = __End_a_A_PipeEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_a_A.End_a_A_PipeEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_PipeEndImp(c,session)}

protected case class __End_a_A_PipeEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_PipeEnd
}
  
}



  trait Failed_p_P extends EPPipe_a_A with event_lang.dsl.ChannelTypeFDtct
  object Failed_p_P extends Failed_p_P {
  override protected def __children: List[EPPipe_a_A] = List(SndFPipe)
  override type implT = __Failed_p_PImp
  override type implNextT = __SndFPipeImp
override def toString() : String = {"EPPipe_a_A.Failed_p_P"}
  override def suspect : Role = Role("p",RoleSet("P")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_p_PImp(c,session)}

protected case class __Failed_p_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_p_P
}
  def failed_p_P(): __SndFPipeImp = {__SndFPipeImp(c,session)}

}


  trait SndFPipe extends EPPipe_a_A with event_lang.dsl.ChannelTypeSnd
  object SndFPipe extends SndFPipe {
  override protected def __children: List[EPPipe_a_A] = List(End_a_A_PipeFHandling)
  override type implT = __SndFPipeImp
  override type implNextT = __End_a_A_PipeFHandlingImp
override def toString() : String = {"EPPipe_a_A.SndFPipe"}
    override def to : RRole = RoleSet("P") 
   override def l : String = "FPipe" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFPipeImp(c,session)}

protected case class __SndFPipeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFPipe
}
  private var notUsed = true
def sndToP(m : MESSAGES.Pipe.FPipe) : __End_a_A_PipeFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_a_A_PipeFHandlingImp(c,session)}
def !(m : MESSAGES.Pipe.FPipe) : __End_a_A_PipeFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_a_A_PipeFHandlingImp(c,session)}
def snd(m : MESSAGES.Pipe.FPipe) : __End_a_A_PipeFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_a_A_PipeFHandlingImp(c,session)}

}


protected  trait End_a_A_PipeFHandling extends EPPipe_a_A with event_lang.dsl.ChannelTypeEnd
protected  object End_a_A_PipeFHandling extends End_a_A_PipeFHandling {
  override protected def __children: List[EPPipe_a_A] = List()
  override type implT = __End_a_A_PipeFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPipe_a_A.End_a_A_PipeFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_a_A_PipeFHandlingImp(c,session)}

protected case class __End_a_A_PipeFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_a_A_PipeFHandling
}
  
}


}

}

}
