import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertEquals
import bst.RedBlackTree
class RedBlackTreeTest {
    /* RedBlackTreeNode(
    key: K,
    value: V,
    parent: RedBlackTreeNode<K, V>?,
    ) */
    private fun setUpBalancedTree(): RedBlackTree<Int, String> {
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

    private fun setUpAnotherBalancedTree(): RedBlackTree<Int, String> {
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
}

    private fun setUpUnbalancedTree() : RedBlackTree<Int, String> {
        val redBlackTree = setUpBalancedTree()
        redBlackTree.root.right.left.setRed()
        redBlackTree.root.left.right.value = 20
        return redBlackTree
    }

    private fun setUpAnotherUnbalancedTree() : RedBlackTree<Int, String> {
        val redBlackTree = setUpAnotherBalancedTree()
        redBlackTree.root.left.right.setRed()
        redBlackTree.root.right.left.right.value = 19
        return redBlackTree
    }


    private fun isBalancedInColour(RedBlackTree<K, V>) {
        TODO("Not finished")
    }

    private fun isBalancedInKeysTest(RedBlackTree<K, V>) {
       // Should be borrowed from regular BST
    }

    @Test
    fun isBalancedInColourTest() {
        val balancedRedBlackTree = setUpBalancedTree()
        val unbalancedRedBlackTree = setUpUnbalancedTree()
        assertEquals(true, balancedRedBlackTree.)
    }

    // Many more tests to come...
}