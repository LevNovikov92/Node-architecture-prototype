package com.levnovikov.feature_car_animation

import com.levnovikov.feature_car_animation.di.CarAnimScope
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

@CarAnimScope
class CarAnimRouter @Inject
constructor() : Router() {

    override fun destroyNode() {

    }

    override fun getNodeState(nodeState: NodeState): NodeState {
        return nodeState
    }

    override fun setState(state: NodeState) {

    }
}
