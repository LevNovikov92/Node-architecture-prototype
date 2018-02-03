package com.levnovikov.feature_auth

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

import com.levnovikov.feature_auth.view_model.AuthActions
import com.levnovikov.feature_auth.view_model.AuthViewModel

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

class AuthView : ConstraintLayout {

    @Inject
    lateinit var vmActions: AuthActions

    @Inject
    lateinit var vm: AuthViewModel

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        vmActions.onGetActive()
    }
}
