package com.example.feature_onboarding;

import com.example.feature_onboarding.di.OnboardingScope;
import com.example.feature_onboarding.login.LoginInteractor;
import com.example.feature_onboarding.signup.SignUpInteractor;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@OnboardingScope
public class OnboardingInteractor implements
        LoginInteractor.LogInListener,
        LoginInteractor.StartSignUpListener,
        SignUpInteractor.SignUpListener,
        SignUpInteractor.StartLogInListener {


    @Override
    public void startSignUp() {
        showSignUpScreen();
    }

    public interface LogInListener {
        void onLogIn();
    }

    private final OnboardingRouter router;
    private final LogInListener logInListener;

    @Inject
    OnboardingInteractor(OnboardingRouter router, LogInListener logInListener) {
        this.router = router;
        this.logInListener = logInListener;
    }

    public void onGetActive() {
        showLoginScreen();
    }

    private void showLoginScreen() {
        router.removeAll();
        router.attachLogInScreen();
    }

    private void showSignUpScreen() {
        router.removeAll();
        router.attachSignUpScreen();
    }

    @Override
    public void onLogIn() {
        logInListener.onLogIn();
    }

    @Override
    public void onSignUp() {
        logInListener.onLogIn();
    }

    @Override
    public void startLogIn() {
        router.removeAll();
        router.attachLogInScreen();
    }
}
