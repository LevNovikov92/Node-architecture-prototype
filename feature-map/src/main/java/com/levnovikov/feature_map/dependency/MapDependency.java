package com.levnovikov.feature_map.dependency;

import com.levnovikov.feature_map.MapInteractor;
import com.levnovikov.feature_map.lifecycle.MapLifecycleEvent;
import com.levnovikov.system_base.base_di.ActivityStateComponent;

import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 6/1/18.
 */

public interface MapDependency extends ActivityStateComponent {
    Observable<MapLifecycleEvent> mapLifecycle();
    MapInteractor.OnMapInitialized onMapInitialized();
}
