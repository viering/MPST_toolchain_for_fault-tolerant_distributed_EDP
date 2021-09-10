package example

import event_lang.dsl.{AbstractChannelType, TState}
import event_lang.semantic.{Config, OperationalSemantic}
import event_lang.types.{Role, RoleSet}

object TestUtil {



  val role_a: Role = Role("a", RoleSet("A"))
  val role_b: Role = Role("b", RoleSet("B"))
  val role_c: Role = Role("c", RoleSet("C"))



  def getIdforRole(r: Role, sesId: Long, eps: Array[OperationalSemantic[AbstractChannelType, TState]],mainId  : Int= 0): Int = {
    val epA = eps.getEP(mainId)
    val cfg = epA.getCfg(sesId)
    val idC = cfg.s.roleToId(r)
    idC
  }

  implicit class OSArray(ar: Array[OperationalSemantic[AbstractChannelType, TState]]) {
    def getEP(id: Int): OperationalSemantic[AbstractChannelType, TState] = {
      assert(ar.exists(_.myID == id),s"$id must be in ${ar.mkString(",")}")
      ar.find(_.myID == id).get
    }
    def idOfRole(r: Role, sesId: Int,mainId  : Int= 0): Int = {
      val epA = ar.getEP(mainId)
      val cfg = epA.getCfg(sesId)
      val idC = cfg.s.roleToId(r)
      idC
    }
  }

  implicit class OSTestMethods(os : OperationalSemantic[AbstractChannelType,TState]){
    /**
     * picks a rnd sub cfg.
     * throws en error if no such cfg exists
     */
    def aSubCfgOf(pCfgId : Long): Config = {
      val pC =getCfg(pCfgId)
      println(pC)
      pC.subSessions.head
    }

    def getCfg(cfgId : Long): Config = {
      assert(os.__debug_cfgs.exists(_.s.id == cfgId),s"$cfgId must be in ${os.__debug_cfgs.mkString(",")}")
     os.__debug_cfgs.find(_.s.id == cfgId).get
    }
  }
}
