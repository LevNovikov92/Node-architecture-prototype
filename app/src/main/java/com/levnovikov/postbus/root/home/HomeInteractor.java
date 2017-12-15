package com.levnovikov.postbus.root.home;

import com.levnovikov.postbus.root.home.di.HomeScope;
import com.levnovikov.stream_state.AppState;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
class HomeInteractor {

    private final Observable<AppState> appStateStream;

    @Inject
    HomeInteractor(Observable<AppState> appStateStream) {

        this.appStateStream = appStateStream;
    }
}
