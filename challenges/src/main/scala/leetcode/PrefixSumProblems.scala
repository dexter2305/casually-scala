package leetcode

object PrefixSumProblems extends App {

  val obj = new NumArray((0 to 4).toArray)
  obj.sumRange(0, 4)

  // https://leetcode.com/problems/range-sum-query-immutable/
  class NumArray(_nums: Array[Int]) {

    private val rSum                         = _nums.scanLeft(0)(_ + _)
    def sumRange(left: Int, right: Int): Int =
      rSum(right + 1) - rSum(left)

  }

  // https://leetcode.com/problems/range-sum-query-2d-immutable/
  class NumMatrix(_matrix: Array[Array[Int]]) {

    /*
      //input
      10    20    30
       5     10    20
       2     (4     6)

      //prefix sum matrix
      10    30    60
      15    45    95
      17    51   107
     */

    val dp = matrixPrefixSumWithLeadingZeroes()

    def matrixPrefixSumWithLeadingZeroes(): Array[Array[Int]] =
      // - calculate dp matrix with preceding zeroes which is easier to visulize and cannot be avoided.
      // - is also in similar lines to 1 d array with dp where preceding 0 is included.
      if (_matrix.size > 0 && _matrix.head.size > 0) {
        val dp = Array.ofDim[Int](_matrix.size + 1, _matrix.head.size + 1)
        for {
          r <- 1 to _matrix.size
          c <- 1 to _matrix.head.size
        }
          dp(r)(c) = dp(r)(c - 1) + dp(r - 1)(c) - dp(r - 1)(c - 1) + _matrix(r - 1)(c - 1)
        dp
      } else _matrix

    def sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int =
      /*
        a) find cumulative total
        b) subtract bottom left
        c) subtract top right
        d) add common between b & c.
       */

      dp(row2 + 1)(col2 + 1) - dp(row2 + 1)(col1 + 1 - 1) - dp(row1 + 1 - 1)(col2 + 1) + dp(row1 + 1 - 1)(col1 + 1 - 1)

  }

}
