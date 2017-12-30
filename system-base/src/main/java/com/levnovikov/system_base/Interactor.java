package com.levnovikov.system_base;

import android.os.Parcelable;

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
        final NodeState state = getNodeState();
        if (state != null) {
            router.setState(state);
        }
    }

    private NodeState getNodeState() {
        return activityState.findNodeState(router.getClass());
    }

    protected Parcelable getNodeStateData() {
        final NodeState state = getNodeState();
        return state != null ? state.data() : null;
    }

    protected boolean hasSavedState() {
        return activityState.findNodeState(router.getClass()) != null;
    }
}
