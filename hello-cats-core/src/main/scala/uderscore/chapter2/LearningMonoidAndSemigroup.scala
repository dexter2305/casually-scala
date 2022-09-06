package uderscore.chapter2

object LearningMonoidAndSemigroup extends App {

  trait Semigroup[A] {
    def combine(a: A, b: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {

    def apply[A](implicit monoid: Monoid[A]) = monoid
  
    object instances {
    
      implicit val orMonoid: Monoid[Boolean] = new Monoid[Boolean](){
        override def combine(a: Boolean, b: Boolean): Boolean = a || b
        override def empty: Boolean = false
      }
      implicit val andMonoid: Monoid[Boolean] = new Monoid[Boolean](){
        override def combine(a: Boolean, b: Boolean): Boolean = ???
        override def empty: Boolean = true
      }
      implicit val intMonoid: Monoid[Int] = new Monoid[Int](){

        override def combine(a: Int, b: Int): Int = a + b

        override def empty: Int = 1

      }
    }

  }

  

}
