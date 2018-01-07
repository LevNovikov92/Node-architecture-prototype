package com.levnovikov.system_base.node_state;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.levnovikov.system_base.Router;

import java.util.Map;
import java.util.Stack;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 29/12/17.
 */

@AutoValue
public abstract class ActivityState implements Parcelable {
    abstract Map<String, NodeState> stateMap();

    private Stack<String> backStack = new Stack<>();

    @Nullable
    public NodeState findNodeState(Class<? extends Router> routerClass) {
        return stateMap().get(routerClass.getSimpleName());
    }

    public static ActivityState create(Map<String, NodeState> stateMap) {
        return new AutoValue_ActivityState(stateMap);
    }

    public void addToBackStack(Class<? extends Router> _class) {
        backStack.add(_class.getSimpleName()); //TODO replace to canonical name after testing
    }

    public boolean isLastInBackStack(Class<? extends Router> _class) {
        return !backStack.isEmpty() && backStack.peek().equals(_class.getSimpleName()); //TODO replace to canonical name after testing
    }

    public void popLastInBackStack() {
        backStack.pop();
    }

    public void removeFromBackStack(Class<? extends Router> _class) {
        backStack.remove(_class.getSimpleName()); //TODO replace to canonical name after testing
    }
}
