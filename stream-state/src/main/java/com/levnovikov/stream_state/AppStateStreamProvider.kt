package com.levnovikov.stream_state

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

class AppStateStreamProvider @Inject
constructor() {

    private val appStateSubject = BehaviorSubject.createDefault(AppState.PREBOOKING)
    fun provideAppStateStream(): Observable<AppState> {
        return appStateSubject.distinctUntilChanged() //TODO stub
    }
}
