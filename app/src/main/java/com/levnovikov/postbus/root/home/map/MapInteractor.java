package com.levnovikov.postbus.root.home.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.levnovikov.postbus.root.home.map.di.MapScope;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.state.ActivityState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

@MapScope
public class MapInteractor extends Interactor<MapRouter> implements OnMapReadyCallback {

    private GoogleMap map; //TODO remove reference on destroy

    @Inject
    MapInteractor(MapRouter router, ActivityState activityState) {
        super(router, activityState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
