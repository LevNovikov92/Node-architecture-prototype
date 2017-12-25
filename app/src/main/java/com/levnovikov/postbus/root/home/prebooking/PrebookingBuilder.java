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

    @Inject
    PrebookingInteractor interactor;

    @Inject
    PrebookingRouter router;

    public PrebookingBuilder(HomeComponent component) {
        this.component = component;
    }

    @Override
    public PrebookingRouter build() {
        final PrebookingComponent cmp = DaggerPrebookingComponent.builder()
                .homeComponent(component)
                .build();
        cmp.inject(this);
        interactor.onGetActive();
        return router;
    }

    @Override
    public void destroy() {
//        interactor.onDestroy(); TODO
        if (router != null) {
            router.destroyNode();
        }
    }
}
