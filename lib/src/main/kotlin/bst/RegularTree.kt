package bst

import bst.nodes.BSTNode

class RegularTree<K : Comparable<K>, V> : RegularAbstractBST<K, V, BSTNode<K, V>>() {
    override fun search(key: K): V? {
        return null // TODO
    }

    override fun insert(key: K, value: V): Boolean {
        return false // TODO
    }

    override fun remove(key: K): V? {
        return null // TODO
    }
}
