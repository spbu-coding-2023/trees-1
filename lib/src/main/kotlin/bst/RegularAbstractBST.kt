package bst

import bst.nodes.AbstractBSTNode
import bst.traversals.BSTTraversal

abstract class RegularAbstractBST<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> : AbstractBST<K, V, R>() {
    override var root: R? = null
    override fun search(key: K): V? {
        return null // TODO
    }

    override fun insert(key: K, value: V): R {
        return createNode(key, value) // TODO
    }

    override fun remove(key: K): V? {
        return null // TODO
    }

    fun <T> traverse(traverseMethod: BSTTraversal<K, V, R>, extractFunction: (R) -> T): List<T> {
        val traverseNode = root ?: return listOf()
        return traverseMethod.traverse(traverseNode, extractFunction)
    }

    protected abstract fun createNode(key: K, value: V): R
    protected abstract fun setNodeLeft(nodeParent: R, nodeChild: R?)
    protected abstract fun setNodeRight(nodeParent: R, nodeChild: R?)
}
