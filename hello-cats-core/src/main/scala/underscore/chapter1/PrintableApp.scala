package undersscore.chapter1

object PrintableApp extends App {

  trait Printable[A] {
    def format(a: A): String
  }

  object Printable {

    //instance
    def instance[A](f: A => String): Printable[A] = new Printable[A]() {
      override def format(a: A): String = f(a)
    }

    //summoner
    def apply[A](implicit printableInstance: Printable[A]): Printable[A] = printableInstance

    //api
    def print[A](a: A)(implicit as: Printable[A]): Unit = println(s"${as.format(a)}")

    object PrintableInstances {

      // implicit def printableString: Printable[String] = new Printable[String]() {
      //   override def format(a: String): String = a
      // }

      // implicit def printableInt: Printable[Int] = new Printable[Int]() {
      //   override def format(a: Int): String = s"$a"
      // }

      implicit def printableString: Printable[String] = instance[String](string => s"string: ${string}")
      implicit def printableInt: Printable[Int]       = instance[Int](int => s"int: $int")
      implicit def printableBoolean                   = instance[Boolean](b => s"boolean: $b")

      implicit def printableOption[A](implicit pa: Printable[A]): Printable[Option[A]] = new Printable[Option[A]]() {
        def format(a: Option[A]): String = a match {
          case None    => "option: <none>"
          case Some(x) => s"option: ${pa.format(x)}"
        }
      }
    }

    object PrintableSyntax {
      implicit class PrintableOps[A](a: A) {
        def format(implicit p: Printable[A]): String = p.format(a)
        def print(implicit p: Printable[A]): Unit    = println(p.format(a))
      }
    }
  }

  case class Cat(name: String, age: Int, color: String)
  object Cat {
    import Printable._
    implicit def printableTeam(implicit ps: Printable[String]) =
      instance[Cat](cat => ps.format(s"${cat.name} is a ${cat.age} year old ${cat.color} cat"))
  }

  // tests
  import Printable.PrintableInstances._
  Printable.print("Learning type classes")
  Printable.print(10)
  Printable.print(Cat("Tom", 3, "grey"))
  Printable.print(Option(1))
  Printable.print(Option(Cat("Razor", 10, "white")))
  Printable.print(Option.empty[Int])
  //Printable.print(1L)

  import Printable.PrintableSyntax._
  val alpine = Cat("TBone", 8, "black")
  alpine.print

}
