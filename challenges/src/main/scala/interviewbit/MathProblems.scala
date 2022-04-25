package interviewbit

object MathProblems extends App {

  def productOfDigits(n: Int): Int = {

    @scala.annotation.tailrec
    def go(n: Int, acc: Int): Int = {
      if (n < 10) n * acc
      else go(n / 10, (n % 10) * acc)
    }
    go(n, 1)
  }
}
