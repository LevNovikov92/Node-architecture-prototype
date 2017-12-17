package com.levnovikov.postbus.root.home;

import com.levnovikov.postbus.root.home.di.HomeScope;
import com.levnovikov.stream_state.AppState;
import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
class HomeInteractor implements Interactor {

    private final Observable<AppState> appStateStream;
    private final HomeRouter router;

    @Inject
    HomeInteractor(
            Observable<AppState> appStateStream,
            HomeRouter router) {
        this.appStateStream = appStateStream;
        this.router = router;
        onGetActive();
    }

    @Override
    public void onGetActive() {
        Disposable disposable = appStateStream
                .subscribe((state) -> {
                    router.switchState(state);
                }, error -> {
                    //TODO handle error
                });
    }
}
