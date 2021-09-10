package example.streaming_popl_08

import event_lang._
import dsl.{AbstractEndPoint, _}
import example.util.EPRunner
import network.{SessionChannelEP, SpawnMain}

object StreamingRunnerNoNetwork {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1
    println(s"\n\nWe will run the streaming application from POPL 08, and let it fail some messages")
    var network: SessionChannelEP = null
    var sigF = false
    val eps = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new EP_Data(customCode = {
        case (_, s, c) if s.num >= 10 && !sigF =>
          network.signalFail(1)
          sigF = true
        case _ =>
      }), List(0)), (() => new EP_Key, List(1)),
      (() => new EP_Kernel, 2 to 3), (() => new EP_Consumer, 4 to 5))
    network = eps._2(0).network
    eps._3.foreach(_.start())
    eps._3.foreach(_.join())
    //    val eps = EPRunner.bootstrap(mainId, pickId,0,0,
    //      (() => new EP_Data(), List(0)), (() => new EP_Key, List(1)),
    //      (() => new EP_Kernel, 2 to 3), (() => new EP_Consumer, 4 to 5))
    //    eps._3.foreach(_.start())
    //    eps._3.foreach(_.join())
  }
}