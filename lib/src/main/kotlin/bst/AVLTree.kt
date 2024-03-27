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

    override fun remove(key: K): V? {
        val removedNode = super.remove(key)
        super.balance(balancer::remover, root)
        return removedNode
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
        node.key = newNode.key
        node.value = newNode.value
        node.right = newNode.right
        node.left = newNode.left
        node.height = newNode.height
        node.parent = newNode.parent
        super.balance(balancer::inserter, node)
    }
}
