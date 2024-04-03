package bst

import bst.balancers.AbstractBSTBalancer
import bst.nodes.AbstractBSTNodeWithParent

abstract class RegularAbstractBSTWithBalancer<K : Comparable<K>, V, R : AbstractBSTNodeWithParent<K, V, R>> :
    RegularAbstractBST<K, V, R>() {
    abstract val balancer: AbstractBSTBalancer<K, V, R>

    protected fun balance(
        balanceFunction: (R) -> R,
        node: R?,
    ) {
        val balanceNode = node ?: return
        root = balanceFunction(balanceNode)
    }
}
