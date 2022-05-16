package leetcode

object LinkedListProblems extends App {

  import mergeTwoList._
  val one   = new ListNode(1)
  val three = new ListNode(3)
  one.next = three
  val two   = new ListNode(2)
  val four  = new ListNode(4)
  two.next = four

  val oneToFour = mergeTwoLists(one, two)
  println(s">> ${showList(oneToFour)}")

  val minusOne = new ListNode(-1)
  minusOne.next = one
  println(s"${showList(removeElements(minusOne, 2))}")

  object hasCycle {
    //Definition for singly-linked list.
    class ListNode(var _x: Int = 0) {
      var next: ListNode = null
      var x: Int         = _x
    }

    val one   = new ListNode(1)
    val two   = new ListNode(2)
    val three = new ListNode(3)
    val four  = new ListNode(4)
    one.next = two
    two.next = three
    three.next = four
    four.next = one

    //println(hasCycle(one))

    // https://leetcode.com/problems/linked-list-cycle/
    def hasCycle(head: ListNode): Boolean = {
      @scala.annotation.tailrec
      def race(slow: ListNode, fast: ListNode): Boolean = {
        //println(s"slow: ${slow._x}, fast: ${fast._x}")
        if (fast == null || fast.next == null) false
        else if (fast.eq(slow)) true
        else race(slow.next, fast.next.next)
      }
      if (head == null) false
      else race(head, head.next)
    }
  }

  object mergeTwoList {
    //Definition for singly-linked list.
    class ListNode(_x: Int = 0, _next: ListNode = null) {
      var next: ListNode = _next
      var x: Int         = _x
    }
    def showNode(node: ListNode): String = if (node != null) node.x.toString else "NULL"
    def showList(node: ListNode): String = {
      def loop(head: ListNode, acc: String = ""): String = if (head == null) acc else loop(head.next, s"$acc ${head.x}")
      loop(node)
    }
    // https://leetcode.com/problems/merge-two-sorted-lists/
    def mergeTwoLists(left: ListNode, right: ListNode): ListNode = {
      (left, right) match {
        case (null, null)           => null
        case (null, r) if r != null => r
        case (l, null) if l != null => l
        case (l, r)                 =>
          if (l.x < r.x) {
            l.next = mergeTwoLists(l.next, r)
            l
          } else {
            r.next = mergeTwoLists(l, r.next)
            r
          }
      }
    }

    def removeElements(head: ListNode, `val`: Int): ListNode = {
      if (head == null) null
      else if (head.x == `val`) removeElements(head.next, `val`)
      else {
        head.next = removeElements(head.next, `val`)
        head
      }
    }
  }
}
