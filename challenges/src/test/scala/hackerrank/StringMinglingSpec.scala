package hackerrank

import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.matchers.should.Matchers
import org.scalacheck.Gen
import org.scalactic.anyvals.PosInt
import scala.util.Random

class StringMinglingSpec extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {

  property("length of mingled = 2 x (p.length) ") {
    forAll() { s: String =>
      StringMingling.mingle(s, s).length should ===(s.length() + s.length())
    }
  }
}
