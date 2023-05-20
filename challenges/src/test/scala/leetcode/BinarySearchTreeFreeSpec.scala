package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import BinarySearchTree._
class BinarySearchTreeFreeSpec extends AnyFreeSpec with Matchers {

  "Insert into BST" - {
    "when tree is empty" - {
      "root value must be inserted value" in {
        val root = insertIntoBST(null, 3)
        root.value must be(3)
      }
      "left node of root must be null" in {
        val root = insertIntoBST(null, 3)
        root.left must be(null)
      }
      "right node of root must be null" in {
        val root = insertIntoBST(null, 3)
        root.right must be(null)
      }
    }
  }

  "isValidBST" - {
    "must be true" - {
      "when root is the only node" in {
        isValidBST(new TreeNode(10)) mustBe true
      }
      "when ONLY node is Int.MinValue" in {
        isValidBST(new TreeNode(Int.MinValue)) mustBe true
      }
      "when ONLY node is Int.MaxValue" in {
        isValidBST(new TreeNode(Int.MaxValue)) mustBe true
      }
      "when [2, 1, 3]" in {
        val nodes = (0 to 2).map(n => new TreeNode(n)).toList
        nodes(1).left = nodes(0)
        nodes(1).right = nodes(2)
        val root  = nodes(1)
        isValidBST(root) mustBe true
      }
      "when [5, 1, 4, null, null, 3, 6]" in {
        val nodes = (0 to 6).map(new TreeNode(_))
        nodes(4).left = nodes(3)
        nodes(4).right = nodes(6)
        nodes(5).right = nodes(4)
        nodes(5).left = nodes(1)

        val root = nodes(5)
        isValidBST(root) mustBe false
      }
      "when [5,4,6,null,null,3,7]" in {
        val nodes = (0 to 7).map(new TreeNode(_))
        nodes(6).left = nodes(3)
        nodes(6).right = nodes(7)
        nodes(5).left = nodes(4)
        nodes(5).right = nodes(6)
        val root  = nodes(5)
        isValidBST(root) mustBe false
      }
    }
  }

}
