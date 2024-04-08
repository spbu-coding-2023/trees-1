package bst.nodes

class RedBlackTreeNode<K : Comparable<K>, V : Any>(
    key: K,
    value: V,
    parent: RedBlackTreeNode<K, V>?,
) : AbstractBSTNodeWithParent<K, V, RedBlackTreeNode<K, V>>(key, value, parent) {
    private var color = 0

    internal fun setRed() {
        color = 0
    }

    internal fun setBlack() {
        color = 1
    }

    fun isRed(): Boolean {
        return color == 0
    }

    fun isBlack(): Boolean {
        return color == 1
    }
}
