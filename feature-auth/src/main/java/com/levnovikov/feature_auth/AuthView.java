package com.levnovikov.feature_auth;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.levnovikov.feature_auth.view_model.AuthActions;
import com.levnovikov.feature_auth.view_model.AuthViewModel;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

public class AuthView extends ConstraintLayout {
    public AuthView(Context context) {
        super(context);
    }

    public AuthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AuthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Inject
    AuthActions vmActions;

    @Inject
    AuthViewModel vm;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        vmActions.onGetActive();
    }
}
