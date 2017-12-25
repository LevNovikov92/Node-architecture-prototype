package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list;

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.CarTypeListScope;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

@CarTypeListScope
public class CarTypeListRouter extends Router {

    @Inject
    CarTypeListRouter() {}

    @Override
    protected void destroyNode() {
        //do nothing for leaf routers
    }
}
