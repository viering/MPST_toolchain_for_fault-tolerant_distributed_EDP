package example.esop_crash_handling.streaming.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object Streaming {
object RS {
val W : RoleSet = RoleSet("W")
val DFS : RoleSet = RoleSet("DFS")
val ZK : RoleSet = RoleSet("ZK")
}

object MESSAGES {
object Main {
case class EndP() extends MSG {
   override def l : String = "EndP"
}

case class DM() extends MSG {
   override def l : String = "DM"
}

case class PrepSpawn() extends MSG {
   override def l : String = "PrepSpawn"
}

case class Spawn() extends MSG {
   override def l : String = "Spawn"
}

case class End() extends MSG {
   override def l : String = "End"
}

}

object Partition {
case class WorkLoad() extends MSG {
   override def l : String = "WorkLoad"
}

case class Result() extends MSG {
   override def l : String = "Result"
}

case class DM() extends MSG {
   override def l : String = "DM"
}

}

}

object PROTOCOLS {
object Partition {
val w_W = Role("w",RoleSet("W"))
val W = RoleSet("W")
val dfs_DFS = Role("dfs",RoleSet("DFS"))
}

object Main {
val dfs_DFS = Role("dfs",RoleSet("DFS"))
val W = RoleSet("W")
val zk_ZK = Role("zk",RoleSet("ZK"))
}

}

object W {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_W.EPMain_W,Partition_w_W.EPPartition_w_W,Partition_W.EPPartition_W)
trait __EPType_W extends AbstractChannelType {

}

trait EPType_W[T<: TState] extends AbstractEndPoint[__EPType_W,T] {
override val roleSet: RoleSet = RoleSet("W")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_W.EPMain_W,Partition_w_W.EPPartition_w_W,Partition_W.EPPartition_W)

}

object Main_W{
trait EPMain_W extends __EPType_W

object EPMain_W extends EPMain_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_W] = List(Hdl)
  override type implT = __EPMain_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("zk",RoleSet("ZK"))) 
  override def argsP: Role = Role("dfs",RoleSet("DFS")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = RoleSet("W") 
  override def rootRole: Role = Role("zk",RoleSet("ZK")) 
  override def name : String = "Main"
}

protected case class __EPMain_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_W
}
  
}


protected  trait Hdl extends EPMain_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_W] = List(RecT,RcvDM)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPMain_W with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPMain_W] = List(Merge_PrepSpawn_EndP)
  override type implT = __RecTImp
  override type implNextT = __Merge_PrepSpawn_EndPImp
override def toString() : String = {"EPMain_W.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait Merge_PrepSpawn_EndP extends EPMain_W with event_lang.dsl.ChannelTypeMerge
protected  object Merge_PrepSpawn_EndP extends Merge_PrepSpawn_EndP {
  override protected def __children: List[EPMain_W] = List(RcvPrepSpawn,RcvEndP)
  override type implT = __Merge_PrepSpawn_EndPImp
  override type implNextT = __RcvPrepSpawnImp
override def toString() : String = {"EPMain_W.Merge_PrepSpawn_EndP"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Merge_PrepSpawn_EndPImp(c,session)}

protected case class __Merge_PrepSpawn_EndPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Merge_PrepSpawn_EndP
}
  
}


  trait RcvPrepSpawn extends EPMain_W with event_lang.dsl.ChannelTypeRcv
  object RcvPrepSpawn extends RcvPrepSpawn {
  override protected def __children: List[EPMain_W] = List(SpawnPartition)
  override type implT = __RcvPrepSpawnImp
  override type implNextT = __SpawnPartitionImp
override def toString() : String = {"EPMain_W.RcvPrepSpawn"}
  override type msgT = MESSAGES.Main.PrepSpawn
   override def frm : Role = Role("zk",RoleSet("ZK")) 
   override def l : String = "PrepSpawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPrepSpawnImp(c,session)}

protected case class __RcvPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPrepSpawn
}
  def rcvFrmzk_ZK : (MESSAGES.Main.PrepSpawn,__SpawnPartitionImp) = {(c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.PrepSpawn],__SpawnPartitionImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.PrepSpawn,__SpawnPartitionImp),T]) : T = {
  f((c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.PrepSpawn],__SpawnPartitionImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.PrepSpawn = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.PrepSpawn]}
