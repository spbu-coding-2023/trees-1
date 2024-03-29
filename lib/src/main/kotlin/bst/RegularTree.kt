package bst

import bst.nodes.BSTNode

class RegularTree<K : Comparable<K>, V> : RegularAbstractBST<K, V, BSTNode<K, V>>() {
    override fun search(key: K): V? {
        return super.search(key)
    }

    override fun insert(
        key: K,
        value: V,
    ): BSTNode<K, V> {
        return super.insert(key, value)
    }

    override fun remove(key: K): V? {
        return super.remove(key)
    }

    override fun createNode(
        key: K,
        value: V,
    ): BSTNode<K, V> {
        return BSTNode(key, value)
    }

    override fun setNodeLeft(
        nodeParent: BSTNode<K, V>,
        nodeChild: BSTNode<K, V>?,
    ) {
        nodeParent.left = nodeChild?.left
    }

    override fun setNodeRight(
        nodeParent: BSTNode<K, V>,
        nodeChild: BSTNode<K, V>?,
    ) {
        nodeParent.right = nodeChild?.right
    }

    override fun setNode(
        node: BSTNode<K, V>,
        newNode: BSTNode<K, V>,
    ) {
        node.key = newNode.key
        node.value = newNode.value
        node.left = newNode.left
        node.right = newNode.right
    }
}
