package leetcode

import leetcode.AlgorithmProblems._
import org.scalatest.prop.TableDrivenPropertyChecks

class AlgorithmProblemsWordSpec extends testtypes.UnitTestWordSpec with TableDrivenPropertyChecks {

  "Binary search" when {
    "target is not present" must {
      "return -1 with target larger than the largest in the array" in {
        AlgorithmProblems.search(Array(1, 2, 3), 4) mustBe -1
      }
      "return -1 with target smaller than the smallest in the array" in {
        AlgorithmProblems.search(Array(1, 2, 3), 0) mustBe -1
      }
      "return -1 with input array being empty" in {
        AlgorithmProblems.search(Array.emptyIntArray, 10) mustBe -1
      }
    }
    "target is present" must {
      "return expected index in the given array" in {
        AlgorithmProblems.search(Array(0, 1, 2, 3, 6), 2) mustBe 2
      }
      "return index when target is the only element in array" in {
        AlgorithmProblems.search(Array(10), 10) mustBe 0
      }
    }
  }

  // "rotate array" when {
  //   "when rotate degree == 0" in {
  //     val a = Array(1, 2, 3, 4, 5)
  //     rotate(a, 0)
  //     a mustBe Array(1, 2, 3, 4, 5)
  //   }
  //   "when rotate degree == 5" in {
  //     val a = Array(1, 2, 3, 4, 5)
  //     rotate(a, 0)
  //     a mustBe Array(1, 2, 3, 4, 5)
  //   }
  //   "when rotate degree is smaller than the length(array)" in {
  //     val a = Array(1, 2, 3, 4, 5, 6, 7)
  //     rotate(a, 3)
  //     a mustBe Array(5, 6, 7, 1, 2, 3, 4)
  //   }
  //   "when rotate degree is larger than the length(array) and rotatable" in {
  //     val a = Array(1, 2)
  //     rotate(a, 3)
  //     a must be(Array(2, 1))
  //   }
  //   "when rotate degree is larger than the length(array) and not rotatable" in {
  //     val a = Array(1, 2)
  //     rotate(a, 6)
  //     a must be(Array(1, 2))
  //   }
  // }

  "Move zeroes" when {
    val exMoveZeroes_case1 = Table(
      ("testcase", "input", "answer"),
      ("not change input array when no zero is present", Array(1, 2, 3, 4, 5), Array(1, 2, 3, 4, 5)),
      ("not change input array when array has only zeroes", Array(0, 0, 0, 0), Array(0, 0, 0, 0)),
      ("alter input array with a single 0 in the body of array", Array(1, 0, 2, 3), Array(1, 2, 3, 0)),
      ("alter input array with multiple sequential zeroes", Array(1, 0, 0, 0, 2, 3), Array(1, 2, 3, 0, 0, 0)),
      ("alter input array with fragmented zeroes", Array(1, 0, 2, 0, 3), Array(1, 2, 3, 0, 0)),
      ("alter input array containing leading zeroes in sequence", Array(0, 0, 0, 1), Array(1, 0, 0, 0))
    )
    val exCase2            = Table(
      ("testcase", "input", "answer"),
      ("not alter input array with non-zero", Array(10), Array(10)),
      ("not alter input array with only 0", Array(0), Array(0))
    )
    "array size > 1" must {
      forAll(exMoveZeroes_case1) { (testcase: String, input: Array[Int], answer: Array[Int]) =>
        s"$testcase:  [${input.mkString("-")}] => [${answer.mkString("-")}]" in {
          moveZeroes(input)
          input mustBe answer
        }
      }
    }
    "array size == 1" must {
      forAll(exCase2) { (testcase: String, input: Array[Int], answer: Array[Int]) =>
        testcase in {
          moveZeroes(input)
          input mustBe answer
        }
      }
    }
  }

  "Count of odds" must {
    "be 3 in 3-7" in {
      countOdds(3, 7) mustBe 3
    }
  }

}
