package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options

import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState
import javax.inject.Inject

/**
 * Created by stepan.goncharov on 1/3/18.
 */
class SubOptionsRouter @Inject constructor(): Router() {
    override val holders: Set<NodeHolder<*>> = emptySet()

    override fun setState(state: NodeState) = Unit
}