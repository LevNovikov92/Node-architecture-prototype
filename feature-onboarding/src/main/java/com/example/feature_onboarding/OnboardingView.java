package com.example.feature_onboarding;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

public class OnboardingView extends FrameLayout {

    public OnboardingView(Context context) {
        this(context, null);
    }

    public OnboardingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OnboardingView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}