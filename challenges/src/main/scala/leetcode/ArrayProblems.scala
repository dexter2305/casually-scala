package leetcode

object ArrayProblems extends App {

  assert(ArrayProblems.maxProfit(Array(7, 1, 5, 3, 6, 4)) == 5)
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

  // https://leetcode.com/problems/intersection-of-two-arrays-ii/
  def intersect(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {

    @scala.annotation.tailrec
    def aux(nums1: Array[Int], nums2: Array[Int], i: Int, j: Int, acc: Array[Int], k: Int): Array[Int] = {
      if (i == nums1.length || j == nums2.length) acc
      else if (nums1(i) < nums2(j)) aux(nums1, nums2, i + 1, j, acc, k)
      else if (nums1(i) > nums2(j)) aux(nums1, nums2, i, j + 1, acc, k)
      else // nums1(i) must be == nums2(j)
        aux(nums1, nums2, i + 1, j + 1, acc :+ nums1(i), k + 1)
    }
    aux(nums1.sorted, nums2.sorted, 0, 0, Array.emptyIntArray, 0)
  }

  // https://leetcode.com/problems/final-value-of-variable-after-performing-operations/
  def finalValueAfterOperations(operations: Array[String]): Int = {
    operations.foldLeft(0) { (s, op) =>
      op match {
        case "++X" | "X++" => s + 1
        case _             => s - 1
      }
    }
  }

  //https://leetcode.com/problems/merge-sorted-array/
  //traverse reverse
  def merge(nums1: Array[Int], m: Int, nums2: Array[Int], n: Int): Unit = {

    @scala.annotation.tailrec
    def sortMerge(left: Array[Int], right: Array[Int], l: Int, r: Int, ri: Int): Array[Int] = {
      //println(s"${left.mkString(" ")} | ${right.mkString(" ")}")
      (l, r) match {
        //right underflow means left had smaller elements than right
        case (_, r) if r < 0 => left
        //left underflow means left had bigger elements than right
        case (l, _) if l < 0 => //copy remaining from right to left
          for (i <- 0 to r) {
            left(i) = right(i)
          }
          left
        case _               =>
          if (right(r) >= left(l)) {
            left(ri) = right(r)
            right(r) = 0
            sortMerge(left, right, l, r - 1, ri - 1)
          } else {
            left(ri) = left(l)
            left(l) = 0
            sortMerge(left, right, l - 1, r, ri - 1)
          }
      }
    }
    sortMerge(nums1, nums2, m - 1, n - 1, (m + n - 1))
  }
}
