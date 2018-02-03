package com.levnovikov.postbus.root.home.prebooking

import com.levnovikov.postbus.root.home.di.HomeComponent
import com.levnovikov.postbus.root.home.prebooking.di.DaggerPrebookingComponent
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent
import com.levnovikov.system_base.NodeHolder

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

class PrebookingNodeHolder(private val component: HomeComponent) : NodeHolder<PrebookingRouter>() {

    override fun build(): PrebookingRouter {
        val cmp = DaggerPrebookingComponent.builder()
                .homeComponent(component)
                .build()
        cmp.inject(this)
        cmp.interactor().onGetActive()
        return cmp.getRouter()
    }
}
