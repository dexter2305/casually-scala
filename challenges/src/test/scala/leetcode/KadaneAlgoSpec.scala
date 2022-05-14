package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class KadaneAlgoSpec extends AnyFreeSpec with Matchers {
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
