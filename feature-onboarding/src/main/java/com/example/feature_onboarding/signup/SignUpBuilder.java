package com.example.feature_onboarding.signup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.feature_onboarding.di.OnboardingComponent;
import com.example.feature_onboarding.signup.di.DaggerSignUpComponent;
import com.example.feature_onboarding.signup.di.SignUpComponent;
import com.example.feature_onboarding.signup.di.SignUpModule;
import com.levnovikov.system_base.ViewBuilder;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 30/11/17.
 */

public class SignUpBuilder extends ViewBuilder<SignUpView, SignUpRouter> {

    @Inject
    SignUpInteractor interactor;

    private final OnboardingComponent parentComponent;

    public SignUpBuilder(ViewGroup parent, OnboardingComponent component, LayoutInflater inflater) {
        super(inflater, parent);
        this.parentComponent = component;
    }

    @Override
    public SignUpRouter build() {
        if (view != null) {
            throw new UnsupportedOperationException("View already attached");
        }
        final SignUpComponent component = DaggerSignUpComponent.builder()
                .onboardingComponent(parentComponent)
                .signUpModule(new SignUpModule(buildView()))
                .build();
        component.inject(this);
        parent.addView(component.view());
        return component.router();
    }

    @Override
    public int getLayout() {
        return SignUpView.layout; //TODO refactor
    }
}
