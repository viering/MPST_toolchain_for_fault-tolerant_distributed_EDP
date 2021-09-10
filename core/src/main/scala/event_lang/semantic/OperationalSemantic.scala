package event_lang.semantic

import event_lang._
import event_lang.dsl._
import event_lang.intern.logging.Logger
import event_lang.network.EndPointMessages._
import event_lang.network.{SessionChannelEP, SpawnMain}
import event_lang.types.{MSG, RRole, Role, RoleSet}

import java.util.concurrent.atomic.AtomicBoolean
import scala.collection.mutable
import scala.util.Random
import scala.util.control.ControlThrowable


case class ParticipantSet(ps: Set[Participant], rs: RoleSet)

//case class NMsg(m : MSG, dblock : Boolean)

object ParticipantSet {
  def apply(ps: Set[Participant]): ParticipantSet = {
    val rs = ps.head.r.rs
    ps.foreach(p => assert(p.r.rs == rs))
    ParticipantSet(ps, rs)
  }
}

case class ThetaData(failed: Boolean)

/**
 * NOT THREAD SAFE
 *
 * Eq and hash is ONLY based on session
 *
 *
 * todo make the types immutable and add a point to the current pos in types
 */
class Config(val s: Session, var lt: AbstractChannelType, var ltHdl: AbstractChannelType) {

  def getRootId: Int = s.roleToId(s.rootRole)

  var waitingSpawn: Boolean = false
  var signaledEnd: Boolean = false
  val subSessions: mutable.Set[Config] = mutable.Set()


  var stopped: Boolean = false
  var willBeTerminated = false


  private[semantic] val spawnIntendMap = collection.mutable.Map[String, List[SignalSpawnIntend]]()
  private[semantic] lazy val terminationMSGs = collection.mutable.Stack[SignalTerminationIntend]()


  def hasSubSession: Boolean = subSessions.nonEmpty //|| subSessionsHdl.nonEmpty

  def getLocalType: AbstractChannelType = {
    if (isDefault) {
      lt
    } else {
      assert(isActiveFHandling)
      ltHdl
    }
  }

  def update(frm: AbstractChannelType, to: AbstractChannelType): Config = {
    //assert(frm == lt || frm == ltHdl || )
    assert(!willBeTerminated)
    if (isDefault) {
      lt = to
    } else {
      ltHdl = to
    }
    this
  }

  def isDefault: Boolean = lt != null

  def isActiveFHandling: Boolean = lt == null

  def activateHdl(): Unit = {
    assert(!willBeTerminated)
    assert(lt != null)
    signaledEnd = false
    waitingSpawn = false
    lt = null
    subSessions.foreach(_.stop())
    //killedSessions ++= subSessions
    //subSessions.clear()
    //subSessionsHdl.foreach(_.stop())
  }

  private def stop(): Unit = {
    assert(!stopped)
    stopped = true
    signaledEnd = false
    subSessions.foreach(_.stop())
    //subSessionsHdl.foreach(_.stop())
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Config]

  override def equals(other: Any): Boolean = other match {
    case that: Config =>
      (that canEqual this) &&
        s == that.s
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(s)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  def transSubSessions: Set[Config] = {
    subSessions.foldLeft(Set[Config]())((cs, c) => {
      cs ++ c.transSubSessions
    })
  }

  override def toString = s"Config($s, $lt, $ltHdl)"

  def verboseToString: String = s"Config($s, $lt, $ltHdl,${subSessions.map(_.s.id)}, waitingSpn=$waitingSpawn, sndTerm = $signaledEnd, stopped = $stopped, wllTerm = $willBeTerminated, spwnM = $spawnIntendMap, trmM = $terminationMSGs)"
}

case class Session(id: Long, idToRole: Map[Int, Role], rootRole: Role, roleSets: Set[RoleSet], name: String) {
  private lazy val __roleToID = {
    val res = idToRole.map(kv => (kv._2, kv._1)).toMap
    assert(res.size == idToRole.size)
    res
  }
  lazy val idRootP: Int = roleToId(rootRole)
  //idToRole.filter(_._2.isInstanceOf[Role]).map(x => (x._2, x._1)).toMap

  def allPs: Set[Int] = idToRole.keySet

  def roleToId: Map[Role, Int] = __roleToID
}

class HandlerManager[GT <: AbstractChannelType, ST <: TState](val ep: AbstractEndPoint[GT, ST]) {

  private val typeToHdl = collection.mutable.Map[AbstractChannelType, List[HDL[ST]]]()

  private val typeToNonBlockingHdl = collection.mutable.Map[AbstractChannelType, List[HDL[ST]]]()

  private def __applicableHandlers(lt: AbstractChannelType): List[HDL[ST]] = {
    typeToHdl.getOrElseUpdate(lt, {
      val tmp = ep.receive

      var acc = List[HDL[ST]]()
      for (i <- hdls.indices) {
        val h = tmp(i)
        assert(tmp.contains(h))
        if (h.definedAt(lt))
          acc = h :: acc
      }
      acc
    })
  }

