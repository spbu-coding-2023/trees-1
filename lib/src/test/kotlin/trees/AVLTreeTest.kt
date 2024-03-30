package trees

import bst.AVLTree
import bst.traversals.InOrder
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class AVLTreeTest {

    lateinit var tree: AVLTree<Int, String>

    @BeforeEach
    fun setup() {
        tree = AVLTree()
    }

    @Test
    fun `search single node`() {
        tree.insert(23, "Germany")

        assertEquals("Germany", tree.search(23))
        assertNull(tree.search(69))
    }

    @Test
    fun `search two nodes`() {
        tree.insert(23, "Germany")
        tree.insert(35, "Niger")

        assertEquals("Germany", tree.search(23))
        assertEquals("Niger", tree.search(35))
    }

    @Test
    fun `search three nodes`() {
        tree.insert(23, "Germany")
        tree.insert(35, "Niger")
        tree.insert(12, "Moldova")

        assertEquals("Germany", tree.search(23))
        assertEquals("Moldova", tree.search(12))
        assertEquals("Niger", tree.search(35))
    }

    @Test
    fun insert() {
    }

    @Test
    fun remove() {
    }
}