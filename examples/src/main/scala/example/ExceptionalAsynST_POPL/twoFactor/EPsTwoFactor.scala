package example.ExceptionalAsynST_POPL.twoFactor

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, TState}
import example.ExceptionalAsynST_POPL.twoFactor.types.TwoFactor.C.{EPType_C, TwoFactor_c_C}
import example.ExceptionalAsynST_POPL.twoFactor.types.TwoFactor.MESSAGES.TwoFactor.{AccData, AccessDenied, Authenticated, Challenge, ChallengeAccessDenied, ChallengeAuthenticated, ChallengeKey, Response}
import example.ExceptionalAsynST_POPL.twoFactor.types.TwoFactor.S.{EPType_S, TwoFactor_s_S, __EPType_S}

import scala.util.Random


case class ServerState(val challenge : Boolean = Random.nextBoolean()) extends TState


class EP_Server(sState: ServerState = ServerState(),
 //               val block: (AbstractChannelType, ServerState, AbstractChannelImp) => Boolean = (d, x, s) => false,
//                val customCode: (AbstractChannelType, ServerState, AbstractChannelImp) => Unit = (d, x, s) => {})
               ) extends EPType_S[ServerState] { //with AbstractEndPointTesting[__EPType_S, ServerState] {

  override def onStartUp: ServerState = sState

  override val receive = ELoop(
    λ_state(TwoFactor_s_S.RcvAccData, TwoFactor_s_S.SndAccessDenied) {
      case (s, c) if c.rcvMSG.upw != "MyPW" =>
        println(s"[Server] Access denied")
        (s, c.channelCon ! AccessDenied())
    },
    λ_state(TwoFactor_s_S.RcvAccData, TwoFactor_s_S.SndAuthenticated) {
      case (s, c) if c.rcvMSG.upw == "MyPW" && s.challenge =>
        println(s"[Server] Authenticated")
        (s, c.channelCon ! Authenticated())
    },

    λ_state(TwoFactor_s_S.RcvAccData, TwoFactor_s_S.SndChallengeKey) {
      case (s, c) =>
        println(s"[Server] You stupid -- check your email")
        (s, c.channelCon ! Challenge() ! ChallengeKey("foo"))
    },
    λ_state(TwoFactor_s_S.RcvResponse, TwoFactor_s_S.SndChallengeAuthenticated) {
      case (s, c) if c.rcvMSG.s == "bar" =>
        println(s"Auth ok")
        (s, c.channelCon ! ChallengeAuthenticated())
    },
    λ_state(TwoFactor_s_S.RcvResponse, TwoFactor_s_S.SndChallengeAccessDenied) {
      case (s, c) =>
        println(s"Dude its foo ... bar")
        (s, c.channelCon ! ChallengeAccessDenied())
    },
    λ(TwoFactor_s_S.Failed_c_C) {
      case c => c.failed_c_C()
    }
  )
}


case class ClientState() extends TState

class EP_Client(sState: ClientState = ClientState()) extends EPType_C[ClientState] {

  override def onStartUp: ClientState = sState

  override val receive = ELoop(
    λ_state(TwoFactor_c_C.SndAccData) {
      case (s, c) =>
        if (Random.nextBoolean()) {
          (s, c ! AccData("*", "MyPW"))
        } else {
          (s, c ! AccData("lets try", "aaaahaa what was it again"))
        }
    },
    λ(TwoFactor_c_C.RcvAccessDenied) {
      case c =>
        println(s"Access Denied")
        c.channelCon
    },
    λ(TwoFactor_c_C.RcvAuthenticated) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(TwoFactor_c_C.RcvChallenge) {
      case c =>
        c.channelCon
    },
    λ(TwoFactor_c_C.RcvChallengeKey, TwoFactor_c_C.SndResponse) {
      case c =>
        val res = if (Random.nextBoolean()) "bar" else "hmm"
        c.channelCon ! Response(res)
    },
    λ(TwoFactor_c_C.RcvChallengeAccessDenied) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    },
    λ(TwoFactor_c_C.RcvChallengeAuthenticated) {
      case c =>
        println(s"Rcv: ${c.rcvMSG}")
        c.channelCon
    }

  )
}