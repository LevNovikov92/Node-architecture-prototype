package com.levnovikov.feature_map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.levnovikov.feature_map.dependency.MapSetter;
import com.levnovikov.feature_map.di.MapScope;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.lifecycle.Lifecycle;
import com.levnovikov.system_base.node_state.ActivityState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

@MapScope
public class MapInteractor extends Interactor<MapRouter> implements OnMapReadyCallback  {

    private final Lifecycle lifecycle;
    private final MapSetter mapSetter;

    @Inject
    MapInteractor(
            MapRouter router,
            ActivityState activityState,
            Lifecycle lifecycle,
            MapSetter mapSetter) {
        super(router, activityState);
        this.lifecycle = lifecycle;
        this.mapSetter = mapSetter;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapSetter.setMap(googleMap);
    }

}
