package com.example.feature_onboarding.login;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by lev.novikov
 * Date: 25/11/17.
 */

public interface LoginPresenter {
    void showProgress();
    void hideProgress();
    void showMessage(String error);

    PublishSubject<Object> getFacebookClickStream();

    PublishSubject<Object> getGoogleClickStream();

    PublishSubject<Object> getSignUpClickStream();
}
