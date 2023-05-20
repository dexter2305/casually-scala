package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {

  var value: Int      = _value
  var left: TreeNode  = _left
  var right: TreeNode = _right

}

class BSTIterator(_root: TreeNode) {

  val iterator = inorderTraverser(_root)

  def inorderTraverser: TreeNode => Iterator[Int] = {
    case null => Iterator.empty
    case node => inorderTraverser(node.left) ++ Iterator.single(node.value) ++ inorderTraverser(node.right)
  }

  def next(): Int        = iterator.next()
  def hasNext(): Boolean = iterator.hasNext

}
