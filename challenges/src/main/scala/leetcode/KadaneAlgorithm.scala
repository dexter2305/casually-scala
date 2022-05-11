package leetcode

object KadaneAlgorithm extends App {

  println(maxSubArray(Array(-2, -3, 4, -1, -2, 1, 5, -3)))

  println(maxSubArray(Array(-3, -2, 0, -1)))

  // https://leetcode.com/problems/maximum-subarray/
  // Kadane's algorithm implementation
  // reference: https://www.youtube.com/watch?v=w_KEocd__20
  //
  def maxSubArray(nums: Array[Int]): Int = {
    val initialMax = nums.head
    val initialSum = 0
    val (max, _)   = nums.foldLeft((initialMax, initialSum)) { case ((max, currentSum), e) =>
      if (currentSum + e > max) (currentSum + e, math.max(currentSum + e, 0))
      else (max, math.max(currentSum + e, 0))
    }
    max
  }
}
