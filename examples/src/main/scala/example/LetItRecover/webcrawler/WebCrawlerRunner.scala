package example.LetItRecover.webcrawler

import event_lang.network.LHChannel.LHostSessionChannelEPs
import example.util.EPRunner

object WebCrawlerRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null

    val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "WebCrawler",
      (() => new EP_M(), List(0)),
      (() => new EP_I(), List(1)),
      (() => new EP_D(), List(2,3)),
      (() => new EP_P(), List(4,5)))
    network = res._1
    res._3.foreach(_.start())


  }

}
