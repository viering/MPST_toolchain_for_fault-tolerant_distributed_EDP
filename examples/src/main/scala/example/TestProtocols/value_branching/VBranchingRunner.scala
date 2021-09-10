package example.TestProtocols.value_branching

import example.util.EPRunner

import scala.io.StdIn


object VBranchingRunner {
  def main(args: Array[String]): Unit = {
    import example.util.UIntput._

    println(s"""In this use case a 'Buyer' wants to buy a book from a 'Seller'""")

    println("""Please provide the amount the 'Buyer' is at most willing to pay for the book""")
    val buyerMaxBookPay = readInput({
      StdIn.readInt()
    }, "Please provide an integer value")
    println("""Please provide the price of the book (used by the 'Seller')""")
    val bookPrice = readInput({
      StdIn.readInt()
    }, "Please provide an integer value")

    println("""The output is: 'OK()' if the is bought or 'No()' otherwise""")
    println("# Program out put: ")



    val eps = EPRunner.bootstrap(0, 1, 0, 0,
      (() => new EP_Seller(bPrice = bookPrice), List(0)), (() => new EP_Buyer(maxPrice = buyerMaxBookPay), List(1)))
    eps._3.foreach(_.start())
    eps._3.foreach(_.join())
  }

}