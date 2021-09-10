package example.frm_paper.ring.failure_handling

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import event_lang.types.{Role, RoleSet}
import example.frm_paper.ring.failure_handling.generated.RingABC.B.g1_b_B.{RcvL7, SndL2, SndL8}
import example.frm_paper.ring.failure_handling.generated.RingABC.C.g1_c_C.RcvL2
import example.util.EPRunner

object RingRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1

    import example.util.UIntput._



    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null


    val block: (AbstractChannelType, StateC, AbstractChannelImp) => Boolean = {
      case (RcvL2, _, _) => true
      case _ => false
    }
    val customCode: (AbstractChannelType, StateB, AbstractChannelImp) => Unit = {
      case (SndL2, s,c) =>
        val epA = eps.find(_.myID == mainId).get
        val idC = c.session.roleToPId(Role("c", RoleSet("C")))
        network.ep_ses_channels(0).signalFail(idC)
      case (RcvL7, s, _) =>
        network.ep_ses_channels(0).signalFail(2)
        network.ep_ses_channels(0).signalFail(3)
      case _ =>
    }


    val ne = EPRunner.bootstrap(mainId, pickId, 0,0,
      (() => new EP_A(), List(0)), (() => new EP_B(customCode = customCode), List(1)),
      (() => new EP_C(block), 2 to 3))

    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())

  }
}
