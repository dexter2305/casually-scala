package interviewbit

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

class PickFromBothSidesSpec extends AnyFreeSpec with Matchers {
  import ArrayProblems._
  "PickFromBothSides" - {

    "should pass with array of only positives" in {
      val nums = Array(1, 2, 3, 4, 5)
      val n    = 4
      pickFromBothSides(nums, n) should be(14)
    }

    "should pass with array of only negatives" in {
      val nums = Array(5, -2, 3, 1, 2)
      val n    = 3
      pickFromBothSides(nums, n) should be(8)
    }

    "should pass with array positive and negatives" in {

      val nums = Array(-533, -666, -500, 169, 724, 478, 358, -38, -536, 705, -855, 281, -173, 961, -509, -5, 942, -173, 436, -609, -396, 902, -847,
        -708, -618, 421, -284, 718, 895, 447, 726, -229, 538, 869, 912, 667, -701, 35, 894, -297, 811, 322, -667, 673, -336, 141, 711, -747, -132,
        547, 644, -338, -243, -963, -141, -277, 741, 529, -222, -684, 35)
      val n    = 48
      pickFromBothSides(nums, 48) should be(6253)
    }
  }

}
