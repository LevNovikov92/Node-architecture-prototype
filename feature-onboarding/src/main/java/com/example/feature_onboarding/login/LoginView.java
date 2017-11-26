package com.example.feature_onboarding.login;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.example.feature_onboarding.R;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class LoginView extends ConstraintLayout {

    static final int layout = R.layout.onboarding_login;

    public LoginView(Context context) {
        super(context);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
