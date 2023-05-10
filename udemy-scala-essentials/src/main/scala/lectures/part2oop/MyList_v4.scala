package lectures.part2oop
package v4

import scala.collection.immutable

/*
 * List of "Generics" with map, filter and flatMap
 * v3 => initially a regular and is now upgrded to 'case class'
 * v4 => application of functions.
 *
 */

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](e: B): MyList[B]
  def asString: String
  override def toString(): String = s"[${asString}]"

  def ++[B >: A](list: MyList[B]): MyList[B]

  //def map[B](transformer: Function1[A, B]): MyList[B]
  //def flatMap[B](transformer: Function1[A, MyList[B]]): MyList[B]
  //def filter(condition: Function1[A, Boolean]): MyList[A]
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(condition: A => Boolean): MyList[A]

  //HOFs
  def foreach(f: A => Unit): Unit = {
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
  }

  def sort(compare: (A, A) => Int): MyList[A]
}

case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException

  override def tail: Nothing = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](e: B): MyList[B] = Cons(e, this)

  override def asString: String = ""

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def filter(condition: Nothing => Boolean): MyList[Nothing] = Empty

  override def map[B](transformer: Nothing => B): MyList[B] = Empty

  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

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

  override def filter(condition: A => Boolean): MyList[A] =
    if (condition(head)) Cons(head, tail.filter(condition))
    else tail.filter(condition)

  override def map[B](transformer: A => B): MyList[B] =
    Cons(transformer(h), t.map(transformer))

  /*
    = [1, 2].flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, Empty].flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, 3] ++ Empty
    = [1, 2, 2, 3, Empty]
    = [1, 2, 2, 3]
   */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ tail.flatMap(transformer)

  def sort(compare: (A, A) => Int): MyList[A] = ???

}

object ListDemo extends App {

  val ints: MyList[Int]       = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
  val strings: MyList[String] = Cons("helo ", Cons(" generics", Empty))
  println(ints.toString())
  println(strings.toString())
  println(assert(ints.head == 1), "head must be 1")
  println(assert(ints.isEmpty == false), "ints must be empty")

  // filter
  val evenInts = ints.filter(_ % 2 == 0)

  println(evenInts)

  // map
  // short form (syntactic sugar)
  val squared = ints.map(element => s"$element ^ 2 = ${element * element}")
  println(squared)

  //flatMap
  //short form
  val intsPlus10 = ints.flatMap(n => Cons(n, Cons(n + 10, Empty)))
  println(intsPlus10)

  //hofs
  ints.foreach(x => print(s" '$x'"))
  println("")

  //for-comprehension
  val numbers = Cons(1, Cons(2, Cons(3, Empty)))
  val chars = Cons('a', Cons('b', Cons('c', Empty)))
  val colors = Cons("red", Cons("blue", Cons("green", Empty)))

  val forCombos = for {
    n <- numbers
    c <- chars
    co <- colors
  } yield(s"$n-$c-$co")
  println(forCombos.toString())

}
