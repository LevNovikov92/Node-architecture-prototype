package com.levnovikov.postbus.root.home.prebooking;

import android.os.Parcelable;

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraBuilder;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorRouter;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetBuilder;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.state.NodeState;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

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

    public NodeState getNodeState(@Nullable Parcelable stateData) {
        final NodeState nodeState = NodeState.create(this.getClass(), stateData);
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
            attachRouter(poiWidgetBuilder.build());
        }
        if (state.contains(poiSelectorBuilder.getClass())) {
            attachRouter(poiSelectorBuilder.build());
        }
        if (state.contains(carTypeSelectorBuilder.getClass())) {
            attachRouter(carTypeSelectorBuilder.build());
        }
        if (state.contains(bookingExtraBuilder.getClass())) {
            attachRouter(bookingExtraBuilder.build());
        }
    }
}
