package example.LetItRecover.webcrawler.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object webCrawler {
object RS {
val D : RoleSet = RoleSet("D")
val P : RoleSet = RoleSet("P")
val I : RoleSet = RoleSet("I")
val M : RoleSet = RoleSet("M")
}

object MESSAGES {
object WebCrawler {
case class DAddD() extends MSG {
   override def l : String = "DAddD"
}

case class PDone() extends MSG {
   override def l : String = "PDone"
}

case class Url(u:String) extends MSG {
   override def l : String = "Url"
}

case class DDOne() extends MSG {
   override def l : String = "DDOne"
}

case class PAddD() extends MSG {
   override def l : String = "PAddD"
}

case class F2() extends MSG {
   override def l : String = "F2"
}

case class IDone() extends MSG {
   override def l : String = "IDone"
}

case class F1() extends MSG {
   override def l : String = "F1"
}

case class IAddD() extends MSG {
   override def l : String = "IAddD"
}

}

object Download {
case class DF1() extends MSG {
   override def l : String = "DF1"
}

case class DF2() extends MSG {
   override def l : String = "DF2"
}

case class DF() extends MSG {
   override def l : String = "DF"
}

}

object Parse {
case class Parse(m:String) extends MSG {
   override def l : String = "Parse"
}

case class Index(i:String) extends MSG {
   override def l : String = "Index"
}

case class PF1() extends MSG {
   override def l : String = "PF1"
}

case class PF2() extends MSG {
   override def l : String = "PF2"
}

case class PF() extends MSG {
   override def l : String = "PF"
}

}

}

object PROTOCOLS {
object Download {
val d_D = Role("d",RoleSet("D"))
val D = RoleSet("D")
val P = RoleSet("P")
val m_M = Role("m",RoleSet("M"))
val i_I = Role("i",RoleSet("I"))
}

object Parse {
val p_P = Role("p",RoleSet("P"))
val P = RoleSet("P")
val m_M = Role("m",RoleSet("M"))
val i_I = Role("i",RoleSet("I"))
val d_D = Role("d",RoleSet("D"))
}

object WebCrawler {
val i_I = Role("i",RoleSet("I"))
val D = RoleSet("D")
val P = RoleSet("P")
val m_M = Role("m",RoleSet("M"))
}

}

object D {
val subs : Seq[dsl.ChannelTypeSubS] = List(WebCrawler_D.EPWebCrawler_D,Download_d_D.EPDownload_d_D,Download_D.EPDownload_D,Parse_d_D.EPParse_d_D)
trait __EPType_D extends AbstractChannelType {

}

trait EPType_D[T<: TState] extends AbstractEndPoint[__EPType_D,T] {
override val roleSet: RoleSet = RoleSet("D")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(WebCrawler_D.EPWebCrawler_D,Download_d_D.EPDownload_d_D,Download_D.EPDownload_D,Parse_d_D.EPParse_d_D)

}

object WebCrawler_D{
trait EPWebCrawler_D extends __EPType_D

object EPWebCrawler_D extends EPWebCrawler_D with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPWebCrawler_D] = List(Hdl)
  override type implT = __EPWebCrawler_DImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPWebCrawler_DImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("i",RoleSet("I")) 
  override def argsRs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def prjTo : RRole = RoleSet("D") 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "WebCrawler"
}

protected case class __EPWebCrawler_DImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPWebCrawler_D
}
  
}


protected  trait Hdl extends EPWebCrawler_D with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPWebCrawler_D] = List(RecT,RcvF1)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPWebCrawler_D.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPWebCrawler_D with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPWebCrawler_D] = List(Merge_DAddD_DDOne)
  override type implT = __RecTImp
  override type implNextT = __Merge_DAddD_DDOneImp
override def toString() : String = {"EPWebCrawler_D.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait Merge_DAddD_DDOne extends EPWebCrawler_D with event_lang.dsl.ChannelTypeMerge
protected  object Merge_DAddD_DDOne extends Merge_DAddD_DDOne {
  override protected def __children: List[EPWebCrawler_D] = List(RcvDAddD,RcvDDOne)
  override type implT = __Merge_DAddD_DDOneImp
  override type implNextT = __RcvDAddDImp
override def toString() : String = {"EPWebCrawler_D.Merge_DAddD_DDOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Merge_DAddD_DDOneImp(c,session)}

protected case class __Merge_DAddD_DDOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Merge_DAddD_DDOne
}
  
}


  trait RcvDAddD extends EPWebCrawler_D with event_lang.dsl.ChannelTypeRcv
  object RcvDAddD extends RcvDAddD {
  override protected def __children: List[EPWebCrawler_D] = List(SpawnDownload)
  override type implT = __RcvDAddDImp
  override type implNextT = __SpawnDownloadImp
override def toString() : String = {"EPWebCrawler_D.RcvDAddD"}
  override type msgT = MESSAGES.WebCrawler.DAddD
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "DAddD"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDAddDImp(c,session)}

protected case class __RcvDAddDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDAddD
}
  def rcvFrmm_M : (MESSAGES.WebCrawler.DAddD,__SpawnDownloadImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.DAddD],__SpawnDownloadImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.WebCrawler.DAddD,__SpawnDownloadImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.DAddD],__SpawnDownloadImp(c,session))) 
}
def rcvMSG : MESSAGES.WebCrawler.DAddD = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.DAddD]}
def ? : MESSAGES.WebCrawler.DAddD = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.DAddD]}
def channelCon : __SpawnDownloadImp = {__SpawnDownloadImp(c,session)}

}


  trait SpawnDownload extends EPWebCrawler_D with event_lang.dsl.ChannelTypeSpawn
  object SpawnDownload extends SpawnDownload {
  override protected def __children: List[EPWebCrawler_D] = List(T)
  override type implT = __SpawnDownloadImp
  override type implNextT = __TImp
override def toString() : String = {"EPWebCrawler_D.SpawnDownload"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def pickR: RoleSet = RoleSet("D") 
  override def rs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def name: String = "Download" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnDownloadImp(c,session)}

protected case class __SpawnDownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnDownload
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPWebCrawler_D with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPWebCrawler_D] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPWebCrawler_D.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvDDOne extends EPWebCrawler_D with event_lang.dsl.ChannelTypeRcv
  object RcvDDOne extends RcvDDOne {
  override protected def __children: List[EPWebCrawler_D] = List(End_D_WebCrawlerDDOne)
  override type implT = __RcvDDOneImp
  override type implNextT = __End_D_WebCrawlerDDOneImp
override def toString() : String = {"EPWebCrawler_D.RcvDDOne"}
  override type msgT = MESSAGES.WebCrawler.DDOne
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "DDOne"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDDOneImp(c,session)}

protected case class __RcvDDOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDDOne
}
  def rcvFrmm_M : (MESSAGES.WebCrawler.DDOne,__End_D_WebCrawlerDDOneImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.DDOne],__End_D_WebCrawlerDDOneImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.WebCrawler.DDOne,__End_D_WebCrawlerDDOneImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.DDOne],__End_D_WebCrawlerDDOneImp(c,session))) 
}
def rcvMSG : MESSAGES.WebCrawler.DDOne = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.DDOne]}
def ? : MESSAGES.WebCrawler.DDOne = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.DDOne]}
def channelCon : __End_D_WebCrawlerDDOneImp = {__End_D_WebCrawlerDDOneImp(c,session)}

}


protected  trait End_D_WebCrawlerDDOne extends EPWebCrawler_D with event_lang.dsl.ChannelTypeEnd
protected  object End_D_WebCrawlerDDOne extends End_D_WebCrawlerDDOne {
  override protected def __children: List[EPWebCrawler_D] = List()
  override type implT = __End_D_WebCrawlerDDOneImp
  override type implNextT = Nothing
override def toString() : String = {"EPWebCrawler_D.End_D_WebCrawlerDDOne"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_D_WebCrawlerDDOneImp(c,session)}

protected case class __End_D_WebCrawlerDDOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_D_WebCrawlerDDOne
}
  
}



  trait RcvF1 extends EPWebCrawler_D with event_lang.dsl.ChannelTypeRcv
  object RcvF1 extends RcvF1 {
  override protected def __children: List[EPWebCrawler_D] = List(End_D_WebCrawlerFHandling)
  override type implT = __RcvF1Imp
  override type implNextT = __End_D_WebCrawlerFHandlingImp
override def toString() : String = {"EPWebCrawler_D.RcvF1"}
  override type msgT = MESSAGES.WebCrawler.F1
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "F1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF1Imp(c,session)}

protected case class __RcvF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF1
}
  def rcvFrmm_M : (MESSAGES.WebCrawler.F1,__End_D_WebCrawlerFHandlingImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.F1],__End_D_WebCrawlerFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.WebCrawler.F1,__End_D_WebCrawlerFHandlingImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.F1],__End_D_WebCrawlerFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.WebCrawler.F1 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.F1]}
def ? : MESSAGES.WebCrawler.F1 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.F1]}
def channelCon : __End_D_WebCrawlerFHandlingImp = {__End_D_WebCrawlerFHandlingImp(c,session)}

}


protected  trait End_D_WebCrawlerFHandling extends EPWebCrawler_D with event_lang.dsl.ChannelTypeEnd
protected  object End_D_WebCrawlerFHandling extends End_D_WebCrawlerFHandling {
  override protected def __children: List[EPWebCrawler_D] = List()
  override type implT = __End_D_WebCrawlerFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPWebCrawler_D.End_D_WebCrawlerFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_D_WebCrawlerFHandlingImp(c,session)}

