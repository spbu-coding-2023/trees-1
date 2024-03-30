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
    }

    @Test
    fun insert() {
    }

    @Test
    fun remove() {
    }
}