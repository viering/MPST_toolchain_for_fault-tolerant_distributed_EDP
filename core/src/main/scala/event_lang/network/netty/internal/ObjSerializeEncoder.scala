package event_lang.network.netty.internal

import event_lang._
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder




class ObjSerializeEncoder extends MessageToByteEncoder[Object] {

  import intern.serialization.Serializer._

  override def encode(ctx: ChannelHandlerContext, msg: Object,
                      out: ByteBuf): Unit = {

    val bArray = msg.serialize
    out.writeShort(bArray.length)
    out.writeBytes(bArray)
  }
}