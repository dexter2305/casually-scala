package uderscore.chapter2
import cats._
import cats.implicits._

object CatsMonoidApp extends App {

  def add_simple(items: List[Int]): Int = items.fold(0)(_ + _)

  // implementation specific to List[Int]
  def add_withMonoid(items: List[Int]): Int = items.fold(Monoid[Int].empty)((a, b) => a |+| b)

  // NOTE: generic add. works for Int, OptionInt
  def add_withGenericMonoid[A](items: List[A])(implicit m: Monoid[A]): A = items.fold(m.empty)(_ |+| _)

  // test simple/builtin types
  val y = add_withGenericMonoid(List(Option(2), Option(3), Option.empty[Int]))
  println(y)

  val x = add_withGenericMonoid(List(List(1, 2, 3), List('a')))
  println(x)

  case class Order(totalCost: Double, quantity: Double)
  object Order {
    object instances {
      implicit val monoidOrder: Monoid[Order] = new Monoid[Order]() {
        override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)

        override def empty: Order = Order(0, 0)
      }

    }
  }

  import Order.instances._

  // test custom types. 
  val o1 = Order(100, 10)
  val o2 = Order(200, 20)
  val z  = o1 |+| o2
  println(z)

}
