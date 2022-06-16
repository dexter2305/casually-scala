package leetcode

import basetypes.UnitTest
import MathProblems._
class MathProblemsUnitTests extends UnitTest {

  "Number of Steps to Reduce a Number to Zero" should "return 6 for n = 14" in { numberOfSteps(14) shouldBe 6 }
  it should "return 4 for n = 8" in { numberOfSteps(8) shouldBe 4 }
  it should "return 12 for n = 123" in { numberOfSteps(123) shouldBe 12 }

}
