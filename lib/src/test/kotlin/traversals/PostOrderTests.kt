import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertEquals
import bst.traversals.PostOrder
import bst.tests

class PostOrderTraversalTest() {
    @Test
    fun postOrderIsValid() {
        val RBTree = setUpBalancedRBTree()
        val BSTree = setUpBalancedBSTree()
        val AVLTree = setUpBalancedAVLTree()
        val postOrderRB = postOrder<Int, String, RedBlackTreeNode<Int, String>>()
        val postOrderBST = postOrder<Int, String, BSTNode<Int, String>>()
        val postOrderAVL = postOrder<Int, String, AVLTreeNode<Int, String>>()
        RBTreeResult : List<RedBlackTreeNode<Int, String>> = postOrderRB.traverse(RBTree.root) {node -> node.key}
        BSTreeResult : List<BSTNode<Int, String>> = postOrderBST.traverse(BSTree.root) {node -> node.value}
        AVLTreeResult : List<AVLTreeNode<Int, String>> = postOrderAVL.traverse(AVLTree.root) {node -> node.left}
        assertEquals(RBTreeResult, [6, 1, 11, 8, 15, 22, 27, 25, 17, 13])
        // assertEquals(BSTreeResult, [your hardcoded result here])
        // assertEquals(AVLTreeResult, [your hardcoded result here])
    }
}
