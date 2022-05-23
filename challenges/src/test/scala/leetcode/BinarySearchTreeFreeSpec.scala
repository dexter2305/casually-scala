package leetcode

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import BinarySearchTree._
class BinarySearchTreeFreeSpec extends AnyFreeSpec with Matchers {

  "Insert into BST" - {
    "when tree is empty" - {
      "root value should be inserted value" in {
        val root = insertIntoBST(null, 3)
        root.value should be(3)
      }
      "left node of root should be null" in {
        val root = insertIntoBST(null, 3)
        root.left should be(null)
      }
      "right node of root should be null" in {
        val root = insertIntoBST(null, 3)
        root.right should be(null)
      }
    }
  }

}
