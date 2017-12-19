package com.levnovikov.feature_ride.ride;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by lev.novikov
 * Date: 19/12/17.
 */

public abstract class BehaviorField<T> {

    private BehaviorSubject<T> field = BehaviorSubject.create();
    public Observable<T> getStream() {
        return field.distinctUntilChanged();
    }

    public void set(T entity) {
        save(entity);
        field.onNext(entity);
    }

    abstract void save(T entity);
}
