package com.levnovikov.system_base;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

public class Router {

    private final Map<Class<? extends Router>, Router> children = new HashMap<>();

    protected void attachRouter(Router router) {
        if (children.containsKey(router.getClass())) {
            throw new UnsupportedOperationException(String.format("%s already attached", router.getClass()));
        }
        children.put(router.getClass(), router);
    }

    protected void detachRouter(Class<? extends Router> router) {
        children.remove(router);
    }

    protected void detachAll() {
        children.clear();
    }
}
