package uderscore.chapter1
import cats.Show
import cats.syntax.show._
object CatsShowApp extends App {

  final case class Cat(name: String, age: Int, color: String)
  object Cat {
    object instances {
      implicit def catshow: Show[Cat] = Show.show(cat => s"${cat.name} is a ${cat.age} year old ${cat.color} cat.")
    }
    // NOTE: Syntax implementation is provided by cats.syntax.show._
    // object syntax    {
    //   implicit class CatOps[Cat](cat: Cat) {
    //     def show(implicit sc: Show[Cat]): String = sc.show(cat)
    //   }
    // }
  }

  val tom     = Cat("Tom", 3, "grey")
  import Cat.instances._
  val showCat = Show[Cat]
  println(s"with instance approach: ${showCat.show(tom)}")

  //import Cat.syntax._
  //import cats.syntax.show._
  println(s"with syntax approach: ${tom.show}")
}
