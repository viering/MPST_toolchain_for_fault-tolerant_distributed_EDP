package example.streaming_popl_08
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object Streaming {
object RS {
val Consumer : RoleSet = RoleSet("Consumer")
val Kernel : RoleSet = RoleSet("Kernel")
val Data : RoleSet = RoleSet("Data")
val Key : RoleSet = RoleSet("Key")
}

object MESSAGES {
object Streaming {
case class Data(n:Int) extends MSG {
   override def l : String = "Data"
}

case class FStreamingk() extends MSG {
   override def l : String = "FStreamingk"
}

case class Result(n:Int) extends MSG {
   override def l : String = "Result"
}

case class Key(n:Int) extends MSG {
   override def l : String = "Key"
}

case class FStreamingd() extends MSG {
   override def l : String = "FStreamingd"
}

}

object SelKernel {
case class FSelKernelCons() extends MSG {
   override def l : String = "FSelKernelCons"
}

case class FSelKernelk() extends MSG {
   override def l : String = "FSelKernelk"
}

}

object Main {
case class FMainCons() extends MSG {
   override def l : String = "FMainCons"
}

case class FMainKern() extends MSG {
   override def l : String = "FMainKern"
}

}

}

object PROTOCOLS {
object Streaming {
val c_Consumer = Role("c",RoleSet("Consumer"))
val d_Data = Role("d",RoleSet("Data"))
val k_Key = Role("k",RoleSet("Key"))
val kernel_Kernel = Role("kernel",RoleSet("Kernel"))
}

object SelKernel {
val kernel_Kernel = Role("kernel",RoleSet("Kernel"))
val Consumer = RoleSet("Consumer")
val d_Data = Role("d",RoleSet("Data"))
val k_Key = Role("k",RoleSet("Key"))
}

object Main {
val k_Key = Role("k",RoleSet("Key"))
val Kernel = RoleSet("Kernel")
val Consumer = RoleSet("Consumer")
val d_Data = Role("d",RoleSet("Data"))
}

}

object Consumer {
val subs : Seq[dsl.ChannelTypeSubS] = List(Streaming_c_Consumer.EPStreaming_c_Consumer,SelKernel_Consumer.EPSelKernel_Consumer,Main_Consumer.EPMain_Consumer)
trait __EPType_Consumer extends AbstractChannelType {

}

trait EPType_Consumer[T<: TState] extends AbstractEndPoint[__EPType_Consumer,T] {
override val roleSet: RoleSet = RoleSet("Consumer")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Streaming_c_Consumer.EPStreaming_c_Consumer,SelKernel_Consumer.EPSelKernel_Consumer,Main_Consumer.EPMain_Consumer)

}

object Streaming_c_Consumer{
trait EPStreaming_c_Consumer extends __EPType_Consumer

object EPStreaming_c_Consumer extends EPStreaming_c_Consumer with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStreaming_c_Consumer] = List(Hdl)
  override type implT = __EPStreaming_c_ConsumerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStreaming_c_ConsumerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key")), Role("kernel",RoleSet("Kernel"))) 
  override def argsP: Role = Role("c",RoleSet("Consumer")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("c",RoleSet("Consumer")) 
  override def rootRole: Role = Role("kernel",RoleSet("Kernel")) 
  override def name : String = "Streaming"
}

protected case class __EPStreaming_c_ConsumerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStreaming_c_Consumer
}
  
}


protected  trait Hdl extends EPStreaming_c_Consumer with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStreaming_c_Consumer] = List(RecT,End_c_Consumer_StreamingFHandling)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStreaming_c_Consumer.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPStreaming_c_Consumer with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPStreaming_c_Consumer] = List(RcvResult)
  override type implT = __RecTImp
  override type implNextT = __RcvResultImp
override def toString() : String = {"EPStreaming_c_Consumer.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait RcvResult extends EPStreaming_c_Consumer with event_lang.dsl.ChannelTypeRcv
  object RcvResult extends RcvResult {
  override protected def __children: List[EPStreaming_c_Consumer] = List(T)
  override type implT = __RcvResultImp
  override type implNextT = __TImp
override def toString() : String = {"EPStreaming_c_Consumer.RcvResult"}
  override type msgT = MESSAGES.Streaming.Result
   override def frm : Role = Role("kernel",RoleSet("Kernel")) 
   override def l : String = "Result"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvResultImp(c,session)}

protected case class __RcvResultImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvResult
}
  def rcvFrmkernel_Kernel : (MESSAGES.Streaming.Result,__TImp) = {(c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.Result],__TImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Streaming.Result,__TImp),T]) : T = {
  f((c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.Result],__TImp(c,session))) 
}
def rcvMSG : MESSAGES.Streaming.Result = {c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.Result]}
def ? : MESSAGES.Streaming.Result = {c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.Result]}
def channelCon : __TImp = {__TImp(c,session)}

}


protected  trait T extends EPStreaming_c_Consumer with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPStreaming_c_Consumer] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStreaming_c_Consumer.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



protected  trait End_c_Consumer_StreamingFHandling extends EPStreaming_c_Consumer with event_lang.dsl.ChannelTypeEnd
protected  object End_c_Consumer_StreamingFHandling extends End_c_Consumer_StreamingFHandling {
  override protected def __children: List[EPStreaming_c_Consumer] = List()
  override type implT = __End_c_Consumer_StreamingFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStreaming_c_Consumer.End_c_Consumer_StreamingFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_Consumer_StreamingFHandlingImp(c,session)}

protected case class __End_c_Consumer_StreamingFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_Consumer_StreamingFHandling
}
  
}


}

object SelKernel_Consumer{
trait EPSelKernel_Consumer extends __EPType_Consumer

object EPSelKernel_Consumer extends EPSelKernel_Consumer with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelKernel_Consumer] = List(Hdl)
  override type implT = __EPSelKernel_ConsumerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelKernel_ConsumerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key"))) 
  override def argsP: Role = Role("kernel",RoleSet("Kernel")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Consumer")) 
  override def prjTo : RRole = RoleSet("Consumer") 
  override def rootRole: Role = Role("d",RoleSet("Data")) 
  override def name : String = "SelKernel"
}

protected case class __EPSelKernel_ConsumerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelKernel_Consumer
}
  
}


protected  trait Hdl extends EPSelKernel_Consumer with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelKernel_Consumer] = List(SpawnStreaming,RcvFSelKernelCons)
  override type implT = __HdlImp
  override type implNextT = __SpawnStreamingImp
