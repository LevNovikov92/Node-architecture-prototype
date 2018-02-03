package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout

import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.CarTypeListComponent
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.di.DaggerCarTypeListComponent
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

class CarTypeListNodeHolder(inflater: LayoutInflater, parent: ViewGroup, private val parentComponent: CarTypeSelectorComponent) : ViewNodeHolder<CarTypeListView, CarTypeListRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.car_type_list_view

    override fun build(): CarTypeListRouter {
        val view = buildView()
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.bottomMargin = getDp(view.context, 280)
        view.layoutParams = params

        val component = DaggerCarTypeListComponent.builder()
                .carTypeSelectorComponent(parentComponent)
                .carTypeListModule(CarTypeListComponent.CarTypeListModule(view))
                .build()
        component.inject(view)
        component.inject(this)
        attachView()
        return component.router()
    }

    //TODO move to utils
    private fun getDp(context: Context, dps: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dps * scale + 0.5f).toInt()
    }
}
