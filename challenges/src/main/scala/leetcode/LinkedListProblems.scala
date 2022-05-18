package leetcode

object LinkedListProblems extends App {

  import mergeTwoList._
  val one   = new ListNode(1)
  val three = new ListNode(3)
  val two   = new ListNode(2)
  val four  = new ListNode(4)
  val a1    = new ListNode(1)
  val a2    = new ListNode(1)
  val a4    = new ListNode(4)
  one.next = a1
  a1.next = a2
  a2.next = two
  two.next = three
  three.next = four
  four.next = a4
  println(s"${showList(deleteDuplicates(one))}")

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

    // https://leetcode.com/problems/remove-linked-list-elements/
    def removeElements(head: ListNode, `val`: Int): ListNode = {
      if (head == null) null
      else if (head.x == `val`) removeElements(head.next, `val`)
      else {
        head.next = removeElements(head.next, `val`)
        head
      }
    }

    /*
      1 -> 2 -> 3 -> 4 -> null
     */
    // https://leetcode.com/problems/reverse-linked-list/
    def reverseList(cur: ListNode): ListNode = {
      if (cur == null || cur.next == null) cur
      // returns 4
      else {
        val head = reverseList(cur.next)
        cur.next.next = cur
        cur.next = null
        head
      }
    }

    def deleteDuplicates(head: ListNode): ListNode = {
      def loop(left: ListNode, right: ListNode): Unit = {
        (left, right) match {
          case (left, right) if right == null     =>
            left.next = null
            ()
          case (left, right) if left.x == right.x => loop(left, right.next)
          case (left, right) if left.x < right.x  =>
            left.next = right
            loop(right, right.next)
        }
      }
      head match {
        case null => head
        case _    =>
          loop(head, head.next)
          head
      }
    }
  }
}
