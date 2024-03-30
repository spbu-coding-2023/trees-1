// import bst.nodes.BSTNode
// import bst.nodes.AVLTreeNode
import bst.nodes.RedBlackTreeNode
import bst.tests.setUpBalancedRBTree
import bst.traversals.LevelOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LevelOrderTraversalTest() {
    @Test
    fun levelOrderIsValid() {
        val redBlackTree = setUpBalancedRBTree()
        // val BSTree = setUpBalancedBSTree()
        // val AVLTree = setUpBalancedAVLTree()
        val levelOrderRB = LevelOrder<Int, String, RedBlackTreeNode<Int, String>>()
        // val levelOrderBST = InOrder<Int, String, BSTNode<Int, String>>()
        // val levelOrderAVL = InOrder<Int, String, AVLTreeNode<Int, String>>()
        val redBlackTreeResult: List<Int> = redBlackTree.traverse(levelOrderRB) { node: RedBlackTreeNode<Int, String> -> node.key }
        // val BSTreeResult : List<String> = redBlackTree.traverse(levelOrderBST) {node : BSTNode<Int, String> -> node.value}
        // val AVLTreeResult : List<AVLTreeNode<Int, String>> = redBlackTree.traverse(levelOrderAVL) {node: AVLTreeNode<Int, String> -> node.left}
        assertEquals(redBlackTreeResult, listOf(13, 8, 17, 1, 11, 15, 25, 6, 22, 27))
        // assertEquals(BSTreeResult, [your hardcoded result here])
        // assertEquals(AVLTreeResult, [your hardcoded result here])
    }
}
