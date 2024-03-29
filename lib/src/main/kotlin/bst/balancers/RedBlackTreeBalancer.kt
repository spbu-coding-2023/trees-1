package bst.balancers

import bst.nodes.RedBlackTreeNode

class RedBlackTreeBalancer<K : Comparable<K>, V> : AbstractBSTBalancer<K, V, RedBlackTreeNode<K, V>>() {
    private fun RedBlackTreeNode<K, V>.findUncle(): RedBlackTreeNode<K, V>? {
        val grandparent = this.findGrandparent() ?: return null
        return when (this.parent) {
            grandparent.left -> grandparent.right
            grandparent.right -> grandparent.left
            else -> null
        }
    }

    private fun RedBlackTreeNode<K, V>.findGrandparent(): RedBlackTreeNode<K, V>? {
        return this.parent?.parent
    }

    override fun inserter(node: RedBlackTreeNode<K, V>) {
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
                if (current == current.parent!!.left && current.parent == grandparent.left) {
                    rotateRight(grandparent)
                } else {
                    rotateLeft(grandparent)
                }
            }
        }
    }

    private fun RedBlackTreeNode<K, V>.findSibling(): RedBlackTreeNode<K, V>? {
        return when (this) {
            this.parent?.left -> this.parent?.right
            this.parent?.right -> this.parent?.left
            else -> null
        }
    }

    override fun remover(node: RedBlackTreeNode<K, V>) {
        if (node.parent != null) {
            var sibling = node.findSibling()

            if (sibling!!.isRed()) {
                node.parent!!.setRed()
                sibling.setBlack()
                if (node == node.parent!!.left) {
                    rotateLeft(node.parent!!)
                } else {
                    rotateRight(node.parent!!)
                }
                sibling = node.findSibling()
            }
            if (node.parent!!.isBlack() && sibling!!.isBlack() && sibling.left!!.isBlack() && sibling.right!!.isBlack()) {
                sibling.setRed()
                remover(node.parent!!)
            } else {
                if (node.parent!!.isRed() && sibling!!.isBlack() && sibling.left!!.isBlack() && sibling.right!!.isBlack()) {
                    sibling.setRed()
                    node.parent!!.setBlack()
                } else {
                    if (sibling!!.isBlack()) {
                        if (node == node.parent!!.left && sibling.right!!.isBlack() && sibling.left!!.isRed()) {
                            sibling.setRed()
                            sibling.left!!.setBlack()
                            rotateRight(sibling)
                        } else if (node == node.parent!!.right && sibling.left!!.isBlack() && sibling.right!!.isRed()) {
                            sibling.setRed()
                            sibling.right!!.setBlack()
                            rotateLeft(sibling)
                        }
                        sibling = node.findSibling()
                    }
                    if (node.parent!!.isRed()) {
                        sibling!!.setRed()
                    } else { sibling!!.setBlack() }
                    node.parent!!.setBlack()
                    if (node == node.parent!!.left) {
                        sibling.right!!.setBlack()
                        rotateLeft(node.parent!!)
                    } else {
                        sibling.left!!.setBlack()
                        rotateRight(node.parent!!)
                    }
                }
            }
        }
    }
}
