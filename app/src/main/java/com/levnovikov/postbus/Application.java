package com.levnovikov.postbus;

import com.levnovikov.postbus.root.RootInteractor;
import com.levnovikov.postbus.root.di.AppComponent;
import com.levnovikov.postbus.root.di.AppModule;
import com.levnovikov.postbus.root.di.DaggerAppComponent;
import com.levnovikov.system_base.base_di.ComponentBuilder;
import com.levnovikov.system_base.base_di.SubComponentProvider;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

public class Application extends android.app.Application implements SubComponentProvider {

    private AppComponent appComponent;

    @Inject public RootInteractor rootInteractor;

    @Inject
    Map<Class<? extends ComponentBuilder>, ComponentBuilder> subComponents;


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

    @Override
    public <C extends ComponentBuilder> C provide(Class<C> key) {
        return (C) subComponents.get(key);
    }
}
