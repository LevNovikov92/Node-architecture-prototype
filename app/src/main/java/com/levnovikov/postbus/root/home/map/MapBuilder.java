package com.levnovikov.postbus.root.home.map;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.map.di.DaggerMapComponent;
import com.levnovikov.postbus.root.home.map.di.MapComponent;
import com.levnovikov.system_base.ViewBuilder;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

public class MapBuilder extends ViewBuilder<MapView, MapRouter> {

    private final HomeComponent parentComponent;

    public MapBuilder(LayoutInflater inflater, ViewGroup parent, HomeComponent parentComponent) {
        super(inflater, parent);
        this.parentComponent = parentComponent;
    }

    @Override
    public MapRouter build() {
        final MapView view = buildView();
        final MapComponent component = DaggerMapComponent
                .builder()
                .homeComponent(parentComponent)
                .mapModule(new MapComponent.MapModule(view))
                .build();
        component.inject(view);
        attachView();
        return component.router();
    }

    @Override
    public int getLayout() {
        return R.layout.map_view;
    }
}
