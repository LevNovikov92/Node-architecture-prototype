package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 1/3/18.
 */
class OptionsView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var interactor: OptionsInteractor

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        interactor.onGetActive()
    }
}