package bst.nodes

abstract class AbstractBSTNode<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>>(
    internal var key: K,
    internal var value: V,
) {
    internal var right: R? = null
    internal var left: R? = null

    fun getKey() = key

    fun getValue() = value

    fun getRight() = right

    fun getLeft() = left
}
