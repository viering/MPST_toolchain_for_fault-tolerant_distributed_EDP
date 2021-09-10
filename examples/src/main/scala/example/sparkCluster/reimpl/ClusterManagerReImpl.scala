package example.sparkCluster.reimpl

import com.softwaremill.quicklens._
import event_lang.dsl._
import event_lang.semantic.CommonTypes.SessionID
import event_lang.types.{Role, RoleSet}
import example.sparkCluster.SparkCM.M.{EPType_M, __EPType_M}
import example.sparkCluster.SparkCM.MESSAGES
import example.sparkCluster.SparkCM.MESSAGES.GSel._
import example.sparkCluster.SparkCM.MESSAGES.GStartEx._
import example.sparkCluster.SparkCM.MESSAGES.Main._
import example.sparkCluster.SparkCM.W.{EPType_W, __EPType_W}
import example.sparkCluster.SparkCM.ZK.{EPType_ZK, __EPType_ZK}
import example.sparkCluster.reimpl.ExCompletionStatus.ExCompletionStatus
import example.sparkCluster.{AbstractDriver, SimpleSumDriver, Task}

import scala.concurrent.{ExecutionContext, Future}


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


object Types {
  type AppId = Int
  type ExId = Int
}


import example.sparkCluster.reimpl.Types._

object ID_Gen {
  private var __id = 0
  private var __curExId: Int = 1

  def appId(): AppId = {
    __id += 1
    __id
  }

  def exId(): ExId = {
    __curExId += 1
    __curExId
  }
}

object ExCompletionStatus extends Enumeration {
  type ExCompletionStatus = Value
  val Undefined, Completed, Failed = Value
}

case class GSelState(appID: AppId,
                     driver: AbstractDriver,
                     numEx: Int
                    )

case class GStartExState(appID: AppId, exID: ExId, task: Task, exComStatus: ExCompletionStatus = ExCompletionStatus.Undefined)

case class GMainState(appsToRun: Seq[AbstractDriver],
                      appsRunning: Map[AppId, AbstractDriver] = Map(),
                      appsDone: Map[AppId, AbstractDriver] = Map(),
                      curAppId: Option[AppId] = None)

case class StateM(
                   gMainState: GMainState,
                   gSelState: Map[SessionID, GSelState] = Map(),
                   gStartExState: Map[SessionID, GStartExState] = Map()
                 ) extends TState {
  def getGSelState(c: Session) = gSelState(c.sesId)

  def addGSelState(sID: SessionID, selS: GSelState): StateM = {
    this.copy(gSelState = gSelState + ((sID, selS)))
  }

  def appNeedsScheduling(c: Session): Boolean = {
    val ms = getGSelState(c)
    ms.numEx < ms.driver.numWorkUnitis
  }
}

