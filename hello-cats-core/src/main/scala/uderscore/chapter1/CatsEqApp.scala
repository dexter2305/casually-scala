package uderscore.chapter1

import cats.Eq
object CatsEqApp extends App {

  final case class Cat(name: String, age: Int, color: String)

  object Cat {

    object instances {
      import cats.syntax.eq._
      implicit val eqCat = Eq.instance[Cat] { (a, b) =>
        a.name === b.name &&
        a.age === b.age &&
        a.color === b.color
      }

    }
  }

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  import cats.syntax.eq._
  import Cat.instances._
  assert(cat1 =!= cat2, "cats are different")
  assert(cat1 === cat1, "cats are same")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]
  assert(optionCat1 =!= optionCat2, "optional cats are different")
}
