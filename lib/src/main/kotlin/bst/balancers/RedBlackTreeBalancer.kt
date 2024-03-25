package bst.balancers

import bst.nodes.RedBlackTreeNode

class RedBlackTreeBalancer<K : Comparable<K>, V> : AbstractBSTBalancer<K, V, RedBlackTreeNode<K, V>>() {
    private fun RedBlackTreeNode<K, V>.findUncle() : RedBlackTreeNode<K, V>? {
        val grandparent = findGrandparent(this) ?: return null
        return when(this.parent) {
            grandparent.left -> grandparent.right
            grandparent.right -> grandparent.left
            else -> null
        }
    }
    private fun RedBlackTreeNode<K, V>.findGrandparent() : RedBlackTreeNode<K, V>? {
        return this.parent?.parent
    }
    override fun inserter(node: RedBlackTreeNode<K, V>) {
        if (node.parent == null) {
            node.setBlack()
        } else if (node.parent.isBlack()) {
            // do nothing
        } else {
            val uncle = node.findUncle()
            val grandparent = node.findGrandparent() // grandparent exists since parent is red and root is always black
            if (uncle != null && uncle.isRed()) {
                node.parent.setBlack()
                uncle.setBlack()
                grandparent.setRed()
                inserter(grandparent)
            } else if (node == node.parent.right && node.parent == grandparent.left) {
                super.rotateLeft(node.parent)
                node = node.left
            } else if (node == node.parent.left && node.parent == grandparent.right) {
                super.rotateRight(node.parent)
                node = node.right
            }
            node.parent.setBlack()
            grandparent.setRed()
            if (node == node.parent.left && node.parent == grandparent.left) {
                rotateRight(grandparent)
            } else {
                rotateLeft(grandparent)
            }
        }
    }

    
    override fun remover(node: RedBlackTreeNode<K, V>) {
        // TODO
    }
}
