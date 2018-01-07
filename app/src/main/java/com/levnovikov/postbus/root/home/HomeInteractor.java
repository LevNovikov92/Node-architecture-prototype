package com.levnovikov.postbus.root.home;

import android.util.Log;

import com.example.core_geo.Point;
import com.levnovikov.feature_map.MapInteractor;
import com.levnovikov.feature_map.map_wrapper.MapInterface;
import com.levnovikov.postbus.root.home.di.HomeScope;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor;
import com.levnovikov.stream_state.AppState;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.lifecycle.Lifecycle;
import com.levnovikov.system_base.node_state.ActivityState;
import com.levnovikov.system_base.node_state.NodeState;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
public class HomeInteractor extends Interactor<HomeRouter>
        implements BookingExtraInteractor.Listener, MapInterface, MapInteractor.MapDataStream {

    private final Observable<AppState> appStateStream;
    private final Lifecycle lifecycle;

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

    private final BehaviorSubject<Point> pickUpPointStream = BehaviorSubject.create();
    @Override
    public void setPickUp(Point point) {
        Log.i(">>>MAP INTERFACE", "setPickUp");
        pickUpPointStream.onNext(point);
    }

    private final BehaviorSubject<Point> dropOffPointStream = BehaviorSubject.create();
    @Override
    public void setDropOff(Point point) {
        Log.i(">>>MAP INTERFACE", "setDropOff");
        dropOffPointStream.onNext(point);
    }

    @Override
    public void clear() {
        Log.i(">>>MAP INTERFACE", "clear");
    }

    @Override
    public Observable<Point> pickUpPointStream() {
        return pickUpPointStream;
    }

    @Override
    public Observable<Point> dropOffPointStream() {
        return dropOffPointStream;
    }
}
