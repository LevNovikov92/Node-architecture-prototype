package com.levnovikov.feature_map.dependency

import com.google.android.gms.maps.GoogleMap

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

interface MapSetter {
    fun setMap(map: GoogleMap)
}
