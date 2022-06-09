package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import leetcode._

import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.time.Span
import org.scalatest.time.Millis
import org.scalatest.time.Seconds

class ArrayProblemsFreeSpec extends AnyFreeSpec with Matchers {

  "Merge sorted arrays" - {
    "when left has smallest element" in {
      val left = Array(1, 2, 3, 0, 0, 0)
      val leftSize = 3
      val right = Array(8, 9, 12)
      val rightSize = 3

      val leftClone = left.take(leftSize)
      val sortedExpected = (leftClone ++ right).sorted

      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(sortedExpected)
    }
    "when right has smallest element" in {
      val left = Array(100, 200, 349, 0, 0, 0)
      val leftSize = 3
      val right = Array(8, 9, 12)
      val rightSize = 3

      val leftClone = left.take(leftSize)
      val sortedExpected = (leftClone ++ right).sorted

      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(sortedExpected)
    }
    "when right array is smaller than left array" in {
      val left = Array(100, 200, 349, 0)
      val leftSize = 3
      val right = Array(12)
      val rightSize = 1

      val leftClone = left.take(leftSize)
      val sortedExpected = (leftClone ++ right).sorted

      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(sortedExpected)
    }
    "when left array is smaller than right array" in {
      val left = Array(100, 0, 0, 0)
      val leftSize = 1
      val right = Array(120, 420, 900)
      val rightSize = 3

      val leftClone = left.take(leftSize)
      val sortedExpected = (leftClone ++ right).sorted

      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(sortedExpected)
    }
    "when both arrays are empty" in {
      val left = Array.emptyIntArray
      val right = Array.emptyIntArray
      ArrayProblems.merge(left, 0, right, 0)
      left should equal(Array.emptyIntArray)
    }
    "when right array is empty" in {
      val left = Array(1, 2, 3, 0, 0, 0)
      val leftSize = 3
      val right = Array.emptyIntArray
      ArrayProblems.merge(left, leftSize, right, 0)
      left should equal(left)
    }
    "when left array is empty but has placeholders for right array" in {
      val left = Array(0, 0, 0)
      val leftSize = 0
      val right = Array(1, 3, 4)
      val rightSize = 3
      ArrayProblems.merge(left, leftSize, right, rightSize)
      left should equal(right)
    }
  }

  "Intersection of two arrays II" - {
    "when arrays are of same size" - {
      "when two arrays have no intersections" in {
        val left = Array(1, 2, 3)
        val right = Array(4, 5, 6)
        ArrayProblems.intersect(left, right) should equal(Array())
      }
      "when multiple distinct ints intersect" in {
        val left = Array(3, 4, -5, 7, -2)
        val right = Array(10, 11, 4, -1, 7)
        ArrayProblems.intersect(left, right) should contain theSameElementsAs (Array(
          7,
          4
        ))
      }
      "when multiple identical ints intersect" in {
        val left = Array(8, 9, -1, 8)
        val right = Array(27, 8, 8, 9)
      }
      "when both arrays are empty" in {
        ArrayProblems.intersect(Array.emptyIntArray, Array.emptyIntArray) should be(
          Array.emptyIntArray
        )
      }
    }
    "when arrays are varying size" - {
      "when left array is empty" in {
        ArrayProblems.intersect(Array.emptyIntArray, Array(9, 10, 11)) should be(
          Array.emptyIntArray
        )
      }
      "when right array is empty" in {
        ArrayProblems.intersect(Array(7, 2, 1), Array.emptyIntArray) should be(
          Array.emptyIntArray
        )
      }
      "when distinct ints intersect" in {
        val left = Array(1, 2, 3, 4, 5)
        val right = Array(3, 2, 5)
        ArrayProblems.intersect(left, right) should be(Array(2, 3, 5))
      }
    }
  }

  "Two Sum II - Input Array Is Sorted" - {
    "target is large but exists" in {
      ArrayProblems.twoSum(Array(2, 5, 8, 12, 23), 35) shouldBe Array(4, 5)
    }
    "target is medium but exists" in {
      ArrayProblems.twoSum(Array(2, 5, 8, 12, 23), 25) shouldBe Array(1, 5)
    }
    "target is small but exists" in {
      ArrayProblems.twoSum(Array(2, 5, 8, 12, 23), 7) shouldBe Array(1, 2)
    }
  }
}
