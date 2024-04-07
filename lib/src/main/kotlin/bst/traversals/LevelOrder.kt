package bst.traversals

import bst.nodes.AbstractBSTNode
import java.util.ArrayDeque

class LevelOrder<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> : BSTTraversal<K, V, R> {
    override fun <T> traverse(
        node: R?,
        extractionFunction: (R) -> T,
    ): List<T> {
        if (node == null) return listOf()
        return levelOrderTraversal(node, extractionFunction, mutableListOf())
    }

    private fun <T> levelOrderTraversal(
        node: R,
        extractionFunction: (R) -> T,
        result: MutableList<T>,
    ): MutableList<T> {
        val queue = ArrayDeque<R>()
        queue.add(node)
        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()
            result.add(extractionFunction(currentNode))
            if (currentNode.left != null) {
                queue.add(currentNode.left!!)
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right!!)
            }
        }
        return result
    }
}
