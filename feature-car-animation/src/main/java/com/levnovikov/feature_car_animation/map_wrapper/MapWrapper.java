package com.levnovikov.feature_car_animation.map_wrapper;

import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

public class MapWrapper {

    private final static float DEFAULT_ZOOM = 18f;

    private final GoogleMap map;

    public MapWrapper(GoogleMap map) {
        this.map = map;
    }

    public Marker showCar(MarkerOptions markerOptions) {
        return map.addMarker(markerOptions);
    }

    public void focusOnCar(Marker car) {
        CameraPosition cameraPosition = CameraPosition.builder().zoom(DEFAULT_ZOOM)
                .target(car.getPosition())
                .build();
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void animateCarTo(Marker car, LatLng target) {
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(getMovementAnimator(car, target)/*, getRotationAnimator(car, target)*/);
        set.start();
    }

    private ValueAnimator getRotationAnimator(Marker car, LatLng latLng) {
        final double startDegrees = car.getRotation();
        Log.i("!ROTATION", "Start: " + String.valueOf(startDegrees));
        final double endDegrees =
                Math.toDegrees(Math.atan2((latLng.longitude - car.getPosition().longitude), (latLng.latitude - car.getPosition().latitude)));
        Log.i("!ROTATION", "Target: " + String.valueOf(endDegrees));
        final ValueAnimator animator = ValueAnimator.ofFloat((float) startDegrees, (float) endDegrees);
        animator.addUpdateListener(valueAnimator ->
                car.setRotation((float) valueAnimator.getAnimatedValue()));
        return animator;
    }

    private ValueAnimator getMovementAnimator(Marker car, LatLng latLng) {
        final ValueAnimator animator = ValueAnimator.ofObject(getLinearTypeEvaluator(), car.getPosition(), latLng);
        animator.addUpdateListener(valueAnimator ->
                car.setPosition((LatLng) valueAnimator.getAnimatedValue()));
        return animator;
    }

    private TypeEvaluator<LatLng> getLinearTypeEvaluator() {
        return (v, from, to) -> {
            Log.i("!FRACTION", String.valueOf(v));
            final double lat = from.latitude + (to.latitude - from.latitude) * v;
            final double lng = from.longitude + (to.longitude - from.longitude) * v;
            Log.i("!POSITION", lat + "   " + lng);
            return new LatLng(lat, lng);
        };
    }

    public void init() {

    }
}
