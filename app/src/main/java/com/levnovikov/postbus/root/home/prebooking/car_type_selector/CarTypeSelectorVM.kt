package com.levnovikov.postbus.root.home.prebooking.car_type_selector

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorScope
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 2/3/18.
 */

@CarTypeSelectorScope
class CarTypeSelectorVM @Inject constructor(
        private val interactor: CarTypeSelectorInteractor
) {

    init {
        if (interactor.hasSavedState()) interactor.restoreState()
    }

    fun onClick() {
        interactor.onServiceSelected()
    }
}