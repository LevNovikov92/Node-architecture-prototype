package com.levnovikov.postbus.root.home

import com.levnovikov.feature_map.MapNodeHolder
import com.levnovikov.postbus.root.home.allocating.AllocatingNodeHolder
import com.levnovikov.postbus.root.home.di.HomeScope
import com.levnovikov.postbus.root.home.prebooking.PrebookingNodeHolder
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
class HomeRouter @Inject
constructor(
        private val prebookingHolder: PrebookingNodeHolder,
        private val allocatingHolder: AllocatingNodeHolder,
        private val mapHolder: MapNodeHolder) : Router() {

    fun startPrebooking() {
        detachNode(allocatingHolder)
        attachNode(prebookingHolder)
    }

    fun startAllocating() {
        detachNode(prebookingHolder)
        attachNode(allocatingHolder)
    }

    fun loadMap() {
        attachNode(mapHolder)
    }

    fun startTracking() {
        detachNode(allocatingHolder)
        detachNode(prebookingHolder)
    }

    override fun destroyNode() {
        //root node will be destroyed with activity
    }

    override val holders: Set<NodeHolder<*>> = setOf(prebookingHolder, allocatingHolder, mapHolder)

    /**
     * Order is important
     */
    override fun setState(state: NodeState) {
        if (state.contains(mapHolder.javaClass)) {
            attachNode(mapHolder)
        }
        if (state.contains(prebookingHolder.javaClass)) {
            attachNode(prebookingHolder)
        }
        if (state.contains(allocatingHolder.javaClass)) {
            attachNode(allocatingHolder)
        }
    }
}