class EP_Master(val sState: StateM = StateM(GMainState(Seq(new SimpleSumDriver(4)))),
                val block: (__EPType_M, StateM, AbstractChannelImp) => Boolean = (d, x, s) => false,
                val customCode: (__EPType_M, StateM, AbstractChannelImp) => Unit = (a, b, c) => {}) extends EPType_M[StateM] with AbstractEndPointTesting[__EPType_M, StateM] {

  import example.sparkCluster.SparkCM.M._


  override def onStartUp: StateM = sState

  override val pickHandler: Seq[PickHandler[StateM]] = ControlPick(
    controlPick(GSel_m_M.SpawnGStartEx) {
      case (st, s, ps) =>
        println(s"... we have a pick request")
        (st, Some(ps.head))
    }
  )

  override val subFinishHandler: Seq[FinishSpawnHandler[StateM]] = FinishSubSessionHandlers(
    finishSubSession(GSel_m_M.EPGSel_m_M) {
      case (st, parentS, childS) =>
        println(s"we finished a sub session $childS and its parent is $parentS")
        val state = st.getGSelState(childS)
        val done = state.driver.isFinished()
        val ns = if (done) {
          st.modify(_.gMainState.appsRunning).using(_ - state.appID).
            modify(_.gMainState.appsDone)(_ + (state.appID -> state.driver)).
            modify(_.gSelState)(_ - childS.sesId)
        } else {
          st.modify(_.gMainState.appsRunning).using(_ - state.appID).
            modify(_.gMainState.appsToRun)(state.driver.restart() +: _).
            modify(_.gSelState)(_ - childS.sesId)
        }
        ns
    },
    finishSubSession(GStartEx_m_M.EPGStartEx_m_M) {
      case (st, pS, cS) =>
        println(s"we finished a sub session $cS and its parent is $pS")
        val nS = st.modify(_.gStartExState)(_ - cS.sesId).
          modify(_.gSelState.index(pS.sesId).numEx).usingIf(st.gStartExState(cS.sesId).exComStatus != ExCompletionStatus.Completed)(_ - 1)
        nS
    }


  )

  override val receive: Seq[HDL[StateM]] = ELoop(

    /*
        Main
     */
    λ(Main_M.RcvFailMtoM) {
      case c => c ? {
        case (m, c) =>
          c
      }
    },


    λ_state(Main_m_M.SndDriverDone, Main_m_M.SndBMsg) {
      case (s, c) if s.gMainState.appsDone.nonEmpty => //  apps.exists(_.finished) =>
        val dA = s.gMainState.appsDone.head
        val dAppId = dA._1
        val dAppDriver = dA._2
        val mS = s.modifyAll(_.gMainState.appsDone, _.gMainState.appsRunning).using(_ - dAppId)
        println(s"[Master] ### Signal completion of Driver: " +
          s"$dAppId \n\t${dAppDriver.infoStr()}\n\t(running apps ${s.gMainState.appsRunning}")
        (mS, c ! DriverDone(dAppId) ! BMsg())
    },

    λ_state(Main_m_M.SndNewDriver, Main_m_M.SndPrepSpawn) {
      case (s, c) if s.gMainState.appsToRun.nonEmpty =>
        val app = s.gMainState.appsToRun.head
        val nS: StateM = s.modify(_.gMainState.appsToRun).using(_.tail).
          modify(_.gMainState.appsRunning).using(_ + (app.id -> app)).
          modify(_.gMainState.curAppId).setTo(Some(app.id))
        println(s"[Master] ### Start Driver $app -- running apps: ${s.gMainState.appsRunning.mkString(";")}")
        (nS, c ! NewDriver(app.id, app.numWorkUnitis) ! PrepSpawn())
    },
    λ_state(Main_m_M.SndEnd, Main_m_M.SndL3) {
          //this is a save block.
      case (s, c) if c.session.hasNoSubsession() =>
        println(s"[Master] ### Signal completion of Scheduling")
        (s, c ! MESSAGES.Main.End() ! L3())
    },
    /*
      GSel
   */
    λ_static_state(Main_m_M.SpawnGSel) {
      case (s, c) =>
        val nSubId = c.session.newSubId.get
        val curId = s.gMainState.curAppId.get
        println(s"[Master] ### Spawn GSel: " +
          s"curId: $curId " +
          s"new id: $nSubId (added $nSubId -> $curId)")
        val nS = s.modify(_.gMainState.curAppId).setTo(None).
          addGSelState(nSubId, GSelState(curId, s.gMainState.appsRunning(curId), 0))
        (nS, c)
    },

    λ_state(GSel_m_M.SndLaunchDriver) {
      case (s, c) =>
        (s, c ! LaunchDriver(s.getGSelState(c.session).appID, s.getGSelState(c.session).driver))
    },
    λ(GSel_m_M.RcvAckNStatus) {
      case c => c ? {
        case (m, c) =>
          println(s"[Master] ### Ack Driver Launch $m")
          c
      }
    },

    λ_state(GSel_m_M.SndStartExCase) {
      case (s, c) if s.appNeedsScheduling(c.session) =>
        val app = s.gSelState(c.session.sesId).appID
        (s.modify(_.gSelState.index(c.session.sesId).numEx).using(_ + 1), c ! StartExCase(app))
    },
    λ_state(GSel_m_M.SndEnd) {
          // again waiting for subsession completion is a save block
      case (s, c) if c.session.hasNoSubsession() =>
        val app = s.getGSelState(c.session)
        println(s"[Master] ### Finished app: $app send out End in GSel (apps: ${s.gMainState.appsRunning}")
        (s, c ! MESSAGES.GSel.End())
    },
    λ_static_state(GSel_m_M.SpawnGStartEx) {
      case (s, c) =>
        val nSubId = c.session.newSubId.get
        val app = s.gSelState(c.session.sesId)

        val exId = ID_Gen.exId()
        val nS = s.modify(_.gStartExState).using(_ + (nSubId ->
          GStartExState(app.appID, exId, app.driver.getTask(exId))))
        println(s"[Master] ### Spawn GStartEx: added ${nS.gStartExState(nSubId)})")
        (nS, c)
    },
    λ_state(GSel_m_M.Failed_w_W, GSel_m_M.SndFailGSelMtoW) {
      case (s, c) =>
        val gSelS = s.gSelState(c.session.sesId)
        println(s"[Master] ### FAILURE in GSel -- " +
          s"Worker ${c.session.roleToPId(Role("w", RoleSet("W")))} for " +
          s"App ${gSelS} [${s.gSelState}]")
        val appId = gSelS.appID
        (s, c.failed_w_W() ! FailGSelMtoW(appId))
    },
    λ_state(GStartEx_m_M.SndStartEx) {
      case (s, c) =>
        val mS = s.gStartExState(c.session.sesId)
        (s, c ! StartEx(mS.appID, mS.exID, mS.task))
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
          (s.modify(_.gStartExState.index(c.session.sesId).exComStatus).setTo(ExCompletionStatus.Completed), c ! ExFinishStatus(m.appId, m.exId))
      }
    },
    λ_state(GStartEx_m_M.Failed_wEx_W, GStartEx_m_M.SndExFailed) {
      case (s, c) =>
        val sId = c.session.sesId
        val mS = s.gStartExState(c.session.sesId)
        val appId = mS.appID
        val exId = mS.exID
        (s.modify(_.gStartExState.index(c.session.sesId).exComStatus).setTo(ExCompletionStatus.Failed), c.failed_wEx_W() ! ExFailed(appId, exId))
    }
  )
}


