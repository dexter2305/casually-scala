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

  //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
  /*
      => track min element and profit
         newMin = min (curMin, currentElement)
         newProfit = max (curProfit, currentElement - newMin)

      t: O(n)
      s: O(1)

      => In a way this is application of Kadane's algorithm.
         Prices will never be negative which is same as max sum subarray with all positives.
         In this case, differential price gives negatives which is passed through kadanes' algorithm
      val differentialPrice = prices.sliding(2).toList.map(array => array(1)- array(0))
      kadaneAlgo(differentialPrice)
   */
  def maxProfit(prices: Array[Int]): Int = {
    val rMin        = Int.MaxValue
    val rProfit     = 0
    val (_, profit) = prices.foldLeft((rMin, rProfit)) { case ((rMin, rProfit), e) =>
      val newMin    = math.min(rMin, e)
      val newProfit = math.max(rProfit, e - newMin)
      (newMin, newProfit)
    }
    profit
  }

}
