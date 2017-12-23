package com.levnovikov.postbus.root.home;

import com.levnovikov.postbus.root.home.allocating.AllocatingBuilder;
import com.levnovikov.postbus.root.home.di.HomeScope;
import com.levnovikov.postbus.root.home.prebooking.PrebookingBuilder;
import com.levnovikov.postbus.root.home.prebooking.PrebookingRouter;
import com.levnovikov.stream_state.AppState;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
class HomeRouter extends Router {

    private final PrebookingBuilder prebookingBuilder;
    private AllocatingBuilder allocatingBuilder;

    @Inject
    HomeRouter(PrebookingBuilder prebookingBuilder, AllocatingBuilder allocatingBuilder) {
        this.prebookingBuilder = prebookingBuilder;
        this.allocatingBuilder = allocatingBuilder;
    }

    void startPrebooking() {
        final PrebookingRouter router = prebookingBuilder.build();
        detachAll();
        attachRouter(router);
    }

    void startAllocating() {
        detachAll();
        attachRouter(allocatingBuilder.build());
    }

    void startTracking() {

    }

    void switchState(AppState state) {
        switch (state) {
            case PREBOOKING:
                startPrebooking();
                break;
            case ALLOCATING:
                startAllocating();
                break;
            case TRACKING:
                startTracking();
                break;
        }
    }

    @Override
    protected void detach() {

    }
}
