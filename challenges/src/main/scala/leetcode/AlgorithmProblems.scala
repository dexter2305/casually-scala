package leetcode

object AlgorithmProblems extends App {

  //println(s"${search(Array(1, 2, 3, 4), 0)}")
  println(firstBadVersion(90))

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

}
