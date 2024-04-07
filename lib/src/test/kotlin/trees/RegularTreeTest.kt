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

    /* Fuzzing is disabled! Remove comments to debug regular tree
    private fun regularTreeCompareTreeNode(
            tree: RegularTree<Int, String>,
            key: Int,
            value: String,
            changeInNumberOfElements: Int,
            isInsert: Boolean,
    ) {
        var changingValue = value
        val listBefore: List<Pair<Int, String>> =
                tree.traversed.inOrder { node: BSTNode<Int, String> -> Pair(node.key, node.value) }
        val frequencyMapBefore = listBefore.groupingBy { it }.eachCount().toMutableMap()
        var oldValue = ""
        if (isInsert && changeInNumberOfElements == 0) {
            oldValue = tree.search(key)!!
            frequencyMapBefore[Pair(key, oldValue)] = 0
            frequencyMapBefore[Pair(key, changingValue)] = 1
        }
        if (changeInNumberOfElements == 0) {
            if (isInsert) {
                tree.insert(key, changingValue)
            } else {
                assertEquals(null, tree.remove(key))
            }
        } else if (changeInNumberOfElements == 1) {
            tree.insert(key, changingValue)
        } else if (changeInNumberOfElements == -1) {
            changingValue = tree.search(key)!!
            tree.remove(key)
        }
        val listAfter: List<Pair<Int, String>> =
                tree.traversed.inOrder { node: BSTNode<Int, String> -> Pair(node.key, node.value) }
        val frequencyMapAfter = listAfter.groupingBy { it }.eachCount().toMutableMap()
        if (changeInNumberOfElements != 0) {
            frequencyMapAfter[Pair(key, changingValue)] = frequencyMapAfter.getOrDefault(Pair(key, changingValue), 0)
            frequencyMapBefore[
                    Pair(
                            key,
                            changingValue,
                    ),
            ] = frequencyMapBefore.getOrDefault(Pair(key, changingValue), 0) + changeInNumberOfElements
        } else if (isInsert) {
            frequencyMapAfter[Pair(key, oldValue)] = frequencyMapAfter.getOrDefault(Pair(key, oldValue), 0)
        }
        assertEquals(frequencyMapBefore, frequencyMapAfter)
        assertEquals(if (isInsert) changingValue else null, tree.search(key))
        assertEquals(true, isBinaryTree(tree))
    }

    @Test
    fun insertDeleteFuzzingTest() {
        val keysRange = -1000..1000
        val percentageOfInserts = 80
        val totalTries = 10000
        for (i in 1..totalTries) {
            val randKey = keysRange.random()
            if ((1..100).random() < percentageOfInserts) {
                if (regularTree.search(randKey) != null) {
                    regularTreeCompareTreeNode(regularTree, randKey, i.toString(), 0, true)
                } else {
                    regularTreeCompareTreeNode(regularTree, randKey, i.toString(), 1, true)
                }
            } else {
                if (regularTree.search(randKey) != null) {
                    regularTreeCompareTreeNode(regularTree, randKey, i.toString(), -1, false)
                } else {
                    regularTreeCompareTreeNode(regularTree, randKey, i.toString(), 0, false)
                }
            }
        }
    }
     */

    @Test
    fun insertRemoveCase1() {
        regularTree.insert(0, "1")
        regularTree.insert(1, "3")
        regularTree.insert(-1, "4")
        regularTree.remove(0)
        assertEquals("4", regularTree.search(-1))
        assertEquals("3", regularTree.search(1))
        assertEquals(null, regularTree.search(0))
        assertEquals(true, isBinaryTree(regularTree))
    }

    @Test
    fun insertRemoveCase2() {
        regularTree.insert(-1, "292")
        regularTree.insert(-2, "302")
        regularTree.insert(1, "304")
        regularTree.insert(0, "307")
        regularTree.insert(-1, "309")
        regularTree.remove(-1)
        assertEquals(true, isBinaryTree(regularTree))
    }

    @Test
    fun insertRemoveCase3() {
        regularTree.insert(1, "5")
        regularTree.insert(0, "6")
        regularTree.insert(1, "7")
        regularTree.remove(1)
        assertEquals(true, isBinaryTree(regularTree))
        assertEquals(null, regularTree.search(1))
        assertEquals("6", regularTree.search(0))
    }

    @Test
    fun `putAll many`() {
        regularTree.putAll(listOf(Pair(23, "M"), Pair(45, "Mm"), Pair(9, "Mmm")))

        assertEquals(setOf(23, 45, 9), regularTree.keys)
    }

    @Test
    fun `putAll none`() {
        regularTree.putAll(listOf())
        assertEquals(setOf<Int>(), regularTree.keys)
    }

    @Test
    fun `get or default`() {
        regularTree.put(23, "non-default")
        regularTree.remove(23)

        assertEquals("default", regularTree.getOrDefault(23, "default"))
    }

    @Test
    fun `get or default ok`() {
        regularTree.put(23, "non-default")

        assertEquals("non-default", regularTree.getOrDefault(23, "default"))
    }

    @Test
    fun `contains key true`() {
        regularTree.put(23, "cat")

        assertEquals(true, regularTree.containsKey(23))
    }

    @Test
    fun `contains key false`() {
        regularTree.put(23, "cat")
        regularTree.remove(23)
        assertEquals(false, regularTree.containsKey(23))
    }

    @Test
    fun `check put`() {
        regularTree.put(23, "M")

        assertEquals("M", regularTree.root?.value)
    }

    @Test
    fun `get values many`() {
        regularTree.putAll(listOf(Pair(23, "M"), Pair(45, "Mm"), Pair(9, "Mmm")))

        assertEquals(linkedSetOf("Mmm", "M", "Mm"), regularTree.values)
    }

    @Test
    fun `get values none`() {
        regularTree.putAll(listOf())

        assertEquals(linkedSetOf<String>(), regularTree.values)
    }

    @Test
    fun `contains value true`() {
        regularTree.put(23, "dog")

        assertEquals(true, regularTree.containsValue("dog"))
    }

    @Test
    fun `contains value false`() {
        regularTree.put(23, "dog")
        regularTree.remove(23)

        assertEquals(false, regularTree.containsValue("dog"))
    }

    @Test
    fun `get keys many`() {
        regularTree.putAll(listOf(Pair(23, "M"), Pair(45, "Mm"), Pair(9, "Mmm")))

        assertEquals(setOf(9, 23, 45), regularTree.keys)
    }

    @Test
    fun `get keys none`() {
        regularTree.putAll(listOf())

        assertEquals(setOf<String>(), regularTree.keys)
    }

    @Test
    fun `get entries many`() {
        regularTree.putAll(listOf(Pair(23, "M"), Pair(45, "Mm"), Pair(9, "Mmm")))

        assertEquals(setOf(Pair(23, "M"), Pair(45, "Mm"), Pair(9, "Mmm")), regularTree.entries)
    }

    @Test
    fun `get entries none`() {
        regularTree.putAll(listOf())

        assertEquals(setOf<Pair<Int, String>>(), regularTree.entries)
    }

    @Test
    fun `operator set`() {
        regularTree[23] = "coca"

        assertEquals("coca", regularTree.search(23))
    }

    @Test
    fun `operator get`() {
        regularTree[23] = "coca"

        assertEquals("coca", regularTree[23])
    }

    @Test
    fun `size non zero`() {
        regularTree[23] = "23"
        regularTree[45] = "45"

        assertEquals(2, regularTree.size)
    }

    @Test
    fun `size zero`() {
        regularTree[23] = "23"
        regularTree[45] = "45"
        regularTree.remove(23)
        regularTree.remove(45)

        assertEquals(0, regularTree.size)
    }

    @Test
    fun `size non negative`() {
        regularTree[23] = "23"
        regularTree[45] = "45"
        regularTree.remove(23)
        regularTree.remove(45)
        regularTree.remove(45)
        regularTree.remove(69)

        assertEquals(0, regularTree.size)
    }

    @Test
    fun `empty true`() {
        regularTree[23] = "23"
        regularTree.remove(23)

        assertEquals(true, regularTree.isEmpty())
        assertEquals(false, regularTree.isNotEmpty())
    }

    @Test
    fun `empty false`() {
        regularTree[23] = "23"

        assertEquals(false, regularTree.isEmpty())
        assertEquals(true, regularTree.isNotEmpty())
    }

    @Test
    fun `clean non empty`() {
        regularTree[23] = "23"
        regularTree[45] = "45"
        regularTree[69] = "nice"

        regularTree.clean()

        assertEquals(null, regularTree.root)
    }

    @Test
    fun `clean empty`() {
        regularTree[23] = "23"
        regularTree.remove(23)

        regularTree.clean()

        assertEquals(null, regularTree.root)
    }

    @Test
    fun `node getKey`() {
        regularTree[23] = "w"

        assertEquals(listOf(23), regularTree.traversed.inOrder { it.getKey() })
    }

    @Test
    fun `node getValue`() {
        regularTree[23] = "w"

        assertEquals(listOf("w"), regularTree.traversed.inOrder { it.getValue() })
    }

    @Test
    fun `node getRight`() {
        regularTree[23] = "w"
        regularTree[45] = "r"

        assertEquals(listOf(regularTree.root?.right, null), regularTree.traversed.inOrder { it.getRight() })
    }

    @Test
    fun `node getLeft`() {
        regularTree[23] = "w"
        regularTree[9] = "r"

        assertEquals(listOf(null, regularTree.root?.left), regularTree.traversed.inOrder { it.getLeft() })
    }
}
