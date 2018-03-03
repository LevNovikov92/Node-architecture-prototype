package com.levnovikov.postbus.root.home.prebooking.poi_widget

import com.example.core_geo.Point
import com.levnovikov.feature_ride.ride.RidePrebookingRepo
import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.PoiWidgetScope
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.node_state.ActivityState
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

@PoiWidgetScope
class PoiWidgetInteractor @Inject
constructor(
        private val poiSelectionListener: PoiClickListener,
        router: PoiWidgetRouter,
        activityState: ActivityState,
        private val prebookingRepo: RidePrebookingRepo) : Interactor<PoiWidgetRouter>(router, activityState) {

    interface PoiClickListener {
        fun onPickUpSelected()
        fun onDropOffSelected()
    }

    fun getPickupPointStream(): Observable<Point> {
        return prebookingRepo.pickupPoint.stream()
    }

    fun getDropOffPointStream(): Observable<Point> {
        return prebookingRepo.dropOffPoint.stream()
    }

    fun selectPickUp() {
        poiSelectionListener.onPickUpSelected()
    }

    fun selectDropOff() {
        poiSelectionListener.onDropOffSelected()
    }
}
