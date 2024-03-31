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
    redBlackTree.root = RedBlackTreeNode(13, "first", null)
    redBlackTree.root!!.setBlack()

    redBlackTree.root!!.left = RedBlackTreeNode(8, "second", redBlackTree.root)
    redBlackTree.root!!.left!!.setRed()

    redBlackTree.root!!.right = RedBlackTreeNode(17, "third", redBlackTree.root)
    redBlackTree.root!!.right!!.setRed()

    redBlackTree.root!!.left!!.left = RedBlackTreeNode(1, "fourth", redBlackTree.root!!.left)
    redBlackTree.root!!.left!!.left!!.setBlack()

    redBlackTree.root!!.left!!.right = RedBlackTreeNode(11, "fifth", redBlackTree.root!!.left)
    redBlackTree.root!!.left!!.right!!.setBlack()

    redBlackTree.root!!.right!!.left = RedBlackTreeNode(15, "sixth", redBlackTree.root!!.right)
    redBlackTree.root!!.right!!.left!!.setBlack()

    redBlackTree.root!!.right!!.right = RedBlackTreeNode(25, "seventh", redBlackTree.root!!.right)
    redBlackTree.root!!.right!!.right!!.setBlack()

    redBlackTree.root!!.left!!.left!!.right = RedBlackTreeNode(6, "eighth", redBlackTree.root!!.left!!.left)
    redBlackTree.root!!.left!!.left!!.right!!.setRed()

    redBlackTree.root!!.right!!.right!!.left = RedBlackTreeNode(22, "ninth", redBlackTree.root!!.right!!.right)
    redBlackTree.root!!.right!!.right!!.left!!.setRed()

    redBlackTree.root!!.right!!.right!!.right = RedBlackTreeNode(27, "tenth", redBlackTree.root!!.right!!.right)
    redBlackTree.root!!.right!!.right!!.right!!.setRed()
    return redBlackTree
}

fun setUpAnotherBalancedRBTree(): RedBlackTree<Int, String> {
    val redBlackTree = RedBlackTree<Int, String>()
    redBlackTree.root = RedBlackTreeNode(10, "first", null)
    redBlackTree.root!!.setBlack()

    redBlackTree.root!!.left = RedBlackTreeNode(7, "second", redBlackTree.root)
    redBlackTree.root!!.left!!.setBlack()

    redBlackTree.root!!.right = RedBlackTreeNode(40, "third", redBlackTree.root)
    redBlackTree.root!!.right!!.setBlack()

    redBlackTree.root!!.left!!.left = RedBlackTreeNode(3, "fourth", redBlackTree.root!!.left)
    redBlackTree.root!!.left!!.left!!.setBlack()

    redBlackTree.root!!.left!!.right = RedBlackTreeNode(8, "fifth", redBlackTree.root!!.left)
    redBlackTree.root!!.left!!.right!!.setBlack()

    redBlackTree.root!!.right!!.left = RedBlackTreeNode(30, "sixth", redBlackTree.root!!.right)
    redBlackTree.root!!.right!!.left!!.setRed()

    redBlackTree.root!!.right!!.right = RedBlackTreeNode(45, "seventh", redBlackTree.root!!.right)
    redBlackTree.root!!.right!!.right!!.setBlack()

    redBlackTree.root!!.left!!.left!!.left = RedBlackTreeNode(1, "eighth", redBlackTree.root!!.left!!.left)
    redBlackTree.root!!.left!!.left!!.left!!.setRed()

    redBlackTree.root!!.left!!.left!!.right = RedBlackTreeNode(5, "ninth", redBlackTree.root!!.left!!.left)
    redBlackTree.root!!.left!!.left!!.right!!.setRed()

    redBlackTree.root!!.right!!.left!!.left = RedBlackTreeNode(20, "tenth", redBlackTree.root!!.right!!.left)
    redBlackTree.root!!.right!!.left!!.left!!.setBlack()

    redBlackTree.root!!.right!!.left!!.right = RedBlackTreeNode(35, "eleventh", redBlackTree.root!!.right!!.left)
    redBlackTree.root!!.right!!.left!!.right!!.setBlack()

    redBlackTree.root!!.right!!.right!!.right = RedBlackTreeNode(60, "twelfth", redBlackTree.root!!.right!!.right)
    redBlackTree.root!!.right!!.right!!.right!!.setRed()

    redBlackTree.root!!.right!!.left!!.left!!.right = RedBlackTreeNode(25, "thirteenth", redBlackTree.root!!.right!!.left!!.left)
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
        assertEquals("sixth", redBlackTree.search(15))
        assertEquals("eleventh", anotherRBTree.search(35))
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
        if (changeInNumberOfElements == 0) {
            if (isInsert) {
                assertEquals(tree.insert(key, changingValue), null)
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
        }
        if (changeInNumberOfElements != 0) {
            frequencyMapBefore[
                Pair(
                    key,
                    changingValue,
                ),
            ] = frequencyMapBefore.getOrDefault(Pair(key, changingValue), 0) + changeInNumberOfElements
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
        universalTreeCompareAfterOperation(redBlackTree, 16, "eleventh", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 16, "twelfth", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 150, "twelfth", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 150, "thirteenth", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 12, "thirteenth", 1, true)
        universalTreeCompareAfterOperation(redBlackTree, 12, "fourteenth", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 17, "fourteenth", 0, true)
        universalTreeCompareAfterOperation(redBlackTree, 15, "fourteenth", 0, true)
        universalTreeCompareAfterOperation(anotherRBTree, 36, "fourteenth", 1, true)
        universalTreeCompareAfterOperation(anotherRBTree, 36, "fifteenth", 0, true)
        universalTreeCompareAfterOperation(anotherRBTree, 400, "fifteenth", 1, true)
        universalTreeCompareAfterOperation(anotherRBTree, 400, "sixteenth", 0, true)
        universalTreeCompareAfterOperation(anotherRBTree, 41, "sixteenth", 1, true)
        universalTreeCompareAfterOperation(anotherRBTree, 41, "seventeenth", 0, true)
        universalTreeCompareAfterOperation(anotherRBTree, 40, "seventeenth", 0, true)
        universalTreeCompareAfterOperation(anotherRBTree, 25, "seventeenth", 0, true)
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
        universalTreeCompareAfterOperation(anotherRBTree, 1, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 1, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 35, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 35, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 8, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 8, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 2, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 4, "", 0, false)
    }
}
