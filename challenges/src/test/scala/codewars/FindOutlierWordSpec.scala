package codewars

import org.scalacheck.Gen
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class FindOutlierWordSpec extends testtypes.UnitTestPropSpec {

  val evenGenerator = Gen.listOfN[Int](100, Gen.choose(1, Int.MaxValue).map(i => if (i % 2 == 0) i else i + 1))
  val oddGenerator  = Gen.listOfN[Int](100, Gen.choose(1, Int.MaxValue).map(i => if (i % 2 == 1) i else i - 1))

  property("odd outlier in even list") {
    forAll(evenGenerator) { evens: List[Int] =>
      Kyu6.findOutlier(5 :: evens) must be(5)
    }
  }
  property("even outlier in odd list") {
    forAll(oddGenerator) { evens: List[Int] =>
      Kyu6.findOutlier(4 :: evens) must be(4)
    }
  }

}
