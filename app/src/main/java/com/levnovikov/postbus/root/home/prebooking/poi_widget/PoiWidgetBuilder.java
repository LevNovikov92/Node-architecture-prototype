package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.DaggerPoiWidgetComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.PoiWidgetComponent;
import com.levnovikov.system_base.ViewBuilder;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

public class PoiWidgetBuilder extends ViewBuilder<PoiWidgetView, PoiWidgetRouter> {

    private final PrebookingComponent parentComponent;

    @Inject
    PoiWidgetInteractor interactor;

    public PoiWidgetBuilder(LayoutInflater inflater, ViewGroup parent, PrebookingComponent parentComponent) {
        super(inflater, parent);
        this.parentComponent = parentComponent;
    }

    @Override
    public PoiWidgetRouter build() {
        final PoiWidgetView view = buildView();
        final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.BOTTOM;
        view.setLayoutParams(params);

        final PoiWidgetComponent component = DaggerPoiWidgetComponent.builder()
                .prebookingComponent(parentComponent)
                .poiWidgetModule(new PoiWidgetComponent.PoiWidgetModule(buildAndAttachView()))
                .build();
        component.inject(this);
        interactor.onGetActive();
        return component.router();
    }

    @Override
    public int getLayout() {
        return R.layout.poi_widget;
    }
}
