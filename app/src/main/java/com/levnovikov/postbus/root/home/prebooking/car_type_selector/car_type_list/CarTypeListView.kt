package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

class CarTypeListView : ConstraintLayout {

    @Inject
    lateinit var interactor: CarTypeListInteractor

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        interactor.onGetActive()
    }
}
