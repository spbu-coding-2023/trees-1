package bst

import bst.nodes.AbstractBSTNode
import bst.traversals.BSTTraversed

abstract class AbstractBST<K : Comparable<K>, V : Any, R : AbstractBSTNode<K, V, R>> : MutableMap<K, V> {
    internal abstract var root: R?

    val traversed = BSTTraversed { root }

    /**
     * @return finds value by key
     */
    internal abstract fun search(key: K): V?

    /**
     * replaces value if key already present in tree, otherwise adds new node to the tree
     *
     * @return inserted node by key-value
     */
    internal abstract fun insert(
        key: K,
        value: V,
    ): R

    override fun put(
        key: K,
        value: V,
    ): V? {
        val oldValue = search(key)
        insert(key, value)
        return oldValue
    }

    override fun putAll(from: Map<out K, V>) = from.forEach { put(it.key, it.value) }

    override fun isEmpty(): Boolean = root == null

    override fun containsKey(key: K): Boolean = search(key) != null

    override fun containsValue(value: V): Boolean = values.contains(value)

    override operator fun get(key: K): V? = search(key)

    override val keys: MutableSet<K>
        get() = traversed.inOrder { it.key }.toMutableSet()
    override val values: MutableCollection<V>
        get() = traversed.inOrder { it.value }.toMutableList()
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = traversed.inOrder { it }.toMutableSet()
    override val size: Int
        get() = traversed.inOrder { it }.size

    override fun clear() {
        root = null
    }
}
