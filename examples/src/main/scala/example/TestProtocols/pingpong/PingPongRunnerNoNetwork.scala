package example.TestProtocols.pingpong

import event_lang._
import example.TestProtocols.pingpong.types.PingPong.A.Main_a_A.{EPMain_a_A, SndPing}
import example.TestProtocols.pingpong.types.PingPong.A._
import example.TestProtocols.pingpong.types.PingPong.B.EPType_B
import example.TestProtocols.pingpong.types.PingPong.B.Main_b_B.{EPMain_b_B, RcvPing}
import example.TestProtocols.pingpong.types.PingPong.MESSAGES.Main.{Ping, Pong}
import example.TestProtocols.pingpong.types.PingPong._
import semantic._
import EndPoint.SpawnMainSelection
import dsl._
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.types.{Role, RoleSet}
import network.SpawnMain

import scala.collection.immutable.Queue

object PingPongRunnerNoNetwork {
  def main(args: Array[String]): Unit = {

    //    val d = codegen.pingpong.PingPong.B.Main_b_B.T
    //    def aTest(o : Any) = {
    //      println(o.isInstanceOf[semantic.ChannelTypeT])
    //    }
    //    aTest(d)

    val roleB = Role("b", RoleSet("B"))
    val roleA = Role("a", RoleSet("A"))
    val rsA = RoleSet("A")
    val rsB = RoleSet("B")


    val mainSes = SpawnMain(0, 0, Map((0 -> roleA), (1 -> roleB)), Map(0 -> rsA, 1 -> rsB),Map())
    val dummyNetwork = new LHostSessionChannelEPs(2, mainSes)




    new Thread(() => {
      println("Start A")
      val ep =EndPoint.createLocalOnlyEP(
        new EP_A(),
        dummyNetwork,
        0
  )
      ep.start()
    }).start()

    new Thread(() => {
      println("Start B")
      val ep =EndPoint.createLocalOnlyEP(
        new EP_B(),
        dummyNetwork,
        1
        )
      ep.start()
    }).start()


    Thread.sleep(10000)
  }
}