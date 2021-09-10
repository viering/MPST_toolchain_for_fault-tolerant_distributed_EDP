package event_lang.network.netty.internal

import java.util

import event_lang._
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class ObjDeserializeDecoder extends ByteToMessageDecoder {

  import intern.serialization.Serializer._

  override def decode(ctx: ChannelHandlerContext, in: ByteBuf,
                      out: util.List[AnyRef]): Unit = {
    if (in.readableBytes() < 2)
      return

    in.markReaderIndex()

    val len = in.readUnsignedShort()

    if (in.readableBytes() < len) {
      in.resetReaderIndex()
      return
    }

    val buf = new Array[Byte](len)
    in.readBytes(buf, 0, len)

    val o = buf.deserialize[Object]
    out.add(o)
  }
}
