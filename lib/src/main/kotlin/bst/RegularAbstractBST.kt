package bst

import bst.nodes.AbstractBSTNode

abstract class RegularAbstractBST<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> : AbstractBST<K, V, R>() {
    override var root: R? = null

    private fun searchRec(
        node: R,
        key: K,
    ): V? {
        val compareKeys = node.key.compareTo(key)
        return when {
            compareKeys == 0 -> node.value
            compareKeys < 0 -> {
                val nodeRight = node.right
                when {
                    nodeRight == null -> return null
                    else -> return searchRec(nodeRight, key)
                }
            }
            else -> {
                val nodeLeft = node.left
                when {
                    nodeLeft == null -> null
                    else -> return searchRec(nodeLeft, key)
                }
            }
        }
    }

    override fun search(key: K): V? {
        val searchFrom = root
        return when {
            searchFrom == null -> null
            else -> searchRec(searchFrom, key)
        }
    }

    private fun insertRec(
        root: R,
        node: R,
    ) {
        if (root.key.compareTo(node.key) == 0) {
            setNode(root, node)
        } else if (root.key.compareTo(node.key) < 0) {
            val rootRight = root.right
            if (rootRight == null) {
                setNodeRight(root, node)
                return
            }
            insertRec(rootRight, node)
        } else {
            val rootLeft = root.left
            if (rootLeft == null) {
                setNodeLeft(root, node)
                return
            }
            insertRec(rootLeft, node)
        }
    }

    override fun insert(
        key: K,
        value: V,
    ): R {
        val newNode = createNode(key, value)
        val insertFrom = root
        when {
            insertFrom == null -> {
                root = newNode
            }
            else -> {
                insertRec(insertFrom, newNode)
            }
        }
        return newNode
    }

    override fun remove(key: K): V? {
        val removeValue = search(key) ?: return null
        root = removeRec(root!!, key)
        return removeValue
    }

    private fun removeRec(
        node: R?,
        key: K,
    ): R? {
        val compareValue = key.compareTo(node!!.key)
        when {
            compareValue < 0 -> node.left = removeRec(node.left, key)
            compareValue > 0 -> node.right = removeRec(node.right, key)
            else -> {
                if (node.left == null) return node.right
                if (node.right == null) return node.left

                val minInOrderNode = getMinInOrder(node.right!!)
                node.key = minInOrderNode.key
                node.value = minInOrderNode.value
                node.right = removeRec(node.right, minInOrderNode.key)
            }
        }
        return node
    }

    private fun getMinInOrder(node: R): R {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }

    protected fun findNode(key: K): R? {
        val treeRoot = root ?: return null
        return findNodeRec(treeRoot, key)
    }

    private fun findNodeRec(
        current: R?,
        key: K,
    ): R? {
        if (current == null) return null
        return when (current.key.compareTo(key)) {
            0 -> current
            1 -> findNodeRec(current.left, key)
            else -> findNodeRec(current.right, key)
        }
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
