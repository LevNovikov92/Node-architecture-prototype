package com.levnovikov.postbus.root.home.prebooking.di;

import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.prebooking.PrebookingBuilder;
import com.levnovikov.postbus.root.home.prebooking.PrebookingRouter;

import dagger.Component;
import dagger.Module;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
@Component(dependencies = HomeComponent.class, modules = PrebookingComponent.PrebookingModule.class)
public interface PrebookingComponent {

    void inject(PrebookingBuilder prebookingBuilder);

    @Module
    class PrebookingModule {

    }


    PrebookingRouter getRouter();
}