override def toString() : String = {"EPSelKernel_Consumer.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnStreaming extends EPSelKernel_Consumer with event_lang.dsl.ChannelTypeSpawn
  object SpawnStreaming extends SpawnStreaming {
  override protected def __children: List[EPSelKernel_Consumer] = List(End_Consumer_SelKernel)
  override type implT = __SpawnStreamingImp
  override type implNextT = __End_Consumer_SelKernelImp
override def toString() : String = {"EPSelKernel_Consumer.SpawnStreaming"}
    override def y: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key")), Role("kernel",RoleSet("Kernel"))) 
  override def pickR: RoleSet = RoleSet("Consumer") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "Streaming" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStreamingImp(c,session)}

protected case class __SpawnStreamingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStreaming
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_Consumer_SelKernel extends EPSelKernel_Consumer with event_lang.dsl.ChannelTypeEnd
protected  object End_Consumer_SelKernel extends End_Consumer_SelKernel {
  override protected def __children: List[EPSelKernel_Consumer] = List()
  override type implT = __End_Consumer_SelKernelImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelKernel_Consumer.End_Consumer_SelKernel"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Consumer_SelKernelImp(c,session)}

protected case class __End_Consumer_SelKernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Consumer_SelKernel
}
  
}



  trait RcvFSelKernelCons extends EPSelKernel_Consumer with event_lang.dsl.ChannelTypeRcv
  object RcvFSelKernelCons extends RcvFSelKernelCons {
  override protected def __children: List[EPSelKernel_Consumer] = List(End_Consumer_SelKernelFHandling)
  override type implT = __RcvFSelKernelConsImp
  override type implNextT = __End_Consumer_SelKernelFHandlingImp
override def toString() : String = {"EPSelKernel_Consumer.RcvFSelKernelCons"}
  override type msgT = MESSAGES.SelKernel.FSelKernelCons
   override def frm : Role = Role("d",RoleSet("Data")) 
   override def l : String = "FSelKernelCons"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFSelKernelConsImp(c,session)}

protected case class __RcvFSelKernelConsImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFSelKernelCons
}
  def rcvFrmd_Data : (MESSAGES.SelKernel.FSelKernelCons,__End_Consumer_SelKernelFHandlingImp) = {(c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.SelKernel.FSelKernelCons],__End_Consumer_SelKernelFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SelKernel.FSelKernelCons,__End_Consumer_SelKernelFHandlingImp),T]) : T = {
  f((c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.SelKernel.FSelKernelCons],__End_Consumer_SelKernelFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.SelKernel.FSelKernelCons = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.SelKernel.FSelKernelCons]}
def ? : MESSAGES.SelKernel.FSelKernelCons = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.SelKernel.FSelKernelCons]}
def channelCon : __End_Consumer_SelKernelFHandlingImp = {__End_Consumer_SelKernelFHandlingImp(c,session)}

}


protected  trait End_Consumer_SelKernelFHandling extends EPSelKernel_Consumer with event_lang.dsl.ChannelTypeEnd
protected  object End_Consumer_SelKernelFHandling extends End_Consumer_SelKernelFHandling {
  override protected def __children: List[EPSelKernel_Consumer] = List()
  override type implT = __End_Consumer_SelKernelFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelKernel_Consumer.End_Consumer_SelKernelFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Consumer_SelKernelFHandlingImp(c,session)}

protected case class __End_Consumer_SelKernelFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Consumer_SelKernelFHandling
}
  
}


}

object Main_Consumer{
trait EPMain_Consumer extends __EPType_Consumer

object EPMain_Consumer extends EPMain_Consumer with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_Consumer] = List(Hdl)
  override type implT = __EPMain_ConsumerImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_ConsumerImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data"))) 
  override def argsP: Role = Role("k",RoleSet("Key")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Kernel"), RoleSet("Consumer")) 
  override def prjTo : RRole = RoleSet("Consumer") 
  override def rootRole: Role = Role("d",RoleSet("Data")) 
  override def name : String = "Main"
}

protected case class __EPMain_ConsumerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_Consumer
}
  
}


protected  trait Hdl extends EPMain_Consumer with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_Consumer] = List(SpawnSelKernel,RcvFMainCons)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelKernelImp
override def toString() : String = {"EPMain_Consumer.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelKernel extends EPMain_Consumer with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelKernel extends SpawnSelKernel {
  override protected def __children: List[EPMain_Consumer] = List(End_Consumer_Main)
  override type implT = __SpawnSelKernelImp
  override type implNextT = __End_Consumer_MainImp
override def toString() : String = {"EPMain_Consumer.SpawnSelKernel"}
    override def y: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key"))) 
  override def pickR: RoleSet = RoleSet("Kernel") 
  override def rs: List[RoleSet] = List(RoleSet("Consumer")) 
  override def name: String = "SelKernel" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelKernelImp(c,session)}

protected case class __SpawnSelKernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelKernel
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_Consumer_Main extends EPMain_Consumer with event_lang.dsl.ChannelTypeEnd
protected  object End_Consumer_Main extends End_Consumer_Main {
  override protected def __children: List[EPMain_Consumer] = List()
  override type implT = __End_Consumer_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Consumer.End_Consumer_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Consumer_MainImp(c,session)}

protected case class __End_Consumer_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Consumer_Main
}
  
}



  trait RcvFMainCons extends EPMain_Consumer with event_lang.dsl.ChannelTypeRcv
  object RcvFMainCons extends RcvFMainCons {
  override protected def __children: List[EPMain_Consumer] = List(End_Consumer_MainFHandling)
  override type implT = __RcvFMainConsImp
  override type implNextT = __End_Consumer_MainFHandlingImp
override def toString() : String = {"EPMain_Consumer.RcvFMainCons"}
  override type msgT = MESSAGES.Main.FMainCons
   override def frm : Role = Role("d",RoleSet("Data")) 
   override def l : String = "FMainCons"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFMainConsImp(c,session)}

protected case class __RcvFMainConsImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFMainCons
}
  def rcvFrmd_Data : (MESSAGES.Main.FMainCons,__End_Consumer_MainFHandlingImp) = {(c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Main.FMainCons],__End_Consumer_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FMainCons,__End_Consumer_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Main.FMainCons],__End_Consumer_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FMainCons = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Main.FMainCons]}
def ? : MESSAGES.Main.FMainCons = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Main.FMainCons]}
def channelCon : __End_Consumer_MainFHandlingImp = {__End_Consumer_MainFHandlingImp(c,session)}

}


protected  trait End_Consumer_MainFHandling extends EPMain_Consumer with event_lang.dsl.ChannelTypeEnd
protected  object End_Consumer_MainFHandling extends End_Consumer_MainFHandling {
  override protected def __children: List[EPMain_Consumer] = List()
  override type implT = __End_Consumer_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Consumer.End_Consumer_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Consumer_MainFHandlingImp(c,session)}

protected case class __End_Consumer_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Consumer_MainFHandling
}
  
}


}

}

object Kernel {
val subs : Seq[dsl.ChannelTypeSubS] = List(Streaming_kernel_Kernel.EPStreaming_kernel_Kernel,SelKernel_kernel_Kernel.EPSelKernel_kernel_Kernel,Main_Kernel.EPMain_Kernel)
trait __EPType_Kernel extends AbstractChannelType {

}

trait EPType_Kernel[T<: TState] extends AbstractEndPoint[__EPType_Kernel,T] {
override val roleSet: RoleSet = RoleSet("Kernel")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Streaming_kernel_Kernel.EPStreaming_kernel_Kernel,SelKernel_kernel_Kernel.EPSelKernel_kernel_Kernel,Main_Kernel.EPMain_Kernel)

}

