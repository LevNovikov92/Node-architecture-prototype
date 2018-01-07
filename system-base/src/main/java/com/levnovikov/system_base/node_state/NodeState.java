package com.levnovikov.system_base.node_state;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.levnovikov.system_base.NodeHolder;
import com.levnovikov.system_base.Router;

import java.util.HashSet;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 27/12/17.
 */

@AutoValue
public abstract class NodeState implements Parcelable {

    public abstract String routerClass();

    @Nullable
    public abstract Parcelable data();

    abstract HashSet<String> activeNodes();

    public static NodeState create(Class<? extends Router> routerClass, @Nullable Parcelable data) {
        return new AutoValue_NodeState(routerClass.getSimpleName(), data, new HashSet<>());
    }

    public boolean contains(Class _class) {
        return activeNodes().contains(_class.getSimpleName()); //TODO change to canonical after testing
    }

    public <T extends NodeHolder<?>> void addNodeBuilder(Class<T> _class) {
        activeNodes().add(_class.getSimpleName()); //TODO change to canonical after testing
    }

}
