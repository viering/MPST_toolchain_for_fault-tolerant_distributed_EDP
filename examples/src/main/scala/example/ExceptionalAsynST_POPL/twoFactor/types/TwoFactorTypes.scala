package example.ExceptionalAsynST_POPL.twoFactor.types
import event_lang._ 
import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}
import event_lang.types.{MSG, Role, RoleSet,RRole}
/* ########################################## 
 * ###### GENERATED CODE - DO NOT EDIT ######
 * ##########################################
*/



object TwoFactor {
object RS {
val C : RoleSet = RoleSet("C")
val S : RoleSet = RoleSet("S")
}

object MESSAGES {
object TwoFactor {
case class ChallengeKey(k:String) extends MSG {
   override def l : String = "ChallengeKey"
}

case class ChallengeAccessDenied() extends MSG {
   override def l : String = "ChallengeAccessDenied"
}

case class AccData(us:String,upw:String) extends MSG {
   override def l : String = "AccData"
}

case class ChallengeAuthenticated() extends MSG {
   override def l : String = "ChallengeAuthenticated"
}

case class Response(s:String) extends MSG {
   override def l : String = "Response"
}

case class Authenticated() extends MSG {
   override def l : String = "Authenticated"
}

case class AccessDenied() extends MSG {
   override def l : String = "AccessDenied"
}

case class Challenge() extends MSG {
   override def l : String = "Challenge"
}

}

}

object PROTOCOLS {
object TwoFactor {
val c_C = Role("c",RoleSet("C"))
val s_S = Role("s",RoleSet("S"))
}

}

object C {
val subs : Seq[dsl.ChannelTypeSubS] = List(TwoFactor_c_C.EPTwoFactor_c_C)
trait __EPType_C extends AbstractChannelType {

}

trait EPType_C[T<: TState] extends AbstractEndPoint[__EPType_C,T] {
override val roleSet: RoleSet = RoleSet("C")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(TwoFactor_c_C.EPTwoFactor_c_C)

}

object TwoFactor_c_C{
trait EPTwoFactor_c_C extends __EPType_C

object EPTwoFactor_c_C extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPTwoFactor_c_C] = List(Hdl)
  override type implT = __EPTwoFactor_c_CImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPTwoFactor_c_CImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("c",RoleSet("C")) 
  override def rootRole: Role = Role("s",RoleSet("S")) 
  override def name : String = "TwoFactor"
}

protected case class __EPTwoFactor_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPTwoFactor_c_C
}
  
}


protected  trait Hdl extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPTwoFactor_c_C] = List(SndAccData,End_c_C_TwoFactorFHandling)
  override type implT = __HdlImp
  override type implNextT = __SndAccDataImp
override def toString() : String = {"EPTwoFactor_c_C.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait SndAccData extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeSnd
  object SndAccData extends SndAccData {
  override protected def __children: List[EPTwoFactor_c_C] = List(SelAuthenticatedChallengeAccessDenied)
  override type implT = __SndAccDataImp
  override type implNextT = __SelAuthenticatedChallengeAccessDeniedImp
override def toString() : String = {"EPTwoFactor_c_C.SndAccData"}
    override def to : RRole = Role("s",RoleSet("S")) 
   override def l : String = "AccData" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAccDataImp(c,session)}

protected case class __SndAccDataImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAccData
}
  private var notUsed = true
def sndTos_S(m : MESSAGES.TwoFactor.AccData) : __SelAuthenticatedChallengeAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SelAuthenticatedChallengeAccessDeniedImp(c,session)}
def !(m : MESSAGES.TwoFactor.AccData) : __SelAuthenticatedChallengeAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SelAuthenticatedChallengeAccessDeniedImp(c,session)}
def snd(m : MESSAGES.TwoFactor.AccData) : __SelAuthenticatedChallengeAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SelAuthenticatedChallengeAccessDeniedImp(c,session)}

}


