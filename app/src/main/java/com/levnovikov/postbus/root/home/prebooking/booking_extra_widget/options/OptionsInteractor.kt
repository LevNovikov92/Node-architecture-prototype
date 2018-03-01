package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.di.OptionsScope
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.node_state.ActivityState
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 1/3/18.
 */

@OptionsScope
class OptionsInteractor @Inject constructor(router: OptionsRouter, activityState: ActivityState) : Interactor<OptionsRouter>(router, activityState) {

    override fun onGetActive() {
        router.showSubOptions()
    }
}