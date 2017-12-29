package com.example.feature_onboarding.login;

import com.example.core_auth.provider.AuthProvider;
import com.example.core_auth.provider.di.AuthProviderModule;
import com.example.feature_onboarding.login.di.LoginScope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

@LoginScope
public class LoginInteractor {


    public interface LogInListener {
        void onLogIn();
    }

    public interface StartSignUpListener {
        void startSignUp();
    }

    private final LoginPresenter presenter;
    private final LoginRouter router;
    private final LogInListener loginListener;
    private final StartSignUpListener startSignUpListener;

    private final AuthProvider googleProvider;
    private final AuthProvider facebookProvider;

    @Inject
    LoginInteractor(
            LoginRouter router,
            LogInListener loginListener,
            StartSignUpListener startSignUpListener,
            LoginPresenter presenter,
            @Named(AuthProviderModule.GOOGLE_AUTH_PROVIDER) AuthProvider googleProvider,
            @Named(AuthProviderModule.FACEBOOK_AUTH_PROVIDER) AuthProvider facebookProvider) {
        this.router = router;
        this.googleProvider = googleProvider;
        this.facebookProvider = facebookProvider;
        this.loginListener = loginListener;
        this.startSignUpListener = startSignUpListener;
        this.presenter = presenter;
        onGetActive();
    }

    public void onGetActive() {
        presenter.getFacebookClickStream().subscribe(o -> loginWithFacebook(), this::onError);
        presenter.getGoogleClickStream().subscribe(o -> loginWithGoogle(), this::onError);
        presenter.getSignUpClickStream().subscribe(o -> signUp(), this::onError);
    }

    private void loginWithGoogle() {
        presenter.showProgress();
        googleProvider.login()
                .subscribe(this::onLogIn, this::onError);
    }

    private void loginWithFacebook() {
        presenter.showProgress();
        facebookProvider.login()
                .subscribe(this::onLogIn, this::onError);
    }

    private void signUp() {
        startSignUpListener.startSignUp();
    }

    private void onLogIn() {
        presenter.hideProgress();
        loginListener.onLogIn();
    }

    private void onError(Throwable error) {
        presenter.hideProgress();
        presenter.showMessage("Error");
    }
}
