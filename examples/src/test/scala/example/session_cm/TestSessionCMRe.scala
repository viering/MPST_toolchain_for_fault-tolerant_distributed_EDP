package example.session_cm

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import example.sparkCluster.{SimpleSumDriver}
import example.sparkCluster.SparkCM.M.{GStartEx_m_M, __EPType_M}
import example.sparkCluster.SparkCM.W.GSel_w_W.RcvLaunchDriver
import example.sparkCluster.SparkCM.W.GStartEx_wEx_W.SndExDone
import example.sparkCluster.SparkCM.W.__EPType_W
import example.sparkCluster.reimpl._
import example.util.{EPRunner, VoidOutStream}
import org.scalatest.concurrent.{Signaler, TimeLimitedTests, TimeLimits}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

import java.util.concurrent.atomic.AtomicInteger
import scala.util.Random

class TestSessionCMRe extends AnyFunSuite with TimeLimitedTests with TimeLimits{

  val timeLimit = Span(240, Seconds)

  override val defaultTestSignaler = new Signaler {
    override def apply(testThread: Thread): Unit = testThread.stop()
  }

  def sumDResult(numTask: Int): Int = {
    val n = numTask * 1000 - 1
    n * (n + 1) / 2
  }

  test("CM Reimple: Normal Execution; One Application to run") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1
        var network: LHostSessionChannelEPs = null
        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
        var gotCalcResutl = false

        val sState= StateM(GMainState(Seq(new SimpleSumDriver(2, x => {
          assert(x == sumDResult(2))
          gotCalcResutl = true
        }))))

        val cusCodeW: (__EPType_W, StateW, AbstractChannelImp) => Unit = {
          case (RcvLaunchDriver, s, c) =>
          case _ =>
        }
        val blockW: (__EPType_W, StateW, AbstractChannelImp) => Boolean = {
          case (SndExDone, s, c) => false
          case _ => false
        }

