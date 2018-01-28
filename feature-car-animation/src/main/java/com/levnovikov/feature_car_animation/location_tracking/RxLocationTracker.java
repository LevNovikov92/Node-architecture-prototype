package com.levnovikov.feature_car_animation.location_tracking;

import android.util.Log;

import com.levnovikov.feature_car_animation.di.CarAnimScope;
import com.levnovikov.system_base.lifecycle.Lifecycle;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.completable.CompletableTimer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: lev.novikov
 * Date: 28/1/18.
 */

@CarAnimScope
public class RxLocationTracker implements LocationTracker {

    private final Lifecycle lifecycle;
    private final RemoteLocationDataSource dataSource;
    private final LocationRepo locationRepo;

    @Inject
    public RxLocationTracker(
            Lifecycle lifecycle,
            RemoteLocationDataSource dataSource,
            LocationRepo locationRepo) {
        this.lifecycle = lifecycle;
        this.dataSource = dataSource;
        this.locationRepo = locationRepo;
    }

    @Override
    public void startLocationTracking() {
//        lifecycle.subscribeUntilDestroy(
                final Disposable disposable = Observable.interval(500, TimeUnit.MILLISECONDS)
                        .flatMapSingle(t -> dataSource.requestLocation())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(locationRepo::setLocation,
                                e -> Log.i("!ERR", e.getMessage())); //);

        CompletableTimer.timer(5, TimeUnit.SECONDS)
                .subscribe(() -> {
                    disposable.dispose();
                    Log.i("!SUBSCRIBE", "");
                }, e -> {});
    }
}
