package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget

import com.levnovikov.feature_promo.promo_list.PromoListNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraScope
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.ExtraNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsNodeHolder
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@BookingExtraScope
class BookingExtraRouter @Inject
constructor(
        private val promoListBuilder: PromoListNodeHolder,
        private val extraNodeHolder: ExtraNodeHolder,
        private val optionsNodeHolder: OptionsNodeHolder) : Router() {

    override val holders: Set<NodeHolder<*>> = setOf(promoListBuilder, extraNodeHolder, optionsNodeHolder)

    override fun destroyNode() {
        detachNode(promoListBuilder)
        hideOptions()
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

    fun attachExtra() {
        attachNode(extraNodeHolder)
    }

    fun detachExtra() {
        detachNode(extraNodeHolder)
    }

    fun showOptions() {
        attachNode(optionsNodeHolder)
    }

    fun hideOptions() {
        detachNode(optionsNodeHolder)
    }
}
