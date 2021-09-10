package example.TestProtocols.simple_rec

import example.util.EPRunner


object SimpleRecRunner {
  def main(args: Array[String]): Unit = {
    val eps = EPRunner.bootstrap(0, 1, 0,0,
      (() => new EP_A(), List(0)), (() => new EP_B, List(1)))
    eps._3.foreach(_.start())
    eps._3.foreach(_.join())
  }
}
