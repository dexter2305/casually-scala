package leetcode

object ArrayProblems extends App {

  //https://leetcode.com/problems/reshape-the-matrix/
  //assumption: every row is equally sized
  //t: O(n)
  //s: O(n); new array has to be built
  def matrixReshape(mat: Array[Array[Int]], r: Int, c: Int): Array[Array[Int]] = {
    if (mat.length > 0 && mat.length * mat(0).length == r * c) {
      
      val n = mat.head.size
      val x = for (x <- 0 until r) yield for (y <- 0 until c) yield {
        val index = (c * x) + y 
        println(s"$index => (${index / n}, ${index % n})")
        mat(index / n)( index % n)
      }
      x.map(_.toArray).toArray
    } else mat
  }

  //https://leetcode.com/problems/convert-1d-array-into-2d-array/
  def construct2DArray(original: Array[Int], r: Int, c: Int): Array[Array[Int]] = {
    if (r * c == original.length) {
      //((for (i <- 0 until r) yield for (ci <- 0 until c) yield (original(ci + (i * c)))).map(_.toArray)).toArray
      Array.tabulate(r, c)((x, y) => original(x * c + y))
    } else Array.empty
  }

  //https://leetcode.com/problems/contains-duplicate/
  def containsDuplicate(nums: Array[Int]): Boolean = {
    @scala.annotation.tailrec
    def hasDupes(nums: Array[Int], i: Int, set: Set[Int]): Boolean = {
      if (i == nums.length) false
      else if (set.contains(nums(i))) true
      else hasDupes(nums, i + 1, set + nums(i))
    }
    hasDupes(nums, i = 0, Set.empty[Int])
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
