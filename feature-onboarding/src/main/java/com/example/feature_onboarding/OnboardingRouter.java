package com.example.feature_onboarding;

import com.example.feature_onboarding.di.OnboardingScope;
import com.example.feature_onboarding.login.LoginBuilder;
import com.example.feature_onboarding.login.LoginRouter;
import com.example.feature_onboarding.signup.SignUpBuilder;
import com.example.feature_onboarding.signup.SignUpRouter;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@OnboardingScope
public class OnboardingRouter extends Router {

    private final LoginBuilder loginBuilder;
    private final SignUpBuilder signUpBuilder;

    @Inject
    OnboardingRouter(LoginBuilder loginBuilder, SignUpBuilder signUpBuilder) {
        this.loginBuilder = loginBuilder;
        this.signUpBuilder = signUpBuilder;
    }

    void attachLogInScreen() {
        final LoginRouter router = loginBuilder.build();
        attachRouter(router);
    }

    void removeAll() {
        loginBuilder.removeView();
        signUpBuilder.removeView();
        detachAll();
    }

    void attachSignUpScreen() {
        final SignUpRouter router = signUpBuilder.build();
        attachRouter(router);
    }
}
