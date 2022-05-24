package leetcode

object AlgorithmProblems extends App {

  println(s"${search(Array(1, 2, 3, 4), 0)}")

  def search(nums: Array[Int], target: Int): Int = {
    @scala.annotation.tailrec
    def searchBinary(left: Int, right: Int): Int = {
      //println(s"left: $left, right: $right")
      val mid = left + (right - left) / 2
      mid match {
        case mid if left > right        => -1
        case mid if nums(mid) == target => mid
        case mid if nums(mid) < target  => searchBinary(mid + 1, right)
        case _                          => searchBinary(left, mid - 1)
      }
    }
    searchBinary(0, nums.length - 1)
  }

  
}
