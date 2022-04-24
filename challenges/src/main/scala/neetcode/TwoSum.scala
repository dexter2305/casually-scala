package neetcode

object TwoSum extends App {

  /*
    https://leetcode.com/problems/two-sum/
    - nums[], target
    - map
    - i <- 0 until nums.length
    -- if (map contains (target - nums(i)) ) return [i, map.get(target-nums(i))]
    -- else
        map + (nums(i) -> i)
   */
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    @scala.annotation.tailrec
    def go(map: Map[Int, Int], array: Array[Int], index: Int): Array[Int] = {
      if (index >= array.length) Array.empty[Int]
      else if (map contains (target - array(index))) Array(index, map(target - array(index)))
      else go(map + (array(index) -> index), array, index + 1)
    }
    go(Map.empty[Int, Int], array = nums, index = 0)
  }

}
