package lectures.part1as

object Recap extends App {

  //nothing type
  //val nothingType = throw new Exception

  //function as instance
  val incrementer = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  val pairs = for {
    n <- 1 to 3
    a <- 'a' to 'c'
  } yield (n, a)

  pairs.foreach(println(_))
}