def ? : MESSAGES.Main.PrepSpawn = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.PrepSpawn]}
def channelCon : __SpawnPartitionImp = {__SpawnPartitionImp(c,session)}

}


  trait SpawnPartition extends EPMain_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnPartition extends SpawnPartition {
  override protected def __children: List[EPMain_W] = List(T)
  override type implT = __SpawnPartitionImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_W.SpawnPartition"}
    override def y: List[Role] = List(Role("dfs",RoleSet("DFS"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Partition" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPartitionImp(c,session)}

protected case class __SpawnPartitionImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPartition
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPMain_W with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPMain_W] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_W.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvEndP extends EPMain_W with event_lang.dsl.ChannelTypeRcv
  object RcvEndP extends RcvEndP {
  override protected def __children: List[EPMain_W] = List(End_W_MainEndP)
  override type implT = __RcvEndPImp
  override type implNextT = __End_W_MainEndPImp
override def toString() : String = {"EPMain_W.RcvEndP"}
  override type msgT = MESSAGES.Main.EndP
   override def frm : Role = Role("zk",RoleSet("ZK")) 
   override def l : String = "EndP"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndPImp(c,session)}

protected case class __RcvEndPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEndP
}
  def rcvFrmzk_ZK : (MESSAGES.Main.EndP,__End_W_MainEndPImp) = {(c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.EndP],__End_W_MainEndPImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.EndP,__End_W_MainEndPImp),T]) : T = {
  f((c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.EndP],__End_W_MainEndPImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.EndP = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.EndP]}
def ? : MESSAGES.Main.EndP = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.EndP]}
def channelCon : __End_W_MainEndPImp = {__End_W_MainEndPImp(c,session)}

}


protected  trait End_W_MainEndP extends EPMain_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_MainEndP extends End_W_MainEndP {
  override protected def __children: List[EPMain_W] = List()
  override type implT = __End_W_MainEndPImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_W.End_W_MainEndP"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_MainEndPImp(c,session)}

protected case class __End_W_MainEndPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_MainEndP
}
  
}



  trait RcvDM extends EPMain_W with event_lang.dsl.ChannelTypeRcv
  object RcvDM extends RcvDM {
  override protected def __children: List[EPMain_W] = List(End_W_MainFHandling)
  override type implT = __RcvDMImp
  override type implNextT = __End_W_MainFHandlingImp
override def toString() : String = {"EPMain_W.RcvDM"}
  override type msgT = MESSAGES.Main.DM
   override def frm : Role = Role("zk",RoleSet("ZK")) 
   override def l : String = "DM"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDMImp(c,session)}

protected case class __RcvDMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDM
}
  def rcvFrmzk_ZK : (MESSAGES.Main.DM,__End_W_MainFHandlingImp) = {(c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.DM],__End_W_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.DM,__End_W_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.DM],__End_W_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.DM = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.DM]}
def ? : MESSAGES.Main.DM = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.DM]}
def channelCon : __End_W_MainFHandlingImp = {__End_W_MainFHandlingImp(c,session)}

}


protected  trait End_W_MainFHandling extends EPMain_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_MainFHandling extends End_W_MainFHandling {
  override protected def __children: List[EPMain_W] = List()
  override type implT = __End_W_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_W.End_W_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_MainFHandlingImp(c,session)}

protected case class __End_W_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_MainFHandling
}
  
}


}

object Partition_w_W{
trait EPPartition_w_W extends __EPType_W

object EPPartition_w_W extends EPPartition_w_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPartition_w_W] = List(Hdl)
  override type implT = __EPPartition_w_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPartition_w_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("dfs",RoleSet("DFS"))) 
  override def argsP: Role = Role("w",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("w",RoleSet("W")) 
  override def rootRole: Role = Role("dfs",RoleSet("DFS")) 
  override def name : String = "Partition"
}

protected case class __EPPartition_w_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPartition_w_W
}
  
}


protected  trait Hdl extends EPPartition_w_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPartition_w_W] = List(RecT,End_w_W_PartitionFHandling)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPartition_w_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPPartition_w_W with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPPartition_w_W] = List(RcvWorkLoad)
  override type implT = __RecTImp
  override type implNextT = __RcvWorkLoadImp
