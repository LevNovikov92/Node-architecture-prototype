package com.levnovikov.feature_map

import com.levnovikov.feature_map.di.MapScope
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

@MapScope
class MapRouter @Inject constructor() : Router() {

    override val holders: Set<NodeHolder<*>> = setOf()

}
