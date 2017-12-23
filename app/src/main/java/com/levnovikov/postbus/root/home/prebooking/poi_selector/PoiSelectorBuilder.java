package com.levnovikov.postbus.root.home.prebooking.poi_selector;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.DaggerPoiSelectorComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.PoiSelectorComponent;
import com.levnovikov.system_base.ViewBuilder;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

public class PoiSelectorBuilder extends ViewBuilder<PoiSelectorView, PoiSelectorRouter> {

    private final LayoutInflater inflater;
    private final PrebookingComponent parentComponent;

    public PoiSelectorBuilder(LayoutInflater inflater, ViewGroup parent, PrebookingComponent parentComponent) {
        super(inflater, parent);
        this.inflater = inflater;
        this.parentComponent = parentComponent;
    }

    @Override
    public PoiSelectorRouter build() {
        final PoiSelectorView view = buildView();
        final PoiSelectorComponent component = DaggerPoiSelectorComponent.builder()
                .prebookingComponent(parentComponent)
                .poiSelectorModule(new PoiSelectorComponent.PoiSelectorModule(view))
                .build();
        component.inject(view);
        parent.addView(view);
        return component.router();
    }

    protected PoiSelectorView buildView() {
        view = (PoiSelectorView) inflater.inflate(getLayout(), null, false);
        return view;
    }

    @Override
    public int getLayout() {
        return R.layout.poi_selector;
    }
}
