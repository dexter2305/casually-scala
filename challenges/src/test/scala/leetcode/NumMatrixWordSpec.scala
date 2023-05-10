package leetcode

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must.Matchers
import leetcode.PrefixSumProblems._
class NumMatrixWordSpec extends AnyWordSpec with Matchers {

  "Prefix Sum Matrix" when {
    val whenTitle = raw"""
      matrix: 3 x 3
      10    20    30
       5    10    20
       2     4     6
    """
    val source    = Array(
      Array(10, 20, 30),
      Array(5, 10, 20),
      Array(2, 4, 6),
    )
    val o         = new NumMatrix(source)
    val dp        = o.matrixPrefixSumWithLeadingZeroes()

    s"$whenTitle" should {
      "have row 0 =>  0   0   0    0" in { dp(0) mustBe Array(0, 0, 0, 0) }
      "have row 1 =>  0  10  30   60" in { dp(1) mustBe Array(0, 10, 30, 60) }
      "have row 2 =>  0  15  45   95" in { dp(2) mustBe Array(0, 15, 45, 95) }
      "have row 3 =>  0  17  51  107" in { dp(3) mustBe Array(0, 17, 51, 107) }
    }

    "Range Sum Query - 2D immutable" when {
      val sourceArray    = Array(
        Array(3, 0, 1, 4, 2),
        Array(5, 6, 3, 2, 1),
        Array(1, 2, 0, 1, 5),
        Array(4, 1, 0, 1, 7),
        Array(1, 0, 3, 0, 5),
      )
      val o              = new NumMatrix(sourceArray)
      val matrixAsString = sourceArray.map(_.mkString(" | ")).mkString("\n")
      val title          = s"matrix: 5 x 5 \n$matrixAsString"
      s"$title" should {
        "return 8 for range (2,1) -> (4,3) " in { o.sumRegion(2, 1, 4, 3) must be(8) }
        "return 11 for range (1,1) -> (2,2)" in (o.sumRegion(1, 1, 2, 2))
      }

    }
  }
}
