package com.levnovikov.feature_promo.promo_list

import com.levnovikov.feature_promo.promo_list.di.PromoListScope
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

@PromoListScope
class PromoListRouter @Inject constructor() : Router() {

    override val holders: Set<NodeHolder<*>> = emptySet()

    override fun destroyNode() = Unit

    override fun setState(state: NodeState) = Unit
}
