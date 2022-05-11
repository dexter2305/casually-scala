import cats.Show
object B_CatsShow extends App {

  final case class Cat(name: String, age: Int)
  implicit val x = new Show[Cat] {
    override def show(t: Cat): String = s"${t.name} is ${t.age} old cat."
  }

  val showString: Show[String] = Show.apply[String]
  val showInt: Show[Int]       = Show.apply[Int]

  val showCat_v1: Show[Cat] = Show.apply[Cat]
  val showCat_v2: Show[Cat] = Show[Cat] // apply method has special meaning
  val showCat_v3: Show[Cat] = Show.show[Cat](cat => s"${cat.name}")

  val b: Show[Boolean] = Show.apply[Boolean]
  b.show(true)

}
