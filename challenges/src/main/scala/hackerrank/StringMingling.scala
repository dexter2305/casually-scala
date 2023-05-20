package hackerrank

object StringMingling extends App {

  def mingle(s1: String, s2: String): String =
    s1.zip(s2).map { case (c1, c2) => s"$c1$c2" }.mkString

  val p       = scala.io.StdIn.readLine()
  val q       = scala.io.StdIn.readLine()
  val mingled = mingle(p, q)
  println(mingled)

}
