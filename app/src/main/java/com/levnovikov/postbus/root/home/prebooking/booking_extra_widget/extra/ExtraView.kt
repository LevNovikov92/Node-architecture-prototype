package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 3/2/18.
 */
class ExtraView : FrameLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @Inject
    lateinit var interactor: ExtraInteractor

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        interactor.onGetActive()
    }
}