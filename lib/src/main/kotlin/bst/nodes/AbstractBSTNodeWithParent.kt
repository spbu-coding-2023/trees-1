package bst.nodes

abstract class AbstractBSTNodeWithParent<K : Comparable<K>, V : Any, R : AbstractBSTNodeWithParent<K, V, R>>(
    key: K,
    value: V,
    internal var parent: R?,
) : AbstractBSTNode<K, V, R>(key, value) {
    fun getParent() = parent
}