        val cusCodeM: (__EPType_M, StateM, AbstractChannelImp) => Unit = {
          case (GStartEx_m_M.RcvExStarted, s, c) =>
          case _ => {}
        }
        val blockM: (__EPType_M, StateM, AbstractChannelImp) => Boolean = {
          case (_, _, _) => false
          case _ => false
        }

        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_ZK(), List(0)),
          (() => new EP_Master(sState = sState, customCode = cusCodeM, block = blockM), List(1)),
          (() => new EP_Worker(customCode = cusCodeW, block = blockW), List(2, 3))
        )
        network = ne._1
        eps = ne._2
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
        while (!gotCalcResutl) {
          Thread.sleep(50)
        }
      }
    }
  }

  test("CM Reimple: Normal Execution; 2 Application to run") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1
        var network: LHostSessionChannelEPs = null
        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
        val numCalcResults = new AtomicInteger(0)

        val sState= StateM(GMainState(Seq(
          new SimpleSumDriver(2, x => {
            assert(x == sumDResult(2))
            numCalcResults.incrementAndGet()
          }),
          new SimpleSumDriver(4, x => {
            assert(x == sumDResult(4))
            numCalcResults.incrementAndGet()
          })
        )))

        val cusCodeW: (__EPType_W, StateW, AbstractChannelImp) => Unit = {
          case (RcvLaunchDriver, s, c) =>
          case _ =>
        }
        val blockW: (__EPType_W, StateW, AbstractChannelImp) => Boolean = {
          case (SndExDone, s, c) => false
          case _ => false
        }

        val cusCodeM: (__EPType_M, StateM, AbstractChannelImp) => Unit = {
          case (GStartEx_m_M.RcvExStarted, s, c) =>
          case _ => {}
        }
        val blockM: (__EPType_M, StateM, AbstractChannelImp) => Boolean = {
          case (_, _, _) => false
          case _ => false
        }

        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_ZK(), List(0)),
          (() => new EP_Master(sState = sState, customCode = cusCodeM, block = blockM), List(1)),
          (() => new EP_Worker(customCode = cusCodeW, block = blockW), List(2, 3, 4, 5, 6))
        )
        network = ne._1
        eps = ne._2
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
        failAfter(Span(5, Seconds)){
        while (numCalcResults.get() < 2) {
          Thread.sleep(50)
        }
        assert(numCalcResults.get()==2)
      }}
    }
  }


  test("CM Reimple: Normal Execution; N (random) Application to run") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1
        var network: LHostSessionChannelEPs = null
        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
        val numCalcResults = new AtomicInteger(0)


        val N = Random.nextInt(5) + 1

        val driver: Seq[SimpleSumDriver] = for (i <- 1 to N) yield {
          val numT = Random.nextInt(4) + 1
          new SimpleSumDriver(numT, x => {
            assert(x == sumDResult(numT))
            numCalcResults.incrementAndGet()
          })
        }

        val sState= StateM(GMainState(driver))
        val cusCodeW: (__EPType_W, StateW, AbstractChannelImp) => Unit = {
          case (RcvLaunchDriver, s, c) =>
          case _ =>
        }
        val blockW: (__EPType_W, StateW, AbstractChannelImp) => Boolean = {
          case (SndExDone, s, c) => false
          case _ => false
        }

        val cusCodeM: (__EPType_M, StateM, AbstractChannelImp) => Unit = {
          case (GStartEx_m_M.RcvExStarted, s, c) =>
          case _ => {}
        }
        val blockM: (__EPType_M, StateM, AbstractChannelImp) => Boolean = {
          case (_, _, _) => false
          case _ => false
        }

        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_ZK(), List(0)),
          (() => new EP_Master(sState = sState, customCode = cusCodeM, block = blockM), List(1)),
          (() => new EP_Worker(customCode = cusCodeW, block = blockW), List(2, 3, 4, 5, 6))
        )
        network = ne._1
        eps = ne._2
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
        failAfter(Span(5, Seconds)){
        while (numCalcResults.get() < N) {
          Thread.sleep(50)
        }
        assert(numCalcResults.get()==N)
      }}
    }
  }

  test("CM Reimple: Normal Execution; N (rnd) Application and M (rnd) worker") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1
        var network: LHostSessionChannelEPs = null
        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
        val numCalcResults = new AtomicInteger(0)


        val N = Random.nextInt(5) + 1
        val M = 5 + Random.nextInt(5)

        val driver: Seq[SimpleSumDriver] = for (i <- 1 to N) yield {
          val numT = Random.nextInt(4) + 1
          new SimpleSumDriver(numT, x => {
            assert(x == sumDResult(numT))
            numCalcResults.incrementAndGet()
          })
        }

        val sState= StateM(GMainState(driver))

        val cusCodeW: (__EPType_W, StateW, AbstractChannelImp) => Unit = {
          case (RcvLaunchDriver, s, c) =>
          case _ =>
        }
        val blockW: (__EPType_W, StateW, AbstractChannelImp) => Boolean = {
          case (SndExDone, s, c) => false
          case _ => false
        }

        val cusCodeM: (__EPType_M, StateM, AbstractChannelImp) => Unit = {
          case (GStartEx_m_M.RcvExStarted, s, c) =>
          case _ => {}
        }
        val blockM: (__EPType_M, StateM, AbstractChannelImp) => Boolean = {
          case (_, _, _) => false
          case _ => false
        }

        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_ZK(), List(0)),
          (() => new EP_Master(sState = sState, customCode = cusCodeM, block = blockM), List(1)),
          (() => new EP_Worker(customCode = cusCodeW, block = blockW), 2 to M)
        )
        network = ne._1
        eps = ne._2
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
        failAfter(Span(5, Seconds)){
        while (numCalcResults.get() < N) {
          Thread.sleep(50)
        }
        assert(numCalcResults.get() == N)

      }}
    }
  }

