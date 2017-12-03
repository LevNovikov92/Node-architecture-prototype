package com.example.feature_onboarding.signup;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by lev.novikov
 * Date: 25/11/17.
 */

public interface SignUpPresenter {
    void showProgress();
    void hideProgress();
    void showMessage(String error);

    PublishSubject<String> getFacebookClickStream();

    PublishSubject<String> getGoogleClickStream();

    PublishSubject<String> getSignUpClickStream();
}
