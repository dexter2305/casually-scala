package leetcode

import testtypes.UnitTestWordSpec
import org.scalatest.prop.TableDrivenPropertyChecks

class StringProblemsSuite extends munit.FunSuite {

  val examplesForMaxWords = Seq(
    (Array("Never quit"), 2),
    (Array("scala rocks", "tdd makes refactor easier"), 4),
    (Array.empty[String], 0),
    (null, 0)
  )

  test("Max number of words in sentence") {
    examplesForMaxWords.foreach { case (strings, exp) =>
      assertEquals(StringProblems.mostWordsFound(strings), exp)

    }
  }

}
