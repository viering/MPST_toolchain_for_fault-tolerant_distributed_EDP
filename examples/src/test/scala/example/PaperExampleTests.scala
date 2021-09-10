package example

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.network.SessionChannelEP
import event_lang.semantic.OperationalSemantic
import example.util.{EPRunner, VoidOutStream}
import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

import java.util.concurrent.atomic.AtomicInteger
import scala.util.Random

class PaperExampleTests extends AnyFunSuite with TimeLimitedTests {
  val mainId =0
  val pickId = 1
  val timeLimit = Span(60, Seconds)
  override val defaultTestSignaler = new Signaler {
    override def apply(testThread: Thread): Unit = testThread.stop()
  }

  test("2 Buyer") {
    import example.two_buyer.TwoBuyer.Seller.TwoBuyer_s_Seller.{RcvAddr, RcvQuit}
    import example.two_buyer.{Buyer, Seller}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {

        val bookPrice = Random.nextInt(500)
        val buyer_b1 = Random.nextInt(500)
        val buyer_b2 = Random.nextInt(500)
        var finishedTrans = false
        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new Seller(BOOK_ONE_Price = bookPrice, customCode = {
            case (RcvQuit, _, _) => finishedTrans = true
            case (RcvAddr, _, _) => finishedTrans = true
            case _ =>
          }), List(0)),
          (() => new Buyer(b1_max_pay = buyer_b1, b2_max_pay = buyer_b2), 1 to 2))

