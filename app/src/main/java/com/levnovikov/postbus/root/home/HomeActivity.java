package com.levnovikov.postbus.root.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

public class HomeActivity extends AppCompatActivity {

    @Inject
    HomeView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDependencies();
        setContentView(view);
    }

    private void setupDependencies() {

    }
}
