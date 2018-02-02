package com.levnovikov.postbus.root.home.allocating.di

import com.levnovikov.postbus.root.home.allocating.AllocatingNodeHolder
import com.levnovikov.postbus.root.home.allocating.AllocatingRouter
import com.levnovikov.postbus.root.home.allocating.AllocatingView
import com.levnovikov.postbus.root.home.di.HomeComponent

import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@AllocatingScope
@Component(dependencies = [(HomeComponent::class)], modules = [(AllocatingComponent.AllocatingModule::class)])
interface AllocatingComponent {

    fun inject(view: AllocatingView)

    fun router(): AllocatingRouter

    fun inject(allocatingBuilder: AllocatingNodeHolder)

    @Module
    class AllocatingModule(private val view: AllocatingView) {

        @AllocatingScope
        @Provides
        internal fun provideView(): AllocatingView {
            return view
        }
    }
}
