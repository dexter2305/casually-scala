package leetcode

object StringProblems extends App {

  println(s"${firstUniqChar("aabbcc")}")

  //https://leetcode.com/problems/first-unique-character-in-a-string/
  //2 pass approach
  //pass #1: build frequency array with zero
  //pass #2: find first occurence using index api which returns -1 when non found.
  def firstUniqChar(s: String): Int = {
    //s only containes lower case english letters.
    val frequency = s.foldLeft(Array.ofDim[Int]('z' - 'a' + 1)) { case (frequency, char) =>
      frequency(char - 'a') = frequency(char - 'a') + 1
      frequency
    }
    s.indexWhere(ch => frequency(ch - 'a') == 1)
  }
}
