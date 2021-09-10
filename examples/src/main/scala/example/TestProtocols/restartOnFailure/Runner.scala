package example.TestProtocols.restartOnFailure

import event_lang.network.SessionChannelEP
import example.TestProtocols.restartOnFailure.types.RestartOnFailure.A.RestartP_a_A.{RcvM1, SndM}
import example.TestProtocols.restartOnFailure.types.RestartOnFailure.PROTOCOLS.RestartP.bb_B
import example.util.EPRunner

object Runner {
  //TODO make to a test case
  def main(args: Array[String]): Unit = {
    val induceNumF = 3
    var numF = 0
    var killIDs = Set[Int]()
    var network: SessionChannelEP = null
    val eps = EPRunner.bootstrap(0, 1, 0, 0,
      (() => new EP_A( numRestart = 2,
        customCode = {
          case (SndM, s, cimpl) if numF < induceNumF =>
            numF += 1
            val idB = cimpl.session.roleToPId(bb_B)
            killIDs += idB
            println(s"Kill $idB, num kills $numF, max kills $induceNumF")
            network.signalFail(idB)
          case  _ =>
        },
        block = {
          case (RcvM1, s, cimpl) =>
            val rcvID = cimpl.session.roleToPId(bb_B)
            killIDs.contains(rcvID)
          case _ => false
        }
      ), List(0)), (() => new EP_B(), List(1, 2,3,4,5,6)))

    network = eps._2(0).network
    eps._3.foreach(_.start())

    eps._3.foreach(_.join())
  }

}
