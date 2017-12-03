package com.example.feature_onboarding.di;

import com.example.feature_onboarding.OnboardingActivity;
import com.example.feature_onboarding.OnboardingView;
import com.example.feature_onboarding.login.LoginInteractor;
import com.example.feature_onboarding.signup.SignUpInteractor;
import com.levnovikov.system_base.base_di.ComponentBuilder;

import dagger.Subcomponent;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@OnboardingScope
@Subcomponent(modules = { ActivityModule.class, OnboardingModule.class })
public interface OnboardingComponent {

    OnboardingView provideView();
//    OnboardingComponent provideComponent();
    LoginInteractor.LogInListener provideLogInListener();
    LoginInteractor.StartSignUpListener provideStartSignUpListener();
    SignUpInteractor.SignUpListener provideSignUpListener();
    SignUpInteractor.StartLogInListener provideStartLogInListener();

    void inject(OnboardingActivity onboardingActivity);

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder {
        Builder activityModule(ActivityModule module);
        OnboardingComponent build();
    }
}
