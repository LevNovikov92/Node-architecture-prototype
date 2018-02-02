package com.levnovikov.postbus.root.home.allocating

import android.view.LayoutInflater
import android.view.ViewGroup

import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.allocating.di.AllocatingComponent
import com.levnovikov.postbus.root.home.allocating.di.DaggerAllocatingComponent
import com.levnovikov.postbus.root.home.di.HomeComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

class AllocatingNodeHolder(inflater: LayoutInflater, parent: ViewGroup, private val parentComponent: HomeComponent) : ViewNodeHolder<AllocatingView, AllocatingRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.alloc_view

    override fun build(): AllocatingRouter {
        val view = buildView()
        val component = DaggerAllocatingComponent.builder()
                .homeComponent(parentComponent)
                .allocatingModule(AllocatingComponent.AllocatingModule(view))
                .build()
        component.inject(view)
        component.inject(this)
        attachView()
        return component.router()
    }
}