protected case class __End_D_WebCrawlerFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_D_WebCrawlerFHandling
}
  
}


}

object Download_d_D{
trait EPDownload_d_D extends __EPType_D

object EPDownload_d_D extends EPDownload_d_D with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPDownload_d_D] = List(Hdl)
  override type implT = __EPDownload_d_DImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPDownload_d_DImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def argsP: Role = Role("d",RoleSet("D")) 
  override def argsRs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def prjTo : RRole = Role("d",RoleSet("D")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Download"
}

protected case class __EPDownload_d_DImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPDownload_d_D
}
  
}


protected  trait Hdl extends EPDownload_d_D with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPDownload_d_D] = List(SpawnParse,End_d_D_DownloadFHandling)
  override type implT = __HdlImp
  override type implNextT = __SpawnParseImp
override def toString() : String = {"EPDownload_d_D.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnParse extends EPDownload_d_D with event_lang.dsl.ChannelTypeSpawn
  object SpawnParse extends SpawnParse {
  override protected def __children: List[EPDownload_d_D] = List(End_d_D_Download)
  override type implT = __SpawnParseImp
  override type implNextT = __End_d_D_DownloadImp
override def toString() : String = {"EPDownload_d_D.SpawnParse"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Parse" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnParseImp(c,session)}

protected case class __SpawnParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnParse
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_d_D_Download extends EPDownload_d_D with event_lang.dsl.ChannelTypeEnd
protected  object End_d_D_Download extends End_d_D_Download {
  override protected def __children: List[EPDownload_d_D] = List()
  override type implT = __End_d_D_DownloadImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_d_D.End_d_D_Download"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_d_D_DownloadImp(c,session)}

protected case class __End_d_D_DownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_d_D_Download
}
  
}



protected  trait End_d_D_DownloadFHandling extends EPDownload_d_D with event_lang.dsl.ChannelTypeEnd
protected  object End_d_D_DownloadFHandling extends End_d_D_DownloadFHandling {
  override protected def __children: List[EPDownload_d_D] = List()
  override type implT = __End_d_D_DownloadFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_d_D.End_d_D_DownloadFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_d_D_DownloadFHandlingImp(c,session)}

protected case class __End_d_D_DownloadFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_d_D_DownloadFHandling
}
  
}


}

object Download_D{
trait EPDownload_D extends __EPType_D

object EPDownload_D extends EPDownload_D with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPDownload_D] = List(Hdl)
  override type implT = __EPDownload_DImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPDownload_DImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def argsP: Role = Role("d",RoleSet("D")) 
  override def argsRs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def prjTo : RRole = RoleSet("D") 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Download"
}

protected case class __EPDownload_DImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPDownload_D
}
  
}


protected  trait Hdl extends EPDownload_D with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPDownload_D] = List(End_D_Download,RcvDF1)
  override type implT = __HdlImp
  override type implNextT = __End_D_DownloadImp
override def toString() : String = {"EPDownload_D.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_D_Download extends EPDownload_D with event_lang.dsl.ChannelTypeEnd
protected  object End_D_Download extends End_D_Download {
  override protected def __children: List[EPDownload_D] = List()
  override type implT = __End_D_DownloadImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_D.End_D_Download"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_D_DownloadImp(c,session)}

protected case class __End_D_DownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_D_Download
}
  
}



  trait RcvDF1 extends EPDownload_D with event_lang.dsl.ChannelTypeRcv
  object RcvDF1 extends RcvDF1 {
  override protected def __children: List[EPDownload_D] = List(SpawnDownload)
  override type implT = __RcvDF1Imp
  override type implNextT = __SpawnDownloadImp
override def toString() : String = {"EPDownload_D.RcvDF1"}
  override type msgT = MESSAGES.Download.DF1
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "DF1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDF1Imp(c,session)}

protected case class __RcvDF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDF1
}
  def rcvFrmm_M : (MESSAGES.Download.DF1,__SpawnDownloadImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF1],__SpawnDownloadImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Download.DF1,__SpawnDownloadImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF1],__SpawnDownloadImp(c,session))) 
}
def rcvMSG : MESSAGES.Download.DF1 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF1]}
def ? : MESSAGES.Download.DF1 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF1]}
def channelCon : __SpawnDownloadImp = {__SpawnDownloadImp(c,session)}

}


  trait SpawnDownload extends EPDownload_D with event_lang.dsl.ChannelTypeSpawn
  object SpawnDownload extends SpawnDownload {
  override protected def __children: List[EPDownload_D] = List(End_D_DownloadFHandling)
  override type implT = __SpawnDownloadImp
  override type implNextT = __End_D_DownloadFHandlingImp
override def toString() : String = {"EPDownload_D.SpawnDownload"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def pickR: RoleSet = RoleSet("D") 
  override def rs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def name: String = "Download" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnDownloadImp(c,session)}

protected case class __SpawnDownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnDownload
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_D_DownloadFHandling extends EPDownload_D with event_lang.dsl.ChannelTypeEnd
protected  object End_D_DownloadFHandling extends End_D_DownloadFHandling {
  override protected def __children: List[EPDownload_D] = List()
  override type implT = __End_D_DownloadFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_D.End_D_DownloadFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_D_DownloadFHandlingImp(c,session)}

protected case class __End_D_DownloadFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_D_DownloadFHandling
}
  
}


}

object Parse_d_D{
trait EPParse_d_D extends __EPType_D

object EPParse_d_D extends EPParse_d_D with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPParse_d_D] = List(Hdl)
  override type implT = __EPParse_d_DImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPParse_d_DImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def argsP: Role = Role("p",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = Role("d",RoleSet("D")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Parse"
}

protected case class __EPParse_d_DImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPParse_d_D
}
  
}


protected  trait Hdl extends EPParse_d_D with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPParse_d_D] = List(SndParse,RcvPF1)
  override type implT = __HdlImp
  override type implNextT = __SndParseImp
override def toString() : String = {"EPParse_d_D.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndParse extends EPParse_d_D with event_lang.dsl.ChannelTypeSnd
  object SndParse extends SndParse {
  override protected def __children: List[EPParse_d_D] = List(End_d_D_Parse)
  override type implT = __SndParseImp
  override type implNextT = __End_d_D_ParseImp
override def toString() : String = {"EPParse_d_D.SndParse"}
    override def to : RRole = Role("p",RoleSet("P")) 
   override def l : String = "Parse" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndParseImp(c,session)}

protected case class __SndParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndParse
}
  private var notUsed = true
def sndTop_P(m : MESSAGES.Parse.Parse) : __End_d_D_ParseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__End_d_D_ParseImp(c,session)}
def !(m : MESSAGES.Parse.Parse) : __End_d_D_ParseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__End_d_D_ParseImp(c,session)}
def snd(m : MESSAGES.Parse.Parse) : __End_d_D_ParseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("p",RoleSet("P")),m)
__End_d_D_ParseImp(c,session)}

}


protected  trait End_d_D_Parse extends EPParse_d_D with event_lang.dsl.ChannelTypeEnd
protected  object End_d_D_Parse extends End_d_D_Parse {
  override protected def __children: List[EPParse_d_D] = List()
  override type implT = __End_d_D_ParseImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_d_D.End_d_D_Parse"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_d_D_ParseImp(c,session)}

protected case class __End_d_D_ParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_d_D_Parse
}
  
}



  trait RcvPF1 extends EPParse_d_D with event_lang.dsl.ChannelTypeRcv
  object RcvPF1 extends RcvPF1 {
  override protected def __children: List[EPParse_d_D] = List(SpawnParse)
  override type implT = __RcvPF1Imp
  override type implNextT = __SpawnParseImp
override def toString() : String = {"EPParse_d_D.RcvPF1"}
  override type msgT = MESSAGES.Parse.PF1
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "PF1"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPF1Imp(c,session)}

protected case class __RcvPF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPF1
}
  def rcvFrmm_M : (MESSAGES.Parse.PF1,__SpawnParseImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF1],__SpawnParseImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Parse.PF1,__SpawnParseImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF1],__SpawnParseImp(c,session))) 
}
def rcvMSG : MESSAGES.Parse.PF1 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF1]}
def ? : MESSAGES.Parse.PF1 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF1]}
def channelCon : __SpawnParseImp = {__SpawnParseImp(c,session)}

}


  trait SpawnParse extends EPParse_d_D with event_lang.dsl.ChannelTypeSpawn
  object SpawnParse extends SpawnParse {
  override protected def __children: List[EPParse_d_D] = List(End_d_D_ParseFHandling)
  override type implT = __SpawnParseImp
  override type implNextT = __End_d_D_ParseFHandlingImp
override def toString() : String = {"EPParse_d_D.SpawnParse"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Parse" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnParseImp(c,session)}

protected case class __SpawnParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnParse
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_d_D_ParseFHandling extends EPParse_d_D with event_lang.dsl.ChannelTypeEnd
protected  object End_d_D_ParseFHandling extends End_d_D_ParseFHandling {
  override protected def __children: List[EPParse_d_D] = List()
  override type implT = __End_d_D_ParseFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_d_D.End_d_D_ParseFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_d_D_ParseFHandlingImp(c,session)}

protected case class __End_d_D_ParseFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_d_D_ParseFHandling
}
  
}


}

}

