package neetcode

import testtypes.UnitTestWordSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import ArraysAndHashing._
import org.scalactic.Bool

class ArrayAndHashingWordSpec extends UnitTestWordSpec with TableDrivenPropertyChecks {

  val examplesContainsDuplicate = Table(
    ("array", "result"),
    (Array(1, 2, 3, 1), true),
    (Array(1, 2, 3, 4), false),
    (Array.emptyIntArray, false),
  )
  "Contains duplicate" must {
    forAll(examplesContainsDuplicate) { (array: Array[Int], expectation: Boolean) =>
      s"eval [${array.mkString("-")}] to $expectation" in {
        containsDuplicate(array)
      }
    }
  }

  val examplesMinOpToMakeZero = Table(
    ("array", "opCount"),
    (Array(1, 5, 0, 3, 5), 3),
    (Array(1, 2, 3, 4, 5), 5),
    (Array(10, 10, 10), 1),
  )
  "Minimum operations to make all zero in array" must {
    forAll(examplesMinOpToMakeZero) { (array: Array[Int], expectedOpCount: Int) =>
      s"return $expectedOpCount for [${array.mkString("-")}]" in {
        minimumOperations(array) must be(expectedOpCount)
      }
    }
  }

  val examplesTwoSum = Table(
    ("array", "target", "expectation"),
    (Array(2, 7, 11, 15), 9, Array(0, 1)),
    (Array(3, 2, 4), 6, Array(1, 2)),
    (Array(3, 3), 6, Array(1, 0)),
  )
  "Two Sum" must {
    forAll(examplesTwoSum) { (array: Array[Int], target: Int, expectation: Array[Int]) =>
      s"return [${expectation.mkString("-")}] for $target on given array [${array.mkString("-")}]" in {
        twoSum(array, target) must contain.theSameElementsAs(expectation)
      }
    }
  }

  val examplesAnagram = Table(
    ("s", "t", "is anagram?"),
    ("anagram", "nagaram", true),
    ("bat", "cat", false),
  )

  "Is anagram?" must {

    forAll(examplesAnagram) { (s: String, t: String, expectation: Boolean) =>
      s"eval '$s' and '$t' to $expectation" in { isAnagram(s, t) mustBe expectation }
    }

    "run to completion with anagram strings of size = 5 x 10 ^ 4" in {
      val r = scala.util.Random
      val s = (0 until (5 * math.pow(10, 4).toInt)).toList.map(_ => (r.between(0, 26) + 64).toChar).mkString
      val t = s
      isAnagram(s, t) must be(true)
    }
    "run to completion with unequal strings of size = 5 x 10 ^ 4" in {
      val r = scala.util.Random
      val s = (0 until (5 * math.pow(10, 4).toInt)).toList.map(_ => (r.between(0, 26) + 64).toChar).mkString
      val t = (0 until (5 * math.pow(10, 4).toInt)).toList.map(_ => (r.between(0, 26) + 64).toChar).mkString
      isAnagram(s, t) must be(false)
    }
  }

  val twoSumExample = Table(
    ("Array", "target", "expectation"),
    (Array(1, 2, 3, 4, 5, 6), 11, Array(5, 4)),
    (Array(-1, -3, 5, 7, 6), 6, Array(0, 3)),
  )
  "Two sum" must {
    forAll(twoSumExample) { (array: Array[Int], target: Int, expectation: Array[Int]) =>
      s"return [${expectation.mkString("-")}] for input [${array.mkString("-")}] with target as $target" in {
        twoSum(array, target) must contain theSameElementsAs expectation
      }
    }
  }

}
