package leetcode

import scala.annotation.tailrec

object AlgorithmProblems extends App {

  //println(s"${search(Array(1, 2, 3, 4), 0)}")
  //println(firstBadVersion(90))
  val array = Array(1, 2)
  rotate(array, 2)
  println(array.mkString(" "))

  //https://leetcode.com/problems/binary-search/
  def search(nums: Array[Int], target: Int): Int = {
    @scala.annotation.tailrec
    def searchBinary(left: Int, right: Int): Int = {
      //println(s"left: $left, right: $right")
      val mid = (left + right) / 2
      mid match {
        case _ if left > right          => -1
        case mid if nums(mid) == target => mid
        case mid if nums(mid) < target  => searchBinary(mid + 1, right)
        case mid if nums(mid) > target  => searchBinary(left, mid - 1)
      }
    }
    searchBinary(0, nums.length - 1)
  }

  //https://leetcode.com/problems/squares-of-a-sorted-array/
  def sortedSquares(nums: Array[Int]): Array[Int] = {
    @scala.annotation.tailrec
    def helper(left: Int, right: Int, acc: List[Int]): List[Int] = {
      if (left <= right) {
        val leftSq  = nums(left) * nums(left)
        val rightSq = nums(right) * nums(right)
        if (leftSq > rightSq) helper(left + 1, right, leftSq :: acc)
        else helper(left, right - 1, rightSq :: acc)
      } else acc
    }
    helper(0, nums.length - 1, List.empty).toArray
  }

  def isBadVersion(version: Int): Boolean = version >= 4

  //https://leetcode.com/problems/first-bad-version/
  def firstBadVersion(n: Int): Int = {
    @scala.annotation.tailrec
    def binarySearch(left: Int, right: Int): Int = {
      if (left == right) left
      else {
        val mid = left + (right - left) / 2
        if (isBadVersion(mid)) binarySearch(left, mid)
        else binarySearch(mid + 1, right)
      }

    }
    binarySearch(0, n)
  }

  //https://leetcode.com/problems/rotate-array/
  def rotate(nums: Array[Int], k: Int): Unit = {

    val d = if (k > nums.length) k % nums.length else k

    @scala.annotation.tailrec
    def reverse(l: Int, r: Int): Unit = {
      if (l < r) {
        val x = nums(l)
        nums(l) = nums(r)
        nums(r) = x
        reverse(l + 1, r - 1)
      }
    }
    // reverse the complete array
    reverse(0, nums.length - 1)
    // reverse only the first segment
    reverse(0, d - 1)
    // reverse only the second segement
    reverse(d, nums.length - 1)

  }

  //https://leetcode.com/problems/reverse-string/
  def reverseString(s: Array[Char]): Unit = {
    @scala.annotation.tailrec
    def reverse(s: Array[Char], l: Int, r: Int): Unit = {
      if (l < r) {
        val t = s(l)
        s(l) = s(r)
        s(r) = t
        reverse(s, l + 1, r - 1)
      }
    }
    reverse(s, 0, s.length - 1)
  }

  //https://leetcode.com/problems/reverse-words-in-a-string-iii/
  def reverseWords(s: String): String = {
    s.split(" ").map(_.reverse).mkString
  }

  //https://leetcode.com/problems/search-insert-position/
  def searchInsert(nums: Array[Int], target: Int): Int = {

    @scala.annotation.tailrec
    def loop(left: Int, right: Int): Int = left + (right - left) / 2 match {
      case mid if left > right        => left
      case mid if nums(mid) == target => mid
      case mid if nums(mid) < target  => loop(mid + 1, right)
      case mid if nums(mid) > target  => loop(0, mid - 1)
    }
    loop(0, nums.length - 1)
    loop(0, nums.length - 1)
  }

  //https://leetcode.com/problems/move-zeroes/
  def moveZeroes(nums: Array[Int]): Unit = {

    @scala.annotation.tailrec
    def move(left: Int, right: Int): Unit = {
      if (left < nums.length && right < nums.length) {
        (nums(left), nums(right)) match {
          case (x, _) if x != 0 => move(left + 1, right + 1)
          case (0, 0)           => move(left, right + 1)
          case (0, y) if y != 0 =>
            nums(left) = y
            nums(right) = 0
            move(left + 1, right + 1)
        }
      }
    }
    move(0, 0)
  }

  //https://leetcode.com/problems/power-of-two/
  def isPowerOfTwo(n: Int): Boolean = n match {
        case 0 => false
        case 1 => true
        case n => n % 2 == 0 && isPowerOfTwo(n / 2)
  }

}
