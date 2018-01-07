package com.levnovikov.feature_map;

import com.levnovikov.feature_map.di.MapScope;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.state.NodeState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

@MapScope
public class MapRouter extends Router {

    @Inject
    public MapRouter() {
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