object P {
val subs : Seq[dsl.ChannelTypeSubS] = List(WebCrawler_P.EPWebCrawler_P,Download_P.EPDownload_P,Parse_p_P.EPParse_p_P,Parse_P.EPParse_P)
trait __EPType_P extends AbstractChannelType {

}

trait EPType_P[T<: TState] extends AbstractEndPoint[__EPType_P,T] {
override val roleSet: RoleSet = RoleSet("P")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(WebCrawler_P.EPWebCrawler_P,Download_P.EPDownload_P,Parse_p_P.EPParse_p_P,Parse_P.EPParse_P)

}

object WebCrawler_P{
trait EPWebCrawler_P extends __EPType_P

object EPWebCrawler_P extends EPWebCrawler_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPWebCrawler_P] = List(Hdl)
  override type implT = __EPWebCrawler_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPWebCrawler_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("i",RoleSet("I")) 
  override def argsRs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def prjTo : RRole = RoleSet("P") 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "WebCrawler"
}

protected case class __EPWebCrawler_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPWebCrawler_P
}
  
}


protected  trait Hdl extends EPWebCrawler_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPWebCrawler_P] = List(RecT,RcvF2)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPWebCrawler_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPWebCrawler_P with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPWebCrawler_P] = List(Merge_PAddD_PDone)
  override type implT = __RecTImp
  override type implNextT = __Merge_PAddD_PDoneImp
override def toString() : String = {"EPWebCrawler_P.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait Merge_PAddD_PDone extends EPWebCrawler_P with event_lang.dsl.ChannelTypeMerge
protected  object Merge_PAddD_PDone extends Merge_PAddD_PDone {
  override protected def __children: List[EPWebCrawler_P] = List(RcvPAddD,RcvPDone)
  override type implT = __Merge_PAddD_PDoneImp
  override type implNextT = __RcvPAddDImp
override def toString() : String = {"EPWebCrawler_P.Merge_PAddD_PDone"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __Merge_PAddD_PDoneImp(c,session)}

protected case class __Merge_PAddD_PDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Merge_PAddD_PDone
}
  
}


  trait RcvPAddD extends EPWebCrawler_P with event_lang.dsl.ChannelTypeRcv
  object RcvPAddD extends RcvPAddD {
  override protected def __children: List[EPWebCrawler_P] = List(SpawnDownload)
  override type implT = __RcvPAddDImp
  override type implNextT = __SpawnDownloadImp
override def toString() : String = {"EPWebCrawler_P.RcvPAddD"}
  override type msgT = MESSAGES.WebCrawler.PAddD
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "PAddD"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPAddDImp(c,session)}

protected case class __RcvPAddDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPAddD
}
  def rcvFrmm_M : (MESSAGES.WebCrawler.PAddD,__SpawnDownloadImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.PAddD],__SpawnDownloadImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.WebCrawler.PAddD,__SpawnDownloadImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.PAddD],__SpawnDownloadImp(c,session))) 
}
def rcvMSG : MESSAGES.WebCrawler.PAddD = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.PAddD]}
def ? : MESSAGES.WebCrawler.PAddD = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.PAddD]}
def channelCon : __SpawnDownloadImp = {__SpawnDownloadImp(c,session)}

}


  trait SpawnDownload extends EPWebCrawler_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnDownload extends SpawnDownload {
  override protected def __children: List[EPWebCrawler_P] = List(T)
  override type implT = __SpawnDownloadImp
  override type implNextT = __TImp
override def toString() : String = {"EPWebCrawler_P.SpawnDownload"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def pickR: RoleSet = RoleSet("D") 
  override def rs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def name: String = "Download" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnDownloadImp(c,session)}

protected case class __SpawnDownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnDownload
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPWebCrawler_P with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPWebCrawler_P] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPWebCrawler_P.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvPDone extends EPWebCrawler_P with event_lang.dsl.ChannelTypeRcv
  object RcvPDone extends RcvPDone {
  override protected def __children: List[EPWebCrawler_P] = List(End_P_WebCrawlerPDone)
  override type implT = __RcvPDoneImp
  override type implNextT = __End_P_WebCrawlerPDoneImp
override def toString() : String = {"EPWebCrawler_P.RcvPDone"}
  override type msgT = MESSAGES.WebCrawler.PDone
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "PDone"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPDoneImp(c,session)}

protected case class __RcvPDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPDone
}
  def rcvFrmm_M : (MESSAGES.WebCrawler.PDone,__End_P_WebCrawlerPDoneImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.PDone],__End_P_WebCrawlerPDoneImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.WebCrawler.PDone,__End_P_WebCrawlerPDoneImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.PDone],__End_P_WebCrawlerPDoneImp(c,session))) 
}
def rcvMSG : MESSAGES.WebCrawler.PDone = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.PDone]}
def ? : MESSAGES.WebCrawler.PDone = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.PDone]}
def channelCon : __End_P_WebCrawlerPDoneImp = {__End_P_WebCrawlerPDoneImp(c,session)}

}


protected  trait End_P_WebCrawlerPDone extends EPWebCrawler_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_WebCrawlerPDone extends End_P_WebCrawlerPDone {
  override protected def __children: List[EPWebCrawler_P] = List()
  override type implT = __End_P_WebCrawlerPDoneImp
  override type implNextT = Nothing
override def toString() : String = {"EPWebCrawler_P.End_P_WebCrawlerPDone"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_WebCrawlerPDoneImp(c,session)}

protected case class __End_P_WebCrawlerPDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_WebCrawlerPDone
}
  
}



  trait RcvF2 extends EPWebCrawler_P with event_lang.dsl.ChannelTypeRcv
  object RcvF2 extends RcvF2 {
  override protected def __children: List[EPWebCrawler_P] = List(End_P_WebCrawlerFHandling)
  override type implT = __RcvF2Imp
  override type implNextT = __End_P_WebCrawlerFHandlingImp
override def toString() : String = {"EPWebCrawler_P.RcvF2"}
  override type msgT = MESSAGES.WebCrawler.F2
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "F2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvF2Imp(c,session)}

protected case class __RcvF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvF2
}
  def rcvFrmm_M : (MESSAGES.WebCrawler.F2,__End_P_WebCrawlerFHandlingImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.F2],__End_P_WebCrawlerFHandlingImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.WebCrawler.F2,__End_P_WebCrawlerFHandlingImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.F2],__End_P_WebCrawlerFHandlingImp(c,session))) 
}
def rcvMSG : MESSAGES.WebCrawler.F2 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.F2]}
def ? : MESSAGES.WebCrawler.F2 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.F2]}
def channelCon : __End_P_WebCrawlerFHandlingImp = {__End_P_WebCrawlerFHandlingImp(c,session)}

}


protected  trait End_P_WebCrawlerFHandling extends EPWebCrawler_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_WebCrawlerFHandling extends End_P_WebCrawlerFHandling {
  override protected def __children: List[EPWebCrawler_P] = List()
  override type implT = __End_P_WebCrawlerFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPWebCrawler_P.End_P_WebCrawlerFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_WebCrawlerFHandlingImp(c,session)}

protected case class __End_P_WebCrawlerFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_WebCrawlerFHandling
}
  
}


}

object Download_P{
trait EPDownload_P extends __EPType_P

object EPDownload_P extends EPDownload_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPDownload_P] = List(Hdl)
  override type implT = __EPDownload_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPDownload_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def argsP: Role = Role("d",RoleSet("D")) 
  override def argsRs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def prjTo : RRole = RoleSet("P") 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Download"
}

protected case class __EPDownload_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPDownload_P
}
  
}


protected  trait Hdl extends EPDownload_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPDownload_P] = List(SpawnParse,RcvDF2)
  override type implT = __HdlImp
  override type implNextT = __SpawnParseImp
override def toString() : String = {"EPDownload_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnParse extends EPDownload_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnParse extends SpawnParse {
  override protected def __children: List[EPDownload_P] = List(End_P_Download)
  override type implT = __SpawnParseImp
  override type implNextT = __End_P_DownloadImp
override def toString() : String = {"EPDownload_P.SpawnParse"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Parse" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnParseImp(c,session)}

protected case class __SpawnParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnParse
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_P_Download extends EPDownload_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_Download extends End_P_Download {
  override protected def __children: List[EPDownload_P] = List()
  override type implT = __End_P_DownloadImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_P.End_P_Download"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_DownloadImp(c,session)}

protected case class __End_P_DownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_Download
}
  
}



  trait RcvDF2 extends EPDownload_P with event_lang.dsl.ChannelTypeRcv
  object RcvDF2 extends RcvDF2 {
  override protected def __children: List[EPDownload_P] = List(SpawnDownload)
  override type implT = __RcvDF2Imp
  override type implNextT = __SpawnDownloadImp
override def toString() : String = {"EPDownload_P.RcvDF2"}
  override type msgT = MESSAGES.Download.DF2
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "DF2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDF2Imp(c,session)}

protected case class __RcvDF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDF2
}
  def rcvFrmm_M : (MESSAGES.Download.DF2,__SpawnDownloadImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF2],__SpawnDownloadImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Download.DF2,__SpawnDownloadImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF2],__SpawnDownloadImp(c,session))) 
}
def rcvMSG : MESSAGES.Download.DF2 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF2]}
def ? : MESSAGES.Download.DF2 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF2]}
def channelCon : __SpawnDownloadImp = {__SpawnDownloadImp(c,session)}

}


  trait SpawnDownload extends EPDownload_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnDownload extends SpawnDownload {
  override protected def __children: List[EPDownload_P] = List(End_P_DownloadFHandling)
  override type implT = __SpawnDownloadImp
  override type implNextT = __End_P_DownloadFHandlingImp
override def toString() : String = {"EPDownload_P.SpawnDownload"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def pickR: RoleSet = RoleSet("D") 
  override def rs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def name: String = "Download" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnDownloadImp(c,session)}

protected case class __SpawnDownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnDownload
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_P_DownloadFHandling extends EPDownload_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_DownloadFHandling extends End_P_DownloadFHandling {
  override protected def __children: List[EPDownload_P] = List()
  override type implT = __End_P_DownloadFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_P.End_P_DownloadFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_DownloadFHandlingImp(c,session)}

protected case class __End_P_DownloadFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_DownloadFHandling
}
  
}


}

