package event_lang.network.netty

import java.util.concurrent.ConcurrentLinkedQueue

import event_lang.dsl.ChannelTypeSubS
import event_lang.network.EndPointMessages._
import event_lang.network.netty.internal.{NettyLayerRcvAPi, NettyLayerSndApi}
import event_lang.network.{QueueAPI, SessionChannelEP}
import event_lang.semantic.CommonTypes.{PartID, SessionID}
import event_lang.intern.logging.Logger
import event_lang.types.{MSG, Role, RoleSet}

import scala.collection.concurrent.TrieMap


class NettySessionChannelEP extends NettyLayerRcvAPi[EPtoEPMSG] with SessionChannelEP with Logger {


  type QueueKey = (SessionID, PartID)

  //    case class QueueKey(sessionId: Long, frm: Int)
  class QueueImpl() extends QueueAPI {
    private val inputQueues = collection.concurrent.TrieMap[QueueKey,
      ConcurrentLinkedQueue[SessionMSG]]()

    private val controlQueue = collection.concurrent.TrieMap[SessionID, ConcurrentLinkedQueue[ControlMSG]]()


    private[NettySessionChannelEP] def addMSG(msg: SessionMSG): Unit = {
      val key = (msg.sesID, msg.frm)
      msg match {
        case m: ControlMSG =>
          val qu = controlQueue.getOrElseUpdate(m.sesID, new ConcurrentLinkedQueue[ControlMSG]())
          qu.add(m)
        case _ =>
          val qu = inputQueues.getOrElseUpdate(key, new ConcurrentLinkedQueue[SessionMSG]())
          qu.add(msg)
      }
    }


    override def peek(sesId: SesId, frm: ParId): Option[SessionMSG] = {
      inputQueues.get((sesId, frm)).map(_.peek())
    }

    override def poll(sesId: SesId, frm: ParId): Option[SessionMSG] = {
      inputQueues.get((sesId, frm)).map(_.poll())
    }

    override def peekControlMsg(sesId: SesId): Option[ControlMSG] =
      controlQueue.get(sesId).flatMap(q => Option.apply(q.peek()))

    override def pollControlMsg(sesId: SesId): Option[ControlMSG] = {
      val msg = controlQueue.get(sesId).flatMap(q => Option.apply(q.poll()))
      trace(s"Rcv control: $msg in $sesId")
      msg
    }

    override def exist(sesId: SesId, frm: ParId, filter: MSG => Boolean): Boolean = {
      //TODO duplicated code with the local host impl -- also for the methods above
      inputQueues.get((sesId, frm)) match {
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


  private var nettySnd: NettyLayerSndApi[EPtoEPMSG] = null
  private lazy val myID = nettySnd.myID
  private lazy val mainID = nettySnd.mainSessionInfo.mainID
  private lazy val mainSesInfo = nettySnd.mainSessionInfo

  val queue = new QueueImpl()
  val failed_endpoints: TrieMap[PartID, Object] = collection.concurrent.TrieMap[PartID, Object]()

  /**
   * this method MUST BE THREADSAFE
   */
  override def rcvMSG(m: EPtoEPMSG): Unit = {
    m match {
      case msg: DataMSG =>
        queue.addMSG(msg)
      case msg: SignalSpawnIntend =>
        queue.addMSG(msg)
      case msg: SpawnSession =>
        queue.addMSG(msg)
      case msg: SignalTerminationIntend =>
        queue.addMSG(msg)
      case msg: SignalTermination =>
        queue.addMSG(msg)

      case FailureSuspicionToMain(fID) =>
        assert(nettySnd.myID == nettySnd.mainSessionInfo.mainID)
        trace(s"[$myID] received $m -- will multi send")
        failed_endpoints.put(fID, new Object)
        nettySnd.broadcast(FailureNotification(fID))

      case msg@FailureNotification(fID) =>
        trace(s"[$myID] received $msg")
        failed_endpoints.put(fID, new Object)
    }
  }

  override def snd(toId: Int, msg: EPtoEPMSG): Unit = {
    trace(s"[snd]@$myID to $toId $msg")
    if (toId == myID) {
      rcvMSG(msg)
    } else {
      nettySnd.snd(toId, msg)
    }
  }

  override def spawn_Session(subSMainID: Int, sesID: Long, name: String,
                             maybeS: ChannelTypeSubS, roles: List[Role], pick: RoleSet,
                             roleSets: List[RoleSet]): Unit = {
    trace(s"[spawn_Session]@$myID ${SignalSpawnIntend(sesID, name, myID)}")
    snd(subSMainID, SignalSpawnIntend(sesID, name, myID))
  }


  override def input_queue: QueueAPI = queue

  override def init(sndLayer: NettyLayerSndApi[EPtoEPMSG]): Unit = nettySnd = sndLayer


  private var __failed_id_cache = Set[Int]()

  override def failed_Ids: Set[Int] = {
    if (__failed_id_cache.size != failed_endpoints.size)
      __failed_id_cache = failed_endpoints.keySet.toSet
    __failed_id_cache
  }

  override def signalFail(id: Int): Unit = {
    trace(s"[$myID] Signal failure of $id to root part: $mainID")
    snd(mainID, FailureSuspicionToMain(id))
  }


  override def shutdown(): Unit = nettySnd.shutdown()
}
