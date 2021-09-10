package example.sparkCluster

import event_lang.dsl.AbstractChannelImp
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.types.{Role, RoleSet}
import example.sparkCluster.SparkCM.M.{GStartEx_m_M, __EPType_M}
import example.sparkCluster.SparkCM.W.GSel_w_W.RcvLaunchDriver
import example.sparkCluster.SparkCM.W.GStartEx_wEx_W.SndExDone
import example.sparkCluster.SparkCM.W.__EPType_W
import example.sparkCluster.reimpl.{GMainState, StateM}
import example.util.EPRunner
import example.util.UIntput.readInput

import scala.io.StdIn
import scala.util.Random

object ClusterRunnerRe {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var idOfAAppWorker = -1
    var numKills = 0
    val numToKillW = 0
    var fliflob = true

    println(s"""In the session CM use case a number of application are scheduled on top of a session cluster manager""")

    println("""Please provide the number of applications you want to run""")
    val numApps = readInput({
      StdIn.readInt()},
      (x : Int) => {
        x > 0 && x <= 10
      }, "Please provide an integer value in the range of [1,10]")


    val apps = for(i <-1 to numApps) yield {
      println(s"Creating App #$i ")
      println(s"How many work packets should be creatad for App #$i?")
      val numW = readInput({
        StdIn.readInt()},
        (x : Int) => {
          x > 0 && x <= 10
        }, "Please provide an integer value in the range of [1,10]")
      new SimpleSumDriver(num = numW)
    }

    val block: (__EPType_W, reimpl.StateW, AbstractChannelImp) => Boolean = {
      case (SndExDone, s, c) =>
        s.exState(c.session.sesId).appId == 1 && numKills < numToKillW
      case _ => false
    }

    val cusCodeW: (__EPType_W, reimpl.StateW, AbstractChannelImp) => Unit = {
      case (RcvLaunchDriver, s, c) =>
        idOfAAppWorker = c.session.myId
      case _ =>
    }
    val cusCodeM: (__EPType_M, reimpl.StateM, AbstractChannelImp) => Unit = {
      case (GStartEx_m_M.RcvExStarted, s, c) if s.gStartExState(c.session.sesId).appID == 1 && numKills < numToKillW =>
        numKills += 1
        val toKillp = c.session.roleToPId(Role("wEx", RoleSet("W")))
        new Thread(() => {
          println(s"we kill p. $toKillp")
          Thread.sleep(Random.nextInt(2000))
          if (fliflob) {
            network.ep_ses_channels(0).signalFail(toKillp)
          } else {
            network.ep_ses_channels(0).signalFail(idOfAAppWorker)
          }
          fliflob = !fliflob
        }).start()
      case _ => {}
    }


    val res = EPRunner.bootstrap(mainId, pickId, 0,0,
      (() => new reimpl.EP_ZK(), List(0)), (() => new reimpl.EP_Master(sState = StateM(GMainState(apps)), customCode = cusCodeM), List(1, 2)),
      (() => new reimpl.EP_Worker(customCode = cusCodeW, block = block), List(3, 4,5)))
    network = res._1
    res._3.foreach(_.start())
  }

}
