package com.example.feature_onboarding.signup;

import io.reactivex.Observable;

/**
 * Created by lev.novikov
 * Date: 25/11/17.
 */

public interface SignUpPresenter {
    void showProgress();
    void hideProgress();
    void showMessage(String error);

    Observable<String> getFacebookClickStream();

    Observable<String> getGoogleClickStream();

    Observable<String> getSignUpClickStream();
}
