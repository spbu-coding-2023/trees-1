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


}
