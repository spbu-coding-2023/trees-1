import bst.AVLTree
import bst.RedBlackTree
import bst.RegularTree
import bst.traversals.PreOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PreOrderTraversalTest() {
    lateinit var avlTree: AVLTree<Int, String>
    lateinit var rbTree: RedBlackTree<Int, String>
    lateinit var regularTree: RegularTree<Int, String>
    val insertedNodes = listOf(6, 8, 2, 3, 5)

    @BeforeEach
    fun setup() {
        avlTree = AVLTree()
        rbTree = RedBlackTree()
        regularTree = RegularTree()
        setTreesData(insertedNodes)
    }

    fun setTreesData(insertedNodes: List<Int>) {
        for (tree in listOf(avlTree, rbTree, regularTree)) {
            for (node in insertedNodes) {
                tree.insert(node, "A")
            }
        }
    }

    @Test
    fun traversed() {
        assertEquals(listOf(6, 3, 2, 5, 8), avlTree.traverse(PreOrder()) { it.key })
        assertEquals(listOf(6, 3, 2, 5, 8), rbTree.traverse(PreOrder()) { it.key })
        assertEquals(listOf(6, 2, 3, 5, 8), regularTree.traverse(PreOrder()) { it.key })
    }
}
