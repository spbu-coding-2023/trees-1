package bst.nodes

abstract class AbstractBSTNode<K : Comparable<K>, V : Any, R : AbstractBSTNode<K, V, R>>(
    initKey: K,
    initValue: V,
) : MutableMap.MutableEntry<K, V> {
    internal var right: R? = null
    internal var left: R? = null

    private var _key: K = initKey
    private var _value: V = initValue

    override val key: K
        get() = _key
    override val value: V
        get() = _value

    override fun setValue(newValue: V): V {
        val oldValue = _value
        _value = newValue
        return oldValue
    }

    internal fun setKey(newKey: K): K {
        val oldKey = _key
        _key = newKey
        return oldKey
    }

    fun getRight() = right

    fun getLeft() = left
}
