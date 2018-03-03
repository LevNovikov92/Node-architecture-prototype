package com.levnovikov.postbus

import android.content.Context
import android.support.multidex.MultiDex
import com.levnovikov.postbus.root.di.AppComponent
import com.levnovikov.postbus.root.di.AppModule
import com.levnovikov.postbus.root.di.DaggerAppComponent
import com.levnovikov.system_base.base_di.ComponentBuilder
import com.levnovikov.system_base.base_di.SubComponentProvider

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

class Application : android.app.Application(), SubComponentProvider {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <C : ComponentBuilder> provide(key: Class<C>): C =
        appComponent.subComponentBuilders()[key] as C

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
