package com.levnovikov.postbus.root.home.prebooking

import android.os.Parcelable
import android.util.Log
import com.example.core_geo.Point
import com.levnovikov.feature_map.dependency.MapProvider
import com.levnovikov.feature_map.map_wrapper.MapWrapper
import com.levnovikov.feature_ride.ride.RidePrebookingData
import com.levnovikov.feature_ride.ride.RidePrebookingRepo
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorInteractor
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorInteractor
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetInteractor
import com.levnovikov.stream_state.PrebookingState
import com.levnovikov.system_base.StateDataProvider
import com.levnovikov.system_base.StateInteractor
import com.levnovikov.system_base.lifecycle.Lifecycle
import com.levnovikov.system_base.node_state.ActivityState
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
class PrebookingInteractor @Inject
internal constructor(router: PrebookingRouter,
                     private val prebookingRepo: RidePrebookingRepo,
                     activityState: ActivityState,
                     private val lifecycle: Lifecycle,
                     private val mapProvider: MapProvider) : StateInteractor<PrebookingRouter>(router, activityState), PoiSelectorInteractor.PoiSelectionListener, PoiWidgetInteractor.PoiClickListener, CarTypeSelectorInteractor.Listener, StateDataProvider {
    private var state = PrebookingState.INITIAL

    /**
     * Router will save this data when activity will go to background
     */
    override fun onSaveData(): Parcelable = prebookingRepo.data

    override fun onGetActive() {
        super.onGetActive()
        if (!hasSavedState()) {
            router.showPoiWidget()
        } else {
            restoreStateIfPossible()
        }
        bindMapAndPrebookingRepo()
    }

    private fun bindMapAndPrebookingRepo() {
        lifecycle.subscribeUntilDestroy(
                prebookingRepo.pickupPoint.stream()
                        .subscribe({ point ->
                            mapProvider.map
                                    .map { MapWrapper(it) }
                                    .subscribe({ map -> map.setPickUp(point) }) { e -> }
                        }) { e -> })
        lifecycle.subscribeUntilDestroy(prebookingRepo.dropOffPoint.stream()
                .subscribe({ point ->
                    mapProvider.map
                            .map { MapWrapper(it) }
                            .subscribe({ map -> map.setDropOff(point) }) { e -> }
                }) { e -> })
    }

    private fun restoreStateIfPossible() {
        val stateDataParcelable = nodeStateData
        if (stateDataParcelable != null) {
            val stateData = stateDataParcelable as RidePrebookingData
            prebookingRepo.data = stateData
        }
    }

    override fun onPoiSelected(point: Point) {
        if (state == PrebookingState.PICK_UP_SELECTION) {
            prebookingRepo.pickupPoint.set(point)
            router.hidePoiChoice()
        } else if (state == PrebookingState.DROP_OFF_SELECTION) {
            prebookingRepo.dropOffPoint.set(point)
            state = PrebookingState.SET_SERVICE_TYPE
            router.startServiceType()
            router.startBookingExtra()
        }
    }

    override fun onPoiSelectionCanceled() {
        router.hidePoiChoice()
    }

    override fun onPickUpSelected() {
        state = PrebookingState.PICK_UP_SELECTION
        router.startPoiChoice()
    }

    override fun onDropOffSelected() {
        state = PrebookingState.DROP_OFF_SELECTION
        router.startPoiChoice()
    }

    override fun onServiceSelected() {
        Log.i(">>>>", "Service selected. Update Bottom Nav.")
    }

}
