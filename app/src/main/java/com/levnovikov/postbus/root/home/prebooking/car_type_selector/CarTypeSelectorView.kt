package com.levnovikov.postbus.root.home.prebooking.car_type_selector

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.levnovikov.postbus.databinding.CarTypeSelectorBinding
import dagger.Lazy
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

class CarTypeSelectorView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var vm: Lazy<CarTypeSelectorVM>

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupDataBinding()
    }

    private fun setupDataBinding() {
        DataBindingUtil.bind<CarTypeSelectorBinding>(this).vm = vm.get()
    }
}
