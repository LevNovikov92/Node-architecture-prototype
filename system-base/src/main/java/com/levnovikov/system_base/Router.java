package com.levnovikov.system_base;

import android.os.Parcelable;
import android.util.Log;

import com.levnovikov.system_base.back_handling.BackHandler;
import com.levnovikov.system_base.state.NodeState;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.Nullable;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

public abstract class Router {

    private final Map<Class<? extends Router>, Router> children = new HashMap<>();

    @Nullable
    private StateDataProvider stateDataProvider; //TODO set to null on node destroy

    @Nullable
    private BackHandler backHandler;

    final void setStateDataProvider(StateDataProvider provider) {
        stateDataProvider = provider;
    }

    final void setBackHandler(BackHandler handler) {
        backHandler = handler;
    }

    protected final void attachRouter(Router router) {
        Log.i(">>>>", "attachRouter " + router.getClass().getSimpleName() + " from " +
                this.getClass().getSimpleName());
        if (children.containsKey(router.getClass())) {
            throw new UnsupportedOperationException(String.format("%s already attached", router.getClass()));
        }
        children.put(router.getClass(), router);
    }

    protected final void detachRouter(Class<? extends Router> router) {
        Log.i(">>>>", "detachRouter " + router.getClass().getSimpleName() + " from " +
                this.getClass().getSimpleName());
        children.remove(router);
    }

    protected final void detachChildren() {
        Log.i(">>>>", "detachChildren " + this.getClass().getSimpleName());
        children.clear();
    }

    private Map<String, NodeState> getChildrenState() {
        final Map<String, NodeState> stateMap = new HashMap<>();
        for (Router router : children.values()) {
            stateMap.putAll(router.getState());
        }
        return stateMap;
    }

    protected abstract void destroyNode();

    public abstract NodeState getNodeState(@Nullable Parcelable stateData);

    public final Map<String, NodeState> getState() {
        final Map<String, NodeState> state = getChildrenState();
        final NodeState nodeState = getNodeState(stateDataProvider != null ? stateDataProvider.getStateData() : null);
        state.put(nodeState.routerClass(), nodeState);
        return state;
    }

    public abstract void setState(NodeState state);

    @SuppressWarnings("SimplifiableConditionalExpression")
    public final boolean onBackPressed() {
        for (Router router: children.values()) {
            if (router.onBackPressed()) {
                return true;
            }
        }
        return backHandler != null ? backHandler.onBackPressed() : false;
    }
}
