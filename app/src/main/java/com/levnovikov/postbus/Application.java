package com.levnovikov.postbus;

import com.levnovikov.postbus.root.RootInteractor;
import com.levnovikov.postbus.root.di.AppComponent;
import com.levnovikov.postbus.root.di.AppModule;
import com.levnovikov.postbus.root.di.DaggerAppComponent;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

public class Application extends android.app.Application {

    private AppComponent appComponent;

    @Inject public RootInteractor rootInteractor;

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
    }

    private void injectDependencies() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}
