package com.example.feature_onboarding.login;

import com.example.core_auth.provider.AuthProvider;
import com.example.core_auth.provider.di.AuthProviderModule;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class LoginInteractor {

    public interface LogInListener {
        void onLogIn();
    }

    private final LoginRouter router;
    private final AuthProvider googleProvider;
    private final AuthProvider facebookProvider;

    @Inject
    LoginInteractor(
            LoginRouter router,
            @Named(AuthProviderModule.GOOGLE_AUTH_PROVIDER) AuthProvider googleProvider,
            @Named(AuthProviderModule.FACEBOOK_AUTH_PROVIDER) AuthProvider facebookProvider) {
        this.router = router;
        this.googleProvider = googleProvider;
        this.facebookProvider = facebookProvider;
    }

    void loginWithGoogle() {
        googleProvider.login()
                .subscribe(this::onLogIn, this::onError);
    }

    void loginWithFacebook() {
        facebookProvider.login()
                .subscribe(this::onLogIn, this::onError);
    }

    private void onLogIn() {
        //TODO communicate with parent
    }

    private void onError(Throwable error) {
        //TODO communicate with parent
    }
}
