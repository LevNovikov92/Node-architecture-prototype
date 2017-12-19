package com.example.feature_onboarding.login;

import io.reactivex.Observable;

/**
 * Created by lev.novikov
 * Date: 25/11/17.
 */

public interface LoginPresenter {
    void showProgress();
    void hideProgress();
    void showMessage(String error);

    Observable<Void> getFacebookClickStream();

    Observable<Void> getGoogleClickStream();

    Observable<Void> getSignUpClickStream();
}
