package neetcode

import neetcode.ArraysAndHashing._
import testtypes.UnitTestPropSpec
import org.scalacheck.Gen
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.propspec.AnyPropSpec
import leetcode.AlgorithmProblems

class ArrayAndHashingPropertySpec extends UnitTestPropSpec("ArrayAndHashing") {

  implicit override val generatorDrivenConfig: PropertyCheckConfiguration =
    PropertyCheckConfiguration(minSuccessful = 100)

  property("contain duplicate == true, if distinct(array).size < array.size") {
    forAll { (array: Array[Int]) =>
      containsDuplicate(array) must be(array.size != array.distinct.size)
    }
  }

  val gArrayOfReals = Gen.containerOf[Array, Int](Gen.choose[Int](0, Int.MaxValue))
  property("minimum operation to make array zero will be equal to number of distinct non-zero elements in array") {
    forAll(gArrayOfReals) { (array: Array[Int]) =>
      // println(s"test array received of length: ${array.length}")
      minimumOperations(array) must be(array.filter(_ != 0).distinct.size)
    }
  }

  property("two sum with table driven examples") {
    val data = Table(
      ("array", "target", "expectation"),
      (Array(2, 7, 11, 15), 9, Array(1, 0))
    )

    forAll(data) { case (array: Array[Int], target: Int, expectation: Array[Int]) =>
      twoSum(array, target) must contain theSameElementsAs expectation
    }
  }

}
