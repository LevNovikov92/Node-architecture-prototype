package com.levnovikov.postbus.root.home.prebooking.car_type_selector;

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.CarTypeListBuilder;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorScope;
import com.levnovikov.system_base.state.NodeState;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@CarTypeSelectorScope
public class CarTypeSelectorRouter extends Router {

    private CarTypeListBuilder carTypeListBuilder;

    @Inject
    CarTypeSelectorRouter(CarTypeListBuilder carTypeListBuilder) {
        this.carTypeListBuilder = carTypeListBuilder;
    }

    @Override
    protected void destroyNode() {
        carTypeListBuilder.destroy();
        detachChildren();
    }

    public void attachTypeList() {
        attachRouter(carTypeListBuilder.build());
    }


    @Override
    public NodeState getNodeState() {
        final NodeState nodeState = NodeState.create(this.getClass(), null);
        if (carTypeListBuilder.isActive())
            nodeState.activeNodes().add(carTypeListBuilder.getClass().getSimpleName());
        return nodeState;
    }

    @Override
    public void setState(NodeState state) {
        if (state.activeNodes().contains(carTypeListBuilder.getClass().getSimpleName())) {
            attachRouter(carTypeListBuilder.build());
        }
    }
}
