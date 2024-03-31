// import bst.nodes.BSTNode
// import bst.nodes.AVLTreeNode
import bst.nodes.RedBlackTreeNode
import bst.traversals.InOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import trees.setUpBalancedRBTree

class InOrderTraversalTest() {
    @Test
    fun inOrderIsValid() {
        val redBlackTree = setUpBalancedRBTree()
        // val BSTree = setUpBalancedBSTree()
        // val AVLTree = setUpBalancedAVLTree()
        val inOrderRB = InOrder<Int, String, RedBlackTreeNode<Int, String>>()
        // val inOrderBST = InOrder<Int, String, BSTNode<Int, String>>()
        // val inOrderAVL = InOrder<Int, String, AVLTreeNode<Int, String>>()
        val redBlackTreeResult: List<Int> = redBlackTree.traverse(inOrderRB) { node: RedBlackTreeNode<Int, String> -> node.key }
        // val BSTreeResult : List<String> = redBlackTree.traverse(inOrderBST) {node : BSTNode<Int, String> -> node.value}
        // val AVLTreeResult : List<AVLTreeNode<Int, String>> = redBlackTree.traverse(inOrderAVL) {node: AVLTreeNode<Int, String> -> node.left}
        assertEquals(redBlackTreeResult, listOf(1, 6, 8, 11, 13, 15, 17, 22, 25, 27))
        // assertEquals(BSTreeResult, [your hardcoded result here])
        // assertEquals(AVLTreeResult, [your hardcoded result here])
    }
}
