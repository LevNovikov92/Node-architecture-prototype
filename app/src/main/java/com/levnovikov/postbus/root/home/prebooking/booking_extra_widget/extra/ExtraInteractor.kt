package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.di.ExtraScope
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.node_state.ActivityState
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 3/2/18.
 */

@ExtraScope
class ExtraInteractor @Inject constructor(
        router: ExtraRouter,
        activityState: ActivityState) : Interactor<ExtraRouter>(router, activityState) {

    init {
        restoreState()
    }
}