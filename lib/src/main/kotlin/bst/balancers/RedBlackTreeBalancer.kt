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
            val uncle = node.findUncle() // uncle doesn't need to be updated so val
            var grandparent = node.findGrandparent() // grandparent exists since parent is red and root is always black
            if (uncle != null && uncle.isRed()) {
                node.parent.setBlack()
                uncle.setBlack()
                grandparent.setRed()
                inserter(grandparent)
            } else {
                if (node == node.parent.right && node.parent == grandparent.left) {
                    super.rotateLeft(node.parent)
                    node = node.left
                } else if (node == node.parent.left && node.parent == grandparent.right) {
                    super.rotateRight(node.parent)
                    node = node.right
                }
                grandparent = node.findGrandparent()
                node.parent.setBlack()
                grandparent.setRed()
                if (node == node.parent.left && node.parent == grandparent.left) {
                    rotateRight(grandparent)
                } else {
                    rotateLeft(grandparent)
                }
            }
        }
    }

    private fun RedBlackTreeNode<K, V>.findSibling() : RedBlackTreeNode<K, V>? {
        when (this) {
            this.parent.left -> return this.parent.right
            this.parent.right -> return this.parent.left
            else -> return null
        }
    }

    override fun remover(node: RedBlackTreeNode<K, V>) {
        if (node.parent != null) {
            var sibling = node.findSibling()
            if (sibling.isRed()) {
                node.parent.setRed()
                sibling.setBlack()
                if (node == node.parent.left) {
                    super.rotateLeft(node.parent)
                } else {
                    super.rotateRight(node.parent)
                }
                sibling = node.findSibling()
            }
            if (node.parent.isBlack() && sibling.isBlack() && sibling.left.isBlack() && sibling.right.isBlack()) {
                sibling.setRed()
                remover(node.parent)
            } else {
                if (node.parent.isRed() && sibling.isBlack() && sibling.left.isBlack() && sibling.right.isBlack()) {
                    sibling.setRed()
                    node.parent.setBlack()
                } else {
                    if (sibling.isBlack()) {
                        if (node == node.parent.left && sibling.right.isBlack() && sibling.left.isRed()) {
                            sibling.setRed()
                            sibling.left.setBlack()
                            super.rotateRight(sibling)
                        } else if (node == node.parent.right && sibling.left.isBlack() && sibling.right.isRed()) {
                            sibling.setRed()
                            sibling.right.setBlack()
                            super.rotateLeft(sibling)
                        }
                        sibling = node.findSibling()
                    }
                    if (node.parent.isRed()) {
                        sibling.setRed()
                    } else {
                        sibling.setBlack()
                    }
                    node.parent.setBlack()
                    if (node == node.parent.left) {
                        sibling.right.setBlack()
                        rotateLeft(node.parent)
                    } else {
                        sibling.left.setBlack()
                        rotateRight(node.parent)
                    }
                }
            }
        }
    }
}
