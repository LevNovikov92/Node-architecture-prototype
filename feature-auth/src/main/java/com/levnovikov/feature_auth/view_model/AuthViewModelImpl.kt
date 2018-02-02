package com.levnovikov.feature_auth.view_model

import com.levnovikov.feature_auth.di.AuthScope

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

@AuthScope
class AuthViewModelImpl @Inject
internal constructor() : AuthActions, AuthViewModel {

    override fun onGetActive() {

    }
}
