package bst

import bst.balancers.AbstractBSTBalancer
import bst.balancers.RedBlackTreeBalancer
import bst.nodes.RedBlackTreeNode

class RedBlackTree<K : Comparable<K>, V> : RegularAbstractBSTWithBalancer<K, V, RedBlackTreeNode<K, V>>() {
    override var balancer: AbstractBSTBalancer<K, V, RedBlackTreeNode<K, V>> = RedBlackTreeBalancer()

    override fun setNode( // NOT FUNCTIONAL AND UNUSED, DOES NOT BALANCE THE TREE. PLACEHOLDER BECAUSE OF INHERITANCE
        node: RedBlackTreeNode<K, V>,
        newNode: RedBlackTreeNode<K, V>,
    ) {
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

    override fun setNodeRight( // NOT FUNCTIONAL AND UNUSED, DOES NOT BALANCE THE TREE. PLACEHOLDER BECAUSE OF INHERITANCE
        nodeParent: RedBlackTreeNode<K, V>,
        nodeChild: RedBlackTreeNode<K, V>?,
    ) {
        nodeParent.right = nodeChild?.right
    }

    override fun setNodeLeft( // NOT FUNCTIONAL AND UNUSED, DOES NOT BALANCE THE TREE. PLACEHOLDER BECAUSE OF INHERITANCE
        nodeParent: RedBlackTreeNode<K, V>,
        nodeChild: RedBlackTreeNode<K, V>?,
    ) {
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

    private fun replaceNodeAndChild(
        node: RedBlackTreeNode<K, V>,
        child: RedBlackTreeNode<K, V>?,
    ) {
        child?.parent = node.parent
        if (node.parent == null) {
            root = child
        } else {
            if (node == node.parent?.left) {
                node.parent?.left = child
            } else {
                node.parent?.right = child
            }
        }
    }

    private fun findNode(key: K): RedBlackTreeNode<K, V>? {
        val treeRoot = root ?: return null
        return findNodeRec(treeRoot, key)
    }

    private fun findNodeRec(
        current: RedBlackTreeNode<K, V>?,
        key: K,
    ): RedBlackTreeNode<K, V>? {
        if (current == null) return null
        return when (current.key.compareTo(key)) {
            0 -> current
            1 -> findNodeRec(current.left, key)
            else -> findNodeRec(current.right, key)
        }
    }

    override fun remove(key: K): V? {
        val nodeToRemove = findNode(key) ?: return null
        if (nodeToRemove.left != null && nodeToRemove.right != null) {
            return super.remove(key)
        }
        val child = if (nodeToRemove.left != null) nodeToRemove.left else nodeToRemove.right
        replaceNodeAndChild(nodeToRemove, child)
        if (nodeToRemove.isBlack()) {
            if (child?.isRed() == true) {
                child.setBlack()
            } else {
                super.balance(balancer::remover, root)
            }
        }
        return nodeToRemove.value
    }

    override fun createNode(
        key: K,
        value: V,
    ): RedBlackTreeNode<K, V> {
        return RedBlackTreeNode(key, value, null)
    }
}
