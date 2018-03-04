package com.levnovikov.system_base

import android.util.Log

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 *
 * NodeHolder
 */

abstract class NodeHolder<R : Router> {

    @JvmField
    @Inject
    internal var router: R? = null

    internal fun isActive(): Boolean = router != null

    abstract fun build(): R

    open fun destroy() {
        Log.i(">>>>", "destroy " + this.javaClass.simpleName)
        router?.run {
            detachAllChildren()
            removeFromBackStack()
            this.setBackHandler(null)
            router = null
        }
    }
}
