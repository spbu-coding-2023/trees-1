package bst

import bst.balancers.AbstractBSTBalancer
import bst.balancers.AVLTreeBalancer
import bst.nodes.AVLTreeNode
import bst.nodes.RedBlackTreeNode

class AVLTree<K : Comparable<K>, V> : RegularAbstractBSTWithBalancer<K, V, AVLTreeNode<K, V>>() {
    override var balancer: AbstractBSTBalancer<K, V, AVLTreeNode<K, V>> = AVLTreeBalancer()

    override fun setNodeRight(nodeParent: AVLTreeNode<K, V>, nodeChild: AVLTreeNode<K, V>?) {
        TODO("Not yet implemented")
    }

    override fun setNodeLeft(nodeParent: AVLTreeNode<K, V>, nodeChild: AVLTreeNode<K, V>?) {
        TODO("Not yet implemented")
    }

    override fun search(key: K): V? {
        return super.search(key)
    }

    override fun insert(key: K, value: V): AVLTreeNode<K, V> {
        val newNode = super.insert(key, value)
        super.balance(balancer::inserter, root)
        return newNode // TODO
    }

    override fun remove(key: K): V? {
        super.balance(balancer::remover, root)
        return null // TODO
    }

    override fun createNode(key: K, value: V): AVLTreeNode<K, V> {
        return AVLTreeNode(key, value, null, 1)
    }
}