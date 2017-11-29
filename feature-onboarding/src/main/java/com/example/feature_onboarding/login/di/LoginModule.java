package com.example.feature_onboarding.login.di;

import com.example.feature_onboarding.login.LoginPresenter;
import com.example.feature_onboarding.login.LoginRouter;
import com.example.feature_onboarding.login.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

@Module
public class LoginModule {

    private final LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @LoginScope
    @Provides
    LoginRouter provideLoginRouter() {
        return new LoginRouter();
    }

    @LoginScope
    @Provides
    LoginView provideView() {
        return view;
    }

    @LoginScope
    @Provides
    LoginPresenter providePresenter() {
        return view;
    }
}
