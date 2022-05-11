package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import leetcode._
class ArrayProblemsSpec extends AnyFreeSpec with Matchers {

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
      val left = Array.emptyIntArray
      val right = Array.emptyIntArray
      ArrayProblems.merge(left, 0, right, 0) 
      left should equal (Array.emptyIntArray)
    }
    "when right array is empty" in {
      val left = Array(1,2,3,0,0,0)
      val leftSize = 3
      val right = Array.emptyIntArray
      ArrayProblems.merge(left, leftSize, right, 0) 
      left should equal (left)
    }
    "when left array is empty but has placeholders for right array" in {
      val left = Array(0,0,0)
      val leftSize = 0
      val right = Array(1,3,4)
      val rightSize = 3
      ArrayProblems.merge(left, leftSize, right, rightSize) 
      left should equal (right)
    }       
  }
}
