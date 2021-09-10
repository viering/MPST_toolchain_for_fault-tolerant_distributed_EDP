package event_lang.types

object GlobalTypes {

  trait GType {
    def allRoles: Set[Role]

    def allRoleSets: Set[RoleSet]

    def rootRole: Option[Role]

    def labels: Set[Label]

    def children : Set[GType]
  }


  case class SubSession(name: String,
                        argsC: List[Role],
                        argsP: Role,
                        argsRs: List[RoleSet],
                        gType: GType
                       ) extends GType {
    override def allRoles: Set[Role] = {
      val res = (argsP :: argsC).toSet
      assert(res == gType.allRoles, s"In $name roles in args ($res) must match used roles ${gType.allRoles} (prot : $name)")
      res
    }

    override def allRoleSets: Set[RoleSet] = {
      assert(argsRs.toSet.subsetOf(gType.allRoleSets), s"${argsRs.toSet}  -- ${gType.allRoleSets} (in $name)")
      argsRs.toSet
    }

    override def rootRole: Option[Role] = gType.rootRole

    override def labels: Set[Label] = gType.labels

    override def children: Set[GType] = Set(gType)
  }

  case class GTop(subS: Set[SubSession]) extends GType {
    override def allRoles: Set[Role] = subS.flatMap(_.allRoles)

    override def allRoleSets: Set[RoleSet] = subS.flatMap(_.allRoleSets)

    override def rootRole: Option[Role] = None

    override def labels: Set[Label] = subS.foldLeft(Set[Label]())((acc, g) => acc ++ g.labels)
    override def children: Set[GType] = subS.toSet
  }

  case class Interaction(frm: Role, to: RRole, con: Map[Label, GType]) extends GType {
    override def allRoles: Set[Role] = to match {
      case t: Role => Set(frm, t) ++ con.flatMap(_._2.allRoles)
      case _ => Set(frm) ++ con.flatMap(_._2.allRoles)
    }

    override def allRoleSets: Set[RoleSet] = to match {
      case t: RoleSet => Set(t) ++ con.flatMap(_._2.allRoleSets)
      case _ => con.flatMap(_._2.allRoleSets).toSet
    }

    override def labels: Set[Label] = con.keySet ++ con.values.foldLeft(Set[Label]())((acc : Set[Label],g2 : GType) => acc ++ g2.labels)

    override def rootRole: Option[Role] = None

    override def children: Set[GType] = con.values.toSet

  }

  case class Spawn(name: String, y: List[Role], pickR: RoleSet, rs: List[RoleSet], con: GType) extends GType {
    override def allRoles: Set[Role] = y.toSet

    override def allRoleSets: Set[RoleSet] = Set(pickR) ++ rs.toSet ++ con.allRoleSets

    override def rootRole: Option[Role] = None

    override def labels: Set[Label] = con.labels

    override def children: Set[GType] = Set(con)
  }

  case class FDetection(fP: Role, monitorP: Role, con: GType) extends GType {
    override def allRoles: Set[Role] = Set(fP, monitorP) ++ con.allRoles

    override def allRoleSets: Set[RoleSet] = con.allRoleSets

    override def rootRole: Option[Role] = Some(monitorP)

    override def labels: Set[Label] = con.labels

    override def children: Set[GType] = Set(con)
  }

  case class Hdl(dBlock: GType, fBlock: GType) extends GType {
    override def allRoles: Set[Role] = dBlock.allRoles ++ fBlock.allRoles

    override def allRoleSets: Set[RoleSet] = dBlock.allRoleSets ++ fBlock.allRoleSets

    override def rootRole: Option[Role] = fBlock.rootRole

    override def labels: Set[Label] = dBlock.labels ++ fBlock.labels

    override def children: Set[GType] = Set(dBlock,fBlock)
  }

  case class Rec(t: RecT, l: GType) extends GType {
    override def allRoles: Set[Role] = l.allRoles

    override def allRoleSets: Set[RoleSet] = l.allRoleSets

    override def rootRole: Option[Role] = None

    override def labels: Set[Label] = l.labels

    override def children: Set[GType] = Set(t,l)
  }

  case class RecT(symbol: String) extends GType {
    override def allRoles: Set[Role] = Set()

    override def allRoleSets: Set[RoleSet] = Set()

    override def rootRole: Option[Role] = None
    override def labels: Set[Label] = Set()
    override def children: Set[GType] =Set()
  }

  case class End() extends GType {
    override def labels: Set[Label] = Set()
    override def allRoles: Set[Role] = Set()

    override def allRoleSets: Set[RoleSet] = Set()

    override def rootRole: Option[Role] = None
    override def children: Set[GType] = Set()
  }


}
