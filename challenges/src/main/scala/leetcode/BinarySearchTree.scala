package leetcode

object BinarySearchTree extends App {

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int      = _value
    var left: TreeNode  = _left
    var right: TreeNode = _right
  }

  //https://leetcode.com/problems/search-in-a-binary-search-tree/
  @scala.annotation.tailrec
  def searchBST(root: TreeNode, value: Int): TreeNode = {

    if (root == null) null
    else
      root.value match {
        case `value`          => root
        case x if value < x => searchBST(root.left, value)
        case x if value > x => searchBST(root.right, value)
      }
  }

  //https://leetcode.com/problems/insert-into-a-binary-search-tree/
  def insertIntoBST(root: TreeNode, value: Int): TreeNode = {
    Option(root) match {
      case None                          => new TreeNode(value, null, null)
      case Some(x) if value < root.value =>
        root.left = insertIntoBST(root.left, value)
        root
      case _                             =>
        root.right = insertIntoBST(root.right, value)
        root
    }
  }
}