protected  trait SelAuthenticatedChallengeAccessDenied extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeBrn
protected  object SelAuthenticatedChallengeAccessDenied extends SelAuthenticatedChallengeAccessDenied {
  override protected def __children: List[EPTwoFactor_c_C] = List(RcvAuthenticated,RcvChallenge,RcvAccessDenied)
  override type implT = __SelAuthenticatedChallengeAccessDeniedImp
  override type implNextT = __RcvAuthenticatedImp
override def toString() : String = {"EPTwoFactor_c_C.SelAuthenticatedChallengeAccessDenied"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelAuthenticatedChallengeAccessDeniedImp(c,session)}

protected case class __SelAuthenticatedChallengeAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelAuthenticatedChallengeAccessDenied
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvAuthenticated extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvAuthenticated extends RcvAuthenticated {
  override protected def __children: List[EPTwoFactor_c_C] = List(End_c_C_TwoFactorAuthenticated)
  override type implT = __RcvAuthenticatedImp
  override type implNextT = __End_c_C_TwoFactorAuthenticatedImp
override def toString() : String = {"EPTwoFactor_c_C.RcvAuthenticated"}
  override type msgT = MESSAGES.TwoFactor.Authenticated
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "Authenticated"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAuthenticatedImp(c,session)}

protected case class __RcvAuthenticatedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAuthenticated
}
  def rcvFrms_S : (MESSAGES.TwoFactor.Authenticated,__End_c_C_TwoFactorAuthenticatedImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.Authenticated],__End_c_C_TwoFactorAuthenticatedImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoFactor.Authenticated,__End_c_C_TwoFactorAuthenticatedImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.Authenticated],__End_c_C_TwoFactorAuthenticatedImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoFactor.Authenticated = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.Authenticated]}
def ? : MESSAGES.TwoFactor.Authenticated = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.Authenticated]}
def channelCon : __End_c_C_TwoFactorAuthenticatedImp = {__End_c_C_TwoFactorAuthenticatedImp(c,session)}

}


protected  trait End_c_C_TwoFactorAuthenticated extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_TwoFactorAuthenticated extends End_c_C_TwoFactorAuthenticated {
  override protected def __children: List[EPTwoFactor_c_C] = List()
  override type implT = __End_c_C_TwoFactorAuthenticatedImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_c_C.End_c_C_TwoFactorAuthenticated"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_TwoFactorAuthenticatedImp(c,session)}

protected case class __End_c_C_TwoFactorAuthenticatedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_TwoFactorAuthenticated
}
  
}



  trait RcvChallenge extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvChallenge extends RcvChallenge {
  override protected def __children: List[EPTwoFactor_c_C] = List(RcvChallengeKey)
  override type implT = __RcvChallengeImp
  override type implNextT = __RcvChallengeKeyImp
override def toString() : String = {"EPTwoFactor_c_C.RcvChallenge"}
  override type msgT = MESSAGES.TwoFactor.Challenge
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "Challenge"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvChallengeImp(c,session)}

protected case class __RcvChallengeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvChallenge
}
  def rcvFrms_S : (MESSAGES.TwoFactor.Challenge,__RcvChallengeKeyImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.Challenge],__RcvChallengeKeyImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoFactor.Challenge,__RcvChallengeKeyImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.Challenge],__RcvChallengeKeyImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoFactor.Challenge = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.Challenge]}
def ? : MESSAGES.TwoFactor.Challenge = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.Challenge]}
def channelCon : __RcvChallengeKeyImp = {__RcvChallengeKeyImp(c,session)}

}


  trait RcvChallengeKey extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvChallengeKey extends RcvChallengeKey {
  override protected def __children: List[EPTwoFactor_c_C] = List(SndResponse)
  override type implT = __RcvChallengeKeyImp
  override type implNextT = __SndResponseImp
override def toString() : String = {"EPTwoFactor_c_C.RcvChallengeKey"}
  override type msgT = MESSAGES.TwoFactor.ChallengeKey
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "ChallengeKey"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvChallengeKeyImp(c,session)}

protected case class __RcvChallengeKeyImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvChallengeKey
}
  def rcvFrms_S : (MESSAGES.TwoFactor.ChallengeKey,__SndResponseImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeKey],__SndResponseImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoFactor.ChallengeKey,__SndResponseImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeKey],__SndResponseImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoFactor.ChallengeKey = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeKey]}
