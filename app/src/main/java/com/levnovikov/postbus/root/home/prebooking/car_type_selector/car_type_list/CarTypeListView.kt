package com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 25/12/17.
 */

class CarTypeListView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var interactor: CarTypeListInteractor

}
