package com.levnovikov.feature_car_animation.location_tracking;

import com.google.android.gms.maps.model.LatLng;
import com.levnovikov.feature_car_animation.di.CarAnimScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Author: lev.novikov
 * Date: 28/1/18.
 */

@CarAnimScope
class RemoteLocationDataSource {

    private final static List<LatLng> points = new ArrayList<>();
    private int lastPoint = 0;

    @Inject
    RemoteLocationDataSource() {
        for (double i = 47.571758; i <= 47.573096; i += 0.0001) {
            points.add(new LatLng(i, -122.313447));
        }
        for (double i = -122.313447; i <= -122.312481; i += 0.0001) {
            points.add(new LatLng(47.573096, i));
        }
        for (double i = 47.573096; i <= 47.57400; i += 0.0001) {
            points.add(new LatLng(i, -122.312481));
        }
    }

    Single<LatLng> requestLocation() {
        lastPoint += 1;
        if (lastPoint >= points.size()) {
            lastPoint = 0;
        }
        return Single.just(points.get(lastPoint));
    }
}
