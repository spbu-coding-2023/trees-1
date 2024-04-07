package bst.traversals

import bst.nodes.AbstractBSTNode

class BSTTraversed<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>>(private val getRoot: () -> R?) {
    private val inOrderInstance = InOrder<K, V, R>()
    private val preOrderInstance = PreOrder<K, V, R>()
    private val postOrderInstance = PostOrder<K, V, R>()
    private val levelOrderInstance = LevelOrder<K, V, R>()

    fun <T> inOrder(extractionFunction: (R) -> T): List<T> = inOrderInstance.traverse(getRoot(), extractionFunction)

    fun <T> preOrder(extractionFunction: (R) -> T): List<T> = preOrderInstance.traverse(getRoot(), extractionFunction)

    fun <T> postOrder(extractionFunction: (R) -> T): List<T> = postOrderInstance.traverse(getRoot(), extractionFunction)

    fun <T> levelOrder(extractionFunction: (R) -> T): List<T> = levelOrderInstance.traverse(getRoot(), extractionFunction)
}
