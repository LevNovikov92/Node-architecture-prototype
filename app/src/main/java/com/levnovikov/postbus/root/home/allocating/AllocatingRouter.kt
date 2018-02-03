package com.levnovikov.postbus.root.home.allocating

import com.levnovikov.postbus.root.home.allocating.di.AllocatingScope
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@AllocatingScope
class AllocatingRouter @Inject
internal constructor() : Router() {

    override fun destroyNode() {

    }

    override fun getNodeState(nodeState: NodeState): NodeState {
        return nodeState
    }

    override fun setState(state: NodeState) {

    }
}
