package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.CarTypeListComponent;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.DaggerCarTypeListComponent;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorComponent;
import com.levnovikov.system_base.ViewBuilder;

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

public class CarTypeListBuilder extends ViewBuilder<CarTypeListView, CarTypeListRouter> {

    private CarTypeSelectorComponent parentComponent;

    public CarTypeListBuilder(LayoutInflater inflater, ViewGroup parent, CarTypeSelectorComponent parentComponent) {
        super(inflater, parent);
        this.parentComponent = parentComponent;
    }

    @Override
    public CarTypeListRouter build() {
        final CarTypeListView view = buildView();
        final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.bottomMargin = getDp(view.getContext(), 280);
        view.setLayoutParams(params);

        final CarTypeListComponent component = DaggerCarTypeListComponent.builder()
                .carTypeSelectorComponent(parentComponent)
                .carTypeListModule(new CarTypeListComponent.CarTypeListModule(view))
                .build();
        component.inject(view);
        component.inject(this);
        attachView();
        return router;
    }

    //TODO move to utils
    private int getDp(Context context, int dps) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

    @Override
    public int getLayout() {
        return R.layout.car_type_list_view;
    }
}
