package com.levnovikov.postbus.root.home.di

import android.view.LayoutInflater

import com.levnovikov.feature_map.dependency.MapDependency
import com.levnovikov.feature_map.dependency.MapProvider
import com.levnovikov.feature_map.dependency.MapSetter
import com.levnovikov.postbus.root.home.HomeActivity
import com.levnovikov.postbus.root.home.HomeView
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor
import com.levnovikov.system_base.base_di.ActivityComponent
import com.levnovikov.system_base.base_di.ComponentBuilder

import dagger.Subcomponent

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
@Subcomponent(modules = [(HomeModule::class)])
interface HomeComponent : ActivityComponent, MapDependency {

    fun inject(homeActivity: HomeActivity)

    @Subcomponent.Builder
    interface Builder : ComponentBuilder {
        fun homeModule(module: HomeModule): HomeComponent.Builder
        fun build(): HomeComponent
    }

    fun inflater(): LayoutInflater
    fun homeView(): HomeView
    fun bookingListener(): BookingExtraInteractor.Listener
    override fun mapSetter(): MapSetter
    fun mapProvider(): MapProvider
}
