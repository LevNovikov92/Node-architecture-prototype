package com.levnovikov.postbus.root.home.prebooking.poi_selector;

import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.PoiSelectorScope;
import com.levnovikov.system_base.state.NodeState;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@PoiSelectorScope
public class PoiSelectorRouter extends Router {

    @Inject
    public PoiSelectorRouter() {

    }

    @Override
    protected void destroyNode() {

    }

    @Override
    public NodeState getNodeState() {
        return NodeState.create(this.getClass(), null);
    }

    @Override
    public void setState(NodeState state) {

    }
}
