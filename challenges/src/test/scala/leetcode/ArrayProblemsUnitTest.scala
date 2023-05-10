package leetcode

import basetypes.UnitTest
import leetcode.ArrayProblems._
class ArrayProblemsUnitTest extends UnitTest {

  "Average Salary Excluding the Minimum and Maximum Salary" should "be 2500 for [4000, 2000, 3000, 1000]" in {
    average(Array(4000, 2000, 1000, 3000)) mustBe 2500.00
  }
  it should "be 2000.00 for [1000,2000,3000]" in { average(Array(1000, 2000, 3000)) mustBe 2000.00 }

}
