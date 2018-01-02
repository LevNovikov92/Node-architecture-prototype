package com.levnovikov.postbus.root.home;

import com.levnovikov.postbus.root.home.di.HomeScope;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor;
import com.levnovikov.stream_state.AppState;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.state.ActivityState;
import com.levnovikov.system_base.state.NodeState;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
public class HomeInteractor extends Interactor<HomeRouter>
        implements BookingExtraInteractor.Listener {

    private final Observable<AppState> appStateStream;

    @Inject
    HomeInteractor(
            Observable<AppState> appStateStream,
            HomeRouter router,
            ActivityState activityState) {
        super(router, activityState);
        this.appStateStream = appStateStream;
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        if (!hasSavedState()) {
            Disposable disposable = appStateStream  //TODO remove checking
                    .subscribe(router::switchState, error -> {
                        //TODO handle error
                    });
        }
    }

    @Override
    public void onBookClick() {
        router.startAllocating();
    }

    public Map<String, NodeState> getState() {
        return router.getState();
    }

    boolean onBackPressed() {
        return router.onBackPressed();
    }
}
