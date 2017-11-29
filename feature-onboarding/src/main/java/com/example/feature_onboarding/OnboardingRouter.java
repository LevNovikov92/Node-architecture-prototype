package com.example.feature_onboarding;

import com.example.feature_onboarding.di.OnboardingScope;
import com.example.feature_onboarding.login.LoginBuilder;
import com.example.feature_onboarding.login.LoginRouter;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@OnboardingScope
public class OnboardingRouter extends Router {

    private final LoginBuilder loginBuilder;
    private final OnboardingView view;

    @Inject
    OnboardingRouter(OnboardingView view, LoginBuilder loginBuilder) {
        this.view = view;
        this.loginBuilder = loginBuilder;
    }

    public void attachLogInScreen() {
        final LoginRouter router = loginBuilder.build();
        attachRouter(router);
    }

    public void removeAll() {
        loginBuilder.removeAllViews();
    }

    public void attachSignInScreen() {

    }
}