object Streaming_kernel_Kernel{
trait EPStreaming_kernel_Kernel extends __EPType_Kernel

object EPStreaming_kernel_Kernel extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(Hdl)
  override type implT = __EPStreaming_kernel_KernelImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStreaming_kernel_KernelImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key")), Role("kernel",RoleSet("Kernel"))) 
  override def argsP: Role = Role("c",RoleSet("Consumer")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("kernel",RoleSet("Kernel")) 
  override def rootRole: Role = Role("kernel",RoleSet("Kernel")) 
  override def name : String = "Streaming"
}

protected case class __EPStreaming_kernel_KernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStreaming_kernel_Kernel
}
  
}


protected  trait Hdl extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(RecT,Failed_c_Consumer)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStreaming_kernel_Kernel.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(RcvData)
  override type implT = __RecTImp
  override type implNextT = __RcvDataImp
override def toString() : String = {"EPStreaming_kernel_Kernel.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait RcvData extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeRcv
  object RcvData extends RcvData {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(RcvKey)
  override type implT = __RcvDataImp
  override type implNextT = __RcvKeyImp
override def toString() : String = {"EPStreaming_kernel_Kernel.RcvData"}
  override type msgT = MESSAGES.Streaming.Data
   override def frm : Role = Role("d",RoleSet("Data")) 
   override def l : String = "Data"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDataImp(c,session)}

protected case class __RcvDataImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvData
}
  def rcvFrmd_Data : (MESSAGES.Streaming.Data,__RcvKeyImp) = {(c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Streaming.Data],__RcvKeyImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Streaming.Data,__RcvKeyImp),T]) : T = {
  f((c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Streaming.Data],__RcvKeyImp(c,session))) 
}
def rcvMSG : MESSAGES.Streaming.Data = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Streaming.Data]}
def ? : MESSAGES.Streaming.Data = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Streaming.Data]}
def channelCon : __RcvKeyImp = {__RcvKeyImp(c,session)}

}


  trait RcvKey extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeRcv
  object RcvKey extends RcvKey {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(SndResult)
  override type implT = __RcvKeyImp
  override type implNextT = __SndResultImp
override def toString() : String = {"EPStreaming_kernel_Kernel.RcvKey"}
  override type msgT = MESSAGES.Streaming.Key
   override def frm : Role = Role("k",RoleSet("Key")) 
   override def l : String = "Key"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvKeyImp(c,session)}

protected case class __RcvKeyImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvKey
}
  def rcvFrmk_Key : (MESSAGES.Streaming.Key,__SndResultImp) = {(c.rcv(Role("k",RoleSet("Key"))).asInstanceOf[MESSAGES.Streaming.Key],__SndResultImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Streaming.Key,__SndResultImp),T]) : T = {
  f((c.rcv(Role("k",RoleSet("Key"))).asInstanceOf[MESSAGES.Streaming.Key],__SndResultImp(c,session))) 
}
def rcvMSG : MESSAGES.Streaming.Key = {c.rcv(Role("k",RoleSet("Key"))).asInstanceOf[MESSAGES.Streaming.Key]}
def ? : MESSAGES.Streaming.Key = {c.rcv(Role("k",RoleSet("Key"))).asInstanceOf[MESSAGES.Streaming.Key]}
def channelCon : __SndResultImp = {__SndResultImp(c,session)}

}


  trait SndResult extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeSnd
  object SndResult extends SndResult {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(T)
  override type implT = __SndResultImp
  override type implNextT = __TImp
override def toString() : String = {"EPStreaming_kernel_Kernel.SndResult"}
    override def to : RRole = Role("c",RoleSet("Consumer")) 
   override def l : String = "Result" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndResultImp(c,session)}

protected case class __SndResultImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndResult
}
  private var notUsed = true
def sndToc_Consumer(m : MESSAGES.Streaming.Result) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Consumer")),m)
__TImp(c,session)}
def !(m : MESSAGES.Streaming.Result) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Consumer")),m)
__TImp(c,session)}
def snd(m : MESSAGES.Streaming.Result) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("Consumer")),m)
__TImp(c,session)}

}


protected  trait T extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStreaming_kernel_Kernel.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait Failed_c_Consumer extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeFDtct
  object Failed_c_Consumer extends Failed_c_Consumer {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(SndFStreamingk)
  override type implT = __Failed_c_ConsumerImp
  override type implNextT = __SndFStreamingkImp
override def toString() : String = {"EPStreaming_kernel_Kernel.Failed_c_Consumer"}
  override def suspect : Role = Role("c",RoleSet("Consumer")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_c_ConsumerImp(c,session)}

protected case class __Failed_c_ConsumerImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_c_Consumer
}
  def failed_c_Consumer(): __SndFStreamingkImp = {__SndFStreamingkImp(c,session)}

}


  trait SndFStreamingk extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeSnd
  object SndFStreamingk extends SndFStreamingk {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(SndFStreamingd)
  override type implT = __SndFStreamingkImp
  override type implNextT = __SndFStreamingdImp
override def toString() : String = {"EPStreaming_kernel_Kernel.SndFStreamingk"}
    override def to : RRole = Role("k",RoleSet("Key")) 
   override def l : String = "FStreamingk" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFStreamingkImp(c,session)}

protected case class __SndFStreamingkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFStreamingk
}
  private var notUsed = true
def sndTok_Key(m : MESSAGES.Streaming.FStreamingk) : __SndFStreamingdImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("k",RoleSet("Key")),m)
__SndFStreamingdImp(c,session)}
def !(m : MESSAGES.Streaming.FStreamingk) : __SndFStreamingdImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("k",RoleSet("Key")),m)
__SndFStreamingdImp(c,session)}
def snd(m : MESSAGES.Streaming.FStreamingk) : __SndFStreamingdImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("k",RoleSet("Key")),m)
__SndFStreamingdImp(c,session)}

}


  trait SndFStreamingd extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeSnd
  object SndFStreamingd extends SndFStreamingd {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List(End_kernel_Kernel_StreamingFHandling)
  override type implT = __SndFStreamingdImp
  override type implNextT = __End_kernel_Kernel_StreamingFHandlingImp
override def toString() : String = {"EPStreaming_kernel_Kernel.SndFStreamingd"}
    override def to : RRole = Role("d",RoleSet("Data")) 
   override def l : String = "FStreamingd" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFStreamingdImp(c,session)}

protected case class __SndFStreamingdImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFStreamingd
}
  private var notUsed = true
def sndTod_Data(m : MESSAGES.Streaming.FStreamingd) : __End_kernel_Kernel_StreamingFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("d",RoleSet("Data")),m)
__End_kernel_Kernel_StreamingFHandlingImp(c,session)}
def !(m : MESSAGES.Streaming.FStreamingd) : __End_kernel_Kernel_StreamingFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("d",RoleSet("Data")),m)
__End_kernel_Kernel_StreamingFHandlingImp(c,session)}
def snd(m : MESSAGES.Streaming.FStreamingd) : __End_kernel_Kernel_StreamingFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("d",RoleSet("Data")),m)
__End_kernel_Kernel_StreamingFHandlingImp(c,session)}

}


