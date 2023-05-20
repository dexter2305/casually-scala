package hackerrank

object StringOPermute extends App {

  def stringOPermute(s: String): String =
    (for (i <- 0 until s.length() by 2) yield s.substring(i, i + 2).reverse).mkString

  println(s"'${stringOPermute("")}'")

  for (_ <- 0 until scala.io.StdIn.readInt()) {
    val t = scala.io.StdIn.readLine()
    val o = stringOPermute(t)
    println(o)
  }

}
