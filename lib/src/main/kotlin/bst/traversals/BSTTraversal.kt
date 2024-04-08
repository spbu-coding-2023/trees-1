package bst.traversals

import bst.nodes.AbstractBSTNode

internal interface BSTTraversal<K : Comparable<K>, V : Any, R : AbstractBSTNode<K, V, R>> {
    fun <T> traverse(
        node: R?,
        extractionFunction: (R) -> T,
    ): List<T>
}
