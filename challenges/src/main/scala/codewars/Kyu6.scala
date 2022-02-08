package codewars

object Kyu6 {
  def findOutlier(integers: List[Int]): Int = {
    val (_, ints) = integers.groupBy(i => i % 2).find { case (_, elements) => elements.size == 1 }.get
    ints.head
  }
}
