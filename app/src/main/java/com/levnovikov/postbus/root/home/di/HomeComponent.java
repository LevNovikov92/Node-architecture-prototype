package com.levnovikov.postbus.root.home.di;

import com.levnovikov.postbus.root.di.AppComponent;

import dagger.Component;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
@Component(dependencies = AppComponent.class, modules = { HomeModule.class })
public class HomeComponent {

}
