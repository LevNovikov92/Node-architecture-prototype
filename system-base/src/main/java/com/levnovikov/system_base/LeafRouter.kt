package com.levnovikov.system_base

import com.levnovikov.system_base.node_state.NodeState

/**
 * Created by lev.novikov
 * Date: 3/2/18.
 */
abstract class LeafRouter : Router() {

    override fun setState(state: NodeState) {
        //do nothing for leaf routers
    }
}