package com.levnovikov.postbus.root.home.prebooking

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraNodeHolder
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorNodeHolder
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorNodeHolder
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetNodeHolder
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
        private val poiWidgetBuilder: PoiWidgetNodeHolder,
        private val poiSelectorBuilder: PoiSelectorNodeHolder,
        private val carTypeSelectorBuilder: CarTypeSelectorNodeHolder,
        private val bookingExtraBuilder: BookingExtraNodeHolder) : Router() {

    fun showPoiWidget() {
        attachNode(poiWidgetBuilder)
    }

    fun startServiceType() {
        detachNode(poiSelectorBuilder)
        detachNode(poiWidgetBuilder)

        attachNode(carTypeSelectorBuilder)
    }

    fun startBookingExtra() {
        attachNode(bookingExtraBuilder)
    }

    fun startPoiChoice() {
        attachNode(poiSelectorBuilder)
    }

    fun hidePoiChoice() {
        detachNode(poiSelectorBuilder)
    }

    override fun destroyNode() {
        detachNode(poiWidgetBuilder)
        detachNode(poiSelectorBuilder)
        detachNode(carTypeSelectorBuilder)
        detachNode(bookingExtraBuilder)
        detachChildren()
    }

    override fun getNodeState(nodeState: NodeState): NodeState {
        if (poiWidgetBuilder.isActive())
            nodeState.addNodeBuilder(poiWidgetBuilder.javaClass)
        if (poiSelectorBuilder.isActive())
            nodeState.addNodeBuilder(poiSelectorBuilder.javaClass)
        if (carTypeSelectorBuilder.isActive())
            nodeState.addNodeBuilder(carTypeSelectorBuilder.javaClass)
        if (bookingExtraBuilder.isActive())
            nodeState.addNodeBuilder(bookingExtraBuilder.javaClass)
        return nodeState
    }

    override fun setState(state: NodeState) {
        if (state.contains(poiWidgetBuilder.javaClass)) {
            attachNode(poiWidgetBuilder)
        }
        if (state.contains(poiSelectorBuilder.javaClass)) {
            attachNode(poiSelectorBuilder)
        }
        if (state.contains(carTypeSelectorBuilder.javaClass)) {
            attachNode(carTypeSelectorBuilder)
        }
        if (state.contains(bookingExtraBuilder.javaClass)) {
            attachNode(bookingExtraBuilder)
        }
    }
}
