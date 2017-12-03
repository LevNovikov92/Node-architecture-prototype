package com.levnovikov.postbus.root.di;

import com.example.feature_onboarding.OnboardingInteractor;
import com.levnovikov.postbus.root.RootInteractor;

import dagger.Binds;
import dagger.Module;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@Module
interface RootModule {

    @Binds
    OnboardingInteractor.LogInListener bindLogInListener(RootInteractor interactor);
}
