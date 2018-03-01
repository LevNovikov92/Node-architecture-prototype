package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options

import android.view.LayoutInflater
import android.view.ViewGroup
import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.di.DaggerOptionsComponent
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.di.OptionsDependencies
import com.levnovikov.system_base.ViewNodeHolder
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 1/3/18.
 */

class OptionsNodeHolder @Inject constructor(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val dependencies: OptionsDependencies) : ViewNodeHolder<OptionsView, OptionsRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.options_view

    override fun build(): OptionsRouter {
        val view = buildView()
        val component = DaggerOptionsComponent.builder()
                .dependencies(dependencies)
                .view(view)
                .build()
        component.inject(this)
        component.inject(view)
        attachView()
        return component.router()
    }

}