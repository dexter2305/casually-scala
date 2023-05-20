package hackerrank

import scala.language.implicitConversions
object PolygonPerimeter extends App {

  import scala.io.StdIn
  case class Point(x: Int, y: Int)
  implicit def toPoint(ints: Array[Int]): Point = Point(ints(0), ints(1))
  val edgeCount                                 = StdIn.readInt()

  def perimeter(points: Seq[Point]): Double = {
    val points: Seq[Point] = for (_ <- 0 until edgeCount) yield StdIn.readLine().split(" ").map(_.toInt)
    val perimeter = (points.zip(points.tail) :+ (points.reverse.head -> points.head)).foldLeft(0.0) { (acc, pair) =>
      pair match {
        case (p1, p2) => math.sqrt(math.pow(p1.x - p2.x, 2) + math.pow(p1.y - p2.y, 2)) + acc
      }
    }
    perimeter
  }

}
