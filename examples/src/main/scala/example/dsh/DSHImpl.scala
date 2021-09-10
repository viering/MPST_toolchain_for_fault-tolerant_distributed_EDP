package example.dsh

import event_lang.{EndPoint, _}
import EndPoint.SpawnMainSelection
import dsl._
import example.dsh.DSH.{PROTOCOLS, RS}
import example.dsh.DSHImpl.{CImpl, PImpl, RImpl}
import example.util.EPRunner
import semantic.Participant
import types.RoleSet

import scala.collection.mutable
import scala.util.Random




/*
  This implements the DSH protocol but does not provide the semantic of DSH
 */

object DSH_Runner {
  def main(args: Array[String]): Unit = {

    val mainId = 0
    val pickId = 1


    println(s"Execute the DSH protocol (we did not implemented the semantic of DSH, i.e., we only perform the messages defined by the DSH protocol)")


    val ne = EPRunner.bootstrap(mainId, pickId, 0,0,
      (() => new PImpl(), List(0)), (() => new RImpl(), List(1)),(() => new CImpl(), List(2)))//(example.Constants.DEFAULT_BOOTSTRAP_ADDR)
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
   // System.exit(0)



  }
}

/**
  * An implementation variant of DSH as presented in
  * A Session TypeProvider (CC'18)
  *
  * The communication patter follows the above paper.
  * We only consider a 2 D space with vertical and horizontal lines
  */
object DSHImpl {

  import DSH._


  class StateC(val allPs: mutable.Buffer[(Int, Int)] = mutable.Buffer(),
               val outPs: mutable.Buffer[(Int, Int)] = mutable.Buffer(),
               val inPs: mutable.Buffer[(Int, Int)] = mutable.Buffer()) extends TState

  class CImpl extends C.EPType_C[ StateC] {
    override val subs: Seq[ChannelTypeSubS] = C.subs
    override val roleSet: RoleSet = RS.C

    override def onStartUp: StateC = new StateC()


    import C.{Main_C => main, SH_c_C => sh}


    override val receive: Seq[HDL[StateC]] = ELoop(
      λ(main.RcvF) {
        case c => c ? {
          case (m,c2)=>
          println(s"[C] rcv: $m ")
          c2}
      },
      registerStatic(main.SpawnSH) {
        case s =>
          println(s"Spawn SH")
          s
      },

      λ(sh.RcvClosePC) {
        case c => c ? {
          case (m,c2)=>
          println(s"[C] rcv: $m ")
         c2}
      },

      λ_state(sh.RcvBothInC) {
        case (s, c) => c ? {
          case (m,c2)=>
          println(s"[C] rcv: $m ")
          s.allPs += ((m.x2, m.y2))
          s.inPs += ((m.x2, m.y2))
          (s, c2)}
        },
      λ(sh.RcvBothOutTwo) {
        case c => c ? {
          case (m,c2)=>
          println(s"[C] rcv: $m ")
          c2
        }
      },

      λ_state(sh.RcvSecIn) {
        case (s,c) => c ? {
          case (m,c2)=>
          println(s"[C] rcv: $m ")
          s.allPs += ((m.x1, m.y1))
          s.allPs += ((m.x2, m.y2))
          s.inPs += ((m.x1, m.y1))
          s.inPs += ((m.x2, m.y2))
          (s, c2)
        }
      },

      λ_state(sh.RcvSecOut) {
        case (s,c) => c ? {
          case (m,c2)=>
          println(s"[C] rcv: $m ")
          s.allPs += ((m.x, m.y))
          s.outPs += ((m.x, m.y))
          (s, c2)
        }
      },

      )
  }


  case class StateR() extends TState

  class RImpl extends R.EPType_R[StateR] {
    override val subs: Seq[ChannelTypeSubS] = R.subs
    override val roleSet: RoleSet = RS.R

    override def onStartUp: StateR = StateR()


    import MESSAGES.SH._
    import R.{Main_r_R => main, SH_r_R => sh}

    val LINE_Y = 2

    def isAbove(x: Int, y: Int): Boolean = y >= LINE_Y

    def calcIntrsctPoint(x1: Int, y1: Int, x2: Int, y2: Int): (Int, Int) = {
      def calc(x: Int, y: Int): (Int, Int) = {
        (x, LINE_Y)
      }

      if (isAbove(x1, y2)) {
        calc(x2, y2)
      } else {
        calc(x1, y1)
      }
    }


    override val receive: Seq[HDL[StateR]] = ELoop(
      registerStatic(main.SpawnSH) {
        case s =>
          println(s"Spawn SH")
          s
      },

      λ(sh.RcvClosePR) {
        case c => c ? {
          case (m,c2)=>
          println(s"[R] rcv: $m ")
          c2}
      },
      λ(sh.RcvIsAbove, sh.SndRes) {
        case c => c ? {
          case (m,c2)=>
          println(s"[R] rcv: $m ")
         c2 ! (Res(isAbove(m.x, m.y)))}
      },

      λ(sh.RcvIsAboveSec, sh.SndResSec) {
        case c => c ? {
          case (m,c2)=>
          println(s"[R] rcv: $m ")
          c2 ! ResSec(isAbove(m.x, m.y))}
      },

      λ(sh.RcvBothInR) {
        case c => c ? {
          case (m,c2)=>
          println(s"[R] rcv: $m ")
          c2}
      },

      λ(sh.RcvBothOut) {
        case c => c ? {
          case (m,c2)=>
          println(s"[R] rcv: $m ")
          c2}
      },

      λ(sh.RcvIntrsct, sh.SndResIntrsct) {
        case c => c ? {
          case (m, c2) =>
            val res = calcIntrsctPoint(m.x1, m.y1, m.x2, m.y2)
            c2 ! ResIntrsct(res._1, res._2)
        }
      },

      λ(sh.RcvFM) {
        case c => c ? {
          case (m,c2)=>
          println(s"[R] rcv: $m ")
          c2}
      }


      )
  }

