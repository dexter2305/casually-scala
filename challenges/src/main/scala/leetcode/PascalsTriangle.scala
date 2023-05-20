package leetcode

object PascalsTriangle extends App {

  generate(3).foreach(r => println(r.mkString(" ")))
  // https://leetcode.com/problems/pascals-triangle/
  // every row starts with '1'
  //
  def generate(numRows: Int): List[List[Int]] = {
    lazy val pascalsNthRow: Int => List[Int] = n => {
      val (_, row) = (1 to n).foldLeft((1, List(1))) { case ((prev, acc), r) =>
        val e = (prev * (n - r + 1)) / r
        (e, acc :+ e)
      }
      row
    }
    val triangle                             = (0 to numRows).map(n => pascalsNthRow(n)).toList
    triangle
  }

}
