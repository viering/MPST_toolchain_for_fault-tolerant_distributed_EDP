package event_lang.types

import event_lang.types.GlobalTypes._
import event_lang.types.LocalTypes._


trait Participant


trait MSG {
  def l: String
}

trait SesDef


trait RRole extends GType {
  def pName: String
}

case class Role(r: String, rs: RoleSet) extends Participant with GType with LocalType with RRole {
  override def pName: String = s"${r}_${rs.rs}"

  override def allRoles: Set[Role] = Set(this)

  override def allRoleSets: Set[RoleSet] = Set()

  override def toString: String = s"Role(${'"'}$r${'"'},RoleSet(${"\"" + rs.rs + "\""}))"

  override def rootRole: Option[Role] = None

  override def labels: Set[Label] = Set()

  override def children: Set[GType] = Set()
}

//case class CRole(p: String, r: String) extends Participant with EndPointTypes.LocalType
case class RoleSet(rs: String) extends Participant with GType with LocalType with RRole {
  override def pName: String = s"${rs}"

  override def allRoles: Set[Role] = Set()

  override def allRoleSets: Set[RoleSet] = Set(this)

  override def toString: String = s"RoleSet(${"\"" + rs + "\""})"

  override def rootRole: Option[Role] = None

  override def labels: Set[Label] = Set()

  override def children: Set[GType] = Set()
}

case class Label(ccName: String, types: List[(String, String)]) extends GType with LocalType with MSG {

  override def allRoles: Set[Role] = Set()

  override def allRoleSets: Set[RoleSet] = Set()

  override def l: String = ccName

  override def rootRole: Option[Role] = None

  override def labels: Set[Label] = Set(this)

  override def children: Set[GType] = Set()
}
