package example

import java.util.concurrent.atomic.AtomicInteger

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, TState}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.semantic.OperationalSemantic
import event_lang.types.{Role, RoleSet}
import example.streaming_popl_08.Streaming.Consumer.Streaming_c_Consumer.RcvResult
import example.streaming_popl_08.{EP_Consumer, EP_Data, EP_Kernel, EP_Key}
import example.util.{EPRunner, VoidOutStream}
import org.scalatest.concurrent.{Signaler, ThreadSignaler, TimeLimitedTests}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.{Seconds, Span}

class StreamingTests extends AnyFunSuite with TimeLimitedTests{

  val timeLimit = Span(30, Seconds)
  override val defaultTestSignaler = new Signaler {
    override def apply(testThread: Thread): Unit = testThread.stop()
  }


  val role_k = Role("k",RoleSet("Key"))

  test("Normal execution streaming") {
    Console.withOut(new VoidOutStream){Console.withErr(new VoidOutStream){
    val mainId = 0
    val pickId = 1
    var network: LHostSessionChannelEPs = null
    var eps: Array[OperationalSemantic[AbstractChannelType, TState]] = null
    val hdlExCnt = new AtomicInteger(0)

    var exV = 0
    val customCode : (AbstractChannelType, TState, AbstractChannelImp) => Unit = {
      case (RcvResult,_,c)=>
        hdlExCnt.getAndIncrement()
        if(exV>5) {
          val kPID = c.session.roleToPId(role_k)
          network.ep_ses_channels(0).signalFail(kPID)
        }
      case _ =>
    }

    val ne = EPRunner.bootstrap(mainId, pickId,0,0,
      (() => new EP_Data(customCode=customCode), List(0)), (() => new EP_Key(customCode = customCode), List(1)),
      (() => new EP_Kernel(customCode = customCode), 2 to 3), (() => new EP_Consumer(rsCB = v =>{
        assert(exV == v)
        exV +=1
      },customCode = customCode), 4 to 5))
    network = ne._1
    eps = ne._2
    ne._3.foreach(_.start())
    ne._3.foreach(_.join)
    assert(hdlExCnt.get()>5)
  }}}


}
