package event_lang.network.LHChannel

import event_lang.dsl.ChannelTypeSubS
import event_lang.network.EndPointMessages.{ControlMSG, DataMSG, EPtoEPMSG, GlobalMSG, SessionMSG, SignalSpawnIntend}
import event_lang.network.{QueueAPI, SessionChannelEP, SpawnMain}
import event_lang.semantic.CommonTypes.{PartID, SessionID}
import event_lang.intern.logging.Logger
import event_lang.types.{MSG, Role, RoleSet}


class LHostSessionChannelEPs(val numIds: Int, val mainSes: SpawnMain) {

  type SesId = SessionID
  type ParId = PartID

  val failures = collection.concurrent.TrieMap[ParId, Object]()
  val ep_ses_channels: Array[DummySessionChannelEP] = (for (j <- 0 until numIds) yield new DummySessionChannelEP(j)).toArray


  def getLayer(id: ParId) = {
    val l = ep_ses_channels(id)
    assert(id == l.myId)
    l
  }

  class DummySessionChannelEP(val myId: ParId) extends SessionChannelEP with Logger {
    // (SessionID, Sender)
    private val controlMsgQueue = collection.concurrent.TrieMap[SesId, java.util.concurrent
    .ConcurrentLinkedQueue[ControlMSG]]()
    private val msgQueue = collection.concurrent.TrieMap[(SesId, ParId), java.util.concurrent
    .ConcurrentLinkedQueue[SessionMSG]]()
    private val msgKeyQueue = new java.util.concurrent.LinkedBlockingQueue[(SesId, ParId)]()


    private def __snd(toId: ParId, msg: SessionMSG): Unit = {
      val l = getLayer(toId)
      msg match {
        case m: ControlMSG =>
          val q = l.controlMsgQueue.getOrElseUpdate(msg.sesID,
            new java.util.concurrent
            .ConcurrentLinkedQueue[ControlMSG]())
          q.add(m)
        case _ =>
          val q = l.msgQueue.getOrElseUpdate((msg.sesID, myId),
            new java.util.concurrent
            .ConcurrentLinkedQueue[SessionMSG]())
          q.add(msg)
      }
      l.msgKeyQueue.add((msg.sesID, msg.frm))
    }

    val nFailureQueue = new java.util.concurrent.ConcurrentLinkedQueue[Int]()

    override def snd(toId: Int, msg: EPtoEPMSG): Unit = {
      msg match {
        case m: SessionMSG =>
          trace(s"Snd: $myId->$toId: $msg")
          __snd(toId, m)

        case m: GlobalMSG => ???
      }
      //queue( (toId,myId)).add(msg)
    }

    override def spawn_Session(spawnControllerID: Int, sesID: Long, name: String,
                               maybeS: ChannelTypeSubS, roles: List[Role], pick: RoleSet,
                               roleSets: List[RoleSet]): Unit = {
      trace(s"SignalSpawnIntend: $myId->$spawnControllerID: ${SignalSpawnIntend(sesID, name, myId)}")
      __snd(spawnControllerID, SignalSpawnIntend(sesID, name, myId))
    }

    override def failed_Ids: Set[Int] = failures.keySet.toSet


    override def signalFail(id: ParId): Unit = {
      debug(s"Signal failure id: $id")
      if (!failures.contains(id)) {
        failures.update(id, new Object)
        for (l <- ep_ses_channels)
          l.nFailureQueue.add(id)
      }
    }

    override def input_queue: QueueAPI = new QueueAPI {

      override def toString: String = {
        import scala.collection.JavaConverters._ //CollectionConverters._
        s"Control msgs\n\t"+ controlMsgQueue.map(sm => s"${sm._1}: ${sm._2.asScala.mkString(",")}")+
          s"\nData msgs:\n\t"+ msgQueue.map(sm => s"${sm._1}: ${sm._2.asScala.mkString(",")}")
      }

      override def peek(sesId: SesId, frm: ParId): Option[SessionMSG] = {
        msgQueue.get((sesId, frm)).map(_.peek())
      }

      override def poll(sesId: SesId, frm: ParId): Option[SessionMSG] = {
        val msg=  msgQueue.get((sesId, frm)).map(_.poll())
        trace(s"Rcv: ?$frm : $msg")
        msg
      }

      override def peekControlMsg(sesId: SesId): Option[ControlMSG] =
        controlMsgQueue.get(sesId).flatMap(q => Option.apply(q.peek()))

      override def pollControlMsg(sesId: SesId): Option[ControlMSG] = {
        val msg = controlMsgQueue.get(sesId).flatMap(q => Option.apply(q.poll()))
        trace(s"Rcv control: $msg in $sesId")
        msg
      }

      //controlMsgQueue.get(sesId).map(_.poll())
      override def exist(sesId: SesId, frm: ParId, filter: MSG => Boolean): Boolean = {
        msgQueue.get((sesId, frm)) match {
          case Some(queue) =>
            val iter = queue.iterator()
            var found = false
            while (!found && iter.hasNext) {
              val el = iter.next()
              val dM = el.asInstanceOf[DataMSG]
              found = filter(dM.payload)
            }
            found
          case _ => false
        }
      }
    }

    override def shutdown(): Unit = {
      info(s"$myId was shutdown")
    }
  }

}