protected  trait End_kernel_Kernel_StreamingFHandling extends EPStreaming_kernel_Kernel with event_lang.dsl.ChannelTypeEnd
protected  object End_kernel_Kernel_StreamingFHandling extends End_kernel_Kernel_StreamingFHandling {
  override protected def __children: List[EPStreaming_kernel_Kernel] = List()
  override type implT = __End_kernel_Kernel_StreamingFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStreaming_kernel_Kernel.End_kernel_Kernel_StreamingFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_kernel_Kernel_StreamingFHandlingImp(c,session)}

protected case class __End_kernel_Kernel_StreamingFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_kernel_Kernel_StreamingFHandling
}
  
}


}

object SelKernel_kernel_Kernel{
trait EPSelKernel_kernel_Kernel extends __EPType_Kernel

object EPSelKernel_kernel_Kernel extends EPSelKernel_kernel_Kernel with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelKernel_kernel_Kernel] = List(Hdl)
  override type implT = __EPSelKernel_kernel_KernelImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelKernel_kernel_KernelImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key"))) 
  override def argsP: Role = Role("kernel",RoleSet("Kernel")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Consumer")) 
  override def prjTo : RRole = Role("kernel",RoleSet("Kernel")) 
  override def rootRole: Role = Role("d",RoleSet("Data")) 
  override def name : String = "SelKernel"
}

protected case class __EPSelKernel_kernel_KernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelKernel_kernel_Kernel
}
  
}


protected  trait Hdl extends EPSelKernel_kernel_Kernel with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelKernel_kernel_Kernel] = List(SpawnStreaming,End_kernel_Kernel_SelKernelFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnStreamingImp
override def toString() : String = {"EPSelKernel_kernel_Kernel.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnStreaming extends EPSelKernel_kernel_Kernel with event_lang.dsl.ChannelTypeSpawn
  object SpawnStreaming extends SpawnStreaming {
  override protected def __children: List[EPSelKernel_kernel_Kernel] = List(End_kernel_Kernel_SelKernel)
  override type implT = __SpawnStreamingImp
  override type implNextT = __End_kernel_Kernel_SelKernelImp
override def toString() : String = {"EPSelKernel_kernel_Kernel.SpawnStreaming"}
    override def y: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key")), Role("kernel",RoleSet("Kernel"))) 
  override def pickR: RoleSet = RoleSet("Consumer") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "Streaming" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStreamingImp(c,session)}

protected case class __SpawnStreamingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStreaming
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_kernel_Kernel_SelKernel extends EPSelKernel_kernel_Kernel with event_lang.dsl.ChannelTypeEnd
protected  object End_kernel_Kernel_SelKernel extends End_kernel_Kernel_SelKernel {
  override protected def __children: List[EPSelKernel_kernel_Kernel] = List()
  override type implT = __End_kernel_Kernel_SelKernelImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelKernel_kernel_Kernel.End_kernel_Kernel_SelKernel"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_kernel_Kernel_SelKernelImp(c,session)}

protected case class __End_kernel_Kernel_SelKernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_kernel_Kernel_SelKernel
}
  
}



protected  trait End_kernel_Kernel_SelKernelFHandling extends EPSelKernel_kernel_Kernel with event_lang.dsl.ChannelTypeEnd
protected  object End_kernel_Kernel_SelKernelFHandling extends End_kernel_Kernel_SelKernelFHandling {
  override protected def __children: List[EPSelKernel_kernel_Kernel] = List()
  override type implT = __End_kernel_Kernel_SelKernelFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelKernel_kernel_Kernel.End_kernel_Kernel_SelKernelFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_kernel_Kernel_SelKernelFHandlingImp(c,session)}

protected case class __End_kernel_Kernel_SelKernelFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_kernel_Kernel_SelKernelFHandling
}
  
}


}

object Main_Kernel{
trait EPMain_Kernel extends __EPType_Kernel

object EPMain_Kernel extends EPMain_Kernel with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_Kernel] = List(Hdl)
  override type implT = __EPMain_KernelImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_KernelImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data"))) 
  override def argsP: Role = Role("k",RoleSet("Key")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Kernel"), RoleSet("Consumer")) 
  override def prjTo : RRole = RoleSet("Kernel") 
  override def rootRole: Role = Role("d",RoleSet("Data")) 
  override def name : String = "Main"
}

protected case class __EPMain_KernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_Kernel
}
  
}


protected  trait Hdl extends EPMain_Kernel with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_Kernel] = List(SpawnSelKernel,RcvFMainKern)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelKernelImp
override def toString() : String = {"EPMain_Kernel.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelKernel extends EPMain_Kernel with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelKernel extends SpawnSelKernel {
  override protected def __children: List[EPMain_Kernel] = List(End_Kernel_Main)
  override type implT = __SpawnSelKernelImp
  override type implNextT = __End_Kernel_MainImp
override def toString() : String = {"EPMain_Kernel.SpawnSelKernel"}
    override def y: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key"))) 
  override def pickR: RoleSet = RoleSet("Kernel") 
  override def rs: List[RoleSet] = List(RoleSet("Consumer")) 
  override def name: String = "SelKernel" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelKernelImp(c,session)}

protected case class __SpawnSelKernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelKernel
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_Kernel_Main extends EPMain_Kernel with event_lang.dsl.ChannelTypeEnd
protected  object End_Kernel_Main extends End_Kernel_Main {
  override protected def __children: List[EPMain_Kernel] = List()
  override type implT = __End_Kernel_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Kernel.End_Kernel_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Kernel_MainImp(c,session)}

protected case class __End_Kernel_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Kernel_Main
}
  
}



  trait RcvFMainKern extends EPMain_Kernel with event_lang.dsl.ChannelTypeRcv
  object RcvFMainKern extends RcvFMainKern {
  override protected def __children: List[EPMain_Kernel] = List(End_Kernel_MainFHandling)
  override type implT = __RcvFMainKernImp
  override type implNextT = __End_Kernel_MainFHandlingImp
override def toString() : String = {"EPMain_Kernel.RcvFMainKern"}
  override type msgT = MESSAGES.Main.FMainKern
   override def frm : Role = Role("d",RoleSet("Data")) 
   override def l : String = "FMainKern"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFMainKernImp(c,session)}

protected case class __RcvFMainKernImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFMainKern
}
  def rcvFrmd_Data : (MESSAGES.Main.FMainKern,__End_Kernel_MainFHandlingImp) = {(c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Main.FMainKern],__End_Kernel_MainFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Main.FMainKern,__End_Kernel_MainFHandlingImp),T]) : T = {
  f((c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Main.FMainKern],__End_Kernel_MainFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Main.FMainKern = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Main.FMainKern]}
def ? : MESSAGES.Main.FMainKern = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.Main.FMainKern]}
def channelCon : __End_Kernel_MainFHandlingImp = {__End_Kernel_MainFHandlingImp(c,session)}

}


protected  trait End_Kernel_MainFHandling extends EPMain_Kernel with event_lang.dsl.ChannelTypeEnd
protected  object End_Kernel_MainFHandling extends End_Kernel_MainFHandling {
  override protected def __children: List[EPMain_Kernel] = List()
  override type implT = __End_Kernel_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_Kernel.End_Kernel_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_Kernel_MainFHandlingImp(c,session)}

