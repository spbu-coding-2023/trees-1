package bst

import bst.nodes.AbstractBSTNode
import bst.traversals.BSTTraversed

abstract class AbstractBST<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> {
    abstract var root: R?

    val traversed = BSTTraversed { root }

    abstract fun search(key: K): V?

    abstract fun insert(
        key: K,
        value: V,
    ): R

    abstract fun remove(key: K): V?
}
