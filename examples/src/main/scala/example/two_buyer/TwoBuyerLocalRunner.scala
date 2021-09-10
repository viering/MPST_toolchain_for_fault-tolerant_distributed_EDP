package example.two_buyer


import example.util.EPRunner
import example.util.UIntput.readInput

import scala.io.StdIn

object TwoBuyerLocalRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1


    println(s"""In this use case two buyer try to buy a book from a seller""")

    println("""Please provide the amount the buyer 'a' is at most willing to pay for the book""")
    val buyer_b1 = readInput({
      StdIn.readInt()
    }, "Please provide an integer value")


    println("""Please provide the amount the buyer 'b' is at most willing to pay for the book""")
    val buyer_b2 = readInput({
      StdIn.readInt()
    }, "Please provide an integer value")


    println("""Please provide the price of the book""")
    val bookPrice = readInput({
      StdIn.readInt()
    }, "Please provide an integer value")

    println("""The output is: 'the book will arrive ..' if the book is bought or 'Canceled Transaction' otherwise""")
    println("# Program out put: ")


    val ne = EPRunner.bootstrap(mainId, pickId, 0, 0,
      (() => new Seller(BOOK_ONE_Price = bookPrice), List(0)),
      (() => new Buyer(b1_max_pay = buyer_b1, b2_max_pay = buyer_b2), 1 to 2))

    ne._3.foreach(_.start())
  }
}
