package bst.balancers

import bst.nodes.AVLTreeNode
import kotlin.math.max

class AVLTreeBalancer<K : Comparable<K>, V> : AbstractBSTBalancer<K, V, AVLTreeNode<K, V>>() {
    private fun updateHeightAndBelow(node: AVLTreeNode<K, V>?): Int {
        if (node == null) {
            return 0
        }

        node.height = max(updateHeightAndBelow(node.right), updateHeightAndBelow(node.left)) + 1
        return node.height
    }

    private fun updateHeightAbove(node: AVLTreeNode<K, V>) {
        var current: AVLTreeNode<K, V>? = node
        while (true) {
            current = current!!.parent
            if (current == null) break
            current.height = max(current.left?.height ?: 0, current.right?.height ?: 0) + 1
        }
    }

    private fun updateHeight(node: AVLTreeNode<K, V>) {
        node.height = updateHeightAndBelow(node)
        updateHeightAbove(node)
    }

    override fun rotateRightHasRightChild(node: AVLTreeNode<K, V>) {
        super.rotateRightHasRightChild(node)
        updateHeight(node.parent!!)
    }

    override fun rotateLeftHasLeftChild(node: AVLTreeNode<K, V>) {
        super.rotateLeftHasLeftChild(node)
        updateHeight(node.parent!!)
    }

    override fun inserter(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {
        var current = node
        var bf = current.getBalanceFactor()
        while (bf != 0) {
            if (bf == 2) {
                if (current.right!!.getBalanceFactor() == 1) {
                    rotateLeftHasLeftChild(current)
                } else {
                    val rightNode = current.right
                    rotateRightHasRightChild(rightNode!!)
                    rotateLeftHasLeftChild(current)
                }
                break
            } else if (bf == -2) {
                if (current.left!!.getBalanceFactor() == -1) {
                    rotateRightHasRightChild(current)
                } else {
                    val leftNode = current.left
                    rotateLeftHasLeftChild(leftNode!!)
                    rotateRightHasRightChild(current)
                }
                break
            }

            current = current.parent ?: break
            bf = current.getBalanceFactor()
        }

        var findRoot = current
        while (findRoot.parent != null) {
            findRoot = findRoot.parent!!
        }
        return findRoot
    }

    override fun remover(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {
        var current = node
        var bf = current.getBalanceFactor()
        while (true) {
            if (bf == 2) {
                val rightNodeBf = current.right!!.getBalanceFactor()
                if (rightNodeBf == 1) {
                    rotateLeftHasLeftChild(current)
                } else if (rightNodeBf == 0) {
                    rotateLeftHasLeftChild(current)
                    break
                } else {
                    val rightNode = current.right
                    rotateRightHasRightChild(rightNode!!)
                    rotateLeftHasLeftChild(current)
                }
                current = current.parent!!
            } else if (bf == -2) {
                val leftNodeBf = current.left!!.getBalanceFactor()
                if (leftNodeBf == -1) {
                    rotateRightHasRightChild(current)
                } else if (leftNodeBf == 0) {
                    rotateRightHasRightChild(current)
                    break
                } else {
                    val leftNode = current.left
                    rotateLeftHasLeftChild(leftNode!!)
                    rotateRightHasRightChild(current)
                }
                current = current.parent!!
            }

            val nodeParentBf = current.parent?.getBalanceFactor() ?: 0
            if (nodeParentBf == -1 || (current.parent?.left == current && nodeParentBf == 1)) {
                break
            }
            current = current.parent ?: break
            bf = current.getBalanceFactor()
        }

        var findRoot = current
        while (findRoot.parent != null) {
            findRoot = findRoot.parent!!
        }
        return findRoot
    }
}
