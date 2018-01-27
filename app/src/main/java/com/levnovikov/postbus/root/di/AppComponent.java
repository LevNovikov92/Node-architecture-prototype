package com.levnovikov.postbus.root.di;

import com.levnovikov.core_profile.di.ProfileModule;
import com.levnovikov.postbus.Application;
import com.levnovikov.postbus.root.home.di.HomeComponentBuilder;

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
        HomeComponentBuilder.class })
public interface AppComponent {

    void inject(Application app);
}
