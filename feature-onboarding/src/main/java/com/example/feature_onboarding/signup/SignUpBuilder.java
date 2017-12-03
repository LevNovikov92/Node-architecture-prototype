package com.example.feature_onboarding.signup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.feature_onboarding.di.OnboardingComponent;
import com.example.feature_onboarding.signup.di.DaggerSignUpComponent;
import com.example.feature_onboarding.signup.di.SignUpComponent;
import com.example.feature_onboarding.signup.di.SignUpModule;
import com.levnovikov.system_base.Builder;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 30/11/17.
 */

public class SignUpBuilder implements Builder<SignUpRouter> {

    @Inject
    SignUpInteractor interactor;

    private final ViewGroup parent;
    private final OnboardingComponent parentComponent;
    private final LayoutInflater inflater;

    private SignUpView signUpView;

    public SignUpBuilder(ViewGroup parent, OnboardingComponent component, LayoutInflater inflater) {
        this.parentComponent = component;
        this.parent = parent;
        this.inflater = inflater;
    }

    public void removeView() {
        if (signUpView != null) {
            parent.removeView(signUpView);
            signUpView = null;
        }
    }

    @Override
    public SignUpRouter build() {
        if (signUpView != null) {
            throw new UnsupportedOperationException("View already attached");
        }
        final SignUpComponent component = DaggerSignUpComponent.builder()
                .onboardingComponent(parentComponent)
                .signUpModule(new SignUpModule(buildView(parent)))
                .build();
        component.inject(this);
        parent.addView(component.view());
        return component.router();
    }

    private SignUpView buildView(ViewGroup parent) {
        signUpView = (SignUpView) inflater.inflate(SignUpView.layout, parent, false);
        return signUpView;
    }
}
