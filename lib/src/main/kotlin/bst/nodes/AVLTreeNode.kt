package bst.nodes

class AVLTreeNode<K : Comparable<K>, V> (
    key: K,
    value: V,
    parent: AVLTreeNode<K, V>?,
    var height: Int
) : AbstractBSTNodeWithParent<K, V, AVLTreeNode<K, V>>(key, value, parent) {
    fun getBalanceFactor(): Int {
        return (this.right?.height ?: 0) - (this.left?.height ?: 0)
    }
}
