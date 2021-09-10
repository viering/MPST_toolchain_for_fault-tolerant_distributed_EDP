package example.frm_paper.ring.fault_tolerant

import event_lang.dsl.{AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import example.util.EPRunner

object RingFTRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1

    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null

//    val block: AbstractChannelType => Boolean = {
//      case _ => false
//    }
//    val customCode: AbstractChannelType => Unit = {
//      case _ =>
//    }


    val ne = EPRunner.bootstrap(mainId, pickId, 0,0,
      (() => new EP_A(), List(0)), (() => new EP_B(), 1 to 3),
      (() => new EP_C(), 4 to 5))

    network = ne._1
    eps = ne._2
  }
}
