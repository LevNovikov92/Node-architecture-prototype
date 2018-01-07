package com.example.feature_onboarding.login;

import com.example.feature_onboarding.login.di.LoginScope;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.state.NodeState;

import javax.inject.Inject;

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
    public NodeState getNodeState(NodeState nodeState) {
        return nodeState;
    }

    @Override
    public void setState(NodeState state) {

    }
}
