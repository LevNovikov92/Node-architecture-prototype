package com.example.feature_onboarding.signup.di;

import com.example.feature_onboarding.signup.SignUpPresenter;
import com.example.feature_onboarding.signup.SignUpView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

@Module
public class SignUpModule {

    private final SignUpView view;

    public SignUpModule(SignUpView view) {
        this.view = view;
    }

    @SignUpScope
    @Provides
    SignUpView provideView() {
        return view;
    }

    @SignUpScope
    @Provides
    SignUpPresenter providePresenter() {
        return view;
    }
}
