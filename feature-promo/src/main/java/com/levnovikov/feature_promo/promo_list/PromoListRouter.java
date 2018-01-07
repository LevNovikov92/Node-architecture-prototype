package com.levnovikov.feature_promo.promo_list;

import com.levnovikov.feature_promo.promo_list.di.PromoListScope;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.node_state.NodeState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

@PromoListScope
public class PromoListRouter extends Router {

    @Inject
    public PromoListRouter() {
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
