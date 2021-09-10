package example.esop_crash_handling.streaming

import java.util.concurrent.atomic.AtomicInteger

import event_lang.network.LHChannel.LHostSessionChannelEPs
import example.esop_crash_handling.streaming.types.Streaming.W.Partition_w_W
import example.util.EPRunner

object ClusterRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null

    val numKills = new AtomicInteger(0)

    println(s"\n\n\nWe will run the Failure-Aware Streaming.\n"+
      "We first let TWO workers FAIL (the protocol can toloerated those) after each other, then we let the DFS Fail resulting in the termination\n\n\n")

    val res = EPRunner.bootstrapWithName(mainId, pickId, 0,0,"Main",
      (() => new EP_ZK(), List(0)),
      (() => new EP_DFS(), List(1)),
      (() => new EP_W(customCode = {
        case (Partition_w_W.RcvWorkLoad,_,c) =>
          val i = numKills.getAndIncrement()
          if(i == 5){
            network.ep_ses_channels(0).signalFail(c.session.myId)
          }else if( i == 10){
            network.ep_ses_channels(0).signalFail(c.session.myId)
          }else if (i == 15){
            network.ep_ses_channels(0).signalFail(1)
          }
        case _ =>
      }), List(2,3,4,5,6)))
    network = res._1
    res._3.foreach(_.start())
  }

}
