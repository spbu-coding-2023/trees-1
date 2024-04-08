package bst.nodes

class BSTNode<K : Comparable<K>, V : Any>(key: K, value: V) : AbstractBSTNode<K, V, BSTNode<K, V>>(key, value)
