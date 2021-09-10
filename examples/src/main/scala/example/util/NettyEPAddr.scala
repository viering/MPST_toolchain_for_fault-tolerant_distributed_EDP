package example.util

import event_lang.network.netty.EPAddr

object NettyEPAddr {
  val DEFAULT_BOOTSTRAP_ADDR = EPAddr("127.0.0.1", 22688)

  private var __port = 23218

  def getBootstrapAddr: EPAddr = {
    __port += 1
    EPAddr("127.0.0.1", __port)
  }
}
