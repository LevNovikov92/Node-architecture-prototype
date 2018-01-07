package com.levnovikov.system_base.base_di;

import com.levnovikov.system_base.lifecycle.Lifecycle;
import com.levnovikov.system_base.node_state.ActivityState;

/**
 * Created by lev.novikov
 * Date: 29/12/17.
 */

public interface ActivityComponent {
    ActivityState activityState();
    Lifecycle lifecycle();
}
