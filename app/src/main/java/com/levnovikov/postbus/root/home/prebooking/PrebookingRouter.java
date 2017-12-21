package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetBuilder;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
public class PrebookingRouter extends Router {

    private PoiWidgetBuilder poiWidgetBuilder;

    @Inject
    public  PrebookingRouter(PoiWidgetBuilder poiWidgetBuilder) {
        this.poiWidgetBuilder = poiWidgetBuilder;
    }

    public void startPoiChoice() {
        attachRouter(poiWidgetBuilder.build());
    }
}
