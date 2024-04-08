package bst.balancers

import bst.nodes.AbstractBSTNodeWithParent

abstract class AbstractBSTBalancer<K : Comparable<K>, V : Any, R : AbstractBSTNodeWithParent<K, V, R>> {
    /**
     * @return root of balanced tree
     */
    abstract fun inserter(node: R): R

    /**
     * @return root of balanced tree
     */
    abstract fun remover(node: R): R

    open fun rotateLeftHasRightChild(node: R) {
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

    open fun rotateRightHasLeftChild(node: R) {
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