override def toString() : String = {"EPPartition_w_W.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait RcvWorkLoad extends EPPartition_w_W with event_lang.dsl.ChannelTypeRcv
  object RcvWorkLoad extends RcvWorkLoad {
  override protected def __children: List[EPPartition_w_W] = List(SndResult)
  override type implT = __RcvWorkLoadImp
  override type implNextT = __SndResultImp
override def toString() : String = {"EPPartition_w_W.RcvWorkLoad"}
  override type msgT = MESSAGES.Partition.WorkLoad
   override def frm : Role = Role("dfs",RoleSet("DFS")) 
   override def l : String = "WorkLoad"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvWorkLoadImp(c,session)}

protected case class __RcvWorkLoadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvWorkLoad
}
  def rcvFrmdfs_DFS : (MESSAGES.Partition.WorkLoad,__SndResultImp) = {(c.rcv(Role("dfs",RoleSet("DFS"))).asInstanceOf[MESSAGES.Partition.WorkLoad],__SndResultImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Partition.WorkLoad,__SndResultImp),T]) : T = {
  f((c.rcv(Role("dfs",RoleSet("DFS"))).asInstanceOf[MESSAGES.Partition.WorkLoad],__SndResultImp(c,session))) 
}
def rcvMSG : MESSAGES.Partition.WorkLoad = {c.rcv(Role("dfs",RoleSet("DFS"))).asInstanceOf[MESSAGES.Partition.WorkLoad]}
def ? : MESSAGES.Partition.WorkLoad = {c.rcv(Role("dfs",RoleSet("DFS"))).asInstanceOf[MESSAGES.Partition.WorkLoad]}
def channelCon : __SndResultImp = {__SndResultImp(c,session)}

}


  trait SndResult extends EPPartition_w_W with event_lang.dsl.ChannelTypeSnd
  object SndResult extends SndResult {
  override protected def __children: List[EPPartition_w_W] = List(T)
  override type implT = __SndResultImp
  override type implNextT = __TImp
override def toString() : String = {"EPPartition_w_W.SndResult"}
    override def to : RRole = Role("dfs",RoleSet("DFS")) 
   override def l : String = "Result" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndResultImp(c,session)}

protected case class __SndResultImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndResult
}
  private var notUsed = true
def sndTodfs_DFS(m : MESSAGES.Partition.Result) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
__TImp(c,session)}
def !(m : MESSAGES.Partition.Result) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
__TImp(c,session)}
def snd(m : MESSAGES.Partition.Result) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
__TImp(c,session)}

}


protected  trait T extends EPPartition_w_W with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPPartition_w_W] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPartition_w_W.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



protected  trait End_w_W_PartitionFHandling extends EPPartition_w_W with event_lang.dsl.ChannelTypeEnd
protected  object End_w_W_PartitionFHandling extends End_w_W_PartitionFHandling {
  override protected def __children: List[EPPartition_w_W] = List()
  override type implT = __End_w_W_PartitionFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPartition_w_W.End_w_W_PartitionFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_w_W_PartitionFHandlingImp(c,session)}

protected case class __End_w_W_PartitionFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_w_W_PartitionFHandling
}
  
}


}

object Partition_W{
trait EPPartition_W extends __EPType_W

object EPPartition_W extends EPPartition_W with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPartition_W] = List(Hdl)
  override type implT = __EPPartition_WImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPartition_WImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("dfs",RoleSet("DFS"))) 
  override def argsP: Role = Role("w",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = RoleSet("W") 
  override def rootRole: Role = Role("dfs",RoleSet("DFS")) 
  override def name : String = "Partition"
}

protected case class __EPPartition_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPartition_W
}
  
}


protected  trait Hdl extends EPPartition_W with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPartition_W] = List(End_W_Partition,RcvDM)
  override type implT = __HdlImp
  override type implNextT = __End_W_PartitionImp
override def toString() : String = {"EPPartition_W.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_W_Partition extends EPPartition_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_Partition extends End_W_Partition {
  override protected def __children: List[EPPartition_W] = List()
  override type implT = __End_W_PartitionImp
  override type implNextT = Nothing
override def toString() : String = {"EPPartition_W.End_W_Partition"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_PartitionImp(c,session)}

protected case class __End_W_PartitionImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_Partition
}
  
}



  trait RcvDM extends EPPartition_W with event_lang.dsl.ChannelTypeRcv
  object RcvDM extends RcvDM {
  override protected def __children: List[EPPartition_W] = List(SpawnPartition)
  override type implT = __RcvDMImp
  override type implNextT = __SpawnPartitionImp
override def toString() : String = {"EPPartition_W.RcvDM"}
  override type msgT = MESSAGES.Partition.DM
   override def frm : Role = Role("dfs",RoleSet("DFS")) 
   override def l : String = "DM"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDMImp(c,session)}

