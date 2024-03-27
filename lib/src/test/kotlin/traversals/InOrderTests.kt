import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertEquals
import bst.traversals.InOrder
import bst.tests

class InOrderTraversalTest() {
    @Test
    fun inOrderIsValid() {
        val RBTree = setUpBalancedRBTree()
        val BSTree = setUpBalancedBSTree()
        val AVLTree = setUpBalancedAVLTree()
        val inOrderRB = inOrder<Int, String, RedBlackTreeNode<Int, String>>()
        val inOrderBST = inOrder<Int, String, BSTNode<Int, String>>()
        val inOrderAVL = inOrder<Int, String, AVLTreeNode<Int, String>>()
        RBTreeResult : List<RedBlackTreeNode<Int, String>> = inOrderRB(RBTree) {node -> node.key}
        BSTreeResult : List<BSTNode<Int, String>> = inOrderBST(BSTree) {node -> node.value}
        AVLTreeResult : List<AVLTreeNode<Int, String>> = inOrderAVL(AVLTree) {node -> node.left}
        assertEquals(RBTreeResult, [1, 6, 8, 11, 13, 15, 17, 22, 25, 27])
        // assertEquals(BSTreeResult, [your hardcoded result here])
        // assertEquals(AVLTreeResult, [your hardcoded result here])
    }
}
