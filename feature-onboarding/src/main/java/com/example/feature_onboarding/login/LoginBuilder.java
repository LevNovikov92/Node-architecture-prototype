package com.example.feature_onboarding.login;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.feature_onboarding.di.OnboardingComponent;
import com.example.feature_onboarding.login.di.DaggerLoginComponent;
import com.example.feature_onboarding.login.di.LoginComponent;
import com.example.feature_onboarding.login.di.LoginModule;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class LoginBuilder {

    @Inject
    LoginInteractor interactor;

    private final ViewGroup parent;
    private final OnboardingComponent parentComponent;
    private final LayoutInflater inflater;

    private LoginComponent component;

    public LoginBuilder(ViewGroup parent, OnboardingComponent component, LayoutInflater inflater) {
        this.parentComponent = component;
        this.parent = parent;
        this.inflater = inflater;
    }

    public void removeAllViews() {
        // TODO
    }


    public LoginRouter build() {
        component = DaggerLoginComponent.builder()
                .onboardingComponent(parentComponent)
                .loginModule(new LoginModule(buildView(parent)))
                .build();
        component.inject(this);
        parent.addView(component.view());
        return component.router();
    }

    private LoginView buildView(ViewGroup parent) {
        return (LoginView) inflater.inflate(LoginView.layout, parent, false);
    }
}
