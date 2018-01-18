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

    @Inject
    var router: R? = null

    fun isActive(): Boolean = router != null

    abstract fun build(): R

    open fun destroy() {
        Log.i(">>>>", "destroy " + this.javaClass.simpleName)
        router?.run {
            destroyNode()
            removeFromBackStack()
            router = null
        }
    }
}
