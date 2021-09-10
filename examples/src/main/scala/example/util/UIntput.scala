package example.util

import scala.io.StdIn


object UIntput {
  case class MaxTries(n : Int)
  implicit val MAX_TRIES  = MaxTries(5)

  implicit def boolToFunBool[T](b : Boolean) : T => Boolean ={
    x : T => b
  }

  def readInput[T](read : => T, errMST : String )(implicit maxTries: MaxTries):T={
    readInput(read,{true},errMST)(maxTries)
  }
  def readInput[T](read : => T,verifier : T => Boolean, errMSG : String)(implicit maxTries: MaxTries) : T = {
    assert(maxTries.n > 1)
    var cnt = 1
    var res : Option[T] = None

    while(res.isEmpty && cnt < maxTries.n){
      try{
        val input = read
        if(verifier(input)){
          res = Some(input)
        }else{
          println(errMSG)
        }
      }catch{
        case e : Exception =>
          println(errMSG)
      }
      cnt += 1
    }
    res.get
  }

}
