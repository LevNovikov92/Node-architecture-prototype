package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorBuilder;
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

    @Inject
    public  PrebookingRouter(
            PoiWidgetBuilder poiWidgetBuilder,
            PoiSelectorBuilder poiSelectorBuilder) {
        this.poiWidgetBuilder = poiWidgetBuilder;
        this.poiSelectorBuilder = poiSelectorBuilder;
    }

    public void showPoiWidget() {
        attachRouter(poiWidgetBuilder.build());
    }

    public void startServiceType() {

    }

    public void startPoiChoice() {
        attachRouter(poiSelectorBuilder.build());
    }
}
