package com.levnovikov.postbus.root.home.di;

import android.app.Activity;
import android.content.Context;

import com.levnovikov.postbus.root.home.HomeView;
import com.levnovikov.stream_state.AppState;
import com.levnovikov.stream_state.AppStateStreamProvider;

import dagger.Provides;
import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

class HomeModule {

    private Activity activity;

    HomeModule(Activity activity) {
        this.activity = activity;
    }

    @HomeScope
    @Provides
    Context provideContext() {
        return activity;
    }

    @HomeScope
    @Provides
    HomeView provideView(Context context) {
        return new HomeView(context);
    }

    @HomeScope
    @Provides
    Observable<AppState> provideAppStateStream(AppStateStreamProvider provider) {
        return provider.provideAppStateStream();
    }
}
