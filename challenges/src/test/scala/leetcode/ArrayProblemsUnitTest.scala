package leetcode

import leetcode.ArrayProblems._
import org.scalatest.prop.TableDrivenPropertyChecks
class ArrayProblemsUnitTest extends testtypes.UnitTestWordSpec with TableDrivenPropertyChecks {

  val examples = Table(
    ("array", "average"),
    (Array(4000, 2000, 3000, 1000), 2500.00),
    (Array(1000, 2000, 3000), 2000.00),
  )
  "Average Salary Excluding the Minimum and Maximum Salary" must {
    forAll(examples) { (nums: Array[Int], average: Double) =>
      s"eval [${nums.mkString("-")}] to $average" in {
        ArrayProblems.average(nums)
      }
    }
  }
}
