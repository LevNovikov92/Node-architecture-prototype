package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.widget.LinearLayout
import com.levnovikov.postbus.databinding.BookingExtraWidgetBinding
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

class BookingExtraView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var vm: BookingExtraVM

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupBinding()
    }


    private fun setupBinding() {
        DataBindingUtil.bind<BookingExtraWidgetBinding>(this).vm = vm
    }
}
