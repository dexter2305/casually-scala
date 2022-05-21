package leetcode

import org.scalatest.freespec.AnyFreeSpec
import leetcode.BinaryTreeTraversal._
import org.scalatest.matchers.should.Matchers
import data._

object data {

  val emptyTree        = null
  val treeWithOnlyRoot = new TreeNode(0)
  val balancedTree: TreeNode = {
    /*
    ---- 0
        /\
      1   4
     /\   /\
    2  3 5  6

    preorder => 0123456
    inorder => 213546
    post order => 2315640
     */
    val nodes = (0 to 6).map(new TreeNode(_)).toArray
    nodes(0).right = nodes(4)
    nodes(0).left = nodes(1)
    nodes(1).left = nodes(2)
    nodes(1).right = nodes(3)
    nodes(4).left = nodes(5)
    nodes(4).right = nodes(6)
    nodes(0)
  }
  val leftOnlyTree: TreeNode = {
    val nodes = (0 to 4).map(new TreeNode(_))
    nodes.reduce { (a, b) =>
      a.left = b
      b
    }
    nodes(0)
  }

  val rightOnlyTree = {
    val nodes = (0 to 4).map(new TreeNode(_))
    nodes.reduce { (a, b) =>
      a.right = b
      b
    }
    nodes(0)
  }
}

class BinaryTreeTraversalFreeSpec extends AnyFreeSpec with Matchers {
  "Preorder traversal" - {
    "when tree is empty" in { preorderTraversal(emptyTree) shouldBe (List.empty[Int]) }
    "when tree has only root node" in { preorderTraversal(treeWithOnlyRoot) shouldBe List(treeWithOnlyRoot.value) }
    "when tree is balanced" in { preorderTraversal(balancedTree) shouldBe List(0, 1, 2, 3, 4, 5, 6) }
    "when tree is left only" in { preorderTraversal(leftOnlyTree) should be(List(0, 1, 2, 3, 4)) }
    "when tree is right only" in { preorderTraversal(rightOnlyTree) should be(List(0, 1, 2, 3, 4)) }
  }
  "Inorder traversal" - {
    "when tree is empty" in { inorderTraversal(emptyTree) should be(List.empty[Int]) }
    "when tree with only root" in { inorderTraversal(treeWithOnlyRoot) should be(List(treeWithOnlyRoot.value)) }
    "when tree is balanced" in { inorderTraversal(balancedTree) should be(List(2, 1, 3, 0, 5, 4, 6)) }
    "when tree is left only" in { inorderTraversal(leftOnlyTree) should be(List(4, 3, 2, 1, 0)) }
    "when tree is right only" in { inorderTraversal(rightOnlyTree) should be(List(0, 1, 2, 3, 4)) }
  }
  "Postorder traversal" - {
    "when tree is empty" in { postorderTraversal(emptyTree) should be(List.empty[Int]) }
    "when tree with only root" in { postorderTraversal(treeWithOnlyRoot) should be(List(treeWithOnlyRoot.value)) }
    "when tree is balanced" in { postorderTraversal(balancedTree) should be(List(2, 3, 1, 5, 6, 4, 0)) }
    "when tree is left only" in { postorderTraversal(leftOnlyTree) should be(List(4, 3, 2, 1, 0)) }
    "when tree is right only" in { postorderTraversal(rightOnlyTree) should be(List(4, 3, 2, 1, 0)) }
  }
  "Level order traversal" - {
    "when tree is null" in { levelOrder(null) should be(List.empty) }
    "when tree has one node" in {levelOrder(new TreeNode(1)) should be (List(List(1)))}
  }
}
