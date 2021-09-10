package event_lang.network

import event_lang.dsl.ChannelTypeSubS
import event_lang.network.EndPointMessages.EPtoEPMSG
import event_lang.types.{Role, RoleSet}

trait SessionChannelEP {


  def snd(toId: Int, msg: EPtoEPMSG)

  def spawn_Session(spawnControllerID: Int, sesID: Long, name: String, maybeS: ChannelTypeSubS,
                    roles: List[Role],
                    pick: RoleSet, roleSets: List[RoleSet])

  def failed_Ids: Set[Int]

  def signalFail(id: Int): Unit

  def input_queue: QueueAPI

  def shutdown(): Unit
}
