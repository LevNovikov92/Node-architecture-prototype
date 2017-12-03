package com.levnovikov.postbus.root.di;

import android.content.Context;

import com.example.feature_onboarding.OnboardingInteractor;
import com.example.feature_onboarding.di.OnboardingModuleComponentBuilder;
import com.levnovikov.core_profile.di.ProfileModule;
import com.levnovikov.postbus.Application;

import javax.inject.Named;

import dagger.Component;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

@RootScope
@Component(modules = {
        AppModule.class,
        ProfileModule.class,
        RootModule.class,
        OnboardingModuleComponentBuilder.class})
public interface AppComponent {

    @Named("AppContext") Context applicationContext();
    OnboardingInteractor.LogInListener logInListener();

    void inject(Application app);
}
