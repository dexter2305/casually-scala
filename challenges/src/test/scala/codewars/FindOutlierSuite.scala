package codewars

import org.scalacheck.Gen
import org.scalacheck.Prop._

class FindOutlierSuite extends munit.ScalaCheckSuite {

  val evenGenerator = Gen.listOfN[Int](100, Gen.choose(1, Int.MaxValue).map(i => if (i % 2 == 0) i else i + 1))
  val oddGenerator  = Gen.listOfN[Int](100, Gen.choose(1, Int.MaxValue).map(i => if (i % 2 == 1) i else i - 1))

  property("odd outlier in even list") {
    forAll(evenGenerator) { evens: List[Int] =>
      assertEquals(Kyu6.findOutlier(5 :: evens), 5)
    }
  }
  property("even outlier in odd list") {
    forAll(oddGenerator) { evens: List[Int] =>
      assertEquals(Kyu6.findOutlier(4 :: evens), 4)
    }
  }
}
