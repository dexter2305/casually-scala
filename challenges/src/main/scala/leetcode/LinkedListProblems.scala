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

    //https://leetcode.com/problems/intersection-of-two-linked-lists/
    def getIntersectionNode(headA: ListNode, headB: ListNode): ListNode = {
      // keep moving next until there is one intersecting node - two lists always meet at null, finally
      // keep swapping heads between p1 and p2 until the intersecting node is reached which could be null at the end.

      def loop(a: ListNode, b: ListNode, headA: ListNode = headA, headB: ListNode = headB): Option[ListNode] = (a, b) match {
        case (null, null)     => None
        case (a, null)        => loop(a.next, headA)
        case (null, b)        => loop(headB, b.next)
        case (a, b) if a == b => Some(a)
        case (a, b)           => loop(a.next, b.next)
      }
      loop(headA, headB) match {
        case Some(x) => x
        case None    => null
      }
    }

  }

  object mergeTwoList {
    //Definition for singly-linked list.
    class ListNode(_x: Int = 0, _next: ListNode = null) {
      var next: ListNode              = _next
      var x: Int                      = _x
      override def toString(): String = s"$x -> $next"
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

    //https://leetcode.com/problems/middle-of-the-linked-list/
    def middleNode(head: ListNode): ListNode = {

      @scala.annotation.tailrec
      def traverse(slow: ListNode, fast: ListNode): ListNode = {
        Option(fast) match {
          case None                      => slow.next
          case Some(x) if x.next == null => slow.next
          case _                         => traverse(slow.next, fast.next.next)
        }
      }

      Option(head) match {
        case None                            => head
        case Some(node) if node.next == null => node
        case _                               => traverse(head, head.next.next)
      }

    }
  }
}
