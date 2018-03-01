package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options

import android.view.LayoutInflater
import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsView
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.di.DaggerSubOptionsComponent
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.sub_options.di.SubOptionsDependencies
import com.levnovikov.system_base.ViewNodeHolder


/**
 * Created by stepan.goncharov on 1/3/18.
 */
class SubOptionsNodeHolder constructor(parent: OptionsView, inflater: LayoutInflater, val dependencies: SubOptionsDependencies) :
        ViewNodeHolder<SubOptionsView,
        SubOptionsRouter>(inflater, parent) {

    override val layout: Int = R.layout.sub_options_view

    override fun build(): SubOptionsRouter {
        val view = buildView()
        val component = DaggerSubOptionsComponent.builder()
                .dependencies(dependencies)
                .view(view)
                .build()
        component.inject(this)
        component.inject(view)
        attachView()
        return component.router()
    }

}