package event_lang.dsl

import event_lang._
import event_lang.semantic.CommonTypes.PartID
import types.RoleSet

//TODO the test trait has to much code duplication
//Furhtermore, revise how to add the debug and test methods to an endpoint
trait AbstractEndPointTesting[GT <: AbstractChannelType, ST <: TState] extends AbstractEndPoint[GT, ST] {

  def block: (GT,ST,AbstractChannelImp) => Boolean
  def customCode: (GT,ST,AbstractChannelImp) => Unit




  override protected def λ(s1: GT)(hdl: PartialFunction[s1.implT, s1.implNextT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s1.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) && !block(s1,s,c) =>
        customCode(s1,s,c)
        (s, hdl(c))
    }
    assert(s1.children.size == 1)
    Handler(s1, s1, s1.children.head, f)
  }

  override protected def λ_state(c: GT)(hdl: PartialFunction[(ST, c.implT), (ST, c.implNextT)]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, c.implT), (ST, c.implNextT)] = {
      case (_, s, ch) if hdl.isDefinedAt(s, ch) && !block(c,s,ch) => customCode(c,s,ch)
        hdl(s, ch)
    }
    assert(c.children.size == 1)
    Handler(c, c, c.children.head, f)
  }

  override protected def λ_static(s1: GT)(hdl: PartialFunction[s1.implT, s1.implT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s1.implT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) && !block(s1,s,c)=> customCode(s1,s,c)
        (s, hdl(c))
    }
    //assert(s1.children.size == 1)
    Handler(s1, s1, s1, f)
  }

  override protected def λ(s1: GT, s2: GT)(hdl: PartialFunction[s1.implT, s2.implNextT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s2.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) && !block(s1,s,c)=> customCode(s1,s,c)
        (s, hdl(c))
    }
    assert(s2.children.size == 1)
    Handler(s1, s1, s2.children.head, f)
  }

  override protected def λ_state(c1: GT, c2: GT)(hdl: PartialFunction[(ST, c1.implT), (ST, c2.implNextT)]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, c1.implT), (ST, c2.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(s, c)&& !block(c1,s,c) =>
        customCode(c1,s,c)
        hdl(s, c)
    }
    assert(c2.children.size == 1)
    Handler(c1, c1, c2.children.head, f)
  }

  override protected def λ_state(c1: GT, c2: GT, c3: GT)(hdl: PartialFunction[(ST, c1.implT), (ST, c3.implNextT)]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, c1.implT), (ST, c3.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(s, c)&& !block(c1,s,c) =>
        customCode(c1,s,c)
        hdl(s, c)

    }
    assert(c3.children.size == 1)
    Handler(c1, c1, c3.children.head, f)
  }

  override protected def λ(s1: GT, s2: GT, s3: GT)(hdl: PartialFunction[s1.implT, s3.implNextT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s3.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) && !block(s1,s,c)=>
        customCode(s1,s,c)
        (s, hdl(c))
    }
    assert(s3.children.size == 1)
    Handler(s1, s1, s3.children.head, f)
  }

  override protected def λ(s1: GT, s2: GT, s3: GT, s4: GT)(hdl: PartialFunction[s1.implT, s4.implNextT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s4.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) && !block(s1,s,c)=>
        customCode(s1,s,c)
        (s, hdl(c))
    }
    assert(s4.children.size == 1)
    Handler(s1, s1, s4.children.head, f)
  }
}


abstract class AbstractEndPoint[GT <: AbstractChannelType, ST <: TState] {

  protected object ELoop {
    def apply(handler: HDL[ST]*): Seq[HDL[ST]] = {
      handler.toList.reverse
    }
  }
  protected object ControlPick {
    def apply(handler: PickHandler[ST]*): Seq[PickHandler[ST]] = {
      handler.toList.reverse
    }
  }
  protected object FinishSubSessionHandlers {
    def apply(handler: FinishSpawnHandler[ST]*): Seq[FinishSpawnHandler[ST]] = {
      handler.toList.reverse
    }
  }

  protected object H {
    def apply(handler: HDL[ST]*): Seq[HDL[ST]] = {
      handler.toList
    }
  }

  val roleSet: RoleSet
  val subs: Seq[dsl.ChannelTypeSubS]


  def onStartUp: ST

  val receive: Seq[HDL[ST]]

  val pickHandler : Seq[PickHandler[ST]] = Seq()
  val subFinishHandler : Seq[FinishSpawnHandler[ST]]=Seq()


  protected def finishSubSession(s : ChannelTypeSubS)(
    hdl : PartialFunction[(ST,Session,Session),ST]): FinishSpawnHandler[ST] ={
    FinishSpawnHandler(s,hdl)
  }
  protected def controlPick(s : GT)(hdl : PartialFunction[(ST,Session,Seq[PartID]),(ST,Option[PartID])]): PickHandler[ST] ={
    PickHandler(s,hdl)
  }


