package com.levnovikov.system_base.state;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.levnovikov.system_base.Router;

import java.util.ArrayList;
import java.util.List;

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

    public final List<String> activeNodes = new ArrayList<>();

    public static NodeState create(Class<? extends Router> routerClass, @Nullable Parcelable data) {
        return new AutoValue_NodeState(routerClass.getSimpleName(), data);
    }

}
