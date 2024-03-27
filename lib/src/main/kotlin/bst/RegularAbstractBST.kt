package bst

import bst.nodes.AbstractBSTNode
import bst.traversals.BSTTraversal

abstract class RegularAbstractBST<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> : AbstractBST<K, V, R>() {
    override var root: R? = null

    override fun search(key: K): V? {
        return null // TODO
    }

    private fun insertRec(
        root: R,
        node: R,
    ) { // TODO: may not be the best solution, but it works
        if (root.key.compareTo(node.key) == 0) {
            setNode(root, node)
        } else if (root.key.compareTo(node.key) < 0) {
            if (root.right == null) {
                setNodeRight(root, node)
                return
            }
            insertRec(root.right!!, node)
        } else {
            if (root.left == null) {
                setNodeLeft(root, node)
                return
            }
            insertRec(root.left!!, node)
        }
    }

    override fun insert(
        key: K,
        value: V,
    ): R {
        // TODO: make better, it is just a placeholder so it can work
        val newNode = createNode(key, value)
        if (root == null) {
            root = newNode
            return newNode
        }
        insertRec(root!!, newNode)
        return newNode
    }

    override fun remove(key: K): V? {
        return null // TODO
    }

    fun <T> traverse(
        traverseMethod: BSTTraversal<K, V, R>,
        extractFunction: (R) -> T,
    ): List<T> {
        val traverseNode = root ?: return listOf()
        return traverseMethod.traverse(traverseNode, extractFunction)
    }

    protected abstract fun createNode(
        key: K,
        value: V,
    ): R

    protected abstract fun setNodeLeft(
        nodeParent: R,
        nodeChild: R?,
    )

    protected abstract fun setNodeRight(
        nodeParent: R,
        nodeChild: R?,
    )

    protected abstract fun setNode(
        node: R,
        newNode: R,
    )
}
