package leetcode

object QueueProblems extends App {

  // https://leetcode.com/problems/implement-queue-using-stacks/
  class MyQueue() {

    // Pop (amortized) friendly queue
    import scala.collection.mutable.Stack
    private val pushStack = new Stack[Int]()
    private val popStack  = new Stack[Int]()

    def push(x: Int) = pushStack.push(x)

    def peek(): Int      = {
      if (popStack.isEmpty) while (pushStack.nonEmpty) popStack.push(pushStack.pop())
      popStack.top
    }
    def pop(): Int       = {
      if (popStack.isEmpty) while (pushStack.nonEmpty) popStack.push(pushStack.pop())
      popStack.pop()
    }
    def empty(): Boolean = pushStack.isEmpty && popStack.isEmpty

  }
}
