package com.levnovikov.postbus.root.home.prebooking.poi_selector;

import android.os.Parcelable;

import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.PoiSelectorScope;
import com.levnovikov.system_base.state.NodeState;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

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
    public NodeState getNodeState(@Nullable Parcelable stateData) {
        return NodeState.create(this.getClass(), null);
    }

    @Override
    public void setState(NodeState state) {

    }
}
