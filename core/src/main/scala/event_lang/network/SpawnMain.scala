package event_lang.network

import event_lang.types._
case class SpawnMain(id: Long, mainID: Int, idToRole : Map[Int,Role], idToRoleSet: Map[Int, RoleSet], itToCId : Map[Int,String]= Map())