protected case class __End_Kernel_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_Kernel_MainFHandling
}
  
}


}

}

object Data {
val subs : Seq[dsl.ChannelTypeSubS] = List(Streaming_d_Data.EPStreaming_d_Data,SelKernel_d_Data.EPSelKernel_d_Data,Main_d_Data.EPMain_d_Data)
trait __EPType_Data extends AbstractChannelType {

}

trait EPType_Data[T<: TState] extends AbstractEndPoint[__EPType_Data,T] {
override val roleSet: RoleSet = RoleSet("Data")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Streaming_d_Data.EPStreaming_d_Data,SelKernel_d_Data.EPSelKernel_d_Data,Main_d_Data.EPMain_d_Data)

}

object Streaming_d_Data{
trait EPStreaming_d_Data extends __EPType_Data

object EPStreaming_d_Data extends EPStreaming_d_Data with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStreaming_d_Data] = List(Hdl)
  override type implT = __EPStreaming_d_DataImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStreaming_d_DataImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key")), Role("kernel",RoleSet("Kernel"))) 
  override def argsP: Role = Role("c",RoleSet("Consumer")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("d",RoleSet("Data")) 
  override def rootRole: Role = Role("kernel",RoleSet("Kernel")) 
  override def name : String = "Streaming"
}

protected case class __EPStreaming_d_DataImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStreaming_d_Data
}
  
}


protected  trait Hdl extends EPStreaming_d_Data with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStreaming_d_Data] = List(RecT,RcvFStreamingd)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStreaming_d_Data.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPStreaming_d_Data with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPStreaming_d_Data] = List(SndData)
  override type implT = __RecTImp
  override type implNextT = __SndDataImp
override def toString() : String = {"EPStreaming_d_Data.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait SndData extends EPStreaming_d_Data with event_lang.dsl.ChannelTypeSnd
  object SndData extends SndData {
  override protected def __children: List[EPStreaming_d_Data] = List(T)
  override type implT = __SndDataImp
  override type implNextT = __TImp
override def toString() : String = {"EPStreaming_d_Data.SndData"}
    override def to : RRole = Role("kernel",RoleSet("Kernel")) 
   override def l : String = "Data" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDataImp(c,session)}

protected case class __SndDataImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndData
}
  private var notUsed = true
def sndTokernel_Kernel(m : MESSAGES.Streaming.Data) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("kernel",RoleSet("Kernel")),m)
__TImp(c,session)}
def !(m : MESSAGES.Streaming.Data) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("kernel",RoleSet("Kernel")),m)
__TImp(c,session)}
def snd(m : MESSAGES.Streaming.Data) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("kernel",RoleSet("Kernel")),m)
__TImp(c,session)}

}


protected  trait T extends EPStreaming_d_Data with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPStreaming_d_Data] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStreaming_d_Data.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvFStreamingd extends EPStreaming_d_Data with event_lang.dsl.ChannelTypeRcv
  object RcvFStreamingd extends RcvFStreamingd {
  override protected def __children: List[EPStreaming_d_Data] = List(End_d_Data_StreamingFHandling)
  override type implT = __RcvFStreamingdImp
  override type implNextT = __End_d_Data_StreamingFHandlingImp
override def toString() : String = {"EPStreaming_d_Data.RcvFStreamingd"}
  override type msgT = MESSAGES.Streaming.FStreamingd
   override def frm : Role = Role("kernel",RoleSet("Kernel")) 
   override def l : String = "FStreamingd"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFStreamingdImp(c,session)}

protected case class __RcvFStreamingdImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFStreamingd
}
  def rcvFrmkernel_Kernel : (MESSAGES.Streaming.FStreamingd,__End_d_Data_StreamingFHandlingImp) = {(c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.FStreamingd],__End_d_Data_StreamingFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Streaming.FStreamingd,__End_d_Data_StreamingFHandlingImp),T]) : T = {
  f((c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.FStreamingd],__End_d_Data_StreamingFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Streaming.FStreamingd = {c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.FStreamingd]}
def ? : MESSAGES.Streaming.FStreamingd = {c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.FStreamingd]}
def channelCon : __End_d_Data_StreamingFHandlingImp = {__End_d_Data_StreamingFHandlingImp(c,session)}

}


protected  trait End_d_Data_StreamingFHandling extends EPStreaming_d_Data with event_lang.dsl.ChannelTypeEnd
protected  object End_d_Data_StreamingFHandling extends End_d_Data_StreamingFHandling {
  override protected def __children: List[EPStreaming_d_Data] = List()
  override type implT = __End_d_Data_StreamingFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStreaming_d_Data.End_d_Data_StreamingFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_d_Data_StreamingFHandlingImp(c,session)}

protected case class __End_d_Data_StreamingFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_d_Data_StreamingFHandling
}
  
}


}

object SelKernel_d_Data{
trait EPSelKernel_d_Data extends __EPType_Data

object EPSelKernel_d_Data extends EPSelKernel_d_Data with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelKernel_d_Data] = List(Hdl)
  override type implT = __EPSelKernel_d_DataImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelKernel_d_DataImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key"))) 
  override def argsP: Role = Role("kernel",RoleSet("Kernel")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Consumer")) 
  override def prjTo : RRole = Role("d",RoleSet("Data")) 
  override def rootRole: Role = Role("d",RoleSet("Data")) 
  override def name : String = "SelKernel"
}

protected case class __EPSelKernel_d_DataImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelKernel_d_Data
}
  
}


protected  trait Hdl extends EPSelKernel_d_Data with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelKernel_d_Data] = List(SpawnStreaming,Failed_kernel_Kernel)
  override type implT = __HdlImp
  override type implNextT = __SpawnStreamingImp
