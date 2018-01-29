package com.levnovikov.feature_auth;

import com.levnovikov.feature_auth.di.AuthScope;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.node_state.ActivityState;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

@AuthScope
public class AuthInteractor extends Interactor<AuthRouter> {

    @Inject
    public AuthInteractor(AuthRouter router, ActivityState activityState) {
        super(router, activityState);
    }

    @Override
    public void onGetActive() {
        super.onGetActive();

    }
}
