package com.levnovikov.feature_map;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.levnovikov.feature_map.dependency.MapDependency;
import com.levnovikov.feature_map.di.DaggerMapComponent;
import com.levnovikov.feature_map.di.MapComponent;
import com.levnovikov.system_base.ViewNodeHolder;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

public class MapNodeHolder extends ViewNodeHolder<MapView, MapRouter> {

    private final MapDependency parentComponent;

    public MapNodeHolder(LayoutInflater inflater, ViewGroup parent, MapDependency parentComponent) {
        super(inflater, parent);
        this.parentComponent = parentComponent;
    }

    @Override
    public MapRouter build() {
        final MapView view = buildView();
        final MapComponent component = DaggerMapComponent
                .builder()
                .mapDependency(parentComponent)
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
