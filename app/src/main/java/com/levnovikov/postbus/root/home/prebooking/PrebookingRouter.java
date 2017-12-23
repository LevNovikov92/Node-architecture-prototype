package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraBuilder;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorRouter;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetBuilder;
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
        poiSelectorBuilder.removeView();
        poiWidgetBuilder.removeView();
        detachAll();

        attachRouter(carTypeSelectorBuilder.build());
    }

    void startBookingExtra() {
        attachRouter(bookingExtraBuilder.build());
    }

    void startPoiChoice() {
        attachRouter(poiSelectorBuilder.build());
    }

    void hidePoiChoice() {
        poiSelectorBuilder.removeView();
        detachRouter(PoiSelectorRouter.class);
    }


    @Override
    protected void detach() {
        poiWidgetBuilder.removeView();
        poiSelectorBuilder.removeView();
        carTypeSelectorBuilder.removeView();
        bookingExtraBuilder.removeView();
        detachAll();
    }
}
