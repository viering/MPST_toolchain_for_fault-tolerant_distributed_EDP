package example.globalescape.buyer

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, PickHandler, TState}
import example.dsh.DSH.C.__EPType_C
import example.globalescape.buyer.types.InterruptibleBuyer.Bank.EPType_Bank
import example.globalescape.buyer.types.InterruptibleBuyer.Client.{EPType_Client, __EPType_Client}
import example.globalescape.buyer.types.InterruptibleBuyer.Interrupt.EPType_Interrupt
import example.globalescape.buyer.types.InterruptibleBuyer.MESSAGES._
import example.globalescape.buyer.types.InterruptibleBuyer.MESSAGES.Main._
import example.globalescape.buyer.types.InterruptibleBuyer.MESSAGES.SelS._
import example.globalescape.buyer.types.InterruptibleBuyer.MESSAGES.Aboard._
import example.globalescape.buyer.types.InterruptibleBuyer.MESSAGES.ThreeBuyers.{D, _}
import example.globalescape.buyer.types.InterruptibleBuyer.MESSAGES.NegotiationCB._
import example.globalescape.buyer.types.InterruptibleBuyer.Seller.EPType_Seller

import scala.util.Random



case class SellerState() extends TState

class EP_Seller(sState: SellerState = SellerState()) extends EPType_Seller[SellerState] {

  import example.globalescape.buyer.types.InterruptibleBuyer.Seller._

  override def onStartUp: SellerState = sState

  override val pickHandler : Seq[PickHandler[SellerState]] = ControlPick(
    controlPick(SelS_s_Seller.SpawnThreeBuyers){
      case (s,ses,ps) =>
        (s,ps.reduceOption(_ min _))
    },

  )

  override val receive = ELoop(
    λ(Main_s_Seller.Failed_c_Client, Main_s_Seller.SndMFailureToI) {
      case c => c.failed_c_Client() ! MFailureToB() ! MFailureToI()
    },

    λ(SelS_s_Seller.Failed_b_Bank, SelS_s_Seller.SndBFailedToI) {
      case c => c.failed_b_Bank() ! BFailedToC() ! BFailedToI()
    },

    λ(Aboard_s_Seller.SndD) {
      case c => c ! Aboard.D()
    },
    λ(Aboard_s_Seller.Failed_i_Interrupt, Aboard_s_Seller.SndAboardToB) {
      case c => c.failed_i_Interrupt() ! AboardToC() ! AboardToB()
    },


    λ(ThreeBuyers_s_Seller.RcvO, ThreeBuyers_s_Seller.SndA) {
      case c =>
        println(s"[Seller] Rcv: ${c.rcvMSG}")
        c.channelCon ! A()
    },
    λ(ThreeBuyers_s_Seller.RcvM, ThreeBuyers_s_Seller.SndD) {
      case c =>
        println(s"[Seller] Rcv: ${c.rcvMSG}")
        c.channelCon ! D()
    },
    λ(ThreeBuyers_s_Seller.Failed_i_Interrupt, ThreeBuyers_s_Seller.SndBuyerInterToInter) {
      case c =>
        c.failed_i_Interrupt() ! BuyerInterToC() ! BuyerInterToB() ! BuyerInterToInter()
    },
  )
}

case class CState() extends TState

class EP_Client(val block: (AbstractChannelType, CState, AbstractChannelImp) => Boolean = (d, x, s) => false,
                val customCode: (AbstractChannelType, CState, AbstractChannelImp) => Unit = (a, b, c) => {},sState: CState = CState())
  extends EPType_Client[CState] with AbstractEndPointTesting[__EPType_Client, CState]{


  import example.globalescape.buyer.types.InterruptibleBuyer.Client._

  override def onStartUp: CState = sState

  controlPick(ThreeBuyers_c_Client.SpawnNegotiationCB){
    case (s,ses,ps) =>
      (s,ps.reduceOption(_ min _))
  }

  override val receive = ELoop(
    λ(SelS_c_Client.RcvBFailedToC) {
      case c => c.channelCon
    },


    λ(Aboard_c_Client.RcvD) {
      case c =>
        println(s"[Client] Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(Aboard_c_Client.RcvAboardToC) {
      case c =>
        println(s"[Client] Rcv: ${c.rcvMSG}")
        c.channelCon
    },


    λ(ThreeBuyers_c_Client.SndO) {
      case c => c ! O()
    },
    λ(ThreeBuyers_c_Client.RcvA, ThreeBuyers_c_Client.SndCo) {
      case c =>
        println(s"[Client] Rcv: ${c.rcvMSG}")
        c.channelCon ! Co()
    },
    λ(ThreeBuyers_c_Client.SndM) {
      case c => c ! M()
    },
    λ(ThreeBuyers_c_Client.RcvD) {
      case c =>
        println(s"[Client] Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(ThreeBuyers_c_Client.RcvBuyerInterToC) {
      case c => c.channelCon
    },


    λ(NegotiationCB_c_Client.RcvNEM) {
      case c =>
        println(s"[Client] Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(NegotiationCB_c_Client.RcvOK) {
      case c =>
        println(s"[Client] Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(NegotiationCB_c_Client.Failed_i_Interrupt, NegotiationCB_c_Client.SndAboardNeg) {
      case c =>
        c.failed_i_Interrupt() ! AboardNeg()
    },
  )
}

case class BState(negRes: Boolean = Random.nextBoolean()) extends TState

class EP_Bank(sState: BState = BState()) extends EPType_Bank[BState] {

  import example.globalescape.buyer.types.InterruptibleBuyer.Bank._

  override def onStartUp: BState = sState

  override val receive = ELoop(
    λ(Main_Bank.RcvMFailureToB){
      case c => c.channelCon
    },
    λ(Aboard_b_Bank.RcvAboardToB) {
      case c =>
        println(s"[Bank] Rcv: ${c.rcvMSG}")
        c.channelCon
    },

    λ(ThreeBuyers_b_Bank.RcvCo) {
      case c =>
        println(s"[Bank] Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(ThreeBuyers_b_Bank.RcvBuyerInterToB) {
      case c =>
        println(s"[Bank] Rcv: ${c.rcvMSG}")
        c.channelCon
    },

    λ_state(NegotiationCB_b_Bank.SndOK) {
      case (s, c) if s.negRes =>
        (s, c ! NegotiationCB.OK())
    },
    λ(NegotiationCB_b_Bank.SndNEM) {
      case c =>
        c ! NEM()
    },
    λ(NegotiationCB_b_Bank.RcvAboardNeg) {
      case c =>
        println(s"[Bank] Rcv: ${c.rcvMSG}")
        c.channelCon
    },

  )
}

case class IState() extends TState

class EP_Interrupt(sState: IState = IState()) extends EPType_Interrupt[IState] {

  import example.globalescape.buyer.types.InterruptibleBuyer.Interrupt._

  override def onStartUp: IState = sState

  override val receive = ELoop(
    λ(Main_Interrupt.RcvMFailureToI) {
      case c =>
        c.channelCon
    },

    λ(SelS_Interrupt.RcvBFailedToI) {
      case c =>
        c.channelCon
    },

    λ(ThreeBuyers_Interrupt.RcvBuyerInterToInter) {
      case c =>
        c.channelCon
    },
  )
}