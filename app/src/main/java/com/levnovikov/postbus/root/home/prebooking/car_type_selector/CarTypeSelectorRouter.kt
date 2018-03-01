package com.levnovikov.postbus.root.home.prebooking.car_type_selector

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.CarTypeListNodeHolder
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorScope
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@CarTypeSelectorScope
class CarTypeSelectorRouter @Inject
internal constructor(private val carTypeListBuilder: CarTypeListNodeHolder) : Router() {
    override val holders: Set<NodeHolder<*>> = setOf(carTypeListBuilder)

    internal fun attachTypeList() {
        attachNode(carTypeListBuilder)
    }

    internal fun detachTypeList() {
        detachNode(carTypeListBuilder)
    }

    override fun setState(state: NodeState) {
        if (state.contains(carTypeListBuilder.javaClass)) {
            attachNode(carTypeListBuilder)
        }
    }
}
