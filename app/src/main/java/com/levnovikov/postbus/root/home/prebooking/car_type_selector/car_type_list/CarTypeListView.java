package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

public class CarTypeListView extends ConstraintLayout {
    public CarTypeListView(Context context) {
        super(context);
    }

    public CarTypeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarTypeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Inject
    CarTypeListInteractor interactor;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        interactor.onGetActive();
    }
}
