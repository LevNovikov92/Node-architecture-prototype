package com.levnovikov.feature_car_animation.location_tracking;

import com.google.android.gms.maps.model.LatLng;
import com.levnovikov.feature_car_animation.di.CarAnimScope;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Author: lev.novikov
 * Date: 28/1/18.
 */

@CarAnimScope
public class LocationRepo {

    @Inject
    LocationRepo() {}

    private final static BehaviorSubject<LatLng> locationSubject = BehaviorSubject.create();

    void setLocation(LatLng location) {
        locationSubject.onNext(location);
    }

    public Observable<LatLng> getLocationStream() {
        return locationSubject;
    }
}
