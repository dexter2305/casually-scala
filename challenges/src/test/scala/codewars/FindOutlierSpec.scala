package codewars

import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.matchers.should.Matchers
import org.scalacheck.Gen
import org.scalactic.anyvals.PosInt

class FindOutlierSpec extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {

  val evenGenerator = Gen.listOfN[Int](100, Gen.choose(1, Int.MaxValue).map(i => if (i % 2 == 0) i else i + 1))
  val oddGenerator  = Gen.listOfN[Int](100, Gen.choose(1, Int.MaxValue).map(i => if (i % 2 == 1) i else i - 1))

  override def minSuccessful(value: PosInt): MinSuccessful = MinSuccessful(5000)
  property("odd outlier in even list") {
    forAll(evenGenerator) { evens: List[Int] =>
      Kyu6.findOutlier(5 :: evens) should be(5)
    }
  }
  property("even outlier in odd list") {
    forAll(oddGenerator) { evens: List[Int] =>
      Kyu6.findOutlier(4 :: evens) should be(4)
    }
  }
}
