import cats._
import cats.implicits._

def concat[A](as: List[A], bs: List[A]): List[A] = as |+| bs

concat (List(1,2,3), List(4,5,6))