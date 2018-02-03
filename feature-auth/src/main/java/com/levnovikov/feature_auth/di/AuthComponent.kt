package com.levnovikov.feature_auth.di

import com.levnovikov.feature_auth.AuthNodeHolder
import com.levnovikov.feature_auth.AuthRouter
import com.levnovikov.feature_auth.AuthView
import com.levnovikov.feature_auth.dependency.AuthDependency
import com.levnovikov.feature_auth.view_model.AuthActions
import com.levnovikov.feature_auth.view_model.AuthViewModel
import com.levnovikov.feature_auth.view_model.AuthViewModelImpl
import com.levnovikov.system_base.base_di.ActivityComponent

import dagger.Binds
import dagger.Component
import dagger.Module

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

@AuthScope
@Component(dependencies = [(AuthDependency::class)], modules = [(AuthComponent.AuthModule::class)])
interface AuthComponent : ActivityComponent {

    fun inject(view: AuthView)

    fun router(): AuthRouter

    @Module(includes = [(AuthModule.AuthBinders::class)])
    class AuthModule(private val view: AuthView) {

        @Module
        interface AuthBinders {
            @Binds
            fun bindViewModel(impl: AuthViewModelImpl): AuthViewModel

            @Binds
            fun bindViewModelActions(impl: AuthViewModelImpl): AuthActions
        }
    }

    fun inject(view: AuthNodeHolder)
}