  protected def registerStatic(s: GT)(hdl: PartialFunction[ST, ST]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, AbstractChannelImp), (ST, AbstractChannelImp)] = {
      case (_, b, c: Any) if hdl.isDefinedAt(b) => (hdl(b), c)
    }
    Handler(s, s, s, f)
  }

  protected def register(s: GT, e: GT)(hdl: PartialFunction[(ST, s.implT), (ST, e.implNextT)])
  : HDL[ST] = {
    val f: PartialFunction[(Any, ST, s.implT), (ST, e.implNextT)] = {
      case (_, b, c) if hdl.isDefinedAt(b, c) => hdl(b, c)
    }
    assert(e.children.size == 1)
    Handler(s, s, e.children.head, f)
  }

  protected def register(s: GT)(hdl: PartialFunction[(ST, s.implT), (ST, s.implNextT)]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s.implT), (ST, s.implNextT)] = {
      case (_, b, c) if hdl.isDefinedAt(b, c) => hdl(b, c)
    }
    assert(s.children.size == 1)
    Handler(s, s, s.children.head, f)
  }



  protected def λ(s1: GT)(hdl: PartialFunction[s1.implT, s1.implNextT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s1.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) => (s, hdl(c))
    }
    assert(s1.children.size == 1)
    Handler(s1, s1, s1.children.head, f)
  }

  protected def λ_state(c: GT)(hdl: PartialFunction[(ST, c.implT), (ST, c.implNextT)]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, c.implT), (ST, c.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(s, c) =>
        hdl(s, c)
    }
    assert(c.children.size == 1)
    Handler(c, c, c.children.head, f)
  }

  protected def λ_static(s1: GT)(hdl: PartialFunction[s1.implT, s1.implT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s1.implT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) => (s, hdl(c))
    }
    //assert(s1.children.size == 1)
    Handler(s1, s1, s1, f)
  }
  protected def λ_static_state(s1: GT)(hdl: PartialFunction[(ST,s1.implT), (ST,s1.implT)]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s1.implT)] = {
      case (_, s, c) if hdl.isDefinedAt(s,c) => hdl(s,c)
    }
    //assert(s1.children.size == 1)
    Handler(s1, s1, s1, f)
  }

  protected def λ(s1: GT, s2: GT)(hdl: PartialFunction[s1.implT, s2.implNextT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s2.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) => (s, hdl(c))
    }
    assert(s2.children.size == 1)
    Handler(s1, s1, s2.children.head, f)
  }

  protected def λ_state(c1: GT, c2: GT)(hdl: PartialFunction[(ST, c1.implT), (ST, c2.implNextT)]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, c1.implT), (ST, c2.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(s, c) => hdl(s, c)
    }
    assert(c2.children.size == 1)
    Handler(c1, c1, c2.children.head, f)
  }

  protected def λ_state(c1: GT, c2: GT, c3: GT)(hdl: PartialFunction[(ST, c1.implT), (ST, c3.implNextT)]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, c1.implT), (ST, c3.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(s, c) => hdl(s, c)
    }
    assert(c3.children.size == 1)
    Handler(c1, c1, c3.children.head, f)
  }

  protected def λ(s1: GT, s2: GT, s3: GT)(hdl: PartialFunction[s1.implT, s3.implNextT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s3.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) => (s, hdl(c))
    }
    assert(s3.children.size == 1)
    Handler(s1, s1, s3.children.head, f)
  }

  protected def λ(s1: GT, s2: GT, s3: GT, s4: GT)(hdl: PartialFunction[s1.implT, s4.implNextT]): HDL[ST] = {
    val f: PartialFunction[(Any, ST, s1.implT), (ST, s4.implNextT)] = {
      case (_, s, c) if hdl.isDefinedAt(c) => (s, hdl(c))
    }
    assert(s4.children.size == 1)
    Handler(s1, s1, s4.children.head, f)
  }


//  protected def regRcv(s: GT)(hdl: PartialFunction[(s.msgT, ST, s.implNextT), (ST, s.implNextT)])
//  : HDL[ST] = {
//    assert(s.children.size == 1)
//    Handler(s, s.children.head, s.children.head, hdl)
//  }
//
//  protected def regRcv(s: GT, e: GT)
//                      (hdl: PartialFunction[(s.msgT, ST, s.implNextT), (ST, e.implNextT)])
//  : HDL[ST] = {
//    assert(e.children.size == 1)
//    Handler(
//      s, s.children.head, e.children.head, hdl)
//  }

  //protected def registerWFilter(s: GT, e: GT)(hdl: (ST, s.implT) => (ST, e.implT), IF: ST =>
  // Boolean): HDL = Handler(s, e, hdl, Some(IF))

  ///protected def registerMSG(s: GT, e: GT)(hdl: (s.msgT, ST, s.implT) => (ST, e.implT)) =
  // Handler(s, e, null)
}
