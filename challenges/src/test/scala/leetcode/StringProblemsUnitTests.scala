package leetcode
import basetypes.UnitTest
import StringProblems._

class StringProblemsUnitTests extends UnitTest {
  "Maximum Number of Words Found in Sentences" should "return 2 for ['Never quit']" in { mostWordsFound(Array("Never quit")) shouldBe 2 }
  it should "return 4 for ['scala rox', 'tdd makes refactor easier']" in {
    mostWordsFound(Array("scala rox", "tdd makes refactor easier")) shouldBe 4
  }
  it should "return 0 when the Array is empty" in { mostWordsFound(Array.empty[String]) shouldBe 0 }
  it should "return 0 when the Array is null" in { mostWordsFound(null) shouldBe 0}
}
