package com.levnovikov.postbus.root.home.prebooking.car_type_selector;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorComponent;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.DaggerCarTypeSelectorComponent;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.system_base.ViewNodeHolder;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

public class CarTypeSelectorNodeHolder extends ViewNodeHolder<CarTypeSelectorView, CarTypeSelectorRouter> {

    private PrebookingComponent parentComponent;

    public CarTypeSelectorNodeHolder(
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
        params.setMargins(0, 0, 0, getDp(view.getContext(), 180));
        view.setLayoutParams(params);

        final CarTypeSelectorComponent component = DaggerCarTypeSelectorComponent.builder()
                .prebookingComponent(parentComponent)
                .carTypeModule(new CarTypeSelectorComponent.CarTypeModule(view))
                .build();
        component.inject(view);
        component.inject(this);
        attachView();
        return getRouter();
    }

    //TODO move to utils
    private int getDp(Context context, int dps) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

    @Override
    public int getLayout() {
        return R.layout.car_type_selector;
    }
}
