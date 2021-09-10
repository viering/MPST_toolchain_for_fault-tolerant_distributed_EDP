package example.util

import event_lang._
import dsl.{AbstractChannelType, AbstractEndPoint, TState}
import event_lang.semantic.OperationalSemantic
import network.SpawnMain
import types.{Role, RoleSet}
import event_lang.network.LHChannel.LHostSessionChannelEPs
import event_lang.network.netty.EPAddr
import event_lang.intern.logging.Logger
object EPRunner extends Logger{

  def bootstrap(mainId: Int, pickId: Int, mainSesId: Int, debugId: Int,epFuns: (() => Object, Seq[Int])*):
  (LHostSessionChannelEPs, Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) = {
    bootstrapWithName(mainId,pickId,mainSesId,debugId,"Main",epFuns:_*)
  }
  def bootstrapWithName(mainId: Int, pickId: Int, mainSesId: Int, debugId: Int, mainName : String, epFuns: (() => Object, Seq[Int])*):
  (LHostSessionChannelEPs, Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) = {
    val funs: Seq[(() =>Object,Seq[Int])] = epFuns.toSeq
    val allIds = funs.flatMap(x => x._2)
    assert(allIds.contains(mainId), s"Main id must be contained in endpoint ides")
    allIds.zipWithIndex.foreach(x => assert(x._1== x._2,s"Ids must be 0, 1, .., #participans -1 (ids are used for array access)"))

    val unroled = funs.flatMap(x => x._2.map(y => (x._1, y)))

    var roleMap = Map[Int, Role]()
    var roleSetMap = Map[Int, RoleSet]()
    var eps: List[(Int, AbstractEndPoint[AbstractChannelType, TState])] = Nil

    //fill id -> role and id -> roleset maps
    //created EP,Id Seq
    for (t <- unroled) {
      val ep = t._1().asInstanceOf[AbstractEndPoint[AbstractChannelType, TState]]
      val epId = t._2
      eps = (epId, ep) :: eps
      val rs = ep.roleSet
      if (t._2 == mainId) {
        val mDef = ep.subs.find(_.name.contains(mainName)).get
        assert(mDef.argsC.size == 1)
        val mRole = mDef.argsC.head
        val pRole = mDef.argsP
        roleMap += ((mainId -> mRole), (pickId -> pRole))
      }
      roleSetMap += epId -> ep.roleSet
    }
    eps = eps.reverse
    val mainSes = SpawnMain(mainSesId, mainId, roleMap, roleSetMap)
    val dummyNetwork = new LHostSessionChannelEPs(unroled.size, mainSes)
    val oSemantics = new Array[OperationalSemantic[AbstractChannelType, TState]](unroled.size)
    val runables = for ((id, ep) <- eps) yield {
      val oSemantic = EndPoint.createLocalOnlyEP(ep, dummyNetwork, id,mainName)
      oSemantics(id) = oSemantic
      oSemantic.debugID = debugId
      new Runnable {
        override def run(): Unit = {
          oSemantic.start()
          debug(s"($id, $ep) has finished")
        }
      }
    }
    oSemantics.foreach(o => assert(o != null))
    val threads = new Array[Thread](runables.size)
    for ((r, i) <- runables.zipWithIndex) {
      threads(i) = new Thread(r)
//      threads(i).start()
    }
    //pool.execute(r)
    (dummyNetwork, oSemantics, threads)
  }

  def bootstrapNetty(mainId: Int, pickId: Int, mainSesId: Int, debugId: Int, epFuns: (() => Object, Seq[Int])*)(implicit addr: EPAddr):
  (Array[OperationalSemantic[AbstractChannelType, TState]], Array[Thread]) = {
    val funs: Seq[(() =>Object,Seq[Int])] = epFuns.toSeq
    val allIds = funs.flatMap(x => x._2)
    assert(allIds.contains(mainId), s"Main id must be contained in endpoint ides")
    allIds.zipWithIndex.foreach(x => assert(x._1== x._2,s"Ids must be 0, 1, .., #participans -1 (ids are used for array access)"))

    val unroled = funs.flatMap(x => x._2.map(y => (x._1, y)))

    var roleMap = Map[Int, Role]()
    var roleSetMap = Map[Int, RoleSet]()
    var eps: List[(Int, AbstractEndPoint[AbstractChannelType, TState])] = Nil

    //fill id -> role and id -> roleset maps
    //created EP,Id Seq
    for (t <- unroled) {
      val ep = t._1().asInstanceOf[AbstractEndPoint[AbstractChannelType, TState]]
      val epId = t._2
      eps = (epId, ep) :: eps
      val rs = ep.roleSet
      if (t._2 == mainId) {
        val mDef = ep.subs.find(_.name.contains("Main")).get
        assert(mDef.argsC.size == 1)
        val mRole = mDef.argsC.head
        val pRole = mDef.argsP
        roleMap += ((mainId -> mRole), (pickId -> pRole))
      }
      roleSetMap += epId -> ep.roleSet
    }
    eps = eps.reverse
    val mainSes = SpawnMain(mainSesId, mainId, roleMap, roleSetMap)

    (new Thread(new Runnable {
      override def run(): Unit = {
        EndPoint.createNettyConnectionBootstrapManger(addr,mainSes)
      }
    })).start()


    val oSemantics = new Array[OperationalSemantic[AbstractChannelType, TState]](unroled.size)
    val runables = for ((id, ep) <- eps) yield {
      new Runnable {
        override def run(): Unit = {
          val oSemantic = EndPoint.createEPusingNetty(ep,addr,"127.0.0.1")
          oSemantics(id) = oSemantic
          oSemantic.debugID = debugId
          oSemantic.start()
        }
      }
    }
    val threads = new Array[Thread](runables.size)
    for ((r, i) <- runables.zipWithIndex) {
      threads(i) = new Thread(r)
    }
    ( oSemantics, threads)
  }
}
