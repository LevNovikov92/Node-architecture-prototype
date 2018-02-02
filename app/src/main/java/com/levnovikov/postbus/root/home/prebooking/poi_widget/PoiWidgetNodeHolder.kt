package com.levnovikov.postbus.root.home.prebooking.poi_widget

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout

import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent
import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.DaggerPoiWidgetComponent
import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.PoiWidgetComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

class PoiWidgetNodeHolder(inflater: LayoutInflater, parent: ViewGroup, private val parentComponent: PrebookingComponent) : ViewNodeHolder<PoiWidgetView, PoiWidgetRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.poi_widget

    override fun build(): PoiWidgetRouter {
        val view = buildView()
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.BOTTOM
        view.layoutParams = params

        val component = DaggerPoiWidgetComponent.builder()
                .prebookingComponent(parentComponent)
                .poiWidgetModule(PoiWidgetComponent.PoiWidgetModule(view))
                .build()
        component.inject(this)
        component.inject(view)
        attachView()
        return component.router()
    }
}
