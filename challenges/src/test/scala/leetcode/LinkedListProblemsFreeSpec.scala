package leetcode

import org.scalatest.freespec.AnyFreeSpec
import LinkedListProblems.hasCycle._
import org.scalatest.matchers.should.Matchers
class LinkedListProblemsFreeSpec extends AnyFreeSpec with Matchers {

  "HasCycle" - {
    "has no loop" - {
      "when length == 0" in {
        hasCycle(null) should be (false)
      }
      "when length == 1" in {
        val one = new ListNode(1)
        hasCycle(one) shouldBe false
      }
      "when list ends with null" in {
        val one = new ListNode(1)
        val two = new ListNode(2)
        one.next = two 
        hasCycle(one) shouldBe false
      }
    }
    "has loop" - {
      "with list with even number of elements" in {
        val one = new ListNode(1)
        val two = new ListNode(2)
        val thr = new ListNode(3)
        val fou = new ListNode(4)
        one.next = two
        two.next = thr
        thr.next = fou
        fou.next = thr
        hasCycle(one) should be (true)
      }
      "with list with odd number of elements" in {
        val one = new ListNode(1)
        val two = new ListNode(2)
        val thr = new ListNode(3)
        one.next = two
        two.next = thr
        thr.next = one
        hasCycle(one) should be (true)
      }      
    }

  }
}
