package event_lang.types

import event_lang.types._

object LocalTypes {
  trait LocalType


  case class AllEndPointTypes(protocolName: String, ep: Map[RoleSet, LTop])

  case class LTop(subSession: Set[LSubSession]) extends LocalType


  case class LSubSession(name: String,
                         argsC: List[Role],
                         argsP: Role,
                         argsRs: List[RoleSet],
                         body: LocalType,
                         prjTo: RRole,
                         rootRole: Role
                        ) extends LocalType {
  }


  case class LSnd(to: RRole, con: Map[Label, LocalType]) extends LocalType

  case class LRcv(frm: Role, con: Map[Label, LocalType]) extends LocalType

  case class LMerge(options: List[LocalType]) extends LocalType

  case class LFDetection(p: Role, con: LocalType) extends LocalType

  case class LSpawn(c: String, y: List[Role], pickR: RoleSet, rs: List[RoleSet], con: LocalType) extends LocalType

  case class LHdl(dBlock: LocalType, fBlock: LocalType) extends LocalType

  case class LActvHdl(fBlock: LocalType) extends LocalType

  case class LRec(t: LRecT, l: LocalType) extends LocalType

  case class LRecT(symbol: String) extends LocalType

  case class LEnd() extends LocalType

  trait GuardType

  case class PY()

  case class GSnd(to: PY, con: GuardType) extends GuardType

  case class GRcv(from: PY, msg: MSG, con: GuardType) extends GuardType

  case class GFDetect(of: PY, con: GuardType) extends GuardType

  case class GSpawn(c: SesDef, y: List[Participant], pickR: Participant, rs: List[Participant],
                    con: GuardType) extends GuardType

  case class GEnd() extends GuardType

}
