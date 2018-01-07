package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.prebooking.di.DaggerPrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.system_base.NodeHolder;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

public class PrebookingNodeHolder extends NodeHolder<PrebookingRouter> {

    private final HomeComponent component;

    public PrebookingNodeHolder(HomeComponent component) {
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
