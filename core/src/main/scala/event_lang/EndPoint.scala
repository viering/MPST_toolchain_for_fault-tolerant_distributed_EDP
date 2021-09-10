package event_lang

import event_lang.dsl.{AbstractChannelType, AbstractEndPoint, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.network.SpawnMain
import event_lang.network.netty.internal.{CreateID, NettyConnectionBootstrapEP, NettyConnectionManger}
import event_lang.network.netty.internal.SetupMessages.SignalSpawnMain
import event_lang.network.netty.{EPAddr, NettySessionChannelEP}
import event_lang.semantic.CommonTypes.PartID
import event_lang.semantic.{CoverageTest, HandlerManager, OperationalSemantic, Participant}
import event_lang.intern.logging.Logger
import event_lang.types._


object EndPoint extends Logger {

  case class SpawnMainSelection(robustP: Participant, picked: Participant)

  def createLocalOnlyEP[LT <: AbstractChannelType, ST <: TState, MT <: MSG](
                                                                             ep: AbstractEndPoint[LT, ST],
                                                                             network: LHostSessionChannelEPs,
                                                                             parId : Int,
                                                                              mainPName : String = "Main"
                                                                             )
  : OperationalSemantic[LT, ST] = {
    val subS = ep.subs
    new CoverageTest[LT, ST](new HandlerManager(ep), ep.subs).check()
    val role = ep.roleSet
    val networkApi = network.getLayer(parId)

    val mainSessions = subS.filter(_.name.contains(mainPName))
    val spawnMain = network.mainSes

    val myMSession = mainSessions.filter(s => {
      s.prjTo == spawnMain.idToRole.getOrElse(parId, role)
    })
    if(myMSession.isEmpty)
      println()
    val endpoint = new OperationalSemantic(ep, networkApi, parId, role, myMSession.head,
                                           spawnMain)
    endpoint

  }

  /**
    * Blocking EndPoint creation
    *
    * @param ep
    * @param connectTo
    * @tparam LT
    * @tparam ST
    */
  def createEPusingNetty[LT <: AbstractChannelType, ST <: TState, MT <: MSG](
                                                                              ep: AbstractEndPoint[LT, ST],
                                                                              connectTo: EPAddr,
                                                                              myIP : String,
                                                                              myCID : Option[String]= None)
  : OperationalSemantic[LT, ST] = {
    val subS = ep.subs
    new CoverageTest[LT, ST](new HandlerManager(ep), ep.subs).check()
    val role = ep.roleSet
    val networkApi = new NettySessionChannelEP()
    val client = new NettyConnectionManger(role, networkApi,myIP,myCID)
    val hdl = client.start(connectTo)
    while (!hdl.ready) {}
    trace(s"EP[$this] of $role connected with ${hdl.mainSessionInfo}")

    val mainSessions = subS.filter(_.name.contains("Main"))
    val spawnMain = client.spawnMain
    val mId = client.id
    val myMSession = mainSessions.filter(s => {
      s.prjTo == spawnMain.idToRole.getOrElse(mId, role)
    })
    assert(myMSession.size == 1)
    trace(s"EP[$this] spawns ${myMSession.head.name}:${myMSession.head.prjTo} in $spawnMain")
    val endpoint = new OperationalSemantic(ep, networkApi, mId, role, myMSession.head,
                                           spawnMain)
    endpoint
  }


  def createNettyConnectionBootstrapManger(addr: EPAddr, ready: Map[RoleSet, Set[Int]] =>
    Option[SpawnMainSelection]): Unit = {
    val epB = new NettyConnectionBootstrapEP(addr, (it,iToc) => {
      val nMap = collection.mutable.Map[RoleSet, Set[Int]]()
      for ((id, rs) <- it) {
        nMap.update(rs, nMap.getOrElse(rs, Set()) + id)
      }

      val selection = ready(nMap.toMap)
      selection.map(
        s =>
          SignalSpawnMain(s.robustP.id,
                             Map(s.robustP.id -> s.robustP.r, s.picked.id -> s.picked.r), it.toMap,iToc.toMap))
    })
    epB.start()
    debug("Netty boostrap thread finished")
  }

  def createNettyConnectionBootstrapManger(addr: EPAddr, setupData : SpawnMain): Unit = {
    val epB = new NettyConnectionBootstrapEP(addr, (it,itoc) => {
      val idToRS = it.toMap
      if(idToRS.size == setupData.idToRoleSet.size){
        idToRS.foreach( pRS=> {
          assert(setupData.idToRoleSet.contains(pRS._1))
          assert(setupData.idToRoleSet(pRS._1)==pRS._2)
        })
        Some(SignalSpawnMain(setupData.mainID,setupData.idToRole,setupData.idToRoleSet,itoc.toMap))
      }else{
        None
      }},new CreateID {
        val openIds : collection.mutable.Map[RoleSet,List[PartID]] = collection.mutable.Map()
        for(idRS <- setupData.idToRoleSet){
          val ids : List[PartID] = openIds.getOrElse(idRS._2,List[PartID]())
          openIds += ((idRS._2, idRS._1 :: ids))
        }
        override def createID(r: RoleSet): PartID = {
          openIds synchronized {
            val ids = openIds(r)
            openIds += ((r,ids.tail))
            ids.head
          }
        }
    })
    epB.start()
    debug("Netty boostrap thread finished")
  }
}