def ? : MESSAGES.TwoFactor.ChallengeKey = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeKey]}
def channelCon : __SndResponseImp = {__SndResponseImp(c,session)}

}


  trait SndResponse extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeSnd
  object SndResponse extends SndResponse {
  override protected def __children: List[EPTwoFactor_c_C] = List(SelChallengeAuthenticatedChallengeAccessDenied)
  override type implT = __SndResponseImp
  override type implNextT = __SelChallengeAuthenticatedChallengeAccessDeniedImp
override def toString() : String = {"EPTwoFactor_c_C.SndResponse"}
    override def to : RRole = Role("s",RoleSet("S")) 
   override def l : String = "Response" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndResponseImp(c,session)}

protected case class __SndResponseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndResponse
}
  private var notUsed = true
def sndTos_S(m : MESSAGES.TwoFactor.Response) : __SelChallengeAuthenticatedChallengeAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SelChallengeAuthenticatedChallengeAccessDeniedImp(c,session)}
def !(m : MESSAGES.TwoFactor.Response) : __SelChallengeAuthenticatedChallengeAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SelChallengeAuthenticatedChallengeAccessDeniedImp(c,session)}
def snd(m : MESSAGES.TwoFactor.Response) : __SelChallengeAuthenticatedChallengeAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("s",RoleSet("S")),m)
__SelChallengeAuthenticatedChallengeAccessDeniedImp(c,session)}

}


protected  trait SelChallengeAuthenticatedChallengeAccessDenied extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeBrn
protected  object SelChallengeAuthenticatedChallengeAccessDenied extends SelChallengeAuthenticatedChallengeAccessDenied {
  override protected def __children: List[EPTwoFactor_c_C] = List(RcvChallengeAuthenticated,RcvChallengeAccessDenied)
  override type implT = __SelChallengeAuthenticatedChallengeAccessDeniedImp
  override type implNextT = __RcvChallengeAuthenticatedImp
override def toString() : String = {"EPTwoFactor_c_C.SelChallengeAuthenticatedChallengeAccessDenied"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelChallengeAuthenticatedChallengeAccessDeniedImp(c,session)}

protected case class __SelChallengeAuthenticatedChallengeAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelChallengeAuthenticatedChallengeAccessDenied
}
  // Branching is only a valid return type not a valid input type
}


  trait RcvChallengeAuthenticated extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvChallengeAuthenticated extends RcvChallengeAuthenticated {
  override protected def __children: List[EPTwoFactor_c_C] = List(End_c_C_TwoFactorChallengeAuthenticated_Challenge)
  override type implT = __RcvChallengeAuthenticatedImp
  override type implNextT = __End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp
override def toString() : String = {"EPTwoFactor_c_C.RcvChallengeAuthenticated"}
  override type msgT = MESSAGES.TwoFactor.ChallengeAuthenticated
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "ChallengeAuthenticated"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvChallengeAuthenticatedImp(c,session)}

protected case class __RcvChallengeAuthenticatedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvChallengeAuthenticated
}
  def rcvFrms_S : (MESSAGES.TwoFactor.ChallengeAuthenticated,__End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeAuthenticated],__End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoFactor.ChallengeAuthenticated,__End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeAuthenticated],__End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoFactor.ChallengeAuthenticated = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeAuthenticated]}
def ? : MESSAGES.TwoFactor.ChallengeAuthenticated = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeAuthenticated]}
def channelCon : __End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp = {__End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp(c,session)}

}


protected  trait End_c_C_TwoFactorChallengeAuthenticated_Challenge extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_TwoFactorChallengeAuthenticated_Challenge extends End_c_C_TwoFactorChallengeAuthenticated_Challenge {
  override protected def __children: List[EPTwoFactor_c_C] = List()
  override type implT = __End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_c_C.End_c_C_TwoFactorChallengeAuthenticated_Challenge"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp(c,session)}

protected case class __End_c_C_TwoFactorChallengeAuthenticated_ChallengeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_TwoFactorChallengeAuthenticated_Challenge
}
  
}



  trait RcvChallengeAccessDenied extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvChallengeAccessDenied extends RcvChallengeAccessDenied {
  override protected def __children: List[EPTwoFactor_c_C] = List(End_c_C_TwoFactorChallengeAccessDenied_Challenge)
  override type implT = __RcvChallengeAccessDeniedImp
  override type implNextT = __End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp
override def toString() : String = {"EPTwoFactor_c_C.RcvChallengeAccessDenied"}
  override type msgT = MESSAGES.TwoFactor.ChallengeAccessDenied
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "ChallengeAccessDenied"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvChallengeAccessDeniedImp(c,session)}

