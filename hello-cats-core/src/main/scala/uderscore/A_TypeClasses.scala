package underscore

object A_TypeClasses extends App {

  final case class Cat(name: String, age: Int, color: String)
  object Cat {

    implicit val printableCat = new Printable[Cat] {
      override def format(e: Cat): String = s"${e.name} is a ${e.age} year old ${e.color} cat"
    }

  }

  trait Printable[A] {
    def format(e: A): String
  }

  object PrintableInstances {

    implicit val printableString = new Printable[String] {
      override def format(e: String): String = e

    }
    implicit val printableInt    = new Printable[Int] {
      override def format(e: Int): String = s"$e"
    }

  }

  object PrintableSyntax {
    implicit class PrintableOps[A](v: A) {

      def format(implicit printable: Printable[A]): String = {
        printable.format(v)
      }

      def print(implicit printable: Printable[A]): Unit = println(s"${printable.format(v)}")
    }
  }

  object Printable {

    def format[A](e: A)(implicit printableInstance: Printable[A]): String = printableInstance.format(e)

    def print[A](e: A)(implicit printableInstance: Printable[A]): Unit = println(s"${printableInstance.format(e)}")
  }

  {
    // interface approach
    import PrintableInstances._
    Printable.print[String]("Hello")
    Printable.print[Int](42)
    Printable.print(Cat("Tom", 5, "grey"))
    //Printable.print(true)
  }

  {
    import PrintableSyntax._
    Cat("Tom", 10, "red").print
  }

}
