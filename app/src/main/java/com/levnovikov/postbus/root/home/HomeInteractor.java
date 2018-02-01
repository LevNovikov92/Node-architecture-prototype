package com.levnovikov.postbus.root.home;

import android.support.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.levnovikov.feature_map.dependency.MapProvider;
import com.levnovikov.feature_map.dependency.MapSetter;
import com.levnovikov.postbus.root.home.di.HomeScope;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor;
import com.levnovikov.stream_state.AppState;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.lifecycle.Lifecycle;
import com.levnovikov.system_base.node_state.ActivityState;
import com.levnovikov.system_base.node_state.NodeState;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
public class HomeInteractor extends Interactor<HomeRouter>
        implements BookingExtraInteractor.Listener,
        MapProvider, MapSetter {

    private final Observable<AppState> appStateStream;
    private final Lifecycle lifecycle;

    private BehaviorSubject<GoogleMap> mapSubject = BehaviorSubject.create();
    @Override
    public Maybe<GoogleMap> getMap() {
        return mapSubject.firstElement();
    }

    @Inject
    HomeInteractor(
            Observable<AppState> appStateStream,
            HomeRouter router,
            ActivityState activityState,
            Lifecycle lifecycle) {
        super(router, activityState);
        this.appStateStream = appStateStream;
        this.lifecycle = lifecycle;
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        router.loadMap();
        lifecycle.subscribeUntilDestroy(appStateStream  //TODO remove checking
                .subscribe(state -> router.switchState(state), error -> {
                    //TODO handle error
                }));
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

    @Override
    public void setMap(@Nullable GoogleMap map) {
        mapSubject.onNext(map);
    }
}
