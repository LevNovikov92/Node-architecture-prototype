package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.di.OptionsScope
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.SubOptionsNodeHolder
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 1/3/18.
 */

@OptionsScope
class OptionsRouter @Inject constructor(private val subOptionsNodeHolder: SubOptionsNodeHolder) : Router() {
    override val holders: Set<NodeHolder<*>> = setOf(subOptionsNodeHolder)

    fun showSubOptions() {
        attachNode(subOptionsNodeHolder)
    }
}