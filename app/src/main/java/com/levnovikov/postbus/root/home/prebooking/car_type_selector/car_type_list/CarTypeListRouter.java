package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list;

import android.os.Parcelable;

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.CarTypeListScope;
import com.levnovikov.system_base.state.NodeState;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

@CarTypeListScope
public class CarTypeListRouter extends Router {

    @Inject
    CarTypeListRouter() {}

    @Override
    protected void destroyNode() {
        //do nothing for leaf routers
    }

    @Override
    public NodeState getNodeState(@Nullable Parcelable stateData) {
        return NodeState.create(this.getClass(), null);
    }

    @Override
    public void setState(NodeState state) {

    }
}
