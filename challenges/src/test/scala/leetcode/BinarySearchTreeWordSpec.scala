package leetcode

import leetcode.BinarySearchTree
import leetcode.BinarySearchTree._
class BinarySearchTreeWordSpec extends testtypes.UnitTestWordSpec {

  "Search in BST" when {
    val root = new TreeNode(100)
    "BST level is 1" should {
      //searchBST(root, 100) mustBe root
      "return valid node when search value exists" in { searchBST(root, 100) mustBe root }
      "return null when search value is non-existant" in { searchBST(root, 101) mustBe null }
    }
  }
  "Search in BST" when {

    val nodes = (0 to 2).map(new TreeNode(_))
    nodes(1).left = nodes(0)
    nodes(1).right = nodes(2)
    val root  = nodes(1)
    "BST level is 2 (balanced)" should {
      "return node when value exists on left side" in { searchBST(root, 0) mustBe nodes(0) }
      "return node when value exists on right side" in { searchBST(root, 2) mustBe nodes(2) }
    }
  }
}
