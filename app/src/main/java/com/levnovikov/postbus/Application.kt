package com.levnovikov.postbus

import com.levnovikov.postbus.root.RootInteractor
import com.levnovikov.postbus.root.di.AppComponent
import com.levnovikov.postbus.root.di.AppModule
import com.levnovikov.postbus.root.di.DaggerAppComponent
import com.levnovikov.system_base.base_di.ComponentBuilder
import com.levnovikov.system_base.base_di.SubComponentProvider

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

class Application : android.app.Application(), SubComponentProvider {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var rootInteractor: RootInteractor
//
//    @Inject
//    lateinit var subComponents: Map<Class<out ComponentBuilder>, ComponentBuilder>


    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)
    }

    override fun <C : ComponentBuilder> provide(key: Class<C>): C {
        appComponent.subComponentBuilders()?.let {
            return it[key] as C
        }
        throw Exception("Sub components not found")
    }
}
