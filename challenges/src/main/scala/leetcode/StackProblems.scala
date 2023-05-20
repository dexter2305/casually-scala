package leetcode

object StackProblems extends App {

  val stack = new MyStack()
  stack.push(1)
  stack.push(2)
  stack.push(3)

  // https://leetcode.com/problems/valid-parentheses/
  def isValid(s: String): Boolean = {
    @scala.annotation.tailrec
    def loop(s: String, i: Int, stack: List[Char]): Boolean =
      if (i == s.length()) stack.size == 0
      else
        s(i) match {
          case '[' | '(' | '{' => loop(s, i + 1, s(i) +: stack)
          case ']'             => if (stack.size > 0 && stack.head == '[') loop(s, i + 1, stack.tail) else false
          case ')'             => if (stack.size > 0 && stack.head == '(') loop(s, i + 1, stack.tail) else false
          case '}'             => if (stack.size > 0 && stack.head == '{') loop(s, i + 1, stack.tail) else false
        }
    loop(s, 0, List.empty[Char])
  }
  // https://leetcode.com/problems/implement-stack-using-queues/
  class MyStack {

    // pop friendly Stack
    import scala.collection.mutable.Queue
    // private val data = Queue[Int]()

    /*
        - enqueue the new element; say at pos 'n' - meaning nth dequeue should evict 'n' from queu
        - dequeue (n-1) elements and enqueue them back to queue
     */
    val data = scala.collection.mutable.Queue.empty[Int]

    def push(x: Int): Unit = {
      data.enqueue(x)
      for (_ <- 0 until data.size - 1)
        data.enqueue(data.dequeue())
      // println(s"$data")
    }

    def pop(): Int = data.dequeue()

    def top(): Int = data.front

    def empty(): Boolean = data.isEmpty

  }

}