object Parse_p_P{
trait EPParse_p_P extends __EPType_P

object EPParse_p_P extends EPParse_p_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPParse_p_P] = List(Hdl)
  override type implT = __EPParse_p_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPParse_p_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def argsP: Role = Role("p",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = Role("p",RoleSet("P")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Parse"
}

protected case class __EPParse_p_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPParse_p_P
}
  
}


protected  trait Hdl extends EPParse_p_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPParse_p_P] = List(RcvParse,End_p_P_ParseFHandling)
  override type implT = __HdlImp
  override type implNextT = __RcvParseImp
override def toString() : String = {"EPParse_p_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvParse extends EPParse_p_P with event_lang.dsl.ChannelTypeRcv
  object RcvParse extends RcvParse {
  override protected def __children: List[EPParse_p_P] = List(SndIndex)
  override type implT = __RcvParseImp
  override type implNextT = __SndIndexImp
override def toString() : String = {"EPParse_p_P.RcvParse"}
  override type msgT = MESSAGES.Parse.Parse
   override def frm : Role = Role("d",RoleSet("D")) 
   override def l : String = "Parse"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvParseImp(c,session)}

protected case class __RcvParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvParse
}
  def rcvFrmd_D : (MESSAGES.Parse.Parse,__SndIndexImp) = {(c.rcv(Role("d",RoleSet("D"))).asInstanceOf[MESSAGES.Parse.Parse],__SndIndexImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Parse.Parse,__SndIndexImp),T]) : T = {
  f((c.rcv(Role("d",RoleSet("D"))).asInstanceOf[MESSAGES.Parse.Parse],__SndIndexImp(c,session))) 
}
def rcvMSG : MESSAGES.Parse.Parse = {c.rcv(Role("d",RoleSet("D"))).asInstanceOf[MESSAGES.Parse.Parse]}
def ? : MESSAGES.Parse.Parse = {c.rcv(Role("d",RoleSet("D"))).asInstanceOf[MESSAGES.Parse.Parse]}
def channelCon : __SndIndexImp = {__SndIndexImp(c,session)}

}


  trait SndIndex extends EPParse_p_P with event_lang.dsl.ChannelTypeSnd
  object SndIndex extends SndIndex {
  override protected def __children: List[EPParse_p_P] = List(End_p_P_Parse)
  override type implT = __SndIndexImp
  override type implNextT = __End_p_P_ParseImp
override def toString() : String = {"EPParse_p_P.SndIndex"}
    override def to : RRole = Role("i",RoleSet("I")) 
   override def l : String = "Index" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndIndexImp(c,session)}

protected case class __SndIndexImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndIndex
}
  private var notUsed = true
def sndToi_I(m : MESSAGES.Parse.Index) : __End_p_P_ParseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__End_p_P_ParseImp(c,session)}
def !(m : MESSAGES.Parse.Index) : __End_p_P_ParseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__End_p_P_ParseImp(c,session)}
def snd(m : MESSAGES.Parse.Index) : __End_p_P_ParseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__End_p_P_ParseImp(c,session)}

}


protected  trait End_p_P_Parse extends EPParse_p_P with event_lang.dsl.ChannelTypeEnd
protected  object End_p_P_Parse extends End_p_P_Parse {
  override protected def __children: List[EPParse_p_P] = List()
  override type implT = __End_p_P_ParseImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_p_P.End_p_P_Parse"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_p_P_ParseImp(c,session)}

protected case class __End_p_P_ParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_p_P_Parse
}
  
}



protected  trait End_p_P_ParseFHandling extends EPParse_p_P with event_lang.dsl.ChannelTypeEnd
protected  object End_p_P_ParseFHandling extends End_p_P_ParseFHandling {
  override protected def __children: List[EPParse_p_P] = List()
  override type implT = __End_p_P_ParseFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_p_P.End_p_P_ParseFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_p_P_ParseFHandlingImp(c,session)}

protected case class __End_p_P_ParseFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_p_P_ParseFHandling
}
  
}


}

object Parse_P{
trait EPParse_P extends __EPType_P

object EPParse_P extends EPParse_P with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPParse_P] = List(Hdl)
  override type implT = __EPParse_PImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPParse_PImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def argsP: Role = Role("p",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = RoleSet("P") 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Parse"
}

protected case class __EPParse_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPParse_P
}
  
}


protected  trait Hdl extends EPParse_P with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPParse_P] = List(End_P_Parse,RcvPF2)
  override type implT = __HdlImp
  override type implNextT = __End_P_ParseImp
override def toString() : String = {"EPParse_P.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_P_Parse extends EPParse_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_Parse extends End_P_Parse {
  override protected def __children: List[EPParse_P] = List()
  override type implT = __End_P_ParseImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_P.End_P_Parse"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_ParseImp(c,session)}

protected case class __End_P_ParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_Parse
}
  
}



  trait RcvPF2 extends EPParse_P with event_lang.dsl.ChannelTypeRcv
  object RcvPF2 extends RcvPF2 {
  override protected def __children: List[EPParse_P] = List(SpawnParse)
  override type implT = __RcvPF2Imp
  override type implNextT = __SpawnParseImp
override def toString() : String = {"EPParse_P.RcvPF2"}
  override type msgT = MESSAGES.Parse.PF2
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "PF2"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPF2Imp(c,session)}

protected case class __RcvPF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPF2
}
  def rcvFrmm_M : (MESSAGES.Parse.PF2,__SpawnParseImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF2],__SpawnParseImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Parse.PF2,__SpawnParseImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF2],__SpawnParseImp(c,session))) 
}
def rcvMSG : MESSAGES.Parse.PF2 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF2]}
def ? : MESSAGES.Parse.PF2 = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF2]}
def channelCon : __SpawnParseImp = {__SpawnParseImp(c,session)}

}


  trait SpawnParse extends EPParse_P with event_lang.dsl.ChannelTypeSpawn
  object SpawnParse extends SpawnParse {
  override protected def __children: List[EPParse_P] = List(End_P_ParseFHandling)
  override type implT = __SpawnParseImp
  override type implNextT = __End_P_ParseFHandlingImp
override def toString() : String = {"EPParse_P.SpawnParse"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Parse" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnParseImp(c,session)}

protected case class __SpawnParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnParse
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_P_ParseFHandling extends EPParse_P with event_lang.dsl.ChannelTypeEnd
protected  object End_P_ParseFHandling extends End_P_ParseFHandling {
  override protected def __children: List[EPParse_P] = List()
  override type implT = __End_P_ParseFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_P.End_P_ParseFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_P_ParseFHandlingImp(c,session)}

protected case class __End_P_ParseFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_P_ParseFHandling
}
  
}


}

}

object I {
val subs : Seq[dsl.ChannelTypeSubS] = List(WebCrawler_i_I.EPWebCrawler_i_I,Download_i_I.EPDownload_i_I,Parse_i_I.EPParse_i_I)
trait __EPType_I extends AbstractChannelType {

}

trait EPType_I[T<: TState] extends AbstractEndPoint[__EPType_I,T] {
override val roleSet: RoleSet = RoleSet("I")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(WebCrawler_i_I.EPWebCrawler_i_I,Download_i_I.EPDownload_i_I,Parse_i_I.EPParse_i_I)

}

object WebCrawler_i_I{
trait EPWebCrawler_i_I extends __EPType_I

object EPWebCrawler_i_I extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPWebCrawler_i_I] = List(Hdl)
  override type implT = __EPWebCrawler_i_IImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPWebCrawler_i_IImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("i",RoleSet("I")) 
  override def argsRs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def prjTo : RRole = Role("i",RoleSet("I")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "WebCrawler"
}

protected case class __EPWebCrawler_i_IImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPWebCrawler_i_I
}
  
}


protected  trait Hdl extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPWebCrawler_i_I] = List(RecT,End_i_I_WebCrawlerFHandling)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPWebCrawler_i_I.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPWebCrawler_i_I] = List(SelIAddDIDone)
  override type implT = __RecTImp
  override type implNextT = __SelIAddDIDoneImp
