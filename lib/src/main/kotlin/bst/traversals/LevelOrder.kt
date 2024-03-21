package bst.traversals

import bst.nodes.AbstractBSTNode

class LevelOrder<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> : BSTTraversal<K, V, R> {
    override fun <T> traverse(node: R, extractionFunction: (R) -> T): List<T> {
        return listOf(extractionFunction(node)) // TODO
    }
}
