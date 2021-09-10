package example.sparkCluster

import event_lang.dsl._
import event_lang.types.{Role, RoleSet}
import example.sparkCluster.SparkCM.M.{EPType_M, __EPType_M}
import example.sparkCluster.SparkCM.MESSAGES
import example.sparkCluster.SparkCM.MESSAGES.GSel._
import example.sparkCluster.SparkCM.MESSAGES.GStartEx._
import example.sparkCluster.SparkCM.MESSAGES.Main._
import example.sparkCluster.SparkCM.W.{EPType_W, __EPType_W}
import example.sparkCluster.SparkCM.ZK.{EPType_ZK, __EPType_ZK}

import java.util.concurrent.atomic.{AtomicBoolean, AtomicInteger}


trait ExecuterInfo

case class WorkId(id: Int) extends ExecuterInfo

case class PoisenPill() extends ExecuterInfo

case class Failed() extends ExecuterInfo

trait Task {
  def execute(): Option[Any]
}

trait Data {}

case class DRange(frm: Int, to: Int) extends Data

//case class Task(task: () => ())

object APP_ID {
  private var __id = 0

  def apply(): Int = {
    __id += 1
    __id
  }
}


abstract class AbstractDriver(val numWorkUnitis: Int) extends Serializable {
  val id: Int = APP_ID()

  val UNINIALIZED = 0
  val RUNNING = 1
  val DONE = 2
  val FAILED = 3

  val toTaskQ = new Array[Data](numWorkUnitis)
  val frmTaskQ = new Array[Int](numWorkUnitis)

  protected var executerIDQ = Map[Int, ExecuterInfo]()
  protected val exStatus = new Array[Int](numWorkUnitis)

  protected def startExecutor(clusterID: Int): Unit = {
    val idx = exStatus.indexWhere(status => status == UNINIALIZED || status == FAILED)
    if (idx < 0) {
      executerIDQ += (clusterID -> PoisenPill())
    } else {
      exStatus(idx) = RUNNING
      executerIDQ += (clusterID -> WorkId(idx))
    }
  }

  def getTask(clusterExId: Int): Task

  def isFinished(): Boolean = {
    exStatus.forall(_ == DONE)
  }

  def start(): Unit



  def exStarted(clusterExId: Int) = {
    startExecutor(clusterExId)
  }


  def exFinished(id: Int): Unit = {
    executerIDQ(id) match {
      case WorkId(id) =>
        exStatus(id) = DONE
      case PoisenPill() =>

    }
  }

  def exFailed(clusterExid: Int): Boolean = {
    executerIDQ.get(clusterExid) match {
      case Some(WorkId(id)) =>
        exStatus(id) match{
          case DONE =>
            false
          case RUNNING =>
            exStatus(id) = FAILED
            executerIDQ += clusterExid -> Failed()
            true
        }

      case None =>
        false

      case _ => ??? //remove compiler warning ..... if this is triggered something is broken
    }
  }

  //todo i should split the driver at master and the driver at the worker more
  def restart(): AbstractDriver

  def infoStr(): String
}

class SimpleSumDriver(num: Int, onCompletionCB : Int => Unit = (x : Int) => {} ) extends AbstractDriver(num) {
  self =>

  val killed = new AtomicBoolean(false)
  private def kill() : Unit = {
    killed.set(true)
  }

  def restart(): AbstractDriver = {
    kill()
    new SimpleSumDriver(numWorkUnitis,onCompletionCB)
  }


  for (i <- toTaskQ.indices) {
    toTaskQ(i) = DRange(i * 1000, (i + 1) * 1000 - 1)
  }

  def infoStr(): String = {
    s"Driver: \n" +
      s"\tEx Status: ${exStatus.mkString(";")}" +
      s"\tEx Result: ${frmTaskQ.mkString(";")}"
  }

  def start(): Unit = {
    while (!isFinished() && !killed.get()) {
      Thread.sleep(100)
    }
    if(!killed.get()){
      val n = toTaskQ.length * 1000 - 1
      assert(n * (n + 1) / 2 == frmTaskQ.sum, s"Tast result must be: n($n) * (n + 1) / 2 (= ${n * (n + 1) / 2}) == frmTaskQ.sum (=${frmTaskQ.sum})")
      println(
        s"###############################\n" +
          s"### Result: ${frmTaskQ.sum} ###\n" +
          s"###############################")
      onCompletionCB(frmTaskQ.sum)}
  }

  override def toString: String = s"SimpleDriver(id = $id, numE = $numWorkUnitis, ${frmTaskQ.mkString(",")}, exIDQ=$executerIDQ, exStatus=${exStatus.mkString(",")}"

