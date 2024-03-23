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
        var current: AVLTreeNode<K,V>? = node
        while (current != null) {
            current = current.parent
            if (current == null) break
            current.height = max(current.left?.height ?: 0, current.right?.height ?: 0) + 1
        }
    }

    private fun updateHeight(node: AVLTreeNode<K, V>?) {
        if (node == null) {
            return
        }

        node.height = updateHeightAndBelow(node)
        updateHeightAbove(node)

    }

    override fun rotateRight(node: AVLTreeNode<K, V>) {
        super.rotateRight(node)
        updateHeight(node.parent)
    }

    override fun rotateLeft(node: AVLTreeNode<K, V>) {
        super.rotateLeft(node)
        updateHeight(node.parent)
    }

    override fun inserter(node: AVLTreeNode<K, V>) {
        var current = node
        var bf = current.getBalanceFactor()
        while(bf != 0) {
            if (bf == 2) {
                if (current.right?.getBalanceFactor() == 1) {
                    rotateLeft(current)
                } else {
                    val rightNode = current.right
                    when {
                        rightNode == null -> rotateLeft(current)
                        else -> {
                            rotateRight(rightNode)
                            rotateLeft(current)
                        }
                    }
                }
                break
            } else if (bf == -2) {
                if (current.left?.getBalanceFactor() == -1) {
                    rotateRight(current)
                } else {
                    val leftNode = current.left
                    when {
                        leftNode == null -> rotateRight(current)
                        else -> {
                            rotateLeft(leftNode)
                            rotateRight(current)
                        }
                    }
                }
                break
            }

            current = current.parent ?: break
            bf = current.getBalanceFactor()
        }
    }

    override fun remover(node: AVLTreeNode<K, V>) {
        // TODO
    }
}
