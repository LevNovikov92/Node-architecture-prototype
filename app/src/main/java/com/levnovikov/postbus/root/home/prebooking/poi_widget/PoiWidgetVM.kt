package com.levnovikov.postbus.root.home.prebooking.poi_widget

import android.databinding.ObservableField
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 3/3/18.
 */
class PoiWidgetVM @Inject constructor(
        private val interactor: PoiWidgetInteractor
) {

    val pickUpPoint = ObservableField("Select pick-up")
    val dropOffPoint = ObservableField("Select drop-off")

    init {
        interactor.getPickupPointStream()
                .subscribe({ (_, title) -> pickUpPoint.set(title) }) { /*handle*/ e -> }

        interactor.getDropOffPointStream()
                .subscribe({ (_, title) -> dropOffPoint.set(title) }) { /*handle*/ e -> }

        if (interactor.hasSavedState()) interactor.restoreState()
    }

    fun onPickupSelected() {
        interactor.selectPickUp()
    }

    fun onDropOffSelected() {
        interactor.selectDropOff()
    }
}