package leetcode

object BinaryTree extends App {

  val three   = new TreeNode(3)
  val nine    = new TreeNode(9)
  val twenty  = new TreeNode(20)
  val fifteen = new TreeNode(15)
  val seven   = new TreeNode(7)
  three.left = nine
  three.right = twenty
  twenty.left = fifteen
  twenty.right = seven

  // levelOrder(null)
  // levelOrder(three)

  // invertTree(three)

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {

    var value: Int                  = _value
    var left: TreeNode              = _left
    var right: TreeNode             = _right
    override def toString(): String = s"$value"

  }

  // https://leetcode.com/problems/binary-tree-preorder-traversal/
  def preorderTraversal(root: TreeNode): List[Int] =
    // root + left + right
    if (root == null) List.empty[Int]
    else root.value +: (preorderTraversal(root.left) ++ preorderTraversal(root.right))

  // https://leetcode.com/problems/binary-tree-inorder-traversal/
  def inorderTraversal(root: TreeNode): List[Int] =
    // left + root + right
    if (root == null) List.empty[Int]
    else {
      (inorderTraversal(root.left) :+ root.value) ::: inorderTraversal(root.right)
    }

  // https://leetcode.com/problems/binary-tree-postorder-traversal/
  def postorderTraversal(root: TreeNode): List[Int] =
    if (root == null) List.empty[Int]
    else {
      (postorderTraversal(root.left) ::: postorderTraversal(root.right)) :+ root.value
    }

  // https://leetcode.com/problems/binary-tree-level-order-traversal/
  def levelOrder(root: TreeNode): List[List[Int]] = {

    @scala.annotation.tailrec
    def go(current: List[TreeNode], acc: List[List[Int]]): List[List[Int]] =
      // every pass-through of go accumulates two items
      // 1. List of all values in a particular level of binary tree
      // 2. List of all child nodes in the same level of binary tree. This becomes input to the next recursive call until input becomes empty (visited all child nodes)
      //
      current match {
        case head :: Nil if head == null => /*println("when input is null.. ") ;*/ acc
        case Nil                         => acc
        case listOfNodes                 =>
          // aggregate (v to list of child nodes - this is binary tree, child node count can vary from 0-2)
          val aggregation: List[(Int, List[TreeNode])] = (for (node <- listOfNodes)
            yield (node.value, (node.left, node.right))).map {
            case (v, (null, null)) => (v, List.empty)
            case (v, (l, null))    => (v, List(l))
            case (v, (null, r))    => (v, List(r))
            case (v, (l, r))       => (v, List(l, r))
          }
          // println(s"${aggregation.mkString(" ")}")
          val (currentValues, nextLevels)              = aggregation
            .foldLeft((List.empty[Int], List.empty[TreeNode])) { case ((valueAcc, nodeAcc), element) =>
              (valueAcc :+ element._1, nodeAcc ::: element._2)
            }

          go(nextLevels, acc.appended(currentValues))
      }

    go(List(root), List.empty)
  }

  // https://leetcode.com/problems/maximum-depth-of-binary-tree/
  def maxDepth(root: TreeNode): Int = {
    def loop(levelNodes: List[TreeNode], acc: Int): Int =
      if (levelNodes.isEmpty) acc
      else {
        val nextLevel =
          levelNodes
            .filter(node => node.left != null || node.right != null)
            .flatMap(node => List(node.left, node.right))
            .filter(_ != null)
        loop(nextLevel, acc + 1)
      }

    if (root == null) 0 else loop(List(root), -1)
  }

  // https://leetcode.com/problems/symmetric-tree/
  def isSymmetric(root: TreeNode): Boolean = {
    def loop(left: TreeNode, right: TreeNode): Boolean =
      (left, right) match {
        case (null, null) => true
        case (null, r)    => false
        case (l, null)    => false
        case (l, r)       => l.value == r.value && loop(l.left, r.right) && loop(l.right, r.left)
      }
    loop(root.left, root.right)
  }

  // https://leetcode.com/problems/path-sum/
  def hasPathSum(root: TreeNode, targetSum: Int): Boolean =
    if (root == null) false
    else
      (root.left, root.right) match {
        case (null, null)  => root.value == targetSum
        case (null, node)  => hasPathSum(node, targetSum - root.value)
        case (node, null)  => hasPathSum(node, targetSum - root.value)
        case (left, right) => hasPathSum(left, targetSum - root.value) || hasPathSum(right, targetSum - root.value)
      }

  // https://leetcode.com/problems/invert-binary-tree/
  def invertTree(root: TreeNode): TreeNode =
    if (root == null) null
    else {
      val currentRight = root.right
      root.right = invertTree(root.left)
      root.left = invertTree(currentRight)
      root
    }

}
