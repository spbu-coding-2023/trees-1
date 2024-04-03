package bst.balancers

import bst.nodes.RedBlackTreeNode

class RedBlackTreeBalancer<K : Comparable<K>, V> : AbstractBSTBalancer<K, V, RedBlackTreeNode<K, V>>() {
    private fun RedBlackTreeNode<K, V>.findUncle(): RedBlackTreeNode<K, V>? {
        val grandparent = this.findGrandparent()!!
        return when (this.parent!!) {
            grandparent.left -> grandparent.right
            else -> grandparent.left
        }
    }

    private fun RedBlackTreeNode<K, V>.findGrandparent(): RedBlackTreeNode<K, V>? {
        return this.parent!!.parent
    }

    override fun inserter(node: RedBlackTreeNode<K, V>): RedBlackTreeNode<K, V> {
        var current = node
        current.setRed()
        if (current.parent == null) {
            current.setBlack()
        } else if (current.parent!!.isBlack()) {
            // do nothing
        } else {
            val uncle = current.findUncle() // uncle doesn't need to be updated so val
            var grandparent = current.findGrandparent() // grandparent exists since parent is red and root is always black
            if (uncle != null && uncle.isRed()) {
                current.parent!!.setBlack()
                uncle.setBlack()
                grandparent!!.setRed()
                inserter(grandparent)
            } else {
                if (current == current.parent!!.right && current.parent == grandparent!!.left) {
                    rotateLeft(current.parent!!)
                    current = current.left!!
                } else if (current == current.parent!!.left && current.parent == grandparent!!.right) {
                    rotateRight(current.parent!!)
                    current = current.right!!
                }
                grandparent = current.findGrandparent()
                current.parent!!.setBlack()
                grandparent!!.setRed()
                if (current.parent == grandparent.left) {
                    rotateRight(grandparent)
                } else {
                    rotateLeft(grandparent)
                }
            }
        }
        var findRoot = node
        while (findRoot.parent != null) findRoot = findRoot.parent!!
        return findRoot
    }

    private fun RedBlackTreeNode<K, V>.findSibling(): RedBlackTreeNode<K, V>? {
        return when (this) {
            this.parent!!.left -> this.parent!!.right
            else -> this.parent!!.left
        }
    }

    override fun remover(node: RedBlackTreeNode<K, V>): RedBlackTreeNode<K, V> {
        if (node.parent != null) {
            val sibling = node.findSibling()
            if (sibling!!.isRed()) {
                node.parent!!.setRed()
                sibling.setBlack()

                if (sibling == sibling.parent!!.left) {
                    rotateRight(node.parent!!)
                } else {
                    rotateLeft(node.parent!!)
                }
                remover(node)
            } else {
                if (sibling.left?.isRed() == true || sibling.right?.isRed() == true) {
                    if (sibling.left?.isRed() == true) {
                        if (sibling == sibling.parent!!.left) {
                            sibling.left!!.setBlack()
                            if (node.parent!!.isRed()) {
                                sibling.setRed()
                                node.parent!!.setBlack()
                            } else {
                                sibling.setBlack()
                            }
                            rotateRight(node.parent!!)
                        } else {
                            if (node.parent!!.isRed()) {
                                sibling.left!!.setRed()
                                node.parent!!.setBlack()
                            } else {
                                sibling.left!!.setBlack()
                            }
                            rotateRight(sibling)
                            rotateLeft(node.parent!!)
                        }
                    } else {
                        if (sibling == sibling.parent!!.left) {
                            if (node.parent!!.isRed()) {
                                sibling.right!!.setRed()
                                node.parent!!.setBlack()
                            } else {
                                sibling.right!!.setBlack()
                            }
                            rotateLeft(sibling)
                            rotateRight(node.parent!!)
                        } else {
                            sibling.right!!.setBlack()
                            if (node.parent!!.isRed()) {
                                sibling.setRed()
                                node.parent!!.setBlack()
                            } else {
                                sibling.setBlack()
                            }
                            rotateLeft(node.parent!!)
                        }
                    }
                } else {
                    sibling.setRed()
                    if (node.parent!!.isBlack()) {
                        remover(node.parent!!)
                    } else {
                        node.parent!!.setBlack()
                    }
                }
            }
        }
        var findRoot = node
        while (findRoot.parent != null) findRoot = findRoot.parent!!
        return findRoot
    }
}
