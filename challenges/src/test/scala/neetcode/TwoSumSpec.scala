package neetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class TwoSumSpec extends AnyFreeSpec with Matchers {
  import neetcode.TwoSum._

  "Two sum spec" - {
    "with non-existing target should return empty array" in {
      twoSum(Array(1, 2, 3, 4), 100) should be(Array.emptyIntArray)
    }
    "with single existing target" - {
      "should return indices of elements." in {
        twoSum(Array(1, 2, 3, 4, 5, 6), 11) should contain theSameElementsAs (Array(5, 4))
      }
      "should work with sequence with negative numbers." in {
        twoSum(Array(-1, -3, 5, 7, 6), 6) should contain theSameElementsAs (Array(0, 3))
      }
    }
  }

}
