package bst.traversals

import bst.nodes.AbstractBSTNode

internal class InOrder<K : Comparable<K>, V : Any, R : AbstractBSTNode<K, V, R>> : BSTTraversal<K, V, R> {
    override fun <T> traverse(
        node: R?,
        extractionFunction: (R) -> T,
    ): List<T> {
        return inOrderTraversal(node, extractionFunction, mutableListOf())
    }

    private fun <T> inOrderTraversal(
        node: R?,
        extractionFunction: (R) -> T,
        result: MutableList<T>,
    ): MutableList<T> {
        if (node != null) {
            inOrderTraversal(node.left, extractionFunction, result)
            result.add(extractionFunction(node))
            inOrderTraversal(node.right, extractionFunction, result)
        }
        return result
    }
}
