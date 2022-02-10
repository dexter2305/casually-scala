package hackerrank

import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.matchers.should.Matchers
import org.scalacheck.Gen

class StringOPermuteSpec extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {

  val evenLengthStringGen =
    Gen.alphaNumStr.map { s =>
      s.length match {
        case 0 => s
        case l if l % 2 == 0 => s
        case l if l % 2 == 1 => s"$s${Gen.alphaNumChar}"
      }
    }

  property("length of permutted string is always equal to given string") {
    forAll(evenLengthStringGen) { s: String =>
      StringOPermute.stringOPermute(s).length() should be(s.length())
    }
  }

  property("permuting permuted string should return given string") {
    forAll(evenLengthStringGen) { s =>
      val permuted  = StringOPermute.stringOPermute(s)
      val expectedS = StringOPermute.stringOPermute(permuted)
      expectedS should ===(s)
    }
  }
}