override def toString() : String = {"EPWebCrawler_i_I.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelIAddDIDone extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeBrn
protected  object SelIAddDIDone extends SelIAddDIDone {
  override protected def __children: List[EPWebCrawler_i_I] = List(RcvIAddD,RcvIDone)
  override type implT = __SelIAddDIDoneImp
  override type implNextT = __RcvIAddDImp
override def toString() : String = {"EPWebCrawler_i_I.SelIAddDIDone"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelIAddDIDoneImp(c,session)}

protected case class __SelIAddDIDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelIAddDIDone
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvIAddD extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeRcv
  object RcvIAddD extends RcvIAddD {
  override protected def __children: List[EPWebCrawler_i_I] = List(SpawnDownload)
  override type implT = __RcvIAddDImp
  override type implNextT = __SpawnDownloadImp
override def toString() : String = {"EPWebCrawler_i_I.RcvIAddD"}
  override type msgT = MESSAGES.WebCrawler.IAddD
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "IAddD"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvIAddDImp(c,session)}

protected case class __RcvIAddDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvIAddD
}
  def rcvFrmm_M : (MESSAGES.WebCrawler.IAddD,__SpawnDownloadImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.IAddD],__SpawnDownloadImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.WebCrawler.IAddD,__SpawnDownloadImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.IAddD],__SpawnDownloadImp(c,session))) 
}
def rcvMSG : MESSAGES.WebCrawler.IAddD = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.IAddD]}
def ? : MESSAGES.WebCrawler.IAddD = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.IAddD]}
def channelCon : __SpawnDownloadImp = {__SpawnDownloadImp(c,session)}

}


  trait SpawnDownload extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeSpawn
  object SpawnDownload extends SpawnDownload {
  override protected def __children: List[EPWebCrawler_i_I] = List(T)
  override type implT = __SpawnDownloadImp
  override type implNextT = __TImp
override def toString() : String = {"EPWebCrawler_i_I.SpawnDownload"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def pickR: RoleSet = RoleSet("D") 
  override def rs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def name: String = "Download" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnDownloadImp(c,session)}

protected case class __SpawnDownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnDownload
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPWebCrawler_i_I] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPWebCrawler_i_I.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait RcvIDone extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeRcv
  object RcvIDone extends RcvIDone {
  override protected def __children: List[EPWebCrawler_i_I] = List(SndUrl)
  override type implT = __RcvIDoneImp
  override type implNextT = __SndUrlImp
override def toString() : String = {"EPWebCrawler_i_I.RcvIDone"}
  override type msgT = MESSAGES.WebCrawler.IDone
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "IDone"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvIDoneImp(c,session)}

protected case class __RcvIDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvIDone
}
  def rcvFrmm_M : (MESSAGES.WebCrawler.IDone,__SndUrlImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.IDone],__SndUrlImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.WebCrawler.IDone,__SndUrlImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.IDone],__SndUrlImp(c,session))) 
}
def rcvMSG : MESSAGES.WebCrawler.IDone = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.IDone]}
def ? : MESSAGES.WebCrawler.IDone = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.WebCrawler.IDone]}
def channelCon : __SndUrlImp = {__SndUrlImp(c,session)}

}


  trait SndUrl extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeSnd
  object SndUrl extends SndUrl {
  override protected def __children: List[EPWebCrawler_i_I] = List(End_i_I_WebCrawlerIDone)
  override type implT = __SndUrlImp
  override type implNextT = __End_i_I_WebCrawlerIDoneImp
override def toString() : String = {"EPWebCrawler_i_I.SndUrl"}
    override def to : RRole = Role("m",RoleSet("M")) 
   override def l : String = "Url" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndUrlImp(c,session)}

protected case class __SndUrlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndUrl
}
  private var notUsed = true
def sndTom_M(m : MESSAGES.WebCrawler.Url) : __End_i_I_WebCrawlerIDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__End_i_I_WebCrawlerIDoneImp(c,session)}
def !(m : MESSAGES.WebCrawler.Url) : __End_i_I_WebCrawlerIDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__End_i_I_WebCrawlerIDoneImp(c,session)}
def snd(m : MESSAGES.WebCrawler.Url) : __End_i_I_WebCrawlerIDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("m",RoleSet("M")),m)
__End_i_I_WebCrawlerIDoneImp(c,session)}

}


protected  trait End_i_I_WebCrawlerIDone extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeEnd
protected  object End_i_I_WebCrawlerIDone extends End_i_I_WebCrawlerIDone {
  override protected def __children: List[EPWebCrawler_i_I] = List()
  override type implT = __End_i_I_WebCrawlerIDoneImp
  override type implNextT = Nothing
override def toString() : String = {"EPWebCrawler_i_I.End_i_I_WebCrawlerIDone"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_I_WebCrawlerIDoneImp(c,session)}

protected case class __End_i_I_WebCrawlerIDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_I_WebCrawlerIDone
}
  
}



protected  trait End_i_I_WebCrawlerFHandling extends EPWebCrawler_i_I with event_lang.dsl.ChannelTypeEnd
protected  object End_i_I_WebCrawlerFHandling extends End_i_I_WebCrawlerFHandling {
  override protected def __children: List[EPWebCrawler_i_I] = List()
  override type implT = __End_i_I_WebCrawlerFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPWebCrawler_i_I.End_i_I_WebCrawlerFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_I_WebCrawlerFHandlingImp(c,session)}

protected case class __End_i_I_WebCrawlerFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_I_WebCrawlerFHandling
}
  
}


}

object Download_i_I{
trait EPDownload_i_I extends __EPType_I

object EPDownload_i_I extends EPDownload_i_I with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPDownload_i_I] = List(Hdl)
  override type implT = __EPDownload_i_IImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPDownload_i_IImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def argsP: Role = Role("d",RoleSet("D")) 
  override def argsRs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def prjTo : RRole = Role("i",RoleSet("I")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Download"
}

protected case class __EPDownload_i_IImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPDownload_i_I
}
  
}


protected  trait Hdl extends EPDownload_i_I with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPDownload_i_I] = List(SpawnParse,RcvDF)
  override type implT = __HdlImp
  override type implNextT = __SpawnParseImp
override def toString() : String = {"EPDownload_i_I.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnParse extends EPDownload_i_I with event_lang.dsl.ChannelTypeSpawn
  object SpawnParse extends SpawnParse {
  override protected def __children: List[EPDownload_i_I] = List(End_i_I_Download)
  override type implT = __SpawnParseImp
  override type implNextT = __End_i_I_DownloadImp
override def toString() : String = {"EPDownload_i_I.SpawnParse"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Parse" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnParseImp(c,session)}

protected case class __SpawnParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnParse
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_i_I_Download extends EPDownload_i_I with event_lang.dsl.ChannelTypeEnd
protected  object End_i_I_Download extends End_i_I_Download {
  override protected def __children: List[EPDownload_i_I] = List()
  override type implT = __End_i_I_DownloadImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_i_I.End_i_I_Download"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_I_DownloadImp(c,session)}

protected case class __End_i_I_DownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_I_Download
}
  
}



  trait RcvDF extends EPDownload_i_I with event_lang.dsl.ChannelTypeRcv
  object RcvDF extends RcvDF {
  override protected def __children: List[EPDownload_i_I] = List(SpawnDownload)
  override type implT = __RcvDFImp
  override type implNextT = __SpawnDownloadImp
override def toString() : String = {"EPDownload_i_I.RcvDF"}
  override type msgT = MESSAGES.Download.DF
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "DF"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvDFImp(c,session)}

protected case class __RcvDFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvDF
}
  def rcvFrmm_M : (MESSAGES.Download.DF,__SpawnDownloadImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF],__SpawnDownloadImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Download.DF,__SpawnDownloadImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF],__SpawnDownloadImp(c,session))) 
}
def rcvMSG : MESSAGES.Download.DF = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF]}
def ? : MESSAGES.Download.DF = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Download.DF]}
def channelCon : __SpawnDownloadImp = {__SpawnDownloadImp(c,session)}

}


  trait SpawnDownload extends EPDownload_i_I with event_lang.dsl.ChannelTypeSpawn
  object SpawnDownload extends SpawnDownload {
  override protected def __children: List[EPDownload_i_I] = List(End_i_I_DownloadFHandling)
  override type implT = __SpawnDownloadImp
  override type implNextT = __End_i_I_DownloadFHandlingImp
override def toString() : String = {"EPDownload_i_I.SpawnDownload"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def pickR: RoleSet = RoleSet("D") 
  override def rs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def name: String = "Download" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnDownloadImp(c,session)}

protected case class __SpawnDownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnDownload
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_i_I_DownloadFHandling extends EPDownload_i_I with event_lang.dsl.ChannelTypeEnd
protected  object End_i_I_DownloadFHandling extends End_i_I_DownloadFHandling {
  override protected def __children: List[EPDownload_i_I] = List()
  override type implT = __End_i_I_DownloadFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_i_I.End_i_I_DownloadFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_I_DownloadFHandlingImp(c,session)}

protected case class __End_i_I_DownloadFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_I_DownloadFHandling
}
  
}


}

object Parse_i_I{
trait EPParse_i_I extends __EPType_I

object EPParse_i_I extends EPParse_i_I with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPParse_i_I] = List(Hdl)
  override type implT = __EPParse_i_IImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPParse_i_IImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def argsP: Role = Role("p",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = Role("i",RoleSet("I")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Parse"
}

protected case class __EPParse_i_IImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPParse_i_I
}
  
}


protected  trait Hdl extends EPParse_i_I with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPParse_i_I] = List(RcvIndex,RcvPF)
  override type implT = __HdlImp
  override type implNextT = __RcvIndexImp
override def toString() : String = {"EPParse_i_I.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvIndex extends EPParse_i_I with event_lang.dsl.ChannelTypeRcv
  object RcvIndex extends RcvIndex {
  override protected def __children: List[EPParse_i_I] = List(End_i_I_Parse)
  override type implT = __RcvIndexImp
  override type implNextT = __End_i_I_ParseImp
override def toString() : String = {"EPParse_i_I.RcvIndex"}
  override type msgT = MESSAGES.Parse.Index
   override def frm : Role = Role("p",RoleSet("P")) 
   override def l : String = "Index"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvIndexImp(c,session)}

protected case class __RcvIndexImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvIndex
}
  def rcvFrmp_P : (MESSAGES.Parse.Index,__End_i_I_ParseImp) = {(c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.Parse.Index],__End_i_I_ParseImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Parse.Index,__End_i_I_ParseImp),T]) : T = {
  f((c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.Parse.Index],__End_i_I_ParseImp(c,session))) 
}
def rcvMSG : MESSAGES.Parse.Index = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.Parse.Index]}
def ? : MESSAGES.Parse.Index = {c.rcv(Role("p",RoleSet("P"))).asInstanceOf[MESSAGES.Parse.Index]}
def channelCon : __End_i_I_ParseImp = {__End_i_I_ParseImp(c,session)}

}


