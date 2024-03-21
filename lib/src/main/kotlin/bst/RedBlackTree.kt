package bst

import bst.balancers.AbstractBSTBalancer
import bst.balancers.RedBlackTreeBalancer
import bst.nodes.RedBlackTreeNode

class RedBlackTree<K : Comparable<K>, V> : RegularAbstractBSTWithBalancer<K, V, RedBlackTreeNode<K, V>>() {
    override var balancer: AbstractBSTBalancer<K, V, RedBlackTreeNode<K, V>> = RedBlackTreeBalancer()

    override fun search(key: K): V? {
        return null // TODO
    }

    override fun insert(key: K, value: V): Boolean {
        super.balance(balancer::inserter)
        return false // TODO
    }

    override fun remove(key: K): V? {
        super.balance(balancer::remover)
        return null // TODO
    }
}
