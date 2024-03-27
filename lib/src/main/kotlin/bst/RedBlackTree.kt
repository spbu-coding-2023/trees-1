package bst

import bst.balancers.AbstractBSTBalancer
import bst.balancers.RedBlackTreeBalancer
import bst.nodes.RedBlackTreeNode

class RedBlackTree<K : Comparable<K>, V> : RegularAbstractBSTWithBalancer<K, V, RedBlackTreeNode<K, V>>() {
    override var balancer: AbstractBSTBalancer<K, V, RedBlackTreeNode<K, V>> = RedBlackTreeBalancer()

    override fun search(key: K): V? {
        return super.search(key)
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
        child: RedBlackTreeNode<K, V>,
    ) {
        child?.parent = node.parent
        if (node.parent == null) {
            root = child
        } else {
            if (node == node.parent.left) {
                node.parent.left = child
            } else {
                node.parent.right = child
            }
        }
    }

    override fun remove(key: K): V? {
        val nodeToRemove = search(key) ?: return null
        if (nodeToRemove.left != null && nodeToRemove.right != null) {
            return super.remove(key)
        }
        val child = if (nodeToRemove.left != null) nodeToRemove.left else nodeToRemove.right // the other one is null so we don't lose anything
        replaceNodeAndChild(nodeToRemove, child)
        if (nodeToRemove.isBlack()) {
            if (child?.isRed()) {
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
