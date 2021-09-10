package example.faulty.twoBuyer.linearity

import event_lang.dsl._
import example.two_buyer.TwoBuyer.Buyer.{EPType_Buyer, __EPType_Buyer}
import example.two_buyer.TwoBuyer.MESSAGES.Main.CancelMain
import example.two_buyer.TwoBuyer.MESSAGES.TwoBuyer._
import example.two_buyer.TwoBuyer.Seller.{EPType_Seller, __EPType_Seller}
import example.two_buyer.TwoBuyerCons.BOOK_ONE

/*
       "TwoBuyer(s : Seller,b1 : Buyer, _b2 : Buyer) = {" +
     "   b1 -> s : Request(s : String)." +
     "   s -> b1 : Price1(i : Int)." +
     "   s -> b2 : Price2(i : Int)." +
     "   b1 -> b2 : IPay(i : Int)." +
     "   b2 -> s : {" +
     "     Ok() : b2 -> s : Addr(s : String). s -> b2 : ArrivalDate(i : Int).0," +
     "     Quit(): 0" +
     " with " +
     "   b2@s. s -> b1 : CancelTrans().0" +
     "};"+
     "Main(s : Seller, _b1 : Buyer, Buyer) = {" +
     "   spawn TwoBuyer(s,b1,_Buyer).0" +
     " with " +
     "   b1@s. s -> Buyer : CancelMain().0" +
     "};}"
  */

object TwoBuyerCons{
  val BOOK_ONE = "Behavioural Types: from Theory to Tools"
}


case class StateB(toPay: Int = 0) extends TState

class Buyer(val b1_max_pay: Int = 50,
            val b2_max_pay: Int = 50,
            val block: (AbstractChannelType, StateB, AbstractChannelImp) => Boolean = (d, x, s) => false,
            val customCode: (AbstractChannelType, StateB, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_Buyer[StateB] with AbstractEndPointTesting[__EPType_Buyer, StateB] {
  override def onStartUp: StateB = StateB()


  import example.two_buyer.TwoBuyer.Buyer.{Main_Buyer => main_B, Main_b1_Buyer => main_b1, TwoBuyer_b1_Buyer => twoB_b1, TwoBuyer_b2_Buyer => twoB_b2}

  override val receive: Seq[HDL[StateB]] = ELoop(
    //main
    λ_static(main_b1.SpawnTwoBuyer) {
      case c => c
    },
    λ_static(main_B.SpawnTwoBuyer) {
      case c => c
    },
    λ(main_B.RcvCancelMain) {
      case c =>
        println(s"[B] CancelMain was triggered")
        c.channelCon
    },
    //two buyer
    //b_1
    λ(twoB_b1.SndRequest) {
      case c =>
        //linearity violation
        c ! Request(BOOK_ONE)
        c ! Request(BOOK_ONE)
    },
    λ(twoB_b1.RcvPrice1, twoB_b1.SndIPay) {
      case c =>
        c ? {
          case (msg, c) if msg.i <= b1_max_pay =>
            c ! IPay(msg.i)
          case (msg, c) =>
            c ! IPay(b1_max_pay)
        }
    },
    λ(twoB_b1.RcvCancelTrans) {
      case c =>
        c.channelCon
    },
    //b_2
    λ_state(twoB_b2.RcvPrice2) {
      case (s, c) =>
        (s.copy(toPay = c.rcvMSG.i), c.channelCon)
    },
    λ_state(twoB_b2.RcvIPay) {
      case (s, c) =>
        (s.copy(toPay = s.toPay - c.rcvMSG.i), c.channelCon)
    },
    λ_state(twoB_b2.SndOk, twoB_b2.SndAddr) {
      case (s, c) if s.toPay <= b2_max_pay => (s.copy(toPay = 0), c ! Ok() ! Addr("My Street"))
    },
    λ(twoB_b2.RcvArrivalDate) {
      case c =>
        c ? {
          case (m, cc) =>
            println(s"the book will arrive at: $m")
            cc
        }
    },
    λ_state(twoB_b2.SndQuit) {
      case (s, c) => //if s.toPay > 50 =>
        (s, c ! Quit())
    }
  )
}


case class StateS() extends TState

class Seller(val BOOK_ONE_Price: Int = 85,
             val BOOK_TWO_Price: Int = 150,
             val block: (AbstractChannelType, StateS, AbstractChannelImp) => Boolean = (d, x, s) => false,
             val customCode: (AbstractChannelType, StateS, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_Seller[StateS] with AbstractEndPointTesting[__EPType_Seller, StateS] {
  override def onStartUp: StateS = StateS()





  import example.two_buyer.TwoBuyer.Seller.{Main_s_Seller => main, TwoBuyer_s_Seller => two_buyer}

  override val receive: Seq[HDL[StateS]] = ELoop(
    //main
    λ_static(main.SpawnTwoBuyer) {
      case c => c
    },
    λ(main.Failed_b1_Buyer, main.SndCancelMain) {
      case c =>
        println(s"[s] CancelMain was triggered")
        c.failed_b1_Buyer() ! CancelMain()
    },
    //two buyer
    λ(two_buyer.RcvRequest, two_buyer.SndPrice1, two_buyer.SndPrice2) {
      case c =>
        c.? {
          case (m, c) =>
            if (m.s == BOOK_ONE) {
              c ! Price1(BOOK_ONE_Price) ! Price2(BOOK_ONE_Price)
            } else {
              c ! Price1(BOOK_TWO_Price) ! Price2(BOOK_TWO_Price)
            }
        }
    },
    λ(two_buyer.RcvOk) {
      case c => c.channelCon
    },
    λ(two_buyer.RcvQuit) {
      case c =>
        println(s"Canceled Transaction")
        c.channelCon
    },
    λ(two_buyer.RcvAddr, two_buyer.SndArrivalDate) {
      case c =>
        c.? {
          case (m, c) =>
            c ! ArrivalDate("Soon :)")
        }
    },
    λ(two_buyer.Failed_b2_Buyer, two_buyer.SndCancelTrans) {
      case c => c.failed_b2_Buyer() ! CancelTrans()
    }
  )
}