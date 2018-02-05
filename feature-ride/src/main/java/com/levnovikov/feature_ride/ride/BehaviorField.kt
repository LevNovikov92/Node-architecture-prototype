package com.levnovikov.feature_ride.ride

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by lev.novikov
 * Date: 19/12/17.
 */

abstract class BehaviorField<T> {

    private val field = BehaviorSubject.create<T>()
    fun stream(): Observable<T> = field.distinctUntilChanged()

    fun set(entity: T) {
        save(entity)
        field.onNext(entity)
    }

    internal abstract fun save(entity: T)
}