protected case class __RcvChallengeAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvChallengeAccessDenied
}
  def rcvFrms_S : (MESSAGES.TwoFactor.ChallengeAccessDenied,__End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeAccessDenied],__End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoFactor.ChallengeAccessDenied,__End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeAccessDenied],__End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoFactor.ChallengeAccessDenied = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeAccessDenied]}
def ? : MESSAGES.TwoFactor.ChallengeAccessDenied = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.ChallengeAccessDenied]}
def channelCon : __End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp = {__End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp(c,session)}

}


protected  trait End_c_C_TwoFactorChallengeAccessDenied_Challenge extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_TwoFactorChallengeAccessDenied_Challenge extends End_c_C_TwoFactorChallengeAccessDenied_Challenge {
  override protected def __children: List[EPTwoFactor_c_C] = List()
  override type implT = __End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_c_C.End_c_C_TwoFactorChallengeAccessDenied_Challenge"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp(c,session)}

protected case class __End_c_C_TwoFactorChallengeAccessDenied_ChallengeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_TwoFactorChallengeAccessDenied_Challenge
}
  
}



  trait RcvAccessDenied extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeRcv
  object RcvAccessDenied extends RcvAccessDenied {
  override protected def __children: List[EPTwoFactor_c_C] = List(End_c_C_TwoFactorAccessDenied)
  override type implT = __RcvAccessDeniedImp
  override type implNextT = __End_c_C_TwoFactorAccessDeniedImp
override def toString() : String = {"EPTwoFactor_c_C.RcvAccessDenied"}
  override type msgT = MESSAGES.TwoFactor.AccessDenied
   override def frm : Role = Role("s",RoleSet("S")) 
   override def l : String = "AccessDenied"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAccessDeniedImp(c,session)}

protected case class __RcvAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAccessDenied
}
  def rcvFrms_S : (MESSAGES.TwoFactor.AccessDenied,__End_c_C_TwoFactorAccessDeniedImp) = {(c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.AccessDenied],__End_c_C_TwoFactorAccessDeniedImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoFactor.AccessDenied,__End_c_C_TwoFactorAccessDeniedImp),T]) : T = {
  f((c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.AccessDenied],__End_c_C_TwoFactorAccessDeniedImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoFactor.AccessDenied = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.AccessDenied]}
def ? : MESSAGES.TwoFactor.AccessDenied = {c.rcv(Role("s",RoleSet("S"))).asInstanceOf[MESSAGES.TwoFactor.AccessDenied]}
def channelCon : __End_c_C_TwoFactorAccessDeniedImp = {__End_c_C_TwoFactorAccessDeniedImp(c,session)}

}


protected  trait End_c_C_TwoFactorAccessDenied extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_TwoFactorAccessDenied extends End_c_C_TwoFactorAccessDenied {
  override protected def __children: List[EPTwoFactor_c_C] = List()
  override type implT = __End_c_C_TwoFactorAccessDeniedImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_c_C.End_c_C_TwoFactorAccessDenied"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_TwoFactorAccessDeniedImp(c,session)}

protected case class __End_c_C_TwoFactorAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_TwoFactorAccessDenied
}
  
}



protected  trait End_c_C_TwoFactorFHandling extends EPTwoFactor_c_C with event_lang.dsl.ChannelTypeEnd
protected  object End_c_C_TwoFactorFHandling extends End_c_C_TwoFactorFHandling {
  override protected def __children: List[EPTwoFactor_c_C] = List()
  override type implT = __End_c_C_TwoFactorFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_c_C.End_c_C_TwoFactorFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_c_C_TwoFactorFHandlingImp(c,session)}

protected case class __End_c_C_TwoFactorFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_c_C_TwoFactorFHandling
}
  
}


}

}

object S {
val subs : Seq[dsl.ChannelTypeSubS] = List(TwoFactor_s_S.EPTwoFactor_s_S)
trait __EPType_S extends AbstractChannelType {

}

trait EPType_S[T<: TState] extends AbstractEndPoint[__EPType_S,T] {
override val roleSet: RoleSet = RoleSet("S")
  override val subs : Seq[dsl.ChannelTypeSubS] = List(TwoFactor_s_S.EPTwoFactor_s_S)

}

object TwoFactor_s_S{
trait EPTwoFactor_s_S extends __EPType_S

object EPTwoFactor_s_S extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeSubS {
  override protected def __children: List[EPTwoFactor_s_S] = List(Hdl)
  override type implT = __EPTwoFactor_s_SImp 
  
