package bst

import bst.balancers.AVLTreeBalancer
import bst.balancers.AbstractBSTBalancer
import bst.nodes.AVLTreeNode
import kotlin.math.max

class AVLTree<K : Comparable<K>, V> : RegularAbstractBSTWithBalancer<K, V, AVLTreeNode<K, V>>() {
    override var balancer: AbstractBSTBalancer<K, V, AVLTreeNode<K, V>> = AVLTreeBalancer()

    override fun setNodeRight(
        nodeParent: AVLTreeNode<K, V>,
        nodeChild: AVLTreeNode<K, V>?,
    ) {
        when (nodeChild) {
            null -> {
                nodeParent.right = null
            }
            else -> {
                nodeChild.parent = nodeParent
                nodeParent.right = nodeChild

                var currentNode = nodeChild
                while (currentNode != root) {
                    currentNode = currentNode?.parent ?: break
                    currentNode.height = max(currentNode.right?.height ?: 0, currentNode.left?.height ?: 0) + 1
                }
            }
        }
    }

    override fun setNodeLeft(
        nodeParent: AVLTreeNode<K, V>,
        nodeChild: AVLTreeNode<K, V>?,
    ) {
        when (nodeChild) {
            null -> {
                nodeParent.left = null
            }
            else -> {
                nodeChild.parent = nodeParent
                nodeParent.left = nodeChild

                var currentNode = nodeChild
                while (currentNode != root) {
                    currentNode = currentNode?.parent ?: break
                    currentNode.height = max(currentNode.right?.height ?: 0, currentNode.left?.height ?: 0) + 1
                }
            }
        }
    }

    override fun search(key: K): V? {
        return super.search(key)
    }

    override fun insert(
        key: K,
        value: V,
    ): AVLTreeNode<K, V> {
        val newNode = super.insert(key, value)
        super.balance(balancer::inserter, newNode.parent)
        return newNode
    }

    private fun minInOrder(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {
        var current = node
        while (current.left != null) current = current.left!!
        return current
    }

    override fun remove(key: K): V? {
        var removeNode = findNode(key) ?: return null
        val removedValue = removeNode.value

        if (removeNode.left != null && removeNode.right != null) {
            val nextNode = minInOrder(removeNode.right!!)
            removeNode.key = nextNode.key
            removeNode.value = nextNode.value
            removeNode = nextNode
        }

        val replaceNode = if (removeNode.left != null) removeNode.left else removeNode.right

        if (replaceNode != null) {
            replaceNode.parent = removeNode.parent

            if (removeNode.parent == null) {
                root = replaceNode
                return removedValue
            } else if (removeNode == removeNode.parent?.left) {
                setNodeLeft(removeNode.parent!!, replaceNode)
                if (replaceNode.parent?.getBalanceFactor() == 1) {
                    removeNode.left = null
                    removeNode.right = null
                    removeNode.parent = null
                    return removedValue
                }
            } else {
                setNodeRight(removeNode.parent!!, replaceNode)
                if (replaceNode.parent?.getBalanceFactor() == -1) {
                    removeNode.left = null
                    removeNode.right = null
                    removeNode.parent = null
                    return removedValue
                }
            }

            removeNode.left = null
            removeNode.right = null
            removeNode.parent = null
            super.balance(balancer::remover, replaceNode.parent)
        } else if (removeNode.parent == null) {
            root = null
            return removedValue
        } else {
            val removeNodeParent = removeNode.parent!!

            if (removeNode == removeNodeParent.left) {
                setNodeLeft(removeNodeParent, null)
                removeNode.parent = null
                if (removeNodeParent.getBalanceFactor() == 1) return removedValue
            } else {
                setNodeRight(removeNodeParent, null)
                removeNode.parent = null
                if (removeNodeParent.getBalanceFactor() == -1) return removedValue
            }

            super.balance(balancer::remover, removeNodeParent)

        }
        return removedValue
    }

    override fun createNode(
        key: K,
        value: V,
    ): AVLTreeNode<K, V> {
        return AVLTreeNode(key, value, null, 1)
    }

    override fun setNode(
        node: AVLTreeNode<K, V>,
        newNode: AVLTreeNode<K, V>,
    ) {
        node.value = newNode.value
    }
}