protected case class __RcvDMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDM
}
  def rcvFrmdfs_DFS : (MESSAGES.Partition.DM,__SpawnPartitionImp) = {(c.rcv(Role("dfs",RoleSet("DFS"))).asInstanceOf[MESSAGES.Partition.DM],__SpawnPartitionImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Partition.DM,__SpawnPartitionImp),T]) : T = {
  f((c.rcv(Role("dfs",RoleSet("DFS"))).asInstanceOf[MESSAGES.Partition.DM],__SpawnPartitionImp(c,session))) 
}
def rcvMSG : MESSAGES.Partition.DM = {c.rcv(Role("dfs",RoleSet("DFS"))).asInstanceOf[MESSAGES.Partition.DM]}
def ? : MESSAGES.Partition.DM = {c.rcv(Role("dfs",RoleSet("DFS"))).asInstanceOf[MESSAGES.Partition.DM]}
def channelCon : __SpawnPartitionImp = {__SpawnPartitionImp(c,session)}

}


  trait SpawnPartition extends EPPartition_W with event_lang.dsl.ChannelTypeSpawn
  object SpawnPartition extends SpawnPartition {
  override protected def __children: List[EPPartition_W] = List(End_W_PartitionFHandling)
  override type implT = __SpawnPartitionImp
  override type implNextT = __End_W_PartitionFHandlingImp
override def toString() : String = {"EPPartition_W.SpawnPartition"}
    override def y: List[Role] = List(Role("dfs",RoleSet("DFS"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Partition" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPartitionImp(c,session)}

protected case class __SpawnPartitionImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPartition
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_W_PartitionFHandling extends EPPartition_W with event_lang.dsl.ChannelTypeEnd
protected  object End_W_PartitionFHandling extends End_W_PartitionFHandling {
  override protected def __children: List[EPPartition_W] = List()
  override type implT = __End_W_PartitionFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPartition_W.End_W_PartitionFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_W_PartitionFHandlingImp(c,session)}

protected case class __End_W_PartitionFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_W_PartitionFHandling
}
  
}


}

}

object DFS {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_dfs_DFS.EPMain_dfs_DFS,Partition_dfs_DFS.EPPartition_dfs_DFS)
trait __EPType_DFS extends AbstractChannelType {

}

trait EPType_DFS[T<: TState] extends AbstractEndPoint[__EPType_DFS,T] {
override val roleSet: RoleSet = RoleSet("DFS")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_dfs_DFS.EPMain_dfs_DFS,Partition_dfs_DFS.EPPartition_dfs_DFS)

}

object Main_dfs_DFS{
trait EPMain_dfs_DFS extends __EPType_DFS

object EPMain_dfs_DFS extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_dfs_DFS] = List(Hdl)
  override type implT = __EPMain_dfs_DFSImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_dfs_DFSImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("zk",RoleSet("ZK"))) 
  override def argsP: Role = Role("dfs",RoleSet("DFS")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("dfs",RoleSet("DFS")) 
  override def rootRole: Role = Role("zk",RoleSet("ZK")) 
  override def name : String = "Main"
}

protected case class __EPMain_dfs_DFSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_dfs_DFS
}
  
}


protected  trait Hdl extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_dfs_DFS] = List(RecT,End_dfs_DFS_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_dfs_DFS.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPMain_dfs_DFS] = List(SelSpawnEnd)
  override type implT = __RecTImp
  override type implNextT = __SelSpawnEndImp
override def toString() : String = {"EPMain_dfs_DFS.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelSpawnEnd extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeBrn
protected  object SelSpawnEnd extends SelSpawnEnd {
  override protected def __children: List[EPMain_dfs_DFS] = List(RcvSpawn,RcvEnd)
  override type implT = __SelSpawnEndImp
  override type implNextT = __RcvSpawnImp
override def toString() : String = {"EPMain_dfs_DFS.SelSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelSpawnEndImp(c,session)}

protected case class __SelSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelSpawnEnd
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvSpawn extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeRcv
  object RcvSpawn extends RcvSpawn {
  override protected def __children: List[EPMain_dfs_DFS] = List(SpawnPartition)
  override type implT = __RcvSpawnImp
  override type implNextT = __SpawnPartitionImp
override def toString() : String = {"EPMain_dfs_DFS.RcvSpawn"}
  override type msgT = MESSAGES.Main.Spawn
   override def frm : Role = Role("zk",RoleSet("ZK")) 
   override def l : String = "Spawn"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvSpawnImp(c,session)}

protected case class __RcvSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvSpawn
}
  def rcvFrmzk_ZK : (MESSAGES.Main.Spawn,__SpawnPartitionImp) = {(c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.Spawn],__SpawnPartitionImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.Spawn,__SpawnPartitionImp),T]) : T = {
  f((c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.Spawn],__SpawnPartitionImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.Spawn = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.Spawn]}
