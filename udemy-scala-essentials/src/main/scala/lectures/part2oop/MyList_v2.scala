package lectures.part2oop
package v2

import scala.collection.immutable

/**
 * List of "Generics"
 */
abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](e: B): MyList[B]
  def asString: String
  override def toString: String = s"[${asString}]"
}

object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException

  override def tail: Nothing = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](e: B): MyList[B] = new Cons(e, this)

  override def asString: String = ""

}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](e: B): MyList[B] = new Cons(e, this)

  override def asString: String = if (this == Empty) this.asString else s" $head " + tail.asString

}

object ListDemo extends App {

  val ints: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val strings: MyList[String] = new Cons("helo ", new Cons(" generics", Empty))
  println(ints.toString())
  println(strings.toString())
  println(assert(ints.head == 1), "head should be 1")
  println(assert(ints.isEmpty == false), "ints should be empty")

}
