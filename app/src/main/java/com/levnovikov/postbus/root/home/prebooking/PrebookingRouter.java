package com.levnovikov.postbus.root.home.prebooking;

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

    @Inject
    PrebookingRouter(
            PoiWidgetBuilder poiWidgetBuilder,
            PoiSelectorBuilder poiSelectorBuilder,
            CarTypeSelectorBuilder carTypeSelectorBuilder) {
        this.poiWidgetBuilder = poiWidgetBuilder;
        this.poiSelectorBuilder = poiSelectorBuilder;
        this.carTypeSelectorBuilder = carTypeSelectorBuilder;
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

    void startPoiChoice() {
        attachRouter(poiSelectorBuilder.build());
    }

    void hidePoiChoice() {
        poiSelectorBuilder.removeView();
        detachRouter(PoiSelectorRouter.class);
    }
}
