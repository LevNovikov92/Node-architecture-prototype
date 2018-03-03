package com.levnovikov.system_base.exceptions

import com.levnovikov.system_base.Router

/**
 * Author: lev.novikov
 * Date: 3/3/18.
 */
class RouterAlreadyAttachedException(private val router: Router) : Exception() {

    override val message: String?
        get() = "Router ${router.javaClass.simpleName} is already attached"
}