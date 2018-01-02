package com.example.feature_onboarding.login;

import android.os.Parcelable;

import com.example.feature_onboarding.login.di.LoginScope;
import com.levnovikov.system_base.state.NodeState;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

@LoginScope
public class LoginRouter extends Router {

    @Inject
    LoginRouter() {

    }

    @Override
    protected void destroyNode() {

    }

    @Override
    public NodeState getNodeState(@Nullable Parcelable stateData) {
        return NodeState.create(this.getClass(), null);
    }

    @Override
    public void setState(NodeState state) {

    }
}
