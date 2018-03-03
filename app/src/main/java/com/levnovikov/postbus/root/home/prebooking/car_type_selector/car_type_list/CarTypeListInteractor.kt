package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list

import android.os.Parcelable

import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.CarTypeListScope
import com.levnovikov.system_base.BackStateInteractor
import com.levnovikov.system_base.node_state.ActivityState

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

@CarTypeListScope
class CarTypeListInteractor @Inject
constructor(router: CarTypeListRouter, activityState: ActivityState, private val listener: TypeListListener) : BackStateInteractor<CarTypeListRouter>(router, activityState) {

    override fun onSaveData(): Parcelable?  = null

    override fun onBackPressed(): Boolean {
        listener.onCancel()
        return true
    }
}

interface TypeListListener {
    fun onCancel()
}
