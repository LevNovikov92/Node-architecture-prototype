package com.levnovikov.system_base;

import android.util.Log;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 *
 * NodeHolder
 */

public abstract class NodeHolder<R extends Router> {

    @Inject
    public R router;

    public abstract R build();

    public void destroy() {
        Log.i(">>>>", "destroy " + this.getClass().getSimpleName());
        if (router != null) {
            router.destroyNode();
            router.removeFromBackStack();
            router = null;
        }
    }

    public boolean isActive() {
        return router != null;
    }
}
