package hackerrank

import org.scalacheck.Prop._

class StringMinglingSuite extends munit.ScalaCheckSuite {

  property("length of mingled = 2 x (p.length) ") {
    forAll { s: String =>
      assertEquals(StringMingling.mingle(s, s).length, s.length() + s.length())
    }
  }
}