def ? : MESSAGES.Main.Spawn = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.Spawn]}
def channelCon : __SpawnPartitionImp = {__SpawnPartitionImp(c,session)}

}


  trait SpawnPartition extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeSpawn
  object SpawnPartition extends SpawnPartition {
  override protected def __children: List[EPMain_dfs_DFS] = List(T)
  override type implT = __SpawnPartitionImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_dfs_DFS.SpawnPartition"}
    override def y: List[Role] = List(Role("dfs",RoleSet("DFS"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Partition" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPartitionImp(c,session)}

protected case class __SpawnPartitionImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPartition
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPMain_dfs_DFS] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_dfs_DFS.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvEnd extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeRcv
  object RcvEnd extends RcvEnd {
  override protected def __children: List[EPMain_dfs_DFS] = List(End_dfs_DFS_MainEnd)
  override type implT = __RcvEndImp
  override type implNextT = __End_dfs_DFS_MainEndImp
override def toString() : String = {"EPMain_dfs_DFS.RcvEnd"}
  override type msgT = MESSAGES.Main.End
   override def frm : Role = Role("zk",RoleSet("ZK")) 
   override def l : String = "End"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvEndImp(c,session)}

protected case class __RcvEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvEnd
}
  def rcvFrmzk_ZK : (MESSAGES.Main.End,__End_dfs_DFS_MainEndImp) = {(c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.End],__End_dfs_DFS_MainEndImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.End,__End_dfs_DFS_MainEndImp),T]) : T = {
  f((c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.End],__End_dfs_DFS_MainEndImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.End = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.End]}
def ? : MESSAGES.Main.End = {c.rcv(Role("zk",RoleSet("ZK"))).asInstanceOf[MESSAGES.Main.End]}
def channelCon : __End_dfs_DFS_MainEndImp = {__End_dfs_DFS_MainEndImp(c,session)}

}


protected  trait End_dfs_DFS_MainEnd extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeEnd
protected  object End_dfs_DFS_MainEnd extends End_dfs_DFS_MainEnd {
  override protected def __children: List[EPMain_dfs_DFS] = List()
  override type implT = __End_dfs_DFS_MainEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_dfs_DFS.End_dfs_DFS_MainEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_dfs_DFS_MainEndImp(c,session)}

protected case class __End_dfs_DFS_MainEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_dfs_DFS_MainEnd
}
  
}



protected  trait End_dfs_DFS_MainFHandling extends EPMain_dfs_DFS with event_lang.dsl.ChannelTypeEnd
protected  object End_dfs_DFS_MainFHandling extends End_dfs_DFS_MainFHandling {
  override protected def __children: List[EPMain_dfs_DFS] = List()
  override type implT = __End_dfs_DFS_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_dfs_DFS.End_dfs_DFS_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_dfs_DFS_MainFHandlingImp(c,session)}

protected case class __End_dfs_DFS_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_dfs_DFS_MainFHandling
}
  
}


}

object Partition_dfs_DFS{
trait EPPartition_dfs_DFS extends __EPType_DFS

object EPPartition_dfs_DFS extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPPartition_dfs_DFS] = List(Hdl)
  override type implT = __EPPartition_dfs_DFSImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPPartition_dfs_DFSImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("dfs",RoleSet("DFS"))) 
  override def argsP: Role = Role("w",RoleSet("W")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("dfs",RoleSet("DFS")) 
  override def rootRole: Role = Role("dfs",RoleSet("DFS")) 
  override def name : String = "Partition"
}

protected case class __EPPartition_dfs_DFSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPPartition_dfs_DFS
}
  
}


protected  trait Hdl extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPPartition_dfs_DFS] = List(RecT,Failed_w_W)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPartition_dfs_DFS.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPPartition_dfs_DFS] = List(SndWorkLoad)
  override type implT = __RecTImp
  override type implNextT = __SndWorkLoadImp
