package hackerrank

class StringMinglingWordSpec extends testtypes.UnitTestPropSpec {

  property("length of mingled = 2 x (p.length) ") {
    forAll() { s: String =>
      StringMingling.mingle(s, s).length must ===(s.length() + s.length())
    }
  }
}
