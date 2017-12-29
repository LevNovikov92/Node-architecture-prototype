package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraBuilder;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorRouter;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetBuilder;
import com.levnovikov.system_base.state.NodeState;
import com.levnovikov.system_base.Router;

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
        attachRouter(poiWidgetBuilder.build());
    }

    void startServiceType() {
        poiSelectorBuilder.destroy();
        poiWidgetBuilder.destroy();
        detachChildren();

        attachRouter(carTypeSelectorBuilder.build());
    }

    void startBookingExtra() {
        attachRouter(bookingExtraBuilder.build());
    }

    void startPoiChoice() {
        attachRouter(poiSelectorBuilder.build());
    }

    void hidePoiChoice() {
        poiSelectorBuilder.destroy();
        detachRouter(PoiSelectorRouter.class);
    }

    @Override
    protected void destroyNode() {
        poiWidgetBuilder.destroy();
        poiSelectorBuilder.destroy();
        carTypeSelectorBuilder.destroy();
        bookingExtraBuilder.destroy();
        detachChildren();
    }

    @Override
    public NodeState getNodeState() {
        final NodeState nodeState = NodeState.create(this.getClass(), null);
        if (poiWidgetBuilder.isActive())
            nodeState.activeNodes().add(poiWidgetBuilder.getClass().getSimpleName());
        if (poiSelectorBuilder.isActive())
            nodeState.activeNodes().add(poiSelectorBuilder.getClass().getSimpleName());
        if (carTypeSelectorBuilder.isActive())
            nodeState.activeNodes().add(carTypeSelectorBuilder.getClass().getSimpleName());
        if (bookingExtraBuilder.isActive())
            nodeState.activeNodes().add(bookingExtraBuilder.getClass().getSimpleName());
        return nodeState;
    }

    @Override
    public void setState(NodeState state) {
        if (state.activeNodes().contains(poiWidgetBuilder.getClass().getSimpleName())) {
            attachRouter(poiWidgetBuilder.build());
        }
        if (state.activeNodes().contains(poiSelectorBuilder.getClass().getSimpleName())) {
            attachRouter(poiSelectorBuilder.build());
        }
        if (state.activeNodes().contains(carTypeSelectorBuilder.getClass().getSimpleName())) {
            attachRouter(carTypeSelectorBuilder.build());
        }
        if (state.activeNodes().contains(bookingExtraBuilder.getClass().getSimpleName())) {
            attachRouter(bookingExtraBuilder.build());
        }
    }
}
