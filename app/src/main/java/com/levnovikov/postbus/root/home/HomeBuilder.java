package com.levnovikov.postbus.root.home;

import android.view.LayoutInflater;

import com.levnovikov.postbus.root.di.AppComponent;
import com.levnovikov.system_base.Builder;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

// TODO remove redundant
public class HomeBuilder implements Builder<HomeRouter> {

    private final AppComponent component;
    private final LayoutInflater inflater;

    @Inject
    HomeBuilder(AppComponent component, LayoutInflater inflater) {
        this.component = component;
        this.inflater = inflater;
    }

    @Override
    public HomeRouter build() {
        return null;
    }
}
