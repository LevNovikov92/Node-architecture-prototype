package com.levnovikov.system_base.state;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.levnovikov.system_base.Router;

import java.util.Map;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 29/12/17.
 */

@AutoValue
public abstract class ActivityState implements Parcelable {
    abstract Map<String, NodeState> stateMap();

    @Nullable
    public NodeState findNodeState(Class<? extends Router> routerClass) {
        return stateMap().get(routerClass.getSimpleName());
    }

    public static ActivityState create(Map<String, NodeState> stateMap) {
        return new AutoValue_ActivityState(stateMap);
    }
}
