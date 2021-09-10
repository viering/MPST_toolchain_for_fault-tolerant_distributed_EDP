package example.frm_paper.ring.fault_tolerant

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import example.TestUtil._
import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.A._
import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.B._
import example.frm_paper.ring.fault_tolerant.generated.RingFaultTolerant.C._
import example.util.{EPRunner, VoidOutStream}
import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

import java.util.concurrent.atomic.AtomicInteger
import scala.util.Random

class RingFTTest extends AnyFunSuite with TimeLimitedTests {

  val timeLimit = Span(30, Seconds)
  override val defaultTestSignaler = new Signaler {
    override def apply(testThread: Thread): Unit = testThread.stop()
  }

  test("Dummy") {
    val mainId = 0
    val pickId = 1

    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    val hdlExe = new AtomicInteger(0)


    val block: (AbstractChannelType, TState, AbstractChannelImp) => Boolean = {
      case _ => false
    }
    val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      //all A
      case (Main_a_A.SndL1, _, _) =>
      case (Main_a_A.Failed_b_B, _, _) =>
      case (g1_a_A.RcvL3, _, _) =>
      case (g1_a_A.Failed_c_C, _, _) =>
      //all B
      case (Main_b_B.RcvL1, _, _) =>
      case (Main_B.RcvLFMain1, _, _) =>
      case (g1_b_B.SndL2, _, _) =>
      case (g1_b_B.RcvLFg2, _, _) =>
      //all C
      case (Main_C.RcvLFMain2, _, _) =>
      case (g1_c_C.RcvL2, _, _) =>
      case (g1_C.RcvLFg3, _, _) =>
      //case _ =>
      case _ => assert(false)
    }

    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new EP_A(block, customCode), List(0)), (() => new EP_B(block, customCode), 1 to 3),
      (() => new EP_C(block, customCode), 4 to 6))

    network = ne._1
    eps = ne._2

  }

  test("Normal Run") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1

        var network: LHostSessionChannelEPs = null
        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
        val hdlExe = new AtomicInteger(0)


        val block: (AbstractChannelType, TState, AbstractChannelImp) => Boolean = {
          case (_) => false
        }
        val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
          //all A
          case (Main_a_A.SndL1, _, _) => assert(hdlExe.incrementAndGet() == 1)
          case (Main_a_A.Failed_b_B, _, _) => assert(false)
          case (g1_a_A.RcvL3, _, _) => assert(hdlExe.incrementAndGet() == 5)
          case (g1_a_A.Failed_c_C, _, _) => assert(false)
          //all B
          case (Main_b_B.RcvL1, _, _) => assert(hdlExe.incrementAndGet() == 2)
          case (Main_B.RcvLFMain1, _, _) => assert(false)
          case (g1_b_B.SndL2, _, _) => assert(hdlExe.incrementAndGet() == 3)
          case (g1_b_B.RcvLFg2, _, _) => assert(false)
          //all C
          case (Main_C.RcvLFMain2, _, _) => assert(false)
          case (g1_c_C.RcvL2, _, _) => assert(hdlExe.incrementAndGet() == 4)
          case (g1_C.RcvLFg3, _, _) => assert(false)
          //case _ =>
          case _ => assert(false)
        }

        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_A(block, customCode), List(0)), (() => new EP_B(block, customCode), 1 to 3),
          (() => new EP_C(block, customCode), 4 to 6))

        network = ne._1
        eps = ne._2
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
        assert(hdlExe.incrementAndGet() == 6)

      }
    }
  }

  test("Fail of b in Main") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1

        var network: LHostSessionChannelEPs = null
        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
        val hdlExe = new AtomicInteger(0)


        val block: (AbstractChannelType, TState, AbstractChannelImp) => Boolean = {
          case (Main_b_B.RcvL1, _, _) if hdlExe.get() < 7 => true
          case _ => false
        }
        val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
          //all A
          case (Main_a_A.SndL1, _, c) =>
            val v = hdlExe.incrementAndGet()
            assert(v == 1 || v == 8)
            val bId = c.session.roleToPId(role_b)
            if (v == 1) network.ep_ses_channels(0).signalFail(bId)
          case (Main_a_A.Failed_b_B, _, _) => //b@a !B!C
            assert(hdlExe.incrementAndGet() == 2)
          case (g1_a_A.RcvL3, _, _) =>
          //assert(hdlExe.incrementAndGet() == 5)
          case (g1_a_A.Failed_c_C, _, _) => assert(false)
          //all B
          case (Main_b_B.RcvL1, _, _) =>
          case (Main_B.RcvLFMain1, _, _) =>
            assert(hdlExe.incrementAndGet() <= 7)

          case (g1_b_B.SndL2, _, _) =>
            assert(hdlExe.incrementAndGet() == 9)
          case (g1_b_B.RcvLFg2, _, _) => assert(false)
          //all C
          case (Main_C.RcvLFMain2, _, _) =>
            assert(hdlExe.incrementAndGet() <= 7)
          case (g1_c_C.RcvL2, _, _) =>
            assert(hdlExe.incrementAndGet() == 10)
          case (g1_C.RcvLFg3, _, _) => assert(false)
          //case _ =>
          case _ => assert(false)
        }

        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_A(block, customCode), List(0)), (() => new EP_B(block, customCode), 1 to 3),
          (() => new EP_C(block, customCode), 4 to 6))

        network = ne._1
        eps = ne._2
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
        assert(hdlExe.incrementAndGet() == 11)
      }
    }
  }

  test("Fail in g1 twice") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1

        var network: LHostSessionChannelEPs = null
        var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
        val hdlExe = new AtomicInteger(0)


        var blkG1_one = true
        var blkG1_two = true

        val block: (AbstractChannelType, TState, AbstractChannelImp) => Boolean = {
          case (g1_a_A.RcvL3, _, _) if blkG1_one || blkG1_two => true
          //case Main_b_B.RcvL1 if hdlExe.get() < 7 => true
          case _ => false
        }
        val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
          //all A
          case (Main_a_A.SndL1, _, _) =>
          case (Main_a_A.Failed_b_B, _, _) => //b@a !B!C

          case (g1_b_B.SndL2, _, c) =>
            if (blkG1_one || blkG1_two) {
              val bId = c.session.roleToPId(role_c)
              network.ep_ses_channels(0).signalFail(bId)
            }

          case (g1_b_B.RcvLFg2, _, _) => {
            if (blkG1_one) blkG1_one = false
            else if (!blkG1_one && blkG1_two) blkG1_two = false
          }
          case _ =>
        }

        val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
          (() => new EP_A(block, customCode), List(0)), (() => new EP_B(block, customCode), 1 to 3),
          (() => new EP_C(block, customCode), 4 to 6))

        network = ne._1
        eps = ne._2
        ne._3.foreach(_.start())
        ne._3.foreach(_.join)
        assert(!blkG1_one && !blkG1_two)
      }
    }
  }


  test("Termination under rnd Failures") {
    Console.withOut(new VoidOutStream) {
      Console.withErr(new VoidOutStream) {
        val mainId = 0
        val pickId = 1

        for (i <- 0 to 10) {
          import java.util.concurrent.locks.ReentrantLock
          val lock: ReentrantLock = new ReentrantLock
          lock.lock()

          var network: LHostSessionChannelEPs = null
          var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
          var ne: (LHostSessionChannelEPs, Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) = null
          var cnt = 0
          val toFail = for (i <- 1 to 3) yield {
            cnt = cnt + Random.nextInt(2) + 1
            (cnt, Random.nextInt(5) + 1)
          }
          val hdlExe = new AtomicInteger(0)
          val block: (AbstractChannelType, TState, AbstractChannelImp) => Boolean = {
            case _ => false
          }

          def doFailure(myId: Int): Unit = {
            lock.lock()
            assert(eps != null)
            Thread.sleep(50)
            val aC = hdlExe.getAndIncrement()
            toFail.find(_._1 == aC) match {
              case Some(d) if myId != d._2 =>
                val idx = eps.indexWhere(_.myID == d._2)
                //                  ne._3(idx).stop()
                eps(idx).forceStop()
                network.ep_ses_channels(0).signalFail(d._2)
              case _ =>
            }
            lock.unlock()
          }

          val customCode: (AbstractChannelType, TState, AbstractChannelImp) => Unit = {

            //all A
            case (Main_a_A.SndL1, _, c) => doFailure(c.session.myId)
            case (Main_a_A.Failed_b_B, _, c) => doFailure(c.session.myId)
            case (g1_a_A.RcvL3, _, c) => doFailure(c.session.myId)
            case (g1_a_A.Failed_c_C, _, c) => doFailure(c.session.myId)
            //all B
            case (Main_b_B.RcvL1, _, c) => doFailure(c.session.myId)
            case (Main_B.RcvLFMain1, _, c) => doFailure(c.session.myId)
            case (g1_b_B.SndL2, _, c) => doFailure(c.session.myId)
            case (g1_b_B.RcvLFg2, _, c) => doFailure(c.session.myId)
            //all C
            case (Main_C.RcvLFMain2, _, c) => doFailure(c.session.myId)
            case (g1_c_C.RcvL2, _, c) => doFailure(c.session.myId)
            case (g1_C.RcvLFg3, _, c) => doFailure(c.session.myId)
            //case _ =>
            case _ => assert(false)
          }

          ne = EPRunner.bootstrap(mainId, pickId, 0, i,
            (() => new EP_A(block, customCode), List(0)), (() => new EP_B(block, customCode), 1 to 4),
            (() => new EP_C(block, customCode), 5 to 8))

          network = ne._1
          eps = ne._2
          assert(eps != null)
          lock.unlock()
          ne._3.foreach(_.start())
          ne._3.foreach(_.join)
        }
      }
    }
  }


}

