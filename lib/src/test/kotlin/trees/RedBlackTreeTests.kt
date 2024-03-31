package bst.tests

import bst.RedBlackTree
import bst.nodes.RedBlackTreeNode
import bst.traversals.InOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/* RedBlackTreeNode(
key: K,
value: V,
parent: RedBlackTreeNode<K, V>?,
) */

fun setUpBalancedRBTree(): RedBlackTree<Int, String> {
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

fun setUpAnotherBalancedRBTree(): RedBlackTree<Int, String> {
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

// : RegularTreeTest  Hello darkness my old friend
class RedBlackTreeTest {
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

    /* @Test
    fun isBalancedInKeysRBTreeTest(): Unit {
        assertEquals(true, super.isBalancedInKeys(setUpBalancedRBTree()))
        assertEquals(true, super.isBalancedInKeys(setUpAnotherBalancedRBTree()))
        assertEquals(false, super.isBalancedInKeys(setUpUnbalancedRBTree()))
        assertEquals(false, super.isBalancedInKeys(setUpAnotherUnbalancedRBTree()))
    }
    Waiting for Max to complete his test assignment! Very patiently, waiting...
     */

    @Test
    fun searchTest() {
        val redBlackTree = setUpBalancedRBTree()
        val anotherRBTree = setUpAnotherBalancedRBTree()
        assertEquals("6", redBlackTree.search(15))
        assertEquals("11", anotherRBTree.search(35))
        assertEquals(redBlackTree.search(7), null)
        assertEquals(anotherRBTree.search(21), null)
    }

    private fun universalTreeCompareAfterOperation(
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
        var oldValue: String = ""
        if (isInsert && changeInNumberOfElements == 0) {
            oldValue = tree.search(key)!!
            frequencyMapBefore[Pair(key, oldValue)] = 0
            frequencyMapBefore[Pair(key, changingValue)] = 1
        }
        if (changeInNumberOfElements == 0) {
            if (isInsert) {
                tree.insert(key, changingValue)
            } else {
                assertEquals(tree.remove(key), null)
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
        assertEquals(frequencyMapAfter, frequencyMapBefore)
        assertEquals(tree.search(key), if (isInsert) changingValue else null)
        // assertEquals(super.isBalancedInKeys(tree), true)    Waiting for Max...
        assertEquals(isBalancedInColourRBTree(tree), true)
    }

    @Test
    fun insertTest() {
        val redBlackTree = setUpBalancedRBTree()
        val anotherRBTree = setUpAnotherBalancedRBTree()
        universalTreeCompareAfterOperation(redBlackTree, 16, "11", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 16, "12", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 150, "13", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 150, "14", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 12, "15", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 12, "16", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 10, "17", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 10, "18", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 9, "19", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 9, "20", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 17, "21", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 15, "22", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 18, "23", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 19, "24", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 20, "25", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 21, "26", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 22, "27", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 23, "28", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 24, "29", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 15000, "30", 1, true)
        universalTreeCompareAfterOperation(anotherRBTree, 36, "16", 1, true)
        universalTreeCompareAfterOperation(anotherRBTree, 36, "17", 0, true)
        universalTreeCompareAfterOperation(anotherRBTree, 400, "18", 1, true)
        universalTreeCompareAfterOperation(anotherRBTree, 400, "19", 0, true)
        universalTreeCompareAfterOperation(anotherRBTree, 41, "20", 1, true)
        universalTreeCompareAfterOperation(anotherRBTree, 41, "21", 0, true)
        universalTreeCompareAfterOperation(anotherRBTree, 40, "22", 0, true)
        universalTreeCompareAfterOperation(anotherRBTree, 25, "23", 0, true)
    }

    @Test
    fun deleteTest() {
        val redBlackTree = setUpBalancedRBTree()
        val anotherRBTree = setUpAnotherBalancedRBTree()
        universalTreeCompareAfterOperation(redBlackTree, 13, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 13, "", 0, false)
        universalTreeCompareAfterOperation(redBlackTree, 27, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 27, "", 0, false)
        universalTreeCompareAfterOperation(redBlackTree, 15, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 15, "", 0, false)
        universalTreeCompareAfterOperation(redBlackTree, 2, "", 0, false)
        universalTreeCompareAfterOperation(redBlackTree, 3, "", 0, false)
        universalTreeCompareAfterOperation(redBlackTree, 8, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 25, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 1, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 6, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 15000, "", 0, false)
        universalTreeCompareAfterOperation(redBlackTree, 17, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 11, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 22, "", -1, false)
        universalTreeCompareAfterOperation(redBlackTree, 0, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 1, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 1, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 35, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 35, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 8, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 60, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 8, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 2, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 4, "", 0, false)
    }

    @Test
    fun massiveDeleteTest() {
        var yetAnotherRBTree = RedBlackTree<Int, String>()
        for (j in 1..1000) {
            yetAnotherRBTree = RedBlackTree()
            for (i in 0..1000) {
                universalTreeCompareAfterOperation(yetAnotherRBTree, i, "some value", 1, true)
            }
            for (i in 0..1000 step j) {
                universalTreeCompareAfterOperation(yetAnotherRBTree, i, "", -1, false)
            }
        }
        for (j in 1..1000) {
            yetAnotherRBTree = RedBlackTree()
            for (i in 0..1000) {
                universalTreeCompareAfterOperation(yetAnotherRBTree, i, "some value", 1, true)
            }
            for (i in 1000 downTo 0 step j) {
                universalTreeCompareAfterOperation(yetAnotherRBTree, i, "", -1, false)
            }
        }
    }
}
