package com.levnovikov.postbus.root.home.prebooking.car_type_selector

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.levnovikov.postbus.R
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

class CarTypeSelectorView : ConstraintLayout, CarTypeSelectorInteractor.Presenter {

    @Inject
    lateinit var interactor: CarTypeSelectorInteractor


    private val clickStream = BehaviorSubject.create<Any>()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initView()
        interactor.onGetActive()
    }

    private fun initView() {
        findViewById<View>(R.id.layout).setOnClickListener({ v -> clickStream.onNext(Any()) })
    }

    override fun clickStream(): Observable<Any> {
        return clickStream
    }
}
