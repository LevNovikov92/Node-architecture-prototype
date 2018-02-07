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
        private val poiWidgetHolder: PoiWidgetNodeHolder,
        private val poiSelectorHolder: PoiSelectorNodeHolder,
        private val carTypeSelectorHolder: CarTypeSelectorNodeHolder,
        private val bookingExtraHolder: BookingExtraNodeHolder) : Router() {

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

    override fun destroyNode() {
        detachNode(poiWidgetHolder)
        detachNode(poiSelectorHolder)
        detachNode(carTypeSelectorHolder)
        detachNode(bookingExtraHolder)
        detachChildren()
    }

    /**
     * Method will be called before onSaveInstanceState. Should return state for saving.
     * Router know only about attached children and should add this info.
     */
    override fun getNodeState(nodeState: NodeState): NodeState {
        if (poiWidgetHolder.isActive())
            nodeState.addNodeBuilder(poiWidgetHolder.javaClass)
        if (poiSelectorHolder.isActive())
            nodeState.addNodeBuilder(poiSelectorHolder.javaClass)
        if (carTypeSelectorHolder.isActive())
            nodeState.addNodeBuilder(carTypeSelectorHolder.javaClass)
        if (bookingExtraHolder.isActive())
            nodeState.addNodeBuilder(bookingExtraHolder.javaClass)
        return nodeState
    }

    /**
     * After restoring router should restore state
     */
    override fun setState(state: NodeState) {
        if (state.contains(poiWidgetHolder.javaClass)) {
            attachNode(poiWidgetHolder)
        }
        if (state.contains(poiSelectorHolder.javaClass)) {
            attachNode(poiSelectorHolder)
        }
        if (state.contains(carTypeSelectorHolder.javaClass)) {
            attachNode(carTypeSelectorHolder)
        }
        if (state.contains(bookingExtraHolder.javaClass)) {
            attachNode(bookingExtraHolder)
        }
    }
}
