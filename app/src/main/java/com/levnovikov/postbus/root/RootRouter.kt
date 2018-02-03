package com.levnovikov.postbus.root

import com.levnovikov.postbus.root.di.RootScope
import com.levnovikov.postbus.root.home.HomeActivity
import com.levnovikov.system_base.ActivityStarter
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

@RootScope
class RootRouter @Inject
internal constructor(private val starter: ActivityStarter) {

    internal fun home() {
        starter.startActivity(HomeActivity::class.java, null)
    }
}
