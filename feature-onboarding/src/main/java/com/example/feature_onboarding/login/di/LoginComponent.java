package com.example.feature_onboarding.login.di;

import com.example.core_auth.provider.di.AuthProviderModule;
import com.example.feature_onboarding.di.OnboardnigComponent;
import com.example.feature_onboarding.login.LoginRouter;
import com.example.feature_onboarding.login.LoginView;

import dagger.Component;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

@LoginScope
@Component(modules = { LoginModule.class, AuthProviderModule.class }, dependencies = OnboardnigComponent.class)
public interface LoginComponent {
    LoginRouter router();
    LoginView view();
}
