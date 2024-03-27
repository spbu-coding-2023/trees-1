package bst.traversals

import bst.nodes.AbstractBSTNode

class LevelOrder<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> : BSTTraversal<K, V, R> {
    override fun <T> traverse(node: R, extractionFunction: (R) -> T): List<T> {
        return levelOrderTraversal(node, extractionFunction, mutableListOf())
    }

    private fun <T> levelOrderTraversal(node: R, extractionFunction : (R) -> T, result: MutableList<T>): List<T> {
        if (node != null) {
            val queue = ArrayDeque<R>()
            queue.add(node)
            while (queue.isNotEmpty()) {
                val currentNode = queue.poll()
                result.add(extractionFunction(currentNode))
                if currentNode.left != null {
                    queue.add(currentNode.left)
                }
                if currentNode.right != null {
                    queue.add(currentNode.right)
                }
            }
        }
    }
}
