package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.di

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraComponent
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.ExtraNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.ExtraRouter
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.ExtraView
import com.levnovikov.system_base.base_di.ActivityComponent
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Created by lev.novikov
 * Date: 3/2/18.
 */

@ExtraScope
@Component(dependencies = [BookingExtraComponent::class], modules = [ExtraComponent.ExtraModule::class])
interface ExtraComponent : ActivityComponent {

    @Module
    class ExtraModule(private val view: ExtraView) {
        @Provides
        fun provideView() = view
    }

    fun router(): ExtraRouter
    fun inject(view: ExtraView)
    fun inject(view: ExtraNodeHolder)
}