package com.example.feature_onboarding.di;

import com.levnovikov.system_base.base_di.ComponentBuilder;
import com.levnovikov.system_base.base_di.ComponentKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by lev.novikov
 * Date: 2/12/17.
 */

@Module(subcomponents = OnboardingComponent.class)
public interface OnboardingModuleComponentBuilder {

    @Binds
    @IntoMap
    @ComponentKey(OnboardingComponent.Builder.class)
    ComponentBuilder mainActivityComponentBuilder(OnboardingComponent.Builder impl);

}
