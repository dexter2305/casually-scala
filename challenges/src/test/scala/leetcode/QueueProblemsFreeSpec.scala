package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class QueueProblemsFreeSpec extends AnyFreeSpec with Matchers {
  
  def qu = new QueueProblems.MyQueue()

  "MyQueue" - {
    "when initialized" - {
      "queue is empty" in {
        val queue = new QueueProblems.MyQueue()
        queue.empty() should be (true)
      }
    }
    "peek"- {
      "on push" in {
        val q = qu
        q.push(1)
        q.peek()  should be (1)
      }
    }
    "pop" - {
      "on push" in {
        val q = qu
        q.push(1)
        q.pop() should be (1)
        q.empty() should be (true)
      }
    }
  }

}
