package com.levnovikov.feature_car_animation.location_tracking;

import android.os.Handler;

import com.levnovikov.feature_car_animation.di.CarAnimScope;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 28/1/18.
 */

@CarAnimScope
public class HandlerLocationTracker implements LocationTracker {


    private final RemoteLocationDataSource dataSource;
    private final LocationRepo locationRepo;
    private final Handler handler;
    private Runnable task;

    @Inject
    public HandlerLocationTracker(
            RemoteLocationDataSource dataSource,
            LocationRepo locationRepo,
            Handler handler) {
        this.dataSource = dataSource;
        this.locationRepo = locationRepo;
        this.handler = handler;
    }

    @Override
    public void startLocationTracking() {
        task = () -> {
            try {
                locationRepo.setLocation(dataSource.requestLocation().blockingGet());
            } finally {
                handler.postDelayed(task, 500);
            }
        };
        handler.post(task);
    }

    public void stop() {
        handler.removeCallbacks(task);
    }
}
