package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 3/3/18.
 */
class BookingExtraVM @Inject constructor(
        private val interactor: BookingExtraInteractor
) {

    init {
        if (interactor.hasSavedState()) interactor.restoreState()
    }

    fun onPaymentClick() {

    }

    fun onPromoClick() {
        interactor.showPromo()
    }

    fun onOptionsClick() {

    }

    fun onBookClick() {
        interactor.onBookClick()
    }
}