protected  trait End_i_I_Parse extends EPParse_i_I with event_lang.dsl.ChannelTypeEnd
protected  object End_i_I_Parse extends End_i_I_Parse {
  override protected def __children: List[EPParse_i_I] = List()
  override type implT = __End_i_I_ParseImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_i_I.End_i_I_Parse"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_I_ParseImp(c,session)}

protected case class __End_i_I_ParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_I_Parse
}
  
}



  trait RcvPF extends EPParse_i_I with event_lang.dsl.ChannelTypeRcv
  object RcvPF extends RcvPF {
  override protected def __children: List[EPParse_i_I] = List(SpawnParse)
  override type implT = __RcvPFImp
  override type implNextT = __SpawnParseImp
override def toString() : String = {"EPParse_i_I.RcvPF"}
  override type msgT = MESSAGES.Parse.PF
   override def frm : Role = Role("m",RoleSet("M")) 
   override def l : String = "PF"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvPFImp(c,session)}

protected case class __RcvPFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvPF
}
  def rcvFrmm_M : (MESSAGES.Parse.PF,__SpawnParseImp) = {(c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF],__SpawnParseImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.Parse.PF,__SpawnParseImp),T]) : T = {
  f((c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF],__SpawnParseImp(c,session))) 
}
def rcvMSG : MESSAGES.Parse.PF = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF]}
def ? : MESSAGES.Parse.PF = {c.rcv(Role("m",RoleSet("M"))).asInstanceOf[MESSAGES.Parse.PF]}
def channelCon : __SpawnParseImp = {__SpawnParseImp(c,session)}

}


  trait SpawnParse extends EPParse_i_I with event_lang.dsl.ChannelTypeSpawn
  object SpawnParse extends SpawnParse {
  override protected def __children: List[EPParse_i_I] = List(End_i_I_ParseFHandling)
  override type implT = __SpawnParseImp
  override type implNextT = __End_i_I_ParseFHandlingImp
override def toString() : String = {"EPParse_i_I.SpawnParse"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Parse" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnParseImp(c,session)}

protected case class __SpawnParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnParse
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_i_I_ParseFHandling extends EPParse_i_I with event_lang.dsl.ChannelTypeEnd
protected  object End_i_I_ParseFHandling extends End_i_I_ParseFHandling {
  override protected def __children: List[EPParse_i_I] = List()
  override type implT = __End_i_I_ParseFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_i_I.End_i_I_ParseFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_i_I_ParseFHandlingImp(c,session)}

protected case class __End_i_I_ParseFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_i_I_ParseFHandling
}
  
}


}

}

object M {
val subs : Seq[dsl.ChannelTypeSubS] = List(WebCrawler_m_M.EPWebCrawler_m_M,Download_m_M.EPDownload_m_M,Parse_m_M.EPParse_m_M)
trait __EPType_M extends AbstractChannelType {

}

trait EPType_M[T<: TState] extends AbstractEndPoint[__EPType_M,T] {
override val roleSet: RoleSet = RoleSet("M")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(WebCrawler_m_M.EPWebCrawler_m_M,Download_m_M.EPDownload_m_M,Parse_m_M.EPParse_m_M)

}

object WebCrawler_m_M{
trait EPWebCrawler_m_M extends __EPType_M

object EPWebCrawler_m_M extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPWebCrawler_m_M] = List(Hdl)
  override type implT = __EPWebCrawler_m_MImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPWebCrawler_m_MImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M"))) 
  override def argsP: Role = Role("i",RoleSet("I")) 
  override def argsRs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def prjTo : RRole = Role("m",RoleSet("M")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "WebCrawler"
}

protected case class __EPWebCrawler_m_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPWebCrawler_m_M
}
  
}


protected  trait Hdl extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPWebCrawler_m_M] = List(RecT,Failed_i_I)
  override type implT = __HdlImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPWebCrawler_m_M.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait RecT extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeRec
protected  object RecT extends RecT {
  override protected def __children: List[EPWebCrawler_m_M] = List(SelIAddDIDone)
  override type implT = __RecTImp
  override type implNextT = __SelIAddDIDoneImp
override def toString() : String = {"EPWebCrawler_m_M.RecT"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RecTImp(c,session)}

protected case class __RecTImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RecT
}
  
}


protected  trait SelIAddDIDone extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSel
protected  object SelIAddDIDone extends SelIAddDIDone {
  override protected def __children: List[EPWebCrawler_m_M] = List(SndIAddD,SndIDone)
  override type implT = __SelIAddDIDoneImp
  override type implNextT = __SndIAddDImp
override def toString() : String = {"EPWebCrawler_m_M.SelIAddDIDone"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelIAddDIDoneImp(c,session)}

protected case class __SelIAddDIDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelIAddDIDone
}
  private var notUsed = true
def !(m : MESSAGES.WebCrawler.IAddD) : __SndDAddDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
 __SndDAddDImp(c,session)}
def sndToi_I(m : MESSAGES.WebCrawler.IAddD) : __SndDAddDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
 __SndDAddDImp(c,session)}

def !(m : MESSAGES.WebCrawler.IDone) : __SndDDOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
 __SndDDOneImp(c,session)}
def sndToi_I(m : MESSAGES.WebCrawler.IDone) : __SndDDOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
 __SndDDOneImp(c,session)}

}


  trait SndIAddD extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSnd
  object SndIAddD extends SndIAddD {
  override protected def __children: List[EPWebCrawler_m_M] = List(SndDAddD)
  override type implT = __SndIAddDImp
  override type implNextT = __SndDAddDImp
override def toString() : String = {"EPWebCrawler_m_M.SndIAddD"}
    override def to : RRole = Role("i",RoleSet("I")) 
   override def l : String = "IAddD" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndIAddDImp(c,session)}

protected case class __SndIAddDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndIAddD
}
  private var notUsed = true
def sndToi_I(m : MESSAGES.WebCrawler.IAddD) : __SndDAddDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndDAddDImp(c,session)}
def !(m : MESSAGES.WebCrawler.IAddD) : __SndDAddDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndDAddDImp(c,session)}
def snd(m : MESSAGES.WebCrawler.IAddD) : __SndDAddDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndDAddDImp(c,session)}

}


  trait SndDAddD extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSnd
  object SndDAddD extends SndDAddD {
  override protected def __children: List[EPWebCrawler_m_M] = List(SndPAddD)
  override type implT = __SndDAddDImp
  override type implNextT = __SndPAddDImp
override def toString() : String = {"EPWebCrawler_m_M.SndDAddD"}
    override def to : RRole = RoleSet("D") 
   override def l : String = "DAddD" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDAddDImp(c,session)}

protected case class __SndDAddDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDAddD
}
  private var notUsed = true
def sndToD(m : MESSAGES.WebCrawler.DAddD) : __SndPAddDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndPAddDImp(c,session)}
def !(m : MESSAGES.WebCrawler.DAddD) : __SndPAddDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndPAddDImp(c,session)}
def snd(m : MESSAGES.WebCrawler.DAddD) : __SndPAddDImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndPAddDImp(c,session)}

}


  trait SndPAddD extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSnd
  object SndPAddD extends SndPAddD {
  override protected def __children: List[EPWebCrawler_m_M] = List(SpawnDownload)
  override type implT = __SndPAddDImp
  override type implNextT = __SpawnDownloadImp
override def toString() : String = {"EPWebCrawler_m_M.SndPAddD"}
    override def to : RRole = RoleSet("P") 
   override def l : String = "PAddD" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPAddDImp(c,session)}

protected case class __SndPAddDImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPAddD
}
  private var notUsed = true
def sndToP(m : MESSAGES.WebCrawler.PAddD) : __SpawnDownloadImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnDownloadImp(c,session)}
def !(m : MESSAGES.WebCrawler.PAddD) : __SpawnDownloadImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnDownloadImp(c,session)}
def snd(m : MESSAGES.WebCrawler.PAddD) : __SpawnDownloadImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnDownloadImp(c,session)}

}


  trait SpawnDownload extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSpawn
  object SpawnDownload extends SpawnDownload {
  override protected def __children: List[EPWebCrawler_m_M] = List(T)
  override type implT = __SpawnDownloadImp
  override type implNextT = __TImp
override def toString() : String = {"EPWebCrawler_m_M.SpawnDownload"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def pickR: RoleSet = RoleSet("D") 
  override def rs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def name: String = "Download" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnDownloadImp(c,session)}

protected case class __SpawnDownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnDownload
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait T extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeT
protected  object T extends T {
  override protected def __children: List[EPWebCrawler_m_M] = List(RecT)
  override type implT = __TImp
  override type implNextT = __RecTImp
override def toString() : String = {"EPWebCrawler_m_M.T"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __TImp(c,session)}

protected case class __TImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    T
}
  
}



  trait SndIDone extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSnd
  object SndIDone extends SndIDone {
  override protected def __children: List[EPWebCrawler_m_M] = List(SndDDOne)
  override type implT = __SndIDoneImp
  override type implNextT = __SndDDOneImp
override def toString() : String = {"EPWebCrawler_m_M.SndIDone"}
    override def to : RRole = Role("i",RoleSet("I")) 
   override def l : String = "IDone" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndIDoneImp(c,session)}

protected case class __SndIDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndIDone
}
  private var notUsed = true
