package com.levnovikov.feature_map.dependency

import com.levnovikov.feature_map.lifecycle.MapLifecycleEvent
import com.levnovikov.system_base.base_di.ActivityComponent

import io.reactivex.Observable

/**
 * Author: lev.novikov
 * Date: 6/1/18.
 */

interface MapDependency : ActivityComponent {
    fun mapLifecycle(): Observable<MapLifecycleEvent>
    fun mapSetter(): MapSetter
}
