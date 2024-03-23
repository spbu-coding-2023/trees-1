package bst

import bst.balancers.AbstractBSTBalancer
import bst.balancers.RedBlackTreeBalancer
import bst.nodes.RedBlackTreeNode

class RedBlackTree<K : Comparable<K>, V> : RegularAbstractBSTWithBalancer<K, V, RedBlackTreeNode<K, V>>() {
    override var balancer: AbstractBSTBalancer<K, V, RedBlackTreeNode<K, V>> = RedBlackTreeBalancer()

    override fun setNodeRight(nodeParent: RedBlackTreeNode<K, V>, nodeChild: RedBlackTreeNode<K, V>?) {
        TODO("Not yet implemented")
    }

    override fun setNodeLeft(nodeParent: RedBlackTreeNode<K, V>, nodeChild: RedBlackTreeNode<K, V>?) {
        TODO("Not yet implemented")
    }

    override fun search(key: K): V? {
        return null // TODO
    }

    override fun insert(key: K, value: V): RedBlackTreeNode<K, V> {
        super.balance(balancer::inserter, root)
        return createNode(key, value) // TODO
    }

    override fun remove(key: K): V? {
        super.balance(balancer::remover, root)
        return null // TODO
    }

    override fun createNode(key: K, value: V): RedBlackTreeNode<K, V> {
        return RedBlackTreeNode(key, value, null)
    }

    override fun setNode(node: RedBlackTreeNode<K, V>, newNode: RedBlackTreeNode<K, V>) {
        TODO("Not yet implemented")
    }
}