def sndToi_I(m : MESSAGES.WebCrawler.IDone) : __SndDDOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndDDOneImp(c,session)}
def !(m : MESSAGES.WebCrawler.IDone) : __SndDDOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndDDOneImp(c,session)}
def snd(m : MESSAGES.WebCrawler.IDone) : __SndDDOneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndDDOneImp(c,session)}

}


  trait SndDDOne extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSnd
  object SndDDOne extends SndDDOne {
  override protected def __children: List[EPWebCrawler_m_M] = List(SndPDone)
  override type implT = __SndDDOneImp
  override type implNextT = __SndPDoneImp
override def toString() : String = {"EPWebCrawler_m_M.SndDDOne"}
    override def to : RRole = RoleSet("D") 
   override def l : String = "DDOne" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDDOneImp(c,session)}

protected case class __SndDDOneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDDOne
}
  private var notUsed = true
def sndToD(m : MESSAGES.WebCrawler.DDOne) : __SndPDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndPDoneImp(c,session)}
def !(m : MESSAGES.WebCrawler.DDOne) : __SndPDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndPDoneImp(c,session)}
def snd(m : MESSAGES.WebCrawler.DDOne) : __SndPDoneImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndPDoneImp(c,session)}

}


  trait SndPDone extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSnd
  object SndPDone extends SndPDone {
  override protected def __children: List[EPWebCrawler_m_M] = List(RcvUrl)
  override type implT = __SndPDoneImp
  override type implNextT = __RcvUrlImp
override def toString() : String = {"EPWebCrawler_m_M.SndPDone"}
    override def to : RRole = RoleSet("P") 
   override def l : String = "PDone" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPDoneImp(c,session)}

protected case class __SndPDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPDone
}
  private var notUsed = true
def sndToP(m : MESSAGES.WebCrawler.PDone) : __RcvUrlImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__RcvUrlImp(c,session)}
def !(m : MESSAGES.WebCrawler.PDone) : __RcvUrlImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__RcvUrlImp(c,session)}
def snd(m : MESSAGES.WebCrawler.PDone) : __RcvUrlImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__RcvUrlImp(c,session)}

}


  trait RcvUrl extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeRcv
  object RcvUrl extends RcvUrl {
  override protected def __children: List[EPWebCrawler_m_M] = List(End_m_M_WebCrawlerIDone)
  override type implT = __RcvUrlImp
  override type implNextT = __End_m_M_WebCrawlerIDoneImp
override def toString() : String = {"EPWebCrawler_m_M.RcvUrl"}
  override type msgT = MESSAGES.WebCrawler.Url
   override def frm : Role = Role("i",RoleSet("I")) 
   override def l : String = "Url"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvUrlImp(c,session)}

protected case class __RcvUrlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvUrl
}
  def rcvFrmi_I : (MESSAGES.WebCrawler.Url,__End_m_M_WebCrawlerIDoneImp) = {(c.rcv(Role("i",RoleSet("I"))).asInstanceOf[MESSAGES.WebCrawler.Url],__End_m_M_WebCrawlerIDoneImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.WebCrawler.Url,__End_m_M_WebCrawlerIDoneImp),T]) : T = {
  f((c.rcv(Role("i",RoleSet("I"))).asInstanceOf[MESSAGES.WebCrawler.Url],__End_m_M_WebCrawlerIDoneImp(c,session))) 
}
def rcvMSG : MESSAGES.WebCrawler.Url = {c.rcv(Role("i",RoleSet("I"))).asInstanceOf[MESSAGES.WebCrawler.Url]}
def ? : MESSAGES.WebCrawler.Url = {c.rcv(Role("i",RoleSet("I"))).asInstanceOf[MESSAGES.WebCrawler.Url]}
def channelCon : __End_m_M_WebCrawlerIDoneImp = {__End_m_M_WebCrawlerIDoneImp(c,session)}

}


protected  trait End_m_M_WebCrawlerIDone extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_WebCrawlerIDone extends End_m_M_WebCrawlerIDone {
  override protected def __children: List[EPWebCrawler_m_M] = List()
  override type implT = __End_m_M_WebCrawlerIDoneImp
  override type implNextT = Nothing
override def toString() : String = {"EPWebCrawler_m_M.End_m_M_WebCrawlerIDone"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_WebCrawlerIDoneImp(c,session)}

protected case class __End_m_M_WebCrawlerIDoneImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_WebCrawlerIDone
}
  
}



  trait Failed_i_I extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeFDtct
  object Failed_i_I extends Failed_i_I {
  override protected def __children: List[EPWebCrawler_m_M] = List(SndF1)
  override type implT = __Failed_i_IImp
  override type implNextT = __SndF1Imp
override def toString() : String = {"EPWebCrawler_m_M.Failed_i_I"}
  override def suspect : Role = Role("i",RoleSet("I")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_i_IImp(c,session)}

protected case class __Failed_i_IImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_i_I
}
  def failed_i_I(): __SndF1Imp = {__SndF1Imp(c,session)}

}


  trait SndF1 extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSnd
  object SndF1 extends SndF1 {
  override protected def __children: List[EPWebCrawler_m_M] = List(SndF2)
  override type implT = __SndF1Imp
  override type implNextT = __SndF2Imp
override def toString() : String = {"EPWebCrawler_m_M.SndF1"}
    override def to : RRole = RoleSet("D") 
   override def l : String = "F1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF1Imp(c,session)}

protected case class __SndF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF1
}
  private var notUsed = true
def sndToD(m : MESSAGES.WebCrawler.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndF2Imp(c,session)}
def !(m : MESSAGES.WebCrawler.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndF2Imp(c,session)}
def snd(m : MESSAGES.WebCrawler.F1) : __SndF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndF2Imp(c,session)}

}


  trait SndF2 extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeSnd
  object SndF2 extends SndF2 {
  override protected def __children: List[EPWebCrawler_m_M] = List(End_m_M_WebCrawlerFHandling)
  override type implT = __SndF2Imp
  override type implNextT = __End_m_M_WebCrawlerFHandlingImp
override def toString() : String = {"EPWebCrawler_m_M.SndF2"}
    override def to : RRole = RoleSet("P") 
   override def l : String = "F2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndF2Imp(c,session)}

protected case class __SndF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndF2
}
  private var notUsed = true
def sndToP(m : MESSAGES.WebCrawler.F2) : __End_m_M_WebCrawlerFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_m_M_WebCrawlerFHandlingImp(c,session)}
def !(m : MESSAGES.WebCrawler.F2) : __End_m_M_WebCrawlerFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_m_M_WebCrawlerFHandlingImp(c,session)}
def snd(m : MESSAGES.WebCrawler.F2) : __End_m_M_WebCrawlerFHandlingImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__End_m_M_WebCrawlerFHandlingImp(c,session)}

}


protected  trait End_m_M_WebCrawlerFHandling extends EPWebCrawler_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_WebCrawlerFHandling extends End_m_M_WebCrawlerFHandling {
  override protected def __children: List[EPWebCrawler_m_M] = List()
  override type implT = __End_m_M_WebCrawlerFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPWebCrawler_m_M.End_m_M_WebCrawlerFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_WebCrawlerFHandlingImp(c,session)}

protected case class __End_m_M_WebCrawlerFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_WebCrawlerFHandling
}
  
}


}

object Download_m_M{
trait EPDownload_m_M extends __EPType_M

object EPDownload_m_M extends EPDownload_m_M with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPDownload_m_M] = List(Hdl)
  override type implT = __EPDownload_m_MImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPDownload_m_MImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def argsP: Role = Role("d",RoleSet("D")) 
  override def argsRs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def prjTo : RRole = Role("m",RoleSet("M")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Download"
}

protected case class __EPDownload_m_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPDownload_m_M
}
  
}


protected  trait Hdl extends EPDownload_m_M with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPDownload_m_M] = List(SpawnParse,Failed_d_D)
  override type implT = __HdlImp
  override type implNextT = __SpawnParseImp
override def toString() : String = {"EPDownload_m_M.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SpawnParse extends EPDownload_m_M with event_lang.dsl.ChannelTypeSpawn
  object SpawnParse extends SpawnParse {
  override protected def __children: List[EPDownload_m_M] = List(End_m_M_Download)
  override type implT = __SpawnParseImp
  override type implNextT = __End_m_M_DownloadImp
override def toString() : String = {"EPDownload_m_M.SpawnParse"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Parse" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnParseImp(c,session)}

protected case class __SpawnParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnParse
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_m_M_Download extends EPDownload_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_Download extends End_m_M_Download {
  override protected def __children: List[EPDownload_m_M] = List()
  override type implT = __End_m_M_DownloadImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_m_M.End_m_M_Download"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_DownloadImp(c,session)}

protected case class __End_m_M_DownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_Download
}
  
}



  trait Failed_d_D extends EPDownload_m_M with event_lang.dsl.ChannelTypeFDtct
  object Failed_d_D extends Failed_d_D {
  override protected def __children: List[EPDownload_m_M] = List(SndDF)
  override type implT = __Failed_d_DImp
  override type implNextT = __SndDFImp
override def toString() : String = {"EPDownload_m_M.Failed_d_D"}
  override def suspect : Role = Role("d",RoleSet("D")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_d_DImp(c,session)}

protected case class __Failed_d_DImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_d_D
}
  def failed_d_D(): __SndDFImp = {__SndDFImp(c,session)}

}


  trait SndDF extends EPDownload_m_M with event_lang.dsl.ChannelTypeSnd
  object SndDF extends SndDF {
  override protected def __children: List[EPDownload_m_M] = List(SndDF1)
  override type implT = __SndDFImp
  override type implNextT = __SndDF1Imp
override def toString() : String = {"EPDownload_m_M.SndDF"}
    override def to : RRole = Role("i",RoleSet("I")) 
   override def l : String = "DF" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDFImp(c,session)}

protected case class __SndDFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDF
}
  private var notUsed = true
