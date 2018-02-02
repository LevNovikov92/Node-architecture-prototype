package com.levnovikov.postbus.root.di

import com.levnovikov.core_profile.di.ProfileModule
import com.levnovikov.postbus.Application
import com.levnovikov.postbus.root.home.di.HomeComponentBuilder
import com.levnovikov.system_base.base_di.ComponentBuilder
import dagger.Component

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

@RootScope
@Component(modules = arrayOf(AppModule::class, ProfileModule::class, RootModule::class, HomeComponentBuilder::class))
interface AppComponent {

    fun subComponentBuilders(): Map<Class<out ComponentBuilder>, ComponentBuilder>
    fun inject(app: Application)
}