  override def __create(c : AbstractChannel, session : Session) : implT = __EPTwoFactor_s_SImp(c,session) 
  override def body: AbstractChannelType = children.head
  override def argsC: List[Role] = List(Role("s",RoleSet("S"))) 
  override def argsP: Role = Role("c",RoleSet("C")) 
  override def argsRs: List[RoleSet] = List() 
  override def prjTo : RRole = Role("s",RoleSet("S")) 
  override def rootRole: Role = Role("s",RoleSet("S")) 
  override def name : String = "TwoFactor"
}

protected case class __EPTwoFactor_s_SImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    EPTwoFactor_s_S
}
  
}


protected  trait Hdl extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeHdl
protected  object Hdl extends Hdl {
  override protected def __children: List[EPTwoFactor_s_S] = List(RcvAccData,Failed_c_C)
  override type implT = __HdlImp
  override type implNextT = __RcvAccDataImp
override def toString() : String = {"EPTwoFactor_s_S.Hdl"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __HdlImp(c,session)}

protected case class __HdlImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Hdl
}
  
}


  trait RcvAccData extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeRcv
  object RcvAccData extends RcvAccData {
  override protected def __children: List[EPTwoFactor_s_S] = List(SelAuthenticatedChallengeAccessDenied)
  override type implT = __RcvAccDataImp
  override type implNextT = __SelAuthenticatedChallengeAccessDeniedImp
override def toString() : String = {"EPTwoFactor_s_S.RcvAccData"}
  override type msgT = MESSAGES.TwoFactor.AccData
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "AccData"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvAccDataImp(c,session)}

protected case class __RcvAccDataImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvAccData
}
  def rcvFrmc_C : (MESSAGES.TwoFactor.AccData,__SelAuthenticatedChallengeAccessDeniedImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.TwoFactor.AccData],__SelAuthenticatedChallengeAccessDeniedImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoFactor.AccData,__SelAuthenticatedChallengeAccessDeniedImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.TwoFactor.AccData],__SelAuthenticatedChallengeAccessDeniedImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoFactor.AccData = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.TwoFactor.AccData]}
def ? : MESSAGES.TwoFactor.AccData = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.TwoFactor.AccData]}
def channelCon : __SelAuthenticatedChallengeAccessDeniedImp = {__SelAuthenticatedChallengeAccessDeniedImp(c,session)}

}


protected  trait SelAuthenticatedChallengeAccessDenied extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeSel
protected  object SelAuthenticatedChallengeAccessDenied extends SelAuthenticatedChallengeAccessDenied {
  override protected def __children: List[EPTwoFactor_s_S] = List(SndAuthenticated,SndChallenge,SndAccessDenied)
  override type implT = __SelAuthenticatedChallengeAccessDeniedImp
  override type implNextT = __SndAuthenticatedImp
override def toString() : String = {"EPTwoFactor_s_S.SelAuthenticatedChallengeAccessDenied"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelAuthenticatedChallengeAccessDeniedImp(c,session)}

protected case class __SelAuthenticatedChallengeAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelAuthenticatedChallengeAccessDenied
}
  private var notUsed = true
def !(m : MESSAGES.TwoFactor.Authenticated) : __End_s_S_TwoFactorAuthenticatedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_s_S_TwoFactorAuthenticatedImp(c,session)}
def sndToc_C(m : MESSAGES.TwoFactor.Authenticated) : __End_s_S_TwoFactorAuthenticatedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_s_S_TwoFactorAuthenticatedImp(c,session)}

def !(m : MESSAGES.TwoFactor.Challenge) : __SndChallengeKeyImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __SndChallengeKeyImp(c,session)}
def sndToc_C(m : MESSAGES.TwoFactor.Challenge) : __SndChallengeKeyImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __SndChallengeKeyImp(c,session)}

