package com.example.feature_onboarding.signup;

import com.example.core_auth.provider.AuthProvider;
import com.example.core_auth.provider.di.AuthProviderModule;
import com.example.feature_onboarding.signup.di.SignUpScope;
import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

@SignUpScope
public class SignUpInteractor implements Interactor {

    public interface SignUpListener {

        void onSignUp();
    }
    public interface StartLogInListener {

        void startLogIn();
    }

    private final SignUpRouter router;
    private final SignUpPresenter presenter;
    private final StartLogInListener startLogInListener;
    private final SignUpListener signUpListener;

    private final AuthProvider googleProvider;
    private final AuthProvider facebookProvider;

    @Inject
    SignUpInteractor(
            SignUpRouter router,
            StartLogInListener startLogInListener,
            SignUpListener signUpListener,
            SignUpPresenter presenter,
            @Named(AuthProviderModule.GOOGLE_AUTH_PROVIDER) AuthProvider googleProvider,
            @Named(AuthProviderModule.FACEBOOK_AUTH_PROVIDER) AuthProvider facebookProvider) {
        this.googleProvider = googleProvider;
        this.facebookProvider = facebookProvider;
        this.startLogInListener = startLogInListener;
        this.signUpListener = signUpListener;
        this.presenter = presenter;
        this.router = router;
        onGetActive();
    }

    @Override
    public void onGetActive() {
        presenter.getFacebookClickStream().subscribe(this::signUpWithFacebook, this::onError);
        presenter.getGoogleClickStream().subscribe(this::signUpWithGoogle, this::onError);
        presenter.getSignUpClickStream().subscribe(o -> signUp(), this::onError);
    }

    private void signUpWithGoogle(String phone) {
        presenter.showProgress();
        googleProvider.signUp(phone)
                .subscribe(this::onSignUp, this::onError);
    }

    private void signUpWithFacebook(String phone) {
        presenter.showProgress();
        facebookProvider.signUp(phone)
                .subscribe(this::onSignUp, this::onError);
    }

    private void signUp() {
        startLogInListener.startLogIn();
    }

    private void onSignUp() {
        presenter.hideProgress();
        signUpListener.onSignUp();
    }

    private void onError(Throwable error) {
        presenter.hideProgress();
        presenter.showMessage("Error");
    }
}