  def getTask(clusterExId: Int): Task = {
    new Task {
      override def execute(): Option[Any] = {
        println(s"[Task:$clusterExId] started -- for Driver: ${self.id}")
        val cId = clusterExId
        //wait until the driver is ready to deal with us
        while (!executerIDQ.contains(cId)) {
          Thread.sleep(100)
        }
        println(s"[Task$clusterExId] input data receive -- for Driver: ${self.id}")
        val id = executerIDQ(cId)
        id match {
          case WorkId(id) =>
            val d = toTaskQ(id)
            d match {
              case DRange(frm, to) =>
                var res = 0
                for (i <- frm to to) {
                  res += i
                }
                frmTaskQ(id) = res
                println(s"[Task] result $res -- for Driver: ${self.id}")
                Some(res)
            }
          case Failed() =>
            println(s"[Task] The task to execute is failed. This should only happen if the worker which executes the tasked faile -- for Driver: ${self.id}")
            None
          case PoisenPill() =>
            println(s"[Task] got poisen pill -- for Driver: ${self.id}")
            None
        }
      }
    }
  }
}


case class App(appID: Int, work: Int, scheduled: Boolean, finished: Boolean = false)

class StateMastr(appsToSchedule : List[AbstractDriver]) extends TState {
  type AppId = Int
  type SesId = Long
  var appsToRun: Seq[AbstractDriver] = appsToSchedule //List(new SimpleSumDriver(2), new SimpleSumDriver(5), new SimpleSumDriver(10))
  var appsRunning: Map[AppId, AbstractDriver] = Map()
  var appNumEx: Map[AppId, Int] = Map()
  var appsDone: Set[AppId] = Set()
  var curAppId: Option[AppId] = None
  var sesIdToApp: Map[SesId, AppId] = Map()
  var sesIdToExId: Map[SesId, AppId] = Map()
  private var __curExId = 1

  override def toString = s"StateM(appsToRun=$appsToRun, appsRunning=$appsRunning, appNumEx=$appNumEx, appsDone=$appsDone, curlAppId=$curAppId, sesIdToApp=$sesIdToApp, sesIdToExId=$sesIdToExId, ${__curExId})"


  def appFinished(appID: Int): Boolean = appsDone.contains(appID)

  def appFinished(sesId: Long): Boolean = appsDone.contains(sesIdToApp(sesId))

  def appNeedsScheduling(sesId: Long): Boolean = {
    if (sesIdToApp.contains(sesId)) {
      val appId = sesIdToApp(sesId)
      if (appsRunning.contains(appId)) {
        val app = appsRunning(appId)
        val numAEx: Int = appNumEx.getOrElse(app.id, 0)
        numAEx < app.numWorkUnitis
      } else {
        false
      }
    } else {
      false
    }
  }


  def getApp(sesId: Long): AbstractDriver = {
    val appId = sesIdToApp(sesId)
    appsRunning(appId)
    //val app = apps.find(_.appID == appId)
    //assert(app.nonEmpty, s"$sesId must be in $sesIdToApp -- $apps")
    //app.get
  }

  //var apps: Set[App] = Set(App(0, 5, false)) //, App(1, 3, false), App(2, 2, false))


  def nextExId(): Int = {
    __curExId += 1
    __curExId - 1
  }


}



case class StateZK(runApps: Set[Int] = Set()) extends TState

class EP_ZK(val block: (AbstractChannelType, StateZK, AbstractChannelImp) => Boolean = (d, x, s) => false,
            val customCode: (AbstractChannelType, StateZK, AbstractChannelImp) => Unit = (a, b, c) => {}) extends EPType_ZK[StateZK] with AbstractEndPointTesting[__EPType_ZK, StateZK] {

  import example.sparkCluster.SparkCM.ZK._

  override def onStartUp: StateZK = StateZK()

  override val receive: Seq[HDL[StateZK]] = ELoop(
    λ_state(Main_zk_ZK.Failed_m_M, Main_zk_ZK.SndFailMtoM, Main_zk_ZK.SndFailMtoW) {
      case (s, c) =>
        println(s"[Zk] we should restart (not implemented): ${s.runApps.mkString(",")}")
        (s, c.failed_m_M() ! FailMtoM() ! FailMtoW())
    },

    λ_state(Main_zk_ZK.RcvNewDriver) {
      case (s, c) => c ? {
        case (m, c) =>
          (s.copy(runApps = s.runApps + m.appID), c)
      }
    },
    λ_state(Main_zk_ZK.RcvDriverDone) {
      case (s, c) => c ? {
        case (m, c) =>
          (s.copy(runApps = s.runApps - m.appID), c)
      }
    },
    λ_state(Main_zk_ZK.RcvEnd) {
      case (s, c) => c ? {
        case (m, c) =>
          println(s"[zk] received $m - start termination")
          if (s.runApps.nonEmpty) {
            println(s"[zk] received $m however the following apps are still running ${s.runApps.mkString(",")}")
          }
          (s, c)
      }
    }

  )
}

