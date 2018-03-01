package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.di

import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

/**
 * Created by stepan.goncharov on 1/3/18.
 */
class SubOptionsRouter: Router() {
    override val holders: Set<NodeHolder<*>>
        get() = emptySet()

    override fun destroyNode() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setState(state: NodeState) = Unit
}