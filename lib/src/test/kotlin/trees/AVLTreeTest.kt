package trees

import bst.AVLTree
import bst.traversals.InOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
    fun `remove multiple nodes`() {
        tree.insert(23, "Germany")
        tree.insert(35, "Niger")
        tree.insert(12, "Moldova")

        assertNull(tree.remove(69))
        assertEquals("Niger", tree.remove(35))
        assertNull(tree.remove(55))
        assertEquals("Germany", tree.remove(23))
        assertNull(tree.remove(7))
        assertEquals("Moldova", tree.remove(12))
    }

    @Test
    fun `insert 5right`() {
        tree.insert(5, "Grenada")
        tree.insert(6, "Serbia")
        tree.insert(7, "Tanzania")
        tree.insert(8, "Solomon Islands")
        tree.insert(9, "Austria")

        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.right)
        assertNotNull(tree.root?.right?.right)
        assertNotNull(tree.root?.right?.left)

        assertEquals("Grenada", tree.root?.left?.value)
        assertEquals("Serbia", tree.root?.value)
        assertEquals("Solomon Islands", tree.root?.right?.value)
        assertEquals("Tanzania", tree.root?.right?.left?.value)
        assertEquals("Austria", tree.root?.right?.right?.value)
    }

    @Test
    fun `remove 1 of 5 nodes`() {
        tree.insert(5, "Grenada")
        tree.insert(6, "Serbia")
        tree.insert(7, "Tanzania")
        tree.insert(8, "Solomon Islands")
        tree.insert(9, "Austria")

        // tree after insert
        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.right)
        assertNotNull(tree.root?.right?.right)
        assertNotNull(tree.root?.right?.left)

        assertEquals("Grenada", tree.remove(5))

        // tree after remove
        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.left?.right)
        assertNotNull(tree.root?.right)
        // nulls
        assertNull(tree.root?.right?.right)
        assertNull(tree.root?.right?.left)
        assertNull(tree.root?.left?.left)
        assertNull(tree.root?.left?.right?.right)
        assertNull(tree.root?.left?.right?.left)
    }

    @Test
    fun `remove 2 of 5 nodes`() {
        tree.insert(5, "Grenada")
        tree.insert(6, "Serbia")
        tree.insert(7, "Tanzania")
        tree.insert(8, "Solomon Islands")
        tree.insert(9, "Austria")

        // tree after insert
        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.right)
        assertNotNull(tree.root?.right?.right)
        assertNotNull(tree.root?.right?.left)

        assertEquals("Grenada", tree.remove(5))

        // tree after remove
        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.left?.right)
        assertNotNull(tree.root?.right)
        // nulls
        assertNull(tree.root?.right?.right)
        assertNull(tree.root?.right?.left)
        assertNull(tree.root?.left?.left)
        assertNull(tree.root?.left?.right?.right)
        assertNull(tree.root?.left?.right?.left)

        assertEquals("Austria", tree.remove(9))

        // tree after remove
        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.right)
        // nulls
        assertNull(tree.root?.left?.left)
        assertNull(tree.root?.right?.left)
        assertNull(tree.root?.left?.right)
        assertNull(tree.root?.right?.right)
    }

    @Test
    fun `removing nodes return value`() {
        tree.insert(5, "Grenada")
        tree.insert(6, "Serbia")
        tree.insert(7, "Tanzania")
        tree.insert(8, "Solomon Islands")
        tree.insert(9, "Austria")

        assertEquals("Solomon Islands", tree.remove(8))
        assertEquals("Grenada", tree.remove(5))
        assertEquals("Tanzania", tree.remove(7))
        assertEquals("Serbia", tree.remove(6))
        assertEquals("Austria", tree.remove(9))
    }

    @Test
    fun insert() {
    }

    @Test
    fun remove() {
    }
}
