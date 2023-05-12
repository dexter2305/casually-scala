package leetcode

import MathProblems._
import org.scalatest.prop.TableDrivenPropertyChecks
class MathProblemsUnitTests extends testtypes.UnitTestWordSpec with TableDrivenPropertyChecks {

  val examplesForStepsToReduceToZero = Table(
    ("n", "answer"),
    (8, 4),
    (123, 12),
  )
  "Number of Steps to Reduce a Number to Zero" must {
    forAll(examplesForStepsToReduceToZero) { (n: Int, answer: Int) =>
      s"return $answer for n = $n" in {
        numberOfSteps(n) mustBe answer
      }
    }
  }
}
