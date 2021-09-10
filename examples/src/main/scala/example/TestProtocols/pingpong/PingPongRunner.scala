package example.TestProtocols.pingpong

import event_lang.{EndPoint, _}
import example.TestProtocols.pingpong.types.PingPong.A.Main_a_A.{EPMain_a_A, SndPing}
import example.TestProtocols.pingpong.types.PingPong.A._
import example.TestProtocols.pingpong.types.PingPong.B.EPType_B
import example.TestProtocols.pingpong.types.PingPong.B.Main_b_B.{EPMain_b_B, RcvPing}
import example.TestProtocols.pingpong.types.PingPong.MESSAGES.Main.{Ping, Pong}
import example.TestProtocols.pingpong.types.PingPong._
import semantic._
import EndPoint.SpawnMainSelection
import dsl._
import event_lang.types.{Role, RoleSet}
import example.util.NettyEPAddr

import scala.collection.immutable.Queue


object PingPongRunner {
  def main(args: Array[String]): Unit = {

    //    val d = codegen.pingpong.PingPong.B.Main_b_B.T
    //    def aTest(o : Any) = {
    //      println(o.isInstanceOf[semantic.ChannelTypeT])
    //    }
    //    aTest(d)


    val rsA = RoleSet("A")
    val rsB = RoleSet("B")


    new Thread(() => {
      EndPoint.createNettyConnectionBootstrapManger(NettyEPAddr.DEFAULT_BOOTSTRAP_ADDR, rsToIDs => {
        if (rsToIDs.contains(rsA) && rsToIDs.contains(rsB)) {
          Some(SpawnMainSelection(
            Participant(rsToIDs(rsA).head, Role("a", RoleSet("A"))),
            Participant(rsToIDs(rsB).head, Role("b", RoleSet("B")))))
        } else {
          None
        }
      })
    }).start()
    //
    //    val a =
    //    val b = Participant(2, Role("b", RoleSet("B")))
    //    val s = Session(1, Map(a.r -> a, b.r -> b), Map())
    //
    //
    //    val ac = Config(s, a, SndPing, null, in_queue = collection.concurrent.TrieMap(b ->
    //    Queue.empty))
    //    val bc = Config(s, b, RcvPing, null, in_queue = collection.concurrent.TrieMap(a ->
    //    Queue.empty))
    //    val dummyNetwork = DummyNetwork.bootstrap_dummy_network(s, List((a, ac), (b, bc)))
    //


    new Thread(() => {
      println("Start A")
      val ep = EndPoint.createEPusingNetty(
        new EP_A(),
        //EPMain_a_A,
        //A.subs,
        NettyEPAddr.DEFAULT_BOOTSTRAP_ADDR,"127.0.0.1")
      ep.start()
      //      val ep_a =
      //      val epA = new RunningEP(ep_a, ac, dummyNetwork, a, A.subs)
      //      epA.start()
    }).start()

    new Thread(() => {
      println("Start B")
      val ep = EndPoint.createEPusingNetty(
        new EP_B(),
        //EPMain_b_B,
        //B.subs,
        NettyEPAddr.DEFAULT_BOOTSTRAP_ADDR,"127.0.0.1")
      ep.start()
      //      val ep_b = new EP_B()
      //      val epB = new RunningEP(ep_b, bc, dummyNetwork, b, B.subs)
      //      epB.start()
    }).start()


    Thread.sleep(1000)
  }
}