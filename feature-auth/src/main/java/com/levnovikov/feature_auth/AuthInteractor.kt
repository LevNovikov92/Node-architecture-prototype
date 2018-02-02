package com.levnovikov.feature_auth

import com.levnovikov.feature_auth.di.AuthScope
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.node_state.ActivityState

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

@AuthScope
class AuthInteractor @Inject
constructor(router: AuthRouter, activityState: ActivityState) : Interactor<AuthRouter>(router, activityState) {

    override fun onGetActive() {
        super.onGetActive()

    }
}
