package interviewbit

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.prop.TableDrivenPropertyChecks

class PickFromBothSidesWordSpec extends testtypes.UnitTestWordSpec with TableDrivenPropertyChecks {
  import ArrayProblems._

  val examples = Table(
    ("testcase","input array", "elements to take", "sum"),
    ("pass with array of only positives", Array(1, 2, 3, 4, 5), 4, 14),
    ("pass with array positive and negatives",Array(-533, -666, -500, 169, 724, 478, 358, -38, -536, 705, -855, 281, -173, 961, -509, -5, 942, -173, 436, -609, -396, 902, -847,
        -708, -618, 421, -284, 718, 895, 447, 726, -229, 538, 869, 912, 667, -701, 35, 894, -297, 811, 322, -667, 673, -336, 141, 711, -747, -132,
        547, 644, -338, -243, -963, -141, -277, 741, 529, -222, -684, 35) , 48, 6253 )
  )

  "PickFromBothSides" must {
    forAll(examples){(testcase: String, array: Array[Int], n: Int, expectation: Int) => 
      testcase in {
        pickFromBothSides(array, n) mustBe expectation
      }
    }
  }
}