override def toString() : String = {"EPPartition_dfs_DFS.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait SndWorkLoad extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeSnd
  object SndWorkLoad extends SndWorkLoad {
  override protected def __children: List[EPPartition_dfs_DFS] = List(RcvResult)
  override type implT = __SndWorkLoadImp
  override type implNextT = __RcvResultImp
override def toString() : String = {"EPPartition_dfs_DFS.SndWorkLoad"}
    override def to : RRole = Role("w",RoleSet("W")) 
   override def l : String = "WorkLoad" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndWorkLoadImp(c,session)}

protected case class __SndWorkLoadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndWorkLoad
}
  private var notUsed = true
def sndTow_W(m : MESSAGES.Partition.WorkLoad) : __RcvResultImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__RcvResultImp(c,session)}
def !(m : MESSAGES.Partition.WorkLoad) : __RcvResultImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__RcvResultImp(c,session)}
def snd(m : MESSAGES.Partition.WorkLoad) : __RcvResultImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("w",RoleSet("W")),m)
__RcvResultImp(c,session)}

}


  trait RcvResult extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeRcv
  object RcvResult extends RcvResult {
  override protected def __children: List[EPPartition_dfs_DFS] = List(T)
  override type implT = __RcvResultImp
  override type implNextT = __TImp
override def toString() : String = {"EPPartition_dfs_DFS.RcvResult"}
  override type msgT = MESSAGES.Partition.Result
   override def frm : Role = Role("w",RoleSet("W")) 
   override def l : String = "Result"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvResultImp(c,session)}

protected case class __RcvResultImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvResult
}
  def rcvFrmw_W : (MESSAGES.Partition.Result,__TImp) = {(c.rcv(Role("w",RoleSet("W"))).asInstanceOf[MESSAGES.Partition.Result],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Partition.Result,__TImp),T]) : T = {
  f((c.rcv(Role("w",RoleSet("W"))).asInstanceOf[MESSAGES.Partition.Result],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.Partition.Result = {c.rcv(Role("w",RoleSet("W"))).asInstanceOf[MESSAGES.Partition.Result]}
def ? : MESSAGES.Partition.Result = {c.rcv(Role("w",RoleSet("W"))).asInstanceOf[MESSAGES.Partition.Result]}
def channelCon : __TImp = {__TImp(c,session)}

}


protected  trait T extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPPartition_dfs_DFS] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPPartition_dfs_DFS.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait Failed_w_W extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeFDtct
  object Failed_w_W extends Failed_w_W {
  override protected def __children: List[EPPartition_dfs_DFS] = List(SndDM)
  override type implT = __Failed_w_WImp
  override type implNextT = __SndDMImp
override def toString() : String = {"EPPartition_dfs_DFS.Failed_w_W"}
  override def suspect : Role = Role("w",RoleSet("W")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_w_WImp(c,session)}

protected case class __Failed_w_WImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_w_W
}
  def failed_w_W(): __SndDMImp = {__SndDMImp(c,session)}

}


  trait SndDM extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeSnd
  object SndDM extends SndDM {
  override protected def __children: List[EPPartition_dfs_DFS] = List(SpawnPartition)
  override type implT = __SndDMImp
  override type implNextT = __SpawnPartitionImp
override def toString() : String = {"EPPartition_dfs_DFS.SndDM"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "DM" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDMImp(c,session)}

protected case class __SndDMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDM
}
  private var notUsed = true
def sndToW(m : MESSAGES.Partition.DM) : __SpawnPartitionImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnPartitionImp(c,session)}
def !(m : MESSAGES.Partition.DM) : __SpawnPartitionImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnPartitionImp(c,session)}
def snd(m : MESSAGES.Partition.DM) : __SpawnPartitionImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__SpawnPartitionImp(c,session)}

}


  trait SpawnPartition extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeSpawn
  object SpawnPartition extends SpawnPartition {
  override protected def __children: List[EPPartition_dfs_DFS] = List(End_dfs_DFS_PartitionFHandling)
  override type implT = __SpawnPartitionImp
  override type implNextT = __End_dfs_DFS_PartitionFHandlingImp
override def toString() : String = {"EPPartition_dfs_DFS.SpawnPartition"}
    override def y: List[Role] = List(Role("dfs",RoleSet("DFS"))) 
  override def pickR: RoleSet = RoleSet("W") 
  override def rs: List[RoleSet] = List(RoleSet("W")) 
  override def name: String = "Partition" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnPartitionImp(c,session)}

protected case class __SpawnPartitionImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnPartition
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_dfs_DFS_PartitionFHandling extends EPPartition_dfs_DFS with event_lang.dsl.ChannelTypeEnd
protected  object End_dfs_DFS_PartitionFHandling extends End_dfs_DFS_PartitionFHandling {
  override protected def __children: List[EPPartition_dfs_DFS] = List()
  override type implT = __End_dfs_DFS_PartitionFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPPartition_dfs_DFS.End_dfs_DFS_PartitionFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_dfs_DFS_PartitionFHandlingImp(c,session)}

