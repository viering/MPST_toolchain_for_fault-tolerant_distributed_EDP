package example.faulty.twoBuyer.coverage

import example.util.EPRunner
import example.util.UIntput.readInput

import scala.io.StdIn

object TwoBuyerRunnerCoverageError {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1


    val buyer_b1 = 12
    val buyer_b2 =12

    val bookPrice = 30


    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new Seller(BOOK_ONE_Price = bookPrice), List(0)),
      (() => new Buyer(b1_max_pay = buyer_b1, b2_max_pay = buyer_b2), 1 to 2))

    ne._3.foreach(_.start())
  }
}
