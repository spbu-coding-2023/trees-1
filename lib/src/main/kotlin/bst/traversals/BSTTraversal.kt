package bst.traversals

import bst.nodes.AbstractBSTNode

interface BSTTraversal<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> {
    fun <T> traverse(node: R, extractionFunction: (R) -> T): List<T>
}
