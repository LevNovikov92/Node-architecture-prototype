package com.example.feature_onboarding;

import com.example.feature_onboarding.di.OnboardingScope;
import com.example.feature_onboarding.login.LoginInteractor;
import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@OnboardingScope
public class OnboardingInteractor implements
        Interactor,
        LoginInteractor.LogInListener,
        LoginInteractor.StartSignUpListener {

    @Override
    public void startSignUp() {
        showSignInScreen();
    }

    public interface LogInListener {
        void onLogIn();
    }

    private final OnboardingRouter router;

    @Inject
    OnboardingInteractor(OnboardingRouter router) {
        this.router = router;
    }

    @Override
    public void onGetActive() {
        showLoginScreen();
    }

    private void showLoginScreen() {
        router.removeAll();
        router.attachLogInScreen();
    }

    private void showSignInScreen() {
        router.removeAll();
        router.attachSignInScreen();
    }

    @Override
    public void onLogIn() {
        
    }
}
