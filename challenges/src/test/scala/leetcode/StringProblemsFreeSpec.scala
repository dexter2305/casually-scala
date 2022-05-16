package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class StringProblemsSpec extends AnyFreeSpec with Matchers {

  "First Uniq Char" - {
    "return -1" - {
      "when there are no unique chars" in {
        StringProblems.firstUniqChar("aabbcc") should be(-1)
      }
    }
    "return first uniq index" - {
      "when there is only one uniq char" in {
        StringProblems.firstUniqChar("bbccaccbbeee") should be(4)
      }
      "when there are multiple uniq/distinct chars" in {
        StringProblems.firstUniqChar("leetcode") should be(0)
      }
    }
  }

  "Ransom Note" - {
    "can not construct" - {
      "when ransom length > magazine length" in {
        StringProblems.canConstruct("abcd", "a") shouldBe false
      }
      "when ransom has chars missing in magazine" in {
        StringProblems.canConstruct("abbc", "adda") shouldBe false
      }
    }
    "can construct" - {
      "when ransom intersects magazine" in {
        StringProblems.canConstruct("abc", "zabsca") shouldBe true
      }
      "with magazine and ransom having duplicated chars" in {
        StringProblems.canConstruct("aaacbbcbb", "ccaaabbbb") shouldBe true
      }
    }
  }
}
