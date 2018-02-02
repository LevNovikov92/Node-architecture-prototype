package com.levnovikov.postbus.root.home

import com.google.android.gms.maps.GoogleMap
import com.levnovikov.feature_map.dependency.MapProvider
import com.levnovikov.feature_map.dependency.MapSetter
import com.levnovikov.postbus.root.home.di.HomeScope
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor
import com.levnovikov.stream_state.AppState
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.lifecycle.Lifecycle
import com.levnovikov.system_base.node_state.ActivityState
import com.levnovikov.system_base.node_state.NodeState
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
class HomeInteractor @Inject
internal constructor(
        private val appStateStream: Observable<AppState>,
        router: HomeRouter,
        activityState: ActivityState,
        private val lifecycle: Lifecycle) : Interactor<HomeRouter>(router, activityState), BookingExtraInteractor.Listener, MapProvider, MapSetter {

    private val mapSubject = BehaviorSubject.create<GoogleMap>()
    override val map: Maybe<GoogleMap>
        get() = mapSubject.firstElement()

    val state: Map<String, NodeState>
        get() = router.getState()

    override fun onGetActive() {
        super.onGetActive()
        router.loadMap()
        lifecycle.subscribeUntilDestroy(appStateStream  //TODO remove checking
                .subscribe({ state -> router.switchState(state) }) { error ->
                    //TODO handle error
                })
    }

    override fun onBookClick() {
        router.startAllocating()
    }

    internal fun onBackPressed(): Boolean {
        return router.onBackPressed()
    }

    override fun setMap(map: GoogleMap) {
        mapSubject.onNext(map)
    }
}
