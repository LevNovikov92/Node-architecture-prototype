package com.levnovikov.postbus.root.home.prebooking.poi_selector

import android.view.LayoutInflater
import android.view.ViewGroup

import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent
import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.DaggerPoiSelectorComponent
import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.PoiSelectorComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

class PoiSelectorNodeHolder(inflater: LayoutInflater, parent: ViewGroup, private val parentComponent: PrebookingComponent) : ViewNodeHolder<PoiSelectorView, PoiSelectorRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.poi_selector

    override fun build(): PoiSelectorRouter {
        val view = buildView()
        val component = DaggerPoiSelectorComponent.builder()
                .prebookingComponent(parentComponent)
                .poiSelectorModule(PoiSelectorComponent.PoiSelectorModule(view))
                .build()
        component.inject(view)
        component.inject(this)
        attachView()
        return component.router()
    }
}
