import bst.AVLTree
import bst.RedBlackTree
import bst.RegularTree
import bst.traversals.PostOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PostOrderTraversalTest() {
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
        assertEquals(listOf(2, 5, 3, 8, 6), avlTree.traverse(PostOrder()) {it.key})
        assertEquals(listOf(2, 5, 3, 8, 6), rbTree.traverse(PostOrder()) {it.key})
        assertEquals(listOf(5, 3, 2, 8, 6), regularTree.traverse(PostOrder()) {it.key})
    }
}
