package example.three_buyer_global_progress

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, HDL, TState}
import example.three_buyer_global_progress.ThreeBuyer.A.{EPType_A, __EPType_A}
import example.three_buyer_global_progress.ThreeBuyer.B.{EPType_B, __EPType_B}
import example.three_buyer_global_progress.ThreeBuyer.C.threeBuyers_C.EPthreeBuyers_C
import example.three_buyer_global_progress.ThreeBuyer.C.{EPType_C, __EPType_C}
import example.three_buyer_global_progress.ThreeBuyer.MESSAGES.Main.{FMainF1, FMainF2}
import example.three_buyer_global_progress.ThreeBuyer.MESSAGES.SelS.{FSelC, FSelS, FSelb}
import example.three_buyer_global_progress.ThreeBuyer._
import example.three_buyer_global_progress.ThreeBuyer.S.{EPType_S, __EPType_S}
import example.three_buyer_global_progress.ThreeBuyer.MESSAGES.threeBuyers._
import example.three_buyer_global_progress.ThreeBuyer.MESSAGES.negotiationBC._


/*
      "threeBuyers (a : A, b : B, s : S, _ss : S,C) = {" +
      "   a -> s : Book(title : String). " +
      "   s -> a : PriceA(p : Int)." +
      "   s -> b : PriceB(p : Int)." +
      "   a -> b : MyShare(p : Int)." +
      "   spawn negotiationBC(b,_C)." +
      "   b -> s : {" +
      "     OkS() : " +
      "       b -> a : OkA(). " +
      "       b -> s : Addr(s : String)." +
      "       s -> b : ShipD(d : String).0," +
      "     QuitS(): " +
      "       b -> a : QuitA().0" +
      "   }" +
      " with " +
      "   ss@a. a -> b : F3Bb(). a -> s : F3Bs(). a -> C : F3BC(). 0 " +
      "};" +
      "negotiationBC(b : B, _c : C) = {" +
      "   b -> c : {" +
      "     YourShare(i : Int) : " +
      "       c -> b : {" +
      "         Ok() : 0," +
      "         No() : 0" +
      "       }," +
      "     End() : 0" +
      "   }" +
      " with " +
      "   c@b. 0" +
      "};" +
      "SelS (a : A, b : B, _s : S, S,C) = {" +
      "   spawn threeBuyers(a,b,s, _S,C)." + //we pick one more s as we have to do it
      "   0" +
      " with " +
      "   s@a. a -> b : FSelb(). a -> S : FSelS(). a -> C : FSelC(). 0};" +
      "Main (a : A, _b : B, C, S) = {" +
      " spawn SelS(a,b,_S,S,C).0" +
      " with " +
      "b@a. a -> C : FMainF1(). a -> S : FMainF2(). 0};" +
    "}"
*/

object ThreeBuyerCons {
  val BOOK = "XXX"
}

case class StateA() extends TState

