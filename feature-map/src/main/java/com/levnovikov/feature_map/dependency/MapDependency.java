package com.levnovikov.feature_map.dependency;

import com.levnovikov.feature_map.MapInteractor;
import com.levnovikov.feature_map.lifecycle.MapLifecycleEvent;
import com.levnovikov.system_base.base_di.ActivityComponent;

import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 6/1/18.
 */

public interface MapDependency extends ActivityComponent {
    Observable<MapLifecycleEvent> mapLifecycle();
    MapInteractor.MapDataStream mapDataStream();
}
