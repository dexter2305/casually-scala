package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class StringProblemsSpec extends AnyFreeSpec with Matchers {

  "First Uniq Char" - {
    "return -1" - {
      "when there are no unique chars" in {
        StringProblems.firstUniqChar("aabbcc") should be (-1)
      }
    }
    "return first uniq index" - {
      "when there is only one uniq char" in {
        StringProblems.firstUniqChar("bbccaccbbeee") should be (4)
      }
      "when there are multiple uniq/distinct chars" in {
        StringProblems.firstUniqChar("leetcode") should be (0)
      }
    }
  }


}
