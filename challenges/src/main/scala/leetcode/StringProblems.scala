package leetcode

object StringProblems extends App {

  println(s"${canConstruct("a", "b")}")

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

  //https://leetcode.com/problems/ransom-note/
  def canConstruct(ransomNote: String, magazine: String): Boolean = {
    val frequency: Array[Int] = magazine.foldLeft(Array.ofDim[Int](26)) { (frequency, char) =>
      frequency(char - 'a') += 1
      frequency
    }
    // check if there exists a char in ransomNote that does not exist frequency
    !ransomNote.exists { char =>
      frequency(char - 'a') = frequency(char - 'a') - 1
      frequency(char - 'a') < 0
    }
  }

  //https://leetcode.com/problems/number-of-different-integers-in-a-string/
  //"a123b456bcd" = 2
  def numDifferentIntegers(word: String): Int = {
      val r = "[0-9]+".r
      r.findAllIn(word).toList.map(_.toIntOption).toSet.size 
  }

}