//
//  test("CM Reimple: Kill one rnd worker - one application") {
//    for (i <- 0 to 12) {
//      println(s"Kill one rnd Worker on step: $i")
//      Console.withOut(new VoidOutStream) {
//        Console.withErr(new VoidOutStream) {
//          val mainId = 0
//          val pickId = 1
//          var network: LHostSessionChannelEPs = null
//          var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
//          var gotCalcResutl = false
//          val killOnStep = i
//          val killId = Random.nextInt(2) + 2
//          var ne: (LHostSessionChannelEPs, Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) = null
//
//           val sState= StateM(GMainState(Seq(new SimpleSumDriver(2, x => {
//            assert(x == sumDResult(2))
//            gotCalcResutl = true
//          }))))
//
//          val cusCodeW: (__EPType_W, StateW, AbstractChannelImp) => Unit = {
//            case (RcvLaunchDriver, s, c) =>
//            case _ =>
//          }
//          val blockW: (__EPType_W, StateW, AbstractChannelImp) => Boolean = {
//            case (SndExDone, s, c) => false
//            case _ => false
//          }
//
//          var cnt = 0
//          val cusCodeM: (__EPType_M, StateM, AbstractChannelImp) => Unit = {
//            case (_, s, c) =>
//              if (cnt == killOnStep) {
//                ne._3(killId).stop()
//                network.ep_ses_channels(0).signalFail(killId)
//              }
//              cnt += 1
//          }
//          val blockM: (__EPType_M, StateM, AbstractChannelImp) => Boolean = {
//            case (_, _, _) => false
//            case _ => false
//          }
//
//          ne = EPRunner.bootstrap(mainId, pickId, 0, i,
//            (() => new EP_ZK(), List(0)),
//            (() => new EP_Master(sState = sState, customCode = cusCodeM, block = blockM), List(1)),
//            (() => new EP_Worker(customCode = cusCodeW, block = blockW), List(2, 3, 4))
//          )
//          network = ne._1
//          eps = ne._2
//          ne._3.foreach(_.start())
//          ne._3.foreach(_.join)
//          failAfter(Span(5, Seconds)){
//          while (!gotCalcResutl) {
//            Thread.sleep(50)
//            println(".")
//          }
//        }}
//      }
//    }
//  }
//
//
//  test("CM Reimple: Kill 2 rnd worker - 2 application") {
//    for (killOnStep1 <- 0 to 12) {
//      System.gc()
//      println(s"Num Threads: ${Thread.getAllStackTraces.keySet().size()}, ${Thread.activeCount()}")
//
//      for (killOnStep2 <- killOnStep1 to 12) {
//        Thread.sleep(100)
//        println(s"Kill 2 rnd worker on steps: $killOnStep1 and $killOnStep2")
//
//        val mainId = 0
//        val pickId = 1
//        var network: LHostSessionChannelEPs = null
//        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
//        var gotCalcResutl = false
//        val workIds = 2 to 5
//
//        val killId1 = workIds(Random.nextInt(workIds.size))
//        val killId2 = workIds(Random.nextInt(workIds.size))
//        assert(killId1 > 1 && killId2 > 1)
//        var ne: (LHostSessionChannelEPs, Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) = null
//        val numCalcResults = new AtomicInteger(0)
//         val sState= StateM(GMainState(Seq(
//          new SimpleSumDriver(2, x => {
//            assert(x == sumDResult(2))
//            numCalcResults.incrementAndGet()
//          }),
//          new SimpleSumDriver(3, x => {
//            assert(x == sumDResult(3))
//            numCalcResults.incrementAndGet()
//          }))))
//
//        val cusCodeW: (__EPType_W, StateW, AbstractChannelImp) => Unit = {
//          case (RcvLaunchDriver, s, c) =>
//          case _ =>
//        }
//        val blockW: (__EPType_W, StateW, AbstractChannelImp) => Boolean = {
//          case (SndExDone, s, c) => false
//          case _ => false
//        }
//
//        var cnt = 0
//        val cusCodeM: (__EPType_M, StateM, AbstractChannelImp) => Unit = {
//          case (_, s, c) =>
//            if (cnt == killOnStep1) {
//              //              ne._3(killId1).stop()
//              ne._2(killId1).forceStop()
//              network.ep_ses_channels(0).signalFail(killId1)
//            } else if (cnt == killOnStep2 && killId1 != killId2) {
//              //              ne._3(killId2).stop()
//              ne._2(killId2).forceStop()
//              network.ep_ses_channels(0).signalFail(killId2)
//            }
//            cnt += 1
//        }
//        val blockM: (__EPType_M, StateM, AbstractChannelImp) => Boolean = {
//          case (_, _, _) => false
//          case _ => false
//        }
//        Console.withOut(new VoidOutStream) {
//          Console.withErr(new VoidOutStream) {
//            ne = EPRunner.bootstrap(mainId, pickId, 0, killOnStep1 * 100 + killOnStep2,
//              (() => new EP_ZK(), List(0)),
//              (() => new EP_Master(sState = sState, customCode = cusCodeM, block = blockM), List(1)),
//              (() => new EP_Worker(customCode = cusCodeW, block = blockW), workIds)
//            )
//            network = ne._1
//            eps = ne._2
//
//            //          println(s"@Start: ${Thread.getAllStackTraces.keySet().size()}, ${Thread.activeCount()}")
//
//            ne._3.foreach(_.start())
//          }
//        }
//        //          println(s"@join: ${Thread.getAllStackTraces.keySet().size()}, ${Thread.activeCount()}")
//        ne._3.foreach(_.join)
//        ne._3.foreach(_.stop())
//        //          println(s"@end: ${Thread.getAllStackTraces.keySet().size()}, ${Thread.activeCount()}")
//        failAfter(Span(5, Seconds)){
//        while (numCalcResults.get() < 2) {
//          Thread.sleep(50)
//        }}
//
//        assert(numCalcResults.get() == 2)
//      }
//    }
//  }
//
//  test("CM Reimple: Kill the master") {
//    for (i <- 1 to 18) {
//      println(s"Kill the master on action: $i")
//      println(s"Num Threads: ${Thread.getAllStackTraces.keySet().size()}, ${Thread.activeCount()}")
//      Console.withOut(new VoidOutStream) {
//        Console.withErr(new VoidOutStream) {
//          val mainId = 0
//          val pickId = 1
//          var network: LHostSessionChannelEPs = null
//          var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
//          var gotCalcResutl = false
//          val killOnStep = i
//          val killId = 1
//          var ne: (LHostSessionChannelEPs, Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) = null
//
//          val sState= StateM(GMainState(Seq(new SimpleSumDriver(2, x => {
//            assert(x == sumDResult(2))
//            gotCalcResutl = true
//          }))))
//          var cnt = new AtomicInteger(0)
//          val cusCodeW: (__EPType_W, StateW, AbstractChannelImp) => Unit = {
//            case (_, _, c) =>
//              if (cnt.incrementAndGet() == killOnStep) {
//                ne._3(killId).stop()
//                network.ep_ses_channels(c.session.myId).signalFail(killId)
//              }
//          }
//          val blockW: (__EPType_W, StateW, AbstractChannelImp) => Boolean = {
//            case (SndExDone, s, c) => false
//            case _ => false
//          }
//
//
//          val cusCodeM: (__EPType_M, StateM, AbstractChannelImp) => Unit = {
//            case (_, s, c) =>
//          }
//          val blockM: (__EPType_M, StateM, AbstractChannelImp) => Boolean = {
//            case (_, _, _) => false
//            case _ => false
//          }
//
//          ne = EPRunner.bootstrap(mainId, pickId, 0, i,
//            (() => new EP_ZK(), List(0)),
//            (() => new EP_Master(sState = sState, customCode = cusCodeM, block = blockM), List(1)),
//            (() => new EP_Worker(customCode = cusCodeW, block = blockW), List(2, 3, 4))
//          )
//          network = ne._1
//          eps = ne._2
//          ne._3.foreach(_.start())
//          ne._3.foreach(_.join)
//
//          Thread.sleep(50)
//        }
//      }
//    }
//  }

}
