package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.levnovikov.postbus.R
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

class BookingExtraView : LinearLayout, BookingExtraInteractor.Presenter {

    @Inject
    lateinit var interactor: BookingExtraInteractor

    private val bookingClickStream = BehaviorSubject.create<Any>()

    private val promoClickStream = BehaviorSubject.create<Any>()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initView()
        interactor.onGetActive()
    }

    private fun initView() {
        findViewById<View>(R.id.book_button).setOnClickListener({ v -> bookingClickStream.onNext(Any()) })
        findViewById<View>(R.id.promo).setOnClickListener({ v -> promoClickStream.onNext(Any()) })
    }

    override fun bookingClickStream(): Observable<Any> {
        return bookingClickStream
    }

    override fun promoClickStream(): Observable<Any> {
        return promoClickStream
    }
}
