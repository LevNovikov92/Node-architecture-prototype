package com.levnovikov.postbus.root.home.prebooking.poi_widget

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.widget.LinearLayout
import com.levnovikov.postbus.databinding.PoiWidgetBinding
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

class PoiWidgetView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var vm: PoiWidgetVM

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupBinding()
    }

    private fun setupBinding() {
        DataBindingUtil.bind<PoiWidgetBinding>(this).vm = vm
    }
}
