package example.esop_crash_handling.streaming

import event_lang.dsl.{AbstractChannelImp, AbstractChannelType, AbstractEndPointTesting, TState}
import example.esop_crash_handling.streaming.types.Streaming.DFS._
import example.esop_crash_handling.streaming.types.Streaming.MESSAGES.Main.{PrepSpawn, Spawn}
import example.esop_crash_handling.streaming.types.Streaming.MESSAGES.Partition.{DM, WorkLoad}
import example.esop_crash_handling.streaming.types.Streaming.W._
import example.esop_crash_handling.streaming.types.Streaming.ZK._
import example.esop_crash_handling.streaming.types.Streaming.MESSAGES._



case class ZkState(numToSpawn : Int = 2, numSpawns : Int = 0) extends TState
class EP_ZK(sState: ZkState = ZkState()) extends EPType_ZK[ZkState] {

  implicit def chToId(c: AbstractChannelImp): Long = {
    c.session.sesId
  }

  override def onStartUp: ZkState = sState

  override val receive = ELoop(
    λ_state(Main_zk_ZK.SndSpawn, Main_zk_ZK.SndPrepSpawn) {
      case (s, c) if s.numSpawns < s.numToSpawn =>
        (s.copy(numSpawns = 1 + s.numSpawns), c ! Spawn() ! PrepSpawn())
    },
    λ(Main_zk_ZK.SndEnd, Main_zk_ZK.SndEndP) {
      case c => c ! Main.End() ! Main.EndP()
    },
  λ(Main_zk_ZK.Failed_dfs_DFS,Main_zk_ZK.SndDM){
    case c => c.failed_dfs_DFS() ! Main.DM()
  })

}

case class DFSState() extends TState
class EP_DFS(sState: DFSState = DFSState()) extends EPType_DFS[DFSState] {

  implicit def chToId(c: AbstractChannelImp): Long = {
    c.session.sesId
  }

  override def onStartUp: DFSState = sState

  override val receive = ELoop(
    λ(Main_dfs_DFS.RcvEnd) {
      case c =>
        println(s"[DFS] received end in Main")
        c.channelCon
    },
    λ(Main_dfs_DFS.RcvSpawn) {
      case c => c.channelCon
    },
    λ_static(Main_dfs_DFS.SpawnPartition){
      case c => c
    },
    λ(Partition_dfs_DFS.SndWorkLoad){
      case c =>
        c ! WorkLoad()
    },
    λ(Partition_dfs_DFS.RcvResult){
      case c =>
        println(s"[DFS] received result")
        Thread.sleep(100)
        c.channelCon
    },
    λ(Partition_dfs_DFS.Failed_w_W,Partition_dfs_DFS.SndDM){
      case c =>
        c.failed_w_W() ! Partition.DM()
    },
    λ_static(Partition_dfs_DFS.SpawnPartition){
      case  c => c
    }
  )
}


case class WState(numSpawn : Int = 2) extends TState
class EP_W(val block: (AbstractChannelType, WState, AbstractChannelImp) => Boolean = (d, x, s) => false,
           val customCode: (AbstractChannelType, WState, AbstractChannelImp) => Unit = (a, b, c) => {},
           sState: WState = WState()) extends EPType_W[WState] with AbstractEndPointTesting[__EPType_W, WState]{

  implicit def chToId(c: AbstractChannelImp): Long = {
    c.session.sesId
  }

  override def onStartUp: WState = sState

  override val receive = ELoop(
    λ(Main_W.RcvPrepSpawn) {
      case c => c.channelCon
    },
    λ(Main_W.RcvEndP){
      case c => c.channelCon
    },
    λ(Main_W.RcvDM){
      case c => c.channelCon
    },
    λ(Partition_w_W.RcvWorkLoad,Partition_w_W.SndResult){
      case c =>
        println(s"[w:${c.session.sesId}] Rcv WorkLoad .. Processing .. Snd Result")
        c.channelCon ! Partition.Result()
    },
    λ(Partition_W.RcvDM){
      case c => c.channelCon
    },
    λ_static(Partition_W.SpawnPartition){
      case c => c
    }
)

}
