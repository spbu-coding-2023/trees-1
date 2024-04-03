package trees

import bst.RedBlackTree
import bst.nodes.RedBlackTreeNode
import bst.traversals.InOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RedBlackTreeTest : AbstractBSTTest<RedBlackTree<Int, String>, RedBlackTreeNode<Int, String>>() {
    private fun setUpBalancedRBTree(): RedBlackTree<Int, String> {
        val redBlackTree = RedBlackTree<Int, String>()
        redBlackTree.root = RedBlackTreeNode(13, "1", null)
        redBlackTree.root!!.setBlack()

        redBlackTree.root!!.left = RedBlackTreeNode(8, "2", redBlackTree.root)
        redBlackTree.root!!.left!!.setRed()

        redBlackTree.root!!.right = RedBlackTreeNode(17, "3", redBlackTree.root)
        redBlackTree.root!!.right!!.setRed()

        redBlackTree.root!!.left!!.left = RedBlackTreeNode(1, "4", redBlackTree.root!!.left)
        redBlackTree.root!!.left!!.left!!.setBlack()

        redBlackTree.root!!.left!!.right = RedBlackTreeNode(11, "5", redBlackTree.root!!.left)
        redBlackTree.root!!.left!!.right!!.setBlack()

        redBlackTree.root!!.right!!.left = RedBlackTreeNode(15, "6", redBlackTree.root!!.right)
        redBlackTree.root!!.right!!.left!!.setBlack()

        redBlackTree.root!!.right!!.right = RedBlackTreeNode(25, "7", redBlackTree.root!!.right)
        redBlackTree.root!!.right!!.right!!.setBlack()

        redBlackTree.root!!.left!!.left!!.right = RedBlackTreeNode(6, "8", redBlackTree.root!!.left!!.left)
        redBlackTree.root!!.left!!.left!!.right!!.setRed()

        redBlackTree.root!!.right!!.right!!.left = RedBlackTreeNode(22, "9", redBlackTree.root!!.right!!.right)
        redBlackTree.root!!.right!!.right!!.left!!.setRed()

        redBlackTree.root!!.right!!.right!!.right = RedBlackTreeNode(27, "10", redBlackTree.root!!.right!!.right)
        redBlackTree.root!!.right!!.right!!.right!!.setRed()
        return redBlackTree
    }

    private fun setUpAnotherBalancedRBTree(): RedBlackTree<Int, String> {
        val redBlackTree = RedBlackTree<Int, String>()
        redBlackTree.root = RedBlackTreeNode(10, "1", null)
        redBlackTree.root!!.setBlack()

        redBlackTree.root!!.left = RedBlackTreeNode(7, "2", redBlackTree.root)
        redBlackTree.root!!.left!!.setBlack()

        redBlackTree.root!!.right = RedBlackTreeNode(40, "3", redBlackTree.root)
        redBlackTree.root!!.right!!.setBlack()

        redBlackTree.root!!.left!!.left = RedBlackTreeNode(3, "4", redBlackTree.root!!.left)
        redBlackTree.root!!.left!!.left!!.setBlack()

        redBlackTree.root!!.left!!.right = RedBlackTreeNode(8, "5", redBlackTree.root!!.left)
        redBlackTree.root!!.left!!.right!!.setBlack()

        redBlackTree.root!!.right!!.left = RedBlackTreeNode(30, "6", redBlackTree.root!!.right)
        redBlackTree.root!!.right!!.left!!.setRed()

        redBlackTree.root!!.right!!.right = RedBlackTreeNode(45, "7", redBlackTree.root!!.right)
        redBlackTree.root!!.right!!.right!!.setBlack()

        redBlackTree.root!!.left!!.left!!.left = RedBlackTreeNode(1, "8", redBlackTree.root!!.left!!.left)
        redBlackTree.root!!.left!!.left!!.left!!.setRed()

        redBlackTree.root!!.left!!.left!!.right = RedBlackTreeNode(5, "9", redBlackTree.root!!.left!!.left)
        redBlackTree.root!!.left!!.left!!.right!!.setRed()

        redBlackTree.root!!.right!!.left!!.left = RedBlackTreeNode(20, "10", redBlackTree.root!!.right!!.left)
        redBlackTree.root!!.right!!.left!!.left!!.setBlack()

        redBlackTree.root!!.right!!.left!!.right = RedBlackTreeNode(35, "11", redBlackTree.root!!.right!!.left)
        redBlackTree.root!!.right!!.left!!.right!!.setBlack()

        redBlackTree.root!!.right!!.right!!.right = RedBlackTreeNode(60, "12", redBlackTree.root!!.right!!.right)
        redBlackTree.root!!.right!!.right!!.right!!.setRed()

        redBlackTree.root!!.right!!.left!!.left!!.right = RedBlackTreeNode(25, "13", redBlackTree.root!!.right!!.left!!.left)
        redBlackTree.root!!.right!!.left!!.left!!.right!!.setRed()
        return redBlackTree
    }

    private fun setUpUnbalancedRBTree(): RedBlackTree<Int, String> {
        val redBlackTree = setUpBalancedRBTree()
        redBlackTree.root!!.right!!.left!!.setRed()
        redBlackTree.root!!.left!!.right!!.key = 20
        return redBlackTree
    }

    private fun setUpAnotherUnbalancedRBTree(): RedBlackTree<Int, String> {
        val redBlackTree = setUpAnotherBalancedRBTree()
        redBlackTree.root!!.left!!.right!!.setRed()
        redBlackTree.root!!.right!!.left!!.right!!.key = 19
        return redBlackTree
    }

    private fun numberOfBlackNodesDownwardsAndRBConditionChecker(node: RedBlackTreeNode<Int, String>?): Int {
        if (node == null) {
            return 1
        }
        if (node.parent == null && node.isRed()) {
            return -1
        }
        val leftBlackHeight = numberOfBlackNodesDownwardsAndRBConditionChecker(node.left)
        val rightBlackHeight = numberOfBlackNodesDownwardsAndRBConditionChecker(node.right)
        if (leftBlackHeight == -1 || rightBlackHeight == -1 || leftBlackHeight != rightBlackHeight) {
            return -1
        }
        if (node.isRed() && node.parent?.isRed() == true) {
            return -1
        }
        return if (node.isBlack()) leftBlackHeight + 1 else leftBlackHeight
    }

    private fun isBalancedInColourRBTree(RBTree: RedBlackTree<Int, String>): Boolean {
        return numberOfBlackNodesDownwardsAndRBConditionChecker(RBTree.root) != -1 // -1 means conditions are not met
    }

    @Test
    fun isBalancedInColourRBTreeTest() {
        assertEquals(true, isBalancedInColourRBTree(setUpBalancedRBTree()))
        assertEquals(true, isBalancedInColourRBTree(setUpAnotherBalancedRBTree()))
        assertEquals(false, isBalancedInColourRBTree(setUpUnbalancedRBTree()))
        assertEquals(false, isBalancedInColourRBTree(setUpAnotherUnbalancedRBTree()))
    }

    @Test
    fun isBalancedInKeysRBTreeTest() {
        assertEquals(true, isBinaryTree(setUpBalancedRBTree()))
        assertEquals(true, isBinaryTree(setUpAnotherBalancedRBTree()))
        assertEquals(false, isBinaryTree(setUpUnbalancedRBTree()))
        assertEquals(false, isBinaryTree(setUpAnotherUnbalancedRBTree()))
    }

    @Test
    fun searchTest() {
        val redBlackTree = setUpBalancedRBTree()
        val anotherRBTree = setUpAnotherBalancedRBTree()
        assertEquals("6", redBlackTree.search(15))
        assertEquals("11", anotherRBTree.search(35))
        assertEquals(redBlackTree.search(7), null)
        assertEquals(anotherRBTree.search(21), null)
    }

    private fun redBlackTreeCompareAfterOperation(
        tree: RedBlackTree<Int, String>,
        key: Int,
        value: String,
        changeInNumberOfElements: Int,
        isInsert: Boolean,
    ) {
        var changingValue = value
        val inOrderInstance = InOrder<Int, String, RedBlackTreeNode<Int, String>>()
        val listBefore: List<Pair<Int, String>> =
            tree.traverse(
                inOrderInstance,
            ) { node: RedBlackTreeNode<Int, String> -> Pair(node.key, node.value) }
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
            tree.traverse(
                inOrderInstance,
            ) { node: RedBlackTreeNode<Int, String> -> Pair(node.key, node.value) }
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
        assertEquals(true, isBalancedInColourRBTree(tree))
    }

    @Test
    fun insertTest() {
        val redBlackTree = setUpBalancedRBTree()
        val anotherRBTree = setUpAnotherBalancedRBTree()
        redBlackTreeCompareAfterOperation(redBlackTree, 16, "11", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 16, "12", 0, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 150, "13", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 150, "14", 0, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 12, "15", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 12, "16", 0, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 10, "17", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 10, "18", 0, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 9, "19", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 9, "20", 0, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 17, "21", 0, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 15, "22", 0, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 18, "23", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 19, "24", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 20, "25", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 21, "26", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 22, "27", 0, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 23, "28", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 24, "29", 1, true)
        redBlackTreeCompareAfterOperation(redBlackTree, 15000, "30", 1, true)
        redBlackTreeCompareAfterOperation(anotherRBTree, 36, "16", 1, true)
        redBlackTreeCompareAfterOperation(anotherRBTree, 36, "17", 0, true)
        redBlackTreeCompareAfterOperation(anotherRBTree, 400, "18", 1, true)
        redBlackTreeCompareAfterOperation(anotherRBTree, 400, "19", 0, true)
        redBlackTreeCompareAfterOperation(anotherRBTree, 41, "20", 1, true)
        redBlackTreeCompareAfterOperation(anotherRBTree, 41, "21", 0, true)
        redBlackTreeCompareAfterOperation(anotherRBTree, 40, "22", 0, true)
        redBlackTreeCompareAfterOperation(anotherRBTree, 25, "23", 0, true)
    }

    @Test
    fun deleteTest() {
        val redBlackTree = setUpBalancedRBTree()
        val anotherRBTree = setUpAnotherBalancedRBTree()
        redBlackTreeCompareAfterOperation(redBlackTree, 13, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 13, "", 0, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 27, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 27, "", 0, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 15, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 15, "", 0, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 2, "", 0, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 3, "", 0, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 8, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 25, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 1, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 6, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 15000, "", 0, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 17, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 11, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 22, "", -1, false)
        redBlackTreeCompareAfterOperation(redBlackTree, 0, "", 0, false)
        redBlackTreeCompareAfterOperation(anotherRBTree, 1, "", -1, false)
        redBlackTreeCompareAfterOperation(anotherRBTree, 1, "", 0, false)
        redBlackTreeCompareAfterOperation(anotherRBTree, 35, "", -1, false)
        redBlackTreeCompareAfterOperation(anotherRBTree, 35, "", 0, false)
        redBlackTreeCompareAfterOperation(anotherRBTree, 8, "", -1, false)
        redBlackTreeCompareAfterOperation(anotherRBTree, 60, "", -1, false)
        redBlackTreeCompareAfterOperation(anotherRBTree, 8, "", 0, false)
        redBlackTreeCompareAfterOperation(anotherRBTree, 2, "", 0, false)
        redBlackTreeCompareAfterOperation(anotherRBTree, 4, "", 0, false)
    }

    @Test
    fun iteratorDeleteTest() {
        var yetAnotherRBTree: RedBlackTree<Int, String>
        for (j in 1..18) {
            yetAnotherRBTree = RedBlackTree()
            for (i in 0..18) {
                redBlackTreeCompareAfterOperation(yetAnotherRBTree, i, (i + (j - 1) * 18).toString(), 1, true)
            }
            for (i in 0..18 step j) {
                redBlackTreeCompareAfterOperation(yetAnotherRBTree, i, "", -1, false)
            }
        }
        for (j in 1..18) {
            yetAnotherRBTree = RedBlackTree()
            for (i in 0..18) {
                redBlackTreeCompareAfterOperation(yetAnotherRBTree, i, (i + (j - 1) * 18).toString(), 1, true)
            }
            for (i in 18 downTo 0 step j) {
                redBlackTreeCompareAfterOperation(yetAnotherRBTree, i, "", -1, false)
            }
        }
    }

    /* Fuzzing is disabled! Remove comments to debug red black tree
    @Test
    fun insertDeleteFuzzingTest() {
        val redBlackTree = RedBlackTree<Int, String>()
        val keysRange = -50..50
        val percentageOfInserts = 50
        val totalTries = 10000
        for (i in 1..totalTries) {
            val randKey = keysRange.random()
            if ((1..100).random() < percentageOfInserts) {
                if (redBlackTree.search(randKey) != null) {
                    redBlackTreeCompareAfterOperation(redBlackTree, randKey, i.toString(), 0, true)
                } else {
                    redBlackTreeCompareAfterOperation(redBlackTree, randKey, i.toString(), 1, true)
                }
            } else {
                if (redBlackTree.search(randKey) != null) {
                    redBlackTreeCompareAfterOperation(redBlackTree, randKey, i.toString(), -1, false)
                } else {
                    redBlackTreeCompareAfterOperation(redBlackTree, randKey, i.toString(), 0, false)
                }
            }
        }
     */

    @Test
    fun rootDoesntHaveARightChildTest() {
        val tree = RedBlackTree<Int, String>()
        tree.insert(1, "1")
        tree.insert(0, "5")
        assertEquals(null, tree.root!!.right)
        tree.insert(-1, "6")
        assertEquals("6", tree.search(-1))
        assertEquals(true, isBalancedInColourRBTree(tree))
        assertEquals(true, isBinaryTree(tree))
    }

    @Test
    fun replacementNodeParentIsRed() {
        val tree = RedBlackTree<Int, String>()
        tree.insert(-1, "54")
        tree.insert(2, "57")
        tree.insert(3, "59")
        tree.insert(-2, "60")
        tree.insert(1, "64")
        tree.insert(0, "65")
        assertEquals("57", tree.search(2))
        tree.remove(2)
        assertEquals(null, tree.search(2))
        assertEquals(true, isBalancedInColourRBTree(tree))
        assertEquals(true, isBinaryTree(tree))
    }
}