class EP_Master(val sState: StateMastr = new StateMastr(List(new SimpleSumDriver(4))),
                val block: (__EPType_M, StateMastr, AbstractChannelImp) => Boolean = (d, x, s) => false,
                val customCode: (__EPType_M, StateMastr, AbstractChannelImp) => Unit = (a, b, c) => {}) extends EPType_M[StateMastr] with AbstractEndPointTesting[__EPType_M, StateMastr] {

  import example.sparkCluster.SparkCM.M._


  override def onStartUp: StateMastr = sState


  override val pickHandler: Seq[PickHandler[StateMastr]] = ControlPick(
    controlPick(GSel_m_M.SpawnGStartEx){
      case (st,s,ps)=>
        println(s"... we have a pick request")
        (st,Some(ps.head))
    }
  )

  override val subFinishHandler: Seq[FinishSpawnHandler[StateMastr]] = FinishSubSessionHandlers(
    finishSubSession(GSel_m_M.EPGSel_m_M){
      case (st,parentS,childS)=>
        println(s"we finished a sub session $childS with $parentS as parent session")
        st
    }


  )
  override val receive: Seq[HDL[StateMastr]] = ELoop(
    /*
        Main
     */
    λ(Main_M.RcvFailMtoM) {
      case c => c ? {
        case (m, c) =>
          //TODO should add failure handling (currently not covered in the global type
          c
      }
    },


    λ_state(Main_m_M.SndDriverDone, Main_m_M.SndBMsg) {
      case (s, c) if s.appsRunning.exists(_._2.isFinished()) => //  apps.exists(_.finished) =>
        val dA = s.appsRunning.find(_._2.isFinished()).get
        val dAppId = dA._1
        val dAppDriver = dA._2
        s.appsDone += dAppId
        s.appsRunning -= dAppId
        //val doneApp = s.apps.find(_.finished).get
        //s.apps = s.apps - doneApp
        println(s"[Master] ### Signal completion of Driver: $dAppId \n\t${dAppDriver.infoStr()}\n\t(running apps ${s.appsRunning}")
        (s, c ! DriverDone(dAppId) ! BMsg())
    },

    λ_state(Main_m_M.SndNewDriver, Main_m_M.SndPrepSpawn) {
      case (s, c) if s.appsToRun.nonEmpty =>
        //val appToSchedule = s.apps.find(!_.scheduled).get
        val app = s.appsToRun.head
        s.appsToRun = s.appsToRun.tail
        s.appsRunning += app.id -> app
        println(s"[Master] ### Start Driver $app -- running apps: ${s.appsRunning.mkString(";")}")

        s.curAppId = Some(app.id)
        //s.apps = s.apps - appToSchedule + App(appToSchedule.appID, appToSchedule.work, true)
        (s, c ! NewDriver(app.id, app.numWorkUnitis) ! PrepSpawn())
    },
    //this should be save if i just add a condition on no subsessions here
    λ_state(Main_m_M.SndEnd, Main_m_M.SndL3) {
      case (s, c) if s.appsRunning.isEmpty && s.appsToRun.isEmpty => //s.apps.isEmpty =>
        println(s"[Master] ### Signal completion of Scheduling")
        (s, c ! MESSAGES.Main.End() ! L3())
    },
    /*
      GSel
   */
    λ_static_state(Main_m_M.SpawnGSel) {
      case (s, c) =>
        val nSubId = c.session.newSubId.get
        val curId = s.curAppId.get
        println(s"[Master] ### Spawn GSel: curId: $curId new id: $nSubId (added $nSubId -> $curId) [${s.sesIdToApp.mkString(";")}]")
        s.sesIdToApp += nSubId -> curId
        //println(s"[Master] ${s.sesIdToApp}")
        s.curAppId = None
        (s, c)
    },

    λ_state(GSel_m_M.SndLaunchDriver) {
      case (s, c) =>
        val aId = s.sesIdToApp(c.session.sesId)
        val app = s.appsRunning(aId)
        (s, c ! LaunchDriver(aId, app))
    },
    λ(GSel_m_M.RcvAckNStatus) {
      case c => c ? {
        case (m, c) =>
          println(s"[Master] ### Ack Driver Lauch $m")
          c
      }
    },

    λ_state(GSel_m_M.SndStartExCase) {
      case (s, c) if s.appNeedsScheduling(c.session.sesId) =>
        val app = s.getApp(c.session.sesId)
        s.appNumEx += (app.id -> (s.appNumEx.getOrElse(app.id, 0) + 1))


        (s, c ! StartExCase(app.id))
    },
    //just waiting for all subsession completion should be sufficient
    λ_state(GSel_m_M.SndEnd) {
      case (s, c) if s.appFinished(c.session.sesId) => // s.getApp(c.session.sesId).isFinished() => //s.getApp(c.session.sesId).work == 0 && c.session.subSessions.isEmpty =>
        val app = s.sesIdToApp(c.session.sesId)
        //s.appsRunning -= app.id
        println(s"[Master] ### Finished app: $app send out End in GSel (apps: ${s.appsRunning}")
        (s, c ! MESSAGES.GSel.End())
    },
    λ_static_state(GSel_m_M.SpawnGStartEx) {
      case (s, c) =>
        val nSubId = c.session.newSubId.get
        val app = s.getApp(c.session.sesId)
        println(s"[Master] ### Spawn GStartEx: added $nSubId -> ${app.id}) [${s.sesIdToApp.mkString(";")}]")
        s.sesIdToApp += nSubId -> app.id
        s.sesIdToExId += nSubId -> s.nextExId()
        (s, c)
    },
    λ_state(GSel_m_M.Failed_w_W, GSel_m_M.SndFailGSelMtoW) {
      case (s, c) =>
        println(s"[Master] ### FAILURE in GSel -- Worker ${c.session.roleToPId(Role("w", RoleSet("W")))} for App ${s.sesIdToApp(c.session.sesId)} [${s.sesIdToApp.mkString(";")}]")
        val appId = s.sesIdToApp(c.session.sesId)
        if(!s.appsDone.contains(appId)) {
          val app = s.getApp(c.session.sesId)
          s.appsRunning -= app.id
          s.appNumEx -= app.id
          s.appsToRun = app.restart() +: s.appsToRun
          (s, c.failed_w_W() ! FailGSelMtoW(s.sesIdToApp(c.session.sesId)))
        }else{
          // in rare caes this handling can get triggerd but the app is already finished -- then there is nothing to do execpt normal session cleanum
          (s, c.failed_w_W() ! FailGSelMtoW(appId))
        }
    },
    λ_state(GStartEx_m_M.SndStartEx) {
      case (s, c) =>
        println(s"[Master] SndStartEx -- ${s.sesIdToApp}")
        val app = s.getApp(c.session.sesId)
        val appId = s.sesIdToApp(c.session.sesId)
        val exId = s.sesIdToExId(c.session.sesId)
        (s, c ! StartEx(appId, exId, app.getTask(exId)))
    },
    λ(GStartEx_m_M.RcvExStarted, GStartEx_m_M.SndExRunning) {
      case c => c ? {
        case (m, c) =>
          println(s"[Master] ### Ack Ex Started: $m")
          c ! ExRunning(m.appId, m.exId)
      }
    },
    λ_state(GStartEx_m_M.RcvExDone, GStartEx_m_M.SndExFinishStatus) {
      case (s, c) => c ? {
        case (m, c) =>
          //FIXME we need to clean state however since a failure could still trigger f handling we cannot do it
          // => we need a handler which is executed after completion !!!
          (s, c ! ExFinishStatus(m.appId, m.exId))
      }
    },
    λ_state(GStartEx_m_M.Failed_wEx_W, GStartEx_m_M.SndExFailed) {
      case (s, c) =>
        val sId = c.session.sesId
        val appId = s.sesIdToApp(sId)
        val exId = s.sesIdToExId(sId)
        if(!s.appsDone(appId)) {
          println(s"[Master] ### FAILURE in GStartEx - Worker ${c.session.roleToPId(Role("wEx", RoleSet("W")))} - EX ${s.sesIdToExId(sId)} -- remove $sId")
          //s.sesIdToExId -= sId
          //s.sesIdToApp -= sId
          val num = s.appNumEx(appId)
          s.appNumEx += (appId -> (num - 1))
//          s.appsDone -= appId
          //s.apps -= app
          //s.apps += app.copy(work = app.work + 1)
        }
        (s, c.failed_wEx_W() ! ExFailed(appId, exId))
    }

  )
}

