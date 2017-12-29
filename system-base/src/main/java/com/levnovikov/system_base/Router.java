package com.levnovikov.system_base;

import android.util.Log;

import com.levnovikov.system_base.state.NodeState;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

public abstract class Router {

    private final Map<Class<? extends Router>, Router> children = new HashMap<>();

    protected void attachRouter(Router router) {
        Log.i(">>>>", "attachRouter " + router.getClass().getSimpleName() + " from " +
                this.getClass().getSimpleName());
        if (children.containsKey(router.getClass())) {
            throw new UnsupportedOperationException(String.format("%s already attached", router.getClass()));
        }
        children.put(router.getClass(), router);
    }

    protected void detachRouter(Class<? extends Router> router) {
        Log.i(">>>>", "detachRouter " + router.getClass().getSimpleName() + " from " +
                this.getClass().getSimpleName());
        children.remove(router);
    }

    protected void detachChildren() {
        Log.i(">>>>", "detachChildren " + this.getClass().getSimpleName());
        children.clear();
    }

    protected Map<String, NodeState> getChildrenState() {
        final Map<String, NodeState> stateMap = new HashMap<>();
        for (Router router : children.values()) {
            stateMap.putAll(router.getState());
        }
        return stateMap;
    }

    protected abstract void destroyNode();

    public abstract NodeState getNodeState();

    public Map<String, NodeState> getState() {
        final Map<String, NodeState> state = getChildrenState();
        final NodeState nodeState = getNodeState();
        state.put(nodeState.routerClass(), nodeState);
        return state;
    }

    public abstract void setState(NodeState state);
}
