package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.di.ExtraScope
import com.levnovikov.system_base.LeafRouter
import com.levnovikov.system_base.NodeHolder
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 3/2/18.
 */

@ExtraScope
class ExtraRouter @Inject constructor() : LeafRouter() {
    override val holders: Set<NodeHolder<*>>
        get() = emptySet()
}