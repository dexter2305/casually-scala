package leetcode


import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must.Matchers

class SampleWordSpec extends AnyWordSpec with Matchers {


  // Describe a scope for a subject, in this case: "A Set"
  "A Set" when { // All tests within these curly braces are about "A Set"

    val set = Set()
    // Can describe nested scopes that "narrow" its outer scopes
    "empty" should { // All tests within these curly braces are about "A Set (when empty)"

      "have size 0" in {            // Here, 'it' refers to "A Set (when empty)". The full name
        set.size mustBe 0 // of this test is: "A Set (when empty) should have size 0"
      }
      "produce NoSuchElementException when head is invoked" in { // Define another test
        intercept[NoSuchElementException] {
          set.head
        }
      }
      "must be empty" ignore { // To ignore a test, change 'it' to 'ignore'...
        set.isEmpty mustBe true
      }
    }

    // Describe a second nested scope that narrows "A Set" in a different way
    "non-empty" should { // All tests within these curly braces are about "A Set (when non-empty)"

      "have the correct size" in {     // Here, 'it' refers to "A Set (when non-empty)". This test's full
        assert(Set(1, 2, 3).size == 3) // name is: "A Set (when non-empty) should have the correct size"
      }
      // Define a pending test by using (pending) for the body
      "return a contained value when head is invoked" is pending
      "be non-empty" in { // Tag a test by placing a tag object after the test name
        assert(Set(1, 2, 3).nonEmpty)
      }
    }
  }
}

