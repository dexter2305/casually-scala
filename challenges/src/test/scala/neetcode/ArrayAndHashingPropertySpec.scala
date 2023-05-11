package neetcode

import neetcode.ArraysAndHashing._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import testtypes.UnitTestPropSpec
import org.scalacheck.Gen
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.propspec.AnyPropSpec

class ArrayAndHashingPropertySpec extends UnitTestPropSpec("ArrayAndHashing") with ScalaCheckPropertyChecks {

  override implicit val generatorDrivenConfig: PropertyCheckConfiguration = PropertyCheckConfiguration(minSuccessful = 100)

  property("contain duplicate == true, if distinct(array).size < array.size") {
    forAll { (array: Array[Int]) =>
      containsDuplicate(array) must be(array.size != array.distinct.size)
    }
  }

  val gArrayOfReals = Gen.containerOf[Array, Int](Gen.choose[Int](0, Int.MaxValue))
  property("minimum operation to make array zero will be equal to number of distinct non-zero elements in array") {
    forAll(gArrayOfReals) { (array: Array[Int]) =>
      //println(s"test array received of length: ${array.length}")
      minimumOperations(array) must be(array.filter(_ != 0).distinct.size)
    }
  }
}
