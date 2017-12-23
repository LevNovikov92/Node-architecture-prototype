package com.example.feature_onboarding.login;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.feature_onboarding.R;
import com.example.feature_onboarding.di.OnboardingComponent;
import com.example.feature_onboarding.login.di.DaggerLoginComponent;
import com.example.feature_onboarding.login.di.LoginComponent;
import com.example.feature_onboarding.login.di.LoginModule;
import com.levnovikov.system_base.ViewBuilder;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class LoginBuilder extends ViewBuilder<LoginView, LoginRouter> {

    @Inject
    LoginInteractor interactor;

    private final OnboardingComponent parentComponent;

    public LoginBuilder(ViewGroup parent, OnboardingComponent component, LayoutInflater inflater) {
        super(inflater, parent);
        this.parentComponent = component;
    }

    @Override
    public LoginRouter build() {
        if (view != null) {
            throw new UnsupportedOperationException("View already attached");
        }
        final LoginComponent component = DaggerLoginComponent.builder()
                .onboardingComponent(this.parentComponent)
                .loginModule(new LoginModule(buildAndAttachView()))
                .build();
        component.inject(this);
        parent.addView(component.view());
        return component.router();
    }

    @Override
    public int getLayout() {
        return R.layout.onboarding_login;
    }
}
