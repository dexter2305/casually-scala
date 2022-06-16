package leetcode

object ArrayProblems extends App {

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

  // https://leetcode.com/problems/intersection-of-two-arrays-ii/
  def intersect(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {

    @scala.annotation.tailrec
    def aux(
      nums1: Array[Int],
      nums2: Array[Int],
      i: Int,
      j: Int,
      acc: Array[Int],
      k: Int,
    ): Array[Int] = {
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
    def sortMerge(
      left: Array[Int],
      right: Array[Int],
      l: Int,
      r: Int,
      ri: Int,
    ): Array[Int] = {
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

  //https://leetcode.com/problems/missing-number/
  def missingNumber(nums: Array[Int]): Int = {
    if (nums.length == nums.max) {
      val (max, sum) = nums.tail.foldLeft((nums.head, nums.head)) { case ((max, sum), e) =>
        (math.max(max, e), sum + e)
      }
      ((max + 1) * max / 2) - sum
    } else nums.length
  }

  //https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
  def twoSum(numbers: Array[Int], target: Int): Array[Int] = {

    @scala.annotation.tailrec
    def _twoSum(numbers: Array[Int], l: Int, r: Int): Array[Int] = {
      (numbers(l), numbers(r)) match {
        case (x, y) if x + y == target => Array(l + 1, r + 1)
        case (x, y) if x + y < target  => _twoSum(numbers, l + 1, r)
        case (x, y) if x + y > target  => _twoSum(numbers, l, r - 1)
      }
    }
    _twoSum(numbers, 0, numbers.length - 1)
  }

  //https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
  def average(salary: Array[Int]): Double = {

    val min = salary.head
    val max = salary.head
    val sum = salary.head

    val (gt, mn, mx, n) = salary.tail.foldLeft((sum, min, max, 1)) {

      case ((sum, min, max, size), e) if e < min => (sum + e, e, max, size + 1)
      case ((sum, min, max, size), e) if e > max => (sum + e, min, e, size + 1)
      case ((sum, min, max, size), e)            => (sum + e, min, max, size + 1)
    }
    
    (gt - mn - mx).toDouble / (n - 2)

  }

}
