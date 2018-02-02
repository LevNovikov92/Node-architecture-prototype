package com.levnovikov.feature_map.lifecycle

import android.os.Bundle

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

enum class MapLifecycleEvent constructor(val bundle: Bundle?) {
    CREATE(Bundle()), //TODO refactor it
    RESUME(null),
    PAUSE(null),
    DESTROY(null),
    ON_SAVE_INSTANCE(null),
    ON_LOW_MEMORY(null)
}
