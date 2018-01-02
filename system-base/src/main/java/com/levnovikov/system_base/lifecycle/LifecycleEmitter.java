package com.levnovikov.system_base.lifecycle;

import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

public interface LifecycleEmitter {

    Observable<LifecycleEvent> getLifecycleStream();
}
