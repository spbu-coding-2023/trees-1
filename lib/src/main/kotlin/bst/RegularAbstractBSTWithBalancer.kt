package bst

import bst.balancers.AbstractBSTBalancer
import bst.nodes.AbstractBSTNodeWithParent

abstract class RegularAbstractBSTWithBalancer<K : Comparable<K>, V, R : AbstractBSTNodeWithParent<K, V, R>> : RegularAbstractBST<K, V, R>() {
    abstract var balancer: AbstractBSTBalancer<K, V, R>

    fun balance(balanceFunction: (R) -> Unit, node: R?) {
        val balanceNode = node ?: return
        balanceFunction(balanceNode)
    }
}
