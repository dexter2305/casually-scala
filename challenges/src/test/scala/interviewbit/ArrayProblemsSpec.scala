package interviewbit

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class ArrayProblemsSpec extends AnyFreeSpec with Matchers {
  import ArrayProblems._

  "First missing positive integer" - {
    "with only positive ints" - {
      "series starting with 1 should return size." in {
        firstMissingPositive(Array(1, 2, 3, 4)) should be(5)
      }
      "1 should be first positive integer." in {
        firstMissingPositive(Array(2, 3, 100, 1000)) should be(1)
      }
      "empty list should return 1" in {
        firstMissingPositive(Array.emptyIntArray) should be(1)
      }
      "missing intermediate positive number should be found." in {
        firstMissingPositive(Array(1, 2, 3, 5, 8)) should be(4)
      }
      "sequence with 0 should discount 0" in {
        firstMissingPositive(Array(0)) should be(1)
      }
      "sequence with 0 followed by positive integers" in {
        firstMissingPositive(Array(89, 0, 1, 6)) should be(2)
      }
      "sequence with all 1s should return 2" in {
        firstMissingPositive(Array(1, 1, 1)) should be(2)
      }
    }
    "with only negative ints" - {
      "1 should be the first positive int" in {
        firstMissingPositive(Array(-2, -1, -8, -19)) should be(1)
      }
    }
    "with mix of positives and negatives" - {
      "1 is the first positive number" in {
        firstMissingPositive(Array(-7, -19, -7, -9, 2, 7, 4, 100, 6)) should be(1)
      }
    }
  }

}