def !(m : MESSAGES.TwoFactor.AccessDenied) : __End_s_S_TwoFactorAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_s_S_TwoFactorAccessDeniedImp(c,session)}
def sndToc_C(m : MESSAGES.TwoFactor.AccessDenied) : __End_s_S_TwoFactorAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_s_S_TwoFactorAccessDeniedImp(c,session)}

}


  trait SndAuthenticated extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeSnd
  object SndAuthenticated extends SndAuthenticated {
  override protected def __children: List[EPTwoFactor_s_S] = List(End_s_S_TwoFactorAuthenticated)
  override type implT = __SndAuthenticatedImp
  override type implNextT = __End_s_S_TwoFactorAuthenticatedImp
override def toString() : String = {"EPTwoFactor_s_S.SndAuthenticated"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "Authenticated" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAuthenticatedImp(c,session)}

protected case class __SndAuthenticatedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAuthenticated
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.TwoFactor.Authenticated) : __End_s_S_TwoFactorAuthenticatedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorAuthenticatedImp(c,session)}
def !(m : MESSAGES.TwoFactor.Authenticated) : __End_s_S_TwoFactorAuthenticatedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorAuthenticatedImp(c,session)}
def snd(m : MESSAGES.TwoFactor.Authenticated) : __End_s_S_TwoFactorAuthenticatedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorAuthenticatedImp(c,session)}

}


protected  trait End_s_S_TwoFactorAuthenticated extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_TwoFactorAuthenticated extends End_s_S_TwoFactorAuthenticated {
  override protected def __children: List[EPTwoFactor_s_S] = List()
  override type implT = __End_s_S_TwoFactorAuthenticatedImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_s_S.End_s_S_TwoFactorAuthenticated"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_TwoFactorAuthenticatedImp(c,session)}

protected case class __End_s_S_TwoFactorAuthenticatedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_TwoFactorAuthenticated
}
  
}



  trait SndChallenge extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeSnd
  object SndChallenge extends SndChallenge {
  override protected def __children: List[EPTwoFactor_s_S] = List(SndChallengeKey)
  override type implT = __SndChallengeImp
  override type implNextT = __SndChallengeKeyImp
override def toString() : String = {"EPTwoFactor_s_S.SndChallenge"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "Challenge" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndChallengeImp(c,session)}

protected case class __SndChallengeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndChallenge
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.TwoFactor.Challenge) : __SndChallengeKeyImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__SndChallengeKeyImp(c,session)}
def !(m : MESSAGES.TwoFactor.Challenge) : __SndChallengeKeyImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__SndChallengeKeyImp(c,session)}
def snd(m : MESSAGES.TwoFactor.Challenge) : __SndChallengeKeyImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__SndChallengeKeyImp(c,session)}

}


  trait SndChallengeKey extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeSnd
  object SndChallengeKey extends SndChallengeKey {
  override protected def __children: List[EPTwoFactor_s_S] = List(RcvResponse)
  override type implT = __SndChallengeKeyImp
  override type implNextT = __RcvResponseImp
override def toString() : String = {"EPTwoFactor_s_S.SndChallengeKey"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "ChallengeKey" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndChallengeKeyImp(c,session)}

protected case class __SndChallengeKeyImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndChallengeKey
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.TwoFactor.ChallengeKey) : __RcvResponseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__RcvResponseImp(c,session)}
def !(m : MESSAGES.TwoFactor.ChallengeKey) : __RcvResponseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__RcvResponseImp(c,session)}
def snd(m : MESSAGES.TwoFactor.ChallengeKey) : __RcvResponseImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__RcvResponseImp(c,session)}

}


  trait RcvResponse extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeRcv
  object RcvResponse extends RcvResponse {
  override protected def __children: List[EPTwoFactor_s_S] = List(SelChallengeAuthenticatedChallengeAccessDenied)
  override type implT = __RcvResponseImp
  override type implNextT = __SelChallengeAuthenticatedChallengeAccessDeniedImp
override def toString() : String = {"EPTwoFactor_s_S.RcvResponse"}
  override type msgT = MESSAGES.TwoFactor.Response
   override def frm : Role = Role("c",RoleSet("C")) 
   override def l : String = "Response"
  override protected def __create(c : AbstractChannel, session : Session) : implT = __RcvResponseImp(c,session)}

protected case class __RcvResponseImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    RcvResponse
}
  def rcvFrmc_C : (MESSAGES.TwoFactor.Response,__SelChallengeAuthenticatedChallengeAccessDeniedImp) = {(c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.TwoFactor.Response],__SelChallengeAuthenticatedChallengeAccessDeniedImp(c,session))}
