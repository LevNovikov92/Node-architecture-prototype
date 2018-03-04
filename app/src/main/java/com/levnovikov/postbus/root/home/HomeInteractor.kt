package com.levnovikov.postbus.root.home

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.android.gms.maps.GoogleMap
import com.levnovikov.feature_map.dependency.MapProvider
import com.levnovikov.feature_map.dependency.MapSetter
import com.levnovikov.postbus.root.home.di.HomeScope
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor
import com.levnovikov.stream_state.AppState
import com.levnovikov.system_base.StateInteractor
import com.levnovikov.system_base.lifecycle.Lifecycle
import com.levnovikov.system_base.node_state.ActivityState
import com.levnovikov.system_base.node_state.NodeState
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.parcel.Parcelize
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
        private val lifecycle: Lifecycle) : StateInteractor<HomeRouter>(router, activityState), BookingExtraInteractor.Listener, MapProvider, MapSetter {

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class HomeData(val state: AppState) : Parcelable

    override fun onSaveData(): Parcelable? {
        return currentState?.let { HomeData(it) }
    }

    private var currentState: AppState? = null

    private val mapSubject = BehaviorSubject.create<GoogleMap>()
    override val map: Maybe<GoogleMap>
        get() = mapSubject.firstElement()

    private val state: Map<String, NodeState>
        get() = router.getState()

    override fun restoreState() {
        super.restoreState()
        nodeState?.let {
            val data: HomeData? = it.data as HomeData?
            data?.let {
                currentState = it.state
            }
        } ?: router.loadMap()

        lifecycle.subscribeUntilDestroy(appStateStream
                .subscribe({ state -> switchState(state) }) { error ->
                    //TODO handle error
                })
    }

    private fun switchState(state: AppState) {
        if (state == currentState) {
            return
        }
        when (state) {
            AppState.PREBOOKING -> router.startPrebooking()
            AppState.ALLOCATING -> router.startAllocating()
            AppState.TRACKING -> router.startTracking()
        }
        currentState = state
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

    fun getActivityState(): Parcelable? {
        return ActivityState(stateMap = state)
    }
}
