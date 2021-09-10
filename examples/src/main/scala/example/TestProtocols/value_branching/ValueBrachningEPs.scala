package example.TestProtocols.value_branching

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPoint, AbstractEndPointTesting, HDL, TState}
import example.util.EPRunner
import ValueBranching.S._
import ValueBranching.B._
import example.TestProtocols.value_branching.ValueBranching.MESSAGES.Main.{Book, FailMain, No, OK}

//object VBranchingRunner {
//  def main(args: Array[String]): Unit = {
//    val mainId = 0
//    val pickId = 1
//
//    val f: PartialFunction[Int, Unit] = {
//      case x if x > 5 => println(x)
//    }
//
//    EPRunner.bootstrap(mainId, pickId, 0,0,
//      (() => new EP_Buyer(), 1 to 2), (() => new EP_Seller, List(0)))
//  }
//}

case class StateS() extends TState

class EP_Seller(val bPrice : Int = 20,val block: (AbstractChannelType, StateS, AbstractChannelImp) => Boolean = (d, x, s) => false,
                val customCode: (AbstractChannelType, StateS, AbstractChannelImp) => Unit = (d, x, s) => {}) extends EPType_S[StateS] with AbstractEndPointTesting[__EPType_S,StateS] {


  override def onStartUp: StateS = StateS()


  override val receive: Seq[HDL[StateS]] = ELoop(
    λ(Main_s_S.SndBook) {
      case c => c ! Book(bPrice)
    },
    λ(Main_s_S.Failed_b_B, Main_s_S.SndFailMain) {
      case c => c.failed_b_B() ! FailMain()
    },
    λ(Main_s_S.RcvOK) {
      case c => c ? {
        case (m, c2) =>
          println(m)
          c2
      }
    },
    λ(Main_s_S.RcvNo) {
      case c => c ? {
        case (m, c2) =>
          println(m)
          c2
      }
    }
  )
}

case class StateB() extends TState

class EP_Buyer(val maxPrice : Int =50) extends EPType_B[StateB] {

  override def onStartUp: StateB = StateB()


  override val receive: Seq[HDL[StateB]] = ELoop(
    λ(Main_B.RcvFailMain) {
      case c => c.channelCon
    },
    λ(Main_b_B.RcvFailMain) {
      case c => c.channelCon
    },
    λ(Main_b_B.RcvBook, Main_b_B.SndOK) {
      case c if (c ?).price <= maxPrice
      => c.? {
        case (Book(price), c) => c ! OK()
      }
    },
    λ(Main_b_B.RcvBook, Main_b_B.SndNo) {
      case c => c ? {
          case (Book(price), c) => c ! No()
        }
    }
  )
}