package example.three_buyer_global_progress

import example.util.EPRunner
import example.util.UIntput.readInput

import scala.io.StdIn

object ThreeBuyerRunner {
  def main(args: Array[String]): Unit = {
    val mainId = 0
    val pickId = 1

    println(s"""In this use case three buyer try to buy a book from a seller""")

    println("""Please provide the amount buyer 'a' is willing to pay""")
    val buyer_1 = readInput({
      StdIn.readInt()
    }, "Please provide an integer value")

    println("""Please provide the amount buyer 'b' is willing to pay""")
    val buyer_2 = readInput({
      StdIn.readInt()
    }, "Please provide an integer value")


    println("""Please provide the amount buyer 'c' is willing to pay""")
    val buyer_3 = readInput({
      StdIn.readInt()
    }, "Please provide an integer value")


    println("""Please provide the price of the book the three buyer try to buy""")
    val bookPrice = readInput({
      StdIn.readInt()
    }, "Please provide an integer value")

    println("""The output is: 'Book will arrive soon...' if the book is bought or 'Canceled Transaction' otherwise""")
    println("# Program out put: ")

    val ne = EPRunner.bootstrap(mainId, pickId,0,0,
      (() => new EP_A(A_AMOUNT = buyer_1), List(0)), (() => new EP_B(B_AMOUNT = buyer_2), List(1)),
      (() => new EP_C(C_AMOUNT = buyer_3), 2 to 3), (() => new EP_S(PRICE = bookPrice), 4 to 5))


    ne._3.foreach(_.start())
  }
}
