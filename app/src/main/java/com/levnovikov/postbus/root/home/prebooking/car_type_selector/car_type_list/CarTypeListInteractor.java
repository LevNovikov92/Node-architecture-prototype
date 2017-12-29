package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list;

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.CarTypeListScope;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.state.ActivityState;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

@CarTypeListScope
public class CarTypeListInteractor extends Interactor<CarTypeListRouter>  {

    @Inject
    public CarTypeListInteractor(CarTypeListRouter router, ActivityState activityState) {
        super(router, activityState);
    }
}
