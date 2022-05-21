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

    def loop(nodes: List[TreeNode]): List[List[Int]] = {
      nodes match {
        case null => List.empty
        case Nil  => List.empty
        case _    =>
          nodes.filter(_ != null).map(_.value) ::: loop(
            nodes.filter(_ != null).flatMap(node => List(node.left, node.right)).filter(_ != null),
          )
      }
    }

    val r = loop(List(root))
    r
  }
}
