package com.levnovikov.postbus.root.home.prebooking.car_type_selector;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorComponent;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.DaggerCarTypeSelectorComponent;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.system_base.ViewBuilder;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

public class CarTypeSelectorBuilder extends ViewBuilder<CarTypeSelectorView, CarTypeSelectorRouter> {

    private PrebookingComponent parentComponent;

    public CarTypeSelectorBuilder(
            LayoutInflater inflater,
            ViewGroup parent,
            PrebookingComponent parentComponent) {
        super(inflater, parent);
        this.parentComponent = parentComponent;
    }

    @Override
    public CarTypeSelectorRouter build() {
        final CarTypeSelectorView view = buildView();
        final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.BOTTOM;
        view.setLayoutParams(params);

        final CarTypeSelectorComponent component = DaggerCarTypeSelectorComponent.builder()
                .prebookingComponent(parentComponent)
                .carTypeModule(new CarTypeSelectorComponent.CarTypeModule(view))
                .build();
        component.inject(view);
        attachView();
        return component.router();
    }

    @Override
    public int getLayout() {
        return R.layout.car_type_selector;
    }
}
