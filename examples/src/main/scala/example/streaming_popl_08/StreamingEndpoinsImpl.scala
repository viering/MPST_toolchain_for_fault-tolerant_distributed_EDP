package example.streaming_popl_08

import event_lang._
import dsl._
import example.streaming_popl_08.Streaming.Consumer.{EPType_Consumer, __EPType_Consumer}
import example.streaming_popl_08.Streaming.Data.{EPType_Data, __EPType_Data}
import example.streaming_popl_08.Streaming.Kernel.{EPType_Kernel, __EPType_Kernel}
import example.streaming_popl_08.Streaming.Key.{EPType_Key, __EPType_Key}
import example.streaming_popl_08.Streaming.MESSAGES.Main.{FMainCons, FMainKern}
import example.streaming_popl_08.Streaming.MESSAGES.SelKernel.{FSelKernelCons, FSelKernelk}
import example.streaming_popl_08.Streaming.MESSAGES.Streaming.{FStreamingd, FStreamingk}
import example.streaming_popl_08.Streaming._

import scala.util.Random


/*
"{" +
      "Streaming(d : Data, k : Key, kernel : Kernel, _c : Consumer) = {" +
      "   mu t." +
      "   d -> kernel : Data()." +
      "   k -> kernel : Key()." +
      "   kernel -> c : Result()." +
      "   t" +
      " with " +
      "   c@kernel. 0" +
      "};" +
      "SelKernel(d : Data, k : Key, _kernel : Kernel, Consumer) = {" +
      "   spawn Streaming(d,k,kernel, _Consumer)." + //we pick one more s as we have to do it
      "   0" +
      " with " +
      "   k@d. 0};" +
      "};" +
      "Main (d : Data, _k : Key, Kernel, Consumer) = {" +
      "  spawn SelKernel(d,k, _Kernel, Consumer).0" +
      " with " +
      "   k@d. 0};" +
      "}"
 */

case class DataState(num: Int = 0) extends TState

class EP_Data(val block: (AbstractChannelType, DataState, AbstractChannelImp) => Boolean = (d, x, s) => false,
              val customCode: (AbstractChannelType, DataState, AbstractChannelImp) => Unit = (a, b, c) => {}) extends EPType_Data[DataState] with AbstractEndPointTesting[__EPType_Data, DataState] {

  override def onStartUp: DataState = DataState()

  import example.streaming_popl_08.Streaming.Data.{Main_d_Data => main, SelKernel_d_Data => selKernel, Streaming_d_Data => streaming}

  var dataNum = 0

  override val receive: Seq[HDL[DataState]] = ELoop(
    /*
    Main
     */
    λ_static(main.SpawnSelKernel) {
      case c => c
    },
    λ(main.Failed_k_Key, main.SndFMainCons) {
      case c =>
        println(s"activated failure handling in main -> stop ")
        c.failed_k_Key() ! FMainKern() ! FMainCons()
    },
    /*
     SelKern
     */
    λ_static(selKernel.SpawnStreaming) {
      case c => c
    },
    λ(selKernel.Failed_kernel_Kernel, selKernel.SndFSelKernelCons) {
      case c => c.failed_kernel_Kernel() ! FSelKernelk() ! FSelKernelCons()
    },
    /*
    Streaming
     */
    //    λ_state(streaming.SndData) {
    //      case (s, c) if pred(s.num) =>
    //        Thread.sleep(100)
    //        (s.copy(num = s.num + 1), c.sndTokernel_Kernel(MESSAGES.Streaming.Data(s.num)))
    //    },
    λ_state(streaming.SndData) {
      case (s, c) if Random.nextBoolean() =>
        Thread.sleep(100)
        (s.copy(num = s.num + 1), c.sndTokernel_Kernel(MESSAGES.Streaming.Data(s.num)))
      case (s, c) =>
        Thread.sleep(100)
        (s.copy(num = s.num + 1), c.sndTokernel_Kernel(MESSAGES.Streaming.Data(s.num)))
    }
    ,
    λ(streaming.RcvFStreamingd) {
      case c => c ? {
        case (m, c2) => c2
      }
    }
  )

  def pred(n: Int): Boolean = ???
}

case class KeyState() extends TState

