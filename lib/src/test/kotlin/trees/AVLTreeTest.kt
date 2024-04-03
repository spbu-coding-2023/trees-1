package trees

import bst.AVLTree
import bst.nodes.AVLTreeNode
import bst.traversals.LevelOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AVLTreeTest : AbstractBSTTest<AVLTree<Int, String>, AVLTreeNode<Int, String>>() {
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
    fun `remove 1 node`() {
        tree.insert(23, "Germany")
        assertEquals("Germany", tree.remove(23))
        assertNull(tree.root)
    }

    @Test
    fun `insert rebalanced small tree`() {
        tree.insert(20, "Uganda")
        tree.insert(4, "Haiti")

        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)

        tree.insert(15, "New Zealand")
        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.right)
        assertNull(tree.root?.left?.right)
        assertNull(tree.root?.left?.left)
        assertNull(tree.root?.right?.right)
        assertNull(tree.root?.right?.left)
        assertEquals("New Zealand", tree.root?.value)
        assertEquals("Haiti", tree.root?.left?.value)
        assertEquals("Uganda", tree.root?.right?.value)
    }

    @Test
    fun `insert rebalanced medium tree`() {
        tree.insert(20, "Uganda")
        tree.insert(4, "Haiti")
        tree.insert(26, "Rwanda")
        tree.insert(3, "Botswana")
        tree.insert(9, "Grenada")

        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.left?.right)
        assertNotNull(tree.root?.left?.left)
        assertNotNull(tree.root?.right)

        tree.insert(15, "New Zealand")
        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.right)
        assertNotNull(tree.root?.left?.left)
        assertNull(tree.root?.left?.left?.left)
        assertNull(tree.root?.left?.left?.right)
        assertNull(tree.root?.left?.right)
        assertNotNull(tree.root?.right?.right)
        assertNull(tree.root?.right?.right?.left)
        assertNull(tree.root?.right?.right?.right)
        assertNotNull(tree.root?.right?.left)
        assertNull(tree.root?.right?.left?.right)
        assertNull(tree.root?.right?.left?.left)
        assertEquals("Grenada", tree.root?.value)
        assertEquals("Haiti", tree.root?.left?.value)
        assertEquals("Uganda", tree.root?.right?.value)
        assertEquals("Botswana", tree.root?.left?.left?.value)
        assertEquals("Rwanda", tree.root?.right?.right?.value)
        assertEquals("New Zealand", tree.root?.right?.left?.value)
    }

    @Test
    fun `remove balanced small tree`() {
        tree.insert(2, "Rwanda")
        tree.insert(1, "Botswana")
        tree.insert(4, "Uganda")
        tree.insert(3, "Haiti")
        tree.insert(5, "Grenada")

        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.right)
        assertNotNull(tree.root?.right?.left)
        assertNotNull(tree.root?.right?.right)

        assertEquals("Botswana", tree.remove(1))

        assertNotNull(tree.root)
        assertNotNull(tree.root?.left)
        assertNotNull(tree.root?.right)
        assertNotNull(tree.root?.left?.right)
        assertNull(tree.root?.right?.right)
        assertNull(tree.root?.right?.left)
        assertNull(tree.root?.left?.left)
        assertNull(tree.root?.left?.right?.right)
        assertNull(tree.root?.left?.right?.left)

        assertEquals("Rwanda", tree.root?.left?.value)
        assertEquals("Uganda", tree.root?.value)
        assertEquals("Grenada", tree.root?.right?.value)
        assertEquals("Haiti", tree.root?.left?.right?.value)
    }

    @Test
    fun `remove balanced medium tree`() {
        tree.insert(6, "6")
        tree.insert(2, "2")
        tree.insert(9, "9")
        tree.insert(1, "1")
        tree.insert(4, "4")
        tree.insert(8, "8")
        tree.insert(11, "11")
        tree.insert(3, "3")
        tree.insert(5, "5")
        tree.insert(7, "7")
        tree.insert(10, "10")
        tree.insert(12, "12")
        tree.insert(13, "13")

        assertEquals("1", tree.remove(1))

        assertEquals("6", tree.root?.value)
        assertEquals("4", tree.root?.left?.value)
        assertEquals("9", tree.root?.right?.value)
        assertEquals("2", tree.root?.left?.left?.value)
        assertEquals("5", tree.root?.left?.right?.value)
        assertEquals("8", tree.root?.right?.left?.value)
        assertEquals("11", tree.root?.right?.right?.value)
        assertEquals("3", tree.root?.left?.left?.right?.value)
        assertEquals("7", tree.root?.right?.left?.left?.value)
        assertEquals("10", tree.root?.right?.right?.left?.value)
        assertEquals("12", tree.root?.right?.right?.right?.value)
        assertEquals("13", tree.root?.right?.right?.right?.right?.value)
    }

    @Test
    fun `insert for node in root`() {
        tree.insert(23, "aba")

        assertEquals("aba", tree.search(23))

        tree.insert(23, "dcdc")

        assertEquals("dcdc", tree.search(23))
    }

    @Test
    fun `insert for node in root child`() {
        tree.insert(23, "aba")

        assertEquals("aba", tree.search(23))

        tree.insert(35, "dcdc")

        assertEquals("dcdc", tree.search(35))

        tree.insert(35, "fgfg")

        assertEquals("fgfg", tree.search(35))
        assertEquals(tree.root?.right?.parent, tree.root)
    }

    private fun calculateHeight(node: AVLTreeNode<Int, String>?): Int {
        if (node == null) return 0
        val heightLeft = calculateHeight(node.left)
        val heightRight = calculateHeight(node.right)
        val heightMax = if (heightRight > heightLeft) heightRight else heightLeft
        return heightMax + 1
    }

    private fun isBalanced(tree: AVLTree<Int, String>): Boolean {
        for (node in tree.traverse(LevelOrder()) { it }) {
            val heightLeft = calculateHeight(node.left)
            val heightRight = calculateHeight(node.right)
            val isBfInRange = (heightRight - heightLeft) in -1..1
            if (!isBfInRange) return false
        }
        return true
    }

    private fun fuzz(
        tree: AVLTree<Int, String>,
        keysRange: IntRange,
        percentageOfInserts: Int,
        totalTries: Int,
    ) {
        repeat(totalTries) {
            val randKey = keysRange.random()
            if ((1..100).random() < percentageOfInserts) {
                tree.insert(randKey, "a")
                assertEquals(true, isBalanced(tree))
                assertEquals(true, isBinaryTree(tree))
            } else {
                tree.remove(randKey)
                assertEquals(true, isBalanced(tree))
                assertEquals(true, isBinaryTree(tree))
            }
        }
    }

    @Test
    fun `fuzzing fewKeys mostInsert fewTimes`() {
        fuzz(tree, 1..10, 70, 1000)
    }

    @Test
    fun `fuzzing aLotKeys mostInsert aLotTimes`() {
        fuzz(tree, 1..10000, 70, 10000)
    }

    @Test
    fun `fuzzing fewKeys mostRemove fewTimes`() {
        fuzz(tree, 1..10, 20, 1000)
    }

    @Test
    fun `fuzzing aLotKeys mostRemove aLotTimes`() {
        fuzz(tree, 1..10000, 20, 10000)
    }

    @Test
    fun `fuzzing fewKeys mostInsert aLotTimes`() {
        fuzz(tree, 1..10, 70, 10000)
    }

    @Test
    fun `single rotate right`() {
        tree.insert(9, "9")
        tree.insert(4, "4")
        tree.insert(10, "10")
        tree.insert(3, "3")
        tree.insert(6, "6")

        assertEquals(true, isBalanced(tree))

        tree.insert(2, "2")

        assertEquals(true, isBalanced(tree))
    }

    @Test
    fun `single rotate right with remove`() {
        val path =
            listOf(
                listOf(1, 6),
                listOf(1, 8),
                listOf(1, 10),
                listOf(1, 9),
                listOf(1, 3),
                listOf(0, 8),
                listOf(1, 4),
                listOf(1, 2),
            )

        for (p in path) {
            if (p[0] == 1) {
                tree.insert(p[1], "a")
            } else {
                tree.remove(p[1])
            }

            if (!isBalanced(tree)) {
                break
            }
        }
        assertEquals(true, isBalanced(tree))
        assertEquals(true, isBinaryTree(tree))
    }

    @Test
    fun `insert twice remove once`() {
        tree.insert(23, "a")
        tree.insert(23, "a")
        assertEquals("a", tree.remove(23))
        assertNull(tree.root)
        assertEquals(true, isBalanced(tree))
        assertEquals(true, isBinaryTree(tree))
    }

    @Test
    fun `insert twice remove once 3 nodes`() {
        tree.insert(23, "a")
        tree.insert(24, "b")
        tree.insert(22, "c")
        tree.insert(20, "d")
        tree.insert(20, "p")
        assertEquals("p", tree.remove(20))
        assertNotNull(tree.root)
        assertEquals(true, isBalanced(tree))
        assertEquals(true, isBinaryTree(tree))
    }

    @Test
    fun `search if 1 node`() {
        tree.insert(23, "a")

        assertEquals("a", tree.search(23))
        assertEquals("a", tree.search(23))
    }

    @Test
    fun `search if 3 nodes`() {
        tree.insert(23, "a")
        tree.insert(34, "b")
        tree.insert(45, "c")

        assertEquals("a", tree.search(23))
        assertEquals("a", tree.search(23))
        assertEquals("b", tree.search(34))
        assertEquals("b", tree.search(34))
        assertEquals("c", tree.search(45))
        assertEquals("c", tree.search(45))
        assertEquals("a", tree.search(23))
        assertEquals("a", tree.search(23))
        assertEquals("b", tree.search(34))
        assertEquals("b", tree.search(34))
        assertEquals("c", tree.search(45))
        assertEquals("c", tree.search(45))
    }

    @Test
    fun `search replaced node`() {
        tree.insert(23, "a")
        tree.insert(34, "b")
        tree.insert(45, "c")

        assertEquals("b", tree.search(34))
        tree.insert(34, "d")
        assertEquals("d", tree.search(34))

        assertEquals("d", tree.search(34))
        assertEquals("a", tree.search(23))
        assertEquals("c", tree.search(45))
        assertEquals("d", tree.search(34))
        assertEquals("a", tree.search(23))
        assertEquals("c", tree.search(45))
    }
}
