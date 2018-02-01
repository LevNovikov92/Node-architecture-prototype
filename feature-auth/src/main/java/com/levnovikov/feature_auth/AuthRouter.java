package com.levnovikov.feature_auth;

import com.levnovikov.feature_auth.di.AuthScope;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.node_state.NodeState;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

@AuthScope
public class AuthRouter extends Router {

    @Inject
    public AuthRouter() {
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
