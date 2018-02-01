package com.levnovikov.feature_map.dependency;

import android.support.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

public interface MapSetter {
    void setMap(@Nullable GoogleMap map);
}
