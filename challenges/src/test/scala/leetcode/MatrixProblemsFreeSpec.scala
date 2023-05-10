package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class MatrixProblemsSpec extends AnyFreeSpec with Matchers {

  "Reshape matrix" - {
    "reshape not possible" - {
      "given matrix < expected" in {
        val input = Array(Array(1, 2, 3), Array(4, 5, 6))
        MatrixProblems.matrixReshape(input, 20, 30) must be(input)
      }
      "given matrix > expected" in {
        val input = Array(Array(1, 2, 3), Array(4, 5, 6))
        MatrixProblems.matrixReshape(input, 2, 2) must be(input)
      }
    }
    "reshape possible" - {
      "2 x 3 => 3 x 2" in {
        val input = Array(Array(1, 2, 3), Array(4, 5, 6))
        MatrixProblems.matrixReshape(input, 3, 2) must be(Array(Array(1, 2), Array(3, 4), Array(5, 6)))

      }
      "1 x 4 => 2 x 2" in {
        val input = Array(Array(1, 2, 3, 4))
        MatrixProblems.matrixReshape(input, 2, 2) must be(Array(Array(1, 2), Array(3, 4)))
      }
      "3 x 3 => 1 x 9" in {
        val input = Array(Array(1, 2, 3), Array(4, 5, 6), Array(7, 8, 9))
        MatrixProblems.matrixReshape(input, 1, 9) must be(Array(Array(1, 2, 3, 4, 5, 6, 7, 8, 9)))
      }
    }
  }
  "Convert 1d array to 2d array" - {
    "when transformation not possible" - {
      "return empty array" - {
        "with input as empty array" in {
          MatrixProblems.construct2DArray(Array(1, 2, 3), 2, 3) must be(Array.empty)
        }
        "with 1d array length is odd" in {
          MatrixProblems.construct2DArray(Array(1, 2, 3, 4, 5), 2, 2) must be(Array.empty)
        }
        "with 1d array length is even; given 2d size is large" in {
          MatrixProblems.construct2DArray(Array(1, 2, 3, 4, 5, 6), 10, 10) must be(Array.empty)
        }
        "with 1d array length is even; given 2d size is small" in {
          MatrixProblems.construct2DArray(Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2, 2) must be(Array.empty)
        }
      }
    }
    "when transformation possible" - {
      "return 2d array" - {
        "with 1d array length is even; given 2d size fits" in {
          MatrixProblems.construct2DArray(Array(1, 2, 3, 4, 5, 6), 2, 3) must be(Array(Array(1, 2, 3), Array(4, 5, 6)))
        }
        "with 1d array length is odd; given 2d with r =1" in {
          MatrixProblems.construct2DArray(Array(1, 2, 3, 4, 5, 6), 1, 6) must be(Array(Array(1, 2, 3, 4, 5, 6)))
        }
      }

    }
  }

  "matrix transpose" - {
    "matrix: 3 x 3" in {
      MatrixProblems.transpose(Array(Array(1, 2, 3), Array(4, 5, 6), Array(7, 8, 9))) mustBe (
        Array(
          Array(1, 4, 7),
          Array(2, 5, 8),
          Array(3, 6, 9),
        ),
      )
    }
    "matrix: 1 x 3" in {
      MatrixProblems.transpose(Array(Array(1,2,3))) must be (Array(Array(1), Array(2), Array(3)))
    }
  }

}