  val hdls: Seq[HDL[ST]] = ep.receive

  /**
   * hdl t
   *
   * @param lt
   * @param m
   * @param state
   * @return
   */
  def fireableHdls(lt: AbstractChannelType, m: Option[MSG], state: ST, session: dsl.Session): Option[HDL[ST]] = {
    __applicableHandlers(lt).find(_.canExecute(lt, m, state, lt.create(FrozenChannel(m), session)))
  }

  def applicableHandlers(lt: AbstractChannelType): List[HDL[ST]] = {
    __applicableHandlers(lt)
  }

}

case class Config_Handler(mainS: Session, mainSub: ChannelTypeSubS) {
  val mainCfg: Config = newCfg(mainS, mainSub)
  private var configs: Map[Long, Config] = Map(mainS.id -> mainCfg)

  private def newCfg(s: Session, sub: ChannelTypeSubS): Config = {
    val dBlock = sub.body.children.head
    val fBlock = sub.body.children.tail.head
    new Config(s, lt = dBlock, ltHdl = fBlock)
  }

  def dumpConfigs(): String = {
    configs.map(kv => (kv._1, kv._2.verboseToString)).mkString("\n")
  }

  def createNAddSubSession(newSesID: Long, newPicked: Int, curCfg: Config, subS: ChannelTypeSubS, spawn: ChannelTypeSpawn)
  : Config = {


    val pS = curCfg.s
    //val idToR = subS.argsC.map(r => pS.roleToId(r) -> r).toMap
    assert(spawn.y.size == subS.argsC.size)
    val idToR = spawn.y.zip(subS.argsC).map(r => pS.roleToId(r._1) -> r._2).toMap
    val nS = Session(newSesID, idToR + (newPicked -> subS.argsP), subS.rootRole, subS.argsRs.toSet, subS.name)
    val nC = newCfg(nS, subS)
    curCfg.subSessions += nC
    configs = configs + (newSesID -> nC)
    nC
  }

  def removeNonRootCfg(cfg: Config): Unit = {
    val par = getParent(cfg)
    par.subSessions.remove(cfg)
    configs = configs - cfg.s.id
  }

  def getParent(cfg: Config): Config = {
    var hit = false
    val res = configs.values.find(c => {
      for (cc <- c.subSessions) {
        hit = hit || cc == cfg
      }
      c.subSessions.contains(cfg)
    })
    if (res.isEmpty) {
    }

    res.get
  }

  def containsNRunning(sessionID: Long): Boolean = {
    configs.contains(sessionID) && !configs(sessionID).stopped
  }

  def containsNStopped(sessionID: Long): Boolean = {
    configs.contains(sessionID) && configs(sessionID).stopped

  }

  def containsCanBeStopped(sessionID: Long): Boolean = {
    configs.contains(sessionID)
  }

  def get(sessionID: Long): Config = {
    configs(sessionID)
  }

  def apply(sessionID: Long): Config = get(sessionID)

  def allCfgs = configs.values
}

case class ToReadPointer(sessionId: Long, frmId: Int)


class OperationalSemantic[GT <: AbstractChannelType, ST <: TState](
                                                                    val ep: AbstractEndPoint[GT,
                                                                      ST],
                                                                    val network: SessionChannelEP,
                                                                    val myID: Int,
                                                                    val myRole: RoleSet,
                                                                    val mainSub: ChannelTypeSubS,
                                                                    //val
                                                                    // subS: Seq[ChannelTypeSubS],
                                                                    val spawnMain: SpawnMain
                                                                  ) extends Logger {
  self =>
  trace(s"OperationalSemantic created for $myID - $spawnMain")

  var debugID = -1
  val subS: Seq[ChannelTypeSubS] = ep.subs
  private val handler_manager = new HandlerManager(ep)
  private val in_queue = network.input_queue
  private val maxNumParticipans = spawnMain.idToRoleSet.size
  private var __roleSetToId: collection.mutable.Map[RoleSet, Set[Int]] = null
  private var poisenPill = false
  private var known_failures: Set[Int] = Set()

  private var state: ST = ep.onStartUp
  private val mainS = Session(spawnMain.id, spawnMain.idToRole, mainSub.rootRole,
    mainSub.argsRs.toSet, "_MAIN_")
  private val config = Config_Handler(mainS, mainSub)

  def __debug_cfgs: Iterable[Config] = config.allCfgs

  new CoverageTest[GT, ST](handler_manager, subS).check()


  /*
  A session id is generated by the following scheme:

    myID    fill up to reach max Long
      |       |
    XXXX 0000000000000  + nextSubID
    |XXXX| = digs needed to represent maxParticipants
   */
  private var __nextID: Long = 1

  def getNexSessionID: Long = {
    val numX: Int = (Math.floor(Math.log10(maxNumParticipans) / Math.log10(2.0)) + 1).toInt
    //val nextSesID = (myID.toLong << (63 - numX)) + __nextID
    val nextSesID = ((myID.toLong * Math.pow(10, 15 - numX)) + __nextID).toLong
    __nextID += 1
    nextSesID
  }

  /**
   * All non suspected ids of a role set
   *
   * @param rs
   * @return
   */
  def roleSetToId(rs: RoleSet): Set[Int] = {
    if (__roleSetToId == null || recomputeRoleSetIds) {
      recomputeRoleSetIds = false
      __roleSetToId = collection.mutable.Map()
      val fID = known_failures //network.failed_Ids
      for ((id, rs) <- spawnMain.idToRoleSet) {
        if (!fID.contains(id)) {
          __roleSetToId.update(rs, __roleSetToId.getOrElse(rs, Set[Int]()) + id)
        }
      }
    }
    __roleSetToId.getOrElse(rs, Set.empty[Int])
  }


  def forceStop(): Unit = {
    poisenPill = true
  }

  case class HdlChannel(c: Config, msg: Option[MSG]) extends AbstractChannel with Logger {
    override def snd(to: RRole, m: MSG): Unit = {
      to match {
        case r: Role =>
          val to = c.s.roleToId(r)
          network.snd(to, DataMSG(myID, to, c.s.id, m))
        //network.snd(c.s, self, c.s.ps(r), m)
        case rs: RoleSet =>
          val to = roleSetToId(rs)
          for (i <- to)
            network.snd(i, DataMSG(myID, i, c.s.id, m))
        //dummyNetwork.mSnd(c.s, self, r, m)
      }
    }

    override def rcv(frm: Role): MSG = {
      msg.get
    }
  }


  def getAllIdsOfASession(s: Session): Set[Int] = {
    s.roleSets.flatMap(roleSetToId) ++ s.allPs.filter(!known_failures.contains(_)) //network.failed_Ids.contains(_))
  }

  private case class DSLSession(sesId: Long, myId: Int, subSessions: Set[Long],
                                newSubId: Option[Long], roleToPId: Map[Role, Int],
                                setupInfo: SpawnMain, failedIDs: Set[Int]) extends dsl.Session {
  }

  def dslSession(cfg: Config, newSubId: Option[Long] = None): dsl.Session = {
    val subs = cfg.subSessions.map(_.s.id).toSet

    DSLSession(cfg.s.id, myID, subs, newSubId, cfg.s.roleToId, spawnMain, network.failed_Ids)

  }

  def fireActivateHDL(lt: AbstractChannelType, cfg: Config, hdl: HDL[ST], msg: Option[MSG])
  : Config = {
    assert(!cfg.stopped)
    assert(cfg.isDefault)
    val cfgL = cfg.ltHdl
    assert(lt == cfgL || extractLtFromSelBraMerge(cfgL).contains(lt))

    trace(s"[$myID][${cfg.s.id}][dID:$debugID] Activate failure handling in ($cfg,$state) by $hdl with $msg")
    val channel = if (lt == hdl.inCStart) {
      lt.create(HdlChannel(cfg, msg), dslSession(cfg))
    } else {
      assert(lt.children.head == hdl.inCStart,
        s"${lt.children.head} should match ${hdl.inCStart}")
      lt.children.head.create(HdlChannel(cfg, msg), dslSession(cfg))
    }
    val change = hdl.execute(msg, state, channel)
    trace(s"[$myID][dID:$debugID] Hdl result: $change")
    self.state = change._1
    trace(s"[$myID][${cfg.s.id}][dID:$debugID] Stopped the following subsessions: ${cfg.transSubSessions.mkString(";")}")

    cfg.activateHdl()
    cfg.update(lt, change._2.from)
    cfg
  }

  def fire(lt: AbstractChannelType, cfg: Config, hdl: HDL[ST], msg: Option[MSG], newSubId: Option[Long] = None): Config = {
    assert(!cfg.stopped)
    assert(lt != null)
    assert(cfg != null)
    assert(hdl != null)
    assert(msg != null)
    assert(newSubId != null)
    //TODO add a assert that checks lt matches
    assert(lt == cfg.getLocalType || extractLtFromSelBraMerge(cfg.getLocalType).contains(lt))
    trace(s"[$myID][${cfg.s.id}][dID:$debugID] Before Fire: In ($cfg,$state) activate $hdl with $msg")
    val channel = if (lt == hdl.inCStart) {
      lt.create(HdlChannel(cfg, msg), dslSession(cfg, newSubId))
    } else {
      assert(lt.children.head == hdl.inCStart,
        s"${lt.children.head} should match ${hdl.inCStart}")
      lt.children.head.create(HdlChannel(cfg, msg), dslSession(cfg, newSubId))
    }
    val change = hdl.execute(msg, state, channel)
    trace(s"[$myID][dID:$debugID] After Fire: $change")
    self.state = change._1
    cfg.update(lt, change._2.from)
  }


  private var recomputeRoleSetIds: Boolean =
    false

  def checkNIssueTerminationMSGS(cfg: Config) = {
    val msgs = cfg.terminationMSGs.filter(_.defaultActivity == cfg.isDefault)
    val allID = getAllIdsOfASession(cfg.s)


    if (msgs.size >= allID.size && allID.forall(tm => msgs.exists(_.frm == tm))) {
      //we have enough to terminate

      for (id <- allID) {
        val m = SignalTermination(cfg.s.id, myID, cfg.isDefault)
        trace(s"[$myID:$myRole][dID:$debugID] Signaled terminated for sesId:${cfg.s.id} name:${cfg.s.name} -- $cfg")
        trace(s"[$myID:$myRole][dID:$debugID] SignalTermination msg is: $m")
        network.snd(id, m)
      }
      cfg.terminationMSGs.clear()
      cfg.willBeTerminated = true
    }
  }

  //TODO should we cache that?
  //only used in assertions
  private def extractLtFromSelBraMerge(lt: AbstractChannelType): Seq[AbstractChannelType]
  = {
    def isSelBranMerge(lt: AbstractChannelType): Boolean = {
      lt match {
        case l: ChannelTypeSel => true
        case l: ChannelTypeBrn => true
        case l: ChannelTypeMerge => true
        case _ => false
      }
    }

    val todo = mutable.Stack(lt)
    var res = List[AbstractChannelType]()
    while (todo.nonEmpty) {
      val cur = todo.pop()
      for (c <- cur.children) {
        if (isSelBranMerge(c)) {
          todo.push(c)
        } else {
          res = c :: res
        }
      }
    }
    res
  }

  implicit class ItrUntil[T](itr: Iterable[T]) {
    def itrWith[B](f: PartialFunction[T, Option[B]]): Option[B] = {
      val iterator = itr.iterator
      var done = false
      var res: Option[B] = None
      while (!done && iterator.hasNext) {
        val el = iterator.next()
        res = f(el)
        done = res.isDefined
      }
      res
    }
  }

  def getTypeSpawn(lt: AbstractChannelType, name: String)
  : Option[ChannelTypeSpawn] = {
    lt match {
      case l: ChannelTypeSpawn if l.name == name => Some(l)
      case _ =>
        var itr = lt.children.iterator
        lt.children.itrWith {
          case x => getTypeSpawn(x, name)
        }
    }
  }


  class EndEventLoopControlException(val err: Boolean = false) extends ControlThrowable
  private val isDone = new AtomicBoolean(false)
  def start(): Unit = {
    var numFailures = 0
    var debugCnt = 0

    def curStateLog(): String = {
      val str = new StringBuilder()
      str.append(s"#### Periodic state log dump #### \nfor $myID:$myRole\n")
      str.append(s"SpawnMain: $spawnMain\n")
      str.append(s"Failure: ${known_failures}\n")
      str.append(s"DebugId: $debugID\n")
      str.append(s"CConfigurations \n")
      str.append(this.config.allCfgs.map(_.verboseToString).mkString("\n"))
      str.append(s"\nQueues: \n")
      str.append(this.in_queue)
      str.append(s"\nApplication State:\n$state")
      str.toString()
    }

    var periodicLogCounter = 0L

    val watchDog = new Thread(new Runnable {
      override def run(): Unit = {
        var last_seen_id = -1L
        while (!isDone.get()) {
          if (last_seen_id == -1) {
            last_seen_id = periodicLogCounter
          } else {
            info(s"\n\n\n[$myID] Periodical state dump ($periodicLogCounter) :\n${curStateLog()}\n\n\n")
            last_seen_id = periodicLogCounter
          }
          Thread.sleep(5000)
        }
      }
    })
    watchDog.start()
    try {

      var noChangeCount = 0


      while (true) {
        if (Thread.interrupted())
          throw new RuntimeException {

          }
        if (poisenPill) {
          debug(
            s"#####################\n#### [$myID][dID:$debugID] Poisen Pill -- Will SHUTDOWN " +
              s"###\n#####################")
          throw new EndEventLoopControlException(true)
        }
        if (network.failed_Ids.contains(myID)) {
          Console.err.println(
            s"#####################\n#### [$myID][dID:$debugID] I am suspected !! -- Will SHUTDOWN " +
              s"###\n#####################")
          throw new EndEventLoopControlException(true)
        }
        periodicLogCounter += 1
        if (periodicLogCounter % 10000 == 1) {
          // trace(curStateLog())
          trace(s"[$myID:$myRole] Is running (periodicLogCounter = $periodicLogCounter)")
        }

        if (network.failed_Ids.size > numFailures) {
          recomputeRoleSetIds = true
          numFailures = network.failed_Ids.size
          known_failures ++= network.failed_Ids
          trace(s"[$myID][dID:$debugID] observed new failure (old set: ${__roleSetToId} new set: ${network.failed_Ids})")
        }


        def toMSG(m: Option[SessionMSG]): Option[MSG] = m match {
          case Some(DataMSG(_, _, _, msg: MSG)) => Some(msg)
          case _ => None
        }

        debugCnt += 1
        if (debugCnt > 10000) {
          debugCnt = 0
        }


        /**
         * Fires
         */
        for (cfg <- config.allCfgs if !cfg.stopped) {
          val session = cfg.s
//          if (myID > 2 && cfg.s.id == 0) {
//            print("")
//          }


          /**
           * do cleaning
           */
          if (cfg.isActiveFHandling) { //} && !lbl_for_clean(cfg.localType).contains(msg.l)) {
            for (p: Int <- session.roleToId.values) {
              in_queue.peek(session.id, p) match {
                case Some(DataMSG(frm, to, id, data: MSG)) =>
                  if (!lbl_for_clean(cfg.getLocalType).contains(data.l)) {
                    warn(s"[$myID][dID:$debugID] Cleaned $data")
                    in_queue.poll(session.id, p)
                  }
                case _ =>
              }
            }
          } else {
            for (p: Int <- session.roleToId.values) {
              in_queue.peek(session.id, p) match {
                case Some(DataMSG(frm, to, id, data: MSG)) =>
                  if (lbl_for_clean(cfg.lt).contains(data.l)) {
                    in_queue.exist(session.id, p, msg => {
                      if (lbl_for_clean(cfg.ltHdl).contains(msg.l)) {
                        warn(s"[$myID][dID:$debugID] Cleaned $data -- because the queue contains $msg")
                        in_queue.poll(session.id, p)
                        true
                      } else {
                        false
                      }
                    })
                  }
                case _ =>
              }
            }
          }

          /**
           * if we are in default check if we can activate failure handling
           */
          if (cfg.isDefault && !cfg.willBeTerminated) {

            def checkNFireFHanadling(lt: AbstractChannelType, isRec: Boolean = false): Boolean = {
              lt match {
                case c: ChannelTypeFDtct =>
                  val susP = session.roleToId(c.suspect)
                  if (known_failures.contains(susP)) {
                    trace(s"[$myID][dID:$debugID] Failure of $susP activation in $cfg")
                    val hdl = handler_manager.fireableHdls(cfg.ltHdl, None, state, dslSession(cfg)).get
                    fireActivateHDL(cfg.ltHdl, cfg, hdl, None)
                    true
                  } else {
                    false
                  }
                case c: ChannelTypeRcv =>
                  val frm = session.roleToId(c.frm)
                  val msg = toMSG(in_queue.peek(session.id, frm))
                  val hdl = handler_manager.fireableHdls(lt, msg, state, dslSession(cfg))
                  hdl match {
                    case Some(hdl) =>
                      trace(s"[$myID][dID:$debugID] Receive failure notification $msg and activate failure handling for $cfg")
                      fireActivateHDL(lt, cfg, hdl, msg)
                      in_queue.poll(session.id, frm)
                      true
                    case _ =>
                      false
                  }
                case l: ChannelTypeBrn =>
                  val chl = l.children
                  var fired = false
                  for (c <- chl if !fired) {
                    fired = fired || checkNFireFHanadling(c, true)
                  }
                  fired

                case c: ChannelTypeEnd => false
                //the one that fails has an empty handler
              }
            }


            checkNFireFHanadling(cfg.ltHdl)
          }

          /**
           * unfold
           */
          def unfold(): Unit = {
            val lt = cfg.getLocalType
            lt match {
              case l: ChannelTypeRec =>
                cfg.update(cfg.getLocalType, l.children.head)
                unfold()
              case l: ChannelTypeT =>
                cfg.update(cfg.getLocalType, l.children.head)
                unfold()
              case _ =>
            }
          }

          unfold()

          val lt = cfg.getLocalType

          /**
           * Handle simple handlers like snd, receive
           */
          def fireHdl(lt: AbstractChannelType, isRec: Boolean): Boolean =
            lt match {
              case c: ChannelTypeRcv =>
                val frm = session.roleToId(c.frm)
                val msg = toMSG(in_queue.peek(session.id, frm))
                val hdl = handler_manager.fireableHdls(lt, msg, state, dslSession(cfg))
                hdl match {
                  case Some(h) =>
                    fire(lt, cfg, h, msg)
                    assert(msg == toMSG(in_queue.poll(session.id, frm)))
                    true
                  case _ => false
                }

              case l: ChannelTypeMerge =>
                val chl = l.children
                var fired = false
                for (c <- chl if !fired) {
                  fired = fired || fireHdl(c, true)
                }
                fired
              case l: ChannelTypeBrn =>
                val chl = l.children
                var fired = false
                for (c <- chl if !fired) {
                  fired = fired || fireHdl(c, true)
                }
                fired
              case l: ChannelTypeSel =>
                val chl = l.children
                var fired = false
                for (c <- chl if !fired) {
                  fired = fired || fireHdl(c, true)
                }
                fired

              case l: ChannelTypeSnd =>
                val hdl = handler_manager.fireableHdls(lt, None, state, dslSession(cfg))
                hdl match {
                  case Some(h) =>
                    fire(lt, cfg, h, None)
                    true
                  case _ =>
                    false
                }

              case _ => false
            }

          fireHdl(lt, false)

          def spawn(lt: AbstractChannelType): Unit =
            lt match {
              case l: ChannelTypeSpawn
                if !cfg.waitingSpawn
              =>
                cfg.waitingSpawn = true
                //cfg.update(null, l)
                //we use the first para roll as man in charge for the commit protocol
                val roles = l.y
                val spawnController = cfg.s.roleToId(roles.head)
                val pick = l.pickR
                val roleSets = l.rs
                val lmsg = s"[$myID][${cfg.s.id}][dID:$debugID] Signal Spawn Session ${spawnController}, ${session.id},${l.name},${subS.find(_.name == l.name).get},${roles}, ${pick},${roleSets})"
                trace(lmsg)
                network.spawn_Session(spawnController,
                  session.id,
                  l.name,
                  subS.find(_.name == l.name).get,
                  roles,
                  pick,
                  roleSets)
              // true //hm -- we did not yet fire a handler -- however we send out msg
              case l: ChannelTypeSpawn =>
                val spawnController = cfg.s.roleToId(l.y.head)

                //Check if we can send out spanwn message
                if (myID == spawnController) {
                  val ltSpawn = l
                  val name = l.name

                  val spawns = cfg.spawnIntendMap.getOrElse(name, Nil)

                  val idsOfNamed = ltSpawn.y.map(cfg.s.roleToId(_))
                  val roleSetIds = ltSpawn.rs.flatMap(roleSetToId)
                  val pickIds = roleSetToId(ltSpawn.pickR)

                  val bAllNamed = idsOfNamed.forall(p => spawns.exists(_.frm == p))
                  val bAllPick = pickIds.forall(p => spawns.exists(_.frm == p))
                  val bAllRoleSet = roleSetIds.forall(p => spawns.exists(_.frm == p))

                  if (bAllNamed && bAllPick && bAllRoleSet) {
                    trace(s"[$myID][dID:$debugID] Prep Spawn: roleToID: ${cfg.s.idToRole.mkString(",")}. idOfN: $idsOfNamed, idOfP: $pickIds and idOfRS: $roleSetIds")

                    //                    val pID= pickIds.find(id => !idsOfNamed.contains(id))
                    //inefficant way for a more randome picking in spawn. The one above is fine but a bit deterministic
                    //                    val pickablePIDs= pickIds.filter(id => !idsOfNamed.contains(id)).toSeq
                    val pickablePIDs = pickIds.filter(id => !idsOfNamed.contains(id)).toSeq

                    val pID: Option[Int] = ep.pickHandler.find(_.canExecute(l, state, pickablePIDs, dslSession(cfg))) match {
                      case Some(hdl) =>
                        val res = hdl.execute(l, state, pickablePIDs, dslSession(cfg))
                        state = res._1
                        res._2
                      case _ =>
                        if (pickablePIDs.nonEmpty)
                          Some(pickablePIDs(Random.nextInt(pickablePIDs.size)))
                        else None
                    }
                    //                    assert(pickablePIDs.nonEmpty, s"No participation to pick for ${l.asInstanceOf[ChannelTypeSpawn].pickR} in spawn. FIds: ${known_failures}, PickedIds: ${cfg.s.idToRole}")


                    if (pID.nonEmpty) {
                      val pickedId = pID.get
                      assert(pickablePIDs.contains(pickedId))
                      val newSesId = getNexSessionID

                      val spawnMSg = SpawnSession(cfg.s.id, myID, newSesId, pickedId, name)
                      //                      assert(!cfg.s.idToRole.contains(pickedId),s"Picked id must not be already playing named role: $spawnMSg in ${cfg.s}")
                      cfg.spawnIntendMap.update(name, Nil)
                      trace(s"[$myID][dID:$debugID] Send out $spawnMSg to $idsOfNamed, $pickIds and $roleSetIds")
                      for (id <- idsOfNamed)
                        network.snd(id, spawnMSg)
                      for (id <- pickIds if !idsOfNamed.contains(id))
                        network.snd(id, spawnMSg)
                      for (id <- roleSetIds if !idsOfNamed.contains(id) && !pickIds.contains(id))
                        network.snd(id, spawnMSg)
                    }
                  }
                }
                val msg = in_queue.peek(session.id, spawnController)
                msg match {
                  // a role set participant may recevie a spawn but is not part of the spawn
                  case Some(m@SpawnSession(sesId, frm, newSesId, picked, name)) =>
                    if (myID > 2) {
                      print("")
                    }
                    trace(s"[OS:$myID](1/3) SpawnSession: $m in (new SesId: $newSesId, in SesID: ${cfg.s.id}) cfg: $cfg")
                    in_queue.poll(session.id, spawnController)
                    var ltNew =
                      subS
                        .find(ss => {
                          if (ss.name != name) {
                            false
                          } else if (myID == picked) {
                            assert(!cfg.s.idToRole.contains(myID) || !l.y.contains(cfg.s.idToRole(myID)))
                            ss.argsP == ss.prjTo
                          } else if (cfg.s.idToRole.contains(myID) && l.y.contains(cfg.s.idToRole(myID))) {

                            val argIdx = l.y.indexOf(cfg.s.idToRole(myID))
                            val newRole = ss.argsC(argIdx)
                            ss.prjTo == newRole
                            //cfg.s.idToRole(myID) && l.y.contains(cfg.s.idToRole(myID))
                          } else {
                            ss.prjTo == myRole && l.rs.contains(myRole)
                          }
                        })
                    trace(s"[OS:$myID](2/3) SpawnSession: $m (new SesId: $newSesId, in SesID: ${cfg.s.id})  in cfg: $cfg \nAm i part of the new session: ${ltNew.nonEmpty} ($ltNew)")
                    cfg.waitingSpawn = false
                    if (ltNew.nonEmpty) {

                      val nSub = ltNew.get
                      val nC = config.createNAddSubSession(newSesId, picked, cfg, nSub, l)
                      trace(s"[OS:$myID](3/3) SpawnSession: $m (new SesId: $newSesId, in SesID: ${cfg.s.id}) new cfg:\n$nC \n old cfg\n $cfg")

                      val hdl = handler_manager.fireableHdls(cfg.getLocalType, None, state, dslSession(cfg))
                      //we have a spawn local type, i.e. there can be but must not be a handler
                      //furthermore, the spawn handler is often static, i.e., does not move the local type forward
                      //We may need to move the local type to the continuation of the spawn manually
                      hdl match {
                        case Some(h) =>
                          val cur = cfg.getLocalType
                          fire(cfg.getLocalType, cfg, h, None, Some(newSesId))
                          if (cur == cfg.getLocalType)
                            cfg.update(cfg.getLocalType, cfg.getLocalType.children.head)
                        case _ =>
                          cfg.update(cfg.getLocalType, cfg.getLocalType.children.head)
                      }
                    } else {
                      //TODO add some assertion that verify that myself was a roleset involved in the spawn but myself was not picked and the spawned session does not contain my roleset
                      //TODO i should prob change to only calc tthe stuff on one side an then send out all the infos
                      cfg.update(cfg.getLocalType, cfg.getLocalType.children.head)
                      trace(s"[OS:$myID](3/3) SpawnSession: $m (new SesId: $newSesId, in SesID: ${cfg.s.id}) - I WAS PART OF SPAWN BUT NOT OF NEW SUBSESSION")

                    }
                  //                    true
                  case _ => //false
                }
              case _ =>

            }

          spawn(lt)
        }
        for (cfg <- config.allCfgs) {
          val session = cfg.s
          val lt = cfg.getLocalType

          /**
           * handle the sync rules
           * sync follows the scheme:
           * all ps in session --(i am ready)--> monitor
           * monitor --(perform action)--> all ps in session
           *
           * There is no explicit abort in that protocol (in case of e.g. activation of handling it is just not completed)
           */

          def sessionGC(): Unit = {
            def isDone(cfg: Config): Boolean = {
              cfg.stopped || cfg.getLocalType.isInstanceOf[ChannelTypeEnd]
            }

            if (isDone(cfg)) {
              def terminateSession(): Unit = {
                if (cfg.s == mainS) {
                  debug(s"[$myID][dID:$debugID] Terminated")
                  throw new EndEventLoopControlException
                } else {
                  trace(s"[$myID:$myRole][dID:$debugID] Terminated sesId:${cfg.s.id} name:${cfg.s.name} -- $cfg")
                  val parCfg = config.getParent(cfg)
                  ep.subFinishHandler.find(_.canExecute(cfg.s.name, state, dslSession(parCfg), dslSession(cfg))).foreach(hd => {
                    trace(s"[$myID:$myRole][dID:$debugID] Terminated sesId:${cfg.s.id} name:${cfg.s.name} -- call $hd")
                    val res = hd.execute(null, state, dslSession(parCfg), dslSession(cfg))
                    this.state = res
                  })
                  config.removeNonRootCfg(cfg)
                  trace(s"[$myID:$myRole][dID:$debugID] Terminated sesId:${cfg.s.id} name:${cfg.s.name}\nWe now have configs:\n${config.dumpConfigs()}")
                }
              }

              if (!cfg.signaledEnd && !cfg.hasSubSession) {
                trace(s"[$myID][dID:$debugID] SesId: ${session.id} -- Pid: $myID send out done")
                cfg.signaledEnd = true
                network.snd(cfg.getRootId,
                  SignalTerminationIntend(cfg.s.id, myID,
                    defaultActivity = cfg.isDefault))
              } else {
                if (cfg.terminationMSGs.nonEmpty) {
                  checkNIssueTerminationMSGS(cfg)
                }
                if (cfg.signaledEnd && !cfg.hasSubSession && !cfg.stopped) {
                  val msg = in_queue.peek(session.id, cfg.s.roleToId(cfg.s.rootRole))
                  msg match {
                    case Some(SignalTermination(sesID, frm, isDefault)) =>
                      assert(cfg.subSessions.isEmpty)
                      terminateSession()
                      in_queue.poll(session.id, cfg.s.roleToId(cfg.s.rootRole))
                    case _ =>
                  }
                } else {
                }
              }
              if (cfg.stopped && !cfg.hasSubSession) {
                // async session gc for stopped cfgs -- paper use sync session gc.
                // the formalism uses sync for simplicity and uniform coherence
                // the async impl simplifies the session gc syncronizaiton protocol
                trace(s"[$myID:$myRole][dID:$debugID] Terminated the stopped sesId:${cfg.s.id} name:${cfg.s.name} -- $cfg")
                terminateSession()
              }
            }
          }

          sessionGC()

          /**
           * handle control messages
           */
          val msg = in_queue.peekControlMsg(session.id)
          msg match {
            case None =>
            case Some(m: SignalTerminationIntend) if config.containsCanBeStopped(
              m.sesID) =>
              val cfg = config(m.sesID)
              cfg.terminationMSGs.push(m)
              in_queue.pollControlMsg(session.id)
              trace(s"[$myID][dID:$debugID] Rcv $m (now collected: ${cfg.terminationMSGs}, All Id in Session: ${getAllIdsOfASession(cfg.s)}")
              checkNIssueTerminationMSGS(cfg)
            case Some(m: SignalTerminationIntend) =>
              warn(s"[$myID][dID:$debugID] Clean up $m as ${config.allCfgs.mkString(":")} does not contain the session")
              in_queue.pollControlMsg(session.id)

            case Some(m@SignalSpawnIntend(sesID, name, frm)) if config.containsNRunning(
              sesID) =>
              val cfg = config(sesID)
              val lt = cfg.getLocalType
              in_queue.pollControlMsg(session.id)
              val spawn = getTypeSpawn(lt, name)

              spawn match {
                case None =>
                  //I should save all sub session global
                  warn(s"[dID:$debugID]discarded $msg @ $myID")
                case Some(ltSpawn: ChannelTypeSpawn) =>
                  val spawns = m :: cfg.spawnIntendMap.getOrElse(name, Nil)
                  cfg.spawnIntendMap.update(name, spawns)
                  trace(s"[$myID][dID:$debugID] Rcv $m -- we now have: ${cfg.spawnIntendMap}")
              }
            case Some(m@SignalSpawnIntend(sesID, name, frm)) =>
              val m = in_queue.pollControlMsg(session.id)
              warn(s"[$myID][dID:$debugID] cleand $m -- there was no matching configuration")

          }


        }

      }
    }
    catch {
      case e: EndEventLoopControlException =>
        if (e.err) {
          warn(s"$myID killed himself (was suspected)(debugId: $debugID)")
        } else {
          info(s"$myID was terminated successfully (debugId: $debugID)")
        }
      case e: Exception =>
        error("ERROR STATE DUMP")
        error(curStateLog())
        error(e.getStackTrace.mkString("\n"))
        network.signalFail(myID)
        Thread.sleep(200)//this is a bit of a hack
        throw e
      case a : AssertionError =>
        error("ERROR STATE DUMP")
        error(curStateLog())
        error(a.getStackTrace.mkString("\n"))
        network.signalFail(myID)
        Thread.sleep(200)//this is a bit of a hack)
        throw a
    }
    finally {
      isDone.set(true)
      network.shutdown()
    }
  }

  private val typeToLbl =
    mutable.Map[AbstractChannelType, Set[String]]()

  def lbl_for_clean(lt: AbstractChannelType): Set[String] = {
    typeToLbl.getOrElseUpdate(
      lt, {
        val stack = mutable.Stack(lt)
        val marked = mutable.Set[AbstractChannelType]()
        var lbls = Set[String]()
        while (stack.nonEmpty) {
          val curL = stack.pop()
          if (marked.add(curL)) {
            curL match {
              case c: ChannelTypeRcv =>
                lbls = lbls + c.l
              case _ =>
            }
            curL.children.foreach(stack.push)
          }
        }
        lbls
      }
    )
  }

}
