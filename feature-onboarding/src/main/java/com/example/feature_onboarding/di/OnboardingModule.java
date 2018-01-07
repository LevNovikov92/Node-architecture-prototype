package com.example.feature_onboarding.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.feature_onboarding.OnboardingInteractor;
import com.example.feature_onboarding.OnboardingView;
import com.example.feature_onboarding.login.LoginInteractor;
import com.example.feature_onboarding.login.LoginNodeHolder;
import com.example.feature_onboarding.signup.SignUpNodeHolder;
import com.example.feature_onboarding.signup.SignUpInteractor;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@Module(includes = OnboardingModule.BindsModule.class)
public class OnboardingModule {

    @OnboardingScope
    @Provides
    OnboardingView provideView(Context context) {
        return new OnboardingView(context);
    }

    @OnboardingScope
    @Provides
    LoginNodeHolder provideLoginBuilder(OnboardingView view, OnboardingComponent component,
                                        LayoutInflater inflater) {
        return new LoginNodeHolder(view, component, inflater);
    }

    @OnboardingScope
    @Provides
    SignUpNodeHolder provideSignUpBuilder(OnboardingView view, OnboardingComponent component,
                                          LayoutInflater inflater) {
        return new SignUpNodeHolder(view, component, inflater);
    }

    @Module
    public interface BindsModule {
        @Binds
        @OnboardingScope
        LoginInteractor.LogInListener bindLoginListener(OnboardingInteractor impl);

        @Binds
        @OnboardingScope
        LoginInteractor.StartSignUpListener bindStartSignUp(OnboardingInteractor impl);

        @Binds
        @OnboardingScope
        SignUpInteractor.SignUpListener bindSignUpListener(OnboardingInteractor impl);

        @Binds
        @OnboardingScope
        SignUpInteractor.StartLogInListener bindStartLogin(OnboardingInteractor impl);
    }
}