        ne._3.foreach(_.start())
        ne._3.foreach(_.join())
        assert(finishedTrans)
      }
    }
  }

  test("Streaming") {
    import example.streaming_popl_08.{EP_Consumer, EP_Data, EP_Kernel, EP_Key}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var network: SessionChannelEP = null
        var sigF = false
        val eps = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_Data(customCode = {
            case (_, s, c) if s.num >= 10 && !sigF =>
              network.signalFail(1)
            case _ =>
          }), List(0)), (() => new EP_Key, List(1)),
          (() => new EP_Kernel, 2 to 3), (() => new EP_Consumer, 4 to 5))
        network = eps._2(0).network
        eps._3.foreach(_.start())
        eps._3.foreach(_.join())
      }
    }
  }


  test("Sutherland-Hodgman") {
    import example.dsh.DSHImpl.{CImpl, PImpl, RImpl}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new PImpl(), List(0)), (() => new RImpl(), List(1)), (() => new CImpl(), List(2)))
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
      }
    }
  }
  test("3-Buyers") {
    import example.three_buyer_global_progress.ThreeBuyer.A.threeBuyers_a_A.RcvOkA
    import example.three_buyer_global_progress.ThreeBuyer.B.threeBuyers_b_B.RcvShipD
    import example.three_buyer_global_progress.{EP_A, EP_B, EP_C, EP_S}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1
        var network: LHostSessionChannelEPs = null
        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
        var hdlExCheck = new AtomicInteger(0)


        var curV = 0
        val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
          case (RcvOkA, _, c) =>
            hdlExCheck.incrementAndGet()
          case (RcvShipD, _, _) =>
            hdlExCheck.incrementAndGet()
          case _ =>
        }

        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_A(customCode = customCode), List(0)), (() => new EP_B(customCode = customCode), List(1)),
          (() => new EP_C(customCode = customCode), 2 to 3), (() => new EP_S(customCode = customCode), 4 to 5))

        network = ne._1
        eps = ne._2
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
        assert(hdlExCheck.get() == 2)
      }
    }
  }

  test("N State Pipe") {

    import example.pipe.types.Pipe.A.Pipe_a_A
    import example.pipe.{EP_A, EP_P}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var numPipSpawns = 0
        var network: LHostSessionChannelEPs = null

        val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "Main",
          (() => new EP_A(customCode = {
            case (Pipe_a_A.SndPrepSpawn, _, _) =>
              numPipSpawns += 1
            case _ =>
          }), List(0)),
          (() => new EP_P(), Range(1, 4).toList))
        network = res._1
        res._3.foreach(_.start())
        res._3.foreach(_.join())
        assert(numPipSpawns == 20, s"numPipSpawns ($numPipSpawns) should be 20 ")
      }
    }

  }

  test("Ring") {

    import example.ring.types.Ring.A.Chain_a_A
    import example.ring.{EP_A, EP_W}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var network: LHostSessionChannelEPs = null
        var numSpwans = 0
        val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "Main",
          (() => new EP_A(customCode = {
            case (Chain_a_A.SndSpawn, _, _) => numSpwans += 1
            case _ =>
          }), List(0)),
          (() => new EP_W(), Range(1, 4).toList))
        network = res._1
        res._3.foreach(_.start())
        res._3.foreach(_.join())

        assert(numSpwans == 8, s"numSpawn ($numSpwans) should be 8")
      }
    }
  }

  test("Two Factor") {
    import example.ExceptionalAsynST_POPL.twoFactor.{EP_Client, EP_Server}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var network: LHostSessionChannelEPs = null

        val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "TwoFactor",
          (() => new EP_Server(), List(0)),
          (() => new EP_Client(), List(1)))
        network = res._1
        res._3.foreach(_.start())
        res._3.foreach(_.join())
      }
    }
  }
  test("Resource Control") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        import example.resourceAccessControl._

        var network: LHostSessionChannelEPs = null

        val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "ResAccCon",
          (() => new EP_C(), List(0)),
          (() => new EP_U(), List(1)),
          (() => new EP_A(), List(2)),
          (() => new EP_IOne(), List(3)),
          (() => new EP_ITwo(), List(4, 5)))
        network = res._1
        res._3.foreach(_.start())

        Thread.sleep(2000)
        network.ep_ses_channels(0).signalFail(4)
        Thread.sleep(2500)
        network.ep_ses_channels(0).signalFail(3)
        res._3.foreach(_.join)
      }
    }
  }
  test("WebCrawler") {
    import example.LetItRecover.webcrawler.{EP_D, EP_I, EP_M, EP_P}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var network: LHostSessionChannelEPs = null

        val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "WebCrawler",
          (() => new EP_M(), List(0)),
          (() => new EP_I(), List(1)),
          (() => new EP_D(), List(2, 3)),
          (() => new EP_P(), List(4, 5)))
        network = res._1
        res._3.foreach(_.start())
        res._3.foreach(_.join())
      }
    }
  }
  test("Interruptible 3 Buyer") {
    import example.globalescape.buyer.types.InterruptibleBuyer.Client.{NegotiationCB_c_Client, ThreeBuyers_c_Client}
    import example.globalescape.buyer.{EP_Bank, EP_Client, EP_Interrupt, EP_Seller}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var network: LHostSessionChannelEPs = null
        var shouldInter = false
        var assCnt = 0
        val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "Main",
          (() => new EP_Seller(), List(0)),
          (() => new EP_Client(block = {
            //            case (NegotiationCB_c_Client.RcvOK, _, c) =>
            //              shouldInter
            //            case (NegotiationCB_c_Client.RcvNEM, _, c) =>
            //              shouldInter
            case (ThreeBuyers_c_Client.SndM, _, c) =>
              shouldInter
            case _ => false
          }, customCode = {
            case (NegotiationCB_c_Client.RcvOK, _, c) =>
              assCnt += 1
            case (NegotiationCB_c_Client.RcvNEM, _, c) =>
              assCnt += 1
            case _ =>
            //            case (ThreeBuyers_c_Client.SndM, _, c) =>
            //              shouldInter
          }), List(1)),
          (() => new EP_Bank(), List(2)),
          (() => new EP_Interrupt(), List(3, 4, 5)))
        network = res._1
        res._3.foreach(_.start())

        if (shouldInter) {
          Thread.sleep(2500)
          network.ep_ses_channels(0).signalFail(4)
          shouldInter = false
        }
        res._3.foreach(_.join())
        assert(assCnt == 1, s"assCnt ($assCnt) shoudl be 1")
      }
    }
  }


  test("Interruptible 3 Buyer (one interrupt)") {
    import example.globalescape.buyer.types.InterruptibleBuyer.Client.{NegotiationCB_c_Client, ThreeBuyers_c_Client}
    import example.globalescape.buyer.{EP_Bank, EP_Client, EP_Interrupt, EP_Seller}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var network: LHostSessionChannelEPs = null
        var shouldInter = true
        var assCnt = 0
        val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "Main",
          (() => new EP_Seller(), List(0)),
          (() => new EP_Client(block = {
            //            case (NegotiationCB_c_Client.RcvOK, _, c) =>
            //              shouldInter
            //            case (NegotiationCB_c_Client.RcvNEM, _, c) =>
            //              shouldInter
            case (ThreeBuyers_c_Client.SndM, _, c) =>
              shouldInter
            case _ => false
          }, customCode = {
            case (NegotiationCB_c_Client.RcvOK, _, c) =>
              assCnt += 1
            case (NegotiationCB_c_Client.RcvNEM, _, c) =>
              assCnt += 1
            case (ThreeBuyers_c_Client.RcvBuyerInterToC, _, _) =>
              assCnt += 1
            case _ =>
            //            case (ThreeBuyers_c_Client.SndM, _, c) =>
            //              shouldInter
          }), List(1)),
          (() => new EP_Bank(), List(2)),
          (() => new EP_Interrupt(), List(3, 4, 5)))
        network = res._1
        res._3.foreach(_.start())

        if (shouldInter) {
          Thread.sleep(2500)
          network.ep_ses_channels(0).signalFail(3)
          Thread.sleep(1500)
          shouldInter = false
        }
        res._3.foreach(_.join())
        assert(assCnt == 2, s"assCnt ($assCnt) shoudl be 2")
      }
    }
  }

  test("Failure-Aware Streaming") {
    import example.esop_crash_handling.streaming.types.Streaming.W.Partition_w_W
    import example.esop_crash_handling.streaming.{EP_DFS, EP_W, EP_ZK}
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        var network: LHostSessionChannelEPs = null

        val numKills = new AtomicInteger(0)

        val res = EPRunner.bootstrapWithName(mainId, pickId, 0, 0, "Main",
          (() => new EP_ZK(), List(0)),
          (() => new EP_DFS(), List(1)),
          (() => new EP_W(customCode = {
            case (Partition_w_W.RcvWorkLoad, _, c) =>
              val i = numKills.getAndIncrement()
              if (i == 0) {
                network.ep_ses_channels(0).signalFail(c.session.myId)
              }
            case _ =>
          }), List(2, 3, 4)))
        network = res._1
        res._3.foreach(_.start())
        Thread.sleep(1500)
        network.ep_ses_channels(0).signalFail(pickId)
        res._3.foreach(_.join())
      }
    }
  }
}
