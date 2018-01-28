package com.levnovikov.feature_car_animation.di;

import android.content.Context;
import android.graphics.drawable.VectorDrawable;
import android.os.Handler;
import android.os.HandlerThread;

import com.google.android.gms.maps.model.LatLng;
import com.levnovikov.feature_car_animation.CarAnimInteractor;
import com.levnovikov.feature_car_animation.CarAnimRouter;
import com.levnovikov.feature_car_animation.R;
import com.levnovikov.feature_car_animation.dependency.CarAnimDependency;
import com.levnovikov.feature_car_animation.location_tracking.LocationTracker;
import com.levnovikov.feature_car_animation.location_tracking.RxLocationTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

@CarAnimScope
@Component(dependencies = CarAnimDependency.class, modules = { CarAnimComponent.CarAnimModule.class })
public interface CarAnimComponent {

    CarAnimInteractor interactor();

    CarAnimRouter router();

    @Module
    class CarAnimModule {

        private final static List<LatLng> points = new ArrayList<>();

        CarAnimModule() {
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

        @Provides
        VectorDrawable providesCarDrawable(Context context) {
            return (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_vehicle_taxi);
        }

        @Provides
        Observable<LatLng> providesPositionStream() {
            return Observable.fromIterable(points)
                    .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS), (point, time) -> point);
        }

        @Provides
        @CarAnimScope
        LocationTracker provideLocationTracker(RxLocationTracker tracker) {
            return tracker;
        }

        @Provides
        @CarAnimScope
        Handler providesHandler() {
            final HandlerThread thread = new HandlerThread("Custom thread");
            thread.start();
            return new Handler(thread.getLooper());
        }
    }
}
