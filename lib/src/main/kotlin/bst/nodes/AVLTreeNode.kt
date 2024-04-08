package bst.nodes

class AVLTreeNode<K : Comparable<K>, V : Any>(
    key: K,
    value: V,
    parent: AVLTreeNode<K, V>?,
    internal var height: Int,
) : AbstractBSTNodeWithParent<K, V, AVLTreeNode<K, V>>(key, value, parent) {
    fun getHeight() = height

    fun getBalanceFactor(): Int {
        return (this.right?.height ?: 0) - (this.left?.height ?: 0)
    }
}
