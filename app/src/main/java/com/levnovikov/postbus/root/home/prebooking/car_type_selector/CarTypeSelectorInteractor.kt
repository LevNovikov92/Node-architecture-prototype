package com.levnovikov.postbus.root.home.prebooking.car_type_selector

import com.levnovikov.feature_ride.ride.RidePrebookingRepo
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.CarTypeListInteractor
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorScope
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.lifecycle.Lifecycle
import com.levnovikov.system_base.node_state.ActivityState
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@CarTypeSelectorScope
class CarTypeSelectorInteractor @Inject
internal constructor(
        private val presenter: Presenter,
        private val listener: Listener,
        private val prebookingRepo: RidePrebookingRepo,
        router: CarTypeSelectorRouter,
        activityState: ActivityState,
        private val lifecycle: Lifecycle) : Interactor<CarTypeSelectorRouter>(router, activityState), CarTypeListInteractor.TypeListListener {

    override fun onCancel() {
        router.detachTypeList()
    }

    interface Presenter {
        fun clickStream(): Observable<Any>
    }

    interface Listener {
        fun onServiceSelected()
    }

    override fun onGetActive() {
        super.onGetActive()
        lifecycle.subscribeUntilDestroy(presenter.clickStream()
                .subscribe({ o ->
                    router.attachTypeList()
                    listener.onServiceSelected()
                }) { /*handle error*/ e -> })
    }
}
