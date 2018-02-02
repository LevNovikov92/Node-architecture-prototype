package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout

import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraComponent
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.DaggerBookingExtraComponent
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

class BookingExtraNodeHolder(inflater: LayoutInflater, parent: ViewGroup, private val parentComponent: PrebookingComponent) : ViewNodeHolder<BookingExtraView, BookingExtraRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.booking_extra_widget

    override fun build(): BookingExtraRouter {
        val view = buildView()
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.BOTTOM
        view.layoutParams = params

        val component = DaggerBookingExtraComponent
                .builder()
                .prebookingComponent(parentComponent)
                .bookingExtraModule(BookingExtraComponent.BookingExtraModule(view))
                .build()
        component.inject(view)
        component.inject(this)
        attachView()
        return component.router()
    }
}
