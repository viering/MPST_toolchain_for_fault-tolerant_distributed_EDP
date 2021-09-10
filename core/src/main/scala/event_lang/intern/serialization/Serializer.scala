package event_lang.intern.serialization

import com.twitter.chill.KryoInjection

import scala.util.Success

object Serializer {


  def serialize[T](o: T): Array[Byte] = {
    val bytes: Array[Byte] = KryoInjection(o)
    bytes
  }

  def deserialize[T](bytes: Array[Byte]): T = KryoInjection.invert(bytes) match {
    case Success(v) => v.asInstanceOf[T]
    case _ => ???
  }


  implicit class BArrayWrapper(bytes: Array[Byte]) {
    def deserialize[T]: T = KryoInjection.invert(bytes) match {
      case Success(v) => v.asInstanceOf[T]
      case _ => ???
    }
  }

  implicit class ObjSerWrapper(o: Object) {
    def serialize: Array[Byte] = {
      val bytes: Array[Byte] = KryoInjection(o)
      bytes
    }
  }

}