import scala.util.Random

val r = Random
val x = (0 until(5 * math.pow(10, 4).toInt)).toList.map(_ => (r.between(0, 26) + 64).toChar).mkString
x.length