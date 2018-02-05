package com.levnovikov.postbus.root.home

import com.levnovikov.feature_map.MapNodeHolder
import com.levnovikov.postbus.root.home.allocating.AllocatingNodeHolder
import com.levnovikov.postbus.root.home.di.HomeScope
import com.levnovikov.postbus.root.home.prebooking.PrebookingNodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope class HomeRouter @Inject
constructor(
        private val prebookingBuilder: PrebookingNodeHolder,
        private val allocatingBuilder: AllocatingNodeHolder,
        private val mapBuilder: MapNodeHolder) : Router() {

    fun startPrebooking() {
        detachNode(allocatingBuilder)
        attachNode(prebookingBuilder)
    }

    fun startAllocating() {
        detachNode(prebookingBuilder)
        attachNode(allocatingBuilder)
    }

    fun loadMap() {
        attachNode(mapBuilder)
    }

    fun startTracking() {
        detachNode(allocatingBuilder)
        detachNode(prebookingBuilder)
    }

    override fun destroyNode() {
        //root node will be destroyed with activity
    }

    override fun getNodeState(nodeState: NodeState): NodeState {
        if (prebookingBuilder.isActive())
            nodeState.addNodeBuilder(prebookingBuilder.javaClass)
        if (allocatingBuilder.isActive())
            nodeState.addNodeBuilder(allocatingBuilder.javaClass)
        if (mapBuilder.isActive())
            nodeState.addNodeBuilder(mapBuilder.javaClass)
        return nodeState
    }

    override fun setState(state: NodeState) {
        if (state.contains(prebookingBuilder.javaClass)) {
            attachNode(prebookingBuilder)
        }

        if (state.contains(allocatingBuilder.javaClass)) {
            attachNode(allocatingBuilder)
        }

        if (state.contains(mapBuilder.javaClass)) {
            attachNode(mapBuilder)
        }
    }
}
