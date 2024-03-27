import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertEquals
import bst.traversals.PreOrder
import bst.tests

class PreOrderTraversalTest() {
    @Test
    fun preOrderIsValid() {
        val RBTree = setUpBalancedRBTree()
        val BSTree = setUpBalancedBSTree()
        val AVLTree = setUpBalancedAVLTree()
        val preOrderRB = preOrder<Int, String, RedBlackTreeNode<Int, String>>()
        val preOrderBST = preOrder<Int, String, BSTNode<Int, String>>()
        val preOrderAVL = preOrder<Int, String, AVLTreeNode<Int, String>>()
        RBTreeResult : List<RedBlackTreeNode<Int, String>> = preOrderRB.traverse(RBTree.root) {node -> node.key}
        BSTreeResult : List<BSTNode<Int, String>> = preOrderBST.traverse(BSTree.root) {node -> node.value}
        AVLTreeResult : List<AVLTreeNode<Int, String>> = preOrderAVL.traverse(AVLTree.root) {node -> node.left}
        assertEquals(RBTreeResult, [13, 8, 1, 6, 11, 17, 15, 25, 22, 27])
        // assertEquals(BSTreeResult, [your hardcoded result here])
        // assertEquals(AVLTreeResult, [your hardcoded result here])
    }
}
