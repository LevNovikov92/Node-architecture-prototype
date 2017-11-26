package com.example.feature_onboarding.di;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 26/11/17.
 */

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Context provideContext() {
        return activity;
    }

    @Provides
    LayoutInflater provideInflater() {
        return activity.getLayoutInflater();
    }
}
