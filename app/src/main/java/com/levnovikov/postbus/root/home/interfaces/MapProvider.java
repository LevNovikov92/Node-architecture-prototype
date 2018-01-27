package com.levnovikov.postbus.root.home.interfaces;

import com.google.android.gms.maps.GoogleMap;

import io.reactivex.Maybe;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

public interface MapProvider {
    Maybe<GoogleMap> getMap();
}
