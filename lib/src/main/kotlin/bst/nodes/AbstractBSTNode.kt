package bst.nodes

abstract class AbstractBSTNode<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>>(
    val key: K,
    val value: V
) {
    var right: R? = null
    var left: R? = null
}
