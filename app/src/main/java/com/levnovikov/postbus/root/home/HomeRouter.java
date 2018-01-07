package com.levnovikov.postbus.root.home;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.levnovikov.feature_map.MapBuilder;
import com.levnovikov.feature_map.map_wrapper.MapInterface;
import com.levnovikov.postbus.root.home.allocating.AllocatingBuilder;
import com.levnovikov.postbus.root.home.di.HomeScope;
import com.levnovikov.postbus.root.home.prebooking.PrebookingBuilder;
import com.levnovikov.stream_state.AppState;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.state.NodeState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
class HomeRouter extends Router {

    private final PrebookingBuilder prebookingBuilder;
    private final AllocatingBuilder allocatingBuilder;
    private final MapBuilder mapBuilder;
    private @Nullable AppState currentState;

    @Inject
    HomeRouter(PrebookingBuilder prebookingBuilder, AllocatingBuilder allocatingBuilder, MapBuilder mapBuilder) {
        this.prebookingBuilder = prebookingBuilder;
        this.allocatingBuilder = allocatingBuilder;
        this.mapBuilder = mapBuilder;
    }

    void startPrebooking(MapInterface mapInterface) {
        detachNode(allocatingBuilder);
        prebookingBuilder.setMapInterface(mapInterface);
        attachNode(prebookingBuilder);
    }

    void startAllocating() {
        detachNode(prebookingBuilder);
        attachNode(allocatingBuilder);
    }

    void loadMap() {
        attachNode(mapBuilder);
    }

    void startTracking() {

    }

    void switchState(AppState state, MapInterface mapInterface) {
        if (state == currentState) {
            return;
        }
        switch (state) {
            case PREBOOKING:
                startPrebooking(mapInterface);
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
            nodeState.addNodeBuilder(prebookingBuilder.getClass());
        if (allocatingBuilder.isActive())
            nodeState.addNodeBuilder(allocatingBuilder.getClass());
        if (mapBuilder.isActive())
            nodeState.addNodeBuilder(mapBuilder.getClass());
        return nodeState;
    }

    @Override
    public void setState(NodeState state) {
        if (state.contains(prebookingBuilder.getClass())) {
            attachNode(prebookingBuilder);
        }

        if (state.contains(allocatingBuilder.getClass())) {
            attachNode(allocatingBuilder);
        }

        if (state.contains(mapBuilder.getClass())) {
            attachNode(mapBuilder);
        }
    }
}
