package bst

import bst.balancers.AbstractBSTBalancer
import bst.balancers.RedBlackTreeBalancer
import bst.nodes.RedBlackTreeNode

class RedBlackTree<K : Comparable<K>, V> : RegularAbstractBSTWithBalancer<K, V, RedBlackTreeNode<K, V>>() {
    override var balancer: AbstractBSTBalancer<K, V, RedBlackTreeNode<K, V>> = RedBlackTreeBalancer()

    override fun setNode(
        node: RedBlackTreeNode<K, V>,
        newNode: RedBlackTreeNode<K, V>,
    ) {
        if (node == node.parent?.left) {
            node.parent!!.left = newNode
        } else if (node == node.parent?.right) {
            node.parent!!.right = newNode
        }
        node.right?.parent = newNode
        node.left?.parent = newNode
        newNode.parent = node.parent
        newNode.right = node.right
        newNode.left = node.left
        if (node.isBlack()) {
            newNode.setBlack()
        } else {
            newNode.setRed()
        }
    }

    override fun setNodeRight(
        nodeParent: RedBlackTreeNode<K, V>,
        nodeChild: RedBlackTreeNode<K, V>?,
    ) {
        nodeParent.right = nodeChild
        nodeChild!!.parent = nodeParent
    }

    override fun setNodeLeft(
        nodeParent: RedBlackTreeNode<K, V>,
        nodeChild: RedBlackTreeNode<K, V>?,
    ) {
        nodeParent.left = nodeChild
        nodeChild!!.parent = nodeParent
    }

    override fun insert(
        key: K,
        value: V,
    ): RedBlackTreeNode<K, V> {
        val doBalance = search(key) == null
        val foundNode = findNode(key)
        val newNode: RedBlackTreeNode<K, V>
        if (foundNode != null && foundNode.key == key && foundNode.value == value) {
            newNode = foundNode
        } else {
            newNode = super.insert(key, value)
        }
        if (doBalance) super.balance(balancer::inserter, newNode)
        return newNode
    }

    private fun RedBlackTreeNode<K, V>.findSibling(): RedBlackTreeNode<K, V>? {
        return when (this) {
            this.parent?.left -> this.parent?.right
            this.parent?.right -> this.parent?.left
            else -> null
        }
    }

    override fun remove(key: K): V? {
        val nodeToRemove = findNode(key) ?: return null
        val valueToReturn = nodeToRemove.value
        if (nodeToRemove == root && nodeToRemove.left == null && nodeToRemove.right == null) {
            root = null
            return valueToReturn
        }
        removeNode(nodeToRemove)
        return valueToReturn
    }

    private fun removeNode(nodeToRemove: RedBlackTreeNode<K, V>) {
        val leftChild = nodeToRemove.left
        val rightChild = nodeToRemove.right
        var replacementNode: RedBlackTreeNode<K, V>?
        if (leftChild != null && rightChild != null) {
            replacementNode = rightChild
            while (replacementNode!!.left != null) {
                replacementNode = replacementNode.left
            }
        } else if (leftChild == null && rightChild == null) {
            replacementNode = null
        } else {
            replacementNode = leftChild ?: rightChild
        }
        val nodeAndReplacementAreBlack = (replacementNode == null || replacementNode.isBlack()) and (nodeToRemove.isBlack())
        if (replacementNode == null) {
            if (nodeToRemove == root) {
                root = null
            } else {
                if (nodeAndReplacementAreBlack) {
                    balance(balancer::remover, nodeToRemove)
                } else {
                    if (nodeToRemove.findSibling() != null) {
                        nodeToRemove.findSibling()!!.setRed()
                    }
                }
            }
            if (nodeToRemove.parent!!.left == nodeToRemove) {
                nodeToRemove.parent!!.left = null
            } else {
                nodeToRemove.parent!!.right = null
            }
            nodeToRemove.parent = null
            nodeToRemove.left?.parent = null
            nodeToRemove.right?.parent = null
            nodeToRemove.left = null
            nodeToRemove.right = null
            return
        }
        if (nodeToRemove.left == null || nodeToRemove.right == null) {
            if (nodeToRemove == root) {
                nodeToRemove.value = replacementNode.value
                nodeToRemove.key = replacementNode.key
                nodeToRemove.left = null
                nodeToRemove.right = null
                if (replacementNode.parent!!.left == replacementNode) {
                    replacementNode.parent!!.left = null
                } else {
                    replacementNode.parent!!.right = null
                }
                replacementNode.parent = null
                replacementNode.left?.parent = null
                replacementNode.right?.parent = null
                replacementNode.left = null
                replacementNode.right = null
            } else {
                if (nodeToRemove == nodeToRemove.parent!!.left) {
                    nodeToRemove.parent!!.left = replacementNode
                } else {
                    nodeToRemove.parent!!.right = replacementNode
                }
                replacementNode.parent = nodeToRemove.parent
                nodeToRemove.parent = null
                nodeToRemove.left = null
                nodeToRemove.right = null
                if (nodeAndReplacementAreBlack) {
                    balance(balancer::remover, replacementNode)
                } else {
                    replacementNode.setBlack()
                }
            }
        } else {
            val tempVal = replacementNode.value
            val tempKey = replacementNode.key
            replacementNode.value = nodeToRemove.value
            nodeToRemove.value = tempVal
            replacementNode.key = nodeToRemove.key
            nodeToRemove.key = tempKey
            removeNode(replacementNode)
        }
    }

    override fun createNode(
        key: K,
        value: V,
    ): RedBlackTreeNode<K, V> {
        return RedBlackTreeNode(key, value, null)
    }
}
