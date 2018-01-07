package com.example.feature_onboarding.signup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.feature_onboarding.R;
import com.example.feature_onboarding.di.OnboardingComponent;
import com.example.feature_onboarding.signup.di.DaggerSignUpComponent;
import com.example.feature_onboarding.signup.di.SignUpComponent;
import com.example.feature_onboarding.signup.di.SignUpModule;
import com.levnovikov.system_base.ViewNodeHolder;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 30/11/17.
 */

public class SignUpNodeHolder extends ViewNodeHolder<SignUpView, SignUpRouter> {

    @Inject
    SignUpInteractor interactor;

    @Inject
    SignUpRouter router;

    private final OnboardingComponent parentComponent;

    public SignUpNodeHolder(ViewGroup parent, OnboardingComponent component, LayoutInflater inflater) {
        super(inflater, parent);
        this.parentComponent = component;
    }

    @Override
    public SignUpRouter build() {
        final SignUpView view = buildView();
        final SignUpComponent component = DaggerSignUpComponent.builder()
                .onboardingComponent(this.parentComponent)
                .signUpModule(new SignUpModule(view))
                .build();
        component.inject(this);
        component.inject(view);
        attachView();
        return component.router();
    }

    @Override
    public int getLayout() {
        return R.layout.onboarding_signup; //TODO refactor
    }
}
