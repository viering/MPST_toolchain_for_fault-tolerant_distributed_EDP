package example.resourceAccessControl

import event_lang.dsl.{PickHandler, TState}
import example.resourceAccessControl.types.ResControl.A.{EPType_A, HdlRes_a_A, InterruptOne_a_A, InterruptTwo_a_A, ResAccCon_A}
import example.resourceAccessControl.types.ResControl.C._
import example.resourceAccessControl.types.ResControl.InOne.{EPType_InOne, HdlRes_InOne, ResAccCon_InOne}
import example.resourceAccessControl.types.ResControl.InOne.HdlRes_InOne.EPHdlRes_InOne
import example.resourceAccessControl.types.ResControl.InOne.ResAccCon_InOne.EPResAccCon_InOne
import example.resourceAccessControl.types.ResControl.InTwo.{EPType_InTwo, HdlRes_InTwo, InterruptOne_InTwo, InterruptTwo_InTwo, InterruptTwo_iTwo_InTwo, ResAccCon_InTwo}
import example.resourceAccessControl.types.ResControl.MESSAGES.HdlRes.Start
import example.resourceAccessControl.types.ResControl.MESSAGES.InterruptTwo.{Data, Resume}
import example.resourceAccessControl.types.ResControl.MESSAGES.ResAccCon.Req
import example.resourceAccessControl.types.ResControl.MESSAGES._
import example.resourceAccessControl.types.ResControl.U.{EPType_U, HdlRes_u_U, InterruptOne_u_U, InterruptTwo_u_U, ResAccCon_u_U}
import example.resourceAccessControl.types.ResControl._

import scala.util.Random



case class CState() extends TState

class EP_C(sState: CState = CState()) extends EPType_C[CState] {

  override def onStartUp: CState = sState


  override val pickHandler : Seq[PickHandler[CState]] = ControlPick(
    controlPick(InterruptOne_c_C.SpawnInterruptTwo){
      case (s,ses,ps) =>
        (s,ps.find(_ == 4))
    }
  )


  override val receive = ELoop(
    λ(ResAccCon_c_C.RcvReq) {
      case c =>
        println(s"[c] RcvReq")
        c.channelCon
    },
    λ(ResAccCon_c_C.Failed_u_U, ResAccCon_c_C.SndF2) {
      case c =>
        println(s"[c] Failed u")
        c.failed_u_U() ! ResAccCon.F() ! ResAccCon.F1() ! ResAccCon.F2()
    },


    λ(HdlRes_c_C.SndStart) {
      case c =>
        println(s"[c] SndStart")
        c ! Start()
    },
    λ(HdlRes_c_C.Failed_a_A, HdlRes_c_C.SndF2) {
      case c =>
        println(s"[c] a Failed")
        c.failed_a_A() ! HdlRes.F() ! HdlRes.F1() ! HdlRes.F2()
    },


    λ(InterruptOne_c_C.Failed_iOne_InOne, InterruptOne_c_C.SndF2) {
      case c =>
        println(s"[c] iOne Failed")
        c.failed_iOne_InOne() ! InterruptOne.F() ! InterruptOne.F1() ! InterruptOne.F2()
    },


    λ(InterruptTwo_c_C.Failed_iTwo_InTwo, InterruptTwo_c_C.SndF3) {
      case c =>
        println(s"[c] iTwo Failed")
        c.failed_iTwo_InTwo() ! InterruptTwo.InterPaus() ! InterruptTwo.F2() ! InterruptTwo.F3()
    },
  )
}

case class UState() extends TState

class EP_U(sState: UState = UState()) extends EPType_U[UState] {

  override def onStartUp: UState = sState

  override val receive = ELoop(
    λ(ResAccCon_u_U.SndReq) {
      case c =>
        println(s"[u] SndReq")
        c ! Req(0)
    },


    λ(HdlRes_u_U.RcvF) {
      case c =>
        println(s"[u] RcvF")
        c.channelCon
    },


    λ(InterruptOne_u_U.RcvF) {
      case c =>
        println(s"[User] Our resource access was interruped => terminate")
        c.channelCon
    },


    λ(InterruptTwo_u_U.RcvData) {
      case c =>
        println(s"[User:${c.session.sesId}] Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(InterruptTwo_u_U.RcvInterPaus, InterruptTwo_u_U.SndResume) {
      case c =>
        println(s"[User] Rcv: ${c.rcvMSG}")
        c.channelCon ! Resume()
    },
  )
}


case class AState() extends TState

class EP_A(sState: AState = AState()) extends EPType_A[AState] {

  override def onStartUp: AState = sState

  override val receive = ELoop(
    λ(ResAccCon_A.RcvF) {
      case c =>
        println(s"[A] RcvF")
        c.channelCon
    },


    λ(HdlRes_a_A.RcvStart) {
      case c =>
        println(s"[a] RcvStart")
        c.channelCon
    },


    λ(InterruptOne_a_A.RcvF1) {
      case c =>
        println(s"[a] RcvF1")
        c.channelCon
    },

    λ(InterruptTwo_a_A.RcvF3) {
      case c =>
        println(s"[a] RcvF3")
        c.channelCon
    },

    λ(InterruptTwo_a_A.SndData) {
      case c =>

        Thread.sleep(250)
        println(s"[a] SndData")
        c ! Data()
    },

    λ(InterruptTwo_a_A.RcvResume) {
      case c =>
        println(s"[a] RcvResume")
        c.channelCon
    },
  )
}


case class IOneState() extends TState

class EP_IOne(sState: IOneState = IOneState()) extends EPType_InOne[IOneState] {

  override def onStartUp: IOneState = sState

  override val receive = ELoop(
    λ(ResAccCon_InOne.RcvF1) {
      case c =>
        println(s"[InOne] RcvF1")
        c.channelCon
    },

    λ(HdlRes_InOne.RcvF1) {
      case c =>
        println(s"[InOne] RcvF1")
        c.channelCon
    }

  )
}

case class ITwoState() extends TState

class EP_ITwo(sState: ITwoState = ITwoState()) extends EPType_InTwo[ITwoState] {

  override def onStartUp: ITwoState = sState

  override val receive = ELoop(
    λ(ResAccCon_InTwo.RcvF2) {
      case c =>
        println(s"[InTwo] ResAccCon.RcvF2")
        c.channelCon
    },


    λ(HdlRes_InTwo.RcvF2) {
      case c =>
        println(s"[InTwo] Hdlres.RcvF2")
        c.channelCon
    },


    λ(InterruptOne_InTwo.RcvF2) {
      case c =>
        println(s"[InTwo] InterruptOne.RcvF2")
        c.channelCon
    },

    λ(InterruptTwo_InTwo.RcvF2) {
      case c =>
        println(s"[InTwo] InterruptTwo.RcvF2")
        c.channelCon
    }
  )
}