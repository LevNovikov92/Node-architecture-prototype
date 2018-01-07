package com.levnovikov.system_base.lifecycle;

import io.reactivex.disposables.Disposable;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

public interface Lifecycle {

    void subscribeUntil(LifecycleEvent event, Disposable disposable);

    void subscribeUntilDestroy(Disposable disposable);
}
