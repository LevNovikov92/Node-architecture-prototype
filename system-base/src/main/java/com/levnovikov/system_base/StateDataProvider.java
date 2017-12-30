package com.levnovikov.system_base;

import android.os.Parcelable;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 29/12/17.
 */

public interface StateDataProvider {

    @Nullable
    Parcelable getStateData();
}
