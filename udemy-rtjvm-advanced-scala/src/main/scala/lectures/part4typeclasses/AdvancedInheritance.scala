package lectures.part4typeclasses

object AdvancedInheritance extends App {

  class Red {
    def print = println("red")
  }

  trait Green extends Cold {
    override def print = {
      println("green")
      super.print
    }
  }

  trait Blue extends Cold {
    override def print = {
      println("blue")
      super.print
    }
  }

  trait Cold {
    def print = println("cold")
  }

  class White extends Red with Green with Blue {
    override def print = {
      println("white")
      super.print
    }
  }

  val white = new White
  white.print

  class Item
  class Dress extends Item
  class Shoe  extends Item
  class InvariantBasket[T](e: T) {
    def add(e: T): InvariantBasket[T]    = ???
    def list[S <: T](): List[S] = ???

  }
  class Shirt extends Dress
  val dressBasket = new InvariantBasket[Dress](new Dress )
  dressBasket.add(new Dress)
  dressBasket.add(new Shirt)
  //dressBasket.add(new Shoe)
}
