package example.globalescape.buyer

import event_lang.network.LHChannel.LHostSessionChannelEPs
import example.globalescape.buyer.types.InterruptibleBuyer.Client.{NegotiationCB_c_Client, ThreeBuyers_c_Client}
import example.globalescape.buyer.types.InterruptibleBuyer.Client.ThreeBuyers_c_Client.EPThreeBuyers_c_Client
import example.util.EPRunner

object InterBuyerRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    val shouldInter = true
    println(s"\n\n\nWe will run the Interruptible 3-Buyers. The run will include induced FAILURES\n\n\n")
    val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "Main",
      (() => new EP_Seller(), List(0)),
      (() => new EP_Client(block = {
        case (NegotiationCB_c_Client.RcvOK,_,c) =>
          shouldInter
        case (NegotiationCB_c_Client.RcvNEM,_,c) =>
          shouldInter
        case (ThreeBuyers_c_Client.SndM,_,c) =>
          shouldInter
        case _ => false
      }), List(1)),
      (() => new EP_Bank(), List(2)),
      (() => new EP_Interrupt(), List(3,4,5)))
    network = res._1
    res._3.foreach(_.start())

    if(shouldInter){
      Thread.sleep(5000)
      network.ep_ses_channels(0).signalFail(4)
      Thread.sleep(2500)
      network.ep_ses_channels(0).signalFail(3)
    }
    res._3.foreach(_.join())
  }

}
