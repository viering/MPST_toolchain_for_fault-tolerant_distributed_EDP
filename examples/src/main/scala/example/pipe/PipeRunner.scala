package example.pipe

import event_lang.network.LHChannel.LHostSessionChannelEPs
import example.util.EPRunner

object PipeRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null

    val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "Main",
      (() => new EP_A(), List(0)),
      (() => new EP_P(), Range(1, 8).toList))
    network = res._1
    res._3.foreach(_.start())


  }

}
