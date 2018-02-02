package com.levnovikov.postbus.root.home.prebooking.poi_widget

import com.levnovikov.feature_ride.ride.RidePrebookingRepo
import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.PoiWidgetScope
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.lifecycle.Lifecycle
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
        private val presenter: Presenter,
        router: PoiWidgetRouter,
        activityState: ActivityState,
        private val lifecycle: Lifecycle,
        prebookingRepo: RidePrebookingRepo) : Interactor<PoiWidgetRouter>(router, activityState) {

    interface PoiClickListener {
        fun onPickUpSelected()
        fun onDropOffSelected()
    }

    init {

        lifecycle.subscribeUntilDestroy(prebookingRepo.pickupPoint.stream
                .subscribe({ (_, title) -> presenter.setPickUp(title) }) { /*handle*/ e -> })

        lifecycle.subscribeUntilDestroy(prebookingRepo.dropOffPoint.stream
                .subscribe({ (_, title) -> presenter.setDropOff(title) }) { /*handle*/ e -> })
    }

    override fun onGetActive() {
        super.onGetActive()
        lifecycle.subscribeUntilDestroy(presenter.onPickUpClick()
                .subscribe({ v -> poiSelectionListener.onPickUpSelected() }) { /*handle it*/ error -> })

        lifecycle.subscribeUntilDestroy(presenter.onDropOffClick()
                .subscribe({ v -> poiSelectionListener.onDropOffSelected() }) { /*handle it*/ error -> })
    }

    interface Presenter {
        fun onPickUpClick(): Observable<Any>
        fun onDropOffClick(): Observable<Any>

        fun setPickUp(placeTitle: String)
        fun setDropOff(placeTitle: String)
    }
}
