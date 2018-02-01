package com.levnovikov.feature_map.dependency;

import com.google.android.gms.maps.GoogleMap;

import io.reactivex.Maybe;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

public interface MapProvider {
    Maybe<GoogleMap> getMap();
}
