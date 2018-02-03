package com.levnovikov.feature_map.di

import com.levnovikov.feature_map.MapRouter
import com.levnovikov.feature_map.MapView
import com.levnovikov.feature_map.dependency.MapDependency

import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

@MapScope
@Component(dependencies = [(MapDependency::class)], modules = [(MapComponent.MapModule::class)])
interface MapComponent {

    fun router(): MapRouter

    fun inject(view: MapView)

    @Module
    class MapModule(private val view: MapView) {

        @MapScope
        @Provides
        internal fun provideView(): MapView {
            return view
        }
    }
}
