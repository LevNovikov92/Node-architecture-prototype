package com.levnovikov.system_base.back_handling

import com.levnovikov.system_base.Router

/**
 * Created by lev.novikov
 * Date: 1/1/18.
 */

interface BackHandler {
    fun onBackPressed(): Boolean

    fun isLastInStack(_class: Class<out Router>): Boolean

    fun popLastInStack()

    fun removeFromBackStack(_class: Class<out Router>)
}
