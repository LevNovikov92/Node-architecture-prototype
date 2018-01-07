package com.levnovikov.system_base;

/**
 * Author: lev.novikov
 * Date: 6/1/18.
 */

import com.levnovikov.system_base.back_handling.BackHandler;
import com.levnovikov.system_base.node_state.ActivityState;

public abstract class BackStateInteractor<R extends Router> extends StateInteractor<R>
        implements BackHandler {

    public BackStateInteractor(R router, ActivityState activityState) {
        super(router, activityState);
        /*
         * If interactor need to handle onBackPressed
         */
        router.setBackHandler(this);
        activityState.addToBackStack(router.getClass());
    }

    @Override
    public boolean isLastInStack(Class<? extends Router> _class) {
        return activityState.isLastInBackStack(_class);
    }

    @Override
    public void popLastInStack() {
        activityState.popLastInBackStack();
    }

    @Override
    public void removeFromBackStack(Class<? extends Router> _class) {
        activityState.removeFromBackStack(_class);
    }
}
