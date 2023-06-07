import munit.FunSuite
class HelperSuite extends FunSuite {

  def check[T](
      name: String,
      original: List[T],
      expected: Option[T]
  )(implicit loc: munit.Location): Unit =
    test(name) {
      val obtained = original.headOption
      assertEquals(obtained, expected)
    }

    
    test("Check for nested-ness") {
      
      check("basic", List(1, 2), Some(1))
      check("empty", List(), Some(1))
      check("null", List(null, 2), Some(null))
    }

}
