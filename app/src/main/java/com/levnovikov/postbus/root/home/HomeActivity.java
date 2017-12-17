package com.levnovikov.postbus.root.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.di.HomeModule;
import com.levnovikov.system_base.base_di.SubComponentProvider;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

public class HomeActivity extends AppCompatActivity {

    @Inject
    HomeView view;

    @Inject
    HomeInteractor interactor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setContentView(view);
    }

    private void injectDependencies() {
        final android.app.Application app = getApplication();
        if (app instanceof SubComponentProvider) {
            ((HomeComponent.Builder) ((SubComponentProvider) app).provide(HomeComponent.Builder.class))
                    .homeModule(new HomeModule(this))
                    .build()
                    .inject(this);
        }
    }
}