def ?[T](f : PartialFunction[(MESSAGES.TwoFactor.Response,__SelChallengeAuthenticatedChallengeAccessDeniedImp),T]) : T = {
  f((c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.TwoFactor.Response],__SelChallengeAuthenticatedChallengeAccessDeniedImp(c,session))) 
}
def rcvMSG : MESSAGES.TwoFactor.Response = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.TwoFactor.Response]}
def ? : MESSAGES.TwoFactor.Response = {c.rcv(Role("c",RoleSet("C"))).asInstanceOf[MESSAGES.TwoFactor.Response]}
def channelCon : __SelChallengeAuthenticatedChallengeAccessDeniedImp = {__SelChallengeAuthenticatedChallengeAccessDeniedImp(c,session)}

}


protected  trait SelChallengeAuthenticatedChallengeAccessDenied extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeSel
protected  object SelChallengeAuthenticatedChallengeAccessDenied extends SelChallengeAuthenticatedChallengeAccessDenied {
  override protected def __children: List[EPTwoFactor_s_S] = List(SndChallengeAuthenticated,SndChallengeAccessDenied)
  override type implT = __SelChallengeAuthenticatedChallengeAccessDeniedImp
  override type implNextT = __SndChallengeAuthenticatedImp
override def toString() : String = {"EPTwoFactor_s_S.SelChallengeAuthenticatedChallengeAccessDenied"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __SelChallengeAuthenticatedChallengeAccessDeniedImp(c,session)}

protected case class __SelChallengeAuthenticatedChallengeAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SelChallengeAuthenticatedChallengeAccessDenied
}
  private var notUsed = true
def !(m : MESSAGES.TwoFactor.ChallengeAuthenticated) : __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp(c,session)}
def sndToc_C(m : MESSAGES.TwoFactor.ChallengeAuthenticated) : __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp(c,session)}

def !(m : MESSAGES.TwoFactor.ChallengeAccessDenied) : __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp(c,session)}
def sndToc_C(m : MESSAGES.TwoFactor.ChallengeAccessDenied) : __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
 __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp(c,session)}

}


  trait SndChallengeAuthenticated extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeSnd
  object SndChallengeAuthenticated extends SndChallengeAuthenticated {
  override protected def __children: List[EPTwoFactor_s_S] = List(End_s_S_TwoFactorChallengeAuthenticated_Challenge)
  override type implT = __SndChallengeAuthenticatedImp
  override type implNextT = __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp
override def toString() : String = {"EPTwoFactor_s_S.SndChallengeAuthenticated"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "ChallengeAuthenticated" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndChallengeAuthenticatedImp(c,session)}

protected case class __SndChallengeAuthenticatedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndChallengeAuthenticated
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.TwoFactor.ChallengeAuthenticated) : __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp(c,session)}
def !(m : MESSAGES.TwoFactor.ChallengeAuthenticated) : __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp(c,session)}
def snd(m : MESSAGES.TwoFactor.ChallengeAuthenticated) : __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp(c,session)}

}


protected  trait End_s_S_TwoFactorChallengeAuthenticated_Challenge extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_TwoFactorChallengeAuthenticated_Challenge extends End_s_S_TwoFactorChallengeAuthenticated_Challenge {
  override protected def __children: List[EPTwoFactor_s_S] = List()
  override type implT = __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_s_S.End_s_S_TwoFactorChallengeAuthenticated_Challenge"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp(c,session)}

protected case class __End_s_S_TwoFactorChallengeAuthenticated_ChallengeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_TwoFactorChallengeAuthenticated_Challenge
}
  
}



  trait SndChallengeAccessDenied extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeSnd
  object SndChallengeAccessDenied extends SndChallengeAccessDenied {
  override protected def __children: List[EPTwoFactor_s_S] = List(End_s_S_TwoFactorChallengeAccessDenied_Challenge)
  override type implT = __SndChallengeAccessDeniedImp
  override type implNextT = __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp
override def toString() : String = {"EPTwoFactor_s_S.SndChallengeAccessDenied"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "ChallengeAccessDenied" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndChallengeAccessDeniedImp(c,session)}

protected case class __SndChallengeAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndChallengeAccessDenied
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.TwoFactor.ChallengeAccessDenied) : __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp(c,session)}
def !(m : MESSAGES.TwoFactor.ChallengeAccessDenied) : __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp(c,session)}
def snd(m : MESSAGES.TwoFactor.ChallengeAccessDenied) : __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp(c,session)}

}


