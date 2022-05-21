package leetcode

object BinaryTreeTraversal extends App {

  val three   = new TreeNode(3)
  val nine    = new TreeNode(9)
  val twenty  = new TreeNode(20)
  val fifteen = new TreeNode(15)
  val seven   = new TreeNode(7)
  three.left = nine
  three.right = twenty
  twenty.left = fifteen
  twenty.right = seven

  levelOrder(null)
  //levelOrder(three)

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int                  = _value
    var left: TreeNode              = _left
    var right: TreeNode             = _right
    override def toString(): String = s"$value"
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

  //https://leetcode.com/problems/binary-tree-level-order-traversal/
  def levelOrder(root: TreeNode): List[List[Int]] = {

    @scala.annotation.tailrec
    def go(current: List[TreeNode], acc: List[List[Int]]): List[List[Int]] = {
      //every pass-through of go accumulates two items
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
          //println(s"${aggregation.mkString(" ")}")
          val (currentValues, nextLevels)              = aggregation.foldLeft((List.empty[Int], List.empty[TreeNode])) { case (acc, element) =>
            (acc._1 :+ element._1, acc._2 ::: element._2)
          }

          go(nextLevels, acc.appended(currentValues))
      }
    }

    go(List(root), List.empty)
  }
}
