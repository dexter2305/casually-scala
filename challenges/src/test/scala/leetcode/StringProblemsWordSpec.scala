package leetcode

import StringProblems._
import testtypes.UnitTestWordSpec
import org.scalatest.prop.TableDrivenPropertyChecks

class StringProblemsWordSpec extends UnitTestWordSpec with TableDrivenPropertyChecks {

  val examplesForMaxWords = Table(
    ("Array of words", "Count"),
    (Array("Never quit"), 2),
    (Array("scala rocks", "tdd makes refactor easier"), 4),
    (Array.empty[String], 0),
    (null, 0)
  )

  "Max number of words in sentence" must {
    forAll(examplesForMaxWords) { (words, expectedCount) =>
      s"eval [${if (words != null) words.mkString("-") else words}] to $expectedCount" in {
        mostWordsFound(words) mustBe expectedCount
      }
    }
  }

}
