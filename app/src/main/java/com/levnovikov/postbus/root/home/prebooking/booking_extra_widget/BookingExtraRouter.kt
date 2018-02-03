package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget

import com.levnovikov.feature_promo.promo_list.PromoListNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraScope
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@BookingExtraScope
class BookingExtraRouter @Inject
constructor(private val promoListBuilder: PromoListNodeHolder) : Router() {

    override fun destroyNode() {
        detachNode(promoListBuilder)
    }

    override fun getNodeState(nodeState: NodeState): NodeState {
        if (promoListBuilder.isActive()) {
            nodeState.addNodeBuilder(promoListBuilder.javaClass) //TODO refactor it
        }
        return nodeState
    }

    override fun setState(state: NodeState) {
        if (state.contains(PromoListNodeHolder::class.java)) {
            attachNode(promoListBuilder)
        }
    }

    fun detachPromoList() {
        detachNode(promoListBuilder)
    }

    fun attachPromoList() {
        attachNode(promoListBuilder)
    }
}