protected  trait End_s_S_TwoFactorChallengeAccessDenied_Challenge extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_TwoFactorChallengeAccessDenied_Challenge extends End_s_S_TwoFactorChallengeAccessDenied_Challenge {
  override protected def __children: List[EPTwoFactor_s_S] = List()
  override type implT = __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_s_S.End_s_S_TwoFactorChallengeAccessDenied_Challenge"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp(c,session)}

protected case class __End_s_S_TwoFactorChallengeAccessDenied_ChallengeImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_TwoFactorChallengeAccessDenied_Challenge
}
  
}



  trait SndAccessDenied extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeSnd
  object SndAccessDenied extends SndAccessDenied {
  override protected def __children: List[EPTwoFactor_s_S] = List(End_s_S_TwoFactorAccessDenied)
  override type implT = __SndAccessDeniedImp
  override type implNextT = __End_s_S_TwoFactorAccessDeniedImp
override def toString() : String = {"EPTwoFactor_s_S.SndAccessDenied"}
    override def to : RRole = Role("c",RoleSet("C")) 
   override def l : String = "AccessDenied" 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __SndAccessDeniedImp(c,session)}

protected case class __SndAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    SndAccessDenied
}
  private var notUsed = true
def sndToc_C(m : MESSAGES.TwoFactor.AccessDenied) : __End_s_S_TwoFactorAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorAccessDeniedImp(c,session)}
def !(m : MESSAGES.TwoFactor.AccessDenied) : __End_s_S_TwoFactorAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorAccessDeniedImp(c,session)}
def snd(m : MESSAGES.TwoFactor.AccessDenied) : __End_s_S_TwoFactorAccessDeniedImp = {
assert(notUsed, s"The channel send musted be used linear")
 notUsed=false
c.snd(Role("c",RoleSet("C")),m)
__End_s_S_TwoFactorAccessDeniedImp(c,session)}

}


protected  trait End_s_S_TwoFactorAccessDenied extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_TwoFactorAccessDenied extends End_s_S_TwoFactorAccessDenied {
  override protected def __children: List[EPTwoFactor_s_S] = List()
  override type implT = __End_s_S_TwoFactorAccessDeniedImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_s_S.End_s_S_TwoFactorAccessDenied"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_TwoFactorAccessDeniedImp(c,session)}

protected case class __End_s_S_TwoFactorAccessDeniedImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_TwoFactorAccessDenied
}
  
}



  trait Failed_c_C extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeFDtct
  object Failed_c_C extends Failed_c_C {
  override protected def __children: List[EPTwoFactor_s_S] = List(End_s_S_TwoFactorFHandling)
  override type implT = __Failed_c_CImp
  override type implNextT = __End_s_S_TwoFactorFHandlingImp
override def toString() : String = {"EPTwoFactor_s_S.Failed_c_C"}
  override def suspect : Role = Role("c",RoleSet("C")) 

  override protected def __create(c : AbstractChannel, session : Session) : implT = __Failed_c_CImp(c,session)}

protected case class __Failed_c_CImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    Failed_c_C
}
  def failed_c_C(): __End_s_S_TwoFactorFHandlingImp = {__End_s_S_TwoFactorFHandlingImp(c,session)}

}


protected  trait End_s_S_TwoFactorFHandling extends EPTwoFactor_s_S with event_lang.dsl.ChannelTypeEnd
protected  object End_s_S_TwoFactorFHandling extends End_s_S_TwoFactorFHandling {
  override protected def __children: List[EPTwoFactor_s_S] = List()
  override type implT = __End_s_S_TwoFactorFHandlingImp
  override type implNextT = Nothing
override def toString() : String = {"EPTwoFactor_s_S.End_s_S_TwoFactorFHandling"}
  
  override protected def __create(c : AbstractChannel, session : Session) : implT = __End_s_S_TwoFactorFHandlingImp(c,session)}

protected case class __End_s_S_TwoFactorFHandlingImp(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {
  override def from : AbstractChannelType = {
    End_s_S_TwoFactorFHandling
}
  
}


}

}

}
