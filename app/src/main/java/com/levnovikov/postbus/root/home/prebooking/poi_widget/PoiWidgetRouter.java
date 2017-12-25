package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.PoiWidgetScope;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

@PoiWidgetScope
public class PoiWidgetRouter extends Router {

    @Inject
    public PoiWidgetRouter() {

    }

    @Override
    protected void destroyNode() {

    }
}
