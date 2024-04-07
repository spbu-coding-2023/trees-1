package bst

import bst.nodes.AbstractBSTNode
import bst.traversals.BSTTraversed

abstract class AbstractBST<K : Comparable<K>, V, R : AbstractBSTNode<K, V, R>> {
    internal abstract var root: R?

    val traversed = BSTTraversed { root }

    abstract fun search(key: K): V?

    internal abstract fun insert(
        key: K,
        value: V,
    ): R

    abstract fun remove(key: K): V?

    val size: Int
        get() = traversed.inOrder { it }.size

    fun put(
        key: K,
        value: V,
    ) {
        insert(key, value)
    }

    fun isEmpty(): Boolean = root == null

    fun isNotEmpty(): Boolean = root != null

    fun containsKey(key: K): Boolean = search(key) != null

    fun containsValue(value: V): Boolean = traversed.inOrder { it.value }.contains(value)

    operator fun get(key: K): V? = search(key)

    fun getOrDefault(
        key: K,
        defaultValue: @UnsafeVariance V,
    ): V {
        val value = search(key) ?: return defaultValue
        return value
    }

    val keys: Set<K>
        get() = traversed.inOrder { it.key }.toSet()
    val values: Collection<V>
        get() = traversed.inOrder { it.value }.toSet()
    val entries: Set<Pair<K, V>>
        get() = traversed.inOrder { Pair(it.key, it.value) }.toSet()

    operator fun set(
        key: K,
        value: V,
    ): R = insert(key, value)

    fun putAll(from: List<Pair<K, V>>) = from.forEach { put(it.first, it.second) }

    fun clean() {
        root = null
    }
}
