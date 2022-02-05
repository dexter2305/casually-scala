package codewars

object Kyu7 {

  def findShort(str: String): Int = str.split(" ").sorted.reverse.head.length()

}
