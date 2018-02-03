package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.CarTypeListScope
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

@CarTypeListScope
class CarTypeListRouter @Inject
internal constructor() : Router() {

    override fun destroyNode() {
        //do nothing for leaf routers
    }

    override fun getNodeState(nodeState: NodeState): NodeState {
        return nodeState
    }

    override fun setState(state: NodeState) {

    }
}
