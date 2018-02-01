package com.levnovikov.feature_car_animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.levnovikov.feature_car_animation.di.CarAnimScope;
import com.levnovikov.feature_car_animation.location_tracking.LocationRepo;
import com.levnovikov.feature_car_animation.location_tracking.LocationTracker;
import com.levnovikov.feature_car_animation.map_wrapper.MapWrapper;
import com.levnovikov.feature_map.dependency.MapProvider;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.lifecycle.Lifecycle;
import com.levnovikov.system_base.node_state.ActivityState;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

@CarAnimScope
public class CarAnimInteractor extends Interactor<CarAnimRouter> {

    private final MapProvider mapProvider;
    private final VectorDrawable carDrawable;
    private final Lifecycle lifecycle;
    private final LocationRepo locationRepo;
    private final Observable<LatLng> positionStream;
    private final LocationTracker locationTracker;

    @Nullable
    private Marker car;

    @Inject
    public CarAnimInteractor(
            CarAnimRouter router,
            ActivityState activityState,
            MapProvider mapProvider,
            VectorDrawable carDrawable,
            Lifecycle lifecycle,
            LocationTracker locationTracker,
            LocationRepo locationRepo,
            Observable<LatLng> positionStream) {
        super(router, activityState);
        this.mapProvider = mapProvider;
        this.carDrawable = carDrawable;
        this.lifecycle = lifecycle;
        this.locationTracker = locationTracker;
        this.locationRepo = locationRepo;
        this.positionStream = positionStream;
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        mapProvider.getMap()
//                .map(MapWrapper::new)
//                .subscribe(map -> {
//                    initMap(map);
//                    animateCar(map);
//                }, e -> { });
                .map(MapWrapper::new)
                .subscribe(map -> {
                    initMap(map);
                    trackLocation(map);
                }, e -> { });
    }

    private void initMap(MapWrapper map) {
        map.init();
        car = map.showCar(createCar(new LatLng(47.571758, -122.313447), 0));
        map.focusOnCar(car);
    }

    private void trackLocation(MapWrapper map) {
        lifecycle.subscribeUntilDestroy(
                locationRepo.getLocationStream()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(location -> {
                            map.animateCarTo(car, location);
                        }, e -> Log.i("!LOCATION", e.getMessage()))
        );
        locationTracker.startLocationTracking();
    }

    private void animateCar(MapWrapper map) {
        lifecycle.subscribeUntilDestroy(positionStream
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(position -> {
                    map.focusOnCar(car);
                    map.animateCarTo(car, position);
                    Log.i("!POSITION", position.toString());
                }, e -> {
                    Log.i("!ERROR", e.getMessage());
                }));
    }

    private MarkerOptions createCar(LatLng latLng, float rotation) {
        return new MarkerOptions()
                .icon(getBitmapDescriptor(R.drawable.ic_vehicle_taxi))
                .position(latLng)
                .rotation(rotation);
    }

    private BitmapDescriptor getBitmapDescriptor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int h = carDrawable.getIntrinsicHeight();
            int w = carDrawable.getIntrinsicWidth();

            carDrawable.setBounds(0, 0, w, h);

            Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bm);
            carDrawable.draw(canvas);

            return BitmapDescriptorFactory.fromBitmap(bm);

        } else {
            return BitmapDescriptorFactory.fromResource(id);
        }
    }
}
