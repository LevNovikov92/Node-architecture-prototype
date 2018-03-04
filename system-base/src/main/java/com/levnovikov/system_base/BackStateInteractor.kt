package com.levnovikov.system_base

/**
 * Author: lev.novikov
 * Date: 6/1/18.
 */

import com.levnovikov.system_base.back_handling.BackHandler
import com.levnovikov.system_base.node_state.ActivityState

abstract class BackStateInteractor<R : Router>(
        router: R, activityState: ActivityState
) : StateInteractor<R>(router, activityState), BackHandler {

    init {
        /*
         * If interactor need to handle onBackPressed
         */
        @Suppress("LeakingThis")
        router.setBackHandler(this)
        activityState.addToBackStack(router.javaClass)
    }

    override fun isLastInBackStack(_class: Class<out Router>): Boolean {
        return activityState.isLastInBackStack(_class)
    }

    override fun popLastInBackStack() {
        activityState.popLastInBackStack()
    }

    override fun removeFromBackStack(_class: Class<out Router>) {
        activityState.removeFromBackStack(_class)
    }
}
