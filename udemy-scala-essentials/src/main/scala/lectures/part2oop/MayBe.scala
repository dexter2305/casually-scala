package lectures.part2oop

object MayBe extends App {

  abstract class MayBe[+A] {

    def map[B](f: A => B): MayBe[B]

    def flatMap[B](f: A => MayBe[B]): MayBe[B]

    def filter(condition: A => Boolean): MayBe[A]
  }

  case object None extends MayBe[Nothing] {

    override def map[B](f: Nothing => B): MayBe[B] = None

    override def flatMap[B](f: Nothing => MayBe[B]): MayBe[B] = None

    override def filter(condition: Nothing => Boolean): MayBe[Nothing] = None


  }

  case class Just[+A](element: A) extends MayBe[A] {

    override def map[B](f: A => B): MayBe[B] = Just(f(element))

    override def flatMap[B](f: A => MayBe[B]): MayBe[B] = f(element)

    override def filter(condition: A => Boolean): MayBe[A] = if (condition(element)) Just(element) else None

  }
}
