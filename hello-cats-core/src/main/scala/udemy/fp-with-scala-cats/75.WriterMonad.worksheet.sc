import scala.util.Random
import cats._
import cats.implicits._
import cats.data._

// writer construction
type Tracked[A] = Writer[List[String], A]
7.pure[Tracked]
val x = 10.writer[List[String]](List("a 10"))
Writer(List("intialize"), 100)

// reset the writer
x.reset
// open for op in the context of writer
x.listen

// returns ONLY the value
x.value
// returns logs and value
val (logs: List[String], n: Int) = x.run

// map affects only value
x.map(_ + 69)
// flatmap can affect value and log
x.flatMap(x => (x + 10).writer(List("add another 10")))
// mapN affects the value and log
val y = Writer(List("an eight"), 8)
(x, y).mapN(_ + _)

x |+| y

// use case

