package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class KadaneAlgorithmSpec extends AnyFreeSpec with Matchers {

  "max sum array" - {
    "with only positive ints" - {
      "sum should be sum of all elements" in {
        KadaneAlgorithm.maxSubArray(Array(1, 2, 3, 4)) should be(10)
      }
    }
    "with only negatives ints" - {
      "should be the largest of all negatives" in {
        KadaneAlgorithm.maxSubArray((-5 to -1).toArray) should be(-1)
      }
    }
    "with mix of signed ints" - {
      "should be largest sub array" in {
        KadaneAlgorithm.maxSubArray(Array(-2, 1, -3, 4, -1, 2, 1, -5, 4)) should be(6)
      }
    }

  }
  "maxProfit" - {
    "should be profit when prices increase constantly" in {
      KadaneAlgorithm.maxProfit(Array(1, 2, 3)) should be(2)
    }
    "should be no profit when prices decrease constantly" in {
      KadaneAlgorithm.maxProfit(Array(7, 6, 5, 4, 3)) should be(0)
    }
    "profit when early peaks with drops" in {
      KadaneAlgorithm.maxProfit(Array(7, 1, 5, 3, 6, 4)) should be(5)
    }
  }
}
