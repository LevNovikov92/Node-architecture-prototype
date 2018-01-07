package com.levnovikov.postbus.root.home.prebooking.poi_selector;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.DaggerPoiSelectorComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.PoiSelectorComponent;
import com.levnovikov.system_base.ViewNodeHolder;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

public class PoiSelectorNodeHolder extends ViewNodeHolder<PoiSelectorView, PoiSelectorRouter> {

    private final PrebookingComponent parentComponent;

    public PoiSelectorNodeHolder(LayoutInflater inflater, ViewGroup parent, PrebookingComponent parentComponent) {
        super(inflater, parent);
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
        component.inject(this);
        attachView();
        return router;
    }

    @Override
    public int getLayout() {
        return R.layout.poi_selector;
    }
}
