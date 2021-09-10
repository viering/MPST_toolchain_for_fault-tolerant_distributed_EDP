package event_lang.semantic
import event_lang._
import dsl._
import event_lang.intern.logging.Logger

import scala.collection.mutable

class CoverageTest[C <: AbstractChannelType, T <: TState](val hdm: HandlerManager[C, T],
                                                          val subS: Seq[ChannelTypeSubS])
  extends Logger {


  private def checkPath(hdl: HDL[T]): Boolean = {
    val start = hdl.cStart
    val end = hdl.endC
    val q = mutable.Queue[AbstractChannelType](start)
    while (q.nonEmpty) {
      val c = q.dequeue()
      // end = the type after hdl execution (i.e. that one can be a blocking one )
      if (end == c || c.children.contains(end)) {
        return true
      }
      /*
      we check that a path contains no blocking actions
       */
      for (child <- c.children) {
        child match {
          case l: ChannelTypeSnd =>
            q.enqueue(child)
          case l: ChannelTypeSel =>
            q.enqueue(child)
          case l: ChannelTypeMSnd =>
            q.enqueue(child)
          case l: ChannelTypeMerge =>
            q.enqueue(child)
          case l: ChannelTypeRcv =>

          case l: ChannelTypeBrn =>

          case l: ChannelTypeRec =>

          case l: ChannelTypeT =>

          case l: ChannelTypeHdl =>

          case l: ChannelTypeSpawn =>

          case l: ChannelTypeSubS =>

          case l: ChannelTypeFDtct =>

          case l: ChannelTypeEnd =>
          //no hdl needed for end
        }

      }

    }
    assert(false, s"No path from $start to $end found")
    false
  }

  private var failures = false

  private def assert(test: => Boolean, msg: => String = ""): Unit = {
    if (!test) {
      failures = true
      error(msg)
      Console.err.println(msg)
    }
  }

  def check(): Boolean = {
    val todo = mutable.Queue[AbstractChannelType](subS.map(_.body): _*)
    val used = mutable.Set[HDL[T]]()
    val loopCheck = mutable.Set[AbstractChannelType]()


    def addTodo(l: AbstractChannelType): Unit = {
      if (loopCheck.add(l)) {
        todo.enqueue(l)
      }
    }

    def addAllTodo(ls: Iterable[AbstractChannelType]): Unit = {
      for (l <- ls)
        if (loopCheck.add(l)) {
          todo.enqueue(l)
        }
    }

    def defaultTreatment(l: AbstractChannelType): Unit = {
      val hdls = hdm.applicableHandlers(l)
      if(hdls.isEmpty){
        println("")
      }
      assert(hdls.nonEmpty, s"No matching handler for $l")
      for (h <- hdls) {
        used.add(h)
        if (h.cStart != h.endC) {
          assert(h.cStart == l)
          checkPath(h)
          //if (h.endC.children.nonEmpty) {
          //assert(h.endC.children.size == 1, s"XXXX")
          addTodo(h.endC) //.endC.children.head)
          //}

        } else {
          //assert(l.children.size == 1, s"XXXXX")
          assert(false, s"handler $h does not move types forward")
          addTodo(l.children.head)
        }
      }
    }

    while (todo.nonEmpty) {
      val cur = todo.dequeue()
      cur match {
        case l: ChannelTypeSnd =>
          defaultTreatment(l)
        case l: ChannelTypeSel =>
          addAllTodo(l.children)
          //defaultTreatment(l)
        case l: ChannelTypeMSnd =>
          defaultTreatment(l)
        case l: ChannelTypeMerge =>
          addAllTodo(l.children)
          //defaultTreatment(l)
        case l: ChannelTypeRcv =>
          defaultTreatment(l)
        case l: ChannelTypeBrn =>
          addAllTodo(l.children)
          //defaultTreatment(l)
        case l: ChannelTypeRec =>
          addAllTodo(l.children)
        case l: ChannelTypeT =>
        //skip
        case l: ChannelTypeHdl =>
          addAllTodo(l.children)
        case l: ChannelTypeSpawn =>
          subS.filter(_.name == l.name).foreach(s => {
            addTodo(l)
          })
          //we dont need a hdl for spawn but we can have one
          hdm.applicableHandlers(l).foreach(hdl => {
            used.add(hdl)
            assert(hdl.endC == hdl.cStart, s"Spawn handler must not advance the channel type")
          })
          assert(l.children.size <= 1, s"A spawn must have at most one child: $l")
          addAllTodo(l.children)
        case l: ChannelTypeSubS =>
          addAllTodo(l.children)
        case l: ChannelTypeFDtct =>
          defaultTreatment(l)
        case l: ChannelTypeEnd =>
        //no hdl needed for end
      }

    }

    for (h <- hdm.hdls if !used.contains(h)) {
      Console.err.println(s"$h is not used")
      warn(s"$h is not used")
      throw new RuntimeException("Coverage test failed")
    }
    if (failures)
      throw new RuntimeException("Coverage test failed")
    true
  }

}

