package bst.balancers

import bst.nodes.AbstractBSTNode

abstract class AbstractBSTBalancer<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> {

    abstract fun inserter(node: R)
    abstract fun remover(node: R)

    fun rotateLeft(node: R) {
        // TODO
    }

    fun rotateRight(node: R) {
        // TODO
    }
}

