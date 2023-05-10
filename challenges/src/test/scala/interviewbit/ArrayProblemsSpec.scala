package interviewbit

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class ArrayProblemsSpec extends AnyFreeSpec with Matchers {
  import ArrayProblems._

  "First missing positive integer" - {
    "with only positive ints" - {
      "series starting with 1 should return size." in {
        firstMissingPositive(Array(1, 2, 3, 4)) must be(5)
      }
      "1 must be first positive integer." in {
        firstMissingPositive(Array(2, 3, 100, 1000)) must be(1)
      }
      "empty list should return 1" in {
        firstMissingPositive(Array.emptyIntArray) must be(1)
      }
      "missing intermediate positive number must be found." in {
        firstMissingPositive(Array(1, 2, 3, 5, 8)) must be(4)
      }
      "sequence with 0 should discount 0" in {
        firstMissingPositive(Array(0)) must be(1)
      }
      "sequence with 0 followed by positive integers" in {
        firstMissingPositive(Array(89, 0, 1, 6)) must be(2)
      }
      "sequence with all 1s should return 2" in {
        firstMissingPositive(Array(1, 1, 1)) must be(2)
      }
    }
    "with only negative ints" - {
      "1 must be the first positive int" in {
        firstMissingPositive(Array(-2, -1, -8, -19)) must be(1)
      }
    }
    "with mix of positives and negatives" - {
      "1 is the first positive number" in {
        firstMissingPositive(Array(-7, -19, -7, -9, 2, 7, 4, 100, 6)) must be(1)
      }
    }
  }

}
