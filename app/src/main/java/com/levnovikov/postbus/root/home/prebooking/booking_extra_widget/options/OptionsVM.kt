package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 4/3/18.
 */
class OptionsVM @Inject constructor(
        interactor: OptionsInteractor
) {

    init {
        if (interactor.hasSavedState()) {
            interactor.restoreState()
        } else {
            interactor.showSubOptions()
        }
    }


}