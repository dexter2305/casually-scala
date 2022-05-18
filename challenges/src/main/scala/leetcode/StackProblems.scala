package leetcode

object StackProblems {

  //https://leetcode.com/problems/valid-parentheses/
  def isValid(s: String): Boolean = {
    @scala.annotation.tailrec
    def loop(s: String, i: Int, stack: List[Char]): Boolean = {
      if (i == s.length()) stack.size == 0
      else
        s(i) match {
          case '[' | '(' | '{' => loop(s, i + 1, s(i) +: stack)
          case ']'             => if (stack.size > 0 && stack.head == '[') loop(s, i + 1, stack.tail) else false
          case ')'             => if (stack.size > 0 && stack.head == '(') loop(s, i + 1, stack.tail) else false
          case '}'             => if (stack.size > 0 && stack.head == '{') loop(s, i + 1, stack.tail) else false
        }
    }
    loop(s, 0, List.empty[Char])
  }
}
