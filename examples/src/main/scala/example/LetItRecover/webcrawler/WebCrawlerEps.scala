package example.LetItRecover.webcrawler

import event_lang.dsl.TState
import example.LetItRecover.webcrawler.types.webCrawler.D.EPType_D
import example.LetItRecover.webcrawler.types.webCrawler.I.EPType_I
import example.LetItRecover.webcrawler.types.webCrawler.MESSAGES.Download._
import example.LetItRecover.webcrawler.types.webCrawler.MESSAGES.Parse._
import example.LetItRecover.webcrawler.types.webCrawler.MESSAGES.WebCrawler._
import example.LetItRecover.webcrawler.types.webCrawler.P._


//case class CState() extends TState
//
//class EP_C(sState: CState = CState()) extends EPType_C[CState] {
//
//  override def onStartUp: CState = sState
//
//  override val receive = ELoop(
//    λ(ResAccCon_c_C.RcvReq) {
//      case c => c.channelCon
//    },
//  )
//}

import example.LetItRecover.webcrawler.types.webCrawler.M.EPType_M

case class MState(tNum : Int = 2, curNum : Int = 0) extends TState

class EP_M(sState: MState = MState()) extends EPType_M[MState] {
  import example.LetItRecover.webcrawler.types.webCrawler.M._
  override def onStartUp: MState = sState

  override val receive = ELoop(
    λ_state(WebCrawler_m_M.SndIAddD,WebCrawler_m_M.SndPAddD) {
      case (s,c) if s.curNum < s.tNum =>
        println(s"[Master] Add a downloader (s: $s)")
        (s.copy(curNum = s.curNum + 1), c ! IAddD() ! DAddD() ! PAddD())
    },
    λ(WebCrawler_m_M.SndIDone,WebCrawler_m_M.SndPDone){
      case c => c ! IDone() ! DDOne() ! PDone()
    },
    λ(WebCrawler_m_M.RcvUrl){
      case c =>
        println(s"[Master] rec: ${c.rcvMSG}")
        c.channelCon
    },
    λ(WebCrawler_m_M.Failed_i_I,WebCrawler_m_M.SndF2){
      case c => c.failed_i_I() ! F1() ! F2()
    },



    λ(Download_m_M.Failed_d_D,Download_m_M.SndDF2){
      case c => c.failed_d_D() ! DF() ! DF1() ! DF2()
    },

    λ(Parse_m_M.Failed_p_P, Parse_m_M.SndPF2){
      case c => c.failed_p_P() ! PF() ! PF1() ! PF2()
    }
  )
}


case class IState(res : String = "") extends TState

class EP_I(sState: IState = IState()) extends EPType_I[IState] {
  import example.LetItRecover.webcrawler.types.webCrawler.I._
  override def onStartUp: IState = sState

  override val receive = ELoop(
    λ(WebCrawler_i_I.RcvIAddD) {
      case c => c.channelCon
    },
    λ(WebCrawler_i_I.RcvIDone) {
      case c => c.channelCon
    },
    λ_state(WebCrawler_i_I.SndUrl) {
      case (s,c) if c.session.hasNoSubsession() =>
        println(s"Snd ${s.res} to master")
        (s, c ! Url(s.res))
    },

    λ(Download_i_I.RcvDF){
      case c => c.channelCon
    },

    λ_state(Parse_i_I.RcvIndex){
      case (s,c) =>
        println(s"[Indexer] rcv: ${c.rcvMSG}")
        (s.copy(res = s.res + " " + c.rcvMSG.i),c.channelCon)
    },

    λ(Parse_i_I.RcvPF){
      case c => c.channelCon
    }

  )
}


case class DState() extends TState

class EP_D(sState: DState = DState()) extends EPType_D[DState] {
  import example.LetItRecover.webcrawler.types.webCrawler.D._
  override def onStartUp: DState = sState

  override val receive = ELoop(
    λ(WebCrawler_D.RcvDAddD) {
      case c => c.channelCon
    },
    λ(WebCrawler_D.RcvDDOne) {
      case c => c.channelCon
    },
    λ(WebCrawler_D.RcvF1) {
      case c => c.channelCon
    },

    λ(Download_D.RcvDF1){
      case c => c.channelCon
    },

    λ(Parse_d_D.SndParse){
      case c =>
        println(s"[Downlaoder] Snd out parsed text")
        c ! Parse("parse text")
    },

    λ(Parse_d_D.RcvPF1){
      case c => c.channelCon
    }

  )
}

case class PState() extends TState

class EP_P(sState: PState = PState()) extends EPType_P[PState] {
  import example.LetItRecover.webcrawler.types.webCrawler.P._
  override def onStartUp: PState = sState

  override val receive = ELoop(
    λ(WebCrawler_P.RcvPAddD) {
      case c => c.channelCon
    },
    λ(WebCrawler_P.RcvPDone) {
      case c => c.channelCon
    },
    λ(WebCrawler_P.RcvF2) {
      case c => c.channelCon
    },

    λ(Download_P.RcvDF2){
      case c => c.channelCon
    },

    λ(Parse_p_P.RcvParse, Parse_p_P.SndIndex){
      case c =>
        c.channelCon ! Index(c.rcvMSG.m.toUpperCase())
    },

    λ(Parse_P.RcvPF2){
      case c => c.channelCon
    }

  )
}