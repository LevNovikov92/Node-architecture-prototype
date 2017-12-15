package com.levnovikov.postbus.root.home.di;

import com.levnovikov.system_base.base_di.ComponentBuilder;
import com.levnovikov.system_base.base_di.ComponentKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by lev.novikov
 * Date: 2/12/17.
 */

@Module(subcomponents = HomeComponent.class)
public interface HomeComponentBuilder {

    @Binds
    @IntoMap
    @ComponentKey(HomeComponent.Builder.class)
    ComponentBuilder mainActivityComponentBuilder(HomeComponent.Builder impl);

}
