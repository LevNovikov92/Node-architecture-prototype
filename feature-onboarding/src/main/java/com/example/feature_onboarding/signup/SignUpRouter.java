package com.example.feature_onboarding.signup;

import android.os.Parcelable;

import com.example.feature_onboarding.signup.di.SignUpScope;
import com.levnovikov.system_base.state.NodeState;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

@SignUpScope
public class SignUpRouter extends Router {

    @Inject
    SignUpRouter() {

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
