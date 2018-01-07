package com.levnovikov.postbus.root.home.prebooking.car_type_selector;

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.CarTypeListBuilder;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorScope;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.state.NodeState;

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
        detachNode(carTypeListBuilder);
    }

    void attachTypeList() {
        attachNode(carTypeListBuilder);
    }

    void detachTypeList() {
        detachNode(carTypeListBuilder);
    }

    @Override
    public NodeState getNodeState(NodeState nodeState) {
        if (carTypeListBuilder.isActive())
            nodeState.addNodeBuilder(carTypeListBuilder.getClass());
        return nodeState;
    }

    @Override
    public void setState(NodeState state) {
        if (state.contains(carTypeListBuilder.getClass())) {
            attachNode(carTypeListBuilder);
        }
    }
}
