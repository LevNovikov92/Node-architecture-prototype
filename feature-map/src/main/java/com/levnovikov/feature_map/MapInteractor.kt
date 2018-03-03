package com.levnovikov.feature_map

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.levnovikov.feature_map.dependency.MapSetter
import com.levnovikov.feature_map.di.MapScope
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.node_state.ActivityState
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

@MapScope
class MapInteractor @Inject constructor(
        router: MapRouter,
        activityState: ActivityState,
        private val mapSetter: MapSetter) : Interactor<MapRouter>(router, activityState), OnMapReadyCallback {

    init {
        restoreState()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapSetter.setMap(googleMap)
    }
}
