package bst.nodes

abstract class AbstractBSTNodeWithParent<K : Comparable<K>, V, R : AbstractBSTNodeWithParent<K, V, R>>(
    key: K, value: V, val parent: R?
) : AbstractBSTNode<K, V, R>(key, value)
