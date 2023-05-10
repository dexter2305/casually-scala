package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class StringProblemsFreeSpec extends AnyFreeSpec with Matchers {

  "First Uniq Char" - {
    "return -1" - {
      "when there are no unique chars" in {
        StringProblems.firstUniqChar("aabbcc") must be(-1)
      }
    }
    "return first uniq index" - {
      "when there is only one uniq char" in {
        StringProblems.firstUniqChar("bbccaccbbeee") must be(4)
      }
      "when there are multiple uniq/distinct chars" in {
        StringProblems.firstUniqChar("leetcode") must be(0)
      }
    }
  }

  "Ransom Note" - {
    "can not construct" - {
      "when ransom length > magazine length" in {
        StringProblems.canConstruct("abcd", "a") mustBe false
      }
      "when ransom has chars missing in magazine" in {
        StringProblems.canConstruct("abbc", "adda") mustBe false
      }
    }
    "can construct" - {
      "when ransom intersects magazine" in {
        StringProblems.canConstruct("abc", "zabsca") mustBe true
      }
      "with magazine and ransom having duplicated chars" in {
        StringProblems.canConstruct("aaacbbcbb", "ccaaabbbb") mustBe true
      }
    }
  }

  "Number of distinct ints in a string" - {
    "simple cases" - {
      "when string has no int" in {
        StringProblems.numDifferentIntegers("abc") must be(0)
      }
      "when string has only ints" in {
        StringProblems.numDifferentIntegers("123") must be(1)
      }
      "when string has distinct ints separated by chars" in {
        StringProblems.numDifferentIntegers("a123b456c") must be(2)
      }
      "when string duplicate ints separated by chars" in {
        StringProblems.numDifferentIntegers("a123b123c") must be(1)
      }
      "when string of ints exceeds (2^ 32) -1; breaking assumptions with Int" in {
        StringProblems.numDifferentIntegers("123131313131318892942194128412461247129812374912836129219842139846312") must be(1)
      }
    }
    "cases dealing with zero" - {
      "with 0 is the only char" in {
        StringProblems.numDifferentIntegers("0") must be(1)
      }
      "with 0 and 00 are separated by chars" in {
        StringProblems.numDifferentIntegers("a0bc00sd") must be (1)
      }
      "with 01 and 1" in {
        StringProblems.numDifferentIntegers("a1bc01asdf") must be (1)
      }
      "with 10 and 1" in {
        StringProblems.numDifferentIntegers("a10asdf1") must be (2)
      }
    }
  }
}
