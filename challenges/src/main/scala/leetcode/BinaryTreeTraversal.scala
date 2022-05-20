package leetcode

object BinaryTreeTraversal extends App {

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int      = _value
    var left: TreeNode  = _left
    var right: TreeNode = _right
  }

  //https://leetcode.com/problems/binary-tree-preorder-traversal/
  def preorderTraversal(root: TreeNode): List[Int] = {
    // root + left + right
    if (root == null) List.empty[Int]
    else root.value +: (preorderTraversal(root.left) ++ preorderTraversal(root.right))
  }

  //https://leetcode.com/problems/binary-tree-inorder-traversal/
  def inorderTraversal(root: TreeNode): List[Int] = {
    //left + root + right
    if (root == null) List.empty[Int]
    else {
      (inorderTraversal(root.left) :+ root.value) ::: inorderTraversal(root.right)
    }
  }

  //https://leetcode.com/problems/binary-tree-postorder-traversal/
  def postorderTraversal(root: TreeNode): List[Int] = {
    if (root == null) List.empty[Int]
    else {
      (postorderTraversal(root.left) ::: postorderTraversal(root.right)) :+ root.value
    }
  }

}
