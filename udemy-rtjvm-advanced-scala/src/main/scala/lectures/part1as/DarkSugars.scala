package lectures.part1as

object DarkSugars extends App {

  // 1 - single arg method can take expression
  def singleArgMethod(name: String): String = s"hello, $name"

  val v = singleArgMethod {
    println("")
    "42"
  }

  //2. single abstract method #SAM
  //also application for abstract classes with only one abstract method - Duh ! SAM
  trait Action {
    def act(i: Int): String
  }
  val usual = new Action {
    override def act(i: Int): String = "something"
  }

  val functional: Action = (x: Int) => "functional somehing"

  //3. The '::' and '#::'  sugar.
  //scala specs; operator ending with ':' are right associative
  val l = 1 :: 2 :: Nil

  class MyCollection[T] {
    def ->:(value: T): MyCollection[T] = this
  }
  val lx = 1 ->: 2 ->: new MyCollection[Int]
  println(s"${l.mkString("-")}")

  //#4 multi word method naming
  object Kids {
    def `and then said`(msg: String): String = ???
  }

  Kids `and then said` "hello"

  //#5 Infix types
  class MyClass[Int, String]
  val composite: Int MyClass String = ???

  class Person(name: String)
  class XPerson(name: String, x: String)
  trait -->[typeA, typeB] {
    def act(ta: typeA): typeB
  }
  val legacyCon: -->[Int, Float] = ???
  val converter: Int --> Float   = ???

}
