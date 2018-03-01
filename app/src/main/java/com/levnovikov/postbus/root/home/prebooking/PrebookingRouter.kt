package com.levnovikov.postbus.root.home.prebooking

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraNodeHolder
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorNodeHolder
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorNodeHolder
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetNodeHolder
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
class PrebookingRouter @Inject
internal constructor(
        private val poiWidgetHolder: PoiWidgetNodeHolder,
        private val poiSelectorHolder: PoiSelectorNodeHolder,
        private val carTypeSelectorHolder: CarTypeSelectorNodeHolder,
        private val bookingExtraHolder: BookingExtraNodeHolder) : Router() {

    override val holders: Set<NodeHolder<*>> = setOf(poiWidgetHolder, poiSelectorHolder, carTypeSelectorHolder, bookingExtraHolder)

    fun showPoiWidget() {
        attachNode(poiWidgetHolder)
    }

    fun startServiceType() {
        detachNode(poiSelectorHolder)
        detachNode(poiWidgetHolder)

        attachNode(carTypeSelectorHolder)
    }

    fun startBookingExtra() {
        attachNode(bookingExtraHolder)
    }

    fun startPoiChoice() {
        attachNode(poiSelectorHolder)
    }

    fun hidePoiChoice() {
        detachNode(poiSelectorHolder)
    }
}
