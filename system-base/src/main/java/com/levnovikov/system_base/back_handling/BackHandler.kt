package com.levnovikov.system_base.back_handling

import com.levnovikov.system_base.Router

/**
 * Created by lev.novikov
 * Date: 1/1/18.
 */

internal interface BackHandler {
    fun onBackPressed(): Boolean
    fun isLastInBackStack(_class: Class<out Router>): Boolean
    fun popLastInBackStack()
    fun removeFromBackStack(_class: Class<out Router>)
}
