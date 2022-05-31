package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import leetcode.AlgorithmProblems._

class AlgorithmProblemsFreeSpec extends AnyFreeSpec with Matchers {

  "Binary search" - {
    "target is not present" - {
      "with target larger than the largest in the array" in {
        AlgorithmProblems.search(Array(1, 2, 3), 4) shouldBe -1
      }
      "with target smaller than the smallest in the array" in {
        AlgorithmProblems.search(Array(1, 2, 3), 0) shouldBe -1
      }
      "with input array being empty" in {
        AlgorithmProblems.search(Array.emptyIntArray, 10) shouldBe -1
      }
    }
    "target is present" - {
      "return index" in {
        AlgorithmProblems.search(Array(0, 1, 2, 3, 6), 2) shouldBe 2
      }
      "target is the only element in array" in {
        AlgorithmProblems.search(Array(10), 10) shouldBe 0
      }
    }
  }

  "rotate array" - {
    "when rotate degree == 0" in {
      val a = Array(1, 2, 3, 4, 5)
      rotate(a, 0)
      a shouldBe Array(1, 2, 3, 4, 5)
    }
    "when rotate degree == 5" in {
      val a = Array(1, 2, 3, 4, 5)
      rotate(a, 0)
      a shouldBe Array(1, 2, 3, 4, 5)
    }
    "when rotate degree is smaller than the length(array)" in {
      val a = Array(1, 2, 3, 4, 5, 6, 7)
      rotate(a, 3)
      a shouldBe Array(5, 6, 7, 1, 2, 3, 4)
    }
    "when rotate degree is larger than the length(array) and rotatable" in {
      val a = Array(1, 2)
      rotate(a, 3)
      a should be(Array(2, 1))
    }
    "when rotate degree is larger than the length(array) and not rotatable" in {
      val a = Array(1, 2)
      rotate(a, 6)
      a should be(Array(1, 2))
    }    
  }
}
