package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.prebooking.di.DaggerPrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.system_base.Builder;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

public class PrebookingBuilder extends Builder<PrebookingRouter> {

    private final HomeComponent component;

    public PrebookingBuilder(HomeComponent component) {
        this.component = component;
    }

    @Override
    public PrebookingRouter build() {
        final PrebookingComponent cmp = DaggerPrebookingComponent.builder()
                .homeComponent(component)
                .build();
        cmp.inject(this);
        cmp.interactor().onGetActive();
        return router;
    }
}
