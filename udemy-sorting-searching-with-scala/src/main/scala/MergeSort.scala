/**
  * Merge sort implementation using scala FP
  * refer https://www.youtube.com/watch?v=nCNfu_zNhyI
  */
object MergeSort extends App {

  def mergeSort(numbers: Seq[Int]): Seq[Int] = {

    def merge(left: Seq[Int], right: Seq[Int]): Seq[Int] = {

      val (sorted, _, _) = (0 until left.length + right.length)
        .foldLeft(List[Int](), left, right) { (triplet, _) =>
          val (merged, leftRemaining, rightRemaining) = triplet
          (leftRemaining, rightRemaining) match {
            case (Nil, r :: rTail)                 => (r :: merged, Nil, rTail)
            case (l :: lTail, Nil)                 => (l :: merged, lTail, Nil)
            case (l :: lTail, r :: rTail) if l < r => (l :: merged, lTail, r :: rTail)
            case (l :: lTail, r :: rTail)          => (r :: merged, l :: lTail, rTail)
            case (Nil, Nil)                        => (merged, Nil, Nil)
          }
        }

      sorted

    }

    if (numbers.length == 1) List(numbers.head)
    else {
      val (left, right) = numbers.splitAt(numbers.length / 2)
      val sortedLeft    = mergeSort(left)
      val sortedRight   = mergeSort(right)
      val sortedList    = merge(sortedLeft, sortedRight)
      sortedList.reverse
    }

  }



  val l = List(100, 6, 1, 3, 8, 2, 5)
  println(s"${mergeSort(l).mkString(" ")}")

}
