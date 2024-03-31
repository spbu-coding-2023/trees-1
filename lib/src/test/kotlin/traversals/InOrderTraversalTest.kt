import bst.traversals.InOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import bst.AVLTree
import bst.RedBlackTree
import bst.RegularTree
import org.junit.jupiter.api.BeforeEach

class InOrderTraversalTest() {
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
        assertEquals(listOf(2, 3, 5, 6, 8), avlTree.traverse(InOrder()) {it.key})
        assertEquals(listOf(2, 3, 5, 6, 8), rbTree.traverse(InOrder()) {it.key})
        assertEquals(listOf(2, 3, 5, 6, 8), regularTree.traverse(InOrder()) {it.key})
    }
}
