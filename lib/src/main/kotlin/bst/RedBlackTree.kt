package bst

import bst.balancers.AbstractBSTBalancer
import bst.balancers.RedBlackTreeBalancer
import bst.nodes.RedBlackTreeNode

class RedBlackTree<K : Comparable<K>, V> : RegularAbstractBSTWithBalancer<K, V, RedBlackTreeNode<K, V>>() {
    override var balancer: AbstractBSTBalancer<K, V, RedBlackTreeNode<K, V>> = RedBlackTreeBalancer()

    override fun setNode(
        node: RedBlackTreeNode<K, V>,
        newNode: RedBlackTreeNode<K, V>,
    ) { // NOT FUNCTIONAL AND UNUSED, DOES NOT BALANCE THE TREE. PLACEHOLDER BECAUSE OF INHERITANCE
        node.key = newNode.key
        node.value = newNode.value
        node.parent = newNode.parent
        node.left = newNode.left
        node.right = newNode.right
        if (newNode.isRed()) {
            node.setRed()
        } else {
            node.setBlack()
        }
    }

    override fun setNodeRight(
        nodeParent: RedBlackTreeNode<K, V>,
        nodeChild: RedBlackTreeNode<K, V>?,
    ) { // NOT FUNCTIONAL AND UNUSED, DOES NOT BALANCE THE TREE. PLACEHOLDER BECAUSE OF INHERITANCE
        nodeParent.right = nodeChild?.right
    }

    override fun setNodeLeft(
        nodeParent: RedBlackTreeNode<K, V>,
        nodeChild: RedBlackTreeNode<K, V>?,
    ) { // NOT FUNCTIONAL AND UNUSED, DOES NOT BALANCE THE TREE. PLACEHOLDER BECAUSE OF INHERITANCE
        nodeParent.left = nodeChild?.left
    }

    override fun insert(
        key: K,
        value: V,
    ): RedBlackTreeNode<K, V> {
        val newNode = super.insert(key, value)
        super.balance(balancer::inserter, newNode)
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
                nodeToRemove.left?.parent = null
                nodeToRemove.right?.parent = null
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
