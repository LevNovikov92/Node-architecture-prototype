package com.levnovikov.postbus.root.home.prebooking.car_type_selector

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.TypeListListener
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorScope
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.node_state.ActivityState
import javax.inject.Inject


/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@CarTypeSelectorScope
class CarTypeSelectorInteractor @Inject constructor(
        private val listener: Listener,
        router: CarTypeSelectorRouter,
        activityState: ActivityState) : Interactor<CarTypeSelectorRouter>(router, activityState), TypeListListener {

    override fun onCancel() {
        router.detachTypeList()
    }

    interface Listener {
        fun onServiceSelected()
    }

    fun onServiceSelected() {
        router.attachTypeList()
        listener.onServiceSelected()
    }
}
