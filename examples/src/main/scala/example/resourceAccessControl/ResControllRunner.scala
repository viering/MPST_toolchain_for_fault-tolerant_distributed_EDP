package example.resourceAccessControl

import event_lang.network.LHChannel.LHostSessionChannelEPs
import example.util.EPRunner

object ResControllRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    println(s"\n\n\nWe will run the Resource Control. The run will include TWO induced FAILURES\n\n\n")
    val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "ResAccCon",
      (() => new EP_C(), List(0)),
      (() => new EP_U(), List(1)),
      (() => new EP_A(), List(2)),
      (() => new EP_IOne(), List(3)),
      (() => new EP_ITwo(), List(4,5)))
    network = res._1
    res._3.foreach(_.start())

    Thread.sleep(5000)
    network.ep_ses_channels(0).signalFail(4)
    Thread.sleep(2500)
    network.ep_ses_channels(0).signalFail(3)
  }

}
