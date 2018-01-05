package com.levnovikov.feature_promo.promo_list;

import android.os.Parcelable;

import com.levnovikov.feature_promo.promo_list.di.PromoListScope;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.state.NodeState;

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
    public NodeState getNodeState(Parcelable stateData) {
        return NodeState.create(this.getClass(), null); //TODO move default state to base entity
    }

    @Override
    public void setState(NodeState state) {

    }
}
