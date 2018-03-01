package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.di.ExtraScope
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 3/2/18.
 */

@ExtraScope
class ExtraRouter @Inject constructor() : Router() {
    override val holders: Set<NodeHolder<*>>
        get() = emptySet()
}