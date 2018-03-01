package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.di

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsRouter
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsView
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

/**
 * Created by lev.novikov
 * Date: 1/3/18.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OptionsScope

interface OptionsDependencies {

}

@OptionsScope
@Component(dependencies = [OptionsDependencies::class], modules = [OptionsComponent.OptionsModule::class])
interface OptionsComponent {

    @Module
    class OptionsModule {

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
}