package lectures.part2oop
package v3

import scala.collection.immutable

/**
 * List of "Generics" with map, filter and flatMap
 * v3 was initially a regular and is now upgrded to 'case class'
 */

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](e: B): MyList[B]
  def asString: String
  override def toString(): String = s"[${asString}]"

  def ++[B >: A](list: MyList[B]): MyList[B]

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(condition: MyPredicate[A]): MyList[A]

}

case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException

  override def tail: Nothing = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](e: B): MyList[B] = Cons(e, this)

  override def asString: String = ""

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def filter(condition: MyPredicate[Nothing]): MyList[Nothing] = Empty

  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](e: B): MyList[B] = Cons(e, this)

  override def asString: String = if (isEmpty) this.asString else s" $head " + tail.asString

  /*
    = [1, 2] ++ [3, 4]
    = new Cons(1, 2 ++ [3, 4])
    = new Cons(1, new Cons(2, Empty ++ [3, 4]))
    = new Cons(1, new Cons(2, [3, 4]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
   */
  override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t.++(list))

  override def filter(condition: MyPredicate[A]): MyList[A] =
    if (condition.test(head)) Cons(head, tail.filter(condition))
    else tail.filter(condition)

  override def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    Cons(transformer.transform(h), t.map(transformer))

  /*
    = [1, 2].flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, Empty].flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, 3] ++ Empty
    = [1, 2, 2, 3, Empty]
    = [1, 2, 2, 3]
   */
  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ tail.flatMap(transformer)

}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}
object ListDemo extends App {

  val ints: MyList[Int]       = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
  val strings: MyList[String] = Cons("helo ", Cons(" generics", Empty))
  println(ints.toString())
  println(strings.toString())
  println(assert(ints.head == 1), "head should be 1")
  println(assert(ints.isEmpty == false), "ints should be empty")

  val evenInts = ints.filter(new MyPredicate[Int] {

    override def test(element: Int): Boolean = element % 2 == 0

  })

  println(evenInts)

  // map (long form)
  val squareOfInts = ints.map(new MyTransformer[Int, String] {

    override def transform(element: Int): String = s"$element ^ 2 = ${element * element}"

  })
  // short form (syntactic sugar)
  val squared = ints.map(element => s"$element ^ 2 = ${element * element}")
  println(squareOfInts)

  //flatMap (long form)
  val intsPlus10 = ints.flatMap(new MyTransformer[Int, MyList[Int]] {

    override def transform(element: Int): MyList[Int] = Cons(element, Cons(element + 10, Empty))

  })
  //short form
  /*
  ints.flatMap{ n =>
    val x = new Cons(n, new Cons(n + 10, Empty))
    x
  }
   */
  println(intsPlus10)

}
