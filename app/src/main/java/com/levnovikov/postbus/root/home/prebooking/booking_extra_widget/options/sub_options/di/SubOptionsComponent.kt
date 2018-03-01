package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.di

import android.view.LayoutInflater
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.SubOptionsNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.SubOptionsRouter
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.SubOptionsView
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

/**
 * Created by stepan.goncharov on 1/3/18.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SubOptionsScope

interface SubOptionsDependencies {

}

@SubOptionsScope
@Component(dependencies = [SubOptionsDependencies::class], modules = [SubOptionsComponent.OptionsModule::class])
interface SubOptionsComponent {

    @Module
    class OptionsModule {

    }

    @Component.Builder
    interface Builder {
        fun build(): SubOptionsComponent
        fun dependencies(dependencies: SubOptionsDependencies): Builder
        @BindsInstance
        fun view(view: SubOptionsView): Builder
    }

    fun inject(view: SubOptionsView)
    fun inject(view: SubOptionsNodeHolder)
    fun router(): SubOptionsRouter
}