override def toString() : String = {"EPSelKernel_d_Data.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnStreaming extends EPSelKernel_d_Data with event_lang.dsl.ChannelTypeSpawn
  object SpawnStreaming extends SpawnStreaming {
  override protected def __children: List[EPSelKernel_d_Data] = List(End_d_Data_SelKernel)
  override type implT = __SpawnStreamingImp
  override type implNextT = __End_d_Data_SelKernelImp
override def toString() : String = {"EPSelKernel_d_Data.SpawnStreaming"}
    override def y: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key")), Role("kernel",RoleSet("Kernel"))) 
  override def pickR: RoleSet = RoleSet("Consumer") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "Streaming" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStreamingImp(c,session)}

protected case class __SpawnStreamingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStreaming
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_d_Data_SelKernel extends EPSelKernel_d_Data with event_lang.dsl.ChannelTypeEnd
protected  object End_d_Data_SelKernel extends End_d_Data_SelKernel {
  override protected def __children: List[EPSelKernel_d_Data] = List()
  override type implT = __End_d_Data_SelKernelImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelKernel_d_Data.End_d_Data_SelKernel"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_d_Data_SelKernelImp(c,session)}

protected case class __End_d_Data_SelKernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_d_Data_SelKernel
}
  
}



  trait Failed_kernel_Kernel extends EPSelKernel_d_Data with event_lang.dsl.ChannelTypeFDtct
  object Failed_kernel_Kernel extends Failed_kernel_Kernel {
  override protected def __children: List[EPSelKernel_d_Data] = List(SndFSelKernelk)
  override type implT = __Failed_kernel_KernelImp
  override type implNextT = __SndFSelKernelkImp
override def toString() : String = {"EPSelKernel_d_Data.Failed_kernel_Kernel"}
  override def suspect : Role = Role("kernel",RoleSet("Kernel")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_kernel_KernelImp(c,session)}

protected case class __Failed_kernel_KernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_kernel_Kernel
}
  def failed_kernel_Kernel(): __SndFSelKernelkImp = {__SndFSelKernelkImp(c,session)}

}


  trait SndFSelKernelk extends EPSelKernel_d_Data with event_lang.dsl.ChannelTypeSnd
  object SndFSelKernelk extends SndFSelKernelk {
  override protected def __children: List[EPSelKernel_d_Data] = List(SndFSelKernelCons)
  override type implT = __SndFSelKernelkImp
  override type implNextT = __SndFSelKernelConsImp
override def toString() : String = {"EPSelKernel_d_Data.SndFSelKernelk"}
    override def to : RRole = Role("k",RoleSet("Key")) 
   override def l : String = "FSelKernelk" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFSelKernelkImp(c,session)}

protected case class __SndFSelKernelkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFSelKernelk
}
  private var notUsed = true
def sndTok_Key(m : MESSAGES.SelKernel.FSelKernelk) : __SndFSelKernelConsImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("k",RoleSet("Key")),m)
__SndFSelKernelConsImp(c,session)}
def !(m : MESSAGES.SelKernel.FSelKernelk) : __SndFSelKernelConsImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("k",RoleSet("Key")),m)
__SndFSelKernelConsImp(c,session)}
def snd(m : MESSAGES.SelKernel.FSelKernelk) : __SndFSelKernelConsImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("k",RoleSet("Key")),m)
__SndFSelKernelConsImp(c,session)}

}


  trait SndFSelKernelCons extends EPSelKernel_d_Data with event_lang.dsl.ChannelTypeSnd
  object SndFSelKernelCons extends SndFSelKernelCons {
  override protected def __children: List[EPSelKernel_d_Data] = List(End_d_Data_SelKernelFHandling)
  override type implT = __SndFSelKernelConsImp
  override type implNextT = __End_d_Data_SelKernelFHandlingImp
override def toString() : String = {"EPSelKernel_d_Data.SndFSelKernelCons"}
    override def to : RRole = RoleSet("Consumer") 
   override def l : String = "FSelKernelCons" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFSelKernelConsImp(c,session)}

protected case class __SndFSelKernelConsImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFSelKernelCons
}
  private var notUsed = true
def sndToConsumer(m : MESSAGES.SelKernel.FSelKernelCons) : __End_d_Data_SelKernelFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Consumer"),m)
__End_d_Data_SelKernelFHandlingImp(c,session)}
def !(m : MESSAGES.SelKernel.FSelKernelCons) : __End_d_Data_SelKernelFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Consumer"),m)
__End_d_Data_SelKernelFHandlingImp(c,session)}
def snd(m : MESSAGES.SelKernel.FSelKernelCons) : __End_d_Data_SelKernelFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Consumer"),m)
__End_d_Data_SelKernelFHandlingImp(c,session)}

}


protected  trait End_d_Data_SelKernelFHandling extends EPSelKernel_d_Data with event_lang.dsl.ChannelTypeEnd
protected  object End_d_Data_SelKernelFHandling extends End_d_Data_SelKernelFHandling {
  override protected def __children: List[EPSelKernel_d_Data] = List()
  override type implT = __End_d_Data_SelKernelFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelKernel_d_Data.End_d_Data_SelKernelFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_d_Data_SelKernelFHandlingImp(c,session)}

protected case class __End_d_Data_SelKernelFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_d_Data_SelKernelFHandling
}
  
}


}

object Main_d_Data{
trait EPMain_d_Data extends __EPType_Data

object EPMain_d_Data extends EPMain_d_Data with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_d_Data] = List(Hdl)
  override type implT = __EPMain_d_DataImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_d_DataImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data"))) 
  override def argsP: Role = Role("k",RoleSet("Key")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Kernel"), RoleSet("Consumer")) 
  override def prjTo : RRole = Role("d",RoleSet("Data")) 
  override def rootRole: Role = Role("d",RoleSet("Data")) 
  override def name : String = "Main"
}

protected case class __EPMain_d_DataImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_d_Data
}
  
}


protected  trait Hdl extends EPMain_d_Data with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_d_Data] = List(SpawnSelKernel,Failed_k_Key)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelKernelImp
override def toString() : String = {"EPMain_d_Data.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelKernel extends EPMain_d_Data with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelKernel extends SpawnSelKernel {
  override protected def __children: List[EPMain_d_Data] = List(End_d_Data_Main)
  override type implT = __SpawnSelKernelImp
  override type implNextT = __End_d_Data_MainImp
override def toString() : String = {"EPMain_d_Data.SpawnSelKernel"}
    override def y: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key"))) 
  override def pickR: RoleSet = RoleSet("Kernel") 
  override def rs: List[RoleSet] = List(RoleSet("Consumer")) 
  override def name: String = "SelKernel" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelKernelImp(c,session)}

protected case class __SpawnSelKernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelKernel
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_d_Data_Main extends EPMain_d_Data with event_lang.dsl.ChannelTypeEnd
protected  object End_d_Data_Main extends End_d_Data_Main {
  override protected def __children: List[EPMain_d_Data] = List()
  override type implT = __End_d_Data_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_d_Data.End_d_Data_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_d_Data_MainImp(c,session)}

protected case class __End_d_Data_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_d_Data_Main
}
  
}



  trait Failed_k_Key extends EPMain_d_Data with event_lang.dsl.ChannelTypeFDtct
  object Failed_k_Key extends Failed_k_Key {
  override protected def __children: List[EPMain_d_Data] = List(SndFMainKern)
  override type implT = __Failed_k_KeyImp
  override type implNextT = __SndFMainKernImp
override def toString() : String = {"EPMain_d_Data.Failed_k_Key"}
  override def suspect : Role = Role("k",RoleSet("Key")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_k_KeyImp(c,session)}

protected case class __Failed_k_KeyImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_k_Key
}
  def failed_k_Key(): __SndFMainKernImp = {__SndFMainKernImp(c,session)}

}


  trait SndFMainKern extends EPMain_d_Data with event_lang.dsl.ChannelTypeSnd
  object SndFMainKern extends SndFMainKern {
  override protected def __children: List[EPMain_d_Data] = List(SndFMainCons)
  override type implT = __SndFMainKernImp
  override type implNextT = __SndFMainConsImp
override def toString() : String = {"EPMain_d_Data.SndFMainKern"}
    override def to : RRole = RoleSet("Kernel") 
   override def l : String = "FMainKern" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFMainKernImp(c,session)}

