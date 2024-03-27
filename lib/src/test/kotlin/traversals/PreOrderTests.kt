import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertEquals
import bst.traversals.PostOrder
import bst.tests

class PreOrderTraversalTest() {
    @Test
    fun preOrderIsValid() {
        val RBTree = setUpBalancedRBTree()
        val BSTree = setUpBalancedBSTree()
        val AVLTree = setUpBalancedAVLTree()
        val preOrderRB = postOrder<Int, String, RedBlackTreeNode<Int, String>>()
        val preOrderBST = postOrder<Int, String, BSTNode<Int, String>>()
        val preOrderAVL = postOrder<Int, String, AVLTreeNode<Int, String>>()
        RBTreeResult : List<RedBlackTreeNode<Int, String>> = preOrderRB(RBTree) {node -> node.key}
        BSTreeResult : List<BSTNode<Int, String>> = preOrderBST(BSTree) {node -> node.value}
        AVLTreeResult : List<AVLTreeNode<Int, String>> = preOrderAVL(AVLTree) {node -> node.left}
        assertEquals(RBTreeResult, [6, 1, 11, 8, 15, 22, 27, 25, 13])
        // assertEquals(BSTreeResult, [your hardcoded result here])
        // assertEquals(AVLTreeResult, [your hardcoded result here])
    }
}

