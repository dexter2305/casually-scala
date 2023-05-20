package leetcode

object MatrixProblems extends App {

  val matrix = Array(Array(1, 3, 5, 7), Array(10, 11, 16, 20), Array(23, 30, 34, 60))
  println(searchMatrix(matrix, 16))

  // https://leetcode.com/problems/search-a-2d-matrix/
  // 2d array accessing nth element: array(n / colCount)(n % colCount)
  def searchMatrix(matrix: Array[Array[Int]], target: Int): Boolean = {
    val rowCount = matrix.length
    val colCount = matrix.head.size

    @scala.annotation.tailrec
    def search(matrix: Array[Array[Int]], target: Int, left: Int, right: Int): Boolean = {

      val mid = (left + right - 1) / 2
      println(s"l: $left, r: $right, mid: $mid, e($mid): ${matrix(mid / colCount)(mid % colCount)}")
      (left, right) match {
        case (l, r) if l == r                                           => false
        case (l, r) if matrix(mid / colCount)(mid % colCount) == target => true
        case (l, r) if matrix(mid / colCount)(mid % colCount) < target  => search(matrix, target, mid + 1, right)
        case (l, r) if matrix(mid / colCount)(mid % colCount) > target  => search(matrix, target, left, mid)
      }
    }
    search(matrix, target, 0, rowCount * colCount)

  }

  // https://leetcode.com/problems/reshape-the-matrix/
  // assumption: every row is equally sized
  // t: O(n)
  // s: O(n); new array has to be built
  def matrixReshape(mat: Array[Array[Int]], r: Int, c: Int): Array[Array[Int]] =
    if (mat.length > 0 && mat.length * mat(0).length == r * c) {

      val n = mat.head.size
      val x = for (x <- 0 until r) yield for (y <- 0 until c) yield {
        val index = (c * x) + y
        // println(s"$index => (${index / n}, ${index % n})")
        mat(index / n)(index % n)
      }
      x.map(_.toArray).toArray
    } else mat

  // https://leetcode.com/problems/convert-1d-array-into-2d-array/
  def construct2DArray(original: Array[Int], r: Int, c: Int): Array[Array[Int]] =
    if (r * c == original.length) {
      // ((for (i <- 0 until r) yield for (ci <- 0 until c) yield (original(ci + (i * c)))).map(_.toArray)).toArray
      Array.tabulate(r, c)((x, y) => original(x * c + y))
    } else Array.empty

  def transpose(matrix: Array[Array[Int]]): Array[Array[Int]] = {
    val rowCount = matrix.size
    val colCount = matrix.head.size

    (for (r <- 0 until colCount) yield for (c <- 0 until rowCount) yield matrix(c)(r))
      .map(_.toArray)
      .toArray
  }

}
