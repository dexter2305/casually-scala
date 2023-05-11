package leetcode


import basetypes.UnitTestFlatSpec
import leetcode.PrefixSumProblems._

class NumArrayFlatSpec extends UnitTestFlatSpec {

  val sourceArray = Array(0, 1, 2, 3, 4, 5)
  val obj         = new NumArray(sourceArray)

  "Array (1,2,3,4)" should "be valid for range: full length" in {
    obj.sumRange(0, sourceArray.length - 1) mustBe sourceArray.sum
  }

  it should "be valid for range: sub array from start" in {
    obj.sumRange(0, 2) mustBe 3
  }

  it should "be valid for range: sub array at end" in {
    obj.sumRange(3, 5) must be(12)
  }
  it should "be valid for range: sub array in middle" in {
    obj.sumRange(1, 4) mustBe 10
  }
  it should "be valid for range: sub array has 1 element" in {
    obj.sumRange(0, 0) must be(sourceArray.head)

  }
}
