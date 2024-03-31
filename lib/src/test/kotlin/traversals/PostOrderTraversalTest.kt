// import bst.nodes.BSTNode
// import bst.nodes.AVLTreeNode
import bst.nodes.RedBlackTreeNode
import bst.traversals.PostOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import trees.setUpBalancedRBTree

class PostOrderTraversalTest() {
    @Test
    fun postOrderIsValid() {
        val redBlackTree = setUpBalancedRBTree()
        // val BSTree = setUpBalancedBSTree()
        // val AVLTree = setUpBalancedAVLTree()
        val postOrderRB = PostOrder<Int, String, RedBlackTreeNode<Int, String>>()
        // val postOrderBST = PostOrder<Int, String, BSTNode<Int, String>>()
        // val postOrderAVL = PostOrder<Int, String, AVLTreeNode<Int, String>>()
        val redBlackTreeResult: List<Int> = redBlackTree.traverse(postOrderRB) { node: RedBlackTreeNode<Int, String> -> node.key }
        // val BSTreeResult : List<String> = redBlackTree.traverse(postOrderBST) {node : BSTNode<Int, String> -> node.value}
        // val AVLTreeResult : List<AVLTreeNode<Int, String>> = redBlackTree.traverse(postOrderAVL) {node: AVLTreeNode<Int, String> -> node.left}
        assertEquals(redBlackTreeResult, listOf(6, 1, 11, 8, 15, 22, 27, 25, 17, 13))
        // assertEquals(BSTreeResult, [your hardcoded result here])
        // assertEquals(AVLTreeResult, [your hardcoded result here])
    }
}