class StateW extends TState {
  var sIdToAppId = Map[Long, Int]()
  var sIdToExId = Map[Long, Int]()
  var aIdToDriver = Map[Int, AbstractDriver]()
  var sIdWStates = Map[Long, AtomicInteger]()

  override def toString: String = s"StateW($sIdToAppId,$sIdToExId,$aIdToDriver,$sIdWStates)"
}

class EP_Worker(val block: (__EPType_W, StateW, AbstractChannelImp) => Boolean = (d, x, s) => false,
                val customCode: (__EPType_W, StateW, AbstractChannelImp) => Unit = (a, b, c) => {}) extends EPType_W[StateW] with AbstractEndPointTesting[__EPType_W, StateW] {

  import example.sparkCluster.SparkCM.W._

  override def onStartUp: StateW = new StateW()

  override val receive: Seq[HDL[StateW]] = ELoop(
    λ(Main_W.RcvFailMtoW) {
      case c => c ? {
        case (m, c) =>
          c
      }
    },
    λ(Main_W.RcvPrepSpawn) {
      case c => c.channelCon
    },

    λ(Main_W.RcvBMsg) {
      case c => c.channelCon
    }
    ,
    λ(Main_W.RcvL3) {
      case c =>
        println(s"[W] received L3 - start termination")
        c.channelCon
    },


    λ(GSel_W.RcvEnd) {
      case c => c.channelCon
    },
    λ(GSel_W.RcvFailGSelMtoW) {
      case c => c.channelCon
    },
    λ(GSel_W.RcvStartExCase) {
      case c => c.channelCon
    },
    λ_state(GSel_w_W.RcvLaunchDriver) {
      case (s, c) =>
        c ? {
          case (m, c) =>
            assert(m.appID == m.driver.id)
            s.sIdToAppId += c.session.sesId -> m.appID
            val wStatus = new AtomicInteger(0)
            s.sIdWStates += c.session.sesId -> wStatus
            s.aIdToDriver += m.appID -> m.driver
            new Thread(() => {
              m.driver.start()
            }).start()
            (s, c)
        }
    },
    λ_state(GSel_w_W.SndAckNStatus) {
      case (s, c) => //(if s.sIdWStates(c.session.sesId).get() > 0 =>
        (s, c ! AckNStatus(s.sIdToAppId(c.session.sesId)))
    },
    λ(GSel_w_W.RcvEnd) {
      case c => c.channelCon
    },

    λ(GSel_w_W.RcvStartExCase) {
      case c => c.channelCon
    },
    λ_state(GStartEx_w_W.RcvExRunning) {
      case (s, c) => c ? {
        case (m, c) =>
          val d = s.aIdToDriver(m.appId)
          d.exStarted(m.exId)
          println(s"[w:${c.session.myId}] ${m.appId} startEx ${m.exId}")
          (s, c)
      }
    },
    λ_state(GStartEx_w_W.RcvExFinishStatus) {
      case (s, c) => c ? {
        case (m, c) =>
          val d = s.aIdToDriver(m.appId)
          d.exFinished(m.exId)
          (s, c)
      }
    },
    λ_state(GStartEx_wEx_W.RcvStartEx, GStartEx_wEx_W.SndExStarted) {
      case (s, c) => c ? {
        case (m, c) =>
          val a = new AtomicInteger(0)
          s.sIdWStates += c.session.sesId -> a
          s.sIdToAppId += c.session.sesId -> m.appId
          s.sIdToExId += c.session.sesId -> m.exId
          new Thread(() => {
            m.task.execute()
            //Thread.sleep(1000)
            a.set(1)
          }).start()
          (s, c ! ExStarted(m.appId, m.exId))
      }
    },
    λ_state(GStartEx_w_W.RcvExFailed) {
      case (s, c) => c ? {
        case (m, c) =>
          println(s"[Worker;pId:${c.session.myId};sId:${c.session.sesId}] Signal driver (appId: ${m.appId}) that exId:${m.exId} has failed")
          val d = s.aIdToDriver(m.appId)
          if(!d.exFailed(m.exId)){
            Console.err.println(s"[warning] ${c.session} should kill ex: ${m.exId} however its not running or already done")
            //a failure can result in abording GStart before this worker receide the start ex messag. Therefore, most likle this warning can be safly ignored.
          }
          (s, c)
      }
    },
    λ_state(GStartEx_wEx_W.SndExDone) {
      case (s, c) if s.sIdWStates(c.session.sesId).get() > 0 =>
        (s, c ! ExDone(s.sIdToAppId(c.session.sesId), s.sIdToExId(c.session.sesId)))
    }


  )
}