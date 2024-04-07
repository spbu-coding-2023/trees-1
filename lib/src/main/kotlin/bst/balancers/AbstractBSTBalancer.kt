package bst.balancers

import bst.nodes.AbstractBSTNodeWithParent

abstract class AbstractBSTBalancer<K : Comparable<K>, V, R : AbstractBSTNodeWithParent<K, V, R>> {
    // returns root of a balanced tree
    abstract fun inserter(node: R): R

    // returns root of a balanced tree
    abstract fun remover(node: R): R

    open fun rotateLeftHasLeftChild(node: R) {
        val isParentLinkRight = node.parent?.right == node
        val switchNode = node.right
        switchNode!!.parent = node.parent
        node.parent = switchNode
        if (isParentLinkRight) {
            switchNode.parent!!.right = switchNode
        } else {
            switchNode.parent?.left = switchNode
        }
        node.right = switchNode.left
        switchNode.left?.parent = node
        switchNode.left = node
    }

    open fun rotateRightHasRightChild(node: R) {
        val isParentLinkRight = node.parent?.right == node
        val switchNode = node.left
        switchNode!!.parent = node.parent
        node.parent = switchNode
        if (isParentLinkRight) {
            switchNode.parent!!.right = switchNode
        } else {
            switchNode.parent?.left = switchNode
        }
        node.left = switchNode.right
        switchNode.right?.parent = node
        switchNode.right = node
    }
}
