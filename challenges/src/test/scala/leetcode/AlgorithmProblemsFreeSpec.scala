package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class AlgorithmProblemsFreeSpec extends AnyFreeSpec with Matchers {

  "Binary search" - {
    "target is not present" - {
      "with target larger than the largest in the array" in {
        AlgorithmProblems.search(Array(1, 2, 3), 4) should be(-1)
      }
      "with target smaller than the smallest in the array" in {
        AlgorithmProblems.search(Array(1, 2, 3), 0) should be(-1)
      }
      "with input array being empty" in {
        AlgorithmProblems.search(Array.emptyIntArray, 10) should be (-1)
      }
    }
    "target is present" - {
      "return index" in {
        AlgorithmProblems.search(Array(0,1,2,3,6), 2) should be (2)
      }
      "target is the only element in array" in {
        AlgorithmProblems.search(Array(10), 10) should be (0)
      }
    }
  }
}
