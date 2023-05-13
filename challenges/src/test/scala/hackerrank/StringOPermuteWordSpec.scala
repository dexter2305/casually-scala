package hackerrank

import org.scalacheck.Gen

class StringOPermuteWordSpec extends testtypes.UnitTestPropSpec{

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
        StringOPermute.stringOPermute(s) must be(s)
      } else StringOPermute.stringOPermute(s).length() must be(s.length())
    }
  }

  property("permuting permuted string should return given string") {
    forAll((evenLengthStringGen, "string")) { s =>
      val permuted  = StringOPermute.stringOPermute(s)
      val expectedS = StringOPermute.stringOPermute(permuted)
      expectedS must be(s)
    }
  }
}
