package com.levnovikov.system_base.exceptions

import com.levnovikov.system_base.NodeHolder

/**
 * Author: lev.novikov
 * Date: 3/3/18.
 */
class ViewIsAlreadyAttachedException(private val nodeHolder: NodeHolder<*>) : Exception() {

    override val message: String?
        get() = "ViewNodeHolder ${nodeHolder.javaClass.simpleName} is already attached"
}