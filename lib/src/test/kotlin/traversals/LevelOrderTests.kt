import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertEquals
import bst.traversals.LevelOrder
import bst.tests

class LevelOrderTraversalTest() {
    @Test
    fun levelOrderIsValid() {
        val RBTree = setUpBalancedRBTree()
        val BSTree = setUpBalancedBSTree()
        val AVLTree = setUpBalancedAVLTree()
        val levelOrderRB = levelOrder<Int, String, RedBlackTreeNode<Int, String>>()
        val levelOrderBST = levelOrder<Int, String, BSTNode<Int, String>>()
        val levelOrderAVL = levelOrder<Int, String, AVLTreeNode<Int, String>>()
        RBTreeResult : List<RedBlackTreeNode<Int, String>> = levelOrderRB(RBTree) {node -> node.key}
        BSTreeResult : List<BSTNode<Int, String>> = levelOrderBST(BSTree) {node -> node.value}
        AVLTreeResult : List<AVLTreeNode<Int, String>> = levelOrderAVL(AVLTree) {node -> node.left}
        assertEquals(RBTreeResult, [13, 8, 17, 1, 11, 15, 25, 6, 22, 27])
        // assertEquals(BSTreeResult, [your hardcoded result here])
        // assertEquals(AVLTreeResult, [your hardcoded result here])
    }
}
