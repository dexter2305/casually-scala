package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import StackProblems._
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
}
