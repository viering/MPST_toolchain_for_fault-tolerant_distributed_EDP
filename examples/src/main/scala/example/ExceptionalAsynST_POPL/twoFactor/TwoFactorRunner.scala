package example.ExceptionalAsynST_POPL.twoFactor

import java.util.concurrent.atomic.AtomicInteger

import event_lang.network.LHChannel.LHostSessionChannelEPs
import example.esop_crash_handling.streaming.types.Streaming.W.Partition_w_W
import example.util.EPRunner

object TwoFactorRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null

    val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "TwoFactor",
      (() => new EP_Server(), List(0)),
      (() => new EP_Client(), List(1)))
    network = res._1
    res._3.foreach(_.start())
  }

}
