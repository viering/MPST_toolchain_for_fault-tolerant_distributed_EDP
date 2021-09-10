package example.sparkcmminus

import event_lang.dsl.AbstractChannelImp
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.types.{Role, RoleSet}
import example.sparkCluster.SparkCM.M.{GStartEx_m_M, __EPType_M}
import example.sparkCluster.SparkCM.W.GSel_w_W.RcvLaunchDriver
import example.sparkCluster.SparkCM.W.GStartEx_wEx_W.SndExDone
import example.sparkCluster.SparkCM.W.__EPType_W
import example.util.EPRunner
import example.util.UIntput.readInput

import scala.io.StdIn
import scala.util.Random

object ClusterRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null


    val res = EPRunner.bootstrapWithName(mainId, pickId, 0,0,"StartDriver",
      (() => new EP_Master(), List(0)),
      (() => new EP_Worker(), List(1,2,3,4)))
    network = res._1
    res._3.foreach(_.start())
  }

}
