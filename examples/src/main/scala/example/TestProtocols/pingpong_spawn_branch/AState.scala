package example.TestProtocols.pingpong_spawn_branch

import event_lang._
import dsl.TState

case class AState(pingCnt : Int = 0) extends TState
