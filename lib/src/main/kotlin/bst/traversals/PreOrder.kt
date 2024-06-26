package bst.traversals

import bst.nodes.AbstractBSTNode

internal class PreOrder<K : Comparable<K>, V : Any, R : AbstractBSTNode<K, V, R>> : BSTTraversal<K, V, R> {
    override fun <T> traverse(
        node: R?,
        extractionFunction: (R) -> T,
    ): List<T> {
        return preOrderTraversal(node, extractionFunction, mutableListOf())
    }

    private fun <T> preOrderTraversal(
        node: R?,
        extractionFunction: (R) -> T,
        result: MutableList<T>,
    ): MutableList<T> {
        if (node != null) {
            result.add(extractionFunction(node))
            preOrderTraversal(node.left, extractionFunction, result)
            preOrderTraversal(node.right, extractionFunction, result)
        }
        return result
    }
}
