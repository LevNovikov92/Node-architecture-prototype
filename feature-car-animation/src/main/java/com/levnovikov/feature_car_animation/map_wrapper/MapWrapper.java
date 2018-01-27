package com.levnovikov.feature_car_animation.map_wrapper;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

public class MapWrapper {

    private final GoogleMap map;

    public MapWrapper(GoogleMap map) {
        this.map = map;
    }

    public Marker showCar(MarkerOptions markerOptions) {
        return map.addMarker(markerOptions);
    }

    public void focusOnCar(Marker car) {

    }

    public void animateCarTo(Marker car, LatLng latLng) {

    }

    public void init() {

    }
}
