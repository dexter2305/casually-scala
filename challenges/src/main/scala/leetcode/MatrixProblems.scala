package leetcode

object MatrixProblems extends App {

  //https://leetcode.com/problems/reshape-the-matrix/
  //assumption: every row is equally sized
  //t: O(n)
  //s: O(n); new array has to be built
  def matrixReshape(mat: Array[Array[Int]], r: Int, c: Int): Array[Array[Int]] = {
    if (mat.length > 0 && mat.length * mat(0).length == r * c) {

      val n = mat.head.size
      val x = for (x <- 0 until r) yield for (y <- 0 until c) yield {
        val index = (c * x) + y
        //println(s"$index => (${index / n}, ${index % n})")
        mat(index / n)(index % n)
      }
      x.map(_.toArray).toArray
    } else mat
  }

  //https://leetcode.com/problems/convert-1d-array-into-2d-array/
  def construct2DArray(original: Array[Int], r: Int, c: Int): Array[Array[Int]] = {
    if (r * c == original.length) {
      //((for (i <- 0 until r) yield for (ci <- 0 until c) yield (original(ci + (i * c)))).map(_.toArray)).toArray
      Array.tabulate(r, c)((x, y) => original(x * c + y))
    } else Array.empty
  }

}
