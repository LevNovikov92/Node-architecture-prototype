package com.example.feature_onboarding.signup;

import com.example.feature_onboarding.signup.di.SignUpScope;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.state.NodeState;

import javax.inject.Inject;

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
    public NodeState getNodeState(NodeState nodeState) {
        return nodeState;
    }

    @Override
    public void setState(NodeState state) {

    }
}
