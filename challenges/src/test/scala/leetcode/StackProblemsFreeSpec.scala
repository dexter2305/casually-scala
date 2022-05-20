package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import StackProblems._
import scala.util.Random
class StackProblemsFreeSpec extends AnyFreeSpec with Matchers {

  "Valid Paranthesis" - {
    "valid pairs" - {
      "with simple pairs" in {
        isValid("(){}[]") shouldBe true
      }
      "with complex pair" in {
        isValid("{[()]}") shouldBe true
      }
      "with complex pairs and simple pairs" in {
        isValid("{[()]}[](){}") should be (true)
      }
    }
    "invalid pairs" - {
      "with left only" in {
        isValid("(") should be (false)
      }
      "with right only" in {
        isValid(")") should be (false)
      }
      "with right preceding left" in {
        isValid(")(") should be (false)
      }
      "with left and right mixed" in {
        isValid("{]{}}[") should be (false)
      }
    }
  }

  "MyStack" - {
    "push & peek" - {
      "latest pushed should be returned but not evicted" in {
        val stack = new MyStack()
        stack.push(1)
        stack.push(2)
        stack.top() should be (stack.top())
      }
      "peek should not empty the stack" in {
        val stack = new MyStack()
        stack.push(1)
        stack.push(2)
        val r = scala.util.Random
        for (_ <- 0 to r.nextInt(10)) stack.top()
        stack.empty() should be (false)
      }
    }
    "push - pop" - {
      "latest push should be evicted" in {
        val stack = new MyStack()
        stack.push(1)
        stack.push(2)
        stack.pop() should be (2)
      }
    }
  }
}
