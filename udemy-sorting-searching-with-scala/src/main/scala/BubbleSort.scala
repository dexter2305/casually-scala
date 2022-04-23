object BubbleSort extends App {

  def bubbleSort_v1(numbers: Array[Int]): Array[Int] = {
    var counter = 0
    for (k <- 1 until numbers.length) {
      for (j <- 0 until numbers.length - 1) {
        if (numbers(j) > numbers(j + 1)) {
          val x = numbers(j)
          numbers(j) = numbers(j + 1)
          numbers(j + 1) = x
          counter = counter + 1
        }
      }
    }
    numbers
  }

  /*
  Transformed from double for-loop to a single using scala constructs. Effect is still the same as v2. 
  */

  def bubbleSort_v2(numbers: Array[Int]): Array[Int] = {
    var counter = 0 
    for (
      k <- 1 until numbers.length;
      j <- 0 until numbers.length - 1 if numbers(j) > numbers(j + 1)
    ) {
      val x = numbers(j)
      numbers(j) = numbers(j + 1)
      numbers(j + 1) = x
      counter = counter + 1
    }
    numbers
  }

  def bubbleSort_v3(numbers: Array[Int]): Array[Int] = {
    var counter = 0
    for (
      k <- 1 until numbers.length;
      j <- 0 until numbers.length - k if numbers(j) > numbers(j + 1)
    ) {
      val x = numbers(j)
      numbers(j) = numbers(j + 1)
      numbers(j + 1) = x
      counter = counter + 1
    }
    numbers
  }

  val numbers = Array(9, 1, 3, 8, 2, 4)
  println(s"${bubbleSort_v1(numbers).mkString("-")}")
  println(s"${bubbleSort_v2(numbers).mkString("-")}")
  println(s"${bubbleSort_v3(numbers).mkString("-")}")

}
