package com.levnovikov.postbus.root.home.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.levnovikov.postbus.root.home.map.di.MapScope;
import com.levnovikov.postbus.root.home.map.map_wrapper.MapInterface;
import com.levnovikov.postbus.root.home.map.map_wrapper.MapWrapper;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.state.ActivityState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

@MapScope
public class MapInteractor extends Interactor<MapRouter> implements OnMapReadyCallback {

    private final OnMapInitialized onMapInitialized;
    private MapWrapper mapWrapper; //TODO remove reference on destroy

    public interface OnMapInitialized {
        void onMapInitialized(MapInterface mapInterface);
    }

    @Inject
    MapInteractor(MapRouter router, ActivityState activityState, OnMapInitialized onMapInitialized) {
        super(router, activityState);
        this.onMapInitialized = onMapInitialized;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapWrapper = new MapWrapper(googleMap);
        onMapInitialized.onMapInitialized(mapWrapper);
        // Add a marker in Sydney, Australia,
        // and move the mapWrapper's camera to the same location.
//        LatLng sydney = new LatLng(-33.852, 151.211);
//        googleMap.addMarker(new MarkerOptions().position(sydney)
//                .title("Marker in Sydney"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
