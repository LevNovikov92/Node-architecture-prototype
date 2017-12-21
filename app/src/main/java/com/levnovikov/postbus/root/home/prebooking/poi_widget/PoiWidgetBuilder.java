package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import android.view.LayoutInflater;
import android.view.ViewGroup;

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
        final PoiWidgetComponent component = DaggerPoiWidgetComponent.builder()
                .prebookingComponent(parentComponent)
                .poiWidgetModule(new PoiWidgetComponent.PoiWidgetModule(buildView()))
                .build();
        component.inject(this);
        return component.router();
    }

    @Override
    public int getLayout() {
        return R.layout.poi_widget;
    }
}
