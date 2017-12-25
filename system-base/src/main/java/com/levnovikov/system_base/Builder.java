package com.levnovikov.system_base;

import android.util.Log;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

public abstract class Builder<R extends Router> {

    @Inject
    public R router;

    public abstract R build();

    public void destroy() {
        Log.i(">>>>", "destroy " + this.getClass().getSimpleName());
//        interactor.onDestroy(); TODO
        if (router != null) {
            router.destroyNode();
            router = null;
        }
    }
}
