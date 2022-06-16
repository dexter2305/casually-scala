package leetcode

object BinarySearchTree extends App {

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int                  = _value
    var left: TreeNode              = _left
    var right: TreeNode             = _right
    override def toString(): String = s"$value"
  }

  //https://leetcode.com/problems/search-in-a-binary-search-tree/
  @scala.annotation.tailrec
  def searchBST(root: TreeNode, value: Int): TreeNode = {
    Option(root) match {
      case None       => null
      case Some(node) =>
        node.value match {
          case x if value == x => node
          case x if value < x  => searchBST(node.left, value)
          case x if value > x  => searchBST(node.right, value)
        }
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

  def isValidBST(root: TreeNode): Boolean = {

    def validate(oNode: Option[TreeNode], oLow: Option[Int], oHigh: Option[Int]): Boolean = oNode match {
      case None       => true
      case Some(node) =>
        println(s"evaluating ${node.value} in range [$oLow -> $oHigh]")
        (oLow, oHigh) match {

          case (None, None)            =>
            validate(Option(node.left), oLow, Option(node.value)) &&
              validate(Option(node.right), Option(node.value), oHigh)
          case (None, Some(high))      =>
            node.value < high &&
              validate(Option(node.left), oLow, Option(node.value)) &&
              validate(Option(node.right), Option(node.value), oHigh)
          case (Some(low), None)       =>
            low < node.value &&
              validate(Option(node.left), oLow, Option(node.value)) &&
              validate(Option(node.right), Option(node.value), oHigh)
          case (Some(low), Some(high)) =>
            low < node.value && node.value < high &&
              validate(Option(node.left), oLow, Option(node.value)) &&
              validate(Option(node.right), Option(node.value), oHigh)
        }
    }

    validate(Option(root), None, None)

  }

  //https://leetcode.com/explore/learn/card/introduction-to-data-structure-binary-search-tree/141/basic-operations-in-a-bst/1006/
  def deleteNode(root: TreeNode, key: Int): TreeNode = {
    ???
  }

}