protected case class __End_dfs_DFS_PartitionFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_dfs_DFS_PartitionFHandling
}
  
}


}

}

object ZK {
val subs : Seq[dsl.ChannelTypeSubS] = List(Main_zk_ZK.EPMain_zk_ZK)
trait __EPType_ZK extends AbstractChannelType {

}

trait EPType_ZK[T<: TState] extends AbstractEndPoint[__EPType_ZK,T] {
override val roleSet: RoleSet = RoleSet("ZK")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Main_zk_ZK.EPMain_zk_ZK)

}

object Main_zk_ZK{
trait EPMain_zk_ZK extends __EPType_ZK

object EPMain_zk_ZK extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_zk_ZK] = List(Hdl)
  override type implT = __EPMain_zk_ZKImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_zk_ZKImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("zk",RoleSet("ZK"))) 
  override def argsP: Role = Role("dfs",RoleSet("DFS")) 
  override def argsRs: List[RoleSet] = List(RoleSet("W")) 
  override def prjTo : RRole = Role("zk",RoleSet("ZK")) 
  override def rootRole: Role = Role("zk",RoleSet("ZK")) 
  override def name : String = "Main"
}

protected case class __EPMain_zk_ZKImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_zk_ZK
}
  
}


protected  trait Hdl extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_zk_ZK] = List(RecT,Failed_dfs_DFS)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_zk_ZK.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPMain_zk_ZK] = List(SelSpawnEnd)
  override type implT = __RecTImp
  override type implNextT = __SelSpawnEndImp
override def toString() : String = {"EPMain_zk_ZK.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelSpawnEnd extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSel
protected  object SelSpawnEnd extends SelSpawnEnd {
  override protected def __children: List[EPMain_zk_ZK] = List(SndSpawn,SndEnd)
  override type implT = __SelSpawnEndImp
  override type implNextT = __SndSpawnImp
override def toString() : String = {"EPMain_zk_ZK.SelSpawnEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelSpawnEndImp(c,session)}

protected case class __SelSpawnEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelSpawnEnd
}
  private var notUsed = true
def !(m : MESSAGES.Main.Spawn) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
 __SndPrepSpawnImp(c,session)}
def sndTodfs_DFS(m : MESSAGES.Main.Spawn) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
 __SndPrepSpawnImp(c,session)}

def !(m : MESSAGES.Main.End) : __SndEndPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
 __SndEndPImp(c,session)}
def sndTodfs_DFS(m : MESSAGES.Main.End) : __SndEndPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
 __SndEndPImp(c,session)}

}


  trait SndSpawn extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSnd
  object SndSpawn extends SndSpawn {
  override protected def __children: List[EPMain_zk_ZK] = List(SndPrepSpawn)
  override type implT = __SndSpawnImp
  override type implNextT = __SndPrepSpawnImp
override def toString() : String = {"EPMain_zk_ZK.SndSpawn"}
    override def to : RRole = Role("dfs",RoleSet("DFS")) 
   override def l : String = "Spawn" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndSpawnImp(c,session)}

protected case class __SndSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndSpawn
}
  private var notUsed = true
def sndTodfs_DFS(m : MESSAGES.Main.Spawn) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
__SndPrepSpawnImp(c,session)}
def !(m : MESSAGES.Main.Spawn) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
__SndPrepSpawnImp(c,session)}
def snd(m : MESSAGES.Main.Spawn) : __SndPrepSpawnImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
__SndPrepSpawnImp(c,session)}

}


  trait SndPrepSpawn extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSnd
  object SndPrepSpawn extends SndPrepSpawn {
  override protected def __children: List[EPMain_zk_ZK] = List(T)
  override type implT = __SndPrepSpawnImp
  override type implNextT = __TImp
override def toString() : String = {"EPMain_zk_ZK.SndPrepSpawn"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "PrepSpawn" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPrepSpawnImp(c,session)}

protected case class __SndPrepSpawnImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPrepSpawn
}
  private var notUsed = true
def sndToW(m : MESSAGES.Main.PrepSpawn) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__TImp(c,session)}
def !(m : MESSAGES.Main.PrepSpawn) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__TImp(c,session)}
def snd(m : MESSAGES.Main.PrepSpawn) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__TImp(c,session)}

}


