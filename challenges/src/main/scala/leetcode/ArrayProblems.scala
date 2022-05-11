package leetcode

object ArrayProblems extends App {
  val l = Array(4, 5, 6, 0, 0, 0)
  merge(l, 3, Array(1, 2, 3), 3)
  println(l.mkString(" "))
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
