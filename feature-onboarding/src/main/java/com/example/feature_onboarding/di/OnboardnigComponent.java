package com.example.feature_onboarding.di;

import com.example.feature_onboarding.OnboardingActivity;
import com.example.feature_onboarding.OnboardingView;

import dagger.Component;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@OnboardingScope
@Component(modules = { ActivityModule.class, OnboardingModule.class })
public interface OnboardnigComponent {

    OnboardingView provideView();

    OnboardnigComponent provideComponent();

    void inject(OnboardingActivity onboardingActivity);
}
