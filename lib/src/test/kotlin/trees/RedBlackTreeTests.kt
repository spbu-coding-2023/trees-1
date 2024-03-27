package bst.tests

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertEquals
import bst.RedBlackTree

/* RedBlackTreeNode(
key: K,
value: V,
parent: RedBlackTreeNode<K, V>?,
) */

fun setUpBalancedRBTree(): RedBlackTree<Int, String> {
    val redBlackTree = RedBlackTree<Int, String>()
    redBlackTree.root = RedBLackTreeNode(13, "first", null)
    redBlackTree.root.setBlack()

    redBlackTree.root.left = RedBlackTreeNode(8, "second", redBlackTree.root)
    redBlackTree.root.left.setRed()

    redBlackTree.root.right = RedBlackTreeNode(17, "third", redBlackTree.root)
    redBlackTree.root.right.setRed()

    redBlackTree.root.left.left = RedBlackTreeNode(1, "fourth", redBlackTree.root.left)
    redBlackTree.root.left.left.setBlack()

    redBlackTree.root.left.right = RedBlackTreeNode(11, "fifth", redBlackTree.root.left)
    redBlackTree.root.left.right.setBlack()

    redBlackTree.root.right.left = RedBlackTreeNode(15, "sixth", redBlackTree.root.right)
    redBlackTree.root.right.left.setBlack()

    redBlackTree.root.right.right = RedBlackTreeNode(25, "seventh", redBlackTree.root.right)
    redBlackTree.root.right.right.setBlack()

    redBlackTree.root.left.left.right = RedBlackTreeNode(6, "eigth", redBlackTree.root.left.left)
    redBlackTree.root.left.left.right.setRed()

    redBlackTree.root.right.right.left = RedBlackTreeNode(22, "ninth", redBlackTree.root.right.right)
    redBlackTree.root.right.right.left.setRed()

    redBlackTree.root.right.right.right = RedBlackTreeNode(27, "tenth", redBlackTree.root.right.right)
    redBlackTree.root.right.right.right.setRed()
    return redBlackTree
}

fun setUpAnotherBalancedRBTree(): RedBlackTree<Int, String> {
    val redBlackTree = RedBlackTree<Int, String>()
    redBlackTree.root = RedBlackTreeNode(10, "first", null)
    redBlackTree.root.setBlack()

    redBlackTree.root.left = RedBlackTreeNode(7, "second", redBlackTree.root)
    redBlackTree.root.left.setBlack()

    redBlackTree.root.right = RedBlackTreeNode(40, "third", redBlackTree.root)
    redBlackTree.root.right.setBlack()

    redBlackTree.root.left.left = RedBlackTreeNode(3, "fourth", redBlackTree.root.left)
    redBlackTree.root.left.left.setBlack()

    redBlackTree.root.left.right = RedBlackTreeNode(8, "fifth", redBlackTree.root.left)
    redBlackTree.root.left.right.setBlack()

    redBlackTree.root.right.left = RedBlackTreeNode(30, "sixth", redBlackTree.root.right)
    redBlackTree.root.right.left.setRed()

    redBlackTree.root.right.right = RedBlackTreeNode(45, "seventh", redBlackTree.root.right)
    redBlackTree.root.right.right.setBlack()

    redBlackTree.root.left.left.left = RedBlackTreeNode(1, "eighth", redBlackTree.root.left.left)
    redBlackTree.root.left.left.left.setRed()

    redBlackTree.root.left.left.right = RedBlackTreeNode(5, "ninth", redBlackTree.root.left.left)
    redBlackTree.root.left.left.right.setRed()

    redBlackTree.root.right.left.left = RedBlackTreeNode(20, "tenth", redBlackTree.root.right.left)
    redBlackTree.root.right.left.left.setBlack()

    redBlackTree.root.right.left.right = RedBlackTreeNode(35, "eleventh", redBlackTree.root.right.left)
    redBlackTree.root.right.left.right.setBlack()

    redBlackTree.root.right.right.right = RedBlackTreeNode(60, "twelfth", redBlackTree.root.right.right)
    redBlackTree.root.right.right.right.setRed()

    redBlackTree.root.right.left.left.right = RedBlackTreeNode(25, "thirteenth", redBlackTree.root.right.left.left)
    redBlackTree.root.right.left.left.right.setRed()
    return redBlackTree
}

private fun setUpUnbalancedRBTree() : RedBlackTree<Int, String> {
    val redBlackTree = setUpBalancedRBTree()
    redBlackTree.root.right.left.setRed()
    redBlackTree.root.left.right.value = 20
    return redBlackTree
}

private fun setUpAnotherUnbalancedRBTree() : RedBlackTree<Int, String> {
    val redBlackTree = setUpAnotherBalancedRBTree()
    redBlackTree.root.left.right.setRed()
    redBlackTree.root.right.left.right.value = 19
    return redBlackTree
}

class RedBlackTreeTest : RegularTreeTest() {
    private fun numberOfBlackNodesDownwardsAndRBConditionChecker(node : RedBlackTreeNode<K, V>) : Int {
        if (node == null) {
            return 1
        }
        if (node.parent == null && node.isRed()) {
            return -1
        }
        val leftBlackHeight = isBalancedInColourRBTree(node.left)
        val rightBlackHeight = isBalancedInColourRBTree(node.right)
        if (leftBlackHeight == -1 || rightBlackHeight == -1 || leftBlackHeight != rightBlackHeight) {
            return -1
        }
        if (node.isRed() && node.parent?.isRed()) {
            return -1
        }
        return if (node.isBlack()) leftHeight + 1 else leftHeight
    }

