package bst

import bst.nodes.BSTNode

class RegularTree<K : Comparable<K>, V> : RegularAbstractBST<K, V, BSTNode<K, V>>() {
    override fun search(key: K): V? {
        return null // TODO
    }

    override fun insert(key: K, value: V): BSTNode<K, V> {
        return createNode(key, value) // TODO
    }

    override fun remove(key: K): V? {
        return null // TODO
    }

    override fun createNode(key: K, value: V): BSTNode<K, V> {
        return BSTNode(key, value)
    }

    override fun setNodeLeft(nodeParent: BSTNode<K, V>, nodeChild: BSTNode<K, V>?) {
        TODO("Not yet implemented")
    }

    override fun setNodeRight(nodeParent: BSTNode<K, V>, nodeChild: BSTNode<K, V>?) {
        TODO("Not yet implemented")
    }
}
