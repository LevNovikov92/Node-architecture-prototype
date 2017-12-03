package com.example.feature_onboarding.signup.di;

import com.example.core_auth.provider.di.AuthProviderModule;
import com.example.feature_onboarding.di.OnboardingComponent;
import com.example.feature_onboarding.signup.SignUpBuilder;
import com.example.feature_onboarding.signup.SignUpRouter;
import com.example.feature_onboarding.signup.SignUpView;

import dagger.Component;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

@SignUpScope
@Component(modules = { SignUpModule.class, AuthProviderModule.class }, dependencies = OnboardingComponent.class)
public interface SignUpComponent {
    SignUpRouter router();
    SignUpView view();

    void inject(SignUpBuilder signUpBuilder);
}
