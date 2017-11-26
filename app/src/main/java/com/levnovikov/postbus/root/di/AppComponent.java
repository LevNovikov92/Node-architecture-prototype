package com.levnovikov.postbus.root.di;

import android.content.Context;

import com.levnovikov.core_profile.di.ProfileModule;
import com.levnovikov.postbus.Application;

import dagger.Component;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

@Component(modules = {
        AppModule.class,
        ProfileModule.class,
        RootModule.class })
public interface AppComponent {
    Context applicationContext();

    void inject(Application app);
}
