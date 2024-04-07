package bst.traversals

import bst.nodes.AbstractBSTNode

internal class PostOrder<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> : BSTTraversal<K, V, R> {
    override fun <T> traverse(
        node: R?,
        extractionFunction: (R) -> T,
    ): List<T> {
        return postOrderTraversal(node, extractionFunction, mutableListOf())
    }

    private fun <T> postOrderTraversal(
        node: R?,
        extractionFunction: (R) -> T,
        result: MutableList<T>,
    ): MutableList<T> {
        if (node != null) {
            postOrderTraversal(node.left, extractionFunction, result)
            postOrderTraversal(node.right, extractionFunction, result)
            result.add(extractionFunction(node))
        }
        return result
    }
}
