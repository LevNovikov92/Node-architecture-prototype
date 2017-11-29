package com.example.feature_onboarding.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.feature_onboarding.OnboardingInteractor;
import com.example.feature_onboarding.OnboardingView;
import com.example.feature_onboarding.login.LoginInteractor;
import com.example.feature_onboarding.login.LoginBuilder;

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
    LoginBuilder provideLoginBuilder(OnboardingView view, OnboardingComponent component,
                                     LayoutInflater inflater) {
        return new LoginBuilder(view, component, inflater);
    }

    @Module
    public interface BindsModule {
        @Binds
        @OnboardingScope
        LoginInteractor.LogInListener bindLoginListener(OnboardingInteractor impl);

        @Binds
        @OnboardingScope
        LoginInteractor.StartSignUpListener bindStartSignUp(OnboardingInteractor impl);
    }
}
