package com.levnovikov.feature_auth

import android.view.LayoutInflater
import android.view.ViewGroup

import com.levnovikov.feature_auth.dependency.AuthDependency
import com.levnovikov.feature_auth.di.AuthComponent
import com.levnovikov.feature_auth.di.DaggerAuthComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

class AuthNodeHolder(inflater: LayoutInflater, parent: ViewGroup, private val authDependency: AuthDependency) : ViewNodeHolder<AuthView, AuthRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.auth_view

    override fun build(): AuthRouter {
        val view = buildView()
        val component = DaggerAuthComponent.builder()
                .authDependency(authDependency)
                .authModule(AuthComponent.AuthModule(view))
                .build()
        component.inject(this)
        component.inject(view)
        attachView()
        return component.router()
    }
}
