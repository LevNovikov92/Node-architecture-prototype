package com.levnovikov.postbus.root.home.prebooking.poi_widget

import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.PoiWidgetScope
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

@PoiWidgetScope
class PoiWidgetRouter @Inject
constructor() : Router() {

    override val holders: Set<NodeHolder<*>> = setOf()

    override fun destroyNode() {

    }

    override fun setState(state: NodeState) {

    }
}
