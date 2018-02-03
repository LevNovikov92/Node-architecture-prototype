package com.levnovikov.system_base

import com.levnovikov.system_base.node_state.ActivityState

abstract class StateInteractor<R : Router>(router: R, activityState: ActivityState) : Interactor<R>(router, activityState), StateDataProvider {

    init {
        /*
         * If interactor need to store data after Activity recreation, we can set data source to router.
         * Router will get and save data when Activity will call onSaveInstanceState.
         * P.S. possible to move getStateData() in base Interactor and override if need to store data.
         */
        router.setStateDataProvider(this) //TODO fix leak
    }

    override fun onGetActive() {
        val state = nodeState
        if (state != null) {
            router.setState(state)
        }
    }
}