protected  trait T extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPMain_zk_ZK] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPMain_zk_ZK.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait SndEnd extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSnd
  object SndEnd extends SndEnd {
  override protected def __children: List[EPMain_zk_ZK] = List(SndEndP)
  override type implT = __SndEndImp
  override type implNextT = __SndEndPImp
override def toString() : String = {"EPMain_zk_ZK.SndEnd"}
    override def to : RRole = Role("dfs",RoleSet("DFS")) 
   override def l : String = "End" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndImp(c,session)}

protected case class __SndEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEnd
}
  private var notUsed = true
def sndTodfs_DFS(m : MESSAGES.Main.End) : __SndEndPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
__SndEndPImp(c,session)}
def !(m : MESSAGES.Main.End) : __SndEndPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
__SndEndPImp(c,session)}
def snd(m : MESSAGES.Main.End) : __SndEndPImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("dfs",RoleSet("DFS")),m)
__SndEndPImp(c,session)}

}


  trait SndEndP extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSnd
  object SndEndP extends SndEndP {
  override protected def __children: List[EPMain_zk_ZK] = List(End_zk_ZK_MainEnd)
  override type implT = __SndEndPImp
  override type implNextT = __End_zk_ZK_MainEndImp
override def toString() : String = {"EPMain_zk_ZK.SndEndP"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "EndP" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndEndPImp(c,session)}

protected case class __SndEndPImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndEndP
}
  private var notUsed = true
def sndToW(m : MESSAGES.Main.EndP) : __End_zk_ZK_MainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_zk_ZK_MainEndImp(c,session)}
def !(m : MESSAGES.Main.EndP) : __End_zk_ZK_MainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_zk_ZK_MainEndImp(c,session)}
def snd(m : MESSAGES.Main.EndP) : __End_zk_ZK_MainEndImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_zk_ZK_MainEndImp(c,session)}

}


protected  trait End_zk_ZK_MainEnd extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeEnd
protected  object End_zk_ZK_MainEnd extends End_zk_ZK_MainEnd {
  override protected def __children: List[EPMain_zk_ZK] = List()
  override type implT = __End_zk_ZK_MainEndImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_zk_ZK.End_zk_ZK_MainEnd"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_zk_ZK_MainEndImp(c,session)}

protected case class __End_zk_ZK_MainEndImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_zk_ZK_MainEnd
}
  
}



  trait Failed_dfs_DFS extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeFDtct
  object Failed_dfs_DFS extends Failed_dfs_DFS {
  override protected def __children: List[EPMain_zk_ZK] = List(SndDM)
  override type implT = __Failed_dfs_DFSImp
  override type implNextT = __SndDMImp
override def toString() : String = {"EPMain_zk_ZK.Failed_dfs_DFS"}
  override def suspect : Role = Role("dfs",RoleSet("DFS")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_dfs_DFSImp(c,session)}

protected case class __Failed_dfs_DFSImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_dfs_DFS
}
  def failed_dfs_DFS(): __SndDMImp = {__SndDMImp(c,session)}

}


  trait SndDM extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeSnd
  object SndDM extends SndDM {
  override protected def __children: List[EPMain_zk_ZK] = List(End_zk_ZK_MainFHandling)
  override type implT = __SndDMImp
  override type implNextT = __End_zk_ZK_MainFHandlingImp
override def toString() : String = {"EPMain_zk_ZK.SndDM"}
    override def to : RRole = RoleSet("W") 
   override def l : String = "DM" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDMImp(c,session)}

protected case class __SndDMImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDM
}
  private var notUsed = true
def sndToW(m : MESSAGES.Main.DM) : __End_zk_ZK_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_zk_ZK_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.DM) : __End_zk_ZK_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_zk_ZK_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.DM) : __End_zk_ZK_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("W"),m)
__End_zk_ZK_MainFHandlingImp(c,session)}

}


protected  trait End_zk_ZK_MainFHandling extends EPMain_zk_ZK with event_lang.dsl.ChannelTypeEnd
protected  object End_zk_ZK_MainFHandling extends End_zk_ZK_MainFHandling {
  override protected def __children: List[EPMain_zk_ZK] = List()
  override type implT = __End_zk_ZK_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_zk_ZK.End_zk_ZK_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_zk_ZK_MainFHandlingImp(c,session)}

protected case class __End_zk_ZK_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_zk_ZK_MainFHandling
}
  
}


}

}

}
