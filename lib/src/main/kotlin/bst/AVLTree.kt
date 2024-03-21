package bst

import bst.balancers.AbstractBSTBalancer
import bst.balancers.AVLTreeBalancer
import bst.nodes.AVLTreeNode

class AVLTree<K : Comparable<K>, V> : RegularAbstractBSTWithBalancer<K, V, AVLTreeNode<K, V>>() {
    override var balancer: AbstractBSTBalancer<K, V, AVLTreeNode<K, V>> = AVLTreeBalancer()

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