  case class StateP(points: List[(Int, Int)], p1Above: Boolean = false, p2Above: Boolean = false, numIter : Int = 0)
    extends TState {
    assert(points.isEmpty || points.size % 2 == 0)

    //def tail: StateP = this.copy(points = points.tail)

  }

  class PImpl extends P.EPType_P[StateP] {
    override val subs: Seq[ChannelTypeSubS] = P.subs
    override val roleSet: RoleSet = RS.P

    override def onStartUp: StateP = StateP(
      (1, 5) :: (1, 1) :: (2, 1) :: (2, 3) :: (3, 3) :: (3, 4)
        :: (4, 4) :: (4, 0) :: (5, 0) :: (5, 5) :: Nil)


    import MESSAGES.Main._
    import MESSAGES.SH._
    import P.{Main_p_P => main, SH_p_P => sh}


    override val receive = ELoop(
      /**
        * Main
        */
      register(main.Failed_r_R, main.SndF) {
        case (s, c) =>
          println(s"Noticed failure of r in Main session")
          (s, c.failed_r_R().sndToC(F()))
      },
      // Not impl spawn

      /**
        * SH
        */
      register(sh.SndIsAbove) {
        case (s@StateP(p1 :: p2 :: tail, _, _,_), c) if s.numIter < 10 =>
          (s.copy(numIter = s.numIter + 1), c.sndTor_R(IsAbove(p1._1, p1._2)))

      },
      λ_state(sh.RcvRes, sh.SndIsAboveSec) {
        case (s,c) => c ? {
          case (m,c2)=>
          println(s"[p] rcv: $m")
          (s.copy(p1Above = m.b), c2 ! (IsAboveSec(s.points.tail.head._1, s.points.tail.head._2)))}
      },


//      register(sh.RcvRes, sh.SndIsAboveSec) {
//        case (s, c)  =>
//          c.rcvFrmr_R().
//          println(s"We have: ${s.points.head} above? ${s.p1Above} and" +
//                    s" ${s.points.tail.head} above? ${s.p2Above}")
//          (s, c.sndTor_R(BothOut()).sndToc_C(BothOutTwo()))
//      },

      λ_state(sh.RcvResSec) {
        case (s,c) => c ? {
          case (m,c2)=>
          println(s"[p] rcv: $m")
          (s.copy(p2Above = m.b), c2)}
      },

      //p -> r {BothIn, BothOUt, Intrse}
      //register(sh.Snd)
      register(sh.SndBothInR, sh.SndBothInC) {
        case (s, c) if s.p1Above && s.p2Above =>
          println(s"We have: ${s.points.head} above? ${s.p1Above} and" +
                    s" ${s.points.tail.head} above? ${s.p2Above}")
          val p2 = s.points.tail.head
          (s,
            c.sndTor_R(BothInR()).
              sndToc_C(BothInC(p2._1, p2._2)))
      },

      register(sh.SndBothOut, sh.SndBothOutTwo) {
        case (s, c) if !s.p1Above && !s.p2Above =>
          println(s"We have: ${s.points.head} above? ${s.p1Above} and" +
                    s" ${s.points.tail.head} above? ${s.p2Above}")
          (s, c.sndTor_R(BothOut()).sndToc_C(BothOutTwo()))
      },


      register(sh.SndIntrsct) {
        case (s, c) if s.p1Above ^ s.p2Above =>
          val p1 = s.points.head
          val p2 = s.points.tail.head
          (s, c.sndTor_R(Intrsct(p1._1, p1._2, p2._1, p2._2)))
      },

      λ_state(sh.RcvResIntrsct, sh.SndSecOut) {
        case (s,c) if !s.p2Above => c ? {
          case (m,c2)=>
          println(s"[p] rcv $m")
          (s.copy(s.points.tail.tail), c2 ! SecOut(m.x1, m.y1))
        }
      },
      λ_state(sh.RcvResIntrsct, sh.SndSecIn) {
        case (s,c) if s.p2Above => c ? {
          case (m,c2)=>
          println(s"[p] rcv $m")
          val v2 = s.points.tail.head
          (s.copy(s.points.tail.tail), c2 ! SecIn(m.x1, m.y1, v2._1, v2._2))}
      },

      register(sh.SndClosePR, sh.SndClosePC) {
        case (s, c) => // if s.points.isEmpty =>
          (s, c.sndTor_R(ClosePR()).sndToc_C(ClosePC()))
      },
      register(sh.Failed_c_C, sh.SndFM) {
        case (s, c) =>
          val cn = c.failed_c_C().sndTor_R(FM())
          (s, cn)
      }

      )
  }

}
