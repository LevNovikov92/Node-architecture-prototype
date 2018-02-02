package com.levnovikov.postbus.root.home.prebooking.car_type_selector

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout

import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorComponent
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.DaggerCarTypeSelectorComponent
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

class CarTypeSelectorNodeHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val parentComponent: PrebookingComponent) : ViewNodeHolder<CarTypeSelectorView, CarTypeSelectorRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.car_type_selector

    override fun build(): CarTypeSelectorRouter {
        val view = buildView()
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.BOTTOM
        params.setMargins(0, 0, 0, getDp(view.context, 180))
        view.layoutParams = params

        val component = DaggerCarTypeSelectorComponent.builder()
                .prebookingComponent(parentComponent)
                .carTypeModule(CarTypeSelectorComponent.CarTypeModule(view))
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
