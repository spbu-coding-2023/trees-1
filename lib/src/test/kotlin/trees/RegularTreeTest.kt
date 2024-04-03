package trees

import bst.RegularTree
import bst.nodes.BSTNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RegularTreeTest : AbstractBSTTest<RegularTree<Int, String>, BSTNode<Int, String>>() {
    lateinit var regularTree: RegularTree<Int, String>

    @BeforeEach
    fun setup() {
        regularTree = RegularTree()
    }

    @Test
    fun doubleInsertSameNodeRemoveOnce() {
        regularTree.insert(4, "Four")
        regularTree.insert(4, "Four")
        assertEquals("Four", regularTree.remove(4))
        assertNull(regularTree.root)
    }

    @Test
    fun searchOneNode() {
        regularTree.insert(5, "Five")

        assertEquals("Five", regularTree.search(5))
        assertNotNull(regularTree.search(5))
    }

    @Test
    fun searchTwoNodes() {
        regularTree.insert(4, "Four")
        regularTree.insert(5, "Five")
        assertEquals("Four", regularTree.search(4))
        assertEquals("Five", regularTree.search(5))
    }

    @Test
    fun searchThreeNodes() {
        regularTree.insert(1, "One")
        regularTree.insert(2, "Two")
        regularTree.insert(3, "Three")

        assertEquals("One", regularTree.search(1))
        assertEquals("Two", regularTree.search(2))
        assertEquals("Three", regularTree.search(3))
    }

    @Test
    fun removeOneNode() {
        regularTree.insert(1, "One")
        assertEquals("One", regularTree.remove(1))
        assertNull(regularTree.root)
    }

    @Test
    fun removeMultipleNodes() {
        regularTree.insert(1, "One")
        regularTree.insert(2, "Two")
        regularTree.insert(3, "Three")

        assertNull(regularTree.remove(4))
        assertEquals("Two", regularTree.remove(2))
        assertNull(regularTree.remove(5))
        assertEquals("One", regularTree.remove(1))
        assertNull(regularTree.remove(6))
        assertEquals("Three", regularTree.remove(3))
    }

    @Test
    fun searchRemovedNode() {
        regularTree.insert(1, "One")
        regularTree.insert(2, "Two")
        regularTree.insert(3, "Three")

        assertEquals("Two", regularTree.search(2))
        regularTree.insert(2, "SecondNubmerTwo")
        assertEquals("SecondNubmerTwo", regularTree.search(2))

        assertEquals("One", regularTree.search(1))
        assertEquals("Three", regularTree.search(3))
        assertEquals("SecondNubmerTwo", regularTree.search(2))
    }
}
