package neetcode

import neetcode.ArraysAndHashing._
import org.scalacheck.Prop._
import org.scalacheck.Gen

class ArrayAndHashingSuite extends munit.ScalaCheckSuite {

  // implicit override val generatorDrivenConfig: PropertyCheckConfiguration =
  //   PropertyCheckConfiguration(minSuccessful = 100)

  property("contain duplicate == true, if distinct(array).size < array.size") {
    forAll { (array: Array[Int]) =>
      assertEquals(containsDuplicate(array), array.size != array.distinct.size)
    }
  }

  property("minimum operation to make array zero will be equal to number of distinct non-zero elements in array") {
    val gArrayOfReals = Gen.containerOf[Array, Int](Gen.choose[Int](0, Int.MaxValue))
    forAll(gArrayOfReals) { (array: Array[Int]) =>
      assertEquals(minimumOperations(array), array.filter(_ != 0).distinct.size)
    }
  }

  test("two sum: using property over fixed data".ignore) {
    val samples = Seq(
      (Array(1, 2, 3, 4), 9, Array(1, 0)),
      (Array(5, 6, 7, 8), 9, Array(1, 0)),
      (Array(9, 10, 11, 12), 9, Array(1, 0))
    )
    samples.foreach { case (input: Array[Int], target: Int, exp: Array[Int]) =>
      assertEquals(twoSum(input, target), exp)
    }
  }

  test("contains duplicate identifies duplicates in array") {
    val samples = Seq(
      (Array(1, 2, 3, 1), true),
      (Array(1, 2, 3, 4), false),
      (Array.emptyIntArray, false)
    )
    for ((ints, exp) <- samples)
      assertEquals(containsDuplicate(ints), exp)
  }

  test("anagram examples must pass") {
    val examplesAnagram = Seq(
      ("anagram", "nagaram", true),
      ("bat", "cat", false)
    )

    examplesAnagram.foreach { case (a, b, exp) => assert(isAnagram(a, b) == exp, s"$a is anagram of $b => $exp") }
  }

}
