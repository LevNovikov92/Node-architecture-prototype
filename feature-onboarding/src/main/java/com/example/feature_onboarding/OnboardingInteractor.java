package com.example.feature_onboarding;

import com.example.feature_onboarding.login.LoginInteractor;
import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

public class OnboardingInteractor implements Interactor, LoginInteractor.LogInListener {

    public interface LogInListener {
        void onLogIn();
    }

    private final OnboardingRouter router;

    @Inject
    OnboardingInteractor(OnboardingRouter router) {
        this.router = router;
        onGetActive();
    }

    @Override
    public void onGetActive() {
        showLoginScreen();
    }

    private void showLoginScreen() {
        router.removeAll();
        router.attachLogInScreen();
    }

    public void showSigninScreen() {
        router.removeAll();
        router.attachSignInScreen();
    }

    @Override
    public void onLogIn() {
        
    }
}
