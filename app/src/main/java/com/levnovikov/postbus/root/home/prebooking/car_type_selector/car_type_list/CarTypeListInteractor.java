package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list;

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.CarTypeListScope;
import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

@CarTypeListScope
public class CarTypeListInteractor implements Interactor  {

    @Inject
    public CarTypeListInteractor() {

    }

    @Override
    public void onGetActive() {

    }
}