class EP_Key(val block: (AbstractChannelType, KeyState, AbstractChannelImp) => Boolean = (d, x, s) => false,
             val customCode: (AbstractChannelType, KeyState, AbstractChannelImp) => Unit = (a, b, c) => {}) extends EPType_Key[KeyState] with AbstractEndPointTesting[__EPType_Key, KeyState] {

  override def onStartUp: KeyState = KeyState()

  import Key.{Main_k_Key => main, SelKernel_k_Key => selKernel, Streaming_k_Key => streaming}

  var dataNum = 0

  val key = 1010101010
  override val receive: Seq[HDL[KeyState]] = ELoop(
    /*
    Main
     */
    λ_static(main.SpawnSelKernel) {
      case c =>
        println(s"[main] Spawn SelKernel")
        c
    },
    /*
     SelKern
     */
    λ_static(selKernel.SpawnStreaming) {
      case c =>
        println(s"[main] Spawn Streaming")
        c
    },
    λ(selKernel.RcvFSelKernelk) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    /*
    Streaming
     */
    λ(streaming.SndKey) {
      case c =>
        Thread.sleep(100)
        c.sndTokernel_Kernel(MESSAGES.Streaming.Key(key))
    },
    λ(streaming.RcvFStreamingk) {
      case c =>
        c ? {
          case (m, c2) => c2
        }
    }
  )
}


case class KernelState(data: Int = 0) extends TState

class EP_Kernel(val block: (AbstractChannelType, KernelState, AbstractChannelImp) => Boolean = (d, x, s) => false,
                val customCode: (AbstractChannelType, KernelState, AbstractChannelImp) => Unit = (a, b, c) => {})
  extends EPType_Kernel[KernelState] with AbstractEndPointTesting[__EPType_Kernel, KernelState] {

  override def onStartUp: KernelState = KernelState()

  import Kernel.{Main_Kernel => main, SelKernel_kernel_Kernel => selKernel, Streaming_kernel_Kernel => streaming}

  var dataNum = 0

  override val receive: Seq[HDL[KernelState]] = ELoop(
    /*
    Main
     */
    λ_static(main.SpawnSelKernel) {
      case c => c
    },
    λ(main.RcvFMainKern) {
      case c => c ? {
        case (m, c) => c
      }
    },
    /*
     SelKern
     */
    λ_static(selKernel.SpawnStreaming) {
      case c => c
    },
    /*
    Streaming
     */
    λ_state(streaming.RcvData) {
      case (s, c) =>
        val rD = c.rcvFrmd_Data
        (KernelState(rD._1.n), rD._2)
    },
    λ_state(streaming.RcvKey, streaming.SndResult) {
      case (s, c) =>
        (KernelState(), c.channelCon.sndToc_Consumer(MESSAGES.Streaming.Result(s.data ^ c.rcvMSG.n)))
    },
    λ(streaming.Failed_c_Consumer, streaming.SndFStreamingd) {
      case c =>
        c.failed_c_Consumer() ! FStreamingk() ! FStreamingd()
    }
  )
}

case class ConsumerState(data: Int = 0) extends TState

class EP_Consumer(val rsCB : Int => Unit = v =>{},
                  val block: (AbstractChannelType, ConsumerState, AbstractChannelImp) => Boolean = (d, x, s) => false,
                  val customCode: (AbstractChannelType, ConsumerState, AbstractChannelImp) => Unit = (a, b, c) => {})
  extends EPType_Consumer[ConsumerState] with AbstractEndPointTesting[__EPType_Consumer, ConsumerState] {

  override def onStartUp: ConsumerState = ConsumerState()

  import Consumer.{Main_Consumer => main, SelKernel_Consumer => selKernel, Streaming_c_Consumer => streaming}

  val key = 1010101010
  var dataNum: Int = 0

  override val receive: Seq[HDL[ConsumerState]] = ELoop(
    /*
    Main
     */
    λ_static(main.SpawnSelKernel) {
      case c => c
    },
    λ(main.RcvFMainCons) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    /*
     SelKern
     */
    λ_static(selKernel.SpawnStreaming) {
      case c => c
    },
    λ(selKernel.RcvFSelKernelCons) {
      case c => c ? {
        case (m, c2) => c2
      }
    },
    /*
    Streaming
     */
    λ(streaming.RcvResult) {
      case c =>
        val m = c.rcvMSG
        println(s"Received: $m -- send value was: ${(m.n ^ key)}")
        assert(dataNum == (m.n ^ key))
        rsCB(m.n ^ key)
        dataNum += 1
        c.channelCon
    }
  )
}