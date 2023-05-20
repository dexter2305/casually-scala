package hackerrank

import scala.io.StdIn

object FunctionOrNot extends App {

  for (test <- 0 until StdIn.readInt()) {
    val intPairSeq = for {
      elementCount <- 0 until StdIn.readInt()
      data        = StdIn.readLine()
      stringArray = data.split(" ")
      intPair     = (stringArray(0).toInt, stringArray(1).toInt)
    } yield intPair
    if (isFunction(intPairSeq)) println("YES") else println("NO")
  }

  def isFunction(xform: Seq[(Int, Int)]): Boolean = !xform.groupBy(_._1).exists(groups => groups._2.length > 1)

}
