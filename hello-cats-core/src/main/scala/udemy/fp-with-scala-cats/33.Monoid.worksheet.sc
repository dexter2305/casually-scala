import cats._
import cats.implicits._

case class Speed(metersPerSecond: Long)
object Speed {

  implicit val combineSpeed: Monoid[Speed] = Monoid.instance[Speed](Speed(0), (x: Speed, y: Speed) => Speed(x.metersPerSecond + y.metersPerSecond))
}

val a = Speed(100)
val b = Speed(200)

// combine
// long form
Monoid[Speed].combine(a, b)
// short form
a |+| b

// combine on list
// with instance
Monoid[Speed].combineAll(List(Speed(1), Speed(2), Speed(3), Speed(4)))
// extension method
List(Speed(1), Speed(2), Speed(3), Speed(4)).combineAll

// empty
Monoid[Speed].empty

// exercises

val sumMonoid: Monoid[Int]         = Monoid.instance[Int](0, _ + _)
val minMonoid: Monoid[Int]         = Monoid.instance[Int](Int.MaxValue,  _ min _)
def listMonoid[A]: Monoid[List[A]] = Monoid.instance(Nil, _ ++ _)
val stringMonoid: Monoid[String]   = Monoid.instance[String]("", _ + _)

// tests 
sumMonoid.combine(2, 3)
minMonoid.combine(100, minMonoid.empty)
stringMonoid.combine("hello", "cats")
listMonoid[Int].combine(List(1,2,3,4), List(5,6,7,8))