protected case class __SndFMainKernImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFMainKern
}
  private var notUsed = true
def sndToKernel(m : MESSAGES.Main.FMainKern) : __SndFMainConsImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Kernel"),m)
__SndFMainConsImp(c,session)}
def !(m : MESSAGES.Main.FMainKern) : __SndFMainConsImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Kernel"),m)
__SndFMainConsImp(c,session)}
def snd(m : MESSAGES.Main.FMainKern) : __SndFMainConsImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Kernel"),m)
__SndFMainConsImp(c,session)}

}


  trait SndFMainCons extends EPMain_d_Data with event_lang.dsl.ChannelTypeSnd
  object SndFMainCons extends SndFMainCons {
  override protected def __children: List[EPMain_d_Data] = List(End_d_Data_MainFHandling)
  override type implT = __SndFMainConsImp
  override type implNextT = __End_d_Data_MainFHandlingImp
override def toString() : String = {"EPMain_d_Data.SndFMainCons"}
    override def to : RRole = RoleSet("Consumer") 
   override def l : String = "FMainCons" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndFMainConsImp(c,session)}

protected case class __SndFMainConsImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndFMainCons
}
  private var notUsed = true
def sndToConsumer(m : MESSAGES.Main.FMainCons) : __End_d_Data_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Consumer"),m)
__End_d_Data_MainFHandlingImp(c,session)}
def !(m : MESSAGES.Main.FMainCons) : __End_d_Data_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Consumer"),m)
__End_d_Data_MainFHandlingImp(c,session)}
def snd(m : MESSAGES.Main.FMainCons) : __End_d_Data_MainFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("Consumer"),m)
__End_d_Data_MainFHandlingImp(c,session)}

}


protected  trait End_d_Data_MainFHandling extends EPMain_d_Data with event_lang.dsl.ChannelTypeEnd
protected  object End_d_Data_MainFHandling extends End_d_Data_MainFHandling {
  override protected def __children: List[EPMain_d_Data] = List()
  override type implT = __End_d_Data_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_d_Data.End_d_Data_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_d_Data_MainFHandlingImp(c,session)}

protected case class __End_d_Data_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_d_Data_MainFHandling
}
  
}


}

}

object Key {
val subs : Seq[dsl.ChannelTypeSubS] = List(Streaming_k_Key.EPStreaming_k_Key,SelKernel_k_Key.EPSelKernel_k_Key,Main_k_Key.EPMain_k_Key)
trait __EPType_Key extends AbstractChannelType {

}

trait EPType_Key[T<: TState] extends AbstractEndPoint[__EPType_Key,T] {
override val roleSet: RoleSet = RoleSet("Key")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(Streaming_k_Key.EPStreaming_k_Key,SelKernel_k_Key.EPSelKernel_k_Key,Main_k_Key.EPMain_k_Key)

}

object Streaming_k_Key{
trait EPStreaming_k_Key extends __EPType_Key

object EPStreaming_k_Key extends EPStreaming_k_Key with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPStreaming_k_Key] = List(Hdl)
  override type implT = __EPStreaming_k_KeyImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPStreaming_k_KeyImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key")), Role("kernel",RoleSet("Kernel"))) 
  override def argsP: Role = Role("c",RoleSet("Consumer")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("k",RoleSet("Key")) 
  override def rootRole: Role = Role("kernel",RoleSet("Kernel")) 
  override def name : String = "Streaming"
}

protected case class __EPStreaming_k_KeyImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPStreaming_k_Key
}
  
}


protected  trait Hdl extends EPStreaming_k_Key with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPStreaming_k_Key] = List(RecT,RcvFStreamingk)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStreaming_k_Key.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPStreaming_k_Key with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPStreaming_k_Key] = List(SndKey)
  override type implT = __RecTImp
  override type implNextT = __SndKeyImp
override def toString() : String = {"EPStreaming_k_Key.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


  trait SndKey extends EPStreaming_k_Key with event_lang.dsl.ChannelTypeSnd
  object SndKey extends SndKey {
  override protected def __children: List[EPStreaming_k_Key] = List(T)
  override type implT = __SndKeyImp
  override type implNextT = __TImp
override def toString() : String = {"EPStreaming_k_Key.SndKey"}
    override def to : RRole = Role("kernel",RoleSet("Kernel")) 
   override def l : String = "Key" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndKeyImp(c,session)}

protected case class __SndKeyImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndKey
}
  private var notUsed = true
def sndTokernel_Kernel(m : MESSAGES.Streaming.Key) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("kernel",RoleSet("Kernel")),m)
__TImp(c,session)}
def !(m : MESSAGES.Streaming.Key) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("kernel",RoleSet("Kernel")),m)
__TImp(c,session)}
def snd(m : MESSAGES.Streaming.Key) : __TImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("kernel",RoleSet("Kernel")),m)
__TImp(c,session)}

}


protected  trait T extends EPStreaming_k_Key with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPStreaming_k_Key] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPStreaming_k_Key.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvFStreamingk extends EPStreaming_k_Key with event_lang.dsl.ChannelTypeRcv
  object RcvFStreamingk extends RcvFStreamingk {
  override protected def __children: List[EPStreaming_k_Key] = List(End_k_Key_StreamingFHandling)
  override type implT = __RcvFStreamingkImp
  override type implNextT = __End_k_Key_StreamingFHandlingImp
override def toString() : String = {"EPStreaming_k_Key.RcvFStreamingk"}
  override type msgT = MESSAGES.Streaming.FStreamingk
   override def frm : Role = Role("kernel",RoleSet("Kernel")) 
   override def l : String = "FStreamingk"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFStreamingkImp(c,session)}

protected case class __RcvFStreamingkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFStreamingk
}
  def rcvFrmkernel_Kernel : (MESSAGES.Streaming.FStreamingk,__End_k_Key_StreamingFHandlingImp) = {(c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.FStreamingk],__End_k_Key_StreamingFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Streaming.FStreamingk,__End_k_Key_StreamingFHandlingImp),T]) : T = {
  f((c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.FStreamingk],__End_k_Key_StreamingFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.Streaming.FStreamingk = {c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.FStreamingk]}
def ? : MESSAGES.Streaming.FStreamingk = {c.rcv(Role("kernel",RoleSet("Kernel"))).asInstanceOf[MESSAGES.Streaming.FStreamingk]}
def channelCon : __End_k_Key_StreamingFHandlingImp = {__End_k_Key_StreamingFHandlingImp(c,session)}

}


protected  trait End_k_Key_StreamingFHandling extends EPStreaming_k_Key with event_lang.dsl.ChannelTypeEnd
protected  object End_k_Key_StreamingFHandling extends End_k_Key_StreamingFHandling {
  override protected def __children: List[EPStreaming_k_Key] = List()
  override type implT = __End_k_Key_StreamingFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPStreaming_k_Key.End_k_Key_StreamingFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_k_Key_StreamingFHandlingImp(c,session)}

protected case class __End_k_Key_StreamingFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_k_Key_StreamingFHandling
}
  
}


}

