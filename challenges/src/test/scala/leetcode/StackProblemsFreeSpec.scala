package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import StackProblems._
import scala.util.Random
class StackProblemsFreeSpec extends AnyFreeSpec with Matchers {

  "Valid Paranthesis" - {
    "valid pairs" - {
      "with simple pairs" in {
        isValid("(){}[]") mustBe true
      }
      "with complex pair" in {
        isValid("{[()]}") mustBe true
      }
      "with complex pairs and simple pairs" in {
        isValid("{[()]}[](){}") must be (true)
      }
    }
    "invalid pairs" - {
      "with left only" in {
        isValid("(") must be (false)
      }
      "with right only" in {
        isValid(")") must be (false)
      }
      "with right preceding left" in {
        isValid(")(") must be (false)
      }
      "with left and right mixed" in {
        isValid("{]{}}[") must be (false)
      }
    }
  }

  "MyStack" - {
    "push & peek" - {
      "latest pushed must be returned but not evicted" in {
        val stack = new MyStack()
        stack.push(1)
        stack.push(2)
        stack.top() must be (stack.top())
      }
      "peek should not empty the stack" in {
        val stack = new MyStack()
        stack.push(1)
        stack.push(2)
        val r = scala.util.Random
        for (_ <- 0 to r.nextInt(10)) stack.top()
        stack.empty() must be (false)
      }
    }
    "push - pop" - {
      "latest push must be evicted" in {
        val stack = new MyStack()
        stack.push(1)
        stack.push(2)
        stack.pop() must be (2)
      }
    }
  }
}
