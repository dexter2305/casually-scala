package interviewbit

object PickFromBothSides extends App {

  /*
    //psuedocode
    max = nums.take(n).sum
    ri <- n - 1 to 0
    max - nums(ri) + nums(nums.length - 1 - ( n -1 -ri ))

    // test
    nums = [1,2,3,4,5]
    n = 4
    max = 10
    ri <- 3 to 0
      max; ri; exp; value
      10; 3; 10 - 4 + nums(4 - 0) -> 6 + 5 -> 11 (newMax)
      11; 2; 11 - 3 + nums(4 - 1) -> 8 + 4 -> 12 (newMax)
      12; 1; 12 - 2 + nums(4 - 2) -> 10 + 3 -> 13 (newMax)
      13; 0; 13 - 1 + nums(4 - 3) -> 12 + 2 -> 14 (newMax)
   */
  def solve(nums: Array[Int], n: Int): Int = {
    if (nums.length == n) nums.sum
    else {
      val initialMax = nums.take(n).sum
      val initialSum = initialMax
      val (max, _) = (n - 1 to 0 by -1).foldLeft((initialMax, initialSum)) { case ((currentMax, currentSum), ri) =>
        val newSum = currentSum - nums(ri) + nums(nums.length - 1 - (n - 1 - ri))
        val newMax = math.max(newSum, currentMax)
        (newMax, newSum)
      }
      max
    }
  }

  val ns = Array(1, 2, 3, 4, 5)
  val e  = 2
  println(s"${solve(ns, 4)}")

  (5 to 0 by -1).foldLeft((0, 0))((c, e) => ???)

}
