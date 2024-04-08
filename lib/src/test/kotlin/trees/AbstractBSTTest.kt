package trees

import bst.RegularAbstractBST
import bst.nodes.AbstractBSTNode

abstract class AbstractBSTTest<T : RegularAbstractBST<Int, String, R>, R : AbstractBSTNode<Int, String, R>> {
    protected fun isBinaryTree(tree: T): Boolean {
        return isBinaryTreeRecursive(tree.root, Int.MIN_VALUE, Int.MAX_VALUE)
    }

    private fun isBinaryTreeRecursive(
        node: R?,
        min: Int,
        max: Int,
    ): Boolean {
        if (node == null) return true

        if (node.key <= min || node.key >= max) return false

        return isBinaryTreeRecursive(node.left, min, node.key) && isBinaryTreeRecursive(node.right, node.key, max)
    }
}
