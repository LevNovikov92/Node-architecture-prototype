package com.levnovikov.postbus.root.di;

import android.content.Context;

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
    Context provideContext() {
        return appContext;
    }
}
