package com.levnovikov.postbus.root.home;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.levnovikov.postbus.root.home.allocating.AllocatingBuilder;
import com.levnovikov.postbus.root.home.di.HomeScope;
import com.levnovikov.postbus.root.home.prebooking.PrebookingBuilder;
import com.levnovikov.stream_state.AppState;
import com.levnovikov.system_base.state.NodeState;
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
    private @Nullable AppState currentState;

    @Inject
    HomeRouter(PrebookingBuilder prebookingBuilder, AllocatingBuilder allocatingBuilder) {
        this.prebookingBuilder = prebookingBuilder;
        this.allocatingBuilder = allocatingBuilder;
    }

    void startPrebooking() {
        allocatingBuilder.destroy();
        attachRouter(prebookingBuilder.build());
    }

    void startAllocating() {
        prebookingBuilder.destroy();
        attachRouter(allocatingBuilder.build());
    }

    void startTracking() {

    }

    void switchState(AppState state) {
        if (state == currentState) {
            return;
        }
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
        currentState = state;
    }

    @Override
    protected void destroyNode() {
        //root node will be destroyed with activity
    }

    @Override
    public NodeState getNodeState(@io.reactivex.annotations.Nullable Parcelable stateData) {
        final NodeState nodeState = NodeState.create(this.getClass(), null);
        if (prebookingBuilder.isActive())
            nodeState.activeNodes().add(prebookingBuilder.getClass().getSimpleName());
        if (allocatingBuilder.isActive())
            nodeState.activeNodes().add(allocatingBuilder.getClass().getSimpleName());
        return nodeState;
    }

    @Override
    public void setState(NodeState state) {
        if (state.activeNodes().contains(prebookingBuilder.getClass().getSimpleName())) {
            attachRouter(prebookingBuilder.build());
        }

        if (state.activeNodes().contains(allocatingBuilder.getClass().getSimpleName())) {
            attachRouter(allocatingBuilder.build());
        }
    }
}
