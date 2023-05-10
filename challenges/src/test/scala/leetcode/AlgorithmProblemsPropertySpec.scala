package leetcode

import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.matchers.must.Matchers
import AlgorithmProblems._
import org.scalacheck.Gen._
import org.scalacheck.Gen

class AlgorithmProblemsPropertySpec extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {

  property("reverse a string") {
    forAll((Gen.asciiPrintableStr), minSuccessful(100)) { (string: String) =>
      val input = string.toCharArray()
      reverseString(input)
      input.mkString must be(string.reverse)
    }
  }
}
