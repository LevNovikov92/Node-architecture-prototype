package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.di

import android.view.LayoutInflater
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsRouter
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsView
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.SubOptionsNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.di.SubOptionsDependencies
import com.levnovikov.system_base.base_di.ActivityComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

/**
 * Created by lev.novikov
 * Date: 1/3/18.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OptionsScope

interface OptionsDependencies : ActivityComponent {
    fun inflater(): LayoutInflater
}

@OptionsScope
@Component(dependencies = [OptionsDependencies::class], modules = [OptionsComponent.OptionsModule::class])
interface OptionsComponent : SubOptionsDependencies, ActivityComponent {

    @Module
    class OptionsModule {
        @Provides
        fun subOptionsNodeHolder(view: OptionsView, layoutInflater: LayoutInflater, component: OptionsComponent): SubOptionsNodeHolder =
                SubOptionsNodeHolder(view, layoutInflater, component)
    }

    @Component.Builder
    interface Builder {
        fun build(): OptionsComponent
        fun dependencies(dependencies: OptionsDependencies): Builder
        @BindsInstance
        fun view(view: OptionsView): Builder
    }

    fun inject(view: OptionsView)
    fun inject(view: OptionsNodeHolder)
    fun router(): OptionsRouter
    fun dependencies(): OptionsDependencies
}