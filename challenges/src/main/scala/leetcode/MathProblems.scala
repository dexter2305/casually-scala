package leetcode

object MathProblems {
  //https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
  def numberOfSteps(num: Int): Int = {

    @scala.annotation.tailrec
    def helper(n: Int, steps: Int = 0): Int = n match {
      case 0 => steps
      case x if x % 2 == 0 => helper(x / 2, steps + 1)
      case x if x % 2 == 1 => helper(x - 1, steps + 1)
    }
    helper(num)
  }

  // https://leetcode.com/problems/number-of-1-bits/
  // you need treat n as an unsigned value
  def hammingWeight(n: Int): Int = {
    val (_, hw) = (0 until 32).foldLeft((n, 0)) { case ((n, hw), e) => (n >>> 1, hw + (n & 1)) }
    hw
  }

  //https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
  def subtractProductAndSum(n: Int): Int = {

    def loop(n: Int, s: Int = 0, p: Int = 1): Int = {
      if (n < 10) (p * n ) - (s + n)
      else {
        loop(n / 10, s + (n % 10), p * (n % 10))
      }
    }

    loop(n)
  }
}