    private fun isBalancedInColourRBTree(RBTree : RedBlackTree<K, V>) : Int {
        return numberOfBlackNodesDownwardsAndRBConditionChecker(RBTree.root) != -1 // -1 means conditions are not met
    }

    @Test
    fun isBalancedInColourRBTreeTest() {
        assertEquals(true, isBalancedInColourRBTree(setUpBalancedTree()))
        assertEquals(true, isBalancedInColourRBTree(setUpAnotherBalancedTree()))
        assertEquals(false, isBalancedInColourRBTree(setUpUnbalancedTree()))
        assertEquals(false, isBalancedInColourRBTree(setUpAnotherUnbalancedTree()))
    }

    @Test
    private fun isBalancedInKeysRBTreeTest() {
        assertEquals(true, super.isBalancedInKeys(setUpBalancedRBTree()))
        assertEquals(true, super.isBalancedInKeys(setUpAnotherBalancedRBTree()))
        assertEquals(false, super.isBalancedInKeys(setUpUnbalancedRBTree()))
        assertEquals(false, super.isBalancedInKeys(setUpAnotherUnbalancedRBTree()))
    }

    @Test
    private fun searchTest() {
        val RBTree = setUpBalancedRBTree()
        val anotherRBTree = setUpAnotherRBTree()
        assertEquals(RBTree.search(15), "sixth")
        assertEquals(anotherRBTree.search(35), "eleventh")
        assertEquals(RBTree.search(7), null)
        assertEquals(anotherRBTree.search(21), null)
    }

    private fun universalTreeCompareAfterOperation(tree: RedBlackTree<Int, String>, key: Int, value: String, changeInNumberOfElements: Int, isInsert: Boolean) {
        val inOrderInstance = inOrder<Int, String, RedBlackTreeNode<Int, String>>()
        val listBefore: List<Pair<Int, String>> = inOrderInstance.traverse(tree.root) { node -> Pair(node.key, node.value) }
        val frequencyMapBefore = listBefore.groupingBy { it }.eachCount()
        if (changeInNumberOfElements == 0) {
            if (isInsert) {
                assertEquals(tree.insert(key, value), null)
            } else {
                assertEquals(tree.delete(key), null)
            }
        } else if (changeInNumberOfElements == 1) {
            tree.insert(key, value)
        } else if (changeInNumberOfElements == -1) {
            value = tree.search(key)
            tree.delete(key)

        }
        val listAfter: List<Pair<Int, String>> = inOrderInstance.traverse(tree.root) { node -> Pair(node.key, node.value) }
        val frequencyMapAfter = listAfter.groupingBy { it }.eachCount()
        if (changeInNumberOfElements != 0) {
            frequencyMapBefore[Pair(key, value)] = frequencyMapBefore.getOrDefault(Pair(key, value), 0) + changeInNumberOfElements
        }
        assertEquals(frequencyMapAfter, frequencyMapBefore)
        assertEquals(tree.search(key), if (isInsert) value else null)
        assertEquals(super.isBalancedInKeys(tree), true)
        assertEquals(isBalancedInColourRBTree(tree), true)
    }

    @Test
    private fun insertTest() {
        val RBTree = setUpBalancedRBTree()
        val anotherRBTree = setUpAnotherBalancedRBTree()
        universalTreeCompareAfterOperation(RBTree, 16, "eleventh", 1, true)
        universalTreeCompareAfterOperation(RBTree, 16, "twelfth", 0, true)
        universalTreeCompareAfterOperation(RBTree, 150, "twelfth", 1, true)
        universalTreeCompareAfterOperation(RBTree, 150, "thirteenth", 0, true)
        universalTreeCompareAfterOperation(RBTree, 12, "thirteenth", 1, true)
        universalTreeCompareAfterOperation(RBTree, 12, "fourteenth", 0, true)
        universalTreeCompareAfterOperation(RBTree, 17, "fourteenth", 0, true)
        universalTreeCompareAfterOperation(RBTree, 30, "fourteenth", 0, true)
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
    private fun deleteTest() {
        val RBTree = setUpBalancedRBTree()
        val anotherRBTree = setUpAnotherBalancedRBTree()
        universalTreeCompareAfterOperation(RBTree, 13, "", -1, false)
        universalTreeCompareAfterOperation(RBTree, 13, "", 0, false)
        universalTreeCompareAfterOperation(RBTree, 27, "", -1, false)
        universalTreeCompareAfterOperation(RBTree, 27, "", 0, false)
        universalTreeCompareAfterOperation(RBTree, 15, "", -1, false)
        universalTreeCompareAfterOperation(RBTree, 15, "", 0, false)
        universalTreeCompareAfterOperation(RBTree, 2, "", 0, false)
        universalTreeCompareAfterOperation(RBTree, 3, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 1, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 1, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 35, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 35, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 8, "", -1, false)
        universalTreeCompareAfterOperation(anotherRBTree, 8, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 2, "", 0, false)
        universalTreeCompareAfterOperation(anotherRBTree, 3, "", 0, false)
    }
}
