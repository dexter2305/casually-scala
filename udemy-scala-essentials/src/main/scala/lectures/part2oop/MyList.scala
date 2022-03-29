package lectures.part2oop

import scala.collection.immutable

/**
 * List of "INTEGERS"
 */
abstract class MyList {

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(e: Int): MyList
  def asString: String
  override def toString(): String = s"[${asString}]"
}

object Empty extends MyList {

  override def head: Int = throw new NoSuchElementException

  override def tail: MyList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(e: Int): MyList = new Cons(e, this)

  override def asString: String = ""

}

class Cons(h: Int, t: MyList) extends MyList {

  override def head: Int = h

  override def tail: MyList = t

  override def isEmpty: Boolean = false

  override def add(e: Int): MyList = new Cons(e, this)

  override def asString: String = if (this == Empty) this.asString else s" $head " + tail.asString

}

object ListDemo extends App {

  val ints = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(ints.toString())
  println(assert(ints.head == 1), "head should be 1")
  println(assert(ints.isEmpty == false), "ints should be empty")

}