def sndToi_I(m : MESSAGES.Download.DF) : __SndDF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndDF1Imp(c,session)}
def !(m : MESSAGES.Download.DF) : __SndDF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndDF1Imp(c,session)}
def snd(m : MESSAGES.Download.DF) : __SndDF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndDF1Imp(c,session)}

}


  trait SndDF1 extends EPDownload_m_M with event_lang.dsl.ChannelTypeSnd
  object SndDF1 extends SndDF1 {
  override protected def __children: List[EPDownload_m_M] = List(SndDF2)
  override type implT = __SndDF1Imp
  override type implNextT = __SndDF2Imp
override def toString() : String = {"EPDownload_m_M.SndDF1"}
    override def to : RRole = RoleSet("D") 
   override def l : String = "DF1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDF1Imp(c,session)}

protected case class __SndDF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDF1
}
  private var notUsed = true
def sndToD(m : MESSAGES.Download.DF1) : __SndDF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndDF2Imp(c,session)}
def !(m : MESSAGES.Download.DF1) : __SndDF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndDF2Imp(c,session)}
def snd(m : MESSAGES.Download.DF1) : __SndDF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("D"),m)
__SndDF2Imp(c,session)}

}


  trait SndDF2 extends EPDownload_m_M with event_lang.dsl.ChannelTypeSnd
  object SndDF2 extends SndDF2 {
  override protected def __children: List[EPDownload_m_M] = List(SpawnDownload)
  override type implT = __SndDF2Imp
  override type implNextT = __SpawnDownloadImp
override def toString() : String = {"EPDownload_m_M.SndDF2"}
    override def to : RRole = RoleSet("P") 
   override def l : String = "DF2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndDF2Imp(c,session)}

protected case class __SndDF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndDF2
}
  private var notUsed = true
def sndToP(m : MESSAGES.Download.DF2) : __SpawnDownloadImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnDownloadImp(c,session)}
def !(m : MESSAGES.Download.DF2) : __SpawnDownloadImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnDownloadImp(c,session)}
def snd(m : MESSAGES.Download.DF2) : __SpawnDownloadImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnDownloadImp(c,session)}

}


  trait SpawnDownload extends EPDownload_m_M with event_lang.dsl.ChannelTypeSpawn
  object SpawnDownload extends SpawnDownload {
  override protected def __children: List[EPDownload_m_M] = List(End_m_M_DownloadFHandling)
  override type implT = __SpawnDownloadImp
  override type implNextT = __End_m_M_DownloadFHandlingImp
override def toString() : String = {"EPDownload_m_M.SpawnDownload"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I"))) 
  override def pickR: RoleSet = RoleSet("D") 
  override def rs: List[RoleSet] = List(RoleSet("D"), RoleSet("P")) 
  override def name: String = "Download" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnDownloadImp(c,session)}

protected case class __SpawnDownloadImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnDownload
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_m_M_DownloadFHandling extends EPDownload_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_DownloadFHandling extends End_m_M_DownloadFHandling {
  override protected def __children: List[EPDownload_m_M] = List()
  override type implT = __End_m_M_DownloadFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPDownload_m_M.End_m_M_DownloadFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_DownloadFHandlingImp(c,session)}

protected case class __End_m_M_DownloadFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_DownloadFHandling
}
  
}


}

object Parse_m_M{
trait EPParse_m_M extends __EPType_M

object EPParse_m_M extends EPParse_m_M with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPParse_m_M] = List(Hdl)
  override type implT = __EPParse_m_MImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPParse_m_MImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def argsP: Role = Role("p",RoleSet("P")) 
  override def argsRs: List[RoleSet] = List(RoleSet("P")) 
  override def prjTo : RRole = Role("m",RoleSet("M")) 
  override def rootRole: Role = Role("m",RoleSet("M")) 
  override def name : String = "Parse"
}

protected case class __EPParse_m_MImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPParse_m_M
}
  
}


protected  trait Hdl extends EPParse_m_M with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPParse_m_M] = List(End_m_M_Parse,Failed_p_P)
  override type implT = __HdlImp
  override type implNextT = __End_m_M_ParseImp
override def toString() : String = {"EPParse_m_M.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


protected  trait End_m_M_Parse extends EPParse_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_Parse extends End_m_M_Parse {
  override protected def __children: List[EPParse_m_M] = List()
  override type implT = __End_m_M_ParseImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_m_M.End_m_M_Parse"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_ParseImp(c,session)}

protected case class __End_m_M_ParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_Parse
}
  
}



  trait Failed_p_P extends EPParse_m_M with event_lang.dsl.ChannelTypeFDtct
  object Failed_p_P extends Failed_p_P {
  override protected def __children: List[EPParse_m_M] = List(SndPF)
  override type implT = __Failed_p_PImp
  override type implNextT = __SndPFImp
override def toString() : String = {"EPParse_m_M.Failed_p_P"}
  override def suspect : Role = Role("p",RoleSet("P")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_p_PImp(c,session)}

protected case class __Failed_p_PImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_p_P
}
  def failed_p_P(): __SndPFImp = {__SndPFImp(c,session)}

}


  trait SndPF extends EPParse_m_M with event_lang.dsl.ChannelTypeSnd
  object SndPF extends SndPF {
  override protected def __children: List[EPParse_m_M] = List(SndPF1)
  override type implT = __SndPFImp
  override type implNextT = __SndPF1Imp
override def toString() : String = {"EPParse_m_M.SndPF"}
    override def to : RRole = Role("i",RoleSet("I")) 
   override def l : String = "PF" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPFImp(c,session)}

protected case class __SndPFImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPF
}
  private var notUsed = true
def sndToi_I(m : MESSAGES.Parse.PF) : __SndPF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndPF1Imp(c,session)}
def !(m : MESSAGES.Parse.PF) : __SndPF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndPF1Imp(c,session)}
def snd(m : MESSAGES.Parse.PF) : __SndPF1Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("i",RoleSet("I")),m)
__SndPF1Imp(c,session)}

}


  trait SndPF1 extends EPParse_m_M with event_lang.dsl.ChannelTypeSnd
  object SndPF1 extends SndPF1 {
  override protected def __children: List[EPParse_m_M] = List(SndPF2)
  override type implT = __SndPF1Imp
  override type implNextT = __SndPF2Imp
override def toString() : String = {"EPParse_m_M.SndPF1"}
    override def to : RRole = Role("d",RoleSet("D")) 
   override def l : String = "PF1" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPF1Imp(c,session)}

protected case class __SndPF1Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPF1
}
  private var notUsed = true
def sndTod_D(m : MESSAGES.Parse.PF1) : __SndPF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("d",RoleSet("D")),m)
__SndPF2Imp(c,session)}
def !(m : MESSAGES.Parse.PF1) : __SndPF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("d",RoleSet("D")),m)
__SndPF2Imp(c,session)}
def snd(m : MESSAGES.Parse.PF1) : __SndPF2Imp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("d",RoleSet("D")),m)
__SndPF2Imp(c,session)}

}


  trait SndPF2 extends EPParse_m_M with event_lang.dsl.ChannelTypeSnd
  object SndPF2 extends SndPF2 {
  override protected def __children: List[EPParse_m_M] = List(SpawnParse)
  override type implT = __SndPF2Imp
  override type implNextT = __SpawnParseImp
override def toString() : String = {"EPParse_m_M.SndPF2"}
    override def to : RRole = RoleSet("P") 
   override def l : String = "PF2" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndPF2Imp(c,session)}

protected case class __SndPF2Imp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndPF2
}
  private var notUsed = true
def sndToP(m : MESSAGES.Parse.PF2) : __SpawnParseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnParseImp(c,session)}
def !(m : MESSAGES.Parse.PF2) : __SpawnParseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnParseImp(c,session)}
def snd(m : MESSAGES.Parse.PF2) : __SpawnParseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(RoleSet("P"),m)
__SpawnParseImp(c,session)}

}


  trait SpawnParse extends EPParse_m_M with event_lang.dsl.ChannelTypeSpawn
  object SpawnParse extends SpawnParse {
  override protected def __children: List[EPParse_m_M] = List(End_m_M_ParseFHandling)
  override type implT = __SpawnParseImp
  override type implNextT = __End_m_M_ParseFHandlingImp
override def toString() : String = {"EPParse_m_M.SpawnParse"}
    override def y: List[Role] = List(Role("m",RoleSet("M")), Role("i",RoleSet("I")), Role("d",RoleSet("D"))) 
  override def pickR: RoleSet = RoleSet("P") 
  override def rs: List[RoleSet] = List(RoleSet("P")) 
  override def name: String = "Parse" 
  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {  null   }
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SpawnParseImp(c,session)}

protected case class __SpawnParseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SpawnParse
}
  // SPAWN is handled internally -- i.e. no use code here
}


protected  trait End_m_M_ParseFHandling extends EPParse_m_M with event_lang.dsl.ChannelTypeEnd
protected  object End_m_M_ParseFHandling extends End_m_M_ParseFHandling {
  override protected def __children: List[EPParse_m_M] = List()
  override type implT = __End_m_M_ParseFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPParse_m_M.End_m_M_ParseFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_m_M_ParseFHandlingImp(c,session)}

protected case class __End_m_M_ParseFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_m_M_ParseFHandling
}
  
}


}

}

}