class EP_A(val A_AMOUNT: Int = 50,
           val block: (AbstractChannelType, StateA, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateA, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_A[StateA] with AbstractEndPointTesting[__EPType_A, StateA] {

  import example.three_buyer_global_progress.ThreeBuyer.A._

  override def onStartUp: StateA = StateA()

  override val receive: Seq[HDL[StateA]] = ELoop(
    λ(Main_a_A.Failed_b_B, Main_a_A.SndFMainF2) {
      case c => c.failed_b_B() ! FMainF1() ! FMainF2()
    },

    λ_static(SelS_a_A.SpawnthreeBuyers) {
      case c => c
    },
    λ(SelS_a_A.Failed_s_S, SelS_a_A.SndFSelC) {
      case c => c.failed_s_S() ! FSelb() ! FSelS() ! FSelC()
    },

    λ(threeBuyers_a_A.SndBook) {
      case c =>
        c ! Book(ThreeBuyerCons.BOOK)
    },
    λ(threeBuyers_a_A.RcvPriceA, threeBuyers_a_A.SndMyShare) {
      case c => c.? {
        case (m, c) =>
          c ! MyShare(Math.min(m.p, A_AMOUNT))
      }
    },
    λ(threeBuyers_a_A.RcvOkA) {
      case c =>
        println(s"[A] got ok")
        c.channelCon
    },
    λ(threeBuyers_a_A.RcvQuitA) {
      case c =>
        println(s"[A] got quit msg")
        c.channelCon
    },
    λ(threeBuyers_a_A.Failed_ss_S, threeBuyers_a_A.SndF3BC) {
      case c => c.failed_ss_S() ! F3Bb() ! F3Bs() ! F3BC()
    },
  )
}

case class StateB(bPrice: Option[Int] = None, aShare: Option[Int] = None, cPaysRest: Option[Boolean] = None) extends TState

class EP_B(val B_AMOUNT: Int = 20,
           val block: (AbstractChannelType, StateB, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateB, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_B[StateB] with AbstractEndPointTesting[__EPType_B, StateB] {

  import example.three_buyer_global_progress.ThreeBuyer.B._

  override def onStartUp: StateB = StateB()

  def canBuy(price: Int, helpA: Int, cPaysRest: Boolean): Boolean = {
    price <= helpA + B_AMOUNT || cPaysRest
  }

  override val receive: Seq[HDL[StateB]] = ELoop(
    λ_static(Main_b_B.SpawnSelS) {
      case c =>
        println(s"[b] Spawned SelS")
        c
    },

    λ_static(SelS_b_B.SpawnthreeBuyers) {
      case c =>
        println(s"[b] Spawned three Buyers")
        c
    },
    λ(SelS_b_B.RcvFSelb) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    λ_state(threeBuyers_b_B.RcvPriceB) {
      case (s, c) => c ? {
        case (m, c) =>
          (s.copy(bPrice = Some(m.p)), c)
      }
    },
    λ_state(threeBuyers_b_B.RcvMyShare) {
      case (s, c) => c ? {
        case (m, c) =>
          (s.copy(aShare = Some(m.p)), c)
      }
    },


    λ_state(threeBuyers_b_B.SndOkS, threeBuyers_b_B.SndOkA, threeBuyers_b_B.SndAddr) {
      //TODO do not like that
      case (s@StateB(Some(bPrice), Some(aShare), Some(cPaysRest)), c)
        if canBuy(bPrice, aShare, cPaysRest) =>
        (s, c ! OkS() ! OkA() ! Addr("my addr"))
    },
    λ(threeBuyers_b_B.RcvShipD) {
      case c =>
        c ? {
          case (m, c) =>
            println(s"####################\n### Book will arrive soon (confirmation msg: $m) ###\n####################")
            c
        }
    }
    ,
    λ_state(threeBuyers_b_B.SndQuitS, threeBuyers_b_B.SndQuitA) {
      //TODO how to ensure no deadlock here?
      case (s@StateB(Some(bPrice), Some(aShare), Some(cPaysRest)), c)
        if !canBuy(bPrice, aShare, cPaysRest) =>
        (s, c ! QuitS() ! QuitA())
    },
    λ_static(threeBuyers_b_B.SpawnnegotiationBC) {
      case c =>
        println(s"[b] Spawned Negotiation")
        c
    },
    λ(threeBuyers_b_B.RcvShipD) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    λ(threeBuyers_b_B.RcvF3Bb) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    // nego
    λ_state(negotiationBC_b_B.SndYourShare) {
      case (s@StateB(Some(bPrice), Some(aShare), None), c)
        if bPrice > aShare + B_AMOUNT =>
        (s, c ! YourShare(bPrice - aShare - B_AMOUNT))
    },
    λ_state(negotiationBC_b_B.SndEnd) {
      case (s@StateB(Some(bPrice), Some(aShare), None), c)
        if bPrice < aShare + B_AMOUNT =>
        (s.copy(cPaysRest = Some(false)), c ! End())
    },
    λ_state(negotiationBC_b_B.RcvOk) {
      case (s, c) => c ? {
        case (m, c) =>
          (s.copy(cPaysRest = Some(true)), c)
      }
    },
    λ_state(negotiationBC_b_B.RcvNo) {
      case (s, c) => c ? {
        case (m, c) =>
          (s.copy(cPaysRest = Some(false)), c)
      }
    },
    λ_state(negotiationBC_b_B.Failed_c_C) {
      case (s, c) =>
        (s.copy(cPaysRest = Some(false)), c.failed_c_C())
    }

  )
}

case class StateC() extends TState

class EP_C(
            val C_AMOUNT: Int = 100,
            val block: (AbstractChannelType, StateC, AbstractChannelImp) => Boolean = (d, x, s) => false,
            val customCode: (AbstractChannelType, StateC, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_C[StateC] with AbstractEndPointTesting[__EPType_C, StateC] {
  override def onStartUp: StateC = StateC()

  import example.three_buyer_global_progress.ThreeBuyer.C._

  override val receive: Seq[HDL[StateC]] = ELoop(
    λ(negotiationBC_c_C.RcvYourShare, negotiationBC_c_C.SndOk) {
      case c if c.rcvMSG.i <= C_AMOUNT =>
        c.channelCon.sndTob_B(Ok())
    },
    λ(negotiationBC_c_C.RcvYourShare, negotiationBC_c_C.SndNo) {
      case c if c.rcvMSG.i > C_AMOUNT =>
        c.channelCon.sndTob_B(No())
    },
    λ(negotiationBC_c_C.RcvEnd) {
      case c => c.channelCon
    }
    ,
    λ(threeBuyers_C.RcvF3BC) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    λ(SelS_C.RcvFSelC) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    λ(Main_C.RcvFMainF1) {
      case c => c ? {
        case (m, c2) =>
          c2
      }
    }
  )
}

case class StateS() extends TState

class EP_S(val PRICE: Int = 100,
           val block: (AbstractChannelType, StateS, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, StateS, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_S[StateS] with AbstractEndPointTesting[__EPType_S, StateS] {

  override def onStartUp: StateS = StateS()

  import example.three_buyer_global_progress.ThreeBuyer.S._

  override val receive: Seq[HDL[StateS]] = ELoop(
    λ(threeBuyers_s_S.RcvBook, threeBuyers_s_S.SndPriceA, threeBuyers_s_S.SndPriceB) {
      case c => c ? {
        case (m, c) =>
          if (m.title == ThreeBuyerCons.BOOK) {
            c ! PriceA(PRICE) ! PriceB(PRICE)
          } else {
            c ! PriceA(Int.MaxValue) ! PriceB(Int.MaxValue)
          }
      }
    },
    λ(threeBuyers_s_S.RcvOkS) {
      case c => c.channelCon
    },
    λ(threeBuyers_s_S.RcvAddr, threeBuyers_s_S.SndShipD) {
      case c => c ? {
        case (m, c) =>
          c ! ShipD("Soon XD")
      }
    },
    λ(threeBuyers_s_S.RcvQuitS) {
      case c =>
        println(s"####################\n### Transaction was canceled\n####################")
        c.channelCon
    },
    λ(threeBuyers_s_S.RcvF3Bs) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    λ(SelS_S.RcvFSelS) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    λ(Main_S.RcvFMainF2) {
      case c => c ? {
        case (m, c2) => c2
      }
    }

  )
}


