package com.levnovikov.postbus.root.di

import com.levnovikov.core_profile.di.ProfileModule
import com.levnovikov.postbus.root.home.di.HomeComponentBuilder
import com.levnovikov.system_base.base_di.ComponentBuilder
import dagger.Component
import javax.inject.Singleton

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

@Singleton
@Component(modules = [(AppModule::class), (ProfileModule::class), (HomeComponentBuilder::class)])
interface AppComponent {

    fun subComponentBuilders(): Map<Class<out ComponentBuilder>, ComponentBuilder>
}
