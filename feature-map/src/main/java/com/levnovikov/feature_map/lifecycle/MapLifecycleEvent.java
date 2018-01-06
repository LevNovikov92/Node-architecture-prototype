package com.levnovikov.feature_map.lifecycle;

import android.os.Bundle;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

public enum MapLifecycleEvent {
    CREATE(new Bundle()), //TODO refactor it
    RESUME(null),
    PAUSE(null),
    DESTROY(null),
    ON_SAVE_INSTANCE(null),
    ON_LOW_MEMORY(null);

    private final Bundle bundle;

    MapLifecycleEvent(Bundle bundle) {

        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }
}
