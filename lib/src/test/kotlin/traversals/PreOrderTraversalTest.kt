// import bst.nodes.BSTNode
// import bst.nodes.AVLTreeNode
import bst.nodes.RedBlackTreeNode
import bst.traversals.PreOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import trees.setUpBalancedRBTree

class PreOrderTraversalTest() {
    @Test
    fun preOrderIsValid() {
        val redBlackTree = setUpBalancedRBTree()
        // val BSTree = setUpBalancedBSTree()
        // val AVLTree = setUpBalancedAVLTree()
        val preOrderRB = PreOrder<Int, String, RedBlackTreeNode<Int, String>>()
        // val preOrderBST = PostOrder<Int, String, BSTNode<Int, String>>()
        // val preOrderAVL = PostOrder<Int, String, AVLTreeNode<Int, String>>()
        val redBlackTreeResult: List<Int> = redBlackTree.traverse(preOrderRB) { node: RedBlackTreeNode<Int, String> -> node.key }
        // val BSTreeResult : List<String> = redBlackTree.traverse(preOrderBST) {node : BSTNode<Int, String> -> node.value}
        // val AVLTreeResult : List<AVLTreeNode<Int, String>> = redBlackTree.traverse(preOrderAVL) {node: AVLTreeNode<Int, String> -> node.left}
        assertEquals(redBlackTreeResult, listOf(13, 8, 1, 6, 11, 17, 15, 25, 22, 27))
        // assertEquals(BSTreeResult, [your hardcoded result here])
        // assertEquals(AVLTreeResult, [your hardcoded result here])
    }
}