object SelKernel_k_Key{
trait EPSelKernel_k_Key extends __EPType_Key

object EPSelKernel_k_Key extends EPSelKernel_k_Key with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPSelKernel_k_Key] = List(Hdl)
  override type implT = __EPSelKernel_k_KeyImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPSelKernel_k_KeyImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key"))) 
  override def argsP: Role = Role("kernel",RoleSet("Kernel")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Consumer")) 
  override def prjTo : RRole = Role("k",RoleSet("Key")) 
  override def rootRole: Role = Role("d",RoleSet("Data")) 
  override def name : String = "SelKernel"
}

protected case class __EPSelKernel_k_KeyImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPSelKernel_k_Key
}
  
}


protected  trait Hdl extends EPSelKernel_k_Key with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPSelKernel_k_Key] = List(SpawnStreaming,RcvFSelKernelk)
  override type implT = __HdlImp
  override type implNextT = __SpawnStreamingImp
override def toString() : String = {"EPSelKernel_k_Key.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnStreaming extends EPSelKernel_k_Key with event_lang.dsl.ChannelTypeSpawn
  object SpawnStreaming extends SpawnStreaming {
  override protected def __children: List[EPSelKernel_k_Key] = List(End_k_Key_SelKernel)
  override type implT = __SpawnStreamingImp
  override type implNextT = __End_k_Key_SelKernelImp
override def toString() : String = {"EPSelKernel_k_Key.SpawnStreaming"}
    override def y: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key")), Role("kernel",RoleSet("Kernel"))) 
  override def pickR: RoleSet = RoleSet("Consumer") 
  override def rs: List[RoleSet] = List() 
  override def name: String = "Streaming" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnStreamingImp(c,session)}

protected case class __SpawnStreamingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnStreaming
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_k_Key_SelKernel extends EPSelKernel_k_Key with event_lang.dsl.ChannelTypeEnd
protected  object End_k_Key_SelKernel extends End_k_Key_SelKernel {
  override protected def __children: List[EPSelKernel_k_Key] = List()
  override type implT = __End_k_Key_SelKernelImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelKernel_k_Key.End_k_Key_SelKernel"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_k_Key_SelKernelImp(c,session)}

protected case class __End_k_Key_SelKernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_k_Key_SelKernel
}
  
}



  trait RcvFSelKernelk extends EPSelKernel_k_Key with event_lang.dsl.ChannelTypeRcv
  object RcvFSelKernelk extends RcvFSelKernelk {
  override protected def __children: List[EPSelKernel_k_Key] = List(End_k_Key_SelKernelFHandling)
  override type implT = __RcvFSelKernelkImp
  override type implNextT = __End_k_Key_SelKernelFHandlingImp
override def toString() : String = {"EPSelKernel_k_Key.RcvFSelKernelk"}
  override type msgT = MESSAGES.SelKernel.FSelKernelk
   override def frm : Role = Role("d",RoleSet("Data")) 
   override def l : String = "FSelKernelk"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvFSelKernelkImp(c,session)}

protected case class __RcvFSelKernelkImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvFSelKernelk
}
  def rcvFrmd_Data : (MESSAGES.SelKernel.FSelKernelk,__End_k_Key_SelKernelFHandlingImp) = {(c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.SelKernel.FSelKernelk],__End_k_Key_SelKernelFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.SelKernel.FSelKernelk,__End_k_Key_SelKernelFHandlingImp),T]) : T = {
  f((c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.SelKernel.FSelKernelk],__End_k_Key_SelKernelFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.SelKernel.FSelKernelk = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.SelKernel.FSelKernelk]}
def ? : MESSAGES.SelKernel.FSelKernelk = {c.rcv(Role("d",RoleSet("Data"))).asInstanceOf[MESSAGES.SelKernel.FSelKernelk]}
def channelCon : __End_k_Key_SelKernelFHandlingImp = {__End_k_Key_SelKernelFHandlingImp(c,session)}

}


protected  trait End_k_Key_SelKernelFHandling extends EPSelKernel_k_Key with event_lang.dsl.ChannelTypeEnd
protected  object End_k_Key_SelKernelFHandling extends End_k_Key_SelKernelFHandling {
  override protected def __children: List[EPSelKernel_k_Key] = List()
  override type implT = __End_k_Key_SelKernelFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPSelKernel_k_Key.End_k_Key_SelKernelFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_k_Key_SelKernelFHandlingImp(c,session)}

protected case class __End_k_Key_SelKernelFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_k_Key_SelKernelFHandling
}
  
}


}

object Main_k_Key{
trait EPMain_k_Key extends __EPType_Key

object EPMain_k_Key extends EPMain_k_Key with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPMain_k_Key] = List(Hdl)
  override type implT = __EPMain_k_KeyImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPMain_k_KeyImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("d",RoleSet("Data"))) 
  override def argsP: Role = Role("k",RoleSet("Key")) 
  override def argsRs: List[RoleSet] = List(RoleSet("Kernel"), RoleSet("Consumer")) 
  override def prjTo : RRole = Role("k",RoleSet("Key")) 
  override def rootRole: Role = Role("d",RoleSet("Data")) 
  override def name : String = "Main"
}

protected case class __EPMain_k_KeyImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPMain_k_Key
}
  
}


protected  trait Hdl extends EPMain_k_Key with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPMain_k_Key] = List(SpawnSelKernel,End_k_Key_MainFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnSelKernelImp
override def toString() : String = {"EPMain_k_Key.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnSelKernel extends EPMain_k_Key with event_lang.dsl.ChannelTypeSpawn
  object SpawnSelKernel extends SpawnSelKernel {
  override protected def __children: List[EPMain_k_Key] = List(End_k_Key_Main)
  override type implT = __SpawnSelKernelImp
  override type implNextT = __End_k_Key_MainImp
override def toString() : String = {"EPMain_k_Key.SpawnSelKernel"}
    override def y: List[Role] = List(Role("d",RoleSet("Data")), Role("k",RoleSet("Key"))) 
  override def pickR: RoleSet = RoleSet("Kernel") 
  override def rs: List[RoleSet] = List(RoleSet("Consumer")) 
  override def name: String = "SelKernel" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnSelKernelImp(c,session)}

protected case class __SpawnSelKernelImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnSelKernel
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_k_Key_Main extends EPMain_k_Key with event_lang.dsl.ChannelTypeEnd
protected  object End_k_Key_Main extends End_k_Key_Main {
  override protected def __children: List[EPMain_k_Key] = List()
  override type implT = __End_k_Key_MainImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_k_Key.End_k_Key_Main"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_k_Key_MainImp(c,session)}

protected case class __End_k_Key_MainImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_k_Key_Main
}
  
}



protected  trait End_k_Key_MainFHandling extends EPMain_k_Key with event_lang.dsl.ChannelTypeEnd
protected  object End_k_Key_MainFHandling extends End_k_Key_MainFHandling {
  override protected def __children: List[EPMain_k_Key] = List()
  override type implT = __End_k_Key_MainFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPMain_k_Key.End_k_Key_MainFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_k_Key_MainFHandlingImp(c,session)}

protected case class __End_k_Key_MainFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_k_Key_MainFHandling
}
  
}


}

}

}
