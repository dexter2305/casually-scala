package hackerrank

import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.matchers.should.Matchers
import org.scalacheck.Gen
import org.scalactic.anyvals.PosInt

class StringOPermuteSpec extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {

  override def minSuccessful(value: PosInt): MinSuccessful = MinSuccessful(500)

  val evenLengthStringGen =
    Gen.alphaNumStr.map { s =>
      s.length match {
        case 0 => s
        case l if l % 2 == 0 => s
        case l if l % 2 == 1 => s"${s.substring(0, s.length - 1)}"
      }
    }

  property("length of permutted string is always equal to given string") {
    forAll((evenLengthStringGen, "string")) { s: String =>
      if (s.isEmpty) {
        println("*** empty string ")
        StringOPermute.stringOPermute(s) should be(s)
      } else StringOPermute.stringOPermute(s).length() should be(s.length())
    }
  }

  property("permuting permuted string should return given string") {
    forAll((evenLengthStringGen, "string")) { s =>
      val permuted  = StringOPermute.stringOPermute(s)
      val expectedS = StringOPermute.stringOPermute(permuted)
      expectedS should be(s)
    }
  }
}
