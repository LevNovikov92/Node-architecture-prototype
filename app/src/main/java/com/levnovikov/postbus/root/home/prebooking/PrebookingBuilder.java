package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.prebooking.di.DaggerPrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.system_base.Builder;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

public class PrebookingBuilder implements Builder<PrebookingRouter> {

    private final HomeComponent component;
    @Inject PrebookingInteractor interactor;

    public PrebookingBuilder(HomeComponent component) {
        this.component = component;
    }

    @Override
    public PrebookingRouter build(PrebookingComponent parentComponent) {
        final PrebookingComponent cmp = DaggerPrebookingComponent.builder()
                .homeComponent(component)
                .build();
        cmp.inject(this);
        return cmp.getRouter();
    }
}
