package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.widget.FrameLayout
import com.levnovikov.postbus.databinding.OptionsViewBinding
import dagger.Lazy
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 1/3/18.
 */
class OptionsView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var vm: Lazy<OptionsVM>

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupBinding()
    }

    private fun setupBinding() {
        DataBindingUtil.bind<OptionsViewBinding>(this).vm = vm.get()
    }
}