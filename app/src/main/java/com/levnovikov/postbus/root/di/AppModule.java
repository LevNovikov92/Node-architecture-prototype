package com.levnovikov.postbus.root.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 21/11/17.
 */

@Module
public class AppModule {

    private final Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    @Named("AppContext")
    Context provideContext() {
        return appContext;
    }
}
