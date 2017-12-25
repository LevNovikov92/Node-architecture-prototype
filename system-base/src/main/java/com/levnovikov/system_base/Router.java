package com.levnovikov.system_base;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

public abstract class Router {

    private final Map<Class<? extends Router>, Router> children = new HashMap<>();

    protected void attachRouter(Router router) {
//        Log.i(">>>>", "attachRouter " + router.getClass().getSimpleName() + " from " +
//                this.getClass().getSimpleName());
//        if (children.containsKey(router.getClass())) {
//            throw new UnsupportedOperationException(String.format("%s already attached", router.getClass()));
//        }
//        children.put(router.getClass(), router);
    }

    protected void detachRouter(Class<? extends Router> router) {
//        Log.i(">>>>", "detachRouter " + router.getClass().getSimpleName() + " from " +
//                this.getClass().getSimpleName());
//        children.remove(router);
    }

    protected void detachChildren() {
//        Log.i(">>>>", "detachChildren " + this.getClass().getSimpleName());
//        children.clear();
    }

    protected abstract void destroyNode();
}
