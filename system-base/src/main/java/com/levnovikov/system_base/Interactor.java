package com.levnovikov.system_base;

import com.levnovikov.system_base.state.ActivityState;
import com.levnovikov.system_base.state.NodeState;

public abstract class Interactor<R extends Router> {

    protected R router;
    protected ActivityState activityState;

    public Interactor(R router, ActivityState activityState) {
        this.router = router;
        this.activityState = activityState;
    }

    public void onGetActive() {
        final NodeState state = activityState.findNodeState(router.getClass());
        if (state != null) {
            router.setState(state);
        }
    }
}
