package com.levnovikov.postbus.root.home.prebooking.poi_selector

import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.PoiSelectorScope
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@PoiSelectorScope
class PoiSelectorRouter @Inject
constructor() : Router() {

    override val holders: Set<NodeHolder<*>> = setOf()

    override fun setState(state: NodeState) {

    }
}
