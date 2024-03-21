package bst.balancers

import bst.nodes.AVLTreeNode

class AVLTreeBalancer<K : Comparable<K>, V> : AbstractBSTBalancer<K, V, AVLTreeNode<K, V>>() {
    override fun inserter(node: AVLTreeNode<K, V>) {
        // TODO
    }

    override fun remover(node: AVLTreeNode<K, V>) {
        // TODO
    }
}
