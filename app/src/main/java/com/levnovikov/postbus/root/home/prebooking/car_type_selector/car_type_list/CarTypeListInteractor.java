package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list;

import android.os.Parcelable;

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.CarTypeListScope;
import com.levnovikov.system_base.BackStateInteractor;
import com.levnovikov.system_base.state.ActivityState;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

@CarTypeListScope
public class CarTypeListInteractor extends BackStateInteractor<CarTypeListRouter> {

    private TypeListListener listener;

    public interface TypeListListener {
        void onCancel();
    }

    @Inject
    public CarTypeListInteractor(CarTypeListRouter router, ActivityState activityState, TypeListListener listener) {
        super(router, activityState);
        this.listener = listener;
    }

    @Override
    public boolean onBackPressed() {
        listener.onCancel();
        return true;
    }

    @Override
    public Parcelable getStateData() {
        return null;
    }
}
