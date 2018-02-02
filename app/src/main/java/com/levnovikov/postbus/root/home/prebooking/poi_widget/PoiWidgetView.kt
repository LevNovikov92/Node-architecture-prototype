package com.levnovikov.postbus.root.home.prebooking.poi_widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.levnovikov.postbus.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

class PoiWidgetView : LinearLayout, PoiWidgetInteractor.Presenter {

    @Inject
    lateinit var interactor: PoiWidgetInteractor

    private val pickUpSubject = PublishSubject.create<Any>()
    private val dropOffSubject = PublishSubject.create<Any>()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onPickUpClick(): Observable<Any> {
        return pickUpSubject
    }

    override fun onDropOffClick(): Observable<Any> {
        return dropOffSubject
    }

    override fun setPickUp(placeTitle: String) {
        (findViewById<View>(R.id.pickUpButton) as Button).text = placeTitle
    }

    override fun setDropOff(placeTitle: String) {
        (findViewById<View>(R.id.dropOffButton) as Button).text = placeTitle
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initView()
        interactor.onGetActive()
    }

    private fun initView() {
        findViewById<View>(R.id.pickUpButton).setOnClickListener({ v ->
            Log.i(">>>", "click")
            pickUpSubject.onNext(Any())
        })
        findViewById<View>(R.id.dropOffButton).setOnClickListener({ v -> dropOffSubject.onNext(Any()) })
    }
}
