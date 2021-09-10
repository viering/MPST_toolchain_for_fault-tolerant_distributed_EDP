package event_lang.network.netty.internal

import event_lang.network.SpawnMain


trait NettyLayerRcvAPi[T] {
  /**
   * will be called concurrently
   *
   * @param m
   */
  def rcvMSG(m: T)

  def init(sndLayer: NettyLayerSndApi[T])
}

trait NettyLayerSndApi[T] {
  def ready: Boolean

  def mainSessionInfo: SpawnMain

  def snd(toId: Int, msg: T): Unit

  def broadcast(msg: T): Unit

  def myID: Int

  def shutdown(): Unit
}








