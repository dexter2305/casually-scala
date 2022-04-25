package interviewbit

object ArrayProblems extends App {

  println(s"${majorityElement(Array(2, 1, 2))}")

  //https://www.interviewbit.com/problems/positive-negative/
  def positiveNegative(array: Array[Int]): Array[Int] = {
    val (sizeOfPositive, sizeOfNegative) = array.partition(_ > 0) match {
      case (pos, negsWithMayBeZero) =>
        (pos.size, negsWithMayBeZero.count(_ < 0))
    }
    Array(sizeOfPositive, sizeOfNegative)
  }

  def majorityElement(n: Array[Int]): Int = {
    val limit = math.floor(n.size / 2)
    n.groupBy(n => n).map { case (e, l) => (e, l.size) }.find { case (e, f) => f > limit }.map { case (e, _) => e }.get
  }

  //https://www.interviewbit.com/problems/max-min-05542f2f-69aa-4253-9cc7-84eb7bf739c4/
  def maxMinSum(nums: Array[Int]): Int = {
    val (max, min) = nums.tail.foldLeft((nums.head, nums.head)) { case ((max, min), e) =>
      (math.max(max, e), math.min(min, e))
    }
    println(s"max: $max, min: $min")
    max + min
  }

  //https://www.interviewbit.com/problems/first-missing-integer/
  /*
      case 1
      1 2 3 4
      0 1 2 3 => i + 2 or element + 1
      case 2
      2 6 7 10
      0 1 2 3   =>
   */
  def firstMissingPositive(nums: Array[Int]): Int = {
    val (positives, _)    = nums.sorted.partition(_ > 0)
    val distinctPositives = positives.distinct
    distinctPositives.zipWithIndex.find { case (element, index) =>
      element > index + 1
    }.map { case (element, index) =>
      index + 1
    }.getOrElse(distinctPositives.size + 1)
  }

  //https://www.interviewbit.com/problems/find-duplicate-in-array/
  def findDuplicateInArray(nums: Array[Int]): Int = {
    nums
      .groupBy(i => i)
      .find { case (n, list) => list.size > 1 }
      .map { case (n, _) => n }
      .getOrElse(-1)
  }

  //https://www.interviewbit.com/problems/pick-from-both-sides/
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
  def pickFromBothSides(nums: Array[Int], n: Int): Int = {
    if (nums.length == n) nums.sum
    else {
      val initialMax = nums.take(n).sum
      val initialSum = initialMax
      val (max, _)   = (n - 1 to 0 by -1).foldLeft((initialMax, initialSum)) { case ((currentMax, currentSum), ri) =>
        val newSum = currentSum - nums(ri) + nums(nums.length - 1 - (n - 1 - ri))
        val newMax = math.max(newSum, currentMax)
        (newMax, newSum)
      }
      max
    }
  }

}
