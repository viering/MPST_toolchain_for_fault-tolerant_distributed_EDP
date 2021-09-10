package example.TestProtocols.branchFActivation

import event_lang.network.SessionChannelEP
import event_lang.types.{Role, RoleSet}
import example.TestProtocols.branchFActivation.types.BranchInFActivation.A.Main_a_A.{RcvM1, SndM}
import example.TestProtocols.branchFActivation.types.BranchInFActivation.PROTOCOLS.Main.b_B
import example.util.EPRunner

object Runner {
  def main(args: Array[String]): Unit = {
    import example.util.UIntput._

    var network: SessionChannelEP = null
    val eps = EPRunner.bootstrap(0, 1, 0, 0,
      (() => new EP_A(
        customCode = {
          case (SndM, s, cimpl) =>
            val idB = cimpl.session.roleToPId(b_B)
            println(s"Kill $idB")
            network.signalFail(idB)
          case  _ =>
        },
        block = {
          case (RcvM1, s, cimpl) => true
          case _ => false
        }
      ), List(0)), (() => new EP_B(), List(1, 2,3)))

    network = eps._2(0).network
    eps._3.foreach(_.start())

    eps._3.foreach(_.join())
  }

}
