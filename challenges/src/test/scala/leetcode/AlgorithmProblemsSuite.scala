package leetcode

import org.scalacheck.Prop._
import org.scalacheck.Gen

class AlgorithmProblemsSuite extends munit.ScalaCheckSuite {

  property("reverse a string") {
    forAll(Gen.asciiPrintableStr) { (string: String) =>
      val input = string.toCharArray()
      AlgorithmProblems.reverseString(input)
      assert(input.mkString == string.reverse)
    }
  }
}
