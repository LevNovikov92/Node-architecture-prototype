package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraBuilder;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetBuilder;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.state.NodeState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
public class PrebookingRouter extends Router {

    private final PoiWidgetBuilder poiWidgetBuilder;
    private final PoiSelectorBuilder poiSelectorBuilder;
    private final CarTypeSelectorBuilder carTypeSelectorBuilder;
    private BookingExtraBuilder bookingExtraBuilder;

    @Inject
    PrebookingRouter(
            PoiWidgetBuilder poiWidgetBuilder,
            PoiSelectorBuilder poiSelectorBuilder,
            CarTypeSelectorBuilder carTypeSelectorBuilder,
            BookingExtraBuilder bookingExtraBuilder) {
        this.poiWidgetBuilder = poiWidgetBuilder;
        this.poiSelectorBuilder = poiSelectorBuilder;
        this.carTypeSelectorBuilder = carTypeSelectorBuilder;
        this.bookingExtraBuilder = bookingExtraBuilder;
    }

    void showPoiWidget() {
        attachNode(poiWidgetBuilder);
    }

    void startServiceType() {
        detachNode(poiSelectorBuilder);
        detachNode(poiWidgetBuilder);

        attachNode(carTypeSelectorBuilder);
    }

    void startBookingExtra() {
        attachNode(bookingExtraBuilder);
    }

    void startPoiChoice() {
        attachNode(poiSelectorBuilder);
    }

    void hidePoiChoice() {
        detachNode(poiSelectorBuilder);
    }

    @Override
    protected void destroyNode() {
        detachNode(poiWidgetBuilder);
        detachNode(poiSelectorBuilder);
        detachNode(carTypeSelectorBuilder);
        detachNode(bookingExtraBuilder);
        detachChildren();
    }

    public NodeState getNodeState(NodeState nodeState) {
        if (poiWidgetBuilder.isActive())
            nodeState.addNodeBuilder(poiWidgetBuilder.getClass());
        if (poiSelectorBuilder.isActive())
            nodeState.addNodeBuilder(poiSelectorBuilder.getClass());
        if (carTypeSelectorBuilder.isActive())
            nodeState.addNodeBuilder(carTypeSelectorBuilder.getClass());
        if (bookingExtraBuilder.isActive())
            nodeState.addNodeBuilder(bookingExtraBuilder.getClass());
        return nodeState;
    }

    @Override
    public void setState(NodeState state) {
        if (state.contains(poiWidgetBuilder.getClass())) {
            attachNode(poiWidgetBuilder);
        }
        if (state.contains(poiSelectorBuilder.getClass())) {
            attachNode(poiSelectorBuilder);
        }
        if (state.contains(carTypeSelectorBuilder.getClass())) {
            attachNode(carTypeSelectorBuilder);
        }
        if (state.contains(bookingExtraBuilder.getClass())) {
            attachNode(bookingExtraBuilder);
        }
    }
}
