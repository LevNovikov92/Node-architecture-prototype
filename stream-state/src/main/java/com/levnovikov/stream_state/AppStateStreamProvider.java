package com.levnovikov.stream_state;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

public class AppStateStreamProvider {

    @Inject
    public AppStateStreamProvider() {

    }

    public Observable<AppState> provideAppStateStream() {
        return BehaviorSubject.createDefault(AppState.PREBOOKING).distinctUntilChanged(); //TODO stub
    }
}
