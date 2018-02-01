package com.levnovikov.feature_car_animation;

import com.levnovikov.feature_car_animation.di.CarAnimScope;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.node_state.NodeState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

@CarAnimScope
public class CarAnimRouter extends Router {

    @Inject
    public CarAnimRouter() { }

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
