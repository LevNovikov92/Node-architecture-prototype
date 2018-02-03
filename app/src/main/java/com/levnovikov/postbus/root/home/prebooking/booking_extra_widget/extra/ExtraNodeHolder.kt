package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra

import android.view.LayoutInflater
import android.view.ViewGroup
import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraComponent
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.di.DaggerExtraComponent
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.di.ExtraComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Created by lev.novikov
 * Date: 3/2/18.
 */
class ExtraNodeHolder(layoutInflater: LayoutInflater, parent: ViewGroup, private val dependency: BookingExtraComponent)
    : ViewNodeHolder<ExtraView, ExtraRouter>(layoutInflater, parent) {

    override fun build(): ExtraRouter {
        val view = buildView()
        val component = DaggerExtraComponent.builder()
                .bookingExtraComponent(dependency)
                .extraModule(ExtraComponent.ExtraModule(view))
                .build()
        component.inject(view)
        component.inject(this)
        attachView()
        return component.router()
    }

    override val layout: Int = R.layout.extra_view
}