case class WGSelState(appId: AppId, driver: AbstractDriver, driverCalc: Future[Unit])

case class WGExWorkerState(appId: AppId, exId: ExId, exCalc: Future[Unit])

case class WGExWorker_wState(driver: AbstractDriver)

case class StateW(selState: Map[SessionID, WGSelState] = Map(),
                  exState: Map[SessionID, WGExWorkerState] = Map(),
                  exState_w: Map[SessionID, WGExWorker_wState] = Map()) extends TState {
}

class EP_Worker(val block: (__EPType_W, StateW, AbstractChannelImp) => Boolean = (d, x, s) => false,
                val customCode: (__EPType_W, StateW, AbstractChannelImp) => Unit = (a, b, c) => {}) extends EPType_W[StateW] with AbstractEndPointTesting[__EPType_W, StateW] {

  import example.sparkCluster.SparkCM.W._

  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.fromExecutor(new java.util.concurrent.ForkJoinPool(6))

  override def onStartUp: StateW = StateW()

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
    λ_state(GSel_w_W.RcvLaunchDriver, GSel_w_W.SndAckNStatus) {
      case (s, c) =>
        c ? {
          case (m, c) =>
            assert(m.appID == m.driver.id)

            val ms = s.modify(_.selState).using(_ + ((c.session.sesId, WGSelState(m.appID, m.driver, Future {
              m.driver.start()
            }))))
            (ms, c ! AckNStatus(m.appID))
        }
    },

    λ(GSel_w_W.RcvEnd) {
      case c => c.channelCon
    },

    λ(GSel_w_W.RcvStartExCase) {
      case c => c.channelCon
    },
    λ_static_state(GSel_w_W.SpawnGStartEx) {
      case (s, c) =>
        (s.modify(_.exState_w).using(_ + (c.session.newSubId.get -> WGExWorker_wState(s.selState(c.session.sesId).driver))), c)
    }

    ,
    λ_state(GStartEx_wEx_W.RcvStartEx, GStartEx_wEx_W.SndExStarted) {
      case (s, c) => c ? {
        case (m, c) =>
          val ns = s.modify(_.exState).using(_ + (c.session.sesId -> WGExWorkerState(m.appId, m.exId, Future {
            m.task.execute()
          })))
          println(s"[w:${c.session.myId}] start Task for exId: ${m.exId}")
          (ns, c ! ExStarted(m.appId, m.exId))
      }
    },
    λ_state(GStartEx_w_W.RcvExRunning) {
      case (s, c) => c ? {
        case (m, c) =>
          s.exState_w(c.session.sesId).driver.exStarted(m.exId)
          println(s"[w:${c.session.myId}] For app id: ${m.appId} start executor with id: ${m.exId}")
          (s, c)
      }
    },
    λ_state(GStartEx_w_W.RcvExFinishStatus) {
      case (s, c) => c ? {
        case (m, c) =>
          s.exState_w(c.session.sesId).driver.exFinished(m.exId)
          (s, c)
      }
    },
    λ_state(GStartEx_w_W.RcvExFailed) {
      case (s, c) => c ? {
        case (m, c) =>
          println(s"[Worker;pId:${c.session.myId};sId:${c.session.sesId}] Signal driver (appId: ${m.appId}) that exId:${m.exId} has failed")
          val d = s.exState_w(c.session.sesId).driver //s.aIdToDriver(m.appId)
          if (!d.exFailed(m.exId)) {
            Console.err.println(s"[warning] ${c.session} should kill ex: ${m.exId} however its not running or already done")
            //a failure can result in abording GStart before this worker receide the start ex messag. Therefore, most likle this warning can be safly ignored.
          }
          (s, c)
      }
    },
    λ_state(GStartEx_wEx_W.SndExDone) {
      case (s, c) if s.exState(c.session.sesId).exCalc.isCompleted =>
        val a = s.exState(c.session.sesId)
        (s, c ! ExDone(a.appId, a.exId))
    }


  )
}