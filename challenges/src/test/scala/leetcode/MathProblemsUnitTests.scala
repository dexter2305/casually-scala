package leetcode

import basetypes.UnitTestFlatSpec
import MathProblems._
class MathProblemsUnitTests extends UnitTestFlatSpec {

  "Number of Steps to Reduce a Number to Zero" should "return 6 for n = 14" in { numberOfSteps(14) mustBe 6 }
  it should "return 4 for n = 8" in { numberOfSteps(8) mustBe 4 }
  it should "return 12 for n = 123" in { numberOfSteps(123) mustBe 12 }

}
