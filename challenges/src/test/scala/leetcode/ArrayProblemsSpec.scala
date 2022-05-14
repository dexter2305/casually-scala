package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import leetcode._

import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.time.Span
import org.scalatest.time.Millis
import org.scalatest.time.Seconds

class ArrayProblemsSpec extends AnyFreeSpec with Matchers {

  "Reshape matrix" - {
    "reshape not possible" - {
      "given matrix < expected" in {
        val input = Array(Array(1,2,3), Array(4,5,6))
        ArrayProblems.matrixReshape(input, 20, 30) should be (input)
      }
      "given matrix > expected" in {
        val input = Array(Array(1,2,3), Array(4,5,6))
        ArrayProblems.matrixReshape(input, 2, 2) should be (input)
      }      
    }
    "reshape possible" - {
      "2 x 3 => 3 x 2" in {
        val input = Array(Array(1,2,3), Array(4,5,6))
        ArrayProblems.matrixReshape(input, 3, 2) should be (Array(Array(1,2), Array(3,4), Array(5,6)))

      }
      "1 x 4 => 2 x 2" in {
        val input = Array(Array(1, 2, 3, 4))
        ArrayProblems.matrixReshape(input, 2, 2) should be (Array(Array(1, 2), Array(3, 4)))
      }
      "3 x 3 => 1 x 9" in {
        val input = Array(Array(1,2,3), Array(4,5,6), Array(7,8,9))
        ArrayProblems.matrixReshape(input, 1, 9) should be (Array(Array(1,2,3,4,5,6,7,8,9)))
      }
    }
  }
  "Convert 1d array to 2d array" - {
    "when transformation not possible" - {
      "return empty array" - {
        "with input as empty array" in {
          ArrayProblems.construct2DArray(Array(1, 2, 3), 2, 3) should be(Array.empty)
        }
        "with 1d array length is odd" in {
          ArrayProblems.construct2DArray(Array(1, 2, 3, 4, 5), 2, 2) should be(Array.empty)
        }
        "with 1d array length is even; given 2d size is large" in {
          ArrayProblems.construct2DArray(Array(1, 2, 3, 4, 5, 6), 10, 10) should be(Array.empty)
        }
        "with 1d array length is even; given 2d size is small" in {
          ArrayProblems.construct2DArray(Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2, 2) should be(Array.empty)
        }
      }
    }
    "when transformation possible" - {
      "return 2d array" - {
        "with 1d array length is even; given 2d size fits" in {
          ArrayProblems.construct2DArray(Array(1, 2, 3, 4, 5, 6), 2, 3) should be (Array(Array(1,2,3), Array(4,5,6)))
        }
        "with 1d array length is odd; given 2d with r =1" in {
          ArrayProblems.construct2DArray(Array(1, 2, 3, 4, 5, 6), 1, 6) should be(Array(Array(1, 2, 3, 4, 5, 6)))
        }
      }

    }
  }
  "Merge sorted arrays" - {
    "when left has smallest element" in {
      val left      = Array(1, 2, 3, 0, 0, 0)
      val leftSize  = 3
      val right     = Array(8, 9, 12)
      val rightSize = 3

      val leftClone      = left.take(leftSize)
      val sortedExpected = (leftClone ++ right).sorted

      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(sortedExpected)
    }
    "when right has smallest element" in {
      val left      = Array(100, 200, 349, 0, 0, 0)
      val leftSize  = 3
      val right     = Array(8, 9, 12)
      val rightSize = 3

      val leftClone      = left.take(leftSize)
      val sortedExpected = (leftClone ++ right).sorted

      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(sortedExpected)
    }
    "when right array is smaller than left array" in {
      val left      = Array(100, 200, 349, 0)
      val leftSize  = 3
      val right     = Array(12)
      val rightSize = 1

      val leftClone      = left.take(leftSize)
      val sortedExpected = (leftClone ++ right).sorted

      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(sortedExpected)
    }
    "when left array is smaller than right array" in {
      val left      = Array(100, 0, 0, 0)
      val leftSize  = 1
      val right     = Array(120, 420, 900)
      val rightSize = 3

      val leftClone      = left.take(leftSize)
      val sortedExpected = (leftClone ++ right).sorted

      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(sortedExpected)
    }
    "when both arrays are empty" in {
      val left  = Array.emptyIntArray
      val right = Array.emptyIntArray
      ArrayProblems.merge(left, 0, right, 0)
      left should equal(Array.emptyIntArray)
    }
    "when right array is empty" in {
      val left     = Array(1, 2, 3, 0, 0, 0)
      val leftSize = 3
      val right    = Array.emptyIntArray
      ArrayProblems.merge(left, leftSize, right, 0)
      left should equal(left)
    }
    "when left array is empty but has placeholders for right array" in {
      val left      = Array(0, 0, 0)
      val leftSize  = 0
      val right     = Array(1, 3, 4)
      val rightSize = 3
      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(right)
    }
  }

  "Intersection of two arrays II" - {
    "when arrays are of same size" - {
      "when two arrays have no intersections" in {
        val left  = Array(1, 2, 3)
        val right = Array(4, 5, 6)
        ArrayProblems.intersect(left, right) should equal(Array())
      }
      "when multiple distinct ints intersect" in {
        val left  = Array(3, 4, -5, 7, -2)
        val right = Array(10, 11, 4, -1, 7)
        ArrayProblems.intersect(left, right) should contain theSameElementsAs (Array(7, 4))
      }
      "when multiple identical ints intersect" in {
        val left  = Array(8, 9, -1, 8)
        val right = Array(27, 8, 8, 9)
      }
      "when both arrays are empty" in {
        ArrayProblems.intersect(Array.emptyIntArray, Array.emptyIntArray) should be(Array.emptyIntArray)
      }
    }
    "when arrays are varying size" - {
      "when left array is empty" in {
        ArrayProblems.intersect(Array.emptyIntArray, Array(9, 10, 11)) should be(Array.emptyIntArray)
      }
      "when right array is empty" in {
        ArrayProblems.intersect(Array(7, 2, 1), Array.emptyIntArray) should be(Array.emptyIntArray)
      }
      "when distinct ints intersect" in {
        val left  = Array(1, 2, 3, 4, 5)
        val right = Array(3, 2, 5)
        ArrayProblems.intersect(left, right) should be(Array(2, 3, 5))
      }
    }
  }

  "maxProfit" - {
    "should be profit when prices increase constantly" in {
      ArrayProblems.maxProfit(Array(1, 2, 3)) should be(2)
    }
    "should be no profit when prices decrease constantly" in {
      ArrayProblems.maxProfit(Array(7, 6, 5, 4, 3)) should be(0)
    }
    "profit when early peaks with drops" in {
      ArrayProblems.maxProfit(Array(7, 1, 5, 3, 6, 4)) should be(5)
    }
  }
}
